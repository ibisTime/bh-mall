/*
-- Query: SELECT ckey,cvalue,note,updater, now() as update_datetime,remark,system_code FROM zhpay_std_user.tsys_config
LIMIT 0, 10000

-- Date: 2017-02-27 16:59
*/
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('aboutus','关于我们','<p style=\"text-indent: 2em;\">正汇钱包，自2015年5月1日试运行起受到广大用户的关注，今日全新形象的正汇钱包上线，欢迎大家继续使用。本软件由义乌市正汇信息科技有限公司运行制作，（简称正汇科技）正汇科技成立于2015年初，创始团队至今已有5年历程。正汇科技是一家集程序开发、市场运营为一体的移动互联网企业，并集新型创业、提供就业、提高生活质量的全新经济体系的高新技术公司。</p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CZH000001');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('reg_protocol','注册协议','注册协议','admin','2017-02-27 16:59:17',NULL,'CD-CZH000001');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('treasure_rule','夺宝玩法介绍','<p>用户阅读并了解该功能的所有相关信息后，选择心仪的奖品，进行购买幸运号码（幸运号码=原始值10000000+购买次序：第一个购买者购买次序是1，第二个购买者购买次序是2….）当所有号码都被分配完毕后，系统根据规则计算出1个幸运号码，持有该号码的支持者，直接获得该商品</p><p>中奖号码的计算规则：</p><p>1.&nbsp;&nbsp; 奖品的最后一个号码分配完毕后，将最后5个号码的分配时间的数值进行求和（得出数值A）（每个分配时间按时、分、秒、毫秒的顺序组合，如21.15.27.368则为211527368）；</p><p>2.&nbsp;&nbsp; 数值A除以该商品总需人次得到的余数+原始值10000001，得到最终幸运号码，拥有该幸运号码者，直接获得该奖品。</p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CZH000001');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('treasure_statement','夺宝免责申明','<p>小目标服务: 是指将代币分作为奖励的多人次参与福利活动的服务。本公司提示您注意: 该项服务适用于贡献值，分润，快捷支付的付款方式但未经实名的正汇钱包账户可能会受到参与次数的限制。基于此项服务可能存在的风险, 在使用小目标服务时, 您理解并接受:</p><p>&nbsp;</p><p>a)为控制可能存在的风险, 本公司对所有用户使用小目标服务时的单日单次消费总额进行了限制, 并保留对限制种类和限制限额进行无需预告进行调整的权利。</p><p>&nbsp;b)使用小目标服务是基于您对小目标服务的充分了解, 一旦您使用小目标服, 您应当自行承担因您指示错误(失误)而导致的风险。本公司依照您参与的时间判断号码并根据本协议的约定完成派奖计算后, 即完成了当次服务的所有义务, 对于当次服务参与所有人次方之间产生的关于当次服务的任何纠纷不承担任何责任, 也不提供任何形式的纠纷解决途径, 您应当自行处理相关的纠纷。</p><p>C）本公司绝对禁止任何用户使用小目标服务进行变相牟利或者其他违反法律法规的自发组织行为，对此造成的任何形式的纠纷或者严重后果本公司不予承担，请所有使用小目标服务的用户合理参与。并且在未违反国家相关法律法规及部门规章规定，却对本公司在声誉，市场，财产的相关方面造成损失的行为，本公司将保留诉讼权利（包括但不限于通过本公司的产品或服务从事类似金字塔或矩阵型的高额返利业务模式，变相博彩模式。）</p><p>凡使用小目标服务的所有用户都已在使用前认真阅读并深刻了解该服务的所有相关信息</p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CZH000001');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('fyf_rule','发一发玩法介绍','<p>凡拥有“汇赚宝”的用户每天会有5个红包链接，用户可将该红包链接分享到微信，由好友打开后领取奖励，好友领取后分享着的账号会获得一定数值的红包业绩。</p><p>规则</p><p>一个正汇钱包账号单日最多领取1个红包链接</p><p>一个正汇钱包账号单日最多可发送5个红包链接</p><p>红包链接有效期：红包链接发送后可以转发，每日<span style=\"display: inline !important;\">0</span><span style=\"display: inline !important;\">点，前日的红包链接失效</span></p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CZH000001');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('fyf_statement','发一发免责申明','<p>本公司绝对禁止任何用户使用发一发服务进行变相牟利或者其他违反法律法规的自发组织行为，对此造成的任何形式的纠纷或者严重后果本公司不予承担，并且在未违反国家相关法律法规及部门规章规定，却对本公司在声誉，市场，财产的相关方面造成损失的行为，本公司将保留诉讼权利（包括但不限于通过本公司的产品或服务从事类似金字塔或矩阵型的高额返利业务模式。变相博彩模式。）</p><p>凡使用小目标服务的所有用户都已在使用前认真阅读并深刻了解该服务的所有相关信息</p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CZH000001');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('yyy_rule','摇一摇玩法介绍','<p>凡拥有了“汇赚宝”的用户（包括前摇钱树用户）只要打开app，周围的正汇钱包用户都可在摇一摇的界面中找到您的红包。（无需硬件打开设备）</p><p>规则</p><p>一个汇赚宝单日最多可被摇900次</p><p>一个正汇钱包用户单日最多可以摇3次（每次摇的金额和代币种类皆为随机）</p><p></p><p>一个手机（或pad）单日最多可以摇3次（每次摇的金额和代币种类皆为随机）</p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CZH000001');
INSERT INTO `tsys_config` (`ckey`,`cvalue`,`note`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('yyy_statement','摇一摇免责申明','<p>本公司绝对禁止任何用户使用发一发服务进行变相牟利或者其他违反法律法规的自发组织行为，对此造成的任何形式的纠纷或者严重后果本公司不予承担，并且在未违反国家相关法律法规及部门规章规定，却对本公司在声誉，市场，财产的相关方面造成损失的行为，本公司将保留诉讼权利（包括但不限于通过本公司的产品或服务从事类似金字塔或矩阵型的高额返利业务模式。变相博彩模式。）</p><p>凡使用小目标服务的所有用户都已在使用前认真阅读并深刻了解该服务的所有相关信息</p><p><br></p>','admin','2017-02-27 16:59:17',NULL,'CD-CZH000001');

/*
-- Query: SELECT * FROM zhpay_std_user.tstd_user where login_name = 'admin' and system_code  = 'CD-CZH000001'
-- Date: 2017-01-08 19:52
*/
INSERT INTO `tstd_user` (`user_id`,`login_name`,`nickname`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`user_referee`,`mobile`,`id_kind`,`id_no`,`real_name`,`trade_pwd`,`trade_pwd_strength`,`role_code`,`status`,`pdf`,`amount`,`lj_amount`,`company_code`,`open_id`,`jpush_id`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('U201600000000000000','admin',NULL,'21218cca77804d2ba1922c33e0151105',NULL,'01','1','',NULL,NULL,NULL,NULL,NULL,NULL,'SR201600000000000000','0',NULL,NULL,NULL,NULL,NULL,NULL,'admin',NULL,'管理端系统方','CD-CZH000001');

/*
-- Query: SELECT code,name,type,url,order_no,updater, now() as update_datetime,remark,parent_code,system_code FROM tsys_menu where system_code = 'CD-CZH000001'
LIMIT 0, 10000

-- Date: 2017-02-28 23:18
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201600000000000000','根目录','1','#','1','admin',now(),'','','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201600001000000000','系统根目录','1','#','1','admin',now(),'','SM201600000000000000','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201600001000000001','系统管理','1','#','1','admin',now(),'','SM201600001000000000','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201600001000000002','运维管理','1','#','2','admin',now(),NULL,'SM201600001000000001','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201600001000000003','菜单管理','1','/security/menu.htm','1','admin',now(),NULL,'SM201600001000000002','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201600001000000004','修改','2','/edit','1','admin',now(),NULL,'SM201600001000000003','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101716241339082','运营管理','1','#','1','admin',now(),'','SM201600001000000001','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101716253866426','角色管理','1','/security/role.htm','1','admin',now(),'','SM2016101716241339082','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101716261754674','用户管理','1','/security/user.htm','2','admin',now(),'','SM2016101716241339082','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101716290657836','系统参数管理','1','/general/param.htm','2','admin',now(),'','SM201600001000000002','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101716295904680','数据字典管理','1','/general/dict.htm','3','admin',now(),'','SM201600001000000002','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101716450533995','分配菜单','2','/change','4','admin',now(),'','SM2016101716253866426','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101717551955993','新增','2','/add','1','admin',now(),'','SM2016101716253866426','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101717560118734','修改','2','/edit','2','admin',now(),'','SM2016101716253866426','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101717563661357','删除','2','/delete','3','admin',now(),'','SM2016101716253866426','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101719082391126','新增','2','/add','1','admin',now(),'','SM2016101716261754674','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101719094151894','重置密码','2','/reset','2','admin',now(),'','SM2016101716261754674','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101719100760088','注销','2','/rock','4','admin',now(),'','SM2016101716261754674','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101719110981215','设置角色','2','/assign','5','admin',now(),'','SM2016101716261754674','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101719140087629','修改','2','/edit','1','admin',now(),'','SM2016101716290657836','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016101719143965297','修改','2','/edit','1','admin',now(),'','SM2016101716295904680','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112911141208861','消息管理','1','#','4','admin',now(),'','SM201600001000000000','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112911152991684','消息推送','1','#','1','admin',now(),'','SM2016112911141208861','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112911280249973','公告管理','1','/news/notice.htm','1','admin',now(),'','SM2016112911152991684','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112911292717856','短信推送','1','/news/message.htm','2','admin',now(),'','SM2016112911152991684','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112911322482563','极光推送','1','/news/aurora.htm','3','admin',now(),'','SM2016112911152991684','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112911334743939','微信推送','1','/news/wechat.htm','4','admin',now(),'','SM2016112911152991684','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112914292031228','新增','2','/add','1','admin',now(),'','SM2016112911280249973','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112914295002950','修改','2','/edit','2','admin',now(),'','SM2016112911280249973','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112914322481897','发布','2','/pub','3','admin',now(),'','SM2016112911280249973','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112914325471772','详情','2','/detail','5','admin',now(),'','SM2016112911280249973','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112915194249520','单发','2','/pub','1','admin',now(),'','SM2016112911292717856','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201611291521071981','详情','2','/detail','3','admin',now(),'','SM2016112911292717856','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112915302885244','单发','2','/pub','1','admin',now(),'','SM2016112911322482563','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201611291531308302','详情','2','/detail','3','admin',now(),'','SM2016112911322482563','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112915315987472','单发','2','/pub','1','admin',now(),'','SM2016112911334743939','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016112915325414564','详情','2','/detail','3','admin',now(),'','SM2016112911334743939','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120210533096194','撤下','2','/pull','4','admin',now(),'','SM2016112911280249973','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120610552303416','激活','2','/active','3','admin',now(),'','SM2016101716261754674','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201612071021105964','财务管理','1','#','3','admin',now(),'','SM201600001000000000','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120710215035834','账户查询','1','#','1','admin',now(),'','SM201612071021105964','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201612071022206883','外部账对账','1','#','4','admin',now(),'','SM201612071021105964','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120710225078473','内部账对账','1','#','5','admin',now(),'','SM201612071021105964','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120710233232137','平台账户','1','#','2','admin',now(),'','SM201612071021105964','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120710242555432','线下走款','1','#','3','admin',now(),'','SM201612071021105964','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201612071029309117','账户查询','1','/finance/account.htm','1','admin',now(),'','SM2016120710215035834','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120710341064879','流水查询','1','/finance/ledger.htm','2','admin',now(),'','SM2016120710215035834','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120710421392861','自动对账监控','1','/finance/autoReconControl.htm','1','admin',now(),'','SM201612071022206883','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120710443551388','不平账处理','1','/finance/roughHand.htm','2','admin',now(),'','SM201612071022206883','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120710470696888','二次轧账','1','/finance/twoRoll.htm','1','admin',now(),'','SM2016120710225078473','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120710484209132','不平账处理','1','/finance/inRoughHand.htm','2','admin',now(),'','SM2016120710225078473','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120710583381112','盈亏余额','1','/finance/breakBalance.htm','1','admin',now(),'','SM2016120710233232137','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120711021944714','线下充值','1','/finance/lineRecharge.htm','1','admin',now(),'','SM2016120710242555432','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120711034365344','线下取现','1','/finance/lineUnder.htm','2','admin',now(),'','SM2016120710242555432','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120714062949665','流水','2','/flow','1','admin',now(),'','SM201612071029309117','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120714343212111','详情','2','/detail','1','admin',now(),'','SM2016120710341064879','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120715221559156','群发','2','/pubs','2','admin',now(),'','SM2016112911292717856','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120715320175228','群发','2','/pubs','2','admin',now(),'','SM2016112911322482563','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120715325497790','群发','2','/pubs','2','admin',now(),'','SM2016112911334743939','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120816125733948','人工对账','2','edit','1','admin',now(),'','SM2016120710421392861','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120816133940523','详情','2','/detail','2','admin',now(),'','SM2016120710421392861','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120911033945882','审核','2','check','1','admin',now(),'','SM2016120710443551388','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120911042060088','详情','2','/detail','2','admin',now(),'','SM2016120710443551388','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201612091114505291','二次轧账','2','edit','1','admin',now(),'','SM2016120710470696888','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120911160661084','详情','2','/detail','2','admin',now(),'','SM2016120710470696888','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120911293120195','审核','2','check','1','admin',now(),'','SM2016120710484209132','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016120911301231346','详情','2','/detail','2','admin',now(),'','SM2016120710484209132','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201612121417489854','业务管理','1','#','2','admin',now(),'','SM201600001000000000','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121214481743755','取现审批','1','/biz/withdraw.htm','4','admin',now(),'','SM2017022413213453454','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121214490617928','代申请','2','add','1','admin',now(),'','SM2016121214481743755','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121214495421331','审批','2','/check','2','admin',now(),'','SM2016121214481743755','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215243785360','汇赚宝管理','1','/biz/moneytree.htm','6','admin',now(),'','SM2017022413213453454','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215255452846','解冻/冻结','2','/lock','2','admin',now(),'','SM2016121215243785360','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215304545522','红包审批','1','/biz/luckymoney.htm','8','admin',now(),'','SM2017022413213453454','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215310612145','审批','2','/check','1','admin',now(),'','SM2016121215304545522','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215323620971','红包业绩审批','1','/biz/performance.htm','9','admin',now(),'','SM2017022413213453454','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215325117652','审批','2','/check','1','admin',now(),'','SM2016121215323620971','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215332251046','业务规则','1','#','2','admin',now(),'','SM201612121417489854','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215342898771','汇赚宝规则','1','/biz/distribution.htm','1','admin',now(),'','SM2016121215332251046','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215345041790','修改','2','/edit','1','admin',now(),'','SM2016121215342898771','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215411661735','摇一摇规则','1','/biz/shake.htm','2','admin',now(),'','SM2016121215332251046','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215414008454','修改','2','edit','1','admin',now(),'','SM2016121215411661735','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215431330778','虚拟币规则','1','/biz/virtualmoney.htm','3','admin',now(),'','SM2016121215332251046','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215435401753','修改','2','/edit','1','admin',now(),'','SM2016121215431330778','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215443012922','定向红包规则','1','/biz/location.htm','4','admin',now(),'','SM2016121215332251046','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215444925722','修改','2','/edit','1','admin',now(),'','SM2016121215443012922','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215462081241','用户管理','1','#','3','admin',now(),'','SM201612121417489854','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215465935188','C端用户','1','/biz/customer.htm','1','admin',now(),'','SM2016121215462081241','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215475590373','代注册','2','/add','1','admin',now(),'','SM2016121215465935188','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215491223788','禁止登录/取消','2','/lock','2','admin',now(),'','SM2016121215465935188','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215501001074','B端用户','1','/biz/bizman.htm','2','admin',now(),'','SM2016121215462081241','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215511604893','代注册','2','/add','1','admin',now(),'','SM2016121215501001074','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215513360129','禁止登录/取消','2','/lock','2','admin',now(),'','SM2016121215501001074','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215521211246','合伙人管理','1','/biz/partner.htm','3','admin',now(),'','SM2016121215462081241','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215522782115','新增','2','/add','1','admin',now(),'','SM2016121215521211246','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215524418148','修改','2','/edit','2','admin',now(),'','SM2016121215521211246','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215534398095','解除关系/取消','2','lock','3','admin',now(),'','SM2016121215521211246','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121215543215610','文章管理','1','#','4','admin',now(),'','SM201612121417489854','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121216045274832','关于我们','1','/biz/aboutus_addedit.htm','3','admin',now(),'','SM2016121215543215610','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121216055859786','合伙人','1','#','6','admin',now(),'','SM201612121417489854','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121216070440235','我的账本','1','/biz/myaccount.htm','1','admin',now(),'','SM2016121216055859786','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121216081334671','辖区商户','1','/biz/myseller.htm','2','admin',now(),'','SM2016121216055859786','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121216083080679','审批','2','/check','1','admin',now(),'','SM2016121216081334671','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016121216084598347','详情','2','/detail','2','admin',now(),'','SM2016121216081334671','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016122219561180130','导出','2','/export','9','admin',now(),'','SM201612071029309117','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016122219573703627','导出','2','/export','9','admin',now(),'','SM2016120710341064879','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016122219590218515','导出','2','/export','9','admin',now(),'','SM2016120710470696888','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016122220000572417','导出','2','/export','9','admin',now(),'','SM2016120710484209132','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016122317321369746','线下充值','2','add','1','admin',now(),'','SM2016120711021944714','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016122317325464966','线下取现','2','add','2','admin',now(),'','SM2016120711034365344','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016122317590316953','流水','2','/flow','1','admin',now(),'','SM2016120710583381112','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016122412144574736','导出','2','export','9','admin',now(),'','SM2016120710583381112','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016122413510161245','审核','2','check','2','admin',now(),'','SM2016120711021944714','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2016122413512505485','审核','2','check','2','admin',now(),'','SM2016120711034365344','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017010715093123723','详情','2','detail','3','admin',now(),'','SM2016121215465935188','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017010715095226059','详情','2','detail','3','admin',now(),'','SM2016121215501001074','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017010715111895455','详情','2','detail','3','admin',now(),'','SM2016121215521211246','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201701170252348209','下载地址','1','/biz/otherrule.htm','5','admin',now(),'','SM2016121215332251046','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017011702525678266','修改','2','edit','1','admin',now(),'','SM201701170252348209','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022412460281492','资金流水','1','/finance/platform_ledger.htm','2','admin',now(),'','SM2016120710233232137','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022412472301488','详情','2','/detail','1','admin',now(),'','SM2017022412460281492','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022412482059354','导出','2','/export','2','admin',now(),'','SM2017022412460281492','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022413014074915','导出','2','/export','3','admin',now(),'','SM2016120710443551388','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022413024043573','导出','2','/export','3','admin',now(),'','SM2016120710421392861','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022413213453454','业务管理','1','#','0','admin',now(),'','SM201612121417489854','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022413260522430','夺宝发布','1','/biz/duobao.htm','0','admin',now(),'','SM2017022413213453454','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022413271607883','新增','2','/add','0','admin',now(),'','SM2017022413260522430','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022413273858135','修改','2','/edit','1','admin',now(),'','SM2017022413260522430','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022413280525934','删除','2','/delete','3','admin',now(),'','SM2017022413260522430','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022413284597757','上/下架','2','/updown','4','admin',now(),'','SM2017022413260522430','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022414045652840','详情','2','/detail','2','admin',now(),'','SM2017022413260522430','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022414170702295','夺宝期数查询','1','/biz/dbrecord.htm','1','admin',now(),'','SM2017022413213453454','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201702241447571075','参与详情','2','/joinDetail','0','admin',now(),'','SM2017022414170702295','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201702241530394485','定向红包查询','1','/biz/directionmoney.htm','3','admin',now(),'','SM2017022413213453454','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022416243474533','注册协议','1','/biz/reg_protocol.htm','0','admin',now(),'','SM2016121215543215610','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022416261359445','夺宝玩法介绍','1','/biz/treasure_rule.htm','1','admin',now(),'','SM2016121215543215610','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022416292077012','夺宝免责申明','1','/biz/treasure_statement.htm','2','admin',now(),'','SM2016121215543215610','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022416304538172','发一发玩法介绍','1','/biz/fyf_rule.htm','3','admin',now(),'','SM2016121215543215610','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022416314144419','发一发免责申明','1','/biz/fyf_statement.htm','4','admin',now(),'','SM2016121215543215610','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022620371788183','详情','2','/list','0','admin',now(),'','SM2016121215243785360','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022713464525823','详情','2','/detail1','2','admin',now(),'','SM2016121215304545522','CD-CZH000001');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM2017022714144175214','详情','2','/detail1','3','admin',now(),'','SM2016121214481743755','CD-CZH000001');

/*
-- Query: SELECT `code`,`name`,`level`,'admin' as `updater`,now() as 'update_datetime',`remark`,`system_code` FROM tsys_role where name = '超级管理员' and system_code = 'CD-CZH000001'
-- Date: 2017-01-03 10:48
*/
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','超级管理员','1','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR20160000000000hhr1','合伙人','1','admin',now(),'','CD-CZH000001');

/*
-- Query: SELECT `role_code`,`menu_code`,`updater`,now() as `update_datetime`,`remark`,`system_code` FROM tsys_menu_role where role_code in('SR201600000000000000','SR20160000000000hhr1')
LIMIT 0, 10000

-- Date: 2017-02-28 23:19
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR20160000000000hhr1','SM201600000000000000','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR20160000000000hhr1','SM201600001000000000','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR20160000000000hhr1','SM201612121417489854','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR20160000000000hhr1','SM2016121216055859786','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR20160000000000hhr1','SM2016121216070440235','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201600000000000000','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201600001000000000','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201600001000000001','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201600001000000002','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201600001000000003','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201600001000000004','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101716295904680','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101719143965297','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101716241339082','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101716253866426','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101716450533995','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101717551955993','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101717560118734','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101717563661357','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101716261754674','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101719082391126','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101719094151894','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101719100760088','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016101719110981215','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120610552303416','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016112911141208861','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016112911152991684','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016112911280249973','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016112914292031228','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016112914295002950','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016112914322481897','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016112914325471772','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120210533096194','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016112911292717856','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016112915194249520','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201611291521071981','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201612071021105964','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120710215035834','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201612071029309117','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120714062949665','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016122219561180130','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120710341064879','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120714343212111','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016122219573703627','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201612071022206883','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120710421392861','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120816125733948','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120816133940523','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022413024043573','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120710443551388','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120911033945882','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120911042060088','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022413014074915','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120710225078473','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120710470696888','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201612091114505291','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120911160661084','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016122219590218515','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120710484209132','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120911293120195','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120911301231346','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016122220000572417','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120710233232137','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120710583381112','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016122317590316953','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016122412144574736','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022412460281492','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022412472301488','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022412482059354','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120710242555432','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120711021944714','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016122317321369746','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016122413510161245','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016120711034365344','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016122317325464966','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016122413512505485','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201612121417489854','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215332251046','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215342898771','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215345041790','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215411661735','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215414008454','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215431330778','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215435401753','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215443012922','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215444925722','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201701170252348209','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017011702525678266','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215462081241','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215465935188','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215475590373','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215491223788','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017010715093123723','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215501001074','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215511604893','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215513360129','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017010715095226059','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215521211246','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215522782115','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215524418148','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215534398095','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017010715111895455','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215543215610','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121216045274832','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022416243474533','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022416261359445','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022416292077012','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022416304538172','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022416314144419','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022413213453454','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121214481743755','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121214490617928','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121214495421331','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022714144175214','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215243785360','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215255452846','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022620371788183','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215304545522','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215310612145','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022713464525823','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215323620971','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2016121215325117652','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022413260522430','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022413271607883','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022413273858135','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022413280525934','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022413284597757','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022414045652840','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM2017022414170702295','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201702241447571075','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SR201600000000000000','SM201702241530394485','admin',now(),NULL,'CD-CZH000001');

/*
-- Query: SELECT code,type,account,password,remark,company_code,system_code FROM zhpay_std_user.tstd_cpassword where system_code = 'CD-CZH000001'
-- Date: 2017-01-06 12:00
*/
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CZH000001-001','1','org_name','tianleios',NULL,'CD-CZH000001','CD-CZH000001');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CZH000001-002','1','app_name','zh-dev',NULL,'CD-CZH000001','CD-CZH000001');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CZH000001-003','2','ACCESS_KEY','M0atdzBYOQ-oloFpRJFtX7HDDU1NTBBvRUu3MS1T',NULL,'CD-CZH000001','CD-CZH000001');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CZH000001-004','2','SECRET_KEY','F8eJ94o1WoFIB7VxTwtI5rB8RLi7IHC7cY47Bnwh',NULL,'CD-CZH000001','CD-CZH000001');
INSERT INTO `tstd_cpassword` (`code`,`type`,`account`,`password`,`remark`,`company_code`,`system_code`) VALUES ('CD-CZH000001-005','2','bucket','tianleidb',NULL,'CD-CZH000001','CD-CZH000001');


