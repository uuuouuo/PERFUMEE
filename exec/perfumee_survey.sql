-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: j6c101.p.ssafy.io    Database: perfumee
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

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
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey` (
  `survey_no` int NOT NULL AUTO_INCREMENT,
  `note_kor` varchar(45) NOT NULL,
  `note_no` int DEFAULT NULL,
  PRIMARY KEY (`survey_no`),
  KEY `FK4tfo27fv9ytekaagwb31bnaps` (`note_no`),
  CONSTRAINT `FK4tfo27fv9ytekaagwb31bnaps` FOREIGN KEY (`note_no`) REFERENCES `note` (`note_no`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
INSERT INTO `survey` VALUES (1,'알데하이드',3414),(2,'용연향',3090),(3,'사과',989),(4,'바질',2574),(5,'벤조인',2477),(6,'버가못',2836),(7,'후추',733),(8,'블랙커런트',944),(9,'불가리안 로즈',3695),(10,'카라멜',2710),(11,'카다멈',1960),(12,'카네이션',362),(13,'시더',918),(14,'시나몬',2992),(15,'시트러스',2609),(16,'시벳',4174),(17,'클라리 세이지',1344),(18,'클로브',4037),(19,'코코넛',3269),(20,'고수',480),(21,'시클라멘',2651),(22,'솔잎',3514),(23,'엘레미',497),(24,'플로럴',3346),(25,'프랑킨센스',3653),(26,'프리지아',406),(27,'제라늄',2890),(28,'진저',1396),(29,'자몽',1508),(30,'바닐라 초콜릿',3218),(31,'꿀',2178),(32,'히야신스',2726),(33,'아이리스',2891),(34,'자스민',441),(35,'자스민 삼박',746),(36,'쥬니퍼베리',3762),(37,'랍다넘',1622),(38,'라벤더',1240),(39,'가죽',2227),(40,'레몬',4165),(41,'백합',2363),(42,'은방울꽃',3436),(43,'라임',2353),(44,'매그놀리아',3982),(45,'만다린',856),(46,'미모사',740),(47,'민트',635),(48,'모스',4002),(49,'머스크',2641),(50,'몰약',3853),(51,'네롤리',4018),(52,'넛맥',2550),(53,'오크모스',1256),(54,'오렌지',867),(55,'난초',3966),(56,'북꽃',3740),(57,'오스만투스',1113),(58,'침향',2237),(59,'파출리',3353),(60,'복숭아',1736),(61,'배',2465),(62,'모란',911),(63,'감귤',3941),(64,'파인애플',285),(65,'핑크 페퍼',636),(66,'자두',1974),(67,'라즈베리',4477),(68,'장미',238),(69,'로즈마리',2978),(70,'샤프론',1330),(71,'허브',4541),(72,'샌달우드',2562),(73,'매운향',3683),(74,'귤',192),(75,'통카',500),(76,'튜베로즈',2565),(77,'바닐라',3258),(78,'베티버',3838),(79,'바이올렛',3910),(80,'바이올렛 리프',702),(81,'화이트 머스크',1788),(82,'우디',1390),(83,'일랑',525);
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-07 15:08:13
