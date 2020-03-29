package com.othmaneHadday.metier;

import java.util.Date;

import javax.management.RuntimeErrorException;

import com.othmaneHadday.Dao.CompteDaoImpl;
import com.othmaneHadday.Dao.ICompteDao;
import com.othmaneHadday.Dao.IOperationDao;
import com.othmaneHadday.Dao.OperationDaoIpl;
import com.othmaneHadday.entities.Compte;
import com.othmaneHadday.entities.Retrait;
import com.othmaneHadday.entities.Versement;

public class BanqueMetierImpl implements IBanqueMetier {
	private ICompteDao compteDao = new CompteDaoImpl();
	private IOperationDao operationDao=new OperationDaoIpl();
	@Override
	public Compte consulterCompte(Long codeCpte) {
		Compte compte =(Compte)compteDao.getCompte(codeCpte);
		if(compte==null) {
			throw new RuntimeException("compte introvable!!?");
		}
		return compte;
	}

	@Override
	public void verser(Long codeCpte, double montant) {
		Compte compte=consulterCompte(codeCpte);
		Versement ver=new Versement(compte,new Date(),montant);
		operationDao.saveVersement(ver);
		compte.setSolde(montant+compte.getSolde());
		compteDao.update(compte);
	}

	@Override
	public void retirer(Long codeCpte, double montant) {
		Compte compte=consulterCompte(codeCpte);
		if(compte.getSolde()<=0 || compte.getSolde()<montant) {
			throw new RuntimeException("Solde insuffisant");
		}
		Retrait ret=new Retrait(compte,new Date(),montant);
		operationDao.saveRetrait(ret);
		compte.setSolde(compte.getSolde()-montant);
		compteDao.update(compte);
	}

	@Override
	public void virement(Long codeCpte1, Long codeCpte2, double montant) {
		if(codeCpte1 == codeCpte2) {
			throw new RuntimeException("Impossible virement sur le meme compte");
		}
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
	}

}
