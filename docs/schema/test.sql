insert into `tbh_product` (`code`, `is_free`, `name`, `ad_price`, `price`, `vir_number`, `real_number`, `order_no`, `pic`, `adv_pic`, `slogan`, `is_total`, `status`, `create_datetime`, `updater`, `update_datetime`, `remark`) values('P201809050948485503639','1','0903产品1','100000','2000000','100','200','2','正汇aaa3 (2) (1)_1535939332638.jpg','319617275473843876254919117829_1535939325803.png||363429678576263141417555334359_1535939328715.png','广告语广告语广告语广告语','1','2','2018-09-05 09:48:48','USYS201800000000002','2018-09-05 20:37:21','');
insert into `tbh_product` (`code`, `is_free`, `name`, `ad_price`, `price`, `vir_number`, `real_number`, `order_no`, `pic`, `adv_pic`, `slogan`, `is_total`, `status`, `create_datetime`, `updater`, `update_datetime`, `remark`) values('P201809051055046003642','0','产品2','200000','300000','300','3000','1','f3940189b55b19cc9156db67d5b08ea7_1535943297874.jpg','banner_1535943294673.png','广告语广告语广告语广告语','0','2','2018-09-05 10:55:04','USYS201800000000002','2018-09-05 21:20:21','');





insert into `tbh_specs` (`code`, `product_code`, `is_single`, `single_number`, `ref_code`, `name`, `number`, `weight`, `is_sq_order`, `is_sj_order`, `is_normal_order`, `stock_number`) values('PS201809050948485549003','P201809050948485503639','0',NULL,'','箱','1','100000','1','1','1','811');
insert into `tbh_specs` (`code`, `product_code`, `is_single`, `single_number`, `ref_code`, `name`, `number`, `weight`, `is_sq_order`, `is_sj_order`, `is_normal_order`, `stock_number`) values('PS201809050956413305842','P201809050948485503639','1','2','PS201809050948485549003','普通单','10','10000','0','0','1','650');
insert into `tbh_specs` (`code`, `product_code`, `is_single`, `single_number`, `ref_code`, `name`, `number`, `weight`, `is_sq_order`, `is_sj_order`, `is_normal_order`, `stock_number`) values('PS20180905095825182427','P201809050948485503639','1','1','','授权单','10','10000','1','0','0','1000');
insert into `tbh_specs` (`code`, `product_code`, `is_single`, `single_number`, `ref_code`, `name`, `number`, `weight`, `is_sq_order`, `is_sj_order`, `is_normal_order`, `stock_number`) values('PS201809051055046012495','P201809051055046003642','0',NULL,'','黄色','1','10000','0','0','0','85');
insert into `tbh_specs` (`code`, `product_code`, `is_single`, `single_number`, `ref_code`, `name`, `number`, `weight`, `is_sq_order`, `is_sj_order`, `is_normal_order`, `stock_number`) values('PS201809052037124888703','P201809050948485503639','1','2',NULL,'升级单','100','10000','0','1','0','10000');






insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050948485616947','PS201809050948485549003','1','100','20000','1','1','10000','10000','10000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050948485628129','PS201809050948485549003','2','30000','40000','2','2','100000','10000','100000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050948485655191','PS201809050948485549003','3','50000','60000','2','2','20000','20000','20000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050948485658794','PS201809050948485549003','4','70000','80000','1','1','20000','20000','20000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050948485665746','PS201809050948485549003','5','90000','100000','1','1','10000','10000','100000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050948485681770','PS201809050948485549003','6','110000','120000','1','1','10000','10000','10000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050956413311664','PS201809050956413305842','1','20000','30000','1','1','10000','10000','10000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050956413312441','PS201809050956413305842','2','40000','50000','2','2','1000','10000','10000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050956413324792','PS201809050956413305842','3','60000','70000','2','2','20000','20000','20000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050956413324889','PS201809050956413305842','4','80000','90000','2','2','20000','20000','20000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP2018090509564133333','PS201809050956413305842','5','100000','110000','1','1','20000','20000','20000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050956413335158','PS201809050956413305842','6','120000','130000','2','2','1000','10000','100000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050958251857499','PS20180905095825182427','1','11000','22000','1','1','10000','10000','10000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050958251868699','PS20180905095825182427','2','33000','44000','2','2','10000','10000','10000','0');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050958251871956','PS20180905095825182427','3','55000','66000','1','1','20000','200000','200000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050958251875162','PS20180905095825182427','4','77000','88000','1','1','10000','10000','100000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050958251883650','PS20180905095825182427','5','99000','110000','1','1','20000','20000','200000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809050958251896258','PS20180905095825182427','6','120000','130000','2','2','10000','10000','10000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809051055046022135','PS201809051055046012495','1','11000','22000','1','1','10000','10000','10000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809051055046031063','PS201809051055046012495','2','33000','44000','1','1','1000','1000','10000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809051055046037033','PS201809051055046012495','3','55000','66000','1','1','10000','10000','10000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809051055046038823','PS201809051055046012495','4','77000','88000','2','2','10000','10000','100000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809051055046041095','PS201809051055046012495','5','99000','111000','2','2','200000','200000','20000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809051055046042695','PS201809051055046012495','6','222000','333000','2','2','30000','300000','300000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809052037124894802','PS201809052037124888703','1','33000','44000','1','1','10000','10000','100000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809052037124897746','PS201809052037124888703','2','55000','66000','1','1','10000','10000','10000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP20180905203712490580','PS201809052037124888703','5','122000','133000','2','2','20000','20000','20000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP2018090520371249074','PS201809052037124888703','3','77000','88000','2','2','10000','10000','10000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809052037124908701','PS201809052037124888703','4','99000','111000','2','2','20000','20000','20000','1');
insert into `tbh_agent_price` (`code`, `specs_code`, `level`, `price`, `change_price`, `start_number`, `min_number`, `daily_number`, `weekly_number`, `monthly_number`, `is_buy`) values('PSP201809052037124912438','PS201809052037124888703','6','144000','155000','2','2','20000','200000','20000','1');





insert into `tbh_tj_award` (`code`, `product_code`, `level`, `value1`, `value2`, `value3`) values('AW201809050948485708856','P201809050948485503639','1','9.000','8.000','7.000');
insert into `tbh_tj_award` (`code`, `product_code`, `level`, `value1`, `value2`, `value3`) values('AW201809050948485828601','P201809050948485503639','4','6.000','5.000','4.000');
insert into `tbh_tj_award` (`code`, `product_code`, `level`, `value1`, `value2`, `value3`) values('AW201809050948485829165','P201809050948485503639','2','8.000','7.000','6.000');
insert into `tbh_tj_award` (`code`, `product_code`, `level`, `value1`, `value2`, `value3`) values('AW201809050948485829746','P201809050948485503639','3','7.000','6.000','5.000');
insert into `tbh_tj_award` (`code`, `product_code`, `level`, `value1`, `value2`, `value3`) values('AW201809050948485838535','P201809050948485503639','5','5.000','4.000','3.000');
insert into `tbh_tj_award` (`code`, `product_code`, `level`, `value1`, `value2`, `value3`) values('AW201809051055046049992','P201809051055046003642','1','8.000','8.000','8.000');
insert into `tbh_tj_award` (`code`, `product_code`, `level`, `value1`, `value2`, `value3`) values('AW201809051055046052062','P201809051055046003642','4','5.000','5.000','5.000');
insert into `tbh_tj_award` (`code`, `product_code`, `level`, `value1`, `value2`, `value3`) values('AW201809051055046059188','P201809051055046003642','3','6.000','6.000','6.000');
insert into `tbh_tj_award` (`code`, `product_code`, `level`, `value1`, `value2`, `value3`) values('AW201809051055046059800','P201809051055046003642','2','7.000','7.000','7.000');
insert into `tbh_tj_award` (`code`, `product_code`, `level`, `value1`, `value2`, `value3`) values('AW201809051055046068478','P201809051055046003642','5','4.000','4.000','4.000');
