package com.infosupport.jaxrs;

import com.infosupport.Domain.FidelityCard;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Raymond Phua on 22-9-2016.
 */
@Path("/Fidelitycards")
public class FidelityCardResource implements DefaultResource<FidelityCard>{

    @Override
    public Response getOne(int id) {
        return null;
    }

    @Override
    public Response getAll() {
        return null;
    }

    @Override
    public Response add(FidelityCard fidelityCard) {
        return null;
    }

    @Override
    public Response update(FidelityCard fidelityCard) {
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
