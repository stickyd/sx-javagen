package sx.richard.javagen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Element<M extends Enum<M>> implements Comparable<Element<?>> {

	public enum Scope {
		PUBLIC, PROTECTED, DEFAULT, PRIVATE;

		public String toString() {
			switch (this) {
			case DEFAULT:
				return "";
			}
			return super.toString().toLowerCase() + " ";
		}
	}

	public enum ClassModifier {
		STATIC, FINAL;

		public String toString() {
			return super.toString().toLowerCase() + " ";
		}
	}

	public enum MethodModifier {
		STATIC, SYNCHRONIZED, FINAL;

		public String toString() {
			return super.toString().toLowerCase()+ " ";
		}
	}

	public enum FieldModifier {
		STATIC, TRANSIENT, FINAL;

		public String toString() {
			return super.toString().toLowerCase()+ " ";
		}
	}

	public final String name;

	private String description;
	private Scope scope;
	private final Set<M> modifiers;

	{
		scope = Scope.PUBLIC;
		modifiers = new HashSet<M>();
	}

	public Element(String name) {
		if (name == null)
			throw new IllegalArgumentException("Name must not be null");
		this.name = name;
	}

	public List<M> getModifiers() {
		List<M> list = new ArrayList<M>();
		for (M m : modifiers) {
			list.add(m);
		}
		Collections.sort(list);
		return list;
	}

	public void removeModifiers() {
		modifiers.clear();
	}

	@SuppressWarnings("unchecked")
	public void setModifiers(M modifier, M... modifiers) {
		if (modifier == null)
			throw new IllegalArgumentException("Modifier must not be null");
		this.modifiers.clear();
		this.modifiers.add(modifier);
		for (M m : modifiers) {
			if (m == null)
				throw new IllegalArgumentException("Modifier must not be null");
			this.modifiers.add(m);
		}
	}

	public boolean addModifier(M modifier) {
		return modifiers.add(modifier);
	}

	public boolean removeModifier(M modifier) {
		return modifiers.remove(modifier);
	}

	public void setScope(Scope scope) {
		if (scope == null)
			throw new IllegalArgumentException("Scope must not be null");
		this.scope = scope;
	}

	public Scope getScope() {
		return scope;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int compareTo(Element<?> arg0) {
		return name.compareTo(arg0.name);
	}

}
