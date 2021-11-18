/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dublinclientserver;
//importing tools that can access arrays, threads and user input//
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author JamesKawala
 */
public class CalendarServer{
    private static ServerSocket servSock;
    private static final int PORT =1234;
    private static int clientConnections = 0;
    private static ArrayList<String> arrayList;
            
    public static void main(String[] args){
        System.out.println("Opening Port. Give us a minute....\n");
        try{
            servSock = new ServerSocket(PORT);
        }
        catch(IOException e){
            System.out.println("Unable to attach to port, Try agian later!");
            System.exit(1);
        }
        do{
            run();
        }
        while(true);
    }
    
    
    private static void run(){
        Socket link = null;
        try{
            link = servSock.accept();
            clientConnections++;
            BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
            PrintWriter out = new PrintWriter(link.getOutputStream(),true);
            do {
                
            
            String message = in.readLine();
            String[] splitMessage = message.split(":");
            if(splitMessage[0].contains("add")){
                add(message);
                out.println("successfully added");
                arrayList.add(message);
            } 
            else if(splitMessage[0].contains("list")){
                
            }
            else if(splitMessage[0].contains("remove")){
                remove(message);
                out.println("Successfully removed");
                arrayList.remove(message);
                
            }
            System.out.println("Message recieverd from the client server: " + clientConnections + " "+ message);
            out.println("The message:" + message);
            } while(true);
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                System.out.println("\n* Closing connection.....*");
                link.close();
            }
            catch(IOException e){
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
        
        
    }
    public static void splitMessage(String message){
        String[] splitMessage  = message.split(":");
        if(splitMessage[0].contains("add")){
            add(message);
            message = splitMessage[1].concat(splitMessage[2]);
            
        }
        else if(splitMessage[0].contains("list")){
            for(int i = 0; i < splitMessage.length; i++){
                System.out.println(splitMessage[i]);
                System.out.println("Showing the list");
            }
            
        }
            
        }
    public static void add(String message) {
 Thread addClient = new Thread(); {
        arrayList.add(message);
        System.out.println("Successfully added");
    }
 addClient.start();
 try{
     addClient.join();
 }
 catch(InterruptedException x){
     System.out.println("Error");
 
 }
    }
    public static void list(String replyMessage){
        Thread listClient = new Thread();{
        System.out.println(arrayList);
    }
        listClient.start();
        try{
            listClient.join();
        }
        catch(InterruptedException x){
            System.out.println("errors");
            
        }
    }
 public static void remove(String message){
        Thread removeClient = new Thread();{
     arrayList.remove(message);
        System.out.println("Successfully removed");
    }
        removeClient.start();
        try{
            removeClient.join();
        }
        catch(InterruptedException x){
            System.out.println("errors");
            
        }
    }
}
