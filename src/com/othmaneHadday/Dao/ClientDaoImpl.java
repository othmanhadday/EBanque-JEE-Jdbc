package com.othmaneHadday.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.othmaneHadday.entities.Client;
import com.othmaneHadday.entities.Login;

public class ClientDaoImpl implements IClientDao {
	

	@Override
	public Client save(Client c) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO CLIENTS (NOM,ADDRESS,LOGINID) VALUES (?,?,?)");
			ps.setString(1,c.getNom());
			ps.setString(2, c.getAddress());
			ps.setLong(3,c.getLogin().getId());
			ps.executeUpdate();
			
			PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(CODE) AS MAX_ID FROM CLIENTS");
			ResultSet rs = ps2.executeQuery();
			if(rs.next()) {
				c.setCode(rs.getLong("MAX_Id"));
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<Client> AllClients() {
		List<Client> client = new ArrayList<Client>();
		Connection connection =SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM CLIENTS");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Client c = new Client();
				Login login=new Login();
				c.setCode(rs.getLong("CODE"));
				c.setNom(rs.getString("NOM"));
				c.setAddress(rs.getString("ADDRESS"));
				login.setId(rs.getLong("LOGINID"));
				c.setLogin(login);
				client.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public Client getClient(Long code) {
		Client c=null;
		Login login=new Login();
		Connection connection =SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM CLIENTS WHERE CODE=?");
			ps.setLong(1, code);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				c=new Client();
				c.setCode(rs.getLong("CODE"));
				c.setNom(rs.getString("NOM"));
				c.setAddress(rs.getString("ADDRESS"));
				login.setId(rs.getLong("LOGINID"));
				c.setLogin(login);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Client update(Client c) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE CLIENTS SET NOM=?,ADDRESS=? WHERE CODE=?");
			ps.setString(1, c.getNom());
			ps.setString(2, c.getAddress());
			ps.setLong(3, c.getCode());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void deleteClient(Long code) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(" DELETE FROM CLIENTS WHERE CODE=?");
			ps.setLong(1, code);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
