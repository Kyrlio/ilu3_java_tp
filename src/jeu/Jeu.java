package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private Set<Joueur> joueurs;
	
	public Jeu() {
		JeuDeCartes jdc = new JeuDeCartes();
		Carte[] cartes = jdc.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>(); 
		Collections.addAll(listeCartes, cartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		Carte[] cartesMelangees = new Carte[0];
		cartesMelangees = listeCartes.toArray(cartesMelangees);
		sabot = new Sabot(cartesMelangees);
		joueurs = new LinkedHashSet<>();
	}

	public Sabot getSabot() {
		return sabot;
	}
	
	public void inscrire(Joueur...joueurs) {
		Collections.addAll(this.joueurs, joueurs);
	}
	
	public void distribuerCartes() {
		for (Joueur joueur : joueurs) {
			for (int i = 0; i < 7; i++) {
				joueur.donner(sabot.piocher());
			}
		}
	}
	
	
}
