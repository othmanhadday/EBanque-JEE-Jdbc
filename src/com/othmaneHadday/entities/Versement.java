package com.othmaneHadday.entities;

import java.io.Serializable;
import java.util.Date;

public class Versement extends Operation implements Serializable{
	private String type_Mouvement;

	public Versement() {
		
	}
	public Versement(Compte compte, Date dateOperation, double montantMouvement) {
		super(compte, dateOperation, montantMouvement);
		this.type_Mouvement = "V";
	}

	public void setType_Mouvement(String type_Mouvement) {
		this.type_Mouvement = type_Mouvement;
	}
	public String getType_Mouvement() {
		return type_Mouvement;
	}

	@Override
	public String toString() {
		return "Versement [type_Mouvement=" + type_Mouvement + ", compte=" + compte.getNumCompte() + ", dateOperation=" + dateOperation
				+ ", montantMouvement=" + montantMouvement + "]";
	}

	

}
