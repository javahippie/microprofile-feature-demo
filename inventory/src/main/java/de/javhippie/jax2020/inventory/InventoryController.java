package de.javhippie.jax2020.inventory;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 *
 */
@Path("/inventory")
@RequestScoped
@LoginConfig(authMethod = "MP-JWT", realmName = "master") 
public class InventoryController {
   
    private final Map<String, Integer> inventory = new HashMap<>();
    
    @Inject
    private JsonWebToken jwt;

    public InventoryController() {
        this.inventory.put("01", 10);
        this.inventory.put("02", 2_432);
        this.inventory.put("03", 73_291);
        this.inventory.put("04", 0);
        this.inventory.put("05", 405);
        this.inventory.put("06", 17);
        this.inventory.put("07", 4);
        this.inventory.put("08", 22);
        this.inventory.put("09", 2_000);
    }

    @GET
    @Produces("application/json")
    @RolesAllowed({"view-profile", "uma_protection", "uma_authorization"})
    public Response getProductAmount(@QueryParam("productNumber") String productNumber) throws InterruptedException {
        if(!inventory.containsKey(productNumber)) {
            Thread.sleep(5000L);
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(inventory.get(productNumber)).build();
        }
    }
    
    @PUT
    @Produces("application/json")
    @RolesAllowed({"view-profile", "uma_protection", "uma_authorization"})
    @Path("reduce")
    public Response reduceInventory(@QueryParam("productNumber") String productNumber, @QueryParam("amount") Integer amountToReduce) {
        if(!inventory.containsKey(productNumber)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            Integer currentAmount = inventory.get(productNumber);
            if(amountToReduce > currentAmount) {
                return Response.status(500, "Cannot create a negative inventory").build();
            }
            inventory.put(productNumber, currentAmount - amountToReduce);
            return Response.ok(inventory.get(productNumber)).build();
        }
    }
}
