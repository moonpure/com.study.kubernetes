create table if not exists user_account
(
    account_code varchar(50)                         not null comment '登录用户名，唯一'  primary key,
    user_id      bigint                               not null comment 'user_base.id',
    deleted      int    default 0                 not null comment '1删除',
    create_id    bigint      default 0                 not null comment '创建id',
    create_time  timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_id    bigint     default 0                 not null comment '创建id',
    update_time  timestamp default CURRENT_TIMESTAMP not null comment '修改时间'
    )
    comment '登录用户';

create table if not exists user_base
(
    id          int                                  not null primary key,
    mobile      varchar(50)  default ''                not null comment '手机号，只用于显示',
    name        varchar(50)  default ''                not null comment '用户显示名',
    password    varchar(100) default ''                not null comment '用户密码',
    nick_name   varchar(50)  default ''                not null comment '昵称',
    headimgurl  varchar(500) default ''                not null comment '用户头像',
    create_id   int       default 0                 not null comment '创建id',
    update_id   int       default 0                 not null comment '修改id',
    create_time timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp    default CURRENT_TIMESTAMP not null comment '修改时间'
    )
    comment '用户';

create table if not exists user_mail
(
    e_mail      varchar(50)                         not null comment '邮箱，唯一'
    primary key,
    user_id     bigint                              not null comment 'user_base.id',
    deleted     int    default 0                 not null comment '1删除',
    create_id   bigint    default 0                 not null comment '创建id',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_id   bigint    default 0                 not null comment '创建id',
    update_time timestamp default CURRENT_TIMESTAMP not null comment '修改时间'
    )
    comment '邮箱用户';

create table if not exists user_mobile
(
    mobile      varchar(50)                         not null comment '手号，唯一'
    primary key,
    user_id     bigint                              not null comment 'user_base.id',
    deleted     int    default 0                 not null comment '1删除',
    create_id   bigint    default 0                 not null comment '创建id',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_id   bigint    default 0                 not null comment '创建id',
    update_time timestamp default CURRENT_TIMESTAMP not null comment '修改时间'
    )
    comment '手机用户';

create table if not exists user_role
(
    id          bigint                              not null comment '唯一'
    primary key,
    user_id     bigint                              not null comment 'user_base.id',
    user_type   int    default 0                 not null comment '1求职者，2企业hr,3猎头...',
    status      int    default 1                 not null comment '1可用，2不可用',
    deleted     int    default 0                 not null comment '1删除',
    create_id   bigint    default 0                 not null comment '创建id',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_id   bigint    default 0                 not null comment '创建id',
    update_time timestamp default CURRENT_TIMESTAMP not null comment '修改时间'
) comment '用户类型';

create table if not exists user_wallet
(
    id                bigint   not null
    primary key,
    remaining_balance bigint   not null comment '余额',
    user_id           bigint   not null comment '用户id',
    identity          int   not null comment '身份 1.个人 2.公司',
    create_id         bigint   not null comment '创建id',
    update_id         bigint   not null comment '修改id',
    creat_time        datetime not null comment '创建时间',
    update_time       datetime not null comment '修改时间'
)   comment '用户钱包';

create table if not exists user_wechat
(
    open_id     varchar(50)                           not null comment '微信openid'
    primary key,
    union_id    varchar(50) default ''                not null comment '微信unionid',
    user_id     bigint      default 0                 not null comment 'user_base.id',
    nick_name   varchar(50) default ''                not null comment '昵称',
    province    varchar(50) default ''                not null comment '省',
    city        varchar(50) default ''                not null comment '城市',
    sex         int      default 0                 not null comment '1男性,2女性,0未知',
    headimgurl  varchar(500)                          null comment '微信头像',
    bind_mobile bigint      default 0                 not null comment '是否帮定手机，1为绑定。',
    create_id   bigint      default 0                 not null comment '创建id',
    create_time timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_id   bigint      default 0                 not null comment '创建id',
    update_time timestamp   default CURRENT_TIMESTAMP not null comment '修改时间'
    )
    comment '微信用户';