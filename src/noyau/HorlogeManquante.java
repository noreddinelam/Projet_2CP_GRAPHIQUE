package noyau;

public class HorlogeManquante extends ExceptionProgramme{ /// utilis� si une entr�e horloge est non reli�e

	public HorlogeManquante(TypesExceptions typesExceptions, Composant composant) {
		super(typesExceptions, composant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getProblem() {
		// TODO Auto-generated method stub
		return "L'horloge du composant : "+composant.getClass().getSimpleName()+ " est non reli�e";
	}

	@Override
	public String getSolution() {
		// TODO Auto-generated method stub
		return "Relier l'horloge";
	}

}
