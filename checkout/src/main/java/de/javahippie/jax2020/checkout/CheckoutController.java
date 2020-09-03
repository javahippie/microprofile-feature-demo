package de.javahippie.jax2020.checkout;

import de.javahippie.jax2020.checkout.inventory.InventoryController;
import de.javahippie.jax2020.checkout.pricing.PriceController;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

@Path("/checkout")
@Singleton
public class CheckoutController {

    @Inject
    @Claim("raw_token")
    private String rawToken;

    private PriceController priceController;

    private InventoryController inventoryController;

    public CheckoutController() throws URISyntaxException {
        this.priceController = RestClientBuilder.newBuilder()
                .baseUri(new URI("http://pricing"))
                .build(PriceController.class);

        this.inventoryController = RestClientBuilder.newBuilder()
                .baseUri(new URI("http://inventory"))
                .build(InventoryController.class);
    }

    @PUT
    public Response checkout(CheckoutRequest checkoutRequest) throws URISyntaxException {

        //System.out.println(rawToken);

        BigDecimal singlePrice = priceController
                .getPrice(checkoutRequest.getProductNumber(), "Bearer " + rawToken)
                .readEntity(BigDecimal.class);
        
        Integer availableAmount = inventoryController
                .getPrice(checkoutRequest.getProductNumber(), "Bearer " + rawToken)
                .readEntity(Integer.class);
        
        if(availableAmount < checkoutRequest.getAmount()) {
            return Response.serverError().build();
        }

        return Response.ok(singlePrice).build();
    }
}
