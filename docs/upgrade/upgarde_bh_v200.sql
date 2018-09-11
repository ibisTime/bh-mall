/****************tbh_account  *****************/
UPDATE tbh_account SET TYPE = 'P' WHERE account_number = 'CD-CBH000020';
UPDATE tbh_jour	 SET TYPE = 'P' WHERE account_number = 'CD-CBH000020';

INSERT INTO `tbh_account` 
(`account_number`, `user_id`, `real_name`, `type`, `status`, `currency`, `amount`, `frozen_amount`, `create_datetime`, `last_order`)
VALUES
('CD-CBH000022','SYS_USER_BH','公司','P','0','TG_CNY','0','0',NOW(),NULL);

INSERT INTO `tbh_account` (
`account_number`, `user_id`, `real_name`, `type`, `status`,currency,`amount`, `frozen_amount`,`create_datetime`)
SELECT 
CONCAT('X',account_number) ,user_id ,real_name,TYPE,STATUS,'C_CNY','0','0',NOW()
FROM `tbh_account` WHERE TYPE = 'B' GROUP BY user_id ;

ALTER TABLE tbh_account ADD LEVEL INT(11)  DEFAULT NULL COMMENT '代理等级';

UPDATE tbh_account a JOIN tbh_agent u SET a.`level`=u.`level`;

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

ALTER TABLE tbh_mini_code 
CHANGE security_code mini_code VARCHAR(13) DEFAULT NULL;
 
/*********** tbh_change_product **************/
ALTER TABLE tbh_change_product RENAME tbh_exchange_order;

ALTER TABLE tbh_exchange_order
CHANGE  product_specs_code specs_code VARCHAR(32) DEFAULT NULL,
CHANGE  product_specs_name specs_name VARCHAR(255) DEFAULT NULL;

/*********** tbh_channel_bank **************/
ALTER TABLE tbh_channel_bank 
ADD updater VARCHAR(32) DEFAULT NULL COMMENT '更新人',
ADD update_datetime DATETIME COMMENT '更新时间';

UPDATE tbh_channel_bank SET updater = 'USYS201800000000002', update_datetime = NOW();

/*********** tbh_charge **************/
ALTER TABLE tbh_charge
ADD high_user_id VARCHAR(32) DEFAULT NULL,
ADD team_name VARCHAR(255) DEFAULT NULL,
ADD `level` INT(11) DEFAULT NULL;

UPDATE tbh_charge c JOIN tbh_user u ON  c.`apply_user` = u.`user_id`
SET c.`high_user_id` = u.`high_user_id`, c.`team_name` = u.`team_name`, c.`level` = u.`level` WHERE c.`status`IN (1,4) ;

UPDATE tbh_charge c JOIN tbh_user u ON  c.`apply_user` = u.`user_id`
SET c.`high_user_id` = c.pay_user,  c.`level` = u.`level`,c.`team_name`=u.team_name WHERE c.`status` IN (5,6) ;

UPDATE tbh_charge SET high_user_id  = 'USYS201800000000002' WHERE LEVEL = 1;

UPDATE tbh_charge SET high_user_id = 'USYS201800000000002' , LEVEL = '1',team_name = '小义团队' WHERE apply_user = 'U201807101359562302076';

/*********** tbh_charge **************/
DROP TABLE tbh_company_channel;

/*********** tbh_inner_order **************/
ALTER TABLE tbh_inner_order
ADD real_name VARCHAR(255) DEFAULT NULL COMMENT '真实姓名',
ADD specs_code VARCHAR(32) DEFAULT NULL COMMENT '规格编号',
ADD specs_name VARCHAR(255) DEFAULT NULL COMMENT '规格名称',
ADD approver VARCHAR(255) DEFAULT NULL COMMENT '审核人',
ADD approve_datetime DATETIME COMMENT '审核名称',
ADD approve_note TEXT COMMENT '审核备注';


/*********** tbh_jour **************/
UPDATE tbh_jour j JOIN tbh_order o ON j.`ref_no` = o.`code` SET j.biz_type  = (
CASE o.kind WHEN 2 THEN 'AJ_TJJL_IN'
 ELSE 'AJ_TJJL_OUT' END
  )WHERE j.biz_type = 'AJ_TJJL';

UPDATE tbh_jour j JOIN tbh_order o ON j.`ref_no` = o.`code` SET j.biz_type  = (
CASE o.kind WHEN 2 THEN 'AJ_CHJL_IN'
 ELSE 'AJ_CHJL_OUT' END
  )WHERE j.biz_type = 'AJ_CHJL';


/*********** tbh_in_order **************/
UPDATE tbh_order o,tbh_user u SET o.`updater` = u.user_id WHERE o.`updater` = u.login_name ;

UPDATE tbh_order o,tbh_user u SET o.`approver` = u.user_id WHERE o.`approver` = u.login_name ;

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
)
SELECT 
`code`, `product_code`, `product_name`, `product_specs_code`, `product_specs_name`, `pic`, 
`quantity`, `price`, `to_user`, `to_user_name`, `level`, `amount`, 
CASE `status`
WHEN 4 THEN 1
WHEN 5 THEN 2
WHEN 6 THEN 3
WHEN 7 THEN 4
ELSE `status` END, 
`apply_user`, 
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
`pay_group`, `pay_amount`, `pay_datetime`, `pay_code`, `apply_user`, `real_name`, `team_name`, 
`team_leader`, `apply_datetime`, `apply_note`, `signer`, `mobile`, `province`, `city`, `area`, 
`address`, `deliver`, `delive_datetime`, `logistics_code`, `logistics_company`, 
`updater`, `update_datetime`, `update_note`, `approver`, `approve_datetime`, `approve_note`, `remark`) 
SELECT 
`code`, `level`, `is_company_send`,
 CASE `kind`
 WHEN 4 THEN 2
 ELSE kind END,
`product_code`,`product_name`, 
`product_specs_code`, `product_specs_name`, `bar_code`, `pic`, `quantity`, `price`, 
`to_user`, `to_user_name`, `amount`, `yunfei`, `status`, `pay_type`,
`code`, `pay_amount`, `pay_datetime`, `pay_code`, `apply_user`,  `real_name`, `team_name`, 
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

UPDATE tbh_specs_log l JOIN tbh_user u 
SET l.updater=u.`user_id` WHERE l.`updater`=u.`login_name`;
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

/*********************************************************************/
UPDATE tbh_user SET user_id = 'U201807101359562302076' WHERE user_id = 'U201809071221480269150';

UPDATE tbh_agency_log SET apply_user = 'U201807101359562302076' WHERE apply_user = 'U201809071221480269150';

/********************************************************************/

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

ALTER TABLE `tbh_agent` 
ADD INDEX `idx_high_user_id` (`high_user_id` ASC),
ADD INDEX `idx_level` (`level` ASC);

