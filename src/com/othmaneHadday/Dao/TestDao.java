package com.othmaneHadday.Dao;

import java.util.*;

import com.othmaneHadday.entities.Client;
import com.othmaneHadday.entities.Compte;
import com.othmaneHadday.entities.Login;
import com.othmaneHadday.entities.Operation;
import com.othmaneHadday.entities.Retrait;
import com.othmaneHadday.entities.Versement;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class TestDao {
	public static void main(String[]args) {
		ILoginDao login=new LoginDaoImpl();
		IClientDao client=new ClientDaoImpl();
		ICompteDao compte=new CompteDaoImpl();
		IOperationDao o=new OperationDaoIpl();
		/*Login l1=login.inscription(new Login("rrrr","123",true));
		Client c1=client.save(new Client("rrrrr","rrrrr",l1));
		
		Compte c=compte.save(new Compte(c1,new Date(),12.2));*/
		//Compte c= compte.getCompte(2L);
		/*ArrayList<Operation>ops=(ArrayList<Operation>) o.AllOperation();
		for(Operation q:ops ) {
			System.out.println(q);
			try {
			Retrait r=(Retrait) q;
			if(r.getType_Mouvement()!=null) {
				System.out.println(r.getType_Mouvement());}
				
			
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				Versement v=(Versement)q;
				
				if(v.getType_Mouvement()!=null) {
					System.out.println(v.getType_Mouvement());}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}*/
		
		
		/*Login l2 =login.login("oth2", "123");
		if(l2.getIsAdmin()) {
			System.out.println("admin");
			System.out.println(l2.toString());
		}else {
			System.out.println("user");
			System.out.println(l2.toString());
		}*/
	}

	
}
