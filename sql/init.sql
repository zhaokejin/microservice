-- ============================================================
-- Spring Cloud Alibaba 微服务项目 - 数据库初始化脚本
-- ============================================================
-- 数据库：mytest
-- MySQL 版本：8.0+
--
-- 使用方式：
--   docker exec -i mysql-standalone mysql -uroot -proot < init.sql
--   或 连接 MySQL 后执行：source /path/to/init.sql
-- ============================================================

-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS `mytest`
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_general_ci;

USE `mytest`;

-- ============================================================
-- 用户表（供 provider / provider-file / consul-provider 使用）
-- ============================================================
DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user`
(
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `user_name`   VARCHAR(50)  NOT NULL                COMMENT '用户名',
    `password`    VARCHAR(100) NOT NULL                COMMENT '密码',
    `phone`       VARCHAR(20)  DEFAULT NULL            COMMENT '手机号',
    `create_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_name` (`user_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  COMMENT ='用户表';

-- ============================================================
-- 测试数据
-- ============================================================
INSERT INTO `t_user` (`id`, `user_name`, `password`, `phone`) VALUES
    (1, 'zhangsan', '123456', '13800138001'),
    (2, 'lisi',     '123456', '13800138002'),
    (3, 'wangwu',   '123456', '13800138003');
