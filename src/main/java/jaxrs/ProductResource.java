package jaxrs;

import Domain.Product;
import Domain.ProductSpec;
import Properties.Name;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 21-9-2016.
 */
@Path("/products")
public class ProductResource implements DefaultResource<Product> {

    private static List<Product> products = new ArrayList<>();;
    @Context
    UriInfo uriInfo;

    public ProductResource() {

        if (products.size() == 0) {
            //hardcoded add
            Map properties = new HashMap();
            properties.put("Name", Name.COLA);
            products.add(new Product(1111, 1.5, 4, new ProductSpec(properties)));

            properties.clear();
            properties.put("Name", Name.ICETEA);
            products.add(new Product(2222, 2, 4, new ProductSpec(properties)));

            properties.clear();
            properties.put("Name", Name.MILK);
            products.add(new Product(3333, 1.25, 4, new ProductSpec(properties)));
        }
    }

    @Override
    public Response getOne(int id) {
        Product product = products.stream().filter(p -> p.getDigitCode() == id).findFirst().orElseThrow(() -> new RuntimeException("error"));
        return Response.ok(product).build();
    }

    @Override
    public Response getAll() {
        return Response.ok(this.products).build();
    }

    @Override
    public Response add(Product product) {
        Map properties = new HashMap();
        properties.put("Name", Name.COLA);
        ProductSpec spec = new ProductSpec(properties);

        product.setProductSpec(spec);

        this.products.add(product);

        URI uri = URI.create(uriInfo.getAbsolutePath().toString() + "/" + product.getDigitCode());

        return Response.created(uri).build();
}

    @Override
    public Response update(Product product) {
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
