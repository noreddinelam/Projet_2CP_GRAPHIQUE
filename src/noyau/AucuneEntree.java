package noyau;

public class AucuneEntree extends ExceptionProgramme{ /// exception generer si le composant est non reli�

	public AucuneEntree(TypesExceptions typesExceptions) {
		super(typesExceptions, null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getProblem() {
		// TODO Auto-generated method stub
		return "Le circuit ne contient pas d'entr�es";
	}

	@Override
	public String getSolution() {
		// TODO Auto-generated method stub
		return "Ajouter des �l�ments d'entr�e (PIN / HORLOGE)";
	}

}
