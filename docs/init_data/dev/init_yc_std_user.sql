/*
-- Query: SELECT ckey,cvalue,note,updater, now() as update_datetime,remark,system_code FROM tsys_config where system_code = 'CD-CYC000009'
LIMIT 0, 10000

-- Date: 2017-04-05 23:16
*/
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('aboutus','关于我们','关于我们','admin',now(),NULL,'CD-CYC000009','CD-CYC000009');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('reg_protocol','注册协议','注册协议','admin',now(),NULL,'CD-CYC000009','CD-CYC000009');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('telephone','服务热线','0571-86162013','admin',now(),NULL,'CD-CYC000009','CD-CYC000009');

/*
-- Query: SELECT * FROM std_user.tstd_cnavigate where system_code = 'CD-CYC000009'
-- Date: 2017-05-25 19:06
*/
INSERT INTO `tstd_cnavigate` (`code`,`name`,`type`,`url`,`pic`,`status`,`location`,`order_no`,`belong`,`parent_code`,`remark`,`content_type`,`company_code`,`system_code`) VALUES ('DH2017052416405468812','banner','2','http://www.baidu.com','橙币-banner_1495615303157.png','1','index_banner',1,'1','0','','1','CD-CYC000009','CD-CYC000009');
INSERT INTO `tstd_cnavigate` (`code`,`name`,`type`,`url`,`pic`,`status`,`location`,`order_no`,`belong`,`parent_code`,`remark`,`content_type`,`company_code`,`system_code`) VALUES ('DH2017052416411344623','banner橙币','2','','橙币-banner2_1495615337780.jpg','1','index_banner',2,'1','0','','1','CD-CYC000009','CD-CYC000009');


