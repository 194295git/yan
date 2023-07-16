-- ----------------------------
-- 重要：程序启动自加载该脚本文件，程序中以';;' 双分号区分隔脚本依次执行，如需在文件中另添加脚本则必须以双分号';;'标识结束。
-- ----------------------------
-- 修改列所用存储过程 修改列及增加列时需借助存储过程判断字段的存在性，mysql本身没有特定的语法去判断字段的存在性
-- 当前数据库 TableName表名 ColumnName列名
-- 新增列
-- CALL Pro_Temp_ColumnWork ('表名','字段名','int(11) NULL DEFAULT NULL AFTER `xxxxxxxxxxx`; ', 1);
-- 删除列
-- CALL Pro_Temp_ColumnWork ('e_handcard_control','EntranceType','', 3);


CREATE TABLE IF NOT EXISTS `user`  (
  `name_a` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  ,
  `age_a` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ,
  `sex_a` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC comment='用户表';;

