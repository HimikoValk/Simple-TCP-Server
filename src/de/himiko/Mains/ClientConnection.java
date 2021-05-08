package de.himiko.Mains;

/**
 * @Author himiko
 */

import de.himiko.listener.PacketListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientConnection {


    private  final Socket socket;

    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private PacketListener packetListener;

    public ClientConnection(final Socket socket)
    {
        this.socket = socket;

    }

    public boolean establish()
    {
        try{
            this.dataInputStream =  new DataInputStream(this.socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());

            this.packetListener = new PacketListener(this);
            this.packetListener.start();
        }catch (Exception e)
        {
            return  false;

        }
        return true;
    }

    public void cancel()
    {
        try
        {
            this.socket.close();
            this.dataOutputStream.close();
            this.dataInputStream.close();

        }catch (Exception e)
        {

        }
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public Socket getSocket() {
        return socket;
    }


}
