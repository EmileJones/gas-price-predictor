-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.27 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 ruoyi 的数据库结构
CREATE DATABASE IF NOT EXISTS `ruoyi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ruoyi`;

-- 导出  表 ruoyi.gas_station_geo 结构
CREATE TABLE IF NOT EXISTS `gas_station_geo` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '地理信息主键',
  `system_station_id` varchar(64) DEFAULT NULL COMMENT '系统内加油站ID',
  `out_system_station_id` varchar(64) DEFAULT NULL COMMENT '系统外加油站ID',
  `distance` double DEFAULT NULL COMMENT '两加油站之间的距离',
  `traffic_lights` int DEFAULT NULL COMMENT '两加油站之间路线红绿灯数',
  `traffic_factor` double DEFAULT NULL COMMENT '两加油站红绿灯数的影响因子',
  `route_shape` varchar(512) DEFAULT NULL COMMENT '路线形状',
  `route_shape_factor` double DEFAULT NULL COMMENT '路线曲折度影响系数',
  `route_factor` double DEFAULT NULL COMMENT '路线影响系数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='加油站地理信息表';

-- 正在导出表  ruoyi.gas_station_geo 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `gas_station_geo` DISABLE KEYS */;
/*!40000 ALTER TABLE `gas_station_geo` ENABLE KEYS */;

