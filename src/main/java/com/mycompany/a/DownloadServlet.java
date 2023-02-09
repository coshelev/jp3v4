import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.util.*;
import java.util.Base64;
import java.net.URI;
import java.net.http.*;
import org.json.JSONException;
import org.json.JSONObject;

public class DownloadServlet extends HttpServlet {
    private String greeting = "Hello from DownloadServlet";
    public DownloadServlet(){}
    public DownloadServlet(String greeting){
        this.greeting=greeting;
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>"+greeting+"</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
    }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
  	 StringBuffer jb = new StringBuffer();
	 String line = null;
	 
   	try {
		BufferedReader reader = request.getReader();
    		while ((line = reader.readLine()) != null){
      			jb.append(line);	
			System.out.println("line = "+line);
		};
    	} catch (Exception e) { /*report an error*/ }     
	

	String js = jb.toString();
	System.out.println("js = "+js);

	if (js.length()== 0)
		 return;

	//try {
		JSONObject jo	= new JSONObject(js);
		
	//} catch (JSONException e) {};
    	//	throw new IOException("Error parsing JSON request string");
 	// }

	System.out.println(jo);
	
	String filename = jo.getString("filename");
	String content  = jo.getString("content");

	System.out.println("* filename = "+ filename);
	System.out.println("* content = "+ content);
	System.out.println("**************************");	

	byte[] decodedBytes = Base64.getDecoder().decode(content);
	
	//Files.write(outputFile.toPath(), decodedBytes);
	
	String path = filename+".wav";
	try (FileOutputStream stream = new FileOutputStream(path)) {
    		stream.write(decodedBytes);}


   } 
}
