package com.iutnc.flammerouge.model;


public class Rouleur extends Cycliste {
	
	public Rouleur(Couleur c) {
		// Crée les paquets
		super(c);
		
		// Adapte la pioche pour un rouleur
		pioche = new PileCartes(new Integer[] {3,3,3, 4,4,4, 5,5,5, 6,6,6, 7,7,7});
		pioche.melanger();
	}
	
	@Override
	public String getNomClasse() {
		return "rouleur";
	}
}
