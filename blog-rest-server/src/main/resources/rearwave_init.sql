/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.24 : Database - rearwave
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `t_rearwave_article` */

CREATE TABLE `t_rearwave_article` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(1) DEFAULT NULL COMMENT '文章类型 1 图文 2 视频 3 图集 4 碎语',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容 type为1时是图文内容，2时是视频地址，3时是图集的地址数组，4时是碎语内容',
  `is_original` int(1) DEFAULT NULL COMMENT '是否原创 0 不是  1 是',
  `abstract` varchar(64) DEFAULT NULL COMMENT '摘要',
  `status` int(1) DEFAULT NULL COMMENT '状态 0 草稿 1 发布',
  `sync_status` int(1) DEFAULT NULL COMMENT '同步状态 0 未同步 1 已同步',
  `category` int(11) DEFAULT NULL COMMENT '文章分类 对应分类表的id',
  `tags` varchar(50) DEFAULT NULL COMMENT '文章标签 格式如 1,2,3',
  `cover_iamges` varchar(150) DEFAULT NULL COMMENT '封面图，对应封面图的id 格式如 1,2,3',
  `view_count` int(11) DEFAULT NULL COMMENT '阅读次数',
  `reply_count` int(11) DEFAULT NULL COMMENT '评论次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` int(11) DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

/*Table structure for table `t_rearwave_attach` */

CREATE TABLE `t_rearwave_attach` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `filename` varchar(100) DEFAULT NULL COMMENT '文件名',
  `file_type` varchar(10) DEFAULT NULL COMMENT '文件类型',
  `file_path` varchar(250) DEFAULT NULL COMMENT '文件路径',
  `platform` int(1) DEFAULT NULL COMMENT '0 本地存储 1 阿里云 2 腾讯云 3 七牛云',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件表';

/*Table structure for table `t_rearwave_category` */

CREATE TABLE `t_rearwave_category` (
  `id_` int(11) DEFAULT NULL COMMENT '主键',
  `name` varchar(15) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '0 无效 1 有效',
  `create_user` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_rearwave_comments` */

CREATE TABLE `t_rearwave_comments` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` int(11) DEFAULT NULL COMMENT '文章id',
  `content` text COMMENT '评论内容',
  `reply_id` int(11) DEFAULT NULL COMMENT '回复评论id',
  `reply_tree` varchar(200) DEFAULT NULL COMMENT '评论树 记录评论的层级 格式为评论id拼接 例如：1,3,6,11,25',
  `user_agent` varchar(100) DEFAULT NULL COMMENT '评论工具 如 Chrome IE Iphone Android等',
  `is_markdown` int(1) DEFAULT '0' COMMENT '是不是Markdown内容 0 不是 1 是',
  `create_user` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_rearwave_config` */

CREATE TABLE `t_rearwave_config` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `config_key` varchar(24) DEFAULT NULL,
  `config_value` varchar(64) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0 后台 1 前台 2 第三方',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

/*Table structure for table `t_rearwave_link` */

CREATE TABLE `t_rearwave_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL COMMENT '标题',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `logo` varchar(255) NOT NULL COMMENT '文件',
  `href` varchar(255) NOT NULL COMMENT '跳转的路径',
  `sort` smallint(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='友情链接表';

/*Table structure for table `t_rearwave_logs` */

CREATE TABLE `t_rearwave_logs` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `api` varchar(50) DEFAULT NULL,
  `user_ip` varchar(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL COMMENT 'query update insert delete',
  `system` varchar(20) DEFAULT NULL,
  `user_agent` varchar(50) DEFAULT NULL,
  `soft_version` varchar(50) DEFAULT NULL,
  `parameters` text COMMENT '请求参数',
  `status` varchar(5) DEFAULT NULL COMMENT '请求状态',
  `duration` int(11) DEFAULT NULL COMMENT '处理耗时（单位：毫秒）',
  `description` varchar(100) DEFAULT NULL COMMENT '请求描述',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_rearwave_social_config` */

CREATE TABLE `t_rearwave_social_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(32) NOT NULL COMMENT 'qq、csdn、wechat、weibo、email等',
  `content` varchar(100) DEFAULT NULL COMMENT '社交内容',
  `show_type` smallint(6) NOT NULL COMMENT '展示类型( 1、显示图片，2、显示账号，3、跳转链接)',
  `remark` varchar(150) DEFAULT NULL COMMENT '备注',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `enable` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否启用 0 不启用 1 启用',
  `is_home` smallint(6) DEFAULT '0' COMMENT '是否主页社交信息 0 不是 1 是',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表社交信息表';

/*Table structure for table `t_rearwave_tags` */

CREATE TABLE `t_rearwave_tags` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `status` int(1) DEFAULT '1' COMMENT '0 无效 1 有效',
  `create_user` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_rearwave_users` */

CREATE TABLE `t_rearwave_users` (
  `id_` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(24) DEFAULT NULL COMMENT '用户名',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别 0 其他 1 男 2 女',
  `nikename` varchar(24) DEFAULT NULL COMMENT '昵称',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `channel` int(2) DEFAULT '0' COMMENT '来源 0 平台注册 1 微信登录 2 百度登录 3 GitHub登录 4 头条登录 5 微博登录',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT NULL COMMENT '0 正常 1 锁定',
  `avatar` varchar(150) DEFAULT NULL COMMENT '头像',
  `introduction` varchar(100) DEFAULT NULL COMMENT '个人介绍',
  `role` int(1) DEFAULT '0' COMMENT '角色 0 普通用户 1 管理员',
  `social_id` varchar(64) DEFAULT NULL COMMENT '第三方社交账号id',
  `access_key` varchar(64) DEFAULT NULL COMMENT 'API调用链接key',
  `secret_key` varchar(64) DEFAULT NULL COMMENT '私钥',
  `review_permissions` int(1) DEFAULT NULL COMMENT '评论权限 0 锁定 1 正常',
  `donation_amount` decimal(9,2) DEFAULT NULL COMMENT '捐赠金额 单位：元',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `phone_idx` (`phone`),
  UNIQUE KEY `username_idx` (`username`),
  UNIQUE KEY `email_idx` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
