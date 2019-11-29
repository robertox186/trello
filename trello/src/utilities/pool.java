package utilities;

import java.util.ArrayList;
import java.sql.Connection;
import controllers.properties;
import controllers.conexionBd;

public class pool {

private static pool p=null;
private ArrayList  c=new ArrayList();
private properties prop=properties.getProp();
private conexionBd bd=new  conexionBd();


private pool(){
for(int i=0;i<15;i++){
	
	c.add(bd.getCon());
}
}
public  static pool getPool(){
	
	if(p==null){
		p=new pool();
		
	}
	return p;
}
public void setCon(Connection d){
	
	c.add(d);
}
 Connection getCon(){
	if(c.size()!=0){
	Connection d=(Connection)c.get(0);
	c.remove(0);
	return d;
}else{
	return null;
	
}
}

}

