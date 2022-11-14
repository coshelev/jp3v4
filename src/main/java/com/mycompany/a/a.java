import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class A{

	//private static final Logger logger = LogManager.getLogger("App");

	public static void main(String[] args){

		System.out.print ("	class A before jetty\n");
		//logger.info("class A (i.e app) before jetty");

		JettyServer server = new JettyServer();
		try {
        		server.start();
        	} catch (Exception e) {           
            		e.printStackTrace();
        	}
		System.out.print ("class A constructor end\n");
		return;  
	}

	
}
