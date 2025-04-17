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
import cartes.Botte;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	private List<Limite> pileLimite;
	private List<Bataille> pileBataille;
	private List<Borne> collectionBorne;
	private Set<Botte> bottes;
	
	public ZoneDeJeu() {
		pileLimite = new LinkedList<>();
		pileBataille = new LinkedList<>();
		collectionBorne = new ArrayList<>();
		bottes = new HashSet<>();
	}
	
	/**
	 * 
	 * @return true si le joueur possède la carte prioritaire
	 */
	private boolean estPrioritaire() {
		return bottes.contains(Cartes.PRIORITAIRE);
	}
	
	/**
	 * 
	 * @return la limite de vitesse en cours du joueur en int 
	 */
	public int donnerLimitationVitesse() {
		if (pileLimite.isEmpty() || pileLimite.getFirst() instanceof FinLimite || estPrioritaire())
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
			case Borne borne: collectionBorne.addFirst(borne); break;
			case Limite limite: pileLimite.addFirst(limite); break;
			case Bataille bataille: pileBataille.addFirst(bataille); break;
			case Botte botte: bottes.add(botte); break;
			default:
				break;
		}
	}
	
	/**
	 * 
	 * @return true si le joueur peut avancer
	 */
	public boolean peutAvancer() {
		if (pileBataille.isEmpty() && estPrioritaire()) return true;
		
		if (!pileBataille.isEmpty()) {
			Bataille first = pileBataille.getFirst();
			if (first.equals(Cartes.FEU_VERT)) return true;
			if (first instanceof Parade && estPrioritaire()) return true;
			if (first.equals(new Attaque(Type.FEU)) && estPrioritaire()) return true;
			if (first instanceof Attaque && !first.equals(new Attaque(Type.FEU))) {
				Type firstType = first.getType();
				Botte botte = new Botte(firstType);
				if (bottes.contains(botte) && estPrioritaire()) return true;
			}
		}
		
		return false;
	}
	
	private boolean estDepotFeuVertAutorise() {
		if (estPrioritaire()) return false;
		if (pileBataille.isEmpty()) return true;
		
		Bataille first = pileBataille.getFirst();
		if(first.equals(Cartes.FEU_ROUGE)) return true;
		else if (first instanceof Parade && !first.equals(Cartes.FEU_VERT)) return true;
		else if (first instanceof Attaque) {
			Type firstType = first.getType();
			if (bottes.contains(new Botte(firstType))) return true;
		}
		
		return false;
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return 	peutAvancer() &&
				borne.getKm() <= donnerLimitationVitesse() &&
				donnerKmParcourus() + borne.getKm() <= 1000;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if (bottes.contains(Cartes.PRIORITAIRE)) return false;
		if (limite instanceof DebutLimite && (pileLimite.isEmpty() || pileLimite.getFirst() instanceof FinLimite)) return true;
		if (limite instanceof FinLimite && pileLimite.getFirst() instanceof DebutLimite) return true;
		if (estPrioritaire()) return false;
		return false;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque && peutAvancer()) {
			if (pileBataille.isEmpty() || !pileBataille.getFirst().equals(bataille)) return true;
			else return false;
		}
		else if (bataille instanceof Parade) {
			if (bataille.equals(Cartes.FEU_VERT)) {
				return estDepotFeuVertAutorise();
			} else if (!pileBataille.isEmpty() &&
						pileBataille.getFirst() instanceof Attaque &&
						pileBataille.getFirst().getClass().equals(bataille.getClass())) 
				return true;
		}
		Type type = bataille.getType();
		if (bottes.contains(new Botte(type))) return false;
		return false;
	}
	
	private boolean estDepotBotteAutorise(Botte botte) {
		return !bottes.contains(botte);
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
		case Botte botte : return estDepotBotteAutorise(botte);
		default : return false;
		}
	}


	
	
	
	
	
}