/*
-- Query: select `type`,`parent_key`,`dkey`,`dvalue`,`updater`,now() as update_datetime,`remark`,`system_code` from tsys_dict where system_code = 'CD-CZH000001'
LIMIT 0, 10000

-- Date: 2017-02-24 20:43
*/
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_status','用户状态','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','0','正常','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','1','程序锁定','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','2','人工锁定','admin',now(),NULL,'CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'id_kind','证件类型','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','id_kind','1','身份证','admin',now(),NULL,'CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'role_level','角色等级','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','1','运维','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','2','运营','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','3','客户','admin',now(),NULL,'CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'res_type','资源类型','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','1','菜单','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','2','按钮','admin',now(),NULL,'CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'lock_direction','锁定方向','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','1','锁定','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','2','解锁','admin',now(),NULL,'CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'partner_status','合伙人状态','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','partner_status','0','合伙中','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','partner_status','2','已解散合伙','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'news_status','信息状态','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','news_status','0','待发送','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','news_status','1','已发送','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','news_status','2','发送失败','admin',now(),NULL,'CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'toKind','针对人群','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','toKind','1','C端用户','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','toKind','2','B端用户','admin',now(),NULL,'CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','toKind','3','平台用户','admin',now(),NULL,'CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'currency_kind','货币种类','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency_kind','CNY','人民币','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency_kind','GXJL','贡献奖励','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency_kind','QBB','钱包币','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency_kind','GWB','购物币','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency_kind','HBB','红包','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency_kind','HBYJ','红包业绩','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','currency_kind','FRB','分润','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'bankcard_status','银行卡状态','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','bankcard_status','0','弃用','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','bankcard_status','1','启用','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'account_kind','账户类型','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_kind','C','C端用户','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_kind','P','平台用户','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_kind','PA','合伙人','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'account_status','账户状态','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_status','0','正常','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_status','1','程序锁定','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','account_status','2','人工锁定','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'jour_status','流水状态','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','0','刚生成待回调','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','1','已回调通过待对账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','2','回调不通过','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','3','已对账且账不平','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','4','账不平待调账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','5','已调账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','9','无需对账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','6','待审批','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','7','审批通过','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','jour_status','8','审批不通过','admin',now(),'','CD-CZH000001');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'channel_type','渠道类型','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','01','线下_橙账本','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','0','内部账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','9','调账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','10','轧账','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','1','币种兑换','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','30','支付宝APP支付','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','35','微信公众号支付','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','36','微信APP支付','admin',now(),'','CD-CZH000001');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','channel_type','40','网银代付','admin',now(),'','CD-CZH000001');