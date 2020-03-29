package com.othmaneHadday.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.othmaneHadday.Dao.ClientDaoImpl;
import com.othmaneHadday.Dao.CompteDaoImpl;
import com.othmaneHadday.Dao.IClientDao;
import com.othmaneHadday.Dao.ICompteDao;
import com.othmaneHadday.entities.Client;
import com.othmaneHadday.entities.Compte;

/**
 * Servlet implementation class CompteSerlet
 */
@WebServlet(name="compte",urlPatterns = "*.co")
public class CompteSerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IClientDao clientDao=new ClientDaoImpl();
	private ICompteDao compteDao=new CompteDaoImpl();
    public CompteSerlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if(path.equals("/compte.co")) {
			List<Client> clients=clientDao.AllClients();
			request.setAttribute("toutClients", clients);
			
			List<Compte> comptes = compteDao.getAllComptes();
			request.setAttribute("toutComptes", comptes);
			request.getRequestDispatcher("/compte.jsp").forward(request, response);
		}
		else if(path.equals("/ajouterCompte.co") && request.getMethod().equals("POST")) {
			double solde = Double.parseDouble(request.getParameter("solde"));
			Long clientid =Long.parseLong(request.getParameter("client"));
			Client client=new Client();
			client.setCode(clientid);
			Compte compte = compteDao.save(new Compte(client,new Date(),solde));
			if(compte.getNumCompte()==null) {
				request.setAttribute("err", "compte Deja exist");
				request.getRequestDispatcher("/compte.co").forward(request, response);
			}else {
				request.setAttribute("compteSucces", "compte "+compte.getNumCompte() +" a ete cree ave succes");
				request.getRequestDispatcher("/compte.co").forward(request, response);
			}
		}
		else if(path.equals("/supprimerCompte.co")) {
			Long id=Long.parseLong(request.getParameter("id"));
			compteDao.deleteCompte(id);
			request.setAttribute("compteSucces","code client : "+id+" supprimer avec succes");
			request.getRequestDispatcher("/compte.co").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
