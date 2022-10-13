/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calenderserver;
import java.io.*;
import java.net.*;

/**
 *
 * @author spotk
 */
public class DublinServer {
private static ServerSocket servSocket;
private static final int PORT = 1234;
private static int clientConnections = 0;


public static void main(String[] args){
    System.out.println("Opening port....\n");
    try{ 
        servSocket = new ServerSocket(PORT);
    }
    catch(IOException e){
System.out.println("Unable to attach to the port");
System.exit(10);
    }
    do{ 
        run();
    }
    while(true);
}

private static void run(){
    Socket link = null;
    
    try{
        link = servSocket.accept();
        clientConnections++;
        String client_ID = "Customer " + clientConnections;
        Runnable resource = new clientConnectionsRun(link, client_ID);
   Thread t = new Thread (resource);
   t.start();
    }
    catch(IOException e1){
        e1.printStackTrace();
        try{
           System.out.println("\n* Closing connection *"); 
            link.close();
        }
        catch(IOException e2){
            System.out.println("Unable to disconnect! ");
            System.exit(1);
        }
    }
}
}
