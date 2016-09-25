package jaxrs;

import Domain.Discount;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 22-9-2016.
 */
@Path("/discounts")
public class DiscountResource implements DefaultResource<Discount> {

    private static List<Discount> discounts = new ArrayList<>();
    @Context
    UriInfo uriInfo;
    public DiscountResource() {

        if (discounts.size() == 0) {
            this.discounts.add(new Discount(0.1));
            this.discounts.add(new Discount(0.2));
            this.discounts.add(new Discount(0.5));
        }
    }

    @Override
    public Response getOne(int id) {

        Discount discount = discounts.stream().filter(d -> d.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("404 Not found"));

        return Response.ok(discount).build();
    }

    @Override
    public Response getAll() {
        return Response.ok(discounts).build();
    }

    @Override
    public Response add(Discount discount) {
        this.discounts.add(discount);
        URI uri = URI.create(uriInfo.getAbsolutePath() + "/" + discount.getId());
        return Response.created(uri).entity(discount).build();
    }

    @Override
    public Response update(Discount discount) {
        return null;
    }

    @Override
    public Response remove(int id) {
        return null;
    }

    @Override
    public Response cancel(int id) {
        return null;
    }
}
