-- ----------------------------
--  Table structure for `tsys_config`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_config`;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(255) DEFAULT NULL COMMENT 'key',
  `cvalue` text DEFAULT NULL COMMENT 'value',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_dict`
-- ----------------------------
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tbh_address`;
CREATE TABLE `tbh_address` (
  `code` varchar(32) NOT NULL COMMENT '收件编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `receiver` varchar(64) DEFAULT NULL COMMENT '收件人姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `province` varchar(64) DEFAULT NULL COMMENT '省份',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `district` varchar(64) DEFAULT NULL COMMENT '区',
  `detail_address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `is_default` char(1) DEFAULT NULL COMMENT '是否默认地址',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbh_cnavigate`
-- ----------------------------
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

-- ----------------------------
--  Table structure for `tbh_user`
-- ----------------------------
DROP TABLE IF EXISTS `tbh_user`;
CREATE TABLE `tbh_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登陆名',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `weixin_id` varchar(32) DEFAULT NULL COMMENT '微信号',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  
  `nickname` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `login_pwd` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登陆密码强度',
  `kind` varchar(4) DEFAULT NULL COMMENT '用户类型',
  `now_level` varchar(32) DEFAULT NULL COMMENT '用户等级',
  
  `apply_level` varchar(32) DEFAULT NULL COMMENT '申请等级',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  
  `introducer` varchar(32) DEFAULT NULL COMMENT '介绍人',
  `high_level` varchar(32) DEFAULT NULL COMMENT '上级用户',
  `pur_user` varchar(32) DEFAULT NULL COMMENT '意向归属用户',
  `team_name` varchar(32) DEFAULT NULL COMMENT '团队名称',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  
  `id_card_reverse` text DEFAULT NULL COMMENT '身份证反面',
  `id_card_front` text DEFAULT NULL COMMENT '身份证正面',
  `id_card_hand` text DEFAULT NULL COMMENT '手持身份证',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  
  `intend_status` varchar(4) DEFAULT NULL COMMENT '状态',
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
  `cash_pdf` varchar(32) DEFAULT NULL COMMENT '保证金截图',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `last_agent_log` varchar(32) DEFAULT NULL COMMENT '最后一条代理轨迹记录',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',  
  PRIMARY KEY (`user_id`)
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
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
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
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
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
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
