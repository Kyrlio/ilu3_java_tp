package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;

public class ZoneDeJeu {
	private List<Limite> pileLimite;
	private List<Bataille> pileBataille;
	private Set<Borne> collectionBorne; // Pas besoin que les bornes soient triées donc HashSet
	
	public ZoneDeJeu() {
		pileLimite = new LinkedList<>();
		pileBataille = new LinkedList<>();
		collectionBorne = new HashSet<>();
	}
	
	public int donnerLimitationVitesse() {
		if (pileLimite.isEmpty() || pileLimite.getFirst() instanceof FinLimite)
			return 200;
		else 
			return 50;
	}
	
	public int donnerKmParcourus() {
		int km = 0;
		for (Borne borne : collectionBorne) {
			km += borne.getKm();
		}
		return km;
	}
	
	public void deposer(Carte c) {
		if (c instanceof Borne borne)
			collectionBorne.add(borne);
		if (c instanceof Limite limite)
			pileLimite.add(limite);
		if (c instanceof Bataille bataille)
			pileBataille.add(bataille);
	}

	
	
	
	
	
}
