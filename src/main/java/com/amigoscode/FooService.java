package com.amigoscode;

import org.springframework.stereotype.Service;

@Service
public class FooService {

    private final Main.Foo foo;

    public FooService(Main.Foo foo) {
        this.foo = foo;
        System.out.println();
    }

    String getFooName() {
        return foo.name();
    }
}
