package com.othmaneHadday.entities;

import java.io.Serializable;

public class Client implements Serializable {
	private Long code;
	private String nom;
	private String address;
	private Login login;
	
	public Client() {
		
	}

	public Client(String nom, String address, Login login) {
		super();
		this.nom = nom;
		this.address = address;
		this.login = login;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "Client [code=" + code + ", nom=" + nom + ", address=" + address + ", login=" + login.getId() + "]";
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
