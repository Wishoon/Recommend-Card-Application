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
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `CARD_NAME` varchar(30) NOT NULL,
  `CARD_ATTRIBUTE` varchar(12) NOT NULL,
  `CARD_CONTENT` varchar(100) NOT NULL,
  `CARD_IMG` varchar(100) DEFAULT NULL,
  `CARD_BANK_CARD_BANK_NAME` varchar(15) NOT NULL,
  PRIMARY KEY (`CARD_NAME`),
  KEY `fk_CARD_CARD_BANK1_idx` (`CARD_BANK_CARD_BANK_NAME`),
  CONSTRAINT `fk_CARD_CARD_BANK1` FOREIGN KEY (`CARD_BANK_CARD_BANK_NAME`) REFERENCES `card_bank` (`CARD_BANK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES ('IBK 쇼핑앤조이 카드','신용카드','남 보다 더 잘사는 비결, 온라인쇼핑 특화카드','21','IBK'),('IBK 이사배 카드','신용카드','H＆B스토어, 화장품, 미용 할인으로 뷰티에 관심있는 고객 대상 특화서비스 제공','22','IBK'),('IBK 참! 좋은 다이소카드 ','신용카드','다이소 할인 제휴카드','23','IBK'),('KB 국민 노리 체크카드','체크카드','교통/통신 할인부터 커피숍과 영화관까지. 당신이 가는 곳곳마다 놀라운 할인 혜택!','1','국민은행'),('KB국민카드청춘대로 톡톡카드','신용카드','Simple하게 즐기자! 혜택 톡톡!','2','국민은행'),('KB국민카드탄탄대로 올쇼핑 티타늄카드','신용카드','부모님을 위한 쇼핑 및 라운지 혜택의 신용카드','3','국민은행'),('Mr.Life 카드','신용카드','자취 중이신 직장인 혹 대학생','4','신한은행'),('NH20 해봄 체크카드','체크카드','자기계발, 맘 놓고 하세요~','5','농협'),('NH올원 Shooping&11번가','신용카드','11번가 애용자를 위한 신용카드','6','농협'),('taptap O','신용카드','월 평균 소비가 50만원이신 대학생','7','삼성은행'),('국민 나라사랑카드','체크카드','꿈꾸고 즐기는 청춘을 위한 다양한 할인 혜택','8','국민은행'),('굿데이 올림 카드','신용카드','집중적인 생활비 지출 할인을 원하시는 분','9','국민은행'),('롯데카드 LIKIT FUN 카드','신용카드','커피, 영화 최대 50% 할인 / 교통, 통신, 소셜커머스 5~20% 할인','10','롯데은행'),('부산 지역화폐 동백전 카드','체크카드','소비의 선순환을 통한 지역경제 활성화','11','하나은행'),('신한 S20 카드','체크카드','스무살, 첫 금융특권','12','신한은행'),('신한카드 B.Big(삑)','신용카드','매일매일 가는 길마다 신한카드 교통 Big 할인!','13','신한은행'),('신한카드 YOLOⓘ','신용카드','할인율과 디자인을 내 마음대로!! 나의 맞춤카드를 원하신다면, “YOLO”오세요~','14','신한은행'),('신한카드 YOLOⓘ (미니언즈)','신용카드','할인율과 디자인을 내 마음대로!! 나의 맞춤카드를 원하신다면, “YOLO”오세요~','15','신한은행'),('썸타는 우리 체크카드','체크카드','국내·외 카드 혜택을 SUM하다','16','우리은행'),('우리카드 카드의 정석 위비온 플러스','신용카드','국내외 가맹점 할인, 나를 위한 곳에서 더 큰 할인!','17','우리은행'),('카카오뱅크 체크카드','체크카드','생활 속 다양한 분야에서 매월 최대 6만 1천원 혜택! ','18','카카오뱅크'),('하나 마패 체크카드','체크카드','대중교통 할인은 my pass 마패 카드!','19','하나은행'),('하나카드 카카오페이 카드','체크카드','가장 빠른 모바일 결제 카카오페이와 체크카드의 만남','20','하나은행');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-29 22:33:23
