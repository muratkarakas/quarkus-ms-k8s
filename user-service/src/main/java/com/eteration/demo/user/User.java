package com.eteration.demo.user;

import java.util.ArrayList;
import java.util.Collection;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "User")
public class User extends PanacheMongoEntity {

  private String code;
  private String name;
  private String surname;
  private Collection<Post> posts = new ArrayList<>();

  public User() {}

  public User(String code, String name, String surname) {
    this.code = code;
    this.name = name;
    this.surname = surname;
  }



  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Collection<Post> getPosts() {
    return posts;
  }

  public void setPosts(Collection<Post> posts) {
    this.posts = posts;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
