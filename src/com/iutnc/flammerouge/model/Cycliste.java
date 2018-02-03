package com.iutnc.flammerouge.model;

public abstract class Cycliste {
	
	// ATTRIBUTS
	protected PileCartes pioche;
	protected PileCartes defausse;
	protected PileCartes cartesJouees;
	protected PileCartes cartesEnMain;
	protected Couleur couleur;
	
	// CONSTRUCTEUR
	public Cycliste(Couleur c) {
		pioche       = new PileCartes();
		defausse     = new PileCartes();
		cartesJouees = new PileCartes();
		cartesEnMain = new PileCartes();
		couleur = c;
	}
	
	// MÉTHODES
	/**
	 * Récupère la carte du dessus de la pioche et la met dans sa main
	 * @return la carte ajoutée à la main
	 */
	public int piocherCarte() {
		int carte = pioche.piocherCarteDessus();
		
		// Si plus de cartes dans la pioche
		if (carte == -1) {
			// On met les cartes de la défausse dans la pioche
			pioche.ajouterCartes(defausse.getCartes());
			pioche.melanger();
			defausse.vider();
			
			// On tire une carte
			carte = pioche.piocherCarteDessus();
		}
		
		if (carte != -1) {
			cartesEnMain.ajouterCarte(carte);
		}
		
		return carte;
	}
	
	/**
	 * Prend N cartes de la pioche et les met dans sa main
	 * @param nb le nombre de cartes à prendre
	 * @return tableau des cartes ajoutées à la main
	 */
	public int[] piocherCartes(int nb) {
		int[] cartes = new int[nb];
		for (int i = 0 ; i < nb ; i++) {
			int carte = piocherCarte();
			if (carte != -1) {
				cartes[i] = carte;
			} else {
				// Plus de cartes dans la pioche (ni la défausse)
				
				// Change la taille du tableau pour adapter au nombre de cartes qu'on a pu tirer
				int[] tab = new int[i];
				for (int j = 0 ; j < tab.length ; j++) {
					tab[j] = cartes[j];
				}
				cartes = tab;
				break;
			}
		}
		
		return cartes;
	}
	
	/**
	 * Mélange les cartes de la pioche
	 */
	public void melangerPioche() {
		pioche.melanger();
	}
	
	/**
	 * Ajoute une carte fatigue à la défausse
	 */
	public void ajouterCarteFatigue() {
		defausse.ajouterCarte(2);
	}
	
	/**
	 * Joue la carte donnée en paramètres et met les autres cartes en main dans la défausse
	 * @param valeur la valeur de la carte jouée
	 */
	public void jouerCarte(int valeur){
		// Déplace la carte jouée dans le tas du même nom
		cartesEnMain.retirerCarte(valeur);
		cartesJouees.ajouterCarte(valeur);
		
		// Déplace les autres cartes dans la défausse
		defausse.ajouterCartes(cartesEnMain.getCartes());
		cartesEnMain.vider();
	}
	
	/**
	 * Renvoie la valeur de la dernière carte jouée
	 * @return valeur entière de la carte
	 */
	public int getCarteJouee() {
		return cartesJouees.getCarteDessus();
	}

	/**
	 * Renvoie la couleur du cycliste
	 * @return la couleur du cycliste
	 */
	public Couleur getCouleur() {
		return couleur;
	}

	/**
	 * Renvoie une chaîne représentant la classe
	 * @return "cycliste"
	 */
	public String getNomClasse() {
		return "cycliste";
	}
	
	@Override
	public String toString() {
		return getNomClasse() + " " + getCouleur().ordinal();
	}
}
