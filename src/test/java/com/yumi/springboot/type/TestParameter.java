package com.yumi.springboot.type;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

public class TestParameter {

    @Test
    public void test() throws Exception{
        Annotation[] annotations = Foo.class.getTypeParameters()[0].getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation instanceof TypeParameterAnnotation);
        }
    }
}
