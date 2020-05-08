package noyau;

public class HorlogeManquante extends ExceptionProgramme{ /// utilisé si une entrée horloge est non reliée

	public HorlogeManquante(TypesExceptions typesExceptions, Composant composant) {
		super(typesExceptions, composant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getProblem() {
		// TODO Auto-generated method stub
		return "L'horloge du composant : "+composant.getClass().getSimpleName()+ " est non reliée";
	}

	@Override
	public String getSolution() {
		// TODO Auto-generated method stub
		return "Relier l'horloge";
	}

}
