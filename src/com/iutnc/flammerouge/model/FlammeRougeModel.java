package com.iutnc.flammerouge.model;

import java.util.ArrayList;
import java.util.List;

public class FlammeRougeModel {
	
	// ATTRIBUTS
	private List<Joueur> joueurs;
	private Etape etape;
	public static final int nbMaxJoueurs = Couleur.values().length; // On limite le nombre de joueurs au nombre de couleurs possibles
	
	// CONSTRUCTEUR
	public FlammeRougeModel(Etape etape) {
		this.etape = etape;
		joueurs = new ArrayList<Joueur>();
	}
	
	// METHODES
	/**
	 * Ajoute un joueur au jeu
	 * @param j le joueur à ajouter
	 * @return true si le joueur a pu être ajouté, false s'il y était déjà ou s'il n'y a plus de place pour d'autres joueurs
	 */
	public boolean ajouterJoueur(Joueur j) {
		if (joueurs.size() < nbMaxJoueurs && joueurs.indexOf(j) == -1) {
			joueurs.add(j);
			return true;
		}
		return false;
	}
	
	/**
	 * Renvoie la liste des joueurs
	 * @return la liste des joueurs
	 */
	public List<Joueur> getJoueurs() {
		return joueurs;
	}
	
	/**
	 * Renvoie l'étape actuellement jouée
	 * @return l'étape actuellement jouée
	 */
	public Etape getEtape() {
		return etape;
	}
	
	/**
	 * Renvoie le nombre de joueurs dans la partie
	 * @return le nombre de joueurs dans la partie
	 */
	public int getNbJoueurs() {
		return joueurs.size();
	}
	
	public void setEtape(Etape etape) {
		this.etape = etape;
	}
	
	public static int getNbMaxjoueurs() {
		return nbMaxJoueurs;
	}
}