/*
-- Query: SELECT `user_id`,`login_name`,`nickname`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`user_referee`,`mobile`,`id_kind`,`id_no`,`real_name`,`trade_pwd`,`trade_pwd_strength`,`role_code`,`status`,`pdf`,`company_code`,`open_id`,`jpush_id`,`updater`,`update_datetime`,`remark`,`system_code` FROM std_user.tstd_user where system_code = 'CD-CYC000009'
LIMIT 0, 10000

-- Date: 2017-03-25 17:26
*/
INSERT INTO `tstd_user` (`user_id`,`login_name`,`nickname`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`user_referee`,`mobile`,`id_kind`,`id_no`,`real_name`,`trade_pwd`,`trade_pwd_strength`,`role_code`,`status`,`pdf`,`company_code`,`open_id`,`jpush_id`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYC201600000000000001','admin',NULL,'21218cca77804d2ba1922c33e0151105','1','01','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SR2015032821443420391','0',NULL,NULL,NULL,NULL,'admin',NULL,'管理端系统方','CD-CYC000009');
INSERT INTO `tstd_user` (`user_id`,`login_name`,`nickname`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`user_referee`,`mobile`,`id_kind`,`id_no`,`real_name`,`trade_pwd`,`trade_pwd_strength`,`role_code`,`status`,`pdf`,`company_code`,`open_id`,`jpush_id`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SYS_USER_YAOCHENG','yaocheng',NULL,'21218cca77804d2ba1922c33e0151105','1','01','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CYCR201500000000000001','0',NULL,NULL,NULL,NULL,'admin','2017-03-28 21:43:29','','CD-CYC000009');


/*
-- Query: SELECT `code`,`name`,`level`,'admin' as `updater`,now() as 'update_datetime',`remark`,`system_code` FROM tsys_role where system_code = 'CD-CYC000009'
LIMIT 0, 10000

-- Date: 2017-03-28 21:57
*/
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','超级管理员','1','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','加盟商','2','admin',now(),'','CD-CYC000009');
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','系统管理员','1','admin',now(),'','CD-CYC000009');

/*
-- Query: SELECT code,name,type,url,order_no,updater, now() as update_datetime,remark,parent_code,system_code FROM tsys_menu where system_code = 'CD-CYC000009'
-- Date: 2017-05-25 18:58
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM201500000000000000','根目录','1','#','1','admin',now(),'','','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM201500001000000001','系统管理','1','#','1','admin',now(),'','CYCSM201500000000000000','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM201500001000000002','运维管理','1','#','2','admin',now(),'','CYCSM201500001000000001','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM201500001000000003','菜单管理','1','/security/menu.htm','1','admin',now(),'','CYCSM201500001000000002','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM201500001000000004','修改','2','/edit','2','admin',now(),'','CYCSM201500001000000003','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM2015101716253866426','角色管理','1','/security/role.htm','1','admin',now(),'','SM2015032110563544277','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM2015101716261754674','用户管理','1','/security/user.htm','2','admin',now(),'','SM2015032110563544277','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM2015101716295904680','数据字典管理','1','/general/dict.htm','3','admin',now(),'','CYCSM201500001000000002','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM2015101716450533995','分配菜单','2','/change','4','admin',now(),'','CYCSM2015101716253866426','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM2015101717551955993','新增','2','/add','1','admin',now(),'','CYCSM201500001000000003','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM2015101717560118734','修改','2','/edit','2','admin',now(),'','CYCSM2015101716253866426','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM2015101717563661357','删除','2','/delete','3','admin',now(),'','CYCSM2015101716253866426','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM2015101719082391126','新增','2','/add','1','admin',now(),'','CYCSM2015101716261754674','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM2015101719110981215','设置角色','2','/assign','5','admin',now(),'','CYCSM2015101716261754674','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CYCSM2015101719143965297','修改','2','/edit','2','admin',now(),'','CYCSM2015101716295904680','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032110392893832','新增','2','/add','1','admin',now(),'','CYCSM2015101716253866426','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032110563544277','运营管理','1','#','1','admin',now(),'','CYCSM201500001000000001','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032111004908332','平台管理','1','#','2','admin',now(),'','CYCSM201500000000000000','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032111135334551','系统参数设置','1','/general/param.htm','2','admin',now(),'','CYCSM201500001000000002','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032111164208375','修改','2','/edit','2','admin',now(),'','SM2015032111135334551','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201503211121130805','新增','2','/add','1','admin',now(),'','CYCSM2015101716295904680','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032114020974172','锁定','2','/locking','3','admin',now(),'','CYCSM2015101716261754674','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032114023217214','解锁','2','/unLock','4','admin',now(),'','CYCSM2015101716261754674','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032115230918954','删除','2','/delete','3','admin',now(),'','CYCSM2015101716295904680','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032115320529046','加盟商','1','#','3','admin',now(),'','CYCSM201500000000000000','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032115462629024','我的账户','1','#','10','admin',now(),'','SM2015032111004908332','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032517423397534','商城管理','1','#','30','admin',now(),'','SM2015032111004908332','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032517492791638','商品类别管理','1','/product/category.htm','10','admin',now(),'','SM2015032517423397534','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032517495892729','新增','2','/add','1','admin',now(),'','SM2015032517492791638','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032517501280632','修改','2','/edit2','2','admin',now(),'','SM2015032517492791638','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032517502765755','上架','2','/up','4','admin',now(),'','SM2015032517492791638','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032517504971878','下架','2','/down','5','admin',now(),'','SM2015032517492791638','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032615195207380','商品管理','1','/product/product.htm','20','admin',now(),'','SM2015032517423397534','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201503261520244332','新增','2','/add','1','admin',now(),'','SM2015032615195207380','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201503261520436243','修改','2','/edit2','2','admin',now(),'','SM2015032615195207380','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032615210645370','删除','2','/delete','3','admin',now(),'','SM2015032615195207380','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032615213092920','详情','2','/detail2','4','admin',now(),'','SM2015032615195207380','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032615224289281','上架','2','/up2','5','admin',now(),'','SM2015032615195207380','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032615225688297','下架','2','/down','6','admin',now(),'','SM2015032615195207380','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032713365270841','产品参数','2','/productParam','7','admin',now(),'','SM2015032615195207380','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032716101314297','商家管理','1','#','10','admin',now(),'','SM2015032115320529046','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032716104252541','我的商家','1','/store/store.htm','10','admin',now(),'','SM2015032716101314297','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032716111198076','入驻商家','2','/add','1','admin',now(),'','SM2015032716104252541','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032716113221592','审核','2','/examine','20','admin',now(),'','SM2015032716104252541','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032716141802593','上架','2','/up2','30','admin',now(),'','SM2015032716104252541','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032716143658792','下架','2','/down','40','admin',now(),'','SM2015032716104252541','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032716153420883','详情','2','/detail2','50','admin',now(),'','SM2015032716104252541','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032718005584448','O2O管理','1','#','20','admin',now(),'','SM2015032111004908332','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032718013959184','商家类别管理','1','/platform/storeCategory.htm','1','admin',now(),'','SM2015032718005584448','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032718020246126','新增','2','/add','1','admin',now(),'','SM2015032718013959184','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032718021506884','修改','2','/edit','2','admin',now(),'','SM2015032718013959184','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201503271802290866','上架','2','/up','3','admin',now(),'','SM2015032718013959184','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032718034531686','下架','2','/down','4','admin',now(),'','SM2015032718013959184','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032719033447798','订单处理','1','/product/order.htm','30','admin',now(),'','SM2015032517423397534','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032719054525781','物流发货','2','/sendOutGoods','1','admin',now(),'','SM2015032719033447798','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032719065095891','现场发货','2','/spotDelivery','2','admin',now(),'','SM2015032719033447798','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032719073017798','取消订单','2','/cancelOrder','3','admin',now(),'','SM2015032719033447798','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032719080275648','确认收货','2','/confirmOrder','4','admin',now(),'','SM2015032719033447798','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201503271908263870','详情','2','/detail','5','admin',now(),'','SM2015032719033447798','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201503281400585289','订单处理','1','/store/order.htm','1','admin',now(),'','SM2015032814152244462','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032814034964260','现场发货','2','/spotDelivery','2','admin',now(),'','SM201503281400585289','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032814040701331','取消订单','2','/cancelOrder','3','admin',now(),'','SM201503281400585289','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032814045201792','详情','2','/detail','5','admin',now(),'','SM201503281400585289','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032814152244462','商城管理','1','#','20','admin',now(),'','SM2015032115320529046','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032814280820460','客户管理','1','#','60','admin',now(),'','SM2015032111004908332','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032814282786586','财务管理','1','#','70','admin',now(),'','SM2015032111004908332','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032815341153189','我的账户','1','/platform/account.htm','10','admin',now(),'','SM2015032115462629024','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032816395662792','加盟商管理','1','/platform/AllianceBusiness.htm','10','admin',now(),'','SM2015032814280820460','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032816453831766','商家查询','1','/platform/business.htm','20','admin',now(),'','SM2015032814280820460','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032816471476911','会员查询','1','/platform/member.htm','30','admin',now(),'','SM2015032814280820460','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032817101406466','线下充值','1','/platform/lineRecharge.htm','10','admin',now(),'','SM2015032814282786586','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032817104102180','线下取现','1','/platform/lineUnder.htm','20','admin',now(),'','SM2015032814282786586','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032817154633256','充值查询','1','/platform/historyQuery.htm','30','admin',now(),'','SM2015032814282786586','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032817343941744','我的账户','1','#','5','admin',now(),'','SM2015032115320529046','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032817375340722','我的账户','1','/store/account.htm','10','admin',now(),'','SM2015032817343941744','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032819353033392','代注册','2','/add','0','admin',now(),'','SM2015032816395662792','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032819555604844','发货情况','2','/fahuo','2','admin',now(),'','SM2015032816395662792','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032819561324334','代销情况','2','/daixiao','3','admin',now(),'','SM2015032816395662792','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032820190275624','详情','2','/detail2','5','admin',now(),'','SM2015032816395662792','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032821100699155','账户查询','2','/accountQuery','1','admin',now(),'','SM2015032816471476911','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015032821314105573','详情','2','/detail2','10','admin',now(),'','SM2015032816471476911','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201503282210346965','完善客户信息','2','/info','0','admin',now(),'','SM2015032816471476911','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033115104850917','营销规则','1','#','90','admin',now(),'','SM2015032111004908332','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033115111510393','送橙币规则','1','/rule/rule.htm','1','admin',now(),'','SM2015033115104850917','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033117394389125','重置密码','2','/reset','2','admin',now(),'','CYCSM2015101716261754674','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033118080150917','文章管理','1','#','91','admin',now(),'','SM2015032111004908332','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033118095013031','banner管理','1','/public/banner.htm','0','admin',now(),'','SM2015033118080150917','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033118103738281','新增','2','/add','1','admin',now(),'','SM2015033118095013031','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033118105111062','修改','2','/edit','2','admin',now(),'','SM2015033118095013031','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033118112048197','删除','2','/delete','3','admin',now(),'','SM2015033118095013031','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033118113511496','详情','2','/detail','4','admin',now(),'','SM2015033118095013031','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033120375148323','营收流水','2','/yy','51','admin',now(),'','SM2015032716104252541','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033120383090825','代销情况','2','/daixiao','53','admin',now(),'','SM2015032716104252541','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201503312048586062','商家管理','1','/platform/store.htm','2','admin',now(),'','SM2015032718005584448','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033120501071069','上架','2','/up2','3','admin',now(),'','SM201503312048586062','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033120511886659','下架','2','/down','4','admin',now(),'','SM201503312048586062','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033121113217540','导出','2','/export','7','admin',now(),'','SM2015032719033447798','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033121114642345','导出','2','/export','7','admin',now(),'','SM201503281400585289','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033122324657221','线下充值','2','/add','1','admin',now(),'','SM2015032817101406466','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015033122330444499','线下取现','2','/add','1','admin',now(),'','SM2015032817104102180','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201503312233317182','审核','2','che','2','admin',now(),'','SM2015032817101406466','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201503312233449950','审核','2','che','2','admin',now(),'','SM2015032817104102180','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040114513582931','公告管理','1','#','92','admin',now(),'','SM2015032111004908332','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040115400265469','公告管理','1','/message/notice.htm','1','admin',now(),'','SM2015040114513582931','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040115434479328','新增','2','/add','1','admin',now(),'','SM2015040115400265469','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040115443032316','发布/撤下','2','/push','3','admin',now(),'','SM2015040115400265469','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040115444938511','修改','2','/edit','2','admin',now(),'','SM2015040115400265469','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040116043257469','修改','2','/edit','1','admin',now(),'','SM2015033115111510393','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040515030890573','结算情况','2','/balance','0','admin',now(),'','SM2015032816453831766','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040515034112569','回收情况','2','/return','2','admin',now(),'','SM2015032816453831766','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201504051504007317','详情','2','/detail2','3','admin',now(),'','SM2015032816453831766','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040517011442277','导出','2','/export','90','admin',now(),'','SM2015032816395662792','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040517015279288','导出','2','/export','90','admin',now(),'','SM2015032816453831766','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201504051702213592','导出','2','/export','90','admin',now(),'','SM2015032816471476911','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040522524135569','关于我们','1','/public/aboutUs_addedit.htm','2','admin',now(),'','SM2015033118080150917','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015040522554348136','服务热线','1','/public/tel_addedit.htm','3','admin',now(),'','SM2015033118080150917','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015041910113586962','修改','2','/edit','1','admin',now(),'','SM2015032816395662792','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201504191046584135','修改','2','/edit2','10','admin',now(),'','SM2015032716104252541','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201504191658403636','详情','2','/deta','3','admin',now(),'','SM2015032817101406466','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015041916585392724','详情','2','/deta','3','admin',now(),'','SM2015032817104102180','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042116284496966','币种兑换规则','1','/rule/bzRule.htm','2','admin',now(),'','SM2015033115104850917','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042116290614925','修改','2','/edit','1','admin',now(),'','SM2015042116284496966','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042121533440724','营收流水','2','/yy','1','admin',now(),'','SM201503312048586062','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042121541681889','代销情况','2','/daixiao','11','admin',now(),'','SM201503312048586062','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042316305712363','充值专区管理','1','#','51','admin',now(),'','SM2015032111004908332','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042316323814062','虚拟产品管理','1','/product/vproduct.htm','1','admin',now(),'','SM2015042316305712363','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042316372961065','虚拟订单处理','1','/product/vorder.htm','2','admin',now(),'','SM2015042316305712363','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042316382479912','修改','2','/edit2','1','admin',now(),'','SM2015042316323814062','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042316385361318','上架','2','/up','2','admin',now(),'','SM2015042316323814062','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042316392348091','下架','2','/down','3','admin',now(),'','SM2015042316323814062','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042316400964384','详情','2','/detail2','4','admin',now(),'','SM2015042316323814062','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042316410397430','取消','2','/cannel','1','admin',now(),'','SM2015042316372961065','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042316413193265','兑换','2','/exchange','2','admin',now(),'','SM2015042316372961065','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015042316414802760','详情','2','/detail2','3','admin',now(),'','SM2015042316372961065','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015050215483017927','取现规则','1','/rule/enchashmentRule.htm','50','admin',now(),'','SM2015032814282786586','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201505021549048393','修改','2','/edit','1','admin',now(),'','SM2015050215483017927','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015050316194220024','详情','2','/detail2','6','admin',now(),'','SM201503312048586062','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015050316481743795','返点规则','1','/rule/o2oRule.htm','3','admin',now(),'','SM2015032718005584448','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015050316483632046','修改','2','/edit','1','admin',now(),'','SM2015050316481743795','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015050320244030571','详情','2','/deta','1','admin',now(),'','SM2015032817154633256','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2015050419263008518','审核','2','/examine','2','admin',now(),'','SM201503312048586062','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017052218551217919','回录','2','/huilu','2','admin',now(),'','SM2015032817104102180','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017052219031320215','取现查询','1','/platform/lineUnderStory.htm','40','admin',now(),'','SM2015032814282786586','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017052219084519751','详情','2','/deta','1','admin',now(),'','SM2017052219031320215','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017052614332372049','卡券管理','1','/rule/card.htm','3','admin','2017-05-26 14:33:23','','SM2015033115104850917','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017052614334823153','新增','2','/add','1','admin','2017-05-26 14:33:48','','SM2017052614332372049','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017052614341287840','作废','2','/cancel','2','admin','2017-05-26 14:34:12','','SM2017052614332372049','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017052614344381469','详情','2','/detail','3','admin','2017-05-26 15:01:00','','SM2017052614332372049','CD-CYC000009');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201705261435124340','下载二维码','2','/download','4','admin','2017-05-26 14:35:12','','SM2017052614332372049','CD-CYC000009');

/*
-- Query: SELECT `role_code`,`menu_code`,`updater`,now() as `update_datetime`,`remark`,`system_code` FROM tsys_menu_role where role_code in('CYCR201500000000000001','YCSR201500000000000001','SR2015032821443420391')
-- Date: 2017-05-25 18:59
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM201500000000000000','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM201500001000000001','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM201500001000000002','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM201500001000000003','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM201500001000000004','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM2015101717551955993','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','SM2015032110563544277','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM2015101716253866426','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM2015101716450533995','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM2015101717560118734','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM2015101717563661357','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','SM2015032110392893832','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM2015101716261754674','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM2015101719082391126','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','CYCSM2015101719110981215','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','SM2015032114020974172','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','SM2015032114023217214','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR2015032821443420391','SM2015033117394389125','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','CYCSM201500000000000000','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032115320529046','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032716101314297','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032716104252541','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032716111198076','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032716113221592','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032716141802593','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032716143658792','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032716153420883','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015033120375148323','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015033120383090825','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015033120390124613','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM201504191046584135','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032814152244462','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM201503281400585289','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032814034964260','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032814040701331','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM201503281404300866','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032814045201792','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015033121114642345','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032817343941744','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM2015032817375340722','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('YCSR201500000000000001','SM201503281403312139','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','CYCSM201500000000000000','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032111004908332','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032115462629024','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032815341153189','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032517423397534','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032517492791638','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032517495892729','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032517501280632','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032517502765755','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032517504971878','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032615195207380','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503261520244332','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503261520436243','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032615210645370','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032615213092920','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032615224289281','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032615225688297','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032713365270841','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032719033447798','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032719054525781','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032719065095891','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032719073017798','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032719080275648','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503271908263870','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033121113217540','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032718005584448','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032718013959184','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032718020246126','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032718021506884','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503271802290866','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032718034531686','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503312048586062','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033120501071069','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033120511886659','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042121533440724','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042121541681889','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015050316194220024','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015050419263008518','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015050316481743795','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015050316483632046','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032814280820460','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032816395662792','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032819353033392','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032819555604844','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032819561324334','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032820190275624','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040517011442277','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015041910113586962','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032816453831766','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040515030890573','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040515034112569','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201504051504007317','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040517015279288','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032816471476911','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032821100699155','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032821314105573','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503282210346965','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201504051702213592','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032814282786586','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032817101406466','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033122324657221','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503312233317182','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201504191658403636','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032817104102180','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033122330444499','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503312233449950','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015041916585392724','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2017052218551217919','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032817154633256','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015050320244030571','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015050215483017927','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201505021549048393','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2017052219031320215','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2017052219084519751','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503281429023268','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032817272128240','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040116422423474','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040116425093485','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040116441308394','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032817302022648','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033115104850917','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033115111510393','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040116043257469','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042116284496966','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042116290614925','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033118080150917','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033118095013031','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033118103738281','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033118105111062','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033118112048197','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015033118113511496','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040522524135569','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040522554348136','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042016164296284','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042016171944264','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042016182942251','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042016190088314','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042016194507099','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042016202182081','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040114513582931','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040115400265469','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040115434479328','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040115443032316','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015040115444938511','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042316305712363','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042316323814062','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042316382479912','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042316385361318','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042316392348091','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042316400964384','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042316372961065','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042316410397430','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042316413193265','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015042316414802760','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032117183616412','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032117282029312','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503211728354320','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503211728518555','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032210400802828','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503221040581383','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032210454810734','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032211194568931','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503221322552773','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032214114468759','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201503221412258772','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032214124268017','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032214130632938','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032214142605813','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2015032215215115350','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2017052614332372049','admin','2017-05-26 20:21:30',NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2017052614334823153','admin','2017-05-26 20:21:30',NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2017052614341287840','admin','2017-05-26 20:21:30',NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM2017052614344381469','admin','2017-05-26 20:21:30',NULL,'CD-CYC000009');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CYCR201500000000000001','SM201705261435124340','admin','2017-05-26 20:21:30',NULL,'CD-CYC000009');


/*
-- Query: SELECT code,type,account,password,remark,company_code,system_code FROM std_user.tstd_cpassword where system_code = 'CD-CYC000009'
LIMIT 0, 10000

-- Date: 2017-03-25 17:31
*/
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CYC000009-001','2','ACCESS_KEY','EDwp_G4S-dbWdLUAVqdYciXvjTe2f8nC3X9mGAP3','','CD-CYC000009','CD-CYC000009');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CYC000009-002','2','SECRET_KEY','ZxFAZFxZ_FDataELWdzsfiZzXu6Snu6RakO1sib7','','CD-CYC000009','CD-CYC000009');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CYC000009-003','2','bucket','yaocheng','','CD-CYC000009','CD-CYC000009');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CYC000009-004','3','ACCESS_KEY','wx8bc03dd744895352','','CD-CYC000009','CD-CYC000009');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CYC000009-005','3','SECRET_KEY','44ebf0ef908dc54656573625a579ea82','','CD-CYC000009','CD-CYC000009');

