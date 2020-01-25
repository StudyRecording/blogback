package com.hu.blogback.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "t_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    // 订单编号
    private String outTradeNo;

    // 支付宝交易号
    private String trade_no;

    // 交易金额
    private String totalAmount;

    // 订单标题
    private String subject;

    // 订单描述
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date stopDate;

    // 是否有效，过期后无效
    private boolean valid;

    // 交款用户
    @ManyToOne
    private User user;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", outTradeNo=" + outTradeNo +
                ", trade_no='" + trade_no + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", startDate=" + startDate +
                ", stopDate=" + stopDate +
                ", valid=" + valid +
                ", user=" + user +
                '}';
    }
}
