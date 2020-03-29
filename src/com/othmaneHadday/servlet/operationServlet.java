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
import com.othmaneHadday.Dao.CompteDaoImpl;
import com.othmaneHadday.Dao.IClientDao;
import com.othmaneHadday.Dao.ICompteDao;
import com.othmaneHadday.Dao.IOperationDao;
import com.othmaneHadday.Dao.OperationDaoIpl;
import com.othmaneHadday.entities.Client;
import com.othmaneHadday.entities.Compte;
import com.othmaneHadday.entities.Operation;
import com.othmaneHadday.metier.BanqueMetierImpl;
import com.othmaneHadday.metier.IBanqueMetier;

/**
 * Servlet implementation class operation
 */
@WebServlet(name = "operation", urlPatterns = "*.po")
public class operationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IClientDao clientDao = new ClientDaoImpl();
	private IBanqueMetier metier=new BanqueMetierImpl();
	private IOperationDao operationDao=new OperationDaoIpl();

	public operationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/operation.po")) {
			String codeCompte = request.getParameter("codeCompte");
			if (codeCompte == null) {
				this.getServletContext().getRequestDispatcher("/operation.jsp").forward(request, response);
			} else {
				try {
					Compte compte = metier.consulterCompte(Long.parseLong(codeCompte));
					Client client = clientDao.getClient(compte.getClient().getCode());
					System.out.println(compte);
					System.out.println(client);
					request.setAttribute("compte", compte);
					request.setAttribute("client", client);
					List<Operation> operations=operationDao.AllOperationparNumCompte(Long.parseLong(codeCompte));
					request.setAttribute("operations", operations);
					this.getServletContext().getRequestDispatcher("/operation.jsp").forward(request, response);
				} catch (Exception e) {
					request.setAttribute("exception", "compte introvable");
					this.getServletContext().getRequestDispatcher("/operation.jsp").forward(request, response);
				}
			}
		}else if(path.equals("/ajouterOperation.po") && request.getMethod().equals("POST")) {
			Long codeCompte=Long.parseLong(request.getParameter("codeCompte"));
			String typeOperation=request.getParameter("typeOperation");
			double montant=Double.parseDouble(request.getParameter("montant"));
			if(typeOperation.equals("Vers")) {
				metier.verser(codeCompte, montant);
				this.getServletContext().getRequestDispatcher("/operation.po?codeCompte="+codeCompte).forward(request, response);
			}else if(typeOperation.equals("Retr")) {
				try {
				metier.retirer(codeCompte, montant);}
				catch (RuntimeException e) {
					request.setAttribute("exception", e.getMessage());
				}
				this.getServletContext().getRequestDispatcher("/operation.po?codeCompte="+codeCompte).forward(request, response);
			}else if(typeOperation.equals("Vir")) {
				Long codeCompte2=Long.parseLong(request.getParameter("codeCompte2"));
				try {
					metier.virement(codeCompte, codeCompte2, montant);
				}catch (RuntimeException e) {
					request.setAttribute("exception", e.getMessage());
				}
				this.getServletContext().getRequestDispatcher("/operation.po?codeCompte="+codeCompte).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
