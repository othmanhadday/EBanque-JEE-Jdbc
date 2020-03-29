package com.othmaneHadday.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.othmaneHadday.entities.Login;

public class LoginDaoImpl implements ILoginDao{

	@Override
	public Login inscription(Login l) {
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO LOGIN (USERNAME,PASSWORD,ISADMIN) VALUES (?,?,?)");
			ps.setString(1, l.getUsername());
			ps.setString(2, l.getPassword());
			ps.setBoolean(3, true);
			ps.executeUpdate();
			
			PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(ID) AS MAX_ID FROM LOGIN");
			ResultSet rs = ps2.executeQuery();
			if(rs.next()) {
				l.setId(rs.getLong("MAX_Id"));
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	@Override 
	public Login login(String username,String password) {
		Login l =null;
		Connection connection =SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM LOGIN WHERE USERNAME=? AND PASSWORD=? ");
			ps.setString(1, username);
			ps.setString(2, password);			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				l= new Login();
				l.setId(rs.getLong("ID"));		
				l.setUsername(rs.getString("USERNAME"));	
				l.setPassword(rs.getString("PASSWORD"));	
				l.setAdmin(rs.getBoolean("ISADMIN"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}


}
