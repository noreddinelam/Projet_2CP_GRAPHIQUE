package noyau;

public interface ElementHorloge {
	default void  tictac() {
		int etage = 0 ;
		while(etage <= Circuit.getNbEtages()) {
			for (Sequentiels b : Circuit.getListeEtages()) {
				if (b.getEtages().contains(etage) && b.validerSyncho() && b.sleep == false) {
					b.genererSortiesSyncho();
					b.sleep = true ;
				}
			}
			for (Sequentiels b : Circuit.getListeEtages()) {
				if (b.getEtages().contains(etage)) {
					b.evaluer();
				}
			}
			etage ++ ;
		}
	}
}
