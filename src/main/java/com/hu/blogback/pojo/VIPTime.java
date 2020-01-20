package com.hu.blogback.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * vip时间类
 */
@Entity(name = "t_vip_time")
public class VIPTime {

    @Id
    @GeneratedValue
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date stopDate;

    // 是否有效，过期后无效
    private boolean valid;

    public VIPTime() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return "VIPTime{" +
                "userId=" + userId +
                ", startDate=" + startDate +
                ", stopDate=" + stopDate +
                ", valid=" + valid +
                '}';
    }
}
