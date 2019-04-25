/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_learn

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-04-25 18:18:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'chinese');
INSERT INTO `course` VALUES ('2', 'math');
INSERT INTO `course` VALUES ('3', 'english');
INSERT INTO `course` VALUES ('4', 'physics');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `student_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('1', '2', '78');
INSERT INTO `score` VALUES ('1', '3', '67');
INSERT INTO `score` VALUES ('1', '4', '67');
INSERT INTO `score` VALUES ('2', '1', '52');
INSERT INTO `score` VALUES ('2', '2', '81');
INSERT INTO `score` VALUES ('2', '3', '92');
INSERT INTO `score` VALUES ('2', '4', '67');
INSERT INTO `score` VALUES ('3', '1', '52');
INSERT INTO `score` VALUES ('3', '2', '47');
INSERT INTO `score` VALUES ('3', '3', '88');
INSERT INTO `score` VALUES ('3', '4', '67');
INSERT INTO `score` VALUES ('4', '2', '88');
INSERT INTO `score` VALUES ('4', '3', '90');
INSERT INTO `score` VALUES ('4', '4', '67');
INSERT INTO `score` VALUES ('5', '1', '52');
INSERT INTO `score` VALUES ('5', '3', '78');
INSERT INTO `score` VALUES ('5', '4', '67');
INSERT INTO `score` VALUES ('6', '1', '52');
INSERT INTO `score` VALUES ('6', '2', '68');
INSERT INTO `score` VALUES ('6', '4', '67');
INSERT INTO `score` VALUES ('1', '1', '52');
INSERT INTO `score` VALUES ('5', '2', '72');
INSERT INTO `score` VALUES ('7', '2', '72');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'lilei', '19', 'female');
INSERT INTO `student` VALUES ('2', 'hanmeimei', '18', 'male');
INSERT INTO `student` VALUES ('3', 'polly', '17', 'female');
INSERT INTO `student` VALUES ('4', 'tom', '18', 'male');
INSERT INTO `student` VALUES ('5', 'david', '17', 'male');
INSERT INTO `student` VALUES ('6', 'lucy', '19', 'female');
INSERT INTO `student` VALUES ('7', 'jacky', '25', 'male');
INSERT INTO `student` VALUES ('8', 'jdbc', '24', 'male');
