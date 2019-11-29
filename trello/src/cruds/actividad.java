package cruds;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.properties;
import utilities.poolManager;

public class actividad {

private properties prop=properties.getProp();
private poolManager pm=new poolManager();
private PreparedStatement ps;

public ResultSet insert(String name,int id, Connection con){
	;
	String sql=prop.getKey("insert_actividad");
	try {
		ps=con.prepareStatement(sql);
	
	ps.setString(1, name);
	ps.setInt(2, id);
	ps.setBoolean(3, false);

	
	return ps.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
         return null;	
	
	}
	
	
}//fin de insert
public ResultSet select(int id, Connection con){
	
	
	String sql=prop.getKey("select_actividad");
	try {
		ps=con.prepareStatement(sql);
		ps.setInt(1, id);
		
		return ps.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	return null;
	}
	
	
	
	
	
}//fin de select

public boolean updateName(String name,int id, Connection con){

	String sql=prop.getKey("update_actividadname");
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
	
	
	
}// fin de update name
public boolean UpdateListo(boolean listo,int id, Connection con){
	
	
	String sql=prop.getKey("update_actividadlisto");
	try {
		ps=con.prepareStatement(sql);
		ps.setBoolean(1, listo);
		ps.setInt(2, id);
		
		ps.executeQuery();
		return true;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	
}// fin de update listo


public boolean delete(int id,Connection con){
	
	
	String sql=prop.getKey("delete_actividad");
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
