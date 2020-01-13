## zjf's project

## 资料
[Spring 文档](https://spring.io/guides/gs/serving-web-content/)
[github oauth](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)
[es](https://elasticsearch.cn/)
[mysql 菜鸟教程](https://www.runoob.com/mysql/mysql-insert-query.html) 
[Database Spring](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)

## 工具
[Git](https://git-scm.com/download)   
[Visual Paradigm](https://www.visual-paradigm.com)  

## 脚本
```sql
create table user
(
    id           int auto_increment
        primary key,
    account_id   varchar(100) null,
    name         varchar(50)  null,
    token        char(36)     null,
    GMT_CREATE   bigint       null,
    GMT_MODIFIED bigint       null
);

```