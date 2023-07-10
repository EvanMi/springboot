package com.yumi.springboot.type;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@TypeUseAnnotation("class")
public class TestUse {
    public static class Bar <@TypeUseAnnotation("generic")  T> {
    }
    @TypeUseAnnotation("field")
    private String x = "x";
    private @TypeUseAnnotation String str(@TypeUseAnnotation("obj-ak") Object in,
                                          List<@TypeUseAnnotation("list-ak") String> list)
            throws @TypeUseAnnotation RuntimeException {

        // 我想这个的话，只有编译器能获取到了吧
        Long l = (@TypeUseAnnotation Long) in;
        return l.toString();
    }

    @Test
    public void test() throws Exception{

        for (Annotation annotation : TestUse.class.getAnnotations()) {
            if (annotation instanceof  TypeUseAnnotation tp) {
                System.out.println(tp.value());
            }
        }


        System.out.println("----------------");

        for (Annotation annotation : Bar.class.getTypeParameters()[0].getAnnotations()) {
            if (annotation instanceof  TypeUseAnnotation tp) {
                System.out.println(tp.value());
            }
        }

        System.out.println("----------------");

        Field xField = TestUse.class.getDeclaredField("x");
        for (Annotation annotation :  xField.getAnnotatedType().getAnnotations()) {
            if (annotation instanceof  TypeUseAnnotation tp) {
                System.out.println(tp.value());
            }
        }

        System.out.println("----------------");

        Method strMethod = TestUse.class.getDeclaredMethod("str", Object.class, List.class);
        AnnotatedType[] annotatedParameterTypes = strMethod.getAnnotatedParameterTypes();
        for (AnnotatedType annotatedParameterType : annotatedParameterTypes) {
            TypeUseAnnotation declaredAnnotation = annotatedParameterType.getDeclaredAnnotation(TypeUseAnnotation.class);
            if (null == declaredAnnotation) {
                if (annotatedParameterType instanceof AnnotatedParameterizedType ant) {
                    Annotation[] a2 = ant.getAnnotatedActualTypeArguments()[0].getAnnotations();
                    for (Annotation annotation : a2) {
                        if (annotation instanceof TypeUseAnnotation ta) {
                            System.out.println(ta.value());
                        }
                    }
                }
            } else {
                System.out.println(declaredAnnotation.value());
            }
        }



        System.out.println("----------------");

        AnnotatedType[] annotatedExceptionTypes = strMethod.getAnnotatedExceptionTypes();
        for (AnnotatedType annotatedExceptionType : annotatedExceptionTypes) {
            TypeUseAnnotation declaredAnnotation = annotatedExceptionType.getDeclaredAnnotation(TypeUseAnnotation.class);
            System.out.println(declaredAnnotation != null);
        }


        System.out.println("----------------");

        AnnotatedType annotatedReturnType = strMethod.getAnnotatedReturnType();
        TypeUseAnnotation declaredAnnotation = annotatedReturnType.getDeclaredAnnotation(TypeUseAnnotation.class);
        System.out.println(declaredAnnotation != null);

    }
}
