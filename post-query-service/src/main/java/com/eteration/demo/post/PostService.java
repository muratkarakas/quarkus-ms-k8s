package com.eteration.demo.post;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PostService
 */
@ApplicationScoped
public class PostService {


	private Logger logger = LoggerFactory.getLogger(getClass());


	@Inject
    ElasticsearchClient elasticsearchClient;
    
    public void createPost(Post post) throws IOException {
        elasticsearchClient.createPost(post);

    }

    @Incoming("post-read")
	public void read(String post) throws IOException {
         logger.info("---- Post Receieved {} ------", post);
         elasticsearchClient.createPost(post);
	}

    public String searchpostByName(String name) throws IOException {
        return elasticsearchClient.searchPostByName(name);

    }
}