/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

/**
 *
 * @author zoeller
 */
@Readiness
public class HealthCheck implements org.eclipse.microprofile.health.HealthCheck {
    
    @Inject
    @ConfigProperty(name = "javahippie.application.name")
    private String healthCheckName;

    @Override
    public HealthCheckResponse call() {
        return new HealthCheckResponse(healthCheckName, HealthCheckResponse.State.UP, null);
    }
    
}
