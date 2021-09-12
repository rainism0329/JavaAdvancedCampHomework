CREATE TABLE IF NOT EXISTS `user`
(
    `id`           bigint(20) primary key comment '用户 ID',
    `account`      varchar(20) not null unique comment '账号',
    `mobile`       varchar(20) unique comment '手机号',
    `nick_name`    varchar(20) comment '昵称',
    `gender`       tinyint(1) comment '性别, 1: 男, 2: 女',
    `brief`        varchar(150) comment '个人简介',
    `gmt_create`   timestamp   not null default current_timestamp comment '创建时间',
    `gmt_modified` timestamp   not null default current_timestamp on update current_timestamp comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '用户';

CREATE TABLE IF NOT EXISTS `user_shipping_address`
(
    `id`               bigint(20) primary key comment '收获地址 ID',
    `user_id`          bigint(20) not null comment '用户 ID',
    `province_code`    varchar(10) comment '省编码',
    `city_code`        varchar(10) comment '市编码',
    `district_code`    varchar(10) comment '区编码',
    `post_code`        varchar(10) comment '邮编',
    `address_detail`   varchar(50) comment '地址详情',
    `consignee_name`   varchar(10) comment '收货人姓名',
    `consignee_mobile` varchar(15) comment '收货人手机号',
    `default_flag`     tinyint(1) comment '是否默认, 0: 否, 1: 是',
    `gmt_create`       timestamp  not null default current_timestamp comment '创建时间',
    `gmt_modified`     timestamp  not null default current_timestamp on update current_timestamp comment '更新时间',
    index `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '用户配送地址';


CREATE TABLE IF NOT EXISTS `product`
(
    `id`            bigint(20) primary key comment '商品 ID',
    `merchant_id`   bigint(20)  not null comment '商家 ID',
    `code`          varchar(10) not null comment '商品编码',
    `name`          varchar(50) comment '商品名称',
    `category_code` varchar(10) comment '商品类目编码',
    `gmt_create`    timestamp   not null default current_timestamp comment '创建时间',
    `gmt_modified`  timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    index `idx_merchant_id` (`merchant_id`),
    index `idx_code` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '商品信息';

CREATE TABLE IF NOT EXISTS `sku`
(
    `id`            bigint(20) primary key comment 'SKU ID',
    `product_code`  varchar(10) not null comment '商品编码',
    `code`          varchar(10) not null comment 'SKU 编码',
    `name`          varchar(50) comment '商品名称',
    `category_code` varchar(10) comment '商品类目编码',
    `stock`         int(8) comment '库存',
    `price`         int(10) comment '价格',
    `gmt_create`    timestamp   not null default current_timestamp comment '创建时间',
    `gmt_modified`  timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',
    index `idx_code` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '商品 SKU 信息';

CREATE TABLE IF NOT EXISTS `order`
(
    `id`                  bigint(20) primary key comment '订单 ID',
    `code`                varchar(20) not null comment '订单编码',
    `merchant_id`         bigint(20)  not null comment '卖家 ID',
    `user_id`             bigint(20)  not null comment '买家 ID',
    `amount`              int(10)     not null comment '订单金额',
    `cut_amount`          int(10)     not null comment '订单优惠金额',
    `shipping_address_id` bigint(20)  not null comment '订单配送地址',
    `status`              int(2)      not null comment '订单状态',
    `gmt_create`          timestamp   not null default current_timestamp comment '创建时间',
    `gmt_modified`        timestamp   not null default current_timestamp on update current_timestamp comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '订单';

CREATE TABLE IF NOT EXISTS `order_item`
(
    `id`           bigint(20) primary key comment '订单项 ID',
    `order_id`     bigint(20)  not null comment '订单 ID',
    `sku_code`     varchar(10) not null comment 'SKU 编码',
    `quantity`     int(4)      not null comment 'SKU 数量',
    `price`        int(10)     not null comment '商品价格',
    `item_amount`  int(10)     not null comment '订单项金额',
    `cut_amount`   int(10)     not null comment '订单项优惠金额',
    `gmt_create`   timestamp   not null default current_timestamp comment '创建时间',
    `gmt_modified` timestamp   not null default current_timestamp on update current_timestamp comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '订单项';

CREATE TABLE IF NOT EXISTS `order_item_sku_snapshot`
(
    `id`              bigint(20) primary key comment '订单项商品快照信息 ID',
    `order_item_id`   bigint(20)  not null comment '订单项 ID',
    `sku_code`        varchar(10) not null comment 'SKU 编码',
    `snapshot_detail` text comment '快照信息',
    `gmt_create`      timestamp   not null default current_timestamp comment '创建时间',
    `gmt_modified`    timestamp   not null default current_timestamp on update current_timestamp comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '订单项商品快照';