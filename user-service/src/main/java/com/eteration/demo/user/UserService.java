package com.eteration.demo.user;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

@ApplicationScoped
public class UserService {

	@Inject
	MongoClient mongoClient;



	public List<User> list() {
		List<User> list = new ArrayList<>();
		MongoCursor<Document> cursor = getCollection().find().iterator();

		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				User fruit = new User();
				fruit.setName(document.getString("name"));
				fruit.setDescription(document.getString("description"));
				list.add(fruit);
			}
		} finally {
			cursor.close();
		}
		return list;
	}

	public void add(User user) {
		Document document = new Document().append("name", user.getName()).append("description", user.getDescription());
		getCollection().insertOne(document);
	}

	private MongoCollection getCollection() {
		return mongoClient.getDatabase("fruit").getCollection("fruit");
	}
}