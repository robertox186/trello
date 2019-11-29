package cruds;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.poolManager;
import controllers.properties;
public class projects {

private PreparedStatement ps;
private properties prop=properties.getProp();
private poolManager pm=new poolManager();


public ResultSet insert(String name, Connection con){
	
	String sql=prop.getKey("insert_project");
		
	try {
		ps=con.prepareStatement(sql);
	
		ps.setString(1, name);
		
		
		
		return ps.executeQuery();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	return null;
		}
		
	}// fin de insert
 public boolean update(String name, int id, Connection con){
	 
	 
		
		String sql=prop.getKey("update_project");
		try{
		ps=con.prepareStatement(sql);
		
		ps.setString(1, name);
		ps.setInt(2, id);
		
		ps.executeQuery();
		return true;
		}catch(SQLException e){
			e.getStackTrace();
			return false;
			
		}
		
		
 }	// fin de update
 
 public boolean delete(int id, Connection con){
	
		
		String sql=prop.getKey("delete_project");
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

