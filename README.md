# 个人博客
简单的个人博客实现（个人学习练习使用，原本想搭建一个，但是花钱😂）
## 实现框架
Spring Boot + Jpa + Thymeleaf  
使用MySql数据库  
前端UI组件样式使用Segment UI
 
## 功能
- 博客前台
    - 博客显示
    - 分类
    - 标签
    - 查询
    - 关于我
    - 评论
- 博客后台
    - 登录
    - 添加、修改博客
    - 添加、修改标签
    
## 未实现功能 （有时间实现😁）
~~- 有且仅有一个管理员注册账号~~（已完成）
- 后台图片上传，博客中图片可通过后台自由设置
- 添加博客页面不常改变信息（页脚、关于我页面）可通过后台进行设置的功能
- 关于后台代码中的一些常量可通过properties文件进行设置或通过后台进行管理
- 评论的后台管理功能
- 后台管理员信息的管理功能
- 。。。。。。

## bug （有时间修改🙄）
~~1. 前台的分类、标签后的文章数不准确，现在文章数是草稿+发布，应改为仅发布的文章数，在前台应彻底隐藏草稿文章的信息。~~(已修复)