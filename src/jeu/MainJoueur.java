package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte>{
	private List<Carte> main;
	
	protected MainJoueur() {
		main = new ArrayList<>();
	}
	
	public void prendre(Carte carte) {
		main.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert main.contains(carte);
		main.remove(carte);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Main du joueur : ");
		for (Carte carte : main) {
			str.append(carte.toString());
			str.append(", ");
		}
		
		str.deleteCharAt(str.length()-2);
		str.append("\n");
		return str.toString();
		
		// return main.toString();
	}
	
	public int getNbCarte() {
		return main.size();
	}

	@Override
	public Iterator<Carte> iterator() {
		return main.iterator();
	}
}
