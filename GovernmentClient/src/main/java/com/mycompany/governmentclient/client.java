/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.governmentclient;
import java.io.*;
import java.net.*;

/**
 *
 * @author spotk
 */
public class client {
    public static void main(String[] args) {
        String address = "time.nist.gov";
        int host = 13;
        try(Socket socket = new Socket(address, host))
        {
            InputStream input = socket.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input);
        
        
   
        int user;
        StringBuilder info = new StringBuilder();
        
        while((user = reader.read()) !=-1) {
            info.append((char) user);
        }
       System.out.println(info);
        }
       catch(IOException e){
           System.out.println("Host ID not found!");
           System.exit(1);
        }
    }
}
