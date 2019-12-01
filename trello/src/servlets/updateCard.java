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

import cruds.tarjetas;
import pojos.Respuesta;
import pojos.tarjeta;
import utilities.poolManager;
/**
 * Servlet implementation class updateCard
 */
@WebServlet("/updateCard")
public class updateCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCard() {
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
	//update tarjeta lista
	protected void doPost(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = entrada.getSession();
		
		
		tarjetas crud=new tarjetas();
		ObjectMapper om=new ObjectMapper();
		Respuesta <tarjeta> resp=new Respuesta <tarjeta>();
		if(session!=null || entrada.isRequestedSessionIdValid()){
		tarjeta tarjeta=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),tarjeta.class);
			resp.setData(tarjeta);
			poolManager pm=new poolManager();
			Connection con=pm.setCon();
			if(crud.updateList(tarjeta.getId_lista(), tarjeta.getId_tarjeta(),con)){
				
				resp.setMessage("tarjeta actualizada sastifactoriamente");
				resp.setStatus(200);
			
				
			}else{
				
				resp.setMessage("error tarjeta no actualizada");
				resp.setStatus(400);;
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
		 tarjetas crud=new tarjetas();
		ObjectMapper om=new ObjectMapper();
		Respuesta <tarjeta> resp=new Respuesta <tarjeta>();
		if(session!=null || entrada.isRequestedSessionIdValid()){
		tarjeta tarjeta=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),tarjeta.class);
			resp.setData(tarjeta);
			poolManager pm=new poolManager();
			Connection con=pm.setCon();
			
			if(crud.update(tarjeta.getName(), tarjeta.getId_tarjeta(),con)){
				
				resp.setMessage("tarjeta atualizada sastifactoriamente");
				resp.setStatus(200);
				
			}else{
				resp.setMessage("error tarjeta no actualizada");
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
	protected void doDelete(HttpServletRequest entrada, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = entrada.getSession();
		 tarjetas crud=new tarjetas();
		ObjectMapper om=new ObjectMapper();
		Respuesta <tarjeta> resp=new Respuesta <tarjeta>();
		if(session!=null || entrada.isRequestedSessionIdValid()){
		tarjeta tarjeta=om.readValue(entrada.getReader().lines().collect(Collectors.joining(System.lineSeparator())),tarjeta.class);
			resp.setData(tarjeta);
			poolManager pm=new poolManager();
			Connection con=pm.setCon();
			if(crud.delete(tarjeta.getId_tarjeta(),con)){
				
				resp.setMessage("tarjeta eliminada sastifactoriamente");
				resp.setStatus(200);
			}else{
				
				resp.setMessage("error tarjeta no eliminada");
			    resp.setStatus(400);
			}
			pm.getCon(con);
		}else{
			
			resp.setMessage("no hay una session iniciada");
			resp.setStatus(300);
			
		}
			String sr=om.writeValueAsString(resp);
			
			response.getWriter().print(sr);
}
}