/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part2;

/**
 *
 * @author spotk
 */
public class priority extends Thread {
    private String message;
    private int number;
    
    public priority(message m, int number) {
        message = m;
        this.number = number;
    }
    
    public void run(){
        int value = 0;
        for (int i = 0; i<10; i++) {
            value = message.get(this.number);
            System.out.println("Consumer #" + this.number + "Has gotten: " + value);
        }
    }
}
