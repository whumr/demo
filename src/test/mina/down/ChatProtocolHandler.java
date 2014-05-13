/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package test.mina.down;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.MdcInjectionFilter;

/**
 * {@link IoHandler} implementation of a simple chat server protocol.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class ChatProtocolHandler extends IoHandlerAdapter {

    private final Set<IoSession> sessions = Collections
            .synchronizedSet(new HashSet<IoSession>());

    private final Set<String> users = Collections
            .synchronizedSet(new HashSet<String>());

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
    	System.out.println("Unexpected exception." + cause.getMessage());
        // Close connection when unexpected exception is caught.
        session.close(true);
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
    	System.out.println("received: " + message);
        String theMessage = (String) message;
        String[] result = theMessage.split(" ", 2);
        String theCommand = result[0];

        try {

            ChatCommand command = ChatCommand.valueOf(theCommand);
            String user = (String) session.getAttribute("user");

            switch (command.toInt()) {

            case ChatCommand.QUIT:
                session.write("QUIT OK");
                session.close(true);
                break;
            case ChatCommand.LOGIN:

                if (user != null) {
                    session.write("LOGIN ERROR user " + user + " already logged in.");
                    return;
                }

                if (result.length == 2) {
                    user = result[1];
                } else {
                    session.write("LOGIN ERROR invalid login command.");
                    return;
                }

                // check if the username is already used
                if (users.contains(user)) {
                    session.write("LOGIN ERROR the name " + user + " is already used.");
                    return;
                }

                sessions.add(session);
                session.setAttribute("user", user);
                MdcInjectionFilter.setProperty(session, "user", user);

                // Allow all users
                users.add(user);
                session.write("LOGIN OK");
                broadcast("The user " + user + " has joined the chat session.");
                break;

            case ChatCommand.BROADCAST:

                if (result.length == 2) {
                    broadcast(user + ": " + result[1]);
                }
                break;
            default:
            	System.out.println("Unhandled command: " + command);
                break;
            }

        } catch (IllegalArgumentException e) {
        	System.out.println("Illegal argument\t" + e.getMessage());
        }
    }

    public void broadcast(String message) {
        synchronized (sessions) {
            for (IoSession session : sessions) {
                if (session.isConnected()) {
                    session.write("BROADCAST OK " + message);
                }
            }
        }
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        String user = (String) session.getAttribute("user");
        users.remove(user);
        sessions.remove(session);
        broadcast("The user " + user + " has left the chat session.");
    }

    public boolean isChatUser(String name) {
        return users.contains(name);
    }

    public int getNumberOfUsers() {
        return users.size();
    }

    public void kick(String name) {
        synchronized (sessions) {
            for (IoSession session : sessions) {
                if (name.equals(session.getAttribute("user"))) {
                    session.close(true);
                    break;
                }
            }
        }
    }
}
