package com.othmaneHadday.entities;

import java.io.Serializable;
import java.util.Date;

public class Compte implements Serializable {
	private Long numCompte;
	private Client client;
	private Date dateCreation;
	private double solde;
	
	public Compte() {
		
	}
	
	public Compte(Client client, Date dateCreation, double solde) {
		super();
		this.client = client;
		this.dateCreation = dateCreation;
		this.solde = solde;
	}
	public Long getNumCompte() {
		return numCompte;
	}
	public void setNumCompte(Long numCompte) {
		this.numCompte = numCompte;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	@Override
	public String toString() {
		return "Compte [numCompte=" + numCompte + ", client=" + client.getCode() + ", dateCreation=" + dateCreation + ", solde="
				+ solde + "]";
	}
	
}
