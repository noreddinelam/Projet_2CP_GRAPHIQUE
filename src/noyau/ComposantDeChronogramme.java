package noyau;

public interface ComposantDeChronogramme { /// interface utiliser pour regrouper les elements du chronogramme
  public String getNom();
  public EtatLogique getSortieAafficher();
  public EtatLogique getSortieBar();
  public default String getIcon() {
	  return null;
  }
}
