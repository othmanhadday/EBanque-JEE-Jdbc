package com.othmaneHadday.servlet.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.othmaneHadday.Dao.ILoginDao;
import com.othmaneHadday.Dao.LoginDaoImpl;
import com.othmaneHadday.entities.Login;

/**
 * Servlet implementation class login
 */
@WebServlet(name="login",urlPatterns = "*.oh")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ILoginDao loginDao=new LoginDaoImpl();
    private Login login=null;
    public login() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if(path.equals("/login.oh")) {
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}else if(path.equals("/loginSub.oh")) {
			String username = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			if(username.trim().isEmpty() || pwd.trim().isEmpty()) {
				request.setAttribute("error", "saisie tout les champs");
				this.getServletContext().getRequestDispatcher("/login.oh").forward(request, response);
			}else {
				login=loginDao.login(username, pwd);
				if(login!=null) {
					HttpSession session = request.getSession(true);
					session.setAttribute("loginSession", login);
					System.out.println(login.toString());
					request.getRequestDispatcher("/operation.po").forward(request, response);
				}else {
					request.setAttribute("error", "verfier votre username ou password");
					request.getRequestDispatcher("/login.oh").forward(request, response);
				}
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
