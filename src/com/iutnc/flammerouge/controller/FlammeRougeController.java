package com.iutnc.flammerouge.controller;

import java.util.List;
import com.iutnc.flammerouge.model.Case;
import com.iutnc.flammerouge.model.Couleur;
import com.iutnc.flammerouge.model.Cycliste;
import com.iutnc.flammerouge.model.Etape;
import com.iutnc.flammerouge.model.FlammeRougeModel;
import com.iutnc.flammerouge.model.Joueur;
import com.iutnc.flammerouge.view.FlammeRougeViewConsole;
import com.iutnc.flammerouge.view.FlammeRougeViewI;


public class FlammeRougeController implements FlammeRougeControllerI {
	// ATTRIBUTS
	private FlammeRougeViewI view;
	private FlammeRougeModel model;
	
	// CONSTRUCTEUR
	/**
	 * Constructeur vide
	 * @param modele le modèle
	 * @param vue la vue
	 */
	public FlammeRougeController(FlammeRougeModel modele, FlammeRougeViewI vue) {
		model = modele;
		view = vue;
		view.setController(this);
	}
	
	// METHODES
	/**
	 * Lance le contrôle de la partie
	 */
	public void jouer() {
		// Demande le nombre de joueurs
		int nbJoueurs = view.demanderNbJoueurs(FlammeRougeModel.getNbMaxjoueurs());
		
		// Crée les joueurs
		for (int i = 0 ; i < nbJoueurs ; i++) {
			model.ajouterJoueur(new Joueur(Couleur.values()[i]));
		}
		
		// Place tous les cyclistes
		for (Joueur j : model.getJoueurs()) {
			for (Cycliste c : j.getCyclistes()) {
				view.rafraichir();
				// Essaie de placer le cycliste
				while (model.getEtape().placerCycliste(c, view.demanderEmplacementDepart(c, model.getEtape().getPosDepart())) == false) {
					view.afficherMessage("! Le cycliste ne peut pas être mis sur cette case");
				}
			}
		}
		
		view.rafraichir();
		
		// Boucle jusqu'à ce que l'arrivée soit franchie
		while (model.getEtape().arriveeFranchie() == false) {
			
			// Chaque joueur choisit la carte à jouer
			for (Joueur j : model.getJoueurs()) {
				for (Cycliste c : j.getCyclistes()) {
					// Il pioche quatre cartes
					int[] cartes = c.piocherCartes(4);
					
					// Si le joueur a pu récupérer des cartes
					if (cartes.length != 0) {
						c.jouerCarte(view.choisirCarteJoueurs(cartes, c));
					} else {
						c.jouerCarte(0);
						view.plusDeCartes(c);
					}
				}
			}
			
			// Applique les mouvements en commençant par les cyclistes tout devant, ainsi de suite jusqu'aux derniers
			for (int i = model.getEtape().getNbCases()-1 ; i >= 0 ; i--) {
				Case c = model.getEtape().getCase(i);
				List<Cycliste> cyclistes = c.getCyclistes();
				int taille = cyclistes.size();
				for (int j = 0 ; j < taille ; j++) {
					Cycliste cy = cyclistes.get(0); // Récupère celui tout à droite
					int nbCase = model.getEtape().avancerCycliste(cy, cy.getCarteJouee());
					view.avancerCycliste(cy, nbCase);
				}
			}
			
			// Applique les aspirations
			for (int i = 0 ; i < model.getEtape().getNbCases()-3 ; i++) {
				// Si la case a un (des) cycliste(s) et que l'aspiration est à faire
				if (!model.getEtape().getCase(i).estVide() && !model.getEtape().getCase(i).isMontee() && model.getEtape().getCase(i+1).estVide() && !model.getEtape().getCase(i+2).estVide() && !model.getEtape().getCase(i+2).isMontee()) {
					model.getEtape().avancerCyclistesCase(i);
					
					// Applique l'aspiration au reste du groupe
					for (int j = i-1 ; j >= 0 && model.getEtape().getCase(j).estVide()==false ; j--) {
						model.getEtape().avancerCyclistesCase(j);
					}
				}
			}

			view.rafraichir();
			
			// Plus besoin des cartes fatigues si la ligne d'arrivée est franchie
			if (model.getEtape().arriveeFranchie()) {
				break;
			}
			
			// Attribution des cartes fatigues
			for (int i = model.getEtape().getNbCases()-2 ; i > 0 ; i--) {
				if (model.getEtape().getCase(i+1).estVide()) {
					model.getEtape().getCase(i).attribuerCartesFatigue();
					for (Cycliste c : model.getEtape().getCase(i).getCyclistes()) {
						view.attribuerCarteFatigue(c);
					}
				}
			}
		}
		
		// Arrivée
		view.rafraichir();
		view.animationVainqueur(model.getEtape().getPremierCycliste());
	}
	
	public Etape getEtape() {
		return model.getEtape();
	}
	
}
