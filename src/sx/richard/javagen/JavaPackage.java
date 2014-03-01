package sx.richard.javagen;

public class JavaPackage {

	private final String name;

	public JavaPackage(String name) {
		if (name == null)
			throw new IllegalArgumentException("Name must not be null");
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