INSERT INTO `tbh_agent` (
`user_id`, `from_user_id`, `mobile`, `wx_id`, `photo`, `nickname`, `trade_pwd`, `trade_pwd_strength`, 
`level`, `referrer`, `introducer`, `high_user_id`, `team_name`, `id_kind`, `id_no`, `id_hand`, `real_name`, 
`status`, `manager`, `union_id`, `h5_open_id`, `app_open_id`, `address`, `province`, `city`, `area`,
`create_datetime`, `updater`, `update_datetime`, `approver`,  `approve_datetime`, `impower_datetime`, `last_agent_log`, `remark`)
SELECT 
`user_id`, `user_referee`, `mobile`, `wx_id`, `photo`, `nickname`, `trade_pwd`, `trade_pwd_strength`,
`level`, `user_referee`, `introducer`, `high_user_id`, `team_name`, `id_kind`, `id_no`, `id_hand`, `real_name`, 
   	CASE `status`
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
              ELSE `status` END ,
`manager`, `union_id`, `h5_open_id`, `app_open_id`, `address`, `province`, `city`, `area`, 
`create_datetime`, `updater`, `update_datetime`, `approver`, `approve_datetime`, `impower_datetime`, `last_agent_log`, `remark`
FROM tbh_user WHERE kind = 'B'; 

UPDATE tbh_agent a JOIN tbh_user u ON a.updater = u.login_name
SET a.updater = u.user_id;

UPDATE tbh_agent a JOIN tbh_user u ON a.approver = u.login_name
SET a.approver = u.user_id,a.approve_name = u.real_name;

UPDATE tbh_agent SET manager = 'USYS201800000000002';
  
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
	     `user_id`,
             `wx_id`,
             `apply_level`,
             `real_name`,
             `mobile`,
             `address`,
             `area`,
             `city`,
             `province`,
				'0',
             `apply_datetime`,
             `approver`,
             `approve_datetime`,
             `remark`
FROM tbh_user u WHERE u.status = 3;


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
              CASE  u.`status`
				WHEN 4 THEN 1
				WHEN 5 THEN 3
		 		END,
             l.`apply_datetime`,
             l.`approver`,
             l.`approve_datetime`,
             l.`remark`
FROM tbh_user u,  tbh_agency_log l WHERE u.last_agent_log = l.code AND u.status IN (4,5);

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
             u.`user_referee`,
 
              CASE u.status
             WHEN 7  THEN 8
             
             WHEN 8  THEN 10
             WHEN 10  THEN 9
             WHEN 11  THEN 7
             WHEN 15 THEN 11
             
             ELSE u.status END ,            
             
             l.`apply_datetime`,
             u.`approver`,
             u.`approve_datetime`,
             u.`impower_datetime`,
             u.`remark`
FROM tbh_user u,tbh_agency_log l WHERE u.last_agent_log = l.code AND u.status IN (6,7,8,9,10,11,15);

 
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
              `status`,
             `pay_amount`,
             `pay_pdf`,
             `approver`,

             `apply_datetime`,
             `approve_datetime`,

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
              CASE     u.`status`
             WHEN 13  THEN 14
             WHEN 14  THEN 13
             ELSE u.`status` END ,
		
	         u.`pay_amount`,
             l.`pay_pdf`,
             l.`approver`,
             l.`apply_datetime`,
             l.`approve_datetime`,
             l.`remark`
FROM tbh_user u ,tbh_agency_log l  WHERE u.last_agent_log = l.code AND u.status IN (12,13,14);
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

ALTER TABLE tbh_ware_log
ADD updater VARCHAR(32) DEFAULT NULL COMMENT '更新人',
ADD update_datetime DATETIME COMMENT '更新时间',
ADD remark VARCHAR(255)  DEFAULT NULL COMMENT '备注';
/*********** thf_ware_house_specs **************/
DROP TABLE thf_ware_house_specs;

/*********** tbh_withdraw **************/
ALTER TABLE   tbh_withdraw 
ADD high_user_id VARCHAR(32) DEFAULT NULL COMMENT '上级编号',
ADD is_company_pay VARCHAR(32) DEFAULT NULL COMMENT '是否公司支付';

