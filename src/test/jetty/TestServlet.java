package test.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.continuation.Continuation;
import org.eclipse.jetty.continuation.ContinuationSupport;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget....");
		Object o = request.getAttribute("aaaa");
		System.out.println("o=" + o);

		final Continuation continuation = ContinuationSupport.getContinuation(request);
		
		System.out.println("continuation.isInitial()\t" + continuation.isInitial());
		System.out.println("continuation.isResumed()\t" + continuation.isResumed());
		System.out.println("continuation.isSuspended()\t" + continuation.isSuspended());
		System.out.println("continuation.isExpired()\t" + continuation.isExpired());
		
		
		if (o == null) {
			if(continuation.isInitial()) {
				request.setAttribute("aaaa", 1111);
				continuation.suspend();
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
	//					continuation.setAttribute("aaaa", 1111);
	//					if (continuation.isSuspended()) {
							continuation.resume();
							System.out.println("----------------resume----------");
	//					}
					}
				}).start();
				System.out.println("isInitial...\n");
			}
//			continuation.undispatch();
		} else {
			String s = "start" + System.currentTimeMillis();
			response.setContentType("text/html");
			response.getWriter().write(s);
			response.getWriter().flush();
			System.out.println(s);
			System.out.println("isResumed...\n");
		}

	}
}