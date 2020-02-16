package com.eteration.demo.user;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {


	@Inject
    @RestClient
    PostServiceClient postServiceClient;

    
    @GET()
    @Path("/init")
    public List<User> init() {
        User.persist(new User("1","Murat","Karakas"));
        User.persist(new User("2","Defne","Karakas"));
        User.persist(new User("3","Deniz","Karakas"));
        return list();
    }

    @GET()
    @Path("/{userId}")
    public User userDetail(@PathParam("userId") String userId) {
        Collection<Post> posts = postServiceClient.getByUserId(userId);


        User user =  User.find("code", userId).singleResult();
        user.setPosts(posts);

        return user;
    }

    @GET
    public List<User> list() {
        return User.findAll().list();
    }

    @POST
    public void add(User user) {
        User.persist(user);
    }
}