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
import pojos.comentario;
import cruds.comments;
import utilities.poolManager;
/**
 * Servlet implementation class updatecomment
 */
@WebServlet("/updatecomment")
public class updatecomment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatecomment() {
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
	
	//update
	protected void doPost(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = entrada.getSession();
		
		
		comments crud=new comments();
	
		ObjectMapper om=new ObjectMapper();
		Respuesta <comentario> resp=new Respuesta <comentario>();
		comentario comentario=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),comentario.class);
			resp.setData(comentario);
			poolManager pm=new poolManager();
			Connection con=pm.setCon();
			
		if(crud.delete(comentario.getId_comment(),con)){
			
			resp.setMessage("mensaje eliminado");
			resp.setStatus(200);
			
			
			
			
		}else{
			
			resp.setMessage("mensaje no eliminado");
			resp.setStatus(400);
		}
		pm.getCon(con);
		String sr=om.writeValueAsString(resp);
		
		response.getWriter().print(sr);
			}

}
