package com.othmaneHadday.Dao;

import com.othmaneHadday.entities.Login;

public interface ILoginDao {
	public Login inscription(Login l);
	public Login login(String username,String password);
}