/*********** tbh_delive_order **************/
DROP TABLE IF EXISTS `tbh_delive_order`;
CREATE TABLE `tbh_delive_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `kind` char(1) DEFAULT NULL COMMENT '类型（0 出货订单，1内购订单）',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `bar_code` varchar(15) DEFAULT NULL COMMENT '箱码',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `quantity` int(11) DEFAULT '0' COMMENT '数量',
  `price` bigint(20) DEFAULT '0' COMMENT '单价',
  `yunfei` bigint(20) DEFAULT '0' COMMENT '运费',
  `amount` bigint(20) DEFAULT '0' COMMENT '总价',
  `status` char(4) DEFAULT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '下单时间',
  `real_name` varchar(255) DEFAULT NULL COMMENT '下单人姓名',
  `apply_note` text COMMENT '下单备注',
  `signer` varchar(255) DEFAULT NULL COMMENT '收货人',
  `mobile` varchar(16) DEFAULT NULL COMMENT '收货电话',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` text COMMENT '地址',
  `deliver` varchar(32) DEFAULT NULL COMMENT '发货人',
  `delive_datetime` datetime DEFAULT NULL COMMENT '发货时间',
  `logistics_code` varchar(32) DEFAULT NULL COMMENT '物流单号',
  `logistics_company` varchar(255) DEFAULT NULL COMMENT '物流公司',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tbh_delive_order`
            (`code`,
             `kind`,
             `product_code`,
             `product_name`,
             `specs_code`,
             `specs_name`,
             `bar_code`,
             `pic`,
             `quantity`,
             `price`,
             `yunfei`,
             `amount`,
             `status`,
             `apply_user`,
             `apply_datetime`,
             `real_name`,
             `apply_note`,
             `signer`,
             `mobile`,
             `province`,
             `city`,
             `area`,
             `address`,
             `deliver`,
             `delive_datetime`,
             `logistics_code`,
             `logistics_company`)
SELECT      
	      `code`,
             '0',
             `product_code`,
             `product_name`,
             `specs_code`,
             `specs_name`,
             `pro_code`,
             `pic`,
             `quantity`,
             `price`,
             `yunfei`,
             `amount`,
             `status`,
             `apply_user`,
             `apply_datetime`,
             `real_name`,
             `apply_note`,
             `signer`,
             `mobile`,
             `province`,
             `city`,
             `area`,
             `address`,
             `deliver`,
             `delive_datetime`,
             `logistics_code`,
             `logistics_company`
 FROM tbh_out_order WHERE STATUS IN (2,3);

/*********** tbh_delive_order **************/
DROP TABLE IF EXISTS `tbh_product_report`;
CREATE TABLE `tbh_product_report` (
  `code` VARCHAR(32) NOT NULL COMMENT '编号',
  `team_name` VARCHAR(255) DEFAULT NULL COMMENT '团队名称',
  `team_leader` VARCHAR(255) DEFAULT NULL COMMENT '团队长',
  `product_code` VARCHAR(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` VARCHAR(255) DEFAULT NULL COMMENT '产品名称',
  `specs_code` VARCHAR(32) DEFAULT NULL COMMENT '规格编号',
  `specs_name` VARCHAR(255) DEFAULT NULL COMMENT '规格名字',
  `quantity` INT(11) DEFAULT '0' COMMENT '数量',
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/********** tbh_order_report ***************/
DROP TABLE IF EXISTS `tbh_order_report`;
CREATE TABLE `tbh_order_report` (
  `code` VARCHAR(32) NOT NULL COMMENT '订单编号',
  `level` INT(32) DEFAULT NULL COMMENT '等级',
  `is_company_send` VARCHAR(32) DEFAULT NULL COMMENT '是否公司发货',
  `kind` VARCHAR(4) DEFAULT NULL COMMENT '分类',
  `type` VARCHAR(4) DEFAULT NULL COMMENT '分类',
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
  `update_note` VARCHAR(32) DEFAULT NULL COMMENT '更新备注',
  `approver` VARCHAR(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` DATETIME DEFAULT NULL COMMENT '审核时间',
  `approve_note` VARCHAR(255) DEFAULT NULL COMMENT '审核备注',
  `remark` VARCHAR(255) COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

 INSERT INTO `tbh_order_report`
            (`code`,
             `level`,
             `is_company_send`,
             `kind`,
             `type`,
             `product_code`,
             `product_name`,
             `specs_code`,
             `specs_name`,
             `pro_code`,
             `pic`,
             `quantity`,
             `price`,
             `to_user_id`,
             `to_user_name`,
             `amount`,
             `yunfei`,
             `status`,
             `pay_type`,
             `pay_group`,
             `pay_amount`,
             `pay_datetime`,
             `pay_code`,
             `apply_user`,
             `high_user_id`,
             `real_name`,
             `team_name`,
             `team_leader`,
             `apply_datetime`,
             `apply_note`,
             `signer`,
             `mobile`,
             `province`,
             `city`,
             `area`,
             `address`,
             `deliver`,
             `delive_datetime`,
             `logistics_code`,
             `logistics_company`,
             `updater`,
             `update_datetime`,
             `update_note`,
             `approver`,
             `approve_datetime`,
             `approve_note`,
             `remark`)
SELECT 
	     o.`code`,
             o.`level`,
             o.`is_company_send`,
             CASE 
             WHEN o.`kind` = '2' THEN '5'
             ELSE o.kind END,
            
             CASE  
             WHEN o.`kind` = '2' THEN '0'
             ELSE '1' END,
             o.`product_code`,
             o.`product_name`,
             o.`product_specs_code`,
             o.`product_specs_name`,
             o.`bar_code`,
             o.`pic`,
             o.`quantity`,
             o.`price`,
             o.`to_user`,
             o.`to_user_name`,
             o.`amount`,
             o.`yunfei`,
             o.`status`,
             o.`pay_type`,
             o.`pay_group`,
             o.`pay_amount`,
             o.`pay_datetime`,
             o.`pay_code`,
             o.`apply_user`,
             CASE 
	     WHEN o.kind='2' THEN o.to_user
             ELSE a.high_user_id END,
             o.`real_name`,
             o.`team_name`,
             o.`team_leader`,
             o.`apply_datetime`,
             o.`apply_note`,
             o.`signer`,
             o.`mobile`,
             o.`province`,
             o.`city`,
             o.`area`,
             o.`address`,
             o.`deliver`,
             o.`delive_datetime`,
             o.`logistics_code`,
             o.`logistics_company`,
             o.`updater`,
             o.`update_datetime`,
             o.`update_note`,
             o.`approver`,
             o.`approve_datetime`,
             o.`approve_note`,
             o.`remark`
FROM tbh_order o,tbh_agent a WHERE o.apply_user = a.user_id AND o.status IN(2,3,4);



/********** tsys_config ************/

insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('WD_QX','WDQXCS','10','USYS201800000000002',NOW(),'微店每月取现次数');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('WD_QX','WDQXFL','0.02','USYS201800000000002',NOW(),'微店用户取现费率');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('WD_QX','WDQXJE','10','USYS201800000000002',NOW(),'微店单次取现最低金额');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('WD_QX','WDQXBS','0.02','USYS201800000000002',NOW(),'微店取现倍数');

insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('wx_pay','name','麦记麦绿素','USYS201800000000002',NOW(),'渠道名称');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('wx_pay','type','35','USYS201800000000002',NOW(),'渠道类型');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('wx_pay','company_code','1508468601','USYS201800000000002',NOW(),'渠道代号');

insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('wx_pay','private_key1','r2jgDFSdiikklwlllejlwjio3238431h','USYS201800000000002',NOW(),'秘钥1');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('wx_pay','private_key2','wx1f67df3de14d4fcf','USYS201800000000002',NOW(),'秘钥2');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('xcx_pay','name','微信小程序','USYS201800000000002',NOW(),'渠道名称');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('xcx_pay','type','1','USYS201800000000002',NOW(),'渠道类型');

insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('xcx_pay','company_code','1508468601','USYS201800000000002',NOW(),'渠道代号');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('xcx_pay','private_key1','r2jgDFSdiikklwlllejlwjio3238431h','USYS201800000000002',NOW(),'秘钥1');
insert into `tsys_config` (`type`, `ckey`, `cvalue`, `updater`, `update_datetime`, `remark`) values('xcx_pay','private_key2','wxa0c8f09abc470f2f','USYS201800000000002',NOW(),'秘钥2');

UPDATE tsys_config SET cvalue = '0' WHERE ckey = '四川省';

UPDATE tsys_config SET cvalue = '0' WHERE ckey = '西藏自治区';

/********** tsys_dict ************/
TRUNCATE TABLE tsys_dict;
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'user_status','用户状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','user_status','0','正常','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','user_status','1','程序锁定','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','user_status','2','人工锁定','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'id_kind','证件类型','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','id_kind','1','身份证','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'res_type','资源类型','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','res_type','1','菜单','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','res_type','2','按钮','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'lock_direction','锁定方向','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','lock_direction','1','锁定','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','lock_direction','2','解锁','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'kd_company','物流公司','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','EMS','邮政EMS','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','STO','申通快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','ZTO','中通快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','YTO','圆通快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','HTKY','汇通快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','SF','顺丰快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','TTKD','天天快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','ZJS','宅急送','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','BSHT','百世汇通','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','YDKD','韵达快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','DBKD','德邦快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','QFKD','全峰快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','GTKD','国通快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','TDHY','天地华宇','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','JJWL','佳吉物流','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','JJKD','快捷快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','YSKD','优速快递','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','kd_company','QTKD','其他','USYS201800000000002',NOW(),'');
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'user_kind','针对人群','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','user_kind','C','C端用户','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','user_kind','B','代理','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','user_kind','P','平台所有用户','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'material_type','素材类型','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','material_type','0','产品素材','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','material_type','1','培训素材','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'inner_status','内购产品状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','inner_status','1','待上架','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','inner_status','2','已上架','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','inner_status','3','已下架','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'product_status','产品状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','product_status','1','待上架','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','product_status','2','已上架','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','product_status','3','已下架','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'award','奖励','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','award','0','推荐奖励','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','award','1','出货奖励','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','award','2','介绍奖励','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'charge_status','充值订单状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','charge_status','1','待支付','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','charge_status','2','支付失败','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','charge_status','3','支付成功','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','charge_status','4','待审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','charge_status','5','审核未通过','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','charge_status','6','审核通过','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'inner_order_status','内购产品订单状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','inner_order_status','0','待支付','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','inner_order_status','1','已支付待审单','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','inner_order_status','2','已审单待发货','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','inner_order_status','3','待收货','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','inner_order_status','4','已收货','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','inner_order_status','5','申请取消','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','inner_order_status','6','已取消','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'jour_status','流水状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','jour_status','1','待对账','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','jour_status','3','已对账且账已平','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','jour_status','4','帐不平待调账审批','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','jour_status','5','已对账且账不平','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','jour_status','6','无需对账','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'channel_type','渠道类型','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','channel_type','90','线下支付','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','channel_type','35','微信充值','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','channel_type','0','内部转账','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','channel_type','1','小程序支付','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'biz_type','业务类型','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','11','取现','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','XXFK','提现回录','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_CZ','充值','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_KK','扣款','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_MKCZ','门槛充值','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_GMNP','购买内购商品','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_GMYC','购买产品','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_CELR','差额利润','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_TJJL_IN','推荐奖励','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_CHJL_IN','出货奖励','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_TJJL_OUT','推荐奖励','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_CHJL_OUT','出货奖励','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_XGSJ','修改上级','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_XJCZ','下级微信充值','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_XCX','小程序付款','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_QXSQ','退出清空余额','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','YUNFEI','支付运费','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_GMCP_TK','取消订单','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_QKYE','升级余额变动','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_JSJL','介绍奖励','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'withdraw_status','取现状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','withdraw_status','1','待审批','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','withdraw_status','2','审批不通过','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','withdraw_status','3','审批通过待支付','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','withdraw_status','4','支付失败','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','withdraw_status','5','支付成功','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0','','out_order_status','提货订单状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_status','0','待支付','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_status','1','已支付待审单','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_status','2','已审单待发货','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_status','3','待收货','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_status','4','已收货','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_status','5','申请取消','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_status','6','已取消','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'agent_status','代理状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_status','3','已忽略','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_status','6','待上级授权','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_status','7','待公司授权','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_status','8','已授权','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_status','9','未授权','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'out_order_type','订单类型','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_type','0','授权单','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_type','1','升级单','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_type','2','普通单','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_type','3','云仓提货','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','out_order_type','4','微店订单','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'currency','币种','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','currency','TX_CNY','可提现账户','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','currency','TC_CNY','云仓账户','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','currency','MK_CNY','门槛账户','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','currency','C_CNY','C端账户','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'change_product_status','置换单','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','change_product_status','0','申请换货','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','change_product_status','1','申请通过','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','change_product_status','2','申请未通过','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','biz_type','AJ_GMCP','购买产品','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'product_log_type','产品库存记录类型','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','product_log_type','0','出库','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','product_log_type','1','入库','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','product_log_type','2','代理下单','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','product_log_type','3','代理换货','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'agnecy_log_type','代理轨迹类型','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agnecy_log_type','0','意向分配','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agnecy_log_type','1','授权','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agnecy_log_type','2','升级','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agnecy_log_type','3','退出','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'account_status','账户状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','account_status','0','正常','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','account_status','1','程序锁定','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','account_status','2','人工锁定','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'address_type','地址类型','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','address_type','1','用户地址','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'banner_status','banner图状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','banner_status','0','已发布','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','banner_status','1','已撤下','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'sq_status','授权单状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sq_status','4','补充授权资料','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sq_status','5','填写授权资料','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sq_status','6','待上级审核授权','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sq_status','7','授权待公司审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sq_status','8','已授权','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sq_status','9','授权未通过','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sq_status','10','申请退出待上级审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sq_status','11','申请退出待公司审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'yx_status','意向单状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','yx_status','0','有意愿','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','yx_status','1','已忽略','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','yx_status','2','已接受','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','yx_status','3','已分配','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'sj_status','升级单状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sj_status','10','上级审核升级','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sj_status','11','公司审核升级','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sj_status','12','已升级','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','sj_status','13','升级未通过','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'agent_log_status','代理轨迹状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','0','有意向','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','1','已忽略','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','2','接受意向','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','3','已分配','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','4','待填写授权资料','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','5','补充授权资料','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','6','待上级授权','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','7','待公司授权','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','8','已授权','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','9','未授权','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','10','退出待上级审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','11','退出待公司审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','12','升级待上级审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','13','升级待公司审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','14','已升级','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_log_status','15','升级未通过','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'in_order_status','云仓订单状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','in_order_status','0','待支付','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','in_order_status','1','已收货','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','in_order_status','2','申请取消','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','in_order_status','3','已取消','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','in_order_status','4','支付失败','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'pro_code_status','箱码状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','pro_code_status','0','待下载','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','pro_code_status','1','未使用','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','pro_code_status','2','已使用','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','pro_code_status','3','已拆分','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'mini_code_status','盒码状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','mini_code_status','0','待下载','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','mini_code_status','1','未使用','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','mini_code_status','2','已使用','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'source','意向来源','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','source','0','公众号','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','currency','C_CNY','C端账户','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'bananer_belong','banner图位置','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','bananer_belong','0','地方','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'ware_log_type','云仓变动类型','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','ware_log_type','0','购买云仓','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','ware_log_type','1','云仓出货','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','ware_log_type','2','云仓置换','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'user_status','用户状态','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','user_status','0','正常','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','user_status','1','程序锁定','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','user_status','2','人工锁定','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('0',NULL,'specs_log_type','产品库存变动类型','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','specs_log_type','0','出库','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','specs_log_type','1','入库','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','specs_log_type','2','代理下单','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','specs_log_type','3','代理置换','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_status','10','退出待上级审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_status','11','退出待公司审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_status','12','升级待上级审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_status','13','升级待公司审核','USYS201800000000002',NOW(),NULL);
insert into `tsys_dict` (`type`, `parent_key`, `dkey`, `dvalue`, `updater`, `update_datetime`, `remark`) values('1','agent_status','14','已升级','USYS201800000000002',NOW(),NULL);


/**************tsys_menu****************/
TRUNCATE TABLE tsys_menu;
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM201700000000000000','','根目录','1','#','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM201700001000000001','BHXTSM201700000000000000','系统管理','1','#','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM201700001000000002','BHXTSM201700001000000001','运维管理','1','#','2','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM201700001000000003','BHXTSM201700001000000002','菜单管理','1','/system/menu.htm','1','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM201700001000000004','BHXTSM201700001000000003','新增','2','/add','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017032911200961325','BHXTSM201700001000000003','修改','2','/edit','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017033020005366333','BHXTSM201707251006045006005','banner管理','1','/public/banner.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017033020015631166','BHXTSM2017033020005366333','新增','2','/add','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017033020021094115','BHXTSM2017033020005366333','修改','2','/edit','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017033020022649991','BHXTSM2017033020005366333','发布/撤下','2','/release','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017033020024827112','BHXTSM2017033020005366333','详情','2','/detail','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM201707251006045006005','BHXTSM201700001000000001','广告位管理','1','#','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM201708241024194086655','BHXTSM201700001000000003','删除','2','/delete','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017101716241339082','BHXTSM201700001000000001','运营管理','1','#','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017101716253866426','BHXTSM2017101716241339082','角色管理','1','/system/role.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017101716261754674','BHXTSM2017101716241339082','用户管理','1','/system/user.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017101716450533995','BHXTSM2017101716253866426','分配菜单','2','/change','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017101717551955993','BHXTSM2017101716253866426','新增','2','/add','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017101717560118734','BHXTSM2017101716253866426','修改','2','/edit','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017101717563661357','BHXTSM2017101716253866426','删除','2','/delete','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017101719082391126','BHXTSM2017101716261754674','新增','2','/add','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017101719094151894','BHXTSM2017101716261754674','重置密码','2','/reset','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017101719100760088','BHXTSM2017101716261754674','注销/激活','2','/rock','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017121215543215610','BHXTSM201700001000000001','文章管理','1','#','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('BHXTSM2017121216045274832','BHXTSM2017121215543215610','关于我们','1','/public/aboutus_addedit.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261509408624073','BHXTSM201700000000000000','代理管理','1','#','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261521004394630','BHXTSM201700000000000000','报货管理','1','#','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261521182464507','BHXTSM201700000000000000','财务管理','1','#','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261521343823118','BHXTSM201700000000000000','统计分析','1','#','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180326152552471787','BHXTSM201700001000000002','系统参数管理','1','/system/sysPara.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261526327112082','BHXTSM201700001000000002','数据字典管理','1','/system/dataDict.htm','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261531095855290','SM201803261509408624073','代理管理','1','#','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261535196381139','SM201803261531095855290','意向代理分配','1','/dailiManage/yixiangdailifenpei.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261536091829626','SM201803261531095855290','审核授权','1','/dailiManage/shenheshouquan.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261536425266724','SM201803261531095855290','审核升级','1','/dailiManage/shenheshengji.htm','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261538117296253','SM201803261531095855290','代理管理','1','/dailiManage/dailiziliao.htm','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261538424769323','SM201803261531095855290','代理结构','1','/dailiManage/dailijiegou.htm','6','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261539118953097','SM201803261531095855290','代理轨迹','1','/dailiManage/dailiguiji.htm','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261540237406513','SM201803261509408624073','财务管理','1','#','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261540507906075','SM201803261509408624073','系统设置','1','#','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261542031354832','SM201803261540237406513','充值查询','1','/dailiManage/shenhechongzhi.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261542346663628','SM201803261540237406513','账户管理','1','/dailiManage/yueguanli.htm','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261543389416493','SM201803261540237406513','出货奖励','1','/dailiManage/chuhuojiangli.htm','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261544115873430','SM201803261540237406513','推荐奖励','1','/dailiManage/tuijianjiangli.htm','6','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261544471555008','SM201803261540237406513','介绍奖励','1','/dailiManage/jieshaojiangli.htm','7','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261545567283077','SM201803261540507906075','代理授权设置','1','/dailiManage/dailishouquanConfig.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261546290999792','SM201803261540507906075','代理升级设置','1','/dailiManage/dailishengjiConfig.htm','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261547049232632','SM201803261540507906075','推荐奖设置','1','/dailiManage/tuijianjiangConfig.htm','6','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261547449352404','SM201803261540507906075','出货奖设置','1','/dailiManage/chuhuojiangConfig.htm','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261548253013147','SM201803261540507906075','介绍奖设置','1','/dailiManage/jieshaojiangConfig.htm','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180326154903564539','SM201803261540507906075','授权证书设置','1','/dailiManage/shouquanzhengshuConfig.htm','7','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261550403898481','SM201803261521004394630','云仓管理','1','#','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261551311709995','SM201803261521004394630','溯源防伪','1','#','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261551473755878','SM201803261521004394630','素材管理','1','#','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261552202877480','SM201803261521004394630','内购商城','1','#','6','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261554058861920','SM201808111956547548940','库存记录','1','/bhManage/productRecord.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261554393396515','SM201803261550403898481','出货订单','1','/bhManage/waitOrder.htm','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261559284039168','SM201803261551311709995','已使用列表','1','/bhManage/fangweisuyuan.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261601154414563','SM201803261551473755878','素材管理','1','/bhManage/sucaiManage.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261604087514061','SM201803261552202877480','产品管理','1','/bhManage/productManage.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261604394462102','SM201803261552202877480','订单管理','1','/bhManage/orderManage.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261606277913100','SM201803261521182464507','平台账户','1','#','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261606472405257','SM201803261521182464507','线下取现','1','#','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261608099875430','SM201803261606277913100','账户查询','1','/financeManage/account.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261608592077925','SM201803261606277913100','流水查询','1','/financeManage/liushui.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261611046693924','SM201803261606472405257','取现规则','1','/financeManage/quxianRules.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261611550204639','SM201803261606472405257','线下取现','1','/financeManage/underlineQuxian.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261612401458650','SM201803261606472405257','取现查询','1','/financeManage/queryQuxian.htm','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261613482969432','SM20180326161939371910','业绩统计','1','/count/yejiCount.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261614184379303','SM20180326161939371910','代理商统计','1','/count/dailishangCount.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261617490833870','SM20180326161939371910','团队产品销售数量统计','1','/count/teamSellNumCount.htm','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180326161845976397','SM20180326161939371910','新增代理统计','1','/count/newDailiCount.htm','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180326161939371910','SM201803261521343823118','统计管理','1','#','0','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261626555555504','BHXTSM2017121215543215610','服务热线','1','/public/hotline_addedit.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180326162715242440','BHXTSM2017121215543215610','服务时间','1','/public/time_addedit.htm','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261631287846965','SM201803261535196381139','审核分配','2','/check','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261631486278542','SM201803261535196381139','接受意向','2','/edityx','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261632083649677','SM201803261535196381139','忽略意向','2','/hulve','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261633072051819','SM201803261536091829626','审核','2','/check','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261634051172033','SM201803261536425266724','审核','2','/check','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261635411719158','SM201803261538117296253','详情','2','/detail','8','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261635537796598','SM201803261538117296253','编辑','2','/edit','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261636184379364','SM201803261538117296253','修改上级','2','/changeUp','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261636377473042','SM201803261538117296253','修改推荐人','2','/changeReferee','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180326163700054949','SM201803261538117296253','修改管理员','2','/changeAdmin','6','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261637186144643','SM201803261538117296253','导出','2','/export','9','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261638111069070','SM201803261538424769323','导出','2','/export','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261639392578554','SM201803261542031354832','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261642354034722','SM201803261542346663628','充值','2','/in','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261643499083022','SM201803261542346663628','扣款','2','/out','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261644177075053','SM201803261542346663628','余额变动记录','2','/yueChangeRecord','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261701446659755','SM201803261543389416493','支出明细','2','/outRecord','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261702027406812','SM201803261543389416493','收入明细','2','/inRecord','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261702324378','SM201803261544115873430','支出明细','2','/outRecord','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261702507289103','SM201803261544115873430','收入明细','2','/inRecord','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261703206553784','SM201803261544471555008','支出明细','2','/outRecord','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261704056995038','SM201803261544471555008','收入明细','2','/inRecord','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261740162214183','SM201803261526327112082','修改','2','/edit','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261745351957613','SM20180326152552471787','修改','2','/edit','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261806292929983','SM201803261545567283077','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM2018032618065379263','SM201803261545567283077','修改','2','/edit','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261807305875425','SM201803261546290999792','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM2018032618074402344','SM201803261546290999792','修改','2','/edit','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261808165365305','SM201803261547049232632','修改','2','/edit','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261808458576273','SM201803261547449352404','修改','2','/edit','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261809133466785','SM201803261548253013147','修改','2','/edit','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261809372583496','SM20180326154903564539','修改','2','/edit','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261812481746184','SM201803261554058861920','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261813218699036','SM201803261554393396515','详情','2','/detail','6','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261813405429345','SM201803261554393396515','修改收货','2','/changeAddress','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261813582615854','SM201803261554393396515','导出','2','/export','7','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261814154879609','SM201803261554393396515','批量审单','2','/shendan','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261814510524596','SM201803261554393396515','审核取消','2','/cancel','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261851312553803','SM201803261559284039168','防伪记录','2','/fangweijilu','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261851511533166','SM201803261559284039168','溯源记录','2','/suyuanjilu','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261853151325367','SM201803261601154414563','新增','2','/add','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261853274263384','SM201803261601154414563','修改','2','/edit','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261853439898122','SM201803261601154414563','删除','2','/delete','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261856116089330','SM201803261608592077925','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261856258966710','SM201803261608592077925','导出','2','/export','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261856526437749','SM201803261611046693924','修改','2','/edit','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261857286203906','SM201803261611550204639','代申请','2','/daishenqing','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261857445834545','SM201803261611550204639','批量审核','2','/check','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261858170842370','SM201803261611550204639','批量回录','2','/huilu','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261858335968438','SM201803261611550204639','导出','2','/export','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261859167411401','SM201803261612401458650','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261859309677825','SM201803261612401458650','导出','2','/export','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180326190235565892','SM201803261604087514061','新增','2','/add','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261902536746938','SM201803261604087514061','编辑','2','/edit','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261903122085714','SM201803261604087514061','上架','2','/up','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261904206199828','SM201803261604394462102','详情','2','/detail','5','USYS201800000000002','2018-09-04 16:40:08','','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261904405563055','SM201803261604394462102','导出','2','/export','6','USYS201800000000002','2018-09-04 16:40:19','','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803261906042753348','SM201803261604394462102','物流信息','2','/wuliu','3','USYS201800000000002','2018-09-04 16:39:34','','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180327141118551514','SM201803261540507906075','代理等级管理','1','/dailiManage/levelManage.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803271424448506892','SM20180327141118551514','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180327142457630660','SM20180327141118551514','修改','2','/edit','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201803271445283981921','SM201803261604087514061','下架','2','/down','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201804131614374363524','SM201803261539118953097','导出','2','/export','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201804161652350873803','SM201803261550403898481','置换订单','1','/bhManage/exchange.htm','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201804161653521178359','SM201804161652350873803','审核','2','/check','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201806282229539506679','SM201803261547449352404','新增','2','/add','0','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201806282329507537878','SM201803261548253013147','新增','2','/add','0','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201806282330166273884','SM201803261547449352404','删除','2','/delete','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201806282330376493849','SM201803261548253013147','删除','2','/delete','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201806292105196303080','SM201803261526327112082','新增','2','/add','0','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201807012221248843694','SM201803261551311709995','条形码','1','/bhManage/lineCode.htm','99','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808071555598116534','SM201803261538117296253','取消授权','2','/remove','6','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808081019090343000','SM201803261540507906075','银行管理','1','/dailiManage/yinhangka.htm','8','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808081035063905150','SM201808081019090343000','新增','2','/add','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808081051478892078','SM201808081019090343000','修改','2','/change','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808091845507273211','BHXTSM2017101716261754674','设置角色','2','/ueditj','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808101014134339536','SM201803261535196381139','详情','2','/edit','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180810110804839315','SM201803261538117296253','银行卡','2','/bankCard','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808101149391957333','SM201803261539118953097','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808101346167894203','SM201803261540237406513','取现查询','1','/dailiManage/quxiancx.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808101354201927274','SM201808101346167894203','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808101355188338040','SM201808101346167894203','导出','2','/export','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808102009475995392','SM201803261538117296253','代理轨迹','2','/dailiguiji','6','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808102010257587133','SM201803261538117296253','代理结构','2','/dailijiegou','6','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808111622304469609','SM201803261540237406513','账户流水','1','/dailiManage/liushui.htm','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808111648291836625','SM201803261509408624073','C端用户','1','#','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808111650532792275','SM201808111648291836625','用户管理','1','/dailiManage/cduanyhu.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808111909359184581','SM201808111650532792275','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808111956547548940','SM201803261521004394630','产品管理','1','#','0','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808112007153779569','SM201808111956547548940','产品列表','1','/bhManage/productList.htm','0','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808112010270909654','SM201808112007153779569','修改','2','/edit','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808112012080732177','SM201808112007153779569','新增','2','/add','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM2018081120142648098','SM201808112007153779569','上架','2','/up','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808112015006307410','SM201808112007153779569','下架','2','/down','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808112017031283322','SM201808112007153779569','规格库存','2','/guige','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808112019543172176','SM201808112007153779569','库存变动记录','2','/kucunbd','6','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808131012315965824','SM201808111956547548940','云仓订单','1','/bhManage/yuncangguanli.htm','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808131035479435317','SM201808131012315965824','审核取消','2','/shenheqx','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808131036174493831','SM201808131012315965824','详情','2','/detail','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808131037018486188','SM201808131012315965824','导出','2','/export','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808131236351047650','SM201803261550403898481','云仓订单','1','/bhManage/yuncangdingdan.htm','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808131237245701310','SM201803261550403898481','云仓查询','1','/bhManage/yuncangchaxun.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180813123803589109','SM201803261550403898481','云仓记录','1','/bhManage/yuncangjilu.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808131243114905685','SM201808131237245701310','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180813124432508912','SM201808131237245701310','库存变动记录','2','/kucunbd','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808131752567015815','SM201808160334413891953','审核','2','/check','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808131821224605031','SM201808160335410547169','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808160005256757582','SM20180813123803589109','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808160025098029737','SM201808131236351047650','详情','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808160041085023959','SM201808131236351047650','导出','2','/export','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808160116516092918','SM201803261554393396515','作废','2','/cancellation','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808160122408601657','SM201804161652350873803','导出','2','/export','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808160127522407644','SM201803261551311709995','箱码盒码管理','1','/bhManage/xiangmahema.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808160147538679438','SM201808160127522407644','新增','2','/addxm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808160307541352860','SM201803261604394462102','批量审单','2','/shendan','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808160332359037371','SM201803261521182464507','充值管理','1','#','3','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808160334413891953','SM201808160332359037371','审核充值','1','/financeManage/shenhechongz.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808160335410547169','SM201808160332359037371','充值查询','1','/financeManage/chongzhichaxun.htm','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808201615425553242','BHXTSM2017101716261754674','详情','2','/ydetail','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808271526170728592','SM201804161652350873803','详情','2','/ydetail','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808271734486488859','SM201803261604087514061','详情','2','/ydetail','5','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808271737000518604','SM201808112007153779569','详情','2','/ydetail','7','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180827181336737309','SM201803261535196381139','修改资料','2','/yedit','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808271948257094538','SM201803261601154414563','详情','2','/ydetail','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808281132176405818','SM201804161652350873803','修改换货价','2','/detail','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201808291128507781190','SM201803261536091829626','审核退出','2','/qxcheck','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM20180830111127384910','SM201803261611550204639','详情','2','/detail','4','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201809012047159529424','SM201803261521004394630','运费模块','1','#','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201809012048004315159','SM201809012047159529424','运费配置','1','/bhManage/yunfeipeiz.htm','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201809012053120039219','SM201809012048004315159','修改','2','/edit','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201809031746140785986','SM201808131237245701310','修改库存','2','/edit','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201809041638486267168','SM201803261604394462102','审核取消','2','/qxcheck','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201809051505212104563','SM201803261550403898481','全部订单','1','/bhManage/allOrder.htm','7','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201809051537132022414','SM201809051505212104563','物流信息','2','/wuliu','1','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201809051537316768613','SM201809051505212104563','详情','2','/detail','2','USYS201800000000002',NOW(),'','CD-CBH000020');
insert into `tsys_menu` (`code`, `parent_code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `system_code`) values('SM201809051538122448673','SM201809051505212104563','导出','2','/export','3','USYS201800000000002',NOW(),'','CD-CBH000020');




/********** tsys_menu_role **************/
TRUNCATE TABLE tsys_menu_role;
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM201700000000000000','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM201700001000000001','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM201700001000000002','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM201700001000000003','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM201700001000000004','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017032911200961325','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM201708241024194086655','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180326152552471787','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261745351957613','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261526327112082','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261740162214183','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201806292105196303080','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM201707251006045006005','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017033020005366333','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017033020015631166','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017033020021094115','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017033020022649991','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017033020024827112','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017101716241339082','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017101716253866426','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017101716450533995','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017101717551955993','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017101717560118734','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017101717563661357','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017101716261754674','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017101719082391126','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017101719094151894','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017101719100760088','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808091845507273211','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808201615425553242','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017121215543215610','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','BHXTSM2017121216045274832','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261626555555504','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180326162715242440','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261509408624073','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261531095855290','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261535196381139','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261631287846965','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261631486278542','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261632083649677','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808101014134339536','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180827181336737309','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261536091829626','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261633072051819','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808291128507781190','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261536425266724','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261634051172033','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261538117296253','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261635411719158','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261635537796598','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261636184379364','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261636377473042','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180326163700054949','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261637186144643','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808071555598116534','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180810110804839315','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808102009475995392','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808102010257587133','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261538424769323','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261638111069070','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261539118953097','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201804131614374363524','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808101149391957333','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261540237406513','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261542031354832','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261639392578554','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261542346663628','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261642354034722','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261643499083022','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261644177075053','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261543389416493','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261701446659755','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261702027406812','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261544115873430','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261702324378','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261702507289103','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261544471555008','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261703206553784','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261704056995038','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808101346167894203','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808101354201927274','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808101355188338040','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808111622304469609','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261540507906075','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261545567283077','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261806292929983','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM2018032618065379263','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261546290999792','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261807305875425','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM2018032618074402344','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261547049232632','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261808165365305','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261547449352404','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261808458576273','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201806282229539506679','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201806282330166273884','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261548253013147','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261809133466785','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201806282329507537878','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201806282330376493849','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180326154903564539','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261809372583496','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180327141118551514','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803271424448506892','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180327142457630660','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808081019090343000','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808081035063905150','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808081051478892078','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808111648291836625','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808111650532792275','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808111909359184581','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261521004394630','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261550403898481','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261554393396515','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261813218699036','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261813405429345','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261813582615854','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261814154879609','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261814510524596','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808160116516092918','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201804161652350873803','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201804161653521178359','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808160122408601657','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808271526170728592','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808281132176405818','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808131236351047650','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808160025098029737','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808160041085023959','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808131237245701310','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808131243114905685','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180813124432508912','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201809031746140785986','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180813123803589109','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808160005256757582','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201809051505212104563','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201809051537132022414','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201809051537316768613','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201809051538122448673','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261551311709995','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261559284039168','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261851312553803','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261851511533166','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201807012221248843694','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808160127522407644','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808160147538679438','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261551473755878','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261601154414563','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261853151325367','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261853274263384','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261853439898122','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808271948257094538','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261552202877480','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261604087514061','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180326190235565892','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261902536746938','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261903122085714','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803271445283981921','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808271734486488859','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261604394462102','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261904206199828','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261904405563055','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261906042753348','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808160307541352860','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201809041638486267168','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808111956547548940','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261554058861920','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261812481746184','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808112007153779569','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808112010270909654','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808112012080732177','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM2018081120142648098','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808112015006307410','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808112017031283322','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808112019543172176','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808271737000518604','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808131012315965824','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808131035479435317','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808131036174493831','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808131037018486188','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201809012047159529424','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201809012048004315159','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201809012053120039219','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261521182464507','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261606277913100','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261608099875430','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261608592077925','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261856116089330','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261856258966710','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261606472405257','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261611046693924','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261856526437749','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261611550204639','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261857286203906','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261857445834545','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261858170842370','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261858335968438','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180830111127384910','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261612401458650','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261859167411401','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261859309677825','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808160332359037371','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808160334413891953','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808131752567015815','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808160335410547169','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201808131821224605031','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261521343823118','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180326161939371910','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261613482969432','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261614184379303','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201803261617490833870','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180326161845976397','USYS201800000000002',NOW(),NULL,'CD-CBH000020');


/***************  tsys_role *********************/
UPDATE tsys_role SET updater='USYS201800000000002' WHERE updater = 'admin';

/**************** tsys_config *******************/
UPDATE tsys_config SET updater='USYS201800000000002' WHERE updater = 'admin';

/**************** tbh_inner_specs *******************/
DROP TABLE IF EXISTS `tbh_inner_specs`;
CREATE TABLE `tbh_inner_specs` (
  `code` VARCHAR(32) NOT NULL COMMENT '编号',
  `inner_product_code` VARCHAR(32) DEFAULT NULL COMMENT '内购产品编号',
  `name` VARCHAR(64) DEFAULT NULL COMMENT '规格名称',
  `number` INT(11) DEFAULT '0' COMMENT '规格包含数量',
  `weight` INT(11) DEFAULT '0' COMMENT '重量',
  `price` INT(11) DEFAULT '0' COMMENT '单价',
  `ref_code` VARCHAR(32) DEFAULT NULL COMMENT '关联规格编号',
  `stock_number` INT(11) DEFAULT '0' COMMENT '库存',
  `is_single` CHAR(1) DEFAULT NULL COMMENT '是否可拆单',
  `single_number` INT(11) DEFAULT '0' COMMENT '拆单数量',
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/**************** tbh_material *******************/
ALTER TABLE tbh_material 
ADD create_datetime DATETIME COMMENT '创建时间',
ADD updater VARCHAR(32) DEFAULT NULL COMMENT '更新人',
ADD update_datetime DATETIME COMMENT '更新时间',
ADD remark VARCHAR(255) DEFAULT NULL COMMENT '备注';

DROP TABLE tbh_order;

DROP TABLE tbh_user;

DROP TABLE tbh_agency_log;

/**************** tbh_cart *******************/
ALTER TABLE tbh_cart 
ADD TYPE CHAR(1) DEFAULT NULL COMMENT '类型';

ALTER TABLE tbh_cart 
CHANGE product_specs_code specs_code VARCHAR(32) DEFAULT NULL;

DELETE FROM tbh_pro_code WHERE STATUS = '0';

DELETE FROM tbh_mini_code WHERE STATUS = '0';

UPDATE tbh_agent SET referrer= NULL ,from_user_id= NULL WHERE referrer = 'U201807171405128051197';

UPDATE tbh_agent SET referrer= NULL ,from_user_id= NULL WHERE referrer = 'U201807101359562302076';

UPDATE tbh_agent SET referrer= NULL ,from_user_id= NULL WHERE referrer = 'U201807111435137758097';

INSERT INTO `tbh_agent_price`
            (`code`,
             `specs_code`,
             `level`,
             `price`,
             `change_price`,
             `start_number`,
             `min_number`,
             `daily_number`,
             `weekly_number`,
             `monthly_number`,
             `is_buy`)
SELECT 
	     CONCAT('C',CODE),
             `specs_code`,
             '6',
             '268000',
             '268000',
             '0',
             '0',
             '0',
             '0',
             '0',
             '1'
FROM tbh_agent_price GROUP BY  specs_code;            

UPDATE tbh_agent_price SET price = 10720000 WHERE specs_code = 'PS201806301939383841880' AND LEVEL= 6;

UPDATE tbh_out_order o JOIN `tbh_agent_log` l
SET o.`high_user_id` = l.`high_user_id` WHERE o.`apply_user` = l.`apply_user` AND o.`level` = l.`level`;

ALTER TABLE tbh_cnavigate 
ADD updater VARCHAR(32) DEFAULT NULL COMMENT '更新人',
ADD update_datetime DATETIME COMMENT '更新时间';

UPDATE tbh_cnavigate SET updater = 'USYS201800000000002',update_datetime = NOW();

DELETE FROM tbh_jour WHERE trans_amount = '0';

UPDATE tbh_out_order SET STATUS = '2' WHERE STATUS = '1' AND specs_code = 'PS201806291944580145174';

UPDATE tbh_out_order SET high_user_id = 'USYS201800000000002' WHERE LEVEL = 1;

INSERT INTO `tbh_agent_level`
            (`code`,
             `level`,
             `name`,
             `red_amount`,
             `amount`,
             `min_charge_amount`,
             `min_surplus`,
             `is_send`,
             `is_ware`,
             `is_company_approve`,
             `re_number`,
             `is_reset`,
             `is_intent`,
             `is_jsAward`,
             `is_real_name`,
             `is_company_impower`,
             `min_charge`,
             `updater`,
             `update_datetime`,
             `remark`)
VALUES 
		('AL201800000000000006',
        '6',
        'C店用户',
        '0',
        '0',
        '0',
        '0',
        '0',
        '0',
        '0',
        '0',
        '0',
        '0',
        '0',
        '0',
        '0',
        '0',
        'USYS201800000000002',
        NOW(),
        '');

UPDATE tbh_ware_log w JOIN tbh_agent u SET w.`real_name` = u.`real_name` WHERE u.user_id = w.apply_user;

UPDATE tbh_ware_log SET TYPE = '1' WHERE biz_type = 'AJ_YCTH'; 

UPDATE tbh_ware_log SET TYPE = '0' WHERE biz_type = 'AJ_GMYC';

UPDATE tbh_ware_log SET TYPE = '1' WHERE biz_type = 'AJ_YCCH';

/*********************** WH201807231615508828525 *************************/
UPDATE tbh_ware SET user_id = 'U201807081440374532026',quantity=0 WHERE user_id = 'USYS201800000000002';

UPDATE tbh_agent_log l,tbh_agent u SET l.`to_user_id` = u.`high_user_id`
WHERE l.`apply_user` = u.`user_id` AND l.to_user_id = 'USYS201800000000002' AND l.apply_level != 1;

UPDATE tbh_order_report o JOIN tbh_agent u ON o.`signer`=u.`user_id`
SET o.signer = u.`real_name`;

UPDATE tbh_cnavigate SET STATUS = 0 AND belong = 0 WHERE CODE = 'DH201807251626344874399';

UPDATE tbh_agent_log SET to_user_id = 'USYS201800000000002' WHERE apply_level = '1';

UPDATE tbh_agent_log SET high_user_id = 'USYS201800000000002' WHERE LEVEL = '1';

update `tsys_config` set cvalue = 'wxa0c8f09abc470f2f' where type='wx_xcx' and  ckey='wx_xcx_access_key';

update `tsys_config` set cvalue = '0aebaab70f987fe95bc88e9f4aecdfaa' where type='wx_xcx' and  ckey='wx_xcx_secret_key';

ALTER TABLE tbh_agent 
ADD is_impower CHAR(1) DEFAULT NULL COMMENT '是否完成授权单/升级单';

UPDATE tbh_agent SET is_impower = '2';

UPDATE tbh_agent ag,(SELECT `user_id` FROM tbh_agent a  WHERE  NOT EXISTS 
(SELECT a.`user_id` FROM tbh_out_order o WHERE a.user_id = o.`apply_user` AND a.`status`IN ('8','10','11','12','13') AND o.kind = '0' )) re
SET is_impower = '0' WHERE ag.`user_id` = re.user_id;

ALTER TABLE tbh_in_order 
ADD is_pay CHAR(1) DEFAULT NULL COMMENT '奖励是否发放';

ALTER TABLE tbh_out_order
ADD is_pay CHAR(1) DEFAULT NULL COMMENT '奖励是否发放';

UPDATE tbh_in_order SET is_pay = '1' WHERE apply_datetime < '2018-09-01 00:00:00';

UPDATE tbh_out_order SET is_pay = '1' WHERE apply_datetime < '2018-09-01 00:00:00';

UPDATE tbh_out_order SET is_pay = '0' WHERE apply_datetime >= '2018-09-01 00:00:00';

UPDATE tbh_in_order SET is_pay = '0' WHERE apply_datetime >= '2018-09-01 00:00:00';

