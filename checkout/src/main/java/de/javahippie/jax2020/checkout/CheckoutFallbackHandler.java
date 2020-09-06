/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.javahippie.jax2020.checkout;

import javax.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

/**
 *
 * @author zoeller
 */
public class CheckoutFallbackHandler implements FallbackHandler<Response> {

    @Override
    public Response handle(ExecutionContext ec) {
        return Response.status(500).entity("Could not complete Request. Please try again later").build();
    }
    
}
