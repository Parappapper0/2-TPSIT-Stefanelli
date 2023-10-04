package com.itismeucci.stefanelli;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    protected ServerSocket serverSocket;
    protected Socket clientSocket;
    protected BufferedReader input;
    protected DataOutputStream output;

    protected static int clientNumber = 0;

    public void start(PrintStream progressMessageStream) {

        start(0, progressMessageStream);
    }

    public void start(int port, PrintStream progressMessageStream) {

        try {

            serverSocket = new ServerSocket(port);
            progressMessageStream.println("Server aperto su ip " + Inet4Address.getLocalHost() + " e porta " + serverSocket.getLocalPort());
            
            accept();

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void accept() {

        try {

            clientSocket = serverSocket.accept();
            clientNumber++;
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new DataOutputStream(clientSocket.getOutputStream());

        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public void send(String message) {

        try {

            output.writeBytes(message);

        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public String receive() {
        
        try {

            return input.readLine();

        } catch (IOException e) {
            
            e.printStackTrace();
            return "ERROR";
        }
    }

    public void closeServer() {

        try {

            serverSocket.close();

        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public void closeClient() {

        try {
            
            clientSocket.close();

        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}