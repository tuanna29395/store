-- MySQL dump 10.13  Distrib 5.7.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: store
-- ------------------------------------------------------
-- Server version	5.7.28-log

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Trà sữa','2020-06-15 02:24:42','2020-07-14 13:13:20',0,NULL,NULL,2),(2,'Nước trái cây tươi','2020-06-15 02:25:04','2020-07-12 08:32:01',0,NULL,NULL,2),(3,'Nước đá xay','2020-06-15 02:26:16','2020-06-15 02:26:16',0,NULL,NULL,2),(4,'Mango','2020-07-14 12:37:24','2020-07-14 12:37:24',0,NULL,NULL,2),(5,'Matcha series','2020-07-14 12:48:51','2020-07-14 12:50:24',0,NULL,NULL,2),(6,'Trà nguyên chất','2020-07-14 13:01:23','2020-07-14 13:01:23',0,NULL,NULL,2),(7,'THỨC UỐNG ĐẶC BIỆT GONG CHA','2020-07-14 13:06:23','2020-07-14 13:06:23',0,NULL,NULL,2),(8,'THỨC UỐNG SÁNG TẠO','2020-07-14 13:09:08','2020-07-14 13:09:40',0,NULL,NULL,2);
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
INSERT INTO `order_items` VALUES (6,1,1,20000,1,0,'2020-06-01 03:12:44',NULL,'2020-06-01 03:12:44',20000,0),(7,4,1,20000,1,0,'2020-06-17 09:34:35',NULL,'2020-06-17 09:34:35',20000,0),(8,4,1,20000,1,0,'2020-06-17 09:37:35',NULL,'2020-06-17 09:37:35',20000,0),(9,4,1,25000,1,0,'2020-06-19 04:02:59',NULL,'2020-06-19 04:02:59',23750,5),(10,4,2,25000,1,0,'2020-06-19 04:14:36',NULL,'2020-06-19 04:14:36',47500,5),(10,5,2,18000,1,0,'2020-06-19 04:14:36',NULL,'2020-06-19 04:14:36',36000,0),(11,4,1,25000,1,0,'2020-06-19 06:09:00',NULL,'2020-06-19 06:09:00',23750,5),(12,4,1,25000,1,0,'2020-06-25 03:09:47',NULL,'2020-06-25 03:09:47',23750,5),(13,7,2,12000,2,0,'2020-06-25 08:58:45',NULL,'2020-06-25 08:58:45',34000,0),(14,7,1,12000,1,0,'2020-06-25 09:08:35',NULL,'2020-06-25 09:08:35',12000,0),(14,9,2,8000,2,0,'2020-06-25 09:08:35',NULL,'2020-06-25 09:08:35',26000,0),(15,4,2,25000,1,0,'2020-06-29 09:41:15',NULL,'2020-06-29 09:41:15',50000,0),(16,4,2,25000,1,0,'2020-06-29 09:41:27',NULL,'2020-06-29 09:41:27',50000,0),(17,4,1,25000,1,0,'2020-06-29 10:12:17',NULL,'2020-06-29 10:12:17',25000,0),(19,5,1,18000,1,0,'2020-07-01 10:03:46',NULL,'2020-07-01 10:03:46',18000,0),(20,4,1,25000,1,0,'2020-07-02 06:57:49',NULL,'2020-07-02 06:57:49',25000,0),(21,9,2,8000,3,7000,'2020-07-14 14:19:16',NULL,'2020-07-14 14:19:16',30000,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (6,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',1,'2020-06-01 03:12:44','2020-06-01 03:12:44'),(7,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-17 09:34:35','2020-06-17 09:34:35'),(8,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-17 09:37:35','2020-06-17 09:37:35'),(9,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-19 04:02:59','2020-06-19 04:02:59'),(10,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-19 04:14:36','2020-06-19 04:14:36'),(11,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-19 06:09:00','2020-06-19 06:09:00'),(12,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-25 03:09:47','2020-06-25 03:09:47'),(13,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-25 08:58:45','2020-06-25 08:58:45'),(14,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-25 09:08:35','2020-06-25 09:08:35'),(15,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-29 09:41:15','2020-06-29 09:41:15'),(16,1,'paypal','Nguyen anh Tuan','0365213399','67 van cao',2,'2020-06-29 09:41:27','2020-06-29 09:41:27'),(17,1,'payreceived','Nguyen anh Tuan','0365213399','67 van cao',0,'2020-06-29 10:12:17','2020-07-12 10:31:09'),(19,1,'payreceived','Nguyen anh Tuan','0365213399','67 van cao',1,'2020-07-01 10:03:46','2020-07-12 10:43:08'),(20,11,'payreceived','Nguyen Tuan',NULL,NULL,1,'2020-07-02 06:57:49','2020-07-12 10:55:26'),(21,11,'paypal','Nguyen Tuan','0365213399','ha noi',2,'2020-07-14 14:19:16','2020-07-14 14:19:16');
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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Matcha','Apple-Tea-with-Fresh-Pulp.png',20000,25000,'2020-05-05 13:31:04','2020-06-24 02:13:50',0,1,1,NULL,2),(4,'Trà sữa cafe','Milk-Tea.png',17000,25000,'2020-05-05 13:31:04','2020-06-23 07:15:53',0,1,1,'<p>Nước chanh leo giải nhiệt m&ugrave;a h&egrave; tốt cho sức khỏe</p>',2),(5,'Chanh leo','chanhleo.jpg',13000,18000,'2020-05-05 13:33:39','2020-06-23 07:17:27',0,1,3,NULL,2),(6,'Hồng trà việt quất','Black-Tea-Macchiato.png',15000,20000,'2020-05-05 13:33:39','2020-06-23 07:17:10',0,1,3,NULL,2),(7,'Trà xanh','Kiwi-Fruit-Sorbet.png',7000,12000,'2020-05-05 13:33:39','2020-06-23 07:53:45',0,NULL,3,NULL,2),(8,'Chanh leo','chanhleo.jpg',7000,10000,'2020-05-05 13:36:19','2020-07-14 13:21:10',0,NULL,2,NULL,2),(9,'Nước ép dứa','kiwichanhleo.jpg',5000,8000,'2020-05-05 13:36:19','2020-07-14 13:20:30',0,NULL,2,NULL,2),(10,'Hông trà việt quất','hongtravietquat.jpg',8000,12000,'2020-05-05 13:36:19','2020-07-14 13:21:32',0,NULL,2,NULL,2),(11,'Trà sữa tươi','tra-sua-milk-tea.png',15000,20000,'2020-05-09 02:54:58','2020-06-23 06:56:15',0,NULL,1,NULL,2),(12,'Trà sữa cafe thạch','tra-sua-cafe-thach.jpg',17000,22000,'2020-05-09 02:54:58','2020-06-23 07:53:46',0,NULL,1,NULL,2),(13,'Nho tươi','nho.jpg',8000,12000,'2020-05-09 02:54:58','2020-07-14 13:50:20',0,NULL,2,NULL,2),(14,'Trà sữa đậu đỏ','Red-Bean-Milk-Tea.png',15000,20000,'2020-05-09 02:54:58','2020-06-23 07:53:43',0,NULL,1,NULL,2),(15,'Trà sữa thạch rau câu','Red-Bean-Match-Mike.png',17000,20000,'2020-05-09 02:54:58','2020-06-23 07:53:43',0,NULL,1,NULL,2),(16,'Pepsi','Pepsi_non.jpg',7000,10000,'2020-05-09 02:54:58','2020-06-23 07:53:42',0,NULL,3,NULL,2),(17,'Sữa tươi Trân châu','Pearl-Milk-Tea.png',20000,28000,'2020-05-09 02:59:32','2020-06-23 07:53:42',0,NULL,1,NULL,2),(18,'Nước ép cam tươi','Peach-Tea-with-Fresh-Pulp.png',15000,20000,'2020-05-09 03:00:48','2020-06-23 07:53:42',0,NULL,2,NULL,2),(19,'Trà sữa ô long','tra-sua-olong.jpg',20000,25000,'2020-05-09 03:05:32','2020-06-29 07:35:57',0,NULL,1,NULL,3),(20,'Trà sữa alisan','tra-sua-alisan.jpg',25000,30000,'2020-05-09 03:06:58','2020-06-29 07:36:06',0,NULL,1,NULL,3),(21,'Trà sữa trà xanh','tra-sua-tra-xanh.jpg',20000,25000,'2020-05-09 03:07:59','2020-06-29 07:35:39',0,NULL,1,NULL,3),(22,'Mango Milk Tea','tra-sua-cafe-thach.jpg',NULL,30000,'2020-07-14 12:40:58','2020-07-14 12:40:58',0,NULL,4,'<p>Tr&agrave; sữa được l&agrave;m từ nguy&ecirc;n liệu tr&agrave; đen cao cấp kết hợp vị b&eacute;o ngậy của Caramel, sữa v&agrave; thạch caramel mềm dẻo</p>',2),(23,'Mango Matcha Teaffogato','Mango-Fruit-Sorbet.png',NULL,30000,'2020-07-14 12:48:10','2020-07-14 12:48:10',0,NULL,4,'<p>Mango Matcha Teaffogato l&agrave; thức uống sang trọng v&agrave; ho&agrave;n hảo</p>',2),(24,'Mango Matcha Latte','Kiwi-Fruit-Sorbet.png',NULL,30000,'2020-07-14 12:49:46','2020-07-14 12:49:46',0,NULL,5,'<p>Mango Matcha Latte Tr&agrave; d&acirc;u được l&agrave;m từ nguy&ecirc;n liệu tr&agrave; đen cao cấp kết hợp thạch thơm ngon m&aacute;t lạnh</p>',2),(25,'Matcha Strawberry Milk Tea','Apple-Lemon-Babo.png',NULL,40000,'2020-07-14 12:51:29','2020-07-14 12:51:29',0,NULL,5,'<p>Matcha Strawberry Milk Teal&agrave; sự h&ograve;a quyện ho&agrave;n hảo giữa Espresso v&agrave; sữa c&ugrave;ng socola ngọt ng&agrave;o</p>',2),(26,'Trà Sữa Oolong','Cocoa-Milk.png',NULL,25000,'2020-07-14 12:52:18','2020-07-14 12:52:18',0,NULL,1,'<p>Tr&agrave; sữa được l&agrave;m từ nguy&ecirc;n liệu tr&agrave; đen cao cấp kết hợp vị b&eacute;o ngậy của Caramel, sữa v&agrave; thạch caramel mềm dẻo</p>',2),(27,'Trà Sữa Earl Grey','Milk-Tea.png',NULL,20000,'2020-07-14 12:52:59','2020-07-14 12:52:59',0,NULL,1,'<p>Tr&agrave; Sữa Earl Grey l&agrave; sự h&ograve;a quyện ho&agrave;n hảo giữa Espresso v&agrave; sữa c&ugrave;ng socola ngọt ng&agrave;o</p>',2),(28,'Trà Sữa Trà Đen','tra-sua-milk-tea.png',NULL,27000,'2020-07-14 12:53:50','2020-07-14 12:53:50',0,NULL,1,'<p>Tr&agrave; sữa được l&agrave;m từ nguy&ecirc;n liệu tr&agrave; đen cao cấp kết hợp vị b&eacute;o ngậy của Caramel, sữa v&agrave; thạch caramel mềm dẻo</p>',2),(29,'Trà Sữa Chocolate','Pearl-Milk-Tea.png',NULL,30000,'2020-07-14 12:54:40','2020-07-14 12:54:40',0,NULL,1,'<p>Tr&agrave; Sữa Chocolate l&agrave; c&aacute;ch biến tấu nhẹ nh&agrave;ng từ Espresso</p>',2),(30,'Trà Sữa Khoai Môn','Red-Bean-Milk-Tea.png',NULL,35000,'2020-07-14 12:56:20','2020-07-14 13:15:30',0,NULL,1,'<p><strong>Tr&agrave; Sữa Khoai M&ocirc;n d&agrave;nh cho những người y&ecirc;u th&iacute;ch v&agrave; thường uống tr&agrave; sữa&nbsp;mỗi ng&agrave;y.</strong></p>',2),(31,'Trà Sữa Thạch Đen','matcha-milk-tea-chanhleo.png',NULL,30000,'2020-07-14 12:57:22','2020-07-14 12:57:22',0,NULL,1,NULL,2),(32,'Trà Sữa Matcha Đậu Đỏ','Apple-Fruit-Sorbet.png',NULL,35000,'2020-07-14 12:58:09','2020-07-14 12:58:09',0,NULL,1,'<p>&nbsp;Tr&agrave; Sữa Matcha Đậu Đỏ được l&agrave;m từ nguy&ecirc;n liệu tr&agrave; đen cao cấp kết hợp thạch thơm ngon m&aacute;t lạnh</p>',2),(33,'Xoài Đá Xay','Kumquat-Lemon-Fiber-Jelly-Tea.png',NULL,50000,'2020-07-14 12:59:28','2020-07-14 12:59:28',0,NULL,3,'<p>Xo&agrave;i Đ&aacute; Xay l&agrave;m từ nguy&ecirc;n liệu xo&agrave;i tươi&nbsp;cao cấp kết hợp vị b&eacute;o ngậy của Caramel, sữa v&agrave; thạch caramel mềm dẻo</p>',2),(34,'Yakult Đào Đá Xay','Black-Tea.png',NULL,40000,'2020-07-14 13:00:03','2020-07-14 13:00:03',0,NULL,3,'<p>Tr&agrave; hoa quả đặc biệt gi&uacute;p thanh lọc cơ thể, đập tan ng&agrave;y d&agrave;i căng thẳng.</p>',2),(35,'Socola Đá Xay','Cocoa-Milk.png',NULL,35000,'2020-07-14 13:00:58','2020-07-14 13:00:58',0,NULL,3,'<p>Americano l&agrave; c&aacute;ch biến tấu nhẹ nh&agrave;ng từ Espresso</p>',2),(36,'Trà Oolong','Black-Tea-Macchiato.png',NULL,20000,'2020-07-14 13:02:11','2020-07-14 13:04:26',0,NULL,6,'<p>Tr&agrave; đen hương đ&agrave;o c&ugrave;ng đ&agrave;o miếng ng&acirc;m siro thơm ngon.</p>',2),(37,'Trà Xanh','Matcha-Milk-Tea.png',NULL,30000,'2020-07-14 13:02:38','2020-07-14 13:02:38',0,NULL,6,NULL,2),(38,'Trà Bí Đao','Kiwi-Smoothies.png',NULL,30000,'2020-07-14 13:03:17','2020-07-14 13:03:17',0,NULL,6,'<p>Tr&agrave; B&iacute; Đao đặc biệt gi&uacute;p thanh lọc cơ thể, đập tan ng&agrave;y d&agrave;i căng thẳng.</p>',2),(39,'Trà Bí Đao Alisan','kiwichanhleo.jpg',NULL,40000,'2020-07-14 13:06:05','2020-07-14 13:06:05',0,NULL,6,NULL,2),(40,'Trà Bí Đao Kem Sữa','Apple-Lemon-Babo.png',NULL,30000,'2020-07-14 13:10:26','2020-07-14 13:10:26',0,NULL,7,NULL,2),(41,'Trà Đen Kem Sữa','Apple-Tea-with-Fresh-Pulp.png',NULL,40000,'2020-07-14 13:10:54','2020-07-14 13:10:54',0,NULL,7,NULL,2),(42,'Trà Bí Đao Kem Sữa','Mango-Smoothies.png',NULL,45000,'2020-07-14 13:11:20','2020-07-14 13:11:20',0,NULL,7,NULL,2),(43,'Chanh Ai-Yu & Trân Châu Trắng','Apple-Fruit-Sorbet.png',NULL,45000,'2020-07-14 13:11:48','2020-07-14 13:11:48',0,NULL,8,NULL,2),(44,'Đào Hồng Mận & Hột É','Kiwi-Fruit-Sorbet.png',NULL,40000,'2020-07-14 13:12:17','2020-07-14 13:12:17',0,NULL,8,NULL,2),(45,'Trà Alisan Chanh Dây','Mango-Fruit-Sorbet.png',NULL,35000,'2020-07-14 13:12:47','2020-07-14 13:12:47',0,NULL,8,NULL,2);
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
  `review_content` longtext COLLATE utf8mb4_unicode_ci,
  `number_star` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,8,4,'rat ngon',NULL,'2020-06-29 07:44:26'),(2,8,4,'qua ngon',NULL,'2020-06-29 07:44:31'),(3,1,5,'ngo9n',NULL,'2020-06-29 08:34:57'),(4,11,8,'Rất ngon và bổ dưỡng',3,'2020-07-12 08:23:02'),(5,11,8,'ádsa',NULL,'2020-07-12 08:23:10');
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
  `avatar` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type_login` int(4) DEFAULT NULL,
  `user_app_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'neo29395@gmail.com','$2a$10$t57.zw65ysnwYYFzESaBiOeH7SFQjhlwcY5qiVaPF3eVovsNB3QHa','anhtuan',2,'2020-04-12 03:16:54','2020-07-03 06:27:09',NULL,0,'Nguyen anh Tuan','67 van cao','0365213399',NULL,NULL,NULL),(2,'TESS@GMAIL.COM','$2a$10$lKVAM5wlakV7Akp7AWAvmuW8XKmeh.G2dOv1VAl2iSHRzSD1krV5y','test12345',2,'2020-04-12 03:18:00','2020-04-12 03:18:00',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(3,'test1@gmail.com','$2a$10$Kr9UbBSmBMtJJLsEpi4AXeQhiNATVeO7DrqdZ2.EC6HYNAhXTTl/C','nguyễn anh tuấn',2,'2020-04-12 08:26:19','2020-04-12 08:26:19',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(4,'neo29391235@gmail.com','$2a$10$zhidTLf28FciXalmgWGHwekX0ruYy3SrRfH6Q4Iqx1egyWWxGdFp.','anhtuan',2,'2020-05-16 13:00:29','2020-05-16 13:00:29',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(5,'tuan123@gmail.com','$2a$10$wsvpHnFShNNjkbRhLdtgy.F1u/wYRmhaUSWNX.ahdAaUBNFAnmT8K','anhtuan',2,'2020-06-01 02:45:24','2020-06-01 02:45:24',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(7,'neo293952@gmail.com','$2a$10$1pBKZMHTACB8SaeqbXY5Du/2TYt6oFebIPswCUDuzWGf75VJpfZXm','anhtuan',2,'2020-06-19 04:28:24','2020-06-19 04:28:24',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL),(8,'admintest@gmail.com','$2a$10$t57.zw65ysnwYYFzESaBiOeH7SFQjhlwcY5qiVaPF3eVovsNB3QHa','admin',1,'2020-04-12 03:16:54','2020-06-10 07:42:03',NULL,0,'','','',NULL,NULL,NULL),(11,NULL,'','Nguyen Tuan',2,'2020-07-01 09:28:08','2020-07-01 09:28:08',NULL,0,'Nguyen Tuan',NULL,NULL,'https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=274419690443151&height=50&width=50&ext=1596187687&hash=AeSeIqoXGAYAFUkh',1,'274419690443151'),(12,'tuan123324@gmail.com','$2a$10$zpZc5gZ0c8g4wKi2JndMpO0511EfnxP1qWZz4NMyHjh8PdkGG/QFy','tuan1234',2,'2020-07-02 07:15:54','2020-07-02 07:15:54',NULL,0,NULL,NULL,NULL,NULL,NULL,NULL);
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

-- Dump completed on 2020-07-14 21:53:37
