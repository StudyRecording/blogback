package com.hu.blogback.controller.usr;

import com.hu.blogback.util.AlipayUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

@Controller
@RequestMapping("/usr")
public class UserRecharge {

    @GetMapping("/recharge")
    public String recharge() {
        return "usr/recharge";
    }

    @GetMapping("/alipay")
    @ResponseBody
    public void alipay(HttpServletResponse response) {
        //System.out.println("------------支付Controller！");
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String mon = "";
        if (month > 9) {
            mon = String.valueOf(month);
        } else {
            mon = "0" + month;
        }
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        String dateStr = year + "_" + mon + "_" + day + "_" + hour + "_" + min + "_" + sec;
        String rand = String.valueOf((int)(Math.random() * 10000));
        String outTradeNo = dateStr + "_" + rand;
        System.out.println("-----------dateStr: " + outTradeNo);

    }
}
