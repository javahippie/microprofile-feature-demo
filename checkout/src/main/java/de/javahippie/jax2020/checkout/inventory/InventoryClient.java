/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javahippie.jax2020.checkout.inventory;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author zoeller
 */
@RegisterRestClient
public interface InventoryClient {
    
    @GET
    @Path("/data/inventory")
    @Produces("application/json")
    @Timeout(500L) 
    @Retry(maxRetries = 1)
    public Response getInventory(@QueryParam("productNumber") String productNumber, @HeaderParam("Authorization") String token); 
    
    @GET
    @Path("/health")
    public Response getHealth();
}
