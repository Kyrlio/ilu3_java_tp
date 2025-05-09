package cartes;

public class JeuDeCartes {
	private final Configuration[] typesDeCartes = new Configuration[19];
	
	public JeuDeCartes() {
		typesDeCartes[0] = new Configuration(new Borne(25), 10);
		typesDeCartes[1] = new Configuration(new Borne(50), 10);
		typesDeCartes[2] = new Configuration(new Borne(75), 10);
		typesDeCartes[3] = new Configuration(new Borne(100), 12);
		typesDeCartes[4] = new Configuration(new Borne(200), 4);
		typesDeCartes[5] = new Configuration(new Parade(Type.FEU), 14);
		typesDeCartes[6] = new Configuration(new FinLimite(), 6);
		typesDeCartes[7] = new Configuration(new Parade(Type.ESSENCE), 6);
		typesDeCartes[8] = new Configuration(new Parade(Type.CREVAISON), 6);
		typesDeCartes[9] = new Configuration(new Parade(Type.ACCIDENT), 6);
		typesDeCartes[10] = new Configuration(new Attaque(Type.FEU), 5);
		typesDeCartes[11] = new Configuration(new DebutLimite(), 4);
		typesDeCartes[12] = new Configuration(new Attaque(Type.ESSENCE), 3);
		typesDeCartes[13] = new Configuration(new Attaque(Type.CREVAISON), 3);
		typesDeCartes[14] = new Configuration(new Attaque(Type.ACCIDENT), 3);
		typesDeCartes[15] = new Configuration(new Botte(Type.FEU), 1);
		typesDeCartes[16] = new Configuration(new Botte(Type.ESSENCE), 1);
		typesDeCartes[17] = new Configuration(new Botte(Type.CREVAISON), 1);
		typesDeCartes[18] = new Configuration(new Botte(Type.ACCIDENT), 1);
	}
	
	public String affichageJeuDeCartes() {
		StringBuilder str = new StringBuilder();
		
		for (Configuration carte : typesDeCartes) {
			if (carte != null)
				str.append(carte.getNbExemplaires() + " " + carte.getCarte() + "\n");
		}
		
		return str.toString();
	}
	
	public Carte[] donnerCartes() {
		int totalCartes = 0;
		for (Configuration carte : typesDeCartes) {
			if (carte != null)
				totalCartes += carte.nbExemplaires;
		}
		
		Carte[] cartes = new Carte[totalCartes];
		int index = 0;
		
		for (Configuration carte : typesDeCartes) {
			if (carte != null) {
				for (int i = 0; i < carte.nbExemplaires; i++)
					cartes[index++] = carte.getCarte();
			}
		}
		
		return cartes;
	}
	
	public boolean checkCount() {
		Carte[] cartes = donnerCartes();
		
		int totalCartes = cartes.length;
		int compteurBottes = 0;
		int compteurAttaques = 0;
		int compteurParades = 0;
		int compteurBornes = 0;
		int compteurDebutLim = 0;
		int compteurFinLim = 0;
		
		for (Carte carte : cartes) {
			if (carte instanceof Botte)
				compteurBottes++;
			else if (carte instanceof Attaque)
				compteurAttaques++;
			else if (carte instanceof Parade)
				compteurParades++;
			else if (carte instanceof Borne)
				compteurBornes++;
			else if (carte instanceof DebutLimite)
				compteurDebutLim++;
			else if (carte instanceof FinLimite)
				compteurFinLim++;
		}
		
//		System.out.println("totalCartes : " + totalCartes);
//		System.out.println("bottes : " + compteurBottes);
//		System.out.println("atq : " + compteurAttaques);
//		System.out.println("par : " + compteurParades);
//		System.out.println("borne : " + compteurBornes);
//		System.out.println("debut lim : " + compteurDebutLim);
//		System.out.println("fin lim : " + compteurFinLim);
		
		return 	(totalCartes == 106) &&
				(compteurBottes == 4) &&
				(compteurAttaques == 14) &&
				(compteurParades == 32) &&
				(compteurBornes == 46) &&
				(compteurDebutLim == 4) &&
				(compteurFinLim == 6);
	}
	
	
	
	
	private class Configuration {
		private int nbExemplaires;
		private Carte carte;
		
		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
	}
}
