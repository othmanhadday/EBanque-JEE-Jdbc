package com.othmaneHadday.Dao;

import java.util.List;

import com.othmaneHadday.entities.Compte;


public interface ICompteDao {
	public Compte save(Compte c);
	public List<Compte> getAllComptes();	 
	public Compte getCompte(Long i);
	public Compte update(Compte c);
	public void deleteCompte(Long code);
}
