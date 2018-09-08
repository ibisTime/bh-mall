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

/*********** tbh_tj_award、tbh_ch_award **************/
DROP TABLE IF EXISTS `tbh_tj_award`;
CREATE TABLE `tbh_tj_award` (
  `code` VARCHAR(32) NOT NULL,
  `product_code` VARCHAR(32) DEFAULT NULL COMMENT '产品编号',
  `level` VARCHAR(64) DEFAULT NULL COMMENT '等级',
  `value1` DECIMAL(10,3) DEFAULT '0.000' COMMENT '直接推荐',
  `value2` DECIMAL(10,3) DEFAULT '0.000' COMMENT '间接推荐奖励',
  `value3` DECIMAL(10,3) DEFAULT '0.000' COMMENT '次推荐奖励',
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_ch_award`;
CREATE TABLE `tbh_ch_award` (
  `code` VARCHAR(32) NOT NULL COMMENT '编号',
  `level` VARCHAR(32) DEFAULT NULL COMMENT '等级',
  `start_amount` BIGINT(20) DEFAULT '0' COMMENT '起始金额',
  `end_amount` BIGINT(20) DEFAULT '0' COMMENT '结束金额',
  `percent` DECIMAL(10,2) DEFAULT '0.00' COMMENT '奖励百分比',
  `updater` VARCHAR(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `remark` TEXT COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


