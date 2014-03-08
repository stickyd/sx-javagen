package sx.richard.javagen.test;

import sx.richard.javagen.Element.ClassModifier;
import sx.richard.javagen.Element.Scope;
import sx.richard.javagen.Field;
import sx.richard.javagen.JavaClass;
import sx.richard.javagen.JavaGenerator;
import sx.richard.javagen.JavaPackage;

public class JavaGeneratorTest {
	
	public static void main(String[] args) {
		
		
		JavaPackage pkg = new JavaPackage("sx.richard.output");
		JavaClass clss = new JavaClass("MyClass", pkg);
		clss.setModifiers(ClassModifier.FINAL);
		
		Field f = new Field(JavaClass.STRING, "myString");
		f.setValue("This is the value");
		clss.add(f);
		
		JavaClass inner = new JavaClass("InnerClass");
		inner.setModifiers(ClassModifier.STATIC, ClassModifier.FINAL);
		inner.setScope(Scope.PROTECTED);
		clss.add(inner);
		
		String output = JavaGenerator.create(clss);
		System.out.println(output);

	}

}
