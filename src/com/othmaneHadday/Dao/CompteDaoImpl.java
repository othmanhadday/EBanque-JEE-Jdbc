package com.othmaneHadday.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.othmaneHadday.entities.Client;
import com.othmaneHadday.entities.Compte;
import com.othmaneHadday.entities.Login;

public class CompteDaoImpl implements ICompteDao{

	@Override
	public Compte save(Compte c) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO COMPTES (DATECREATION,SOLDE,CLIENTID) VALUES (?,?,?)");
			
			ps.setDate(1,new java.sql.Date(System.currentTimeMillis()));
			ps.setDouble(2, c.getSolde());
			ps.setLong(3,c.getClient().getCode());
			ps.executeUpdate();
			
			PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(NUMCOMPTE) AS MAX_ID FROM COMPTES");
			ResultSet rs = ps2.executeQuery();
			if(rs.next()) {
				c.setNumCompte(rs.getLong("MAX_Id"));
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<Compte> getAllComptes() {
		List<Compte> comptes = new ArrayList<Compte>();
		Connection connection =SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM COMPTES");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Compte compte = new Compte();
				Client c = new Client();
				compte.setNumCompte(rs.getLong("NUMCOMPTE"));
				compte.setSolde(rs.getDouble("SOLDE"));
				compte.setDateCreation(rs.getDate("DATECREATION"));
				c.setCode(rs.getLong("CLIENTID"));
				compte.setClient(c);
				comptes.add(compte);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comptes;
	}

	@Override
	public Compte getCompte(Long i) {
		Compte compte=null;
		Client c=new Client();
		Connection connection =SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM COMPTES WHERE NUMCOMPTE=?");
			ps.setLong(1, i);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				compte=new Compte();
				compte.setNumCompte(rs.getLong("NUMCOMPTE"));
				compte.setSolde(rs.getDouble("SOLDE"));
				compte.setDateCreation(rs.getDate("DATECREATION"));
				c.setCode(rs.getLong("CLIENTID"));
				compte.setClient(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return compte;
	}

	@Override
	public Compte update(Compte c) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE COMPTES SET SOLDE=?,CLIENTID=? WHERE NUMCOMPTE=?");
			ps.setDouble(1, c.getSolde());
			ps.setLong(2, c.getClient().getCode());
			ps.setLong(3, c.getNumCompte());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void deleteCompte(Long code) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(" DELETE FROM COMPTES WHERE NUMCOMPTE=?");
			ps.setLong(1, code);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
