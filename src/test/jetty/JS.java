package test.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class JS {

	public static void main(String[] args) throws Exception {
		Server s = new Server();
		//threadPool
		QueuedThreadPool tp = new QueuedThreadPool(100);
		s.setThreadPool(tp);
		//Connector
		SelectChannelConnector nioConnector = new SelectChannelConnector();
        nioConnector.setPort(80);
		s.addConnector(nioConnector);
		//ContextHandlerCollection
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		//servlet
		ServletContextHandler context = new ServletContextHandler(contexts, "", ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new TestServlet()),"/*");
        //HandlerCollection
        HandlerCollection collection = new HandlerCollection();
        //Handler
        collection.setHandlers(new Handler[] { contexts, new DefaultHandler() });
        s.setHandler(collection);
        
        s.start();
	}

}
