package de.javahippie.jax2020.pricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.auth.LoginConfig;

/**
 *
 */
@Path("/pricing")
@Singleton
@LoginConfig(authMethod = "MP-JWT", realmName = "master") 
public class PricingController {
    
    private final Map<String, BigDecimal> priceTable = new HashMap<>();
    
    public PricingController() { 
        priceTable.put("01", new BigDecimal("12.99"));
        priceTable.put("02", new BigDecimal("13.99"));
        priceTable.put("03", new BigDecimal("9.99"));
        priceTable.put("04", new BigDecimal("1.99"));
        priceTable.put("05", new BigDecimal("0.99"));
        priceTable.put("06", new BigDecimal("99.99"));
        priceTable.put("07", new BigDecimal("50.99"));
        priceTable.put("08", new BigDecimal("20.99"));
        priceTable.put("09", new BigDecimal("10.99"));
    }

    @GET
    @Path("/")
    @Produces("application/json")
    @RolesAllowed({"view-profile", "uma_protection", "uma_authorization"})
    public Response getPrice(@QueryParam("productNumber") String productNumber) {
        if(!priceTable.containsKey(productNumber)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(priceTable.get(productNumber)).build();
        }
    }
}
