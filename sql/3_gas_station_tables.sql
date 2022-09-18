-- 加油站地理信息表
CREATE TABLE IF NOT EXISTS `gas_station_geo`
(
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '地理信息主键',
    `system_station_id` varchar(64) DEFAULT NULL COMMENT '系统内加油站ID',
    `out_system_station_id` varchar(64) DEFAULT NULL COMMENT '系统外加油站ID',
    `distance` double DEFAULT NULL COMMENT '两加油站之间的距离',
    `traffic_lights` int DEFAULT NULL COMMENT '两加油站之间路线红绿灯数',
    `traffic_factor` double DEFAULT NULL COMMENT '两加油站红绿灯数的影响因子',
    `route_shape` varchar(512) DEFAULT NULL COMMENT '路线形状',
    `route_shape_factor` double DEFAULT NULL COMMENT '路线曲折度影响系数',
    `route_factor` double DEFAULT NULL COMMENT '路线影响系数',
    PRIMARY KEY(`id`)
)   ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT ='加油站地理信息表';

-- 加油站信息表
CREATE TABLE IF NOT EXISTS `gas_station_info`(
    `id` varchar(45) NOT NULL COMMENT '唯一ID',
    `name` varchar(512) NOT NULL COMMENT '加油站名称',
    `location` varchar(64) NOT NULL COMMENT '加油站位置',
    `province` varchar(15) NOT NULL COMMENT '加油站所在省',
    `city` varchar(32) NOT NULL COMMENT '加油站所在市',
    `address` varchar(1024) NOT NULL COMMENT '加油站地址',
    `isSystem` tinyint NOT NULL,
    PRIMARY KEY(`id`)
)   ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT ='加油站信息表';

-- 加油站油对手油价表
CREATE TABLE IF NOT EXISTS `gas_station_oil_price`(
    `gas_station_id` varchar(45) NOT NULL COMMENT '系统内加油站ID',
    `out_gas_station_id` varchar(45) NOT NULL COMMENT '系统外加油站ID',
    `period_id` int NOT NULL COMMENT '周期ID',
    `price_92` double unsigned DEFAULT NULL COMMENT '92号汽油价格',
    `price_95` double unsigned DEFAULT NULL COMMENT '95号汽油价格',
    `price_98` double unsigned DEFAULT NULL COMMENT '98号汽油价格',
    `price_00` double unsigned DEFAULT NULL COMMENT '柴油价格',
    PRIMARY KEY(`gas_station_id`,`out_gas_station_id`,`period_id`)
)   ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT '加油站油对手油价表';

-- 加油站周期表
CREATE TABLE IF NOT EXISTS `gas_station_price_period`
(
    `id` int NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
    `start_time` datetime NOT NULL COMMENT '开始时间',
    `rise` boolean NOT NULL COMMENT '是否上调',
    `create_by` varchar(64) default '' comment '创建者',
    `create_time` datetime comment '创建时间',
    `update_by` varchar(64) default '' comment '更新者',
    `update_time` datetime comment '更新时间',
    `remark` varchar(500) default null comment '备注',
    PRIMARY KEY(`id`),
    UNIQUE(`start_time` DESC)
)   ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT '加油站周期表';

