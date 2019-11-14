package com.hu.blogback;

import com.hu.blogback.util.MarkdownUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MarkdownTest {

    @Test
    void markTest() {
        String table = "| hello | hi   | 哈哈哈   |\n" +
                "| ----- | ---- | ----- |\n" +
                "| 斯维尔多  | 士大夫  | f啊    |\n" +
                "| 阿什顿发  | 非固定杆 | 撒阿什顿发 |\n" +
                "\n";
        String a = "[imCoding 爱编程](http://www.lirenmi.cn)";

        String code = "### 冒泡排序\n" +
                "\n" +
                "将无序的数列，从前向后进行遍历，依次比较相邻的两个数，如果发现逆序，则交换这两个数，使较大的值逐渐向后移动，如同水底的气泡向上升的过程一样，因此成为冒泡排序\n" +
                "\n" +
                "```java\n" +
                "\t/**\n" +
                "\t * 冒泡排序\n" +
                "\t */\n" +
                "\tpublic static void bubbleSort(int[] a) {\n" +
                "\t\t\n" +
                "\t\tint temp = 0;\n" +
                "\t\tboolean flag = false;\n" +
                "\t\tfor(int i = 0; i < a.length - 1; i++) {\n" +
                "\t\t\t\n" +
                "\t\t\tfor(int j = 0; j < a.length - i -1; j++) {\n" +
                "\t\t\t\t\n" +
                "\t\t\t\tif(a[j] > a[j+1]) {\n" +
                "\t\t\t\t\tflag = true;\n" +
                "\t\t\t\t\ttemp = a[j];\n" +
                "\t\t\t\t\ta[j] = a[j+1];\n" +
                "\t\t\t\t\ta[j+1] = temp;\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "\t\t\tif(!flag) {\n" +
                "\t\t\t\tbreak;\n" +
                "\t\t\t} else {\n" +
                "\t\t\t\tflag = false;\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "```";

        System.out.println(MarkdownUtils.markdownToHtmlExtensions(code));
    }
}
