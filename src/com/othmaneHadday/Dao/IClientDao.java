package com.othmaneHadday.Dao;

import java.util.List;

import com.othmaneHadday.entities.Client;

public interface IClientDao {
	public Client save(Client c);
	public List<Client> AllClients();	 
	public Client getClient(Long i);
	public Client update(Client c);
	public void deleteClient(Long code);
}
