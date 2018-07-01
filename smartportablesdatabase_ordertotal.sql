-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: smartportablesdatabase
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ordertotal`
--

DROP TABLE IF EXISTS `ordertotal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordertotal` (
  `orderId` varchar(45) NOT NULL,
  `dateofOrder` varchar(45) DEFAULT NULL,
  `dateofdelivery` varchar(45) DEFAULT NULL,
  `totalPrice` varchar(45) DEFAULT NULL,
  `customerEmailId` varchar(45) DEFAULT NULL,
  `shippingAddress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordertotal`
--

LOCK TABLES `ordertotal` WRITE;
/*!40000 ALTER TABLE `ordertotal` DISABLE KEYS */;
INSERT INTO `ordertotal` VALUES ('SP#120','10/02/2017 17:20:50','10/16/2017','269.0','ahsanburney','3515 Sierra Pines Drive'),('SP#157','11/07/2017 11:53:55','11/21/2017','893.0','ahsanburney','3515 Sierra Pines Dr'),('SP#180','10/14/2017 15:21:07','10/28/2017','52.0','ahsanburney','3515 Sierra Pines Drive'),('SP#181','11/20/2017 15:37:51','12/04/2017','722.0','ahsanburney','3515 Sierra Pines Drive'),('SP#199','10/23/2017 22:49:09','11/06/2017','293.0','raj','3515 Sierra Pines Dr'),('SP#223','10/14/2017 16:28:04','10/28/2017','90.0','ahsanburney','3515 Sierra Pines Drive'),('SP#254','10/19/2017 13:37:53','11/02/2017','250.0','raj','3515 Sierra Pines Dr'),('SP#284','11/09/2017 20:43:48','11/23/2017','499.0','raj','3515 Sierra Pines Dr'),('SP#342','10/14/2017 15:16:55','10/28/2017','775.0','ahsanburney','3515 Sierra Pines Drive'),('SP#344','10/18/2017 15:08:23','11/01/2017','2159.0','jabbar','2851 S Martin Luther King Dr'),('SP#37','11/07/2017 12:00:53','11/21/2017','813.0','ahsanburney','3515 Sierra Pines Dr'),('SP#402','10/19/2017 10:45:54','11/02/2017','293.0','sadique','3001 South King Drive\r\nApartment # 1507'),('SP#414','10/19/2017 11:31:07','11/02/2017','52.0','raj','3001 South King Drive\r\nApartment # 1507'),('SP#418','11/07/2017 12:11:58','11/21/2017','799.0','raj','3515 Sierra Pines Dr'),('SP#419','10/18/2017 15:06:10','11/01/2017','108.0','ahsanburney','3515 Sierra Pines Dr'),('SP#506','10/14/2017 15:18:03','10/28/2017','108.0','ahsanburney','3515 Sierra Pines Drive'),('SP#513','11/07/2017 11:56:57','11/21/2017','1096.0','ahsanburney','3515 Sierra Pines Dr'),('SP#632','10/14/2017 15:34:38','10/28/2017','1434.0','ahsanburney','3515 Sierra Pines Drive'),('SP#676','10/02/2017 17:18:52','10/16/2017','269.0','ahsanburney','3515 Sierra Pines Drive'),('SP#744','11/06/2017 21:23:01','11/20/2017','330.0','ahsanburney','3515 Sierra Pines Dr'),('SP#763','10/18/2017 16:32:31','11/01/2017','19.0','ahsanburney','3515 Sierra Pines Dr'),('SP#776','10/19/2017 13:31:59','11/02/2017','250.0','raj','3001 South King Drive\r\nApartment # 1507'),('SP#813','10/19/2017 15:59:15','11/02/2017','1434.0','new','adafsdfsf'),('SP#831','10/17/2017 15:59:44','10/31/2017','293.0','sadique','3515 Sierra Pines Dr'),('SP#84','10/19/2017 11:49:57','11/02/2017','372.0','raj','3001 South King Drive\r\nApartment # 1507'),('SP#876','10/24/2017 22:33:32','11/07/2017','65.0','jabbar','3515 Sierra Pines Dr'),('SP#880','10/19/2017 10:43:58','11/02/2017','293.0','jabbar','3515 Sierra Pines Dr'),('SP#890','11/07/2017 11:51:55','11/21/2017','811.0','ahsanburney','3515 Sierra Pines Dr'),('SP#894','10/25/2017 20:31:15','11/08/2017','250.0','ahsanburney','3515 Sierra Pines Dr'),('SP#915','10/18/2017 15:07:24','11/01/2017','52.0','sadique','3001 South King Drive\r\nApartment # 1507'),('SP#954','10/23/2017 22:47:16','11/06/2017','946.0','ahsanburney','3515 Sierra Pines Dr'),('SP#966','10/14/2017 15:06:25','10/28/2017','436.0','ahsanburney','3515 Sierra Pines Drive');
/*!40000 ALTER TABLE `ordertotal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-20 15:57:29
