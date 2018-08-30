
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

/*Table structure for table `tbh_address` */

DROP TABLE IF EXISTS `tbh_address`;

CREATE TABLE `tbh_address` (
  `code` varchar(32) NOT NULL COMMENT '收件编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `receiver` varchar(64) DEFAULT NULL COMMENT '收件人姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `province` varchar(64) DEFAULT NULL COMMENT '省份',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `area` varchar(64) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `is_default` char(1) DEFAULT NULL COMMENT '是否默认地址',
  `type` varchar(32) DEFAULT NULL COMMENT '类型(1.用户地址，2.售后地址)',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_agent` */

DROP TABLE IF EXISTS `tbh_agent`;

CREATE TABLE `tbh_agent` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `from_user_id` varchar(32) DEFAULT NULL COMMENT '分享二位码代理',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `wx_id` varchar(32) DEFAULT NULL COMMENT '微信号',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `trade_pwd` varchar(32) DEFAULT NULL COMMENT '交易密码',
  `trade_pwd_strength` varchar(1) DEFAULT NULL,
  `level` int(32) DEFAULT NULL COMMENT '用户等级',
  `referrer` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `introducer` varchar(32) DEFAULT NULL COMMENT '介绍人',
  `high_user_id` varchar(32) DEFAULT NULL COMMENT '上级用户',
  `team_name` varchar(32) DEFAULT NULL COMMENT '团队名称',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `id_hand` text COMMENT '手持身份证',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `manager` varchar(32) DEFAULT NULL COMMENT '关联管理员',
  `union_id` varchar(255) DEFAULT NULL COMMENT '联合编号',
  `h5_open_id` varchar(255) DEFAULT NULL COMMENT '公众号开放编号',
  `app_open_id` varchar(255) DEFAULT NULL COMMENT 'app开放编号',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_name` varchar(32) DEFAULT NULL,
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `impower_datetime` datetime DEFAULT NULL COMMENT '最后审核时间',
  `last_agent_log` varchar(32) DEFAULT NULL COMMENT '最后一条代理轨迹记录',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_agent_level` */

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

/*Table structure for table `tbh_agent_log` */

DROP TABLE IF EXISTS `tbh_agent_log`;

CREATE TABLE `tbh_agent_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(1) DEFAULT NULL COMMENT '类型',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `real_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `wx_id` varchar(255) DEFAULT NULL COMMENT '微信号',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `to_user_id` varchar(32) DEFAULT NULL COMMENT '意向归属人',
  `level` int(11) DEFAULT NULL COMMENT '当前等级',
  `apply_level` int(11) DEFAULT NULL COMMENT '申请等级',
  `high_user_id` varchar(32) DEFAULT NULL COMMENT '上级',
  `referrer` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `introducer` varchar(32) DEFAULT NULL COMMENT '介绍人',
  `team_name` varchar(255) DEFAULT NULL COMMENT '团队名称',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '打款金额',
  `pay_pdf` varchar(255) DEFAULT NULL COMMENT '打款截图',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` text COMMENT '详细地址',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_name` varchar(255) DEFAULT NULL COMMENT '审核人姓名',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` text COMMENT '审核备注',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `impower_datetime` datetime DEFAULT NULL COMMENT '授权时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_agent_price` */

DROP TABLE IF EXISTS `tbh_agent_price`;

CREATE TABLE `tbh_agent_price` (
  `code` varchar(32) NOT NULL,
  `specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `level` int(11) DEFAULT '0' COMMENT '等级',
  `price` bigint(20) DEFAULT '0' COMMENT '价格',
  `change_price` bigint(20) DEFAULT '0' COMMENT '换货价',
  `start_number` int(11) DEFAULT '0' COMMENT '起购数',
  `min_number` int(11) DEFAULT '0' COMMENT '本等级最低云仓发货数',
  `daily_number` int(11) DEFAULT '0' COMMENT '日限购',
  `weekly_number` int(11) DEFAULT '0' COMMENT '周限购',
  `monthly_number` int(11) DEFAULT '0' COMMENT '月限购',
  `is_buy` char(1) DEFAULT NULL COMMENT '本等级是否可购买',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_agent_report` */

DROP TABLE IF EXISTS `tbh_agent_report`;

CREATE TABLE `tbh_agent_report` (
  `user_id` varchar(32) NOT NULL COMMENT '代理Id',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `wxId` varchar(255) DEFAULT NULL COMMENT '微信号',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推挤人',
  `introducer` varchar(32) DEFAULT NULL COMMENT '介绍人',
  `high_user_id` varchar(32) DEFAULT NULL COMMENT '上级',
  `team_name` varchar(255) DEFAULT NULL COMMENT '团队名称',
  `manager` varchar(32) DEFAULT NULL COMMENT '管理员',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` text COMMENT '地址',
  `impower_datetime` datetime DEFAULT NULL COMMENT '授权时间',
  `send_award` bigint(20) DEFAULT '0' COMMENT '累积出货奖励',
  `intr_award` bigint(20) DEFAULT '0' COMMENT '累积介绍奖',
  `refree_award` bigint(20) DEFAULT '0' COMMENT '累积推荐奖',
  `profit_award` bigint(20) DEFAULT '0' COMMENT '累积差额利润',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tbh_award_interval` */

DROP TABLE IF EXISTS `tbh_award_interval`;

CREATE TABLE `tbh_award_interval` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `level` varchar(32) DEFAULT NULL COMMENT '等级',
  `start_amount` bigint(20) DEFAULT '0' COMMENT '起始金额',
  `end_amount` bigint(20) DEFAULT '0' COMMENT '结束金额',
  `percent` decimal(10,2) DEFAULT '0.00' COMMENT '奖励百分比',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_bankcard` */

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

/*Table structure for table `tbh_cart` */

DROP TABLE IF EXISTS `tbh_cart`;

CREATE TABLE `tbh_cart` (
  `code` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `type` varchar(4) DEFAULT NULL COMMENT '用户类型',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '产品规格编号',
  `price` bigint(20) DEFAULT NULL COMMENT '单价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_ch_award` */

DROP TABLE IF EXISTS `tbh_ch_award`;

CREATE TABLE `tbh_ch_award` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `level` varchar(32) DEFAULT NULL COMMENT '等级',
  `start_amount` bigint(20) DEFAULT '0' COMMENT '起始金额',
  `end_amount` bigint(20) DEFAULT '0' COMMENT '结束金额',
  `percent` decimal(10,2) DEFAULT '0.00' COMMENT '奖励百分比',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_channel_bank` */

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
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

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
  `charge_pdf` varchar(255) DEFAULT NULL COMMENT '打款截图',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `biz_note` varchar(255) DEFAULT NULL COMMENT '流水说明',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `high_user_id` varchar(32) DEFAULT NULL COMMENT '上级',
  `team_name` varchar(255) DEFAULT NULL COMMENT '申请人团队',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `pay_user` varchar(32) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付渠道说明',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `channel_order` varchar(255) DEFAULT NULL COMMENT '支付渠道编号',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`) COMMENT '充值订单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_cnavigate` */

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
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `content_type` varchar(32) DEFAULT NULL COMMENT '内容源类型',
  `is_company_edit` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_company_channel` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_cuser` */

DROP TABLE IF EXISTS `tbh_cuser`;

CREATE TABLE `tbh_cuser` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `wx_id` varchar(32) DEFAULT NULL COMMENT '微信号',
  `union_id` varchar(255) DEFAULT NULL COMMENT '联合编号',
  `h5_open_id` varchar(255) DEFAULT NULL COMMENT '公众号开放编号',
  `app_open_id` varchar(255) DEFAULT NULL COMMENT 'app开放编号',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_delive_order` */

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

/*Table structure for table `tbh_exchange_order` */

DROP TABLE IF EXISTS `tbh_exchange_order`;

CREATE TABLE `tbh_exchange_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
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

/*Table structure for table `tbh_in_order` */

DROP TABLE IF EXISTS `tbh_in_order`;

CREATE TABLE `tbh_in_order` (
  `code` varchar(32) NOT NULL,
  `product_code` varchar(32) DEFAULT NULL COMMENT '是否云仓发货',
  `product_name` varchar(255) DEFAULT NULL COMMENT '分类',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `specs_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `pic` varbinary(255) DEFAULT NULL COMMENT '规格编号',
  `quantity` int(11) DEFAULT NULL COMMENT '规格名称',
  `price` bigint(20) DEFAULT NULL COMMENT '图片',
  `to_user_id` varchar(32) DEFAULT NULL COMMENT '订单归属人',
  `to_user_name` varchar(32) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL COMMENT '总价',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单人',
  `real_name` varchar(32) DEFAULT NULL,
  `team_name` varchar(32) DEFAULT NULL,
  `team_leader` varchar(32) DEFAULT NULL,
  `pay_type` varchar(64) DEFAULT NULL COMMENT '支付渠道',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '支付金额',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `apply_datetime` datetime DEFAULT NULL COMMENT '下单时间',
  `apply_note` text COMMENT '下单备注',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_inner_order` */

DROP TABLE IF EXISTS `tbh_inner_order`;

CREATE TABLE `tbh_inner_order` (
  `code` varchar(32) NOT NULL,
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(64) DEFAULT NULL COMMENT '产品名称',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `pic` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `price` bigint(20) DEFAULT NULL COMMENT '单价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单代理',
  `real_name` varchar(255) DEFAULT NULL COMMENT '代理名称',
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
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` text COMMENT '审核备注',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_inner_product` */

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

/*Table structure for table `tbh_inner_specs` */

DROP TABLE IF EXISTS `tbh_inner_specs`;

CREATE TABLE `tbh_inner_specs` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `inner_product_code` varchar(32) DEFAULT NULL COMMENT '内购产品编号',
  `name` varchar(64) DEFAULT NULL COMMENT '规格名称',
  `number` int(11) DEFAULT '0' COMMENT '规格包含数量',
  `weight` int(11) DEFAULT '0' COMMENT '重量',
  `price` int(11) DEFAULT '0' COMMENT '单价',
  `ref_code` varchar(32) DEFAULT NULL COMMENT '关联规格编号',
  `stock_number` int(11) DEFAULT '0' COMMENT '库存',
  `is_single` char(1) DEFAULT NULL COMMENT '是否可拆单',
  `single_number` int(11) DEFAULT '0' COMMENT '拆单数量',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_intro` */

DROP TABLE IF EXISTS `tbh_intro`;

CREATE TABLE `tbh_intro` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `intro_level` int(11) DEFAULT NULL COMMENT '可介绍等级',
  `percent` decimal(10,2) DEFAULT NULL COMMENT '标题',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_jour` */

DROP TABLE IF EXISTS `tbh_jour`;

CREATE TABLE `tbh_jour` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `ref_no` text COMMENT '参考订单号',
  `pay_group` text COMMENT '分组组号',
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

/*Table structure for table `tbh_js_award` */

DROP TABLE IF EXISTS `tbh_js_award`;

CREATE TABLE `tbh_js_award` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `intro_level` int(11) DEFAULT NULL COMMENT '可介绍等级',
  `percent` decimal(10,2) DEFAULT NULL COMMENT '标题',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_material` */

DROP TABLE IF EXISTS `tbh_material`;

CREATE TABLE `tbh_material` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `order_no` int(11) DEFAULT NULL COMMENT '排序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0未发布 1发布）',
  `level` varchar(32) DEFAULT NULL COMMENT '查看等级',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_mini_code` */

DROP TABLE IF EXISTS `tbh_mini_code`;

CREATE TABLE `tbh_mini_code` (
  `mini_code` varchar(13) NOT NULL COMMENT '防伪码',
  `trace_code` varchar(13) DEFAULT NULL COMMENT '溯源码',
  `ref_code` varchar(13) DEFAULT NULL COMMENT '关联箱码',
  `order_code` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `number` int(11) DEFAULT '0' COMMENT '查询次数',
  `batch` varchar(32) DEFAULT NULL,
  `status` char(9) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `use_datetime` datetime DEFAULT NULL COMMENT '使用时间',
  PRIMARY KEY (`mini_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_order_report` */

DROP TABLE IF EXISTS `tbh_order_report`;

CREATE TABLE `tbh_order_report` (
  `code` varchar(32) NOT NULL,
  `level` int(32) DEFAULT NULL COMMENT '等级',
  `is_company_send` varchar(32) DEFAULT NULL COMMENT '是否公司发货',
  `kind` varchar(4) DEFAULT NULL COMMENT '分类',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `pro_code` varchar(32) DEFAULT NULL COMMENT '关联箱码',
  `pic` varbinary(255) DEFAULT NULL COMMENT '图片',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `price` bigint(20) DEFAULT NULL COMMENT '单价',
  `to_user_id` varchar(32) DEFAULT NULL COMMENT '向谁提货',
  `to_user_name` varchar(255) DEFAULT NULL COMMENT '订单归属人姓名',
  `amount` bigint(20) DEFAULT NULL COMMENT '总价',
  `yunfei` bigint(20) DEFAULT NULL COMMENT '运费',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `pay_type` varchar(64) DEFAULT NULL COMMENT '支付渠道',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '支付金额',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '渠道编号',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单人',
  `high_user_id` varchar(32) DEFAULT NULL COMMENT '上级',
  `real_name` varchar(255) DEFAULT NULL COMMENT '下单人姓名',
  `team_name` varchar(255) DEFAULT NULL COMMENT '团队名称',
  `team_leader` varchar(255) DEFAULT NULL COMMENT '团队长',
  `apply_datetime` datetime DEFAULT NULL COMMENT '下单时间',
  `apply_note` text COMMENT '下单备注',
  `signer` varchar(32) DEFAULT NULL COMMENT '收件人姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '收件人电话',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `deliver` varchar(32) DEFAULT NULL COMMENT '发货人',
  `delive_datetime` datetime DEFAULT NULL COMMENT '发货时间',
  `logistics_code` varchar(32) DEFAULT NULL COMMENT '物流单号',
  `logistics_company` varchar(32) DEFAULT NULL COMMENT '物流公司',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `update_note` varchar(32) DEFAULT NULL COMMENT '更新备注',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_out_order` */

DROP TABLE IF EXISTS `tbh_out_order`;

CREATE TABLE `tbh_out_order` (
  `code` varchar(32) NOT NULL,
  `level` int(32) DEFAULT NULL COMMENT '等级',
  `is_ware_send` varchar(32) DEFAULT NULL COMMENT '是否云仓发货',
  `kind` varchar(4) DEFAULT NULL COMMENT '分类',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `pro_code` varchar(32) DEFAULT NULL COMMENT '关联箱码',
  `pic` varbinary(255) DEFAULT NULL COMMENT '图片',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `price` bigint(20) DEFAULT NULL COMMENT '单价',
  `to_user_id` varchar(32) DEFAULT NULL COMMENT '向谁提货',
  `to_user_name` varchar(255) DEFAULT NULL COMMENT '订单归属人姓名',
  `amount` bigint(20) DEFAULT NULL COMMENT '总价',
  `yunfei` bigint(20) DEFAULT NULL COMMENT '运费',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `pay_type` varchar(64) DEFAULT NULL COMMENT '支付渠道',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '支付金额',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '渠道编号',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单人',
  `high_user_id` varchar(32) DEFAULT NULL COMMENT '上级',
  `real_name` varchar(255) DEFAULT NULL COMMENT '下单人姓名',
  `team_name` varchar(255) DEFAULT NULL COMMENT '团队名称',
  `team_leader` varchar(255) DEFAULT NULL COMMENT '团队长',
  `apply_datetime` datetime DEFAULT NULL COMMENT '下单时间',
  `apply_note` text COMMENT '下单备注',
  `signer` varchar(32) DEFAULT NULL COMMENT '收件人姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '收件人电话',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `deliver` varchar(32) DEFAULT NULL COMMENT '发货人',
  `delive_datetime` datetime DEFAULT NULL COMMENT '发货时间',
  `logistics_code` varchar(32) DEFAULT NULL COMMENT '物流单号',
  `logistics_company` varchar(32) DEFAULT NULL COMMENT '物流公司',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `update_note` varchar(32) DEFAULT NULL COMMENT '更新备注',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_pro_code` */

DROP TABLE IF EXISTS `tbh_pro_code`;

CREATE TABLE `tbh_pro_code` (
  `code` varchar(39) NOT NULL,
  `status` char(3) DEFAULT NULL,
  `batch` varchar(32) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `use_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_product` */

DROP TABLE IF EXISTS `tbh_product`;

CREATE TABLE `tbh_product` (
  `code` varchar(32) NOT NULL,
  `is_free` varchar(4) DEFAULT NULL COMMENT '是否包邮',
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

/*Table structure for table `tbh_product_report` */

DROP TABLE IF EXISTS `tbh_product_report`;

CREATE TABLE `tbh_product_report` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `team_name` varchar(255) DEFAULT NULL COMMENT '团队名称',
  `team_leader` varchar(255) DEFAULT NULL COMMENT '团队长',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `specs_name` varchar(255) DEFAULT NULL COMMENT '规格名字',
  `quantity` int(11) DEFAULT '0' COMMENT '数量',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_security_trace` */

DROP TABLE IF EXISTS `tbh_security_trace`;

CREATE TABLE `tbh_security_trace` (
  `security_code` varchar(13) DEFAULT NULL COMMENT '防伪码',
  `trace_code` varchar(13) DEFAULT NULL COMMENT '溯源码',
  `ref_code` varchar(13) DEFAULT NULL COMMENT '关联箱码',
  `order_code` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `number` int(11) DEFAULT '0' COMMENT '查询次数',
  `status` char(9) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `use_datetime` datetime DEFAULT NULL COMMENT '使用时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_sj_form` */

DROP TABLE IF EXISTS `tbh_sj_form`;

CREATE TABLE `tbh_sj_form` (
  `user_id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '申请人',
  `real_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '真实姓名',
  `id_kind` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(18) CHARACTER SET utf8 DEFAULT NULL COMMENT '证件号',
  `id_hand` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '手持身份证',
  `team_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '团队名称',
  `to_user_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '归属人',
  `level` int(11) DEFAULT NULL COMMENT '当前等级',
  `apply_level` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '申请等级',
  `re_number` int(11) DEFAULT '0' COMMENT '半门槛推荐人数',
  `pay_amount` bigint(20) DEFAULT '0' COMMENT '打款金额',
  `pay_pdf` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '打款截图',
  `approver` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '审核人',
  `approve_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '审核人名称',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `status` varchar(4) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态',
  `remark` text CHARACTER SET utf8 COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tbh_specs` */

DROP TABLE IF EXISTS `tbh_specs`;

CREATE TABLE `tbh_specs` (
  `code` varchar(32) NOT NULL,
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `is_single` char(1) DEFAULT NULL COMMENT '是否可拆单',
  `single_number` int(11) DEFAULT '0' COMMENT '拆单数量',
  `ref_code` varchar(32) DEFAULT NULL COMMENT '关联规格编号',
  `name` varchar(64) DEFAULT NULL COMMENT '规格名称',
  `number` int(11) DEFAULT '0' COMMENT '规格包含数量',
  `weight` int(11) DEFAULT '0' COMMENT '重量',
  `is_sq_order` char(1) DEFAULT NULL COMMENT '是否允许授权单下单',
  `is_sj_order` char(1) DEFAULT NULL COMMENT '是否允许升级单下单',
  `is_normal_order` char(1) DEFAULT NULL COMMENT '是否允许普通单下单',
  `stock_number` int(11) DEFAULT '0' COMMENT '库存数量',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_specs_log` */

DROP TABLE IF EXISTS `tbh_specs_log`;

CREATE TABLE `tbh_specs_log` (
  `code` varchar(32) NOT NULL,
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `tran_count` int(11) DEFAULT NULL COMMENT '变动数量',
  `pre_count` int(11) DEFAULT NULL COMMENT '变动前数量',
  `post_count` int(11) DEFAULT NULL COMMENT '变动后数量',
  `updater` varchar(32) DEFAULT NULL COMMENT '变动人',
  `update_datetime` datetime DEFAULT NULL COMMENT '变动时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_sq_form` */

DROP TABLE IF EXISTS `tbh_sq_form`;

CREATE TABLE `tbh_sq_form` (
  `user_id` varchar(32) DEFAULT NULL COMMENT '申请人',
  `wx_id` varchar(255) DEFAULT NULL COMMENT '微信号',
  `apply_level` varchar(32) DEFAULT NULL COMMENT '申请等级',
  `team_name` varchar(255) DEFAULT NULL COMMENT '团队名称',
  `real_name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `to_user_id` varchar(32) DEFAULT NULL COMMENT '授权归属人',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `area` varchar(64) DEFAULT NULL COMMENT '区',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `province` varchar(64) DEFAULT NULL COMMENT '省',
  `id_hand` text COMMENT '手持身份证',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `introducer` varchar(32) DEFAULT NULL COMMENT '介绍人',
  `referrer` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_name` varchar(255) DEFAULT NULL COMMENT '审核人姓名',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `impower_datetime` datetime DEFAULT NULL COMMENT '授权时间',
  `remark` text COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_tj_award` */

DROP TABLE IF EXISTS `tbh_tj_award`;

CREATE TABLE `tbh_tj_award` (
  `code` varchar(32) NOT NULL,
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `level` varchar(64) DEFAULT NULL COMMENT '等级',
  `value1` decimal(10,3) DEFAULT '0.000' COMMENT '直接推荐/出货奖励',
  `value2` decimal(10,3) DEFAULT '0.000' COMMENT '间接推荐奖励',
  `value3` decimal(10,3) DEFAULT '0.000' COMMENT '次推荐奖励',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_ware` */

DROP TABLE IF EXISTS `tbh_ware`;

CREATE TABLE `tbh_ware` (
  `code` varchar(32) NOT NULL,
  `type` varchar(4) DEFAULT NULL COMMENT '类型（B/P）',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `currency` varchar(64) DEFAULT NULL COMMENT '币种',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `specs_code` varchar(255) DEFAULT NULL COMMENT '规格编号',
  `specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `price` bigint(20) DEFAULT NULL COMMENT '价格',
  `amount` bigint(20) DEFAULT NULL COMMENT '总价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `real_name` varchar(255) DEFAULT NULL COMMENT '代理名称',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `last_change_code` varchar(32) DEFAULT NULL COMMENT '最后一条流水',
  `system_code` varchar(32) DEFAULT NULL,
  `company_code` varbinary(32) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_ware_log` */

DROP TABLE IF EXISTS `tbh_ware_log`;

CREATE TABLE `tbh_ware_log` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `ref_no` text COMMENT '关联编号',
  `ware_code` varchar(32) DEFAULT NULL COMMENT '所属云仓编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型(置换，购买/出货)',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `specs_name` varbinary(255) DEFAULT NULL COMMENT '规格名称',
  `tran_number` int(11) DEFAULT NULL COMMENT '价格',
  `before_number` int(11) DEFAULT NULL COMMENT '变动数量',
  `after_number` int(11) DEFAULT NULL COMMENT '变动前数量',
  `price` bigint(20) DEFAULT NULL COMMENT '变动后数量',
  `amount` bigint(20) DEFAULT NULL COMMENT '总价',
  `change_number` int(11) DEFAULT NULL COMMENT '可置换数量',
  `change_price` bigint(20) DEFAULT NULL COMMENT '置换价格',
  `change_amount` bigint(20) DEFAULT NULL,
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `real_name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `apply_note` text COMMENT '申请备注',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` text COMMENT '审核备注',
  `biz_type` varbinary(64) DEFAULT NULL COMMENT '业务类型',
  `biz_note` text COMMENT '业务说明',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_withdraw` */

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
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人',
  `high_user_id` varchar(32) DEFAULT NULL COMMENT '上级',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审批时间',
  `pay_user` varchar(32) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(32) DEFAULT NULL COMMENT '支付回录说明',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付渠道订单编号',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付回录时间',
  PRIMARY KEY (`code`) COMMENT '取现订单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbh_yx_form` */

DROP TABLE IF EXISTS `tbh_yx_form`;

CREATE TABLE `tbh_yx_form` (
  `user_id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '申请人',
  `wx_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信号',
  `to_user_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '意向归属人',
  `apply_level` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '申请等级',
  `real_name` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '地址',
  `area` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '区',
  `city` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '市',
  `province` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '省',
  `from_info` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '信息来源',
  `status` varchar(4) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approver` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '审核人',
  `approve_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '审核人名称',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `remark` text CHARACTER SET utf8 COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `tsys_config` */

DROP TABLE IF EXISTS `tsys_config`;

CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(255) DEFAULT NULL COMMENT 'key',
  `cvalue` text COMMENT 'value',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

/*Table structure for table `tsys_dict` */

DROP TABLE IF EXISTS `tsys_dict`;

CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` char(9) DEFAULT NULL,
  `parent_key` varchar(288) DEFAULT NULL,
  `dkey` varchar(288) DEFAULT NULL,
  `dvalue` varchar(2295) DEFAULT NULL,
  `updater` varchar(288) DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(2295) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

/*Table structure for table `tsys_menu` */

DROP TABLE IF EXISTS `tsys_menu`;

CREATE TABLE `tsys_menu` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父亲节点',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `type` varchar(2) DEFAULT NULL COMMENT '类型',
  `url` varchar(64) DEFAULT NULL COMMENT '请求url',
  `order_no` int(11) DEFAULT NULL COMMENT '序号',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tsys_menu_role` */

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

/*Table structure for table `tsys_role` */

DROP TABLE IF EXISTS `tsys_role`;

CREATE TABLE `tsys_role` (
  `code` varchar(32) NOT NULL COMMENT '角色编号',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tsys_user` */

DROP TABLE IF EXISTS `tsys_user`;

CREATE TABLE `tsys_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `real_name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varbinary(32) DEFAULT NULL COMMENT '登录密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登录密码强度',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