-- 加油站经营数据表
CREATE TABLE IF NOT EXISTS `gas_station_sale_data`
(
    `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一id',
    `user_id` int unsigned NOT NULL COMMENT '用户ID',
    `gas_station_id` varchar(45) NOT NULL COMMENT '系统内加油站ID',
    `price` double NOT NULL COMMENT '价格',
    `l_number` double NOT NULL COMMENT '数量/升',
    `kg_number` double NOT NULL COMMENT '数量/千克',
    `oil_type` int NOT NULL COMMENT '石油类型',
    `date` datetime NOT NULL COMMENT '日期',
    `effective` tinyint NOT NULL DEFAULT '1' COMMENT '是否被删除',
    `batch` int unsigned NOT NULL COMMENT '批次号',
    PRIMARY KEY(`id`,`user_id`,`date`),
    UNIQUE KEY `id_UNIQUE`(`id`)
)   ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT '加油站经营数据表';

-- 用户加油站表
CREATE TABLE IF NOT EXISTS `gas_station_user_owned`
(
    `id` BIGINT(19) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT(19) NOT NULL COMMENT '用户ID',
    `station_id` VARCHAR(45) NOT NULL COMMENT '加油站ID（系统内）' COLLATE 'utf8mb4_general_ci',
    `station_name` VARCHAR(512) NOT NULL COMMENT '加油站名称' COLLATE 'utf8mb4_general_ci',
    `status` TINYINT(3) NOT NULL DEFAULT '0' COMMENT '加油站状态（0:创建,1:正常,2:禁用,3:审核中, 4:已删除）',
    `update_time` DATETIME NULL DEFAULT NULL COMMENT '经营数据最新更新时间',
    PRIMARY KEY(`id`) USING BTREE,
    UNIQUE INDEX `idx_user_station`(`user_id`,`station_id`) USING BTREE
)   ENGINE = InnoDB
    COLLATE = 'utf8mb4_general_ci'
    COMMENT ='用户加油站表';

-- 竞争对手价格表
CREATE TABLE IF NOT EXISTS `gas_station_opponent_price`
(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
    `user_period_id` BIGINT NOT NULL COMMENT '用户周期id',
    `user_id` BIGINT NOT NULL COMMENT'用户id',
    `gas_station_id` VARCHAR(45) NOT NULL COMMENT '系统内加油站id',
    `out_gas_station_id` VARCHAR(45) NOT NULL COMMENT '系统外加油站id',
    `price_92` DECIMAL(7,2) NULL DEFAULT 0 COMMENT '92号汽油价格',
    `price_95` DECIMAL(7,2) NULL DEFAULT 0 COMMENT '95号汽油价格',
    `price_98` DECIMAL(7,2) NULL DEFAULT 0 COMMENT '98号汽油价格',
    `price_00` DECIMAL(7,2) NULL DEFAULT 0 COMMENT '柴油价格',
    PRIMARY KEY(`id`),
    UNIQUE INDEX `id_UNIQUE`(`id` ASC)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COMMENT = '竞争对手价格表';

-- 对手加油站信息
CREATE TABLE IF NOT EXISTS `gas_station_opponent_message`
(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
    `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户id',
    `gas_station_id` VARCHAR(45) NOT NULL COMMENT '系统内加油站id',
    `out_gas_station_id` VARCHAR(45) NOT NULL COMMENT '系统外加油站id',
    `out_gas_station_name` VARCHAR(45) NOT NULL COMMENT '系统外加油站名称',
    `status` INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态',
    PRIMARY KEY(`id`),
    UNIQUE INDEX `id_UNIQUE`(`id` ASC)
)   ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COMMENT = '竞争对手加油站信息表';

CREATE TABLE IF NOT EXISTS `gas_station_user_period`
(
    `id`               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据唯一标识',
    `user_id`          BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    `gas_station_id`   VARCHAR(45) NOT NULL COMMENT '系统内加油站ID',
    `time_stamp`       DATETIME    NOT NULL COMMENT '时间戳',
    `is_system_period` boolean     NOT NULL DEFAULT FALSE COMMENT '是否为系统的周期',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC)
) ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COMMENT = '用户自己定义的周期表';

CREATE TABLE IF NOT EXISTS `gas_station_price_calculation_log`
(
    `id`                    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据唯一标识',
    `user_id`               BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    `gas_station_id`        VARCHAR(45)   NOT NULL COMMENT '系统内加油站ID',
    `time_stamp`            DATETIME      NOT NULL COMMENT '时间戳',
    `calculation_parameter` DOUBLE NOT NULL COMMENT '计算时的参数值',
    `oil_type`              INT           NOT NULL COMMENT '石油类型',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COMMENT = '计算价格时的参数';