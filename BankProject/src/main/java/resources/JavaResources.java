/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs. core.Response;
/**
 *
 * @author spotk
 */
@Path("java")
public class JavaResources {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
