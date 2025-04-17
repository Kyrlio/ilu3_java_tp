package cartes;

public class Botte extends Probleme {

	public Botte(Type type) {
		super(type);
	}
	
	@Override
	public String toString() {
		return getType().getBotte();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Botte) {
			Botte botte = (Botte) obj;
			return this.getType().equals(botte.getType());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 31 * this.getType().hashCode();
	}

}
