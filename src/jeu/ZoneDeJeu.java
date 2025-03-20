package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import cartes.Bataille;
import cartes.Borne;
import cartes.DebutLimite;
import cartes.FinLimite;

public class ZoneDeJeu {
	private List<DebutLimite> pileDebutLimite;
	private List<FinLimite> pileFinLimite;
	private List<Bataille> pileBataille;
	private NavigableSet<Borne> collectionBorne;
	
	public ZoneDeJeu() {
		pileDebutLimite = new LinkedList<>();
		pileFinLimite = new LinkedList<>();
		pileBataille = new LinkedList<>();
		collectionBorne = new TreeSet<>();
	}

}
