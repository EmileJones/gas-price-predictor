--
-- Table structure for table `gas_station_info`
--

DROP TABLE IF EXISTS `gas_station_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gas_station_info` (
  `id` varchar(45) NOT NULL COMMENT '唯一ID',
  `name` varchar(512) NOT NULL COMMENT '加油站名称',
  `location` varchar(64) NOT NULL COMMENT '加油站位置',
  `province` varchar(4) NOT NULL COMMENT '加油站所在省',
  `city` varchar(32) NOT NULL COMMENT '加油站所在市',
  `isSystem` tinyint NOT NULL COMMENT '是否是系统内',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='加油站信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

-- 正在导出表  ruoyi.gas_station_info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `gas_station_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `gas_station_info` ENABLE KEYS */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='加油站地理信息表';

-- 正在导出表  ruoyi.gas_station_geo 的数据：~0 rows (大约)
    /*!40000 ALTER TABLE `gas_station_geo` DISABLE KEYS */;
/*!40000 ALTER TABLE `gas_station_geo` ENABLE KEYS */;
-- 正在导出表  ruoyi.sys_menu 的数据：~83 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
	(2000, '加油站自助服务', 0, 0, 'gas', NULL, NULL, 1, 0, 'M', '0', '0', '', 'table', 'admin', '2022-01-20 20:09:10', 'admin', '2022-01-22 09:27:39', ''),
	(2002, '加油站地理信息', 2000, 1, 'geo', 'gas/geo/index', NULL, 1, 0, 'C', '0', '0', 'gas:geo:list', '#', 'admin', '2022-01-21 21:37:51', 'admin', '2022-01-21 21:45:14', '加油站地理信息菜单'),
	(2003, '加油站地理信息查询', 2002, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'gas:geo:query', '#', 'admin', '2022-01-21 21:37:51', '', NULL, ''),
	(2004, '加油站地理信息新增', 2002, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'gas:geo:add', '#', 'admin', '2022-01-21 21:37:51', '', NULL, ''),
	(2005, '加油站地理信息修改', 2002, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'gas:geo:edit', '#', 'admin', '2022-01-21 21:37:51', '', NULL, ''),
	(2006, '加油站地理信息删除', 2002, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'gas:geo:remove', '#', 'admin', '2022-01-21 21:37:51', '', NULL, ''),
	(2007, '加油站地理信息导出', 2002, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'gas:geo:export', '#', 'admin', '2022-01-21 21:37:51', '', NULL, '');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;