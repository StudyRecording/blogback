package com.hu.blogback.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_setting")
public class BlogSetting implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    // 是否分类
    private boolean classification;

    // 是否标签
    private boolean tag;

    // 是否归档
    private boolean archive;

    // 是否关于我
    private boolean aboutMe;

    // 是否搜索
    private boolean search;

    // 侧边分类
    private boolean sideClassification;

    // 侧边标签
    private boolean sideTag;

    // 侧边推荐
    private boolean sideRecommend;

    // 侧边二维码
    private boolean sideQRcode;

    // 侧边信息
    private boolean side;

    // 底部二维码
    private boolean footerQRcode;

    // 底部email
    private String footerEmail;

    // 底部QQ
    private String footerQQ;

    // 博客底部是否显示评论
    private boolean comment;

    public BlogSetting() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isClassification() {
        return classification;
    }

    public void setClassification(boolean classification) {
        this.classification = classification;
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public boolean isAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(boolean aboutMe) {
        this.aboutMe = aboutMe;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public boolean isSideClassification() {
        return sideClassification;
    }

    public void setSideClassification(boolean sideClassification) {
        this.sideClassification = sideClassification;
    }

    public boolean isSideTag() {
        return sideTag;
    }

    public void setSideTag(boolean sideTag) {
        this.sideTag = sideTag;
    }

    public boolean isSideRecommend() {
        return sideRecommend;
    }

    public void setSideRecommend(boolean sideRecommend) {
        this.sideRecommend = sideRecommend;
    }

    public boolean isSideQRcode() {
        return sideQRcode;
    }

    public void setSideQRcode(boolean sideQRcode) {
        this.sideQRcode = sideQRcode;
    }

    public boolean isSide() {
        return side;
    }

    public void setSide(boolean side) {
        this.side = side;
    }

    public boolean isFooterQRcode() {
        return footerQRcode;
    }

    public void setFooterQRcode(boolean footerQRcode) {
        this.footerQRcode = footerQRcode;
    }

    public String getFooterEmail() {
        return footerEmail;
    }

    public void setFooterEmail(String footerEmail) {
        this.footerEmail = footerEmail;
    }

    public String getFooterQQ() {
        return footerQQ;
    }

    public void setFooterQQ(String footerQQ) {
        this.footerQQ = footerQQ;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BlogSetting{" +
                "id=" + id +
                ", classification=" + classification +
                ", tag=" + tag +
                ", archive=" + archive +
                ", aboutMe=" + aboutMe +
                ", search=" + search +
                ", sideClassification=" + sideClassification +
                ", sideTag=" + sideTag +
                ", sideRecommend=" + sideRecommend +
                ", sideQRcode=" + sideQRcode +
                ", side=" + side +
                ", footerQRcode=" + footerQRcode +
                ", footerEmail='" + footerEmail + '\'' +
                ", footerQQ='" + footerQQ + '\'' +
                ", comment=" + comment +
                '}';
    }
}
