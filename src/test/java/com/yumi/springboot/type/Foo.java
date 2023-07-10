package com.yumi.springboot.type;

public class Foo <@TypeParameterAnnotation T>{
    private final T handler;

    public Foo(T handler) {
        this.handler = handler;
    }

    public T get() {
        return handler;
    }
}
