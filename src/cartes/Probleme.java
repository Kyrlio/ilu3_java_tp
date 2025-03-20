package cartes;

public abstract class Probleme extends Carte {
	private Type type;

	protected Probleme(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Probleme) {
			Probleme pb = (Probleme) obj;
			return super.equals(obj) && type.equals(pb.getType());
		}
		return false;
	}

}
