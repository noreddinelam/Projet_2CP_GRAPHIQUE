package noyau;

public class EntreeManquante extends ExceptionProgramme{ /// exception utilisé si une entree d'un composant est non relié
	
	private int entreeManquante;

	public EntreeManquante(TypesExceptions typesExceptions, Composant composant,int entreeManquante) { 
		super(typesExceptions, composant);
		this.entreeManquante = entreeManquante;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getProblem() {
		return "L'entrée : "+String.valueOf(entreeManquante) + " du composant : "+composant.getClass().getSimpleName()+" est non reliée";
	}
	@Override
	public String getSolution() {
		return "Relier l'entrée : "+String.valueOf(entreeManquante) +" du composant : "+composant.getClass().getSimpleName();
	}

	public int getEntreeManquante() {
		return entreeManquante;
	}

	public void setEntreeManquante(int entreeManquante) {
		this.entreeManquante = entreeManquante;
	}

}
