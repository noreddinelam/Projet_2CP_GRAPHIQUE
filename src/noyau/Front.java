package noyau;

public enum Front {
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
