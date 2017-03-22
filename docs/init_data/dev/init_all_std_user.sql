delete from tstd_user where system_code = '#';
/*
-- Query: select `user_id`, `login_name`, `nickname`, `login_pwd`, `login_pwd_strength`, `kind`, `level`, `user_referee`, `mobile`, `id_kind`, `id_no`, `real_name`, `trade_pwd`, `trade_pwd_strength`, `role_code`, `status`, `pdf`, `amount`, `lj_amount`, `company_code`, `open_id`, `jpush_id`, `updater`, `update_datetime`, `longitude`, `latitude`, `remark`, `system_code` from tstd_user where login_name = 'admin'
LIMIT 0, 10000

-- Date: 2017-03-05 22:51
*/
INSERT INTO `tstd_user` (`user_id`,`login_name`,`nickname`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`user_referee`,`mobile`,`id_kind`,`id_no`,`real_name`,`trade_pwd`,`trade_pwd_strength`,`role_code`,`status`,`pdf`,`amount`,`lj_amount`,`company_code`,`open_id`,`jpush_id`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGD201600000000000001','admin',NULL,'21218cca77804d2ba1922c33e0151105','1','01','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CGDR201600000000000000','0',NULL,NULL,NULL,NULL,NULL,NULL,'admin',NULL,'管理端系统方','CD-CGD000006');

delete from tsys_menu where system_code = '#';
/*
-- Query: select `code`, `name`, `type`, `url`, `order_no`, `updater`,now() as `update_datetime`, `remark`, `parent_code`, `system_code` from tsys_menu
LIMIT 0, 10000

-- Date: 2017-03-12 14:26
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM201600000000000000','根目录','1','#','1','admin',now(),'','','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM201600001000000001','系统管理','1','#','1','admin',now(),'','CGDSM201600000000000000','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM201600001000000002','运维管理','1','#','2','admin',now(),NULL,'CGDSM201600001000000001','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM201600001000000003','菜单管理','1','/security/menu.htm','1','admin',now(),'','CGDSM201600001000000002','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM201600001000000004','修改','2','/edit','2','admin',now(),'','CGDSM201600001000000003','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101716241339082','运营管理','1','#','1','admin',now(),'','CGDSM201600001000000001','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101716253866426','角色管理','1','/security/role.htm','1','admin',now(),'','CGDSM2016101716241339082','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101716261754674','用户管理','1','/security/user.htm','2','admin',now(),'','CGDSM2016101716241339082','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101716295904680','数据字典管理','1','/general/dict.htm','3','admin',now(),'','CGDSM201600001000000002','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101716450533995','分配菜单','2','/change','4','admin',now(),'','CGDSM2016101716253866426','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101717551955993','新增','2','/add','1','admin',now(),'','CGDSM2016101716253866426','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101717560118734','修改','2','/edit','2','admin',now(),'','CGDSM2016101716253866426','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101717563661357','删除','2','/delete','3','admin',now(),'','CGDSM2016101716253866426','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101719082391126','新增','2','/add','1','admin',now(),'','CGDSM2016101716261754674','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101719094151894','重置密码','2','/reset','3','admin',now(),'','CGDSM2016101716261754674','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101719100760088','注销/激活','2','/rock','4','admin',now(),'','CGDSM2016101716261754674','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101719110981215','设置角色','2','/assign','5','admin',now(),'','CGDSM2016101716261754674','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101719140087629','修改','2','/edit','1','admin',now(),'','CGDSM2016101716290657836','CD-CGD000006');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CGDSM2016101719143965297','修改','2','/edit','1','admin',now(),'','CGDSM2016101716295904680','CD-CGD000006');

delete from tsys_role where system_code = 'CD-CGD000006';
/*
-- Query: select `code`, `name`, `level`, `updater`, `update_datetime`, `remark`, `system_code` from tsys_role
LIMIT 0, 10000

-- Date: 2017-03-05 22:53
*/
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','超级管理员','1','admin',now(),NULL,'CD-CGD000006');

delete from tsys_menu_role where system_code = 'CD-CGD000006';

/*
-- Query: select `role_code`, `menu_code`, `updater`, now() as `update_datetime`, `remark`, `system_code` from tsys_menu_role
LIMIT 0, 10000

-- Date: 2017-03-12 14:27
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDR201600000000000000','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM201600001000000000','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM201600001000000001','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM201600001000000002','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM201600001000000003','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM201600001000000004','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017021018033855940','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101716290657836','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101719140087629','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101716295904680','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101719143965297','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030815112816426','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017031114511245839','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030620442366911','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030620450403033','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101716241339082','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101716253866426','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101716450533995','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101717551955993','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101717560118734','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101717563661357','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101716261754674','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101719082391126','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101719094151894','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101719100760088','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','CGDSM2016101719110981215','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020311025413018','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201612231757239863','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020314523573980','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020314532282169','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020316151295859','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020316154848198','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020316162500797','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020316173879220','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020316183105161','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702031619375004','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030516042495495','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030719533013547','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020321493071389','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020321502736515','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020321512991994','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020321520027875','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020321523489618','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020321532639262','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020409590183357','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020410032770545','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020411274396493','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416332943266','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702041639081128','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416394591255','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416402487218','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416411460349','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416414258318','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416422038038','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020817072970932','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416363501158','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416455303771','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416462552265','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416465032938','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416493412398','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416501233842','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020913115487299','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030117251147961','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416381078436','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020416521660151','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020413511816440','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414103390461','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414201469247','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414205782357','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414213057735','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414221960399','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414273444146','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030918023787091','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610213249273','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610281550771','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610351229968','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610353811462','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610355993896','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702061036220433','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610364645372','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201703021759408746','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610293929078','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610383010638','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610220283186','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610314371252','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610405772145','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610412351224','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610414515137','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610421971334','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702181640584617','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201703031612336849','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610235240129','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020617183991116','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020617390170335','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020617434980211','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020617444278531','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020617452005555','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020617455861584','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022219495266557','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702071128274431','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020711290510772','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020711293108351','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702221548191711','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702221552062943','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022215491296977','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022215525007632','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030710495368466','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030710511904933','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030710561096025','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020610250213999','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615204950650','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615453919280','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615461994087','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615465815529','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615473285543','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020714592629311','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702071502453323','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020715031436278','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020715034821216','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017031115324112760','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017021020085520798','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017021020101576518','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017021020250061294','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017021314503390389','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022411425809511','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022411441299333','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022411443824710','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022411451795785','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022116285947571','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020409575729352','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020410000738295','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020410020389285','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702041002356230','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022411130723924','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020410010671464','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022117491861052','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022216182276584','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414112043154','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414354598023','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414361608190','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414364626171','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414371082673','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414373715191','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017021615243603648','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414123987370','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702041438208006','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414384501063','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414391122399','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414393784378','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020414400358599','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615130333038','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615384540247','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615394619392','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615402074332','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702221120287974','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017022314305157673','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615174171710','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615411982039','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615415208875','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017021520103970012','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030318083987467','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030318093645753','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615184124216','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702061542287572','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615430947091','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020816112771138','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201703061739367605','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030617402509685','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615193118584','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615435610117','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020615442236438','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017021620033844868','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030215265114084','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017030215273743968','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020312424032896','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM201702031244065461','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020312470827065','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020313185760856','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020313192638078','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020313202638576','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020313205388797','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020312482965449','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020313350273821','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020313361190790','admin',now(),NULL,'CD-CGD000006');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGDR201600000000000000','SM2017020313365469973','admin',now(),NULL,'CD-CGD000006');

delete from tsys_dict where system_code = 'CD-CGD000006';
/*
-- Query: select `system_code`, `type`, `parent_key`, `dkey`, `dvalue`, `updater`,now() as `update_datetime`, `remark` from tsys_dict
LIMIT 0, 10000

-- Date: 2017-03-09 16:16
*/
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0',NULL,'user_status','用户状态','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','user_status','0','正常','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','user_status','1','程序锁定','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','user_status','2','人工锁定','admin',now(),'');

INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0',NULL,'id_kind','证件类型','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','id_kind','1','身份证','admin',now(),'');

INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0',NULL,'res_type','资源类型','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','res_type','1','菜单','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','res_type','2','按钮','admin',now(),'');

INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0',NULL,'lock_direction','锁定方向','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','lock_direction','1','用锁','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','lock_direction','2','解锁','admin',now(),'');

INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0',NULL,'role_level','角色等级','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','role_level','1','管理员','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','role_level','2','运营','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','role_level','3','运维','admin',now(),'');

INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0','','account_type','账户类型','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','account_type','CNY','人民币账户','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','account_type','XNB','虚拟币账户','admin',now(),'');

INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0',NULL,'currency_type','币种状态','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','currency_type','CNY','人民币','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','currency_type','XNB','虚拟币','admin',now(),'');

INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0',NULL,'account_status','用户/账户状态','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','account_status','0','正常','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','account_status','1','程序冻结','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','account_status','2','人工冻结','admin',now(),'');

INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0','','account_kind','账户类型','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','account_kind','C','C端用户 ','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','account_kind','B','B端用户','admin',now(),'');
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','account_kind','P','平台','admin',now(),'');

INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0',NULL,'notice_status','公告状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','notice_status','0','未发布','admin',now(),NULL);
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','notice_status','1','已发布','admin',now(),NULL);
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','notice_status','2','已下架','admin',now(),NULL);

INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0',NULL,'toKind','针对人群','admin',now(),NULL);
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','toKind','0','b端用户','admin',now(),NULL);
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','toKind','1','c端用户','admin',now(),NULL);

INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','0',NULL,'news_status','消息状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','news_status','0','发送失败','admin',now(),NULL);
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','news_status','1','已发送','admin',now(),NULL);
INSERT INTO `tsys_dict` (`system_code`,`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('CD-CGD000006','1','news_status','2','发送失败','admin',now(),NULL);

delete from tstd_cpassword where system_code = 'CD-CGD000006';
/*
-- Query: SELECT `code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code` FROM tstd_cpassword
LIMIT 0, 10000

-- Date: 2017-03-12 14:52
*/
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('GD001','2','ACCESS_KEY','Dc0pMP8ImFm78-uk4iGsOPpB2-vHc64D07OsOQVi','','CD-CGD000006','CD-CGD000006');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('GD002','2','SECRET_KEY','3NP-tpZP9-5fH-R-FhvKTfYpPPVFNvjFF3JXmrcq','','CD-CGD000006','CD-CGD000006');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('GD003','2','bucket','b2coss','','CD-CGD000006','CD-CGD000006');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('GD004','3','ACCESS_KEY','wx8bc03dd744895352','','CD-CGD000006','CD-CGD000006');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('GD005','3','SECRET_KEY','44ebf0ef908dc54656573625a579ea82','','CD-CGD000006','CD-CGD000006');