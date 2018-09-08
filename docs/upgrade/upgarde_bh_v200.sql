/****************tbh_account  *****************/
UPDATE tbh_account SET TYPE = 'P' WHERE account_number = 'CD-CBH000020';
UPDATE tbh_jour	 SET TYPE = 'P' WHERE account_number = 'CD-CBH000020';

INSERT INTO `tbh_account` (`account_number`, `user_id`, `real_name`, `type`, `status`, `currency`, `amount`, `frozen_amount`, `create_datetime`, `last_order`)
VALUES('CD-CBH000022','SYS_USER_BH','公司','P','0','TG_CNY','0','0',NOW(),NULL);

INSERT INTO `tbh_account` (`account_number`, `user_id`, `real_name`, `type`, `status`,currency,`amount`, `frozen_amount`,`create_datetime`);
 SELECT CONCAT('X',account_number) ,user_id ,real_name,TYPE,STATUS,'C_CNY','0','0',NOW()
FROM `tbh_account` WHERE TYPE = 'B';

/**********  tbh_address ********/
ALTER TABLE tbh_address DROP COLUMN TYPE,DROP COLUMN system_code,DROP COLUMN company_code;

/********** tbh_after_sale ******/
DROP TABLE tbh_after_sale;

/*********** tbh_agent_level **************/
DROP TABLE IF EXISTS `tbh_agent_level`;
CREATE TABLE `tbh_agent_level` (
  `code` VARCHAR(32) NOT NULL COMMENT '编号',
  `level` BIGINT(32) DEFAULT NULL COMMENT '等级',
  `name` VARCHAR(64) DEFAULT NULL COMMENT '等级名称',
  `red_amount` BIGINT(32) DEFAULT NULL COMMENT '红线金额',
  `amount` BIGINT(32) DEFAULT NULL COMMENT '授权单金额',
  `min_charge_amount` BIGINT(32) DEFAULT NULL COMMENT '本等级每次最低充值金额',
  `min_surplus` BIGINT(20) DEFAULT NULL COMMENT '本等级门槛最低余额',
  `is_send` CHAR(1) DEFAULT NULL COMMENT '本等级授权单是否可以自发',
  `is_ware` CHAR(1) DEFAULT NULL COMMENT '本等级是否启用云仓',
  `is_company_approve` CHAR(1) DEFAULT NULL COMMENT '本等级升级是否公司审核',
  `re_number` INT(11) DEFAULT NULL COMMENT '半门槛推荐人数',
  `is_reset` CHAR(1) DEFAULT NULL COMMENT '本等级升级是否余额清零',
  `is_intent` CHAR(1) DEFAULT NULL COMMENT '是否被意向（0否 1是）',
  `is_jsAward` CHAR(1) DEFAULT NULL COMMENT '是否可被介绍（0否 1是）',
  `is_real_name` CHAR(1) DEFAULT NULL COMMENT '是否实名（0否 1是）',
  `is_company_impower` CHAR(1) DEFAULT NULL COMMENT '是否需要公司审核（0否 1是）',
  `min_charge` BIGINT(20) DEFAULT NULL COMMENT '门槛款',
  `updater` VARCHAR(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `remark` VARCHAR(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO tbh_agent_level (CODE,`level`,`name`,`red_amount`,`amount`,`min_charge_amount`,`min_surplus`,
`is_send`,`is_ware`,`re_number`,`is_reset`,`is_company_approve`,`is_intent`,`is_jsAward`,`is_real_name`,
`is_company_impower`,`min_charge`,`updater`,`update_datetime`,`remark`
)SELECT 
 CONCAT('AL20180000000000000',a.level),a.level,a.name,a.red_amount,a.amount,a.min_charge_amount,a.min_surplus,
 a.is_send,a.is_wareHouse,u.re_number,u.is_reset,u.is_company_approve,i.is_intent,i.is_intro,i.is_real_name,
 i.is_company_impower,i.min_charge,i.updater,i.update_datetime,i.remark
FROM tbh_agent a JOIN tbh_agent_impower i ON a.level = i.level
JOIN tbh_agent_upgrade u ON  U.level = i.level
ORDER BY A.LEVEL;

UPDATE tbh_agent_level a JOIN tbh_user u ON u.login_name = a.updater
SET a.updater = u.user_id;

DROP TABLE IF EXISTS `tbh_agent`;
DROP TABLE IF EXISTS `tbh_agent_impower`;
DROP TABLE IF EXISTS `tbh_agent_upgrade`;

/*********** tbh_tj_award **************/
DROP TABLE IF EXISTS `tbh_tj_award`;
CREATE TABLE `tbh_tj_award` (
  `code` VARCHAR(32) NOT NULL,
  `product_code` VARCHAR(32) DEFAULT NULL COMMENT '产品编号',
  `level` VARCHAR(64) DEFAULT NULL COMMENT '等级',
  `value1` DECIMAL(10,3) DEFAULT '0.000' COMMENT '直接推荐/出货奖励',
  `value2` DECIMAL(10,3) DEFAULT '0.000' COMMENT '间接推荐奖励',
  `value3` DECIMAL(10,3) DEFAULT '0.000' COMMENT '次推荐奖励',
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT INTO `tbh_tj_award`
            (`code`,
             `product_code`,
             `level`,
             `value1`,
             `value2`,
             `value3`)
SELECT 	
	    `code`,
             `product_code`,
             `level`,
             `value1`,
             `value2`,
             `value3`
FROM tbh_award WHERE TYPE = '0';
  
DROP TABLE tbh_award;

 /*********** tbh_ch_award **************/

ALTER TABLE tbh_award_interval RENAME tbh_ch_award;

UPDATE tbh_ch_award a JOIN tbh_user u ON u.login_name = a.updater
SET a.updater = u.user_id;

/*********** tbh_js_award **************/
ALTER TABLE tbh_intro RENAME tbh_js_award;
UPDATE tbh_js_award a JOIN tbh_user u ON u.login_name = a.updater
SET a.updater = u.user_id;

/*********** tbh_bar_code **************/
ALTER TABLE tbh_bar_code RENAME tbh_pro_code;

/*********** tbh_security_trace **************/
ALTER TABLE  tbh_security_trace RENAME tbh_mini_code;
 
/*********** tbh_change_product **************/
ALTER TABLE tbh_change_product RENAME tbh_exchange_order;

ALTER TABLE tbh_exchange_order
CHANGE  product_specs_code specs_code VARCHAR(32) DEFAULT NULL,
CHANGE  product_specs_name specs_name VARCHAR(255) DEFAULT NULL;

/*********** tbh_charge **************/
ALTER TABLE tbh_charge
ADD high_user_id VARCHAR(32) DEFAULT NULL,
ADD team_name VARCHAR(255) DEFAULT NULL,
ADD `level` INT(11) DEFAULT NULL;

UPDATE tbh_charge c JOIN tbh_user u ON  c.`apply_user` = u.`user_id`
SET c.`high_user_id` = u.`high_user_id`, c.`team_name` = u.`team_name`, c.`level` = u.`level` WHERE c.`status`IN (1,4) ;

UPDATE tbh_charge c JOIN tbh_user u ON  c.`apply_user` = u.`user_id`
SET c.`high_user_id` = c.pay_user,  c.`level` = u.`level`,c.`team_name`=u.team_name WHERE c.`status` IN (5,6) ;

/*********** tbh_charge **************/
DROP TABLE tbh_company_channel;

/*********** tbh_inner_order **************/
ALTER TABLE tbh_inner_order
ADD specs_code VARCHAR(32) DEFAULT NULL,
ADD specs_name VARCHAR(255) DEFAULT NULL,
ADD approver VARCHAR(255) DEFAULT NULL,
ADD approve_datetime DATETIME,
ADD approve_note TEXT;

/*********** tbh_jour **************/
UPDATE tbh_jour j JOIN tbh_order o ON j.`ref_no` = o.`code` SET j.biz_type  = (
CASE o.kind WHEN 2 THEN 'AJ_TJJL_IN'
 ELSE 'AJ_TJJL_OUT' END
  )WHERE j.biz_type = 'AJ_TJJL';

UPDATE tbh_jour j JOIN tbh_order o ON j.`ref_no` = o.`code` SET j.biz_type  = (
CASE o.kind WHEN 2 THEN 'AJ_CHJL_IN'
 ELSE 'AJ_CHJL_OUT' END
  )WHERE j.biz_type = 'AJ_CHJL';

UPDATE tbh_order SET pay_group	= CODE;

/*********** tbh_in_order **************/
DROP TABLE IF EXISTS `tbh_in_order`;
CREATE TABLE `tbh_in_order` (
  `code` VARCHAR(32) NOT NULL,
  `product_code` VARCHAR(32) DEFAULT NULL COMMENT '是否云仓发货',
  `product_name` VARCHAR(255) DEFAULT NULL COMMENT '分类',
  `specs_code` VARCHAR(32) DEFAULT NULL COMMENT '产品编号',
  `specs_name` VARCHAR(255) DEFAULT NULL COMMENT '产品名称',
  `pic` VARBINARY(255) DEFAULT NULL COMMENT '规格编号',
  `quantity` INT(11) DEFAULT NULL COMMENT '规格名称',
  `price` BIGINT(20) DEFAULT NULL COMMENT '图片',
  `to_user_id` VARCHAR(32) DEFAULT NULL COMMENT '订单归属人',
  `to_user_name` VARCHAR(255) DEFAULT NULL,
  `level` INT(11) DEFAULT NULL,
  `amount` BIGINT(20) DEFAULT NULL COMMENT '总价',
  `status` VARCHAR(4) DEFAULT NULL COMMENT '状态',
  `apply_user` VARCHAR(32) DEFAULT NULL COMMENT '下单人',
  `real_name` VARCHAR(255) DEFAULT NULL COMMENT '下单人名称',
  `team_name` VARCHAR(255) DEFAULT NULL COMMENT '团队名称',
  `team_leader` VARCHAR(32) DEFAULT NULL COMMENT '团队长',
  `pay_type` VARCHAR(64) DEFAULT NULL COMMENT '支付渠道',
  `pay_group` VARCHAR(32) DEFAULT NULL COMMENT '支付组号',
  `pay_amount` BIGINT(20) DEFAULT NULL COMMENT '支付金额',
  `pay_datetime` DATETIME DEFAULT NULL COMMENT '支付时间',
  `pay_code` VARCHAR(32) DEFAULT NULL COMMENT '支付编号',
  `apply_datetime` DATETIME DEFAULT NULL COMMENT '下单时间',
  `apply_note` TEXT COMMENT '下单备注',
  `approver` VARCHAR(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` DATETIME DEFAULT NULL COMMENT '审核时间',
  `remark` TEXT COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO tbh_in_order(
`code`, `product_code`, `product_name`, `specs_code`, `specs_name`, `pic`, 
`quantity`, `price`, `to_user_id`, `to_user_name`, `level`, `amount`, `status`, `apply_user`, 
`real_name`, `team_name`, `team_leader`, `pay_type`, `pay_group`, `pay_amount`, `pay_datetime`, `pay_code`, `apply_datetime`, 
`apply_note`, `approver`, `approve_datetime`, `remark` 
)SELECT 
`code`, `product_code`, `product_name`, `product_specs_code`, `product_specs_name`, `pic`, 
`quantity`, `price`, `to_user`, `to_user_name`, `level`, `amount`, `status`, `apply_user`, 
`real_name`, `team_name`, `team_leader`, `pay_type`, `code`, `pay_amount`, `pay_datetime`, `pay_code`, `apply_datetime`, 
`apply_note`, `approver`, `approve_datetime`, `remark` 
 FROM tbh_order WHERE kind = '2';

/*********** tbh_out_order **************/
DROP TABLE IF EXISTS `tbh_out_order`;
CREATE TABLE `tbh_out_order` (
  `code` VARCHAR(32) NOT NULL,
  `level` INT(32) DEFAULT NULL COMMENT '等级',
  `is_ware_send` VARCHAR(32) DEFAULT NULL COMMENT '是否云仓发货',
  `kind` VARCHAR(4) DEFAULT NULL COMMENT '分类',
  `product_code` VARCHAR(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` VARCHAR(255) DEFAULT NULL COMMENT '产品名称',
  `specs_code` VARCHAR(32) DEFAULT NULL COMMENT '规格编号',
  `specs_name` VARCHAR(255) DEFAULT NULL COMMENT '规格名称',
  `pro_code` VARCHAR(32) DEFAULT NULL COMMENT '关联箱码',
  `pic` VARBINARY(255) DEFAULT NULL COMMENT '图片',
  `quantity` INT(11) DEFAULT NULL COMMENT '数量',
  `price` BIGINT(20) DEFAULT NULL COMMENT '单价',
  `to_user_id` VARCHAR(32) DEFAULT NULL COMMENT '向谁提货',
  `to_user_name` VARCHAR(255) DEFAULT NULL COMMENT '订单归属人姓名',
  `amount` BIGINT(20) DEFAULT NULL COMMENT '总价',
  `yunfei` BIGINT(20) DEFAULT NULL COMMENT '运费',
  `status` VARCHAR(4) DEFAULT NULL COMMENT '状态',
  `pay_type` VARCHAR(64) DEFAULT NULL COMMENT '支付渠道',
  `pay_group` VARCHAR(32) DEFAULT NULL COMMENT '支付组号',
  `pay_amount` BIGINT(20) DEFAULT NULL COMMENT '支付金额',
  `pay_datetime` DATETIME DEFAULT NULL COMMENT '支付时间',
  `pay_code` VARCHAR(32) DEFAULT NULL COMMENT '渠道编号',
  `apply_user` VARCHAR(32) DEFAULT NULL COMMENT '下单人',
  `high_user_id` VARCHAR(32) DEFAULT NULL COMMENT '上级',
  `real_name` VARCHAR(255) DEFAULT NULL COMMENT '下单人姓名',
  `team_name` VARCHAR(255) DEFAULT NULL COMMENT '团队名称',
  `team_leader` VARCHAR(255) DEFAULT NULL COMMENT '团队长',
  `apply_datetime` DATETIME DEFAULT NULL COMMENT '下单时间',
  `apply_note` TEXT COMMENT '下单备注',
  `signer` VARCHAR(32) DEFAULT NULL COMMENT '收件人姓名',
  `mobile` VARCHAR(32) DEFAULT NULL COMMENT '收件人电话',
  `province` VARCHAR(32) DEFAULT NULL COMMENT '省',
  `city` VARCHAR(32) DEFAULT NULL COMMENT '市',
  `area` VARCHAR(32) DEFAULT NULL COMMENT '区',
  `address` VARCHAR(255) DEFAULT NULL COMMENT '收货地址',
  `deliver` VARCHAR(32) DEFAULT NULL COMMENT '发货人',
  `delive_datetime` DATETIME DEFAULT NULL COMMENT '发货时间',
  `logistics_code` VARCHAR(255) DEFAULT NULL COMMENT '物流单号',
  `logistics_company` VARCHAR(255) DEFAULT NULL COMMENT '物流公司',
  `updater` VARCHAR(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `update_note` VARCHAR(255) DEFAULT NULL COMMENT '更新备注',
  `approver` VARCHAR(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` DATETIME DEFAULT NULL COMMENT '审核时间',
  `approve_note` VARCHAR(255) DEFAULT NULL COMMENT '审核备注',
  `remark` TEXT COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `tbh_out_order` (
`code`, `level`, `is_ware_send`, `kind`, `product_code`, `product_name`, 
`specs_code`, `specs_name`, `pro_code`, `pic`, `quantity`, `price`, 
`to_user_id`, `to_user_name`, `amount`, `yunfei`, `status`, `pay_type`,
`pay_group`, `pay_amount`, `pay_datetime`, `pay_code`, `apply_user`, `high_user_id`, `real_name`, `team_name`, 
`team_leader`, `apply_datetime`, `apply_note`, `signer`, `mobile`, `province`, `city`, `area`, 
`address`, `deliver`, `delive_datetime`, `logistics_code`, `logistics_company`, 
`updater`, `update_datetime`, `update_note`, `approver`, `approve_datetime`, `approve_note`, `remark`) 
SELECT 
`code`, `level`, `is_company_send`, `kind`, `product_code`,`product_name`, 
`product_specs_code`, `product_specs_name`, `bar_code`, `pic`, `quantity`, `price`, 
`to_user`, `to_user_name`, `amount`, `yunfei`, `status`, `pay_type`,
`code`, `pay_amount`, `pay_datetime`, `pay_code`, `apply_user`, `to_user`, `real_name`, `team_name`, 
`team_leader`, `apply_datetime`, `apply_note`, `signer`, `mobile`, `province`, `city`, `area`, 
`address`, `deliver`, `delive_datetime`, logistics_code, `logistics_company`, 
`updater`, `update_datetime`, `update_note`, `approver`, `approve_datetime`, `approve_note`, `remark`
 FROM tbh_order WHERE kind != '2';

UPDATE tbh_out_order SET is_ware_send = '1' WHERE specs_code = 'PS201806301939383841880'; 

UPDATE tbh_out_order SET is_ware_send = '0' WHERE specs_code = 'PS201806291944580145174'; 

UPDATE tbh_out_order o JOIN tbh_user u ON o.`signer`=u.`user_id`
SET o.signer = u.`real_name`;

/*********** tbh_specs_log **************/
ALTER TABLE tbh_product_log RENAME tbh_specs_log;

ALTER TABLE tbh_specs_log 
ADD product_name VARCHAR(255) DEFAULT NULL  COMMENT '产品名称',
ADD specs_code VARCHAR(32) DEFAULT NULL  COMMENT '规格编号',
ADD specs_name VARCHAR(255) DEFAULT NULL  COMMENT '规格名称',
ADD ref_code VARCHAR(32) DEFAULT NULL  COMMENT '关联订单';

UPDATE tbh_specs_log l JOIN tbh_product p ON l.`product_code`= p.`code` SET l.`product_name` = p.`name`;

ALTER TABLE `tbh_specs_log` 
CHANGE COLUMN `product_name` `product_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '产品名称' AFTER `product_code`,
CHANGE COLUMN `specs_code` `specs_code` VARCHAR(32) NULL DEFAULT NULL COMMENT '规格编号' AFTER `product_name`,
CHANGE COLUMN `specs_name` `specs_name` VARCHAR(255) NULL DEFAULT NULL COMMENT '规格名称' AFTER `specs_code`,
CHANGE COLUMN `ref_code` `ref_code` VARCHAR(32) NULL DEFAULT NULL COMMENT '关联订单' AFTER `post_count`;

/*********** tbh_product_specs **************/
ALTER TABLE tbh_product_specs RENAME tbh_specs;
ALTER TABLE tbh_specs 
CHANGE is_impower_order is_sq_order CHAR(1) DEFAULT NULL,
CHANGE is_upgrade_order is_sj_order CHAR(1) DEFAULT NULL;
ALTER TABLE tbh_specs 
ADD stock_number INT(11) DEFAULT 0 COMMENT '规格库存数量';

ALTER TABLE `tbh_specs` 
CHANGE COLUMN `stock_number` `stock_number` INT(11) NULL DEFAULT '0' COMMENT '规格库存数量' AFTER `number`;

/*********** tbh_product_specs_price **************/
ALTER TABLE tbh_product_specs_price RENAME tbh_agent_price;

ALTER TABLE tbh_agent_price
CHANGE product_specs_code specs_code VARCHAR(32) DEFAULT NULL,
CHANGE min_quantity start_number INT(11) DEFAULT 0;

/*********** tbh_report **************/
ALTER TABLE tbh_report RENAME tbh_agent_report;

ALTER TABLE tbh_agent_report CHANGE price_spread profit_award INT(11) DEFAULT 0;
 
ALTER TABLE tbh_agent_report ADD send_amount INT(11) DEFAULT 0 COMMENT '出货总金额';

INSERT INTO `tbh_agent_report`
            (`user_id`,
             `real_name`,
             `mobile`,
             `wxId`,
             `level`,
             `user_referee`,
             `introducer`,
             `high_user_id`,
             `team_name`,
             `manager`,
             `province`,
             `city`,
             `area`,
             `address`,
             `impower_datetime`,
             `send_award`,
             `intr_award`,
             `refree_award`,
             `profit_award`)
SELECT 
	     `user_id`,
             `real_name`,
             `mobile`,
             `wx_id`,
             `level`,
             `user_referee`,
             `introducer`,
             `high_user_id`,
             `team_name`,
             `manager`,
             `province`,
             `city`,
             `area`,
             `address`,
             `impower_datetime`,
             '0',
             '0',
             '0',
             '0'
 FROM tbh_user WHERE STATUS IN (7,11,15,12,13,14);
 
/*********** tbh_user **************/
DROP TABLE IF EXISTS `tsys_user`;
CREATE TABLE `tsys_user` (
  `user_id` VARCHAR(32) NOT NULL COMMENT '用户ID',
  `role_code` VARCHAR(32) DEFAULT NULL COMMENT '角色编号',
  `real_name` VARCHAR(64) DEFAULT NULL COMMENT '真实姓名',
  `photo` VARCHAR(255) DEFAULT NULL COMMENT '头像',
  `mobile` VARCHAR(16) DEFAULT NULL COMMENT '手机号',
  `login_name` VARCHAR(64) DEFAULT NULL COMMENT '登录名',
  `login_pwd` VARBINARY(32) DEFAULT NULL COMMENT '登录密码',
  `login_pwd_strength` CHAR(1) DEFAULT NULL COMMENT '登录密码强度',
  `create_datetime` DATETIME DEFAULT NULL COMMENT '注册时间',
  `status` VARCHAR(4) DEFAULT NULL COMMENT '状态',
  `updater` VARCHAR(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `remark` TEXT COMMENT '备注',
  `system_code` VARCHAR(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `tsys_user` (
`user_id`, `role_code`, `real_name`, `photo`, `mobile`, 
`login_name`, `login_pwd`, `login_pwd_strength`, `create_datetime`, 
`status`, `updater`, `update_datetime`, `remark`, `system_code`) 
SELECT 
`user_id`, `role_code`, `real_name`, `photo`, `mobile`, 
`login_name`, `login_pwd`, `login_pwd_strength`, `create_datetime`, 
`status`, `updater`, `update_datetime`, `remark`, `system_code`
FROM tbh_user WHERE kind = 'P';

UPDATE tsys_user s   JOIN tbh_user u ON s.updater = u.login_name
SET s.updater = u.user_id;

DROP TABLE IF EXISTS `tbh_agent`;
CREATE TABLE `tbh_agent` (
  `user_id` VARCHAR(32) NOT NULL COMMENT '用户编号',
  `from_user_id` VARCHAR(32) DEFAULT NULL COMMENT '分享二位码代理',
  `mobile` VARCHAR(16) DEFAULT NULL COMMENT '手机号',
  `wx_id` VARCHAR(32) DEFAULT NULL COMMENT '微信号',
  `photo` VARCHAR(255) DEFAULT NULL COMMENT '头像',
  `nickname` VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `trade_pwd` VARCHAR(32) DEFAULT NULL COMMENT '交易密码',
  `trade_pwd_strength` VARCHAR(1) DEFAULT NULL,
  `level` INT(32) DEFAULT NULL COMMENT '用户等级',
  `referrer` VARCHAR(32) DEFAULT NULL COMMENT '推荐人',
  `introducer` VARCHAR(32) DEFAULT NULL COMMENT '介绍人',
  `high_user_id` VARCHAR(32) DEFAULT NULL COMMENT '上级用户',
  `team_name` VARCHAR(32) DEFAULT NULL COMMENT '团队名称',
  `id_kind` CHAR(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` VARCHAR(32) DEFAULT NULL COMMENT '证件号码',
  `id_hand` TEXT COMMENT '手持身份证',
  `real_name` VARCHAR(32) DEFAULT NULL COMMENT '真实姓名',
  `status` VARCHAR(4) DEFAULT NULL COMMENT '状态',
  `manager` VARCHAR(32) DEFAULT NULL COMMENT '关联管理员',
  `union_id` VARCHAR(255) DEFAULT NULL COMMENT '联合编号',
  `h5_open_id` VARCHAR(255) DEFAULT NULL COMMENT '公众号开放编号',
  `app_open_id` VARCHAR(255) DEFAULT NULL COMMENT 'app开放编号',
  `address` VARCHAR(255) DEFAULT NULL COMMENT '详细地址',
  `province` VARCHAR(255) DEFAULT NULL COMMENT '省',
  `city` VARCHAR(255) DEFAULT NULL COMMENT '市',
  `area` VARCHAR(255) DEFAULT NULL COMMENT '区',
  `create_datetime` DATETIME DEFAULT NULL COMMENT '注册时间',
  `updater` VARCHAR(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` DATETIME DEFAULT NULL COMMENT '修改时间',
  `approver` VARCHAR(32) DEFAULT NULL COMMENT '审核人',
  `approve_name` VARCHAR(32) DEFAULT NULL,
  `approve_datetime` DATETIME DEFAULT NULL COMMENT '审核时间',
  `impower_datetime` DATETIME DEFAULT NULL COMMENT '最后审核时间',
  `last_agent_log` VARCHAR(32) DEFAULT NULL COMMENT '最后一条代理轨迹记录',
  `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `tbh_agent` (
`user_id`, `from_user_id`, `mobile`, `wx_id`, `photo`, `nickname`, `trade_pwd`, `trade_pwd_strength`, 
`level`, `referrer`, `introducer`, `high_user_id`, `team_name`, `id_kind`, `id_no`, `id_hand`, `real_name`, 
`status`, `manager`, `union_id`, `h5_open_id`, `app_open_id`, `address`, `province`, `city`, `area`,
`create_datetime`, `updater`, `update_datetime`, `approver`,  `approve_datetime`, `impower_datetime`, `last_agent_log`, `remark`)
SELECT 
`user_id`, `user_referee`, `mobile`, `wx_id`, `photo`, `nickname`, `trade_pwd`, `trade_pwd_strength`,
`level`, `user_referee`, `introducer`, `high_user_id`, `team_name`, `id_kind`, `id_no`, `id_hand`, `real_name`, 
`status`, `manager`, `union_id`, `h5_open_id`, `app_open_id`, `address`, `province`, `city`, `area`, 
`create_datetime`, `updater`, `update_datetime`, `approver`, `approve_datetime`, `impower_datetime`, `last_agent_log`, `remark`
FROM tbh_user WHERE kind = 'B'; 

UPDATE tbh_agent a JOIN tbh_user u ON a.updater = u.login_name
SET a.updater = u.user_id;

UPDATE tbh_agent a JOIN tbh_user u ON a.approver = u.login_name
SET a.approver = u.user_id,a.approve_name = u.real_name;

  
DROP TABLE IF EXISTS `tbh_cuser`;
CREATE TABLE `tbh_cuser` (
  `user_id` VARCHAR(32) NOT NULL COMMENT '用户编号',
  `wx_id` VARCHAR(32) DEFAULT NULL COMMENT '微信号',
  `union_id` VARCHAR(255) DEFAULT NULL COMMENT '联合编号',
  `h5_open_id` VARCHAR(255) DEFAULT NULL COMMENT '公众号开放编号',
  `app_open_id` VARCHAR(255) DEFAULT NULL COMMENT 'app开放编号',
  `photo` VARCHAR(255) DEFAULT NULL COMMENT '头像',
  `nickname` VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `create_datetime` DATETIME DEFAULT NULL COMMENT '注册时间',
  `status` VARCHAR(4) DEFAULT NULL COMMENT '状态',
  `updater` VARCHAR(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `tbh_cuser`
            (`user_id`,
             `wx_id`,
             `union_id`,
             `h5_open_id`,
             `app_open_id`,
             `photo`,
             `nickname`,
             `create_datetime`,
             `status`,
             `updater`)
SELECT  	
	     	 `user_id`,
             `wx_id`,
             `union_id`,
             `h5_open_id`,
             `app_open_id`,
             `photo`,
             `nickname`,
             `create_datetime`,
             `status`,
             `updater`
FROM tbh_user WHERE kind = 'C';

/*********** tbh_agent_log **************/
DROP TABLE IF EXISTS `tbh_agent_log`;
CREATE TABLE `tbh_agent_log` (
  `code` VARCHAR(32) NOT NULL COMMENT '编号',
  `type` VARCHAR(1) DEFAULT NULL COMMENT '类型',
  `apply_user` VARCHAR(32) DEFAULT NULL COMMENT '申请人',
  `real_name` VARCHAR(255) DEFAULT NULL COMMENT '姓名',
  `wx_id` VARCHAR(255) DEFAULT NULL COMMENT '微信号',
  `mobile` VARCHAR(16) DEFAULT NULL COMMENT '手机号',
  `to_user_id` VARCHAR(32) DEFAULT NULL COMMENT '意向归属人',
  `level` INT(11) DEFAULT NULL COMMENT '当前等级',
  `apply_level` INT(11) DEFAULT NULL COMMENT '申请等级',
  `high_user_id` VARCHAR(32) DEFAULT NULL COMMENT '上级',
  `referrer` VARCHAR(32) DEFAULT NULL COMMENT '推荐人',
  `introducer` VARCHAR(32) DEFAULT NULL COMMENT '介绍人',
  `team_name` VARCHAR(255) DEFAULT NULL COMMENT '团队名称',
  `pay_amount` BIGINT(20) DEFAULT NULL COMMENT '打款金额',
  `pay_pdf` VARCHAR(255) DEFAULT NULL COMMENT '打款截图',
  `province` VARCHAR(255) DEFAULT NULL COMMENT '省',
  `city` VARCHAR(255) DEFAULT NULL COMMENT '市',
  `area` VARCHAR(255) DEFAULT NULL COMMENT '区',
  `address` TEXT COMMENT '详细地址',
  `status` VARCHAR(4) DEFAULT NULL COMMENT '状态',
  `apply_datetime` DATETIME DEFAULT NULL COMMENT '申请时间',
  `approver` VARCHAR(32) DEFAULT NULL COMMENT '审核人',
  `approve_name` VARCHAR(255) DEFAULT NULL COMMENT '审核人姓名',
  `approve_datetime` DATETIME DEFAULT NULL COMMENT '审核时间',
  `approve_note` TEXT COMMENT '审核备注',
  `updater` VARCHAR(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `impower_datetime` DATETIME DEFAULT NULL COMMENT '授权时间',
  `remark` TEXT COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `tbh_agent_log`
            (`code`,
             `type`,
             `apply_user`,
             `real_name`,
             `wx_id`,
             `mobile`,
             `to_user_id`,
             `level`,
             `apply_level`,
             `high_user_id`,
             `referrer`,
             `introducer`,
             `team_name`,
             `pay_amount`,
             `pay_pdf`,
             `province`,
             `city`,
             `area`,
             `address`,
             `status`,
             `apply_datetime`,
             `approver`,
             `approve_datetime`,
             `approve_note`,
             `updater`,
             `update_datetime`,
             `impower_datetime`,
             `remark`)
SELECT l.`code`,
             l.`type`,
             l.`apply_user`,
             u.`real_name`,
              u.`wx_id`,
              u.`mobile`,
              l.`to_user_id`,
             l.`level`,
             l.`apply_level`,
             l.`high_user_id`,
              u.`user_referee`,
              u.`introducer`,
             l.`team_name`,
             u.`pay_amount`,
             l.`pay_pdf`,
              u.`province`,
              u.`city`,
              u.`area`,
              u.`address`,
             
             CASE l.status
             WHEN 16  THEN 0
             WHEN 3  THEN 0
             WHEN 4  THEN 1
             WHEN 5  THEN 3
             WHEN 7  THEN 8
             
             WHEN 8  THEN 10
             WHEN 10  THEN 9
             WHEN 11  THEN 7
             WHEN 15 THEN 11
             
             WHEN 13 THEN 14
             WHEN 14 THEN 13
             WHEN 18 THEN 5
             WHEN 19 THEN 4
             ELSE l.status END ,
             l.`apply_datetime`,
             l.`approver`,
 
             l.`approve_datetime`,
            l.`remark`,
              u.`updater`,
              u.`update_datetime`,
             u.`impower_datetime`,
             l.`remark`
FROM tbh_agency_log l JOIN tbh_user u ON l.apply_user = u.user_id;
 
UPDATE tbh_agent_log l, tbh_user u  SET l.approve_name = u.real_name WHERE u.user_id = l.approver AND u.approver LIKE 'U%'; 
 
DROP TABLE tbh_agency_log;
 
/*********** tbh_yx_from **************/
DROP TABLE IF EXISTS `tbh_yx_form`;
CREATE TABLE `tbh_yx_form` (
  `user_id` VARCHAR(32) CHARACTER SET utf8mb4 NOT NULL COMMENT '申请人',
  `wx_id` VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '微信号',
  `to_user_id` VARCHAR(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '意向归属人',
  `apply_level` VARCHAR(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '申请等级',
  `real_name` VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '姓名',
  `mobile` VARCHAR(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '手机号',
  `address` VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '地址',
  `area` VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '区',
  `city` VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '市',
  `province` VARCHAR(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '省',
  `from_info` VARCHAR(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '信息来源',
  `status` VARCHAR(4) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '状态',
  `apply_datetime` DATETIME DEFAULT NULL COMMENT '申请时间',
  `approver` VARCHAR(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '审核人',
  `approve_name` VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '审核人名称',
  `approve_datetime` DATETIME DEFAULT NULL COMMENT '审核时间',
  `remark` TEXT CHARACTER SET utf8mb4 COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `tbh_yx_form`
            (`user_id`,
             `wx_id`,
             `to_user_id`,
             `apply_level`,
             `real_name`,
             `mobile`,
             `address`,
             `area`,
             `city`,
             `province`,
             `status`,
             `apply_datetime`,
             `approver`,
             `approve_datetime`,
             `remark`)
SELECT 
	     u.`user_id`,
             u.`wx_id`,
             l.`to_user_id`,
             l.`apply_level`,
             u.`real_name`,
             u.`mobile`,
             u.`address`,
             u.`area`,
             u.`city`,
             u.`province`,
             u.`status`,
             l.`apply_datetime`,
             l.`approver`,
             l.`approve_datetime`,
             l.`remark`
FROM tbh_agent u  JOIN 
(SELECT * FROM (SELECT l.* FROM  tbh_agent_log l  
WHERE l.status IN (0,1,3)
ORDER BY CODE DESC) l
GROUP BY l.apply_user) l ON u.user_id = l.apply_user;

UPDATE tbh_yx_form y, tbh_user u  SET y.approve_name = u.real_name WHERE u.user_id = y.approver AND u.approver LIKE 'U%'; 
 
UPDATE tbh_yx_form SET from_info = 0 WHERE from_info IS NULL;

/******************* tbh_sq_from *****************/
DROP TABLE IF EXISTS `tbh_sq_form`;
CREATE TABLE `tbh_sq_form` (
  `user_id` VARCHAR(32) DEFAULT NULL COMMENT '用户编号',
  `wx_id` VARCHAR(255) DEFAULT NULL COMMENT '微信号',
  `apply_level` VARCHAR(32) DEFAULT NULL COMMENT '申请等级',
  `team_name` VARCHAR(255) DEFAULT NULL COMMENT '团队名称',
  `real_name` VARCHAR(255) DEFAULT NULL COMMENT '真实姓名',
  `to_user_id` VARCHAR(32) DEFAULT NULL COMMENT '授权归属人',
  `address` VARCHAR(255) DEFAULT NULL COMMENT '地址',
  `area` VARCHAR(64) DEFAULT NULL COMMENT '区',
  `mobile` VARCHAR(32) DEFAULT NULL COMMENT '手机号',
  `city` VARCHAR(64) DEFAULT NULL COMMENT '市',
  `province` VARCHAR(64) DEFAULT NULL COMMENT '省',
  `id_hand` TEXT COMMENT '手持身份证',
  `id_kind` CHAR(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` VARCHAR(32) DEFAULT NULL COMMENT '身份证号',
  `introducer` VARCHAR(32) DEFAULT NULL COMMENT '介绍人',
  `referrer` VARCHAR(32) DEFAULT NULL COMMENT '推荐人',
  `status` VARCHAR(4) DEFAULT NULL COMMENT '状态',
  `apply_datetime` DATETIME DEFAULT NULL COMMENT '申请时间',
  `approver` VARCHAR(32) DEFAULT NULL COMMENT '审核人',
  `approve_name` VARCHAR(255) DEFAULT NULL COMMENT '审核人姓名',
  `approve_datetime` DATETIME DEFAULT NULL COMMENT '审核时间',
  `impower_datetime` DATETIME DEFAULT NULL COMMENT '授权时间',
  `remark` TEXT COMMENT '备注'
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `tbh_sq_form`
            (`user_id`,
             `wx_id`,
             `apply_level`,
             `team_name`,
             `real_name`,
             `to_user_id`,
             `address`,
             `area`,
             `mobile`,
             `city`,
             `province`,
             `id_hand`,
             `id_kind`,
             `id_no`,
             `introducer`,
             `referrer`,
             `status`,
             `apply_datetime`,
             `approver`,
             `approve_name`,
             `approve_datetime`,
             `impower_datetime`,
             `remark`)
SELECT 		
	        u.`user_id`,
             u.`wx_id`,
             l.`apply_level`,
             u.`team_name`,
             u.`real_name`,
             l.`to_user_id`,
             u.`address`,
             u.`area`,
             u.`mobile`,
             u.`city`,
             u.`province`,
             u.`id_hand`,
             u.`id_kind`,
             u.`id_no`,
             u.`introducer`,
             u.`referrer`,
             u.`status`,
             l.`apply_datetime`,
             u.`approver`,
             u.`approve_name`,
             u.`approve_datetime`,
             u.`impower_datetime`,
             u.`remark`
   FROM tbh_agent u JOIN 
(SELECT * FROM (SELECT l.* FROM  tbh_agent_log l  
WHERE l.status IN (6,7,8,9,10,11)
ORDER BY CODE DESC) l
GROUP BY l.apply_user) l ON u.user_id = l.apply_user;
 
UPDATE tbh_sq_form s, tbh_user u  SET s.approve_name = u.real_name WHERE u.user_id = s.approver AND u.approver LIKE 'U%'; 
 

/******************* tbh_sj_from *****************/
DROP TABLE IF EXISTS `tbh_sj_form`;
CREATE TABLE `tbh_sj_form` (
  `user_id` VARCHAR(32) CHARACTER SET utf8 NOT NULL COMMENT '用户编号',
  `real_name` VARCHAR(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '真实姓名',
  `id_kind` CHAR(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '证件类型',
  `id_no` VARCHAR(18) CHARACTER SET utf8 DEFAULT NULL COMMENT '证件号',
  `id_hand` VARCHAR(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '手持身份证',
  `team_name` VARCHAR(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '团队名称',
  `to_user_id` VARCHAR(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '归属人',
  `level` INT(11) DEFAULT NULL COMMENT '当前等级',
  `apply_level` VARCHAR(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '申请等级',
  `re_number` INT(11) DEFAULT '0' COMMENT '半门槛推荐人数',
  `pay_amount` BIGINT(20) DEFAULT '0' COMMENT '打款金额',
  `pay_pdf` VARCHAR(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '打款截图',
  `approver` VARCHAR(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '审核人',
  `approve_name` VARCHAR(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '审核人名称',
  `apply_datetime` DATETIME DEFAULT NULL COMMENT '申请时间',
  `approve_datetime` DATETIME DEFAULT NULL COMMENT '审核时间',
  `status` VARCHAR(4) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态',
  `remark` TEXT CHARACTER SET utf8 COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO tbh_sj_form
            (`user_id`,
             `real_name`,
             `id_kind`,
             `id_no`,
             `id_hand`,
             `team_name`,
             `to_user_id`,
             `level`,
             `apply_level`,
             `pay_amount`,
             `pay_pdf`,
             `approver`,

             `apply_datetime`,
             `approve_datetime`,
             `status`,
             `remark`)
SELECT 
	     u.`user_id`,
             u.`real_name`,
             u.`id_kind`,
             u.`id_no`,
             u.`id_hand`,
             l.`team_name`,
             l.`to_user_id`,
             l.`level`,
             l.`apply_level`,
        
	     l.`pay_amount`,
             l.`pay_pdf`,
             l.`approver`,
             l.`apply_datetime`,
             l.`approve_datetime`,
             l.`status`,
             l.`remark`
FROM tbh_agent u  JOIN 
(SELECT * FROM (SELECT l.* FROM  tbh_agent_log l  
WHERE l.status IN (12,13,14)
ORDER BY CODE DESC) l
GROUP BY l.apply_user) l ON u.user_id = l.apply_user;

UPDATE tbh_sj_form s, tbh_user u  SET s.approve_name = u.real_name WHERE u.user_id = s.approver AND u.approver LIKE 'U%'; 
 
/*********** tbh_ware_house **************/
ALTER TABLE tbh_ware_house RENAME tbh_ware;

ALTER TABLE tbh_ware
CHANGE product_specs_code specs_code VARCHAR(32) DEFAULT NULL,
CHANGE product_specs_name specs_name VARCHAR(255) DEFAULT NULL;

/*********** tbh_ware_house_log **************/
ALTER TABLE tbh_ware_house_log RENAME tbh_ware_log;

ALTER TABLE tbh_ware_log
CHANGE ware_house_code ware_code VARCHAR(32) DEFAULT NULL,
CHANGE product_specs_code specs_code  VARCHAR(32) DEFAULT NULL,
CHANGE product_specs_name specs_name VARCHAR(255) DEFAULT NULL;

/*********** thf_ware_house_specs **************/
DROP TABLE thf_ware_house_specs;

/*********** tbh_withdraw **************/
ALTER TABLE   tbh_withdraw 
ADD high_user_id VARCHAR(32) DEFAULT NULL COMMENT '上级编号',
ADD is_company_pay VARCHAR(32) DEFAULT NULL COMMENT '是否公司支付';



