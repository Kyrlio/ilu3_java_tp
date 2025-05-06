package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import cartes.Borne;
import cartes.Carte;
import cartes.Cartes;

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
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> coupsPossibles = new HashSet<>();
		for (Joueur participant : participants) {
			for (Carte carte : main) {
				System.out.println("test coup " + carte.toString() + " a : " + participant.toString());
				Coup coup = new Coup(carte, this, participant);
				if (coup.estValide()) coupsPossibles.add(coup);
			}
		}
		System.out.println(coupsPossibles.toString());
		return coupsPossibles;
	}

	public Set<Coup> coupsDefausse(){
		Set<Coup> coupsDefausse = new HashSet<>();
		for (Carte carte : main) {
			Coup coup = new Coup(carte, this, null);
			if (coup.estValide()) coupsDefausse.add(coup);
		}
		return coupsDefausse;
	}
	
	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}
	
	private Coup randomCoupChoisi(Set<Coup> coup) {
		Iterator<Coup> iter = coup.iterator();
		Random random = new Random();
		Coup next = null;
		if (coup.size() <= 0) return next;
		int n = random.nextInt(coup.size());
		for (int i = 0; i < n; i++) {
			next = iter.next();
			if (i == n) {
				break;
			}
		}
		return next;
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupsPossibles = coupsPossibles(participants);
		if (coupsPossibles.isEmpty())
			randomCoupChoisi(coupsDefausse());
		return randomCoupChoisi(coupsPossibles);
	}
	
	public String afficherEtatJoueur() {
		boolean limiteVitesse = false;
		if (zoneDeJeu.donnerLimitationVitesse() == 50) limiteVitesse = true;
		return 	"Bottes : " + zoneDeJeu.afficherBottes() + "\n"
				+ "Limitation de vitesse : " + limiteVitesse + "\n"
				+ "Sommet pile bataille : " + zoneDeJeu.getFirstBataille() + "\n"
				+ "Main : " + main.toString() + "\n"
				+ "Km parcourus : " + donnerKmParcourus();
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
