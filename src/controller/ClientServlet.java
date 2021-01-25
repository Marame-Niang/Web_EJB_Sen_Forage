package controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sn.senforage.dao.Clientimpl;
import sn.senforage.dao.IClient;
import sn.senforage.dao.IVillage;
import sn.senforage.dao.Villageimpl;
import sn.senforage.entities.Client;
import sn.senforage.entities.Village;


/**
 * Servlet implementation class ClientServlet
 */
@WebServlet(urlPatterns = {"/Client", "/ListeClient"})

public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IClient clientdao;
	private IVillage villagedao;
     
	@Override
	public void init(ServletConfig config) throws ServletException{
		clientdao = new Clientimpl();
		villagedao = new Villageimpl();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	if(request.getServletPath().equalsIgnoreCase("/Client")) {
    		
    		request.setAttribute("ListeVillage", villagedao.list());
    		request.getRequestDispatcher("WEB-INF/view/client/ajout.jsp").forward(request, response);
    	}
    	else if(request.getServletPath().equalsIgnoreCase("/ListeClient")) {
    	
    		request.setAttribute("ListeClient", clientdao.list());
    	   	
    	request.getRequestDispatcher("WEB-INF/view/client/liste.jsp").forward(request, response);
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		int id =Integer.parseInt(request.getParameter("village"));
		Village villagec = new Village();
		villagec = villagedao.getVillageById(id);
		String adresse = request.getParameter("adresse");
		String telephone = request.getParameter("tel");
		
		Client client = new Client();
		client.setNom(nom);
		client.setVillage(villagec);
		client.setAdresse(adresse);
		client.setTelephone(telephone);
		
		int ajout = clientdao.add(client);
		response.getWriter().println(ajout);
		
	}

}
