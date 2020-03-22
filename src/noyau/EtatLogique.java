package noyau;

import java.io.Serializable;

public enum EtatLogique implements Serializable{
	ZERO(0),
	ONE(1),
	HAUTE_IMPEDANCE(2),
	ERROR(-1);
	private int num;
	private EtatLogique(int num) {
		this.num = num;
	}
	public int getNum() {
		return num;
	}
	
}

