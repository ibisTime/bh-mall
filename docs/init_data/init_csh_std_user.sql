/*
-- Query: SELECT * FROM cd_std_user.tstd_user where login_name = 'admin'
LIMIT 0, 50000

-- Date: 2016-10-20 10:27
*/
INSERT INTO `tstd_user` (`user_id`, `login_name`, `nickname`, `login_pwd`, `login_pwd_strength`, `kind`, `level`, `user_referee`, `mobile`, `id_kind`, `id_no`, `real_name`, `trade_pwd`, `trade_pwd_strength`, `role_code`, `status`, `pdf`, `amount`, `lj_amount`, `company_code`, `updater`, `update_datetime`, `remark`) VALUES ('U201600000000000000', 'admin', NULL, '21218cca77804d2ba1922c33e0151105', '1', '01', '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'SR201600000000000000', '0', NULL, NULL, NULL, NULL, 'admin', NULL, '管理端系统方');

/*
-- Query: SELECT code,name,type,url,order_no,updater, now() as update_datetime,remark,parent_code,kind FROM tsys_menu 
LIMIT 0, 50000

-- Date: 2016-11-23 22:34
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM201600000000000000','根目录','1','#','1','admin',now(),'','','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM201600001000000000','系统根目录','1','#','1','admin',now(),'','SM201600000000000000','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM201600001000000001','系统管理','1','#','1','admin',now(),'','SM201600001000000000','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM201600001000000002','运维管理','1','#','2','admin',now(),NULL,'SM201600001000000001','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM201600001000000003','菜单管理','1','/security/menu.htm','1','admin',now(),NULL,'SM201600001000000002','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM201600001000000004','修改','2','/edit','1','admin',now(),NULL,'SM201600001000000003','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101716241339082','运营管理','1','#','1','admin',now(),'','SM201600001000000001','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101716253866426','角色管理','1','/security/role.htm','1','admin',now(),'','SM2016101716241339082','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101716261754674','用户管理','1','/security/user.htm','2','admin',now(),'','SM2016101716241339082','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101716271123590','公司管理','1','/general/company.htm','1','admin',now(),'','SM2016102516555766535','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101716290657836','系统参数管理','1','/general/param.htm','2','admin',now(),'','SM201600001000000002','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101716295904680','数据字典管理','1','/general/dict.htm','3','admin',now(),'','SM201600001000000002','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101716450533995','分配菜单','2','/change','4','admin',now(),'','SM2016101716253866426','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101717551955993','新增','2','/add','1','admin',now(),'','SM2016101716253866426','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101717560118734','修改','2','/edit','2','admin',now(),'','SM2016101716253866426','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101717563661357','删除','2','/delete','3','admin',now(),'','SM2016101716253866426','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719082391126','新增','2','/add','1','admin',now(),'','SM2016101716261754674','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719094151894','重置密码','2','/reset','3','admin',now(),'','SM2016101716261754674','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719100760088','注销','2','/rock','4','admin',now(),'','SM2016101716261754674','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719110981215','设置角色','2','/assign','5','admin',now(),'','SM2016101716261754674','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719120926719','新增','2','/add','1','admin',now(),'','SM2016101716271123590','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719123215692','注销/取消','2','/rock','2','admin',now(),'','SM2016101716271123590','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719125359852','修改','2','/edit','3','admin',now(),'','SM2016101716271123590','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719132278227','详情','2','/detail','4','admin',now(),'','SM2016101716271123590','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719140087629','修改','2','/edit','1','admin',now(),'','SM2016101716290657836','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719143965297','修改','2','/edit','1','admin',now(),'','SM2016101716295904680','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719284086819','菜单管理','1','/std/menu.htm','2','admin',now(),'','SM2016102516555766535','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM201610171939350218','Banner管理','1','/std/banner.htm','3','admin',now(),'','SM2016102516555766535','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719441184314','新增','2','/add','1','admin',now(),'','SM2016101719284086819','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719444119219','删除','2','/delete','3','admin',now(),'','SM2016101719284086819','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719451349522','修改','2','/edit','2','admin',now(),'','SM2016101719284086819','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719454161086','详情','2','/detail','4','admin',now(),'','SM2016101719284086819','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719463455794','新增','2','/add','1','admin',now(),'','SM201610171939350218','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719470668795','修改','2','/edit','2','admin',now(),'','SM201610171939350218','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719472470447','删除','2','/delete','3','admin',now(),'','SM201610171939350218','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016101719474966791','详情','2','/detail','4','admin',now(),'','SM201610171939350218','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016102516555766535','总部管理','1','#','3','admin',now(),'','SM201600001000000001','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111510330493381','商户管理','1','#','3','admin',now(),'','SM201600001000000000','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111510350776128','产品管理','1','#','1','admin',now(),'','SM2016111510330493381','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111510355949169','会员管理','1','#','2','admin',now(),'','SM2016111510330493381','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111510362607317','基础数据','1','#','3','admin',now(),'','SM2016111510330493381','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111510503080635','产品管理','1','/merchant/input.htm','1','admin',now(),'','SM2016111510350776128','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM201611151053121379','订单处理','1','/merchant/order.htm','3','admin',now(),'','SM2016111510350776128','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM201611151055259648','会员查询','1','/merchant/memQuery.htm','1','admin',now(),'','SM2016111510355949169','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111510564458473','消息推送','1','/merchant/message.htm','2','admin',now(),'','SM2016111510355949169','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111511020827441','业务配置','1','/merchant/configure.htm','1','admin',now(),'','SM2016111510362607317','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111511054647887','产品类别管理','1','/merchant/genre.htm','2','admin',now(),'','SM2016111510362607317','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111513535858948','增加','2','/add','1','admin',now(),'','SM2016111510503080635','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111513542942836','修改','2','/edit','3','admin',now(),'','SM2016111510503080635','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111513550543593','详情','2','/detail','4','admin',now(),'','SM2016111510503080635','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111515293904842','新增','2','/add','1','admin',now(),'','SM2016111510564458473','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111515301172665','修改','2','/edit','2','admin',now(),'','SM2016111510564458473','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111515304277518','详情','2','/detail','3','admin',now(),'','SM2016111510564458473','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM201611151618596835','增加','2','/add','1','admin',now(),'','SM2016111511054647887','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111516193382464','修改','2','/edit','2','admin',now(),'','SM2016111511054647887','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111516200165657','删除','2','/delete','3','admin',now(),'','SM2016111511054647887','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111614583781382','修改','2','/edit','1','admin',now(),'','SM2016111511020827441','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111615021523874','删除','2','/delete','3','admin',now(),'','SM2016111510503080635','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111615072749327','上架','2','/put','5','admin',now(),'','SM2016111510503080635','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111615075646173','下架','2','/pull','6','admin',now(),'','SM2016111510503080635','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111617482621219','取消退款','2','/refund','1','admin',now(),'','SM201611151053121379','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111617503076371','物流发货','2','/logistics','2','admin',now(),'','SM201611151053121379','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111617514987199','现场给货','2','/site','3','admin',now(),'','SM201611151053121379','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111617525666040','确认收货','2','/sure','4','admin',now(),'','SM201611151053121379','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111617541945270','快递异常','2','/abnormal','5','admin',now(),'','SM201611151053121379','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016111617545503974','详情','2','/detail','6','admin',now(),'','SM201611151053121379','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016112114571112079','积分流水','2','/integral','3','admin',now(),'','SM201611151055259648','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016112115582632850','冻结','2','/freeze-up','1','admin',now(),'','SM201611151055259648','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016112115593957631','解冻','2','/freeze-down','2','admin',now(),'','SM201611151055259648','1');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`kind`) VALUES ('SM2016112216130115559','发布','2','/release','4','admin',now(),'','SM2016111510564458473','1');

/*
-- Query: SELECT `code`,`name`,`level`,'admin' as `updater`,now() as 'update_datetime',`remark`,`kind` FROM tsys_role
LIMIT 0, 50000

-- Date: 2016-10-21 10:58
*/
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`kind`) VALUES ('SR201600000000000000','超级管理员','1','admin',now(),NULL,'1');
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`kind`) VALUES ('SR201600000000000012','普通商户','2','admin',now(),NULL,'1');

