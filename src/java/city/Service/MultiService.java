/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package city.Service;

import java.io.InputStream;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sfsuarez
 */
@Path("picture")
public class MultiService {
    
    String jsonresp = "";

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of IncidentService
     */
    public MultiService() {
    }

    /**
     * Retrieves representation of an instance of city.Service.MultiService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of MultiService
     * @param uploadedImageInputStream
     * @param content representation for the resource
     * @return 
     */
    /*@PUT
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
   @SuppressWarnings("unchecked")
    @Path("/uploadImagenData")

    @Produces(javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM)
    public String uploadImagenData(@FormDataParam("file")InputStream uploadedImageInputStream) {
        
        return "";
        
    }
*/
}
