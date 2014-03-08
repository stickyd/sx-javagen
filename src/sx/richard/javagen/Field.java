package sx.richard.javagen;

import sx.richard.javagen.Element.FieldModifier;

public class Field extends Element<FieldModifier> {

	public final JavaClass type;

	private String value;

	public Field(JavaClass type, String name) {
		super(name);
		this.type = type;
	}

	public String getValue() {
		if(type == JavaClass.STRING) {
			return "\"" + value + "\"";
		}
		return value;
	}

	/**
	 * @param value
	 *            the value to use
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
