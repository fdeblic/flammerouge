package com.iutnc.flammerouge.controller;

import com.iutnc.flammerouge.model.Etape;
import com.iutnc.flammerouge.model.FlammeRougeModel;
import com.iutnc.flammerouge.view.FlammeRougeViewI;


public interface FlammeRougeControllerI {
	// ATTRIBUTS
	FlammeRougeViewI view = null;
	FlammeRougeModel model = null;
	
	// METHODES
	/**
	 * Démarre la partie
	 */
	public void jouer();
	
	/**
	 * Getter de l'étape
	 * @return l'étape du controlleur
	 */
	public Etape getEtape();
	
}
