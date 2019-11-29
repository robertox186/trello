package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import controllers.cripto;
import pojos.Respuesta;
import pojos.project;
import cruds.projects;
import cruds.grupos;
import cruds.listas;
import utilities.poolManager;
/**
 * Servlet implementation class proyecto
 */
@WebServlet("/proyecto")
public class proyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public proyecto() {
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
		
		
		projects crud=new projects();
		ObjectMapper om=new ObjectMapper();
		Respuesta <project> resp=new Respuesta <project>();
		project project=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),project.class);
			resp.setData(project);
			poolManager pm=new poolManager();
			Connection con=pm.setCon();
		ResultSet rs=crud.insert(project.getName(),con);
			if(rs!=null){
				grupos g=new grupos();
				try {
					if(g.insert(rs.getInt("id_project"), (int)session.getAttribute("id"),con)){
						
					
						project.setId(rs.getInt("id_project"));
						resp.setData(project);
						resp.setStatus(200);
						resp.setMessage("project creado");
					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					resp.setMessage("error:proyecto no creado");
					resp.setStatus(400);
					resp.setMessage("error:grupo no creado");
					resp.setStatus(400);
					e.printStackTrace();
				}
			}else{
				resp.setMessage("error:proyecto no creado");
				resp.setStatus(400);
			}
			pm.getCon(con);
			String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
			}
	

	protected void doPut(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = entrada.getSession();
		
		
		projects crud=new projects();
		ObjectMapper om=new ObjectMapper();
		Respuesta <project> resp=new Respuesta <project>();
		project project=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),project.class);
			resp.setData(project);
		poolManager pm=new poolManager();
		Connection con=pm.setCon();
		if(crud.update(project.getName(), project.getId(),con)){
			
			resp.setMessage("actualizado con exito");
			resp.setStatus(200);
			
		}else{
			
			resp.setMessage("error en actualizacion");
			resp.setStatus(400);
		}
		pm.getCon(con);
		String sr=om.writeValueAsString(resp);
		
		response.getWriter().print(sr);
	}
	
}
