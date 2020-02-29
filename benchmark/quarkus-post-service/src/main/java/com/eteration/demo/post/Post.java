package com.eteration.demo.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "posts")
public class Post extends PanacheEntity{


    @Column(length = 40, unique = true)
    private String name;
    @Column(name="user_id")
    private Long userId;

    
    
    
    
    public Post(Long  id ,String name, Long userId) {
      super();
      this.name = name;
      this.userId = userId;
      super.id =  id;
    }

    public Post() {
    }

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

}
