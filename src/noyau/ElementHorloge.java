package noyau;

import javafx.scene.layout.BackgroundRepeat;

public interface ElementHorloge { /// interface utiliser pour l'execution des composants sequentiels avec l'idee des etages
	default void  tictac() { // sert dans l'execution des composants sequentiels qui sont relier avec une horloge
		int etage = 0 ;
		while(etage <= Circuit.getNbEtages()) { // executer par etages
			for (Sequentiels b : Circuit.getListeEtages()) { // executer tout les composants sequentiels dans un étage donné
				if (b.getEtages().contains(etage) && b.validerSyncho() && b.sleep == false) { // verifier si l'elt existe dans l'etage à executer 																						  // et verifier si il peut etre executé

					b.genererSortiesSyncho(); // generer les sorties en mode synchrone
					b.sleep = true ;
				}
			}
			for (Sequentiels b : Circuit.getListeEtages()) { // evaluer tout les composant executé dans l'etage present
				if (b.getEtages().contains(etage)) {
					b.evaluer();
				}
			}
			etage ++ ;
		}
	}
}
