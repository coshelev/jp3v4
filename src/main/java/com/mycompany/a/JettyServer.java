
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyServer {

	private Server server;

	public void start() throws Exception {
		System.out.print("Starting jetty...");

		Server server = new Server(8680);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
 
        context.addServlet(new ServletHolder(new HelloServlet()),"/*");
        context.addServlet(new ServletHolder(new HelloServlet("Buongiorno Mondo")),"/it/*");
        context.addServlet(new ServletHolder(new HelloServlet("Bonjour le Monde")),"/fr/*");
	
	//Read autobroker mail
	context.addServlet(new ServletHolder(new MailServlet("mail")),"/mail/*");	

	// this servlet create web-page with script to create sse-connection to http-server to url "/ssevent/"
	context.addServlet(new ServletHolder(new Servlet2("From Servlet 2, creating sse-connetion to /ssevent/ url  ")),"/en/*");

	////////context.addServlet(new ServletHolder(new SSEventSourceServlet()),"/ssevent/*"); //=>
	SSEventSourceServlet SSEventSrlt = new SSEventSourceServlet();

	//this servlet create sse-stream for /en/ web-page
	context.addServlet(new ServletHolder(SSEventSrlt),"/ssevent/*");
	
	//emit event for /en/ web-page
	context.addServlet(new ServletHolder(new SleepServlet("sleep", SSEventSrlt)),"/sleep/*");

        try {
        	server.start();
        	server.dumpStdErr();
            server.join();
        	} catch (Exception e) {           
            	e.printStackTrace();
        };
   	}
}

