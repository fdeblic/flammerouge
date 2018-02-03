package com.iutnc.flammerouge.model;

import java.util.LinkedList;

/**
 * Classe représentant les cases du circuit (Etape)
 * @author francois
 *
 */
public class Case {
	// ATTRIBUTS
	/** Nombre de files sur la route (= nb max de cyclistes sur une case) */
	public static final int nbFiles = 2;
	/** Représentation des cyclistes sur les différentes files */
	private LinkedList<Cycliste> cyclistes = new LinkedList<Cycliste>();
	/** Montée d'un col */
	private boolean montee;
	/** Descente d'une pente */
	private boolean descente;
	
	
	// CONSTRUCTEURS
	
	/**
	 * Crée une case plate (ni montée ni descente)
	 */
	public Case() {
		montee = false;
		descente = false;
	}
	
	/**
	 * Crée une case pour la montée ou descente. 
	 * @param estMontee true si la case est une montée
	 * @param estDescente true si la case est une descente
	 * @throws CaseParameterException si estMontee et estDescente sont tous les deux vrais
	 */
	public Case(boolean estMontee, boolean estDescente) throws CaseParameterException {
		if (estMontee && estDescente) {
			throw new CaseParameterException("Une case ne peut être déclarée montée et descente en même temps");
		}
		montee = estMontee;
		descente = estDescente;
	}
	
	
	// METHODES
	/**
	 * Tente d'ajouter un cycliste sur la case
	 * @param c le cycliste à ajouter
	 * @return true si réussi, false s'il n'y a pas de place
	 */
	public boolean ajouterCycliste(Cycliste c) {
		// Si on n'a pas atteint le max de cyclistes sur la case et si le cycliste n'est pas déjà sur la case
		if (cyclistes.size() < nbFiles && cyclistes.indexOf(c)==-1) {
			cyclistes.addLast(c);
			return true;
		}
		return false;
	}
	
	/**
	 * Retire un cycliste de la case
	 * @param c le cycliste à retirer
	 * @return true si retiré, false si le cycliste n'est pas sur cette case
	 */
	public boolean retirerCycliste(Cycliste c) {
		return cyclistes.remove(c);
	}
	
	/**
	 * Vérifie si la case contient des cyclistes
	 * @return true s'il n'y a pas de cyclistes, false sinon
	 */
	public boolean estVide() {
		return cyclistes.size() == 0;
	}
	
	/**
	 * Renvoie la liste des cyclistes sur la case
	 * @return la liste des cyclistes sur la case
	 */
	public LinkedList<Cycliste> getCyclistes() {
		return cyclistes;
	}

	/**
	 * Teste la présence d'un cycliste sur la case
	 * @param c le cycliste à chercher
	 * @return true si trouvé, false sinon
	 */
	public boolean contient(Cycliste c) {
		return cyclistes.indexOf(c) != -1;
	}
	
	/**
	 * Attribue une carte fatigue à tous les cyclistes de la case
	 */
	public void attribuerCartesFatigue() {
		for (int i = 0 ; i < cyclistes.size(); i++) {
			cyclistes.get(i).ajouterCarteFatigue();
		}
	}
	
	public void setMontee(boolean montee) {
		this.montee = montee;
	}
	
	public void setDescente(boolean descente) {
		this.descente = descente;
	}
	
	public boolean isMontee() {
		return montee;
	}

	public boolean isDescente() {
		return descente;
	}
}
