package noyau;

public abstract class ExceptionProgramme {
	protected TypesExceptions typesExceptions;
	protected Composant composant;
	public ExceptionProgramme(TypesExceptions typesExceptions,Composant composant) {
		// TODO Auto-generated constructor stub
		this.typesExceptions = typesExceptions;
		this.composant = composant;
		Circuit.AjouterException(this);
	}
	
	public String getTypeExceptions() {
		return typesExceptions.getTypeExString();
	}
	public abstract String getProblem();
	public abstract String getSolution();
}
