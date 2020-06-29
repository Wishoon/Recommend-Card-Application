-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: card
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user_card`
--

DROP TABLE IF EXISTS `user_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_card` (
  `CARD_NUMBER` varchar(20) NOT NULL,
  `CARD_NICKNAME` varchar(45) DEFAULT '　',
  `CARD_CVC` varchar(45) NOT NULL,
  `CARD_Validity` varchar(45) NOT NULL,
  `USER_USER_ID` varchar(20) NOT NULL,
  `CARD_CARD_NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`CARD_NUMBER`),
  KEY `fk_USER_CARD_USER_idx` (`USER_USER_ID`),
  KEY `fk_USER_CARD_CARD1_idx` (`CARD_CARD_NAME`),
  CONSTRAINT `fk_USER_CARD_CARD1` FOREIGN KEY (`CARD_CARD_NAME`) REFERENCES `card` (`CARD_NAME`),
  CONSTRAINT `fk_USER_CARD_USER` FOREIGN KEY (`USER_USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_card`
--

LOCK TABLES `user_card` WRITE;
/*!40000 ALTER TABLE `user_card` DISABLE KEYS */;
INSERT INTO `user_card` VALUES ('023489712902','C','157','0823','admin','신한 S20 카드'),('1','4','2','3','a','신한 S20 카드'),('1212342','14','12331','4242','a','부산 지역화폐 동백전 카드'),('123','마나나','123','123','a','IBK 이사배 카드'),('12345678908765431 ','','321','0623','a','국민 나라사랑카드'),('123894238459','G','412','1023','admin','썸타는 우리 체크카드'),('123989532093','아','345','0222','admin','NH올원 Shooping&11번가'),('149821754956','J','727','1124','admin','하나 마패 체크카드'),('2','5','3','4','a','카카오뱅크 체크카드'),('231231232','바ㅓ바바바','132','123231212','a','굿데이 올림 카드'),('234123950283','다','048','0921','admin','IBK 참! 좋은 다이소카드 '),('249810532095','D','723','0821','admin','신한카드 B.Big(삑)'),('325902838749','F','762','0924','admin','신한카드 YOLOⓘ (미니언즈)'),('329418923820','마','194','0522','admin','KB국민카드탄탄대로 올쇼핑 티타늄카드'),('3492948','38291','20149','483294','a','taptap O'),('35235','3535234','643','342','a','신한 S20 카드'),('412421423523','가','425','0721','admin','IBK 쇼핑앤조이 카드'),('434234234123','나','231','1223','admin','IBK 이사배 카드'),('437583495723','A','578','1221','admin','롯데카드 LIKIT FUN 카드'),('437649672123','K','745','1225','admin','하나카드 카카오페이 카드'),('438297498232','I','623','1025','admin','카카오뱅크 체크카드'),('5','5','3','4','A','신한카드 YOLOⓘ'),('534423049312','마','979','0221','admin','KB국민카드청춘대로 톡톡카드'),('587438693421','H','512','1024','admin','우리카드 카드의 정석 위비온 플러스'),('634806520343','바','772','0221','admin','Mr.Life 카드'),('654649020219','자','423','1122','admin','taptap O'),('656748931239','B','732','0622','admin','부산 지역화폐 동백전 카드'),('7394747382913','간다','345','2222','ba','IBK 쇼핑앤조이 카드'),('814729483842','E','982','0923','admin','신한카드 YOLOⓘ'),('863453409414','라','146','0126','admin','KB 국민 노리 체크카드'),('889650349582','카','665','0622','admin','굿데이 올림 카드'),('912344212345','차','295','0224','admin','국민 나라사랑카드'),('983247012924','사','392','0823','admin','NH20 해봄 체크카드');
/*!40000 ALTER TABLE `user_card` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-29 22:33:22
