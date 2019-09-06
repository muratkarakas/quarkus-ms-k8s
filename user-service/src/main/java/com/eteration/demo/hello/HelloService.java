package com.eteration.demo.hello;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {

    public String greeting() {
        return "hello world";
    }

}
