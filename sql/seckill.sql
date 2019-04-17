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
`version` INT ( 11 ) NOT NULL COMMENT '乐观锁版本号',
PRIMARY KEY ( `id` )
) ENGINE = INNODB AUTO_INCREMENT = 2 DEFAULT charset = utf8;


CREATE TABLE `stock_order` (
`id` INT ( 11 ) UNSIGNED NOT NULL AUTO_INCREMENT,
`sid` INT ( 11 ) NOT NULL COMMENT '库存ID',
`name` VARCHAR ( 30 ) NOT NULL DEFAULT '' COMMENT '商品名称',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY ( `id` ) 
) ENGINE = INNODB auto_increment = 55 DEFAULT charset = utf8;