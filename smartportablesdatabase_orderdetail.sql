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
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderdetail` (
  `orderId` varchar(45) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `quantity` varchar(45) DEFAULT NULL,
  `dateofOrder` varchar(45) DEFAULT NULL,
  `dateofdelivery` varchar(45) DEFAULT NULL,
  `customerEmailId` varchar(45) DEFAULT NULL,
  `shippingAddress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES ('SP#157','Apple iPhone 6 ','563.0','1','11/07/2017 11:53:55','11/21/2017','ahsanburney','3515 Sierra Pines Dr'),('SP#181','Samsung Galaxy S7 edge','722.0','1','11/20/2017 15:37:51','12/04/2017','ahsanburney','3515 Sierra Pines Drive'),('SP#199','Apple iPhone 5c','293.0','1','10/23/2017 22:49:09','11/06/2017','raj','3515 Sierra Pines Dr'),('SP#223','Dell Wireless Charging Mat - PM30W17','100.0','1','10/14/2017 16:28:04','10/28/2017','ahsanburney','3515 '),('SP#254','All Samsung Products Extended Warranties for 10 years','250.0','1','10/19/2017 13:37:53','11/02/2017','raj','3515 Sierra Pines Dr'),('SP#284','AhsanDemo','499.0','1','11/09/2017 20:43:48','11/23/2017','raj','3515 Sierra Pines Dr'),('SP#344','MacBook Pro with Touch Bar (15-inch)','2159.0','1','10/18/2017 15:08:23','11/01/2017','jabbar','2851 S Martin Luther King Dr'),('SP#37','Apple iPhone 6 ','813.0','1','11/07/2017 12:00:53','11/21/2017','ahsanburney','3515 Sierra Pines Dr'),('SP#402','Apple iPhone 5c','293.0','1','10/19/2017 10:45:54','11/02/2017','sadique','3001 South King Drive\r\nApartment # 1507'),('SP#419','JBL Clip 2 Waterproof Bluetooth Speaker','108.0','1','10/18/2017 15:06:10','11/01/2017','ahsanburney','3515 Sierra Pines Dr'),('SP#513','Samsung Galaxy','533.0','1','11/07/2017 11:56:57','11/21/2017','ahsanburney','3515 Sierra Pines Dr'),('SP#744','35 Wireless Bose Headphones','330.0','1','11/06/2017 21:23:01','11/20/2017','ahsanburney','3515 Sierra Pines Dr'),('SP#813','Apple MacBook Pro 13-inch','1434.0','1','10/19/2017 15:59:15','11/02/2017','new','adafsdfsf'),('SP#876','Portable Bluetooth Speaker','65.0','1','10/24/2017 22:33:32','11/07/2017','jabbar','3515 Sierra Pines Dr'),('SP#880','Apple iPhone 5c','293.0','1','10/19/2017 10:43:58','11/02/2017','jabbar','3515 Sierra Pines Dr'),('SP#890','Samsung Galaxy S7 edge','611.0','1','11/07/2017 11:51:55','11/21/2017','ahsanburney','3515 Sierra Pines Dr'),('SP#894','All Samsung Products Extended Warranties for 10 years','250.0','1','10/25/2017 20:31:15','11/08/2017','ahsanburney','3515 Sierra Pines Dr'),('SP#915','Philips® EverPlay Portable Bluetooth Speaker','52.0','1','10/18/2017 15:07:24','11/01/2017','sadique','3001 South King Drive\r\nApartment # 1507'),('SP#954','Lenovo Yoga 910','946.0','1','10/23/2017 22:47:16','11/06/2017','ahsanburney','3515 Sierra Pines Dr');
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
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
