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

    @Inject UserService userService;

	@Inject
    @RestClient
    PostServiceClient postServiceClient;

    
    @GET()
    @Path("/init")
    public List<User> init() {
        userService.add(new User("Murat","Eteration"));
        return userService.list();
    }

    @GET()
    @Path("/{userId}")
    public User userDetail(@PathParam("userId") String userId) {
        Collection<Post> posts = postServiceClient.getByUserId(userId);

        User user =  userService.list().get(0);
        user.setPosts(posts);

        return user;
    }

    @GET
    public List<User> list() {
        return userService.list();
    }

    @POST
    public List<User> add(User fruit) {
        userService.add(fruit);
        return list();
    }
}