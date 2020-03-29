package com.othmaneHadday.Dao;

import java.util.List;

import com.othmaneHadday.entities.Operation;
import com.othmaneHadday.entities.Retrait;
import com.othmaneHadday.entities.Versement;

public interface IOperationDao {
	public Retrait saveRetrait(Retrait r);
	public Versement saveVersement(Versement v);
	public List<Operation> AllOperationparNumCompte(Long id);
}
