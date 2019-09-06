package com.eteration.demo.post;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("posts")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class PostResource {

    @Inject
    EntityManager entityManager;

    @GET
    public Post[] get() {
        return entityManager.createNamedQuery("Posts.findAll", Post.class)
              .getResultList().toArray(new Post[0]);
    }

    @GET
    @Path("{id}")
    public Post getSingle(@PathParam Integer id) {
    	Post entity = entityManager.find(Post.class, id);
        if (entity == null) {
            throw new WebApplicationException("Post with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @GET
    @Path("/user/{userId}")
    public List<Post> getUserPosts(@PathParam Integer userId) {
    	List<Post> entity =  entityManager.createNamedQuery("Posts.findAll", Post.class).getResultList();
        if (entity == null) {
            throw new WebApplicationException("Posts with user id of " + userId + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Post fruit) {
        if (fruit.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        entityManager.persist(fruit);
        return Response.ok(fruit).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Post update(@PathParam Integer id, Post post) {
        if (post.getName() == null) {
            throw new WebApplicationException("Post Name was not set on request.", 422);
        }

        Post entity = entityManager.find(Post.class, id);

        if (entity == null) {
            throw new WebApplicationException("Post with id of " + id + " does not exist.", 404);
        }

        entity.setName(post.getName());

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Integer id) {
    	Post entity = entityManager.getReference(Post.class, id);
        if (entity == null) {
            throw new WebApplicationException("Post with id of " + id + " does not exist.", 404);
        }
        entityManager.remove(entity);
        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }
            return Response.status(code)
                    .entity(Json.createObjectBuilder().add("error", exception.getMessage()).add("code", code).build())
                    .build();
        }

    }
}
