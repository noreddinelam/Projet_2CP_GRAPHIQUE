package noyau;

public interface ComposantDeChronogramme {
  public String getNom();
  public EtatLogique getSortieAafficher();
  public EtatLogique getSortieBar();
  public default String getIcon() {
	  return null;
  }
}
