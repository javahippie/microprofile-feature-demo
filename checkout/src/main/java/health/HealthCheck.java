/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

/**
 *
 * @author zoeller
 */
@Readiness
@ApplicationScoped
public class HealthCheck implements org.eclipse.microprofile.health.HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return new HealthCheckResponse("General", HealthCheckResponse.State.UP, null);
    }
    
}
