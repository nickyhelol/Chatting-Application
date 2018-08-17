/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;

/**
 *
 * @author nicky
 */
public class ChatServer {

    public static final int PORT = 8888;
    public boolean stopRequested;
    public static HashMap<Integer, Socket> hm = new HashMap<>();
    public static int i = 1;

    public ChatServer() {
        stopRequested = false;
        startServer();
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
    }

    public void startServer() {
        stopRequested = false;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(2000);            // timeout for accept
            System.out.println("Server started at "
                    + InetAddress.getLocalHost() + " on port " + PORT);
        } catch (IOException e) {
            System.err.println("Server can't listen on port: " + e);
            System.exit(-1);
        }
        while (!stopRequested) {
            // block until the next client requests a connection
            // or else the server socket timeout is reached
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Connection made with "
                        + socket.getInetAddress());
                // start an echo with this connection
                EchoConnection echo = new EchoConnection(socket);
                Thread thread = new Thread(echo);
                thread.start();
                hm.put(i, socket);
                for (Socket s : hm.values()) {
                    Socket toSocket = s;
                    PrintWriter pw = new PrintWriter(toSocket.getOutputStream(), true);
                    pw.println("Client " + i + " has connected to the room.");
                }
                ++i;
            } catch (SocketTimeoutException e) {  
            } catch (IOException e) {
                System.err.println("Can't accept client connection: " + e);
                stopRequested = true;
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {  // ignore
        }
        System.out.println("Server finishing");
    }

    private class EchoConnection implements Runnable {

        private Socket socket; // socket for client/server communication

        public EchoConnection(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            PrintWriter pw = null; // output stream to client
            BufferedReader br = null; // input stream from client
            try {  // create an autoflush output stream for the socket
                pw = new PrintWriter(socket.getOutputStream(), true);
                // create a buffered input stream for this socket
                br = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                // echo client messages until DONE is received
                String input;
                String word = "All";
                while (true) {
                    // start communication by waiting for client request
                    input = br.readLine(); // blocking
                    if (input != null) {
                        String[] lines = input.split("/");

                        if (lines[0].equals(word)) {
                            for (Socket s : hm.values()) {
                                Socket toSocket = s;
                                pw = new PrintWriter(toSocket.getOutputStream(), true);
                                pw.println(lines[1]);
                            }
                        } else {
                            int n = Integer.parseInt(lines[1]);

                            Socket toSocket = hm.get(n);
                            pw = new PrintWriter(toSocket.getOutputStream(), true);
                            pw.println(lines[2]);
                        }
                    }
                }

            } catch (IOException e) {
                System.err.println("Server error: " + e);
            } finally {
                try {
                    if (pw != null) {
                        pw.close();
                    }
                    if (br != null) {
                        br.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    System.err.println("Failed to close streams: " + e);
                }
            }
        }
    }
}
