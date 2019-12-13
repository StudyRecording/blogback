package com.hu.blogback;

import com.hu.blogback.util.NonsenseUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnumTest {

    @Test
    public void test() {
        System.out.println("--------Enum :" + NonsenseUtil.UserType.ADMIN);
    }
}
