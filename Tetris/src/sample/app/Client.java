package sample.app;

import java.io.*;
import java.net.Socket;

public class Client {


    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public void startConnection(String ip, int port){
        try {
            clientSocket = new Socket(ip, port);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
