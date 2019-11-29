package controllers;

	import java.io.IOException;
import java.io.InputStream;
	import java.util.Properties;

	public class properties {

		
		private Properties prop=null;
		private InputStream input;
		private static properties p=null;
		
		private properties(){
			input=  ClassLoader.getSystemResourceAsStream("utilities/config.properties");		
		try {
			prop.load(input);
		
		input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		public static properties getProp(){
	      if(p==null){
	    	  p=new properties();
	    	  
	      }
		
		return p;
		}
		
		public String getKey(String i){
			
			return prop.getProperty(i);
			
		}
		
		}


