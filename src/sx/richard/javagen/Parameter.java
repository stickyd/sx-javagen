package sx.richard.javagen;

import sx.richard.javagen.Parameter.ParameterModifiers;

public final class Parameter extends Element<ParameterModifiers>{
	
	public enum ParameterModifiers {
		
	}

	public final JavaClass type;

	public Parameter(String name, JavaClass type) {
		super(name);
		if (type == null)
			throw new IllegalArgumentException("JavaClass must not be null");
		this.type = type;
	}
}
