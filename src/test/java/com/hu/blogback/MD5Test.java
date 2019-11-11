package com.hu.blogback;

import com.hu.blogback.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MD5Test {

    @Test
    public void md5Test() {

        System.out.println(MD5Util.code("admin"));
    }
}
