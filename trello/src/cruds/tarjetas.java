package cruds;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.properties;
import utilities.poolManager;

public class tarjetas {

private properties prop=properties.getProp();
private poolManager pm=new poolManager();
private PreparedStatement ps;


public ResultSet insert(String name,int id, Connection con){
	
	String sql=prop.getKey("insert_tarjeta");
	try {
		ps=con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, id);
		
		
		return ps.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	}// fin de insert
 public ResultSet select(int id,Connection con){
	
	 String sql=prop.getKey("select_tarjeta");
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

 
 public boolean update(String name,int id, Connection con){
	 
	 String sql=prop.getKey("update_tarjeta");
	 try {
		ps=con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, id);
		
		ps.executeQuery();
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	return false;
	}
	}// fin de update
 
 public boolean updateList(int lista,int tarjeta, Connection con){
	 
	 String sql=prop.getKey("update_tarjetalista");
	 try {
		ps=con.prepareStatement(sql);
		ps.setInt(1, lista);
		ps.setInt(2, tarjeta);
		ps.executeQuery();
		
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	 
 } 
 
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
