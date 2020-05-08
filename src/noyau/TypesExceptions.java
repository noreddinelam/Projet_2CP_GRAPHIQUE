package noyau;

public enum TypesExceptions { /// pour declarer les types des exceptions
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
