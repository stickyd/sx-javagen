package sx.richard.javagen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sx.richard.javagen.Element.ClassModifier;

public class JavaClass extends Element<ClassModifier> {

	public static final JavaClass INT = new JavaClass("int");
	public static final JavaClass STRING = new JavaClass("String");
	public static final JavaClass OBJECT = new JavaClass("Object");
	public static final JavaClass FLOAT = new JavaClass("float");
	public static final JavaClass CHAR = new JavaClass("char");
	public static final JavaClass BYTE = new JavaClass("byte");
	public static final JavaClass SHORT = new JavaClass("short");
	public static final JavaClass DOUBLE = new JavaClass("double");
	public static final JavaClass LONG = new JavaClass("long");

	public final JavaPackage pkg;

	private final Set<Method> methods;
	private final Set<JavaClass> classes;
	private final Set<Field> fields;

	{
		methods = new HashSet<Method>();
		classes = new HashSet<JavaClass>();
		fields = new HashSet<Field>();
	}

	public List<Method> getMethods() {
		List<Method> list = new ArrayList<Method>(methods);
		Collections.sort(list);
		return list;
	}

	public List<Field> getFields() {
		List<Field> list = new ArrayList<Field>(fields);
		Collections.sort(list);
		return list;
	}

	public List<JavaClass> getClasses() {
		List<JavaClass> list = new ArrayList<JavaClass>(classes);
		Collections.sort(list);
		return list;
	}

	public void add(JavaClass clss) {
		if (clss == null)
			throw new IllegalArgumentException("Class must not be null");
		if (!classes.add(clss))
			throw new IllegalStateException("Class already within this class");
	}

	public JavaClass(String name, JavaPackage pkg) {
		super(name);
		if (pkg == null)
			throw new IllegalArgumentException("JavaPackage must not be null");
		this.pkg = pkg;
	}

	public JavaClass(String name) {
		super(name);
		this.pkg = null;
	}

	public void add(Field field) {
		if (field == null)
			throw new IllegalArgumentException("Field must not be null");
		if (!fields.add(field))
			throw new IllegalStateException("Field already within this class");
	}

	public void add(Method method) {
		if (method == null)
			throw new IllegalArgumentException("Method must not be null");
		if (!methods.add(method))
			throw new IllegalStateException("Method already within this class");
	}

}
