package com.hu.blogback.controller;

import com.alipay.api.AlipayApiException;
import com.hu.blogback.pojo.Order;
import com.hu.blogback.pojo.User;
import com.hu.blogback.service.OrderService;
import com.hu.blogback.service.UserService;
import com.hu.blogback.util.AlipayUtil;
import com.hu.blogback.util.NonsenseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class AlipayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/alipay")
    public void alipay(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        AlipayUtil.alipay(outTradeNo, "10.00", "充值vip", "月卡vip",request, response);
    }

    @RequestMapping("/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
        AlipayUtil.callback(request1 -> {
//            String outTradeNo = "";
//            String tradeNo = "";
//            //String trade_status = "";
//            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//            //商户订单号
//            try {
//                outTradeNo = new String(request1.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
//                //支付宝交易号
//                tradeNo = new String(request1.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
//
//                //交易状态
//                //tradeStatus = new String(request1.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            //内部逻辑处理
//            dealWith(outTradeNo, tradeNo, );
        }, request, response);
    }

    @RequestMapping("/return")
    public String success(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
        Order success = AlipayUtil.success(request1 -> {
            String outTradeNo = "";
            String tradeNo = "";

            try {
                outTradeNo = new String(request1.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
                //支付宝交易号
                tradeNo = new String(request1.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //处理内部业务逻辑
            //dealWith(outTradeNo, tradeNo, request1.getServletContext());
            Order order = new Order();
            order.setTrade_no(tradeNo);
            order.setOutTradeNo(outTradeNo);
            return order;
        }, request, response);

        if (success != null) {
            boolean b = dealWith(success, request.getSession());
            if (b == true) {
                return  "success";
            } else {
                return "fail";
            }
        }  else {
            return "fail";
        }
    }


    private boolean dealWith(Order order, HttpSession session) {

        order.setSubject("充值vip");
        order.setBody("月卡vip");
        Date date = new Date();
        order.setStartDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        order.setStopDate(calendar.getTime());
        order.setTotalAmount("10.00");
        order.setValid(true);
        User user = (User) session.getAttribute("usr");
        order.setUser(user);
        Order save = orderService.save(order);
        if (save != null) {
            user.setType(NonsenseUtil.UserType.VIP.getData());
            User user1 = userService.saveUser(user);
            if (user1 != null) {
                System.out.println("----------------内部逻辑处理成功！---------------------");
                return true;
            }
        }
        return false;
    }
}
