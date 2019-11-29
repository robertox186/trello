package utilities;

import java.sql.Connection;

public class poolManager {
private Connection con=null;
private pool p;
public poolManager (){
p=pool.getPool();
}

public Connection setCon(){
	con=p.getCon();
	if(con==null){
		con=p.getCon();
	}
	
	
return con;	
	
}

public void getCon(Connection c){
	p.setCon(c);
	
}


}//fin clase
