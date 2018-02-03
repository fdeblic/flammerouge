package com.iutnc.flammerouge.model;

import java.util.ArrayList;

public class Joueur {
	// ATTRIBUTS

	/** Nombre de cyclistes par joueur */
	private static final int nbCyclistesParJoueur = 2;
	private Couleur couleur;
	private final ArrayList<Cycliste> cyclistes = new ArrayList<Cycliste>();
	
	// CONSTRUCTEUR
	public Joueur(Couleur col) {
		couleur = col;
		ajouterCycliste(new Rouleur(couleur));
		ajouterCycliste(new Sprinteur(couleur));
	}
	
	// MÉTHODES
	/**
	 * Tente d'ajouter un cycliste
	 * @param c le cycliste à ajouter
	 * @return false si le joueur n'a plus de places dans l'équipe
	 */
	public boolean ajouterCycliste(Cycliste c) {
		if (cyclistes.size() < nbCyclistesParJoueur) {
			cyclistes.add(c);
			return true;
		}
		return false;
	}
	
	/**
	 * @return la couleur du joueur
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	/**
	 * Renvoie la liste des cyclistes
	 * @return
	 */
	public ArrayList<Cycliste> getCyclistes() {
		return cyclistes;
	}
	
	
}
