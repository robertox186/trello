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
import pojos.comentario;
import pojos.project;
import cruds.comments;
import cruds.projects;
import utilities.poolManager;
/**
 * Servlet implementation class comment
 */
@WebServlet("/comment")
public class comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public comment() {
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
		
		comments crud=new comments();
		ObjectMapper om=new ObjectMapper();
		Respuesta <comentario> resp=new Respuesta <comentario>();
		comentario comentario=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),comentario.class);
			con=pm.setCon();
			
			ResultSet rs=crud.insert(comentario.getComentario(), (int)session.getAttribute("id"),comentario.getTarjeta(),con);
			if(rs!=null){
				
				try {
					comentario.setId_comment(rs.getInt("id_comment"));
					resp.setMessage("comentario creado con exito");
					resp.setStatus(200);
					resp.setData(comentario);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resp.setMessage("error comentario no creado");
					resp.setStatus(400);
				}
				
			}else{
				resp.setMessage("error comentario no creado");
				resp.setStatus(400);
			}
			pm.getCon(con);
String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
	}

	
	
	protected void doPut(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = entrada.getSession();
		poolManager pm= new poolManager();
		Connection con=null;
		ArrayList array=new ArrayList();
		comments crud=new comments();
		ObjectMapper om=new ObjectMapper();
		Respuesta <ArrayList> resp=new Respuesta <ArrayList>();
		comentario comentario=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),comentario.class);
		
		con=pm.setCon();
		ResultSet rs=crud.select(comentario.getTarjeta(),con);
			if(rs!=null){
				
				try {
					while(rs.next()){
						comentario=new comentario();
						comentario.setComentario(rs.getString("comment"));
						comentario.setId_comment(rs.getInt("id_comment"));
						comentario.setTarjeta(rs.getInt("id_tarjeta"));
						comentario.setUser(rs.getInt("id_users"));
						array.add(comentario);
						
					}
					
					resp.setData(array);
					resp.setMessage("comentarios contenidos");
					resp.setStatus(200);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resp.setMessage("error comentario no contenidos");
					resp.setStatus(400);
				}
				
				
			}else{
				resp.setMessage("error comentario no contenidos");
				resp.setStatus(400);
			}
			pm.getCon(con);
String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
}
}
