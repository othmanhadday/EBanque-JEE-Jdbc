package com.othmaneHadday.entities;

import java.io.Serializable;
import java.util.Date;

public abstract class Operation implements Serializable {
	private Long numOperation;
	protected Compte compte;
	protected Date dateOperation;
	protected double montantMouvement;

	public Operation(Compte compte, Date dateOperation, double montantMouvement) {
		super();
		this.compte = compte;
		this.dateOperation = dateOperation;
		this.montantMouvement = montantMouvement;
	}

	public Long getNumOperation() {
		return numOperation;
	}

	public void setNumOperation(Long numOperation) {
		this.numOperation = numOperation;
	}

	public Operation() {
		super();
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@Override
	public String toString() {
		return "Operation [numOperation=" + numOperation + ", compte=" + compte.getNumCompte() + ", dateOperation=" + dateOperation
				+ ", montantMouvement=" + montantMouvement + "]";
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public double getMontantMouvement() {
		return montantMouvement;
	}

	public void setMontantMouvement(double montantMouvement) {
		this.montantMouvement = montantMouvement;
	}

}
