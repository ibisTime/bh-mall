/****************tbh_account  *****************/
UPDATE tbh_account SET TYPE = 'P' WHERE account_number = 'CD-CBH000020';

INSERT INTO `tbh_account` (`account_number`, `user_id`, `real_name`, `type`, `status`, `currency`, `amount`, `frozen_amount`, `create_datetime`, `last_order`)
VALUES('CD-CBH000022','SYS_USER_BH','公司','P','0','TG_CNY','0','0',NOW(),NULL);

INSERT INTO `tbh_account` (`account_number`, `user_id`, `real_name`, `type`, `status`);
 SELECT CONCAT('X',account_number) ,user_id ,real_name,TYPE,STATUS
FROM `tbh_account` WHERE TYPE = 'B';

UPDATE tbh_account SET currency = 'C_CNY' WHERE currency IS NULL;


/**************** tbh_agent_log  *****************/
ALTER TABLE tbh_agency_log RENAME TO tbh_agent_log;

ALTER TABLE tbh_agent_log  CHANGE  user_referee  referrer VARCHAR(32) DEFAULT NULL;

ALTER TABLE tbh_agent_log  
ADD real_name VARCHAR(255) DEFAULT NULL,
ADD wx_id VARCHAR(255) DEFAULT NULL,
ADD mobile VARCHAR(16) DEFAULT NULL,
ADD introducer  VARCHAR(32) DEFAULT NULL,
ADD pay_amount BIGINT (20) DEFAULT '0',
ADD province VARCHAR(255) DEFAULT NULL,
ADD city VARCHAR(255) DEFAULT NULL,
ADD AREA VARCHAR(255) DEFAULT NULL,
ADD address  VARCHAR(255) DEFAULT NULL,
ADD approve_name VARCHAR(255) DEFAULT NULL,
ADD updater  VARCHAR(32) DEFAULT NULL,
ADD update_datetime DATETIME,
ADD impower_datetime DATETIME;

UPDATE tbh_agent_log  LOG INNER JOIN  tbh_user u ON u.user_id = log.apply_user 
SET 
log.real_name = u.real_name, 
log.wx_id = u.wx_id, 
log.mobile = u.mobile,
log.introducer = u.introducer,
log.pay_amount = u.pay_amount,
log.province = u.province,
log.city = u.city,
log.AREA = u.AREA,
log.address = u.address,
log.impower_datetime = u.impower_datetime,
log.updater = u.updater,
log.update_datetime = u.update_datetime;

UPDATE tbh_agent_log  LOG INNER JOIN  tbh_user u ON u.user_id = log.approver
SET 
log.approve_name = u.real_name;


/*********** tbh_pro_code **************/
ALTER TABLE tbh_bar_code RENAME tbh_pro_code;


/*********** tbh_exchange_order **************/
ALTER TABLE tbh_change_product RENAME tbh_exchange_order;

ALTER TABLE tbh_exchange_order
CHANGE  product_specs_code specs_code VARCHAR(32) DEFAULT NULL,
CHANGE  product_specs_name specs_name VARCHAR(32) DEFAULT NULL;



/*********** tbh_charge **************/
ALTER TABLE tbh_charge
ADD high_user_id VARCHAR(32) DEFAULT NULL,
ADD team_name VARCHAR(255) DEFAULT NULL,
ADD level INT(11) DEFAULT NULL;

#待支付与待审核状态：保留当前信息
UPDATE tbh_charge c JOIN tbh_user u ON  c.`apply_user` = u.`user_id`
SET c.`high_user_id` = u.`high_user_id`, c.`team_name` = u.`team_name`, c.`level` = u.`level` WHERE c.`status`IN (1,4) ;

#审核通过与审核不通过：保留操作
UPDATE tbh_charge c JOIN tbh_user u ON  c.`apply_user` = u.`user_id`
SET c.`high_user_id` = c.pay_user,  c.`level` = u.`level`,c.`team_name`=u.team_name WHERE c.`status` IN (5,6) ;
/*********** tbh_inner_order **************/
ALTER TABLE tbh_inner_order
ADD specs_code VARCHAR(32) DEFAULT NULL,
ADD specs_name VARCHAR(255) DEFAULT NULL,
ADD approver VARCHAR(255) DEFAULT NULL,
ADD approve_datetime DATETIME,
ADD approve_note TEXT;


