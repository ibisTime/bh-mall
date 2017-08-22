INSERT INTO `tstd_user` (`user_id`,`login_name`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`role_code`,`status`,`create_datetime`,`remark`,`company_code`,`system_code`) VALUES ('UHY201700000000000001','admin','21218cca77804d2ba1922c33e0151105','1','P','0','HYR201700000000000001','0',now(),'管理端系统方','CD-CHY000015','CD-CHY000015');
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYR201700000000000001','超级管理员','1','admin',now(),NULL,'CD-CHY000015');

INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017032911200961325','修改','2','/edit','2','admin',now(),'','HYSM201700001000000003','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017033020005366333','banner管理','1','/public/banner.htm','1','xman',now(),'','HYSM201707251006045006005','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017033020015631166','新增','2','/add','1','admin',now(),'','HYSM2017033020005366333','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017033020021094115','修改','2','/edit','2','admin',now(),'','HYSM2017033020005366333','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017033020022649991','删除','2','/delete','3','admin',now(),'','HYSM2017033020005366333','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017033020024827112','详情','2','/detail','4','admin',now(),'','HYSM2017033020005366333','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017040621255637076','新手入门介绍','1','/biz/xs_rule.htm','7','admin',now(),'','HYSM2017121215543215610','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM20170612151451609525','签约协议','1','/biz/qy_protocol.htm','9','xman',now(),'','HYSM2017121215543215610','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM201707251006045006005','广告位管理','1','#','5','xman',now(),'','HYSM201700001000000001','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM201700000000000000','根目录','1','#','1','admin',now(),'','','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM201700001000000001','系统管理','1','#','1','admin',now(),'','HYSM201700000000000000','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM201700001000000002','运维管理','1','#','2','admin',now(),NULL,'HYSM201700001000000001','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM201700001000000003','菜单管理','1','/security/menu.htm','1','admin',now(),NULL,'HYSM201700001000000002','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM201700001000000004','新增','2','/add','1','admin',now(),'','HYSM201700001000000003','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017101716241339082','运营管理','1','#','1','admin',now(),'','HYSM201700001000000001','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017101716253866426','角色管理','1','/security/role.htm','1','admin',now(),'','HYSM2017101716241339082','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017101716261754674','用户管理','1','/security/user.htm','2','admin',now(),'','HYSM2017101716241339082','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017101716450533995','分配菜单','2','/change','4','admin',now(),'','HYSM2017101716253866426','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017101717551955993','新增','2','/add','1','admin',now(),'','HYSM2017101716253866426','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017101717560118734','修改','2','/edit','2','admin',now(),'','HYSM2017101716253866426','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017101717563661357','删除','2','/delete','3','admin',now(),'','HYSM2017101716253866426','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017101719082391126','新增','2','/add','1','admin',now(),'','HYSM2017101716261754674','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017101719094151894','重置密码','2','/reset','2','admin',now(),'','HYSM2017101716261754674','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017101719100760088','注销','2','/rock','4','admin',now(),'','HYSM2017101716261754674','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017101719110981215','设置角色','2','/assign','5','admin',now(),'','HYSM2017101716261754674','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017112911152991684','消息推送','1','#','3','xman',now(),'','HYSM201700001000000001','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017112911280249973','公告管理','1','/news/notice.htm','1','admin',now(),'','HYSM2017112911152991684','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017112914292031228','新增','2','/add','1','admin',now(),'','HYSM2017112911280249973','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017112914295002950','修改','2','/edit2','2','admin',now(),'','HYSM2017112911280249973','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017112914322481897','发布/撤下','2','/push','3','admin',now(),'','HYSM2017112911280249973','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017112914325471772','详情','2','/detail','5','admin',now(),'','HYSM2017112911280249973','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017120610552303416','激活','2','/active','3','admin',now(),'','HYSM2017101716261754674','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017121215543215610','文章管理','1','#','4','xman',now(),'','HYSM201700001000000001','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017121216045274832','关于我们','1','/biz/aboutus_addedit.htm','3','admin',now(),'','HYSM2017121215543215610','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM201701170252348209','下载地址','1','/biz/otherrule.htm','4','xman',now(),'','HYSM201700001000000002','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017011702525678266','修改','2','edit','1','admin',now(),'','HYSM201701170252348209','CD-CHY000015');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('HYSM2017022416243474533','注册协议','1','/biz/reg_protocol.htm','0','admin',now(),'','HYSM2017121215543215610','CD-CHY000015');