-- 导出  表 ruoyi.gas_station_info 结构
CREATE TABLE IF NOT EXISTS `gas_station_info` (
  `id` varchar(64) NOT NULL COMMENT '唯一ID',
  `name` varchar(512) NOT NULL COMMENT '加油站名称',
  `location` varchar(64) NOT NULL COMMENT '加油站位置',
  `province` varchar(4) NOT NULL COMMENT '加油站所在省',
  `city` varchar(32) NOT NULL COMMENT '加油站所在市',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='加油站信息表';

-- 正在导出表  ruoyi.gas_station_info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `gas_station_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `gas_station_info` ENABLE KEYS */;

-- 导出  表 ruoyi.gen_table 结构
CREATE TABLE IF NOT EXISTS `gen_table` (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成业务表';

-- 正在导出表  ruoyi.gen_table 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `gen_table` DISABLE KEYS */;
INSERT INTO `gen_table` (`table_id`, `table_name`, `table_comment`, `sub_table_name`, `sub_table_fk_name`, `class_name`, `tpl_category`, `package_name`, `module_name`, `business_name`, `function_name`, `function_author`, `gen_type`, `gen_path`, `options`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, 'gas_station_geo', '加油站地理信息表', NULL, NULL, 'GasStationGeo', 'crud', 'com.ruoyi.gas', 'gas', 'geo', '加油站地理信息', 'ruoyi', '0', '/', NULL, 'admin', '2022-01-21 21:21:31', '', NULL, NULL),
	(2, 'gas_station_info', '加油站信息表', NULL, NULL, 'GasStationInfo', 'crud', 'com.ruoyi.gas', 'gas', 'info', '加油站信息', 'ruoyi', '0', '/', NULL, 'admin', '2022-01-21 21:21:32', '', NULL, NULL),
	(3, 't_user', '测试用户表', NULL, NULL, 'TUser', 'crud', 'com.ruoyi.gas', 'gas', 'user', '测试用户', 'ruoyi', '0', '/', NULL, 'admin', '2022-01-22 09:25:21', '', NULL, NULL);
/*!40000 ALTER TABLE `gen_table` ENABLE KEYS */;

-- 导出  表 ruoyi.gen_table_column 结构
CREATE TABLE IF NOT EXISTS `gen_table_column` (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成业务表字段';

-- 正在导出表  ruoyi.gen_table_column 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `gen_table_column` DISABLE KEYS */;
INSERT INTO `gen_table_column` (`column_id`, `table_id`, `column_name`, `column_comment`, `column_type`, `java_type`, `java_field`, `is_pk`, `is_increment`, `is_required`, `is_insert`, `is_edit`, `is_list`, `is_query`, `query_type`, `html_type`, `dict_type`, `sort`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, '1', 'id', '地理信息主键', 'bigint', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-01-21 21:21:31', '', NULL),
	(2, '1', 'system_station_id', '系统内加油站ID', 'varchar(64)', 'String', 'systemStationId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-01-21 21:21:31', '', NULL),
	(3, '1', 'out_system_station_id', '系统外加油站ID', 'varchar(64)', 'String', 'outSystemStationId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-01-21 21:21:31', '', NULL),
	(4, '1', 'distance', '两加油站之间的距离', 'double', 'Long', 'distance', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-01-21 21:21:31', '', NULL),
	(5, '1', 'traffic_lights', '两加油站之间路线红绿灯数', 'int', 'Long', 'trafficLights', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-01-21 21:21:31', '', NULL),
	(6, '1', 'traffic_factor', '两加油站红绿灯数的影响因子', 'double', 'Long', 'trafficFactor', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-01-21 21:21:31', '', NULL),
	(7, '1', 'route_shape', '路线形状', 'varchar(512)', 'String', 'routeShape', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 7, 'admin', '2022-01-21 21:21:31', '', NULL),
	(8, '1', 'route_shape_factor', '路线曲折度影响系数', 'double', 'Long', 'routeShapeFactor', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-01-21 21:21:31', '', NULL),
	(9, '1', 'route_factor', '路线影响系数', 'double', 'Long', 'routeFactor', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2022-01-21 21:21:32', '', NULL),
	(10, '2', 'id', '唯一ID', 'varchar(64)', 'String', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-01-21 21:21:32', '', NULL),
	(11, '2', 'name', '加油站名称', 'varchar(512)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'textarea', '', 2, 'admin', '2022-01-21 21:21:32', '', NULL),
	(12, '2', 'location', '加油站位置', 'varchar(64)', 'String', 'location', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-01-21 21:21:32', '', NULL),
	(13, '2', 'province', '加油站所在省', 'varchar(4)', 'String', 'province', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-01-21 21:21:32', '', NULL),
	(14, '2', 'city', '加油站所在市', 'varchar(32)', 'String', 'city', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-01-21 21:21:32', '', NULL),
	(15, '3', 'id', '用户ID', 'bigint', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-01-22 09:25:21', '', NULL),
	(16, '3', 'username', '用户名', 'varchar(128)', 'String', 'username', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2022-01-22 09:25:21', '', NULL),
	(17, '3', 'email', '邮箱', 'varchar(512)', 'String', 'email', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 3, 'admin', '2022-01-22 09:25:21', '', NULL);
/*!40000 ALTER TABLE `gen_table_column` ENABLE KEYS */;

-- 导出  表 ruoyi.qrtz_blob_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `blob_data` blob COMMENT '存放持久化Trigger对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Blob类型的触发器表';

-- 正在导出表  ruoyi.qrtz_blob_triggers 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;

-- 导出  表 ruoyi.qrtz_calendars 结构
CREATE TABLE IF NOT EXISTS `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日历信息表';

-- 正在导出表  ruoyi.qrtz_calendars 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;

-- 导出  表 ruoyi.qrtz_cron_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `cron_expression` varchar(200) NOT NULL COMMENT 'cron表达式',
  `time_zone_id` varchar(80) DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Cron类型的触发器表';

-- 正在导出表  ruoyi.qrtz_cron_triggers 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_cron_triggers` (`sched_name`, `trigger_name`, `trigger_group`, `cron_expression`, `time_zone_id`) VALUES
	('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai'),
	('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/15 * * * * ?', 'Asia/Shanghai'),
	('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;

-- 导出  表 ruoyi.qrtz_fired_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint NOT NULL COMMENT '触发的时间',
  `sched_time` bigint NOT NULL COMMENT '定时器制定的时间',
  `priority` int NOT NULL COMMENT '优先级',
  `state` varchar(16) NOT NULL COMMENT '状态',
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='已触发的触发器表';

-- 正在导出表  ruoyi.qrtz_fired_triggers 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;

-- 导出  表 ruoyi.qrtz_job_details 结构
CREATE TABLE IF NOT EXISTS `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) NOT NULL COMMENT '任务组名',
  `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务详细信息表';

-- 正在导出表  ruoyi.qrtz_job_details 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
INSERT INTO `qrtz_job_details` (`sched_name`, `job_name`, `job_group`, `description`, `job_class_name`, `is_durable`, `is_nonconcurrent`, `is_update_data`, `requests_recovery`, `job_data`) VALUES
	('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', _binary 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000f5441534b5f50524f504552544945537372001e636f6d2e72756f79692e71756172747a2e646f6d61696e2e5379734a6f6200000000000000010200084c000a636f6e63757272656e747400124c6a6176612f6c616e672f537472696e673b4c000e63726f6e45787072657373696f6e71007e00094c000c696e766f6b6554617267657471007e00094c00086a6f6247726f757071007e00094c00056a6f6249647400104c6a6176612f6c616e672f4c6f6e673b4c00076a6f624e616d6571007e00094c000d6d697366697265506f6c69637971007e00094c000673746174757371007e000978720027636f6d2e72756f79692e636f6d6d6f6e2e636f72652e646f6d61696e2e42617365456e7469747900000000000000010200074c0008637265617465427971007e00094c000a63726561746554696d657400104c6a6176612f7574696c2f446174653b4c0006706172616d7371007e00034c000672656d61726b71007e00094c000b73656172636856616c756571007e00094c0008757064617465427971007e00094c000a75706461746554696d6571007e000c787074000561646d696e7372000e6a6176612e7574696c2e44617465686a81014b597419030000787077080000017e6b36130878707400007070707400013174000e302f3130202a202a202a202a203f74001172795461736b2e72794e6f506172616d7374000744454641554c547372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078700000000000000001740018e7b3bbe7bb9fe9bb98e8aea4efbc88e697a0e58f82efbc8974000133740001317800),
	('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', _binary 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000f5441534b5f50524f504552544945537372001e636f6d2e72756f79692e71756172747a2e646f6d61696e2e5379734a6f6200000000000000010200084c000a636f6e63757272656e747400124c6a6176612f6c616e672f537472696e673b4c000e63726f6e45787072657373696f6e71007e00094c000c696e766f6b6554617267657471007e00094c00086a6f6247726f757071007e00094c00056a6f6249647400104c6a6176612f6c616e672f4c6f6e673b4c00076a6f624e616d6571007e00094c000d6d697366697265506f6c69637971007e00094c000673746174757371007e000978720027636f6d2e72756f79692e636f6d6d6f6e2e636f72652e646f6d61696e2e42617365456e7469747900000000000000010200074c0008637265617465427971007e00094c000a63726561746554696d657400104c6a6176612f7574696c2f446174653b4c0006706172616d7371007e00034c000672656d61726b71007e00094c000b73656172636856616c756571007e00094c0008757064617465427971007e00094c000a75706461746554696d6571007e000c787074000561646d696e7372000e6a6176612e7574696c2e44617465686a81014b597419030000787077080000017e6b36130878707400007070707400013174000e302f3135202a202a202a202a203f74001572795461736b2e7279506172616d7328277279272974000744454641554c547372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078700000000000000002740018e7b3bbe7bb9fe9bb98e8aea4efbc88e69c89e58f82efbc8974000133740001317800),
	('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 'com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', _binary 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000f5441534b5f50524f504552544945537372001e636f6d2e72756f79692e71756172747a2e646f6d61696e2e5379734a6f6200000000000000010200084c000a636f6e63757272656e747400124c6a6176612f6c616e672f537472696e673b4c000e63726f6e45787072657373696f6e71007e00094c000c696e766f6b6554617267657471007e00094c00086a6f6247726f757071007e00094c00056a6f6249647400104c6a6176612f6c616e672f4c6f6e673b4c00076a6f624e616d6571007e00094c000d6d697366697265506f6c69637971007e00094c000673746174757371007e000978720027636f6d2e72756f79692e636f6d6d6f6e2e636f72652e646f6d61696e2e42617365456e7469747900000000000000010200074c0008637265617465427971007e00094c000a63726561746554696d657400104c6a6176612f7574696c2f446174653b4c0006706172616d7371007e00034c000672656d61726b71007e00094c000b73656172636856616c756571007e00094c0008757064617465427971007e00094c000a75706461746554696d6571007e000c787074000561646d696e7372000e6a6176612e7574696c2e44617465686a81014b597419030000787077080000017e6b36130878707400007070707400013174000e302f3230202a202a202a202a203f74003872795461736b2e72794d756c7469706c65506172616d7328277279272c20747275652c20323030304c2c203331362e3530442c203130302974000744454641554c547372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078700000000000000003740018e7b3bbe7bb9fe9bb98e8aea4efbc88e5a49ae58f82efbc8974000133740001317800);
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;

-- 导出  表 ruoyi.qrtz_locks 结构
CREATE TABLE IF NOT EXISTS `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='存储的悲观锁信息表';

