package jeu;

import cartes.Borne;
import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur main;
	
	public Joueur(String nom) {
		this.nom = nom;
		zoneDeJeu = new ZoneDeJeu();
		main = new MainJoueur();
	}
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) return null;
		Carte carte = sabot.piocher();
		donner(carte);
		return carte;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	
	
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur) {
			Joueur joueur = (Joueur) obj;
			return this.nom.equals(joueur.nom);
		}
		return false;
	}
	
	
	// ----- GETTERS -----
	
	@Override
	public String toString() {
		return nom;
	}

	public ZoneDeJeu getZoneDeJeu() {
		return zoneDeJeu;
	}

	public MainJoueur getMain() {
		return main;
	}
	
	
}
