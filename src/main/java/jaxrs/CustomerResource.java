package jaxrs;

import Domain.Customer;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Raymond Phua on 22-9-2016.
 */
@Path("/customers")
public class CustomerResource implements DefaultResource<Customer>{

    @Override
    public Response getOne(int id) {
        return null;
    }

    @Override
    public Response getAll() {
        return null;
    }

    @Override
    public Response add(Customer customer) {
        return null;
    }

    @Override
    public Response update(Customer customer) {
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
