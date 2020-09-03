/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javahippie.jax2020.checkout.pricing;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author zoeller
 */
@Path("/data/pricing")
public interface PriceController {
    
    @GET
    @Path("/")
    @Produces("application/json")
    public Response getPrice(@QueryParam("productNumber") String productNumber, @HeaderParam("Authorization") String token); 
    
}
