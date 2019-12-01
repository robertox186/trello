package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servlet implementation class activity
 */
@WebServlet("/activity")
public class activity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public activity() {
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
ObjectMapper om=new ObjectMapper();
		Respuesta <actividades> resp=new Respuesta <actividades>();
		if(session!=null || entrada.isRequestedSessionIdValid()){
		poolManager pm=new poolManager();
		Connection con=null;
		
		actividad crud=new actividad();
		
		actividades actividad=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),actividades.class);
			
			con=pm.setCon();
			ResultSet rs=crud.insert(actividad.getName_actividad(), actividad.getId_tarjeta(),con);
			
		if(rs!=null){
			try {
				actividad.setId_actividad(rs.getInt("id_actividad"));
				resp.setData(actividad);
				resp.setMessage("actividad creada");
				resp.setStatus(200);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.setMessage("error actividad no creada");
				resp.setStatus(400);
			}
			
			
			
		}else{
			resp.setMessage("error actividad no creada");
			resp.setStatus(400);
			
		}
		pm.getCon(con);
		
		}
		else{
		resp.setMessage("no hay session iniciada");
		resp.setStatus(300);
		}
		String sr=om.writeValueAsString(resp);
		
		
		
		response.getWriter().print(sr);
		
		
		
	}

	
	protected void doPut(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = entrada.getSession();
		poolManager pm=new poolManager();
		Connection con=null;
		ArrayList array=new ArrayList();
		actividad crud=new actividad();
		ObjectMapper om=new ObjectMapper();
		Respuesta <ArrayList> resp=new Respuesta <ArrayList>();
		if(session!=null || entrada.isRequestedSessionIdValid()){
		actividades actividad=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),actividades.class);
			con=pm.setCon();
		ResultSet rs=crud.select(actividad.getId_tarjeta(),con);
		
		if(rs!=null){
			try {
				while(rs.next()){
					actividad=new actividades();
					actividad.setId_actividad(rs.getInt("id_actividad"));
					actividad.setId_tarjeta(rs.getInt("id_tarjeta"));
					actividad.setListo(rs.getBoolean("listo"));
					actividad.setName_actividad(rs.getString("name_actividad"));
					
					array.add(actividad);
				}
				resp.setData(array);
				resp.setMessage("actividades contenidas");
				resp.setStatus(200);
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.setMessage("actividades no contenidas");
				resp.setStatus(400);
			}
			
		}else{
			
			resp.setMessage("actividades no contenidas");
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
	
	
}
