/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part2;

/**
 *
 * @author spotk
 */
public class task8 {
    public static void main(String[] args) {
    Counter counter = new Counter();
    Thread t1 = new Thread(counter, "Thread One");
    Thread t2 = new Thread(counter, "Thread Two");
    Thread t3 = new Thread(counter, "Thread Three");
    t1.start();
    t2.start();
    t3.start();
  }
}

public class Counter implements Runnable {
  private int c = 0;

  public void increment() {
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      System.out.println("Interrupted");
    }
    c++;
  }

  public void decrement() {
    c--;
  }

  public int getValue() {
    return c;
  }

  @Override
  public void run() {
    //incrementing
    increment();
    System.out.println("Counter value for " + Thread.currentThread().getName()+ " after increment is " + this.getValue());

    //decrementing
    decrement();
    System.out.println("Counter value at the end for " + Thread.currentThread().getName() + " is " + this.getValue());
    
  }

}
