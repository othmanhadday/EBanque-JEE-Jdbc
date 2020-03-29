package com.othmaneHadday.metier;

import com.othmaneHadday.entities.Compte;

public interface IBanqueMetier {
	public Compte consulterCompte(Long codeCpte);
	public void verser(Long codeCpte,double montant);
	public void retirer(Long codeCpte,double montant);
	public void virement(Long codeCpte1,Long codeCpte2,double montant);
	//public Page<O> listOperation(String codeCpte,int page,int size);
}
