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
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `STORE_NAME` varchar(20) NOT NULL,
  `STORE_ATTRIBUTE` varchar(10) NOT NULL,
  PRIMARY KEY (`STORE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES ('CGV','영화'),('CU','편의점'),('GS25','편의점'),('GS칼텍스','주유'),('TGIF','패밀리레스토랑'),('VIPS','패밀리레스토랑'),('네이처리퍼블릭','화장품'),('다이소','문구'),('더샘','화장품'),('더페이스샵','화장품'),('던킨도너츠','베이커리'),('도미노피자','피자'),('뚜레쥬르','베이커리'),('랄라블라','화장품'),('롭스','화장품'),('롯데리아','패스트푸드'),('롯데마트','대형마트'),('롯데시네마','영화'),('롯데월드','놀이동산'),('맥도날드','패스트푸드'),('메가박스','영화'),('미샤','화장품'),('미스터피자','피자'),('버거킹','패스트푸드'),('버스','대중교통'),('베스킨라빈스','아이스크림'),('부츠','화장품'),('세븐일레븐','편의점'),('스킨푸드','화장품'),('스타벅스','커피'),('아리따움','화장품'),('아웃백','패밀리레스토랑'),('에뛰드하우스','화장품'),('에버랜드','놀이동산'),('엔제리너스','커피'),('올리브영','화장품'),('이니스프리','화장품'),('이디야커피','커피'),('이마트','대형마트'),('이마트24','편의점'),('지하철','대중교통'),('카페베네','커피'),('커피빈','커피'),('택시','대중교통'),('토니모리','화장품'),('투썸','커피'),('파리바게뜨','베이커리'),('파스쿠찌','커피'),('피자헛','피자'),('할리스','커피'),('홈플러스','대형마트');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
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
