-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: test2
-- ------------------------------------------------------
-- Server version	5.7.33-log

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
-- Table structure for table `ent_profile`
--

DROP TABLE IF EXISTS `ent_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ent_profile` (
  `ent_index` int(11) NOT NULL,
  `ent_image` varchar(255) DEFAULT '-',
  `ent_ceo` varchar(20) DEFAULT '-',
  `ent_history` varchar(255) DEFAULT '-',
  `ent_address` varchar(100) DEFAULT '-',
  `ent_website` varchar(100) DEFAULT '-',
  `ent_introduce` varchar(255) DEFAULT '내용을 입력해주세요',
  KEY `FK_enterprise_TO_ent_profile_1` (`ent_index`),
  CONSTRAINT `FK_enterprise_TO_ent_profile_1` FOREIGN KEY (`ent_index`) REFERENCES `enterprise` (`ent_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ent_profile`
--

LOCK TABLES `ent_profile` WRITE;
/*!40000 ALTER TABLE `ent_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `ent_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise`
--

DROP TABLE IF EXISTS `enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enterprise` (
  `ent_index` int(11) NOT NULL AUTO_INCREMENT,
  `ent_id` varchar(20) DEFAULT NULL,
  `ent_password` varchar(60) DEFAULT NULL,
  `ent_name` varchar(20) DEFAULT NULL,
  `ent_contact` varchar(11) DEFAULT NULL,
  `ent_email` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`ent_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise`
--

LOCK TABLES `enterprise` WRITE;
/*!40000 ALTER TABLE `enterprise` DISABLE KEYS */;
/*!40000 ALTER TABLE `enterprise` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `test2`.`enterprise_AFTER_INSERT` AFTER INSERT ON `enterprise` FOR EACH ROW
BEGIN
insert into ent_profile(ent_index) values (new.ent_index);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `fol_index` int(11) NOT NULL AUTO_INCREMENT,
  `follower` int(11) DEFAULT NULL,
  `following` int(11) DEFAULT NULL,
  `fol_type` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`fol_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtag` (
  `tag_index` int(11) NOT NULL AUTO_INCREMENT,
  `taglist_index` int(11) NOT NULL,
  `ind_index` int(11) NOT NULL,
  PRIMARY KEY (`tag_index`),
  KEY `FK_taglist_TO_hashtag_1` (`taglist_index`),
  KEY `FK_individual_TO_hashtag_1` (`ind_index`),
  CONSTRAINT `FK_individual_TO_hashtag_1` FOREIGN KEY (`ind_index`) REFERENCES `individual` (`ind_index`),
  CONSTRAINT `FK_taglist_TO_hashtag_1` FOREIGN KEY (`taglist_index`) REFERENCES `taglist` (`taglist_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
/*!40000 ALTER TABLE `hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ind_profile`
--

DROP TABLE IF EXISTS `ind_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ind_profile` (
  `ind_index` int(11) NOT NULL,
  `ind_resume` varchar(200) DEFAULT '-',
  `ind_video` varchar(200) DEFAULT '-',
  `ind_photo` varchar(200) DEFAULT '-',
  `ind_switch` char(1) DEFAULT 'F',
  `ind_introduce` varchar(200) DEFAULT '내용을 입력해주세요.',
  PRIMARY KEY (`ind_index`),
  CONSTRAINT `FK_individual_TO_ind_profile_1` FOREIGN KEY (`ind_index`) REFERENCES `individual` (`ind_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ind_profile`
--

LOCK TABLES `ind_profile` WRITE;
/*!40000 ALTER TABLE `ind_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `ind_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `individual`
--

DROP TABLE IF EXISTS `individual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `individual` (
  `ind_index` int(11) NOT NULL AUTO_INCREMENT,
  `ind_id` varchar(20) DEFAULT NULL,
  `ind_password` varchar(60) DEFAULT NULL,
  `ind_name` varchar(20) DEFAULT NULL,
  `ind_birth` varchar(8) DEFAULT NULL,
  `ind_gender` char(1) DEFAULT NULL,
  `ind_phone` varchar(11) DEFAULT NULL,
  `ind_email` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ind_index`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `individual`
--

LOCK TABLES `individual` WRITE;
/*!40000 ALTER TABLE `individual` DISABLE KEYS */;
INSERT INTO `individual` VALUES (1,'1','1','1','1','1','1','1');
/*!40000 ALTER TABLE `individual` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `test2`.`individual_AFTER_INSERT` AFTER INSERT ON `individual` FOR EACH ROW
BEGIN
 Insert into ind_profile(ind_index) values (new.ind_index);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `interview`
--

DROP TABLE IF EXISTS `interview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interview` (
  `int_index` int(11) NOT NULL AUTO_INCREMENT,
  `ind_index` int(11) NOT NULL,
  `ent_index` int(11) NOT NULL,
  `int_start` datetime DEFAULT NULL,
  `int_end` datetime DEFAULT NULL,
  `int_url` varchar(255) DEFAULT NULL,
  `int_show` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`int_index`),
  KEY `FK_individual_TO_interview_1` (`ind_index`),
  KEY `FK_enterprise_TO_interview_1` (`ent_index`),
  CONSTRAINT `FK_enterprise_TO_interview_1` FOREIGN KEY (`ent_index`) REFERENCES `enterprise` (`ent_index`),
  CONSTRAINT `FK_individual_TO_interview_1` FOREIGN KEY (`ind_index`) REFERENCES `individual` (`ind_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview`
--

LOCK TABLES `interview` WRITE;
/*!40000 ALTER TABLE `interview` DISABLE KEYS */;
/*!40000 ALTER TABLE `interview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recruit`
--

DROP TABLE IF EXISTS `recruit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recruit` (
  `rec_index` int(11) NOT NULL AUTO_INCREMENT,
  `ent_index` int(11) NOT NULL,
  `rec_detail` varchar(255) DEFAULT NULL,
  `rec_qualifications` varchar(255) DEFAULT NULL,
  `rec_preferention` varchar(255) DEFAULT NULL,
  `rec_starttime` datetime DEFAULT NULL,
  `rec_endtime` datetime DEFAULT NULL,
  `rec_requiredDoc` varchar(255) DEFAULT NULL,
  `rec_deadline` char(1) DEFAULT NULL,
  PRIMARY KEY (`rec_index`),
  KEY `FK_enterprise_TO_recruit_1` (`ent_index`),
  CONSTRAINT `FK_enterprise_TO_recruit_1` FOREIGN KEY (`ent_index`) REFERENCES `enterprise` (`ent_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recruit`
--

LOCK TABLES `recruit` WRITE;
/*!40000 ALTER TABLE `recruit` DISABLE KEYS */;
/*!40000 ALTER TABLE `recruit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suggestion`
--

DROP TABLE IF EXISTS `suggestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suggestion` (
  `sug_index` int(11) NOT NULL AUTO_INCREMENT,
  `sug_send` datetime DEFAULT NULL,
  `sug_timeone` datetime DEFAULT NULL,
  `sug_timetwo` datetime DEFAULT NULL,
  `sug_timethree` datetime DEFAULT NULL,
  `ind_index` int(11) NOT NULL,
  `ent_index` int(11) NOT NULL,
  `sug_decision` datetime DEFAULT NULL,
  `sug_state` char(1) DEFAULT 'W',
  PRIMARY KEY (`sug_index`),
  KEY `FK_individual_TO_suggestion_1` (`ind_index`),
  KEY `FK_enterprise_TO_suggestion_1` (`ent_index`),
  CONSTRAINT `FK_enterprise_TO_suggestion_1` FOREIGN KEY (`ent_index`) REFERENCES `enterprise` (`ent_index`),
  CONSTRAINT `FK_individual_TO_suggestion_1` FOREIGN KEY (`ind_index`) REFERENCES `individual` (`ind_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestion`
--

LOCK TABLES `suggestion` WRITE;
/*!40000 ALTER TABLE `suggestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `suggestion` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `test2`.`suggestion_AFTER_UPDATE` AFTER UPDATE ON `suggestion` FOR EACH ROW
BEGIN
	if old.sug_state != new.sug_state
    then
		insert into interview(ind_index,ent_index,int_start) values (new.ind_index,new.ent_index,new.sug_decision);
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `taglist`
--

DROP TABLE IF EXISTS `taglist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taglist` (
  `taglist_index` int(11) NOT NULL AUTO_INCREMENT,
  `taglist_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`taglist_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taglist`
--

LOCK TABLES `taglist` WRITE;
/*!40000 ALTER TABLE `taglist` DISABLE KEYS */;
/*!40000 ALTER TABLE `taglist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-21 17:37:00
