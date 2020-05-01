package noyau;

import javafx.scene.layout.BackgroundRepeat;

public interface ElementHorloge {
	default void  tictac() { // sert dans l'execution des composants sequentiels qui sont relier avec une horloge
		int etage = 0 ;
		while(etage <= Circuit.getNbEtages()) { // executer par etages
			for (Sequentiels b : Circuit.getListeEtages()) { // executer tout les composants sequentiels dans un �tage donn�
			
				if (b.getEtages().contains(etage) && b.validerSyncho() && b.sleep == false) { // verifier si l'elt existe dans l'etage � executer 																						  // et verifier si il peut etre execut�
					
					System.out.println(b.getClass().getSimpleName());
					b.genererSortiesSyncho(); // generer les sorties en mode synchrone
					b.sleep = true ;
				}
			}
			for (Sequentiels b : Circuit.getListeEtages()) { // evaluer tout les composant execut� dans l'etage present
				if (b.getEtages().contains(etage)) {
					b.evaluer();
				}
			}
			etage ++ ;
		}
	}
}
