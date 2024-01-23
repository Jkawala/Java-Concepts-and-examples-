/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseytutorial;

/**
 *
 * @JamesKawala
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/Calculator")
public class CalculatorService {
    
    /* Using PathParam like http://localhost:49000/api/Calculator/Bank/6/360/10000*/    
    @GET
    @Path("/Bank/{Interest}/{Months}/{Amount}")
    public Response Test(@PathParam("Interest") int interest, @PathParam("Months") int months, @PathParam("Amount") int amount)
    {
        
        double MonthlyInterest = (double)interest / 12 / 100;
        double MonthlyIndicies = Math.pow((1 + MonthlyInterest), months);
        double discount = (MonthlyIndicies - 1) / (MonthlyInterest * MonthlyIndicies);
        double MonthlyPay = amount / discount;
        double totalPay = MonthlyPay * (double)months;
        
        String output = "<p>Monthly Payement is " + MonthlyPay + "<p>" + " </p>Total pay due is " +totalPay + " </P> ";
        
        return Response.status(200).entity(output).build();
    }
}
