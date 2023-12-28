-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: yan
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `schedule_job`
--

DROP TABLE IF EXISTS `schedule_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_job` (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_job`
--

LOCK TABLES `schedule_job` WRITE;
/*!40000 ALTER TABLE `schedule_job` DISABLE KEYS */;
INSERT INTO `schedule_job` VALUES (1,'testTask','renren','0 0/30 * * * ?',0,'参数测试','2021-08-19 23:54:51');
/*!40000 ALTER TABLE `schedule_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_job_log`
--

DROP TABLE IF EXISTS `schedule_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时任务日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_job_log`
--

LOCK TABLES `schedule_job_log` WRITE;
/*!40000 ALTER TABLE `schedule_job_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_captcha`
--

DROP TABLE IF EXISTS `sys_captcha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统验证码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_captcha`
--

LOCK TABLES `sys_captcha` WRITE;
/*!40000 ALTER TABLE `sys_captcha` DISABLE KEYS */;
INSERT INTO `sys_captcha` VALUES ('02140836-8fe4-454e-8231-cdae9fb4e13a','fnnda','2022-10-07 10:52:20'),('07f846f4-bdb3-42eb-86e7-8c3c4ff88f25','n48ye','2023-01-29 17:29:11'),('0d6cea05-92f8-43c6-8315-9484d5f7c987','cgc26','2023-01-23 16:52:54'),('0f633802-12eb-422f-8bc6-05100667b3f4','a745n','2023-01-22 20:44:23'),('109cae73-54e6-487a-84a2-a6db07bafb70','6ade5','2023-10-27 14:38:35'),('13a97eba-042c-4587-8bf0-dc9aa9471f5e','ndy3g','2023-01-29 17:45:31'),('1abec66c-dd67-4437-8415-edcc4beb3b9a','gafdd','2023-06-05 21:41:40'),('1c9639e3-13be-49c4-8407-c9b281b64f30','8peap','2023-11-04 19:47:35'),('1f43b9f9-fb4b-4e2b-8067-94f40527b9aa','b7cma','2023-01-22 19:40:23'),('209780a4-509f-4dbd-8e9d-25343e016294','ndmbc','2023-01-29 17:44:31'),('2225360e-a314-4601-8e62-f82c493b1f82','ndxe2','2023-06-08 00:00:43'),('23d7ef0b-1f84-4096-866f-f582137118cf','dm464','2022-11-07 22:22:50'),('2437cd99-9a3c-43b5-81aa-711f0e85425f','m663a','2023-07-12 12:30:50'),('261ee1df-b931-422a-855d-8d8771b96616','wm3nb','2022-11-06 18:39:04'),('2882795f-8fd9-4559-894e-186b7c7a459f','m8g5p','2022-11-21 15:30:47'),('29ce2a5e-ab37-4af2-8270-96e94d767d0e','wwncw','2022-05-23 12:22:46'),('2cbeb170-e09a-4152-8d3a-a4c0a1acbd85','g3fp7','2023-06-08 00:00:55'),('3227573d-74da-4066-8ac2-8913c9751e64','nne4d','2022-02-24 13:35:00'),('33621b38-f109-4399-8ff0-9ad76670b924','223ay','2022-11-06 16:31:12'),('346eae46-ef10-40fb-8a2b-9b653011cf6c','f4ybx','2022-11-08 21:19:58'),('36d90483-4245-4111-8c1a-6acdcaf0c4af','427ep','2023-01-22 19:32:50'),('38e31fcf-0b25-435a-8b1f-cf8d6c515547','mme5e','2023-01-22 19:39:13'),('3a3bc601-fbba-4e6a-809b-0afcca69bea2','75x85','2023-01-29 17:10:43'),('3ccbb720-1ab5-49ce-8ae0-d5a0ab1ed439','22mcg','2022-11-06 18:50:12'),('3e5162f6-c7ac-4105-8016-d38ddee16f7b','5gn8n','2023-01-22 19:38:19'),('4135d119-c56f-441c-82d6-06d289dac7e4','p4gan','2022-11-20 15:38:41'),('4355f1cd-a149-4556-87a5-add36d3473fd','2yyym','2022-11-05 17:32:42'),('43645191-3de2-4021-831e-f582130e79b0','dmg3m','2023-02-17 10:47:53'),('4411a870-e8d3-4d65-8956-fd5c94d1865f','dg8yc','2023-01-29 17:12:16'),('466fec01-0aed-44d9-8668-9e93ccfbbd92','pn25n','2023-01-22 19:39:05'),('46b39b9e-acac-4c6b-8485-b48fa7dad7a2','nmncg','2023-06-20 21:25:21'),('47791e1e-b262-4ff5-8171-8efa73225600','7pxma','2023-01-22 19:40:05'),('4e60cd20-6570-4819-8a9f-8ab17deefb38','pnn5n','2023-01-22 20:08:34'),('4fc7c760-f06b-4792-8f03-8799c534341d','4w42m','2023-01-22 20:47:54'),('54ea0e0d-7742-4b6b-8b67-cda33195e840','dn7yn','2022-11-05 19:27:47'),('5a8b5d56-a57a-4c31-85b2-378f989d568b','8fw27','2023-01-22 19:39:33'),('5b05868e-f626-43a3-8c55-1d4d0a662857','b5g75','2022-11-05 19:27:57'),('5cd8b8be-e963-497c-8291-62e7cbd85613','mc572','2023-07-22 00:10:33'),('5f28968f-b4a3-4abe-8020-32f510401a30','3d2c5','2023-07-04 00:57:52'),('6167e724-12c1-430e-8f86-f73f2e9a6f23','bn5ag','2023-07-22 00:02:51'),('642aa45b-fe5c-4090-8f3b-aea16b47e0cd','yn355','2023-01-22 16:18:50'),('668e5107-d179-4b5d-8d59-9868d19a78f5','7fcma','2023-06-08 00:00:29'),('6de74b30-a15a-43f5-8320-476c738d4276','ya463','2022-11-06 16:33:27'),('6dfab562-48a3-43b0-8878-7a77f2cc680c','7fd67','2023-07-22 00:08:50'),('6e4f6297-dd22-4390-807a-6c8888f26887','bpgdb','2023-01-29 16:15:33'),('706267c5-bf8c-425c-8910-f302f2a4c3ef','nydm6','2023-01-29 17:10:05'),('74ecf90f-9949-47c9-8a04-abb145b7d951','74pcn','2023-07-12 12:39:41'),('7508c47f-4545-4ea8-827a-3dccbbf6b7e1','63fm3','2023-07-22 00:10:15'),('75d1eb93-78ca-46cc-828d-f692c321a9c5','pxc88','2022-11-05 17:32:30'),('794612ac-d9f5-4d54-8e27-2ee0fde3b73e','55mxn','2023-01-29 17:22:39'),('7bf75a78-c3cc-468a-817a-2703e4711c89','5npn4','2022-12-25 16:38:18'),('7d9dffb4-567e-4bd6-8e6b-7ceffb32aab5','mcfw7','2022-06-01 18:54:29'),('830eadc6-9292-4a43-898c-9c818bac7d78','ye2n2','2023-01-31 18:44:19'),('89506506-bc7f-44af-8ca6-80ec15f45ee0','4yfnn','2023-07-04 01:30:05'),('90dfea60-c5c3-42fe-83df-cd7400d35ef9','f4n6f','2022-12-25 16:38:06'),('92cf6f86-9a72-42c1-8ca8-07dab1c8cff8','a6paf','2022-05-28 16:58:18'),('931c5ba9-538b-463d-841b-933ae92db859','x46pn','2023-07-12 12:31:06'),('94ba1494-0187-4dc9-87e5-48d2b36c71f6','3ma67','2022-11-21 15:30:53'),('965cbd1e-ebdf-445d-8f9b-7d2ab955e949','f2dnn','2022-11-03 19:31:20'),('97fcfd5c-a9a5-48d3-81d6-e202350d782d','5benx','2023-01-22 20:44:30'),('98683b9b-9089-4f7e-88a2-a411282549ea','bg2ay','2023-01-22 19:40:57'),('9aafd64b-8a01-4d9b-83aa-9c20927d47b3','xdand','2022-06-02 18:03:15'),('9b00d629-aa41-4c32-899f-a9f843987ed6','2nbmx','2022-11-20 15:39:03'),('9b1bec57-5e60-4197-86af-52ebf3b01e31','3fym8','2023-06-20 21:25:26'),('a1b28746-5c8d-423e-8a28-aaa90bb4ab9f','pf4yc','2023-01-29 17:23:39'),('a831e440-d84c-40be-812f-c89fb3c373e0','8c8a4','2023-01-29 15:11:18'),('ae820cd8-d364-411c-8c5b-31d824cc2d42','7n5gg','2023-01-22 20:08:21'),('b049f775-8f94-401a-81a0-14ebd9ac1f52','p73f6','2022-11-14 19:03:07'),('b33682ca-68be-4709-8472-d836d301e878','7mpmm','2023-02-17 10:46:10'),('b8e23db8-b0e7-4bc3-8294-799e4f67819f','b84pf','2022-11-06 16:31:14'),('bcf7708b-a6f3-4046-87a0-5c3bca1063da','cmy5x','2023-07-13 20:57:36'),('c1aa36b2-8bfe-4b7f-8f1b-9edd6ede4c67','pgm4x','2023-01-29 17:15:47'),('c207c993-6e5c-40e1-8b49-40d4dc00ad64','828wd','2023-01-29 17:15:09'),('c3d85175-7c0d-4026-82ee-f1bf3bfac13e','6mfbf','2022-11-19 13:41:41'),('c6cefcea-9d00-4f7e-8832-92ea5cd53ada','cc8cg','2022-11-19 13:41:49'),('ca115468-3bc9-442b-8d34-7040baf4f6ea','yp46d','2023-01-29 16:07:10'),('ce4d97f7-c17a-40fa-86b3-381569fc7bb8','dpbn4','2023-07-12 12:31:04'),('cf6bc607-0161-4b47-87c3-dde8c0d66ef9','pagxg','2022-11-21 11:19:38'),('d134dbc4-7bb4-4a1b-8f86-5cbd6f9da3dd','mn43d','2022-11-19 13:41:47'),('d333e023-92e9-448f-8666-0544c2df2e36','5p2x6','2022-02-15 22:12:56'),('d7417c75-2d32-4695-8323-4a8d95f60d38','dpm8n','2022-11-06 15:08:02'),('d8885cb8-a9d0-40ed-8753-70095ea8562f','nn57f','2022-06-01 19:05:56'),('d8d29bbd-4924-4dcb-8a52-8d35e89cde2f','nxm63','2023-01-31 18:35:33'),('de42c47f-c388-4b63-8a4d-40d12be7441b','nxnnx','2022-11-13 16:10:39'),('e14cf7ec-7385-4376-8d55-4495689b1776','c753b','2022-11-06 18:40:34'),('e3a09985-a930-42c6-83ae-cdc257e9bc07','4c48n','2023-01-29 17:13:01'),('e87a976b-9a18-4aa9-89fd-102ffaca6c2f','m3nfc','2023-07-12 12:31:02'),('ee325c09-be9a-4be1-8581-ec866d15e135','m7gff','2023-06-08 00:01:01'),('efc1f81f-7ade-444a-81c6-141a680f98df','c46y4','2023-01-29 16:26:36'),('fbc03942-fea2-4430-895b-4288ebea9b93','cga7n','2023-06-20 21:25:25'),('fe0ee73f-ba99-4bc7-889c-960f24cfe382','cb75p','2022-11-13 16:11:07'),('ff69847d-f103-4e3e-8902-24859abf71be','82mff','2023-07-22 00:07:24');
/*!40000 ALTER TABLE `sys_captcha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `pid` bigint DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统配置信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'CLOUD_STORAGE_CONFIG_KEY','{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}',0,'云存储配置信息',NULL),(2,'研战到底模块','yan_app',1,'区分模块',0),(3,'后台管理模块','admin',1,'后台管理',0),(4,'后台微服务配置','micro_config',1,'后台配置',NULL),(5,'gateway','88',1,'网关微服务端口',4);
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (1,'admin','删除菜单','io.renren.modules.sys.controller.SysMenuController.delete()','[6]',0,'0:0:0:0:0:0:0:1','2021-08-26 21:56:23'),(2,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":72,\"parentId\":0,\"name\":\"首页配置\",\"url\":\"/index\",\"type\":1,\"icon\":\"shoucang\",\"orderNum\":0}]',10,'0:0:0:0:0:0:0:1','2022-05-22 18:34:09'),(3,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":31,\"parentId\":0,\"name\":\"研战到底\",\"type\":0,\"icon\":\"system\",\"orderNum\":2}]',9,'0:0:0:0:0:0:0:1','2022-05-22 18:34:33'),(4,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"system\",\"orderNum\":4}]',7,'0:0:0:0:0:0:0:1','2022-05-22 18:34:41'),(5,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":72,\"parentId\":0,\"name\":\"首页配置\",\"url\":\"/index\",\"type\":0,\"icon\":\"shoucang\",\"orderNum\":0}]',7,'0:0:0:0:0:0:0:1','2022-05-22 18:35:00'),(6,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":37,\"parentId\":72,\"name\":\"热榜问题管理\",\"url\":\"generator/yanquestion\",\"type\":1,\"icon\":\"sousuo\",\"orderNum\":6}]',9,'0:0:0:0:0:0:0:1','2022-05-22 18:39:52'),(7,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":73,\"parentId\":0,\"name\":\"Dashboard\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"daohang\",\"orderNum\":0}]',7,'0:0:0:0:0:0:0:1','2022-05-22 18:43:52'),(8,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":72,\"parentId\":0,\"name\":\"首页配置\",\"url\":\"/index\",\"type\":0,\"icon\":\"shoucang\",\"orderNum\":1}]',6,'0:0:0:0:0:0:0:1','2022-05-22 18:44:15'),(9,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":74,\"parentId\":73,\"name\":\"首页\",\"url\":\"/home\",\"perms\":\"\",\"type\":1,\"icon\":\"shouye\",\"orderNum\":0}]',8,'0:0:0:0:0:0:0:1','2022-05-22 18:46:27'),(10,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":75,\"parentId\":73,\"name\":\"大屏数据\",\"url\":\"/new/index\",\"perms\":\"\",\"type\":1,\"icon\":\"job\",\"orderNum\":2}]',8,'0:0:0:0:0:0:0:1','2022-05-22 19:19:47'),(11,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":75,\"parentId\":73,\"name\":\"大屏数据\",\"url\":\"new/index\",\"perms\":\"\",\"type\":1,\"icon\":\"job\",\"orderNum\":2}]',10,'0:0:0:0:0:0:0:1','2022-05-22 19:20:01'),(12,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":76,\"parentId\":0,\"name\":\"商品管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"role\",\"orderNum\":8}]',11,'0:0:0:0:0:0:0:1','2022-05-23 12:56:12'),(13,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":77,\"parentId\":76,\"name\":\"分类管理\",\"url\":\"generator/yancategory\",\"perms\":\"\",\"type\":1,\"icon\":\"zonghe\",\"orderNum\":2}]',10,'0:0:0:0:0:0:0:1','2022-05-23 12:57:12'),(14,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":78,\"parentId\":76,\"name\":\"商品\",\"url\":\"generator/yanproduct\",\"perms\":\"\",\"type\":1,\"icon\":\"admin\",\"orderNum\":2}]',22,'0:0:0:0:0:0:0:1','2022-05-23 13:17:36'),(15,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"system\",\"orderNum\":15}]',9,'0:0:0:0:0:0:0:1','2022-06-01 17:01:05'),(16,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":79,\"parentId\":0,\"name\":\"配置中台\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"xiangqu\",\"orderNum\":10}]',8,'0:0:0:0:0:0:0:1','2022-06-01 17:01:40'),(17,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":80,\"parentId\":79,\"name\":\"excel生成表单\",\"url\":\"midPlatform/formConf\",\"perms\":\"\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":2}]',9,'0:0:0:0:0:0:0:1','2022-06-01 17:05:54'),(18,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":81,\"parentId\":79,\"name\":\"逆向工程模板生成\",\"url\":\"/midPlatform/autoCreateConf\",\"perms\":\"\",\"type\":1,\"icon\":\"role\",\"orderNum\":4}]',23,'0:0:0:0:0:0:0:1','2022-06-02 19:00:24'),(19,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":82,\"parentId\":79,\"name\":\"流量监控\",\"url\":\"/midPlatform/flowMonitor\",\"perms\":\"\",\"type\":1,\"icon\":\"shoucang\",\"orderNum\":6}]',9,'0:0:0:0:0:0:0:1','2022-06-02 19:10:33'),(20,'admin','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":83,\"parentId\":79,\"name\":\"在线编程\",\"url\":\"/midPlatform/programOnline.vue\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":8}]',9,'0:0:0:0:0:0:0:1','2022-06-02 19:11:27'),(21,'admin','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":83,\"parentId\":79,\"name\":\"在线编程\",\"url\":\"/midPlatform/programOnline\",\"perms\":\"\",\"type\":1,\"icon\":\"suoding\",\"orderNum\":8}]',17,'0:0:0:0:0:0:0:1','2022-06-02 19:11:59'),(22,'rose','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":84,\"parentId\":0,\"name\":\"运维管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"admin\",\"orderNum\":12}]',10,'0:0:0:0:0:0:0:1','2022-11-03 19:29:14'),(23,'rose','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":85,\"parentId\":84,\"name\":\"Naocs管理\",\"url\":\"/devops/nacos\",\"perms\":\"\",\"type\":1,\"icon\":\"system\",\"orderNum\":0}]',9,'0:0:0:0:0:0:0:1','2022-11-03 19:30:14'),(24,'rose','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":86,\"parentId\":84,\"name\":\"Rabbit管理\",\"url\":\"/devops/rabbit\",\"perms\":\"\",\"type\":1,\"icon\":\"oss\",\"orderNum\":3}]',18,'0:0:0:0:0:0:0:1','2022-11-05 17:33:37'),(25,'rose','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":87,\"parentId\":84,\"name\":\"运维管理主界面\",\"url\":\"/devops/manage\",\"perms\":\"\",\"type\":1,\"icon\":\"role\",\"orderNum\":1}]',10,'0:0:0:0:0:0:0:1','2022-11-05 17:37:41'),(26,'rose','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":88,\"parentId\":84,\"name\":\"sentinel监控面板\",\"url\":\"/devops/sentinel\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0}]',10,'0:0:0:0:0:0:0:1','2022-11-05 18:06:46'),(27,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":88,\"parentId\":84,\"name\":\"sentinel监控面板\",\"url\":\"/devops/sentinel\",\"perms\":\"\",\"type\":1,\"icon\":\"mudedi\",\"orderNum\":0}]',14,'0:0:0:0:0:0:0:1','2022-11-05 18:07:34'),(28,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":85,\"parentId\":84,\"name\":\"Naocs管理\",\"url\":\"/devops/nacos\",\"perms\":\"\",\"type\":1,\"icon\":\"system\",\"orderNum\":1}]',9,'0:0:0:0:0:0:0:1','2022-11-05 18:15:07'),(29,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":85,\"parentId\":84,\"name\":\"Naocs管理\",\"url\":\"/devops/nacos\",\"perms\":\"\",\"type\":1,\"icon\":\"system\",\"orderNum\":2}]',8,'0:0:0:0:0:0:0:1','2022-11-05 18:15:23'),(30,'rose','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":89,\"parentId\":84,\"name\":\"Redis界面管理\",\"url\":\"/devops/redis\",\"perms\":\"\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":0}]',9,'0:0:0:0:0:0:0:1','2022-11-05 18:15:50'),(31,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":88,\"parentId\":84,\"name\":\"sentinel监控面板\",\"url\":\"/devops/sentinel\",\"perms\":\"\",\"type\":1,\"icon\":\"mudedi\",\"orderNum\":5}]',10,'0:0:0:0:0:0:0:1','2022-11-05 18:16:00'),(32,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":89,\"parentId\":84,\"name\":\"Redis界面管理\",\"url\":\"/devops/redis\",\"perms\":\"\",\"type\":1,\"icon\":\"daohang\",\"orderNum\":7}]',9,'0:0:0:0:0:0:0:1','2022-11-05 18:16:08'),(33,'rose','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":90,\"parentId\":0,\"name\":\"Jenkins管理\",\"url\":\"/devvops/jenkins\",\"perms\":\"\",\"type\":1,\"icon\":\"pinglun\",\"orderNum\":11}]',21,'0:0:0:0:0:0:0:1','2022-11-05 19:15:04'),(34,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":90,\"parentId\":84,\"name\":\"Jenkins管理\",\"url\":\"/devvops/jenkins\",\"perms\":\"\",\"type\":1,\"icon\":\"pinglun\",\"orderNum\":11}]',9,'0:0:0:0:0:0:0:1','2022-11-05 19:15:45'),(35,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":83,\"parentId\":79,\"name\":\"swagger\",\"url\":\"/midPlatform/programOnline\",\"perms\":\"\",\"type\":1,\"icon\":\"suoding\",\"orderNum\":8}]',14,'0:0:0:0:0:0:0:1','2022-11-07 22:22:45'),(36,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":83,\"parentId\":79,\"name\":\"Swagger文档\",\"url\":\"/midPlatform/programOnline\",\"perms\":\"\",\"type\":1,\"icon\":\"suoding\",\"orderNum\":8}]',9,'0:0:0:0:0:0:0:1','2022-11-07 22:23:05'),(37,'rose','删除菜单','io.renren.modules.sys.controller.SysMenuController.delete()','[82]',53,'0:0:0:0:0:0:0:1','2022-11-07 22:25:10'),(38,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":81,\"parentId\":79,\"name\":\"数据库管理\",\"url\":\"/midPlatform/autoCreateConf\",\"perms\":\"\",\"type\":1,\"icon\":\"role\",\"orderNum\":4}]',8,'0:0:0:0:0:0:0:1','2022-11-07 22:25:41'),(39,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":90,\"parentId\":84,\"name\":\"Jenkins管理\",\"url\":\"/devops/jenkins\",\"perms\":\"\",\"type\":1,\"icon\":\"pinglun\",\"orderNum\":11}]',12,'0:0:0:0:0:0:0:1','2022-11-08 21:16:09'),(40,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":81,\"parentId\":79,\"name\":\"数据库管理\",\"url\":\"/midPlatform/database\",\"perms\":\"\",\"type\":1,\"icon\":\"role\",\"orderNum\":4}]',9,'0:0:0:0:0:0:0:1','2022-11-08 21:20:58'),(41,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":81,\"parentId\":79,\"name\":\"数据库管理\",\"url\":\"/midPlatform/database\",\"perms\":\"\",\"type\":1,\"icon\":\"role\",\"orderNum\":4}]',5,'0:0:0:0:0:0:0:1','2022-11-08 21:21:00'),(42,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":83,\"parentId\":79,\"name\":\"Swagger文档\",\"url\":\"/midPlatform/swagger\",\"perms\":\"\",\"type\":1,\"icon\":\"suoding\",\"orderNum\":8}]',9,'0:0:0:0:0:0:0:1','2022-11-08 21:21:28'),(43,'rose','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":91,\"parentId\":79,\"name\":\"数据库创建\",\"url\":\"mid\",\"perms\":\"\",\"type\":1,\"icon\":\"mudedi\",\"orderNum\":5}]',44,'0:0:0:0:0:0:0:1','2022-11-19 21:29:41'),(44,'rose','修改菜单','io.renren.modules.sys.controller.SysMenuController.update()','[{\"menuId\":91,\"parentId\":79,\"name\":\"数据库创建\",\"url\":\"midPlatform/dbCreate\",\"perms\":\"\",\"type\":1,\"icon\":\"mudedi\",\"orderNum\":5}]',18,'0:0:0:0:0:0:0:1','2022-11-19 21:29:59'),(45,'rose','保存菜单','io.renren.modules.sys.controller.SysMenuController.save()','[{\"menuId\":92,\"parentId\":79,\"name\":\"创建数据库\",\"url\":\"midPlatform/yandbtable\",\"perms\":\"\",\"type\":1,\"icon\":\"shoucang\",\"orderNum\":5}]',63,'0:0:0:0:0:0:0:1','2022-11-21 14:56:05'),(46,'rose','保存角色','io.renren.modules.sys.controller.SysRoleController.save()','[{\"roleId\":1,\"roleName\":\"app_role\",\"remark\":\"应用角色\",\"createUserId\":1,\"menuIdList\":[73,74,75,79,80,81,83,91,92,84,85,86,87,88,89,90,-666666],\"createTime\":\"Jan 22, 2023 6:59:47 PM\"}]',185,'0:0:0:0:0:0:0:1','2023-01-22 18:59:48'),(47,NULL,'保存用户','com.rose.loginUser.sys.controller.SysUserController.save()','[{\"userId\":7,\"username\":\"admin\",\"password\":\"8a457910e54bff0eff54b1bc4c742d7f14f5d159a41b985a861153746fd9cfac\",\"salt\":\"LoFVocgLmCLNI3ECe5Wk\",\"email\":\"admin@xx.com\",\"mobile\":\"18503568331\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Jan 22, 2023 8:50:33 PM\"}]',20410,'0:0:0:0:0:0:0:1','2023-01-22 20:50:37'),(48,NULL,'保存用户','com.rose.loginUser.sys.controller.SysUserController.save()','[{\"userId\":8,\"username\":\"123\",\"password\":\"b25ad8e4bdd078e4fb674ada03d6d0e92c1142c4aa209adaceebe187849d61ab\",\"salt\":\"kHjDJDTPlnlHwQNJLvBq\",\"email\":\"123@qq.com\",\"mobile\":\"18503568331\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Jan 23, 2023 3:45:01 PM\"}]',5016,'0:0:0:0:0:0:0:1','2023-01-23 15:45:07'),(49,NULL,'保存用户','com.rose.loginUser.sys.controller.SysUserController.save()','[{\"userId\":9,\"username\":\"tom\",\"password\":\"f9d3e893a08f45c87811d3ddf087baef3e0179212ddbdd5b27667d18417fc586\",\"salt\":\"06s44oO5Mv1TIg75ENpR\",\"email\":\"tom@xx.com\",\"mobile\":\"18503568331\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Jan 23, 2023 3:45:41 PM\"}]',11,'0:0:0:0:0:0:0:1','2023-01-23 15:45:41'),(50,NULL,'保存用户','com.rose.loginUser.sys.controller.SysUserController.save()','[{\"userId\":10,\"username\":\"jack\",\"password\":\"4afac5383c24bc45c232701f06bfe776c380ca856637c0d535d750def083809e\",\"salt\":\"XY9OCvgzeZpwr1PoLeF1\",\"email\":\"jack@qq.com\",\"mobile\":\"18503568331\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Jan 23, 2023 3:46:11 PM\"}]',12,'0:0:0:0:0:0:0:1','2023-01-23 15:46:12'),(51,NULL,'保存配置','com.rose.loginUser.sys.controller.SysConfigController.save()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',5249,'0:0:0:0:0:0:0:1','2023-01-23 16:12:27'),(52,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',13,'0:0:0:0:0:0:0:1','2023-01-23 16:15:52'),(53,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',7,'0:0:0:0:0:0:0:1','2023-01-23 16:16:40'),(54,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',3,'0:0:0:0:0:0:0:1','2023-01-23 16:17:29'),(55,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',12417,'0:0:0:0:0:0:0:1','2023-01-23 16:20:14'),(56,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',13328,'0:0:0:0:0:0:0:1','2023-01-23 16:22:21'),(57,NULL,'保存配置','com.rose.loginUser.sys.controller.SysConfigController.save()','[{\"id\":3,\"paramKey\":\"后台管理模块\",\"paramValue\":\"admin\",\"remark\":\"后台管理\"}]',19456,'0:0:0:0:0:0:0:1','2023-01-23 16:27:22'),(58,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',4213,'0:0:0:0:0:0:0:1','2023-01-23 16:32:34'),(59,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',8092,'0:0:0:0:0:0:0:1','2023-01-23 16:36:34'),(60,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',1746,'0:0:0:0:0:0:0:1','2023-01-23 16:36:36'),(61,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',1750,'0:0:0:0:0:0:0:1','2023-01-23 16:36:36'),(62,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',1164,'0:0:0:0:0:0:0:1','2023-01-23 16:36:36'),(63,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',1166,'0:0:0:0:0:0:0:1','2023-01-23 16:36:36'),(64,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',1168,'0:0:0:0:0:0:0:1','2023-01-23 16:36:36'),(65,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',31003,'0:0:0:0:0:0:0:1','2023-01-23 16:39:16'),(66,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',5229,'0:0:0:0:0:0:0:1','2023-01-23 16:39:30'),(67,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',23637,'0:0:0:0:0:0:0:1','2023-01-23 16:40:50'),(68,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',28843,'0:0:0:0:0:0:0:1','2023-01-23 16:43:32'),(69,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',36497,'0:0:0:0:0:0:0:1','2023-01-23 16:44:20'),(70,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',12129,'0:0:0:0:0:0:0:1','2023-01-23 16:46:29'),(71,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',12013,'0:0:0:0:0:0:0:1','2023-01-23 16:49:17'),(72,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":2,\"paramKey\":\"研战到底模块\",\"paramValue\":\"yan_app\",\"remark\":\"区分模块\"}]',10183,'0:0:0:0:0:0:0:1','2023-01-23 16:50:43'),(73,NULL,'保存配置','com.rose.loginUser.sys.controller.SysConfigController.save()','[{\"id\":4,\"paramKey\":\"后台微服务配置\",\"paramValue\":\"micro_config\",\"remark\":\"\"}]',3868,'0:0:0:0:0:0:0:1','2023-01-23 16:51:58'),(74,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":4,\"paramKey\":\"后台微服务配置\",\"paramValue\":\"micro_config\",\"remark\":\"后台配置\"}]',10622,'0:0:0:0:0:0:0:1','2023-01-23 17:04:45'),(75,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":4,\"paramKey\":\"后台微服务配置\",\"paramValue\":\"micro_config\",\"remark\":\"后台配置\"}]',76554,'0:0:0:0:0:0:0:1','2023-01-23 17:08:33'),(76,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":4,\"paramKey\":\"后台微服务配置\",\"paramValue\":\"micro_config\",\"remark\":\"后台配置\"}]',9584,'0:0:0:0:0:0:0:1','2023-01-23 17:11:26'),(77,NULL,'保存配置','com.rose.loginUser.sys.controller.SysConfigController.save()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"remark\":\"\"}]',9,'0:0:0:0:0:0:0:1','2023-01-23 17:12:21'),(78,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',17466,'0:0:0:0:0:0:0:1','2023-01-23 17:20:47'),(79,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',3655,'0:0:0:0:0:0:0:1','2023-01-23 17:20:49'),(80,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',946,'0:0:0:0:0:0:0:1','2023-01-23 17:20:49'),(81,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',2132,'0:0:0:0:0:0:0:1','2023-01-23 17:20:49'),(82,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',724,'0:0:0:0:0:0:0:1','2023-01-23 17:20:50'),(83,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',3245,'0:0:0:0:0:0:0:1','2023-01-23 17:20:53'),(84,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',2862,'0:0:0:0:0:0:0:1','2023-01-23 17:20:50'),(85,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',6115,'0:0:0:0:0:0:0:1','2023-01-23 17:20:53'),(86,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',2381,'0:0:0:0:0:0:0:1','2023-01-23 17:20:53'),(87,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',10,'0:0:0:0:0:0:0:1','2023-01-23 17:20:53'),(88,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',6,'0:0:0:0:0:0:0:1','2023-01-23 17:20:53'),(89,NULL,'修改配置','com.rose.loginUser.sys.controller.SysConfigController.update()','[{\"id\":5,\"paramKey\":\"gateway\",\"paramValue\":\"88\",\"status\":1,\"remark\":\"网关微服务端口\",\"pid\":4}]',6,'0:0:0:0:0:0:0:1','2023-01-23 17:20:53'),(90,NULL,'修改角色','com.rose.loginUser.sys.controller.SysRoleController.update()','[{\"roleId\":2,\"roleName\":\"app_yan_admin\",\"remark\":\"研app角色\",\"createUserId\":1,\"menuIdList\":[31,32,33,34,35,36,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,37,38,39,40,41,73,74,75,76,77,78,-666666]}]',177,'0:0:0:0:0:0:0:1','2023-01-29 15:59:57'),(91,NULL,'修改角色','com.rose.loginUser.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"base_admin\",\"remark\":\"基本管理员\",\"createUserId\":1,\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,73,74,75,79,80,81,83,91,92,84,85,86,87,88,89,90,-666666]}]',75,'0:0:0:0:0:0:0:1','2023-01-29 16:00:23'),(92,NULL,'保存菜单','com.rose.loginUser.sys.controller.SysMenuController.save()','[{\"menuId\":93,\"parentId\":0,\"name\":\"研战到底特定权限测试\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"job\",\"orderNum\":100}]',14,'0:0:0:0:0:0:0:1','2023-01-29 16:36:22'),(93,NULL,'保存菜单','com.rose.loginUser.sys.controller.SysMenuController.save()','[{\"menuId\":94,\"parentId\":93,\"name\":\"会员系统\",\"url\":\"/yan/test\",\"perms\":\"\",\"type\":1,\"icon\":\"shouye\",\"orderNum\":2}]',11,'0:0:0:0:0:0:0:1','2023-01-29 16:39:52'),(94,NULL,'保存菜单','com.rose.loginUser.sys.controller.SysMenuController.save()','[{\"menuId\":95,\"parentId\":94,\"name\":\"一级会员系统\",\"url\":\"\",\"perms\":\"yan:common:first\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]',7,'0:0:0:0:0:0:0:1','2023-01-29 16:40:27'),(95,NULL,'保存菜单','com.rose.loginUser.sys.controller.SysMenuController.save()','[{\"menuId\":96,\"parentId\":94,\"name\":\"二级会员系统\",\"url\":\"\",\"perms\":\"yan:common:second\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]',6,'0:0:0:0:0:0:0:1','2023-01-29 16:40:56'),(96,NULL,'修改角色','com.rose.loginUser.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"base_admin\",\"remark\":\"基本管理员\",\"createUserId\":1,\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,73,74,75,79,80,81,83,91,92,84,85,86,87,88,89,90,93,94,95,96,-666666]}]',68,'0:0:0:0:0:0:0:1','2023-01-29 17:05:33'),(97,NULL,'保存菜单','com.rose.loginUser.sys.controller.SysMenuController.save()','[{\"menuId\":97,\"parentId\":94,\"name\":\"普通用户\",\"url\":\"\",\"perms\":\"yan:common:zero\",\"type\":2,\"icon\":\"\",\"orderNum\":0}]',6,'0:0:0:0:0:0:0:1','2023-01-29 17:06:58'),(98,NULL,'修改角色','com.rose.loginUser.sys.controller.SysRoleController.update()','[{\"roleId\":5,\"roleName\":\"app_yan_member\",\"remark\":\"普通会员用户，享受一些特权，也不具有管理后台的功能\",\"createUserId\":7,\"menuIdList\":[95,96,-666666,93,94]}]',17,'0:0:0:0:0:0:0:1','2023-01-29 17:09:37'),(99,NULL,'修改角色','com.rose.loginUser.sys.controller.SysRoleController.update()','[{\"roleId\":3,\"roleName\":\"base_admin\",\"remark\":\"基本管理员\",\"createUserId\":1,\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,73,74,75,79,80,81,83,91,92,84,85,86,87,88,89,90,93,94,95,96,97,-666666]}]',70,'0:0:0:0:0:0:0:1','2023-01-29 17:10:39'),(100,NULL,'修改角色','com.rose.loginUser.sys.controller.SysRoleController.update()','[{\"roleId\":4,\"roleName\":\"app_yan_common\",\"remark\":\"普通用户，不拥有后台管理权限，但是拥有前端页面的管理权限\",\"createUserId\":7,\"menuIdList\":[97,-666666,93,94]}]',12,'0:0:0:0:0:0:0:1','2023-01-29 17:11:14'),(101,NULL,'修改用户','com.rose.loginUser.sys.controller.SysUserController.update()','[{\"userId\":8,\"username\":\"123\",\"password\":\"b25ad8e4bdd078e4fb674ada03d6d0e92c1142c4aa209adaceebe187849d61ab\",\"salt\":\"kHjDJDTPlnlHwQNJLvBq\",\"email\":\"123@qq.com\",\"mobile\":\"18503568331\",\"status\":1,\"roleIdList\":[4],\"createUserId\":7}]',48,'0:0:0:0:0:0:0:1','2023-01-29 17:22:57'),(102,NULL,'修改用户','com.rose.loginUser.sys.controller.SysUserController.update()','[{\"userId\":10,\"username\":\"jack\",\"password\":\"4afac5383c24bc45c232701f06bfe776c380ca856637c0d535d750def083809e\",\"salt\":\"XY9OCvgzeZpwr1PoLeF1\",\"email\":\"jack@qq.com\",\"mobile\":\"18503568331\",\"status\":1,\"roleIdList\":[5],\"createUserId\":7}]',10,'0:0:0:0:0:0:0:1','2023-01-29 17:23:19');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,0,'系统管理',NULL,NULL,0,'system',15),(2,1,'管理员列表','sys/user',NULL,1,'admin',1),(3,1,'角色管理','sys/role',NULL,1,'role',2),(4,1,'菜单管理','sys/menu',NULL,1,'menu',3),(5,1,'SQL监控','http://localhost:8080/renren-fast/druid/sql.html',NULL,1,'sql',4),(6,1,'定时任务','job/schedule',NULL,1,'job',5),(7,6,'查看',NULL,'sys:schedule:list,sys:schedule:info',2,NULL,0),(8,6,'新增',NULL,'sys:schedule:save',2,NULL,0),(9,6,'修改',NULL,'sys:schedule:update',2,NULL,0),(10,6,'删除',NULL,'sys:schedule:delete',2,NULL,0),(11,6,'暂停',NULL,'sys:schedule:pause',2,NULL,0),(12,6,'恢复',NULL,'sys:schedule:resume',2,NULL,0),(13,6,'立即执行',NULL,'sys:schedule:run',2,NULL,0),(14,6,'日志列表',NULL,'sys:schedule:log',2,NULL,0),(15,2,'查看',NULL,'sys:user:list,sys:user:info',2,NULL,0),(16,2,'新增',NULL,'sys:user:save,sys:role:select',2,NULL,0),(17,2,'修改',NULL,'sys:user:update,sys:role:select',2,NULL,0),(18,2,'删除',NULL,'sys:user:delete',2,NULL,0),(19,3,'查看',NULL,'sys:role:list,sys:role:info',2,NULL,0),(20,3,'新增',NULL,'sys:role:save,sys:menu:list',2,NULL,0),(21,3,'修改',NULL,'sys:role:update,sys:menu:list',2,NULL,0),(22,3,'删除',NULL,'sys:role:delete',2,NULL,0),(23,4,'查看',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),(24,4,'新增',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),(25,4,'修改',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),(26,4,'删除',NULL,'sys:menu:delete',2,NULL,0),(27,1,'参数管理','sys/config','sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete',1,'config',6),(29,1,'系统日志','sys/log','sys:log:list',1,'log',7),(30,1,'文件上传','oss/oss','sys:oss:all',1,'oss',6),(31,0,'研战到底',NULL,NULL,0,'system',2),(32,31,'回答','generator/yananswer',NULL,1,'config',6),(33,32,'查看',NULL,'generator:yananswer:list,generator:yananswer:info',2,NULL,6),(34,32,'新增',NULL,'generator:yananswer:save',2,NULL,6),(35,32,'修改',NULL,'generator:yananswer:update',2,NULL,6),(36,32,'删除',NULL,'generator:yananswer:delete',2,NULL,6),(37,72,'热榜问题管理','generator/yanquestion',NULL,1,'sousuo',6),(38,37,'查看',NULL,'generator:yanquestion:list,generator:yanquestion:info',2,NULL,6),(39,37,'新增',NULL,'generator:yanquestion:save',2,NULL,6),(40,37,'修改',NULL,'generator:yanquestion:update',2,NULL,6),(41,37,'删除',NULL,'generator:yanquestion:delete',2,NULL,6),(42,31,'收录大学','generator/yancollege',NULL,1,'config',6),(43,42,'查看',NULL,'generator:yancollege:list,generator:yancollege:info',2,NULL,6),(44,42,'新增',NULL,'generator:yancollege:save',2,NULL,6),(45,42,'修改',NULL,'generator:yancollege:update',2,NULL,6),(46,42,'删除',NULL,'generator:yancollege:delete',2,NULL,6),(47,31,'主要大学','generator/yanmajorcollege',NULL,1,'config',6),(48,47,'查看',NULL,'generator:yanmajorcollege:list,generator:yanmajorcollege:info',2,NULL,6),(49,47,'新增',NULL,'generator:yanmajorcollege:save',2,NULL,6),(50,47,'修改',NULL,'generator:yanmajorcollege:update',2,NULL,6),(51,47,'删除',NULL,'generator:yanmajorcollege:delete',2,NULL,6),(52,31,'问题标签','generator/yanquestiontag',NULL,1,'config',6),(53,52,'查看',NULL,'generator:yanquestiontag:list,generator:yanquestiontag:info',2,NULL,6),(54,52,'新增',NULL,'generator:yanquestiontag:save',2,NULL,6),(55,52,'修改',NULL,'generator:yanquestiontag:update',2,NULL,6),(56,52,'删除',NULL,'generator:yanquestiontag:delete',2,NULL,6),(57,31,'用户','generator/yanuser',NULL,1,'config',6),(58,57,'查看',NULL,'generator:yanuser:list,generator:yanuser:info',2,NULL,6),(59,57,'新增',NULL,'generator:yanuser:save',2,NULL,6),(60,57,'修改',NULL,'generator:yanuser:update',2,NULL,6),(61,57,'删除',NULL,'generator:yanuser:delete',2,NULL,6),(62,31,'录入的专业','generator/yanmajorme',NULL,1,'config',6),(63,62,'查看',NULL,'generator:yanmajorme:list,generator:yanmajorme:info',2,NULL,6),(64,62,'新增',NULL,'generator:yanmajorme:save',2,NULL,6),(65,62,'修改',NULL,'generator:yanmajorme:update',2,NULL,6),(66,62,'删除',NULL,'generator:yanmajorme:delete',2,NULL,6),(67,31,'录入的问题','generator/yanmajorquestion',NULL,1,'config',6),(68,67,'查看',NULL,'generator:yanmajorquestion:list,generator:yanmajorquestion:info',2,NULL,6),(69,67,'新增',NULL,'generator:yanmajorquestion:save',2,NULL,6),(70,67,'修改',NULL,'generator:yanmajorquestion:update',2,NULL,6),(71,67,'删除',NULL,'generator:yanmajorquestion:delete',2,NULL,6),(72,0,'首页配置','/index',NULL,0,'shoucang',1),(73,0,'Dashboard','','',0,'daohang',0),(74,73,'首页','/home','',1,'shouye',0),(75,73,'大屏数据','new/index','',1,'job',2),(76,0,'商品管理','','',0,'role',8),(77,76,'分类管理','generator/yancategory','',1,'zonghe',2),(78,76,'商品','generator/yanproduct','',1,'admin',2),(79,0,'配置中台','','',0,'xiangqu',10),(80,79,'excel生成表单','midPlatform/formConf','',1,'daohang',2),(81,79,'数据库管理','/midPlatform/database','',1,'role',4),(83,79,'Swagger文档','/midPlatform/swagger','',1,'suoding',8),(84,0,'运维管理','','',0,'admin',12),(85,84,'Naocs管理','/devops/nacos','',1,'system',2),(86,84,'Rabbit管理','/devops/rabbit','',1,'oss',3),(87,84,'运维管理主界面','/devops/manage','',1,'role',1),(88,84,'sentinel监控面板','/devops/sentinel','',1,'mudedi',5),(89,84,'Redis界面管理','/devops/redis','',1,'daohang',7),(90,84,'Jenkins管理','/devops/jenkins','',1,'pinglun',11),(91,79,'数据库创建','midPlatform/dbCreate','',1,'mudedi',5),(92,79,'创建数据库','midPlatform/yandbtable','',1,'shoucang',5),(93,0,'研战到底特定权限测试','','',0,'job',100),(94,93,'会员系统','/yan/test','',1,'shouye',2),(95,94,'一级会员系统','','yan:common:first',2,'',0),(96,94,'二级会员系统','','yan:common:second',2,'',0),(97,94,'普通用户','','yan:common:zero',2,'',0);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oss`
--

DROP TABLE IF EXISTS `sys_oss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oss` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文件上传';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oss`
--

LOCK TABLES `sys_oss` WRITE;
/*!40000 ALTER TABLE `sys_oss` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_oss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'super_admin','应用角色',1,'2023-01-22 18:59:48'),(2,'app_yan_admin','研app角色',1,'2023-01-29 10:54:09'),(3,'base_admin','基本管理员',1,'2023-01-29 10:58:47'),(4,'app_yan_common','普通用户，不拥有后台管理权限，但是拥有前端页面的管理权限',7,'2023-01-29 11:22:42'),(5,'app_yan_member','普通会员用户，享受一些特权，也不具有管理后台的功能',7,'2023-01-29 11:23:39');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=223 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色与菜单对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1,1,73),(2,1,74),(3,1,75),(4,1,79),(5,1,80),(6,1,81),(7,1,83),(8,1,91),(9,1,92),(10,1,84),(11,1,85),(12,1,86),(13,1,87),(14,1,88),(15,1,89),(16,1,90),(17,1,-666666),(18,2,31),(19,2,32),(20,2,33),(21,2,34),(22,2,35),(23,2,36),(24,2,42),(25,2,43),(26,2,44),(27,2,45),(28,2,46),(29,2,47),(30,2,48),(31,2,49),(32,2,50),(33,2,51),(34,2,52),(35,2,53),(36,2,54),(37,2,55),(38,2,56),(39,2,57),(40,2,58),(41,2,59),(42,2,60),(43,2,61),(44,2,62),(45,2,63),(46,2,64),(47,2,65),(48,2,66),(49,2,67),(50,2,68),(51,2,69),(52,2,70),(53,2,71),(54,2,72),(55,2,37),(56,2,38),(57,2,39),(58,2,40),(59,2,41),(60,2,73),(61,2,74),(62,2,75),(63,2,76),(64,2,77),(65,2,78),(66,2,-666666),(163,5,95),(164,5,96),(165,5,-666666),(166,5,93),(167,5,94),(168,3,1),(169,3,2),(170,3,15),(171,3,16),(172,3,17),(173,3,18),(174,3,3),(175,3,19),(176,3,20),(177,3,21),(178,3,22),(179,3,4),(180,3,23),(181,3,24),(182,3,25),(183,3,26),(184,3,5),(185,3,6),(186,3,7),(187,3,8),(188,3,9),(189,3,10),(190,3,11),(191,3,12),(192,3,13),(193,3,14),(194,3,27),(195,3,29),(196,3,30),(197,3,73),(198,3,74),(199,3,75),(200,3,79),(201,3,80),(202,3,81),(203,3,83),(204,3,91),(205,3,92),(206,3,84),(207,3,85),(208,3,86),(209,3,87),(210,3,88),(211,3,89),(212,3,90),(213,3,93),(214,3,94),(215,3,95),(216,3,96),(217,3,97),(218,3,-666666),(219,4,97),(220,4,-666666),(221,4,93),(222,4,94);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) DEFAULT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--



--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与角色对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,9,2),(2,7,3),(3,8,4),(4,10,5);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_token`
--

DROP TABLE IF EXISTS `sys_user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_token` (
  `user_id` bigint NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户Token';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_token`
--

LOCK TABLES `sys_user_token` WRITE;
/*!40000 ALTER TABLE `sys_user_token` DISABLE KEYS */;
INSERT INTO `sys_user_token` VALUES (1,'11af4ab6ecfb828e27f074c280360089','2023-01-23 04:14:11','2023-01-22 16:14:11');
/*!40000 ALTER TABLE `sys_user_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_db_table`
--

DROP TABLE IF EXISTS `yan_db_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_db_table` (
  `id` varchar(100) NOT NULL COMMENT '主键id',
  `fieldType` varchar(255) DEFAULT NULL COMMENT '字段类型',
  `fieldName` varchar(255) DEFAULT NULL COMMENT '字段名',
  `length` int DEFAULT NULL COMMENT '长度',
  `isnull` tinyint DEFAULT NULL COMMENT '是否null\r\n是否null',
  `decimalPoint` int DEFAULT NULL COMMENT '小数点',
  `fieldKey` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '键',
  `fieldComment` varchar(255) DEFAULT NULL COMMENT '注释',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_db_table`
--

LOCK TABLES `yan_db_table` WRITE;
/*!40000 ALTER TABLE `yan_db_table` DISABLE KEYS */;
INSERT INTO `yan_db_table` VALUES ('5eac96798ae0a3acdf67601164d9d9fa','varchar','test',1322,0,0,'0','cewq',NULL,NULL);
/*!40000 ALTER TABLE `yan_db_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_im_group`
--

DROP TABLE IF EXISTS `yan_im_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_im_group` (
  `group_id` int NOT NULL AUTO_INCREMENT COMMENT '自增，群组id',
  `group_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '群组名',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像路径',
  `description` varchar(30) DEFAULT NULL COMMENT '用户描述',
  `admin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人用户id',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_im_group`
--

LOCK TABLES `yan_im_group` WRITE;
/*!40000 ALTER TABLE `yan_im_group` DISABLE KEYS */;
INSERT INTO `yan_im_group` VALUES (1,'牛气冲天','https://edu-renyun.oss-cn-beijing.aliyuncs.com/2022/06/09/d611c6e845754dadb8f6a3615f07f5c038cdff10de3be4e4b0387e8a59004207.jpeg','第一次尝试','1879878-NKCNO-NKNK');
/*!40000 ALTER TABLE `yan_im_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_im_group_relation`
--

DROP TABLE IF EXISTS `yan_im_group_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_im_group_relation` (
  `relation` int NOT NULL AUTO_INCREMENT COMMENT '自增，关系id',
  `group_id` int DEFAULT NULL COMMENT '组号',
  `member_id` varchar(255) DEFAULT NULL COMMENT '组员号',
  PRIMARY KEY (`relation`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_im_group_relation`
--

LOCK TABLES `yan_im_group_relation` WRITE;
/*!40000 ALTER TABLE `yan_im_group_relation` DISABLE KEYS */;
INSERT INTO `yan_im_group_relation` VALUES (1,1,'1879878-NKCNO-NKNK'),(2,1,'56C02DF0516B4B079ABFCEC08169E577'),(3,1,'B9C6A3A357534C7F9DD4C2F22FA42AD5'),(4,1,'test');
/*!40000 ALTER TABLE `yan_im_group_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_im_user`
--

DROP TABLE IF EXISTS `yan_im_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_im_user` (
  `openid` varchar(132) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户表id',
  `chat_id` int DEFAULT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `avatar_url` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像地址',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `level_id` bigint DEFAULT NULL COMMENT '会员等级id',
  `is_alive` int DEFAULT NULL COMMENT '是否登录',
  `integration` int DEFAULT NULL COMMENT '积分',
  `growth` int DEFAULT NULL COMMENT '成长值',
  `college` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '我心怡的大学',
  `major` varchar(50) DEFAULT NULL COMMENT '我的专业',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`openid`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `yan_im_user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_im_user_address` (
  `address_id` bigint NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户主键id',
  `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `user_phone` varchar(11) NOT NULL DEFAULT '' COMMENT '收货人手机号',
  `default_flag` tinyint NOT NULL DEFAULT '0' COMMENT '是否为默认 0-非默认 1-是默认',
  `province_name` varchar(32) NOT NULL DEFAULT '' COMMENT '省',
  `city_name` varchar(32) NOT NULL DEFAULT '' COMMENT '城',
  `region_name` varchar(32) NOT NULL DEFAULT '' COMMENT '区',
  `detail_address` varchar(64) NOT NULL DEFAULT '' COMMENT '收件详细地址(街道/楼宇/单元)',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_im_user_address`
--

LOCK TABLES `yan_im_user_address` WRITE;
/*!40000 ALTER TABLE `yan_im_user_address` DISABLE KEYS */;
INSERT INTO `yan_im_user_address` VALUES (1,'1879878-NKCNO-NKNK','rose','18503568331',0,'山西省','晋城市','陵川县','山西省晋城市陵川县',0,'2022-06-21 12:51:13','2022-06-21 12:51:20');
/*!40000 ALTER TABLE `yan_im_user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_im_user_chat`
--

DROP TABLE IF EXISTS `yan_im_user_chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_im_user_chat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '聊天id',
  `user_openid` varchar(100) DEFAULT NULL COMMENT '聊天者自己的id',
  `to_openid` varchar(100) DEFAULT NULL COMMENT '别人id',
  `content` varchar(100) DEFAULT NULL COMMENT '聊天内容',
  `is_read` int DEFAULT NULL COMMENT '对方是否接受到',
  `group` tinyint(1) DEFAULT NULL COMMENT '是否群聊(0:群聊；1:单聊)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `msg_id` varchar(200) DEFAULT NULL COMMENT '根据时间戳生成',
  `is_undo` varchar(100) DEFAULT NULL COMMENT '是否撤回',
  `t_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型(1:文本;2:表情包;3:图片;4:文件;)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_im_user_chat`
--

LOCK TABLES `yan_im_user_chat` WRITE;
/*!40000 ALTER TABLE `yan_im_user_chat` DISABLE KEYS */;
INSERT INTO `yan_im_user_chat` VALUES (1,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','111',1,NULL,'2021-07-26 04:48:12',NULL,NULL,NULL),(2,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','你好呀 rose',1,NULL,'2021-07-26 04:49:08',NULL,NULL,NULL),(3,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','我很好。nice',1,NULL,'2021-07-26 04:49:31',NULL,NULL,NULL),(4,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','好的呢',1,NULL,'2021-07-26 04:49:40',NULL,NULL,NULL),(5,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','okk',1,NULL,'2021-07-26 04:49:47',NULL,NULL,NULL),(6,'1879878-NKCNO-NKNK','BEE8E17A7A794A979211DE3C4590824E','156161',1,NULL,'2021-07-30 15:16:06',NULL,NULL,NULL),(7,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','156161',1,NULL,'2021-07-30 15:25:07',NULL,NULL,NULL),(8,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','你在干嘛',1,NULL,'2021-07-31 15:34:25',NULL,NULL,NULL),(9,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','我好想掉线了',1,NULL,'2021-07-31 15:34:43',NULL,NULL,NULL),(10,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','这也太难过了',1,NULL,'2021-07-31 15:35:50',NULL,NULL,NULL),(11,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','突然发现不同浏览器对flex兼容性不一样',1,NULL,'2021-07-31 15:43:44',NULL,NULL,NULL),(12,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','这回看起来连接起来了',1,NULL,'2021-07-31 15:49:20',NULL,NULL,NULL),(13,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','太难过了',1,NULL,'2021-07-31 15:51:52',NULL,NULL,NULL),(14,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','1111',1,NULL,'2021-07-31 15:52:58',NULL,NULL,NULL),(15,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','aaa',1,NULL,'2021-07-31 16:10:41',NULL,NULL,NULL),(16,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','测试测试',1,NULL,'2021-07-31 16:14:48',NULL,NULL,NULL),(17,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','原来是封装的结果的锅原来入场',1,NULL,'2021-07-31 16:15:10',NULL,NULL,NULL),(18,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','看看在不在线',1,NULL,'2021-07-31 16:17:08',NULL,NULL,NULL),(19,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','没点开',1,NULL,'2021-07-31 16:17:16',NULL,NULL,NULL),(20,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','看来在线',1,NULL,'2021-07-31 16:17:25',NULL,NULL,NULL),(21,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','这也行吧',1,NULL,'2021-07-31 16:17:35',NULL,NULL,NULL),(22,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','一个一个推送',1,NULL,'2021-07-31 16:17:56',NULL,NULL,NULL),(23,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','给rose发送看看rose有没有小红点',1,NULL,'2021-08-01 05:49:30',NULL,NULL,NULL),(24,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','再发一条',1,NULL,'2021-08-01 05:50:19',NULL,NULL,NULL),(25,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','收到了',1,NULL,'2021-08-01 05:59:16',NULL,NULL,NULL),(26,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','测试小红点',1,NULL,'2021-08-01 06:08:35',NULL,NULL,NULL),(27,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','测试',1,NULL,'2021-08-26 11:36:59',NULL,NULL,NULL),(28,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','好的',1,NULL,'2021-08-26 11:37:12',NULL,NULL,NULL),(29,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','好像真的被我改坏了',1,NULL,'2021-08-26 11:37:51',NULL,NULL,NULL),(30,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','测试发一条',1,NULL,'2021-08-26 11:42:12',NULL,NULL,NULL),(31,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','进行测试',1,NULL,'2021-08-26 11:46:11',NULL,NULL,NULL),(32,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','刚刚没进来',1,NULL,'2021-08-26 11:46:24',NULL,NULL,NULL),(33,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','好用了？',1,NULL,'2021-08-26 11:47:25',NULL,NULL,NULL),(34,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','11',1,NULL,'2021-08-26 11:47:33',NULL,NULL,NULL),(35,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','11',1,NULL,'2021-08-26 11:48:11',NULL,NULL,NULL),(36,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','22',1,NULL,'2021-08-26 11:50:11',NULL,NULL,NULL),(37,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','为什么头像没了呢',1,NULL,'2021-08-26 12:07:41',NULL,NULL,NULL),(38,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','121321',1,NULL,'2021-08-26 12:10:04',NULL,NULL,NULL),(39,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','11',1,NULL,'2021-08-26 12:12:55',NULL,NULL,NULL),(40,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','12',1,NULL,'2021-08-26 12:13:09',NULL,NULL,NULL),(41,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','？？？',1,NULL,'2021-08-26 12:13:29',NULL,NULL,NULL),(42,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','111',1,NULL,'2021-08-26 12:13:44',NULL,NULL,NULL),(43,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','121',1,NULL,'2021-08-26 12:14:56',NULL,NULL,NULL),(44,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','1232',1,NULL,'2021-08-26 12:15:08',NULL,NULL,NULL),(45,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','122121',1,NULL,'2021-08-26 12:21:14',NULL,NULL,NULL),(46,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','123232',1,NULL,'2021-08-26 12:43:26',NULL,NULL,NULL),(47,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','132',1,NULL,'2021-08-26 12:44:34',NULL,NULL,NULL),(48,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','123',1,NULL,'2021-08-26 12:50:07',NULL,NULL,NULL),(49,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','12312',1,NULL,'2021-08-26 12:50:31',NULL,NULL,NULL),(50,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','外送a',1,NULL,'2021-08-26 12:51:10',NULL,NULL,NULL),(51,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','吖吖',1,NULL,'2021-08-26 12:52:50',NULL,NULL,NULL),(52,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','159',1,NULL,'2021-08-26 12:55:50',NULL,NULL,NULL),(53,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','球球',1,NULL,'2021-08-26 12:57:09',NULL,NULL,NULL),(54,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','qq',1,NULL,'2021-08-26 12:57:25',NULL,NULL,NULL),(55,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','bb',1,NULL,'2021-08-26 12:58:20',NULL,NULL,NULL),(56,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','ww',1,NULL,'2021-08-26 13:01:44',NULL,NULL,NULL),(57,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','aa',1,NULL,'2021-08-26 13:01:51',NULL,NULL,NULL),(58,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','qqq',1,NULL,'2021-08-26 13:02:04',NULL,NULL,NULL),(59,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','eee',1,NULL,'2021-08-26 13:04:07',NULL,NULL,NULL),(60,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','111',1,NULL,'2021-08-26 13:04:28',NULL,NULL,NULL),(61,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','带的',1,NULL,'2021-08-26 13:06:00',NULL,NULL,NULL),(62,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','222',1,NULL,'2021-08-26 13:06:41',NULL,NULL,NULL),(63,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','123123',1,NULL,'2021-08-26 13:08:53',NULL,NULL,NULL),(64,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','终于成了',1,NULL,'2021-08-26 13:09:06',NULL,NULL,NULL),(65,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','这是为啥',1,NULL,'2021-08-26 13:09:28',NULL,NULL,NULL),(66,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','一边行一边不行',1,NULL,'2021-08-26 13:09:49',NULL,NULL,NULL),(67,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','有点慢呐',1,NULL,'2021-08-26 13:10:10',NULL,NULL,NULL),(68,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','分不清楚',1,NULL,'2021-08-26 13:10:44',NULL,NULL,NULL),(69,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','111',1,NULL,'2021-08-26 13:10:49',NULL,NULL,NULL),(70,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','严重怀疑是没刷吧',1,NULL,'2021-08-26 13:11:10',NULL,NULL,NULL),(71,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','12312',1,NULL,'2021-08-26 13:11:15',NULL,NULL,NULL),(72,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','现在数据都过来了',1,NULL,'2021-08-26 13:11:25',NULL,NULL,NULL),(73,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','没有问题',1,NULL,'2021-08-26 13:11:31',NULL,NULL,NULL),(74,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','12312',1,NULL,'2021-09-01 14:13:59',NULL,NULL,NULL),(75,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','13',1,NULL,'2021-09-01 14:15:05',NULL,NULL,NULL),(76,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','aa',1,NULL,'2021-09-01 14:15:12',NULL,NULL,NULL),(77,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','测试消息',1,NULL,'2022-02-15 18:20:13',NULL,NULL,NULL),(78,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','收到收到',1,NULL,'2022-02-15 18:20:33',NULL,NULL,NULL),(79,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','测试2',1,NULL,'2022-02-15 18:21:21',NULL,NULL,NULL),(80,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','2.24',1,NULL,'2022-02-15 18:24:18',NULL,NULL,NULL),(81,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','2:27',1,NULL,'2022-02-15 18:27:19',NULL,NULL,NULL),(82,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','111',1,NULL,'2022-02-15 18:27:25',NULL,NULL,NULL),(83,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','',1,NULL,'2022-02-16 13:02:18',NULL,NULL,NULL),(84,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','',1,NULL,'2022-02-16 13:02:25',NULL,NULL,NULL),(85,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','',1,NULL,'2022-02-16 13:02:31',NULL,NULL,NULL),(86,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','aaa',1,NULL,'2022-02-16 13:04:03',NULL,NULL,NULL),(87,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','bbb',1,NULL,'2022-02-16 13:04:09',NULL,NULL,NULL),(88,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','ccc',1,NULL,'2022-02-16 13:04:14',NULL,NULL,NULL),(89,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','ddd',1,NULL,'2022-02-16 13:50:44',NULL,NULL,NULL),(90,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','eee',1,NULL,'2022-02-16 13:50:49',NULL,NULL,NULL),(91,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','fff',1,NULL,'2022-02-16 13:50:54',NULL,NULL,NULL),(92,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','okok',1,NULL,'2022-02-16 13:51:00',NULL,NULL,NULL),(93,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','mq测试',1,NULL,'2022-02-23 10:48:33',NULL,NULL,NULL),(94,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','试试',1,NULL,'2022-02-23 12:24:05',NULL,NULL,NULL),(95,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','还真有',1,NULL,'2022-02-23 12:24:23',NULL,NULL,NULL),(96,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','再试试',1,NULL,'2022-02-23 12:37:56',NULL,NULL,NULL),(97,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','11',1,NULL,'2022-02-23 12:40:28',NULL,NULL,NULL),(98,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','必须进入了才有',1,NULL,'2022-02-23 12:40:36',NULL,NULL,NULL),(99,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','就很离谱',1,NULL,'2022-02-23 12:40:43',NULL,NULL,NULL),(100,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','消息分不清左右了',1,NULL,'2022-02-23 12:40:58',NULL,NULL,NULL),(101,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','11',1,NULL,'2022-02-23 12:50:27',NULL,NULL,NULL),(102,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','22',1,NULL,'2022-02-23 12:50:45',NULL,NULL,NULL),(103,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','33',1,NULL,'2022-02-23 12:51:22',NULL,NULL,NULL),(104,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','44',1,NULL,'2022-02-23 12:51:28',NULL,NULL,NULL),(105,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','发送给admin',1,NULL,'2022-02-23 13:04:04',NULL,NULL,NULL),(106,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','发送给admin',1,NULL,'2022-02-23 13:04:05',NULL,NULL,NULL),(107,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','给123用户',1,NULL,'2022-02-23 13:06:25',NULL,NULL,NULL),(108,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','给123用户再次发送消息',1,NULL,'2022-02-23 13:13:26',NULL,NULL,NULL),(109,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','123',1,NULL,'2022-02-23 13:21:18',NULL,NULL,NULL),(110,'1879878-NKCNO-NKNK','BEE8E17A7A794A979211DE3C4590824E','给admin2 发消息',1,NULL,'2022-02-23 13:24:22',NULL,NULL,NULL),(111,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','123456',1,NULL,'2022-02-23 13:25:08',NULL,NULL,NULL),(112,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','基本同步应该',1,NULL,'2022-02-23 13:25:21',NULL,NULL,NULL),(113,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','发送消息',1,NULL,'2022-05-15 06:51:05',NULL,NULL,NULL),(114,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','收到了',1,NULL,'2022-05-15 06:51:20',NULL,NULL,NULL),(115,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','可以可以',1,NULL,'2022-05-15 06:51:28',NULL,NULL,NULL),(116,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','发送消息',1,NULL,'2022-05-18 15:07:53',NULL,NULL,NULL),(117,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','难道刚刚是连接错误码',1,NULL,'2022-05-18 15:08:29',NULL,NULL,NULL),(118,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','确实是了',1,NULL,'2022-05-18 15:08:36',NULL,NULL,NULL),(119,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','小bug',1,NULL,'2022-05-18 15:08:50',NULL,NULL,NULL),(120,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','测试netty',1,NULL,'2022-05-29 07:10:49',NULL,NULL,NULL),(121,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','测试netty',1,NULL,'2022-05-29 07:15:20',NULL,NULL,NULL),(122,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','测试消息',1,NULL,'2022-05-29 10:19:51',NULL,NULL,NULL),(123,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','111',1,NULL,'2022-05-29 10:28:11',NULL,NULL,NULL),(124,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','vue3',1,NULL,'2022-05-29 10:30:47',NULL,NULL,NULL),(125,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','vue3',1,NULL,'2022-05-29 10:33:51',NULL,NULL,NULL),(126,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','vue2',1,NULL,'2022-05-29 10:35:08',NULL,NULL,NULL),(127,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','vue3',1,NULL,'2022-05-29 10:35:24',NULL,NULL,NULL),(128,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','test',1,NULL,'2022-05-29 10:37:00',NULL,NULL,NULL),(129,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','again',1,NULL,'2022-05-29 10:37:14',NULL,NULL,NULL),(130,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','你在吗',1,NULL,'2022-06-03 09:43:31',NULL,NULL,NULL),(131,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','我在的',1,NULL,'2022-06-03 09:43:42',NULL,NULL,NULL),(132,'1879878-NKCNO-NKNK','1','测试有没有数据',1,1,'2022-06-08 23:33:06',NULL,NULL,NULL),(133,'56C02DF0516B4B079ABFCEC08169E577','1','区分非自己数据',1,1,'2022-06-08 23:33:47',NULL,NULL,NULL),(134,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1','别人的数据',1,1,'2022-06-09 12:31:11',NULL,NULL,NULL),(135,'1879878-NKCNO-NKNK','1','1111',1,0,'2022-06-09 06:07:23',NULL,NULL,NULL),(136,'56C02DF0516B4B079ABFCEC08169E577','1','好像是行了',1,0,'2022-06-09 06:08:12',NULL,NULL,NULL),(137,'1879878-NKCNO-NKNK','1','测试有没有头像',1,0,'2022-06-09 06:21:45',NULL,NULL,NULL),(138,'1879878-NKCNO-NKNK','1','测试头像',1,0,'2022-06-09 06:24:23',NULL,NULL,NULL),(139,'1879878-NKCNO-NKNK','1','再次测试',1,0,'2022-06-09 06:25:42',NULL,NULL,NULL),(140,'1879878-NKCNO-NKNK','1','测试',1,0,'2022-06-09 06:30:31',NULL,NULL,NULL),(141,'1879878-NKCNO-NKNK','1','1111',1,0,'2022-06-09 06:31:57',NULL,NULL,NULL),(142,'56C02DF0516B4B079ABFCEC08169E577','1','可以 有头像了',1,0,'2022-06-09 06:32:05',NULL,NULL,NULL),(143,'1879878-NKCNO-NKNK','1','先这样吧',1,0,'2022-06-09 06:32:18',NULL,NULL,NULL),(144,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','保存其他数据',1,1,'2022-06-09 06:34:08',NULL,NULL,NULL),(145,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','111',1,1,'2022-06-13 10:45:11',NULL,NULL,NULL),(146,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','测试rabbitmq',1,1,'2022-06-13 12:05:01',NULL,NULL,NULL),(147,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','可以了',1,1,'2022-06-13 12:05:08',NULL,NULL,NULL),(148,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','看起来没问题',1,1,'2022-06-13 12:05:15',NULL,NULL,NULL),(149,'56C02DF0516B4B079ABFCEC08169E577','1','群聊rabbitmq',1,0,'2022-06-13 12:05:37',NULL,NULL,NULL),(150,'1879878-NKCNO-NKNK','1','看起来没问题',1,0,'2022-06-13 12:05:46',NULL,NULL,NULL),(151,'56C02DF0516B4B079ABFCEC08169E577','1','先这样吧',1,0,'2022-06-13 12:05:53',NULL,NULL,NULL),(152,'1879878-NKCNO-NKNK','1','看起来是没问题了',1,0,'2022-06-13 12:06:00',NULL,NULL,NULL),(153,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','发送消息',1,1,'2022-06-16 08:42:35',NULL,NULL,NULL),(154,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','测试rabbitmq',1,1,'2022-06-16 08:43:41',NULL,NULL,NULL),(155,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','111',1,1,'2022-06-16 08:44:00',NULL,NULL,NULL),(156,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','目前消息小红点没有及时的通知过来',1,1,'2022-06-16 08:44:21',NULL,NULL,NULL),(157,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','11',1,1,'2022-06-16 09:28:09',NULL,NULL,NULL),(158,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','20230216测试',1,1,'2023-02-16 07:54:41',NULL,NULL,NULL),(159,'56C02DF0516B4B079ABFCEC08169E577','1','再次测试群聊',1,0,'2023-02-17 06:48:48',NULL,NULL,NULL),(160,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','测试消息',1,1,'2023-06-24 07:48:17',NULL,NULL,NULL),(161,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','20230624',1,1,'2023-06-24 07:50:42',NULL,NULL,NULL),(162,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','发送',1,1,'2023-06-24 07:51:58',NULL,NULL,NULL),(163,'B9C6A3A357534C7F9DD4C2F22FA42AD5','1879878-NKCNO-NKNK','高宝宝',1,1,'2023-06-24 09:16:58',NULL,NULL,NULL),(164,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','1231',1,1,'2023-06-24 09:17:16',NULL,NULL,NULL),(165,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','20230624',1,1,'2023-06-27 13:15:13',NULL,NULL,NULL),(166,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','111',1,1,'2023-10-27 06:51:12',NULL,NULL,NULL),(167,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','111',1,1,'2023-12-20 11:00:21',NULL,NULL,NULL),(168,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','20231220测试',1,1,'2023-12-20 11:01:36',NULL,NULL,NULL),(169,'1879878-NKCNO-NKNK','B9C6A3A357534C7F9DD4C2F22FA42AD5','20231220测试',0,1,'2023-12-20 11:02:54',NULL,NULL,NULL),(170,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','20231221测ui',1,1,'2023-12-21 02:16:44',NULL,NULL,NULL),(171,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','111',1,1,'2023-12-21 07:33:38',NULL,NULL,NULL),(172,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','123',1,1,'2023-12-21 07:37:29',NULL,NULL,NULL),(173,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','1234',1,1,'2023-12-21 07:48:11',NULL,NULL,NULL),(174,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','123',1,1,'2023-12-21 07:56:02',NULL,NULL,NULL),(175,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','123',1,1,'2023-12-21 08:19:56',NULL,NULL,NULL),(176,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','11',1,1,'2023-12-21 08:27:14',NULL,NULL,NULL),(177,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','1122',1,1,'2023-12-21 08:49:03',NULL,NULL,NULL),(178,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','112233',1,1,'2023-12-21 09:02:58',NULL,NULL,NULL),(179,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','213123',1,1,'2023-12-21 09:05:18',NULL,NULL,NULL),(180,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','12312',1,1,'2023-12-21 09:05:40',NULL,NULL,NULL),(181,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','12321',1,1,'2023-12-21 09:07:21',NULL,NULL,NULL),(182,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','12321',1,1,'2023-12-21 09:07:59',NULL,NULL,NULL),(183,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','qqz',1,1,'2023-12-21 09:08:13',NULL,NULL,NULL),(184,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','起案子',1,1,'2023-12-21 12:31:05',NULL,NULL,NULL),(185,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','123',1,1,'2023-12-21 12:31:25',NULL,NULL,NULL),(186,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','12',1,1,'2023-12-21 12:32:17',NULL,NULL,NULL),(187,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','热死',1,1,'2023-12-21 12:32:38',NULL,NULL,NULL),(188,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','',1,1,'2023-12-21 12:32:38',NULL,NULL,NULL),(189,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','qwewq',1,1,'2023-12-21 12:33:22',NULL,NULL,NULL),(190,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','q',1,1,'2023-12-21 12:34:02',NULL,NULL,NULL),(191,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','aaa',1,1,'2023-12-21 12:34:38',NULL,NULL,NULL),(192,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','bb',1,1,'2023-12-21 12:35:08',NULL,NULL,NULL),(193,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','cc',1,1,'2023-12-21 12:37:34',NULL,NULL,NULL),(194,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','dd',1,1,'2023-12-21 12:38:01',NULL,NULL,NULL),(195,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','15',1,1,'2023-12-21 13:22:54',NULL,NULL,NULL),(196,'56C02DF0516B4B079ABFCEC08169E577','1879878-NKCNO-NKNK','16',1,1,'2023-12-21 13:23:11',NULL,NULL,NULL),(197,'1879878-NKCNO-NKNK','56C02DF0516B4B079ABFCEC08169E577','17',1,1,'2023-12-21 13:31:35',NULL,NULL,NULL),(198,'1879878-NKCNO-NKNK','1','123☀️',1,0,'2023-12-21 13:47:03',NULL,NULL,NULL),(199,'1879878-NKCNO-NKNK','1','123⌚',1,0,'2023-12-21 13:47:04',NULL,NULL,NULL),(200,'1879878-NKCNO-NKNK','1','123⌛',1,0,'2023-12-21 13:48:55',NULL,NULL,NULL),(201,'56C02DF0516B4B079ABFCEC08169E577','1','1234✈️',1,0,'2023-12-21 13:49:23',NULL,NULL,NULL),(202,'1879878-NKCNO-NKNK','1','1qaz☠️',1,0,'2023-12-21 14:17:32',NULL,NULL,NULL),(203,'1879878-NKCNO-NKNK','1','👨‍🎓',1,0,'2023-12-21 14:18:19',NULL,NULL,NULL);
/*!40000 ALTER TABLE `yan_im_user_chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_im_user_level`
--

DROP TABLE IF EXISTS `yan_im_user_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_im_user_level` (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '等级名称',
  `growth_point` int DEFAULT NULL COMMENT '等级需要的成长值',
  `free_freight` tinyint DEFAULT NULL COMMENT '是否免邮',
  `discount` int DEFAULT NULL COMMENT '打几折',
  `note` varchar(255) DEFAULT NULL COMMENT '是否有生日特权',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_im_user_level`
--

LOCK TABLES `yan_im_user_level` WRITE;
/*!40000 ALTER TABLE `yan_im_user_level` DISABLE KEYS */;
/*!40000 ALTER TABLE `yan_im_user_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_im_user_like`
--

DROP TABLE IF EXISTS `yan_im_user_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_im_user_like` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_like` varchar(100) DEFAULT NULL COMMENT '关注者openid',
  `user` varchar(100) DEFAULT NULL COMMENT '被关注者openid',
  `is_together` tinyint DEFAULT NULL COMMENT '是否互相关注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_im_user_like`
--

LOCK TABLES `yan_im_user_like` WRITE;
/*!40000 ALTER TABLE `yan_im_user_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `yan_im_user_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_answer`
--

DROP TABLE IF EXISTS `yan_info_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_answer` (
  `answer_id` varchar(100) NOT NULL,
  `que_id` int DEFAULT NULL,
  `openid` varchar(40) DEFAULT NULL,
  `answer_name` varchar(30) DEFAULT NULL,
  `answer_des` varchar(100) DEFAULT NULL,
  `answer_content` text,
  `answer_like` int DEFAULT NULL,
  PRIMARY KEY (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_answer`
--

LOCK TABLES `yan_info_answer` WRITE;
/*!40000 ALTER TABLE `yan_info_answer` DISABLE KEYS */;
INSERT INTO `yan_info_answer` VALUES ('1',1,'56C02DF0516B4B079ABFCEC08169E577','Rebecca','WEB前端+我是rose','<P>难道不明白纸质书更贵啊！！！</P>\r\n\r\n            <P>若觉得kindle更贵，我觉得要么阅读量太少，那确实没有买kindle的必要。</P>要么买的都是盗版的纸质书？我不清楚不加以评论。。。\r\n\r\n            另外，用kindle看小说的怎么真心不懂了。题主不看小说么？难道题主拿来看教科书还是技术文档？还是题主觉得小说就是小时代内样的？（对小时代没偏见，尊重多样性）\r\n\r\n            而且纸质书搬起来真心困难啊！当初毕业带不回来，忍痛卖了不少好桑心！\r\n\r\n            碎片时间阅读总不能天天背着一本书吧，那么占地方。\r\n\r\n            <P>看到描述最后一段，感觉有骗答案的嫌疑<P>',123),('10',998,'obba-4kQv-DSbg2ZZm_liCEorcqk','rose',NULL,'rose2',NULL),('11',997,'B9C6A3A357534C7F9DD4C2F22FA42AD5','rose',NULL,'rose2',NULL),('12',994,'obba-4kQv-DSbg2ZZm_liCEorcqk','rose——test',NULL,'<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>',NULL),('13',994,'BEE8E17A7A794A979211DE3C4590824E','rose——test',NULL,'<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>',NULL),('14',994,'BEE8E17A7A794A979211DE3C4590824E','rose——test',NULL,'<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>',NULL),('1421404069859184642',20,'1879878-NKCNO-NKNK',NULL,NULL,'<p>测试回答问题</p><p>测试</p>',1),('1421404633124851714',20,'1879878-NKCNO-NKNK',NULL,NULL,'<p>尝试再回答一次看看情况</p>',NULL),('1421449066465837058',1,'1879878-NKCNO-NKNK',NULL,NULL,'<p>测试单个用户写其他用户的文章</p>',NULL),('1421740943857471490',20,'1879878-NKCNO-NKNK',NULL,NULL,'<p>在写一次回答，查看报错</p>',NULL),('1421788993934102529',3,'1879878-NKCNO-NKNK',NULL,NULL,'<p>测试写一条回答</p>',NULL),('1421789560173531138',3,'1879878-NKCNO-NKNK',NULL,NULL,'<p>测试navigateBack</p>',NULL),('1603421746646994',878,'BEE8E17A7A794A979211DE3C4590824E','rose',NULL,'<p>测试是否能够把answerid给传入进入</p>',NULL),('1603421880321973',878,'obba-4kQv-DSbg2ZZm_liCEorcqk','rose',NULL,'<p>测试是否能够把answerid给传入进入</p>',NULL),('1603426359480190',889,'obba-4kQv-DSbg2ZZm_liCEorcqk','test',NULL,'<p><img class=\"wscnph\" src=\"data:image/jpeg;base64,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GFJswtF908jOsCbzD/YSk3OjSGlHDWz9EQljM8dwzEr5TYTx4F4REO0NnTPwy1BCtwpDLZwffIlJudGkNKOGtn/97z3KTOOV0iPwNchHPLC/F7/6IMC2hN25ohMcaSdfuN5ozj/QWnYUAxaURsnWOgtUMRDWmJUp6PeSN0saIa7goFG+vR9kniCTQ5bzcxME7VwJr3iWvyPQ4G7JHoTM74/I19ykdkmtH4YhyvDfCU37/cjtSsCdC/o0mofrAWWJYZGn5fNxszcnGCfZ/HydMLN0lHRRKw9STgMqTER1caTUP1g+JSz9jUabn4rcfOuwolGzfd7kkyR917oaTUP1Qx1g4nB/RoysmniXssPr3IFKDNWcNZeq2HgbrkIUv69xpNQ/Vusg92Gv6JKTiLuN1fwVXR5EwOPw03EPVLLKc5lfackJ6+sPZOC0y937EFOcxJ7Jllr3waUEglYlTM9x8AkyeV03ZCev/FvQ4G7+k3oMZpf7GbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsf/2gAMAwEAAgADAAAAEPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPONPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPLz/AL+x7zz/AM8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888884880w404988888888888888888888888888888888ss/8APDHH3PfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPHPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPNPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPOO+P/NONO9NfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPLLHvDLHLLPfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPCP/NOvPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPLPHPDHPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPLdF0HfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPLPDHPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPOBDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDNiGG4I1PInHwxP5gHGBhKKEPMPNPPPPPPPPPPPPPPPPPPPPKEMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMP/8QAJREAAQMDAgcBAQAAAAAAAAAAAQAQESFB8CBgMVBRYXCx0cHh/9oACAEDAQE/ENqAwZUUhG6Imc6/UeM5f7ojhv000gzyG0aDUEMKGVCCAjeoNJQQMsaCWFVLA8jAhjVhQyoQogI3raWDXjxEFnthWUDI8R3Qr5md1nv+Kyz3+IN1eiLAu7kTndGrWXVzzz//xAAkEQABBAIBAwUBAAAAAAAAAAABABARIUHwMVBg0SBRYXCBof/aAAgBAgEBPxDtQiRCnf1gYM7jwhQjceP6i2FPPfXPqjoYoyhSJYVDGxCJnvci4RREMMMaUdCFOTLCmNome9xZhiHj6h9ljd+FAlYG+/hBBj6jwnbQHAO7vCFqix9IoFCAd+WKxD8KjjDhCj1v/8QALBAAAgEEAgIBBAEEAwEAAAAAAREAIWHR8THwQeFREGBxgTBwkbHBUIChQP/aAAgBAQABPxD7gIopcqapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqn8oXBcf2R9nIYAWQosgIBjk0v9acuxhQarzQ+IlaSeCA6uDx4+nJ2q+hxHY4HgWIJpUoUIDDhLgO0SwJ4JTFDiBM0xAPUEsCaAlAShxxAoggQeQfsTzHx/NZf+wTE5GVE8nwfPHhQDieIDggkhM/6MJOJUA6hK3xIS4HmUMUXAjkWqVSrR5hQwAEDAoOVOB4/c4Cx/mPp/EWlTIAAupxV/wDgnq51hDLwW+AuHJN8/Tk7VfQZAsdWBgSCCXCpAoIuHfAbKbEiglSKIf7iGBuRVogALFa+ABBikXLQz5AA/YooKlEGVF0tb+Lk7VfanzQCCCLZfiWOi0sdFpY6LSx0WljotLHRaWOi0sdFpY6LSx0WljotLHRaWOi0sdFpY6LSx0WljotLHRaWOi0sdFpY6LSx0WljotLHRaWOi0sdFpY6LSx0WljotLHRaWOi0sdFpY6LSx0WljotLHRaWOi0sdFpY6LSx0WljotLHRaWOi0sdFpY6LSx0WljotLHRaWOi0sdFpY6LSx0WljotLHRaWOi0sdFpY6LSx0WljotLHRaWOi0sdFpY6LSx0WljotLHRaWOi0sdFpY6LSx0Wljot/TU1kJKEhf7M2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GU2GX8vJ2q+zwAhw+mdoKBFjk+PmFkFUD+Qh8RM4EANVAg9bj8wLQSOagBCPkYQR+nJ2q+idwAhGBUe4CC0QEDFIEueVARPhNS5MAiY8QyQgBFNEseYaGWS02aBRNGrHA8UZfNgCb8UEYH6QCUoxckoSCCAYrUXg6XGCQFAzSiEfgPmVzWwE4BIAi/yP5gjCAZtIACr5D88RZGFrWwAMQKLQJgXhUwRAHa1WIF8HwSzQ+wgHFLjGNHgSKMfkQGiFG0hnJkURP+4xKDBlxJIsUAEkfAjypnkpBIoORGCiFQV+nJ2q+gfRwIVkL2FQA6hpOFAognKQIBB0eUvJgG1rAykEuQKAnhcCIICQw4glCVqLRoR/c2oQQgwQMJAyhwjGgoiDUAH4btAwFyRGsFgGCFDyDANnOyAlsqBC5P4AlAs+d5ykkARfgJgneYUKAJrUQFYcQjklJgXQIH5JJJX+yOTtV9qAkgSCSGUn+o/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jiP2OI/Y4j9jj+moJJMAkBhtfuL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5i9jmL2OYvY5/l5O1X2WG0aABIqAPBHzBgLAZg1AGI+Q4g96o/DkywiNWV8RoPUTiUiGASoQqTVxIcFbBAJIDEMlE+Eayo5RoFQa+RR8yaBPhUsUWABmg/4EMUCpZU8kiQCPh1Pk/Tk7VfRIhH2kgg5IkH4kPzBCKIDSKsEZoKpVBBaBJEgASEAKUIa/EV2ArTpQlAhgVRBgFVLMXkrmUSYJIHhcXgc1xFEYJJ5C+Q8cyzpwvQFoECjFOYBhhkAiADUheam8GJTHgoMhYASxcRn0CZCrDFohRHLrWOWhgIuz3KrEk8lf8+eeyCRFAE8A/ELheZy6sFAo/Id4ULVQABHihXA+PEO/iOqFRyPFFyxS8ZKhxPDAn4Af6g3wFGA0AwoD48VgMLQnwxc4B4JX4nwQU26AIP3+PpydqvoGgIiiAcExaa0KVWCKHI+YYUIJJkkHkn8SmnANQSoAFVZIDZAQGADXHTNkSQwIFSwfHkMQsiYwRQ/hjzB2viBUCj+BqT+obqgRVMFkC8OUfxCt4hNHnKIdqhkea8EclUMDu4EQPI1MLAS4w8ECo8jx4hTMq2T89CAFUnmw+yOTtV9qGslpUgL/Ymgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgwmgw/pr80AAAgkV4lzovLnReXOi8udF5c6Ly50XlzovLnReXOi8udF5c6Ly50XlzovLnReXOi8udF5c6Ly50XlzovLnReXOi8udF5c6Ly50XlzovLnReXOi8udF5c6Ly50XlzovLnReXOi8udF5c6Ly50XlzovLnReXOi8udF5c6Ly50XlzovLnReXOi8udF5c6Ly50XlzovLnReXOi8udF5c6Ly50XlzovLnReXOi8udF5c6Ly50XlzovLnReXOi8udF5c6Ly50XlzovLnReXOi8udF5c6L/AMAmoBwSBUkA+B5g1FoxAfII5jrBpAnlDyvrydqvs8ceREswhPwEfuDzpkYgZALQCATqIMqxfIgM0Q8MCrgFgawQhRT4E8Ac8/Tk7VfQCxqBpXHkQVKutSZVWxNFHXGGnIpV5lVE1tCECGJFCCTTnxBW0KgD8iAf2H2UC6oTChUSbd4gAIBESS6AAfMCZKYgF8Aw8eWbzk8YpApUJr48k/Tk7VfRiAqgAKiACuR5B5jImYnY5BBAWCjrnrwQDCUGUjRwvhDQP7EQk/s/Z5XAcP2T9qEWUuHNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1SapNUmqTVJqk1T/oAXrwEIJMgBMgefmUnMm7KMTUIQCaBMlyvBEUVhtRCRICqKwoKcQgUAPwzz4BXlReezMDyDwJ4A+YwKgIgbgY/ZQXgAFOZ4FQP38fZpgFJQEwmOpIsQqUehzkmd6gKIKQwf7jkATz/AN2FRm+RcQHHBCSgHyQl8KuGICioJ9VQQAMfI5gQcWkyy+aJCPAn5EF4hBEQCFI/IP2aXFFEikVRQ/QBHGiqwaBUL8zgkq5Dgh8G8IBJjwW4c/uPakrOqWQGYNMshADqaCKBmkAnhnyv+qJoHBZa4AINFHk2+0+XLly5cuXLly5cuXLly5cuXLly5cuXLly5cuXLly5cuXLly5cuXLly5cuXLly5cuXLly5cuXLly5cuXLly5cn4nH9kEaoOWwOaHApDOaqAYaXLLoipDpQ8cwLvzm/wxx1wqlqoBACWSAOSPmAiPCRCQEAJDpUG4RgKmFCgEtX8rQdgHAAzIgpN5ugV4S24N9QB44PzFWEjwYIpJqPEqYiuBqgRRA1IK5pBiM0PcBASjg0LTk/E4oQEFgnCqgRxMUqrmHziVAxXkVcHYhSFYPYFQEJ/lPwrCygxFVaBAQW7if2SvaJBAN6xChVACAV9OT1Qa/y0AXQEEp5hMjHBNIam5yFQJoQ4Sg+Svqgh8UEVYCqdanokeKUAc+TAUJXtEggG9YhQqgBAK+jMqBwQ/UceSKPhQuD5ggggQLAR/wAi6Q0UjPEHoVphKLHFaEMEOGKLyIolwQR+oSvaJBAN6xChVACAV9Hi4AeTjFQBjUkRyKoxpmEKOQgcJeFB8gZlgckwySWlR1cMqdhIttvEhgFGlUaAle0SCAb1iFCqAEAr6BACBzXlqygkBOohpRgAIFNGyPVCAFHkyw+oiQ3AIqEQDFRSsLhWK3kQNkgAINQKgotBDRXAiCfjmFMGkPyD6clriRyoUr80Y8UrARoPPhEEVuhCDHinACphz8IoAZPIeHAFMQWSfXeIAEon5IADI0VwIgn45hTBpD8g/wDvMHlgkaoaAlfIUfiCjNQGB8uCqIAA2UhGaBiZyLx8B4/y3OF94oCB4cj9/gXkQd6rAJ+C78wynlLokBABIjUGuKiFAAESYkIhRcA9Vo6OVJkCZgIT4HiqhFCP2dEOSXC1oR/ICfFEg/NEUFaIAQGjTUnuH4Ev5M5PxOKEA7JyCD8ImLp+DB4lBbUDg4QzRAdAKsNoCUGDwqPAr9CDqYUCEQKlKUE0+YRABB8EfUwQUxnCakAKCqgAWBRKySA4AilSKoFAlCPIwsFYByCeHhlAj45gtFsiMZQBHX4CvwSIAIPgj6gUnjjUychz5/U/8BZ445lZf4IVppkEgUiYpowBwATy3BWAAwhInh1QAI4PLBbIgAg+CPqfiNJyUAwD5/WsFwITCTqOWTEkiounEJ8ciwIJJAphoS/FI98H+QABDk15WBIQiACD4I+qWDUeQUApWv8AlKEqGBhB+yXn2jvLgHAmRyBfPI/EF9BsWYoeCZcjTisKCAosMcH6jy1FEbWyeFAB5bBQBASACQSYnFICS4ogoBEhDkqDIPg3VrB6AucElHyCh+IUEBRYY4P/ABfJ+JxfaZcn4gQSjyES06adNOmnTTpp006adNOmnTTpp006adNOmnTTpp006adNOmnTTpp006adNOmnTTpp006adNOmnTTpp006adNOmnTTpp006adNOmnTTpp006adNOmnTTpp006adNOmnTTpp006adNOmnTTpp006adNOmnTTp//2Q==\" /></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>自己测试测试测试</p>',NULL),('1603452869313478',883,'obba-4kQv-DSbg2ZZm_liCEorcqk','test',NULL,'<p><strong>测试封面，</strong></p>\n<p><strong>看看数据什么的</strong></p>',NULL),('1603454225761328',776,'56C02DF0516B4B079ABFCEC08169E577','test',NULL,'<p><strong>先简略的写看看数据库里面有没有，</strong>这样子依赖的话电脑</p>\n<p><em>上面的数据库就又需要更换了，目前来说是这样的，但是该如何更换呢</em></p>',NULL),('2',1,'56C02DF0516B4B079ABFCEC08169E577','Rose','rose','<P>难道不明白纸质书更贵啊！！！</P>\r\n\r\n            <P>若觉得kindle更贵，我觉得要么阅读量太少，那确实没有买kindle的必要。</P>要么买的都是盗版的纸质书？我不清楚不加以评论。。。\r\n\r\n            另外，用kindle看小说的怎么真心不懂了。题主不看小说么？难道题主拿来看教科书还是技术文档？还是题主觉得小说就是小时代内样的？（对小时代没偏见，尊重多样性）\r\n\r\n            而且纸质书搬起来真心困难啊！当初毕业带不回来，忍痛卖了不少好桑心！\r\n\r\n            碎片时间阅读总不能天天背着一本书吧，那么占地方。\r\n\r\n            <P>看到描述最后一段，感觉有骗答案的嫌疑<P>',134),('3',1,'obba-4kQv-DSbg2ZZm_liCEorcqk',NULL,'rose_test','<P>难道不明白纸质书更贵啊！！！</P>\r\n\r\n            <P>若觉得kindle更贵，我觉得要么阅读量太少，那确实没有买kindle的必要。</P>要么买的都是盗版的纸质书？我不清楚不加以评论。。。\r\n\r\n            另外，用kindle看小说的怎么真心不懂了。题主不看小说么？难道题主拿来看教科书还是技术文档？还是题主觉得小说就是小时代内样的？（对小时代没偏见，尊重多样性）\r\n\r\n            而且纸质书搬起来真心困难啊！当初毕业带不回来，忍痛卖了不少好桑心！\r\n\r\n            碎片时间阅读总不能天天背着一本书吧，那么占地方。\r\n\r\n            <P>看到描述最后一段，感觉有骗答案的嫌疑<P>',NULL),('4',2,'56C02DF0516B4B079ABFCEC08169E577','make',NULL,'<P>1，多做题，少看视频...</P> <P>2.英语基础好的买黄皮书，差的的买考研圣经（考研真相）</P>',1),('5',3,'B9C6A3A357534C7F9DD4C2F22FA42AD5',NULL,NULL,'<p>我不知道那个铁塔的情况，</p><p>不过气象铁塔上会有一些测太阳辐射的设备，如果说辐射的话，太阳辐射那么多，大家赶紧躲进地底下呀~~~~~要不然辐射量这么大，会变异的呀~~~~</p>',NULL),('6',2,'obba-4kQv-DSbg2ZZm_liCEorcqk',NULL,NULL,'<p>1.ceui </p><p>2.回答的是考研的问题</p>',NULL),('7',2,'obba-4kQv-DSbg2ZZm_liCEorcqk',NULL,NULL,'<p>2.第二次 测试，看看能不能把queid测试进去 </p><p>然后看看看进去问题二，会不会多一个</p>',NULL),('8',2,'obba-4kQv-DSbg2ZZm_liCEorcqk',NULL,NULL,'<p>1.第三次测试</p><p>2.好像那个getdata的数据还是没有获得</p>',NULL),('9',999,'B9C6A3A357534C7F9DD4C2F22FA42AD5','rose',NULL,'<p>测试数据是否入库</p>',NULL);
/*!40000 ALTER TABLE `yan_info_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_answer_comment`
--

DROP TABLE IF EXISTS `yan_info_answer_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_answer_comment` (
  `ans_comment_id` int NOT NULL AUTO_INCREMENT,
  `answer_id` varchar(100) DEFAULT NULL COMMENT '回答的id',
  `relation_id` int DEFAULT NULL COMMENT '关联评论的id',
  `is_top` tinyint DEFAULT '1' COMMENT '是否顶层',
  `comment` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '评论内容',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '评论人id',
  `show_status` int DEFAULT NULL COMMENT '逻辑删除',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ans_comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_answer_comment`
--

LOCK TABLES `yan_info_answer_comment` WRITE;
/*!40000 ALTER TABLE `yan_info_answer_comment` DISABLE KEYS */;
INSERT INTO `yan_info_answer_comment` VALUES (1,'1',NULL,1,'测试一条评论的数据','56C02DF0516B4B079ABFCEC08169E577',0,'2021-07-23 18:05:42',NULL),(3,'1',NULL,1,'测试map参数能不能这样子传递','BEE8E17A7A794A979211DE3C4590824E',0,'2021-07-23 18:05:43',NULL),(4,'2',NULL,1,'测试requestBody','BEE8E17A7A794A979211DE3C4590824E',0,'2021-07-23 18:05:44',NULL),(5,'2',NULL,1,'多两条置顶','BEE8E17A7A794A979211DE3C4590824E',0,'2021-07-23 18:05:45',NULL),(6,'2',NULL,1,'第二条数据的置顶','BEE8E17A7A794A979211DE3C4590824E',0,'2021-07-23 18:05:45',NULL),(10,'2',6,0,'第二条层级的数据','56C02DF0516B4B079ABFCEC08169E577',0,'2021-08-01 17:29:22',NULL),(11,'2',6,0,'第二条层级的数据2','B9C6A3A357534C7F9DD4C2F22FA42AD5',0,'2021-07-23 18:05:47',NULL),(12,'2',6,0,'第二条层级的数据3','56C02DF0516B4B079ABFCEC08169E577',0,'2021-08-01 17:29:19',NULL),(14,'2',NULL,0,'测试自动填充时间','B9C6A3A357534C7F9DD4C2F22FA42AD5',0,'2021-07-23 18:05:48',NULL),(15,'2',NULL,0,'测试自动填充时间2','B9C6A3A357534C7F9DD4C2F22FA42AD5',0,'2021-07-23 18:05:50','2021-07-23 09:39:40'),(16,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL),(17,'4',NULL,1,'159','1879878-NKCNO-NKNK',0,'2021-08-01 09:50:22','2021-08-01 09:50:22'),(18,'4',NULL,1,'先测试一下置顶评论如何','1879878-NKCNO-NKNK',0,'2021-08-01 09:51:49','2021-08-01 09:51:49'),(19,'1421404069859184642',NULL,1,'留言一下','56C02DF0516B4B079ABFCEC08169E577',0,'2021-08-01 10:28:51','2021-08-01 10:28:51'),(20,'1421404069859184642',NULL,1,'留言一下','B9C6A3A357534C7F9DD4C2F22FA42AD5',0,'2021-08-01 10:29:42','2021-08-01 10:29:42');
/*!40000 ALTER TABLE `yan_info_answer_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_answer_like`
--

DROP TABLE IF EXISTS `yan_info_answer_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_answer_like` (
  `like_id` int NOT NULL AUTO_INCREMENT,
  `like_user_id` varchar(100) DEFAULT NULL COMMENT '点赞的用户id',
  `like_answer_id` varchar(100) DEFAULT NULL COMMENT '点赞的回答id',
  `status` tinyint DEFAULT NULL COMMENT '1为点赞，0为未点赞',
  PRIMARY KEY (`like_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_answer_like`
--

LOCK TABLES `yan_info_answer_like` WRITE;
/*!40000 ALTER TABLE `yan_info_answer_like` DISABLE KEYS */;
INSERT INTO `yan_info_answer_like` VALUES (4,'BEE8E17A7A794A979211DE3C4590824E','1',0),(5,'1879878-NKCNO-NKNK','4',0),(6,'56C02DF0516B4B079ABFCEC08169E577','1421404069859184642',1);
/*!40000 ALTER TABLE `yan_info_answer_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_category`
--

DROP TABLE IF EXISTS `yan_info_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_category` (
  `id` int NOT NULL,
  `parent_id` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '类别名称',
  `sort_order` varchar(255) DEFAULT NULL COMMENT '排序编号',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_category`
--

LOCK TABLES `yan_info_category` WRITE;
/*!40000 ALTER TABLE `yan_info_category` DISABLE KEYS */;
INSERT INTO `yan_info_category` VALUES (10001,0,'大类书籍','0','2022-05-23 12:48:13','2022-05-23 12:48:15'),(10002,0,'娱乐','1','2022-06-09 20:36:52','2022-06-09 20:36:56'),(10003,0,'动漫','2','2022-06-09 20:37:11','2022-06-09 20:37:14'),(10004,10001,'王道考研',NULL,'2022-06-09 20:38:03','2022-06-09 20:38:05'),(10005,10001,'肖秀荣政治',NULL,'2022-06-09 20:38:27','2022-06-09 20:38:29'),(10006,10001,'考研英语',NULL,'2022-06-09 20:39:36','2022-06-09 20:39:38'),(10007,10001,'运筹学 ',NULL,'2022-06-09 20:40:08','2022-06-09 20:40:09'),(10008,10002,'篮球吧',NULL,'2022-06-09 20:40:31','2022-06-09 20:40:33'),(10009,10002,'奥数',NULL,'2022-06-09 20:40:56','2022-06-09 20:40:58'),(10010,10002,'lol',NULL,'2022-06-09 20:41:12','2022-06-09 20:41:13'),(10011,10003,'罗小黑',NULL,'2022-06-09 20:41:32','2022-06-09 20:41:34'),(10012,10003,'进击的巨人',NULL,'2022-06-09 20:42:15','2022-06-09 20:42:17'),(10013,10003,'某科学的超电磁炮',NULL,'2022-06-09 20:42:38','2022-06-09 20:42:40'),(10014,10003,'狐妖小红娘',NULL,'2022-06-09 20:43:12','2022-06-09 20:43:14'),(10015,10003,'野良神',NULL,'2022-06-09 20:43:39','2022-06-09 20:43:41'),(10016,10003,'魔法禁书目录',NULL,'2022-06-09 20:45:00','2022-06-09 20:45:04'),(10017,10003,'斗破苍穹',NULL,'2022-06-09 20:45:44','2022-06-09 20:45:46');
/*!40000 ALTER TABLE `yan_info_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_college`
--

DROP TABLE IF EXISTS `yan_info_college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_college` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code1` varchar(10) DEFAULT NULL,
  `direction` varchar(20) DEFAULT NULL,
  `name` varchar(15) DEFAULT NULL,
  `nizhao` varchar(10) DEFAULT NULL,
  `school` varchar(60) DEFAULT NULL,
  `shuju` varchar(60) DEFAULT NULL,
  `tongzhao` varchar(10) DEFAULT NULL,
  `tuimian` varchar(10) DEFAULT NULL,
  `xh` varchar(10) DEFAULT NULL,
  `yuan` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_college`
--

LOCK TABLES `yan_info_college` WRITE;
/*!40000 ALTER TABLE `yan_info_college` DISABLE KEYS */;
INSERT INTO `yan_info_college` VALUES (1,'140111','人文政治','','200','山西财经大学',NULL,'222','1',NULL,NULL);
/*!40000 ALTER TABLE `yan_info_college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_data_discovery`
--

DROP TABLE IF EXISTS `yan_info_data_discovery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_data_discovery` (
  `data_id` int NOT NULL AUTO_INCREMENT,
  `question_id` int DEFAULT NULL,
  `answer_id` bigint DEFAULT NULL,
  `feed_source_id` int DEFAULT NULL,
  `feed_source_name` varchar(15) DEFAULT NULL,
  `feed_source_txt` varchar(15) DEFAULT NULL,
  `feed_source_img` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `question` varchar(120) DEFAULT NULL,
  `answer_ctnt_short` varchar(300) DEFAULT NULL,
  `answer_ctnt` text,
  `good_num` int DEFAULT NULL,
  `comment_num` int DEFAULT NULL,
  PRIMARY KEY (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_data_discovery`
--

LOCK TABLES `yan_info_data_discovery` WRITE;
/*!40000 ALTER TABLE `yan_info_data_discovery` DISABLE KEYS */;
INSERT INTO `yan_info_data_discovery` VALUES (1,1,1,23,'Rebecca','赞了回答1','https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/1540540394516.jpeg','2020年预计硕士生扩招18.9万，这一数字意味着什么，应该如何 把握机遇','国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章.','国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章的要求没博士生那么高。...',112,18),(2,2,4,24,'Alex2','回答了问题2','https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540308141104.jpg','有哪些考研需要避开的坑','1，多做题，少看视频... 2.英语基础好的买黄皮书，差的的买考研圣经（考研真相）','1，多做题，少看视频... 2.英语基础好的买黄皮书，差的的买考研圣经（考研真相）',112,18),(3,3,5,25,'George3','赞了回答3','https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg','气象铁塔的辐射大吗？','我不知道那个铁塔的情况，不过气象铁塔上会有一些测太阳辐射的设备，如果说辐射的话，太阳辐射那么多，大家赶紧躲进地底下呀~~~~~要不然辐射量这么','我不知道那个铁塔的情况，不过气象铁塔上会有一些测太阳辐射的设备，如果说辐射的话，太阳辐射那么多，大家赶紧躲进地底下呀~~~~~要不然辐射量这么大，会变异的呀~~~~',112,18),(4,1,3,NULL,NULL,NULL,'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg','2020年预计硕士生扩招18.9万，这一数字意味着什么，应该如何 把握机遇','国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章的要求没博','国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章的要求没博士生那么高。...',123,2312),(5,1,2,NULL,NULL,NULL,'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg','2020年预计硕士生扩招18.9万，这一数字意味着什么，应该如何 把握机遇','国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章的要求没博','国内的研究生有一个很吊诡的地方，那就是，它不像美国的Master，更像是一个prePhD的学位。啥意思呢，就比如美国的很多master学位是职业导向的，有的甚至是完全授课型硕士，学生读这个就是为了找更好的工作的。但国内研究生和博士生称呼不一样，干的事情却差不多，都要搬砖，写文章，顶多对文章的要求没博士生那么高。...',222,1232),(9,994,NULL,NULL,'rose——test','开始测试',NULL,'994测试 Tiny编辑器是否有用','测试','<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>',NULL,NULL),(10,994,NULL,NULL,'rose——test','开始测试',NULL,'994测试 Tiny编辑器是否有用','测试','<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>',NULL,NULL),(11,994,NULL,NULL,'rose——test','开始测试',NULL,'994测试 Tiny编辑器是否有用','测试','<p>测<strong>试Tinymce&nbsp; &nbsp; &nbsp;</strong></p>\n<p><strong>测试一下换行，看一看内容；</strong></p>\n<p><strong>突然感觉有一个新问题，我从数据库取出来的是带着便签的，但是如果把标签给截取掉 ，然后就没有</strong></p>',NULL,NULL),(12,878,1603421880321973,NULL,'rose','answerid给',NULL,'测试是否能够把answerid给传入进入','测试','<p>测试是否能够把answerid给传入进入</p>',NULL,NULL),(13,889,1603426359480190,NULL,'test','测试封面功能','','测试封面功能','测试','<p><img class=\"wscnph\" src=\"data:image/jpeg;base64,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GFJswtF908jOsCbzD/YSk3OjSGlHDWz9EQljM8dwzEr5TYTx4F4REO0NnTPwy1BCtwpDLZwffIlJudGkNKOGtn/97z3KTOOV0iPwNchHPLC/F7/6IMC2hN25ohMcaSdfuN5ozj/QWnYUAxaURsnWOgtUMRDWmJUp6PeSN0saIa7goFG+vR9kniCTQ5bzcxME7VwJr3iWvyPQ4G7JHoTM74/I19ykdkmtH4YhyvDfCU37/cjtSsCdC/o0mofrAWWJYZGn5fNxszcnGCfZ/HydMLN0lHRRKw9STgMqTER1caTUP1g+JSz9jUabn4rcfOuwolGzfd7kkyR917oaTUP1Qx1g4nB/RoysmniXssPr3IFKDNWcNZeq2HgbrkIUv69xpNQ/Vusg92Gv6JKTiLuN1fwVXR5EwOPw03EPVLLKc5lfackJ6+sPZOC0y937EFOcxJ7Jllr3waUEglYlTM9x8AkyeV03ZCev/FvQ4G7+k3oMZpf7GbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsbTsf/2gAMAwEAAgADAAAAEPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPONPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPLz/AL+x7zz/AM8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888884880w404988888888888888888888888888888888ss/8APDHH3PfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPHPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPNPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPOO+P/NONO9NfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPLLHvDLHLLPfPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP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\" /></p>\n<p>&nbsp;</p>\n<p>&nbsp;</p>\n<p>自己测试测试测试</p>',NULL,NULL),(14,883,1603452869313478,NULL,'test','处理封面','https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/23/6061c505dbd6425ea296f0b1c83271edQQ截图3.jpg','测试封面的数据应该怎么处理','测试','<p><strong>测试封面，</strong></p>\n<p><strong>看看数据什么的</strong></p>',NULL,NULL),(15,776,1603454225761328,NULL,'test','测试缩略图有没有效果','https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/23/e6bafc70cbac4cfd83c3a2726e80c57aQQ截图.jpg','测试缩略图有没有效果','先简略的写看看数据库里面有没有，这样子依赖的话电脑上面的数据库就又需要更换了，目前来说是这样的，但是该如何更换呢','<p><strong>先简略的写看看数据库里面有没有，</strong>这样子依赖的话电脑</p>\n<p><em>上面的数据库就又需要更换了，目前来说是这样的，但是该如何更换呢</em></p>',NULL,NULL);
/*!40000 ALTER TABLE `yan_info_data_discovery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_major`
--

DROP TABLE IF EXISTS `yan_info_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_major` (
  `pri` int NOT NULL AUTO_INCREMENT,
  `id` varchar(10) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`pri`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_major`
--

LOCK TABLES `yan_info_major` WRITE;
/*!40000 ALTER TABLE `yan_info_major` DISABLE KEYS */;
INSERT INTO `yan_info_major` VALUES (4,'1','0101','学硕-哲学-哲学'),(5,'2','0201','学硕-经济学-理论经济学'),(6,'2','0202','学硕-经济学-应用经济学'),(7,'2','0251','专硕-经济学-金融'),(8,'2','0252','专硕-经济学-应用统计'),(9,'2','0253','专硕-经济学-税务'),(10,'2','0254','专硕-经济学-国际商务'),(11,'2','0255','专硕-经济学-保险'),(12,'2','0256','专硕-经济学-资产评估'),(13,'2','0257','专硕-经济学-审计'),(14,'2','0270','学硕-经济学-统计学'),(15,'3','0301','学硕-法学-法学'),(16,'3','0302','学硕-法学-政治学'),(17,'3','0303','学硕-法学-社会学'),(18,'3','0304','学硕-法学-民族学'),(19,'3','0305','学硕-法学-马克思主义理论'),(20,'3','0306','学硕-法学-公安学'),(21,'3','0351','专硕-法学-法律'),(22,'3','0352','专硕-法学-社会工作'),(23,'3','0353','专硕-法学-警务'),(24,'4','0401','学硕-教育学-教育学'),(25,'4','0402','学硕-教育学-心理学'),(26,'4','0403','学硕-教育学-体育学'),(27,'4','0451','专硕-教育学-教育'),(28,'4','0452','专硕-教育学-体育'),(29,'4','0453','专硕-教育学-汉语国际教育'),(30,'4','0454','专硕-教育学-应用心理'),(31,'4','0471','学硕-教育学-教育经济与管理'),(32,'5','0501','学硕-文学-中国语言文学'),(33,'5','0502','学硕-文学-外国语言文学'),(34,'5','0503','学硕-文学-新闻传播学'),(35,'5','0551','专硕-文学-翻译'),(36,'5','0552','专硕-文学-新闻与传播'),(37,'5','0553','专硕-文学-出版'),(38,'6','0601','学硕-历史学-考古学'),(39,'6','0602','学硕-历史学-中国史'),(40,'6','0603','学硕-历史学-世界史'),(41,'6','0651','专硕-历史学-文物与博物馆'),(42,'7','0701','学硕-理学-数学'),(43,'7','0702','学硕-理学-物理学'),(44,'7','0703','学硕-理学-化学'),(45,'7','0704','学硕-理学-天文学'),(46,'7','0705','学硕-理学-地理学'),(47,'7','0706','学硕-理学-大气科学'),(48,'7','0707','学硕-理学-海洋科学'),(49,'7','0708','学硕-理学-地球物理学'),(50,'7','0709','学硕-理学-地质学'),(51,'7','0710','学硕-理学-生物学'),(52,'7','0711','学硕-理学-系统科学'),(53,'7','0712','学硕-理学-科学技术史'),(54,'7','0713','学硕-理学-生态学'),(55,'7','0714','学硕-理学-统计学'),(56,'7','0771','学硕-理学-心理学'),(57,'7','0772','学硕-理学-力学'),(58,'7','0773','学硕-理学-材料科学与工程'),(59,'7','0774','学硕-理学-电子科学与技术'),(60,'7','0775','学硕-理学-计算机科学与技术'),(61,'7','0776','学硕-理学-环境科学与工程'),(62,'7','0777','学硕-理学-生物医学工程'),(63,'7','0778','学硕-理学-基础医学'),(64,'7','0779','学硕-理学-公共卫生与预防医学'),(65,'7','0780','学硕-理学-药学'),(66,'7','0781','学硕-理学-中药学'),(67,'7','0782','学硕-理学-医学技术'),(68,'7','0783','学硕-理学-护理学'),(69,'7','0784','学硕-理学-教育技术学'),(70,'7','0785','学硕-理学-运动人体科学'),(71,'7','0786','学硕-理学-农药学'),(72,'8','0801','学硕-工学-力学'),(73,'8','0802','学硕-工学-机械工程'),(74,'8','0803','学硕-工学-光学工程'),(75,'8','0804','学硕-工学-仪器科学与技术'),(76,'8','0805','学硕-工学-材料科学与工程'),(77,'8','0806','学硕-工学-冶金工程'),(78,'8','0807','学硕-工学-动力工程及工程热物理'),(79,'8','0808','学硕-工学-电气工程'),(80,'8','0809','学硕-工学-电子科学与技术'),(81,'8','0810','学硕-工学-信息与通信工程'),(82,'8','0811','学硕-工学-控制科学与工程'),(83,'8','0812','学硕-工学-计算机科学与技术'),(84,'8','0813','学硕-工学-建筑学'),(85,'8','0814','学硕-工学-土木工程'),(86,'8','0815','学硕-工学-水利工程'),(87,'8','0816','学硕-工学-测绘科学与技术'),(88,'8','0817','学硕-工学-化学工程与技术'),(89,'8','0818','学硕-工学-地质资源与地质工程'),(90,'8','0819','学硕-工学-矿业工程'),(91,'8','0820','学硕-工学-石油与天然气工程'),(92,'8','0821','学硕-工学-纺织科学与工程'),(93,'8','0822','学硕-工学-轻工技术与工程'),(94,'8','0823','学硕-工学-交通运输工程'),(95,'8','0824','学硕-工学-船舶与海洋工程'),(96,'8','0825','学硕-工学-航空宇航科学与技术'),(97,'8','0826','学硕-工学-兵器科学与技术'),(98,'8','0827','学硕-工学-核科学与技术'),(99,'8','0828','学硕-工学-农业工程'),(100,'8','0829','学硕-工学-林业工程'),(101,'8','0830','学硕-工学-环境科学与工程'),(102,'8','0831','学硕-工学-生物医学工程'),(103,'8','0832','学硕-工学-食品科学与工程'),(104,'8','0833','学硕-工学-城乡规划学'),(105,'8','0834','学硕-工学-风景园林学'),(106,'8','0835','学硕-工学-软件工程'),(107,'8','0836','学硕-工学-生物工程'),(108,'8','0837','学硕-工学-安全科学与工程'),(109,'8','0838','学硕-工学-公安技术'),(110,'8','0839','学硕-工学-网络空间安全'),(111,'8','0851','专硕-工学-建筑学'),(112,'8','0852','专硕-工学-工程'),(113,'8','0853','专硕-工学-城市规划'),(114,'8','0870','学硕-工学-科学技术史'),(115,'8','0871','学硕-工学-管理科学与工程'),(116,'8','0872','学硕-工学-设计学'),(117,'9','0901','学硕-农学-作物学'),(118,'9','0902','学硕-农学-园艺学'),(119,'9','0903','学硕-农学-农业资源与环境'),(120,'9','0904','学硕-农学-植物保护'),(121,'9','0905','学硕-农学-畜牧学'),(122,'9','0906','学硕-农学-兽医学'),(123,'9','0907','学硕-农学-林学'),(124,'9','0908','学硕-农学-水产'),(125,'9','0909','学硕-农学-草学'),(126,'9','0951','专硕-农学-农业'),(127,'9','0952','专硕-农学-兽医'),(128,'9','0953','专硕-农学-风景园林'),(129,'9','0954','专硕-农学-林业'),(130,'9','0970','学硕-农学-科学技术史'),(131,'9','0971','学硕-农学-环境科学与工程'),(132,'9','0972','学硕-农学-食品科学与工程'),(133,'9','0973','学硕-农学-风景园林学'),(134,'10','1001','学硕-医学-基础医学'),(135,'10','1002','学硕-医学-临床医学'),(136,'10','1003','学硕-医学-口腔医学'),(137,'10','1004','学硕-医学-公共卫生与预防医学'),(138,'10','1005','学硕-医学-中医学'),(139,'10','1006','学硕-医学-中西医结合'),(140,'10','1007','学硕-医学-药学'),(141,'10','1008','学硕-医学-中药学'),(142,'10','1009','学硕-医学-特种医学'),(143,'10','1010','学硕-医学-医学技术'),(144,'10','1011','学硕-医学-护理学'),(145,'10','1051','专硕-医学-临床医学'),(146,'10','1052','专硕-医学-口腔医学'),(147,'10','1053','专硕-医学-公共卫生'),(148,'10','1054','专硕-医学-护理'),(149,'10','1055','专硕-医学-药学'),(150,'10','1056','专硕-医学-中药学'),(151,'10','1057','专硕-医学-中医'),(152,'10','1071','学硕-医学-科学技术史'),(153,'10','1072','学硕-医学-生物医学工程'),(154,'10','1073','学硕-医学-空'),(155,'10','1074','学硕-医学-社会医学与卫生事业管理'),(156,'11','1101','学硕-军事学-军事思想及军事历史'),(157,'11','1102','学硕-军事学-战略学'),(158,'11','1103','学硕-军事学-战役学'),(159,'11','1104','学硕-军事学-战术学'),(160,'11','1105','学硕-军事学-军队指挥学'),(161,'11','1106','学硕-军事学-军制学'),(162,'11','1107','学硕-军事学-军队政治工作学'),(163,'11','1108','学硕-军事学-军事后勤学'),(164,'11','1109','学硕-军事学-军事装备学'),(165,'11','1110','学硕-军事学-军事训练学'),(166,'11','1151','专硕-军事学-军事'),(167,'12','1201','学硕-管理学-管理科学与工程'),(168,'12','1202','学硕-管理学-工商管理'),(169,'12','1203','学硕-管理学-农林经济管理'),(170,'12','1204','学硕-管理学-公共管理'),(171,'12','1205','学硕-管理学-图书情报与档案管理'),(172,'12','1251','专硕-管理学-工商管理'),(173,'12','1252','专硕-管理学-公共管理'),(174,'12','1253','专硕-管理学-会计'),(175,'12','1254','专硕-管理学-旅游管理'),(176,'12','1255','专硕-管理学-图书情报'),(177,'12','1256','专硕-管理学-工程管理'),(178,'13','1301','学硕-艺术学-艺术学理论'),(179,'13','1302','学硕-艺术学-音乐与舞蹈学'),(180,'13','1303','学硕-艺术学-戏剧与影视学'),(181,'13','1304','学硕-艺术学-美术学'),(182,'13','1305','学硕-艺术学-设计学'),(183,'13','1351','专硕-艺术学-艺术');
/*!40000 ALTER TABLE `yan_info_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_major_college`
--

DROP TABLE IF EXISTS `yan_info_major_college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_major_college` (
  `id` int NOT NULL AUTO_INCREMENT,
  `major_id` varchar(100) DEFAULT NULL,
  `grade` varchar(100) DEFAULT NULL,
  `college_name` varchar(255) DEFAULT NULL,
  `element_1` int DEFAULT NULL,
  `element_2` int DEFAULT NULL,
  `element_3` int DEFAULT NULL,
  `element_4` int DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `element_5` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_major_college`
--

LOCK TABLES `yan_info_major_college` WRITE;
/*!40000 ALTER TABLE `yan_info_major_college` DISABLE KEYS */;
/*!40000 ALTER TABLE `yan_info_major_college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_major_detail`
--

DROP TABLE IF EXISTS `yan_info_major_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_major_detail` (
  `index` int NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL,
  `code1` varchar(10) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_major_detail`
--

LOCK TABLES `yan_info_major_detail` WRITE;
/*!40000 ALTER TABLE `yan_info_major_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `yan_info_major_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_major_me`
--

DROP TABLE IF EXISTS `yan_info_major_me`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_major_me` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_major_me`
--

LOCK TABLES `yan_info_major_me` WRITE;
/*!40000 ALTER TABLE `yan_info_major_me` DISABLE KEYS */;
INSERT INTO `yan_info_major_me` VALUES (1,'法学'),(2,'会计学'),(3,'金融学'),(4,'工商管理'),(5,'商务英语'),(6,'心理学'),(7,'经济学'),(8,'管理科学与工程'),(9,'新闻'),(10,'传播'),(11,'广告'),(12,'计算机应用技术'),(13,'临床医学'),(14,'机械设计制造及其自动化');
/*!40000 ALTER TABLE `yan_info_major_me` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_major_question`
--

DROP TABLE IF EXISTS `yan_info_major_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_major_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `major_id` int DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_major_question`
--

LOCK TABLES `yan_info_major_question` WRITE;
/*!40000 ALTER TABLE `yan_info_major_question` DISABLE KEYS */;
INSERT INTO `yan_info_major_question` VALUES (1,1,'您是否具备法律职业道德，客观公正?/0'),(2,1,'你会在跟同学、同事聚餐时，想到餐盘里的食物归谁所有吗?/1'),(3,1,'你会在买一根铅笔时，想到这其实是一个买卖合同，并且需要你具有一定的行为能力。更深一步，你会想到你刚才实施了法律行为，甚至是物权行为（铅笔和钱的交付）?/1'),(4,1,'邻居家的树的树枝伸到了你家院子，你会想到这产生了一个法律问题，进而往侵权或者相邻关系方面想吗?/1'),(5,1,'您理解能力强，逻辑思维好?/0'),(6,2,'您是不是一个能沉下心去工作的人?/0'),(7,2,'您是否是一个思路清晰，逻辑性较好的人?/0'),(8,2,'在必要的沟通上，您不过于内向？能恰当地与税务局，银行沟通?/0'),(9,2,'您不过于外向，做事冲动?/0'),(10,2,'您是否能抵抗巨大的诱惑?/0'),(11,3,'您是否外向，善于与人打交道？是否具备金融营销能力?/0'),(12,3,'数学是否特别优秀?/0'),(13,3,'您是否有很多资源，包括富有的人脉，富有的财力?/0'),(14,3,'您是否接受压力较大的工作?/0'),(16,3,'即便您不具备上述条件，但您十分热爱该行业，愿意为此牺牲?/0'),(18,4,'您学习兴趣强。关心周围世界的快速变化，比如，互联网、大数据、共享经济等对社会关系、组织结构、消费偏好等方面的改变?/0'),(19,4,'您沟通能力强，喜欢与人沟通，能够条理性地分析事物和表达观点?/0'),(20,4,'您可以掌握商业分析与沟通表达能力，形成自己的逻辑体系?/0'),(21,4,'您有创新意识和创业精神。有较强的成就动机，不墨守成规，喜欢打破旧有的刻板规则?/0'),(22,4,'您有较强的主动学习意愿与能力，不浅尝辄止、人云亦云，能够主动规划自己的未来发展?/0'),(23,5,'平常是否有阅读英语书的习惯?/0'),(25,5,'是否愿意毕业后从事翻译或者编辑?/0'),(26,5,'目前的英语水平状况?/1'),(27,5,'是否能够有效地、有目的地掌握和消化新学的东西?/0'),(28,5,'是否有国外留学的想法?/0'),(29,6,'对于心理学的就业前景是否有了解?/0'),(30,6,'是否读过充足的心理学书籍?/0'),(31,6,'是否喜欢与人交流?/0'),(32,6,'是否有考心理咨询师证书的意愿?/0'),(34,6,'能否接受就业专业不对口?/0'),(35,7,'对自己的英语有自信或者对英语感兴趣吗?/0'),(36,7,'人际沟通能力如何?/0'),(37,7,'数学能力怎么样?/0'),(38,7,'是否接受跨考?/0'),(39,7,'能不能及时了解本专业前沿知识?/0'),(40,8,'逻辑思维能力强吗?/1'),(41,8,'你认为数学在日常生活中有用吗?/0'),(42,8,'能否接受工科知识?/0'),(43,8,'能够利用计算机解决复杂问题吗?/0'),(44,8,'是否经常考虑决策的最优性?/0'),(45,9,'是否具有新闻学理论知识背景?/0'),(46,9,'对主流新闻观是否有自己的认知?/0'),(47,9,'是否了解一名新闻工作者的工作?/0'),(48,9,'对新闻的热爱程度?/1'),(49,9,'文学素养如何?/1'),(50,10,'是否对传播学有基础的认识?/0'),(51,10,'对当代主流媒体是否有自己的评价?/0'),(52,10,'是否对网络舆论一边倒现状有清楚的认知?/0'),(53,10,'是否对传播学基础理论的了解?/0'),(54,10,'是否了解传播学的就业前景?/0'),(55,11,'对广告历史发展理论的了解程度?/0'),(56,11,'是否对当代广告有自己的认知?/0'),(57,11,'是否掌握一些设计软件?/0'),(58,11,'观看广告时是否能有想法?/0'),(59,11,'认为广告重实践还是重理论?/2'),(60,12,'对计算机应用技术的就业要求是否有了解?/0'),(61,12,'对编程是否有足够的兴趣?/0'),(62,12,'逻辑思维如何?/1'),(63,12,'是否做过计算机相关的工程或课题?/0'),(64,12,'是否有擅长的领域（如前端、后端、运维)?/0'),(65,13,'你认为你是一个细致的人吗?/0'),(66,13,'能否忍受毕业后长期从事基础工作?/0'),(67,13,'对基础药理学的掌握程度?/1'),(68,13,'是否拥有医院的实习经历?/0'),(69,13,'能否长时间做同一件事?/0'),(70,14,'对机械设计制造及其自动化的就业要求是否了解?/0'),(71,14,'动手能力强弱?/1'),(72,14,'是否拥有充足的物理化学知识?/0'),(73,14,'是否参与过工程类项目或实习?/0'),(74,14,'是否能接受大量考试?/0');
/*!40000 ALTER TABLE `yan_info_major_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_order`
--

DROP TABLE IF EXISTS `yan_info_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_order` (
  `order_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单表主键id',
  `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
  `openid` varchar(100) NOT NULL DEFAULT '0' COMMENT '用户主键id',
  `total_price` int NOT NULL DEFAULT '1' COMMENT '订单总价',
  `pay_status` tinyint NOT NULL DEFAULT '0' COMMENT '支付状态:0.未支付,1.支付成功,-1:支付失败',
  `pay_type` tinyint NOT NULL DEFAULT '0' COMMENT '0.无 1.支付宝支付 2.微信支付',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `order_status` tinyint NOT NULL DEFAULT '0' COMMENT '订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭',
  `extra_info` varchar(100) NOT NULL DEFAULT '' COMMENT '订单body',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_order`
--

LOCK TABLES `yan_info_order` WRITE;
/*!40000 ALTER TABLE `yan_info_order` DISABLE KEYS */;
INSERT INTO `yan_info_order` VALUES (15,'string','1879878-NKCNO-NKNK',1000,0,0,NULL,0,'无',0,'2022-06-20 15:36:02','2022-06-20 15:36:02'),(16,'','0',1,0,0,NULL,0,'',0,'2022-06-20 15:32:14','2022-06-20 15:36:16');
/*!40000 ALTER TABLE `yan_info_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_order_item`
--

DROP TABLE IF EXISTS `yan_info_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_order_item` (
  `order_item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单关联购物项主键id',
  `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单主键id',
  `goods_id` bigint NOT NULL DEFAULT '0' COMMENT '关联商品id',
  `goods_name` varchar(200) NOT NULL DEFAULT '' COMMENT '下单时商品的名称(订单快照)',
  `goods_cover_img` varchar(200) NOT NULL DEFAULT '' COMMENT '下单时商品的主图(订单快照)',
  `selling_price` int NOT NULL DEFAULT '1' COMMENT '下单时商品的价格(订单快照)',
  `goods_count` int NOT NULL DEFAULT '1' COMMENT '数量(订单快照)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_order_item`
--

LOCK TABLES `yan_info_order_item` WRITE;
/*!40000 ALTER TABLE `yan_info_order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `yan_info_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_product`
--

DROP TABLE IF EXISTS `yan_info_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_product` (
  `id` int NOT NULL COMMENT '商品id\r\n',
  `category_id` int NOT NULL COMMENT '对应的category',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `image` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `detail` text COMMENT '商品详情',
  `stock` int DEFAULT NULL COMMENT '库存数量',
  `status` int DEFAULT '1' COMMENT '商品状态 1 在售 0 下架',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_product`
--

LOCK TABLES `yan_info_product` WRITE;
/*!40000 ALTER TABLE `yan_info_product` DISABLE KEYS */;
INSERT INTO `yan_info_product` VALUES (1,10004,'王道考研书籍',NULL,20.00,NULL,10,1,'2022-05-23 12:44:34','2022-05-23 12:44:37'),(2,10004,'操作系统',NULL,25.00,'详细介绍了操作系统xxx',10,1,'2022-06-19 14:49:06','2022-06-19 14:49:09'),(3,10008,'詹姆斯传记',NULL,20.00,NULL,12,1,'2022-06-19 14:51:25','2022-06-19 14:51:28'),(4,10008,'篮球先锋报',NULL,15.00,NULL,13,1,'2022-06-19 14:52:17','2022-06-19 14:52:19'),(5,10008,'灌篮',NULL,12.00,NULL,15,1,'2022-06-19 14:52:48','2022-06-19 14:52:51'),(6,10008,'体坛周报',NULL,15.00,NULL,15,1,'2022-06-19 14:53:18','2022-06-19 14:53:20'),(7,10010,'青钢影',NULL,16.00,NULL,12,1,'2022-06-19 14:53:48','2022-06-19 14:53:51'),(8,10010,'剑姬',NULL,13.00,NULL,12,1,'2022-06-19 14:54:14','2022-06-19 14:54:17'),(9,10010,'艾克',NULL,20.00,NULL,15,1,'2022-06-19 14:54:45','2022-06-19 14:54:47'),(10,10010,'寡妇',NULL,9.00,NULL,15,1,'2022-06-19 14:55:08','2022-06-19 14:55:11'),(11,10017,'萧炎',NULL,8.00,NULL,30,1,'2022-06-19 14:55:33','2022-06-19 14:55:35'),(12,10016,'超炮',NULL,30.00,NULL,10,1,'2022-06-19 14:56:06','2022-06-19 14:56:08');
/*!40000 ALTER TABLE `yan_info_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_question`
--

DROP TABLE IF EXISTS `yan_info_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_question` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `openid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户id',
  `que_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '主标题',
  `que_content` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '问题副标题',
  `que_tag_id` int DEFAULT NULL COMMENT '标签的id',
  `que_view` int DEFAULT NULL COMMENT '问题阅读数量',
  `que_collect` int DEFAULT NULL COMMENT '问题收藏数量',
  `feed_source_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '问题的展示图片',
  `que_comment` int DEFAULT NULL,
  `que_answer_list_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_question`
--

LOCK TABLES `yan_info_question` WRITE;
/*!40000 ALTER TABLE `yan_info_question` DISABLE KEYS */;
INSERT INTO `yan_info_question` VALUES (1,'56C02DF0516B4B079ABFCEC08169E577','2020年预计硕士生扩招18.9万，这一数字意味着什么，应该如何 把握机遇',' WEB前端*不靠谱天气预报员*',NULL,NULL,100,'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg',NULL,NULL),(2,'B9C6A3A357534C7F9DD4C2F22FA42AD5','有哪些考研需要避开的坑','有哪些考研需要避开的坑 ',1,332,120,'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg',121,NULL),(3,'B9C6A3A357534C7F9DD4C2F22FA42AD5','气象铁塔的辐射大吗？','气象铁塔的辐射大吗？',NULL,NULL,321,'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2020/10/07/mmexport1540703510067.jpg',NULL,NULL),(8,NULL,'测试后端数据是否录入','测试',NULL,NULL,15,NULL,NULL,NULL),(9,NULL,'测试后端2','测试2',NULL,NULL,1651,NULL,NULL,NULL),(10,NULL,'测试后端2','测试2',NULL,NULL,26,NULL,NULL,NULL),(11,NULL,'994测试 Tiny编辑器是否有用','开始测试',NULL,NULL,23,NULL,NULL,NULL),(12,NULL,'994测试 Tiny编辑器是否有用','开始测试',NULL,NULL,23,NULL,NULL,NULL),(13,NULL,'994测试 Tiny编辑器是否有用','开始测试',NULL,NULL,65,NULL,NULL,NULL),(14,NULL,'测试是否能够把answerid给传入进入','测试是否能够把answerid给传入进入',NULL,NULL,615,NULL,NULL,NULL),(15,NULL,'测试是否能够把answerid给传入进入','answerid给',NULL,NULL,152,NULL,NULL,NULL),(16,NULL,'测试封面功能','测试封面功能',NULL,NULL,12,NULL,NULL,NULL),(17,NULL,'测试封面的数据应该怎么处理','处理封面',NULL,NULL,315,NULL,NULL,NULL),(18,NULL,'测试缩略图有没有效果','测试缩略图有没有效果',NULL,NULL,51,NULL,NULL,NULL),(20,'1879878-NKCNO-NKNK','请求的第一个问题','tets',NULL,NULL,NULL,'https://edu-renyun.oss-cn-beijing.aliyuncs.com/2021/07/31/43744bc6d8044d58bf6ea3027464248bChMkJ1ZdCsGIbabrAAOrwsWw18EAAFjoQAh9EgAA6va022.jpg',NULL,NULL);
/*!40000 ALTER TABLE `yan_info_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_question_tag`
--

DROP TABLE IF EXISTS `yan_info_question_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_question_tag` (
  `tag_id` int NOT NULL AUTO_INCREMENT,
  `que_tag` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_question_tag`
--

LOCK TABLES `yan_info_question_tag` WRITE;
/*!40000 ALTER TABLE `yan_info_question_tag` DISABLE KEYS */;
INSERT INTO `yan_info_question_tag` VALUES (1,'阅读'),(2,'电子书'),(3,'考研政治'),(4,'考研数学'),(5,'考研计算机');
/*!40000 ALTER TABLE `yan_info_question_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_info_question_tags`
--

DROP TABLE IF EXISTS `yan_info_question_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_info_question_tags` (
  `id` int NOT NULL AUTO_INCREMENT,
  `que_id` int DEFAULT NULL,
  `tag_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_info_question_tags`
--

LOCK TABLES `yan_info_question_tags` WRITE;
/*!40000 ALTER TABLE `yan_info_question_tags` DISABLE KEYS */;
INSERT INTO `yan_info_question_tags` VALUES (1,20,5);
/*!40000 ALTER TABLE `yan_info_question_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_message_record`
--

DROP TABLE IF EXISTS `yan_message_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_message_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `openid` varchar(32) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `message_type` tinyint DEFAULT NULL COMMENT '1.文本， 2.图片',
  `content` varchar(256) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_message_record`
--

LOCK TABLES `yan_message_record` WRITE;
/*!40000 ALTER TABLE `yan_message_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `yan_message_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_mq_consumer_log`
--

DROP TABLE IF EXISTS `yan_mq_consumer_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_mq_consumer_log` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `topic` varchar(50) DEFAULT NULL COMMENT '主题',
  `tags` varchar(50) DEFAULT NULL COMMENT '标签',
  `keys` varchar(100) DEFAULT NULL COMMENT '消息体唯一标识',
  `body` text COMMENT '消息体',
  `consume_times` tinyint(1) DEFAULT '0' COMMENT '消费次数',
  `exception` text COMMENT '失败原因',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态：0未处理，1处理中，2处理失败， 3处理成功',
  `version` int DEFAULT '1' COMMENT '乐观锁',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_mq_consumer_log`
--

LOCK TABLES `yan_mq_consumer_log` WRITE;
/*!40000 ALTER TABLE `yan_mq_consumer_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `yan_mq_consumer_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_mq_producer_temp`
--

DROP TABLE IF EXISTS `yan_mq_producer_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_mq_producer_temp` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `topic` varchar(50) DEFAULT NULL COMMENT '主题',
  `tags` varchar(50) DEFAULT NULL COMMENT '标签',
  `keys` varchar(100) DEFAULT NULL COMMENT '消息体唯一标识',
  `body` text COMMENT '消息体',
  `exception` text COMMENT '异常原因',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1354013274852601859 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_mq_producer_temp`
--

LOCK TABLES `yan_mq_producer_temp` WRITE;
/*!40000 ALTER TABLE `yan_mq_producer_temp` DISABLE KEYS */;
/*!40000 ALTER TABLE `yan_mq_producer_temp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_push_message`
--

DROP TABLE IF EXISTS `yan_push_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_push_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `user_openid` varchar(100) DEFAULT NULL COMMENT '发布者自己的id',
  `content` varchar(100) DEFAULT NULL COMMENT '聊天内容',
  `is_read` int DEFAULT NULL COMMENT '对方是否接受到',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_push_message`
--

LOCK TABLES `yan_push_message` WRITE;
/*!40000 ALTER TABLE `yan_push_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `yan_push_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yan_shop_cart`
--

DROP TABLE IF EXISTS `yan_shop_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yan_shop_cart` (
  `cart_item_id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物项主键id',
  `openid` varchar(255) NOT NULL COMMENT '用户主键id',
  `goods_id` bigint NOT NULL DEFAULT '0' COMMENT '关联商品id',
  `goods_count` int NOT NULL DEFAULT '1' COMMENT '数量(最大为5)',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
  PRIMARY KEY (`cart_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yan_shop_cart`
--

LOCK TABLES `yan_shop_cart` WRITE;
/*!40000 ALTER TABLE `yan_shop_cart` DISABLE KEYS */;
INSERT INTO `yan_shop_cart` VALUES (15,'1879878-NKCNO-NKNK',1,5,0,'2022-06-20 15:46:57','2022-06-20 15:46:57');
/*!40000 ALTER TABLE `yan_shop_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'yan'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-25 11:24:41
