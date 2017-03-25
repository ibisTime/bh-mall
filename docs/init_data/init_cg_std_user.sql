/*
-- Query: SELECT * FROM std_user.tstd_user where login_name = 'admin' and  system_code = 'CD-CCG000007'
LIMIT 0, 10000

-- Date: 2017-03-25 17:26
*/
INSERT INTO `tstd_user` (`user_id`,`login_name`,`nickname`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`user_referee`,`mobile`,`id_kind`,`id_no`,`real_name`,`trade_pwd`,`trade_pwd_strength`,`role_code`,`status`,`pdf`,`amount`,`lj_amount`,`company_code`,`open_id`,`jpush_id`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCG201600000000000001','admin',NULL,'21218cca77804d2ba1922c33e0151105','1','01','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CCGR201600000000000000','0',NULL,NULL,NULL,NULL,NULL,NULL,'admin',NULL,'管理端系统方','CD-CCG000007');

/*
-- Query: SELECT code,name,type,url,order_no,updater, now() as update_datetime,remark,parent_code,system_code FROM tsys_menu where system_code = 'CD-CCG000007'
LIMIT 0, 10000

-- Date: 2017-03-25 17:27
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM201600000000000000','根目录','1','#','1','admin',now(),'','','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM201600001000000001','系统管理','1','#','1','admin',now(),'','CCGSM201600000000000000','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM201600001000000002','运维管理','1','#','2','admin',now(),'','CCGSM201600001000000001','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM201600001000000003','菜单管理','1','/security/menu.htm','1','admin',now(),'','CCGSM201600001000000002','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM201600001000000004','修改','2','/edit','2','admin',now(),'','CCGSM201600001000000003','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101716253866426','角色管理','1','/security/role.htm','1','admin',now(),'','SM2017032110563544277','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101716261754674','用户管理','1','/security/user.htm','2','admin',now(),'','SM2017032110563544277','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101716295904680','数据字典管理','1','/general/dict.htm','3','admin',now(),'','CCGSM201600001000000002','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101716450533995','分配菜单','2','/change','4','admin',now(),'','CCGSM2016101716253866426','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101717551955993','新增','2','/add','1','admin',now(),'','CCGSM201600001000000003','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101717560118734','修改','2','/edit','2','admin',now(),'','CCGSM2016101716253866426','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101717563661357','删除','2','/delete','3','admin',now(),'','CCGSM2016101716253866426','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101719082391126','新增','2','/add','1','admin',now(),'','CCGSM2016101716261754674','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101719094151894','重置密码','2','/reset','3','admin',now(),'','SM2017032113491674189','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101719100760088','注销/激活','2','/rock','4','admin',now(),'','SM2017032113491674189','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101719110981215','设置角色','2','/assign','5','admin',now(),'','CCGSM2016101716261754674','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101719140087629','修改','2','/edit','1','admin',now(),'','CCGSM2016101716290657836','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101719143965297','修改','2','/edit','2','admin',now(),'','CCGSM2016101716295904680','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032110392893832','新增','2','/add','1','admin',now(),'','CCGSM2016101716253866426','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032110563544277','运营管理','1','#','1','admin',now(),'','CCGSM201600001000000001','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032111004908332','平台管理','1','#','2','admin',now(),'','CCGSM201600000000000000','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032111135334551','系统参数设置','1','/general/param.htm','2','admin',now(),'','CCGSM201600001000000002','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032111164208375','修改','2','/edit','2','admin',now(),'','SM2017032111135334551','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703211121130805','新增','2','/add','1','admin',now(),'','CCGSM2016101716295904680','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703211358052199','新增','2','/add','1','admin',now(),'','SM2017032113491674189','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032114020974172','锁定','2','/locking','2','admin',now(),'','CCGSM2016101716261754674','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032114023217214','解锁','2','/unLock','3','admin',now(),'','CCGSM2016101716261754674','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032115230918954','删除','2','/delete','3','admin',now(),'','CCGSM2016101716295904680','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032115320529046','加盟商','1','#','3','admin',now(),'','CCGSM201600000000000000','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032115375150181','受款账户管理','1','/general/skAccount.htm','2','admin',now(),'','SM2017032110563544277','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032115462629024','我的账户','1','#','1','admin',now(),'','SM2017032111004908332','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032116515108986','摇钱树管理','1','#','2','admin',now(),'','SM2017032111004908332','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032116580703857','摇钱树管理','1','/platform/cashCow.htm','20','admin',now(),'','SM2017032116515108986','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032117014088035','摇一摇规则','1','/platform/shakeRule.htm','40','admin',now(),'','SM2017032116515108986','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032117153442628','夺宝管理','1','#','3','admin',now(),'','SM2017032111004908332','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703211718156943','新增','2','/add','1','admin',now(),'','SM2017032210354305644','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032117183616412','修改','2','/edit2','2','admin',now(),'','SM2017032210354305644','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032117282029312','详情','2','/detail','3','admin',now(),'','SM2017032210354305644','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703211728354320','上架','2','/up','4','admin',now(),'','SM2017032210354305644','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703211728518555','下架','2','/down','5','admin',now(),'','SM2017032210354305644','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032210354305644','摇钱树模板','1','/platform/cashCowTp.htm','10','admin',now(),'','SM2017032116515108986','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032210400802828','冻结/解冻','2','/upDown','1','admin',now(),'','SM2017032116580703857','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703221040581383','摇一摇记录','2','/shakeRecord','2','admin',now(),'','SM2017032116580703857','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032210454810734','红包记录','2','/luckMomList','3','admin',now(),'','SM2017032116580703857','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032211194568931','修改','2','/edit','1','admin',now(),'','SM2017032117014088035','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032213223669717','定向红包规则','1','/platform/hbRule.htm','50','admin',now(),'','SM2017032116515108986','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703221322552773','修改','2','/edit','1','admin',now(),'','SM2017032213223669717','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703221336596893','夺宝模板','1','/platform/dbTp.htm','1','admin',now(),'','SM2017032117153442628','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032214114468759','新增','2','/add','1','admin',now(),'','SM201703221336596893','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703221412258772','修改','2','/edit2','2','admin',now(),'','SM201703221336596893','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032214124268017','上架','2','/up','4','admin',now(),'','SM201703221336596893','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032214130632938','下架','2','/down','5','admin',now(),'','SM201703221336596893','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032214142605813','详情','2','/detail','3','admin',now(),'','SM201703221336596893','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032215211295825','期数查询','1','/platform/dbQuery.htm','2','admin',now(),'','SM2017032117153442628','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032215215115350','参与详情','2','/joinDetail','1','admin',now(),'','SM2017032215211295825','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032220464239536','定向红包查询','1','/platform/hbQuery.htm','30','admin',now(),'','SM2017032116515108986','CD-CCG000007');

/*
-- Query: SELECT `code`,`name`,`level`,'admin' as `updater`,now() as 'update_datetime',`remark`,`system_code` FROM tsys_role where name = '超级管理员' and system_code = 'CD-CCG000007'
LIMIT 0, 10000

-- Date: 2017-03-25 17:28
*/
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','超级管理员','1','admin',now(),NULL,'CD-CCG000007');

/*
-- Query: SELECT `role_code`,`menu_code`,`updater`,now() as `update_datetime`,`remark`,`system_code` FROM tsys_menu_role where role_code in('CCGR201600000000000000')
LIMIT 0, 10000

-- Date: 2017-03-25 17:29
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM201600000000000000','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM201600001000000001','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM201600001000000002','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM201600001000000003','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM201600001000000004','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101717551955993','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101716295904680','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101719143965297','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM201703211121130805','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032115230918954','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032111135334551','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032111164208375','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032110563544277','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101716253866426','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101716450533995','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101717560118734','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101717563661357','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032110392893832','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101716261754674','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101719082391126','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101719110981215','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032114020974172','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032114023217214','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032115375150181','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032111004908332','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032115462629024','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032116515108986','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032116580703857','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032210400802828','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM201703221040581383','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032210454810734','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032117014088035','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032211194568931','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032210354305644','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM201703211718156943','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032117183616412','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032117282029312','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM201703211728354320','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM201703211728518555','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032213223669717','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM201703221322552773','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032220464239536','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032117153442628','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM201703221336596893','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032214114468759','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM201703221412258772','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032214124268017','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032214130632938','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032214142605813','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032215211295825','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032215215115350','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM2017032115320529046','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101719094151894','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101719100760088','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','CCGSM2016101719140087629','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000000','SM201703211358052199','admin',now(),NULL,'CD-CCG000007');

/*
-- Query: SELECT code,type,account,password,remark,company_code,system_code FROM std_user.tstd_cpassword where system_code = 'CD-CCG000007'
LIMIT 0, 10000

-- Date: 2017-03-25 17:31
*/
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CG001','2','ACCESS_KEY','Dc0pMP8ImFm78-uk4iGsOPpB2-vHc64D07OsOQVi','','CD-CCG000007','CD-CCG000007');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CG002','2','SECRET_KEY','3NP-tpZP9-5fH-R-FhvKTfYpPPVFNvjFF3JXmrcq','','CD-CCG000007','CD-CCG000007');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CG003','2','bucket','b2coss','','CD-CCG000007','CD-CCG000007');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CG004','3','ACCESS_KEY','wx8bc03dd744895352','','CD-CCG000007','CD-CCG000007');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CG005','3','SECRET_KEY','44ebf0ef908dc54656573625a579ea82','','CD-CCG000007','CD-CCG000007');

/*
-- Query: select `type`,`parent_key`,`dkey`,`dvalue`,`updater`,now() as update_datetime,`remark`,`system_code` from tsys_dict where system_code = 'CD-CCG000007'
LIMIT 0, 10000

-- Date: 2017-03-25 17:24
*/
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_status','用户状态','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','0','正常','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','1','程序锁定','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','2','人工锁定','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'id_kind','证件类型','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','id_kind','1','身份证','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'role_level','角色等级','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','1','运维','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','2','运营','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','3','客户','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'res_type','资源类型','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','1','菜单','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','2','按钮','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'lock_direction','锁定方向','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','1','锁定','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','2','解锁','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_status','合伙人状态','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','0','正常','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','1','程序锁定','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','2','人工锁定','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_kind','针对人群','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','f1','C端用户','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','f2','B端用户','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','01','平台用户','admin',now(),NULL,'CD-CCG000007');

