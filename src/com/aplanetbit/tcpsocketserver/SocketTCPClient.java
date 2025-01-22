package com.aplanetbit.tcpsocketserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketTCPClient {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public SocketTCPClient(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void start() throws UnknownHostException, IOException {
        System.out.println("(Cliente) Establishing connection...");
        socket = new Socket(serverIP, serverPort);
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("(Cliente) Connection established...");
    }

    public void stop() throws IOException {
        System.out.println("(Cliente) Closing connection...");
        is.close();
        os.close();
        socket.close();
        System.out.println("(Cliente) Connection closed...");

    }

    public static void main (String[] args) {
        SocketTCPClient cliente = new SocketTCPClient("localhost", 49171);
        try {
            cliente.start();
            cliente.os.write(100);
            System.out.println("Mensaje del servidor: " + cliente.is.read());
            cliente.stop();
        } catch (UnknownHostException e) {
            e.printStackTrace();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
