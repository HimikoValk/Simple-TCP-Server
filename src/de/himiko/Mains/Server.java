package de.himiko.Mains;

/**
 * @Author himiko
 */

import de.himiko.listener.ConnectionListener;
import sun.misc.Cleaner;

import java.net.ServerSocket;

public class Server {

    private ServerSocket serverSocket;

    private ConnectionListener connectionListener;

    private final int port;

    public Server(final int port)
    {
        this.port = port;
    }

    public void start()
    {
        try{
            this.serverSocket = new ServerSocket(this.port);
            this.connectionListener = new ConnectionListener(this);
            this.connectionListener.start();


            System.out.println("[Himiko Server] Server succesfully started on Port: " + this.port);
        }catch (Exception e)
        {
           this.cancel();
        }
    }


    public void cancel()
    {
        System.out.println("[Himiko Server] Server stopped");
        try {
            this.connectionListener.cancel();
            this.serverSocket.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public  ServerSocket getServerSocket()
    {
        return  serverSocket;
    }
}
