package noyau;

public enum Front { /// les front d'un element sequentiel
	Front_Montant(1),
	Front_Descendant(0);
    int num;
	Front(int num) {
		this.num=num;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
	
}
