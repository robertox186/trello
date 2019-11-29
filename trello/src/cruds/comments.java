package cruds;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.properties;
import utilities.poolManager;

public class comments {

private properties prop=properties.getProp();
private  poolManager pm=new poolManager();
private PreparedStatement ps;


public ResultSet insert(String comment, int users,int tarjeta, Connection con){
	
	String sql=prop.getKey("insert_comentario"); 
			try {
				ps=con.prepareStatement(sql);
				
				ps.setString(1, comment);
				ps.setInt(2, users);
				ps.setInt(3, tarjeta);
		
				
				return ps.executeQuery();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		return null;
			}
			
			
	
	
}//fin de insert

public ResultSet select(int id, Connection con){
				
		
		String sql=prop.getKey("select_comentario");
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			
			
			return ps.executeQuery();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		}// fin de select
				
		
public boolean delete(int id, Connection con){
	
	String sql=prop.getKey("delete_comentario");
	try {
		ps=con.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ps.executeQuery();
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    return false;
	}
	
	
}






}