/*
-- Query: SELECT `code`,`name`,`level`,'admin' as `updater`,now() as 'update_datetime',`remark`,`system_code` FROM tsys_role where system_code = 'CD-CZH000001'
LIMIT 0, 10000

-- Date: 2017-03-31 10:33
*/
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','超级管理员','1','admin',now(),'','CD-CHY000015');

/*
-- Query: SELECT 'HYSR201700000000000000' `role_code`,code `menu_code`,'admin' `updater`,now() as `update_datetime`,'' `remark`,'CD-CHY000015' `system_code` FROM tsys_menu
-- Date: 2017-08-22 20:16
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM201700000000000000','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM201700001000000001','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM201700001000000002','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM201700001000000003','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM201700001000000004','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM201701170252348209','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017011702525678266','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017022416243474533','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017032911200961325','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017033020005366333','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017033020015631166','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017033020021094115','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017033020022649991','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017033020024827112','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017040621255637076','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM20170612151451609525','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM201707251006045006005','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017101716241339082','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017101716253866426','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017101716261754674','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017101716450533995','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017101717551955993','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017101717560118734','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017101717563661357','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017101719082391126','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017101719094151894','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017101719100760088','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017101719110981215','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017112911152991684','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017112911280249973','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017112914292031228','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017112914295002950','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017112914322481897','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017112914325471772','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017120610552303416','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017121215543215610','admin',now(),'','CD-CHY000015');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('HYSR201700000000000000','HYSM2017121216045274832','admin',now(),'','CD-CHY000015');

/*
-- Query: select `type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code` from tsys_config
-- Date: 2017-08-22 20:30
*/
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('qiniu','qiniu_access_key','cU832V2Gh5LoUWyNBLQ2kS9_0I-KnEF7jERyRDdh','admin',now(),NULL,'CD-CHY000015','CD-CHY000015');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('qiniu','qiniu_secret_key','ed5q5pSqKZZDN80gjGSkX93TmdDMTeZPVNd5bxgA','admin',now(),NULL,'CD-CHY000015','CD-CHY000015');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('qiniu','qiniu_bucket','hyds','admin',now(),NULL,'CD-CHY000015','CD-CHY000015');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('qiniu','qiniu_domain','ov30dsi08.bkt.clouddn.com','admin',now(),NULL,'CD-CHY000015','CD-CHY000015');

INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','about_us','关于我们','jkeg',now(),'关于我们','CD-CHY000015','CD-CHY000015');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','reg_protocol','注册协议','admin',now(),'注册协议','CD-CHY000015','CD-CHY000015');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','help_center','帮助中心','admin',now(),'帮助中心','CD-CHY000015','CD-CHY000015');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','time','09:00:00 - 17:30:00','admin',now(),'服务时间','CD-CHY000015','CD-CHY000015');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','telephone','0571-89000000','admin',now(),'服务电话','CD-CHY000015','CD-CHY000015');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('sys_txt','reg_url','http://hy.hichengdai.com','admin',now(),'注册链接','CD-CHY000015','CD-CHY000015');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_status','用户状态','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','0','正常','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','1','程序锁定','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','2','人工锁定','admin',now(),NULL,'CD-CHY000015');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'id_kind','证件类型','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','id_kind','1','身份证','admin',now(),NULL,'CD-CHY000015');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'role_level','角色等级','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','1','运维','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','2','运营','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','3','客户','admin',now(),NULL,'CD-CHY000015');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'res_type','资源类型','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','1','菜单','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','2','按钮','admin',now(),NULL,'CD-CHY000015');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'lock_direction','锁定方向','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','1','锁定','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','2','解锁','admin',now(),NULL,'CD-CHY000015');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_kind','针对人群','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','C','C端用户','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','P','平台用户','admin',now(),NULL,'CD-CHY000015');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'notice_status','公告状态','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','notice_status','0','未发布','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','notice_status','1','已发布','admin',now(),NULL,'CD-CHY000015');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','notice_status','2','已下架','admin',now(),NULL,'CD-CHY000015');