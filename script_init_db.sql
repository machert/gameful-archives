-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gameful-archives-api
-- ------------------------------------------------------
-- Server version	8.0.34

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


-- Create or use the database
CREATE DATABASE IF NOT EXISTS `gameful-archives-api`;
USE `gameful-archives-api`;

--
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `games` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `platform` varchar(70) DEFAULT NULL,
  `year_finished` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
INSERT INTO `games` VALUES (1,'Donkey Kong Country I','Super Nintendo',NULL),(2,'Donkey Kong Country II','Super Nintendo',NULL),(4,'Donkey Kong Country III','Super Nintendo',NULL),(5,'Super Mario World','super nintendo',NULL),(6,'Zelda Breath of the wild','Emulador',NULL),(7,'Zelda Ocarina of Time','Nintendo 64',NULL),(8,'Zelda Majora\'s mask','Nintendo 64',NULL),(9,'Resident evil 1','playstation',NULL),(10,'Resident evil 2','playstation',NULL),(11,'Resident evil 3','playstation',NULL),(12,'Resident evil code veronica','?',NULL),(13,'Resident evil 1 remake','PC',NULL),(14,'Resident evil 2 remake','PC',NULL),(15,'Silent hill','Playstation',NULL),(16,'Silent hill 2','playstation 2',NULL),(17,'Silent hill 4','playstation 2',NULL),(18,'Chrono Trigger','super nintendo',NULL),(19,'Super Mario RPG','super nintendo',NULL),(20,'Super Metroid','super nintendo',NULL),(21,'Donkey Kong Country 3','super nintendo',NULL),(22,'Sunset Riders','super nintendo',NULL),(23,'Goof Troop','Super nintendo',NULL),(24,'Prince of persia 2 warrior within','Playstation 2',NULL),(25,'Parasite eve 2','Playstation 1',NULL),(26,'Metal Gear Solid','Playstation 1',NULL),(27,'Valkyrie Profile','Playstation 1',NULL),(28,'Vagrant Story','Playstation 1',NULL),(29,'Diablo','Playstation 1',NULL),(30,'Marvel vs. Capcom Clash of Super Heroes','Playstation 1',NULL),(31,'Yu-Gi-Oh Forbidden Memories','Playstation 1',NULL),(32,'Dragon\'s Dogma','PC',NULL),(33,'Witcher 3','Playstation 4',NULL),(34,'Witcher 2','PC',NULL),(35,'007 GoldenEye','Nintendo 64',NULL),(36,'Perfect Dark','Nintendo 64',NULL),(37,'Dragon Age - Origins','PC',NULL),(38,'Dragon Age - Inquisition','PC',NULL),(39,'Dark Souls 3','PC',NULL),(40,'Dark Souls','PC',NULL);
/*!40000 ALTER TABLE `games` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-14 17:20:46