/*********** tbh_intro **************/
ALTER TABLE tbh_intro RENAME tbh_js_award;


/*********** tbh_specs_log **************/
ALTER TABLE tbh_product_log RENAME tbh_specs_log;

ALTER TABLE tbh_specs_log 
ADD product_name VARCHAR(256) DEFAULT NULL,
ADD specs_code VARCHAR(32) DEFAULT NULL,
ADD specs_name VARCHAR(256) DEFAULT NULL,
ADD stock_number INT(11) DEFAULT NULL;



/*********** tbh_specs **************/
ALTER TABLE tbh_product_specs RENAME tbh_specs;



/*********** tbh_specs **************/
ALTER TABLE tbh_product_specs_price RENAME tbh_agent_price;

ALTER TABLE tbh_agent_price
CHANGE product_specs_code specs_code VARCHAR(32) DEFAULT NULL,
CHANGE min_quantity start_number INT(11) DEFAULT 0;

/*********** tbh_agent_report **************/
ALTER TABLE tbh_report RENAME tbh_agent_report;

ALTER TABLE  tbh_agent_report CHANGE price_spread profit_award INT(11) DEFAULT 0;
 
ALTER TABLE  tbh_agent_report ADD send_amount INT(11) DEFAULT 0;
 
/*********** tbh_mini_code **************/
ALTER TABLE  tbh_security_trace RENAME tbh_mini_code;
 

/*********** tbh_mini_code **************/
ALTER TABLE  tbh_ware_house RENAME tbh_ware;

ALTER TABLE  tbh_ware
CHANGE product_specs_code specs_code  VARCHAR(32) DEFAULT NULL,
CHANGE product_specs_name specs_name VARCHAR(32) DEFAULT NULL;

/*********** tbh_mini_code **************/
ALTER TABLE    tbh_ware_house_log RENAME tbh_ware_log;

ALTER TABLE    tbh_ware_log
CHANGE	ware_house_code ware_code VARCHAR(32) DEFAULT NULL,
CHANGE product_specs_code specs_code  VARCHAR(32) DEFAULT NULL,
CHANGE product_specs_name specs_name VARCHAR(32) DEFAULT NULL;

/*********** tbh_mini_code **************/
ALTER TABLE   tbh_withdraw 
ADD high_user_id VARCHAR(32) DEFAULT NULL,
ADD is_company_pay VARCHAR(32) DEFAULT NULL;



