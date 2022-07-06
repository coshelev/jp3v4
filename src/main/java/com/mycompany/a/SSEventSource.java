import java.io.IOException;
import java.util.Date;
 
public class SSEventSource implements org.eclipse.jetty.servlets.EventSource
{
    private Emitter emitter;

    public void onOpen(Emitter emitter) throws IOException {
        try{
            this.emitter = emitter;
            emitter.data("new server event " + new Date().toString());
	    System.out.println("after onOpen...");
        }   
        catch (IOException e) {
                e.printStackTrace();
        }   
 
    }

    public void emitEvent(String dataToSend) throws IOException{
        //this.emitter.data(dataToSend);
        try {
	      System.out.println("before emitEvent..." + dataToSend);
              this.emitter.data(dataToSend);  
        }   
        catch (IOException e) {
                e.printStackTrace();
        }   
    }

    public void onClose()
    {
    }

    public void Hello (){
       System.out.println("hello");   
    }
}
