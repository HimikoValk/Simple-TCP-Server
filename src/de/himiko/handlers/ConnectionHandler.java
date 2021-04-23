package de.himiko.handlers;

/**
 * @Author himiko
 */

import de.himiko.Mains.ClientConnection;
import de.himiko.listener.ConnectionListener;

import java.util.LinkedList;

public class ConnectionHandler {

    public  static final ConnectionHandler INSTANCE = new ConnectionHandler();

    public LinkedList<ClientConnection> connections = new LinkedList<>();



}
