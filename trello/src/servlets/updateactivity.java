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
import pojos.actividades;
import pojos.project;
import cruds.actividad;
import cruds.projects;

import utilities.poolManager;
/**
 * Servlet implementation class updateactivity
 */
@WebServlet("/updateactivity")
public class updateactivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateactivity() {
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
	//update listo
	protected void doPost(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = entrada.getSession();
		
		
		actividad crud=new actividad();
		ObjectMapper om=new ObjectMapper();
		Respuesta <actividades> resp=new Respuesta <actividades>();
		if(session!=null || entrada.isRequestedSessionIdValid()){
		actividades actividad=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),actividades.class);
			resp.setData(actividad);
			poolManager pm=new poolManager();
			Connection con=pm.setCon();
			
			if(crud.UpdateListo(actividad.getListo(), actividad.getId_actividad(),con)){
				
				resp.setMessage("listo actualizado");
				resp.setStatus(200);
				
			}else{
				
				resp.setMessage("error ");
				resp.setStatus(400);
			}
			
			pm.getCon(con);
		}else{
			
			resp.setMessage("no hay session iniciada");
			resp.setStatus(300);
		}
String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
	}
	
	//update name
	protected void doPut(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = entrada.getSession();
		
		
		actividad crud=new actividad();
		ObjectMapper om=new ObjectMapper();
		Respuesta <actividades> resp=new Respuesta <actividades>();
		if(session!=null || entrada.isRequestedSessionIdValid()){
		actividades actividad=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),actividades.class);
			resp.setData(actividad);
			
			poolManager pm= new poolManager();
			Connection con=pm.setCon();
			if(crud.updateName(actividad.getName_actividad(), actividad.getId_actividad(),con)){
				resp.setMessage("actividad actualizada");
				resp.setStatus(200);
				
				
			}else{
				resp.setMessage("actividad no actualizada");
				resp.setStatus(400);
				
			}
			pm.getCon(con);
		}else{
			resp.setMessage("no hay una session iniciada");
			resp.setStatus(200);
			
		}
String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
			
}
	
	
	protected void doDelete(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = entrada.getSession();
		
		
		actividad crud=new actividad();
		ObjectMapper om=new ObjectMapper();
		Respuesta <actividades> resp=new Respuesta <actividades>();
		if(session!=null || entrada.isRequestedSessionIdValid()){
		actividades actividad=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),actividades.class);
			resp.setData(actividad);	
			poolManager pm=new poolManager();
			Connection con=pm.setCon();
	if(crud.delete(actividad.getId_actividad(),con)){
		resp.setMessage("actividad eliminada");
		resp.setStatus(200);
		
		
		
	}else{
		resp.setMessage("actividad no eliminada");
		resp.setStatus(400);
		
	}
		}else{
			
			resp.setMessage("no hay session iniciada");
			resp.setStatus(300);
		}
	String sr=om.writeValueAsString(resp);
	
	response.getWriter().print(sr);
}
}