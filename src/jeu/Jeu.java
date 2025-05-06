package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private Set<Joueur> joueurs;
	private final int NBCARTES = 6;
	private Iterator<Joueur> iter;
	
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
	
	public Set<Joueur> getJoueurs() {
		return joueurs;
	}

	public void inscrire(Joueur...joueurs) {
		Collections.addAll(this.joueurs, joueurs);
	}
	
	public void distribuerCartes() {
		for (Joueur joueur : joueurs) {
			for (int i = 0; i < 6; i++) {
				joueur.donner(sabot.piocher());
			}
		}
	}
	
	private boolean checkNbCarte() {
		for (Joueur joueur : joueurs) {
			if (joueur.getMain().getNbCarte() > 6)
				return false;
		}
		return true;
	}
	
	public StringBuilder jouerTour(Joueur joueur) {
		Set<Joueur> participants = new LinkedHashSet<>(joueurs);
				
		StringBuilder str = new StringBuilder();
		Carte carte = joueur.prendreCarte(sabot);
		str.append("Le joueur " + joueur.toString() + " a pioche " + carte.toString() + "\n");
		str.append("Il a dans sa main : " + joueur.getMain());
		
		Coup coup = joueur.choisirCoup(participants);
		str.append("\n" + joueur.toString() + " " + coup);
//		if (!checkNbCarte()) throw new IllegalArgumentException("Le joueur a plus de 6 cartes !");
		return str;
	}
	
	public Joueur donnerJoueurSuivant() {
		if (!iter.hasNext()) {
			iter = joueurs.iterator();
		}
		return iter.next();
	}
	
	public String lancer() {
		boolean fin = false;
		Joueur gagnant = null;
		distribuerCartes();
		while (!fin || !sabot.estVide()) {
			if (sabot.estVide()) break;
			Joueur joueur = donnerJoueurSuivant();
			System.out.println("C'est au tour de : " + joueur.toString());
			
			System.out.println(jouerTour(joueur));
			if (joueur.donnerKmParcourus() >= 1000) {
				fin = true;
				gagnant = joueur;
				break;
			}
			System.out.println("");
			System.out.println(joueur.afficherEtatJoueur());
			System.out.println("");
		}
		if (sabot.estVide()) System.out.println("Le sabot est vide.");
		System.out.println("");
		
		gagnant = calculerGagnant();
		return "Le gagnant est : " + gagnant + " avec " + gagnant.donnerKmParcourus() + " km.";
	}
	
	private Joueur calculerGagnant() {
		Joueur gagnant = null;
		int maxkm = 0;
		for (Joueur joueur : joueurs) {
			if (joueur.donnerKmParcourus() > maxkm) {
				maxkm = joueur.donnerKmParcourus();
				gagnant = joueur;
			}
			
		}
		
		return gagnant;
	}
	
}
