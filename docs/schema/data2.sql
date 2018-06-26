
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
  `min_surplus` bigint(20) DEFAULT NULL COMMENT '本等级门槛最低余额',
  `is_wareHouse` char(1) DEFAULT NULL COMMENT '本等级是否启用云仓',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`level`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


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
  `is_single` char(1) DEFAULT NULL COMMENT '是否可拆单',
  `single_number` int(11) DEFAULT '0' COMMENT '拆单数量',
  `ref_code` varchar(32) DEFAULT NULL COMMENT '关联拆单编号',
  `name` varchar(64) DEFAULT NULL COMMENT '规格名称',
  `number` int(11) DEFAULT '0' COMMENT '规格包含数量',
  `weight` int(11) DEFAULT '0' COMMENT '重量',
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
  `daily_number` int(11) DEFAULT '0' COMMENT '日限购',
  `weekly_number` int(11) DEFAULT '0' COMMENT '周限购',
  `monthly_number` int(11) DEFAULT '0' COMMENT '月限购',
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
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

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

