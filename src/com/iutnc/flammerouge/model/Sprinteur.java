package com.iutnc.flammerouge.model;


public class Sprinteur extends Cycliste {
	
	public Sprinteur(Couleur c) {
		// Crée les paquets
		super(c);
		
		// Adapte la pioche pour un sprinteur
		pioche = new PileCartes(new Integer[] {2,2,2, 3,3,3, 4,4,4, 5,5,5, 9,9,9});
		pioche.melanger();
	}
	
	@Override
	public String getNomClasse() {
		return "sprinteur";
	}
}
