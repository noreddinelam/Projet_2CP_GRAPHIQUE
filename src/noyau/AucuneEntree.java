package noyau;

public class AucuneEntree extends ExceptionProgramme{

	public AucuneEntree(TypesExceptions typesExceptions) {
		super(typesExceptions, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getProblem() {
		// TODO Auto-generated method stub
		return "Le circuit ne contient pas d'entree";
	}

	@Override
	public String getSolution() {
		// TODO Auto-generated method stub
		return "Ajouter des elements d'entree (PIN / HORLOGE)";
	}

}