/*
-- Query: select `type`,`parent_key`,`dkey`,`dvalue`,`updater`,now() as update_datetime,`remark`,`system_code` from tsys_dict where system_code = 'CD-CYC000009'
LIMIT 0, 10000

-- Date: 2017-03-28 19:23
*/
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_status','用户状态','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','0','正常','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','1','程序锁定','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','2','人工锁定','admin',now(),NULL,'CD-CYC000009');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'id_kind','证件类型','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','id_kind','1','身份证','admin',now(),NULL,'CD-CYC000009');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'role_level','角色等级','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','1','运维','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','2','运营','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','3','客户','admin',now(),NULL,'CD-CYC000009');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'res_type','资源类型','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','1','菜单','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','2','按钮','admin',now(),NULL,'CD-CYC000009');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'lock_direction','锁定方向','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','1','锁定','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','2','解锁','admin',now(),NULL,'CD-CYC000009');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_kind','针对人群','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','1','C端用户','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','2','B端用户','admin',now(),NULL,'CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','3','平台用户','admin',now(),NULL,'CD-CYC000009');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0','','msg_status','消息状态','admin',now(),'','CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','msg_status','0','未发布','admin',now(),'','CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','msg_status','1','已发布','admin',now(),'','CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','msg_status','2','已下架','admin',now(),'','CD-CYC000009');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0','','coupon_status','卡券状态','admin',now(),'','CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','coupon_status','0','待使用','admin',now(),'','CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','coupon_status','1','已使用','admin',now(),'','CD-CYC000009');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','coupon_status','2','作废','admin',now(),'','CD-CYC000009');
