/****************tbh_account  *****************/
UPDATE tbh_account SET TYPE = 'P' WHERE account_number = 'CD-CBH000020'

INSERT INTO `tbh_account` (`account_number`, `user_id`, `real_name`, `type`, `status`, `currency`, `amount`, `frozen_amount`, `create_datetime`, `last_order`)
VALUES('CD-CBH000022','SYS_USER_BH','公司','P','0','TG_CNY','0','0',NOW(),NULL);

INSERT INTO `tbh_account` (`account_number`, `user_id`, `real_name`, `type`, `status`)
 SELECT CONCAT('X',account_number) ,user_id ,real_name,TYPE,STATUS
FROM `tbh_account` WHERE TYPE = 'B';

UPDATE tbh_account SET currency = 'C_CNY' WHERE currency IS NULL;


/**************** tbh_agent_log  *****************/
ALTER TABLE tbh_agency_log RENAME TO tbh_agent_log;

ALTER TABLE tbh_agent_log  CHANGE  user_referee  referrer VARCHAR(32) DEFAULT NULL

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
ADD LEVEL INT(11) DEFAULT NULL;



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

/*********** tbh_mini_code **************/

