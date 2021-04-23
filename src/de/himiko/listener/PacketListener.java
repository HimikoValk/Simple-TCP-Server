package de.himiko.listener;

/**
 * @Author himiko
 */

import de.himiko.Mains.ClientConnection;

import java.io.IOException;

public class PacketListener implements  Runnable{

    private  final ClientConnection clientConnection;
    private boolean running;


    public  PacketListener(final ClientConnection clientConnection)
    {
        this.clientConnection = clientConnection;
    }

    public  void start()
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
                final byte packetID = this.clientConnection.getDataInputStream().readByte();
            } catch (IOException e) {
                this.clientConnection.cancel();
            }
        }
    }
}
