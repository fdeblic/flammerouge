package com.iutnc.flammerouge.model;

import java.util.ArrayList;

public class Etape {
	
	// ATTRIBUTS
	private ArrayList<Case> cases = new ArrayList<Case>();
	private int nbCasesDepart = 4;
	private int nbCasesArrivee = 6;

	
	// CONSTRUCTEUR
	/**
	 * Crée une étape de 40 cases dont 4 pour le départ et 6 à l'arrivée
	 */
	public Etape() {
		cases = new ArrayList<Case>();
		
		// Crée 40 cases pour l'étape
		for (int i = 0 ; i < 40 ; i++) {
			cases.add(new Case());
		}

		for (int i = 10 ; i < 18 ; i++) {
			cases.get(i).setMontee(true);
		}
		for (int i = 18 ; i < 23 ; i++) {
			cases.get(i).setDescente(true);
		}
	}
	
	/**
	 * Crée une étape personnalisée
	 * @param cases les cases du circuit complet (incluant les cases de départ et d'arrivée)
	 * @param nbCasesDepart nombre de cases attribuées pour le départ (4 à 10)
	 * @param nbCasesArrivee nombre de cases attribuées pour l'arrivée (4 à 10)
	 * @throws Exception si l'un des paramètres n'est pas correct
	 */
	public Etape(ArrayList<Case> cases, int nbCasesDepart, int nbCasesArrivee) throws Exception {
		if (nbCasesArrivee < 4 || nbCasesDepart  < 4 || nbCasesArrivee > 10 || nbCasesDepart  > 10)  {
			throw new Exception("Le nombre de cases de départ et d'arrivée doivent être entre 4 et 10");
		}
		if (cases.size() < nbCasesArrivee + nbCasesDepart) {
			throw new Exception("Les cases de départ et d'arrivée ne doivent pas se chevaucher !");
		}
		this.cases = cases;
		this.nbCasesArrivee = nbCasesArrivee;
		this.nbCasesDepart = nbCasesDepart;
	}
	
	// METHODES
	
	/**
	 * Avance tous les cyclistes d'une case
	 * @param numCase la case de départ
	 */
	public void avancerCyclistesCase(int numCase) {
		Case c = cases.get(numCase);
		int nb = c.getCyclistes().size();
		// Avance de 1 tous les cyclistes de la case
		for (int j = 0 ; j < nb ; j++) {
			/* /!\ Les cyclistes sont replacés sur la file de droite à chaque itération :
			   il faut donc toujours prendre le cycliste sur la file de droite (index 0) */
			avancerCycliste(c.getCyclistes().get(0), 1);
		}
	}
	
