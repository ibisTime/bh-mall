
INSERT INTO tsys_dict (TYPE,parent_key,dkey,dvalue,updater,update_datetime) 
VALUES (0,NULL,'order_kind','全部订单类型','USYS201800000000002',NOW()),
(1,'order_kind','0','授权单','USYS201800000000002',NOW()),
(1,'order_kind','1','升级单','USYS201800000000002',NOW()),
(1,'order_kind','2','普通订单','USYS201800000000002',NOW()),
(1,'order_kind','3','云仓提货','USYS201800000000002',NOW()),
(1,'order_kind','4','C端订单','USYS201800000000002',NOW()),
(1,'order_kind','5','云仓订单','USYS201800000000002',NOW());


update tbh_out_order o, tbh_agent u set o.team_leader = u.real_name where u.team_name = o.team_name and u.level = 1;
update tbh_in_order o, tbh_agent u set o.team_leader = u.real_name where u.team_name = o.team_name and u.level = 1;