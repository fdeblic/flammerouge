package com.iutnc.flammerouge.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PileCartes {
	
	// ATTRIBUTS
	private List<Integer> cartes = new ArrayList<Integer>();
	
	// CONSTRUCTEURS
	public PileCartes() {
	}
	
	public PileCartes(Integer[] valeurs) {
		ajouterCartes(valeurs);
	}
	
	// METHODES
	/**
	 * Renvoie la valeur de la carte située au dessus du paquet (0=bas / size()-1=dessus)
	 * @return la valeur de la carte
	 */
	public int getCarteDessus() {
		if (cartes.size() != 0) {
			return cartes.get(cartes.size()-1);
		} else {
			return -1;
		}
	}
	
	/**
	 * Ajoute une carte au jeu
	 * @param valeur la valeur de la carte à ajouter
	 */
	public void ajouterCarte(int valeur) {
		cartes.add(valeur);
	}
	
	/**
	 * Ajoute des cartes au jeu
	 * @param valeurs le tableau des valeurs des cartes à ajouter
	 */
	public void ajouterCartes(Integer[] valeurs) {
		for (int i = 0 ; i < valeurs.length ; i++) {
			ajouterCarte(valeurs[i]);
		}
	}

	/**
	 * Retire une carte désignée du paquet
	 * @param valeur la valeur de la carte à retirer
	 * @return true si la carte a été retirée, false sinon
	 */
	public boolean retirerCarte(int valeur) {
		int pos = cartes.indexOf(valeur);
		if (pos == -1) {
			return false;
		} else {
			cartes.remove(pos);
			return true;
		}
	}
	
	/**
	 * Mélange la pile de cartes
	 */
	public void melanger() {
	    Collections.shuffle(cartes);
	}

	/**
	 * Retire la carte du dessus et envoie la valeur de celle-ci
	 * @return la valeur de la carte tirée
	 */
	public int piocherCarteDessus() {
		int val = getCarteDessus();
		if (val != -1) {
			cartes.remove(cartes.size()-1);
		}
		return val;
	}
	
	/**
	 * Renvoie une représentation en tableau des cartes
	 * @return
	 */
	public Integer[] getCartes() {
		return cartes.toArray(new Integer[0]);
	}

	/**
	 * Supprime toutes les cartes de la pile
	 */
	public void vider() {
		cartes.clear();
	}
	
	@Override
	public String toString() {
		return cartes.toString();
	}
}
