insert into `tsys_menu` (`code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `parent_code`, `system_code`) values('SM201809171901328717564','代申请','2','/daishenqing','1','USYS201800000000002',NOW(),'','SM201808160334413891953','CD-CBH000020');
insert into `tsys_menu` (`code`, `name`, `type`, `url`, `order_no`, `updater`, `update_datetime`, `remark`, `parent_code`, `system_code`) values('SM20180919111511797842','作废','2','/cancellation','1','USYS201800000000002',NOW(),'','SM201809051505212104563','CD-CBH000020');


insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM201809171901328717564','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
insert into `tsys_menu_role` (`role_code`, `menu_code`, `updater`, `update_datetime`, `remark`, `system_code`) values('RO201800000000000001','SM20180919111511797842','USYS201800000000002',NOW(),NULL,'CD-CBH000020');
