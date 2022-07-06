import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import org.eclipse.jetty.servlets.EventSource;

import java.io.BufferedReader;

//import org.eclipse.jetty.servlets.EventSourceServlet;

//This servlet must be subclassed to implement abstract method newEventSource(HttpServletRequest) 
//to return an instance of EventSource that allows application to listen for event source events 
//and to emit event source events. (https://archive.eclipse.org/jetty/9.0.0.RC2/apidocs/org/eclipse/jetty/servlets/EventSourceServlet.html)

public class SSEventSourceServlet extends org.eclipse.jetty.servlets.EventSourceServlet
{
    @Override
    protected EventSource newEventSource(HttpServletRequest request)
    {
        SSEventSource eS = new SSEventSource(); 
        this.EvSource = eS;
        return eS;
    }

    public SSEventSource EvSource;
    public SSEventSource getEventSource(){
        return this.EvSource;
    }

//------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	//response.setContentType("text/html");
        	//response.setStatus(HttpServletResponse.SC_OK);
        	//response.getWriter().println("<h1>SSEventSourceServlet</h1>");
        	//response.getWriter().println("session=" + request.getSession(true).getId());
		response.addHeader("Access-Control-Allow-Origin", "*");
		super.doGet(request, response);
    	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

    		BufferedReader reader = request.getReader();
		String str = reader.readLine();

    		if (str.equals("open")){
			this.newEventSource(request);
			if (this.getEventSource() == null)
				System.out.println("error: Event Source is null");
			else {
				System.out.println("Event Source is established");
				this.getEventSource().Hello();
				this.getEventSource().emitEvent(str);
			};
		};		
		if (str.equals("emit")){
			//this.newEventSource(request);
			this.getEventSource().Hello();
			this.getEventSource().emitEvent(str);
			//EvSource = newEventSource(request);
			//EvSource.emitEvent(str); 

                };

		response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("{"+str+"}");
        }
}
