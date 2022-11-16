import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.util.*;

class A{

	//private static final Logger logger = LogManager.getLogger("App");
	
	public static String AUTOBROKERMAIL_HOST     = "";
	public static String AUTOBROKERMAIL_LOGIN    = "";
	public static String AUTOBROKERMAIL_PASSWORD = "";

	public static void main(String[] args){

	   Gson gson= new Gson();

	   String buildinSettings = "{\"autobrokerMail.host\":\"imap.yandex.ru\", \"autobrokerMail.login\":\"luidorexpertALL\", \"autobrokerMail.password\":\"cfirdrfnabxyzmgk\",\"autobrokerMail.fileFound\":\"false\"}";

                HashMap<String, String> map = gson.fromJson(buildinSettings, HashMap.class);
		
		try{ 	
			String filename = "myapp-1.1_settings1.json";
			File f = new File(filename);
                	if (!f.exists())
                        	System.out.printf("settings file %1 does not exist \n", filename);
			if  (f.exists()){
				JsonReader reader = new JsonReader(new FileReader(filename));
    				map = gson.fromJson(reader, HashMap.class);	
				System.out.printf( "map to string: %s \n", gson.toJson(map));
				};
		}
		catch (Exception e) {
			System.out.print("exception!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		};

		System.out.printf("gson.toJson(map)=%s \n", gson.toJson(map));
		System.out.printf("map.toString() = %s \n",map.toString());

		AUTOBROKERMAIL_HOST = map.get("autobrokerMail.host");
		System.out.printf("AUTOBROKERMAIL_HOST = %s \n", AUTOBROKERMAIL_HOST);

		AUTOBROKERMAIL_LOGIN = map.get("autobrokerMail.login");
                System.out.printf("AUTOBROKERMAIL_HOST = %s \n", AUTOBROKERMAIL_LOGIN);

		AUTOBROKERMAIL_PASSWORD = map.get("autobrokerMail.password");
                System.out.printf("AUTOBROKERMAIL_HOST = %s \n", AUTOBROKERMAIL_PASSWORD);

		System.out.print ("	class A before jetty \n");
		//logger.info("class A (i.e app) before jetty");

		JettyServer server = new JettyServer();
		try {
        		server.start();
        	} catch (Exception e) {           
            		e.printStackTrace();
        	}

		return;  
	}
}
