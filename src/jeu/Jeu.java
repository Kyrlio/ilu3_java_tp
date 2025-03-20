package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	
	public Jeu() {
		JeuDeCartes jdc = new JeuDeCartes();
		Carte[] cartes = jdc.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>(); 
		Collections.addAll(listeCartes, cartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		Carte[] cartesMelangees = new Carte[0];
		cartesMelangees = listeCartes.toArray(cartesMelangees);
		sabot = new Sabot(cartesMelangees);
	}

	public Sabot getSabot() {
		return sabot;
	}
	
	
}
