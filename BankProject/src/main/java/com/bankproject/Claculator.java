/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bankproject;

/**
 *
 * @author spotk
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("Calculator")
public class Claculator {
    
    @GET
    @Path("/Bank/{Interest}/{Months}/{Amount}")
    public Response Test(@PathParam("Interest") int interest, @PathParam("Months") int months, @PathParam("Amount") int amount)
    {
        
        double MonthlyInterest = (double)interest / 12 / 100;
        double MonthlyIndicies = Math.pow((1 + MonthlyInterest), months);
        double discount = (MonthlyIndicies - 1) / (MonthlyInterest * MonthlyIndicies);
        double MonthlyPay = amount / discount;
        double totalPay = MonthlyPay * (double)months;
        
        String output = "<p>Monthly Payement is " + MonthlyPay + "<p>" + "</p>Total pay due is" +totalPay + "</P>";
        
        return Response.status(200).entity(output).build();
    }
}
