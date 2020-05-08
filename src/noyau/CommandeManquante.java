package noyau;

public class CommandeManquante extends ExceptionProgramme{ /// exception si une des commandes est non reliée
	
	private int nbCommande;

	public CommandeManquante(TypesExceptions typesExceptions, Composant composant,int nbCommande) { 
		super(typesExceptions, composant);
		// TODO Auto-generated constructor stub
		this.nbCommande = nbCommande;
	}

	@Override
	public String getProblem() {
		// TODO Auto-generated method stub
		return "La commande : "+String.valueOf(nbCommande) + " du composant : "+composant.getClass().getSimpleName()+" est non relié";
	}

	@Override
	public String getSolution() {
		// TODO Auto-generated method stub
		return "Relier la commande : "+String.valueOf(nbCommande) +" du composant : "+composant.getClass().getSimpleName();
	}

}