-- 正在导出表  ruoyi.qrtz_locks 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
INSERT INTO `qrtz_locks` (`sched_name`, `lock_name`) VALUES
	('RuoyiScheduler', 'STATE_ACCESS'),
	('RuoyiScheduler', 'TRIGGER_ACCESS');
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;

-- 导出  表 ruoyi.qrtz_paused_trigger_grps 结构
CREATE TABLE IF NOT EXISTS `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='暂停的触发器表';

-- 正在导出表  ruoyi.qrtz_paused_trigger_grps 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;

-- 导出  表 ruoyi.qrtz_scheduler_state 结构
CREATE TABLE IF NOT EXISTS `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='调度器状态表';

-- 正在导出表  ruoyi.qrtz_scheduler_state 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
INSERT INTO `qrtz_scheduler_state` (`sched_name`, `instance_name`, `last_checkin_time`, `checkin_interval`) VALUES
	('RuoyiScheduler', 'DESKTOP-OEFMB001642841947516', 1642842505919, 15000);
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;

-- 导出  表 ruoyi.qrtz_simple_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `repeat_count` bigint NOT NULL COMMENT '重复的次数统计',
  `repeat_interval` bigint NOT NULL COMMENT '重复的间隔时间',
  `times_triggered` bigint NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='简单触发器的信息表';

-- 正在导出表  ruoyi.qrtz_simple_triggers 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;

-- 导出  表 ruoyi.qrtz_simprop_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `str_prop_1` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `str_prop_2` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `str_prop_3` varchar(512) DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `int_prop_1` int DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `int_prop_2` int DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `long_prop_1` bigint DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `long_prop_2` bigint DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `dec_prop_1` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `dec_prop_2` decimal(13,4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `bool_prop_1` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `bool_prop_2` varchar(1) DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='同步机制的行锁表';

-- 正在导出表  ruoyi.qrtz_simprop_triggers 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;

-- 导出  表 ruoyi.qrtz_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) NOT NULL COMMENT '触发器的名字',
  `trigger_group` varchar(200) NOT NULL COMMENT '触发器所属组的名字',
  `job_name` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `job_group` varchar(200) NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `description` varchar(250) DEFAULT NULL COMMENT '相关介绍',
  `next_fire_time` bigint DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `prev_fire_time` bigint DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
  `priority` int DEFAULT NULL COMMENT '优先级',
  `trigger_state` varchar(16) NOT NULL COMMENT '触发器状态',
  `trigger_type` varchar(8) NOT NULL COMMENT '触发器的类型',
  `start_time` bigint NOT NULL COMMENT '开始时间',
  `end_time` bigint DEFAULT NULL COMMENT '结束时间',
  `calendar_name` varchar(200) DEFAULT NULL COMMENT '日程表名称',
  `misfire_instr` smallint DEFAULT NULL COMMENT '补偿执行的策略',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='触发器详细信息表';

