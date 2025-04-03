package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;

public class ZoneDeJeu {
	private List<Limite> pileLimite;
	private List<Bataille> pileBataille;
	private Set<Borne> collectionBorne; // Pas besoin que les bornes soient triées donc HashSet
	
	public ZoneDeJeu() {
		pileLimite = new LinkedList<>();
		pileBataille = new LinkedList<>();
		collectionBorne = new HashSet<>();
	}
	
	/**
	 * 
	 * @return la limite de vitesse en cours du joueur en int 
	 */
	public int donnerLimitationVitesse() {
		if (pileLimite.isEmpty() || pileLimite.getFirst() instanceof FinLimite)
			return 200;
		else 
			return 50;
	}
	
	/**
	 * 
	 * @return le nombre de km parcourus par le joueur en int
	 */
	public int donnerKmParcourus() {
		int km = 0;
		for (Borne borne : collectionBorne) {
			km += borne.getKm();
		}
		return km;
	}
	
	/**
	 *
	 * @param c de type Borne, Limite ou Bataille
	 */
	public void deposer(Carte c) {
		switch (c) {
			case Borne borne: collectionBorne.add(borne); break;
			case Limite limite: pileLimite.addFirst(limite); break;
			case Bataille bataille: pileBataille.addFirst(bataille); break;
			default:
				break;
		}
	}
	
	/**
	 * 
	 * @return true si le joueur peut avancer
	 */
	public boolean peutAvancer() {
		return !pileBataille.isEmpty() && 
				pileBataille.getFirst().equals(Cartes.FEU_VERT);
	}
	
	private boolean estDepotFeuVertAutorise() {
		return 	pileBataille.isEmpty() ||
				pileBataille.getFirst().equals(Cartes.FEU_ROUGE) ||
				!pileBataille.getFirst().equals(Cartes.FEU_VERT);
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return 	peutAvancer() &&
				borne.getKm() <= donnerLimitationVitesse() &&
				donnerKmParcourus() + borne.getKm() <= 1000;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite)
			return pileLimite.isEmpty() || pileLimite.getFirst() instanceof FinLimite;
		else
			return pileLimite.getFirst() instanceof DebutLimite;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) return peutAvancer();
		else if (bataille instanceof Parade) {
			if (bataille.equals(Cartes.FEU_VERT)){
				return 	estDepotFeuVertAutorise();
			} else { 
				System.out.println(pileBataille.getFirst().getType());
				System.out.println(bataille.getType());
				return !pileBataille.isEmpty() &&
					pileBataille.getFirst() instanceof Attaque &&
					pileBataille.getFirst().getType().equals(bataille.getType());	
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param carte de type Borne, Limite ou Bataille
	 * @return true si la carte peut être déposée
	 */
	public boolean estDepotAutorise(Carte carte) {
//		System.out.println("Affichage zone de jeu avant depot");
//		System.out.println("Limite : " + pileLimite);
//		System.out.println("Borne : " + collectionBorne);
//		System.out.println("Bataille : " + pileBataille);
		switch (carte) {
		case Borne borne : return estDepotBorneAutorise(borne);
		case Limite limite : return estDepotLimiteAutorise(limite);
		case Bataille bataille : return estDepotBatailleAutorise(bataille);
		
		default : return false;
		}
	}

	
	
	
	
	
}
