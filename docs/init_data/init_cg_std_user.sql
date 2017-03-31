/*
-- Query: SELECT ckey,cvalue,note,updater, now() as update_datetime,remark,system_code FROM tsys_config where system_code = 'CD-CCG000007'
LIMIT 0, 10000

-- Date: 2017-02-27 16:59
*/
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('aboutus','关于我们','关于我们','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('reg_protocol','注册协议','注册协议','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('treasure_rule','夺宝玩法介绍','<p>用户阅读并了解该功能的所有相关信息后，选择心仪的奖品，进行购买幸运号码（幸运号码=原始值10000000+购买次序：第一个购买者购买次序是1，第二个购买者购买次序是2….）当所有号码都被分配完毕后，系统根据规则计算出1个幸运号码，持有该号码的支持者，直接获得该商品</p><p>中奖号码的计算规则：</p><p>1.&nbsp;&nbsp; 奖品的最后一个号码分配完毕后，将最后5个号码的分配时间的数值进行求和（得出数值A）（每个分配时间按时、分、秒、毫秒的顺序组合，如21.15.27.368则为211527368）；</p><p>2.&nbsp;&nbsp; 数值A除以该商品总需人次得到的余数+原始值10000001，得到最终幸运号码，拥有该幸运号码者，直接获得该奖品。</p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CCG000007');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('treasure_statement','夺宝免责申明','<p>小目标服务: 是指将代币分作为奖励的多人次参与福利活动的服务。本公司提示您注意: 该项服务适用于贡献值，分润，快捷支付的付款方式但未经实名的正汇钱包账户可能会受到参与次数的限制。基于此项服务可能存在的风险, 在使用小目标服务时, 您理解并接受:</p><p>&nbsp;</p><p>a)为控制可能存在的风险, 本公司对所有用户使用小目标服务时的单日单次消费总额进行了限制, 并保留对限制种类和限制限额进行无需预告进行调整的权利。</p><p>&nbsp;b)使用小目标服务是基于您对小目标服务的充分了解, 一旦您使用小目标服, 您应当自行承担因您指示错误(失误)而导致的风险。本公司依照您参与的时间判断号码并根据本协议的约定完成派奖计算后, 即完成了当次服务的所有义务, 对于当次服务参与所有人次方之间产生的关于当次服务的任何纠纷不承担任何责任, 也不提供任何形式的纠纷解决途径, 您应当自行处理相关的纠纷。</p><p>C）本公司绝对禁止任何用户使用小目标服务进行变相牟利或者其他违反法律法规的自发组织行为，对此造成的任何形式的纠纷或者严重后果本公司不予承担，请所有使用小目标服务的用户合理参与。并且在未违反国家相关法律法规及部门规章规定，却对本公司在声誉，市场，财产的相关方面造成损失的行为，本公司将保留诉讼权利（包括但不限于通过本公司的产品或服务从事类似金字塔或矩阵型的高额返利业务模式，变相博彩模式。）</p><p>凡使用小目标服务的所有用户都已在使用前认真阅读并深刻了解该服务的所有相关信息</p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CCG000007');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('fyf_rule','发一发玩法介绍','<p>凡拥有“汇赚宝”的用户每天会有5个红包链接，用户可将该红包链接分享到微信，由好友打开后领取奖励，好友领取后分享着的账号会获得一定数值的红包业绩。</p><p>规则</p><p>一个正汇钱包账号单日最多领取1个红包链接</p><p>一个正汇钱包账号单日最多可发送5个红包链接</p><p>红包链接有效期：红包链接发送后可以转发，每日<span style=\"display: inline !important;\">0</span><span style=\"display: inline !important;\">点，前日的红包链接失效</span></p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CCG000007');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('fyf_statement','发一发免责申明','<p>本公司绝对禁止任何用户使用发一发服务进行变相牟利或者其他违反法律法规的自发组织行为，对此造成的任何形式的纠纷或者严重后果本公司不予承担，并且在未违反国家相关法律法规及部门规章规定，却对本公司在声誉，市场，财产的相关方面造成损失的行为，本公司将保留诉讼权利（包括但不限于通过本公司的产品或服务从事类似金字塔或矩阵型的高额返利业务模式。变相博彩模式。）</p><p>凡使用小目标服务的所有用户都已在使用前认真阅读并深刻了解该服务的所有相关信息</p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CCG000007');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('yyy_rule','摇一摇玩法介绍','<p>凡拥有了“汇赚宝”的用户（包括前摇钱树用户）只要打开app，周围的正汇钱包用户都可在摇一摇的界面中找到您的红包。（无需硬件打开设备）</p><p>规则</p><p>一个汇赚宝单日最多可被摇900次</p><p>一个正汇钱包用户单日最多可以摇3次（每次摇的金额和代币种类皆为随机）</p><p></p><p>一个手机（或pad）单日最多可以摇3次（每次摇的金额和代币种类皆为随机）</p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CCG000007');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('yyy_statement','摇一摇免责申明','<p>本公司绝对禁止任何用户使用发一发服务进行变相牟利或者其他违反法律法规的自发组织行为，对此造成的任何形式的纠纷或者严重后果本公司不予承担，并且在未违反国家相关法律法规及部门规章规定，却对本公司在声誉，市场，财产的相关方面造成损失的行为，本公司将保留诉讼权利（包括但不限于通过本公司的产品或服务从事类似金字塔或矩阵型的高额返利业务模式。变相博彩模式。）</p><p>凡使用小目标服务的所有用户都已在使用前认真阅读并深刻了解该服务的所有相关信息</p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CCG000007');

/*
-- Query: SELECT * FROM std_user.tstd_user where login_name = 'admin' and  system_code = 'CD-CCG000007'
LIMIT 0, 10000

-- Date: 2017-03-25 17:26
*/
delete from tstd_user where system_code = 'CD-CCG000007';
INSERT INTO `tstd_user` (`user_id`,`login_name`,`nickname`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`user_referee`,`mobile`,`id_kind`,`id_no`,`real_name`,`trade_pwd`,`trade_pwd_strength`,`role_code`,`status`,`pdf`,`amount`,`lj_amount`,`company_code`,`open_id`,`jpush_id`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCG201600000000000001','admin',NULL,'21218cca77804d2ba1922c33e0151105','1','01','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CGSR201600000000000003','0',NULL,NULL,NULL,NULL,NULL,NULL,'admin',NULL,'管理端系统方','CD-CCG000007');
INSERT INTO `tstd_user` (`user_id`,`login_name`,`nickname`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`user_referee`,`mobile`,`id_kind`,`id_no`,`real_name`,`trade_pwd`,`trade_pwd_strength`,`role_code`,`status`,`pdf`,`amount`,`lj_amount`,`company_code`,`open_id`,`jpush_id`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SYS_USER_CAIGO','caigo',NULL,'21218cca77804d2ba1922c33e0151105','1','01','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CCGR201600000000000001','0',NULL,NULL,NULL,NULL,NULL,NULL,'admin',NULL,'管理端系统方','CD-CCG000007');

/*
-- Query: SELECT code,name,type,url,order_no,updater, now() as update_datetime,remark,parent_code,system_code FROM tsys_menu where system_code = 'CD-CCG000007'
LIMIT 0, 10000

-- Date: 2017-03-28 21:56
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
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101719110981215','设置角色','2','/assign','5','admin',now(),'','CCGSM2016101716261754674','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('CCGSM2016101719143965297','修改','2','/edit','2','admin',now(),'','CCGSM2016101716295904680','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032110392893832','新增','2','/add','1','admin',now(),'','CCGSM2016101716253866426','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032110563544277','运营管理','1','#','1','admin',now(),'','CCGSM201600001000000001','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032111004908332','平台管理','1','#','2','admin',now(),'','CCGSM201600000000000000','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032111135334551','系统参数设置','1','/general/param.htm','2','admin',now(),'','CCGSM201600001000000002','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032111164208375','修改','2','/edit','2','admin',now(),'','SM2017032111135334551','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703211121130805','新增','2','/add','1','admin',now(),'','CCGSM2016101716295904680','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032114020974172','锁定','2','/locking','2','admin',now(),'','CCGSM2016101716261754674','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032114023217214','解锁','2','/unLock','3','admin',now(),'','CCGSM2016101716261754674','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032115230918954','删除','2','/delete','3','admin',now(),'','CCGSM2016101716295904680','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032115320529046','加盟商','1','#','3','admin',now(),'','CCGSM201600000000000000','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032115462629024','我的账户','1','#','10','admin',now(),'','SM2017032111004908332','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032116515108986','摇钱树管理','1','#','20','admin',now(),'','SM2017032111004908332','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032116580703857','摇钱树管理','1','/platform/cashCow.htm','20','admin',now(),'','SM2017032116515108986','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032117014088035','摇一摇规则','1','/platform/shakeRule.htm','40','admin',now(),'','SM2017032116515108986','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032117153442628','夺宝管理','1','#','30','admin',now(),'','SM2017032111004908332','CD-CCG000007');
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
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032517423397534','商城管理','1','#','40','admin',now(),'','SM2017032111004908332','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032517492791638','商品类别管理','1','/product/category.htm','10','admin',now(),'','SM2017032517423397534','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032517495892729','新增','2','/add','1','admin',now(),'','SM2017032517492791638','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032517501280632','修改','2','/edit2','2','admin',now(),'','SM2017032517492791638','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032517502765755','上架','2','/up','4','admin',now(),'','SM2017032517492791638','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032517504971878','下架','2','/down','5','admin',now(),'','SM2017032517492791638','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032615195207380','商品管理','1','/product/product.htm','20','admin',now(),'','SM2017032517423397534','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703261520244332','新增','2','/add','1','admin',now(),'','SM2017032615195207380','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703261520436243','修改','2','/edit2','2','admin',now(),'','SM2017032615195207380','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032615210645370','删除','2','/delete','3','admin',now(),'','SM2017032615195207380','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032615213092920','详情','2','/detail2','4','admin',now(),'','SM2017032615195207380','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032615224289281','上架','2','/up2','5','admin',now(),'','SM2017032615195207380','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032615225688297','下架','2','/down','6','admin',now(),'','SM2017032615195207380','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032713365270841','产品参数','2','/productParam','7','admin',now(),'','SM2017032615195207380','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032716101314297','商家管理','1','#','10','admin',now(),'','SM2017032115320529046','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032716104252541','我的商家','1','/store/store.htm','10','admin',now(),'','SM2017032716101314297','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032716111198076','入驻商家','2','/add','1','admin',now(),'','SM2017032716104252541','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032716113221592','审核','2','/examine','20','admin',now(),'','SM2017032716104252541','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032716141802593','上架','2','/up2','30','admin',now(),'','SM2017032716104252541','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032716143658792','下架','2','/down','40','admin',now(),'','SM2017032716104252541','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032716153420883','详情','2','/detail2','50','admin',now(),'','SM2017032716104252541','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032718005584448','商家管理','1','#','50','admin',now(),'','SM2017032111004908332','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032718013959184','商家类别管理','1','/platform/storeCategory.htm','1','admin',now(),'','SM2017032718005584448','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032718020246126','新增','2','/add','1','admin',now(),'','SM2017032718013959184','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032718021506884','修改','2','/edit','2','admin',now(),'','SM2017032718013959184','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703271802290866','上架','2','/up','3','admin',now(),'','SM2017032718013959184','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032718034531686','下架','2','/down','4','admin',now(),'','SM2017032718013959184','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032719033447798','订单处理','1','/product/order.htm','30','admin',now(),'','SM2017032517423397534','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032719054525781','物流发货','2','/sendOutGoods','1','admin',now(),'','SM2017032719033447798','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032719065095891','现场发货','2','/spotDelivery','2','admin',now(),'','SM2017032719033447798','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032719073017798','取消订单','2','/cancelOrder','3','admin',now(),'','SM2017032719033447798','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032719080275648','确认收货','2','/confirmOrder','4','admin',now(),'','SM2017032719033447798','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703271908263870','详情','2','/detail','5','admin',now(),'','SM2017032719033447798','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703281400585289','订单处理','1','/product/order.htm','20','admin',now(),'','SM2017032814152244462','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703281403312139','物流发货','2','/sendOutGoods','1','admin',now(),'','SM201703281400585289','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032814034964260','现场发货','2','/spotDelivery','2','admin',now(),'','SM201703281400585289','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032814040701331','取消订单','2','/cancelOrder','3','admin',now(),'','SM201703281400585289','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703281404300866','确认收货','2','/confirmOrder','4','admin',now(),'','SM201703281400585289','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032814045201792','详情','2','/detail','5','admin',now(),'','SM201703281400585289','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032814152244462','商城管理','1','#','20','admin',now(),'','SM2017032115320529046','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032814183568630','摇一摇记录查询','1','/platform/shakeRecord.htm','31','admin',now(),'','SM2017032116515108986','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032814280820460','客户管理','1','#','60','admin',now(),'','SM2017032111004908332','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032814282786586','财务管理','1','#','70','admin',now(),'','SM2017032111004908332','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201703281429023268','对接管理','1','#','80','admin',now(),'','SM2017032111004908332','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032815341153189','我的账户','1','/platform/account.htm','10','admin',now(),'','SM2017032115462629024','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032816395662792','加盟商管理','1','/platform/AllianceBusiness.htm','10','admin',now(),'','SM2017032814280820460','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032816453831766','商家管理','1','/platform/business.htm','20','admin',now(),'','SM2017032814280820460','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032816471476911','会员管理','1','/platform/member.htm','30','admin',now(),'','SM2017032814280820460','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032817101406466','线下充值','1','/platform/rechargeOffline.htm','10','admin',now(),'','SM2017032814282786586','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032817104102180','线下取现','1','/platform/withdrawOffline.htm','20','admin',now(),'','SM2017032814282786586','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032817154633256','充取历史查询','1','/platform/historyQuery.htm','30','admin',now(),'','SM2017032814282786586','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032817272128240','对接池管理','1','/platform/capitalPool.htm','10','admin',now(),'','SM201703281429023268','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032817302022648','出金流水','1','/platform/capitalPoolRecord.htm','20','admin',now(),'','SM201703281429023268','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032817343941744','我的账户','1','#','5','admin',now(),'','SM2017032115320529046','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032817375340722','我的账户','1','/store/account.htm','10','admin',now(),'','SM2017032817343941744','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032819353033392','代注册','2','/register','1','admin',now(),'','SM2017032816395662792','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032819555604844','发货情况','2','/','2','admin',now(),'','SM2017032816395662792','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032819561324334','代销情况','2','/','3','admin',now(),'','SM2017032816395662792','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032820182550853','积分情况','2','/','4','admin',now(),'','SM2017032816395662792','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032820190275624','详情','2','/detail2','5','admin',now(),'','SM2017032816395662792','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032821100699155','账户查询','2','/accountQuery','1','admin',now(),'','SM2017032816471476911','CD-CCG000007');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017032821314105573','详情','2','/detail2','10','admin',now(),'','SM2017032816471476911','CD-CCG000007');

/*
-- Query: SELECT `code`,`name`,`level`,'admin' as `updater`,now() as 'update_datetime',`remark`,`system_code` FROM tsys_role where system_code = 'CD-CCG000007'
LIMIT 0, 10000

-- Date: 2017-03-28 21:57
*/
delete from tsys_role where system_code = 'CD-CCG000007';
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','超级管理员','1','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','加盟商','2','admin',now(),'','CD-CCG000007');
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','系统管理员','1','admin',now(),'','CD-CCG000007');

/*
-- Query: SELECT `role_code`,`menu_code`,`updater`,now() as `update_datetime`,`remark`,`system_code` FROM tsys_menu_role where role_code in('CCGR201600000000000001','CGSR201600000000000002','CGSR201600000000000003')
LIMIT 0, 10000

-- Date: 2017-03-28 19:21
*/
delete from tsys_menu_role where system_code = 'CD-CCG000007';
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','CCGSM201600000000000000','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032115320529046','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032716101314297','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032716104252541','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032716111198076','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032716113221592','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032716141802593','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032716143658792','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032716153420883','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032814152244462','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM201703281400585289','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM201703281403312139','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032814034964260','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032814040701331','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM201703281404300866','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032814045201792','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032817343941744','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000002','SM2017032817375340722','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM201600000000000000','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM201600001000000001','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM201600001000000002','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM201600001000000003','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM201600001000000004','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM2016101717551955993','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM2016101716295904680','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM2016101719143965297','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','SM201703211121130805','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','SM2017032115230918954','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','SM2017032111135334551','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','SM2017032111164208375','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','SM2017032110563544277','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM2016101716253866426','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM2016101716450533995','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM2016101717560118734','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM2016101717563661357','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','SM2017032110392893832','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM2016101716261754674','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM2016101719082391126','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','CCGSM2016101719110981215','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','SM2017032114020974172','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CGSR201600000000000003','SM2017032114023217214','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','CCGSM201600000000000000','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032111004908332','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032115462629024','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032815341153189','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032116515108986','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032116580703857','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032210400802828','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703221040581383','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032210454810734','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032117014088035','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032211194568931','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032210354305644','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703211718156943','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032117183616412','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032117282029312','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703211728354320','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703211728518555','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032213223669717','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703221322552773','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032220464239536','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032814183568630','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032117153442628','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703221336596893','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032214114468759','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703221412258772','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032214124268017','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032214130632938','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032214142605813','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032215211295825','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032215215115350','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032517423397534','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032517492791638','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032517495892729','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032517501280632','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032517502765755','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032517504971878','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032615195207380','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703261520244332','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703261520436243','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032615210645370','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032615213092920','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032615224289281','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032615225688297','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032713365270841','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032719033447798','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032719054525781','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032719065095891','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032719073017798','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032719080275648','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703271908263870','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032718005584448','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032718013959184','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032718020246126','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032718021506884','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703271802290866','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032718034531686','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032814280820460','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032816395662792','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032819353033392','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032819555604844','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032819561324334','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032820182550853','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032820190275624','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032816453831766','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032816471476911','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032821100699155','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032821314105573','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032814282786586','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032817101406466','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032817104102180','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032817154633256','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703281429023268','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032817272128240','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032817302022648','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032115320529046','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032716101314297','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032716104252541','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032716111198076','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032716113221592','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032716141802593','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032716143658792','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032716153420883','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032814152244462','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703281400585289','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703281403312139','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032814034964260','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032814040701331','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM201703281404300866','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032814045201792','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032817343941744','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CCGR201600000000000001','SM2017032817375340722','admin',now(),NULL,'CD-CCG000007');

/*
-- Query: SELECT code,type,account,password,remark,company_code,system_code FROM std_user.tstd_cpassword where system_code = 'CD-CCG000007'
LIMIT 0, 10000

-- Date: 2017-03-25 17:31
*/
DELETE from tstd_cpassword where system_code = 'CD-CCG000007';
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CCG000007-001','2','ACCESS_KEY','M0atdzBYOQ-oloFpRJFtX7HDDU1NTBBvRUu3MS1T','','CD-CCG000007','CD-CCG000007');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CCG000007-002','2','SECRET_KEY','F8eJ94o1WoFIB7VxTwtI5rB8RLi7IHC7cY47Bnwh','','CD-CCG000007','CD-CCG000007');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CCG000007-003','2','bucket','cd-test','','CD-CCG000007','CD-CCG000007');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CCG000007-004','3','ACCESS_KEY','wx8bc03dd744895352','','CD-CCG000007','CD-CCG000007');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CCG000007-005','3','SECRET_KEY','44ebf0ef908dc54656573625a579ea82','','CD-CCG000007','CD-CCG000007');

/*
-- Query: select `type`,`parent_key`,`dkey`,`dvalue`,`updater`,now() as update_datetime,`remark`,`system_code` from tsys_dict where system_code = 'CD-CCG000007'
LIMIT 0, 10000

-- Date: 2017-03-28 19:23
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

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'toKind','针对人群','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','toKind','1','C端用户','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','toKind','2','B端用户','admin',now(),NULL,'CD-CCG000007');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','toKind','3','平台用户','admin',now(),NULL,'CD-CCG000007');