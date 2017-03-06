/*
SQLyog Community Edition- MySQL GUI v7.12 
MySQL - 5.6.22-log : Database - dev_moneyboss_php
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`dev_moneyboss_php` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `dev_moneyboss_php`;

/*Table structure for table `_defualtColumns` */

DROP TABLE IF EXISTS `_defualtColumns`;

CREATE TABLE `_defualtColumns` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_corporation_guid` varchar(40) NOT NULL,
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_corporation_guid` (`b_corporation_guid`),
  CONSTRAINT `_defualtColumns_ibfk_1` FOREIGN KEY (`b_corporation_guid`) REFERENCES `b_enterprise` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基本欄位';

/*Table structure for table `b_agents` */

DROP TABLE IF EXISTS `b_agents`;

CREATE TABLE `b_agents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_agents_guid` varchar(40) DEFAULT NULL COMMENT '總代理索引 NULL為總代理',
  `account` varchar(50) NOT NULL COMMENT '代理商帳號',
  `passwd` varchar(64) NOT NULL COMMENT '代理商密碼',
  `serial_no` varchar(20) NOT NULL COMMENT '代理商識別碼',
  `uniform_number` varchar(20) NOT NULL COMMENT '統一編號',
  `company_name` varchar(50) NOT NULL COMMENT '公司名稱',
  `company_phone` varchar(20) NOT NULL COMMENT '公司電話',
  `company_address` varchar(255) NOT NULL COMMENT '公司地址',
  `contact_name` varchar(20) NOT NULL COMMENT '聯絡人姓名',
  `contact_mobile` varchar(20) NOT NULL COMMENT '聯絡人行動電話',
  `contact_email` varchar(255) NOT NULL COMMENT '聯絡人電子郵件',
  `remittance_bank` varchar(40) NOT NULL COMMENT '匯款銀行',
  `remittance_account` varchar(40) NOT NULL COMMENT '匯款帳號',
  `remittance_name` varchar(20) NOT NULL COMMENT '匯款名稱',
  `remark` text NOT NULL COMMENT '備註',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `account` (`account`),
  KEY `b_agents_guid` (`b_agents_guid`),
  CONSTRAINT `b_agents_ibfk_1` FOREIGN KEY (`b_agents_guid`) REFERENCES `b_agents` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `b_agents` */