/*********** tbh_agent_level **************/
DROP TABLE IF EXISTS `tbh_agent_level`;
CREATE TABLE `tbh_agent_level` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `level` bigint(32) DEFAULT NULL COMMENT '等级',
  `name` varchar(64) DEFAULT NULL COMMENT '等级名称',
  `red_amount` bigint(32) DEFAULT NULL COMMENT '红线金额',
  `amount` bigint(32) DEFAULT NULL COMMENT '授权单金额',
  `min_charge_amount` bigint(32) DEFAULT NULL COMMENT '本等级每次最低充值金额',
  `min_surplus` bigint(20) DEFAULT NULL COMMENT '本等级门槛最低余额',
  `is_send` char(1) DEFAULT NULL COMMENT '本等级授权单是否可以自发',
  `is_ware` char(1) DEFAULT NULL COMMENT '本等级是否启用云仓',
  `is_company_approve` char(1) DEFAULT NULL COMMENT '本等级升级是否公司审核',
  `re_number` int(11) DEFAULT NULL COMMENT '半门槛推荐人数',
  `is_reset` char(1) DEFAULT NULL COMMENT '本等级升级是否余额清零',
  `is_intent` char(1) DEFAULT NULL COMMENT '是否被意向（0否 1是）',
  `is_jsAward` char(1) DEFAULT NULL COMMENT '是否可被介绍（0否 1是）',
  `is_real_name` char(1) DEFAULT NULL COMMENT '是否实名（0否 1是）',
  `is_company_impower` char(1) DEFAULT NULL COMMENT '是否需要公司审核（0否 1是）',
  `min_charge` bigint(20) DEFAULT NULL COMMENT '门槛款',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tbh_agent_level (CODE,`level`,`name`,`red_amount`,`amount`,`min_charge_amount`,`min_surplus`,`is_send`,`is_ware`,`re_number`,
`is_reset`,`is_company_approve`,`is_intent`,`is_jsAward`,`is_real_name`,`is_company_impower`,`min_charge`,`updater`,`update_datetime`,`remark`
)SELECT * FROM 
(SELECT 
 CONCAT('AL20180000000000000',a.level),a.level,a.name,a.red_amount,a.amount,a.min_charge_amount,a.min_surplus,a.is_send,a.is_wareHouse,u.re_number,
 u.is_reset,u.is_company_approve,i.is_intent,i.is_intro,i.is_real_name,i.is_company_impower,i.min_charge,i.updater,i.update_datetime,i.remark
FROM tbh_agent a JOIN tbh_agent_impower i ON a.level = i.level
JOIN tbh_agent_upgrade u ON  U.level = i.level
ORDER BY A.LEVEL
) AS t;


UPDATE tbh_agent_level a JOIN tbh_user u ON u.login_name = a.updater
SET a.updater = u.user_id;


DROP TABLE IF EXISTS `tbh_agent`;


/*********** tsys_user **************/
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

INSERT INTO `tsys_user` (`user_id`, `role_code`, `real_name`, `photo`, `mobile`, 
`login_name`, `login_pwd`, `login_pwd_strength`, `create_datetime`, 
`status`, `updater`, `update_datetime`, `remark`, `system_code`) 
SELECT `user_id`, `role_code`, `real_name`, `photo`, `mobile`, 
`login_name`, `login_pwd`, `login_pwd_strength`, `create_datetime`, 
`status`, `updater`, `update_datetime`, `remark`, `system_code`
FROM tbh_user WHERE kind = 'P';

UPDATE tsys_user s   JOIN tbh_user u ON s.updater = u.login_name
SET s.updater = u.user_id;

/*********** tsys_user **************/
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

INSERT INTO `tbh_agent` (`user_id`, `from_user_id`, `mobile`, `wx_id`, `photo`, `nickname`, `trade_pwd`, `trade_pwd_strength`, `level`, `referrer`, `introducer`, `high_user_id`, `team_name`, `id_kind`, `id_no`, `id_hand`, `real_name`, `status`, `manager`, `union_id`, `h5_open_id`, `app_open_id`, `address`, `province`, `city`, `area`, `create_datetime`, `updater`, `update_datetime`, `approver`,  `approve_datetime`, `impower_datetime`, `last_agent_log`, `remark`)
SELECT `user_id`, `user_referee`, `mobile`, `wx_id`, `photo`, `nickname`, `trade_pwd`, `trade_pwd_strength`, `level`, `user_referee`, `introducer`, `high_user_id`, `team_name`, `id_kind`, `id_no`, `id_hand`, `real_name`, `status`, `manager`, `union_id`, `h5_open_id`, `app_open_id`, `address`, `province`, `city`, `area`, `create_datetime`, `updater`, `update_datetime`, `approver`, `approve_datetime`, `impower_datetime`, `last_agent_log`, `remark`
FROM tbh_user WHERE kind = 'B'; 

UPDATE tbh_agent a JOIN tbh_user u ON a.updater = u.login_name
SET a.updater = u.user_id;

