/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : yan

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 16/05/2022 22:06:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job`  (
  `job_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES (1, 'testTask', 'renren', '0 0/30 * * * ?', 0, '参数测试', '2021-08-19 23:54:51');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log`  (
  `log_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(0) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(0) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int(0) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha`  (
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'uuid',
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '验证码',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('3227573d-74da-4066-8ac2-8913c9751e64', 'nne4d', '2022-02-24 13:35:00');
INSERT INTO `sys_captcha` VALUES ('d333e023-92e9-448f-8666-0544c2df2e36', '5p2x6', '2022-02-15 22:12:56');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'value',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `param_key`(`param_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统配置信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', 0, '云存储配置信息');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(0) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '删除菜单', 'io.renren.modules.sys.controller.SysMenuController.delete()', '[6]', 0, '0:0:0:0:0:0:0:1', '2021-08-26 21:56:23');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 1);
INSERT INTO `sys_menu` VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1);
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2);
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3);
INSERT INTO `sys_menu` VALUES (5, 1, 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', NULL, 1, 'sql', 4);
INSERT INTO `sys_menu` VALUES (6, 1, '定时任务', 'job/schedule', NULL, 1, 'job', 5);
INSERT INTO `sys_menu` VALUES (7, 6, '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (8, 6, '新增', NULL, 'sys:schedule:save', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (9, 6, '修改', NULL, 'sys:schedule:update', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (10, 6, '删除', NULL, 'sys:schedule:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (11, 6, '暂停', NULL, 'sys:schedule:pause', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (12, 6, '恢复', NULL, 'sys:schedule:resume', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (13, 6, '立即执行', NULL, 'sys:schedule:run', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (14, 6, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (27, 1, '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7);
INSERT INTO `sys_menu` VALUES (30, 1, '文件上传', 'oss/oss', 'sys:oss:all', 1, 'oss', 6);
INSERT INTO `sys_menu` VALUES (31, 0, '研战到底', NULL, NULL, 0, 'system', 0);
INSERT INTO `sys_menu` VALUES (32, 31, '回答', 'generator/yananswer', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (33, 32, '查看', NULL, 'generator:yananswer:list,generator:yananswer:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (34, 32, '新增', NULL, 'generator:yananswer:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (35, 32, '修改', NULL, 'generator:yananswer:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (36, 32, '删除', NULL, 'generator:yananswer:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (37, 31, '问题', 'generator/yanquestion', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (38, 37, '查看', NULL, 'generator:yanquestion:list,generator:yanquestion:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (39, 37, '新增', NULL, 'generator:yanquestion:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (40, 37, '修改', NULL, 'generator:yanquestion:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (41, 37, '删除', NULL, 'generator:yanquestion:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (42, 31, '收录大学', 'generator/yancollege', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (43, 42, '查看', NULL, 'generator:yancollege:list,generator:yancollege:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (44, 42, '新增', NULL, 'generator:yancollege:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (45, 42, '修改', NULL, 'generator:yancollege:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (46, 42, '删除', NULL, 'generator:yancollege:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (47, 31, '主要大学', 'generator/yanmajorcollege', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (48, 47, '查看', NULL, 'generator:yanmajorcollege:list,generator:yanmajorcollege:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (49, 47, '新增', NULL, 'generator:yanmajorcollege:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (50, 47, '修改', NULL, 'generator:yanmajorcollege:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (51, 47, '删除', NULL, 'generator:yanmajorcollege:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (52, 31, '问题标签', 'generator/yanquestiontag', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (53, 52, '查看', NULL, 'generator:yanquestiontag:list,generator:yanquestiontag:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (54, 52, '新增', NULL, 'generator:yanquestiontag:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (55, 52, '修改', NULL, 'generator:yanquestiontag:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (56, 52, '删除', NULL, 'generator:yanquestiontag:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (57, 31, '用户', 'generator/yanuser', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (58, 57, '查看', NULL, 'generator:yanuser:list,generator:yanuser:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (59, 57, '新增', NULL, 'generator:yanuser:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (60, 57, '修改', NULL, 'generator:yanuser:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (61, 57, '删除', NULL, 'generator:yanuser:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (62, 31, '录入的专业', 'generator/yanmajorme', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (63, 62, '查看', NULL, 'generator:yanmajorme:list,generator:yanmajorme:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (64, 62, '新增', NULL, 'generator:yanmajorme:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (65, 62, '修改', NULL, 'generator:yanmajorme:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (66, 62, '删除', NULL, 'generator:yanmajorme:delete', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (67, 31, '录入的问题', 'generator/yanmajorquestion', NULL, 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (68, 67, '查看', NULL, 'generator:yanmajorquestion:list,generator:yanmajorquestion:info', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (69, 67, '新增', NULL, 'generator:yanmajorquestion:save', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (70, 67, '修改', NULL, 'generator:yanmajorquestion:update', 2, NULL, 6);
INSERT INTO `sys_menu` VALUES (71, 67, '删除', NULL, 'generator:yanmajorquestion:delete', 2, NULL, 6);

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件上传' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(0) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', 1, 1, '2016-11-11 11:11:11');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `user_id` bigint(0) NOT NULL,
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, 'f8ffceaa9f4a3c9498f9f253729c33bc', '2022-02-25 01:31:23', '2022-02-24 13:31:23');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');

-- ----------------------------
-- Table structure for yan_answer
-- ----------------------------
DROP TABLE IF EXISTS `yan_answer`;
CREATE TABLE `yan_answer`  (
  `answer_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `que_id` int(0) NULL DEFAULT NULL,
  `openid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `answer_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `answer_des` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `answer_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `answer_like` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`answer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_answer
-- ----------------------------
INSERT INTO `yan_answer` VALUES ('1', 1, '56C02DF0516B4B079ABFCEC08169E577', 'Rebecca', 'WEB前端+我是rose', '<P>难道不明白纸质书更贵啊！！！</P>\r\n\r\n            <P>若觉得kindle更贵，我觉得要么阅读量太少，那确实没有买kindle的必要。</P>要么买的都是盗版的纸质书？我不清楚不加以评论。。。\r\n\r\n            另外，用kindle看小说的怎么真心不懂了。题主不看小说么？难道题主拿来看教科书还是技术文档？还是题主觉得小说就是小时代内样的？（对小时代没偏见，尊重多样性）\r\n\r\n            而且纸质书搬起来真心困难啊！当初毕业带不回来，忍痛卖了不少好桑心！\r\n\r\n            碎片时间阅读总不能天天背着一本书吧，那么占地方。\r\n\r\n            <P>看到描述最后一段，感觉有骗答案的嫌疑<P>', 123);
INSERT INTO `yan_answer` VALUES ('10', 998, 'obba-4kQv-DSbg2ZZm_liCEorcqk', 'rose', NULL, 'rose2', NULL);
INSERT INTO `yan_answer` VALUES ('11', 997, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 'rose', NULL, 'rose2', NULL);
INSERT INTO `yan_answer` VALUES ('12', 994, 'obba-4kQv-DSbg2ZZm_liCEorcqk', 'rose——test', NULL, '<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>', NULL);
INSERT INTO `yan_answer` VALUES ('13', 994, 'BEE8E17A7A794A979211DE3C4590824E', 'rose——test', NULL, '<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>', NULL);
INSERT INTO `yan_answer` VALUES ('14', 994, 'BEE8E17A7A794A979211DE3C4590824E', 'rose——test', NULL, '<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>', NULL);
INSERT INTO `yan_answer` VALUES ('1421404069859184642', 20, '1879878-NKCNO-NKNK', NULL, NULL, '<p>测试回答问题</p><p>测试</p>', 1);
INSERT INTO `yan_answer` VALUES ('1421404633124851714', 20, '1879878-NKCNO-NKNK', NULL, NULL, '<p>尝试再回答一次看看情况</p>', NULL);
INSERT INTO `yan_answer` VALUES ('1421449066465837058', 1, '1879878-NKCNO-NKNK', NULL, NULL, '<p>测试单个用户写其他用户的文章</p>', NULL);
INSERT INTO `yan_answer` VALUES ('1421740943857471490', 20, '1879878-NKCNO-NKNK', NULL, NULL, '<p>在写一次回答，查看报错</p>', NULL);
INSERT INTO `yan_answer` VALUES ('1421788993934102529', 3, '1879878-NKCNO-NKNK', NULL, NULL, '<p>测试写一条回答</p>', NULL);
INSERT INTO `yan_answer` VALUES ('1421789560173531138', 3, '1879878-NKCNO-NKNK', NULL, NULL, '<p>测试navigateBack</p>', NULL);
INSERT INTO `yan_answer` VALUES ('1603421746646994', 878, 'BEE8E17A7A794A979211DE3C4590824E', 'rose', NULL, '<p>测试是否能够把answerid给传入进入</p>', NULL);
INSERT INTO `yan_answer` VALUES ('1603421880321973', 878, 'obba-4kQv-DSbg2ZZm_liCEorcqk', 'rose', NULL, '<p>测试是否能够把answerid给传入进入</p>', NULL);
INSERT INTO `yan_answer` VALUES ('1603426359480190', 889, 'obba-4kQv-DSbg2ZZm_liCEorcqk', 'test', NULL, '<p><img class=\"wscnph\" src=\"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wgARCAFrAp4DASIAAhEBAxEB/8QAGgABAQADAQEAAAAAAAAAAAAAAAIBAwUEBv/EABUBAQEAAAAAAAAAAAAAAAAAAAAB/9oADAMBAAIQAxAAAAH78AAAAABjBSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRQAADTuBrNjXsJqaDyD1vJ6jIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGraj5TP0e+z5Dd9SX5GvrCfKbvpS8PuBNTRw3cHH6W4gKAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABGaEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqAAAAAABIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIoAAA5h03J8J9I5vSJqaDlydZ49Z0Hg1HUcb2HteDzHYeH3ICgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOF3R8/wCX6ocXtCTU0vHntDh7OwOFt7A+c6XRJzOf9GXw+4QFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQAAAAAAJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFAHjPY4XiPqnx/uT6J8/K/ROT1kmppTiQd5ysHWceDtvm+qnvcvwL9G8HvQFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAeP2Dm+Tuj5/f2RxXaHj9gTU0c50Ry66Q5mekON6/cPD5ewPH7BAUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVAAAAAAAjJSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRQAAEX8+d/HD5x9e4vaSamlOVk6jh9ooAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADTuHjx7R5/QE1NGqPQPPuoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYxQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaN/MPT5+XJ2L4PsTpebXx1+o9XE7dgSgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJoNW0TixrqhNAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABqzO41tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg5ePDqO1v5+k9Pj2+M2+rle1PRriF6vt5nTTTq2l8U7dxp9fn9pNB4eL2vJGvT0Ys6F0Vz+h5Y43X5/SrdiPQTQc7xdbxHmrbZ77oOX1NBx/Zq9hp9en2mMhzeb2dZ4d3ok9W7IAAAAAAAAAAAAAAAAAAAAA07tG4ywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsD//xAArEAACAgICAQMDAwUBAAAAAAAAEQECAyEEEhMUMWAQIjAyM3AFIzRQgED/2gAIAQEAAQUC+QOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBx+WPh0ZaW+tb1vMXrNyPYvnrS188Um2etSNx8DydvFx/F58GSJOL29Vxox+q4/SCF3nHVZWuD4/OR7Gb9eWXa32xir0w/BLYotl/DHt8T9hjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMf8asYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjH+WPb4dk5SzeovGWv9QyTHFzZMskexm5FqWjlWdeRHTFyLZKY+R2xeomcccm6nLkrkx8mt49bMxhz+W/wK2DJk5Pjt6i1c/ThUtS5HsZqWy5Ip/eVz7umLFOPH1v4vBfHTJ5oyxH3Riy1phtM3+Dx7fE9mzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2b/jXZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNm/yx7fCuVlnDgpep5L+Hy5IMuaa8jDe0Zb5714vCta9SPY5Nr1tXJeMkcia0xWy+HBnyeLtecNbz1mMnmxZcvWvIvaMM5PL/vuTinNgnixWfRZZwW4eW1svFyZSvCtjy14l61w45pYj2L8ecl/BfyRgzRPhywU49sePwX6ejtFcmC1reO3f0lumGl6T8Hj2+JoQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCF/GvuIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhC/BN6xMTEx2iJ+ke3w6+PJl53EraceS0Wy8D6R7EunJx25EmS1rcSsKvwnxR5sXFxYiONSJxYa4vpHseOvkrgx0PBj61jrHw6PiigUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUf8AZssYcfluetxRhnmYovbk0rknlYvJP9QxVnFnjN8N5/+H0x96T14OPrXLn+zkq8c2L/AG8GYm3wy1YtB46KaxYilazGOkWiIrHWIn/lHuztJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0/lxfo+D+rWfDyr2yZc84c2bLGHHTPex63J4/XZZieTeMscq3ltzcmM4+TLkgxft55y1r/fre2TJOTDbNOS+TxlZ7QZ72i8Z8qvyJ8uDI898njKz2g5F7VyebI81pjBjv5KXyeMrPaDk3tS8/pzy8mCZ8t8njKz2g5OS9M9cmV2zXi/HrlL5IpMexyb3rbFmzWi2bJ5uPbJ3vkikx7f8Au5OC/mwYr1z+GeXbN5bcTwWoePkRxr472v47Xz+G0cjxXnLw6TWhi/bv5CmC2EnF3vTjY6X+vI4/nLcLV+Na814uSuT65Mc2yeiy+KMdpv0tXL9clJmfTX72w55jHTkRP1vR5PRyTTLbJGLJGT658dslJ482m+CbGPj+PN/rMX7fxPFEdFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFB/8QAGBEAAgMAAAAAAAAAAAAAAAAAATBBYJD/2gAIAQMBAT8B1yK4pP8A/8QAGBEBAAMBAAAAAAAAAAAAAAAAAUBgcJD/2gAIAQIBAT8B5nuRkIoX/8QARBAAAgECBQICBQgGBwkAAAAAAAExAhEDEiFBkSIyUWETIGCBsRBCUnBxodHhBCMzUGLBFDCAksLw8UNTcoKDk6Ky4v/aAAgBAQAGPwL2hlEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolezadLum7aL5aknrToyqhPqpn1GstTy9zWw1lqqsru2xpTVVZXeXYv7CV5e62h+j+jzZrPPfxsfouEr5qanfSBdVKxLvP3ZmPDX6zMmnV1J+8wVT3rF6hd/wDTM+v2fgY2Nrnpx9Hc/SJyelWe3gY/or5Om1/Ux74mR/Np+mYmfE9E8isvpF86orrpV6La+4opcpW9haMR3vRHs/vwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8fVtDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZD9nMiypUu1TqMNLEwsSmu/avzNckX0X5lSxMt14f6+o7ZbJzbyFTVlnV8/gJtP/kpdS+4vkb02pt8RVV0VK/hTf4FFWbLdf7qpl6q1Tv+xqgyuim1r3TfwsK+bM27dLKulXs2rVJ/zGumL6O/sHiOnS1dNV6kUd1XVVVU8jpKs9GJ1WtTeplaacLqytfH1KkqX4J20/zqUNU13zaq0Tv7xLJidz0pbV1r7j0VWH00/Sw3V9gs2HkdLTvTRPDMJLDqb9G19hVbBr1w8vzf5GelL6KU3/AwcNKp5Hq8vkU2z5vRvarTQ1zvSXS6Vx9TsIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEL6tpRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJXsw66bX8zLW8aq1eTNmt/Mwv1r+de9f5o1xHelfSbzafaYi9LVlVK7NbFKeJjv5lnlkqy4rvdpKpdXxKm8V1+TUeo9akvC/kKjNU0qov51CvVh/8AUrysztyvOv7ijVVN/STp+/cw3VTd5W/2rRrTW7U5v2taLU4lVst8rt+BRmpp629c1/EaWKnmpfgv56Dz3je37/dFNrvxM1KzPNm6q2kU4fpbJXvbd3Kqnlu+3rj7tSt3pu6UimtPD0qb7bFadOHU679V7NXLvDpXTa6qv/L1KszWV772KHajR61eM7C6qe7Nrt7hq6dOyzuky3Vfgm2jDpVVKtS6XpceSrCV6culFr/eZlW7/bBhq1Kw6I1FT/Ba+dvX7DWmmleTvf6nZZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJf1bb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/P9RZ1JMundCTau49ksSltJZP8AxL62TevpKvgJ004PS3fNSqb8sxGq820fn6mPXmby0J25E7VO9PzrWv7jFpqrrzpappFrt+b9ivS63y5TSlN3vma1Knevqd+9oqyt9Tvq7+pnt1RJovvKla+abu5ZX97+qSEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiF/YAddV7LwP2GJzT+JTiPNZu1txU9X91lNFq7v+BlNCqVTfgxJp6/xU/iPKo80/g/Y2r3fEv6T9H/AGufNn1t4Cquldum+VNyU2rnTZ3fi+ow86p1v1PFqX+hg56lUurK/I0xKV+rafV5ldnfpo+HsbapJrz+S2Smy1tYV0nYbVKTcjqVFKqe9i1KSXkN2V3P9lLSlnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7Gdj/AK33v2IxKKqemnwky4uG6E+3pfBQqkvR1aX8x11XsvAy1YuDRXe2Wf5mG+hZs1/d7y/Ta16un8yul1UUqlpLobvcoWampNtPoaaKs+EqfoXk/WUJeac/J72ZqHTZTem4ksWirPr2PT7ypUXvTTZeDb3K1VTTbN9ONPsNUy9rfJRTTS3vJm9LVmt7v/QrXpqe2EtfiYfXU/LNf/GzVMva3yYVq0ruLSPNXnottU/iqTMtKtrPcVRqmXtb5MO1aWsWG81d5739IrSxKlVm+m48lcp69c1suZxbdXNUy9rfJhKir7Vb8y7xqsrqu6kuj4GXOv8AsVMVdWKmoat+Zqn8qyOv3L/5ZfPiNX8L/wCAxUsRKnRXyx+ZSq8Su1orXd9xqn+4KnTh6Olw2yirJVa7+b9vkOvGpdNFrUUuftK8Gqip1021S7i9dXT6XPamhtlFNFFaererVtSurLX76aur8DEbw8Waak6beHmYXRi9zdTqt4eRiWwapy311X95FV8PJZ2tr8nvZajKl4v8BvDrvfav/OhfEppadK0kqq9Hhzenpj1Pmrzy6jyvXwztLf8AEX6x9rX2GHV6VNU+Ob8fUpaVOm+5l9Kr5bb/AIiqrfb2pGai2WruT9Siqm16Xf7mektRVX4PRIqp6Wm76udPCxhrEVFqXNNT8PP1MOpW6Xqf7G+a98ms+JmWSm10vnX+BpUvR3zepUlVtBnbpzf8PTwYtsvXSkKu2EtPmUZf3b737Ke9kEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEH/8QALBAAAgIBAwMDBQACAwEAAAAAAAER8SExYfBBkdEQUbFgcYGhwTDhUHCAQP/aAAgBAQABPyH6gaNWkXheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4Xhef5dLe7+jlDBwjY1PrKWZo6Mn4QQjSdPT5n8+kKiiaCifv/oXTItVh3+B44FD0WHf4GSEcp5X0J96v5QaQ91v3CbQOtssiZuBGg6ziBRCohQ/eLwJHoZjneD+43V2wNIEFGjKP7RGOTZrqt/f0+Z/Po/UtJQrCP3Lxg1DSEx0b65xgeRNQ3ZxTqM0E27fQrNMjDTP+L5n8/SieSaevsRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGz/AK12JslUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVX+X5n8/R7HZSOlEqU1nI2GAjenCn3CA9SbK05S+fWCEAtNpcZnrKdPT5n8+iYzSc0QzaiVnH7ImSx0VErp0/IdYrfVg3TkdISaTvuzh+xzf09g/2/Zj3mNPQa6PQemaHB/nakgBJJqOGCMkESJOJ6vGiFoFYAoWJiX5ErHDoUPu112/f0GmxRZiHCenuRMSjXEFK0UjenEaDv1jPRqRjQnmIvXEJrl+nzP59J9rVQhGW319iMKDyc8ZO30CVs15ZYNnKTpuYPrjKF56FsPmJCe9jkbw/ZDTKEkoUmlrJ7pwxr+XyQTPEBP8mnyI2CY3slq6vdkgGRiSRPRluJ+0DNLqkhsm/39EfM/n6UhNIaORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5H/1rLaQkciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyL/AC/M/n6LR8yNfsILM1etnRxn9xNY/sC3nGsw0MlPCW1MtfEw6qhJNMdXEkNK0MGuX157krlujNrldHuaZcmK3j79fT5n8+jKyjakScP1Ue2h92Qg8ES/shSdQvbW17OdNRZdAQ+p9i/pAHha3r2MSANxMdfZa5F3bxjt4s6KUITjBwNdJkyFwElOy1aRt7jtQznBjXD/AKIaRE4uH93RJvH3/wCfd8jFs1GRt+NIf2SHAZRllDJdtxOkaGnhv/YfuSl5bmHLbwOJZGJ8rRZeBADJSdYWjJQtJOM0vfD0+Z/Po70WrDKItF7asUg1JDaf4EdSANbKTWrC6tdZMFycGoc9PY08bTgZreX+khvcR5bao00GssJX7T3CCn0y4R2S1/Iow+vB23iF03HD2sM7ZRRqwkNOC6PdvdtpP6I+Z/P0psbRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqv8ArVLJtvX3I2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjZ/gRmom0mxDWZlNPUaVWgby/X5n8/R7OwRJ+0s/lk1yWLQRp7WOr2sv5UpWJLwhpsV9ohlHp8z+fRvbKXD9mhjGOeinREpj7iENJrzX6hoYpndiX2+ikl5S4OkTJk5ygv2M/wDa8ZH4gauzmek+Z/Po35WGUsrddRQ0+IiG7SWyegkMuiRPZr7sSnEXuN939H6Wt39KNmqTKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrP/AAAjpoMavQg1CRpIdGrG919dJ9FGsjexsqATmpxFqumpMRtGGd+gfI2NT/uPo39/4D9HvrbP6JfqRDMN1biB40l1BJcmhJ/lQaUY/Cv8ZFqhVGuC1IkTlTiLrxlPsPdSRYf0a2PUkr0x8yhBCfua0xKlTDNa9JLL+5qdgVJZHYOiQJKo1EZf/lJNk0picFqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJarz/kehxN39EZ44EsGz+0z1WiHBoc2j8m+pNIevleAjpoMCBQrP1XPT+DO5siMZbp8iURiX2GJxjJnOg1ZJ6Mg+P2+JT1ZPFDUozTLdfOkDW4fEiN6xp+R6HA3Y1r9A7Dq9V0EpaxwbIe69uncV8ykGGWYeyj5IDEibk9DSgpE2F7ohZJe/pLwJwRae/WP9Dq5Le+rooe/bUxSJPHsCfszn9Makpr0ZsPXwCkTYXuiFkl7+kGe1SZ6H7P+DY5iZSstdYTj9CmxpiOp0LdCEiidV7PqhSJsL3RCyS9/Rqe+7uV1mHpGw2DrZ/baTAyDGhKTBYSkmUu5NTyajQKRNhe6IWSXv6JiRLeTiHrhjwYvdAUvZT+0x12GFJswtF908jOsCbzD/YSk3OjSGlHDWz9EQljM8dwzEr5TYTx4F4REO0NnTPwy1BCtwpDLZwffIlJudGkNKOGtn/97z3KTOOV0iPwNchHPLC/F7/6IMC2hN25ohMcaSdfuN5ozj/QWnYUAxaURsnWOgtUMRDWmJUp6PeSN0saIa7goFG+vR9kniCTQ5bzcxME7VwJr3iWvyPQ4G7JHoTM74/I19ykdkmtH4YhyvDfCU37/cjtSsCdC/o0mofrAWWJYZGn5fNxszcnGCfZ/HydMLN0lHRRKw9STgMqTER1caTUP1g+JSz9jUabn4rcfOuwolGzfd7kkyR917oaTUP1Qx1g4nB/RoysmniXssPr3IFKDNWcNZeq2HgbrkIUv69xpNQ/Vusg92Gv6JKTiLuN1fwVXR5EwOPw03EPVLLKc5lfackJ6+sPZOC0y937EFOcxJ7Jllr3waUEglYlTM9x8AkyeV03ZCev/FvQ4G7+k3oMZpf7GbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsf/2gAMAwEAAgADAAAAEPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPONPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPLz/AL+x7zz/AM8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888884880w404988888888888888888888888888888888ss/8APDHH3PfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPHPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPNPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPOO+P/NONO9NfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPLLHvDLHLLPfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP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\" /></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>自己测试测试测试</p>', NULL);
INSERT INTO `yan_answer` VALUES ('1603452869313478', 883, 'obba-4kQv-DSbg2ZZm_liCEorcqk', 'test', NULL, '<p><strong>测试封面，</strong></p>\n<p><strong>看看数据什么的</strong></p>', NULL);
INSERT INTO `yan_answer` VALUES ('1603454225761328', 776, '56C02DF0516B4B079ABFCEC08169E577', 'test', NULL, '<p><strong>先简略的写看看数据库里面有没有，</strong>这样子依赖的话电脑</p>\n<p><em>上面的数据库就又需要更换了，目前来说是这样的，但是该如何更换呢</em></p>', NULL);
INSERT INTO `yan_answer` VALUES ('2', 1, '56C02DF0516B4B079ABFCEC08169E577', 'Rose', 'rose', '<P>难道不明白纸质书更贵啊！！！</P>\r\n\r\n            <P>若觉得kindle更贵，我觉得要么阅读量太少，那确实没有买kindle的必要。</P>要么买的都是盗版的纸质书？我不清楚不加以评论。。。\r\n\r\n            另外，用kindle看小说的怎么真心不懂了。题主不看小说么？难道题主拿来看教科书还是技术文档？还是题主觉得小说就是小时代内样的？（对小时代没偏见，尊重多样性）\r\n\r\n            而且纸质书搬起来真心困难啊！当初毕业带不回来，忍痛卖了不少好桑心！\r\n\r\n            碎片时间阅读总不能天天背着一本书吧，那么占地方。\r\n\r\n            <P>看到描述最后一段，感觉有骗答案的嫌疑<P>', 134);
INSERT INTO `yan_answer` VALUES ('3', 1, 'obba-4kQv-DSbg2ZZm_liCEorcqk', NULL, 'rose_test', '<P>难道不明白纸质书更贵啊！！！</P>\r\n\r\n            <P>若觉得kindle更贵，我觉得要么阅读量太少，那确实没有买kindle的必要。</P>要么买的都是盗版的纸质书？我不清楚不加以评论。。。\r\n\r\n            另外，用kindle看小说的怎么真心不懂了。题主不看小说么？难道题主拿来看教科书还是技术文档？还是题主觉得小说就是小时代内样的？（对小时代没偏见，尊重多样性）\r\n\r\n            而且纸质书搬起来真心困难啊！当初毕业带不回来，忍痛卖了不少好桑心！\r\n\r\n            碎片时间阅读总不能天天背着一本书吧，那么占地方。\r\n\r\n            <P>看到描述最后一段，感觉有骗答案的嫌疑<P>', NULL);
INSERT INTO `yan_answer` VALUES ('4', 2, '56C02DF0516B4B079ABFCEC08169E577', 'make', NULL, '<P>1，多做题，少看视频...</P> <P>2.英语基础好的买黄皮书，差的的买考研圣经（考研真相）</P>', 1);
INSERT INTO `yan_answer` VALUES ('5', 3, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', NULL, NULL, '<p>我不知道那个铁塔的情况，</p><p>不过气象铁塔上会有一些测太阳辐射的设备，如果说辐射的话，太阳辐射那么多，大家赶紧躲进地底下呀~~~~~要不然辐射量这么大，会变异的呀~~~~</p>', NULL);
INSERT INTO `yan_answer` VALUES ('6', 2, 'obba-4kQv-DSbg2ZZm_liCEorcqk', NULL, NULL, '<p>1.ceui </p><p>2.回答的是考研的问题</p>', NULL);
INSERT INTO `yan_answer` VALUES ('7', 2, 'obba-4kQv-DSbg2ZZm_liCEorcqk', NULL, NULL, '<p>2.第二次 测试，看看能不能把queid测试进去 </p><p>然后看看看进去问题二，会不会多一个</p>', NULL);
INSERT INTO `yan_answer` VALUES ('8', 2, 'obba-4kQv-DSbg2ZZm_liCEorcqk', NULL, NULL, '<p>1.第三次测试</p><p>2.好像那个getdata的数据还是没有获得</p>', NULL);
INSERT INTO `yan_answer` VALUES ('9', 999, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 'rose', NULL, '<p>测试数据是否入库</p>', NULL);

-- ----------------------------
-- Table structure for yan_answer_comment
-- ----------------------------
DROP TABLE IF EXISTS `yan_answer_comment`;
CREATE TABLE `yan_answer_comment`  (
  `ans_comment_id` int(0) NOT NULL AUTO_INCREMENT,
  `answer_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回答的id',
  `relation_id` int(0) NULL DEFAULT NULL COMMENT '关联评论的id',
  `is_top` tinyint(0) NULL DEFAULT 1 COMMENT '是否顶层',
  `comment` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论人id',
  `show_status` int(0) NULL DEFAULT NULL COMMENT '逻辑删除',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ans_comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_answer_comment
-- ----------------------------
INSERT INTO `yan_answer_comment` VALUES (1, '1', NULL, 1, '测试一条评论的数据', '56C02DF0516B4B079ABFCEC08169E577', 0, '2021-07-23 18:05:42', NULL);
INSERT INTO `yan_answer_comment` VALUES (3, '1', NULL, 1, '测试map参数能不能这样子传递', 'BEE8E17A7A794A979211DE3C4590824E', 0, '2021-07-23 18:05:43', NULL);
INSERT INTO `yan_answer_comment` VALUES (4, '2', NULL, 1, '测试requestBody', 'BEE8E17A7A794A979211DE3C4590824E', 0, '2021-07-23 18:05:44', NULL);
INSERT INTO `yan_answer_comment` VALUES (5, '2', NULL, 1, '多两条置顶', 'BEE8E17A7A794A979211DE3C4590824E', 0, '2021-07-23 18:05:45', NULL);
INSERT INTO `yan_answer_comment` VALUES (6, '2', NULL, 1, '第二条数据的置顶', 'BEE8E17A7A794A979211DE3C4590824E', 0, '2021-07-23 18:05:45', NULL);
INSERT INTO `yan_answer_comment` VALUES (10, '2', 6, 0, '第二条层级的数据', '56C02DF0516B4B079ABFCEC08169E577', 0, '2021-08-01 17:29:22', NULL);
INSERT INTO `yan_answer_comment` VALUES (11, '2', 6, 0, '第二条层级的数据2', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 0, '2021-07-23 18:05:47', NULL);
INSERT INTO `yan_answer_comment` VALUES (12, '2', 6, 0, '第二条层级的数据3', '56C02DF0516B4B079ABFCEC08169E577', 0, '2021-08-01 17:29:19', NULL);
INSERT INTO `yan_answer_comment` VALUES (14, '2', NULL, 0, '测试自动填充时间', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 0, '2021-07-23 18:05:48', NULL);
INSERT INTO `yan_answer_comment` VALUES (15, '2', NULL, 0, '测试自动填充时间2', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 0, '2021-07-23 18:05:50', '2021-07-23 09:39:40');
INSERT INTO `yan_answer_comment` VALUES (16, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `yan_answer_comment` VALUES (17, '4', NULL, 1, '159', '1879878-NKCNO-NKNK', 0, '2021-08-01 09:50:22', '2021-08-01 09:50:22');
INSERT INTO `yan_answer_comment` VALUES (18, '4', NULL, 1, '先测试一下置顶评论如何', '1879878-NKCNO-NKNK', 0, '2021-08-01 09:51:49', '2021-08-01 09:51:49');
INSERT INTO `yan_answer_comment` VALUES (19, '1421404069859184642', NULL, 1, '留言一下', '56C02DF0516B4B079ABFCEC08169E577', 0, '2021-08-01 10:28:51', '2021-08-01 10:28:51');
INSERT INTO `yan_answer_comment` VALUES (20, '1421404069859184642', NULL, 1, '留言一下', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 0, '2021-08-01 10:29:42', '2021-08-01 10:29:42');

-- ----------------------------
-- Table structure for yan_answer_like
-- ----------------------------
DROP TABLE IF EXISTS `yan_answer_like`;
CREATE TABLE `yan_answer_like`  (
  `like_id` int(0) NOT NULL AUTO_INCREMENT,
  `like_user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '点赞的用户id',
  `like_answer_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '点赞的回答id',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '1为点赞，0为未点赞',
  PRIMARY KEY (`like_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_answer_like
-- ----------------------------
INSERT INTO `yan_answer_like` VALUES (4, 'BEE8E17A7A794A979211DE3C4590824E', '1', 0);
INSERT INTO `yan_answer_like` VALUES (5, '1879878-NKCNO-NKNK', '4', 0);
INSERT INTO `yan_answer_like` VALUES (6, '56C02DF0516B4B079ABFCEC08169E577', '1421404069859184642', 1);

-- ----------------------------
-- Table structure for yan_college
-- ----------------------------
DROP TABLE IF EXISTS `yan_college`;
CREATE TABLE `yan_college`  (
  `index` int(0) NOT NULL AUTO_INCREMENT,
  `code1` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `direction` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nizhao` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `school` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shuju` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tongzhao` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tuimian` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `xh` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `yuan` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`index`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_college
-- ----------------------------

-- ----------------------------
-- Table structure for yan_data_discovery
-- ----------------------------
DROP TABLE IF EXISTS `yan_data_discovery`;
CREATE TABLE `yan_data_discovery`  (
  `data_id` int(0) NOT NULL AUTO_INCREMENT,
  `question_id` int(0) NULL DEFAULT NULL,
  `answer_id` bigint(0) NULL DEFAULT NULL,
  `feed_source_id` int(0) NULL DEFAULT NULL,
  `feed_source_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `feed_source_txt` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `feed_source_img` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `question` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `answer_ctnt_short` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `answer_ctnt` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `good_num` int(0) NULL DEFAULT NULL,
  `comment_num` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_data_discovery
-- ----------------------------
INSERT INTO `yan_data_discovery` VALUES (1, 1, 1, 23, 'Rebecca', '赞了回答1', 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/1540540394516.jpeg', '2020年预计硕士生扩招18.9万，这一数字意味着什么，应该如何 把握机遇', '国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章.', '国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章的要求没博士生那么高。...', 112, 18);
INSERT INTO `yan_data_discovery` VALUES (2, 2, 4, 24, 'Alex2', '回答了问题2', 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540308141104.jpg', '有哪些考研需要避开的坑', '1，多做题，少看视频... 2.英语基础好的买黄皮书，差的的买考研圣经（考研真相）', '1，多做题，少看视频... 2.英语基础好的买黄皮书，差的的买考研圣经（考研真相）', 112, 18);
INSERT INTO `yan_data_discovery` VALUES (3, 3, 5, 25, 'George3', '赞了回答3', 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg', '气象铁塔的辐射大吗？', '我不知道那个铁塔的情况，不过气象铁塔上会有一些测太阳辐射的设备，如果说辐射的话，太阳辐射那么多，大家赶紧躲进地底下呀~~~~~要不然辐射量这么', '我不知道那个铁塔的情况，不过气象铁塔上会有一些测太阳辐射的设备，如果说辐射的话，太阳辐射那么多，大家赶紧躲进地底下呀~~~~~要不然辐射量这么大，会变异的呀~~~~', 112, 18);
INSERT INTO `yan_data_discovery` VALUES (4, 1, 3, NULL, NULL, NULL, 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg', '2020年预计硕士生扩招18.9万，这一数字意味着什么，应该如何 把握机遇', '国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章的要求没博', '国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章的要求没博士生那么高。...', 123, 2312);
INSERT INTO `yan_data_discovery` VALUES (5, 1, 2, NULL, NULL, NULL, 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg', '2020年预计硕士生扩招18.9万，这一数字意味着什么，应该如何 把握机遇', '国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章的要求没博', '国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章的要求没博士生那么高。...', 222, 1232);
INSERT INTO `yan_data_discovery` VALUES (9, 994, NULL, NULL, 'rose——test', '开始测试', NULL, '994测试 Tiny编辑器是否有用', '测试', '<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>', NULL, NULL);
INSERT INTO `yan_data_discovery` VALUES (10, 994, NULL, NULL, 'rose——test', '开始测试', NULL, '994测试 Tiny编辑器是否有用', '测试', '<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>', NULL, NULL);
INSERT INTO `yan_data_discovery` VALUES (11, 994, NULL, NULL, 'rose——test', '开始测试', NULL, '994测试 Tiny编辑器是否有用', '测试', '<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>', NULL, NULL);
INSERT INTO `yan_data_discovery` VALUES (12, 878, 1603421880321973, NULL, 'rose', 'answerid给', NULL, '测试是否能够把answerid给传入进入', '测试', '<p>测试是否能够把answerid给传入进入</p>', NULL, NULL);
INSERT INTO `yan_data_discovery` VALUES (13, 889, 1603426359480190, NULL, 'test', '测试封面功能', '', '测试封面功能', '测试', '<p><img class=\"wscnph\" src=\"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wgARCAFrAp4DASIAAhEBAxEB/8QAGgABAQADAQEAAAAAAAAAAAAAAAIBAwUEBv/EABUBAQEAAAAAAAAAAAAAAAAAAAAB/9oADAMBAAIQAxAAAAH78AAAAABjBSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRQAADTuBrNjXsJqaDyD1vJ6jIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGraj5TP0e+z5Dd9SX5GvrCfKbvpS8PuBNTRw3cHH6W4gKAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABGaEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqEqAAAAAABIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIpIoAAA5h03J8J9I5vSJqaDlydZ49Z0Hg1HUcb2HteDzHYeH3ICgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOF3R8/wCX6ocXtCTU0vHntDh7OwOFt7A+c6XRJzOf9GXw+4QFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQAAAAAAJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFJFAHjPY4XiPqnx/uT6J8/K/ROT1kmppTiQd5ysHWceDtvm+qnvcvwL9G8HvQFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAeP2Dm+Tuj5/f2RxXaHj9gTU0c50Ry66Q5mekON6/cPD5ewPH7BAUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVCVAAAAAAAjJSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRSRQAAEX8+d/HD5x9e4vaSamlOVk6jh9ooAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADTuHjx7R5/QE1NGqPQPPuoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYxQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQlQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaN/MPT5+XJ2L4PsTpebXx1+o9XE7dgSgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJoNW0TixrqhNAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABqzO41tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg1tg5ePDqO1v5+k9Pj2+M2+rle1PRriF6vt5nTTTq2l8U7dxp9fn9pNB4eL2vJGvT0Ys6F0Vz+h5Y43X5/SrdiPQTQc7xdbxHmrbZ77oOX1NBx/Zq9hp9en2mMhzeb2dZ4d3ok9W7IAAAAAAAAAAAAAAAAAAAAA07tG4ywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsDLAywMsD//xAArEAACAgICAQMDAwUBAAAAAAAAEQECAyEEEhMUMWAQIjAyM3AFIzRQgED/2gAIAQEAAQUC+QOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBwOBx+WPh0ZaW+tb1vMXrNyPYvnrS188Um2etSNx8DydvFx/F58GSJOL29Vxox+q4/SCF3nHVZWuD4/OR7Gb9eWXa32xir0w/BLYotl/DHt8T9hjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMf8asYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjGMYxjH+WPb4dk5SzeovGWv9QyTHFzZMskexm5FqWjlWdeRHTFyLZKY+R2xeomcccm6nLkrkx8mt49bMxhz+W/wK2DJk5Pjt6i1c/ThUtS5HsZqWy5Ip/eVz7umLFOPH1v4vBfHTJ5oyxH3Riy1phtM3+Dx7fE9mzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2b/jXZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNmzZs2bNm/yx7fCuVlnDgpep5L+Hy5IMuaa8jDe0Zb5714vCta9SPY5Nr1tXJeMkcia0xWy+HBnyeLtecNbz1mMnmxZcvWvIvaMM5PL/vuTinNgnixWfRZZwW4eW1svFyZSvCtjy14l61w45pYj2L8ecl/BfyRgzRPhywU49sePwX6ejtFcmC1reO3f0lumGl6T8Hj2+JoQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCF/GvuIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhCEIQhC/BN6xMTEx2iJ+ke3w6+PJl53EraceS0Wy8D6R7EunJx25EmS1rcSsKvwnxR5sXFxYiONSJxYa4vpHseOvkrgx0PBj61jrHw6PiigUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUCgUf8AZssYcfluetxRhnmYovbk0rknlYvJP9QxVnFnjN8N5/+H0x96T14OPrXLn+zkq8c2L/AG8GYm3wy1YtB46KaxYilazGOkWiIrHWIn/lHuztJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0naTtJ2k7SdpO0/lxfo+D+rWfDyr2yZc84c2bLGHHTPex63J4/XZZieTeMscq3ltzcmM4+TLkgxft55y1r/fre2TJOTDbNOS+TxlZ7QZ72i8Z8qvyJ8uDI898njKz2g5F7VyebI81pjBjv5KXyeMrPaDk3tS8/pzy8mCZ8t8njKz2g5OS9M9cmV2zXi/HrlL5IpMexyb3rbFmzWi2bJ5uPbJ3vkikx7f8Au5OC/mwYr1z+GeXbN5bcTwWoePkRxr472v47Xz+G0cjxXnLw6TWhi/bv5CmC2EnF3vTjY6X+vI4/nLcLV+Na814uSuT65Mc2yeiy+KMdpv0tXL9clJmfTX72w55jHTkRP1vR5PRyTTLbJGLJGT658dslJ482m+CbGPj+PN/rMX7fxPFEdFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFAoFB/8QAGBEAAgMAAAAAAAAAAAAAAAAAATBBYJD/2gAIAQMBAT8B1yK4pP8A/8QAGBEBAAMBAAAAAAAAAAAAAAAAAUBgcJD/2gAIAQIBAT8B5nuRkIoX/8QARBAAAgECBQICBQgGBwkAAAAAAAExAhEDEiFBkSIyUWETIGCBsRBCUnBxodHhBCMzUGLBFDCAksLw8UNTcoKDk6Ky4v/aAAgBAQAGPwL2hlEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolEolezadLum7aL5aknrToyqhPqpn1GstTy9zWw1lqqsru2xpTVVZXeXYv7CV5e62h+j+jzZrPPfxsfouEr5qanfSBdVKxLvP3ZmPDX6zMmnV1J+8wVT3rF6hd/wDTM+v2fgY2Nrnpx9Hc/SJyelWe3gY/or5Om1/Ux74mR/Np+mYmfE9E8isvpF86orrpV6La+4opcpW9haMR3vRHs/vwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8G/Bvwb8fVtDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZDIZD9nMiypUu1TqMNLEwsSmu/avzNckX0X5lSxMt14f6+o7ZbJzbyFTVlnV8/gJtP/kpdS+4vkb02pt8RVV0VK/hTf4FFWbLdf7qpl6q1Tv+xqgyuim1r3TfwsK+bM27dLKulXs2rVJ/zGumL6O/sHiOnS1dNV6kUd1XVVVU8jpKs9GJ1WtTeplaacLqytfH1KkqX4J20/zqUNU13zaq0Tv7xLJidz0pbV1r7j0VWH00/Sw3V9gs2HkdLTvTRPDMJLDqb9G19hVbBr1w8vzf5GelL6KU3/AwcNKp5Hq8vkU2z5vRvarTQ1zvSXS6Vx9TsIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEIhEL6tpRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJRKJXsw66bX8zLW8aq1eTNmt/Mwv1r+de9f5o1xHelfSbzafaYi9LVlVK7NbFKeJjv5lnlkqy4rvdpKpdXxKm8V1+TUeo9akvC/kKjNU0qov51CvVh/8AUrysztyvOv7ijVVN/STp+/cw3VTd5W/2rRrTW7U5v2taLU4lVst8rt+BRmpp629c1/EaWKnmpfgv56Dz3je37/dFNrvxM1KzPNm6q2kU4fpbJXvbd3Kqnlu+3rj7tSt3pu6UimtPD0qb7bFadOHU679V7NXLvDpXTa6qv/L1KszWV772KHajR61eM7C6qe7Nrt7hq6dOyzuky3Vfgm2jDpVVKtS6XpceSrCV6culFr/eZlW7/bBhq1Kw6I1FT/Ba+dvX7DWmmleTvf6nZZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJZLJf1bb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/Jvyb8m/P9RZ1JMundCTau49ksSltJZP8AxL62TevpKvgJ004PS3fNSqb8sxGq820fn6mPXmby0J25E7VO9PzrWv7jFpqrrzpappFrt+b9ivS63y5TSlN3vma1Knevqd+9oqyt9Tvq7+pnt1RJovvKla+abu5ZX97+qSEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiEQiF/YAddV7LwP2GJzT+JTiPNZu1txU9X91lNFq7v+BlNCqVTfgxJp6/xU/iPKo80/g/Y2r3fEv6T9H/AGufNn1t4Cquldum+VNyU2rnTZ3fi+ow86p1v1PFqX+hg56lUurK/I0xKV+rafV5ldnfpo+HsbapJrz+S2Smy1tYV0nYbVKTcjqVFKqe9i1KSXkN2V3P9lLSlnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7GdjOxnYzsZ2M7Gdj/AK33v2IxKKqemnwky4uG6E+3pfBQqkvR1aX8x11XsvAy1YuDRXe2Wf5mG+hZs1/d7y/Ta16un8yul1UUqlpLobvcoWampNtPoaaKs+EqfoXk/WUJeac/J72ZqHTZTem4ksWirPr2PT7ypUXvTTZeDb3K1VTTbN9ONPsNUy9rfJRTTS3vJm9LVmt7v/QrXpqe2EtfiYfXU/LNf/GzVMva3yYVq0ruLSPNXnottU/iqTMtKtrPcVRqmXtb5MO1aWsWG81d5739IrSxKlVm+m48lcp69c1suZxbdXNUy9rfJhKir7Vb8y7xqsrqu6kuj4GXOv8AsVMVdWKmoat+Zqn8qyOv3L/5ZfPiNX8L/wCAxUsRKnRXyx+ZSq8Su1orXd9xqn+4KnTh6Olw2yirJVa7+b9vkOvGpdNFrUUuftK8Gqip1021S7i9dXT6XPamhtlFNFFaererVtSurLX76aur8DEbw8Waak6beHmYXRi9zdTqt4eRiWwapy311X95FV8PJZ2tr8nvZajKl4v8BvDrvfav/OhfEppadK0kqq9Hhzenpj1Pmrzy6jyvXwztLf8AEX6x9rX2GHV6VNU+Ob8fUpaVOm+5l9Kr5bb/AIiqrfb2pGai2WruT9Siqm16Xf7mektRVX4PRIqp6Wm76udPCxhrEVFqXNNT8PP1MOpW6Xqf7G+a98ms+JmWSm10vnX+BpUvR3zepUlVtBnbpzf8PTwYtsvXSkKu2EtPmUZf3b737Ke9kEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEH/8QALBAAAgIBAwMDBQACAwEAAAAAAAER8SExYfBBkdEQUbFgcYGhwTDhUHCAQP/aAAgBAQABPyH6gaNWkXheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4XheF4Xhef5dLe7+jlDBwjY1PrKWZo6Mn4QQjSdPT5n8+kKiiaCifv/oXTItVh3+B44FD0WHf4GSEcp5X0J96v5QaQ91v3CbQOtssiZuBGg6ziBRCohQ/eLwJHoZjneD+43V2wNIEFGjKP7RGOTZrqt/f0+Z/Po/UtJQrCP3Lxg1DSEx0b65xgeRNQ3ZxTqM0E27fQrNMjDTP+L5n8/SieSaevsRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGz/AK12JslUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVX+X5n8/R7HZSOlEqU1nI2GAjenCn3CA9SbK05S+fWCEAtNpcZnrKdPT5n8+iYzSc0QzaiVnH7ImSx0VErp0/IdYrfVg3TkdISaTvuzh+xzf09g/2/Zj3mNPQa6PQemaHB/nakgBJJqOGCMkESJOJ6vGiFoFYAoWJiX5ErHDoUPu112/f0GmxRZiHCenuRMSjXEFK0UjenEaDv1jPRqRjQnmIvXEJrl+nzP59J9rVQhGW319iMKDyc8ZO30CVs15ZYNnKTpuYPrjKF56FsPmJCe9jkbw/ZDTKEkoUmlrJ7pwxr+XyQTPEBP8mnyI2CY3slq6vdkgGRiSRPRluJ+0DNLqkhsm/39EfM/n6UhNIaORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5GcjORnIzkZyM5H/1rLaQkciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyI5EciORHIjkRyL/AC/M/n6LR8yNfsILM1etnRxn9xNY/sC3nGsw0MlPCW1MtfEw6qhJNMdXEkNK0MGuX157krlujNrldHuaZcmK3j79fT5n8+jKyjakScP1Ue2h92Qg8ES/shSdQvbW17OdNRZdAQ+p9i/pAHha3r2MSANxMdfZa5F3bxjt4s6KUITjBwNdJkyFwElOy1aRt7jtQznBjXD/AKIaRE4uH93RJvH3/wCfd8jFs1GRt+NIf2SHAZRllDJdtxOkaGnhv/YfuSl5bmHLbwOJZGJ8rRZeBADJSdYWjJQtJOM0vfD0+Z/Po70WrDKItF7asUg1JDaf4EdSANbKTWrC6tdZMFycGoc9PY08bTgZreX+khvcR5bao00GssJX7T3CCn0y4R2S1/Iow+vB23iF03HD2sM7ZRRqwkNOC6PdvdtpP6I+Z/P0psbRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqiVRKolUSqJVEqv8ArVLJtvX3I2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjYRsI2EbCNhGwjZ/gRmom0mxDWZlNPUaVWgby/X5n8/R7OwRJ+0s/lk1yWLQRp7WOr2sv5UpWJLwhpsV9ohlHp8z+fRvbKXD9mhjGOeinREpj7iENJrzX6hoYpndiX2+ikl5S4OkTJk5ygv2M/wDa8ZH4gauzmek+Z/Po35WGUsrddRQ0+IiG7SWyegkMuiRPZr7sSnEXuN939H6Wt39KNmqTKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrCsKwrP/AAAjpoMavQg1CRpIdGrG919dJ9FGsjexsqATmpxFqumpMRtGGd+gfI2NT/uPo39/4D9HvrbP6JfqRDMN1biB40l1BJcmhJ/lQaUY/Cv8ZFqhVGuC1IkTlTiLrxlPsPdSRYf0a2PUkr0x8yhBCfua0xKlTDNa9JLL+5qdgVJZHYOiQJKo1EZf/lJNk0picFqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJaryWq8lqvJarz/kehxN39EZ44EsGz+0z1WiHBoc2j8m+pNIevleAjpoMCBQrP1XPT+DO5siMZbp8iURiX2GJxjJnOg1ZJ6Mg+P2+JT1ZPFDUozTLdfOkDW4fEiN6xp+R6HA3Y1r9A7Dq9V0EpaxwbIe69uncV8ykGGWYeyj5IDEibk9DSgpE2F7ohZJe/pLwJwRae/WP9Dq5Le+rooe/bUxSJPHsCfszn9Makpr0ZsPXwCkTYXuiFkl7+kGe1SZ6H7P+DY5iZSstdYTj9CmxpiOp0LdCEiidV7PqhSJsL3RCyS9/Rqe+7uV1mHpGw2DrZ/baTAyDGhKTBYSkmUu5NTyajQKRNhe6IWSXv6JiRLeTiHrhjwYvdAUvZT+0x12GFJswtF908jOsCbzD/YSk3OjSGlHDWz9EQljM8dwzEr5TYTx4F4REO0NnTPwy1BCtwpDLZwffIlJudGkNKOGtn/97z3KTOOV0iPwNchHPLC/F7/6IMC2hN25ohMcaSdfuN5ozj/QWnYUAxaURsnWOgtUMRDWmJUp6PeSN0saIa7goFG+vR9kniCTQ5bzcxME7VwJr3iWvyPQ4G7JHoTM74/I19ykdkmtH4YhyvDfCU37/cjtSsCdC/o0mofrAWWJYZGn5fNxszcnGCfZ/HydMLN0lHRRKw9STgMqTER1caTUP1g+JSz9jUabn4rcfOuwolGzfd7kkyR917oaTUP1Qx1g4nB/RoysmniXssPr3IFKDNWcNZeq2HgbrkIUv69xpNQ/Vusg92Gv6JKTiLuN1fwVXR5EwOPw03EPVLLKc5lfackJ6+sPZOC0y937EFOcxJ7Jllr3waUEglYlTM9x8AkyeV03ZCev/FvQ4G7+k3oMZpf7GbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsf/2gAMAwEAAgADAAAAEPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPONPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPLz/AL+x7zz/AM8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888884880w404988888888888888888888888888888888ss/8APDHH3PfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPHPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPNPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPOO+P/NONO9NfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPLLHvDLHLLPfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP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\" /></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>自己测试测试测试</p>', NULL, NULL);
INSERT INTO `yan_data_discovery` VALUES (14, 883, 1603452869313478, NULL, 'test', '处理封面', 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/23/6061c505dbd6425ea296f0b1c83271edQQ截图3.jpg', '测试封面的数据应该怎么处理', '测试', '<p><strong>测试封面，</strong></p>\n<p><strong>看看数据什么的</strong></p>', NULL, NULL);
INSERT INTO `yan_data_discovery` VALUES (15, 776, 1603454225761328, NULL, 'test', '测试缩略图有没有效果', 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/23/e6bafc70cbac4cfd83c3a2726e80c57aQQ截图.jpg', '测试缩略图有没有效果', '先简略的写看看数据库里面有没有，这样子依赖的话电脑上面的数据库就又需要更换了，目前来说是这样的，但是该如何更换呢', '<p><strong>先简略的写看看数据库里面有没有，</strong>这样子依赖的话电脑</p>\n<p><em>上面的数据库就又需要更换了，目前来说是这样的，但是该如何更换呢</em></p>', NULL, NULL);

-- ----------------------------
-- Table structure for yan_major
-- ----------------------------
DROP TABLE IF EXISTS `yan_major`;
CREATE TABLE `yan_major`  (
  `pri` int(0) NOT NULL AUTO_INCREMENT,
  `id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pri`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 183 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_major
-- ----------------------------
INSERT INTO `yan_major` VALUES (4, '1', '0101', '学硕-哲学-哲学');
INSERT INTO `yan_major` VALUES (5, '2', '0201', '学硕-经济学-理论经济学');
INSERT INTO `yan_major` VALUES (6, '2', '0202', '学硕-经济学-应用经济学');
INSERT INTO `yan_major` VALUES (7, '2', '0251', '专硕-经济学-金融');
INSERT INTO `yan_major` VALUES (8, '2', '0252', '专硕-经济学-应用统计');
INSERT INTO `yan_major` VALUES (9, '2', '0253', '专硕-经济学-税务');
INSERT INTO `yan_major` VALUES (10, '2', '0254', '专硕-经济学-国际商务');
INSERT INTO `yan_major` VALUES (11, '2', '0255', '专硕-经济学-保险');
INSERT INTO `yan_major` VALUES (12, '2', '0256', '专硕-经济学-资产评估');
INSERT INTO `yan_major` VALUES (13, '2', '0257', '专硕-经济学-审计');
INSERT INTO `yan_major` VALUES (14, '2', '0270', '学硕-经济学-统计学');
INSERT INTO `yan_major` VALUES (15, '3', '0301', '学硕-法学-法学');
INSERT INTO `yan_major` VALUES (16, '3', '0302', '学硕-法学-政治学');
INSERT INTO `yan_major` VALUES (17, '3', '0303', '学硕-法学-社会学');
INSERT INTO `yan_major` VALUES (18, '3', '0304', '学硕-法学-民族学');
INSERT INTO `yan_major` VALUES (19, '3', '0305', '学硕-法学-马克思主义理论');
INSERT INTO `yan_major` VALUES (20, '3', '0306', '学硕-法学-公安学');
INSERT INTO `yan_major` VALUES (21, '3', '0351', '专硕-法学-法律');
INSERT INTO `yan_major` VALUES (22, '3', '0352', '专硕-法学-社会工作');
INSERT INTO `yan_major` VALUES (23, '3', '0353', '专硕-法学-警务');
INSERT INTO `yan_major` VALUES (24, '4', '0401', '学硕-教育学-教育学');
INSERT INTO `yan_major` VALUES (25, '4', '0402', '学硕-教育学-心理学');
INSERT INTO `yan_major` VALUES (26, '4', '0403', '学硕-教育学-体育学');
INSERT INTO `yan_major` VALUES (27, '4', '0451', '专硕-教育学-教育');
INSERT INTO `yan_major` VALUES (28, '4', '0452', '专硕-教育学-体育');
INSERT INTO `yan_major` VALUES (29, '4', '0453', '专硕-教育学-汉语国际教育');
INSERT INTO `yan_major` VALUES (30, '4', '0454', '专硕-教育学-应用心理');
INSERT INTO `yan_major` VALUES (31, '4', '0471', '学硕-教育学-教育经济与管理');
INSERT INTO `yan_major` VALUES (32, '5', '0501', '学硕-文学-中国语言文学');
INSERT INTO `yan_major` VALUES (33, '5', '0502', '学硕-文学-外国语言文学');
INSERT INTO `yan_major` VALUES (34, '5', '0503', '学硕-文学-新闻传播学');
INSERT INTO `yan_major` VALUES (35, '5', '0551', '专硕-文学-翻译');
INSERT INTO `yan_major` VALUES (36, '5', '0552', '专硕-文学-新闻与传播');
INSERT INTO `yan_major` VALUES (37, '5', '0553', '专硕-文学-出版');
INSERT INTO `yan_major` VALUES (38, '6', '0601', '学硕-历史学-考古学');
INSERT INTO `yan_major` VALUES (39, '6', '0602', '学硕-历史学-中国史');
INSERT INTO `yan_major` VALUES (40, '6', '0603', '学硕-历史学-世界史');
INSERT INTO `yan_major` VALUES (41, '6', '0651', '专硕-历史学-文物与博物馆');
INSERT INTO `yan_major` VALUES (42, '7', '0701', '学硕-理学-数学');
INSERT INTO `yan_major` VALUES (43, '7', '0702', '学硕-理学-物理学');
INSERT INTO `yan_major` VALUES (44, '7', '0703', '学硕-理学-化学');
INSERT INTO `yan_major` VALUES (45, '7', '0704', '学硕-理学-天文学');
INSERT INTO `yan_major` VALUES (46, '7', '0705', '学硕-理学-地理学');
INSERT INTO `yan_major` VALUES (47, '7', '0706', '学硕-理学-大气科学');
INSERT INTO `yan_major` VALUES (48, '7', '0707', '学硕-理学-海洋科学');
INSERT INTO `yan_major` VALUES (49, '7', '0708', '学硕-理学-地球物理学');
INSERT INTO `yan_major` VALUES (50, '7', '0709', '学硕-理学-地质学');
INSERT INTO `yan_major` VALUES (51, '7', '0710', '学硕-理学-生物学');
INSERT INTO `yan_major` VALUES (52, '7', '0711', '学硕-理学-系统科学');
INSERT INTO `yan_major` VALUES (53, '7', '0712', '学硕-理学-科学技术史');
INSERT INTO `yan_major` VALUES (54, '7', '0713', '学硕-理学-生态学');
INSERT INTO `yan_major` VALUES (55, '7', '0714', '学硕-理学-统计学');
INSERT INTO `yan_major` VALUES (56, '7', '0771', '学硕-理学-心理学');
INSERT INTO `yan_major` VALUES (57, '7', '0772', '学硕-理学-力学');
INSERT INTO `yan_major` VALUES (58, '7', '0773', '学硕-理学-材料科学与工程');
INSERT INTO `yan_major` VALUES (59, '7', '0774', '学硕-理学-电子科学与技术');
INSERT INTO `yan_major` VALUES (60, '7', '0775', '学硕-理学-计算机科学与技术');
INSERT INTO `yan_major` VALUES (61, '7', '0776', '学硕-理学-环境科学与工程');
INSERT INTO `yan_major` VALUES (62, '7', '0777', '学硕-理学-生物医学工程');
INSERT INTO `yan_major` VALUES (63, '7', '0778', '学硕-理学-基础医学');
INSERT INTO `yan_major` VALUES (64, '7', '0779', '学硕-理学-公共卫生与预防医学');
INSERT INTO `yan_major` VALUES (65, '7', '0780', '学硕-理学-药学');
INSERT INTO `yan_major` VALUES (66, '7', '0781', '学硕-理学-中药学');
INSERT INTO `yan_major` VALUES (67, '7', '0782', '学硕-理学-医学技术');
INSERT INTO `yan_major` VALUES (68, '7', '0783', '学硕-理学-护理学');
INSERT INTO `yan_major` VALUES (69, '7', '0784', '学硕-理学-教育技术学');
INSERT INTO `yan_major` VALUES (70, '7', '0785', '学硕-理学-运动人体科学');
INSERT INTO `yan_major` VALUES (71, '7', '0786', '学硕-理学-农药学');
INSERT INTO `yan_major` VALUES (72, '8', '0801', '学硕-工学-力学');
INSERT INTO `yan_major` VALUES (73, '8', '0802', '学硕-工学-机械工程');
INSERT INTO `yan_major` VALUES (74, '8', '0803', '学硕-工学-光学工程');
INSERT INTO `yan_major` VALUES (75, '8', '0804', '学硕-工学-仪器科学与技术');
INSERT INTO `yan_major` VALUES (76, '8', '0805', '学硕-工学-材料科学与工程');
INSERT INTO `yan_major` VALUES (77, '8', '0806', '学硕-工学-冶金工程');
INSERT INTO `yan_major` VALUES (78, '8', '0807', '学硕-工学-动力工程及工程热物理');
INSERT INTO `yan_major` VALUES (79, '8', '0808', '学硕-工学-电气工程');
INSERT INTO `yan_major` VALUES (80, '8', '0809', '学硕-工学-电子科学与技术');
INSERT INTO `yan_major` VALUES (81, '8', '0810', '学硕-工学-信息与通信工程');
INSERT INTO `yan_major` VALUES (82, '8', '0811', '学硕-工学-控制科学与工程');
INSERT INTO `yan_major` VALUES (83, '8', '0812', '学硕-工学-计算机科学与技术');
INSERT INTO `yan_major` VALUES (84, '8', '0813', '学硕-工学-建筑学');
INSERT INTO `yan_major` VALUES (85, '8', '0814', '学硕-工学-土木工程');
INSERT INTO `yan_major` VALUES (86, '8', '0815', '学硕-工学-水利工程');
INSERT INTO `yan_major` VALUES (87, '8', '0816', '学硕-工学-测绘科学与技术');
INSERT INTO `yan_major` VALUES (88, '8', '0817', '学硕-工学-化学工程与技术');
INSERT INTO `yan_major` VALUES (89, '8', '0818', '学硕-工学-地质资源与地质工程');
INSERT INTO `yan_major` VALUES (90, '8', '0819', '学硕-工学-矿业工程');
INSERT INTO `yan_major` VALUES (91, '8', '0820', '学硕-工学-石油与天然气工程');
INSERT INTO `yan_major` VALUES (92, '8', '0821', '学硕-工学-纺织科学与工程');
INSERT INTO `yan_major` VALUES (93, '8', '0822', '学硕-工学-轻工技术与工程');
INSERT INTO `yan_major` VALUES (94, '8', '0823', '学硕-工学-交通运输工程');
INSERT INTO `yan_major` VALUES (95, '8', '0824', '学硕-工学-船舶与海洋工程');
INSERT INTO `yan_major` VALUES (96, '8', '0825', '学硕-工学-航空宇航科学与技术');
INSERT INTO `yan_major` VALUES (97, '8', '0826', '学硕-工学-兵器科学与技术');
INSERT INTO `yan_major` VALUES (98, '8', '0827', '学硕-工学-核科学与技术');
INSERT INTO `yan_major` VALUES (99, '8', '0828', '学硕-工学-农业工程');
INSERT INTO `yan_major` VALUES (100, '8', '0829', '学硕-工学-林业工程');
INSERT INTO `yan_major` VALUES (101, '8', '0830', '学硕-工学-环境科学与工程');
INSERT INTO `yan_major` VALUES (102, '8', '0831', '学硕-工学-生物医学工程');
INSERT INTO `yan_major` VALUES (103, '8', '0832', '学硕-工学-食品科学与工程');
INSERT INTO `yan_major` VALUES (104, '8', '0833', '学硕-工学-城乡规划学');
INSERT INTO `yan_major` VALUES (105, '8', '0834', '学硕-工学-风景园林学');
INSERT INTO `yan_major` VALUES (106, '8', '0835', '学硕-工学-软件工程');
INSERT INTO `yan_major` VALUES (107, '8', '0836', '学硕-工学-生物工程');
INSERT INTO `yan_major` VALUES (108, '8', '0837', '学硕-工学-安全科学与工程');
INSERT INTO `yan_major` VALUES (109, '8', '0838', '学硕-工学-公安技术');
INSERT INTO `yan_major` VALUES (110, '8', '0839', '学硕-工学-网络空间安全');
INSERT INTO `yan_major` VALUES (111, '8', '0851', '专硕-工学-建筑学');
INSERT INTO `yan_major` VALUES (112, '8', '0852', '专硕-工学-工程');
INSERT INTO `yan_major` VALUES (113, '8', '0853', '专硕-工学-城市规划');
INSERT INTO `yan_major` VALUES (114, '8', '0870', '学硕-工学-科学技术史');
INSERT INTO `yan_major` VALUES (115, '8', '0871', '学硕-工学-管理科学与工程');
INSERT INTO `yan_major` VALUES (116, '8', '0872', '学硕-工学-设计学');
INSERT INTO `yan_major` VALUES (117, '9', '0901', '学硕-农学-作物学');
INSERT INTO `yan_major` VALUES (118, '9', '0902', '学硕-农学-园艺学');
INSERT INTO `yan_major` VALUES (119, '9', '0903', '学硕-农学-农业资源与环境');
INSERT INTO `yan_major` VALUES (120, '9', '0904', '学硕-农学-植物保护');
INSERT INTO `yan_major` VALUES (121, '9', '0905', '学硕-农学-畜牧学');
INSERT INTO `yan_major` VALUES (122, '9', '0906', '学硕-农学-兽医学');
INSERT INTO `yan_major` VALUES (123, '9', '0907', '学硕-农学-林学');
INSERT INTO `yan_major` VALUES (124, '9', '0908', '学硕-农学-水产');
INSERT INTO `yan_major` VALUES (125, '9', '0909', '学硕-农学-草学');
INSERT INTO `yan_major` VALUES (126, '9', '0951', '专硕-农学-农业');
INSERT INTO `yan_major` VALUES (127, '9', '0952', '专硕-农学-兽医');
INSERT INTO `yan_major` VALUES (128, '9', '0953', '专硕-农学-风景园林');
INSERT INTO `yan_major` VALUES (129, '9', '0954', '专硕-农学-林业');
INSERT INTO `yan_major` VALUES (130, '9', '0970', '学硕-农学-科学技术史');
INSERT INTO `yan_major` VALUES (131, '9', '0971', '学硕-农学-环境科学与工程');
INSERT INTO `yan_major` VALUES (132, '9', '0972', '学硕-农学-食品科学与工程');
INSERT INTO `yan_major` VALUES (133, '9', '0973', '学硕-农学-风景园林学');
INSERT INTO `yan_major` VALUES (134, '10', '1001', '学硕-医学-基础医学');
INSERT INTO `yan_major` VALUES (135, '10', '1002', '学硕-医学-临床医学');
INSERT INTO `yan_major` VALUES (136, '10', '1003', '学硕-医学-口腔医学');
INSERT INTO `yan_major` VALUES (137, '10', '1004', '学硕-医学-公共卫生与预防医学');
INSERT INTO `yan_major` VALUES (138, '10', '1005', '学硕-医学-中医学');
INSERT INTO `yan_major` VALUES (139, '10', '1006', '学硕-医学-中西医结合');
INSERT INTO `yan_major` VALUES (140, '10', '1007', '学硕-医学-药学');
INSERT INTO `yan_major` VALUES (141, '10', '1008', '学硕-医学-中药学');
INSERT INTO `yan_major` VALUES (142, '10', '1009', '学硕-医学-特种医学');
INSERT INTO `yan_major` VALUES (143, '10', '1010', '学硕-医学-医学技术');
INSERT INTO `yan_major` VALUES (144, '10', '1011', '学硕-医学-护理学');
INSERT INTO `yan_major` VALUES (145, '10', '1051', '专硕-医学-临床医学');
INSERT INTO `yan_major` VALUES (146, '10', '1052', '专硕-医学-口腔医学');
INSERT INTO `yan_major` VALUES (147, '10', '1053', '专硕-医学-公共卫生');
INSERT INTO `yan_major` VALUES (148, '10', '1054', '专硕-医学-护理');
INSERT INTO `yan_major` VALUES (149, '10', '1055', '专硕-医学-药学');
INSERT INTO `yan_major` VALUES (150, '10', '1056', '专硕-医学-中药学');
INSERT INTO `yan_major` VALUES (151, '10', '1057', '专硕-医学-中医');
INSERT INTO `yan_major` VALUES (152, '10', '1071', '学硕-医学-科学技术史');
INSERT INTO `yan_major` VALUES (153, '10', '1072', '学硕-医学-生物医学工程');
INSERT INTO `yan_major` VALUES (154, '10', '1073', '学硕-医学-空');
INSERT INTO `yan_major` VALUES (155, '10', '1074', '学硕-医学-社会医学与卫生事业管理');
INSERT INTO `yan_major` VALUES (156, '11', '1101', '学硕-军事学-军事思想及军事历史');
INSERT INTO `yan_major` VALUES (157, '11', '1102', '学硕-军事学-战略学');
INSERT INTO `yan_major` VALUES (158, '11', '1103', '学硕-军事学-战役学');
INSERT INTO `yan_major` VALUES (159, '11', '1104', '学硕-军事学-战术学');
INSERT INTO `yan_major` VALUES (160, '11', '1105', '学硕-军事学-军队指挥学');
INSERT INTO `yan_major` VALUES (161, '11', '1106', '学硕-军事学-军制学');
INSERT INTO `yan_major` VALUES (162, '11', '1107', '学硕-军事学-军队政治工作学');
INSERT INTO `yan_major` VALUES (163, '11', '1108', '学硕-军事学-军事后勤学');
INSERT INTO `yan_major` VALUES (164, '11', '1109', '学硕-军事学-军事装备学');
INSERT INTO `yan_major` VALUES (165, '11', '1110', '学硕-军事学-军事训练学');
INSERT INTO `yan_major` VALUES (166, '11', '1151', '专硕-军事学-军事');
INSERT INTO `yan_major` VALUES (167, '12', '1201', '学硕-管理学-管理科学与工程');
INSERT INTO `yan_major` VALUES (168, '12', '1202', '学硕-管理学-工商管理');
INSERT INTO `yan_major` VALUES (169, '12', '1203', '学硕-管理学-农林经济管理');
INSERT INTO `yan_major` VALUES (170, '12', '1204', '学硕-管理学-公共管理');
INSERT INTO `yan_major` VALUES (171, '12', '1205', '学硕-管理学-图书情报与档案管理');
INSERT INTO `yan_major` VALUES (172, '12', '1251', '专硕-管理学-工商管理');
INSERT INTO `yan_major` VALUES (173, '12', '1252', '专硕-管理学-公共管理');
INSERT INTO `yan_major` VALUES (174, '12', '1253', '专硕-管理学-会计');
INSERT INTO `yan_major` VALUES (175, '12', '1254', '专硕-管理学-旅游管理');
INSERT INTO `yan_major` VALUES (176, '12', '1255', '专硕-管理学-图书情报');
INSERT INTO `yan_major` VALUES (177, '12', '1256', '专硕-管理学-工程管理');
INSERT INTO `yan_major` VALUES (178, '13', '1301', '学硕-艺术学-艺术学理论');
INSERT INTO `yan_major` VALUES (179, '13', '1302', '学硕-艺术学-音乐与舞蹈学');
INSERT INTO `yan_major` VALUES (180, '13', '1303', '学硕-艺术学-戏剧与影视学');
INSERT INTO `yan_major` VALUES (181, '13', '1304', '学硕-艺术学-美术学');
INSERT INTO `yan_major` VALUES (182, '13', '1305', '学硕-艺术学-设计学');
INSERT INTO `yan_major` VALUES (183, '13', '1351', '专硕-艺术学-艺术');

-- ----------------------------
-- Table structure for yan_major_college
-- ----------------------------
DROP TABLE IF EXISTS `yan_major_college`;
CREATE TABLE `yan_major_college`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `major_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `college_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `element_1` int(0) NULL DEFAULT NULL,
  `element_2` int(0) NULL DEFAULT NULL,
  `element_3` int(0) NULL DEFAULT NULL,
  `element_4` int(0) NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `element_5` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_major_college
-- ----------------------------

-- ----------------------------
-- Table structure for yan_major_detail
-- ----------------------------
DROP TABLE IF EXISTS `yan_major_detail`;
CREATE TABLE `yan_major_detail`  (
  `index` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `code1` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`index`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_major_detail
-- ----------------------------

-- ----------------------------
-- Table structure for yan_major_me
-- ----------------------------
DROP TABLE IF EXISTS `yan_major_me`;
CREATE TABLE `yan_major_me`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_major_me
-- ----------------------------
INSERT INTO `yan_major_me` VALUES (1, '法学');
INSERT INTO `yan_major_me` VALUES (2, '会计学');
INSERT INTO `yan_major_me` VALUES (3, '金融学');
INSERT INTO `yan_major_me` VALUES (4, '工商管理');
INSERT INTO `yan_major_me` VALUES (5, '商务英语');
INSERT INTO `yan_major_me` VALUES (6, '心理学');
INSERT INTO `yan_major_me` VALUES (7, '经济学');
INSERT INTO `yan_major_me` VALUES (8, '管理科学与工程');
INSERT INTO `yan_major_me` VALUES (9, '新闻');
INSERT INTO `yan_major_me` VALUES (10, '传播');
INSERT INTO `yan_major_me` VALUES (11, '广告');
INSERT INTO `yan_major_me` VALUES (12, '计算机应用技术');
INSERT INTO `yan_major_me` VALUES (13, '临床医学');
INSERT INTO `yan_major_me` VALUES (14, '机械设计制造及其自动化');

-- ----------------------------
-- Table structure for yan_major_question
-- ----------------------------
DROP TABLE IF EXISTS `yan_major_question`;
CREATE TABLE `yan_major_question`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `major_id` int(0) NULL DEFAULT NULL,
  `question` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_major_question
-- ----------------------------
INSERT INTO `yan_major_question` VALUES (1, 1, '您是否具备法律职业道德，客观公正?/0');
INSERT INTO `yan_major_question` VALUES (2, 1, '你会在跟同学、同事聚餐时，想到餐盘里的食物归谁所有吗?/1');
INSERT INTO `yan_major_question` VALUES (3, 1, '你会在买一根铅笔时，想到这其实是一个买卖合同，并且需要你具有一定的行为能力。更深一步，你会想到你刚才实施了法律行为，甚至是物权行为（铅笔和钱的交付）?/1');
INSERT INTO `yan_major_question` VALUES (4, 1, '邻居家的树的树枝伸到了你家院子，你会想到这产生了一个法律问题，进而往侵权或者相邻关系方面想吗?/1');
INSERT INTO `yan_major_question` VALUES (5, 1, '您理解能力强，逻辑思维好?/0');
INSERT INTO `yan_major_question` VALUES (6, 2, '您是不是一个能沉下心去工作的人?/0');
INSERT INTO `yan_major_question` VALUES (7, 2, '您是否是一个思路清晰，逻辑性较好的人?/0');
INSERT INTO `yan_major_question` VALUES (8, 2, '在必要的沟通上，您不过于内向？能恰当地与税务局，银行沟通?/0');
INSERT INTO `yan_major_question` VALUES (9, 2, '您不过于外向，做事冲动?/0');
INSERT INTO `yan_major_question` VALUES (10, 2, '您是否能抵抗巨大的诱惑?/0');
INSERT INTO `yan_major_question` VALUES (11, 3, '您是否外向，善于与人打交道？是否具备金融营销能力?/0');
INSERT INTO `yan_major_question` VALUES (12, 3, '数学是否特别优秀?/0');
INSERT INTO `yan_major_question` VALUES (13, 3, '您是否有很多资源，包括富有的人脉，富有的财力?/0');
INSERT INTO `yan_major_question` VALUES (14, 3, '您是否接受压力较大的工作?/0');
INSERT INTO `yan_major_question` VALUES (16, 3, '即便您不具备上述条件，但您十分热爱该行业，愿意为此牺牲?/0');
INSERT INTO `yan_major_question` VALUES (18, 4, '您学习兴趣强。关心周围世界的快速变化，比如，互联网、大数据、共享经济等对社会关系、组织结构、消费偏好等方面的改变?/0');
INSERT INTO `yan_major_question` VALUES (19, 4, '您沟通能力强，喜欢与人沟通，能够条理性地分析事物和表达观点?/0');
INSERT INTO `yan_major_question` VALUES (20, 4, '您可以掌握商业分析与沟通表达能力，形成自己的逻辑体系?/0');
INSERT INTO `yan_major_question` VALUES (21, 4, '您有创新意识和创业精神。有较强的成就动机，不墨守成规，喜欢打破旧有的刻板规则?/0');
INSERT INTO `yan_major_question` VALUES (22, 4, '您有较强的主动学习意愿与能力，不浅尝辄止、人云亦云，能够主动规划自己的未来发展?/0');
INSERT INTO `yan_major_question` VALUES (23, 5, '平常是否有阅读英语书的习惯?/0');
INSERT INTO `yan_major_question` VALUES (25, 5, '是否愿意毕业后从事翻译或者编辑?/0');
INSERT INTO `yan_major_question` VALUES (26, 5, '目前的英语水平状况?/1');
INSERT INTO `yan_major_question` VALUES (27, 5, '是否能够有效地、有目的地掌握和消化新学的东西?/0');
INSERT INTO `yan_major_question` VALUES (28, 5, '是否有国外留学的想法?/0');
INSERT INTO `yan_major_question` VALUES (29, 6, '对于心理学的就业前景是否有了解?/0');
INSERT INTO `yan_major_question` VALUES (30, 6, '是否读过充足的心理学书籍?/0');
INSERT INTO `yan_major_question` VALUES (31, 6, '是否喜欢与人交流?/0');
INSERT INTO `yan_major_question` VALUES (32, 6, '是否有考心理咨询师证书的意愿?/0');
INSERT INTO `yan_major_question` VALUES (34, 6, '能否接受就业专业不对口?/0');
INSERT INTO `yan_major_question` VALUES (35, 7, '对自己的英语有自信或者对英语感兴趣吗?/0');
INSERT INTO `yan_major_question` VALUES (36, 7, '人际沟通能力如何?/0');
INSERT INTO `yan_major_question` VALUES (37, 7, '数学能力怎么样?/0');
INSERT INTO `yan_major_question` VALUES (38, 7, '是否接受跨考?/0');
INSERT INTO `yan_major_question` VALUES (39, 7, '能不能及时了解本专业前沿知识?/0');
INSERT INTO `yan_major_question` VALUES (40, 8, '逻辑思维能力强吗?/1');
INSERT INTO `yan_major_question` VALUES (41, 8, '你认为数学在日常生活中有用吗?/0');
INSERT INTO `yan_major_question` VALUES (42, 8, '能否接受工科知识?/0');
INSERT INTO `yan_major_question` VALUES (43, 8, '能够利用计算机解决复杂问题吗?/0');
INSERT INTO `yan_major_question` VALUES (44, 8, '是否经常考虑决策的最优性?/0');
INSERT INTO `yan_major_question` VALUES (45, 9, '是否具有新闻学理论知识背景?/0');
INSERT INTO `yan_major_question` VALUES (46, 9, '对主流新闻观是否有自己的认知?/0');
INSERT INTO `yan_major_question` VALUES (47, 9, '是否了解一名新闻工作者的工作?/0');
INSERT INTO `yan_major_question` VALUES (48, 9, '对新闻的热爱程度?/1');
INSERT INTO `yan_major_question` VALUES (49, 9, '文学素养如何?/1');
INSERT INTO `yan_major_question` VALUES (50, 10, '是否对传播学有基础的认识?/0');
INSERT INTO `yan_major_question` VALUES (51, 10, '对当代主流媒体是否有自己的评价?/0');
INSERT INTO `yan_major_question` VALUES (52, 10, '是否对网络舆论一边倒现状有清楚的认知?/0');
INSERT INTO `yan_major_question` VALUES (53, 10, '是否对传播学基础理论的了解?/0');
INSERT INTO `yan_major_question` VALUES (54, 10, '是否了解传播学的就业前景?/0');
INSERT INTO `yan_major_question` VALUES (55, 11, '对广告历史发展理论的了解程度?/0');
INSERT INTO `yan_major_question` VALUES (56, 11, '是否对当代广告有自己的认知?/0');
INSERT INTO `yan_major_question` VALUES (57, 11, '是否掌握一些设计软件?/0');
INSERT INTO `yan_major_question` VALUES (58, 11, '观看广告时是否能有想法?/0');
INSERT INTO `yan_major_question` VALUES (59, 11, '认为广告重实践还是重理论?/2');
INSERT INTO `yan_major_question` VALUES (60, 12, '对计算机应用技术的就业要求是否有了解?/0');
INSERT INTO `yan_major_question` VALUES (61, 12, '对编程是否有足够的兴趣?/0');
INSERT INTO `yan_major_question` VALUES (62, 12, '逻辑思维如何?/1');
INSERT INTO `yan_major_question` VALUES (63, 12, '是否做过计算机相关的工程或课题?/0');
INSERT INTO `yan_major_question` VALUES (64, 12, '是否有擅长的领域（如前端、后端、运维)?/0');
INSERT INTO `yan_major_question` VALUES (65, 13, '你认为你是一个细致的人吗?/0');
INSERT INTO `yan_major_question` VALUES (66, 13, '能否忍受毕业后长期从事基础工作?/0');
INSERT INTO `yan_major_question` VALUES (67, 13, '对基础药理学的掌握程度?/1');
INSERT INTO `yan_major_question` VALUES (68, 13, '是否拥有医院的实习经历?/0');
INSERT INTO `yan_major_question` VALUES (69, 13, '能否长时间做同一件事?/0');
INSERT INTO `yan_major_question` VALUES (70, 14, '对机械设计制造及其自动化的就业要求是否了解?/0');
INSERT INTO `yan_major_question` VALUES (71, 14, '动手能力强弱?/1');
INSERT INTO `yan_major_question` VALUES (72, 14, '是否拥有充足的物理化学知识?/0');
INSERT INTO `yan_major_question` VALUES (73, 14, '是否参与过工程类项目或实习?/0');
INSERT INTO `yan_major_question` VALUES (74, 14, '是否能接受大量考试?/0');

-- ----------------------------
-- Table structure for yan_message_record
-- ----------------------------
DROP TABLE IF EXISTS `yan_message_record`;
CREATE TABLE `yan_message_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `openid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message_type` tinyint(0) NULL DEFAULT NULL COMMENT '1.文本， 2.图片',
  `content` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_message_record
-- ----------------------------

-- ----------------------------
-- Table structure for yan_question
-- ----------------------------
DROP TABLE IF EXISTS `yan_question`;
CREATE TABLE `yan_question`  (
  `question_id` int(0) NOT NULL AUTO_INCREMENT,
  `openid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户id',
  `que_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主标题',
  `que_content` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问题副标题',
  `que_tag_id` int(0) NULL DEFAULT NULL COMMENT '标签的id',
  `que_view` int(0) NULL DEFAULT NULL COMMENT '问题阅读数量',
  `que_collect` int(0) NULL DEFAULT NULL COMMENT '问题收藏数量',
  `feed_source_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '问题的展示图片',
  `que_comment` int(0) NULL DEFAULT NULL,
  `que_answer_list_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_question
-- ----------------------------
INSERT INTO `yan_question` VALUES (1, '56C02DF0516B4B079ABFCEC08169E577', '2020年预计硕士生扩招18.9万，这一数字意味着什么，应该如何 把握机遇', ' WEB前端*不靠谱天气预报员*', NULL, NULL, 100, 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg', NULL, NULL);
INSERT INTO `yan_question` VALUES (2, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '有哪些考研需要避开的坑', '有哪些考研需要避开的坑 ', 1, 332, 120, 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg', 121, NULL);
INSERT INTO `yan_question` VALUES (3, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '气象铁塔的辐射大吗？', '气象铁塔的辐射大吗？', NULL, NULL, 321, 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg', NULL, NULL);
INSERT INTO `yan_question` VALUES (8, NULL, '测试后端数据是否录入', '测试', NULL, NULL, 15, NULL, NULL, NULL);
INSERT INTO `yan_question` VALUES (9, NULL, '测试后端2', '测试2', NULL, NULL, 1651, NULL, NULL, NULL);
INSERT INTO `yan_question` VALUES (10, NULL, '测试后端2', '测试2', NULL, NULL, 26, NULL, NULL, NULL);
INSERT INTO `yan_question` VALUES (11, NULL, '994测试 Tiny编辑器是否有用', '开始测试', NULL, NULL, 23, NULL, NULL, NULL);
INSERT INTO `yan_question` VALUES (12, NULL, '994测试 Tiny编辑器是否有用', '开始测试', NULL, NULL, 23, NULL, NULL, NULL);
INSERT INTO `yan_question` VALUES (13, NULL, '994测试 Tiny编辑器是否有用', '开始测试', NULL, NULL, 65, NULL, NULL, NULL);
INSERT INTO `yan_question` VALUES (14, NULL, '测试是否能够把answerid给传入进入', '测试是否能够把answerid给传入进入', NULL, NULL, 615, NULL, NULL, NULL);
INSERT INTO `yan_question` VALUES (15, NULL, '测试是否能够把answerid给传入进入', 'answerid给', NULL, NULL, 152, NULL, NULL, NULL);
INSERT INTO `yan_question` VALUES (16, NULL, '测试封面功能', '测试封面功能', NULL, NULL, 12, NULL, NULL, NULL);
INSERT INTO `yan_question` VALUES (17, NULL, '测试封面的数据应该怎么处理', '处理封面', NULL, NULL, 315, NULL, NULL, NULL);
INSERT INTO `yan_question` VALUES (18, NULL, '测试缩略图有没有效果', '测试缩略图有没有效果', NULL, NULL, 51, NULL, NULL, NULL);
INSERT INTO `yan_question` VALUES (20, '1879878-NKCNO-NKNK', '请求的第一个问题', 'tets', NULL, NULL, NULL, 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2021/07/31/43744bc6d8044d58bf6ea3027464248bChMkJ1ZdCsGIbabrAAOrwsWw18EAAFjoQAh9EgAA6va022.jpg', NULL, NULL);

-- ----------------------------
-- Table structure for yan_question_tag
-- ----------------------------
DROP TABLE IF EXISTS `yan_question_tag`;
CREATE TABLE `yan_question_tag`  (
  `tag_id` int(0) NOT NULL AUTO_INCREMENT,
  `que_tag` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_question_tag
-- ----------------------------
INSERT INTO `yan_question_tag` VALUES (1, '阅读');
INSERT INTO `yan_question_tag` VALUES (2, '电子书');
INSERT INTO `yan_question_tag` VALUES (3, '考研政治');
INSERT INTO `yan_question_tag` VALUES (4, '考研数学');
INSERT INTO `yan_question_tag` VALUES (5, '考研计算机');

-- ----------------------------
-- Table structure for yan_question_tags
-- ----------------------------
DROP TABLE IF EXISTS `yan_question_tags`;
CREATE TABLE `yan_question_tags`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `que_id` int(0) NULL DEFAULT NULL,
  `tag_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_question_tags
-- ----------------------------
INSERT INTO `yan_question_tags` VALUES (1, 20, 5);

-- ----------------------------
-- Table structure for yan_user
-- ----------------------------
DROP TABLE IF EXISTS `yan_user`;
CREATE TABLE `yan_user`  (
  `openid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户表id',
  `chat_id` int(0) NULL DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar_url` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `level_id` bigint(0) NULL DEFAULT NULL COMMENT '会员等级id',
  `is_alive` int(0) NULL DEFAULT NULL COMMENT '是否登录',
  `integration` int(0) NULL DEFAULT NULL COMMENT '积分',
  `growth` int(0) NULL DEFAULT NULL COMMENT '成长值',
  `college` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '我心怡的大学',
  `major` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '我的专业',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`openid`) USING BTREE,
  UNIQUE INDEX `user_name_unique`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_user
-- ----------------------------
INSERT INTO `yan_user` VALUES ('1879878-NKCNO-NKNK', 555, '我是rose', '123', 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2021/07/26/81ec9f7958e14c3ba9225b8bd359ea6asrc=http___imagev2.xmcdn.com_group73_M05_30_05_wKgO216cR1OSZxXWAAq2Sh42txU217.png!strip=1&quality=7&magick=jpg&op_type=5&upload_type=album&name=mobile_large&device_type=ios&refer=http___imagev2.xmcdn.jpg', 'rose', NULL, 1, NULL, NULL, '笔趣阁', '斗破苍穹', '2020-10-01 16:19:47', '2022-05-15 07:41:40');
INSERT INTO `yan_user` VALUES ('56C02DF0516B4B079ABFCEC08169E577', 666, '我不是123', '123', 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2021/07/26/a10016be5a4348a885bc79bb7bf78ad4logo1.png', '123', NULL, 1, NULL, NULL, '山西财经大学', '管理科学', '2021-05-27 09:35:30', '2022-05-15 06:50:43');
INSERT INTO `yan_user` VALUES ('B9C6A3A357534C7F9DD4C2F22FA42AD5', 777, '管理员', '123', 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2021/07/26/0ea48cb529e54573ae791ffbde3fcd9fu=2095913610,1514262792&fm=26&gp=0.jpg', 'admin', NULL, 1, NULL, NULL, '山西大学', '管理', '2021-07-23 16:51:28', '2022-02-24 05:20:14');
INSERT INTO `yan_user` VALUES ('BEE8E17A7A794A979211DE3C4590824E', 888, '机器人', '123', 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2021/07/26/8a30971eaea243fdbf727d11f67b3f3d下载 (1).jpg', 'admin2', NULL, 1, NULL, NULL, '山西大学', '机器人', '2021-07-22 21:54:01', '2022-02-23 13:26:06');
INSERT INTO `yan_user` VALUES ('obba-4kQv-DSbg2ZZm_liCEorcqk', 999, '浅笑  风尘', 'jack', 'https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eric2ZvHFMLWSGwkspibURzL12HHicSAx09XhC5BJx1LQEXR7icIB3usUEkIgtKSbibvMjwtFNWWGuK28Q/132', 'jack', NULL, NULL, NULL, NULL, NULL, NULL, '2020-10-01 20:53:15', '2020-10-09 22:23:52');
INSERT INTO `yan_user` VALUES ('test', 444, '治愈云', 'tom', 'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2021/07/26/54854bde796a455aa81ddafb026e1e97src=http___c-ssl.duitang.com_uploads_item_201509_20_20150920130412_YhnLR.jpeg&refer=http___c-ssl.duitang.jpg', 'tom', NULL, NULL, NULL, NULL, '太原理工大学', '动漫设计', '2021-07-22 20:06:59', '2021-07-26 09:41:06');
INSERT INTO `yan_user` VALUES ('test3', 333, '12312', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for yan_user_chat
-- ----------------------------
DROP TABLE IF EXISTS `yan_user_chat`;
CREATE TABLE `yan_user_chat`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '聊天id',
  `user_openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '聊天者自己的id',
  `to_openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '别人id',
  `content` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '聊天内容',
  `is_read` int(0) NULL DEFAULT NULL COMMENT '对方是否接受到',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_user_chat
-- ----------------------------
INSERT INTO `yan_user_chat` VALUES (1, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '111', 1, '2021-07-26 04:48:12');
INSERT INTO `yan_user_chat` VALUES (2, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '你好呀 rose', 1, '2021-07-26 04:49:08');
INSERT INTO `yan_user_chat` VALUES (3, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '我很好。nice', 1, '2021-07-26 04:49:31');
INSERT INTO `yan_user_chat` VALUES (4, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '好的呢', 1, '2021-07-26 04:49:40');
INSERT INTO `yan_user_chat` VALUES (5, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', 'okk', 1, '2021-07-26 04:49:47');
INSERT INTO `yan_user_chat` VALUES (6, '1879878-NKCNO-NKNK', 'BEE8E17A7A794A979211DE3C4590824E', '156161', 1, '2021-07-30 15:16:06');
INSERT INTO `yan_user_chat` VALUES (7, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '156161', 1, '2021-07-30 15:25:07');
INSERT INTO `yan_user_chat` VALUES (8, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '你在干嘛', 1, '2021-07-31 15:34:25');
INSERT INTO `yan_user_chat` VALUES (9, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '我好想掉线了', 1, '2021-07-31 15:34:43');
INSERT INTO `yan_user_chat` VALUES (10, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '这也太难过了', 1, '2021-07-31 15:35:50');
INSERT INTO `yan_user_chat` VALUES (11, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '突然发现不同浏览器对flex兼容性不一样', 1, '2021-07-31 15:43:44');
INSERT INTO `yan_user_chat` VALUES (12, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '这回看起来连接起来了', 1, '2021-07-31 15:49:20');
INSERT INTO `yan_user_chat` VALUES (13, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '太难过了', 1, '2021-07-31 15:51:52');
INSERT INTO `yan_user_chat` VALUES (14, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '1111', 1, '2021-07-31 15:52:58');
INSERT INTO `yan_user_chat` VALUES (15, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', 'aaa', 1, '2021-07-31 16:10:41');
INSERT INTO `yan_user_chat` VALUES (16, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '测试测试', 1, '2021-07-31 16:14:48');
INSERT INTO `yan_user_chat` VALUES (17, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '原来是封装的结果的锅原来入场', 1, '2021-07-31 16:15:10');
INSERT INTO `yan_user_chat` VALUES (18, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '看看在不在线', 1, '2021-07-31 16:17:08');
INSERT INTO `yan_user_chat` VALUES (19, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '没点开', 1, '2021-07-31 16:17:16');
INSERT INTO `yan_user_chat` VALUES (20, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '看来在线', 1, '2021-07-31 16:17:25');
INSERT INTO `yan_user_chat` VALUES (21, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '这也行吧', 1, '2021-07-31 16:17:35');
INSERT INTO `yan_user_chat` VALUES (22, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '一个一个推送', 1, '2021-07-31 16:17:56');
INSERT INTO `yan_user_chat` VALUES (23, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '给rose发送看看rose有没有小红点', 1, '2021-08-01 05:49:30');
INSERT INTO `yan_user_chat` VALUES (24, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '再发一条', 1, '2021-08-01 05:50:19');
INSERT INTO `yan_user_chat` VALUES (25, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '收到了', 1, '2021-08-01 05:59:16');
INSERT INTO `yan_user_chat` VALUES (26, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '测试小红点', 1, '2021-08-01 06:08:35');
INSERT INTO `yan_user_chat` VALUES (27, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '测试', 1, '2021-08-26 11:36:59');
INSERT INTO `yan_user_chat` VALUES (28, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '好的', 1, '2021-08-26 11:37:12');
INSERT INTO `yan_user_chat` VALUES (29, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '好像真的被我改坏了', 1, '2021-08-26 11:37:51');
INSERT INTO `yan_user_chat` VALUES (30, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '测试发一条', 1, '2021-08-26 11:42:12');
INSERT INTO `yan_user_chat` VALUES (31, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '进行测试', 1, '2021-08-26 11:46:11');
INSERT INTO `yan_user_chat` VALUES (32, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '刚刚没进来', 1, '2021-08-26 11:46:24');
INSERT INTO `yan_user_chat` VALUES (33, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '好用了？', 1, '2021-08-26 11:47:25');
INSERT INTO `yan_user_chat` VALUES (34, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '11', 1, '2021-08-26 11:47:33');
INSERT INTO `yan_user_chat` VALUES (35, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '11', 1, '2021-08-26 11:48:11');
INSERT INTO `yan_user_chat` VALUES (36, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '22', 1, '2021-08-26 11:50:11');
INSERT INTO `yan_user_chat` VALUES (37, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '为什么头像没了呢', 1, '2021-08-26 12:07:41');
INSERT INTO `yan_user_chat` VALUES (38, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '121321', 1, '2021-08-26 12:10:04');
INSERT INTO `yan_user_chat` VALUES (39, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '11', 1, '2021-08-26 12:12:55');
INSERT INTO `yan_user_chat` VALUES (40, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '12', 1, '2021-08-26 12:13:09');
INSERT INTO `yan_user_chat` VALUES (41, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '？？？', 1, '2021-08-26 12:13:29');
INSERT INTO `yan_user_chat` VALUES (42, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '111', 1, '2021-08-26 12:13:44');
INSERT INTO `yan_user_chat` VALUES (43, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '121', 1, '2021-08-26 12:14:56');
INSERT INTO `yan_user_chat` VALUES (44, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1232', 1, '2021-08-26 12:15:08');
INSERT INTO `yan_user_chat` VALUES (45, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '122121', 1, '2021-08-26 12:21:14');
INSERT INTO `yan_user_chat` VALUES (46, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '123232', 1, '2021-08-26 12:43:26');
INSERT INTO `yan_user_chat` VALUES (47, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '132', 1, '2021-08-26 12:44:34');
INSERT INTO `yan_user_chat` VALUES (48, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '123', 1, '2021-08-26 12:50:07');
INSERT INTO `yan_user_chat` VALUES (49, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '12312', 1, '2021-08-26 12:50:31');
INSERT INTO `yan_user_chat` VALUES (50, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '外送a', 1, '2021-08-26 12:51:10');
INSERT INTO `yan_user_chat` VALUES (51, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '吖吖', 1, '2021-08-26 12:52:50');
INSERT INTO `yan_user_chat` VALUES (52, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '159', 1, '2021-08-26 12:55:50');
INSERT INTO `yan_user_chat` VALUES (53, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '球球', 1, '2021-08-26 12:57:09');
INSERT INTO `yan_user_chat` VALUES (54, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 'qq', 1, '2021-08-26 12:57:25');
INSERT INTO `yan_user_chat` VALUES (55, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 'bb', 1, '2021-08-26 12:58:20');
INSERT INTO `yan_user_chat` VALUES (56, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 'ww', 1, '2021-08-26 13:01:44');
INSERT INTO `yan_user_chat` VALUES (57, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', 'aa', 1, '2021-08-26 13:01:51');
INSERT INTO `yan_user_chat` VALUES (58, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', 'qqq', 1, '2021-08-26 13:02:04');
INSERT INTO `yan_user_chat` VALUES (59, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', 'eee', 1, '2021-08-26 13:04:07');
INSERT INTO `yan_user_chat` VALUES (60, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '111', 1, '2021-08-26 13:04:28');
INSERT INTO `yan_user_chat` VALUES (61, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '带的', 1, '2021-08-26 13:06:00');
INSERT INTO `yan_user_chat` VALUES (62, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '222', 1, '2021-08-26 13:06:41');
INSERT INTO `yan_user_chat` VALUES (63, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '123123', 1, '2021-08-26 13:08:53');
INSERT INTO `yan_user_chat` VALUES (64, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '终于成了', 1, '2021-08-26 13:09:06');
INSERT INTO `yan_user_chat` VALUES (65, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '这是为啥', 1, '2021-08-26 13:09:28');
INSERT INTO `yan_user_chat` VALUES (66, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '一边行一边不行', 1, '2021-08-26 13:09:49');
INSERT INTO `yan_user_chat` VALUES (67, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '有点慢呐', 1, '2021-08-26 13:10:10');
INSERT INTO `yan_user_chat` VALUES (68, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '分不清楚', 1, '2021-08-26 13:10:44');
INSERT INTO `yan_user_chat` VALUES (69, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '111', 1, '2021-08-26 13:10:49');
INSERT INTO `yan_user_chat` VALUES (70, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '严重怀疑是没刷吧', 1, '2021-08-26 13:11:10');
INSERT INTO `yan_user_chat` VALUES (71, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '12312', 1, '2021-08-26 13:11:15');
INSERT INTO `yan_user_chat` VALUES (72, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '现在数据都过来了', 1, '2021-08-26 13:11:25');
INSERT INTO `yan_user_chat` VALUES (73, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '没有问题', 1, '2021-08-26 13:11:31');
INSERT INTO `yan_user_chat` VALUES (74, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '12312', 1, '2021-09-01 14:13:59');
INSERT INTO `yan_user_chat` VALUES (75, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '13', 1, '2021-09-01 14:15:05');
INSERT INTO `yan_user_chat` VALUES (76, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 'aa', 1, '2021-09-01 14:15:12');
INSERT INTO `yan_user_chat` VALUES (77, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '测试消息', 1, '2022-02-15 18:20:13');
INSERT INTO `yan_user_chat` VALUES (78, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '收到收到', 1, '2022-02-15 18:20:33');
INSERT INTO `yan_user_chat` VALUES (79, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '测试2', 1, '2022-02-15 18:21:21');
INSERT INTO `yan_user_chat` VALUES (80, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '2.24', 1, '2022-02-15 18:24:18');
INSERT INTO `yan_user_chat` VALUES (81, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '2:27', 1, '2022-02-15 18:27:19');
INSERT INTO `yan_user_chat` VALUES (82, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '111', 1, '2022-02-15 18:27:25');
INSERT INTO `yan_user_chat` VALUES (83, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '', 1, '2022-02-16 13:02:18');
INSERT INTO `yan_user_chat` VALUES (84, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '', 1, '2022-02-16 13:02:25');
INSERT INTO `yan_user_chat` VALUES (85, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '', 1, '2022-02-16 13:02:31');
INSERT INTO `yan_user_chat` VALUES (86, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 'aaa', 1, '2022-02-16 13:04:03');
INSERT INTO `yan_user_chat` VALUES (87, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', 'bbb', 1, '2022-02-16 13:04:09');
INSERT INTO `yan_user_chat` VALUES (88, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 'ccc', 1, '2022-02-16 13:04:14');
INSERT INTO `yan_user_chat` VALUES (89, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', 'ddd', 1, '2022-02-16 13:50:44');
INSERT INTO `yan_user_chat` VALUES (90, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 'eee', 1, '2022-02-16 13:50:49');
INSERT INTO `yan_user_chat` VALUES (91, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', 'fff', 1, '2022-02-16 13:50:54');
INSERT INTO `yan_user_chat` VALUES (92, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 'okok', 1, '2022-02-16 13:51:00');
INSERT INTO `yan_user_chat` VALUES (93, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', 'mq测试', 1, '2022-02-23 10:48:33');
INSERT INTO `yan_user_chat` VALUES (94, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '试试', 1, '2022-02-23 12:24:05');
INSERT INTO `yan_user_chat` VALUES (95, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '还真有', 1, '2022-02-23 12:24:23');
INSERT INTO `yan_user_chat` VALUES (96, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '再试试', 1, '2022-02-23 12:37:56');
INSERT INTO `yan_user_chat` VALUES (97, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '11', 1, '2022-02-23 12:40:28');
INSERT INTO `yan_user_chat` VALUES (98, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '必须进入了才有', 1, '2022-02-23 12:40:36');
INSERT INTO `yan_user_chat` VALUES (99, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '就很离谱', 1, '2022-02-23 12:40:43');
INSERT INTO `yan_user_chat` VALUES (100, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '消息分不清左右了', 1, '2022-02-23 12:40:58');
INSERT INTO `yan_user_chat` VALUES (101, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '11', 1, '2022-02-23 12:50:27');
INSERT INTO `yan_user_chat` VALUES (102, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '22', 1, '2022-02-23 12:50:45');
INSERT INTO `yan_user_chat` VALUES (103, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '33', 1, '2022-02-23 12:51:22');
INSERT INTO `yan_user_chat` VALUES (104, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '44', 1, '2022-02-23 12:51:28');
INSERT INTO `yan_user_chat` VALUES (105, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '发送给admin', 1, '2022-02-23 13:04:04');
INSERT INTO `yan_user_chat` VALUES (106, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '发送给admin', 1, '2022-02-23 13:04:05');
INSERT INTO `yan_user_chat` VALUES (107, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '给123用户', 1, '2022-02-23 13:06:25');
INSERT INTO `yan_user_chat` VALUES (108, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '给123用户再次发送消息', 1, '2022-02-23 13:13:26');
INSERT INTO `yan_user_chat` VALUES (109, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '123', 1, '2022-02-23 13:21:18');
INSERT INTO `yan_user_chat` VALUES (110, '1879878-NKCNO-NKNK', 'BEE8E17A7A794A979211DE3C4590824E', '给admin2 发消息', 1, '2022-02-23 13:24:22');
INSERT INTO `yan_user_chat` VALUES (111, 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '1879878-NKCNO-NKNK', '123456', 1, '2022-02-23 13:25:08');
INSERT INTO `yan_user_chat` VALUES (112, '1879878-NKCNO-NKNK', 'B9C6A3A357534C7F9DD4C2F22FA42AD5', '基本同步应该', 1, '2022-02-23 13:25:21');
INSERT INTO `yan_user_chat` VALUES (113, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '发送消息', 1, '2022-05-15 06:51:05');
INSERT INTO `yan_user_chat` VALUES (114, '56C02DF0516B4B079ABFCEC08169E577', '1879878-NKCNO-NKNK', '收到了', 1, '2022-05-15 06:51:20');
INSERT INTO `yan_user_chat` VALUES (115, '1879878-NKCNO-NKNK', '56C02DF0516B4B079ABFCEC08169E577', '可以可以', 1, '2022-05-15 06:51:28');

-- ----------------------------
-- Table structure for yan_user_level
-- ----------------------------
DROP TABLE IF EXISTS `yan_user_level`;
CREATE TABLE `yan_user_level`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '等级名称',
  `growth_point` int(0) NULL DEFAULT NULL COMMENT '等级需要的成长值',
  `free_freight` tinyint(0) NULL DEFAULT NULL COMMENT '是否免邮',
  `discount` int(0) NULL DEFAULT NULL COMMENT '打几折',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有生日特权',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_user_level
-- ----------------------------

-- ----------------------------
-- Table structure for yan_user_like
-- ----------------------------
DROP TABLE IF EXISTS `yan_user_like`;
CREATE TABLE `yan_user_like`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_like` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关注者openid',
  `user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '被关注者openid',
  `is_together` tinyint(0) NULL DEFAULT NULL COMMENT '是否互相关注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yan_user_like
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
