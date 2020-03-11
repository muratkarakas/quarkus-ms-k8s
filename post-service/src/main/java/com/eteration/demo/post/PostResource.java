package com.eteration.demo.post;

import java.util.List;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/posts")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {



  @GET
  @Retry(maxRetries = 5)
  public List<Post> get() {
    //ramdomFail();
    return Post.findAll().list();
  }


  private void ramdomFail() {
    if (new Random().nextBoolean()) {
      System.out.println("Silent  Fail");
      throw new RuntimeException("Resource failure.");
    }
  }

  @GET
  @Path("{id}")
  public Post getSingle(@PathParam Long id) {

    return Post.findById(id);
  }

  @GET
  @Path("/user/{userId}")
  public List<Post> getUserPosts(@PathParam Long userId) {
    System.out.println(userId);
    return Post.list("userId", userId);
  }

  @POST
  @Transactional
  public void create(Post post) {
    Post.persist(post);
  }



}
