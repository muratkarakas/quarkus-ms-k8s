package com.eteration.demo.post;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.vertx.axle.pgclient.PgPool;
import io.vertx.axle.sqlclient.Row;
@Path("/posts")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {


  
  
  @Inject
  PgPool client;


  
  @GET
  @Path("/v3")
  public CompletionStage<List<Post>> getReactive(){
    return  findAll(client);
  }

  public  CompletionStage<List<Post>> findAll(PgPool client) {
    return client.query("SELECT * FROM posts").thenApply(pgRowSet -> {
        List<Post> list = new ArrayList<>(pgRowSet.size());
        for (Row row : pgRowSet) {
            list.add(new Post(row.getLong("id"), row.getString("name"), row.getLong("user_id")));
        }
        return list;
    });
  }



  


}
