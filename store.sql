-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: store
-- ------------------------------------------------------
-- Server version	5.7.30

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
  `is_enabled` int(4) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Trà sữa','2020-06-15 02:24:42','2020-06-19 07:52:06',0,NULL,NULL,2),(2,'Nước trái cây tươi','2020-06-15 02:25:04','2020-06-15 02:25:04',0,NULL,NULL,2),(3,'Nước đá xay','2020-06-15 02:26:16','2020-06-15 02:26:16',0,NULL,NULL,2);
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
  `delete_flag` int(4) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,5,'2020-06-16 17:00:00','2020-06-25 16:59:59','Giảm giá ngày cuối tuần','2020-06-17 07:11:21',0,'2020-06-17 07:11:21');
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `sold_price` decimal(10,0) DEFAULT NULL,
  `size_id` int(11) DEFAULT NULL,
  `size_price` decimal(10,0) DEFAULT '0',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` decimal(10,0) DEFAULT NULL,
  `percent_discount` double DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (6,1,1,20000,1,0,'2020-06-01 03:12:44',NULL,'2020-06-01 03:12:44',20000,0),(7,4,1,20000,1,0,'2020-06-17 09:34:35',NULL,'2020-06-17 09:34:35',20000,0),(8,4,1,20000,1,0,'2020-06-17 09:37:35',NULL,'2020-06-17 09:37:35',20000,0),(9,4,1,25000,1,0,'2020-06-19 04:02:59',NULL,'2020-06-19 04:02:59',23750,5),(10,4,2,25000,1,0,'2020-06-19 04:14:36',NULL,'2020-06-19 04:14:36',47500,5),(10,5,2,18000,1,0,'2020-06-19 04:14:36',NULL,'2020-06-19 04:14:36',36000,0),(11,4,1,25000,1,0,'2020-06-19 06:09:00',NULL,'2020-06-19 06:09:00',23750,5),(12,4,1,25000,1,0,'2020-06-25 03:09:47',NULL,'2020-06-25 03:09:47',23750,5),(13,7,2,12000,2,0,'2020-06-25 08:58:45',NULL,'2020-06-25 08:58:45',34000,0),(14,7,1,12000,1,0,'2020-06-25 09:08:35',NULL,'2020-06-25 09:08:35',12000,0),(14,9,2,8000,2,0,'2020-06-25 09:08:35',NULL,'2020-06-25 09:08:35',26000,0),(15,4,2,25000,1,0,'2020-06-29 09:41:15',NULL,'2020-06-29 09:41:15',50000,0),(16,4,2,25000,1,0,'2020-06-29 09:41:27',NULL,'2020-06-29 09:41:27',50000,0),(17,4,1,25000,1,0,'2020-06-29 10:12:17',NULL,'2020-06-29 10:12:17',25000,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (6,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',1,'2020-06-01 03:12:44','2020-06-01 03:12:44'),(7,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-17 09:34:35','2020-06-17 09:34:35'),(8,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-17 09:37:35','2020-06-17 09:37:35'),(9,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-19 04:02:59','2020-06-19 04:02:59'),(10,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-19 04:14:36','2020-06-19 04:14:36'),(11,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-19 06:09:00','2020-06-19 06:09:00'),(12,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-25 03:09:47','2020-06-25 03:09:47'),(13,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-25 08:58:45','2020-06-25 08:58:45'),(14,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-25 09:08:35','2020-06-25 09:08:35'),(15,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-29 09:41:15','2020-06-29 09:41:15'),(16,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-29 09:41:27','2020-06-29 09:41:27'),(17,1,'payreceived','Nguyen anh Tuan','0365213399','67 van cao',0,'2020-06-29 10:12:17','2020-06-29 10:12:17');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `password_reset_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `expiry_date` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
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
INSERT INTO `product` VALUES (1,'Matcha','Apple-Tea-with-Fresh-Pulp.png',20000,25000,'2020-05-05 13:31:04','2020-06-24 02:13:50',0,1,1,NULL,2),(4,'Trà sữa cafe','Milk-Tea.png',17000,25000,'2020-05-05 13:31:04','2020-06-23 07:15:53',0,1,1,'<p>Nước chanh leo giải nhiệt m&ugrave;a h&egrave; tốt cho sức khỏe</p>',2),(5,'Chanh leo','chanhleo.jpg',13000,18000,'2020-05-05 13:33:39','2020-06-23 07:17:27',0,1,3,NULL,2),(6,'Hồng trà việt quất','Black-Tea-Macchiato.png',15000,20000,'2020-05-05 13:33:39','2020-06-23 07:17:10',0,1,3,NULL,2),(7,'Trà xanh','Kiwi-Fruit-Sorbet.png',7000,12000,'2020-05-05 13:33:39','2020-06-23 07:53:45',0,NULL,3,NULL,2),(8,'7 UP','7up.jpg',7000,10000,'2020-05-05 13:36:19','2020-06-23 06:56:14',0,NULL,2,NULL,2),(9,'c2','c2.jpg',5000,8000,'2020-05-05 13:36:19','2020-06-23 06:56:14',0,NULL,2,NULL,2),(10,'nutri cam','nutri-cam.jpg',8000,12000,'2020-05-05 13:36:19','2020-06-23 06:56:15',0,NULL,2,NULL,2),(11,'Trà sữa tươi','tra-sua-milk-tea.png',15000,20000,'2020-05-09 02:54:58','2020-06-23 06:56:15',0,NULL,1,NULL,2),(12,'Trà sữa cafe thạch','tra-sua-cafe-thach.jpg',17000,22000,'2020-05-09 02:54:58','2020-06-23 07:53:46',0,NULL,1,NULL,2),(13,'Bò húc','red-bull.png',8000,12000,'2020-05-09 02:54:58','2020-06-23 07:53:43',0,NULL,2,NULL,2),(14,'Trà sữa đậu đỏ','Red-Bean-Milk-Tea.png',15000,20000,'2020-05-09 02:54:58','2020-06-23 07:53:43',0,NULL,1,NULL,2),(15,'Trà sữa thạch rau câu','Red-Bean-Match-Mike.png',17000,20000,'2020-05-09 02:54:58','2020-06-23 07:53:43',0,NULL,1,NULL,2),(16,'Pepsi','Pepsi_non.jpg',7000,10000,'2020-05-09 02:54:58','2020-06-23 07:53:42',0,NULL,3,NULL,2),(17,'Sữa tươi Trân châu','Pearl-Milk-Tea.png',20000,28000,'2020-05-09 02:59:32','2020-06-23 07:53:42',0,NULL,1,NULL,2),(18,'Nước ép cam tươi','Peach-Tea-with-Fresh-Pulp.png',15000,20000,'2020-05-09 03:00:48','2020-06-23 07:53:42',0,NULL,2,NULL,2),(19,'Trà sữa ô long','tra-sua-olong.jpg',20000,25000,'2020-05-09 03:05:32','2020-06-29 07:35:57',0,NULL,1,NULL,3),(20,'Trà sữa alisan','tra-sua-alisan.jpg',25000,30000,'2020-05-09 03:06:58','2020-06-29 07:36:06',0,NULL,1,NULL,3),(21,'Trà sữa trà xanh','tra-sua-tra-xanh.jpg',20000,25000,'2020-05-09 03:07:59','2020-06-29 07:35:39',0,NULL,1,NULL,3);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `review_content` longtext,
  `number_star` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,8,4,'rat ngon',NULL,'2020-06-29 07:44:26'),(2,8,4,'qua ngon',NULL,'2020-06-29 07:44:31'),(3,1,5,'ngo9n',NULL,'2020-06-29 08:34:57');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
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
  `price` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,'default',0),(2,'S',5000),(3,'M',7000),(4,'L',9000),(5,'XL',12000);
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
  `full_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'neo29395@gmail.com','$2a$10$fr0yN0Vd9XbntPvnCZnCHOKHpTKahISfWxAcPx66LRTq4CZcKN01S','anhtuan',2,'2020-04-12 03:16:54','2020-06-10 07:42:03',NULL,0,'Nguyen anh Tuan','67 van cao','0365213399'),(2,'TESS@GMAIL.COM','$2a$10$lKVAM5wlakV7Akp7AWAvmuW8XKmeh.G2dOv1VAl2iSHRzSD1krV5y','test12345',2,'2020-04-12 03:18:00','2020-04-12 03:18:00',NULL,0,NULL,NULL,NULL),(3,'test1@gmail.com','$2a$10$Kr9UbBSmBMtJJLsEpi4AXeQhiNATVeO7DrqdZ2.EC6HYNAhXTTl/C','nguyễn anh tuấn',2,'2020-04-12 08:26:19','2020-04-12 08:26:19',NULL,0,NULL,NULL,NULL),(4,'neo29391235@gmail.com','$2a$10$zhidTLf28FciXalmgWGHwekX0ruYy3SrRfH6Q4Iqx1egyWWxGdFp.','anhtuan',2,'2020-05-16 13:00:29','2020-05-16 13:00:29',NULL,0,NULL,NULL,NULL),(5,'tuan123@gmail.com','$2a$10$wsvpHnFShNNjkbRhLdtgy.F1u/wYRmhaUSWNX.ahdAaUBNFAnmT8K','anhtuan',2,'2020-06-01 02:45:24','2020-06-01 02:45:24',NULL,0,NULL,NULL,NULL),(7,'neo293952@gmail.com','$2a$10$1pBKZMHTACB8SaeqbXY5Du/2TYt6oFebIPswCUDuzWGf75VJpfZXm','anhtuan',2,'2020-06-19 04:28:24','2020-06-19 04:28:24',NULL,0,NULL,NULL,NULL),(8,'admintest@gmail.com','$2a$10$fr0yN0Vd9XbntPvnCZnCHOKHpTKahISfWxAcPx66LRTq4CZcKN01S','admin',1,'2020-04-12 03:16:54','2020-06-10 07:42:03',NULL,0,'','','');
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

-- Dump completed on 2020-06-29 17:31:09
