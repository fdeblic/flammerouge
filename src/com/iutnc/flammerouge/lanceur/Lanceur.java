package com.iutnc.flammerouge.lanceur;

import com.iutnc.flammerouge.controller.FlammeRougeController;
import com.iutnc.flammerouge.model.Etape;
import com.iutnc.flammerouge.model.FlammeRougeModel;
import com.iutnc.flammerouge.view.FlammeRougeViewConsole;
import com.iutnc.flammerouge.view.FlammeRougeViewI;


public class Lanceur {
	
	/**
	 * Démarre le programme du jeu Flamme Rouge
	 * @param args pas de paramètres requis
	 */
	public static void main(String[]args) {
		// Crée la vue
		FlammeRougeViewI vue = new FlammeRougeViewConsole();
		
		// Crée le modèle
		FlammeRougeModel modele = new FlammeRougeModel(new Etape());
		
		// Crée le contrôleur
		FlammeRougeController game = new FlammeRougeController(modele, vue);
		game.jouer();
		
	}
}
