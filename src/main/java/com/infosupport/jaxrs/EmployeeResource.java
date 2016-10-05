package com.infosupport.jaxrs;

import com.infosupport.Domain.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 21-9-2016.
 */
@Path("/employees")
public class EmployeeResource implements DefaultResource<Employee> {

    private static List<Employee> employees = new ArrayList<>();
    @Context
    UriInfo uriInfo;

    public EmployeeResource() {

        if (employees.size() == 0) {
            employees.add(new Employee(1, "test1", "test1"));
            employees.add(new Employee(2, "test2", "test2"));
            employees.add(new Employee(3, "test3", "test3"));
        }
    }

    @Override
    public Response getOne(int id) {
        Employee employee = employees.stream().filter(e -> e.getId() == id).findFirst().orElseThrow(() -> new NotFoundException("Not found"));
        return Response.ok(employee).build();
    }

    @Override
    public Response getAll() {
        return Response.ok(this.employees).build();
    }

    @Override
    public Response add(Employee employee) {
        this.employees.add(employee);

        URI uri = URI.create(uriInfo.getAbsolutePath().toString() + "/" + employee.getId());

        return Response.created(uri).entity(employee).build();
    }

    //TODO: update
    @Override
    public Response update(Employee employee) {
        employees.stream().forEach((Employee e) -> {
            if (e.equals(employee)) {
                e = employee;
                return;
            }
        });
        //Employee updateEmployee = employees.stream().filter(e -> e.getId() == employee.getId()).findFirst().orElseThrow(() -> new NotFoundException("Not found"));
        //updateEmployee = employee;
        return Response.ok(employee).build();
    }

    @Override
    public Response remove(int id) {

        return Response.ok().build();
    }

    @Override
    public Response cancel(int id) {
        return Response.ok().build();
    }
}
