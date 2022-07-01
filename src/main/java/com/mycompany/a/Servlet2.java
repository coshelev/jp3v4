import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class Servlet2 extends HttpServlet
{
    private String greeting="From servlet2";
    public Servlet2(){}
    public Servlet2(String greeting)
    {
        this.greeting=greeting;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>"+greeting+"</h1>");
        response.getWriter().println("<div id=\"content\"></div>");
        response.getWriter().println("<script>");
        response.getWriter().println("var es = new EventSource(\"/ssevent\");");
        response.getWriter().println("es.addEventListener(\'open\', function(e) {");
        response.getWriter().println("document.getElementById(\'content\').innerHTML += \'Connections to the server established..<br/>\';}, false);");
        response.getWriter().println("es.onmessage = function(event){document.getElementById(\'content\').innerHTML += event.data + \'<br/>\';console.log(event.data)};");
        response.getWriter().println("</script>");
        response.getWriter().println("session=" + request.getSession(true).getId());
    }
}
