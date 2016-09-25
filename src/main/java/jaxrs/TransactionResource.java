package jaxrs;

import Domain.Product;
import Domain.Transaction;
import Domain.TransactionFactory;
import Properties.TransactionType;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 21-9-2016.
 */
@Path("transactions")
public class TransactionResource implements DefaultResource<Transaction>{

    private static List<Transaction> transactions = new ArrayList<>();
    @Context UriInfo uriInfo;

    public TransactionResource() {

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
    @Path("/{id}/product")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addProductToTransaction(@PathParam("id")int id, Product product) {
        Transaction transaction = transactions.stream().filter(t -> t.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("404 Not Found"));
        transaction.addProduct(product);

        return Response.ok(transaction).build();
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