insert  into `b_agents`(`id`,`guid`,`b_agents_guid`,`account`,`passwd`,`serial_no`,`uniform_number`,`company_name`,`company_phone`,`company_address`,`contact_name`,`contact_mobile`,`contact_email`,`remittance_bank`,`remittance_account`,`remittance_name`,`remark`,`is_invalid`,`creator_guid`,`creator_name`,`creator_date`,`modifier_guid`,`modifier_name`,`modifier_date`,`invalid_guid`,`invalid_name`,`invalid_date`) values (1,'9371e836-2312-11e6-a4ac-d275b9d5831f',NULL,'everywhere','','ew','80246456','詮通','','','','','','','','','',1,'','','2016-05-26 15:21:28','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(3,'ef20b778-23d2-11e6-8743-d275b9d51234','ef20b778-23d2-11e6-8743-d275b9d5831f','rd22','','rd22','87654321','Light','','','','','','','','','',1,'','','2016-05-26 15:21:28','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(2,'ef20b778-23d2-11e6-8743-d275b9d5831f',NULL,'rd21','','rd21','12345678','Adam','','','','','','','','','',1,'','','2016-05-26 15:21:28','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00');

/*Table structure for table `b_bussiness_card_attribute` */

DROP TABLE IF EXISTS `b_bussiness_card_attribute`;

CREATE TABLE `b_bussiness_card_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '類別名稱',
  `b_corporation_guid` varchar(40) DEFAULT NULL,
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_corporation_guid` (`b_corporation_guid`),
  KEY `b_corporation_guid_2` (`b_corporation_guid`),
  CONSTRAINT `b_bussiness_card_attribute_ibfk_1` FOREIGN KEY (`b_corporation_guid`) REFERENCES `b_enterprise` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='名片類別';

/*Table structure for table `b_bussiness_card_category` */

DROP TABLE IF EXISTS `b_bussiness_card_category`;

CREATE TABLE `b_bussiness_card_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_corporation_guid` varchar(40) DEFAULT NULL,
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '類別名稱',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_corporation_guid` (`b_corporation_guid`),
  CONSTRAINT `b_bussiness_card_category_ibfk_1` FOREIGN KEY (`b_corporation_guid`) REFERENCES `b_enterprise` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='名片類別';

/*Table structure for table `b_bussiness_card_status` */

DROP TABLE IF EXISTS `b_bussiness_card_status`;

CREATE TABLE `b_bussiness_card_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_corporation_guid` varchar(40) DEFAULT NULL,
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '類別名稱',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_corporation_guid` (`b_corporation_guid`),
  CONSTRAINT `b_bussiness_card_status_ibfk_1` FOREIGN KEY (`b_corporation_guid`) REFERENCES `b_enterprise` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='名片類別';

/*Table structure for table `b_enterprise` */

DROP TABLE IF EXISTS `b_enterprise`;

CREATE TABLE `b_enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `store_no` varchar(40) DEFAULT 'null' COMMENT '特店編號',
  `b_agents_guid` varchar(40) NOT NULL COMMENT '代理商索引',
  `account` varchar(50) NOT NULL COMMENT '企業帳號',
  `b_industry_guid` varchar(40) NOT NULL COMMENT '業別索引',
  `uniform_number` varchar(20) NOT NULL COMMENT '統一編號',
  `company_name` varchar(50) NOT NULL COMMENT '公司名稱',
  `company_phone` varchar(20) NOT NULL COMMENT '公司電話',
  `company_address` varchar(255) NOT NULL COMMENT '公司地址',
  `applicant_name` varchar(20) NOT NULL COMMENT '聯絡人姓名',
  `applicant_mobile` varchar(20) NOT NULL COMMENT '聯絡人行動電話',
  `applicant_email` varchar(255) NOT NULL COMMENT '聯絡人電子郵件',
  `email` varchar(255) NOT NULL COMMENT '企業電子郵件',
  `email_validate` int(42) NOT NULL DEFAULT '1' COMMENT 'E-Mail認證 1：未驗證 50：驗證通過',
  `number_of_employees` int(3) NOT NULL DEFAULT '5' COMMENT '可建立子帳號數量',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '帳號狀態 1：未使用 10：審核中 20：不通過 30：試用 40：無效 50：停權 60：有效',
  `remark` text NOT NULL COMMENT '備註',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_agents_guid` (`b_agents_guid`),
  CONSTRAINT `b_enterprise_ibfk_1` FOREIGN KEY (`b_agents_guid`) REFERENCES `b_agents` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COMMENT='企業帳號';

/*Table structure for table `b_enterprise_employee` */

DROP TABLE IF EXISTS `b_enterprise_employee`;

CREATE TABLE `b_enterprise_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_enterprise_guid` varchar(40) NOT NULL COMMENT '企業帳號索引',
  `account` varchar(50) NOT NULL COMMENT '子帳號',
  `email` varchar(255) NOT NULL COMMENT '子帳號電子郵件',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '子帳號狀態 1：關閉 10：停權 50：開啟',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `account` (`account`),
  KEY `b_enterprise_guid` (`b_enterprise_guid`),
  CONSTRAINT `b_enterprise_employee_ibfk_1` FOREIGN KEY (`b_enterprise_guid`) REFERENCES `b_enterprise` (`guid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='子帳號';

/*Table structure for table `b_industry` */

DROP TABLE IF EXISTS `b_industry`;

CREATE TABLE `b_industry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `serial_no` varchar(20) NOT NULL COMMENT '業別代碼',
  `name` varchar(50) NOT NULL COMMENT '業別名稱',
  `sort_no` int(11) NOT NULL COMMENT '排序',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COMMENT='業別';

/*Data for the table `b_industry` */

insert  into `b_industry`(`id`,`guid`,`serial_no`,`name`,`sort_no`,`is_invalid`,`creator_guid`,`creator_name`,`creator_date`,`modifier_guid`,`modifier_name`,`modifier_date`,`invalid_guid`,`invalid_name`,`invalid_date`) values (16,'0166a35c-286f-11e6-b02c-d275b9d5831f','P','教育業',16,1,'','','2016-06-02 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(8,'19f3c6d8-231b-11e6-a4ac-d275b9d5831f','H','運輸及倉儲業',8,1,'','','2016-05-26 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(1,'1a385722-231a-11e6-a4ac-d275b9d5831f','A','農、林、漁、牧業',1,1,'','','2016-05-26 00:00:00','','','2016-05-26 00:00:00','','','1899-12-31 00:00:00'),(17,'1a5f5a34-286f-11e6-b02c-d275b9d5831f','Q','醫療保健及社會工作服務業',17,1,'','','2016-06-02 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(9,'2d3915fe-231b-11e6-a4ac-d275b9d5831f','I','住宿及餐飲業',9,1,'','','2016-05-26 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(10,'4b5c9b3c-231b-11e6-a4ac-d275b9d5831f','J','出版、影音製作、傳播及資通訊服務業',10,1,'','','2016-05-26 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(18,'4f5dc252-286f-11e6-b02c-d275b9d5831f','R','藝術、娛樂及休閒服務業',18,1,'','','2016-06-02 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(2,'6082291a-231a-11e6-a4ac-d275b9d5831f','B','礦業及土石採取業',2,1,'','','2016-05-26 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(3,'79a4664c-231a-11e6-a4ac-d275b9d5831f','C','製造業',3,1,'','','2016-05-26 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(12,'7cd61168-286e-11e6-b02c-d275b9d5831f','L','不動產業',12,1,'','','2016-06-02 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(19,'860d5484-286f-11e6-b02c-d275b9d5831f','S','其他服務業',19,1,'','','2016-06-02 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(13,'9f4665ae-286e-11e6-b02c-d275b9d5831f','M','專業、科學及技術服務業',13,1,'','','2016-06-02 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(4,'9fab3a82-231a-11e6-a4ac-d275b9d5831f','D','電力及燃氣供應業',4,1,'','','2016-05-26 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(11,'b367358e-231b-11e6-a4ac-d275b9d5831f','K','金融及保險業',11,1,'','','2016-05-26 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(15,'ca28a74a-286f-11e6-b02c-d275b9d5831f','O','公共行政及國防；強制性社會安全',15,1,'','','2016-06-02 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(5,'cbd00020-231a-11e6-a4ac-d275b9d5831f','E','用水供應及污染整治業',5,1,'','','2016-05-26 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(14,'ce0376fc-286e-11e6-b02c-d275b9d5831f','N','支援服務業',14,1,'','','2016-06-02 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(6,'dd4fab2a-231a-11e6-a4ac-d275b9d5831f','F','營建工程業',6,1,'','','2016-05-26 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00'),(7,'f84e5868-231a-11e6-a4ac-d275b9d5831f','G','批發及零售業',7,1,'','','2016-05-26 00:00:00','','','1899-12-31 00:00:00','','','1899-12-31 00:00:00');

/*Table structure for table `b_permission` */

DROP TABLE IF EXISTS `b_permission`;

CREATE TABLE `b_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `user_guid` varchar(36) NOT NULL COMMENT '後台使用者',
  `controller` varchar(100) NOT NULL COMMENT 'Controller',
  `action` varchar(100) NOT NULL COMMENT 'Action',
  `is_enable` int(2) NOT NULL DEFAULT '1' COMMENT '是否啟用 EnumIsEnable',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='權限';

/*Table structure for table `b_sample` */

DROP TABLE IF EXISTS `b_sample`;

CREATE TABLE `b_sample` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `serial` varchar(40) NOT NULL COMMENT '編號',
  `sample_password` varchar(32) NOT NULL COMMENT '範例密碼',
  `sample_number` int(11) NOT NULL DEFAULT '0' COMMENT '範例數字',
  `sample_date` date NOT NULL COMMENT '訂單日期',
  `sample_email` text NOT NULL COMMENT '範例Email',
  `sample_url` text NOT NULL COMMENT '範例Url',
  `sample_drop_down_list` varchar(40) NOT NULL COMMENT '範例下拉選單',
  `sample_drop_down_list_from_facade` varchar(40) NOT NULL COMMENT '範例下拉選單(經由Facade產生)',
  `sample_easyui_autocomplete` varchar(40) NOT NULL COMMENT '範例EasyUI自動完成',
  `sample_easyui_autocomplete_from_facade` varchar(40) NOT NULL COMMENT '範例EasyUI自動完成(經由Facade產生)',
  `sample_cm_phone_prefix` varchar(5) NOT NULL COMMENT '範例CmPhone的prefix',
  `sample_cm_phone_number` varchar(10) NOT NULL COMMENT '範例CmPhone的number',
  `sample_cm_phone_extension` varchar(5) NOT NULL COMMENT '範例CmPhone的extension',
  `sample_enum` int(2) NOT NULL COMMENT '範例列舉(單選按鈕選單)',
  `sample_load_field` varchar(40) NOT NULL COMMENT '範例選擇器',
  `sample_textarea` text NOT NULL COMMENT '範例文字輸入區塊',
  `sample_ckeditor` text NOT NULL COMMENT '範例CkEditor',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_dealer_guid` (`sample_easyui_autocomplete_from_facade`),
  KEY `b_staff_guid` (`sample_drop_down_list_from_facade`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='範例主檔';

/*Table structure for table `b_sample_item` */

DROP TABLE IF EXISTS `b_sample_item`;

CREATE TABLE `b_sample_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `main_guid` varchar(40) NOT NULL COMMENT '主檔索引',
  `sample_item_easyui_autocomplete` varchar(40) NOT NULL COMMENT '範例EasyUI自動完成(明細)',
  `sample_item_drop_down_list` varchar(40) NOT NULL COMMENT '範例下拉選單(明細)',
  `sample_item_text` varchar(10) NOT NULL COMMENT '範例文字輸入(明細)',
  `sample_item_date` date NOT NULL COMMENT '範例日期(明細)',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `t_order_guid` (`main_guid`),
  KEY `b_product_guid` (`sample_item_easyui_autocomplete`),
  KEY `s_project_category_guid` (`sample_item_drop_down_list`),
  CONSTRAINT `fk_sampleItem_SIDD_map_sampleOption_guid` FOREIGN KEY (`sample_item_drop_down_list`) REFERENCES `b_sample_option` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_sampleItem_SIEA_map_sampleOption_guid` FOREIGN KEY (`sample_item_easyui_autocomplete`) REFERENCES `b_sample_option` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_sampleItem_mainGuid_map_sample_guid` FOREIGN KEY (`main_guid`) REFERENCES `b_sample` (`guid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='範例明細檔';

/*Table structure for table `b_sample_option` */

DROP TABLE IF EXISTS `b_sample_option`;

CREATE TABLE `b_sample_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `serial` varchar(40) NOT NULL COMMENT '編號',
  `title` varchar(100) NOT NULL COMMENT '產品名稱',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='範例選項檔';

/*Table structure for table `b_scheduled_project` */

DROP TABLE IF EXISTS `b_scheduled_project`;

CREATE TABLE `b_scheduled_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_corporation_guid` varchar(40) DEFAULT NULL,
  `name` varchar(40) NOT NULL DEFAULT '' COMMENT '類別名稱',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_corporation_guid` (`b_corporation_guid`),
  CONSTRAINT `b_scheduled_project_ibfk_1` FOREIGN KEY (`b_corporation_guid`) REFERENCES `b_enterprise` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='名片類別';

/*Table structure for table `b_service` */

DROP TABLE IF EXISTS `b_service`;

CREATE TABLE `b_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_service_category_gui d` varchar(40) NOT NULL COMMENT '服務類別索引',
  `serial_no` varchar(20) NOT NULL COMMENT '服務代碼',
  `title` varchar(50) NOT NULL COMMENT '服務標題',
  `sort_no` int(11) NOT NULL COMMENT '排序',
  `url` text NOT NULL COMMENT '預設服務連結位置',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_service_category_gui d` (`b_service_category_gui d`),
  CONSTRAINT `b_service_ibfk_1` FOREIGN KEY (`b_service_category_gui d`) REFERENCES `b_service_category` (`guid`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='系統服務清單';

/*Table structure for table `b_service_advance` */

DROP TABLE IF EXISTS `b_service_advance`;

CREATE TABLE `b_service_advance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_service_guid` varchar(40) NOT NULL COMMENT '系統服務索引',
  `serial_no` varchar(20) NOT NULL COMMENT '進階服務代碼',
  `title` varchar(50) NOT NULL COMMENT '進階服務標題',
  `sort_no` int(11) NOT NULL COMMENT '排序',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系統進階服務清單';

/*Table structure for table `b_service_category` */

DROP TABLE IF EXISTS `b_service_category`;

CREATE TABLE `b_service_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `serial_no` varchar(20) NOT NULL COMMENT '服務類別代碼',
  `title` varchar(50) NOT NULL COMMENT '服務類別標題',
  `sort_no` int(11) NOT NULL COMMENT '排序',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系統服務類別';

/*Table structure for table `b_user` */

DROP TABLE IF EXISTS `b_user`;

CREATE TABLE `b_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `account` varchar(40) NOT NULL COMMENT '帳號',
  `passwd` varchar(64) NOT NULL COMMENT '密碼',
  `title` varchar(50) NOT NULL COMMENT '名稱',
  `email` varchar(100) NOT NULL COMMENT '信箱',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='後台使用者';

/*Table structure for table `b_user_config` */

DROP TABLE IF EXISTS `b_user_config`;

CREATE TABLE `b_user_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `user_guid` varchar(40) NOT NULL COMMENT '後台使用者索引',
  `type` int(2) NOT NULL DEFAULT '1' COMMENT '後台使用者設定類型 EnumUserConfigType',
  `controller_id` varchar(100) NOT NULL COMMENT '控制器識別',
  `action_id` varchar(100) NOT NULL COMMENT '動作識別',
  `field_id` varchar(100) NOT NULL COMMENT '欄位識別',
  `value` text NOT NULL COMMENT '設定值',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `user_guid` (`user_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='後台使用者設定';

/*Table structure for table `d_bussiness_card` */

DROP TABLE IF EXISTS `d_bussiness_card`;

CREATE TABLE `d_bussiness_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_corporation_guid` varchar(40) NOT NULL,
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_corporation_guid` (`b_corporation_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基本欄位';

/*Table structure for table `d_enterprise_service` */

DROP TABLE IF EXISTS `d_enterprise_service`;

CREATE TABLE `d_enterprise_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_service_guid` varchar(40) NOT NULL COMMENT '系統服務索引',
  `b_enterprise_guid` varchar(40) NOT NULL COMMENT '企業帳號索引',
  `url` text NOT NULL COMMENT '服務連結位置',
  `start_time` datetime NOT NULL COMMENT '服務啟用日期',
  `end_time` datetime NOT NULL COMMENT '服務停用日期',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_service_guid` (`b_service_guid`),
  KEY `b_enterprise_guid` (`b_enterprise_guid`),
  CONSTRAINT `d_enterprise_service_ibfk_1` FOREIGN KEY (`b_service_guid`) REFERENCES `b_service` (`guid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `d_enterprise_service_ibfk_2` FOREIGN KEY (`b_enterprise_guid`) REFERENCES `b_enterprise` (`guid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='企業帳號開啟服務';

/*Table structure for table `d_enterprise_service_advance` */

DROP TABLE IF EXISTS `d_enterprise_service_advance`;

CREATE TABLE `d_enterprise_service_advance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_service_advance_gui d` varchar(40) NOT NULL COMMENT '系統進階服務索引',
  `b_enterprise_guid` varchar(40) NOT NULL COMMENT '企業帳號索引',
  `start_time` datetime NOT NULL COMMENT '服務啟用日期',
  `end_time` datetime NOT NULL COMMENT '服務停用日期',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_service_advance_gui d` (`b_service_advance_gui d`),
  KEY `b_enterprise_guid` (`b_enterprise_guid`),
  CONSTRAINT `d_enterprise_service_advance_ibfk_1` FOREIGN KEY (`b_service_advance_gui d`) REFERENCES `b_service_advance` (`guid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `d_enterprise_service_advance_ibfk_2` FOREIGN KEY (`b_enterprise_guid`) REFERENCES `b_enterprise` (`guid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企業帳號開啟進階服務';

/*Table structure for table `d_enterprise_try_out` */

DROP TABLE IF EXISTS `d_enterprise_try_out`;

CREATE TABLE `d_enterprise_try_out` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_enterprise_guid` varchar(40) NOT NULL COMMENT '企業帳號索引',
  `start_date` date NOT NULL DEFAULT '1988-12-31' COMMENT '試用開始日期',
  `end_date` date NOT NULL DEFAULT '1988-12-31' COMMENT '試用結束日期',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_enterprise_guid` (`b_enterprise_guid`),
  CONSTRAINT `d_enterprise_try_out_ibfk_1` FOREIGN KEY (`b_enterprise_guid`) REFERENCES `b_enterprise` (`guid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企業帳號試用區間';

/*Table structure for table `r_bo_history` */

DROP TABLE IF EXISTS `r_bo_history`;

CREATE TABLE `r_bo_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `origin_bo` varchar(50) NOT NULL COMMENT '來源Bo',
  `record_guid` varchar(40) NOT NULL COMMENT '異動資料索引',
  `method` int(1) NOT NULL COMMENT '異動類別, 0:新增 1:維護 2:作廢 3:刪除: 結案',
  `summary` text NOT NULL COMMENT '異動概要',
  `contents` text NOT NULL COMMENT '異動內容',
  `raw_data` text NOT NULL COMMENT '原始資料',
  `target_data` text NOT NULL COMMENT '目標資料',
  `b_user_guid` varchar(40) NOT NULL COMMENT '使用者索引',
  `b_staff_guid` varchar(40) NOT NULL COMMENT '員工資料索引',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_user_guid` (`b_user_guid`),
  KEY `b_staff_guid` (`b_staff_guid`),
  KEY `record_guid` (`record_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='資料歷程';

/*Table structure for table `r_employee_login_history` */

DROP TABLE IF EXISTS `r_employee_login_history`;

CREATE TABLE `r_employee_login_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_enterprise_employee_guid` varchar(40) NOT NULL COMMENT '子帳號索引',
  `store_no` varchar(40) NOT NULL COMMENT '特店編號',
  `account` varchar(50) NOT NULL COMMENT '帳號',
  `token` varchar(50) DEFAULT NULL COMMENT 'TOKEN',
  `retry_times` int(11) NOT NULL DEFAULT '0' COMMENT '連續登入失敗次數',
  `unblock_date` datetime DEFAULT NULL COMMENT '解除封鎖日期',
  `force_block` int(2) NOT NULL DEFAULT '1' COMMENT '強制開通 1：否 50：是',
  `status` varchar(2) NOT NULL DEFAULT '1' COMMENT '登入狀態 1：帳號不存在 10：特店編號不存在 20：密碼錯誤 30：帳號停權 40：封鎖中拒絕登入 50：登入成功',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_enterprise_employee_ guid` (`b_enterprise_employee_guid`),
  CONSTRAINT `r_employee_login_history_ibfk_1` FOREIGN KEY (`b_enterprise_employee_guid`) REFERENCES `b_enterprise_employee` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='子帳號登入紀錄';

/*Table structure for table `r_enterprise_employee_password_history` */

DROP TABLE IF EXISTS `r_enterprise_employee_password_history`;

CREATE TABLE `r_enterprise_employee_password_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_enterprise_employee_guid` varchar(40) NOT NULL COMMENT '子帳號索引',
  `passwd` varchar(64) NOT NULL COMMENT '子帳號密碼',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_enterprise_employee_guid` (`b_enterprise_employee_guid`) USING BTREE,
  CONSTRAINT `r_enterprise_employee_password_history_ibfk_1` FOREIGN KEY (`b_enterprise_employee_guid`) REFERENCES `b_enterprise_employee` (`guid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='子帳號密碼更新紀錄';

/*Table structure for table `r_enterprise_login_history` */

DROP TABLE IF EXISTS `r_enterprise_login_history`;

CREATE TABLE `r_enterprise_login_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_enterprise_guid` varchar(40) DEFAULT NULL COMMENT '企業帳號索引',
  `store_no` varchar(40) NOT NULL COMMENT '特店編號',
  `account` varchar(50) NOT NULL COMMENT '帳號',
  `token` varchar(50) DEFAULT NULL COMMENT 'TOKEN',
  `retry_times` int(11) NOT NULL DEFAULT '0' COMMENT '連續登入失敗次數',
  `unblock_date` datetime DEFAULT NULL COMMENT '解除封鎖日期',
  `force_block` int(2) NOT NULL DEFAULT '1' COMMENT '強制開通 1：否 50：是',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '登入狀態 1：帳號不存在 10：特店編號不存在 20：密碼錯誤 30：帳號停權 40：封鎖中拒絕登入 50：登入成功',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_enterprise_guid` (`b_enterprise_guid`),
  CONSTRAINT `r_enterprise_login_history_ibfk_1` FOREIGN KEY (`b_enterprise_guid`) REFERENCES `b_enterprise` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企業帳號登入紀錄';

/*Table structure for table `r_enterprise_password_history` */

DROP TABLE IF EXISTS `r_enterprise_password_history`;

CREATE TABLE `r_enterprise_password_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_enterprise_guid` varchar(40) NOT NULL COMMENT '企業帳號索引',
  `passwd` varchar(64) NOT NULL COMMENT '企業密碼',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_enterprise_guid` (`b_enterprise_guid`),
  CONSTRAINT `r_enterprise_password_history_ibfk_1` FOREIGN KEY (`b_enterprise_guid`) REFERENCES `b_enterprise` (`guid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COMMENT='企業帳號密碼更新紀錄';

/*Table structure for table `r_industry_generate_history` */

DROP TABLE IF EXISTS `r_industry_generate_history`;

CREATE TABLE `r_industry_generate_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `b_agents_guid` varchar(40) NOT NULL COMMENT '代理商索引',
  `quantity` varchar(50) NOT NULL COMMENT '產生數量',
  `store_no_list` text NOT NULL COMMENT '特店編號清單，以json 格式儲存',
  `is_invalid` int(1) NOT NULL DEFAULT '1' COMMENT '資料是否作廢 1：否 2：是',
  `creator_guid` varchar(40) NOT NULL COMMENT '資料建立人員索引',
  `creator_name` varchar(50) NOT NULL COMMENT '資料建立人員名稱',
  `creator_date` datetime NOT NULL COMMENT '資料建立時間',
  `modifier_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '最後修改人員索引',
  `modifier_name` varchar(50) NOT NULL DEFAULT '' COMMENT '最後修改人員名稱',
  `modifier_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料最後修改時間',
  `invalid_guid` varchar(40) NOT NULL DEFAULT '' COMMENT '資料作廢人員索引',
  `invalid_name` varchar(50) NOT NULL DEFAULT '' COMMENT '資料作廢人員名稱',
  `invalid_date` datetime NOT NULL DEFAULT '1899-12-31 00:00:00' COMMENT '資料作廢時間',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `id` (`id`),
  KEY `b_agents_guid` (`b_agents_guid`),
  CONSTRAINT `r_industry_generate_history_ibfk_1` FOREIGN KEY (`b_agents_guid`) REFERENCES `b_agents` (`guid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代理商特店編號產生紀錄';

/*Table structure for table `v_enterprise_disable_service` */

DROP TABLE IF EXISTS `v_enterprise_disable_service`;

/*!50001 DROP VIEW IF EXISTS `v_enterprise_disable_service` */;
/*!50001 DROP TABLE IF EXISTS `v_enterprise_disable_service` */;

/*!50001 CREATE TABLE `v_enterprise_disable_service` (
  `b_service_guid` varchar(50) NOT NULL COMMENT '服務標題',
  `b_enterprise_guid` varchar(40) NOT NULL COMMENT '資料識別碼',
  `serial_no` varchar(20) NOT NULL COMMENT '服務代碼',
  `title` varchar(50) NOT NULL COMMENT '服務標題'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 */;

/*Table structure for table `v_enterprise_login` */

DROP TABLE IF EXISTS `v_enterprise_login`;

/*!50001 DROP VIEW IF EXISTS `v_enterprise_login` */;
/*!50001 DROP TABLE IF EXISTS `v_enterprise_login` */;

/*!50001 CREATE TABLE `v_enterprise_login` (
  `pk` varchar(40) NOT NULL DEFAULT '',
  `store_no` varchar(40) DEFAULT NULL,
  `account` varchar(50) NOT NULL DEFAULT '',
  `passwd` varchar(64) DEFAULT NULL,
  `token` varchar(50) DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `status` bigint(20) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 */;

/*View structure for view v_enterprise_disable_service */

/*!50001 DROP TABLE IF EXISTS `v_enterprise_disable_service` */;
/*!50001 DROP VIEW IF EXISTS `v_enterprise_disable_service` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`admin`@`127.0.0.1` SQL SECURITY DEFINER VIEW `v_enterprise_disable_service` AS select `b_service`.`title` AS `b_service_guid`,`b_enterprise`.`guid` AS `b_enterprise_guid`,`b_service`.`serial_no` AS `serial_no`,`b_service`.`title` AS `title` from ((`b_enterprise` join `b_service`) left join `d_enterprise_service` on(((`b_service`.`guid` = `d_enterprise_service`.`b_service_guid`) and (`b_enterprise`.`guid` = `d_enterprise_service`.`b_enterprise_guid`) and (`d_enterprise_service`.`end_time` > now())))) where isnull(`d_enterprise_service`.`guid`) */;

/*View structure for view v_enterprise_login */

/*!50001 DROP TABLE IF EXISTS `v_enterprise_login` */;
/*!50001 DROP VIEW IF EXISTS `v_enterprise_login` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`rd02`@`192.168.0.%` SQL SECURITY DEFINER VIEW `v_enterprise_login` AS (select `b_enterprise`.`guid` AS `pk`,`b_enterprise`.`store_no` AS `store_no`,`b_enterprise`.`account` AS `account`,(select `r_enterprise_password_history`.`passwd` from `r_enterprise_password_history` order by `r_enterprise_password_history`.`id` desc limit 0,1) AS `passwd`,(select `r_enterprise_login_history`.`token` from `r_enterprise_login_history` where ((`r_enterprise_login_history`.`b_enterprise_guid` = `b_enterprise`.`guid`) and (`r_enterprise_login_history`.`status` = 50)) order by `r_enterprise_login_history`.`id` desc limit 0,1) AS `token`,'enterprise' AS `type`,if(((select `r_enterprise_login_history`.`status` from `r_enterprise_login_history` where ((`r_enterprise_login_history`.`b_enterprise_guid` = `b_enterprise`.`guid`) and ((`r_enterprise_login_history`.`status` = 50) or ((`r_enterprise_login_history`.`status` = 40) and (`r_enterprise_login_history`.`unblock_date` > now()) and (`r_enterprise_login_history`.`force_block` = 50)))) order by `r_enterprise_login_history`.`id` desc limit 0,1) = 40),20,(case `b_enterprise`.`status` when 10 then 1 when 20 then 30 when 30 then 50 when 40 then 30 when 50 then 10 when 60 then 50 else 30 end)) AS `status` from `b_enterprise` where (`b_enterprise`.`status` in (10,20,30,40,50,60))) union all (select `b_enterprise_employee`.`guid` AS `pk`,`b_enterprise`.`store_no` AS `store_no`,`b_enterprise_employee`.`account` AS `account`,(select `r_enterprise_employee_password_history`.`passwd` from `r_enterprise_employee_password_history` order by `r_enterprise_employee_password_history`.`id` desc limit 0,1) AS `passwd`,(select `r_employee_login_history`.`token` from `r_employee_login_history` where ((`r_employee_login_history`.`b_enterprise_employee_guid` = `b_enterprise_employee`.`guid`) and ((`r_employee_login_history`.`status` = 50) or ((`r_employee_login_history`.`status` = 40) and (`r_employee_login_history`.`unblock_date` > now()) and (`r_employee_login_history`.`force_block` = 1)))) order by `r_employee_login_history`.`id` desc limit 0,1) AS `token`,'employee' AS `type`,if(((select `r_employee_login_history`.`status` from `r_employee_login_history` where ((`r_employee_login_history`.`b_enterprise_employee_guid` = `b_enterprise_employee`.`guid`) and ((`r_employee_login_history`.`status` = 50) or ((`r_employee_login_history`.`status` = 40) and (`r_employee_login_history`.`unblock_date` > now()) and (`r_employee_login_history`.`force_block` = 1)))) order by `r_employee_login_history`.`id` desc limit 0,1) = 40),20,(case `b_enterprise_employee`.`status` when 1 then 30 when 10 then 10 when 50 then 50 else 30 end)) AS `status` from (`b_enterprise_employee` left join `b_enterprise` on((`b_enterprise_employee`.`b_enterprise_guid` = `b_enterprise`.`guid`)))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
