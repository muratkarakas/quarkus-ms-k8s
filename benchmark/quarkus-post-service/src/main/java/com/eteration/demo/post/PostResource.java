package com.eteration.demo.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.agroal.api.AgroalDataSource;

@Path("/posts")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {


  @Inject
  AgroalDataSource defaultDataSource;

  private final Logger logger = LoggerFactory.getLogger(getClass());


  @GET
  public List<Post> get() {
    return Post.findAll().list();
  }

  @GET
  @Path("/v2")
  public List<Post> getJDBC() {
    List<Post> posts = new ArrayList<>();


    try (Connection con = defaultDataSource.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT * FROM posts")) {

      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          posts.add(new Post(rs.getLong("id"), rs.getString("name"), rs.getLong("user_id")));
        }
      }
    } catch (SQLException e) {
      logger.error("getJDBC Error",e);
    }



    return posts;
  }

  @GET
  @Path("{id}")
  public Post getSingle(@PathParam Long id) {
    return Post.findById(id);
  }

  @GET
  @Path("/user/{userId}")
  public List<Post> getUserPosts(@PathParam Long userId) {
    logger.info("getUserPosts {} ",userId);
    return Post.list("userId", userId);
  }

  @POST
  @Transactional
  public void create(Post post) {
    Post.persist(post);
  }



}
