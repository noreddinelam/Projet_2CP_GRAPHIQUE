package noyau;

public class AucuneEntree extends ExceptionProgramme{ /// exception generer si le composant est non relié

	public AucuneEntree(TypesExceptions typesExceptions) {
		super(typesExceptions, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getProblem() {
		// TODO Auto-generated method stub
		return "Le circuit ne contient pas d'entrées";
	}

	@Override
	public String getSolution() {
		// TODO Auto-generated method stub
		return "Ajouter des éléments d'entrée (PIN / HORLOGE)";
	}

}
