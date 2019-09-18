DROP TABLE IF EXISTS `sys_email`;
CREATE TABLE `sys_email` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(100) DEFAULT NULL,
  `portno` varchar(20) DEFAULT NULL COMMENT '端口号',
  `login_account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `server_addr` varchar(255) DEFAULT NULL,
  `is_valid` varchar(20) DEFAULT NULL,
  `if_del` varchar(2) DEFAULT NULL COMMENT '是否允许删除（后台录入）',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统邮件配置';


DROP TABLE IF EXISTS `sys_email_task`;
CREATE TABLE `sys_email_task` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `insert_date` datetime DEFAULT NULL,
  `send_flag` varchar(20) DEFAULT NULL COMMENT '发送标准 1为成功发送 0为未发送  2为发送失败',
  `last_send_time` datetime DEFAULT NULL COMMENT '最后一次执行发送任务的时间',
  `send_to` varchar(100) DEFAULT NULL COMMENT '接收者',
  `subject` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '正文（不包含模版，模版会动态去读取）',
  `templet_en_name` varchar(100) DEFAULT NULL COMMENT '模版en名称，用于标记模版',
  `send_times` int(10) DEFAULT '0' COMMENT '失败重发次数',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8 COMMENT='邮件发送任务表\n';

DROP TABLE IF EXISTS `sys_templet`;
CREATE TABLE `sys_templet` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UUID` varchar(255) DEFAULT NULL,
  `en_name` varchar(100) DEFAULT NULL COMMENT '用于标记模版用途，不展示，不允许前台修改',
  `templet_name` varchar(255) DEFAULT NULL,
  `templet_content` text,
  `if_modify` varchar(2) DEFAULT NULL COMMENT '是否允许修改',
  `if_del` varchar(2) DEFAULT '1' COMMENT '是否允许删除（后台录入）',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='邮件-模版表';

DROP TABLE IF EXISTS `sys_templet_emails`;
CREATE TABLE `sys_templet_emails` (
  `templet_uuid` varchar(100) DEFAULT NULL,
  `email_uuid` varchar(100) DEFAULT NULL,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模版的发送邮箱';

-- 系统邮件初始化--
INSERT INTO `sys_email` (`Id`, `UUID`, `portno`, `login_account`, `password`, `server_addr`, `is_valid`, `if_del`, `gmt_create`, `gmt_modified`) VALUES ('1', '2741b2aa377e40aaaa405d675577572d', '465', 'no_replay@zkxltech.com', '9qC9a7C229', 'smtp.ym.163.com', '1', NULL, '2018-02-25 16:54:06', NULL);
-- 重置密码模板初始化--
INSERT INTO `sys_templet` (`Id`, `UUID`, `en_name`, `templet_name`, `templet_content`, `if_modify`, `if_del`, `gmt_create`, `gmt_modified`) VALUES ('5', 'aac4a00a98474ac7b2c96d638ac05900', 'test', '邮箱发送测试模版', '<p>邮箱能否正常发送邮件测试!</p>', NULL, '1', '2018-10-19 10:14:46', NULL);
-- 系统邮件和重置密码模板对应关系初始化--
INSERT INTO `sys_templet_emails` (`templet_uuid`, `email_uuid`, `gmt_create`, `gmt_modified`) VALUES ('aac4a00a98474ac7b2c96d638ac05900', '2741b2aa377e40aaaa405d675577572d', '2018-10-19 10:14:46', NULL);INSER