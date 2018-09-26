ALTER TABLE tbh_agent ADD is_trader CHAR(1) COMMENT '是否是操盘手(0 否，1 是)';

UPDATE tbh_agent SET is_trader = '0';

UPDATE tbh_in_order SET STATUS = 7 WHERE STATUS = 4;
UPDATE tbh_in_order SET STATUS = 4 WHERE STATUS = 1;
UPDATE tbh_in_order SET STATUS = 5 WHERE STATUS = 2;
UPDATE tbh_in_order SET STATUS = 6 WHERE STATUS = 3;

UPDATE tsys_dict SET dkey = 7 WHERE parent_key = 'in_order_status' AND dkey = 4;
UPDATE tsys_dict SET dkey = 4 WHERE parent_key = 'in_order_status' AND dkey = 1;
UPDATE tsys_dict SET dkey = 5 WHERE parent_key = 'in_order_status' AND dkey = 2;
UPDATE tsys_dict SET dkey = 6 WHERE parent_key = 'in_order_status' AND dkey = 3;
