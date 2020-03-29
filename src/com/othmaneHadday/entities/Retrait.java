package com.othmaneHadday.entities;

import java.io.Serializable;
import java.util.Date;

public class Retrait extends Operation implements Serializable{
	protected String type_Mouvement;
	
	public Retrait() {
		
	}
	public Retrait(Compte compte, Date dateOperation, double montantMouvement) {
		super(compte, dateOperation, montantMouvement);
		this.type_Mouvement = "R";
	}

	public String getType_Mouvement() {
		return type_Mouvement;
	}
	@Override
	public String toString() {
		return "Retrait [type_Mouvement=" + type_Mouvement + ", compte=" + compte.getNumCompte() + ", dateOperation=" + dateOperation
				+ ", montantMouvement=" + montantMouvement + "]";
	}
	public void setType_Mouvement(String type_Mouvement) {
		this.type_Mouvement = type_Mouvement;
	}

	
	
}
