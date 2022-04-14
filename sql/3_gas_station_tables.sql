-- 加油站地理信息表
CREATE TABLE IF NOT EXISTS `gas_station_geo`
(
    `id`                    bigint NOT NULL AUTO_INCREMENT COMMENT '地理信息主键',
    `system_station_id`     varchar(64)  DEFAULT NULL COMMENT '系统内加油站ID',
    `out_system_station_id` varchar(64)  DEFAULT NULL COMMENT '系统外加油站ID',
    `distance`              double       DEFAULT NULL COMMENT '两加油站之间的距离',
    `traffic_lights`        int          DEFAULT NULL COMMENT '两加油站之间路线红绿灯数',
    `traffic_factor`        double       DEFAULT NULL COMMENT '两加油站红绿灯数的影响因子',
    `route_shape`           varchar(512) DEFAULT NULL COMMENT '路线形状',
    `route_shape_factor`    double       DEFAULT NULL COMMENT '路线曲折度影响系数',
    `route_factor`          double       DEFAULT NULL COMMENT '路线影响系数',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='加油站地理信息表';

-- 加油站信息表
CREATE TABLE IF NOT EXISTS `gas_station_info`
(
    `id`       varchar(45)  NOT NULL COMMENT '唯一ID',
    `name`     varchar(512) NOT NULL COMMENT '加油站名称',
    `location` varchar(64)  NOT NULL COMMENT '加油站位置',
    `province` varchar(4)   NOT NULL COMMENT '加油站所在省',
    `city`     varchar(32)  NOT NULL COMMENT '加油站所在市',
    `isSystem` tinyint      NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='加油站信息表';

-- 加油站油对手油价表
CREATE TABLE IF NOT EXISTS `gas_station_oil_price`
(
    `gas_station_id`     varchar(45) NOT NULL COMMENT '系统内加油站ID',
    `out_gas_station_id` varchar(45) NOT NULL COMMENT '系统外加油站ID',
    `period_id`          int         NOT NULL COMMENT '周期ID',
    `price_92`           double unsigned DEFAULT NULL COMMENT '92号汽油价格',
    `price_95`           double unsigned DEFAULT NULL COMMENT '95号汽油价格',
    `price_98`           double unsigned DEFAULT NULL COMMENT '98号汽油价格',
    `price_00`           double unsigned DEFAULT NULL COMMENT '柴油价格',
    PRIMARY KEY (`gas_station_id`, `out_gas_station_id`, `period_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '加油站油对手油价表';

-- 加油站周期表
CREATE TABLE IF NOT EXISTS `gas_station_price_period`
(
    `id`         int      NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
    `start_time` datetime NOT NULL COMMENT '开始时间',
    `end_time`   datetime DEFAULT NULL COMMENT '结束时间',
    `price_92`   int      NOT NULL COMMENT '92号汽油价格',
    `price_95`   int      NOT NULL COMMENT '95号汽油价格',
    `price_98`   int      NOT NULL COMMENT '98号汽油价格',
    `price_00`   int      NOT NULL COMMENT '柴油价格',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '加油站周期表';

-- 加油站经营数据表
CREATE TABLE IF NOT EXISTS `gas_station_sale_data`
(
    `id`             int unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一id',
    `gas_station_id` varchar(45)  NOT NULL COMMENT '系统内加油站ID',
    `price`          double       NOT NULL COMMENT '价格',
    `number`         double       NOT NULL COMMENT '数量',
    `oil_type`       int          NOT NULL COMMENT '石油类型',
    `date`           datetime     NOT NULL COMMENT '日期',
    `effective`      tinyint      NOT NULL DEFAULT '1' COMMENT '是否被删除',
    `batch`          int unsigned NOT NULL COMMENT '批次号',
    PRIMARY KEY (`id`, `gas_station_id`, `date`),
    UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '加油站经营数据表';

-- 用户加油站表
CREATE TABLE IF NOT EXISTS `gas_station_user_owned`
(
    `id`           BIGINT(19)   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`      BIGINT(19)   NOT NULL COMMENT '用户ID',
    `station_id`   VARCHAR(45)  NOT NULL COMMENT '加油站ID（系统内）' COLLATE 'utf8mb4_general_ci',
    `station_name` VARCHAR(512) NOT NULL COMMENT '加油站名称' COLLATE 'utf8mb4_general_ci',
    `status`       TINYINT(3)   NOT NULL DEFAULT '0' COMMENT '加油站状态（0:创建,1:正常,2:禁用,3:审核中, 4:已删除）',
    `update_time`  DATETIME     NULL     DEFAULT NULL COMMENT '经营数据最新更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_user_station` (`user_id`, `station_id`) USING BTREE
)
    COMMENT ='用户加油站表'
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB;
