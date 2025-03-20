package jeu;

import cartes.Attaque;
import cartes.Botte;
import cartes.Carte;
import cartes.Parade;
import cartes.Type;

public interface Cartes {
	public static final Carte PRIORITAIRE = new Botte(Type.FEU);
	public static final Carte FEU_ROUGE = new Attaque(Type.FEU);
	public static final Carte FEU_VERT = new Parade(Type.FEU);
}
