/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bankproject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
/**
 *
 * @author spotk
 */

@Path("/hello")
public class Tester {
    @GET
    @Path("/{param}")
    public javax.ws.rs.core.Response sayHelloWorld(@PathParam("param") String message) {
        String output = "Hello " + message + "!";
        return javax.ws.rs.core.Response.status(200).entity(output).build();
    }
}