-- 正在导出表  ruoyi.qrtz_triggers 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`, `job_name`, `job_group`, `description`, `next_fire_time`, `prev_fire_time`, `priority`, `trigger_state`, `trigger_type`, `start_time`, `end_time`, `calendar_name`, `misfire_instr`, `job_data`) VALUES
	('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', NULL, 1642841950000, -1, 5, 'PAUSED', 'CRON', 1642841947000, 0, NULL, 2, _binary ''),
	('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', NULL, 1642841955000, -1, 5, 'PAUSED', 'CRON', 1642841947000, 0, NULL, 2, _binary ''),
	('RuoyiScheduler', 'TASK_CLASS_NAME3', 'DEFAULT', 'TASK_CLASS_NAME3', 'DEFAULT', NULL, 1642841960000, -1, 5, 'PAUSED', 'CRON', 1642841947000, 0, NULL, 2, _binary '');
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_config 结构
CREATE TABLE IF NOT EXISTS `sys_config` (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数配置表';

-- 正在导出表  ruoyi.sys_config 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` (`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2022-01-18 11:23:33', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),
	(2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2022-01-18 11:23:33', '', NULL, '初始化密码 123456'),
	(3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2022-01-18 11:23:33', '', NULL, '深色主题theme-dark，浅色主题theme-light'),
	(4, '账号自助-验证码开关', 'sys.account.captchaOnOff', 'true', 'Y', 'admin', '2022-01-18 11:23:33', '', NULL, '是否开启验证码功能（true开启，false关闭）'),
	(5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2022-01-18 11:23:33', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_dept 结构
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';

-- 正在导出表  ruoyi.sys_dept 的数据：~10 rows (大约)
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `ancestors`, `dept_name`, `order_num`, `leader`, `phone`, `email`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-18 11:23:31', '', NULL),
	(101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-18 11:23:31', '', NULL),
	(102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-18 11:23:31', '', NULL),
	(103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-18 11:23:31', '', NULL),
	(104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-18 11:23:31', '', NULL),
	(105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-18 11:23:31', '', NULL),
	(106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-18 11:23:31', '', NULL),
	(107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-18 11:23:31', '', NULL),
	(108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-18 11:23:31', '', NULL),
	(109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-01-18 11:23:31', '', NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_dict_data 结构
CREATE TABLE IF NOT EXISTS `sys_dict_data` (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';

-- 正在导出表  ruoyi.sys_dict_data 的数据：~28 rows (大约)
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '性别男'),
	(2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '性别女'),
	(3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '性别未知'),
	(4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '显示菜单'),
	(5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '隐藏菜单'),
	(6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '正常状态'),
	(7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '停用状态'),
	(8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '正常状态'),
	(9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '停用状态'),
	(10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '默认分组'),
	(11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '系统分组'),
	(12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '系统默认是'),
	(13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '系统默认否'),
	(14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '通知'),
	(15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '公告'),
	(16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '正常状态'),
	(17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '关闭状态'),
	(18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '新增操作'),
	(19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '修改操作'),
	(20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '删除操作'),
	(21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '授权操作'),
	(22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '导出操作'),
	(23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '导入操作'),
	(24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '强退操作'),
	(25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '生成操作'),
	(26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '清空操作'),
	(27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '正常状态'),
	(28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-01-18 11:23:33', '', NULL, '停用状态');
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_dict_type 结构
CREATE TABLE IF NOT EXISTS `sys_dict_type` (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典类型表';

-- 正在导出表  ruoyi.sys_dict_type 的数据：~10 rows (大约)
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '用户性别列表'),
	(2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '菜单状态列表'),
	(3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '系统开关列表'),
	(4, '任务状态', 'sys_job_status', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '任务状态列表'),
	(5, '任务分组', 'sys_job_group', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '任务分组列表'),
	(6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '系统是否列表'),
	(7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '通知类型列表'),
	(8, '通知状态', 'sys_notice_status', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '通知状态列表'),
	(9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '操作类型列表'),
	(10, '系统状态', 'sys_common_status', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '登录状态列表');
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_job 结构
CREATE TABLE IF NOT EXISTS `sys_job` (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务调度表';

-- 正在导出表  ruoyi.sys_job 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` (`job_id`, `job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2022-01-18 11:23:33', '', NULL, ''),
	(2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2022-01-18 11:23:33', '', NULL, ''),
	(3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2022-01-18 11:23:33', '', NULL, '');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_job_log 结构
CREATE TABLE IF NOT EXISTS `sys_job_log` (
  `job_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务调度日志表';

-- 正在导出表  ruoyi.sys_job_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_job_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_job_log` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_logininfor 结构
CREATE TABLE IF NOT EXISTS `sys_logininfor` (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问记录';

-- 正在导出表  ruoyi.sys_logininfor 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_logininfor` DISABLE KEYS */;
INSERT INTO `sys_logininfor` (`info_id`, `user_name`, `ipaddr`, `login_location`, `browser`, `os`, `status`, `msg`, `login_time`) VALUES
	(100, 'admin', '127.0.0.1', '内网IP', 'Firefox 9', 'Windows 10', '0', '登录成功', '2022-01-18 11:37:07'),
	(101, 'admin', '127.0.0.1', '内网IP', 'Firefox 9', 'Windows 10', '0', '登录成功', '2022-01-20 20:03:32'),
	(102, 'admin', '127.0.0.1', '内网IP', 'Firefox 9', 'Windows 10', '0', '登录成功', '2022-01-21 21:19:47'),
	(103, 'admin', '127.0.0.1', '内网IP', 'Firefox 9', 'Windows 10', '1', '验证码错误', '2022-01-22 09:24:50'),
	(104, 'admin', '127.0.0.1', '内网IP', 'Firefox 9', 'Windows 10', '0', '登录成功', '2022-01-22 09:24:55'),
	(105, 'admin', '127.0.0.1', '内网IP', 'Firefox 9', 'Windows 10', '0', '登录成功', '2022-01-22 13:21:41'),
	(106, 'admin', '127.0.0.1', '内网IP', 'Firefox 9', 'Windows 10', '0', '登录成功', '2022-01-22 15:10:29'),
	(107, 'admin', '127.0.0.1', '内网IP', 'Firefox 9', 'Windows 10', '0', '登录成功', '2022-01-22 15:41:38');
/*!40000 ALTER TABLE `sys_logininfor` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2020 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

-- 正在导出表  ruoyi.sys_menu 的数据：~83 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2022-01-18 11:23:32', '', NULL, '系统管理目录'),
	(2, '系统监控', 0, 2, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2022-01-18 11:23:32', '', NULL, '系统监控目录'),
	(3, '系统工具', 0, 3, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2022-01-18 11:23:32', '', NULL, '系统工具目录'),
	(4, '若依官网', 0, 4, 'http://ruoyi.vip', NULL, '', 0, 0, 'M', '1', '1', '', 'guide', 'admin', '2022-01-18 11:23:32', 'admin', '2022-01-21 22:05:41', '若依官网地址'),
	(100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2022-01-18 11:23:32', '', NULL, '用户管理菜单'),
	(101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2022-01-18 11:23:32', '', NULL, '角色管理菜单'),
	(102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2022-01-18 11:23:32', '', NULL, '菜单管理菜单'),
	(103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2022-01-18 11:23:32', '', NULL, '部门管理菜单'),
	(104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2022-01-18 11:23:32', '', NULL, '岗位管理菜单'),
	(105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2022-01-18 11:23:32', '', NULL, '字典管理菜单'),
	(106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2022-01-18 11:23:32', '', NULL, '参数设置菜单'),
	(107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2022-01-18 11:23:32', '', NULL, '通知公告菜单'),
	(108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2022-01-18 11:23:32', '', NULL, '日志管理菜单'),
	(109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2022-01-18 11:23:32', '', NULL, '在线用户菜单'),
	(110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2022-01-18 11:23:32', '', NULL, '定时任务菜单'),
	(111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2022-01-18 11:23:32', '', NULL, '数据监控菜单'),
	(112, '服务监控', 2, 4, 'server', 'monitor/server/index', '', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2022-01-18 11:23:32', '', NULL, '服务监控菜单'),
	(113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2022-01-18 11:23:32', '', NULL, '缓存监控菜单'),
	(114, '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2022-01-18 11:23:32', '', NULL, '表单构建菜单'),
	(115, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2022-01-18 11:23:32', '', NULL, '代码生成菜单'),
	(116, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', '', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2022-01-18 11:23:32', '', NULL, '系统接口菜单'),
	(500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2022-01-18 11:23:32', '', NULL, '操作日志菜单'),
	(501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2022-01-18 11:23:32', '', NULL, '登录日志菜单'),
	(1001, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1002, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1003, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1004, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1005, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1006, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1007, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1008, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1009, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1010, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1011, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1012, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1013, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1014, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1015, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1016, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1017, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1018, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1019, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1020, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1021, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1022, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1023, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1024, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1025, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1026, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1027, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1028, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1029, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1030, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1031, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1032, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1033, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1034, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1035, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1036, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1037, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1038, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1039, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1040, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1041, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1042, '日志导出', 500, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1043, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1044, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1045, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1049, '任务查询', 110, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1050, '任务新增', 110, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1051, '任务修改', 110, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1052, '任务删除', 110, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1053, '状态修改', 110, 5, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1054, '任务导出', 110, 7, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1055, '生成查询', 115, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1056, '生成修改', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1057, '生成删除', 115, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1058, '导入代码', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1059, '预览代码', 115, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(1060, '生成代码', 115, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(2000, '加油站自助服务', 0, 0, 'gas', NULL, NULL, 1, 0, 'M', '0', '0', '', 'table', 'admin', '2022-01-20 20:09:10', 'admin', '2022-01-22 09:27:39', ''),
	(2002, '加油站地理信息', 2000, 1, 'geo', 'gas/geo/index', NULL, 1, 0, 'C', '0', '0', 'gas:geo:list', '#', 'admin', '2022-01-21 21:37:51', 'admin', '2022-01-21 21:45:14', '加油站地理信息菜单'),
	(2003, '加油站地理信息查询', 2002, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'gas:geo:query', '#', 'admin', '2022-01-21 21:37:51', '', NULL, ''),
	(2004, '加油站地理信息新增', 2002, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'gas:geo:add', '#', 'admin', '2022-01-21 21:37:51', '', NULL, ''),
	(2005, '加油站地理信息修改', 2002, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'gas:geo:edit', '#', 'admin', '2022-01-21 21:37:51', '', NULL, ''),
	(2006, '加油站地理信息删除', 2002, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'gas:geo:remove', '#', 'admin', '2022-01-21 21:37:51', '', NULL, ''),
	(2007, '加油站地理信息导出', 2002, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'gas:geo:export', '#', 'admin', '2022-01-21 21:37:51', '', NULL, '');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_notice 结构
CREATE TABLE IF NOT EXISTS `sys_notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告表';

-- 正在导出表  ruoyi.sys_notice 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` (`notice_id`, `notice_title`, `notice_type`, `notice_content`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', _binary 0xe696b0e78988e69cace58685e5aeb9, '0', 'admin', '2022-01-18 11:23:33', '', NULL, '管理员'),
	(2, '维护通知：2018-07-01 若依系统凌晨维护', '1', _binary 0xe7bbb4e68aa4e58685e5aeb9, '0', 'admin', '2022-01-18 11:23:33', '', NULL, '管理员');
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_oper_log 结构
CREATE TABLE IF NOT EXISTS `sys_oper_log` (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志记录';

-- 正在导出表  ruoyi.sys_oper_log 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `sys_oper_log` DISABLE KEYS */;
INSERT INTO `sys_oper_log` (`oper_id`, `title`, `business_type`, `method`, `request_method`, `operator_type`, `oper_name`, `dept_name`, `oper_url`, `oper_ip`, `oper_location`, `oper_param`, `json_result`, `status`, `error_msg`, `oper_time`) VALUES
	(100, '字典类型', 5, 'com.ruoyi.web.controller.system.SysDictTypeController.export()', 'POST', 1, 'admin', NULL, '/system/dict/type/export', '127.0.0.1', '内网IP', '{"params":{}}', NULL, 0, NULL, '2022-01-20 20:05:04'),
	(101, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'DELETE', 1, 'admin', NULL, '/system/menu/4', '127.0.0.1', '内网IP', '{menuId=4}', '{"msg":"菜单已分配,不允许删除","code":500}', 0, NULL, '2022-01-20 20:06:48'),
	(102, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"0","icon":"table","orderNum":"0","menuName":"加油站地理信息查询","params":{},"parentId":0,"isCache":"0","path":"gas/geo-info","createBy":"admin","children":[],"isFrame":"1","menuType":"M","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-20 20:09:10'),
	(103, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"0","icon":"table","orderNum":"0","menuName":"加油站预测自助服务","params":{},"parentId":0,"isCache":"0","path":"gas","children":[],"createTime":1642680550000,"updateBy":"admin","isFrame":"1","menuId":2000,"menuType":"M","perms":"","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-20 20:09:54'),
	(104, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"0","icon":"button","orderNum":"0","menuName":"地理信息查询","params":{},"parentId":2000,"isCache":"0","path":"gas/geo","createBy":"admin","children":[],"isFrame":"1","menuType":"C","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-20 20:10:51'),
	(105, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"0","icon":"button","orderNum":"0","menuName":"地理信息查询","params":{},"parentId":2000,"isCache":"0","path":"geo","children":[],"createTime":1642680650000,"updateBy":"admin","isFrame":"1","menuId":2001,"menuType":"C","perms":"","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-20 20:11:08'),
	(106, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"0","icon":"table","orderNum":"0","menuName":"加油站自助服务","params":{},"parentId":0,"isCache":"0","path":"gas","children":[],"createTime":1642680550000,"updateBy":"admin","isFrame":"1","menuId":2000,"menuType":"M","perms":"","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-20 20:12:09'),
	(107, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'gas_station_geo,gas_station_info', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-21 21:21:32'),
	(108, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', NULL, 0, NULL, '2022-01-21 21:21:36'),
	(109, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"0","icon":"#","orderNum":"1","menuName":"加油站地理信息","params":{},"parentId":2000,"isCache":"0","path":"geo","component":"gas/geo/index","children":[],"createTime":1642772271000,"updateBy":"admin","isFrame":"1","menuId":2002,"menuType":"C","perms":"gas:geo:list","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-21 21:45:14'),
	(110, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"0","icon":"#","orderNum":"1","menuName":"加油站信息","params":{},"parentId":2000,"isCache":"0","path":"info","component":"gas/info/index","children":[],"createTime":1642772359000,"updateBy":"admin","isFrame":"1","menuId":2008,"menuType":"C","perms":"gas:info:list","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-21 21:45:24'),
	(111, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'DELETE', 1, 'admin', NULL, '/system/menu/2001', '127.0.0.1', '内网IP', '{menuId=2001}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-21 21:45:36'),
	(112, '加油站地理信息', 5, 'com.ruoyi.gas.controller.GasStationGeoController.export()', 'POST', 1, 'admin', NULL, '/gas/geo/export', '127.0.0.1', '内网IP', '{"params":{}}', NULL, 0, NULL, '2022-01-21 21:46:22'),
	(113, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"1","query":"","icon":"guide","orderNum":"4","menuName":"若依官网","params":{},"parentId":0,"isCache":"0","path":"http://ruoyi.vip","children":[],"createTime":1642476212000,"updateBy":"admin","isFrame":"0","menuId":4,"menuType":"M","perms":"","status":"1"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-21 22:05:29'),
	(114, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'DELETE', 1, 'admin', NULL, '/system/menu/4', '127.0.0.1', '内网IP', '{menuId=4}', '{"msg":"菜单已分配,不允许删除","code":500}', 0, NULL, '2022-01-21 22:05:32'),
	(115, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"1","query":"","icon":"guide","orderNum":"4","menuName":"若依官网","params":{},"parentId":0,"isCache":"0","path":"http://ruoyi.vip","children":[],"createTime":1642476212000,"updateBy":"admin","isFrame":"0","menuId":4,"menuType":"M","perms":"","status":"1"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-21 22:05:41'),
	(116, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 't_user', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:25:21'),
	(117, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', NULL, 0, NULL, '2022-01-22 09:25:35'),
	(118, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"1","icon":"table","orderNum":"0","menuName":"加油站自助服务","params":{},"parentId":0,"isCache":"0","path":"gas","children":[],"createTime":1642680550000,"updateBy":"admin","isFrame":"1","menuId":2000,"menuType":"M","perms":"","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:27:30'),
	(119, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"0","icon":"table","orderNum":"0","menuName":"加油站自助服务","params":{},"parentId":0,"isCache":"0","path":"gas","children":[],"createTime":1642680550000,"updateBy":"admin","isFrame":"1","menuId":2000,"menuType":"M","perms":"","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:27:39'),
	(120, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"0","icon":"#","orderNum":"1","menuName":"测试用户","params":{},"parentId":0,"isCache":"0","path":"user","component":"gas/user/index","children":[],"createTime":1642814877000,"updateBy":"admin","isFrame":"1","menuId":2014,"menuType":"C","perms":"gas:user:list","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:28:41'),
	(121, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"0","icon":"#","orderNum":"1","menuName":"测试用户","params":{},"parentId":3,"isCache":"0","path":"user","component":"gas/user/index","children":[],"createTime":1642814877000,"updateBy":"admin","isFrame":"1","menuId":2014,"menuType":"C","perms":"gas:user:list","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:37:27'),
	(122, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{"visible":"0","icon":"#","orderNum":"1","menuName":"测试用户","params":{},"parentId":2000,"isCache":"0","path":"user","component":"gas/user/index","children":[],"createTime":1642814877000,"updateBy":"admin","isFrame":"1","menuId":2014,"menuType":"C","perms":"gas:user:list","status":"0"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:38:21'),
	(123, '测试用户', 1, 'com.ruoyi.gas.controller.TUserController.add()', 'POST', 1, 'admin', NULL, '/gas/user', '127.0.0.1', '内网IP', '{"params":{},"email":"123@123.com","username":"12312312"}', NULL, 1, '\r\n### Error updating database.  Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\r\n### The error may exist in file [D:\\test\\RuoYi-Vue\\gas-station\\target\\classes\\mapper\\gas\\TUserMapper.xml]\r\n### The error may involve com.ruoyi.gas.mapper.TUserMapper.insertTUser-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into t_user          ( username,             email )           values ( ?,             ? )\r\n### Cause: java.sql.SQLException: Field \'id\' doesn\'t have a default value\n; Field \'id\' doesn\'t have a default value; nested exception is java.sql.SQLException: Field \'id\' doesn\'t have a default value', '2022-01-22 09:38:55'),
	(124, '测试用户', 1, 'com.ruoyi.gas.controller.TUserController.add()', 'POST', 1, 'admin', NULL, '/gas/user', '127.0.0.1', '内网IP', '{"params":{},"email":"123@123.com","username":"12312312"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:39:30'),
	(125, '测试用户', 1, 'com.ruoyi.gas.controller.TUserController.add()', 'POST', 1, 'admin', NULL, '/gas/user', '127.0.0.1', '内网IP', '{"params":{},"email":"123@123.com","username":"12"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:39:37'),
	(126, '测试用户', 1, 'com.ruoyi.gas.controller.TUserController.add()', 'POST', 1, 'admin', NULL, '/gas/user', '127.0.0.1', '内网IP', '{"params":{},"email":"123@123.com","username":"45"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:39:44'),
	(127, '测试用户', 5, 'com.ruoyi.gas.controller.TUserController.export()', 'POST', 1, 'admin', NULL, '/gas/user/export', '127.0.0.1', '内网IP', '{"params":{}}', NULL, 0, NULL, '2022-01-22 09:40:44'),
	(128, '测试用户', 1, 'com.ruoyi.gas.controller.TUserController.add()', 'POST', 1, 'admin', NULL, '/gas/user', '127.0.0.1', '内网IP', '{"params":{},"email":"123@123.com","username":"del"}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:41:02'),
	(129, '测试用户', 3, 'com.ruoyi.gas.controller.TUserController.remove()', 'DELETE', 1, 'admin', NULL, '/gas/user/4', '127.0.0.1', '内网IP', '{ids=4}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:41:10'),
	(130, '测试用户', 3, 'com.ruoyi.gas.controller.TUserController.remove()', 'DELETE', 1, 'admin', NULL, '/gas/user/1,2,3', '127.0.0.1', '内网IP', '{ids=1,2,3}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 09:41:34'),
	(131, '加油站地理信息', 5, 'com.ruoyi.gas.controller.GasStationGeoController.export()', 'POST', 1, 'admin', NULL, '/gas/geo/export', '127.0.0.1', '内网IP', '{"params":{}}', NULL, 0, NULL, '2022-01-22 09:57:22'),
	(132, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'DELETE', 1, 'admin', NULL, '/system/menu/2014', '127.0.0.1', '内网IP', '{menuId=2014}', '{"msg":"存在子菜单,不允许删除","code":500}', 0, NULL, '2022-01-22 10:32:00'),
	(133, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'DELETE', 1, 'admin', NULL, '/system/menu/2015', '127.0.0.1', '内网IP', '{menuId=2015}', '{"msg":"操作成功","code":200}', 0, NULL, '2022-01-22 10:32:07'),
	(134, '加油站地理信息', 5, 'com.ruoyi.gas.controller.GasStationGeoController.export()', 'POST', 1, 'admin', NULL, '/gas/geo/export', '127.0.0.1', '内网IP', '{"query":"加油"}', NULL, 0, NULL, '2022-01-22 14:01:36');
/*!40000 ALTER TABLE `sys_oper_log` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_post 结构
CREATE TABLE IF NOT EXISTS `sys_post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='岗位信息表';

-- 正在导出表  ruoyi.sys_post 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, 'ceo', '董事长', 1, '0', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(2, 'se', '项目经理', 2, '0', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(3, 'hr', '人力资源', 3, '0', 'admin', '2022-01-18 11:23:32', '', NULL, ''),
	(4, 'user', '普通员工', 4, '0', 'admin', '2022-01-18 11:23:32', '', NULL, '');
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

-- 正在导出表  ruoyi.sys_role 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `menu_check_strictly`, `dept_check_strictly`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '超级管理员'),
	(2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2022-01-18 11:23:32', '', NULL, '普通角色');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_role_dept 结构
CREATE TABLE IF NOT EXISTS `sys_role_dept` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和部门关联表';

-- 正在导出表  ruoyi.sys_role_dept 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` (`role_id`, `dept_id`) VALUES
	(2, 100),
	(2, 101),
	(2, 105);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';

-- 正在导出表  ruoyi.sys_role_menu 的数据：~84 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
	(2, 1),
	(2, 2),
	(2, 3),
	(2, 4),
	(2, 100),
	(2, 101),
	(2, 102),
	(2, 103),
	(2, 104),
	(2, 105),
	(2, 106),
	(2, 107),
	(2, 108),
	(2, 109),
	(2, 110),
	(2, 111),
	(2, 112),
	(2, 113),
	(2, 114),
	(2, 115),
	(2, 116),
	(2, 500),
	(2, 501),
	(2, 1000),
	(2, 1001),
	(2, 1002),
	(2, 1003),
	(2, 1004),
	(2, 1005),
	(2, 1006),
	(2, 1007),
	(2, 1008),
	(2, 1009),
	(2, 1010),
	(2, 1011),
	(2, 1012),
	(2, 1013),
	(2, 1014),
	(2, 1015),
	(2, 1016),
	(2, 1017),
	(2, 1018),
	(2, 1019),
	(2, 1020),
	(2, 1021),
	(2, 1022),
	(2, 1023),
	(2, 1024),
	(2, 1025),
	(2, 1026),
	(2, 1027),
	(2, 1028),
	(2, 1029),
	(2, 1030),
	(2, 1031),
	(2, 1032),
	(2, 1033),
	(2, 1034),
	(2, 1035),
	(2, 1036),
	(2, 1037),
	(2, 1038),
	(2, 1039),
	(2, 1040),
	(2, 1041),
	(2, 1042),
	(2, 1043),
	(2, 1044),
	(2, 1045),
	(2, 1046),
	(2, 1047),
	(2, 1048),
	(2, 1049),
	(2, 1050),
	(2, 1051),
	(2, 1052),
	(2, 1053),
	(2, 1054),
	(2, 1055),
	(2, 1056),
	(2, 1057),
	(2, 1058),
	(2, 1059),
	(2, 1060);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- 正在导出表  ruoyi.sys_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `user_type`, `email`, `phonenumber`, `sex`, `avatar`, `password`, `status`, `del_flag`, `login_ip`, `login_date`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2022-01-22 15:41:38', 'admin', '2022-01-18 11:23:32', '', '2022-01-22 15:41:38', '管理员'),
	(2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2022-01-18 11:23:32', 'admin', '2022-01-18 11:23:32', '', NULL, '测试员');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_user_post 结构
CREATE TABLE IF NOT EXISTS `sys_user_post` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与岗位关联表';

-- 正在导出表  ruoyi.sys_user_post 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES
	(1, 1),
	(2, 2);
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;

-- 导出  表 ruoyi.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';

-- 正在导出表  ruoyi.sys_user_role 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

-- 导出  表 ruoyi.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(128) DEFAULT NULL COMMENT '用户名',
  `email` varchar(512) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='测试用户表';

-- 正在导出表  ruoyi.t_user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
