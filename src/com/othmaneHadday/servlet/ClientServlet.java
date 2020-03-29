package com.othmaneHadday.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.othmaneHadday.Dao.ClientDaoImpl;
import com.othmaneHadday.Dao.IClientDao;
import com.othmaneHadday.Dao.ILoginDao;
import com.othmaneHadday.Dao.LoginDaoImpl;
import com.othmaneHadday.entities.Login;
import com.othmaneHadday.entities.Client;


@WebServlet(name = "client",urlPatterns = "*.cl")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ILoginDao loginDao=new LoginDaoImpl();
    private IClientDao clientDao=new ClientDaoImpl();
	
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if(path.equals("/client.cl")) {
			List<Client> clients=clientDao.AllClients();
			request.setAttribute("toutClients", clients);
			request.getRequestDispatcher("client.jsp").forward(request, response);
		}else if(path.equals("/ajouterClient.cl") && request.getMethod().equals("POST")) {
			String username=request.getParameter("username");
			String pwd=request.getParameter("pwd");
			String nom=request.getParameter("nom");
			String address=request.getParameter("address");
			
			Login login =loginDao.inscription(new Login(username,pwd,false));
			Client client = clientDao.save(new Client(nom,address,login));
			
			request.setAttribute("clientSaved", client.getNom()+" est ajoutée avec succes");
			request.getRequestDispatcher("/client.cl").forward(request, response);
		}else if(path.equals("/supprimerClient.cl")){
			Long id=Long.parseLong(request.getParameter("id"));
			clientDao.deleteClient(id);
			request.setAttribute("clientSaved","code client : "+id+" supprimer avec succes");
			request.getRequestDispatcher("/client.cl").forward(request, response);
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
