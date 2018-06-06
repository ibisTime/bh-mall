
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
  `high_level` int(11) DEFAULT NULL COMMENT '上级',
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
  `value1` decimal(10,3) DEFAULT NULL COMMENT '直接推荐/出货奖励',
  `value2` decimal(10,3) DEFAULT NULL COMMENT '间接推荐奖励',
  `value3` decimal(10,3) DEFAULT NULL COMMENT '次推荐奖励',
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
  `system_code` varchar(32) NOT NULL COMMENT '系统编号',
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
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


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
  `pdf` varchar(255) DEFAULT NULL COMMENT '物流单',
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
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `price` bigint(20) DEFAULT NULL COMMENT '价格',
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
  `red_amount` int(32) DEFAULT NULL COMMENT '红线',
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
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_ware_house`;
CREATE TABLE `tbh_ware_house` (
  `code` varchar(32) NOT NULL,
  `type` varchar(4) DEFAULT NULL COMMENT '类型（B/P）',
  `currency` varchar(64) DEFAULT NULL COMMENT '币种',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `product_specs_code` varchar(255) DEFAULT NULL COMMENT '规格编号',
  `product_specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `price` bigint(20) DEFAULT NULL COMMENT '价格',
  `amount` bigint(20) DEFAULT NULL COMMENT '总价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
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
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8;

/*Data for the table `tsys_dict` */

