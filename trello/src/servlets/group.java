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

import controllers.cripto;
import cruds.grupos;
import pojos.Respuesta;
import pojos.grupo;
import pojos.project;
import utilities.poolManager;
/**
 * Servlet implementation class group
 */
@WebServlet("/group")
public class group extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public group() {
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
		Respuesta <grupo> resp=new Respuesta <grupo>();
		grupo grupo=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),grupo.class);
			resp.setData(grupo);
			grupos crud=new grupos();
			poolManager pm= new poolManager();
			Connection con=pm.setCon();
			if(crud.insert(grupo.getId_project(), (int)session.getAttribute("id"),con)){
				
				resp.setMessage("usuario add");
				resp.setStatus(200);
				
			}else{
				resp.setMessage("error ");
				resp.setStatus(400);
				
			}
			pm.getCon(con);
			String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
			
	}
	
	// put es => proyectos
	protected void doPut(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
				HttpSession session = entrada.getSession();
				ArrayList p=new ArrayList();
				ObjectMapper om=new ObjectMapper();
				Respuesta <ArrayList> resp=new Respuesta <ArrayList>();
				grupo grupo=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),grupo.class);
					
					grupos crud=new grupos();
					poolManager pm=new poolManager();
					Connection con=pm.setCon();
					
					ResultSet rs=crud.selectComplete(grupo.getId_project(),con);
					
					try {
						project o=new project();
						while(rs.next()){
							o.setId(rs.getInt("id_project"));
							o.setName(rs.getString("name_project"));
							p.add(o);
							
						}
						
						resp.setData(p);
						resp.setMessage("select correcto");
						resp.setStatus(200);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						resp.setMessage("error");
						resp.setStatus(400);
						
					}
					pm.getCon(con);
					String sr=om.writeValueAsString(resp);
					
					response.getWriter().print(sr);
					
		
	}
}
