package de.javhippie.jax2020.inventory;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.auth.LoginConfig;
import org.eclipse.microprofile.jwt.Claim;
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
    public Response sayHello(@QueryParam("productNumber") String productNumber) {
        if(!inventory.containsKey(productNumber)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(inventory.get(productNumber)).build();
        }
    }
}
