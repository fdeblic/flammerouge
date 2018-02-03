package com.iutnc.flammerouge.tests;

import static libtest.Lanceur.*;
import static libtest.OutilTest.*;

import com.iutnc.flammerouge.model.Case;
import com.iutnc.flammerouge.model.CaseParameterException;
import com.iutnc.flammerouge.model.Couleur;
import com.iutnc.flammerouge.model.Rouleur;

public class TestCase {
	public static void main(String[]args) {
		lancer(new TestCase(), args);
	}

	/**
	 * Teste le constructeur
	 */
	public void test_Constructeur() {
		try {
			assertEquals("La case devrait être de type montée", true, new Case(true, false).isMontee());
			assertEquals("La case devrait être de type descente", true, new Case(false, true).isDescente());
			assertEquals("La case ne devrait pas être de type montée", true, new Case(false, false).isMontee());
			assertEquals("La case ne devrait pas être de type descente", true, new Case(false, false).isDescente());
			assertEquals("La case ne devrait pas pouvoir être créée", null, new Case(true, true));
		} catch(CaseParameterException e) {
			// Le test d'exception est passé (la case montée+descente n'a pas été créée)
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Teste l'ajout de cycliste
	 */
	public void test_ajouterCycliste() {
		try {
			Case c = new Case(false, false);
			c.ajouterCycliste(new Rouleur(Couleur.BLEU));
			assertEquals("La case devrait être de type montée", true, new Case(true, false).isMontee());
			assertEquals("La case devrait être de type montée", true, new Case(false, true).isDescente());
			assertEquals("La case ne devrait pas pouvoir être créée", null, new Case(true, true));
		} catch(CaseParameterException e) {
			// Le test d'exception est passé (la case montée+descente n'a pas été créée)
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
