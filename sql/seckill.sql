DROP DATABASE
	IF
		EXISTS `distribute_seckill`;

CREATE DATABASE `distribute_seckill`;

USE distribute_seckill;-- 初始化数据库脚本

DROP TABLE
	IF
		EXISTS `stock`;

DROP TABLE
	IF
		EXISTS `stock_order`;

CREATE TABLE `stock` (
						 `id` INT ( 11 ) UNSIGNED NOT NULL AUTO_INCREMENT,
						 `name` VARCHAR ( 50 ) NOT NULL DEFAULT '' COMMENT '名称',
						 `count` INT ( 11 ) NOT NULL COMMENT '库存',
						 `sale` INT ( 11 ) NOT NULL COMMENT '已售',
						 `start_time` timestamp NOT NULL COMMENT '秒杀开始时间',
						 `end_time` timestamp NOT NULL DEFAULT '1970-01-01 10:00:00' COMMENT '秒杀结束时间',
						 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
						 `version` INT ( 11 ) NOT NULL COMMENT '乐观锁版本号',
						 PRIMARY KEY ( `id` )
) ENGINE = INNODB AUTO_INCREMENT = 2 DEFAULT charset = utf8;


CREATE TABLE `stock_order` (
							   `id` INT ( 11 ) UNSIGNED NOT NULL COMMENT '库存ID',
							   `name` VARCHAR ( 30 ) NOT NULL DEFAULT '' COMMENT '商品名称',
							   `user_phone` bigint NOT NULL COMMENT '用户手机号',
							   `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
							   PRIMARY KEY(id, user_phone)  /*联合主键，防止一个用户对同一个产品做秒杀*/
) ENGINE = INNODB auto_increment = 55 DEFAULT charset = utf8 COMMENT='秒杀成功明细表';

insert into `stock` (name, count, sale, start_time, end_time, version) values ('火车票', 10, 0, '2019-3-24 00:00:00', '2019-8-24 00:00:00', 0);

insert into `stock_order` (id, name, user_phone) VALUES (2, '火车票', 18161225484);

update stock
set `count` = `count` - 1
  , `sale` = `sale` + 1
where id=2
  and `count` > 0;

select
	so.id,
	so.name,
	so.user_phone,
	s.id "stock.id",
	s.count "stock.count",
	s.sale "stock.sale",
	s.start_time "stock.start_time",
	s.end_time "stock.end_time",
	s.create_time "stock.create_time",
	s.version "stock.version"

from stock_order so
		 inner join stock s on so.id=s.id
where so.id=2
  and so.user_phone=18161225484;