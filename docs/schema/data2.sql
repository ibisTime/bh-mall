
DROP TABLE IF EXISTS `tbh_account`;

CREATE TABLE `tbh_account` (
  `account_number` varchar(32) NOT NULL DEFAULT '' COMMENT '账号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '真实姓名',
  `type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `status` varchar(2) DEFAULT NULL COMMENT '状态（正常/程序冻结/人工冻结）',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `amount` bigint(32) DEFAULT NULL COMMENT '余额',
  `frozen_amount` bigint(32) DEFAULT NULL COMMENT '冻结金额',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `last_order` varchar(32) DEFAULT NULL COMMENT '最近一次变动对应的流水编号',
  PRIMARY KEY (`account_number`),
  UNIQUE KEY `UQ_stuID` (`account_number`),
  UNIQUE KEY `UQ_ID` (`account_number`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_address`;

CREATE TABLE `tbh_address` (
  `code` varchar(32) NOT NULL COMMENT '收件编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `receiver` varchar(64) DEFAULT NULL COMMENT '收件人姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `province` varchar(64) DEFAULT NULL COMMENT '省份',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `area` varchar(64) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `is_default` char(1) DEFAULT NULL COMMENT '是否默认地址',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_after_sale`;

CREATE TABLE `tbh_after_sale` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '关联订单',
  `sale_type` varchar(4) DEFAULT NULL COMMENT '售后类型',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `apply_note` text COMMENT '申请备注',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` text COMMENT '审核备注',
  `deliver` varchar(32) DEFAULT NULL COMMENT '发货人',
  `delive_datetime` datetime DEFAULT NULL COMMENT '发货时间',
  `logistics_code` varchar(64) DEFAULT NULL COMMENT '物流编号',
  `logistics_company` varchar(255) DEFAULT NULL COMMENT '物流公司',
  `pdf` varchar(255) DEFAULT NULL COMMENT '物流单',
  `signer` varchar(64) DEFAULT NULL COMMENT '收货人',
  `mobile` varchar(32) DEFAULT NULL COMMENT '收货人电话',
  `province` varchar(64) DEFAULT NULL COMMENT '省',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `area` varchar(64) DEFAULT NULL COMMENT '区',
  `address` text COMMENT '地址',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_agency_log`;

CREATE TABLE `tbh_agency_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `to_user_id` varchar(32) DEFAULT NULL COMMENT '意向归属人',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `level` int(11) DEFAULT NULL COMMENT '当前等级',
  `apply_level` int(11) DEFAULT NULL COMMENT '申请等级',
  `high_user_id` varchar(32) DEFAULT NULL COMMENT '上级',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `team_name` varchar(255) DEFAULT NULL COMMENT '团队名称',
  `pay_pdf` varchar(255) DEFAULT NULL COMMENT '打款截图',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_agent`;

CREATE TABLE `tbh_agent` (
  `level` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '等级名称',
  `amount` bigint(32) DEFAULT NULL COMMENT '首次授权发送金额',
  `red_amount` bigint(32) DEFAULT NULL COMMENT '红线金额',
  `min_charge_amount` bigint(32) DEFAULT NULL COMMENT '本等级每次最低充值金额',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`level`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_agent_impower`;

CREATE TABLE `tbh_agent_impower` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `level` bigint(32) DEFAULT NULL COMMENT '代理等级',
  `is_intent` char(1) DEFAULT NULL COMMENT '是否被意向（0否 1是）',
  `is_intro` char(1) DEFAULT NULL COMMENT '是否可被介绍（0否 1是）',
  `is_real_name` char(1) DEFAULT NULL COMMENT '是否实名（0否 1是）',
  `is_company_impower` char(1) DEFAULT NULL COMMENT '是否需要公司审核（0否 1是）',
  `impower_amount` bigint(20) DEFAULT NULL COMMENT '授权单金额',
  `min_charge` bigint(20) DEFAULT NULL COMMENT '充值门槛',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_agent_upgrade`;

CREATE TABLE `tbh_agent_upgrade` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `level` varchar(32) DEFAULT NULL COMMENT '代理等级',
  `is_company_approve` char(1) DEFAULT NULL COMMENT '本等级升级是否公司审核',
  `re_number` int(11) DEFAULT NULL COMMENT '半门槛推荐人数',
  `is_reset` char(1) DEFAULT NULL COMMENT '本等级升级是否余额清零',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_award`;

CREATE TABLE `tbh_award` (
  `code` varchar(32) NOT NULL,
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `level` varchar(64) DEFAULT NULL COMMENT '等级',
  `value1` decimal(10,3) DEFAULT '0.000' COMMENT '直接推荐/出货奖励',
  `value2` decimal(10,3) DEFAULT '0.000' COMMENT '间接推荐奖励',
  `value3` decimal(10,3) DEFAULT '0.000' COMMENT '次推荐奖励',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_bankcard`;

CREATE TABLE `tbh_bankcard` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `bankcard_number` varchar(64) DEFAULT NULL COMMENT '银行卡编号',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行行别',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `subbranch` varchar(255) DEFAULT NULL COMMENT '开户支行',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '银行卡绑定手机号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `amount` bigint(32) DEFAULT NULL COMMENT '余额',
  `frozen_amount` bigint(32) DEFAULT NULL COMMENT '冻结金额',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `last_order` varchar(32) DEFAULT NULL COMMENT '最近一次变动对应的流水编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_cart`;

CREATE TABLE `tbh_cart` (
  `code` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_specs_code` varchar(32) DEFAULT NULL COMMENT '产品规格编号',
  `price` bigint(20) DEFAULT NULL COMMENT '单价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_change_product`;

CREATE TABLE `tbh_change_product` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `product_specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `product_specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `quantity` int(11) DEFAULT NULL COMMENT '置换数量',
  `price` bigint(20) DEFAULT NULL COMMENT '规格价格',
  `amount` bigint(20) DEFAULT NULL COMMENT '置换总价',
  `change_product_code` varchar(32) DEFAULT NULL COMMENT '置换产品编号',
  `change_product_name` varchar(255) DEFAULT NULL COMMENT '置换产品名称',
  `change_specs_code` varchar(32) DEFAULT NULL COMMENT '置换产品规格编号',
  `change_specs_name` varchar(255) DEFAULT NULL COMMENT '置换规格名称',
  `change_price` bigint(20) DEFAULT NULL COMMENT '换货价',
  `can_change_quantity` int(11) DEFAULT NULL COMMENT '可置换数量',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `real_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `level` int(4) DEFAULT NULL COMMENT '申请人等级',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `apply_note` text COMMENT '申请备注',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` text COMMENT '审核备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_channel_bank`;

CREATE TABLE `tbh_channel_bank` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行编号',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `channel_type` varchar(4) DEFAULT NULL COMMENT '渠道类型',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（启用/不启用）',
  `channel_bank` varchar(32) DEFAULT NULL COMMENT '渠道给银行的代号',
  `max_order` bigint(32) DEFAULT NULL COMMENT '笔数限制',
  `order_amount` bigint(32) DEFAULT NULL COMMENT '单笔限额',
  `day_amount` bigint(32) DEFAULT NULL COMMENT '每日限额',
  `month_amount` bigint(32) DEFAULT NULL COMMENT '每月限额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `tbh_channel_bank` */

insert  into `tbh_channel_bank`(`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) values (1,'ICBC','中国工商银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(2,'ABC','中国农业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(3,'CCB','中国建设银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(4,'BOC','中国银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(5,'BCM','中国交通银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(6,'CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(7,'CITIC','中信银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(8,'CEB','中国光大银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(9,'PAB','平安银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(10,'PSBC','中国邮政储蓄银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(11,'SHB','上海银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(12,'SPDB','浦东发展银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL),(13,'CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `tbh_charge` */

DROP TABLE IF EXISTS `tbh_charge`;

CREATE TABLE `tbh_charge` (
  `code` varchar(32) NOT NULL COMMENT '针对编号',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '流水分组组号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '针对账号',
  `amount` bigint(20) DEFAULT NULL COMMENT '充值金额',
  `account_name` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '针对户名',
  `type` varchar(4) DEFAULT NULL COMMENT '账户类型',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `biz_note` varchar(255) DEFAULT NULL COMMENT '流水说明',
  `pay_card_info` varchar(255) DEFAULT NULL COMMENT '支付渠道账号信息',
  `pay_card_no` varchar(32) DEFAULT NULL COMMENT '支付渠道账号',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `pay_user` varchar(32) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付渠道说明',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `remark` text COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`) COMMENT '充值订单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_cnavigate`;

CREATE TABLE `tbh_cnavigate` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `url` varchar(255) DEFAULT NULL COMMENT '访问Url',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(1 显示 0 不显示)',
  `location` varchar(32) DEFAULT NULL COMMENT '位置',
  `order_no` int(11) DEFAULT NULL COMMENT '相对位置编号',
  `belong` varchar(32) DEFAULT NULL COMMENT '属于(1 全局 2 地方默认 3 地方默认编号)',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父编号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `content_type` varchar(32) DEFAULT NULL COMMENT '内容源类型',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_company_channel`;

CREATE TABLE `tbh_company_channel` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `company_name` varchar(32) DEFAULT NULL COMMENT '公司名称',
  `channel_type` varchar(4) DEFAULT NULL COMMENT '渠道类型',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（启用/不启用）',
  `channel_company` varchar(32) DEFAULT NULL COMMENT '渠道给公司的代号',
  `private_key1` text COMMENT '秘钥1',
  `private_key2` text COMMENT '私钥2',
  `private_key3` text COMMENT '私钥3',
  `private_key4` text COMMENT '私钥4',
  `private_key5` text COMMENT '私钥5',
  `page_url` varchar(255) DEFAULT NULL COMMENT '界面正确回调地址',
  `error_url` varchar(255) DEFAULT NULL COMMENT '界面错误回调地址',
  `back_url` varchar(255) DEFAULT NULL COMMENT '服务器回调地址',
  `fee` bigint(32) DEFAULT NULL COMMENT '手续费',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tbh_company_channel` */

insert  into `tbh_company_channel`(`id`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`company_code`,`system_code`) values (1,'户外电商','35','1','1481010032','r2jdDFSdiikklwlllejlwjio3232451n','wx9c709bf30e60c07b','',NULL,NULL,'https://www.baidu.com','https://www.baidu.com','https://www.baidu.com',NULL,NULL,'CD-CBH000020','CD-CBH000020'),(2,'微信小程序','1','1','1400666002','r2jgDFSdiikklwlllejlwjio3242342n','wx0bf01798e93cb2f9',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CD-CBH000020','CD-CBH000020');

/*Table structure for table `tbh_inner_order` */

DROP TABLE IF EXISTS `tbh_inner_order`;

CREATE TABLE `tbh_inner_order` (
  `code` varchar(32) NOT NULL,
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(64) DEFAULT NULL COMMENT '产品名称',
  `pic` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `price` bigint(20) DEFAULT NULL COMMENT '单价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单代理',
  `level` int(11) DEFAULT NULL COMMENT '下单代理等级',
  `team_name` varchar(64) DEFAULT NULL COMMENT '团队名称',
  `apply_note` text COMMENT '下单备注',
  `apply_datetime` datetime DEFAULT NULL COMMENT '下单时间',
  `amount` bigint(20) DEFAULT NULL COMMENT '下单订单金额',
  `yunfei` bigint(20) DEFAULT NULL COMMENT '运费',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `pay_group` varchar(255) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(255) DEFAULT NULL COMMENT '支付编号',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '支付金额',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `signer` varchar(64) DEFAULT NULL COMMENT '接收人名称',
  `mobile` varchar(32) DEFAULT NULL COMMENT '接收人手机号',
  `province` varchar(64) DEFAULT NULL COMMENT '省份',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `area` varchar(64) DEFAULT NULL COMMENT '区',
  `address` text COMMENT '地址',
  `deliver` varchar(64) DEFAULT NULL COMMENT '发货人',
  `delive_datetime` datetime DEFAULT NULL COMMENT '发货时间',
  `logistics_code` varchar(255) DEFAULT NULL COMMENT '物流单号',
  `logistics_company` varchar(255) DEFAULT NULL COMMENT '物流公司',
  `pdf` text COMMENT '物流单',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_inner_product`;

CREATE TABLE `tbh_inner_product` (
  `code` varchar(32) NOT NULL,
  `is_free` varchar(4) DEFAULT NULL COMMENT '是否包邮',
  `name` varchar(64) DEFAULT NULL COMMENT '产品名称',
  `pic` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `adv_pic` varchar(255) DEFAULT NULL COMMENT '广告图',
  `slogan` text COMMENT '广告语',
  `price` bigint(20) DEFAULT NULL COMMENT '价格',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `order_no` int(11) DEFAULT NULL COMMENT '排序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_intro`;

CREATE TABLE `tbh_intro` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `level` varchar(4) DEFAULT NULL COMMENT '类型',
  `percent` decimal(5,2) DEFAULT NULL COMMENT '标题',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tbh_jour`;

CREATE TABLE `tbh_jour` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '参考订单号',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '分组组号',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道类型',
  `channel_order` varchar(32) DEFAULT NULL COMMENT '支付渠道单号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账号',
  `trans_amount` bigint(32) DEFAULT NULL COMMENT '变动金额',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '真实姓名',
  `type` varchar(4) DEFAULT NULL COMMENT '账户类型',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `biz_note` varchar(255) DEFAULT NULL COMMENT '业务类型',
  `pre_amount` bigint(32) DEFAULT NULL COMMENT '变动前金额',
  `post_amount` bigint(32) DEFAULT NULL COMMENT '变动后金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_material`;

CREATE TABLE `tbh_material` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `order_no` int(11) DEFAULT NULL COMMENT '排序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0未发布 1发布）',
  `level` varchar(32) DEFAULT NULL COMMENT '查看等级',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_order`;

CREATE TABLE `tbh_order` (
  `code` varchar(32) NOT NULL,
  `is_company_send` varchar(4) DEFAULT NULL COMMENT '是否云仓发货',
  `is_send_home` varchar(45) DEFAULT NULL COMMENT '是否送货到家',
  `kind` varchar(4) DEFAULT NULL COMMENT '编号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '是否云仓发货',
  `product_name` varchar(255) DEFAULT NULL COMMENT '分类',
  `product_specs_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_specs_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `pic` varbinary(255) DEFAULT NULL COMMENT '规格编号',
  `quantity` int(11) DEFAULT NULL COMMENT '规格名称',
  `price` bigint(20) DEFAULT NULL COMMENT '图片',
  `to_user` varchar(32) DEFAULT NULL COMMENT '数量',
  `amount` bigint(20) DEFAULT NULL COMMENT '单价',
  `yunfei` bigint(20) DEFAULT '0' COMMENT '向谁提货',
  `status` varchar(4) DEFAULT NULL COMMENT '下单订单金额',
  `pay_type` varchar(64) DEFAULT NULL COMMENT '运费',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '状态',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '支付渠道',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付金额',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '支付时间',
  `real_name` varchar(255) DEFAULT NULL COMMENT '下单人姓名',
  `apply_note` text COMMENT '下单备注',
  `apply_datetime` date DEFAULT NULL COMMENT '下单时间',
  `signer` varchar(255) DEFAULT NULL COMMENT '收件人姓名',
  `mobile` varchar(64) DEFAULT NULL COMMENT '收件人电话',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `address` text COMMENT '收货地址',
  `deliver` varchar(32) DEFAULT NULL COMMENT '发货人',
  `delive_datetime` datetime DEFAULT NULL COMMENT '发货时间',
  `logistics_code` varchar(64) DEFAULT NULL COMMENT '物流单号',
  `logistics_company` varchar(255) DEFAULT NULL COMMENT '物流公司',
  `pdf` varchar(255) DEFAULT NULL COMMENT '物流单',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `update_note` text COMMENT '更新备注',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` text COMMENT '审核备注',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_product`;

CREATE TABLE `tbh_product` (
  `is_free` varchar(4) DEFAULT NULL COMMENT '是否包邮',
  `code` varchar(32) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '产品名称',
  `ad_price` bigint(20) DEFAULT NULL COMMENT '建议微信价',
  `price` bigint(20) DEFAULT NULL COMMENT '市场价',
  `vir_number` int(11) DEFAULT NULL COMMENT '虚拟数量',
  `real_number` int(11) DEFAULT NULL COMMENT '实际数量',
  `order_no` int(11) DEFAULT NULL COMMENT '排序',
  `pic` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `adv_pic` varchar(255) DEFAULT NULL COMMENT '广告图',
  `slogan` text COMMENT '广告语',
  `is_total` char(1) DEFAULT NULL COMMENT '是否计入出货',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_product_log`;

CREATE TABLE `tbh_product_log` (
  `code` varchar(32) NOT NULL,
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `tran_count` int(11) DEFAULT NULL COMMENT '变动数量',
  `pre_count` int(11) DEFAULT NULL COMMENT '变动前数量',
  `post_count` int(11) DEFAULT NULL COMMENT '变动后数量',
  `updater` varchar(32) DEFAULT NULL COMMENT '变动人',
  `update_datetime` datetime DEFAULT NULL COMMENT '变动时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_product_specs`;

CREATE TABLE `tbh_product_specs` (
  `code` varchar(32) NOT NULL,
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `name` varchar(64) DEFAULT NULL COMMENT '规格名称',
  `number` int(11) DEFAULT NULL COMMENT '规格包含数量',
  `weight` int(11) DEFAULT NULL COMMENT '重量',
  `is_impower_order` char(1) DEFAULT NULL COMMENT '是否允许授权单下单',
  `is_upgrade_order` char(1) DEFAULT NULL COMMENT '是否允许升级单下单',
  `is_normal_order` char(1) DEFAULT NULL COMMENT '是否允许普通单下单',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_product_specs_price`;

CREATE TABLE `tbh_product_specs_price` (
  `change_price` bigint(20) DEFAULT NULL COMMENT '换货价',
  `code` varchar(32) NOT NULL,
  `product_specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `level` int(11) DEFAULT '0' COMMENT '等级',
  `price` bigint(20) DEFAULT '0' COMMENT '价格',
  `min_number` int(11) DEFAULT '0' COMMENT '本等级最低云仓发货数',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_user`;

CREATE TABLE `tbh_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登陆名',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `wx_id` varchar(32) DEFAULT NULL COMMENT '微信号',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `login_pwd` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `trade_pwd` varchar(45) DEFAULT NULL COMMENT '交易密码',
  `trade_pwd_strength` varchar(45) DEFAULT NULL COMMENT '交易密码强度',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登陆密码强度',
  `kind` varchar(4) DEFAULT NULL COMMENT '用户类型',
  `level` int(32) DEFAULT NULL COMMENT '用户等级',
  `apply_level` varchar(32) DEFAULT NULL COMMENT '申请等级',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `introducer` varchar(32) DEFAULT NULL COMMENT '介绍人',
  `high_user_id` varchar(32) DEFAULT NULL COMMENT '上级用户',
  `team_name` varchar(32) DEFAULT NULL COMMENT '团队名称',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `id_front` text COMMENT '身份证反面',
  `id_behind` text COMMENT '身份证正面',
  `id_hand` text COMMENT '手持身份证',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `source` varchar(32) DEFAULT NULL COMMENT '来源',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `manager` varchar(32) DEFAULT NULL COMMENT '关联管理员',
  `red_amount` int(32) DEFAULT '0' COMMENT '红线',
  `union_id` varchar(255) DEFAULT NULL COMMENT '联合编号',
  `h5_open_id` varchar(255) DEFAULT NULL COMMENT '公众号开放编号',
  `app_open_id` varchar(255) DEFAULT NULL COMMENT 'app开放编号',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `last_agent_log` varchar(32) DEFAULT NULL COMMENT '最后一条代理轨迹记录',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `pay_amount` bigint(20) DEFAULT '0' COMMENT '打款金额',
  `pay_pdf` varchar(255) DEFAULT NULL COMMENT '打款截图',
  `impower_datetime` datetime DEFAULT NULL COMMENT '授权书时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert  into `tbh_user`(`user_id`,`login_name`,`mobile`,`wx_id`,`photo`,`nickname`,`login_pwd`,`trade_pwd`,`trade_pwd_strength`,`login_pwd_strength`,`kind`,`level`,`apply_level`,`user_referee`,`introducer`,`high_user_id`,`team_name`,`id_kind`,`id_no`,`id_front`,`id_behind`,`id_hand`,`real_name`,`role_code`,`status`,`source`,`apply_datetime`,`manager`,`red_amount`,`union_id`,`h5_open_id`,`app_open_id`,`province`,`city`,`area`,`address`,`create_datetime`,`updater`,`update_datetime`,`approver`,`approve_datetime`,`last_agent_log`,`remark`,`company_code`,`system_code`,`pay_amount`,`pay_pdf`,`impower_datetime`) values ('U201806192109249296442',NULL,'13003660091','Liu455472140','http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIM4kvsEOxqK7sUkl11GdMJlvUgde8QdxqMofujQoJ05q1qK9DALDXvWyPLjicbzVOL5p7vXUUeFWQ/132','Mr刘','21218cca77804d2ba1922c33e0151105',NULL,NULL,'1','B',2,'1',NULL,'','',NULL,'1','11111','f8952160-cd31-4aae-8f5d-8245a80bf174.png','2cbda505-6ae3-46df-af45-b041de247f52.png','b0c04d13-7855-4d89-a2de-12352c46be65.png','刘亚',NULL,'7',NULL,'2018-06-19 21:09:50','USYS201800000000002',NULL,NULL,'oMSU70i693H_VZcYeaH0WeOY8Vko',NULL,'安徽省','安庆市','枞阳县','测试','2018-06-19 21:09:24',NULL,NULL,'admin','2018-06-19 21:34:09','AL201806192136561516648','','CD-CBH000020','CD-CBH000020',5300000,NULL,'2018-06-19 21:10:50'),('U201806192112536033004',NULL,'18131582529','1234','http://thirdwx.qlogo.cn/mmopen/vi_32/GhlTicZz0xTm60SN2n0qZfyuk8IPEgC06yibiaBytY1NHm7sReroYt9hxzpL356JtyBbcuvo8PtQEnibaxS4aWr1lA/132','晚来天欲雪','21218cca77804d2ba1922c33e0151105',NULL,NULL,'1','B',3,'2','U201806192109249296442','','U201806192113112325007',NULL,'1','1234','8a017cd1-4a0a-4dc4-8b29-ae47043929fe.png','4846f2f2-7627-4d1d-b6bc-8c53d5eca701.png','d37b211b-f5a8-4489-b580-b97567394ba8.png','宁亚超',NULL,'14','微信推荐',NULL,NULL,NULL,NULL,'oMSU70qpa-Cx7c-uFVcxo2vcmzwA',NULL,'安徽省','安庆市','枞阳县','测试','2018-06-19 21:12:53',NULL,NULL,'U201806192113112325007','2018-06-20 10:49:21','AL201806201049219029273',NULL,'CD-CBH000020','CD-CBH000020',0,NULL,'2018-06-19 21:14:58'),('U201806192113112325007',NULL,'18870421309','1','http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKKcCEON8oLqaueGpQpYWSyKYicHVM8TicSwDppeia01Y9J8DX61IeT9Wuj8ic30HKtzIuqxqsLIicDkew/132','Clare29-','21218cca77804d2ba1922c33e0151105',NULL,NULL,'1','B',2,'2','U201806192109249296442','',NULL,NULL,'1','1','58acd78a-189b-4fea-93e2-f79e1e58c62e.JPEG','7c0411d1-801f-4cb0-a513-545000285c44.JPEG','a49215f3-0aee-4e61-9e04-66ceedca45e4.JPEG','蔡轩',NULL,'7','微信推荐',NULL,NULL,NULL,NULL,'oMSU70izwx4tEwGoEzk6HJ7lqCm0',NULL,'安徽省','安庆市','枞阳县','1','2018-06-19 21:13:11',NULL,NULL,'admin',NULL,'AL201806192114545496670','','CD-CBH000020','CD-CBH000020',NULL,NULL,'2018-06-19 21:14:54'),('U201806192117109057199',NULL,'13036600923','1111','http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLVTSIGgVGv9wdCXw82icElAb47A9yMSdiaquMqSQamTrqKP7Kmib7m561zrFvhIGQIICIZhGsicCeb1w/132','俊杰','21218cca77804d2ba1922c33e0151105',NULL,NULL,'1','B',2,'2','U201806192112536033004','',NULL,NULL,'1','111','731c5875-7d29-4b27-a9fd-41893a7a45d0.png','69f34990-8344-4a24-8412-efab36c238d8.png','7042a00f-d331-48ae-b3f7-f3691c99d323.png','张扬',NULL,'7','微信推荐',NULL,NULL,NULL,NULL,'oMSU70jWuXc_kpEvItnbj1vLhpcU',NULL,'安徽省','安庆市','枞阳县','测','2018-06-19 21:17:10',NULL,NULL,'admin',NULL,'AL201806192119410983616','','CD-CBH000020','CD-CBH000020',NULL,NULL,'2018-06-19 21:19:41'),('U201806192230309253504',NULL,NULL,NULL,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLN5YBFXdBmj1w2jVGfVDnl5bwNJrKq4eSvdJxSobcSFkTm3N1830qZ3ibOuvcqjQibFricTiccoz9Mpw/132','Clare29-','21218cca77804d2ba1922c33e0151105',NULL,NULL,'1','C',6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'oVlQZ0c3C1KbSv0KrVzAo5CfGKzc',NULL,NULL,NULL,NULL,NULL,'2018-06-19 22:30:30',NULL,NULL,NULL,NULL,NULL,NULL,'CD-CBH000020','CD-CBH000020',0,NULL,NULL),('U201806192241193084232',NULL,NULL,NULL,'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo3oe4PJQHFDKM7PukxaLC4nrhcF8Qic6cbRDJLdEHFtg2vvUMN7ZwEWvEeF8px7AeJibHJO4PUZefg/132','夏斌','21218cca77804d2ba1922c33e0151105',NULL,NULL,'1','B',0,NULL,NULL,NULL,'U201806192109249296442',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'19',NULL,NULL,NULL,NULL,NULL,'oMSU70kCBIcONf7vS-o-tfCF_v1o',NULL,NULL,NULL,NULL,NULL,'2018-06-19 22:41:19',NULL,NULL,NULL,NULL,NULL,NULL,'CD-CBH000020','CD-CBH000020',0,NULL,NULL),('U201806221231215685590',NULL,'13958092437','mybao858','http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKaSsy1FAkU3qDKMa5BV2niaxVh1KLXHiaAdJJNfVzaOoJVMba8xMgm8ScUTxuSzb8K2TpV8LW7LXag/132','考拉','21218cca77804d2ba1922c33e0151105',NULL,NULL,'1','B',5,'5',NULL,'','',NULL,'1','330281198908118212','','','','宓永宝',NULL,'7',NULL,'2018-06-22 12:32:27','USYS201800000000002',NULL,NULL,'oMSU70vlxT72iZnaMtY9kHc7TXyw',NULL,'安徽省','安庆市','枞阳县','梦想小镇','2018-06-22 12:31:21',NULL,NULL,'admin','2018-06-22 12:36:50','AL201806221238127333212','','CD-CBH000020','CD-CBH000020',0,NULL,'2018-06-22 12:38:12'),('USYS201800000000002','admin',NULL,'',NULL,NULL,'21218cca77804d2ba1922c33e0151105','1',NULL,'1','P',NULL,NULL,NULL,NULL,'','一一一',NULL,NULL,NULL,NULL,NULL,'admin','RO201800000000000001','0',NULL,'2018-04-08 18:21:25','USYS201800000000002',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'admin','2018-04-03 19:39:47',NULL,NULL,NULL,NULL,'CD-CBH000020','CD-CBH000020',NULL,NULL,NULL);


DROP TABLE IF EXISTS `tbh_ware_house`;

CREATE TABLE `tbh_ware_house` (
  `code` varchar(32) NOT NULL,
  `type` varchar(4) DEFAULT NULL COMMENT '类型（B/P）',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `currency` varchar(64) DEFAULT NULL COMMENT '币种',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `product_specs_code` varchar(255) DEFAULT NULL COMMENT '规格编号',
  `product_specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `price` bigint(20) DEFAULT NULL COMMENT '价格',
  `amount` bigint(20) DEFAULT NULL COMMENT '总价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `last_change_code` varchar(32) DEFAULT NULL COMMENT '最后一条流水',
  `system_code` varchar(32) DEFAULT NULL,
  `company_code` varbinary(32) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL COMMENT '代理名称',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_ware_house_log`;

CREATE TABLE `tbh_ware_house_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '关联编号',
  `ware_house_code` varchar(32) DEFAULT NULL COMMENT '所属云仓编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型(置换，购买/出货)',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `product_specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `product_specs_name` varbinary(255) DEFAULT NULL COMMENT '规格名称',
  `tran_number` int(11) DEFAULT NULL COMMENT '价格',
  `before_number` int(11) DEFAULT NULL COMMENT '变动数量',
  `after_number` int(11) DEFAULT NULL COMMENT '变动前数量',
  `price` bigint(20) DEFAULT NULL COMMENT '变动后数量',
  `amount` bigint(20) DEFAULT NULL COMMENT '总价',
  `change_number` int(11) DEFAULT NULL COMMENT '可置换数量',
  `change_price` bigint(20) DEFAULT NULL COMMENT '置换价格',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `real_name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `level` int(4) DEFAULT NULL COMMENT '用户等级',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `apply_note` text COMMENT '申请备注',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` text COMMENT '审核备注',
  `biz_type` varbinary(64) DEFAULT NULL COMMENT '业务类型',
  `biz_note` text COMMENT '业务说明',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_withdraw`;

CREATE TABLE `tbh_withdraw` (
  `code` varchar(32) NOT NULL COMMENT '针对编号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '针对账号',
  `account_name` varchar(32) DEFAULT NULL COMMENT '针对户名',
  `type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `amount` bigint(20) DEFAULT NULL COMMENT '取现金额',
  `fee` bigint(20) DEFAULT NULL COMMENT '手续费',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `channel_bank` varchar(32) DEFAULT NULL COMMENT '渠道银行代号',
  `channel_order` varchar(32) DEFAULT NULL COMMENT '支付渠道编号',
  `pay_card_info` varchar(255) DEFAULT NULL COMMENT '支付渠道账号信息',
  `pay_card_no` varchar(32) DEFAULT NULL COMMENT '支付渠道账号',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` varchar(32) DEFAULT NULL COMMENT '审批时间',
  `pay_user` varchar(32) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(32) DEFAULT NULL COMMENT '支付回录说明',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付渠道订单编号',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付回录时间',
  PRIMARY KEY (`code`) COMMENT '取现订单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `thf_ware_house_specs`;

CREATE TABLE `thf_ware_house_specs` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `ware_house_code` varchar(32) DEFAULT NULL COMMENT '关联用户编号',
  `product_specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `price` bigint(20) DEFAULT NULL COMMENT '单价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `amount` bigint(20) DEFAULT NULL COMMENT '总价',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tsys_config`;

CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(255) DEFAULT NULL COMMENT 'key',
  `cvalue` text COMMENT 'value',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

/*Data for the table `tsys_config` */

insert  into `tsys_config`(`id`,`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) values (1,'qiniu','qiniu_access_key','07KR5rNezHcXebD-GalrPw0npsAODOMVxygvdFFt','admin','2018-03-26 17:07:42','七牛云key1','CD-CBH000020','CD-CBH000020'),(2,'qiniu','qiniu_secret_key','nsMbXOfEtk3SvQ3GFHbKMozJua3jbTiGPIIwu4tq','admin','2018-03-26 17:07:42','七牛云key1','CD-CBH000020','CD-CBH000020'),(3,'qiniu','qiniu_bucket','zwzj','admin','2018-02-08 17:09:59','qiniu_bucket','CD-CBH000020','CD-CBH000020'),(4,'qiniu','qiniu_domain','otoieuivb.bkt.clouddn.com','admin','2018-03-26 17:07:42','访问域名','CD-CBH000020','CD-CBH000020'),(5,'sys_txt','telephone','0571-88888888','admin','2018-04-28 11:10:30','服务热线','CD-CBH000020','CD-CBH000020'),(6,'sys_txt','about_us','<p><img src=\"http://otoieuivb.bkt.clouddn.com/IMG_2130_1524907296779.JPG\" style=\"max-width:100%\"></p><p>关于我们</p><p><br></p>','admin','2018-04-28 17:21:05','关于我们','CD-CBH000020','CD-CBH000020'),(7,'sys_txt','service_time','<p><span style=\"font-size: xx-large;\">9:00-17:00</span></p>','admin','2018-06-04 16:09:32','服务时间','CD-CBH000020','CD-CBH000020'),(8,'yunfei','北京市','10','admin','2018-03-27 17:10:53','北京市','CD-CBH000020','CD-CBH000020'),(9,'yunfei','上海市','15','admin','2018-03-27 17:13:22','上海市','CD-CBH000020','CD-CBH000020'),(17,'yunfei','天津市','10','admin','2018-03-27 17:17:38','天津市','CD-CBH000020','CD-CBH000020'),(18,'yunfei','重庆市','15','admin','2018-03-27 17:17:38','重庆市','CD-CBH000020','CD-CBH000020'),(19,'yunfei','河北省','10','admin','2018-03-27 17:29:12','河北省','CD-CBH000020','CD-CBH000020'),(20,'yunfei','陕西省','10','admin','2018-03-27 17:29:12','陕西省','CD-CBH000020','CD-CBH000020'),(21,'yunfei','内蒙古自治区','10','admin','2018-03-27 17:29:12','内蒙古自治区','CD-CBH000020','CD-CBH000020'),(22,'yunfei','黑龙江省','10','admin','2018-03-27 17:29:12','黑龙江省','CD-CBH000020','CD-CBH000020'),(23,'yunfei','吉林省','10','admin','2018-03-27 17:29:12','吉林省','CD-CBH000020','CD-CBH000020'),(24,'yunfei','辽宁省','10','admin','2018-03-27 17:29:12','辽宁省','CD-CBH000020','CD-CBH000020'),(25,'yunfei','山西省','10','admin','2018-03-27 17:29:12','山西省','CD-CBH000020','CD-CBH000020'),(26,'yunfei','甘肃省','10','admin','2018-03-27 17:29:12','甘肃省','CD-CBH000020','CD-CBH000020'),(27,'yunfei','青海省','10','admin','2018-03-27 17:29:12','青海省','CD-CBH000020','CD-CBH000020'),(28,'yunfei','新疆维吾尔自治区','10','admin','2018-03-27 17:29:12','新疆维吾尔自治区','CD-CBH000020','CD-CBH000020'),(29,'yunfei','宁夏回族自治区','10','admin','2018-03-27 17:29:12','宁夏回族自治区','CD-CBH000020','CD-CBH000020'),(30,'yunfei','山东省','10','admin','2018-03-27 17:29:12','山东省','CD-CBH000020','CD-CBH000020'),(31,'yunfei','河南省','10','admin','2018-03-27 17:29:12','河南省','CD-CBH000020','CD-CBH000020'),(32,'yunfei','江苏省','10','admin','2018-03-27 17:29:12','江苏省','CD-CBH000020','CD-CBH000020'),(33,'yunfei','浙江省','10','admin','2018-03-27 17:29:12','浙江省','CD-CBH000020','CD-CBH000020'),(34,'yunfei','安徽省','10','admin','2018-03-27 17:29:12','安徽省','CD-CBH000020','CD-CBH000020'),(35,'yunfei','江西省','10','admin','2018-03-27 17:29:12','江西省','CD-CBH000020','CD-CBH000020'),(36,'yunfei','福建省','10','admin','2018-03-27 17:29:12','福建省','CD-CBH000020','CD-CBH000020'),(37,'yunfei','台湾省','10','admin','2018-03-27 17:29:12','台湾省','CD-CBH000020','CD-CBH000020'),(38,'yunfei','湖北省','10','admin','2018-03-27 17:29:12','湖北省','CD-CBH000020','CD-CBH000020'),(39,'yunfei','湖南省','10','admin','2018-03-27 17:29:12','湖南省','CD-CBH000020','CD-CBH000020'),(40,'yunfei','广东省','10','admin','2018-03-27 17:29:12','广东省','CD-CBH000020','CD-CBH000020'),(41,'yunfei','广西壮族自治区','10','admin','2018-03-27 17:29:12','广西壮族自治区','CD-CBH000020','CD-CBH000020'),(42,'yunfei','海南省','10','admin','2018-03-27 17:29:12','海南省','CD-CBH000020','CD-CBH000020'),(43,'yunfei','云南省','10','admin','2018-03-27 17:29:12','云南省','CD-CBH000020','CD-CBH000020'),(44,'yunfei','贵州省','10','admin','2018-03-27 17:29:12','贵州省','CD-CBH000020','CD-CBH000020'),(45,'yunfei','四川省','10','admin','2018-03-27 17:29:12','四川省','CD-CBH000020','CD-CBH000020'),(46,'yunfei','西藏自治区','10','admin','2018-03-27 17:29:12','西藏自治区','CD-CBH000020','CD-CBH000020'),(47,'AT_QX','BUSERMONTIMES','5','admin','2018-03-29 09:56:25','B端用户每月取现次数','CD-CBH000020','CD-CBH000020'),(48,'AT_QX','BUSERQXFL','0.01','admin','2018-03-29 09:56:25','B端用户取现费率','CD-CBH000020','CD-CBH000020'),(49,'AT_QX','QXDBZDJE','500000','admin','2018-06-19 15:11:05','B端用户最大取现额度','CD-CBH000020','CD-CBH000020'),(50,'AT_QX','BUSERQXSX','T+7','admin','2018-03-29 09:56:25','B端用户取现时效','CD-CBH000020','CD-CBH000020'),(51,'AT_QX','BUSERQXBS','0.02','admin','2018-06-19 15:18:01','B端用户取现倍数','CD-CBH000020','CD-CBH000020'),(52,'wx_h5','wx_h5_access_key','wx9c709bf30e60c07b','admin','2018-03-27 17:29:12','微信appId','CD-CBH000020','CD-CBH000020'),(53,'wx_h5','wx_h5_secret_key','2e334a962351aca2add4101cdc063386','admin','2018-03-27 17:29:12','微信secret ','CD-CBH000020','CD-CBH000020'),(55,'pdf','impower_pdf','u=3059607941,393199546&fm=27&gp=0_1528252521821.jpg','admin','2018-06-15 20:34:29',NULL,'CD-CBH000020','CD-CBH000020'),(58,'wx_xcx','wx_xcx_access_key','wx0bf01798e93cb2f9','admin','2018-04-28 11:08:45','微信小程序appid','CD-CBH000020','CD-CBH000020'),(59,'wx_xcx','wx_xcx_secret_key','405cd42a0c5846cd6319336df41c6549','admin','2018-04-28 11:08:45','微信小程序secret','CD-CBH000020','CD-CBH000020'),(60,'wx_url','agent_url','front.bhxt.hichengdai.com','admin','2018-04-28 11:08:45','代理邀请链接','CD-CBH000020','CD-CBH000020');

/*Table structure for table `tsys_dict` */

DROP TABLE IF EXISTS `tsys_dict`;

CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `type` char(1) DEFAULT NULL COMMENT '类型（第一层/第二层）',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(32) DEFAULT NULL COMMENT 'key',
  `dvalue` varchar(255) DEFAULT NULL COMMENT '值',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8;

/*Data for the table `tsys_dict` */

insert  into `tsys_dict`(`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) values (1,'0',NULL,'user_status','用户状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(2,'1','user_status','0','正常','admin','2018-04-28 11:09:23','','CD-CBH000020','CD-CBH000020'),(3,'1','user_status','1','程序锁定','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(4,'1','user_status','2','人工锁定','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(5,'0',NULL,'id_kind','证件类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(6,'1','id_kind','1','身份证','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(7,'0',NULL,'role_level','角色等级','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(8,'1','role_level','1','运维','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(9,'1','role_level','2','运营','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(10,'1','role_level','3','客户','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(11,'0',NULL,'res_type','资源类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(12,'1','res_type','1','菜单','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(13,'1','res_type','2','按钮','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(14,'0',NULL,'lock_direction','锁定方向','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(15,'1','lock_direction','1','锁定','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(16,'1','lock_direction','2','解锁','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(17,'0',NULL,'notice_status','公告状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(18,'1','notice_status','0','未发布','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(19,'1','notice_status','1','已发布','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(20,'1','notice_status','2','已下架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(21,'0',NULL,'kd_company','物流公司','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(22,'1','kd_company','EMS','邮政EMShhh','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(23,'1','kd_company','STO','申通快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(24,'1','kd_company','ZTO','中通快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(25,'1','kd_company','YTO','圆通快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(26,'1','kd_company','HTKY','汇通快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(27,'1','kd_company','SF','顺丰快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(28,'1','kd_company','TTKD','天天快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(29,'1','kd_company','ZJS','宅急送','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(30,'1','kd_company','BSHT','百世汇通','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(31,'1','kd_company','YDKD','韵达快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(32,'1','kd_company','DBKD','德邦快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(33,'1','kd_company','QFKD','全峰快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(34,'1','kd_company','GTKD','国通快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(35,'1','kd_company','TDHY','天地华宇','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(36,'1','kd_company','JJWL','佳吉物流','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(37,'1','kd_company','JJKD','快捷快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(38,'1','kd_company','YSKD','优速快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(39,'1','kd_company','QTKD','其他','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(40,'0',NULL,'user_kind','针对人群','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(41,'1','user_kind','C','C端用户','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(42,'1','user_kind','B','代理','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(43,'1','user_kind','P','平台所有用户','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(44,'0',NULL,'material_type','素材类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(45,'1','material_type','0','产品素材','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(46,'1','material_type','1','培训素材','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(48,'0',NULL,'inner_status','内购产品状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(49,'1','inner_status','1','待上架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(50,'1','inner_status','2','已上架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(51,'1','inner_status','3','已下架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(52,'0',NULL,'product_status','产品状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(53,'1','product_status','1','待上架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(54,'1','product_status','2','已上架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(55,'1','product_status','3','已下架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(56,'0',NULL,'award','奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(57,'1','award','0','推荐奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(58,'1','award','1','出货奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(59,'1','award','2','介绍奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(60,'0',NULL,'charge_status','充值订单状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(61,'1','charge_status','1','代支付','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(62,'1','charge_status','2','支付失败','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(63,'1','charge_status','3','支付成功','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(64,'0',NULL,'inner_order_status','内购产品订单状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(65,'1','inner_order_status','0','待支付','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(66,'1','inner_order_status','1','已支付待发货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(67,'1','inner_order_status','2','待收货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(68,'1','inner_order_status','3','已收货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(69,'1','inner_order_status','4','申请取消','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(70,'1','inner_order_status','5','已取消','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(71,'0',NULL,'jour_status','流水状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(72,'1','jour_status','1','待对账','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(73,'1','jour_status','3','已对账且账已平','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(74,'1','jour_status','4','帐不平待调账审批','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(75,'0',NULL,'channel_type','渠道类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(76,'1','channel_type','90','线下支付','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(77,'1','channel_type','35','微信充值','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(78,'0',NULL,'biz_type','业务类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(79,'1','biz_type','11','取现','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(80,'1','biz_type','XXFK','体现回录','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(81,'1','biz_type','AJ_CZ','充值','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(82,'1','biz_type','AJ_KK','扣款','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(83,'1','biz_type','AJ_MKCZ','门槛充值','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(84,'1','biz_type','AJ_GMNP','购买内购商品','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(85,'1','biz_type','AJ_GMYC','购买云仓','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(86,'1','biz_type','AJ_CELR','差额利润','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(87,'1','biz_type','AJ_TJJL','推荐奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(88,'1','biz_type','AJ_CHJL','出货奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(89,'1','jour_status','5','已对账且账不平','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(90,'1','jour_status','6','无需对账','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(91,'0',NULL,'withdraw_status','取现状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(92,'1','withdraw_status','1','待审批','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(93,'1','withdraw_status','2','审批不通过','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(94,'1','withdraw_status','3','审批通过待支付','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(95,'1','withdraw_status','4','支付失败','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(96,'1','withdraw_status','5','支付成功','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(97,'0','','order_status','普通订单状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(98,'1','order_status','0','待支付','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(99,'1','order_status','1','已支付待审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(100,'1','order_status','2','已审单待发货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(101,'1','order_status','3','待收货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(102,'1','order_status','4','已收货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(103,'1','order_status','5','申请取消','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(104,'1','order_status','6','已取消','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(106,'0',NULL,'agent_status','代理状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(107,'1','agent_status','0','正常','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(108,'1','agent_status','1','程序锁定','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(109,'1','agent_status','2','人工锁定','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(110,'1','agent_status','3','有意愿','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(111,'1','agent_status','4','已忽略','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(112,'1','agent_status','5','已分配','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(113,'1','agent_status','6','授权待审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(114,'1','agent_status','7','已授权','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(115,'1','agent_status','8','取消授权待审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(116,'1','agent_status','9','授权已取消','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(117,'1','agent_status','10','审核未通过','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(118,'1','agent_status','11','授权待公司审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(119,'1','agent_status','12','升级待审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(120,'1','agent_status','13','已升级','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(121,'1','agent_status','14','升级待公司审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(122,'1','agent_status','15','授权取消待公司审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(123,'0',NULL,'agent_type','代理轨迹类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(124,'1','agent_type','0','意向分配','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(125,'1','agent_type','1','授权','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(126,'1','agent_type','2','升级','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(128,'0',NULL,'order_type','订单类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(129,'1','order_type','0','普通单','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(130,'1','order_type','1','升级单','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(131,'1','order_type','2','授权单','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(132,'1','biz_type','AJ_JSJL','介绍奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(133,'0',NULL,'currency','币种','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(134,'1','currency','TX_CNY','可提现账户','admin','2018-03-28 13:58:15',NULL,NULL,'CD-CBH000020'),(135,'1','currency','TC_CNY','云仓账户','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(136,'1','currency','MK_CNY','门槛账户','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(137,'0',NULL,'change_product_status','置换单','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(138,'1','change_product_status','0','申请换货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(139,'1','change_product_status','1','申请通过','admin','2018-04-12 10:22:52',NULL,'CD-CBH000020','CD-CBH000020'),(140,'1','change_product_status','2','申请未通过','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(141,'1','biz_type','AJ_GMCP','购买产品','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(142,'0',NULL,'product_log_type','产品库存记录类型','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(143,'1','product_log_type','0','出库','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(144,'1','product_log_type','1','入库','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(145,'1','product_log_type','2','代理下单','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(146,'1','product_log_type','3','代理换货','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(147,'0',NULL,'agnecy_log_type','代理轨迹类型','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(148,'1','agnecy_log_type','0','意向分配','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(149,'1','agnecy_log_type','1','授权','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(150,'1','agnecy_log_type','2','升级','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(151,'1','agnecy_log_type','3','退出','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(152,'0',NULL,'account_status','账户状态','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(153,'1','account_status','0','正常','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(154,'1','account_status','1','程序锁定','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(155,'1','account_status','2','人工锁定','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(156,'0',NULL,'address_type','地址类型','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(157,'1','address_type','1','用户地址','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(158,'1','address_type','2','售后地址','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(162,'0',NULL,'after_sale_type','售后类型','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(163,'1','after_sale_type','1','换货','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(164,'1','after_sale_type','2','退货','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(166,'0',NULL,'after_sale_status','售后单状态','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(167,'1','after_sale_status','0','待审核','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(168,'1','after_sale_status','1','审核通过','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(169,'1','after_sale_status','2','审核不通过','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(170,'1','after_sale_status','3','已回寄','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(172,'1','after_sale_type','3','赔偿','admin','2018-04-19 09:35:53','222','CD-CBH000020','CD-CBH000020'),(173,'1','agent_status','16','待申请意向代理','admin','2018-04-19 09:35:53',NULL,'CD-CBH000020','CD-CBH000020'),(174,'1','agent_status','17','修改上级','admin','2018-04-19 09:35:53',NULL,'CD-CBH000020','CD-CBH000020'),(175,'1','agent_status','18','补全授权资料','admin','2018-04-19 09:35:53',NULL,'CD-CBH000020','CD-CBH000020'),(178,'1','agent_status','19','待填写授权资料','admin','2018-04-19 09:35:53',NULL,'CD-CBH000020','CD-CBH000020');

/*Table structure for table `tsys_menu` */

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
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tsys_menu` */

insert  into `tsys_menu`(`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) values ('BHXTSM201700000000000000','根目录','1','#','1','admin','2018-03-15 05:58:07','','','CD-CBH000020'),('BHXTSM201700001000000001','系统管理','1','#','1','admin','2018-03-15 05:58:08','','BHXTSM201700000000000000','CD-CBH000020'),('BHXTSM201700001000000002','运维管理','1','#','2','admin','2018-03-15 05:58:08',NULL,'BHXTSM201700001000000001','CD-CBH000020'),('BHXTSM201700001000000003','菜单管理','1','/system/menu.htm','1','admin','2018-03-15 05:58:08',NULL,'BHXTSM201700001000000002','CD-CBH000020'),('BHXTSM201700001000000004','新增','2','/add','1','admin','2018-03-15 05:58:08','','BHXTSM201700001000000003','CD-CBH000020'),('BHXTSM2017032911200961325','修改','2','/edit','2','admin','2018-03-15 05:58:09','','BHXTSM201700001000000003','CD-CBH000020'),('BHXTSM2017033020005366333','banner管理','1','/public/banner.htm','1','admin','2018-03-15 05:58:09','','BHXTSM201707251006045006005','CD-CBH000020'),('BHXTSM2017033020015631166','新增','2','/add','1','admin','2018-03-15 05:58:09','','BHXTSM2017033020005366333','CD-CBH000020'),('BHXTSM2017033020021094115','修改','2','/edit','2','admin','2018-03-15 05:58:09','','BHXTSM2017033020005366333','CD-CBH000020'),('BHXTSM2017033020022649991','删除','2','/delete','3','admin','2018-03-15 05:58:09','','BHXTSM2017033020005366333','CD-CBH000020'),('BHXTSM2017033020024827112','详情','2','/detail','4','admin','2018-03-15 05:58:09','','BHXTSM2017033020005366333','CD-CBH000020'),('BHXTSM201707251006045006005','广告位管理','1','#','5','admin','2018-03-15 05:58:09','','BHXTSM201700001000000001','CD-CBH000020'),('BHXTSM201708241024194086655','删除','2','/delete','3','admin','2018-03-15 05:58:10','','BHXTSM201700001000000003','CD-CBH000020'),('BHXTSM2017101716241339082','运营管理','1','#','1','admin','2018-03-15 05:58:10','','BHXTSM201700001000000001','CD-CBH000020'),('BHXTSM2017101716253866426','角色管理','1','/system/role.htm','1','admin','2018-03-15 05:58:10','','BHXTSM2017101716241339082','CD-CBH000020'),('BHXTSM2017101716261754674','用户管理','1','/system/user.htm','2','admin','2018-03-26 15:14:09','','BHXTSM2017101716241339082','CD-CBH000020'),('BHXTSM2017101716450533995','分配菜单','2','/change','4','admin','2018-03-15 05:58:10','','BHXTSM2017101716253866426','CD-CBH000020'),('BHXTSM2017101717551955993','新增','2','/add','1','admin','2018-03-15 05:58:11','','BHXTSM2017101716253866426','CD-CBH000020'),('BHXTSM2017101717560118734','修改','2','/edit','2','admin','2018-03-15 05:58:11','','BHXTSM2017101716253866426','CD-CBH000020'),('BHXTSM2017101717563661357','删除','2','/delete','3','admin','2018-03-15 05:58:11','','BHXTSM2017101716253866426','CD-CBH000020'),('BHXTSM2017101719082391126','新增','2','/add','1','admin','2018-03-15 05:58:11','','BHXTSM2017101716261754674','CD-CBH000020'),('BHXTSM2017101719094151894','重置密码','2','/reset','2','admin','2018-03-15 05:58:11','','BHXTSM2017101716261754674','CD-CBH000020'),('BHXTSM2017101719100760088','注销/激活','2','/rock','4','admin','2018-03-27 10:19:24','','BHXTSM2017101716261754674','CD-CBH000020'),('BHXTSM2017101719110981215','设置角色','2','/assign','5','admin','2018-03-15 05:58:11','','BHXTSM2017101716261754674','CD-CBH000020'),('BHXTSM2017121215543215610','文章管理','1','#','4','admin','2018-03-15 05:58:12','','BHXTSM201700001000000001','CD-CBH000020'),('BHXTSM2017121216045274832','关于我们','1','/public/aboutus_addedit.htm','1','admin','2018-03-15 05:58:12','','BHXTSM2017121215543215610','CD-CBH000020'),('SM201711281440497751340','修改','2','/edit','1','admin','2018-03-15 05:58:04','','SM201711281440352206679','CD-CBH000020'),('SM201711281442162104491','修改','2','/edit','1','admin','2018-03-15 05:58:04','','SM201711281441276181084','CD-CBH000020'),('SM201803261509408624073','代理管理','1','#','2','admin','2018-03-26 15:09:40','','BHXTSM201700000000000000','CD-CBH000020'),('SM201803261521004394630','报货管理','1','#','3','admin','2018-03-26 15:21:00','','BHXTSM201700000000000000','CD-CBH000020'),('SM201803261521182464507','财务管理','1','#','4','admin','2018-03-26 15:21:18','','BHXTSM201700000000000000','CD-CBH000020'),('SM201803261521343823118','统计分析','1','#','5','admin','2018-03-26 15:21:34','','BHXTSM201700000000000000','CD-CBH000020'),('SM20180326152552471787','系统参数管理','1','/system/sysPara.htm','2','admin','2018-03-26 15:25:52','','BHXTSM201700001000000002','CD-CBH000020'),('SM201803261526327112082','数据字典管理','1','/system/dataDict.htm','3','admin','2018-03-26 15:26:32','','BHXTSM201700001000000002','CD-CBH000020'),('SM201803261531095855290','代理管理','1','#','1','admin','2018-03-26 15:31:09','','SM201803261509408624073','CD-CBH000020'),('SM201803261535196381139','意向代理分配','1','/dailiManage/yixiangdailifenpei.htm','1','admin','2018-03-26 15:35:19','','SM201803261531095855290','CD-CBH000020'),('SM201803261536091829626','审核授权','1','/dailiManage/shenheshouquan.htm','2','admin','2018-03-26 15:36:09','','SM201803261531095855290','CD-CBH000020'),('SM201803261536425266724','审核升级','1','/dailiManage/shenheshengji.htm','3','admin','2018-03-26 15:36:42','','SM201803261531095855290','CD-CBH000020'),('SM201803261537368506885','审核取消授权','1','/dailiManage/shenhequxiaoshouquan.htm','4','admin','2018-03-26 15:37:36','','SM201803261531095855290','CD-CBH000020'),('SM201803261538117296253','代理资料','1','/dailiManage/dailiziliao.htm','5','admin','2018-03-26 15:38:11','','SM201803261531095855290','CD-CBH000020'),('SM201803261538424769323','代理结构','1','/dailiManage/dailijiegou.htm','6','admin','2018-03-26 15:38:42','','SM201803261531095855290','CD-CBH000020'),('SM201803261539118953097','代理轨迹','1','/dailiManage/dailiguiji.htm','7','admin','2018-03-26 15:39:11','','SM201803261531095855290','CD-CBH000020'),('SM201803261540237406513','财务管理','1','#','2','admin','2018-03-26 15:40:23','','SM201803261509408624073','CD-CBH000020'),('SM201803261540507906075','系统设置','1','#','3','admin','2018-03-26 15:40:50','','SM201803261509408624073','CD-CBH000020'),('SM201803261542031354832','审核充值','1','/dailiManage/shenhechongzhi.htm','1','admin','2018-03-26 15:42:03','','SM201803261540237406513','CD-CBH000020'),('SM201803261542346663628','余额管理','1','/dailiManage/yueguanli.htm','2','admin','2018-03-26 15:42:34','','SM201803261540237406513','CD-CBH000020'),('SM201803261543389416493','出货奖励','1','/dailiManage/chuhuojiangli.htm','4','admin','2018-03-26 15:43:38','','SM201803261540237406513','CD-CBH000020'),('SM201803261544115873430','推荐奖励','1','/dailiManage/tuijianjiangli.htm','5','admin','2018-03-26 15:44:11','','SM201803261540237406513','CD-CBH000020'),('SM201803261544471555008','介绍奖励','1','/dailiManage/jieshaojiangli.htm','6','admin','2018-03-26 15:44:47','','SM201803261540237406513','CD-CBH000020'),('SM201803261545567283077','代理授权设置','1','/dailiManage/dailishouquanConfig.htm','1','admin','2018-03-26 15:45:56','','SM201803261540507906075','CD-CBH000020'),('SM201803261546290999792','代理升级设置','1','/dailiManage/dailishengjiConfig.htm','2','admin','2018-03-26 15:46:29','','SM201803261540507906075','CD-CBH000020'),('SM201803261547049232632','推荐奖设置','1','/dailiManage/tuijianjiangConfig.htm','4','admin','2018-03-27 14:11:52','','SM201803261540507906075','CD-CBH000020'),('SM201803261547449352404','出货奖设置','1','/dailiManage/chuhuojiangConfig.htm','5','admin','2018-03-27 14:11:58','','SM201803261540507906075','CD-CBH000020'),('SM201803261548253013147','介绍奖设置','1','/dailiManage/jieshaojiangConfig.htm','6','admin','2018-03-27 14:12:06','','SM201803261540507906075','CD-CBH000020'),('SM20180326154903564539','授权证书设置','1','/dailiManage/shouquanzhengshuConfig.htm','9','admin','2018-03-28 11:08:57','','SM201803261540507906075','CD-CBH000020'),('SM201803261550403898481','云仓管理','1','#','1','admin','2018-03-26 15:50:40','','SM201803261521004394630','CD-CBH000020'),('SM201803261551128451521','售后管理','1','#','2','admin','2018-03-26 15:51:12','','SM201803261521004394630','CD-CBH000020'),('SM201803261551311709995','溯源防伪','1','#','3','admin','2018-03-26 15:51:31','','SM201803261521004394630','CD-CBH000020'),('SM201803261551473755878','素材管理','1','#','4','admin','2018-03-26 15:51:47','','SM201803261521004394630','CD-CBH000020'),('SM201803261552019943313','活动管理','1','#','5','admin','2018-03-26 15:52:01','','SM201803261521004394630','CD-CBH000020'),('SM201803261552202877480','内购商城','1','#','6','admin','2018-03-26 15:52:20','','SM201803261521004394630','CD-CBH000020'),('SM201803261553209118814','产品列表','1','/bhManage/productList.htm','1','admin','2018-03-26 15:53:20','','SM201803261550403898481','CD-CBH000020'),('SM201803261554058861920','产品库存记录','1','/bhManage/productRecord.htm','2','admin','2018-03-26 15:54:05','','SM201803261550403898481','CD-CBH000020'),('SM201803261554393396515','待处理订单','1','/bhManage/waitOrder.htm','3','admin','2018-03-26 15:54:39','','SM201803261550403898481','CD-CBH000020'),('SM201803261555242358719','全部订单','1','/bhManage/allOrder.htm','4','admin','2018-03-26 15:55:24','','SM201803261550403898481','CD-CBH000020'),('SM201803261557155562082','售后类型','1','/bhManage/shouhouType.htm','1','admin','2018-03-26 15:57:15','','SM201803261551128451521','CD-CBH000020'),('SM201803261557459459672','售后地址','1','/bhManage/shouhouAddress.htm','2','admin','2018-03-26 15:57:45','','SM201803261551128451521','CD-CBH000020'),('SM201803261558260212660','售后单管理','1','/bhManage/shouhouOrder.htm','3','admin','2018-03-26 15:58:26','','SM201803261551128451521','CD-CBH000020'),('SM201803261559284039168','防伪溯源','1','/bhManage/fangweisuyuan.htm','1','admin','2018-03-26 15:59:28','','SM201803261551311709995','CD-CBH000020'),('SM201803261600119438072','订单防伪溯源','1','/bhManage/dingdanfangweisuyuan.htm','2','admin','2018-03-26 16:00:11','','SM201803261551311709995','CD-CBH000020'),('SM201803261601154414563','素材管理','1','/bhManage/sucaiManage.htm','1','admin','2018-03-26 16:01:15','','SM201803261551473755878','CD-CBH000020'),('SM201803261602084949589','百万引流','1','/bhManage/baiwanyinliu.htm','1','admin','2018-03-26 16:02:08','','SM201803261552019943313','CD-CBH000020'),('SM201803261602400816900','开门红包','1','/bhManage/kaimenhongbao.htm','2','admin','2018-03-26 16:02:40','','SM201803261552019943313','CD-CBH000020'),('SM201803261604087514061','产品管理','1','/bhManage/productManage.htm','1','admin','2018-03-26 16:04:08','','SM201803261552202877480','CD-CBH000020'),('SM201803261604394462102','订单管理','1','/bhManage/orderManage.htm','2','admin','2018-03-26 16:04:39','','SM201803261552202877480','CD-CBH000020'),('SM201803261606277913100','平台账户','1','#','1','admin','2018-03-26 16:06:27','','SM201803261521182464507','CD-CBH000020'),('SM201803261606472405257','线下取现','1','#','2','admin','2018-03-26 16:06:47','','SM201803261521182464507','CD-CBH000020'),('SM201803261608099875430','账户查询','1','/financeManage/account.htm','1','admin','2018-03-26 16:08:09','','SM201803261606277913100','CD-CBH000020'),('SM201803261608592077925','流水查询','1','/financeManage/liushui.htm','2','admin','2018-03-26 16:08:59','','SM201803261606277913100','CD-CBH000020'),('SM201803261611046693924','取现规则','1','/financeManage/quxianRules.htm','1','admin','2018-03-26 16:11:04','','SM201803261606472405257','CD-CBH000020'),('SM201803261611550204639','线下取现','1','/financeManage/underlineQuxian.htm','2','admin','2018-03-26 16:11:55','','SM201803261606472405257','CD-CBH000020'),('SM201803261612401458650','取现查询','1','/financeManage/queryQuxian.htm','3','admin','2018-03-26 16:12:40','','SM201803261606472405257','CD-CBH000020'),('SM201803261613482969432','业绩统计','1','/count/yejiCount.htm','1','admin','2018-03-26 16:20:13','','SM20180326161939371910','CD-CBH000020'),('SM201803261614184379303','代理商统计','1','/count/dailishangCount.htm','2','admin','2018-03-26 16:20:29','','SM20180326161939371910','CD-CBH000020'),('SM201803261617490833870','团队产品销售数量统计','1','/count/teamSellNumCount.htm','3','admin','2018-03-26 16:20:41','','SM20180326161939371910','CD-CBH000020'),('SM20180326161845976397','新增代理统计','1','/count/newDailiCount.htm','4','admin','2018-03-26 16:20:51','','SM20180326161939371910','CD-CBH000020'),('SM20180326161939371910','统计管理','1','#','0','admin','2018-03-26 16:19:39','','SM201803261521343823118','CD-CBH000020'),('SM201803261626555555504','服务热线','1','/public/hotline_addedit.htm','2','admin','2018-03-26 16:26:55','','BHXTSM2017121215543215610','CD-CBH000020'),('SM20180326162715242440','服务时间','1','/public/time_addedit.htm','3','admin','2018-03-26 17:30:52','','BHXTSM2017121215543215610','CD-CBH000020'),('SM201803261631287846965','审核分配','2','/check','1','admin','2018-03-26 16:31:28','','SM201803261535196381139','CD-CBH000020'),('SM201803261631486278542','修改资料','2','/edit','2','admin','2018-03-26 16:31:48','','SM201803261535196381139','CD-CBH000020'),('SM201803261632083649677','忽略意向','2','/hulve','3','admin','2018-03-26 16:32:08','','SM201803261535196381139','CD-CBH000020'),('SM201803261633072051819','审核','2','/check','1','admin','2018-03-26 16:33:07','','SM201803261536091829626','CD-CBH000020'),('SM201803261634051172033','审核','2','/check','1','admin','2018-03-26 16:34:05','','SM201803261536425266724','CD-CBH000020'),('SM201803261634301975047','审核','2','/check','1','admin','2018-03-26 16:34:30','','SM201803261537368506885','CD-CBH000020'),('SM201803261635411719158','详情','2','/detail','3','admin','2018-04-28 13:58:17','','SM201803261538117296253','CD-CBH000020'),('SM201803261635537796598','编辑','2','/edit','2','admin','2018-03-26 16:35:53','','SM201803261538117296253','CD-CBH000020'),('SM201803261636184379364','修改上级','2','/changeUp','4','admin','2018-04-28 13:58:26','','SM201803261538117296253','CD-CBH000020'),('SM201803261636377473042','修改推荐人','2','/changeReferee','5','admin','2018-04-28 13:58:39','','SM201803261538117296253','CD-CBH000020'),('SM20180326163700054949','修改管理员','2','/changeAdmin','6','admin','2018-04-28 13:58:46','','SM201803261538117296253','CD-CBH000020'),('SM201803261637186144643','导出','2','/export','7','admin','2018-04-28 13:58:52','','SM201803261538117296253','CD-CBH000020'),('SM201803261638111069070','导出','2','/export','1','admin','2018-03-26 16:38:11','','SM201803261538424769323','CD-CBH000020'),('SM201803261639392578554','审核','2','/check','1','admin','2018-03-26 16:39:39','','SM201803261542031354832','CD-CBH000020'),('SM201803261642354034722','充值','2','/in','1','admin','2018-03-26 16:42:35','','SM201803261542346663628','CD-CBH000020'),('SM201803261643499083022','扣款','2','/out','2','admin','2018-03-26 16:43:49','','SM201803261542346663628','CD-CBH000020'),('SM201803261644177075053','余额变动记录','2','/yueChangeRecord','3','admin','2018-03-26 16:44:17','','SM201803261542346663628','CD-CBH000020'),('SM201803261701446659755','支出明细','2','/outRecord','1','admin','2018-03-26 17:01:44','','SM201803261543389416493','CD-CBH000020'),('SM201803261702027406812','收入明细','2','/inRecord','2','admin','2018-03-26 17:02:02','','SM201803261543389416493','CD-CBH000020'),('SM201803261702324378','支出明细','2','/outRecord','1','admin','2018-03-26 17:02:32','','SM201803261544115873430','CD-CBH000020'),('SM201803261702507289103','收入明细','2','/inRecord','2','admin','2018-03-26 17:02:50','','SM201803261544115873430','CD-CBH000020'),('SM201803261703206553784','支出明细','2','/outRecord','1','admin','2018-03-26 17:03:20','','SM201803261544471555008','CD-CBH000020'),('SM201803261704056995038','收入明细','2','/inRecord','2','admin','2018-03-26 17:04:05','','SM201803261544471555008','CD-CBH000020'),('SM20180326171209480622','通知管理','1','#','5','admin','2018-03-26 17:12:09','','BHXTSM201700001000000001','CD-CBH000020'),('SM201803261713155951829','公告管理','1','/public/notice.htm','1','admin','2018-03-26 17:17:52','','SM20180326171209480622','CD-CBH000020'),('SM201803261719182321081','新增','2','/add','1','admin','2018-03-26 17:19:18','','SM201803261713155951829','CD-CBH000020'),('SM20180326171931186591','修改','2','/edit2','2','admin','2018-03-26 17:20:20','','SM201803261713155951829','CD-CBH000020'),('SM201803261720054257820','发布/撤下','2','/push','3','admin','2018-03-26 17:20:05','','SM201803261713155951829','CD-CBH000020'),('SM20180326172153440773','详情','2','/detail','4','admin','2018-03-26 17:21:53','','SM201803261713155951829','CD-CBH000020'),('SM201803261740162214183','修改','2','/edit','1','admin','2018-03-26 17:40:16','','SM201803261526327112082','CD-CBH000020'),('SM201803261745351957613','修改','2','/edit','1','admin','2018-03-26 17:45:35','','SM20180326152552471787','CD-CBH000020'),('SM201803261806292929983','详情','2','/detail','1','admin','2018-03-27 16:52:33','','SM201803261545567283077','CD-CBH000020'),('SM2018032618065379263','修改','2','/edit','2','admin','2018-03-26 18:06:53','','SM201803261545567283077','CD-CBH000020'),('SM201803261807305875425','详情','2','/detail','1','admin','2018-03-27 16:53:12','','SM201803261546290999792','CD-CBH000020'),('SM2018032618074402344','修改','2','/edit','2','admin','2018-03-26 18:07:44','','SM201803261546290999792','CD-CBH000020'),('SM201803261808165365305','修改','2','/edit','1','admin','2018-03-26 18:08:16','','SM201803261547049232632','CD-CBH000020'),('SM201803261808458576273','修改','2','/edit','1','admin','2018-03-26 18:08:45','','SM201803261547449352404','CD-CBH000020'),('SM201803261809133466785','修改','2','/edit','1','admin','2018-03-26 18:09:13','','SM201803261548253013147','CD-CBH000020'),('SM201803261809372583496','修改','2','/edit','1','admin','2018-03-26 18:09:37','','SM20180326154903564539','CD-CBH000020'),('SM201803261810453526093','新增','2','/add','1','admin','2018-03-26 18:10:45','','SM201803261553209118814','CD-CBH000020'),('SM201803261811161518402','修改','2','/edit','2','admin','2018-03-26 18:11:16','','SM201803261553209118814','CD-CBH000020'),('SM201803261811463843676','上架','2','/up','3','admin','2018-03-28 13:59:35','','SM201803261553209118814','CD-CBH000020'),('SM201803261812481746184','详情','2','/detail','1','admin','2018-03-26 18:12:48','','SM201803261554058861920','CD-CBH000020'),('SM201803261813218699036','详情','2','/detail','1','admin','2018-03-26 18:13:21','','SM201803261554393396515','CD-CBH000020'),('SM201803261813405429345','修改收货','2','/changeAddress','2','admin','2018-03-26 18:13:40','','SM201803261554393396515','CD-CBH000020'),('SM201803261813582615854','导出','2','/export','3','admin','2018-03-26 18:13:58','','SM201803261554393396515','CD-CBH000020'),('SM201803261814154879609','批量审单','2','/shendan','4','admin','2018-03-26 18:14:15','','SM201803261554393396515','CD-CBH000020'),('SM201803261814346777379','发货','2','/fahuo','5','admin','2018-03-26 18:14:34','','SM201803261554393396515','CD-CBH000020'),('SM201803261814510524596','审核取消','2','/cancel','6','admin','2018-03-26 18:14:51','','SM201803261554393396515','CD-CBH000020'),('SM201803261815204698759','详情','2','/detail','1','admin','2018-03-26 18:15:20','','SM201803261555242358719','CD-CBH000020'),('SM201803261815352367105','导出','2','/export','2','admin','2018-03-26 18:15:35','','SM201803261555242358719','CD-CBH000020'),('SM201803261816193738729','物流信息','2','/wuliu','4','admin','2018-03-26 18:16:19','','SM201803261555242358719','CD-CBH000020'),('SM201803261848448968050','新增','2','/add','1','admin','2018-03-26 18:48:44','','SM201803261557155562082','CD-CBH000020'),('SM201803261849090399536','修改','2','/edit','2','admin','2018-03-26 18:49:09','','SM201803261557155562082','CD-CBH000020'),('SM201803261849260791866','删除','2','/delete','3','admin','2018-03-26 18:49:26','','SM201803261557155562082','CD-CBH000020'),('SM201803261849599829265','新增','2','/add','1','admin','2018-03-26 18:49:59','','SM201803261557459459672','CD-CBH000020'),('SM201803261850226879215','修改','2','/edit','2','admin','2018-03-26 18:50:22','','SM201803261557459459672','CD-CBH000020'),('SM20180326185049237376','处理','2','/chuli','1','admin','2018-03-26 18:50:49','','SM201803261558260212660','CD-CBH000020'),('SM201803261851312553803','防伪记录','2','/fangweijilu','1','admin','2018-03-26 18:51:31','','SM201803261559284039168','CD-CBH000020'),('SM201803261851511533166','溯源记录','2','/suyuanjilu','2','admin','2018-03-26 18:51:51','','SM201803261559284039168','CD-CBH000020'),('SM20180326185209890977','导入','2','/import','3','admin','2018-03-26 18:52:09','','SM201803261559284039168','CD-CBH000020'),('SM201803261852357601134','溯源记录','2','/suyuanjilu','1','admin','2018-03-26 18:52:35','','SM201803261600119438072','CD-CBH000020'),('SM201803261852503624205','导出','2','/export','2','admin','2018-03-26 18:52:50','','SM201803261600119438072','CD-CBH000020'),('SM201803261853151325367','新增','2','/add','1','admin','2018-03-26 18:53:15','','SM201803261601154414563','CD-CBH000020'),('SM201803261853274263384','修改','2','/edit','2','admin','2018-03-26 18:53:27','','SM201803261601154414563','CD-CBH000020'),('SM201803261853439898122','删除','2','/delete','3','admin','2018-03-26 18:53:43','','SM201803261601154414563','CD-CBH000020'),('SM201803261854100023701','新增','2','/add','1','admin','2018-03-26 18:54:10','','SM201803261602084949589','CD-CBH000020'),('SM201803261854220023761','修改','2','/edit','2','admin','2018-03-26 18:54:22','','SM201803261602084949589','CD-CBH000020'),('SM201803261854515697667','新增','2','/add','1','admin','2018-03-26 18:54:51','','SM201803261602400816900','CD-CBH000020'),('SM201803261855090593419','修改','2','/edit','2','admin','2018-03-26 18:55:09','','SM201803261602400816900','CD-CBH000020'),('SM201803261855242434956','手动下架','2','/down','3','admin','2018-03-26 18:55:24','','SM201803261602400816900','CD-CBH000020'),('SM201803261856116089330','详情','2','/detail','1','admin','2018-03-26 18:56:11','','SM201803261608592077925','CD-CBH000020'),('SM201803261856258966710','导出','2','/export','2','admin','2018-03-26 18:56:25','','SM201803261608592077925','CD-CBH000020'),('SM201803261856526437749','修改','2','/edit','1','admin','2018-03-26 18:56:52','','SM201803261611046693924','CD-CBH000020'),('SM201803261857286203906','代申请','2','/daishenqing','1','admin','2018-03-26 18:57:28','','SM201803261611550204639','CD-CBH000020'),('SM201803261857445834545','批量审核','2','/check','2','admin','2018-03-26 18:57:44','','SM201803261611550204639','CD-CBH000020'),('SM201803261858170842370','批量回录','2','/huilu','3','admin','2018-03-26 18:58:17','','SM201803261611550204639','CD-CBH000020'),('SM201803261858335968438','导出','2','/export','4','admin','2018-03-26 18:58:33','','SM201803261611550204639','CD-CBH000020'),('SM201803261859167411401','详情','2','/detail','1','admin','2018-03-26 18:59:16','','SM201803261612401458650','CD-CBH000020'),('SM201803261859309677825','导出','2','/export','2','admin','2018-03-26 18:59:30','','SM201803261612401458650','CD-CBH000020'),('SM20180326190235565892','新增','2','/add','1','admin','2018-03-26 19:02:35','','SM201803261604087514061','CD-CBH000020'),('SM201803261902536746938','编辑','2','/edit','2','admin','2018-03-26 19:02:53','','SM201803261604087514061','CD-CBH000020'),('SM201803261903122085714','上架','2','/up','3','admin','2018-03-27 14:45:15','','SM201803261604087514061','CD-CBH000020'),('SM201803261904206199828','详情','2','/detail','1','admin','2018-03-26 19:04:20','','SM201803261604394462102','CD-CBH000020'),('SM201803261904405563055','导出','2','/export','2','admin','2018-03-26 19:04:40','','SM201803261604394462102','CD-CBH000020'),('SM201803261906042753348','物流信息','2','/wuliu','4','admin','2018-03-26 19:06:04','','SM201803261604394462102','CD-CBH000020'),('SM20180327141118551514','代理等级管理','1','/dailiManage/levelManage.htm','3','admin','2018-03-27 14:12:50','','SM201803261540507906075','CD-CBH000020'),('SM201803271424448506892','详情','2','/detail','1','admin','2018-03-27 15:30:26','','SM20180327141118551514','CD-CBH000020'),('SM20180327142457630660','修改','2','/edit','2','admin','2018-03-27 14:24:57','','SM20180327141118551514','CD-CBH000020'),('SM201803271445283981921','下架','2','/down','4','admin','2018-03-27 14:45:28','','SM201803261604087514061','CD-CBH000020'),('SM201803281400029721899','下架','2','/down','4','admin','2018-03-28 14:00:02','','SM201803261553209118814','CD-CBH000020'),('SM20180328140021361799','规格库存','2','/guige','5','admin','2018-03-28 14:00:21','','SM201803261553209118814','CD-CBH000020'),('SM201804131614374363524','导出','2','/export','1','admin','2018-04-13 16:14:37','','SM201803261539118953097','CD-CBH000020'),('SM201804161652350873803','置换单','1','/bhManage/exchange.htm','5','admin','2018-04-16 16:52:35','','SM201803261550403898481','CD-CBH000020'),('SM201804161653521178359','审核','2','/check','1','admin','2018-04-16 16:53:52','','SM201804161652350873803','CD-CBH000020'),('SM201804281357471672846','新增代理','2','/addDaili','1','admin','2018-04-28 13:57:47','','SM201803261538117296253','CD-CBH000020'),('SM201806140136101691000','发货','2','/fahuo','4','admin','2018-06-14 01:36:10','','SM201803261604394462102','CD-CBH000020');


DROP TABLE IF EXISTS `tsys_menu_role`;

CREATE TABLE `tsys_menu_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) DEFAULT NULL,
  `menu_code` varchar(32) DEFAULT NULL,
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

/*Data for the table `tsys_menu_role` */

insert  into `tsys_menu_role`(`id`,`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) values (5012,'RO201800000000000001','BHXTSM201700000000000000','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5013,'RO201800000000000001','BHXTSM201700001000000001','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5014,'RO201800000000000001','BHXTSM201700001000000002','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5015,'RO201800000000000001','BHXTSM201700001000000003','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5016,'RO201800000000000001','BHXTSM201700001000000004','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5017,'RO201800000000000001','BHXTSM2017032911200961325','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5018,'RO201800000000000001','BHXTSM201708241024194086655','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5019,'RO201800000000000001','SM20180326152552471787','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5020,'RO201800000000000001','SM201803261745351957613','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5021,'RO201800000000000001','SM201803261526327112082','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5022,'RO201800000000000001','SM201803261740162214183','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5023,'RO201800000000000001','BHXTSM201707251006045006005','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5024,'RO201800000000000001','BHXTSM2017033020005366333','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5025,'RO201800000000000001','BHXTSM2017033020015631166','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5026,'RO201800000000000001','BHXTSM2017033020021094115','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5027,'RO201800000000000001','BHXTSM2017033020022649991','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5028,'RO201800000000000001','BHXTSM2017033020024827112','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5029,'RO201800000000000001','BHXTSM2017101716241339082','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5030,'RO201800000000000001','BHXTSM2017101716253866426','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5031,'RO201800000000000001','BHXTSM2017101716450533995','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5032,'RO201800000000000001','BHXTSM2017101717551955993','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5033,'RO201800000000000001','BHXTSM2017101717560118734','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5034,'RO201800000000000001','BHXTSM2017101717563661357','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5035,'RO201800000000000001','BHXTSM2017101716261754674','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5036,'RO201800000000000001','BHXTSM2017101719082391126','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5037,'RO201800000000000001','BHXTSM2017101719094151894','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5038,'RO201800000000000001','BHXTSM2017101719100760088','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5039,'RO201800000000000001','BHXTSM2017101719110981215','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5040,'RO201800000000000001','BHXTSM2017121215543215610','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5041,'RO201800000000000001','BHXTSM2017121216045274832','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5042,'RO201800000000000001','SM201803261626555555504','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5043,'RO201800000000000001','SM20180326162715242440','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5044,'RO201800000000000001','SM20180326171209480622','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5045,'RO201800000000000001','SM201803261713155951829','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5046,'RO201800000000000001','SM201803261719182321081','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5047,'RO201800000000000001','SM20180326171931186591','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5048,'RO201800000000000001','SM201803261720054257820','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5049,'RO201800000000000001','SM20180326172153440773','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5050,'RO201800000000000001','SM201803261509408624073','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5051,'RO201800000000000001','SM201803261531095855290','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5052,'RO201800000000000001','SM201803261535196381139','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5053,'RO201800000000000001','SM201803261631287846965','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5054,'RO201800000000000001','SM201803261631486278542','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5055,'RO201800000000000001','SM201803261632083649677','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5056,'RO201800000000000001','SM201803261536091829626','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5057,'RO201800000000000001','SM201803261633072051819','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5058,'RO201800000000000001','SM201803261536425266724','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5059,'RO201800000000000001','SM201803261634051172033','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5060,'RO201800000000000001','SM201803261537368506885','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5061,'RO201800000000000001','SM201803261634301975047','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5062,'RO201800000000000001','SM201803261538117296253','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5063,'RO201800000000000001','SM201803261635411719158','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5064,'RO201800000000000001','SM201803261635537796598','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5065,'RO201800000000000001','SM201803261636184379364','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5066,'RO201800000000000001','SM201803261636377473042','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5067,'RO201800000000000001','SM20180326163700054949','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5068,'RO201800000000000001','SM201803261637186144643','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5069,'RO201800000000000001','SM201804281357471672846','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5070,'RO201800000000000001','SM201803261538424769323','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5071,'RO201800000000000001','SM201803261638111069070','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5072,'RO201800000000000001','SM201803261539118953097','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5073,'RO201800000000000001','SM201804131614374363524','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5074,'RO201800000000000001','SM201803261540237406513','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5075,'RO201800000000000001','SM201803261542031354832','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5076,'RO201800000000000001','SM201803261639392578554','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5077,'RO201800000000000001','SM201803261542346663628','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5078,'RO201800000000000001','SM201803261642354034722','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5079,'RO201800000000000001','SM201803261643499083022','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5080,'RO201800000000000001','SM201803261644177075053','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5081,'RO201800000000000001','SM201803261543389416493','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5082,'RO201800000000000001','SM201803261701446659755','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5083,'RO201800000000000001','SM201803261702027406812','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5084,'RO201800000000000001','SM201803261544115873430','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5085,'RO201800000000000001','SM201803261702324378','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5086,'RO201800000000000001','SM201803261702507289103','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5087,'RO201800000000000001','SM201803261544471555008','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5088,'RO201800000000000001','SM201803261703206553784','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5089,'RO201800000000000001','SM201803261704056995038','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5090,'RO201800000000000001','SM201803261540507906075','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5091,'RO201800000000000001','SM201803261545567283077','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5092,'RO201800000000000001','SM201803261806292929983','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5093,'RO201800000000000001','SM2018032618065379263','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5094,'RO201800000000000001','SM201803261546290999792','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5095,'RO201800000000000001','SM201803261807305875425','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5096,'RO201800000000000001','SM2018032618074402344','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5097,'RO201800000000000001','SM201803261547049232632','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5098,'RO201800000000000001','SM201803261808165365305','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5099,'RO201800000000000001','SM201803261547449352404','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5100,'RO201800000000000001','SM201803261808458576273','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5101,'RO201800000000000001','SM201803261548253013147','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5102,'RO201800000000000001','SM201803261809133466785','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5103,'RO201800000000000001','SM20180326154903564539','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5104,'RO201800000000000001','SM201803261809372583496','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5105,'RO201800000000000001','SM20180327141118551514','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5106,'RO201800000000000001','SM201803271424448506892','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5107,'RO201800000000000001','SM20180327142457630660','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5108,'RO201800000000000001','SM201803261521004394630','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5109,'RO201800000000000001','SM201803261550403898481','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5110,'RO201800000000000001','SM201803261553209118814','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5111,'RO201800000000000001','SM201803261810453526093','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5112,'RO201800000000000001','SM201803261811161518402','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5113,'RO201800000000000001','SM201803261811463843676','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5114,'RO201800000000000001','SM201803281400029721899','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5115,'RO201800000000000001','SM20180328140021361799','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5116,'RO201800000000000001','SM201803261554058861920','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5117,'RO201800000000000001','SM201803261812481746184','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5118,'RO201800000000000001','SM201803261554393396515','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5119,'RO201800000000000001','SM201803261813218699036','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5120,'RO201800000000000001','SM201803261813405429345','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5121,'RO201800000000000001','SM201803261813582615854','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5122,'RO201800000000000001','SM201803261814154879609','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5123,'RO201800000000000001','SM201803261814346777379','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5124,'RO201800000000000001','SM201803261814510524596','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5125,'RO201800000000000001','SM201803261555242358719','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5126,'RO201800000000000001','SM201803261815204698759','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5127,'RO201800000000000001','SM201803261815352367105','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5128,'RO201800000000000001','SM201803261816193738729','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5129,'RO201800000000000001','SM201804161652350873803','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5130,'RO201800000000000001','SM201804161653521178359','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5131,'RO201800000000000001','SM201803261551128451521','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5132,'RO201800000000000001','SM201803261557155562082','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5133,'RO201800000000000001','SM201803261848448968050','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5134,'RO201800000000000001','SM201803261849090399536','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5135,'RO201800000000000001','SM201803261849260791866','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5136,'RO201800000000000001','SM201803261557459459672','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5137,'RO201800000000000001','SM201803261849599829265','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5138,'RO201800000000000001','SM201803261850226879215','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5139,'RO201800000000000001','SM201803261558260212660','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5140,'RO201800000000000001','SM20180326185049237376','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5141,'RO201800000000000001','SM201803261551311709995','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5142,'RO201800000000000001','SM201803261559284039168','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5143,'RO201800000000000001','SM201803261851312553803','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5144,'RO201800000000000001','SM201803261851511533166','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5145,'RO201800000000000001','SM20180326185209890977','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5146,'RO201800000000000001','SM201803261600119438072','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5147,'RO201800000000000001','SM201803261852357601134','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5148,'RO201800000000000001','SM201803261852503624205','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5149,'RO201800000000000001','SM201803261551473755878','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5150,'RO201800000000000001','SM201803261601154414563','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5151,'RO201800000000000001','SM201803261853151325367','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5152,'RO201800000000000001','SM201803261853274263384','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5153,'RO201800000000000001','SM201803261853439898122','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5154,'RO201800000000000001','SM201803261552019943313','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5155,'RO201800000000000001','SM201803261602084949589','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5156,'RO201800000000000001','SM201803261854100023701','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5157,'RO201800000000000001','SM201803261854220023761','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5158,'RO201800000000000001','SM201803261602400816900','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5159,'RO201800000000000001','SM201803261854515697667','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5160,'RO201800000000000001','SM201803261855090593419','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5161,'RO201800000000000001','SM201803261855242434956','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5162,'RO201800000000000001','SM201803261552202877480','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5163,'RO201800000000000001','SM201803261604087514061','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5164,'RO201800000000000001','SM20180326190235565892','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5165,'RO201800000000000001','SM201803261902536746938','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5166,'RO201800000000000001','SM201803261903122085714','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5167,'RO201800000000000001','SM201803271445283981921','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5168,'RO201800000000000001','SM201803261604394462102','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5169,'RO201800000000000001','SM201803261904206199828','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5170,'RO201800000000000001','SM201803261904405563055','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5171,'RO201800000000000001','SM201803261906042753348','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5172,'RO201800000000000001','SM201806140136101691000','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5173,'RO201800000000000001','SM201803261521182464507','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5174,'RO201800000000000001','SM201803261606277913100','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5175,'RO201800000000000001','SM201803261608099875430','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5176,'RO201800000000000001','SM201803261608592077925','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5177,'RO201800000000000001','SM201803261856116089330','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5178,'RO201800000000000001','SM201803261856258966710','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5179,'RO201800000000000001','SM201803261606472405257','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5180,'RO201800000000000001','SM201803261611046693924','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5181,'RO201800000000000001','SM201803261856526437749','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5182,'RO201800000000000001','SM201803261611550204639','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5183,'RO201800000000000001','SM201803261857286203906','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5184,'RO201800000000000001','SM201803261857445834545','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5185,'RO201800000000000001','SM201803261858170842370','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5186,'RO201800000000000001','SM201803261858335968438','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5187,'RO201800000000000001','SM201803261612401458650','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5188,'RO201800000000000001','SM201803261859167411401','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5189,'RO201800000000000001','SM201803261859309677825','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5190,'RO201800000000000001','SM201803261521343823118','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5191,'RO201800000000000001','SM20180326161939371910','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5192,'RO201800000000000001','SM201803261613482969432','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5193,'RO201800000000000001','SM201803261614184379303','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5194,'RO201800000000000001','SM201803261617490833870','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5195,'RO201800000000000001','SM20180326161845976397','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5196,'RO201800000000000001','SM201711281440497751340','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020'),(5197,'RO201800000000000001','SM201711281442162104491','admin','2018-06-14 01:36:58',NULL,'CD-CBH000020');

/*Table structure for table `tsys_role` */

DROP TABLE IF EXISTS `tsys_role`;

CREATE TABLE `tsys_role` (
  `code` varchar(32) NOT NULL COMMENT '角色编号',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `level` varchar(2) DEFAULT NULL,
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tsys_role` */

insert  into `tsys_role`(`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) values ('RO201800000000000001','超级管理员','1','admin','2018-02-21 10:00:00',NULL,'CD-CBH000020');

