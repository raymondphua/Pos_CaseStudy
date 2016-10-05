package jaxrs;

import Domain.Product;
import Domain.ProductSpec;
import Domain.Transaction;
import Domain.TransactionFactory;
import Properties.Name;
import Properties.TransactionType;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
@Path("transactions")
public class TransactionResource implements DefaultResource<Transaction>{

    private static List<Transaction> transactions = new ArrayList<>();
    @Context UriInfo uriInfo;

    public TransactionResource() {
        //test transaction

        if (transactions.size() == 0) {
            TransactionFactory factory = new TransactionFactory();
            Transaction t = factory.createTransaction(TransactionType.SALE);

            Map properties = new HashMap();
            properties.put("Name", Name.COLA);
            ProductSpec spec = new ProductSpec(properties);
            Product p1 = new Product(5, 4.5, 5, spec, false);

            Map properties2 = new HashMap();
            properties2.put("Name", Name.ICETEA);
            ProductSpec spec2 = new ProductSpec(properties2);
            Product p2 = new Product(6, 4.5, 5, spec2, false);
            t.addProduct(p1);
            t.addProduct(p2);
            transactions.add(t);
        }
    }

    @Override
    public Response getOne(@PathParam("id") int id) {
        Transaction transaction = transactions.stream().filter(t -> t.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("error"));
        return Response.ok(transaction).build();
    }

    @Override
    public Response getAll() {
        return Response.ok(transactions).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{id}/products")
    public List<Product> getProductsFromOrder(@PathParam("id") int id) {
        return transactions.stream().filter(t -> t.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("error")).getProducts();
    }

    @POST
    @Path("{type}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createTransaction(@PathParam("type") String type) {

        TransactionFactory transactionFactory = new TransactionFactory();
        Transaction createdTransaction = transactionFactory.createTransaction(TransactionType.fromString(type));
        transactions.add(createdTransaction);

        URI uri = URI.create(uriInfo.getAbsolutePath().toString() + "/" + createdTransaction.getId());

        return Response.created(uri).build();
    }

    @POST
    @Path("/{id}/products")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addProductToTransaction(@PathParam("id")int id, Product product) {
        Transaction transaction = transactions.stream().filter(t -> t.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("404 Not Found"));
        transaction.addProduct(product);

        URI uri = URI.create(uriInfo.getAbsolutePath().toString() + product.getDigitCode());
        return Response.created(uri).entity(product).build();
    }

    @Override
    public Response update(Transaction transaction) {
        return null;
    }

    @Override
    public Response remove(int id) {
        Transaction removableTransaction = transactions.stream().filter(t -> t.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("error"));
        transactions.remove(removableTransaction);
        return Response.ok(transactions).build();
    }

    @Override
    public Response cancel(int id) {
        return null;
    }

    //NOT USED!
    @Override
    public Response add(Transaction transaction) {
        return null;
    }
}
