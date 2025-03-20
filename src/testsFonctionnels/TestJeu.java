package testsFonctionnels;

import cartes.Carte;
import jeu.Jeu;

public class TestJeu {

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		for (Carte carte : jeu.getSabot()) {
			System.out.println(carte);
		}

	}

}
