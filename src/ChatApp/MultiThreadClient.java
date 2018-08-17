/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatApp;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicky
 */
public class MultiThreadClient implements Runnable {

    private int clientNo;
    int portNumber = 8888;
    String host = "localhost";
    private String msgReceived = null;
    private Socket clientSocket = null;
    private PrintWriter pw = null;
    private BufferedReader br = null;
    private boolean closed = false;
    private ClientView view;

    public MultiThreadClient(ClientView view, int clientNo) {
        this.view = view;
        this.clientNo = clientNo;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            clientSocket = new Socket(host, portNumber);
            pw = new PrintWriter(clientSocket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (IOException ex) {
            Logger.getLogger(MultiThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (!closed) {
                msgReceived = br.readLine();
                while (msgReceived != null) {
                    view.getChatArea().append(msgReceived + "\n");
                    msgReceived = null;
                }
            }
        } catch (IOException e) {

        }
    }

    public void finish() {
        try {
            if (pw != null) {
                pw.close();
            }
            if (br != null) {
                br.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to close streams: " + e);
        }
    }

    public boolean isClosed() {
        return closed;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getHost() {
        return host;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public BufferedReader getBr() {
        return br;
    }

    public String getMsgReceived() {
        return msgReceived;
    }

    public PrintWriter getPw() {
        return pw;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setMsgReceived(String msgReceived) {
        this.msgReceived = msgReceived;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public ClientView getView() {
        return view;
    }
    
    public int getClientNo() {
        return clientNo;
    }
}
