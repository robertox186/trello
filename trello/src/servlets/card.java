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
import pojos.project;
import pojos.tarjeta;
import cruds.projects;
import cruds.tarjetas;
import utilities.poolManager;
/**
 * Servlet implementation class card
 */
@WebServlet("/card")
public class card extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public card() {
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
		
	poolManager pm=new poolManager();
	Connection con=null;
		tarjetas crud=new tarjetas();
		ObjectMapper om=new ObjectMapper();
		Respuesta <tarjeta> resp=new Respuesta <tarjeta>();
		tarjeta tarjeta=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),tarjeta.class);
			resp.setData(tarjeta);	
			con=pm.setCon();
			ResultSet rs=crud.insert(tarjeta.getName(), tarjeta.getId_lista(),con);
			if(rs!=null){
		try {
			tarjeta.setId_tarjeta(rs.getInt("id_tarjeta"));
			
			resp.setData(tarjeta);
             resp.setMessage("tarjeta creada");
             resp.setStatus(200);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.setMessage("error tarjeta no creada");
			resp.setStatus(400);
		}
		
			}else{
				resp.setMessage("error tarjeta no creada");
				resp.setStatus(400);	
				
			}
			pm.getCon(con);
			String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
		
	}

	protected void doPut(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
HttpSession session = entrada.getSession();
		
		poolManager pm=new poolManager();
		Connection con=null;
		tarjetas crud=new tarjetas();
		ObjectMapper om=new ObjectMapper();
		Respuesta <ArrayList> resp=new Respuesta <ArrayList>();
		tarjeta tarjeta=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),tarjeta.class);
			ArrayList array=new ArrayList();
	con=pm.setCon();
			ResultSet rs=crud.select(tarjeta.getId_lista(),con);
			
			
			if(rs!=null){
				
				try {
					while(rs.next()){
						
						tarjeta=new tarjeta();
						tarjeta.setId_lista(rs.getInt("id_lista"));
						tarjeta.setId_tarjeta(rs.getInt("id_tarjeta"));
						tarjeta.setName(rs.getString("name_tarjeta"));
						array.add(tarjeta);
					}
					
					resp.setData(array);
					resp.setMessage("tarjetas contenidas");
					resp.setStatus(200);
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resp.setMessage("error tarjetas no contenidas");
					resp.setStatus(400);
					
				}
				
			}else{
				resp.setMessage("error tarjetas no contenidas");
				resp.setStatus(400);
				
			}
			pm.getCon(con);
String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
	}
}
