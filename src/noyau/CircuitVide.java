package noyau;

public class CircuitVide extends ExceptionProgramme{

	public CircuitVide(TypesExceptions typesExceptions) {
		super(typesExceptions, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getProblem() {
		// TODO Auto-generated method stub
		return "Le circuit est vide";
	}

	@Override
	public String getSolution() {
		// TODO Auto-generated method stub
		return "Ajouter des composants au circuit";
	}
	
	
	
}
