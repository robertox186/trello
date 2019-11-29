package cruds;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.poolManager;
import controllers.properties;

public class users {

private PreparedStatement ps;
private properties prop=properties.getProp();
private poolManager pm=new poolManager();
private ResultSet rs;
  public boolean insert(String name,boolean sexo,String email,String password, Connection con ){
	  
	   String sql=prop.getKey("insert_user");
	   try {
		ps=con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setBoolean(2, sexo);
		ps.setString(3, email);
		ps.setString(4, password);
		
		ps.executeQuery();
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
return false;	
	}
	   
	   
	   
	   
   }//fin de insert

   public ResultSet select(String email, Connection con){
	   
	   
	   String sql=prop.getKey("select_user");
	   try {
		ps=con.prepareStatement(sql);
		
		ps.setString(1, email);
	
		
		rs=ps.executeQuery();
		
		
			
			return rs;
		
	} catch (SQLException e) {
		// TODO Auto-generated
		e.printStackTrace();
	return null;
	}
	   
	   
   }//fin se select
   public boolean updateEmail(int id,String email, Connection con){
	   
	   String sql=prop.getKey("update_email");
	   try {
		ps=con.prepareStatement(sql);
		ps.setString(1, email);
		ps.setInt(2, id);
		
		ps.executeQuery();
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	return false;
	}
	   
	   
   }// fin de update email
   
   
   public boolean updatePassword(String password,int id, Connection con){
	
	   String sql=prop.getKey("update_password");
	   try {
		ps=con.prepareStatement(sql);
		ps.setString(1, password);
		ps.setInt(2, id);
		
		ps.executeQuery();
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	return false;
	}
	   
	   
	   
   }// fin de update password
public boolean delete(int id, Connection con){
	
	String sql=prop.getKey("delete_user");
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
	
	
	
	
	}// fin de delete





}