/*
-- Query: SELECT `role_code`,`menu_code`,`updater`,now() as `update_datetime`,`remark` FROM tsys_menu_role where role_code in('SR201600000000000000','SR201600000000000012')
LIMIT 0, 50000

-- Date: 2016-11-23 22:40
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM201600000000000000','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM201600001000000000','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM201600001000000001','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM201600001000000002','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM201600001000000003','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM201600001000000004','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101716290657836','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719140087629','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101716295904680','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719143965297','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101716241339082','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101716253866426','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101716450533995','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101717551955993','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101717560118734','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101717563661357','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101716261754674','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719082391126','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719094151894','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719100760088','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719110981215','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016102516555766535','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101716271123590','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719120926719','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719123215692','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719125359852','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719132278227','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719284086819','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719441184314','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719444119219','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719451349522','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719454161086','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM201610171939350218','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719463455794','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719470668795','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719472470447','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000000','SM2016101719474966791','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM201600000000000000','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM201600001000000000','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111510330493381','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111510350776128','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111510503080635','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111513535858948','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111513542942836','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111513550543593','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111615021523874','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111615072749327','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111615075646173','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM201611151053121379','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111617482621219','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111617503076371','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111617514987199','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111617525666040','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111617541945270','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111617545503974','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111510355949169','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM201611151055259648','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016112114571112079','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016112115582632850','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016112115593957631','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111510564458473','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111515293904842','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111515301172665','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111515304277518','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016112216130115559','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111510362607317','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111511020827441','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111614583781382','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111511054647887','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM201611151618596835','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111516193382464','admin',now(),NULL);
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`) VALUES ('SR201600000000000012','SM2016111516200165657','admin',now(),NULL);

/*
-- Query: select `to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,now() as update_datetime,`remark` from tsys_dict
LIMIT 0, 50000

-- Date: 2016-10-25 21:51
*/
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','0',NULL,'user_status','用户状态','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','user_status','0','正常','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','user_status','1','程序锁定','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','user_status','2','人工锁定','admin',now(),'');

INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','0',NULL,'id_kind','证件类型','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','id_kind','1','身份证','admin',now(),'');

INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','0',NULL,'role_level','角色等级','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','role_level','1','运维','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','role_level','2','运营','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','role_level','3','客户','admin',now(),'');

INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','0',NULL,'res_type','资源类型','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','res_type','1','菜单','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','res_type','2','按钮','admin',now(),'');

INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','0',NULL,'lock_direction','锁定方向','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','lock_direction','1','用锁','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','lock_direction','2','解锁','admin',now(),'');

INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','0',NULL,'order_status','订单状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','order_status','1','待支付','admin',now(),NULL);
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','order_status','2','已支付待发货','admin',now(),NULL);
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','order_status','3','已发货待收货','admin',now(),NULL);
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','order_status','4','已收货','admin',now(),NULL);
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','order_status','91','用户取消','admin',now(),NULL);
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','order_status','92','商户取消','admin',now(),NULL);
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','order_status','93','快递异常','admin',now(),NULL);

INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','0',NULL,'product_status','产品状态','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','product_status','0','待发布','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','product_status','1','已上架','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','product_status','2','已下架','admin',now(),'');

INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','0',NULL,'receipt_type','发票类型','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','receipt_type','1','个人','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','receipt_type','2','企业','admin',now(),'');

INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','0',NULL,'wl_company','物流公司','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','wl_company','EMS','邮政EMS','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','wl_company','STO','申通快递','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','wl_company','ZTO','中通快递','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','wl_company','YTO','圆通快递','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','wl_company','HTKY','汇通快递','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','wl_company','ZJS','宅急送','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','wl_company','SF','顺丰快递','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','wl_company','TTKD','天天快递','admin',now(),'');

INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','0',NULL,'banner_location','banner位置','admin',now(),'');
INSERT INTO `tsys_dict` (`to_system`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('8','1','banner_location','B_Mobile_SY_CSH','首页','admin',now(),'');

INSERT INTO `tstd_cnavigate` (`code`,`name`,`type`,`url`,`pic`,`status`,`location`,`order_no`,`belong`,`parent_code`,`remark`,`content_type`,`company_code`) VALUES ('DH2016100000000000101','广告图1','2','','http://114.55.179.135:8901/CSW20161031/banner1.png','1','B_Mobile_SY_CSH',1,'2',NULL,NULL,NULL,'0');
INSERT INTO `tstd_cnavigate` (`code`,`name`,`type`,`url`,`pic`,`status`,`location`,`order_no`,`belong`,`parent_code`,`remark`,`content_type`,`company_code`) VALUES ('DH2016100000000000102','广告图2','2','','http://114.55.179.135:8901/CSW20161031/banner2.png','1','B_Mobile_SY_CSH',2,'2',NULL,NULL,NULL,'0');
INSERT INTO `tstd_cnavigate` (`code`,`name`,`type`,`url`,`pic`,`status`,`location`,`order_no`,`belong`,`parent_code`,`remark`,`content_type`,`company_code`) VALUES ('DH2016100000000000103','广告图3','2','','http://114.55.179.135:8901/CSW20161031/banner3.png','1','B_Mobile_SY_CSH',3,'2',NULL,NULL,NULL,'0');
