-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: helloworld
-- ------------------------------------------------------
-- Server version	5.6.39-log

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
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `cardid` int(11) NOT NULL DEFAULT '0',
  `name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`cardid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'十元'),(2,'二十元'),(3,'三十元');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardbag`
--

DROP TABLE IF EXISTS `cardbag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardbag` (
  `uid` int(11) DEFAULT NULL,
  `cardid` int(11) DEFAULT NULL,
  KEY `uid` (`uid`),
  KEY `cardid` (`cardid`),
  CONSTRAINT `cardbag_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`userid`) ON DELETE CASCADE,
  CONSTRAINT `cardbag_ibfk_2` FOREIGN KEY (`cardid`) REFERENCES `card` (`cardid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardbag`
--

LOCK TABLES `cardbag` WRITE;
/*!40000 ALTER TABLE `cardbag` DISABLE KEYS */;
INSERT INTO `cardbag` VALUES (8,1),(8,2),(8,3);
/*!40000 ALTER TABLE `cardbag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classify`
--

DROP TABLE IF EXISTS `classify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classify` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classify`
--

LOCK TABLES `classify` WRITE;
/*!40000 ALTER TABLE `classify` DISABLE KEYS */;
INSERT INTO `classify` VALUES (1,'手写板'),(2,'动漫玩偶'),(3,'掌上游戏机'),(4,'动漫周边'),(5,'动漫用笔');
/*!40000 ALTER TABLE `classify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `summary` varchar(250) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `pay` varchar(50) DEFAULT NULL,
  `picture` varchar(250) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `recommend` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `classify` (`cid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'旅行青蛙周边蛙的旅行青蛙动漫可爱毛绒公仔娃娃玩具玩偶情侣礼物','29.45','0人付款','403',11,0,2),(2,'旅行青蛙毛绒玩具公仔可爱女生玩偶抱枕动漫青蛙靠垫送女朋友礼物','15.00','18人付款','405',11,0,2),(3,'定制公仔来图定做金赫奎deft公仔公司吉祥物动漫玩偶定做LOGO人偶','20.00','11人付款','410',11,0,2),(5,'德国NICI小猪威比公仔动漫卡通猪可爱毛绒玩具儿童女友生日礼物','159.00','30人付款','412',11,0,2),(6,'正版王者荣耀庄周的坐骑鲲公仔毛绒大号梦奇猫鲸鲤鱼动漫抱枕玩偶','98.00','46人付款','413',11,0,2),(7,'卡通动漫毛绒玩具蜘蛛侠公仔大号睡觉抱枕布娃娃玩偶生日礼物男孩','28.00','171人付款','414',3,0,2),(8,'日本miniso名创优品咱们裸熊16寸站姿毛绒公仔抱枕动漫玩偶玩具','33.95','97人付款','415',11,0,2),(9,'日本官方海贼王周边礼物情人生日男女玩具乔巴动漫布娃娃毛绒公仔','108.00','58人付款','416',7,0,2),(10,'正版乔巴公仔毛绒正版海贼王玩偶小布娃娃儿童生日礼物卡通动漫','24.00','96人付款','417',7,0,2),(11,'龙猫公仔大号毛绒玩具抱枕宫崎骏日本动漫周边女生生日礼物布娃娃','23.80','74人付款','418',11,0,2),(12,'朴坊zewoo黄色小鸡仔复读学说话玩偶萌公仔录音学舌动漫毛绒玩具','99.00','68人付款','420',11,0,2),(13,'长草颜团子公仔颜文字抱枕动漫周边毛绒玩具圣诞节送女生生日礼物','19.00','194人付款','421',11,0,2),(14,'大圣归来公仔正版孙悟空毛绒玩具动漫电影周边毛绒小空空女生礼物','59.00','97人付款','422',10,0,2),(15,'正版海贼王路飞毛绒玩具布娃娃公仔航海王乔巴路飞生日礼物动漫迷','59.90','43人付款','423',1,0,2),(16,'僵小鱼玩具公仔小僵尸异型抱枕原创玩偶生日礼物二次元动漫周边','128.00','47人付款','424',11,0,2),(18,'神奇宝贝口袋妖怪宠物小精灵皮卡丘公仔抱枕毛绒玩具玩偶动漫周边','39.00','1人付款','428',11,0,2),(19,'生日礼物滑稽表情包doge扔狗柴犬抱枕毛绒玩具公仔可爱动漫神烦狗','28.00','93人付款','430',1,0,2),(20,'无敌小鹿动漫公仔毛绒玩具布娃娃可爱玩偶鹿晗公仔同款公仔抱枕','19.90','96人付款','431',11,0,2),(21,'现货金泰亨花郎玩偶新款穿衣果泰娃娃卡通果果毛绒动漫衍生公仔','59.00','17人付款','433',10,0,2),(22,'婴儿款金泰亨花郎玩偶新款穿衣果泰娃娃卡通果果毛绒动漫衍生公','59.00','38人付款','434',10,0,2),(23,'BOSTO数位屏绘画一体机手绘屏22寸手写数位板动漫PS手绘板电脑板','5980.00','39人付款','502',11,0,1),(24,'NIKU21.5寸手绘屏一体机绘画屏电脑手写PS动漫便携绘图液晶数位板','4999.00','27人付款','508',11,0,1),(25,'Wacom数位板CTL472绘图板Bamboo手绘板电脑绘画板手写学习绘图板','248.00','156人付款','517',11,0,1),(26,'Wacom新CTL-672数位板中号红黑手绘手写提拉米苏动漫番ctl-671','445.00','127人付款','520',11,0,1),(27,'Wacom数位板Bambooctl671电脑绘图板手绘板绘画板手写板学习板','519.00','109人付款','521',11,0,1),(28,'wacom数位板pth660无线手绘板影拓5绘画板intuospro手写板pth651','2950.00','9人付款','535',10,0,1),(29,'Wacom数位板CTL672绘图板Bamboo手绘板电脑绘画板手写学习板671','445.00','65人付款','536',8,0,1),(30,'摹影全新22寸数位屏手绘屏手写液晶屏电脑绘画屏绘图屏数位板','1499.00','22人付款','551',11,0,1),(31,'Wacom数位板CTL6100影拓手绘板Intuos绘图画板电子手写板CTL4100','499.00','31人付款','553',11,0,1),(32,'WacomCTL-672CTL472数位板手绘板绘画bamboo电脑电子手写绘图板','289.00','37人付款','556',5,0,1),(33,'唯酷10寸电子写字板液晶手写板wicue儿童涂鸦手绘画画电脑数位板','85.00','215人付款','558',6,0,1),(34,'WacomCTL-671数位板Bambooctl471手绘板手写板绘图板绘画板','299.00','62人付款','561',10,0,1),(35,'WACOM数位板CTL6100WL影拓Intuos无线手绘板蓝牙绘图手写板4100WL','689.00','21人付款','562',11,0,1),(36,'易普森手写板ESX-885M湖北移动营业厅电脑签名板电子工单数位板','298.00','25人付款','570',11,0,1),(37,'WacomPTH-660数位板影拓5手绘板IntuosPro电脑绘画板手写板','2550.00','7人付款','571',8,0,1),(38,'wacom数位板CTL-671手绘板CTL-471绘画板bamboo电脑手写板正品','259.00','7人付款','572',9,0,1),(39,'wacom数位板CTL4100WL影拓Intuos无线手绘板蓝牙绘图手写板6100WL','479.00','13人付款','577',11,0,1),(41,'Wacom数位板CTL671手绘板绘图手写Bamboo学习板电子绘图板绘画板','389.00','23人付款','588',11,0,1),(42,'Wacom数位板CTL672绘图板Bamboo手绘板电脑绘画板手写学习绘图板','445.00','23人付款','591',11,0,1),(43,'WacomCTL471手绘板BambooCTL-471数位板绘画板绘图板手写学习板','255.00','12人付款','594',11,0,1),(44,'HIDC海信手写板电子笔记本数位板会议记录记事本记账本微课手绘板','899.00','7人付款','599',11,0,1),(45,'汉王小黑0906数位板手绘板电脑绘画板手写板绘图板','236.00','17人付款','601',6,0,1),(46,'任天堂switch主机NS游戏机NX体感电视家用掌机塞尔达马里奥港日版','1258.99','787人付款','323',11,0,3),(47,'掌上外贸儿童益智玩具走珠闯关游戏机弹珠盘迷宫球地摊货源批发店','1.55','11人付款','325',7,0,3),(48,'现货进口原装Bittboy怀旧游戏机掌机bittboy礼物8090后','295.00','23人付款','326',4,0,3),(49,'梦龙彩色掌机洛克王国赛尔号掌上PSP插卡游戏机怀旧小型游戏卡带','24.50','70人付款','328',11,0,3),(50,'儿童水中套圈圈水机玩具80后经典怀旧童年打水机游戏掌机玩具礼物','9.90','751人付款','329',11,0,3),(51,'包邮SONYPS4包PS4pro防尘罩索尼游戏机ps4Slim防尘包保护套','10.00','99人付款','330',7,0,3),(52,'迷你掌上语音王打地鼠游戏机小号打鼹鼠打老鼠成人益智玩具包邮','9.60','559人付款','331',5,0,3),(53,'掌上打地鼠儿童玩具宝宝游戏机中文语音可选关包邮','9.80','346人付款','334',8,0,3),(55,'儿童经典玩具小孩带绳水中套圈圈打游戏机幼儿园小学生掌上礼物品','1.35','176人付款','336',11,0,3),(56,'创意儿童弹珠盘弹珠游戏卡通掌上游戏机玩具迷宫弹射得分机休闲','1.00','2535人付款','338',11,0,3),(59,'抖音同款小型街机迷你儿童彩屏NES掌上游戏机走心的男朋友礼物','99.90','14人付款','342',9,0,3),(60,'迷你打地鼠机儿童掌上游戏机掌机儿童玩具益智经典礼物','9.60','135人付款','343',10,0,3),(61,'正品梦龙彩色游戏机游戏机掌机洛克王国赛尔号机甲旋风插卡游戏机','19.00','26人付款','344',11,0,3),(62,'儿童套圈圈游戏水机玩具80后回忆怀旧经典游戏掌机水中套圈圈玩具','9.90','127人付款','345',7,0,3),(63,'佳智3320动物打地鼠游戏机早教益智音乐学习地鼠掌上电动打地鼠','5.55','2人付款','346',11,0,3),(65,'儿童掌上敲击游戏机早教电动打地鼠幼儿益智1-2-3-4-5岁宝宝玩具','12.50','64人付款','348',11,0,3),(66,'比乐B.toys玩水游戏机怀旧复古锻炼手眼协调掌上益智玩具3岁+','39.90','70人付款','349',11,0,3),(67,'PS3街机家用街机游戏机电视投币格斗机月光宝盒5S97拳皇摇杆4S','658.00','3人付款','350',11,0,3),(68,'创意儿童弹珠盘弹珠游戏卡通掌上游戏机玩具迷宫弹射得分机休闲','1.15','481人付款','352',1,0,3),(69,'儿童玩具俄罗斯方块游戏机经典怀旧黑白屏掌上游戏机','9.90','70人付款','353',8,0,3),(70,'正版梦龙洛克王国游戏机王者徽章红外对战机数码掌机刷卡插卡玩具','20.00','33人付款','355',11,0,3),(71,'少年骇客BEN10地球保卫者掌上游戏机红外联机对战变身侠小班/世事','34.80','15人付款','356',11,0,3),(72,'蓝帽子益智儿童黑科技AR飞车电子产品智能感应掌上手机游戏赛车','129.00','2人付款','357',11,0,3),(73,'梦龙王者荣耀掌上游戏机王者荣耀彩屏游戏机高清电子游戏机','58.00','5人付款','358',11,0,3),(75,'迷你掌上语音王打地鼠游戏机打鼹鼠会说话打老鼠成人益智玩具','5.85','119人付款','360',6,0,3),(76,'lolita兔子垂耳兔抖音兔子同款玩具兔子耳朵会动的帽子二次元可爱','29.00','5745人付款','242',8,0,4),(77,'绝地求生吃鸡空投包补给收纳箱盒凳椅模型道具游戏动漫周边礼物','52.20','3689人付款','243',6,0,4),(78,'萌咔动漫等身抱枕穹妹真白狂三saber蕾姆动漫全身周边定制二次元','61.50','489人付款','246',11,0,4),(79,'梦初音周边miku未来无线动漫概念运动跑步蓝牙耳机挂颈式二次元','45.00','2816人付款','248',9,0,4),(80,'全职高手角色烫金签名板国家队队服色纸次元基地官方动漫周边','15.00','669人付款','252',2,0,4),(81,'七创社凹凸世界动漫周边原创方形毛绒玩具抱枕大号方头抱枕28CM','145.00','637人付款','254',11,0,4),(82,'动漫手表约会大作战时崎狂三动漫周边刻刻帝之眼男女二次元机械表','65.00','1277人付款','255',11,0,4),(83,'动漫魔卡少女樱百变小樱动漫周边二次元动漫明信片卡贴海报桃矢月','6.00','1931人付款','256',11,0,4),(84,'守望先锋dva光枪充电宝动漫周边D.VA二次元cosplay手办激光充电宝','258.00','165人付款','259',11,0,4),(85,'馒头社二次元动漫福袋fate魔卡少女百变小樱刀剑神域乱舞周边','39.00','846人付款','260',9,0,4),(86,'fategrandorder周边黑贞德FGO职介卡移动电源二次元动漫充电宝','88.00','893人付款','261',11,0,4),(87,'动漫周边福袋约会狂三凹凸世界全职高手叶修黄漫老师主题动漫福袋','9.90','1702人付款','262',9,0,4),(88,'动漫充电宝20000毫安二次元re从零开始拉姆蕾姆周边斩赤红之瞳','69.00','281人付款','263',11,0,4),(89,'动漫周边约会miku狂三蕾姆东方无线概念蓝牙耳机二次元入耳头戴式','43.03','626人付款','267',11,0,4),(90,'ASSASSYN动漫周边七宗罪sin七大罪二次元短袖衬衫衣服T恤夏男女','68.00','1042人付款','268',11,0,4),(91,'恋与制作人李泽言白起等身抱枕动漫二次元同人许墨周边人形睡枕','88.00','176人付款','272',10,0,4),(92,'动漫周边龙猫连体睡衣男女萌系卡通纯棉情侣家居服动漫二次元夏季','115.00','430人付款','276',10,0,4),(93,'刀剑神域衣服动漫周边短袖T恤夏季连帽薄款卫衣二次元SAO假两件','58.00','950人付款','278',11,0,4),(94,'守望dva先锋光枪充电宝游戏周边二次元cosplay道具源氏移动电源','123.88','97人付款','279',11,0,4),(95,'明星动漫等身抱枕来图定制动漫周边DIY二次元周边生日礼物定制','39.20','168人付款','280',7,0,4),(96,'二次元我的世界装逼马赛克装B打码眼镜墨镜动漫周边cos','8.50','2259人付款','282',5,0,4),(97,'动漫周边刀剑神域约会大作战时崎狂三蕾姆fateFGO二次元充电宝','27.00','478人付款','284',11,0,4),(98,'北方栖姬动漫保温杯二次元周边男女搞笑表情水壶易拉罐水杯子','35.00','1211人付款','286',11,0,4),(99,'干物妹小埋抱枕长头大猫咪毛绒等身玩偶悲伤猫长抱枕动漫周边礼物','148.00','223人付款','287',11,0,4),(100,'动漫复刻版AF嘴airfit嘴转笔专用笔材料','5.00','9人付款','91',13,0,5),(101,'小学生幼儿园卡通动漫铅笔HB写字绘画儿童专用笔环保无毒桶装','24.80','24人付款','92',14,0,5),(102,'包邮动漫画笔套装新概念漫画笔蘸水笔美工钢笔设计用笔五笔尖','4.50','1人付款','94',8,0,5),(103,'暴力流转笔动漫版vggmod转笔专用笔','45.00','2人付款','101',5,0,5),(104,'正品marvy美辉针管笔草图笔绘图勾线笔手绘动漫模型用笔防水针笔','7.50','5人付款','103',5,0,5),(105,'动漫版百搭笔杆转笔专用笔材料','2.50','4人付款','105',10,0,5),(106,'动漫版糖果色DR胶转笔专用笔材料','6.00','7人付款','106',6,0,5),(107,'superior秀普针管笔勾线笔描线描边笔美术高考动漫设计专用笔','3.40','5人付款','107',7,0,5),(108,'动漫复刻版DR嘴转笔专用笔材料转笔材料FAKE版DR嘴','10.00','3人付款','108',12,0,5),(109,'秀普针管勾线笔防水动漫水性描边笔手绘工画绘画绘图设计专用笔','3.00','9人付款','110',5,0,5),(110,'diy相册专用笔动漫绘画彩色丙烯马克笔手绘涂鸦记号笔单支','3.96','1人付款','111',6,0,5),(111,'2支装penbeat动漫系列专用笔pberpb笔penbeat笔BIC磨砂杆','19.00','2人付款','112',8,0,5),(112,'动漫复刻版三菱嘴纯铜材质转笔材料转笔专用笔材料','3.00','5人付款','128',7,0,5),(113,'动漫复刻版faber嘴转笔专用笔材料转笔材料','4.00','3人付款','130',8,0,5),(114,'秀普高光笔白笔动漫手绘设计黑卡专用笔银色笔白色珠光笔白线笔','5.80','3人付款','134',5,0,5),(115,'秀普针管勾线笔防水动漫水性描边笔手绘工画绘画绘图设计专用笔','3.50','2人付款','139',8,0,5),(116,'创意卡通宫崎骏动漫无脸男中性笔全黑色水性笔办公学习用笔QHB151','3.30','0人付款','145',7,0,5),(117,'秀普针管勾线笔防水动漫水性描边笔手绘工画绘画绘图设计专用笔','3.50','1人付款','147',8,0,5),(118,'中柏绘图笔针管笔防水勾线笔漫画描边笔设计手绘笔动漫模型用笔','20.50','1人付款','150',10,0,5),(119,'自来水笔套装固体水彩颜料专用笔动漫手绘画笔储水清水笔小号勾线','34.90','0人付款','151',9,0,5),(120,'UI交互设计针管勾线描边笔动漫设计用笔','5.00','0人付款','153',5,0,5),(121,'针管勾线笔防水动漫水性描边笔手绘工画绘画绘图设计专用笔','4.85','1人付款','158',8,0,5);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `uid` int(11) DEFAULT NULL,
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(250) DEFAULT NULL,
  `mobile` varchar(250) DEFAULT NULL,
  `city` varchar(250) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `code` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,2,'王桂香','1212314453','山西省阳泉市城区','下站口','04500'),(3,5,'郭印','15235456537','山西省晋中市太谷县','学院路8号','030800'),(3,6,'郭印','15235456537','山西省晋中市太谷县','山西农业大学信息学院','030800'),(3,7,'大白','15123456789','北京市海淀区中关村','南三街','123456'),(1,8,'水立方的','11132441123','山西省阳泉市','水秀乡','04500'),(1,9,'张无力','13133456654','山西省阳泉市白马镇','三院路口','04500'),(5,10,'李菲菲','1314456785','山西省阳泉市','白马镇','04500'),(6,11,'梁美人','1341334567','山西省阳泉市','白马镇','04500'),(7,12,'梁梅林','13133135543','山西省阳泉市','白马镇','04500'),(8,13,'梁玉','14344322341','山西省阳泉市','白马镇','04500'),(8,14,'为龙','14533544321','山西省阳泉市','白马镇','04500');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order2`
--

DROP TABLE IF EXISTS `order2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order2` (
  `oid` varchar(250) NOT NULL DEFAULT '',
  `uid` int(11) NOT NULL DEFAULT '0',
  `message` varchar(255) DEFAULT NULL,
  `price_sum` int(11) DEFAULT NULL,
  `isFu` int(11) DEFAULT '0',
  `isFa` int(11) DEFAULT '0',
  PRIMARY KEY (`oid`,`uid`),
  KEY `uid` (`uid`),
  CONSTRAINT `order2_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order2`
--

LOCK TABLES `order2` WRITE;
/*!40000 ALTER TABLE `order2` DISABLE KEYS */;
INSERT INTO `order2` VALUES ('7e238a24-064b-4cd1-9ca7-06474862e8ce',8,'{\"address\":\"白马镇\",\"code\":\"04500\",\"city\":\"山西省阳泉市\",\"link\":\"梁玉\",\"mobile\":\"14344322341\",\"mid\":13}',11960,1,0);
/*!40000 ALTER TABLE `order2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order2item`
--

DROP TABLE IF EXISTS `order2item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order2item` (
  `oid` varchar(250) NOT NULL DEFAULT '',
  `sid` int(11) NOT NULL DEFAULT '0',
  `sum` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`,`sid`),
  CONSTRAINT `order2item_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `order2` (`oid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order2item`
--

LOCK TABLES `order2item` WRITE;
/*!40000 ALTER TABLE `order2item` DISABLE KEYS */;
INSERT INTO `order2item` VALUES ('7e238a24-064b-4cd1-9ca7-06474862e8ce',23,2);
/*!40000 ALTER TABLE `order2item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping`
--

DROP TABLE IF EXISTS `shopping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopping` (
  `sid` int(11) NOT NULL DEFAULT '0',
  `uid` int(11) NOT NULL DEFAULT '0',
  `sum` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`,`sid`),
  KEY `sid` (`sid`),
  CONSTRAINT `shopping_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `goods` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping`
--

LOCK TABLES `shopping` WRITE;
/*!40000 ALTER TABLE `shopping` DISABLE KEYS */;
INSERT INTO `shopping` VALUES (23,1,1),(24,8,2);
/*!40000 ALTER TABLE `shopping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'小说家');
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

-- Dump completed on 2018-04-29 17:54:02
