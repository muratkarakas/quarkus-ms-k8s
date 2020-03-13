package com.eteration.demo.post;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;

@ApplicationScoped
public class PostService {

	ObjectMapper mapper = new ObjectMapper();

	
	
	@Inject
	@Channel("post-create")
	Emitter<String> emitter;

	private Logger logger = LoggerFactory.getLogger(getClass());

	
	@Transactional
	public void createPost(Post post) throws JsonProcessingException {
		Post.persist(post);
		String payload = mapper.writeValueAsString(post);
		logger.info("Post create event {}", payload);
		emitter.send(payload);
	}

}