UPDATE tbh_agent a JOIN tbh_user u ON a.approver = u.login_name
SET a.approver = u.user_id,a.approve_name = u.real_name;



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
  `to_user_name` VARCHAR(32) DEFAULT NULL,
  `level` INT(11) DEFAULT NULL,
  `amount` BIGINT(20) DEFAULT NULL COMMENT '总价',
  `status` VARCHAR(4) DEFAULT NULL COMMENT '状态',
  `apply_user` VARCHAR(32) DEFAULT NULL COMMENT '下单人',
  `real_name` VARCHAR(32) DEFAULT NULL,
  `team_name` VARCHAR(32) DEFAULT NULL,
  `team_leader` VARCHAR(32) DEFAULT NULL,
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

INSERT INTO tbh_in_order(`code`, `product_code`, `product_name`, `specs_code`, `specs_name`, `pic`, 
`quantity`, `price`, `to_user_id`, `to_user_name`, `level`, `amount`, `status`, `apply_user`, 
`real_name`, `team_name`, `team_leader`, `pay_type`, `pay_group`, `pay_amount`, `pay_datetime`, `pay_code`, `apply_datetime`, 
`apply_note`, `approver`, `approve_datetime`, `remark` 
)SELECT 
`code`, `product_code`, `product_name`, `product_specs_code`, `product_specs_name`, `pic`, 
`quantity`, `price`, `to_user`, `to_user_name`, `level`, `amount`, `status`, `apply_user`, 
`real_name`, `team_name`, `team_leader`, `pay_type`, `pay_group`, `pay_amount`, `pay_datetime`, `pay_code`, `apply_datetime`, 
`apply_note`, `approver`, `approve_datetime`, `remark` 
 FROM tbh_order WHERE kind = '2'

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
  `update_note` VARCHAR(32) DEFAULT NULL COMMENT '更新备注',
  `approver` VARCHAR(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` DATETIME DEFAULT NULL COMMENT '审核时间',
  `approve_note` VARCHAR(32) DEFAULT NULL COMMENT '支付组号',
  `remark` TEXT COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `tbh_out_order` (`code`, `level`, `is_ware_send`, `kind`, `product_code`, `product_name`, 
`specs_code`, `specs_name`, `pro_code`, `pic`, `quantity`, `price`, 
`to_user_id`, `to_user_name`, `amount`, `yunfei`, `status`, `pay_type`,
 `pay_group`, `pay_amount`, `pay_datetime`, `pay_code`, `apply_user`, `high_user_id`, `real_name`, `team_name`, 
 `team_leader`, `apply_datetime`, `apply_note`, `signer`, `mobile`, `province`, `city`, `area`, 
 `address`, `deliver`, `delive_datetime`, `logistics_code`, `logistics_company`, 
 `updater`, `update_datetime`, `update_note`, `approver`, `approve_datetime`, `approve_note`, `remark`) 
 
SELECT 
`code`, `level`, `is_company_send`, `kind`, `product_code`, 
`product_name`, `product_specs_code`, `product_specs_name`, `bar_code`, `pic`, 
`quantity`, `price`, `to_user`, `to_user_name`, `amount`, 
`yunfei`, `status`, `pay_type`, `pay_group`, `pay_amount`, 
`pay_datetime`, `pay_code`, `apply_user`, `to_user`, 
`real_name`, `team_name`, `team_leader`, `apply_datetime`, 
`apply_note`, `signer`, `mobile`, `province`, `city`, `area`, 
`address`, `deliver`, `delive_datetime`, logistics_code, `logistics_company`, 
`updater`, `update_datetime`, `update_note`, `approver`, `approve_datetime`, `approve_note`, `remark`
 FROM tbh_order WHERE kind != '2'

 #箱规格的为公司发货
 UPDATE tbh_out_order SET is_ware_send = '1' WHERE specs_code = 'PS201806301939383841880'; 
 #盒规格的为上级代理发货
 UPDATE tbh_out_order SET is_ware_send = '0' WHERE specs_code = 'PS201806291944580145174'; 

 
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
 FROM tbh_agency_log l JOIN tbh_user u ON l.apply_user = u.user_id

 UPDATE tbh_agent_log l, tbh_user u  SET l.approve_name = u.real_name WHERE u.user_id = l.approver AND u.approver LIKE 'U%' 
 
 