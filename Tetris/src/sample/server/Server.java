package sample.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static ServerSocket serverSocket;

    private static Socket player1Socet;
    private static Socket player2Socet;

    private static ObjectInputStream pl1In;
    private static ObjectInputStream pl2In;
    private static ObjectOutputStream pl1Out;
    private static ObjectOutputStream pl2Out;

    private static ArrayList<ArrayList<Integer>> player1Map;
    private static ArrayList<ArrayList<Integer>> player2Map;

    public static void start(int port){
        try {
            serverSocket = new ServerSocket(port);
            player1Socet = serverSocket.accept();
            System.out.println("pl1 connected");
            player2Socet = serverSocket.accept();
            System.out.println("pl2 connected");
            pl1In = new ObjectInputStream(player1Socet.getInputStream());
            pl2In = new ObjectInputStream(player2Socet.getInputStream());
            pl1Out = new ObjectOutputStream(player1Socet.getOutputStream());
            pl2Out = new ObjectOutputStream(player2Socet.getOutputStream());


            pl1Out.writeObject(1);
            player1Map = (ArrayList<ArrayList<Integer>>)pl1In.readObject();
            pl2Out.writeObject(1);
            player2Map = (ArrayList<ArrayList<Integer>>)pl2In.readObject();

            ExecutorService executorService = Executors.newFixedThreadPool(2);
            executorService.execute(new Thread(()->{
                while (true){
                    try {
                        player1Map = (ArrayList<ArrayList<Integer>>) pl1In.readObject();
                        pl1Out.writeObject(player2Map);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }));
            executorService.execute(new Thread(()->{
                while (true){
                    try {
                        player2Map = (ArrayList<ArrayList<Integer>>) pl2In.readObject();
                        pl2Out.writeObject(player1Map);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void stop(){
        try {
            pl1In.close();
            pl2In.close();
            pl1Out.close();
            pl2Out.close();
            serverSocket.close();
            player1Socet.close();
            player2Socet.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        start(6666);
    }
}
