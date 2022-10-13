package resource; 



/**
*
*JamesKawala
*/
@Path("java")
public class JavaResource{

     @GET 
     public Response ping(){
         return Response 
                  .ok("ping")
                  .build();
}
}
