/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import de.javahippie.jax2020.checkout.pricing.PriceClient;
import health.*;
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
public class PricingHealthCheck implements org.eclipse.microprofile.health.HealthCheck {
    
    @Inject
    private PriceClient priceClient;

    @Override
    public HealthCheckResponse call() {
        Response priceHealth = priceClient.getHealth();
        if(priceHealth.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
             return HealthCheckResponse.up("PriceService");
        } else {
            return HealthCheckResponse.down("PriceService");
        }
      
    }
    
}
