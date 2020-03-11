package com.eteration.demo.post;

public class Post {

  private Long id;
  private String name;
  private Long userId;



  public Post(Long id, String name, Long userId) {
    this.name = name;
    this.userId = userId;
    this.setId(id);
  }

  public Post() {}

  public Post(String name) {
    this.name = name;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Post [id=" + id + ", name=" + name + ", userId=" + userId + "]";
  }

}
