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
