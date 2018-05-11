/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2018-05-11 23:14:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article_info`
-- ----------------------------
DROP TABLE IF EXISTS `article_info`;
CREATE TABLE `article_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` varchar(20000) DEFAULT NULL,
  `content_text` varchar(200) DEFAULT NULL,
  `cover` varchar(100) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_info
-- ----------------------------
INSERT INTO `article_info` VALUES ('1', 'BILIBILI WORLD 2018 情报解禁止！', '<p></p><p><img src=\"/upload/2018/04/27/76ce7c83-55ad-4cb3-b9c3-1a7ef21578a9.png\"><br></p><p>BILIBILI WORLD 2018 预购规则</p><p>BILIBILI WORLD 2018共分2档票种，分别为：</p><p>VIP票450元、普通票98元。</p><p>注册会员及正式会员均可参与此次预购活动。</p><p>*所有用户最多可预购所有档位的门票共4张。</p><p>*门票为电子票，请保管好电子票二维码，切勿在公共平台发布。入场时请向工作人员出示电子票二维码，电子票截图无法入场。</p><p>参与抢票</p><p>1）注册会员及正式会员均可参与此次预购活动，购买时请注意选择正确的场次。</p><p>2）全平台会员预购开放时间为：6月11日20:00，售完为止。</p><p>3）预购门票前请确保已登录账号，以免错失预购时机。</p><p>4）用户可于哔哩哔哩手机客户端（5.24版本及以上）提前完善个人信息：点击会员购中心，选择购买人信息进行填写。</p><p>5）购买人信息仅限填写大陆地区身份证号及手机号。</p><p>付款流程</p><p>1）成功拍下门票后，请在1分钟内填写购票人信息，并进入付款页面，如未能及时填写完毕，则该门票失效并回到票池。</p><p>2）个人信息填写完毕并进入付款页面后，请在5分钟内完成支付（支付宝、微信、QQ钱包渠道付款，B币余额无法付款，请勿直接充值B币）。5分钟内如未完成支付，则订单失效，门票将回到票池。</p><p>3）成功支付后，即可在订单中心查询相关购票信息。</p><p>查看电子票</p><p>1）电子票二维码可在订单中心内查询。</p><p>测试字数，嘿嘿</p><p></p>', 'BILIBILI WORLD 2018 预购规则BILIBILI WORLD 2018共分2档票种，分别为：VIP票450元、普通票98元。注册会员及正式会员均可参与此次预购活动。*所有用户最多可预购', 'http://localhost:8080/upload/2018/04/27/b53c9682-11a6-4117-ac5d-f815e3caee7d.png', '6', '2018-05-04 14:12:42', '1', '3');
INSERT INTO `article_info` VALUES ('2', '｡:.ﾟヽ(｡◕‿◕｡)ﾉﾟ.:｡你等的那个#话题#功能上线了', '<p></p><p><img src=\"/upload/2018/04/27/02947f5b-9ade-4e45-bae6-3159c894d26b.jpg\"><span style=\"color: rgb(0, 0, 0); font-family: monospace; font-size: medium; white-space: pre-wrap;\"><br></span></p><p><span style=\"color: rgb(0, 0, 0); font-family: monospace; font-size: medium; white-space: pre-wrap;\">好消息~好消息，动态#话题#上线啦从前你是不是也会有和动态娘一样的烦恼，喜欢的内容不知道和谁讨论；流行的表情包不知道去哪找；拍了萌宠的照片不知道和谁分享。余一我喜欢你啊！！！</span><br></p><p></p>', '好消息~好消息，动态#话题#上线啦从前你是不是也会有和动态娘一样的烦恼，喜欢的内容不知道和谁讨论；流行的表情包不知道去哪找；拍了萌宠的照片不知道和谁分享。余一我喜欢你啊！！！', 'http://localhost:8080/upload/2018/04/27/4cb5f5d2-a966-4229-9f68-5eb23e37ce50.png', '2', '2018-04-28 22:34:21', '1', '3');
INSERT INTO `article_info` VALUES ('3', '全明星周末开启！', '<p></p><p></p><p></p><p></p><p></p><p><img src=\"/upload/2018/05/04/4211d7fd-99a0-4af4-96fe-e5cda71efdd9.jpg\"><span style=\"color: rgb(0, 0, 0); font-family: monospace; font-size: medium; white-space: pre-wrap;\"><br></span></p><p><span style=\"color: rgb(0, 0, 0); font-family: monospace; font-size: medium; white-space: pre-wrap;\">《全职高手》特别篇 每周五12点更新！</span></p><p></p><p></p><p></p><p></p><p></p>', '《全职高手》特别篇 每周五12点更新！', 'http://localhost:8080/upload/2018/04/28/dadf382b-c3bf-4eb2-a4ec-58218bc5daf5.jpg', '6', '2018-05-04 13:59:20', '1', '3');
INSERT INTO `article_info` VALUES ('4', '关于严肃处理求盗版MO8资源的倡议', '<p></p><p><img src=\"/upload/2018/05/04/d1d1d4ca-3e87-403e-8f37-ecd4f375d850.jpg\"><span style=\"font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px;\"><br></span></p><p><span style=\"font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px;\">一楼随便镇</span><br></p><p><span style=\"font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px;\"><br></span></p><p></p>', '一楼随便镇', 'http://localhost:8080/upload/2018/04/30/efb20ec2-1fee-4697-a00c-24e9ec1e00a6.jpg', '6', '2018-05-04 13:59:05', '1', '11');
INSERT INTO `article_info` VALUES ('5', '删除用', '<p>111111</p>', '111111', 'http://localhost:8080/upload/2018/05/01/ae81a769-089a-47a6-9b95-878a8bd8a340.jpg', '1', '2018-05-01 15:43:30', '0', '1');

-- ----------------------------
-- Table structure for `time_info`
-- ----------------------------
DROP TABLE IF EXISTS `time_info`;
CREATE TABLE `time_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(20000) DEFAULT NULL,
  `end_time` datetime NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of time_info
-- ----------------------------
INSERT INTO `time_info` VALUES ('1', '44岁了，这些年辛苦了', '2035-06-01 16:37:30', '0');
INSERT INTO `time_info` VALUES ('2', '五月天，我一定要去一次你们的演唱会', '2021-01-01 16:38:35', '0');

-- ----------------------------
-- Table structure for `type_info`
-- ----------------------------
DROP TABLE IF EXISTS `type_info`;
CREATE TABLE `type_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type_info
-- ----------------------------
INSERT INTO `type_info` VALUES ('1', 'javaee', '1');
INSERT INTO `type_info` VALUES ('2', 'java', '1');
INSERT INTO `type_info` VALUES ('3', '测试一哈', '2');
INSERT INTO `type_info` VALUES ('4', '我是x86', '3');
INSERT INTO `type_info` VALUES ('8', 'c++', '1');
INSERT INTO `type_info` VALUES ('9', 'python', '3');
INSERT INTO `type_info` VALUES ('10', 'linux', '3');
INSERT INTO `type_info` VALUES ('11', '秋之回忆', '2');
INSERT INTO `type_info` VALUES ('12', '测试分类', '4');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) DEFAULT NULL,
  `pass_word` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', '1234');
