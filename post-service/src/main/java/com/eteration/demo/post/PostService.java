package com.eteration.demo.post;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;


@ApplicationScoped
public class PostService {

    ObjectMapper mapper = new ObjectMapper();


	@Inject @Channel("post-create")
	Emitter<String> emitter;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional(value = TxType.REQUIRED)
    public void createPost(Post post) throws JsonProcessingException {
    	
		
        String payload = mapper.writeValueAsString(post);
        
		logger.info("Post create event {}", payload);
		Post.persist(post);
		emitter.send(payload); 
    }


}