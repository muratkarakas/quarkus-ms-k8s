package com.eteration.demo.user;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.ArrayList;


@RegisterRestClient
public interface PostServiceClient {

    @GET
    @Path("/posts/user/{userId}")
    @Produces("application/json")
    ArrayList<Post> getByUserId(@PathParam("userId") String userId);

    
}
