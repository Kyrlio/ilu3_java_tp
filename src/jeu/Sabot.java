package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;
import cartes.JeuDeCartes;

public class Sabot implements Iterable<Carte>{
	private int nbCartes;
	private Carte[] cartes;
	private int nombreOperations = 0;

	public Sabot(Carte[] cartes) {
		JeuDeCartes jeu = new JeuDeCartes();
		this.cartes = cartes;
		this.nbCartes = cartes.length;
		
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) throws IllegalAccessException {
		if (nbCartes >= cartes.length)
			throw new IllegalAccessException("La pioche est pleine !");
		cartes[nbCartes] = carte;
		nbCartes++;
		nombreOperations++;
	}

	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	public Carte piocher() {
		Iterator<Carte> it = this.iterator();
		if (!it.hasNext())
			throw new NoSuchElementException("Le sabot est vide ! Impossible de piocher.");
		Carte carte = it.next();
		it.remove();
		System.out.println("Je pioche " + carte.toString());
		return carte;
	}
	
	
	
	
	
	private class Iterateur implements Iterator<Carte>{
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = nombreOperations;
		
		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}

		@Override
		public Carte next() {
			verificationConcurrence();
			
			if (!hasNext())
				throw new NoSuchElementException();
			Carte carte = cartes[indiceIterateur];
			indiceIterateur++;
			nextEffectue = true;
			return carte;
		}
		
		@Override
		public void remove() {
			verificationConcurrence();
			if (nbCartes < 1 || !nextEffectue)
				throw new IllegalStateException();
			for (int i = indiceIterateur-1; i<nbCartes-1; i++)
				cartes[i] = cartes[i+1];
			cartes[nbCartes-1] = null;
			nbCartes--;
			nextEffectue = false;
			indiceIterateur--;
			nombreOperations++;
			nombreOperationsReference++;
		}
		
		private void verificationConcurrence() {
			if (nombreOperations != nombreOperationsReference)
				throw new ConcurrentModificationException();
		}
		
	}
	
	

}
