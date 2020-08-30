package de.javahippie.jax2020.pricing;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 */
@Path("/pricing")
@Singleton
public class PricingController {

    @GET
    public String getPrice(String productNumber) {
        return "Hello World";
    }
}
