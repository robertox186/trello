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
import cruds.listas;
import pojos.Respuesta;
import pojos.lista;
import utilities.poolManager;
/**
 * Servlet implementation class list
 */
@WebServlet("/list")
public class list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public list() {
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
		Respuesta <lista> resp=new Respuesta <lista>();
		lista lista=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),lista.class);
			resp.setData(lista);
			listas crud =new listas();
			poolManager pm= new poolManager();
			Connection con=pm.setCon();
			ResultSet rs=crud.insert(lista.getName(), lista.getId(),con);
			try {
				if(rs!=null){
				lista.setIdLista(rs.getInt("id_lista"));
				resp.setData(lista);
				resp.setStatus(200);
				resp.setMessage("lista creada");
				}else{
					resp.setMessage("error");
					resp.setStatus(400);
				}
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




protected void doPut(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = entrada.getSession();
	
	ArrayList array = new ArrayList();
	
	ObjectMapper om=new ObjectMapper();
	Respuesta <ArrayList> resp=new Respuesta <ArrayList>();
	lista lista=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),lista.class);
		
		listas crud =new listas();
		poolManager pm=new poolManager();
		Connection con =pm.setCon();
		
	ResultSet rs=crud.select(lista.getId(),con);
	
	if(rs!=null){
		
		
		
		try {
			while(rs.next()){
				lista=new lista();
				lista.setIdLista(rs.getInt("id_lista"));
				lista.setName(rs.getString("name"));
			
			array.add(lista);
			}
			
			resp.setData(array);
			resp.setMessage("exito");
			resp.setStatus(200);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			resp.setMessage("error");
			resp.setStatus(400);
		}
	}else{
		
		resp.setMessage("resultset null");
		resp.setStatus(404);
	}
	pm.getCon(con);
	
	String sr=om.writeValueAsString(resp);
	
	response.getWriter().print(sr);
}
}