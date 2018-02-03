package com.iutnc.flammerouge.view;

import com.iutnc.flammerouge.controller.FlammeRougeController;
import com.iutnc.flammerouge.controller.FlammeRougeControllerI;
import com.iutnc.flammerouge.model.Cycliste;

public interface FlammeRougeViewI {
	/** Méthode pour raffraîchir l'affichage */
	public void rafraichir();

	/**
	 * Méthode demandant à l'utilisateur quelle carte choisir
	 * @param cartes le jeu parmi lequel choisir la carte
	 * @param c le cycliste dont il faut choisir la carte
	 * @return la carte choisie
	 */
	public int choisirCarteJoueurs(int[] cartes, Cycliste c);
	
	/**
	 * Demande à l'utilisateur le nombre de joueurs
	 * @param max nombre maximal de joueurs autorisé
	 * @return le nombre de joueurs indiqué par l'utilisateur
	 */
	int demanderNbJoueurs(int max);
	
	/**
	 * Fait placer le cycliste sur le départ à l'utilisateur
	 * @param c le cycliste à placer
	 * @param posMax l'emplacement de la ligne de départ
	 * @return la place de la case choisie
	 */
	int demanderEmplacementDepart(Cycliste c, int posMax);
	
	/**
	 * Affiche un message à l'écran
	 * @param message le message à afficher
	 */
	void afficherMessage(String message);
	
	/**
	 * Affiche le vainqueur
	 * @param premierCycliste le cycliste qui est premier
	 */
	public void animationVainqueur(Cycliste premierCycliste);
	
	/**
	 * Anime l'avancée d'un cycliste
	 * @param c le cycliste à avancer
	 * @param nbCases le nombre de cases de déplacement
	 */
	public void avancerCycliste(Cycliste c, int nbCases);
	
	/**
	 * Anime la distribution d'une carte fatigue
	 * @param c le cycliste à qui la carte est donnée
	 */
	public void attribuerCarteFatigue(Cycliste c);
	
	/**
	 * Annonce qu'un cycliste n'a plus de cartes
	 * @param c le cycliste n'ayant plus de cartes
	 */
	public void plusDeCartes(Cycliste c);

	public void setController(FlammeRougeController controller);
}
