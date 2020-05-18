package noyau;

public class ComposantNonRelier extends ExceptionProgramme{ /// exception utilis� si le composant est non reli�

	public ComposantNonRelier(TypesExceptions typesExceptions, Composant composant) {
		super(typesExceptions, composant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getProblem() {
		// TODO Auto-generated method stub
		return "Le composant : "+composant.getClass().getSimpleName()+" est non reli� ";
	}

	@Override
	public String getSolution() {
		// TODO Auto-generated method stub
		return "Relier toutes les connections du composant";
	}

}
