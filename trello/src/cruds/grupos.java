package cruds;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.poolManager;
import controllers.properties;

public class grupos {

private properties prop=properties.getProp();
private poolManager pm=new poolManager();
private PreparedStatement ps;
private ResultSet rs;
public boolean insert(int project,int users, Connection con){
	
	
	String sql=prop.getKey("insert_grupo");
	try {
		ps=con.prepareStatement(sql);
		ps.setInt(1, users);
		ps.setInt(2, project);
		
		ps.executeQuery();
		return true;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	return false;
	
	}
}// fin de insert

public ResultSet selectProject(int id, Connection con){
	
	
	String sql=prop.getKey("select_grupoproject");
	try {
		ps=con.prepareStatement(sql);
		ps.setInt(1, id);
		
		return ps.executeQuery();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
		
	}
	
}//fin de select project

public ResultSet selectUsers(int id, Connection con){

	String sql=prop.getKey("select_grupousers");
	try {
		ps=con.prepareStatement(sql);
		ps.setInt(1, id);
		
		return ps.executeQuery();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
	
	
}// fin de select users

public ResultSet selectComplete(int id, Connection con){
	
	String sql=prop.getKey("select_complete");
	try {
		ps=con.prepareStatement(sql);
	
	ps.setInt(1, id);
	
	return ps.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}// fin de select complete


public boolean delete(int id, Connection con){
	
	
	String sql=prop.getKey("delete_grupo");
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
