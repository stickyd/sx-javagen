package sx.richard.javagen;

import java.util.List;

public class JavaGenerator {

	public static String create(JavaClass clss) {
		if (clss == null)
			throw new IllegalArgumentException("Class must not be null");
		return (new JavaGenerator(clss)).toString();
	}

	private final JavaClass clss;
	private final StringBuilder builder;
	private int tabs;

	{
		builder = new StringBuilder();
	}

	private JavaGenerator(JavaClass clss) {
		this.clss = clss;
	}

	private JavaGenerator line(String string, Object... objects) {
		tabs().append(String.format(string, objects)).semiColon().line();
		return this;
	}

	private JavaGenerator tabs() {
		for (int i = 0; i < tabs; i++) {
			builder.append("\t");
		}
		return this;
	}

	private void tabInc() {
		tabs++;
	}

	private void tabDec() {
		tabs--;
		if (tabs < 0)
			throw new IllegalStateException("Negative tabs");
	}

	private JavaGenerator append(String string) {
		builder.append(string);
		return this;
	}

	private JavaGenerator semiColon() {
		builder.append(";");
		return this;
	}

	private JavaGenerator line() {
		builder.append("\n");
		return tabs();
	}

	private <T> JavaGenerator list(List<T> modifiers) {
		for (int i = 0, n = modifiers.size(); i < n; i++) {
			append(modifiers.get(i).toString());
		}
		return this;
	}

	private JavaGenerator append(Object object) {
		return append(object.toString());
	}

	private JavaGenerator space() {
		builder.append(" ");
		return this;
	}

	private JavaGenerator clss(JavaClass clss) {
		return append(new JavaGenerator(clss).toString());
	}
	
	private JavaGenerator field(Field field) {
		append(field.getScope()).list(field.getModifiers()).append(field.type.name).space().append(field.name);
		String value = field.getValue();
		if(value != null) {
			append(" = " + value);
		}
		return semiColon();
	}

	public String toString() {
		builder.setLength(0);

		if (clss.pkg != null) {
			line("package %s", clss.pkg.getName());
			line();
		}
		append(clss.getScope()).list(clss.getModifiers())
				.append("class ").append(clss.name).append(" {");
		tabInc();
		
		boolean empty = true;
		
		for(JavaClass innerClass : clss.getClasses()) {
			line().line().clss(innerClass);
			empty = false;
		}
		if(clss.getClasses().size() > 0) {
			line();
		}
		
		if(clss.getFields().size() > 0) {
			line();
		}
		for(Field field : clss.getFields()) {
			field(field).line();
			empty = false;
		}

		tabDec();
		if(!empty) {
			line().line();
		}
		append("}");

		return builder.toString();
	}
}
