-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: store
-- ------------------------------------------------------
-- Server version	5.7.25

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_flag` int(4) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Trà Sữa','2020-04-19 10:34:16','2020-04-19 10:34:16',NULL,NULL),(2,'Nước đóng chai','2020-04-19 10:35:52','2020-04-19 10:35:52',NULL,NULL),(3,'Nước trái cây','2020-04-19 10:42:20','2020-04-19 10:42:20',NULL,NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `percent` int(11) DEFAULT NULL,
  `start_at` timestamp NULL DEFAULT NULL,
  `end_at` timestamp NULL DEFAULT NULL,
  `description` longtext COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_flag` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `sold_price` decimal(10,0) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `payment` varchar(128) DEFAULT NULL,
  `order_name` varchar(255) DEFAULT NULL,
  `order_phone` varchar(20) DEFAULT NULL,
  `order_address` varchar(255) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image_url` varchar(254) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `original_price` decimal(10,0) DEFAULT NULL,
  `sale_price` decimal(10,0) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_flag` int(4) DEFAULT NULL,
  `discount_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `description` longtext COLLATE utf8mb4_unicode_ci,
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Coca Milk','Apple-Fruit-Sorbet.png',15000,20000,'2020-05-05 13:27:39','2020-05-05 13:27:39',0,NULL,1,NULL,1),(2,'Caramel Milk Tea','Apple-Lemon-Babo.png',15000,20000,'2020-05-05 13:28:22','2020-05-05 13:28:22',0,NULL,1,NULL,1),(3,'Matcha','Apple-Tea-with-Fresh-Pulp.png',20000,25000,'2020-05-05 13:31:04','2020-05-05 13:31:04',0,NULL,1,NULL,1),(4,'Trà sữa cafe','Black-Tea.png',17000,20000,'2020-05-05 13:31:04','2020-05-05 13:31:04',0,NULL,1,NULL,1),(5,'Chanh leo','chanhleo.jpg',13000,18000,'2020-05-05 13:33:39','2020-05-05 13:33:39',0,NULL,3,NULL,1),(6,'Hồng trà việt quất','Black-Tea-Macchiato.png',15000,20000,'2020-05-05 13:33:39','2020-05-05 13:33:39',0,NULL,3,NULL,1),(7,'Trà xanh','Kiwi-Fruit-Sorbet.png',7000,12000,'2020-05-05 13:33:39','2020-05-05 13:33:39',0,NULL,3,NULL,1),(8,'7 UP','7up.jpg',7000,10000,'2020-05-05 13:36:19','2020-05-05 13:36:19',0,NULL,2,NULL,1),(9,'c2','c2.jpg',5000,8000,'2020-05-05 13:36:19','2020-05-05 13:36:19',0,NULL,2,NULL,1),(10,'nutri cam','nutri-cam.jpg',8000,12000,'2020-05-05 13:36:19','2020-05-05 13:36:19',0,NULL,2,NULL,1),(11,'Trà sữa tươi','tra-sua-milk-tea.png',15000,20000,'2020-05-09 02:54:58','2020-05-09 02:54:58',0,NULL,1,NULL,1),(12,'Trà sữa cafe thạch','tra-sua-cafe-thach.jpg',17000,22000,'2020-05-09 02:54:58','2020-05-09 02:54:58',0,NULL,1,NULL,1),(13,'Bò húc','red-bull.png',8000,12000,'2020-05-09 02:54:58','2020-05-09 02:54:58',0,NULL,2,NULL,1),(14,'Trà sữa đậu đỏ','Red-Bean-Milk-Tea.png',15000,20000,'2020-05-09 02:54:58','2020-05-09 02:54:58',0,NULL,1,NULL,1),(15,'Trà sữa thạch rau câu','Red-Bean-Match-Mike.png',17000,20000,'2020-05-09 02:54:58','2020-05-09 02:54:58',0,NULL,1,NULL,1),(16,'Pepsi','Pepsi_non.jpg',7000,10000,'2020-05-09 02:54:58','2020-05-09 02:54:58',0,NULL,3,NULL,1),(17,'Sữa tươi Trân châu đường đen','Pearl-Milk-Tea.png',20000,28000,'2020-05-09 02:59:32','2020-05-09 02:59:32',0,NULL,1,NULL,1),(18,'Nước ép cam tươi','Peach-Tea-with-Fresh-Pulp.png',15000,20000,'2020-05-09 03:00:48','2020-05-09 03:00:48',0,NULL,2,NULL,1),(19,'Trà sữa ô long','tra-sua-olong.jpg',20000,25000,'2020-05-09 03:05:32','2020-05-09 03:05:32',0,NULL,1,NULL,1),(20,'Trà sữa alisan','tra-sua-alisan.jpg',25000,30000,'2020-05-09 03:06:58','2020-05-09 03:06:58',0,NULL,1,NULL,1),(21,'Trà sữa trà xanh','tra-sua-tra-xanh.jpg',20000,25000,'2020-05-09 03:07:59','2020-05-09 03:07:59',0,NULL,1,NULL,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_flag` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN','2020-04-12 03:00:37','2020-04-12 03:00:37',NULL),(2,'CUSTOMER','2020-04-12 03:00:37','2020-04-12 03:00:37',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `size` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_at` timestamp NULL DEFAULT NULL,
  `deleted_flag` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'neo29395@gmail.com','$2a$10$CcjJbcUGBl6yK28I4xbrKO2Gc7KStEAgOUNyQbAF51242cOQI41v.','anhtuan',2,'2020-04-12 03:16:54','2020-04-12 03:16:54',NULL,0),(2,'TESS@GMAIL.COM','$2a$10$lKVAM5wlakV7Akp7AWAvmuW8XKmeh.G2dOv1VAl2iSHRzSD1krV5y','test12345',2,'2020-04-12 03:18:00','2020-04-12 03:18:00',NULL,0),(3,'test1@gmail.com','$2a$10$Kr9UbBSmBMtJJLsEpi4AXeQhiNATVeO7DrqdZ2.EC6HYNAhXTTl/C','nguyễn anh tuấn',2,'2020-04-12 08:26:19','2020-04-12 08:26:19',NULL,0);
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

-- Dump completed on 2020-05-12 15:07:49
