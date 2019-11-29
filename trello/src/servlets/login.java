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
import pojos.user;
import cruds.users;
import utilities.poolManager;
/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		cripto cripto=new cripto();
		
		
		ObjectMapper om=new ObjectMapper();
		Respuesta <user> resp=new Respuesta <user>();
		user user=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),user.class);
			resp.setData(user);
			users crud =new users();
			String pass=cripto.getPass(user.getPassword());
			poolManager pm=new poolManager();
			Connection con=pm.setCon();
			ResultSet rs=crud.select(user.getEmail(),con);
			
			
			try {
				if(rs.getString("password")==pass){
					
					resp.setStatus(200);
					resp.setMessage("login exitoso");
					session.setAttribute("id", rs.getInt("id_users"));
					session.setAttribute("email", user.getEmail());
		
					
					
				}else{
					
					resp.setStatus(300);
					resp.setMessage("usuario u/o contrasena incorrectos");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pm.getCon(con);
			String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
	}

}
