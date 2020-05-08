package noyau;

public class SortieReinjecter extends ExceptionProgramme{ /// exception utilisé pour declarer si une sortie et reinjecter en entree
	
	private int entree;
	private int sortie;
	public SortieReinjecter(TypesExceptions typesExceptions, Composant composant,int entree,int sortie) {
		super(typesExceptions, composant);
		this.entree = entree;
		this.sortie = sortie;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getProblem() {
		// TODO Auto-generated method stub
		return "La sortie 0 du composant : "+composant.getClass().getSimpleName()+" est relier à son entree : "+String.valueOf(entree);
	}

	@Override
	public String getSolution() {
		// TODO Auto-generated method stub
		return "Enlever la liason entre l'entree : "+String.valueOf(entree)+" et la saortie : 0";
	}

}
