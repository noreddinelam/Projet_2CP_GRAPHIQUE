package noyau;

public enum TypesExceptions {
	ALERTE("WARNING"),
	ERREUR("ERREUR");
	
	public String typeExString;

	private TypesExceptions(String typeExString) {
		this.typeExString = typeExString;
	}

	public String getTypeExString() {
		return typeExString;
	}
	
}
