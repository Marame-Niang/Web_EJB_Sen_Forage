package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TestEjbValid.dao.IVillage;
import com.testEjbValid.entities.Village;

@WebServlet(urlPatterns = {"/Village", "/ListeVillage"})
public class VillageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    private IVillage villagedao;
    
    public VillageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equalsIgnoreCase("/Village")) {
	    	request.getRequestDispatcher("/WEB-INF/view/village/ajout.jsp").forward(request, response);
			}
			
			else if(request.getServletPath().equalsIgnoreCase("/ListeVillage")) {
		    	
	    		request.setAttribute("ListeVillage", villagedao.list());
	    	   	
	    	request.getRequestDispatcher("/WEB-INF/view/village/liste.jsp").forward(request, response);
	    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
 String nom = request.getParameter("nom").toString();
		
		Village village = new Village();
		village.setNom(nom);
	
		int ajout = villagedao.add(village);
		response.getWriter().println("Village ajoute avec succes");
		System.out.println(ajout);
		
		
	}

}
