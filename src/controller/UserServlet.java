package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns = "/Login", name = "login")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/view/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String nomUtilisateur = request.getParameter("utilisateur");
	      String motdepasse = request.getParameter("motdepasse");
	      if (nomUtilisateur.equals("marame")) {
	         out.print("Bienvenue, " + nomUtilisateur);
	         HttpSession session = request.getSession(true); 
	         
	         session.setAttribute("USER", nomUtilisateur);
	         session.setMaxInactiveInterval(30); 
	         response.sendRedirect("view/accueil.jsp");
	      } else {
	         RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	         out.println("<font color=red> Nom d'utilisateur ou mot de passe incorrect.</font>");
	         rd.include(request, response);
	      } // TODO Auto-generated method stub
	}

}
