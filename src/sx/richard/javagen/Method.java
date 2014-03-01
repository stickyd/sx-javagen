package sx.richard.javagen;

import sx.richard.javagen.Element.MethodModifier;

public class Method extends Element<MethodModifier> {

	public final Code code;
	public final Parameter[] parameters;
	public final JavaClass returnClass;

	public Method(String name, Code code, JavaClass returnClass, Parameter... parameters) {
		super(name);
		if (code == null)
			throw new IllegalArgumentException("Code must not be null");
		this.code = code;
		this.returnClass = returnClass;
		this.parameters = parameters;
	}

}
