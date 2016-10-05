package com.infosupport.jaxrs;

import com.infosupport.Database.Database;
import com.infosupport.Domain.Product;
import com.infosupport.Domain.ProductSpec;
import com.infosupport.Properties.Name;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.crypto.Data;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 21-9-2016.
 */
@Path("/products")
public class ProductResource implements DefaultResource<Product> {

    private List<Product> products;
    @Context
    UriInfo uriInfo;

    public ProductResource() {

    }

    @Override
    public Response getOne(int id) {
        //Product product = products.stream().filter(p -> p.getDigitCode() == id).findFirst().orElseThrow(() -> new RuntimeException("error"));
        Product product = Database.getProductRepository().getOneProduct(id);
        return Response.ok(product).build();
    }

    @Override
    public Response getAll() {
        products = Database.getProductRepository().getAllProducts();

        if (products != null) {
            return Response.ok(this.products).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Override
    public Response add(Product product) {
        Map properties = new HashMap();
        properties.put("Name", Name.COLA);
        ProductSpec spec = new ProductSpec(properties);
        product.setProductSpec(spec);

        products.add(product);
        URI uri = URI.create(uriInfo.getAbsolutePath().toString() + product.getDigitCode());

        return Response.created(uri).entity(product).build();
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
