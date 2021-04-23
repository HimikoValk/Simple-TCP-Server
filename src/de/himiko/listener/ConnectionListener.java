package de.himiko.listener;

/**
 * @Author himiko
 */

import de.himiko.Mains.ClientConnection;
import de.himiko.Mains.Server;
import de.himiko.handlers.ConnectionHandler;

import java.net.InetAddress;
import java.net.Socket;


public class ConnectionListener implements  Runnable{

   private final Server server;

   private InetAddress IP;
   private boolean running;

   public ConnectionListener(final Server server)
   {
       this.server = server;
   }

   public void start()
   {
       this.running = true;
       final Thread thread = new Thread(this);
       thread.start();
   }

   public void cancel()
   {
       this.running = false;
   }

    @Override
    public void run() {
       while(this.running)
       {
           try {
               final ClientConnection clientConnection = new ClientConnection((this.server.getServerSocket().accept()));

               boolean success = clientConnection.dataOutInPut();

               if(success)
               {
                   ConnectionHandler.INSTANCE.connections.add(clientConnection);
                   System.out.println("[Server] Connection added" + clientConnection.getSocket().getLocalAddress());
                   this.IP = clientConnection.getSocket().getLocalAddress();

               }



           }catch (Exception e)
           {
               e.printStackTrace();
           }
       }

    }
}
