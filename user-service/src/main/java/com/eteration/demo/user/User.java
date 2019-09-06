package com.eteration.demo.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class User {

    private String name;
    private String description;
    private String id;
    private Collection<Post> posts = new ArrayList<>();

    public User() {
    }

    public User(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;

        return Objects.equals(other.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

	public void setId(String id) {
        this.id = id;
	}

	public String getId() {
		return id;
	}

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }
}