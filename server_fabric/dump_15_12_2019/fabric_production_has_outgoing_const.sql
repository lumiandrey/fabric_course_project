CREATE DATABASE  IF NOT EXISTS `fabric` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fabric`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: fabric
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
-- Table structure for table `production_has_outgoing_const`
--

DROP TABLE IF EXISTS `production_has_outgoing_const`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production_has_outgoing_const` (
  `id_production_has_outgoing_const` int(11) NOT NULL AUTO_INCREMENT,
  `production_id_production` int(11) NOT NULL,
  `outgoing_const_id_outgoing_const` int(11) NOT NULL,
  PRIMARY KEY (`id_production_has_outgoing_const`,`production_id_production`,`outgoing_const_id_outgoing_const`),
  UNIQUE KEY `id_production_has_outgoing_const_UNIQUE` (`id_production_has_outgoing_const`),
  KEY `fk_production_has_outgoing_const_outgoing_const1_idx` (`outgoing_const_id_outgoing_const`),
  KEY `fk_production_has_outgoing_const_production1_idx` (`production_id_production`),
  CONSTRAINT `fk_production_has_outgoing_const_outgoing_const1` FOREIGN KEY (`outgoing_const_id_outgoing_const`) REFERENCES `outgoing_const` (`id_outgoing_const`),
  CONSTRAINT `fk_production_has_outgoing_const_production1` FOREIGN KEY (`production_id_production`) REFERENCES `production` (`id_production`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_has_outgoing_const`
--

LOCK TABLES `production_has_outgoing_const` WRITE;
/*!40000 ALTER TABLE `production_has_outgoing_const` DISABLE KEYS */;
INSERT INTO `production_has_outgoing_const` VALUES (1,1,1),(2,1,2),(3,3,3);
/*!40000 ALTER TABLE `production_has_outgoing_const` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-15 23:03:15
