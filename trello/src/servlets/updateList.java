package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import pojos.Respuesta;
import pojos.lista;
import pojos.project;
import cruds.listas;
import cruds.projects;
import utilities.poolManager;
/**
 * Servlet implementation class updateList
 */
@WebServlet("/updateList")
public class updateList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = entrada.getSession();
		
		
		listas crud=new listas();
		ObjectMapper om=new ObjectMapper();
		Respuesta <lista> resp=new Respuesta <lista>();
		if(session!=null || entrada.isRequestedSessionIdValid()){
		lista lista=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),lista.class);
			resp.setData(lista);
			
			poolManager pm=new poolManager();
			Connection con=pm.setCon();
			
			if(crud.update(lista.getName(), lista.getIdLista(),con)){
				
				resp.setMessage("lista actualizada");
				resp.setStatus(200);
			}else{
				
				resp.setMessage("error lista no actualizada");
	             resp.setStatus(400);			
			}
			pm.getCon(con);
		}else{
			
			resp.setMessage("no hay una session iniciada");
			resp.setStatus(300);
			
		}
			String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
	}

	protected void doPut(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = entrada.getSession();
		listas crud=new listas();
		ObjectMapper om=new ObjectMapper();
		Respuesta <lista> resp=new Respuesta <lista>();
		if(session!=null || entrada.isRequestedSessionIdValid()){
		lista lista=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),lista.class);
			resp.setData(lista);
			
			poolManager pm=new poolManager();
			Connection con=pm.setCon();
			if(crud.delete(lista.getIdLista(),con)){
				
				resp.setMessage("lista borrada con exito");
				resp.setStatus(200);
		
			}else{
				
				resp.setMessage("error lista no actualizada");
			     resp.setStatus(400);
			}
			pm.getCon(con);
		}else{
			
			resp.setMessage("no hay una session iniciada");
			resp.setStatus(300);
		}
String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
	}
	
	
}
