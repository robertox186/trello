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

import controllers.cripto;
import cruds.users;
import pojos.Respuesta;
import pojos.register;
import utilities.poolManager;
/**
 * Servlet implementation class registro
 */
@WebServlet("/registro")
public class registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registro() {
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
		Respuesta <register> resp=new Respuesta <register>();
		register register=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),register.class);
			resp.setData(register);
			String pass= cripto.getPass(register.getPassword());
			System.out.println("--------llegue hasta aqui");
			users crud=new users();
			poolManager pm=new poolManager();
			Connection con=pm.setCon();
			
	if(crud.insert(register.getName(), register.getSexo(), register.getEmail(), pass,con)){
		
		resp.setStatus(200);
		resp.setMessage("usuario creado");
        
	
	}else{
	resp.setStatus(350);
	resp.setMessage("error:usuario no creado");
	}
	
	
	pm.getCon(con);
	String sr=om.writeValueAsString(resp);
	
	response.getWriter().print(sr);
	}

}
