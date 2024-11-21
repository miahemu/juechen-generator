#
数据库初始化
# @author 玦尘
# @from <a href="https://github.com/miahemu/juechen-generator">玦尘 - 代码生成器共享平台</a>

-- 创建库
create database if not exists juechen_generator;

-- 切换库
use juechen_generator;

-- 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`           bigint                                  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `userAccount`  varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
    `userPassword` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `userName`     varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '用户昵称',
    `userAvatar`   varchar(1024) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '用户头像',
    `userProfile`  varchar(512) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '用户简介',
    `userRole`     varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
    `createTime`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`     tinyint                                 NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY            `idx_userAccount` (`userAccount`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` (`id`, `userAccount`, `userPassword`, `userName`, `userAvatar`, `userProfile`, `userRole`,
                    `createTime`, `updateTime`, `isDelete`)
VALUES ('1', 'juechen', 'b0dd3697a192885d7c055db46155b26a', '程序员玦尘',
        'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png', '我有一头小毛驴我从来也不骑', 'admin',
        '2024-11-19 16:06:03', '2024-11-19 16:06:03', 0);
INSERT INTO `user` (`id`, `userAccount`, `userPassword`, `userName`, `userAvatar`, `userProfile`, `userRole`,
                    `createTime`, `updateTime`, `isDelete`)
VALUES ('2', 'juechen2', 'b0dd3697a192885d7c055db46155b26a', '普通玦尘',
        'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png', '我有一头小毛驴我从来也不骑', 'user',
        '2024-11-19 16:06:04', '2024-11-19 16:06:04', 0);


-- 代码生成器表
DROP TABLE IF EXISTS `generator`;
CREATE TABLE `generator`
(
    `id`          bigint   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(128) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '名称',
    `description` text COLLATE utf8mb4_unicode_ci COMMENT '描述',
    `basePackage` varchar(128) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '基础包',
    `version`     varchar(128) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '版本',
    `author`      varchar(128) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '作者',
    `tags`        varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签列表（json 数组）',
    `picture`     varchar(256) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '图片',
    `fileConfig`  text COLLATE utf8mb4_unicode_ci COMMENT '文件配置（json字符串）',
    `modelConfig` text COLLATE utf8mb4_unicode_ci COMMENT '模型配置（json字符串）',
    `distPath`    text COLLATE utf8mb4_unicode_ci COMMENT '代码生成器产物路径',
    `status`      int      NOT NULL                        DEFAULT '0' COMMENT '状态',
    `userId`      bigint   NOT NULL COMMENT '创建用户 id',
    `createTime`  datetime NOT NULL                        DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`  datetime NOT NULL                        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`    tinyint  NOT NULL                        DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY           `idx_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='代码生成器';

-- ----------------------------
-- Records of generator
-- ----------------------------
INSERT INTO `generator` (`id`, `name`, `description`, `basePackage`, `version`, `author`, `tags`, `picture`,
                         `fileConfig`, `modelConfig`, `distPath`, `status`, `userId`, `createTime`, `updateTime`,
                         `isDelete`)
VALUES ('1', 'ACM 模板项目', 'ACM 模板项目生成器', 'com.juechen', '1.0', '程序员玦尘', '["Java"]',
        'https://pic.yupi.icu/1/_r0_c1851-bf115939332e.jpg', '{}', '{}', NULL, '0', '1', '2024-11-19 16:08:29',
        '2024-11-19 16:08:29', 0);
INSERT INTO `generator` (`id`, `name`, `description`, `basePackage`, `version`, `author`, `tags`, `picture`,
                         `fileConfig`, `modelConfig`, `distPath`, `status`, `userId`, `createTime`, `updateTime`,
                         `isDelete`)
VALUES ('2', 'Spring Boot 初始化模板', 'Spring Boot 初始化模板项目生成器', 'com.juechen', '1.0', '程序员玦尘',
        '["Java"]', 'https://pic.yupi.icu/1/_r0_c0726-7e30f8db802a.jpg', '{}', '{}', NULL, '0', '1',
        '2024-11-19 16:08:29', '2024-11-19 16:08:29', 0);
INSERT INTO `generator` (`id`, `name`, `description`, `basePackage`, `version`, `author`, `tags`, `picture`,
                         `fileConfig`, `modelConfig`, `distPath`, `status`, `userId`, `createTime`, `updateTime`,
                         `isDelete`)
VALUES ('3', '玦尘外卖', '玦尘外卖项目生成器', 'com.juechen', '1.0', '程序员玦尘', '["Java", "前端"]',
        'https://pic.yupi.icu/1/_r1_c0cf7-f8e4bd865b4b.jpg', '{}', '{}', NULL, '0', '1', '2024-11-19 16:08:29',
        '2024-11-19 16:08:29', 0);
INSERT INTO `generator` (`id`, `name`, `description`, `basePackage`, `version`, `author`, `tags`, `picture`,
                         `fileConfig`, `modelConfig`, `distPath`, `status`, `userId`, `createTime`, `updateTime`,
                         `isDelete`)
VALUES ('4', '玦尘用户中心', '玦尘用户中心项目生成器', 'com.juechen', '1.0', '程序员玦尘', '["Java", "前端"]',
        'https://pic.yupi.icu/1/_r1_c1c15-79cdecf24aed.jpg', '{}', '{}', NULL, '0', '1', '2024-11-19 16:08:29',
        '2024-11-19 16:08:29', 0);
INSERT INTO `generator` (`id`, `name`, `description`, `basePackage`, `version`, `author`, `tags`, `picture`,
                         `fileConfig`, `modelConfig`, `distPath`, `status`, `userId`, `createTime`, `updateTime`,
                         `isDelete`)
VALUES ('5', '玦尘商城', '玦尘商城项目生成器', 'com.juechen', '1.0', '程序员玦尘', '["Java", "前端"]',
        'https://pic.yupi.icu/1/_r1_c0709-8e80689ac1da.jpg', '{}', '{}', NULL, '0', '1', '2024-11-19 16:08:29',
        '2024-11-19 16:08:29', 0);
