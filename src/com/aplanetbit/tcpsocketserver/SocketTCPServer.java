package com.aplanetbit.tcpsocketserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class SocketTCPServer {
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public SocketTCPServer(int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
    }
    public void start() throws IOException {
        System.out.println("(Servidor) Server started...");
        socket = serverSocket.accept();
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("(Servidor) Conexión establecida...");
    }

    public void stop() throws IOException {
        System.out.println("(Servidor) Server closing connection...");
        is.close();
        os.close();
        socket.close();
        serverSocket.close();
        System.out.println("(Servidor) Server closed...");
    }

    public static void main(String[] args) {
       try {
           SocketTCPServer servidor = new SocketTCPServer(49171);
           servidor.start();
           System.out.println("Mensaje del cliente: " + servidor.is.read());
           servidor.os.write(200);
           servidor.stop();

       } catch (IOException e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
    }
    public InputStream getInputStream() {
        return is;
    }

    public OutputStream getOutputStream() {
        return os;
    }


}