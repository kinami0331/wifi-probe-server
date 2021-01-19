DROP DATABASE IF EXISTS `wifi_probe`;
CREATE DATABASE `wifi_probe` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `wifi_probe`;

DROP TABLE IF EXISTS `full_record`;
CREATE TABLE `full_record`
(
    `id`           INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增长，活动id',
    `create_time`  DATETIME(6)                 NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    `updated_time` DATETIME(6)                 NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
    `mid`          VARCHAR(20)                          DEFAULT NULL COMMENT '嗅探设备id',
    `mmac`         VARCHAR(20)                          DEFAULT NULL COMMENT '设备mac',
    `rate`         VARCHAR(10)                          DEFAULT NULL COMMENT '发送频率',
    `time`         DATETIME                             DEFAULT NULL COMMENT '时间戳',
    `lat`          DOUBLE                               DEFAULT NULL COMMENT '纬度',
    `lon`          DOUBLE                               DEFAULT NULL COMMENT '经度',
    PRIMARY KEY pk_id (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `mac_info`;
CREATE TABLE `mac_info`
(
    `id`           INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增长，活动id',
    `create_time`  DATETIME(6)                 NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    `updated_time` DATETIME(6)                 NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
    `mac`          VARCHAR(20)                 NOT NULL COMMENT 'mac地址',
    `alias`        VARCHAR(100)                NOT NULL COMMENT '设备别名',
    PRIMARY KEY pk_id (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `single_record`;
CREATE TABLE `single_record`
(
    `id`           INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增长，活动id',
    `create_time`  DATETIME(6)                 NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    `updated_time` DATETIME(6)                 NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
    `mac`          VARCHAR(20)                          DEFAULT NULL COMMENT 'mac地址',
    `router`       VARCHAR(100)                         DEFAULT NULL COMMENT '""',
    `rssi`         INT                                  DEFAULT NULL COMMENT '',
    `rssi1`        INT                                  DEFAULT NULL COMMENT '',
    `rssi2`        INT                                  DEFAULT NULL COMMENT '',
    `rssi3`        INT                                  DEFAULT NULL COMMENT '',
    `rssi4`        INT                                  DEFAULT NULL COMMENT '',
    `tar_ssid`     VARCHAR(100)                         DEFAULT NULL COMMENT '',
    `tar_mac`      VARCHAR(20)                          DEFAULT NULL COMMENT '',
    `is_connected` BOOLEAN                              DEFAULT NULL COMMENT '',
    `is_sleeping`  BOOLEAN                              DEFAULT NULL COMMENT '',
    `essid0`       VARCHAR(100)                         DEFAULT NULL COMMENT '',
    `essid1`       VARCHAR(100)                         DEFAULT NULL COMMENT '',
    `essid2`       VARCHAR(100)                         DEFAULT NULL COMMENT '',
    `essid3`       VARCHAR(100)                         DEFAULT NULL COMMENT '',
    `essid4`       VARCHAR(100)                         DEFAULT NULL COMMENT '',
    `essid5`       VARCHAR(100)                         DEFAULT NULL COMMENT '',
    `essid6`       VARCHAR(100)                         DEFAULT NULL COMMENT '',
    `range`        DOUBLE                               DEFAULT NULL COMMENT '',
    INDEX idx_mac (mac),
    PRIMARY KEY pk_id (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `r_full_single`;
CREATE TABLE `r_full_single`
(
    `id`        INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增长',
    `full_id`   INT UNSIGNED                NOT NULL COMMENT '完整记录id',
    `single_id` INT UNSIGNED                NOT NULL COMMENT '用户id',
    INDEX idx_full_id (full_id),
    INDEX idx_single_id (single_id),
    PRIMARY KEY pk_id (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

