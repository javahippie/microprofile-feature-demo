/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import de.javahippie.jax2020.checkout.inventory.InventoryClient;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

/**
 *
 * @author zoeller
 */
@Readiness
@ApplicationScoped
public class InventoryHealthCheck implements org.eclipse.microprofile.health.HealthCheck {
    
    @Inject
    private InventoryClient inventoryClient;

    @Override
    public HealthCheckResponse call() {
        Response inventoryHealth = inventoryClient.getHealth();
        if(inventoryHealth.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
             return HealthCheckResponse.up("InventoryService");
        } else {
            return HealthCheckResponse.down("InventoryService");
        }
      
    }
    
}
