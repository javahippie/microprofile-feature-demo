package de.javahippie.jax2020.checkout;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claim;
import de.javahippie.jax2020.checkout.inventory.InventoryClient;
import de.javahippie.jax2020.checkout.pricing.PriceClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/checkout")
@Singleton
public class CheckoutController {

    @Inject
    @Claim("raw_token")
    private String rawToken;

    @Inject
    private PriceClient priceController;

    @Inject
    private InventoryClient inventoryController;

    @PUT
    @Fallback(CheckoutFallbackHandler.class)
    @CircuitBreaker(delay = 10_000L, requestVolumeThreshold = 5)
    @APIResponse(responseCode = "500", content = @Content(mediaType = "text/plain"), description = "Unknown Error")
    @APIResponse(responseCode = "404", content = @Content(mediaType = "text/plain"), description = "Product not found")
    @APIResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CheckoutResponse.class)), description = "The checkout result")
    public Response checkout(CheckoutRequest checkoutRequest) throws URISyntaxException {

        Integer availableAmount = inventoryController
                .getInventory(checkoutRequest.getProductNumber(), "Bearer " + rawToken)
                .readEntity(Integer.class);

        BigDecimal singlePrice = priceController
                .getPrice(checkoutRequest.getProductNumber(), "Bearer " + rawToken)
                .readEntity(BigDecimal.class);

        if (availableAmount < checkoutRequest.getAmount()) {
            return Response.serverError().entity("Could not check out that many items").build();
        }

        return Response.ok(new CheckoutResponse(availableAmount, singlePrice)).build();
    }

    @GET
    @Path("/availibility")
    public Response checkAvailability(List<String> productNumbers) {

        JsonObjectBuilder result = Json.createObjectBuilder();

        productNumbers
                .forEach(number -> result.add("product_" + number, inventoryController
                .getInventory(number, rawToken)
                .readEntity(Integer.class)));

        return Response.ok(result.build()).build();
    }

    private JsonObjectBuilder fetchAmount(String productNumber) {
        return Json.createObjectBuilder();
    }
}
