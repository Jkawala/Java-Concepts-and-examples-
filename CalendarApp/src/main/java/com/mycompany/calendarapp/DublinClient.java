/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calendarapp;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
/**
 *
 * @author spotk
 */
public class DublinClient {
    private static InetAddress host;
    private static final int PORT = 1234;
    
    public static void main(String[] args) {
        try{
           host = InetAddress.getLocalHost(); 
        }
        
        catch(UnknownHostException e){
            System.out.println("Boss not found");
            System.exit(1);
        }
        run();
    }
    
    private static void run() {
        Socket link = null; 
        try{
            link = new Socket(host,PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
            PrintWriter out = new PrintWriter(link.getOutputStream(),true);
            
            BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in));
            String message = null;
            String response = null;
            
            System.out.println("Send message to server: ");
            message = userEntry.readLine();
            out.println(message);
            response = in.readLine();
            System.out.println("\nServer Response " + response);
        }
        catch(IOException e){    
        }
        finally{
            try{
                System.out.println("\n* Closing connections....*");
                link.close();
            }
            catch(IOException e){
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}
