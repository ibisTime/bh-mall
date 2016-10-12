/*
 Navicat MySQL Data Transfer

 Source Server         : 148
 Source Server Version : 50545
 Source Host           : 121.43.101.148
 Source Database       : cd_std_user

 Target Server Version : 50545
 File Encoding         : utf-8

 Date: 10/12/2016 09:23:57 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tstd_account_jour`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_account_jour`;
CREATE TABLE `tstd_account_jour` (
  `aj_no` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `biz_type` varchar(4) DEFAULT NULL COMMENT '业务类型',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '参考订单号',
  `trans_amount` bigint(32) DEFAULT NULL COMMENT '发送金额',
  `pre_amount` bigint(32) DEFAULT NULL COMMENT '发生前金额',
  `post_amount` bigint(32) DEFAULT NULL COMMENT '发生后金额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账户',
  `status` char(1) DEFAULT NULL COMMENT '状态',
  `work_date` varchar(8) DEFAULT NULL COMMENT '对账时间',
  `check_user` varchar(32) DEFAULT NULL COMMENT '对账人',
  `check_datetime` datetime DEFAULT NULL COMMENT '对账时间',
  PRIMARY KEY (`aj_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_b2c_sms`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_b2c_sms`;
CREATE TABLE `tstd_b2c_sms` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类别',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `to_level` varchar(4) DEFAULT NULL COMMENT 'to等级',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
--  Table structure for `tstd_cbanner`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_cbanner`;
CREATE TABLE `tstd_cbanner` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(64) DEFAULT NULL COMMENT '名字',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `location` varchar(4) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'UI顺序',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `update_datetime` date DEFAULT NULL COMMENT '最后修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_certificate`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_certificate`;
CREATE TABLE `tstd_certificate` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型(1 摄影/2 拍摄 3培训/ 4店铺代运营/5培训/6美工)\n',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` varchar(255) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_cintention`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_cintention`;
CREATE TABLE `tstd_cintention` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `from_company` varchar(64) NOT NULL COMMENT '合作公司',
  `from_person` varchar(64) NOT NULL COMMENT '合作联系人',
  `from_contact` varchar(64) NOT NULL COMMENT '联系方式',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `status` char(1) NOT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL,
  `company_code` varchar(32) NOT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_cmaterial`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_cmaterial`;
CREATE TABLE `tstd_cmaterial` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `kind` varchar(64) DEFAULT NULL COMMENT '种类',
  `type` varchar(64) DEFAULT NULL COMMENT '类别',
  `title` varchar(64) DEFAULT NULL COMMENT '内容标题',
  `pic1` varchar(128) DEFAULT NULL COMMENT '缩略图',
  `pic2` varchar(128) DEFAULT NULL COMMENT '大图',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `description` text COMMENT '详情描述',
  `end_note` varchar(255) DEFAULT NULL COMMENT '尾注',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `menu_code` varchar(32) DEFAULT NULL COMMENT '菜单编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_cmenu`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_cmenu`;
CREATE TABLE `tstd_cmenu` (
  `code` varchar(32) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `status` varchar(2) DEFAULT NULL COMMENT '显示状态',
  `location` varchar(4) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'UI次序',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父节点',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `content_type` varchar(32) DEFAULT NULL COMMENT '内容源类型',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_company`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_company`;
CREATE TABLE `tstd_company` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT 'type(1 公司 2个体户)',
  `name` varchar(64) DEFAULT NULL COMMENT '真实姓名/公司名称',
  `abbr_name` varchar(64) DEFAULT NULL COMMENT '公司简称',
  `gsyyzzh` varchar(255) DEFAULT NULL COMMENT '工商营业执照号/身份证正反照',
  `domain` varchar(255) DEFAULT NULL COMMENT '域名',
  `logo` varchar(255) DEFAULT NULL COMMENT 'logo',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `slogan` varchar(255) DEFAULT NULL COMMENT '广告语',
  `corporation` varchar(64) DEFAULT NULL COMMENT '法人',
  `qr_code` varchar(255) DEFAULT NULL COMMENT '二维码',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '具体地址',
  `longitude` varchar(64) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(64) DEFAULT NULL COMMENT '纬度',
  `copyright` varchar(255) DEFAULT NULL COMMENT '底部图片',
  `description` text COMMENT '描述',
  `scale` varchar(4) DEFAULT NULL COMMENT '规模',
  `contacts` varchar(64) DEFAULT NULL COMMENT '联系人姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '电话',
  `fax` varchar(32) DEFAULT NULL COMMENT '传真',
  `email` varchar(32) DEFAULT NULL COMMENT '联系人邮箱',
  `qq` varchar(32) DEFAULT NULL COMMENT 'qq号码',
  `we_chat` varchar(32) DEFAULT NULL COMMENT '微信',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `location` varchar(4) DEFAULT NULL COMMENT 'UI位置（0 禁用 1 正常）',
  `order_no` int(11) DEFAULT NULL COMMENT '序号',
  `is_default` varchar(4) DEFAULT NULL COMMENT '是否默认',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_company_certificate`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_company_certificate`;
CREATE TABLE `tstd_company_certificate` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `certificate_code` varchar(32) DEFAULT NULL COMMENT '资质编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '所属公司',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(0 待审核 1 审核通过 2审核不通过)',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_user` varchar(45) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_cpassword`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_cpassword`;
CREATE TABLE `tstd_cpassword` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类别',
  `account` varchar(64) DEFAULT NULL COMMENT '账号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_home`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_home`;
CREATE TABLE `tstd_home` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型（1 首页/2 人才首页/3 服务首页）',
  `pic1` varchar(255) DEFAULT NULL COMMENT '图1',
  `pic2` varchar(255) DEFAULT NULL COMMENT '图2',
  `pic3` varchar(255) DEFAULT NULL COMMENT '图3',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（1 发布中 0 不发布）',
  `order_no` int(11) DEFAULT NULL COMMENT '序号',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` varchar(45) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_level_rule`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_level_rule`;
CREATE TABLE `tstd_level_rule` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `level` varchar(4) DEFAULT NULL COMMENT '等级',
  `amount_min` bigint(20) DEFAULT NULL COMMENT '数量最小值',
  `amount_max` bigint(20) DEFAULT NULL COMMENT '数量最大值',
  `remark` varchar(255) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_sign_log`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_sign_log`;
CREATE TABLE `tstd_sign_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `location` varchar(255) DEFAULT NULL COMMENT '位置',
  `sign_datetime` datetime DEFAULT NULL COMMENT '签到时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_uread`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_uread`;
CREATE TABLE `tstd_uread` (
  `id` bigint(32) NOT NULL COMMENT 'id',
  `sms_code` varchar(32) DEFAULT NULL COMMENT '广播内容编号',
  `user_id` bigint(32) DEFAULT NULL COMMENT 'C端用户编号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `read_datetime` date DEFAULT NULL COMMENT '阅读时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_user`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_user`;
CREATE TABLE `tstd_user` (
  `user_id` varchar(32) NOT NULL COMMENT 'userId',
  `login_name` varchar(32) DEFAULT NULL COMMENT '登陆名',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `login_pwd` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登陆密码强度',
  `kind` varchar(4) DEFAULT NULL COMMENT '标识',
  `level` varchar(4) DEFAULT NULL COMMENT '用户等级',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `trade_pwd` varchar(32) DEFAULT NULL COMMENT '安全密码',
  `trade_pwd_strength` char(1) DEFAULT NULL COMMENT '安全密码强度',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `pdf` varchar(255) DEFAULT NULL COMMENT '附件',
  `amount` bigint(20) DEFAULT NULL COMMENT '现有积分',
  `lj_amount` bigint(20) DEFAULT NULL COMMENT '累计积分',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_user_ext`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_user_ext`;
CREATE TABLE `tstd_user_ext` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `gender` char(1) DEFAULT NULL COMMENT '性别(1 男 0 女)',
  `birthday` varchar(16) DEFAULT NULL COMMENT '生日',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `diploma` varchar(4) DEFAULT NULL COMMENT '学位',
  `occupation` varchar(64) DEFAULT NULL COMMENT '职业',
  `work_time` varchar(4) DEFAULT NULL COMMENT '工作时间',
  `province` varchar(64) DEFAULT NULL COMMENT '省',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `area` varchar(64) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `introduce` varchar(255) DEFAULT NULL COMMENT '简介',
  `region` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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