package com.hu.blogback.util;

/**
 * Application工具类
 */
public class NonsenseUtil {

    /**
     * 用户类型
     */
    public enum UserType {

        // 管理员，文章作者
        ADMIN(0),

        // vip用户，可以查看vip文章
        VIP(1),

        // 普通用户、游客
        NORMAL(2);

        private int data;

        private UserType(Integer data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    /**
     * 文章是否发布
     */
    public enum ArticlePublish {

        // 发布文章
        PUBLISH(true),

        // 草稿文章
        DRAFT(false);

        private boolean published;

        private ArticlePublish(Boolean  isPublished) {
            this.published = isPublished;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }
    }

}
