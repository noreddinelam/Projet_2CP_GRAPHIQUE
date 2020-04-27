package noyau;

public class EntreeManquante extends ExceptionProgramme{
	
	private int entreeManquante;

	public EntreeManquante(TypesExceptions typesExceptions, Composant composant,int entreeManquante) {
		super(typesExceptions, composant);
		this.entreeManquante = entreeManquante;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getProblem() {
		return "L'entree : "+String.valueOf(entreeManquante) + " du composant : "+composant.getClass().getSimpleName()+" est non relié";
	}
	@Override
	public String getSolution() {
		return "Relier l'entree : "+String.valueOf(entreeManquante) +" du composant : "+composant.getClass().getSimpleName();
	}

	public int getEntreeManquante() {
		return entreeManquante;
	}

	public void setEntreeManquante(int entreeManquante) {
		this.entreeManquante = entreeManquante;
	}

}
