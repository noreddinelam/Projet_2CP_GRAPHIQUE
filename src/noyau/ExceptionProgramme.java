package noyau;

public abstract class ExceptionProgramme { /// la classe d'ou herite les exception utilisées dans la validation d'un circuit
	protected TypesExceptions typesExceptions;
	protected Composant composant;
	public ExceptionProgramme(TypesExceptions typesExceptions,Composant composant) {
		// TODO Auto-generated constructor stub
		this.typesExceptions = typesExceptions;
		this.composant = composant;
	}
	
	public abstract String getProblem();
	public abstract String getSolution();
	
	public String getTypeExceptions() {
		return typesExceptions.getTypeExString();
	}
	
	public TypesExceptions getTypesExceptions() {
		return typesExceptions;
	}

	public void setTypesExceptions(TypesExceptions typesExceptions) {
		this.typesExceptions = typesExceptions;
	}

	
}
