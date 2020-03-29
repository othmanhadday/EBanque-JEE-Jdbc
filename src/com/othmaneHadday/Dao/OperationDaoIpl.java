package com.othmaneHadday.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.othmaneHadday.entities.Client;
import com.othmaneHadday.entities.Compte;
import com.othmaneHadday.entities.Login;
import com.othmaneHadday.entities.Operation;
import com.othmaneHadday.entities.Retrait;
import com.othmaneHadday.entities.Versement;

public class OperationDaoIpl implements IOperationDao{

	@Override
	public Retrait saveRetrait(Retrait r) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO OPERATIONS (DATEOPERATION,MONTANT,TYPEOPERATION,COMPTEID) VALUES (?,?,?,?)");
			ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			ps.setDouble(2, r.getMontantMouvement());
			ps.setString(3, r.getType_Mouvement());
			ps.setLong(4, r.getCompte().getNumCompte());
			ps.executeUpdate();
			
			PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(NUMOPERATION) AS MAX_ID FROM OPERATIONS");
			ResultSet rs = ps2.executeQuery();
			if(rs.next()) {
				r.setNumOperation(rs.getLong("MAX_Id"));
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Versement saveVersement(Versement v) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO OPERATIONS (DATEOPERATION,MONTANT,TYPEOPERATION,COMPTEID) VALUES (?,?,?,?)");
			ps.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			ps.setDouble(2, v.getMontantMouvement());
			ps.setString(3, v.getType_Mouvement());
			ps.setLong(4, v.getCompte().getNumCompte());
			ps.executeUpdate();
			
			PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(NUMOPERATION) AS MAX_ID FROM OPERATIONS");
			ResultSet rs = ps2.executeQuery();
			if(rs.next()) {
				v.setNumOperation(rs.getLong("MAX_Id"));
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
	
	@Override
	public List<Operation> AllOperationparNumCompte(Long id) {
		List<Operation> operations = new ArrayList<Operation>();
		Connection connection =SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM OPERATIONS WHERE COMPTEID=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Compte compte=new Compte();
				String type=rs.getString("TYPEOPERATION");
				if(type.equals("R")) {
					Retrait r=new Retrait();
					r.setDateOperation(rs.getDate("DATEOPERATION"));
					r.setMontantMouvement(rs.getDouble("MONTANT"));
					compte.setNumCompte(rs.getLong("COMPTEID"));
					r.setCompte(compte);
					r.setNumOperation(rs.getLong("NUMOPERATION"));
					r.setType_Mouvement(type);
					operations.add(r);
				}
				if(type.equals("V")) {
					Versement v=new Versement();
					v.setDateOperation(rs.getDate("DATEOPERATION"));
					v.setMontantMouvement(rs.getDouble("MONTANT"));
					compte.setNumCompte(rs.getLong("COMPTEID"));
					v.setCompte(compte);
					v.setNumOperation(rs.getLong("NUMOPERATION"));
					v.setType_Mouvement(type);
					operations.add(v);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return operations;
	}


}
