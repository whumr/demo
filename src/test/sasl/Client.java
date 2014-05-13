package test.sasl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

public class Client {

	public static void main(String[] args) throws SaslException {
		String[] mechanisms = {"DIGEST-MD5"};  
		String mechanism = "DIGEST-MD5", authorizationId = "1", protocol = "xmpp", servername = "test";
		Map<String, ?> props = new HashMap<String, String>();
		CallbackHandler handler = new MyHandler();
		//client端机制  
		final SaslClient sc = Sasl.createSaslClient(mechanisms, authorizationId, protocol, servername, props, handler);  
		//server端机制  
		final SaslServer ss = Sasl.createSaslServer(mechanism, protocol, servername, props, handler);  
		
		
		/* 客户端如何使用机制 */
		// Get optional initial response
		byte[] response = (sc.hasInitialResponse() ? sc.evaluateChallenge(new byte[]{}) : new byte[]{});
		System.out.println("client : " + new String(response));
		
		// Send selected mechanism name and optional initial response to server
//		send(sc.getMechanismName(), response);
//
//		// Read response
//		msg = receive();
		
		
		byte[] challenge = ss.evaluateResponse(response);
		System.out.println("server : " + new String(challenge));
		
		while (!sc.isComplete() && !ss.isComplete()) {
			if (!sc.isComplete()) {
				response = sc.evaluateChallenge(challenge);
				System.out.println("client : " + new String(response));
			}
			if (!ss.isComplete()) {
				challenge = ss.evaluateResponse(response);
				System.out.println("server : " + new String(challenge));
			}
		}
		sc.dispose();
		ss.dispose();
	}
	
	static class MyHandler implements CallbackHandler {

		@Override
		public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
			for (Callback callback : callbacks) {
				System.out.println(callback.getClass().getName());
			}			
		}
		
	}

}
