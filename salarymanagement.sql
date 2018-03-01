/*
Navicat MySQL Data Transfer

Source Server         : themysql
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : salarymanagement

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-02-28 22:36:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `pno` int(11) NOT NULL COMMENT '员工号',
  `time` varchar(10) NOT NULL COMMENT '时间',
  `latecount` int(11) DEFAULT NULL COMMENT '迟到时间',
  `learlyount` int(11) DEFAULT NULL COMMENT '早退时间',
  `leavecount` int(11) DEFAULT NULL COMMENT '请假时间',
  `overtimecount` int(11) DEFAULT NULL COMMENT '加班时间',
  PRIMARY KEY (`pno`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('1', '2017-1', '1', '4', '5', '1');
INSERT INTO `attendance` VALUES ('1', '2017-2', '2', '1', '0', '5');
INSERT INTO `attendance` VALUES ('1', '2017-4', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('1', '2017-5', '1', '0', '0', '2');
INSERT INTO `attendance` VALUES ('1', '2017-8', '2', '3', '4', '23');
INSERT INTO `attendance` VALUES ('1', '2017-9', '0', '2', '0', '2');
INSERT INTO `attendance` VALUES ('2', '2017-1', '0', '0', '0', '2');
INSERT INTO `attendance` VALUES ('2', '2017-2', '1', '1', '2', '0');
INSERT INTO `attendance` VALUES ('2', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('3', '2017-1', '0', '0', '1', '2');
INSERT INTO `attendance` VALUES ('3', '2017-2', '0', '0', '0', '5');
INSERT INTO `attendance` VALUES ('3', '2017-6', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('4', '2017-1', '0', '0', '2', '1');
INSERT INTO `attendance` VALUES ('4', '2017-7', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('5', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('5', '2017-8', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('6', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('6', '2017-9', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('7', '2017-2', '1', '1', '1', '2');
INSERT INTO `attendance` VALUES ('7', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('8', '2017-2', '1', '1', '1', '2');
INSERT INTO `attendance` VALUES ('8', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('9', '2017-3', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('9', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('10', '2017-1', '1', '1', '1', '1');
INSERT INTO `attendance` VALUES ('10', '2017-10', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('10', '2017-11', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('10', '2017-12', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('10', '2017-2', '1', '1', '1', '2');
INSERT INTO `attendance` VALUES ('10', '2017-3', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('10', '2017-4', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('10', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('10', '2017-6', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('10', '2017-7', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('10', '2017-8', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('10', '2017-9', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('11', '2017-1', '1', '1', '1', '1');
INSERT INTO `attendance` VALUES ('11', '2017-10', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('11', '2017-11', '1', '0', '0', '1');
INSERT INTO `attendance` VALUES ('11', '2017-12', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('11', '2017-2', '1', '1', '1', '2');
INSERT INTO `attendance` VALUES ('11', '2017-3', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('11', '2017-4', '1', '0', '3', '0');
INSERT INTO `attendance` VALUES ('11', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('11', '2017-6', '1', '2', '0', '0');
INSERT INTO `attendance` VALUES ('11', '2017-7', '1', '0', '5', '0');
INSERT INTO `attendance` VALUES ('11', '2017-8', '1', '2', '0', '0');
INSERT INTO `attendance` VALUES ('11', '2017-9', '1', '0', '0', '7');
INSERT INTO `attendance` VALUES ('12', '2017-1', '1', '4', '5', '1');
INSERT INTO `attendance` VALUES ('12', '2017-2', '2', '1', '0', '5');
INSERT INTO `attendance` VALUES ('12', '2017-4', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('12', '2017-5', '1', '0', '0', '2');
INSERT INTO `attendance` VALUES ('12', '2017-8', '2', '3', '4', '6');
INSERT INTO `attendance` VALUES ('13', '2017-1', '0', '0', '1', '2');
INSERT INTO `attendance` VALUES ('13', '2017-2', '0', '0', '0', '5');
INSERT INTO `attendance` VALUES ('13', '2017-6', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('14', '2017-1', '0', '0', '2', '1');
INSERT INTO `attendance` VALUES ('14', '2017-7', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('15', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('15', '2017-8', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('16', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('16', '2017-9', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('17', '2017-2', '1', '1', '1', '2');
INSERT INTO `attendance` VALUES ('17', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('18', '2017-2', '1', '1', '1', '2');
INSERT INTO `attendance` VALUES ('18', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('19', '2017-3', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('19', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('20', '2017-1', '1', '1', '1', '1');
INSERT INTO `attendance` VALUES ('20', '2017-10', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('20', '2017-11', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('20', '2017-12', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('20', '2017-2', '1', '1', '1', '2');
INSERT INTO `attendance` VALUES ('20', '2017-3', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('20', '2017-4', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('20', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('20', '2017-6', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('20', '2017-7', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('20', '2017-8', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('20', '2017-9', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('21', '2017-1', '0', '0', '0', '0');
INSERT INTO `attendance` VALUES ('21', '2017-12', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('21', '2017-2', '1', '1', '2', '0');
INSERT INTO `attendance` VALUES ('21', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('22', '2017-10', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('23', '2017-1', '1', '1', '1', '1');
INSERT INTO `attendance` VALUES ('23', '2017-10', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('23', '2017-11', '1', '0', '0', '1');
INSERT INTO `attendance` VALUES ('23', '2017-12', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('23', '2017-2', '1', '1', '1', '2');
INSERT INTO `attendance` VALUES ('23', '2017-3', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('23', '2017-4', '1', '0', '3', '0');
INSERT INTO `attendance` VALUES ('23', '2017-5', '1', '0', '0', '0');
INSERT INTO `attendance` VALUES ('23', '2017-6', '1', '2', '0', '0');
INSERT INTO `attendance` VALUES ('23', '2017-7', '1', '0', '5', '0');
INSERT INTO `attendance` VALUES ('23', '2017-8', '1', '2', '0', '0');
INSERT INTO `attendance` VALUES ('23', '2017-9', '1', '0', '0', '7');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dno` int(11) NOT NULL COMMENT '部门编号',
  `dname` varchar(255) NOT NULL DEFAULT 'no' COMMENT '部门名称',
  `pno` int(11) DEFAULT NULL COMMENT '主管该部门的员工的编号',
  PRIMARY KEY (`dno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('0', '无部门', null);
INSERT INTO `department` VALUES ('1', '市场部', '1');
INSERT INTO `department` VALUES ('2', '财政部', '10');
INSERT INTO `department` VALUES ('3', '技术部', '16');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `pno` int(11) NOT NULL COMMENT '员工编号',
  `name` varchar(255) NOT NULL DEFAULT '无名' COMMENT '员工姓名',
  `position` varchar(255) DEFAULT '临时工' COMMENT '员工职务',
  `thetime` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '入职时间',
  `dno` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '所属部门编号',
  PRIMARY KEY (`pno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '王伟', '经理', '00000000009', '00000000001');
INSERT INTO `employee` VALUES ('2', '张伟', '组长', '00000000005', '00000000001');
INSERT INTO `employee` VALUES ('3', '王芳', '组长', '00000000004', '00000000001');
INSERT INTO `employee` VALUES ('4', '张倩', '副经理', '00000000004', '00000000001');
INSERT INTO `employee` VALUES ('5', '李静', '职工', '00000000002', '00000000001');
INSERT INTO `employee` VALUES ('6', '李勇', '副组长', '00000000003', '00000000001');
INSERT INTO `employee` VALUES ('7', '万亮', '职工', '00000000002', '00000000001');
INSERT INTO `employee` VALUES ('8', '阿光', '职工', '00000000001', '00000000001');
INSERT INTO `employee` VALUES ('9', '小灰灰', '临时工', '00000000000', '00000000001');
INSERT INTO `employee` VALUES ('10', '任天一', '经理', '00000000007', '00000000002');
INSERT INTO `employee` VALUES ('11', '秦宇辉', '副经理', '00000000006', '00000000002');
INSERT INTO `employee` VALUES ('12', '王超', '组长', '00000000004', '00000000002');
INSERT INTO `employee` VALUES ('13', '王涛', '副组长', '00000000004', '00000000002');
INSERT INTO `employee` VALUES ('14', '刘杰', '职工', '00000000003', '00000000002');
INSERT INTO `employee` VALUES ('15', '宋奇', '临时工', '00000000000', '00000000002');
INSERT INTO `employee` VALUES ('16', '陈子越', '经理', '00000000006', '00000000003');
INSERT INTO `employee` VALUES ('17', '陈颖聪', '副经理', '00000000005', '00000000003');
INSERT INTO `employee` VALUES ('18', '王永民', '副经理', '00000000006', '00000000003');
INSERT INTO `employee` VALUES ('19', '秦强', '组长', '00000000004', '00000000003');
INSERT INTO `employee` VALUES ('20', '房子安', '副组长', '00000000003', '00000000003');
INSERT INTO `employee` VALUES ('21', '秦宇辉', '职工', '00000000001', '00000000003');
INSERT INTO `employee` VALUES ('22', '小灰灰', '临时工', '00000000000', '00000000003');
INSERT INTO `employee` VALUES ('23', '任天一', '临时工', '00000000000', '00000000003');
INSERT INTO `employee` VALUES ('24', '王哈哈', '临时工', '00000000000', '00000000001');
INSERT INTO `employee` VALUES ('25', '王哈', '临时工', '00000000000', '00000000001');

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `pno` int(11) NOT NULL COMMENT '员工编号',
  `time` varchar(10) NOT NULL DEFAULT '' COMMENT '时间（格式为年-月）',
  `bsalary` decimal(10,2) DEFAULT NULL COMMENT '基本工资',
  `dsalary` decimal(10,2) DEFAULT NULL COMMENT '部门奖金',
  `psalary` decimal(10,2) DEFAULT NULL COMMENT '职务工资',
  `pfund` decimal(10,2) DEFAULT NULL COMMENT '公积金',
  `insurance` decimal(10,2) DEFAULT NULL COMMENT '保险金',
  `asalary` decimal(10,2) DEFAULT NULL COMMENT '考勤工资（可为负）',
  `besalary` decimal(10,2) DEFAULT NULL COMMENT '税前工资',
  `afsalary` decimal(10,2) DEFAULT NULL COMMENT '税后工资',
  `allsalary` decimal(10,2) DEFAULT NULL COMMENT '总工资（一个企业该月对其支出的总金额）',
  PRIMARY KEY (`pno`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES ('1', '2017-5', '2000.00', '1500.00', '-1870.00', '-970.00', '5000.00', '-50.00', '5610.00', '5504.00', '8450.00');
INSERT INTO `salary` VALUES ('1', '2017-8', '2000.00', '1500.00', '-1870.00', '-970.00', '5000.00', '900.00', '5750.00', '5630.00', '8590.00');
INSERT INTO `salary` VALUES ('1', '2017-9', '1900.00', '1500.00', '-1783.00', '-923.00', '5000.00', '240.00', '5934.00', '5795.60', '8640.00');
INSERT INTO `salary` VALUES ('2', '2017-1', '1500.00', '1500.00', '-1435.00', '-735.00', '2000.00', '0.00', '2830.00', '2830.00', '5000.00');
INSERT INTO `salary` VALUES ('2', '2017-2', '1500.00', '1500.00', '-1435.00', '-735.00', '2000.00', '60.00', '2890.00', '2890.00', '5060.00');
INSERT INTO `salary` VALUES ('7', '2017-5', '1200.00', '1500.00', '-1174.00', '-594.00', '800.00', '-50.00', '1682.00', '1682.00', '3450.00');
INSERT INTO `salary` VALUES ('11', '2017-1', '1600.00', '1100.00', '-1522.00', '-782.00', '3000.00', '-10.00', '3386.00', '3386.00', '5690.00');

-- ----------------------------
-- View structure for gongziguanli
-- ----------------------------
DROP VIEW IF EXISTS `gongziguanli`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `gongziguanli` AS select `employee`.`name` AS `name`,`employee`.`position` AS `position`,`employee`.`thetime` AS `thetime`,`employee`.`pno` AS `pno`,`attendance`.`time` AS `time`,`attendance`.`latecount` AS `latecount`,`attendance`.`learlyount` AS `learlyount`,`attendance`.`leavecount` AS `leavecount`,`attendance`.`overtimecount` AS `overtimecount`,`department`.`dname` AS `dname`,`employee`.`dno` AS `dno` from ((`employee` left join `attendance` on((`employee`.`pno` = `attendance`.`pno`))) left join `department` on((`employee`.`dno` = `department`.`dno`))) ;

-- ----------------------------
-- View structure for info
-- ----------------------------
DROP VIEW IF EXISTS `info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `info` AS select `employee`.`pno` AS `pno`,`employee`.`name` AS `name`,`department`.`dname` AS `dname`,`salary`.`time` AS `time`,`salary`.`bsalary` AS `bsalary`,`salary`.`dsalary` AS `dsalary`,`salary`.`pfund` AS `pfund`,`salary`.`insurance` AS `insurance`,`salary`.`psalary` AS `psalary`,`salary`.`asalary` AS `asalary`,`salary`.`besalary` AS `besalary`,`salary`.`afsalary` AS `afsalary`,`salary`.`allsalary` AS `allsalary` from ((`employee` join `department` on((`employee`.`dno` = `department`.`dno`))) join `salary` on((`employee`.`pno` = `salary`.`pno`))) ;
SET FOREIGN_KEY_CHECKS=1;
