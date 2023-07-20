package com.yumi.springboot.obj;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

public class ObjTest {

    /**
     * 1. 如果一个字段占用X个字节，那么这个字段的偏移量OFFSET需要对齐至NX
     * 2. 在开启了压缩指针的64位JVM中，Java类中的第一个字段的OFFSET需要对齐至4N，在关闭压缩指针的情况下类中第一个字段的OFFSET需要对齐至8N
     * 3. JVM默认分配字段的顺序为：long / double，int / float，short / char，byte / boolean，oops(Ordianry Object Point 引用类型指针)，
     * 并且父类中定义的实例变量会出现在子类实例变量之前。当设置JVM参数-XX +CompactFields时（默认），占用内存小于long / double 的字段会允许被插入
     * 到对象中第一个 long / double字段之前的间隙中，以避免不必要的内存填充
     */
    @Test
    public void testObj() {
        Child child = new Child();
        System.out.println(ClassLayout.parseInstance(child).toPrintable());
    }
}
