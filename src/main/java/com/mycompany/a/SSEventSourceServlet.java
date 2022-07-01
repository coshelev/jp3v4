import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.servlets.EventSource;
//import org.eclipse.jetty.servlets.EventSourceServlet;

public class SSEventSourceServlet extends org.eclipse.jetty.servlets.EventSourceServlet
{
    @Override
    protected EventSource newEventSource(HttpServletRequest request)
    {
        SSEventSource eS = new SSEventSource(); 
        this.EvSourse = eS;
        return eS;
        //return new SSEventSource();
    }

    public SSEventSource EvSourse;
    public SSEventSource getEventSource(){
        return this.EvSourse;
    }
}