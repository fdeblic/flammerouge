package com.iutnc.flammerouge.view;

import java.util.Arrays;
import java.util.Scanner;

import com.iutnc.flammerouge.controller.FlammeRougeController;
import com.iutnc.flammerouge.controller.FlammeRougeControllerI;
import com.iutnc.flammerouge.model.Cycliste;

public class FlammeRougeViewConsole implements FlammeRougeViewI {
	// ATTRIBUTS
	private Scanner sc = new Scanner(System.in);
	FlammeRougeControllerI controller;
	
	// CONSTRUCTEURS
	public FlammeRougeViewConsole() {
		
	}

	public FlammeRougeViewConsole(FlammeRougeControllerI c) {
		controller = c;
	}
	
	// METHODES
	
	public int demanderNbJoueurs(int max) {
		effacerEcran();
		int nb = 0;
		
		do {
			afficherMessage("Combien de joueurs ? (2-"+max+")");
			try {
				nb = sc.nextInt();
				if (nb < 2 || nb > max) {
					afficherMessage("Nombre hors de l'intervalle donné");
				}
			} catch (Exception e) {
				afficherMessage("Veuillez entrer un nombre entier !");
				sc.next();
			}
		} while (nb < 2 || nb > max);
		
		return nb;
	}
	
	public void rafraichir() {
		effacerEcran();
		System.out.println(controller.getEtape());
	}

	public int choisirCarteJoueurs(int[] cartes, Cycliste cycliste) {
		int carte = 0;
		Arrays.sort(cartes); // Nécessaire pour la recherche binaire
		
		System.out.println();
		afficherMessage("C'est au " + cycliste + " de jouer.");
		afficherMessage("Cartes en main : " + Arrays.toString(cartes));
		
		do {
			afficherMessage("Votre choix ?");
			try {
				carte = sc.nextInt();
				if (Arrays.binarySearch(cartes, carte) < 0) {
					afficherMessage("Choisissez une carte que vous avez en main !");
				}
			} catch (Exception e) {
				afficherMessage("Veuillez entrer un nombre entier !");
				sc.next();
			}
		} while (Arrays.binarySearch(cartes, carte) < 0);
		
		rafraichir();
		
		return carte;
	}

	public int demanderEmplacementDepart(Cycliste c, int posMax) {
		int nb = -1;
		
		do {
			afficherMessage("Où placer le " + c + " ? (0-" + posMax + ")");
			try {
				nb = sc.nextInt();
				if (nb<0 || nb > posMax) {
					afficherMessage("Emplacement incorrect");
				}
			} catch (Exception e) {
				afficherMessage("Veuillez entrer un nombre entier !");
				sc.next();
			}
		} while (nb < 0 || nb > posMax);
		
		return nb;
	}

	public void afficherMessage(String message) {
		System.out.println("> " + message);
	}
	
	private void effacerEcran() {
		// Replace le curseur en haut à gauche (caractères d'échappement ANSI)
		System.out.println("\033[H\033[2J");
		System.out.println(" ▲▲ Flamme Rouge ▲▲\n");
	}

	public void animationVainqueur(Cycliste premierCycliste) {
		afficherMessage("*** Le joueur " + premierCycliste.getCouleur().ordinal() + " a gagné ! ***");
	}

	public void avancerCycliste(Cycliste c, int nbCases) {
		if (nbCases != 0) {
			afficherMessage(c + " avance de "+nbCases + " cases !");
		} else {
			afficherMessage(c + " n'a plus d'énergie pour avancer !");
		}
	}

	public void attribuerCarteFatigue(Cycliste c) {
		afficherMessage(c + " prend une carte fatigue !");
	}

	public void plusDeCartes(Cycliste c) {
		afficherMessage(c + " n'a plus de cartes, il ne peut pas jouer !");
	}

	
	public void setController(FlammeRougeController controller) {
		this.controller = controller;
	}
}
