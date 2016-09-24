/*
 Navicat MySQL Data Transfer

 Source Server         : 148
 Source Server Version : 50545
 Source Host           : 121.43.101.148
 Source Database       : jf_std_user

 Target Server Version : 50545
 File Encoding         : utf-8

 Date: 09/06/2016 22:42:51 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tstd_bankcard`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_bankcard`;
CREATE TABLE `tstd_bankcard` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `bank_code` varchar(8) DEFAULT NULL COMMENT '银行行号',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `subbranch` varchar(255) DEFAULT NULL COMMENT '开户支行',
  `bankcard_no` varchar(64) DEFAULT NULL COMMENT '银行卡编号',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '银行卡绑定手机号',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_sign_log`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_sign_log`;
CREATE TABLE `tstd_sign_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `address` varchar(255) DEFAULT NULL COMMENT '地区',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_user`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_user`;
CREATE TABLE `tstd_user` (
  `user_id` VARCHAR(32) NOT NULL COMMENT 'userId',
  `login_name` VARCHAR(32) NULL DEFAULT NULL COMMENT '登陆名',
  `nickname` VARCHAR(32) NULL DEFAULT NULL COMMENT '昵称',
  `login_pwd` VARCHAR(32) NULL DEFAULT NULL COMMENT '登陆密码',
  `login_pwd_strength` CHAR(1) NULL DEFAULT NULL COMMENT '登陆密码强度',
  `kind` VARCHAR(4) NULL DEFAULT NULL COMMENT '标识',
  `level` VARCHAR(4) NULL DEFAULT NULL COMMENT '用户等级',
  `user_referee` VARCHAR(32) NULL DEFAULT NULL COMMENT '推荐人',
  `mobile` VARCHAR(16) NULL DEFAULT NULL COMMENT '手机号',
  `id_kind` CHAR(1) NULL DEFAULT NULL COMMENT '证件类型',
  `id_no` VARCHAR(32) NULL DEFAULT NULL COMMENT '证件号码',
  `real_name` VARCHAR(16) NULL DEFAULT NULL COMMENT '真实姓名',
  `trade_pwd` VARCHAR(32) NULL DEFAULT NULL COMMENT '安全密码',
  `trade_pwd_strength` CHAR(1) NULL DEFAULT NULL COMMENT '安全密码强度',
  `role_code` VARCHAR(32) NULL DEFAULT NULL COMMENT '角色编号',
  `status` VARCHAR(2) NULL DEFAULT NULL COMMENT '状态',
  `updater` VARCHAR(32) NULL DEFAULT NULL COMMENT '修改人',
  `update_datetime` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
  `remark` VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
  `pdf` VARCHAR(255) NULL DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`user_id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8

-- ----------------------------
--  Table structure for `tstd_user_ext`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_user_ext`;
CREATE TABLE `tstd_user_ext` (
  `user_id` VARCHAR(32) NOT NULL COMMENT '用户编号',
  `gender` CHAR(1) NULL DEFAULT NULL COMMENT '性别(1 男 0 女)',
  `birthday` VARCHAR(16) NULL DEFAULT NULL COMMENT '生日',
  `photo` VARCHAR(255) NULL DEFAULT NULL COMMENT '头像',
  `region` VARCHAR(255) NULL DEFAULT NULL COMMENT '所在地区',
  `introduce` VARCHAR(255) NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`user_id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8

-- ----------------------------
--  Table structure for `tstd_user_relation`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_user_relation`;
CREATE TABLE `tstd_user_relation` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `to_user` varchar(32) DEFAULT NULL COMMENT '关系人编号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `update_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_menu`;
CREATE TABLE `tsys_menu` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `type` varchar(2) DEFAULT NULL COMMENT '类型',
  `url` varchar(64) DEFAULT NULL COMMENT '请求url',
  `order_no` varchar(8) DEFAULT NULL COMMENT '序号',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父亲节点',
  `kind` varchar(16) DEFAULT NULL COMMENT '六方',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_menu_role`;
CREATE TABLE `tsys_menu_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) DEFAULT NULL,
  `menu_code` varchar(32) DEFAULT NULL,
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_role`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_role`;
CREATE TABLE `tsys_role` (
  `code` varchar(32) NOT NULL COMMENT '角色编号',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `level` varchar(2) DEFAULT NULL,
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `kind` varchar(16) DEFAULT NULL COMMENT '六方',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;