package test.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class JServer {

	public static void main(String[] args) throws Exception {
		Server s = new Server();
		
		SelectChannelConnector nioConnector = new SelectChannelConnector();
        nioConnector.setUseDirectBuffers(false);
        nioConnector.setPort(80);
        s.addConnector(nioConnector);
        
        
        HandlerCollection handlers = new HandlerCollection();
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        
        //web目录
        WebAppContext app = new WebAppContext("jetty_web/", "/");
        
        contexts.addHandler(app);
        
        
//        contexts.addContext("/", "/t/index.html");
        
        handlers.setHandlers(new Handler[]{contexts, new DefaultHandler()});
        s.setHandler(handlers);
        
        
		s.start();
		
		app.start();
	}
}
