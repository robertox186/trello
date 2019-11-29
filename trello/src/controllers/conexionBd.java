package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBd {
Connection con;
public properties p=properties.getProp();


public conexionBd(){
	try {
		Class.forName("org.postgressql");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public Connection getCon(){
	try {
		con=DriverManager.getConnection(p.getKey("bd"),p.getKey("user"),p.getKey("password"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;
}
}