	/**
	 * Vérifie qu'un cycliste a franchi la ligne d'arrivée
	 * @return true si oui, false sinon
	 */
	public boolean arriveeFranchie() {
		// Cherche si une case d'arrivée contient un cycliste
		for (int i = cases.size()-1 ; i >= cases.size()-nbCasesArrivee ; i--) {
			if (cases.get(i).estVide() == false) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Récupère la case qui suit celle donnée en paramètre
	 * @param c la case dont on veut la suivante
	 * @return null si aucune ne suit
	 */
	public Case caseSuivante(Case c) {
		int index = cases.indexOf(c);
		
		// Si c'est la dernière case
		if (index == cases.size()-1) {
			return null;
		} else {
			return cases.get(index+1);
		}
	}
	
	/**
	 * Renvoie la case du circuit demandée
	 * @param index le numéro de la case du circuit (démarre à 0)
	 * @return la case si elle existe, null sinon
	 */
	public Case getCase(int index) {
		if (index >= 0 && index < cases.size()) {
			return cases.get(index);
		} else {
			return null;
		}
	}
	
	/**
	 * Place un cycliste sur le départ
	 * @param c le cycliste à placer
	 * @param place l'index de la case de départ où le mettre
	 * @return true si placé, false si la case est pleine ou que ce n'est pas une case départ
	 */
	public boolean placerCycliste(Cycliste c, int place) {
		// Si l'emplacement désiré est au-delà du départ, on n'autorise pas
		if (place > nbCasesDepart) {
			return false;
		}
		try {
			return cases.get(place).ajouterCycliste(c);
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Essaie d'avancer le cycliste de N cases en fonction des contraintes de la route
	 * @param c le cycliste à faire avancer
	 * @param nbCases le nombre de cases de déplacement
	 * @return le nombre de cases effectivement avancées
	 */
	public int avancerCycliste(Cycliste c, int nbCases) {
		int dep = getPositionCycliste(c);
		if (dep == -1 || nbCases == -1) {
			return 0;
		} else {
			// Si le déplacement dépasse le bout du circuit, on le limite à la dernière case
			if (dep + nbCases >= cases.size()) {
				nbCases = cases.size() - dep - 1;
			}
			
			// Adapte le nombre de cases en fonction des montées et/ou descentes
			for (int i = nbCases ; i >= 0 ; i--) {
				// Montée
				if (nbCases >= 5 && cases.get(dep+i).isMontee()) {
					nbCases = 5;
					break;
				}
				// Descente
				else if (nbCases <= 5 && cases.get(dep+i).isDescente()) {
					nbCases = 5;
					break;
				}
			}
			
			// Essaie de placer le cycliste selon la place disponible sur les cases devant lui
			for (int pos = dep+nbCases ; pos > dep ; pos--) {
				if (cases.get(pos).ajouterCycliste(c) == false) {
					nbCases--;
				} else {
					cases.get(dep).retirerCycliste(c);
					break;
				}
			}
		}
		return nbCases;
	}
	
	/**
	 * Ajoute une nouvelle case au circuit
	 * @param c la case à ajouter
	 */
	public void ajouterCase(Case c) {
		
	}
	
	/**
	 * Retourne la position de la case où se situe un cycliste
	 * @param c le cycliste à chercher
	 * @return l'emplacement du cycliste ou -1 si le cycliste n'y est pas
	 */
	public int getPositionCycliste(Cycliste c) {
		for (int i = 0 ; i < cases.size() ; i++) {
			if (cases.get(i).contient(c)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Renvoie un affichage textuel du circuit de la sorte : (la ligne du haut est la voie de droite)
	  
	          Dp             ▲▲ ▲▲ ▲▲ ▼▼ ▼▼ ▼▼             Ar
		1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 
		__ S0 __ S1 R0 __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ 
		__ R1 __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __ __
	 
	 */
	@Override
	public String toString() {
		String s = "";
		for (int ligne = 0 ; ligne < 2+Case.nbFiles ; ligne++) {
			for (int numCase = 0 ; numCase < cases.size() ; numCase++) {
				switch(ligne) {
				
				// Affichage depart/montée/descente/normal/arrivée
				case 0:
					if (numCase == nbCasesDepart-1) {
						s += "Dp ";
					} else if (numCase == cases.size()-nbCasesArrivee) {
						s += "Ar ";
					} else if (cases.get(numCase).isMontee()) {
						s += "▲  ";
					} else if (cases.get(numCase).isDescente()) {
						s += "▼  ";
					} else {
						s += "-- ";
					}
					break;
					
				// Affichage numéro de case (max 99)
				case 1:
					if (numCase < 10) {
						s += numCase + "  ";
					} else if (numCase < 100) {
						s += numCase + " ";
					} else {
						s += "** ";
					}
					break;
				
				// Affiche une file de la route avec les cyclistes qui sont dessus
				default:
					try {
						// Teste s'il y a un cycliste sur la voie
						Cycliste c = cases.get(numCase).getCyclistes().get(ligne-2);
						if (c instanceof Rouleur) {
							s += "R" + c.couleur.ordinal() + " ";
						} else if (c instanceof Sprinteur) {
							s += "S" + c.couleur.ordinal() + " ";
						} else {
							s += "C" + c.couleur.ordinal() + " ";
						}
					} catch (Exception e) {
						// Pas de cycliste sur cette voie, on n'affiche rien
						s += "__ ";
					}
					break;
				}
			}
			s += "\n";
		}
		return s;
	}
	
	/**
	 * Renvoie l'emplacement de la ligne de départ
	 * @return la position de la case de départ
	 */
	public int getPosDepart() {
		return nbCasesDepart-1;
	}
	
	/**
	 * Retourne le cycliste qui est tout devant
	 * @return le cycliste qui est tout devant
	 */
	public Cycliste getPremierCycliste() {
		Cycliste premier = null;
		for (int i = cases.size()-1 ; i >= 0 ; i--) {
			if (cases.get(i).estVide() == false) {
				premier = cases.get(i).getCyclistes().get(0);
				break;
			}
		}
		return premier;
	}
	
	/**
	 * Renvoie le nombre de cases de la piste
	 * @return le nombre de cases de la piste
	 */
	public int getNbCases() {
		return cases.size();
	}

	/**
	 * Vérifie que le circuit est conforme
	 * @return true s'il est conforme, false sinon
	 */
	public boolean estCircuitValide() {
		return nbCasesDepart + nbCasesArrivee < cases.size();
	}
}
