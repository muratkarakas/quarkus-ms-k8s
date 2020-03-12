package com.eteration.demo.post;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ElasticsearchClient
 */
@ApplicationScoped
public class ElasticsearchClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ConfigProperty(name = "elasticsearch.host", defaultValue = "localhost")
	String elasticsearchHost;

	@ConfigProperty(name = "elasticsearch.port", defaultValue = "9200")
	int elasticsearchPort;

	ObjectMapper mapper = new ObjectMapper();

	public void createPost(Post post) throws IOException {

		String payload = mapper.writeValueAsString(post);

		createPost(payload);

	}

	public void createPost(String payload) throws IOException {
		RestClient restClient = getRestClient();
		Request request = new Request("POST", "/posts/_doc");

		request.setJsonEntity(payload);
		logger.info("Elastic rest request {}", payload);
		Response response = restClient.performRequest(request);
		logger.info("Elastic rest response {}", response);
	}

	private RestClient getRestClient() {
		return RestClient.builder(new HttpHost(elasticsearchHost, elasticsearchPort, "http")).build();
	}

	public String searchPostByName(String name) throws ParseException, IOException {
		RestClient restClient = getRestClient();

		Request request = new Request("POST", "posts/_search?pretty");

		String queryEntity = "{\"query\": {\"wildcard\" : {\"name\" :  { \"value\" : \"" + name + "*\" }}}}";

		request.setJsonEntity(queryEntity);

		Response response = restClient.performRequest(request);

		String responseBody = EntityUtils.toString(response.getEntity());

		restClient.close();

		return responseBody;
	}
}