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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id_booking` int(11) NOT NULL AUTO_INCREMENT,
  `name_booking` varchar(45) NOT NULL,
  `consumer_id_consumer` int(11) NOT NULL,
  `coef_multiply` double NOT NULL,
  PRIMARY KEY (`id_booking`,`consumer_id_consumer`),
  UNIQUE KEY `id_booking_UNIQUE` (`id_booking`),
  KEY `fk_booking_consumer1_idx` (`consumer_id_consumer`),
  CONSTRAINT `fk_booking_consumer1` FOREIGN KEY (`consumer_id_consumer`) REFERENCES `consumer` (`id_consumer`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'book_1',1,2.3);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component_parts`
--

DROP TABLE IF EXISTS `component_parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component_parts` (
  `id_component_parts` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `cost` double NOT NULL,
  `producer_id_producer` int(11) NOT NULL,
  PRIMARY KEY (`id_component_parts`),
  UNIQUE KEY `id_component_parts_UNIQUE` (`id_component_parts`),
  KEY `fk_component_parts_producer1_idx` (`producer_id_producer`),
  CONSTRAINT `fk_component_parts_producer1` FOREIGN KEY (`producer_id_producer`) REFERENCES `producer` (`id_producer`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component_parts`
--

LOCK TABLES `component_parts` WRITE;
/*!40000 ALTER TABLE `component_parts` DISABLE KEYS */;
INSERT INTO `component_parts` VALUES (1,'component1',44,2),(2,'component2',22,1),(3,'component111',300,2);
/*!40000 ALTER TABLE `component_parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component_parts_has_production`
--

DROP TABLE IF EXISTS `component_parts_has_production`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `component_parts_has_production` (
  `id_component_parts_has_production` int(11) NOT NULL AUTO_INCREMENT,
  `component_parts_id_component_parts` int(11) NOT NULL,
  `production_id_production` int(11) NOT NULL,
  PRIMARY KEY (`id_component_parts_has_production`,`component_parts_id_component_parts`,`production_id_production`),
  UNIQUE KEY `id_component_parts_has_production_UNIQUE` (`id_component_parts_has_production`),
  KEY `fk_component_parts_has_production_production1_idx` (`production_id_production`),
  KEY `fk_component_parts_has_production_component_parts1_idx` (`component_parts_id_component_parts`),
  CONSTRAINT `fk_component_parts_has_production_component_parts1` FOREIGN KEY (`component_parts_id_component_parts`) REFERENCES `component_parts` (`id_component_parts`),
  CONSTRAINT `fk_component_parts_has_production_production1` FOREIGN KEY (`production_id_production`) REFERENCES `production` (`id_production`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component_parts_has_production`
--

LOCK TABLES `component_parts_has_production` WRITE;
/*!40000 ALTER TABLE `component_parts_has_production` DISABLE KEYS */;
INSERT INTO `component_parts_has_production` VALUES (1,1,1),(2,2,3),(3,3,3);
/*!40000 ALTER TABLE `component_parts_has_production` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumer`
--

DROP TABLE IF EXISTS `consumer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumer` (
  `id_consumer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id_consumer`),
  UNIQUE KEY `id_consumer_UNIQUE` (`id_consumer`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumer`
--

LOCK TABLES `consumer` WRITE;
/*!40000 ALTER TABLE `consumer` DISABLE KEYS */;
INSERT INTO `consumer` VALUES (1,'qwerty'),(2,'root23');
/*!40000 ALTER TABLE `consumer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outgoing_const`
--

DROP TABLE IF EXISTS `outgoing_const`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outgoing_const` (
  `id_outgoing_const` int(11) NOT NULL AUTO_INCREMENT,
  `outgoing_name` varchar(100) NOT NULL,
  `cost` double NOT NULL,
  PRIMARY KEY (`id_outgoing_const`),
  UNIQUE KEY `id_outgoing_const_UNIQUE` (`id_outgoing_const`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outgoing_const`
--

LOCK TABLES `outgoing_const` WRITE;
/*!40000 ALTER TABLE `outgoing_const` DISABLE KEYS */;
INSERT INTO `outgoing_const` VALUES (1,'Аренда здания',33),(2,'Электричество',44),(3,'какая-то издержка',34);
/*!40000 ALTER TABLE `outgoing_const` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outgoing_dynamic`
--

DROP TABLE IF EXISTS `outgoing_dynamic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outgoing_dynamic` (
  `id_outgoing_dynamic` int(11) NOT NULL AUTO_INCREMENT,
  `outgoing_name` varchar(100) NOT NULL,
  `cost` double NOT NULL,
  PRIMARY KEY (`id_outgoing_dynamic`),
  UNIQUE KEY `id_outgoing_dynamic_UNIQUE` (`id_outgoing_dynamic`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outgoing_dynamic`
--

LOCK TABLES `outgoing_dynamic` WRITE;
/*!40000 ALTER TABLE `outgoing_dynamic` DISABLE KEYS */;
INSERT INTO `outgoing_dynamic` VALUES (1,'Человеко/час',220),(2,'станок-час',11),(3,'парам',20),(4,'парам-пам-пам',50);
/*!40000 ALTER TABLE `outgoing_dynamic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producer`
--

DROP TABLE IF EXISTS `producer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producer` (
  `id_producer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id_producer`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producer`
--

LOCK TABLES `producer` WRITE;
/*!40000 ALTER TABLE `producer` DISABLE KEYS */;
INSERT INTO `producer` VALUES (1,'root'),(2,'qwerty');
/*!40000 ALTER TABLE `producer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production`
--

DROP TABLE IF EXISTS `production`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production` (
  `id_production` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(70) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_production`),
  UNIQUE KEY `id_production_UNIQUE` (`id_production`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production`
--

LOCK TABLES `production` WRITE;
/*!40000 ALTER TABLE `production` DISABLE KEYS */;
INSERT INTO `production` VALUES (1,'Production','describe_production'),(2,'Production2','describeProduction_2'),(3,'Production3','describeProduction_3');
/*!40000 ALTER TABLE `production` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production_has_booking`
--

DROP TABLE IF EXISTS `production_has_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production_has_booking` (
  `id_production_has_booking` int(11) NOT NULL AUTO_INCREMENT,
  `production_id_production` int(11) NOT NULL,
  `booking_id_booking` int(11) NOT NULL,
  PRIMARY KEY (`id_production_has_booking`,`production_id_production`,`booking_id_booking`),
  UNIQUE KEY `id_production_has_booking_UNIQUE` (`id_production_has_booking`),
  KEY `fk_production_has_booking_booking1_idx` (`booking_id_booking`),
  KEY `fk_production_has_booking_production1_idx` (`production_id_production`),
  CONSTRAINT `fk_production_has_booking_booking1` FOREIGN KEY (`booking_id_booking`) REFERENCES `booking` (`id_booking`),
  CONSTRAINT `fk_production_has_booking_production1` FOREIGN KEY (`production_id_production`) REFERENCES `production` (`id_production`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_has_booking`
--

LOCK TABLES `production_has_booking` WRITE;
/*!40000 ALTER TABLE `production_has_booking` DISABLE KEYS */;
INSERT INTO `production_has_booking` VALUES (1,1,1),(2,2,1);
/*!40000 ALTER TABLE `production_has_booking` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `production_has_outgoing_dynamic`
--

DROP TABLE IF EXISTS `production_has_outgoing_dynamic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production_has_outgoing_dynamic` (
  `id_production_has_outgoing_dynamic` int(11) NOT NULL AUTO_INCREMENT,
  `production_id_production` int(11) NOT NULL,
  `outgoing_dynamic_id_outgoing_dynamic` int(11) NOT NULL,
  PRIMARY KEY (`id_production_has_outgoing_dynamic`,`production_id_production`,`outgoing_dynamic_id_outgoing_dynamic`),
  UNIQUE KEY `id_production_has_outgoing_dynamic_UNIQUE` (`id_production_has_outgoing_dynamic`),
  KEY `fk_production_has_outgoing_dynamic_outgoing_dynamic1_idx` (`outgoing_dynamic_id_outgoing_dynamic`),
  KEY `fk_production_has_outgoing_dynamic_production1_idx` (`production_id_production`),
  CONSTRAINT `fk_production_has_outgoing_dynamic_outgoing_dynamic1` FOREIGN KEY (`outgoing_dynamic_id_outgoing_dynamic`) REFERENCES `outgoing_dynamic` (`id_outgoing_dynamic`),
  CONSTRAINT `fk_production_has_outgoing_dynamic_production1` FOREIGN KEY (`production_id_production`) REFERENCES `production` (`id_production`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_has_outgoing_dynamic`
--

LOCK TABLES `production_has_outgoing_dynamic` WRITE;
/*!40000 ALTER TABLE `production_has_outgoing_dynamic` DISABLE KEYS */;
INSERT INTO `production_has_outgoing_dynamic` VALUES (1,1,2),(2,3,2);
/*!40000 ALTER TABLE `production_has_outgoing_dynamic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_application`
--

DROP TABLE IF EXISTS `role_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_application` (
  `id_role_application` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id_role_application`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `level_UNIQUE` (`level`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_application`
--

LOCK TABLES `role_application` WRITE;
/*!40000 ALTER TABLE `role_application` DISABLE KEYS */;
INSERT INTO `role_application` VALUES (3,'ADMIN',10),(4,'USER',100);
/*!40000 ALTER TABLE `role_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(60) NOT NULL,
  `password` varchar(256) NOT NULL,
  `status` int(1) DEFAULT NULL,
  `id_role_application` int(11) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_user_role_application` (`id_role_application`),
  CONSTRAINT `fk_user_role_application` FOREIGN KEY (`id_role_application`) REFERENCES `role_application` (`id_role_application`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'root','4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2',1,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-15 23:04:41