insert  into `tsys_dict`(`id`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) values (1,'0',NULL,'user_status','用户状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(2,'1','user_status','0','正常','admin','2018-04-28 11:09:23','','CD-CBH000020','CD-CBH000020'),(3,'1','user_status','1','程序锁定','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(4,'1','user_status','2','人工锁定','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(5,'0',NULL,'id_kind','证件类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(6,'1','id_kind','1','身份证','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(7,'0',NULL,'role_level','角色等级','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(8,'1','role_level','1','运维','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(9,'1','role_level','2','运营','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(10,'1','role_level','3','客户','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(11,'0',NULL,'res_type','资源类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(12,'1','res_type','1','菜单','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(13,'1','res_type','2','按钮','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(14,'0',NULL,'lock_direction','锁定方向','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(15,'1','lock_direction','1','锁定','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(16,'1','lock_direction','2','解锁','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(17,'0',NULL,'notice_status','公告状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(18,'1','notice_status','0','未发布','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(19,'1','notice_status','1','已发布','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(20,'1','notice_status','2','已下架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(21,'0',NULL,'kd_company','物流公司','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(22,'1','kd_company','EMS','邮政EMShhh','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(23,'1','kd_company','STO','申通快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(24,'1','kd_company','ZTO','中通快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(25,'1','kd_company','YTO','圆通快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(26,'1','kd_company','HTKY','汇通快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(27,'1','kd_company','SF','顺丰快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(28,'1','kd_company','TTKD','天天快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(29,'1','kd_company','ZJS','宅急送','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(30,'1','kd_company','BSHT','百世汇通','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(31,'1','kd_company','YDKD','韵达快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(32,'1','kd_company','DBKD','德邦快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(33,'1','kd_company','QFKD','全峰快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(34,'1','kd_company','GTKD','国通快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(35,'1','kd_company','TDHY','天地华宇','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(36,'1','kd_company','JJWL','佳吉物流','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(37,'1','kd_company','JJKD','快捷快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(38,'1','kd_company','YSKD','优速快递','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(39,'1','kd_company','QTKD','其他','admin','2018-03-28 13:58:15','','CD-CBH000020','CD-CBH000020'),(40,'0',NULL,'user_kind','针对人群','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(41,'1','user_kind','C','C端用户','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(42,'1','user_kind','B','代理','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(43,'1','user_kind','P','平台所有用户','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(44,'0',NULL,'material_type','素材类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(45,'1','material_type','0','产品素材','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(46,'1','material_type','1','培训素材','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(48,'0',NULL,'inner_status','内购产品状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(49,'1','inner_status','1','待上架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(50,'1','inner_status','2','已上架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(51,'1','inner_status','3','已下架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(52,'0',NULL,'product_status','产品状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(53,'1','product_status','1','待上架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(54,'1','product_status','2','已上架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(55,'1','product_status','3','已下架','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(56,'0',NULL,'award','奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(57,'1','award','0','推荐奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(58,'1','award','1','出货奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(59,'1','award','2','介绍奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(60,'0',NULL,'charge_status','充值订单状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(61,'1','charge_status','1','代支付','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(62,'1','charge_status','2','支付失败','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(63,'1','charge_status','3','支付成功','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(64,'0',NULL,'inner_order_status','内购产品订单状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(65,'1','inner_order_status','0','待支付','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(66,'1','inner_order_status','1','已支付待发货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(67,'1','inner_order_status','2','待收货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(68,'1','inner_order_status','3','已收货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(69,'1','inner_order_status','4','申请取消','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(70,'1','inner_order_status','5','已取消','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(71,'0',NULL,'jour_status','流水状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(72,'1','jour_status','1','待对账','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(73,'1','jour_status','3','已对账且账已平','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(74,'1','jour_status','4','帐不平待调账审批','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(75,'0',NULL,'channel_type','渠道类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(76,'1','channel_type','90','线下支付','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(77,'1','channel_type','35','微信充值','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(78,'0',NULL,'biz_type','业务类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(79,'1','biz_type','11','取现','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(80,'1','biz_type','XXFK','体现回录','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(81,'1','biz_type','AJ_CZ','充值','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(82,'1','biz_type','AJ_KK','扣款','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(83,'1','biz_type','AJ_MKCZ','门槛充值','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(84,'1','biz_type','AJ_GMNP','购买内购商品','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(85,'1','biz_type','AJ_GMYC','购买云仓','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(86,'1','biz_type','AJ_CELR','差额利润','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(87,'1','biz_type','AJ_TJJL','推荐奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(88,'1','biz_type','AJ_CHJL','出货奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(89,'1','jour_status','5','已对账且账不平','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(90,'1','jour_status','6','无需对账','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(91,'0',NULL,'withdraw_status','取现状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(92,'1','withdraw_status','1','待审批','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(93,'1','withdraw_status','2','审批不通过','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(94,'1','withdraw_status','3','审批通过待支付','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(95,'1','withdraw_status','4','支付失败','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(96,'1','withdraw_status','5','支付成功','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(97,'0','','order_status','普通订单状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(98,'1','order_status','0','待支付','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(99,'1','order_status','1','已支付待审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(100,'1','order_status','2','已审单待发货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(101,'1','order_status','3','待收货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(102,'1','order_status','4','已收货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(103,'1','order_status','5','申请取消','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(104,'1','order_status','6','已取消','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(106,'0',NULL,'agent_status','代理状态','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(107,'1','agent_status','0','正常','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(108,'1','agent_status','1','程序锁定','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(109,'1','agent_status','2','人工锁定','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(110,'1','agent_status','3','有意愿','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(111,'1','agent_status','4','已忽略','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(112,'1','agent_status','5','已分配','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(113,'1','agent_status','6','授权待审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(114,'1','agent_status','7','已授权','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(115,'1','agent_status','8','取消授权待审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(116,'1','agent_status','9','授权已取消','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(117,'1','agent_status','10','审核未通过','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(118,'1','agent_status','11','授权待公司审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(119,'1','agent_status','12','升级待审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(120,'1','agent_status','13','已升级','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(121,'1','agent_status','14','升级待公司审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(122,'1','agent_status','15','授权取消待公司审核','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(123,'0',NULL,'agent_type','代理轨迹类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(124,'1','agent_type','0','意向分配','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(125,'1','agent_type','1','授权','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(126,'1','agent_type','2','升级','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(128,'0',NULL,'order_type','订单类型','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(129,'1','order_type','0','普通单','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(130,'1','order_type','1','升级单','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(131,'1','order_type','2','授权单','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(132,'1','biz_type','AJ_JSJL','介绍奖励','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(133,'0',NULL,'currency','币种','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(134,'1','currency','YJ_CNY','可提现账户','admin','2018-03-28 13:58:15',NULL,NULL,'CD-CBH000020'),(135,'1','currency','TC_CNY','云仓账户','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(136,'1','currency','MK_CNY','门槛账户','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(137,'0',NULL,'change_product_status','置换单','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(138,'1','change_product_status','0','申请换货','admin','2018-03-28 13:58:15',NULL,'CD-CBH000020','CD-CBH000020'),(139,'1','change_product_status','1','申请通过','admin','2018-04-12 10:22:52',NULL,'CD-CBH000020','CD-CBH000020'),(140,'1','change_product_status','2','申请未通过','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(141,'1','biz_type','AJ_GMCP','购买产品','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(142,'0',NULL,'product_log_type','产品库存记录类型','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(143,'1','product_log_type','0','出库','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(144,'1','product_log_type','1','入库','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(145,'1','product_log_type','2','代理下单','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(146,'1','product_log_type','3','代理换货','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(147,'0',NULL,'agnecy_log_type','代理轨迹类型','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(148,'1','agnecy_log_type','0','意向分配','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(149,'1','agnecy_log_type','1','授权','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(150,'1','agnecy_log_type','2','升级','admin','2018-04-12 10:22:55',NULL,'CD-CBH000020','CD-CBH000020'),(151,'1','agnecy_log_type','3','退出','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(152,'0',NULL,'account_status','账户状态','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(153,'1','account_status','0','正常','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(154,'1','account_status','1','程序锁定','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(155,'1','account_status','2','人工锁定','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(156,'0',NULL,'address_type','地址类型','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(157,'1','address_type','1','用户地址','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(158,'1','address_type','2','售后地址','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(162,'0',NULL,'after_sale_type','售后类型','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(163,'1','after_sale_type','1','换货','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(164,'1','after_sale_type','2','退货','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(166,'0',NULL,'after_sale_status','售后单状态','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(167,'1','after_sale_status','0','待审核','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(168,'1','after_sale_status','1','审核通过','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(169,'1','after_sale_status','2','审核不通过','admin','2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(170,'1','after_sale_status','3','已回寄',NULL,'2018-04-13 16:49:02',NULL,'CD-CBH000020','CD-CBH000020'),(172,'1','after_sale_type','3','赔偿','admin','2018-04-19 09:35:53','222',NULL,'CD-CBH000020');

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

