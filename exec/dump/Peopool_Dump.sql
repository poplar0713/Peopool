-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 52.79.162.52    Database: peopool
-- ------------------------------------------------------
-- Server version	8.0.26-0ubuntu0.20.04.2

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
-- Table structure for table `classification`
--

DROP TABLE IF EXISTS `classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classification` (
  `cla_index` int NOT NULL AUTO_INCREMENT,
  `ent_index` int NOT NULL,
  `list_index` int NOT NULL,
  PRIMARY KEY (`ent_index`,`list_index`),
  UNIQUE KEY `cla_index_UNIQUE` (`cla_index`),
  KEY `FK_enterprise_TO_classification_idx` (`ent_index`),
  KEY `FK_classlist_idx` (`list_index`),
  CONSTRAINT `FK_classlist` FOREIGN KEY (`list_index`) REFERENCES `classlist` (`list_index`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_entindex` FOREIGN KEY (`ent_index`) REFERENCES `enterprise` (`ent_index`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classification`
--

LOCK TABLES `classification` WRITE;
/*!40000 ALTER TABLE `classification` DISABLE KEYS */;
INSERT INTO `classification` VALUES (141,47,59),(142,47,57),(143,47,55),(144,47,58),(145,48,1),(146,48,31),(147,48,32),(148,49,1),(149,49,36),(151,51,1),(152,51,36),(153,51,31),(154,52,1),(155,52,32),(156,53,1),(157,53,17),(158,54,53),(159,55,17),(160,56,16),(161,57,6),(162,57,1),(165,50,31),(166,30,1),(167,30,21);
/*!40000 ALTER TABLE `classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classlist`
--

DROP TABLE IF EXISTS `classlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classlist` (
  `list_index` int NOT NULL AUTO_INCREMENT,
  `list_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`list_index`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classlist`
--

LOCK TABLES `classlist` WRITE;
/*!40000 ALTER TABLE `classlist` DISABLE KEYS */;
INSERT INTO `classlist` VALUES (1,'대기업'),(2,'중견기업'),(3,'중소기업'),(4,'스타트업'),(5,'호텔/여행/항공'),(6,'음식료/외식/프랜차이즈'),(7,'스포츠/여가/레저'),(8,'뷰티/미용'),(9,'콜센터/아웃소싱/기타'),(10,'정비/AS/카센터'),(11,'렌탈/임대/리스'),(12,'서치펌/헤드헌팅'),(13,'시설관리/보안/경비'),(14,'웨딩/상조/이벤트'),(15,'은행/금융'),(16,'캐피탈/대출'),(17,'증권/보험/카드'),(18,'솔루션/SI/CRM/ERP'),(19,'웹에이전시'),(20,'쇼핑몰/오픈마켓/소셜커머스'),(21,'포털/컨텐츠/커뮤니티'),(22,'네트워크/통신서비스'),(23,'정보보안'),(24,'컴퓨터/하드웨어/장비'),(25,'게임/애니메이션'),(26,'모바일/APP'),(27,'IT컨설팅'),(28,'백화점/유통/도소매'),(29,'무역/상사'),(30,'물류/운송/배송'),(31,'전기/전자/제어'),(32,'반도체/디스플레이/광학'),(33,'기계/기계설비'),(34,'자동차/조선/철강/항공'),(35,'금속/재료/자재'),(36,'화학/에너지/환경'),(37,'섬유/의류/패션'),(38,'생활화학/화장품'),(39,'생활용품/소비재/기타'),(40,'목재/제지/가구'),(41,'식품가공'),(42,'농축산/어업/임업'),(43,'학교(초/중/고/대학/특수)'),(44,'유아/유치원/어린이집'),(45,'학원/어학원/교육원'),(46,'학습지/방문교육'),(47,'건축/설비/환경'),(48,'건설/시공/토목/조경'),(49,'인테리어/자재'),(50,'부동산/중개/임대'),(51,'의료'),(52,'의료(간호/원무/상담)'),(53,'제약/보건/바이오'),(54,'사회복지/요양'),(55,'방송/케이블/프로덕션'),(56,'신문/잡지/언론사'),(57,'광고/홍보/전시'),(58,'영화/음반/배급'),(59,'연예/엔터테인먼트'),(60,'출판/인쇄/사진'),(61,'문화/공연/예술'),(62,'디자인/CAD'),(63,'공기업/공공기관'),(64,'협회/단체'),(65,'컨설팅/연구/조사'),(66,'회계/세무/법무');
/*!40000 ALTER TABLE `classlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ent_profile`
--

DROP TABLE IF EXISTS `ent_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ent_profile` (
  `ent_index` int NOT NULL,
  `ent_image` varchar(255) DEFAULT '-',
  `ent_ceo` varchar(20) DEFAULT '-',
  `ent_history` varchar(255) DEFAULT '-',
  `ent_address` varchar(100) DEFAULT '-',
  `ent_website` varchar(100) DEFAULT '-',
  `ent_introduce` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`ent_index`),
  KEY `FK_enterprise_TO_ent_profile_1` (`ent_index`),
  CONSTRAINT `FK_enterprise_TO_ent_profile_1` FOREIGN KEY (`ent_index`) REFERENCES `enterprise` (`ent_index`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ent_profile`
--

LOCK TABLES `ent_profile` WRITE;
/*!40000 ALTER TABLE `ent_profile` DISABLE KEYS */;
INSERT INTO `ent_profile` VALUES (30,'153','여민수, 조수용, 영화','1995.02.16','제주특별자치도 제주시 첨단로 242',' www.kakaocorp.com','(주)카카오 입니다. 카카오 15만 가즈아'),(31,'79','류영준','2017.04.03','경기도 성남시 분당구 판교역로 152 알파돔타워 12층','https://www.kakaopay.com','2014년 9월, 국내 최초 간편 \'결제\' 서비스를 시작으로<br>\'송금\' \'청구서\' \'멤버십\' \'인증\' \'페이카드\' 까지 -<br><br>카카오페이는 생활 곳곳의 다양한 금융 서비스를 통해 \'지갑 없는 세상\' 을 만들어가고 있습니다.<br><br>기술의 발전으로 사람들은 휴대폰으로 결제를 하고, 돈을 주고받거나 생활비를 납부합니다.<br>일상 속 많은 부분이 편리해졌지만 어렵고 번거로운 것들은 여전히 존재합니다.<br>카카오페이는 기존의 관습을 깨고 윤택한 삶을 위한 생활 금융 서비스를 제공하기 위해<br>복잡한 절차를 간결하게 바꾸고, 수고로움을 줄이고, 이로움을 키우고자 노력합니다.<br><br>짧은 시간 이룬 성과보다 더 엄청나고 놀라운 세상을 카카오페이와 함께 열어갈,<br>바로 당신을 기다립니다!'),(33,'80','박대준, 강한승','2010.07.01','서울시 송파구 송파대로 570, Tower730','https://www.aboutcoupang.com/ko/','쿠팡은 고객이 쿠팡앱을 열어보는 순간부터 상품을 문 앞으로 배송받는 순간까지, 고객 한 분 한 분에게 감동을 줄 수 있도록 쇼핑의 경험을 새롭게 창조하고 있습니다.<br>뛰어난 엔드투엔드(end-to-end) 이커머스와 물류 네트워크, 그리고 고집스러울 정도로 고객만을 생각하는 문화를 바탕으로 쿠팡은 속도, 셀렉션, 그리고 가격 중 그 무엇도 포기하지 않았습니다. 지금 쿠팡은 신선식품을 포함한 수백만 개의 상품이 단 몇 시간 내에 전국 단위로 1년 365일 배송되는 혁신을 실현하였습니다.<br>한국 이커머스 시장의 수백 만 고객을 위한 쿠팡의 노력입니다. 한국은 세계 어느 나라 보다 크고 빠르게 성장하는 이커머스 기회를 가진 시장입니다.<br><br>쿠팡은 지상 최고의 고객 경험을 위해 노력합니다<br>쿠팡 고객이라면 기하급수적으로 개선되는 고객경험을 1년 365일, 하루 24시간 누릴 수 있습니다.<br>당일배송, 새벽배송, 로켓프레시. 쿠팡은 고객의 삶을 모든 과제의 중심에 두고, 신속함과 편리함, 선택과 가치를 보장하기 위해 매일 부단히 노력하고 있습니다.<br><br>포장 제로<br>필요한 상품을 박스포장 없이도 받아볼 수 있습니다. 쿠팡은 75% 이상의 배송상품에 대해 종이상자를 더 이상 사용하지 않는 대신 친환경 가방을 사용해 배송 이후 회수하여 재활용될 수 있도록 합니다.<br><br><br>쉬운 반품<br>박스도 라벨도 우표도 필요 없습니다. 반품하기 버튼을 누르고 상품을 문 앞에 두기만 하면 됩니다.<br><br>기술 덕분에 가능한 멋진 경험<br> 쿠팡은 고객 경험을 개선하기 위해 인프라와 기술에 상당한 금액을 투자했습니다. 100여개의 독자적인 물류센터를 설계, 구축했으며 이는 총 2백삼십만 제곱미터에 달하는 규모로 수 백만 개의 제품을 보관할 수 있습니다. 한국 인구의 70%가 쿠팡 배송센터로부터 10키로미터 내에 거주하고 있습니다.<br>이러한 물리적 인프라는 최첨단 시스템 및 소프트웨어와 접목되어 있습니다. AI와 머신러닝을 이용해 급격한 수요가 발생하기도 전에 미리 예측하며, 상품이 필요하기도 전에 물류와 배송 네트워크에 선제적으로 배치합니다. 그리고 기술을 이용해 수 억건의 주문에 대한 동선을 효율적이고 역동적으로 설계합니다.<br>이러한 노력으로 기존에는 어쩔 수 없이 포기해야만 했던 모든 것이 쿠팡에서 가능해 집니다. 고객은 쿠팡에서 더 큰 선택의 폭, 더 빠른 서비스, 그리고 더 낮아진 비용 모두를 누리게 되었습니다.<br><br>쿠팡 핵심 비즈니스의 성과는 신규 제품과 서비스를 개발하는데 밑거름이 되고 있으며, 쿠팡의 신규 서비스팀은 마치 쿠팡 안의 작은 스타트업 기업처럼 운영됩니다.<br>쿠팡은 언제나 새로운 상품과 고객 경험을 빠르게 개발하고, 테스트하고, 또 최적화합니다. 그리고 우리의 촘촘한 네트워크와 효율적 인프라를 활용해 성장과 규모 확대를 이끌며, 이렇게 마련된 효율성은 낮은 가격이란 형태로 고객에게 돌아갑니다.<br><br>테크 조직은 쿠팡의 핵심으로, 전체 인원의 3분의 1이 넘는 1600여 명의 능력있는 개발자들이 쿠팡에서 일하고 있습니다. 특히 시애틀, 마운틴뷰, 상하이, 베이징, 싱가포르 등 세계 곳곳에서 세계적인 수준의 개발자들이 쿠팡과 함께 하고 있습니다. 비즈니스 전 분야에 걸쳐 새로운 고객 경험을 만들어갈 수 있다는 것이 쿠팡을 선택하게하는 큰 매력이며, 앞으로도 더 많은 이들이 우리와 함께하리라 믿습니다.'),(34,'81','전인천','2010.05.10','서울 강남구 테헤란로114길 38 (대치동,동일타워)','http://www.tmon.co.kr/','✅ 기업 소개<br><br>티몬(TMON)은 2010년 창립을 시작으로 이커머스 시장을 선도하는 기업입니다.<br>약 1000명의 사원이 근무 중이며,<br>기업 가치 1조를 돌파하여 유니콘기업으로 선정 되는 등, 최근 가파른 성장세를 보이고 있습니다.<br>또한 2020년 3월 월간 흑자를 달성하여 이커머스 업계 최초로 흑자 전환에 성공하였습니다.<br><br>✅ 최초의 타임커머스<br><br>‘타임커머스(Time-Commerce)’의 본격적인 도입 이후 서비스를 고도화 및 정교화 하고 있습니다.<br>매 시간 파격적인 상품을 선보이며 고객과 파트너들의 만족을 강화할 수 있도록 노력합니다.<br>‘블랙딜’,‘티몬데이’ 등 다양한 쇼핑 서비스 기획으로 고객 충성도를 높였으며,<br>고객 지표와 수익률 모두 큰 폭으로 상승하였습니다.<br><br>✅ 쇼핑과 콘텐츠의 결합<br><br>티몬(TMON)은 큐레이션딜, 미디어커머스 확대, C2C(Customer-to-Customer) 방송 플랫폼 진행 등을 통해<br>쇼핑과 콘텐츠의 결합을 강화하고 있습니다.<br>티몬의 라이브쇼인 ‘TVON Live’는 지난해 500회 이상 방송을 통해 월 거래액 평균 100억원을 상회했습니다.<br>나아가 개인 방송의 일종인 C2C 방송의 성장은 소비자 유입을 높이는 방법이 될 것으로 예상됩니다.<br><br>✅ 직원의 행복이 곧 회사의 성장<br><br>다양한 복지를 통해 직원의 성장을 응원합니다.<br>티몬의 직원들은 ‘패밀리데이’ , ‘슈퍼패스’ 복지 혜택을 통해 2시간 일찍 퇴근하고 있습니다.<br>또한, 전직원에게 생일 당일에 사용 가능한 오후 반차가 제공됩니다.<br>임직원 전용 특가딜과 같은 타임커머스를 활용한 복지로 즐거움을 제공하고 업무의 효율 또한 강화하고 있습니다.<br>더불어, 여행/숙박/교육/문화 생활 등 다양한 카테고리의 제휴가 당신을 기다립니다.'),(35,'82','홍원표','1985.05.01','서울특별시 송파구 올림픽로 35길 123, 125 (신천동) 삼성SDS타워','www.samsungsds.com','삼성SDS는 1985년 설립된 IT 서비스 기업으로 지난 30여 년간 대한민국 IT 산업의 역사를 개척한다는 자부심과 함께 고객의 니즈, 시대의 변화에 따라 끊임없이 진화해 왔습니다.<br>전산운영, 시스템통합, IT서비스, 그리고 지금의 ICT 서비스까지 삼성SDS는 IT를 활용한 혁신을 통해 고객의 성장과 발전을 견인해 왔습니다. 또한, 삼성 모든 그룹사의 시스템 운영 및 컨설팅, 시스템 통합, IT 아웃소싱, ICT인프라, ICT 융합 등의 주요 사업을 수행하며 삼성그룹이 글로벌 기업으로 성장하는데 중추 역할을 해왔습니다.<br>삼성SDS는 풍부한 경험과 축적된 기술력, 우수한 파트너와 인적 자원을 바탕으로 인프라 구축, 네트워크 서비스, 비즈니스 컨설팅, 제조 IT, 물류 IT, 교육 IT, 의료 IT, Security, Mobility, Cloud, Analytics 등 다양한 ICT 서비스를 제공하며, 세계 수준의 경쟁력을 갖춘 글로벌 ICT 회사로 성장하고 있습니다.<br>앞으로도 삼성SDS는 smart 함을 더해 행복한 ICT 세상을 만들어 가는 글로벌 ICT 기업이 되겠습니다.'),(36,'83','김봉진, 김범준','2011.03.10','서울특별시 송파구 위례성대로 2 (방이동) 장은빌딩','http://www.woowahan.com/','국내 1위 배달앱 배달의민족을 운영하는 ‘우아한형제들’은 \'좋은 음식을 먹고 싶은 곳에서\'라는 비전 아래, 배달이 안 되던 외식업소의 음식을 배달해주는 \'배민라이더스\', 음식업종 자영업자에게 좋은 품질의 배달용품을 합리적인 가격에 제공하는 \'배민상회\' 등으로 배달문화를 바꿔 나가고 있습니다. 또한 종합 \'푸드테크\' 기업으로 나아가기 위하여 해외시장 진출 등의 글로벌 사업과 자율주행 로봇 기술 개발 등의 미래사업으로 사업확장을 준비, 진행하고 있습니다.<br><br>우아한형제들은 창업 초기부터 국내외 유명 투자사에게 가치를 인정받아 왔습니다. 세계적인 투자 은행 골드만삭스와 아시아 최대 규모 투자사 힐하우스 캐피털, 실리콘밸리에 기반을 둔 벤처캐피털 알토스벤처스 등으로부터 총 5천억 원 이상의 투자를 유치하였고 2018년 말, 기업가치가 3조원을 넘어서면서 \'유니콘\' 기업 지위를 공식화하였습니다.<br><br><br>규율위의 자유ㆍ스타보다 팀웍ㆍ진지함과 위트ㆍ열심만큼 성과를 4대 핵심가치로 삼고 있는 우아한형제들은 ‘구성원을 행복하게 만들면 행복한 구성원이 더 좋은 서비스를 만든다’라는 믿음으로 모두가 즐겁게 일하는 회사를 만들어 가기 위해 오늘도 노력합니다.'),(37,'84','김동신','2012.11.01','서울 강남구 선릉로 514, 16층, 17층','https://sendbird.com','센드버드는 인앱 채팅, 영상/음성 메세징 솔루션을 제공하는 실리콘밸리의 서비스형 소프트웨어 (SaaS) 스타트업으로, 전 세계 10,000여개의 모바일과 웹서비스에 SDK(소프트웨어 개발도구)를 제공합니다. 다양한 글로벌 기업들이 센드버드를 통해 고객과 대화채널을 구축하고, 진화된 형태의 수익화 전략을 수행하고 있으며, 월간 플랫폼 사용자 수는 1억 5천 만을 넘어섰습니다.<br><br>한국 출신 스타트업 최초로 와이콤비네이터(Y Combinator)와 테크스타(Techstars) 모두에서 투자를 유치한 바 있으며, 최근 시리즈 C 투자를 성공적으로 마무리하여 국내 최초 기업가치 1조원 이상의 글로벌 B2B 소프트웨어 유니콘 기업이 되었습니다.<br><br>현재까지 실리콘밸리 최정상 투자사로부터 누적 총 2억2천만달러 (약 2,450억 원)의 투자를 유치하였으였으며, 캘리포니아 산 마테오의 본사와 서울의 R&D센터 및 APAC 오피스 그리고 뉴욕, 런던, 싱가포르, 인도 등에 오피스를 두고 있습니다.'),(38,'86','한성숙','1999.06.02','경기 성남시 분당구 불정로 6 (정자동,그린팩토리)','https://www.navercorp.com/','네이버(주)는 한국 최대 검색포털 네이버 뿐만 아니라, 전 세계 2억 명이 사용하고 있는 모바일 메신저 라인, 동영상 카메라 스노우, 디지털 만화 서비스 네이버웹툰 등을 서비스하고 있는 글로벌 ICT 기업입니다. 네이버는 인공지능, 로보틱스, 모빌리티 등 미래 기술에 대한 지속적인 연구개발을 통해 기술 플랫폼의 변화와 혁신을 추구하며 세계 각국의 수많은 이용자와 다양한 파트너들이 함께 성장할 수 있도록 노력하고 있습니다.<br><br>네이버는 연결이 전하는 가치를 새롭게 정립하며 글로벌 도전 집합체로 진화해 나가고 있습니다. 글로벌과 호흡하는 기술을 통해 한 단계 진화한 연결의 가치를 실현하고, 미래의 기술을 오늘날 일상에서 사용할 수 있는 도구로 제안하며 연결의 새로운 가능성을 찾아갑니다. 앞으로도 더 넓은 세상에서 더 큰 미래를 꿈꾸며 네이버가 한 단계 더 도약할 수 있는 계기를 만들겠습니다.'),(39,'87','이정헌','1994.12.26','경기 성남시 분당구 판교로 256번길 7 (삼평동)','http://company.nexon.com/','1994년 설립하여 2014년 12월에 창립 20주년을 맞이한 넥슨 컴퍼니(NEXON COMPANY)는<br>연 매출 1조 9358억 원(2016년 넥슨 일본법인 연결 매출 기준), 전체 사원수 약 4,500명 규모의<br>글로벌 게임회사입니다.<br><br>2002년 일본, 2005년 미국, 2007년 유럽에 현지법인을 설립하며 일찌감치 해외 시장 개척에 앞장서 온<br>넥슨 컴퍼니(NEXON COMPANY)는 일본, 중국, 대만 등 동아시아 3국을 비롯해 북미지역과 유럽, 남미 등 전 세계 100여 개국에 진출해 150여 개의 게임을 서비스 중이며, 현재 약 14억 명의 사용자(계정)을 확보하고 있습니다.<br><br>탄탄한 개발력과 항상 \'최초\'를 추구하고 실현해 낸 크리에이티브를 바탕으로<br>대한민국 온라인 게임 산업을 선도해 온 넥슨과 함께 꿈에 무대로 나아갈 여러분을 찾습니다.'),(40,'88','하성민','1984.04.20','서울 중구 을지로 65','www.sktelecom.com/','에스케이텔레콤(주)의 사업 부문은 이동전화 등의 무선통신사업과 전화, 초고속인터넷 등을 포함한 유선통신사업, 그리고 인터넷포탈서비스, 게임제작 등의 기타사업부문으로 구별됨. 2013년 3분기말 현재 국내 이동전화 가입자는 총인구 대비 100% 보급율을 넘어선 성숙기에 진입한 상황임. 통신산업의 새로운 성장에 기반이 될 스마트폰 가입자는 LTE 가입자 1,100만 명을 포함하여 전분기 대비 65만명 증가한 1,730만 명을 기록하며 시장 1위 사업자의 지위를 더욱 공고히 하였음. SK브로드밴드의 우수한 기술력과 품질을 바탕으로 상품 경쟁력을 제고하여 전 사업 부문에서 가입자 기반을 확대해 나가고 있음'),(41,'89','김용현, 김재현','2015.06.15','서울 강남구 테헤란로4길 14, 14층','-','-'),(42,'125','신중호','2013.02.28','경기 성남시 분당구 황새울로360번길 42 (서현동,AK플라자)','http://linepluscorp.com/','라인 주식회사(LINE Corporation)는 커뮤니케이션 앱 라인(LINE)을 기반으로 커뮤니케이션, 콘텐츠, 엔터테인먼트, 광고 사업 등 모바일에 특화된 서비스를 개발 및 운영하고 있습니다. 이외에도, 핀테크와 AI 사업 등 다양한 분야로 진출하고 있습니다. 라인은 자사 미션인 ‘클로징 더 디스턴스(Closing the Distance)’를 바탕으로 사람, 정보, 콘텐츠, 서비스를 한데 모으는 것을 목표하고 있습니다.<br>LINE Plus Corporation은 2013년 한국에서 LINE의 글로벌 시장을 확장하기 위해 설립됐습니다. 세계 각국의 더 많은 사용자를 만나기 위해 기술, 디자인, 마케팅/영업, 홍보, 제휴 등의 분야에서 글로벌 업무가 진행되고 있습니다. 라인플러스의 직원들은 약 50개국에 이르는 각기 다른 국적의 동료들과 함께 글로벌 단위로 활동하고 있으며, 그 숫자는 점점 더 빠르게 증가하고 있습니다.'),(43,'90','오일석','2000.04.10','서울 강남구 테헤란로 510','www.hyundai-autoever.com','현대오토에버는 현대자동차 그룹의 IT 전문 계열사로서 2000년 4월 설립하여, 그룹의 주력사업인 자동차, 부품, 철강, 물류, 중공업, 건설, 금융 분야 등에서 축적된 IT서비스 노하우와 선진 기술력을 바탕으로 지속적으로 성장해왔습니다.<br>현대오토에버는 ‘고객 가치 증대를 위한 IT서비스 기업’이라는 이념 아래 고객기업의 정보 선진화를 제 일의 핵심가치로 여기며, 최고의 정보 기술과 전문가를 육성하고 있습니다. 그리고, 전문 IT서비스 뿐만 아니라 제조 IT 및 엔지니어링 서비스, 임베디드 S/W개발, 이러닝 등의 분야에서 최고 수준의 기술력을 바탕으로 차별화된 서비스를 제공하고 있습니다.<br>또한, 그룹내 IT 질적 향상을 위해 정보기술 표준화 및 통합화를 구현하여 업무의 효율성을 향상시키고, 품질 강화를 통하여 경영전략의 성공적 수행을 가능케 하며, 아울러 이를 통한 고객사의 미래 가치 발굴을 지원하는 최고의 파트너입니다.'),(45,'91','양민석, 황보경','1998.03.02','서울 마포구 희우정로1길 3','www.ygfamily.com','-'),(47,'126','정욱','1996.04.25','서울 강동구 강동대로 205','www.jype.com','-'),(48,'113','김기남, 김현석, 고동진','1969.01.13','경기 수원시 영통구 삼성로 129 (매탄동,주차타워)','http://www.samsung.com/sec/','삼성전자(주)는 한국 및 CE, IM 부문 산하 해외 9개 지역총괄과 DS 부문 산하 해외 5개 지역총괄의 생산,판매법인 등 158개의 동종업종을 영위하는 종속기업으로 구성된 글로벌 전자기업임. 제품 특성에 따라 CE 사업부문, IM 사업부문, DS 사업부문 3개의 부문으로 나누어 독립 경영하고 있음. 삼성전자만의 고유 디자인을 바탕으로 프리미엄 리더십과 60인치 이상의 초대형 리더십을 강화하고, 사용자가 원하는 컨텐츠를 간편하게 제공하여 Smart TV시장을 주도할 계획. 메모리 판가는 2012년 시장 악화 지속으로 전년 대비 약 40% 하락하였으나 2013년에는 수급 안정화로 하락폭이 25%수준으로 둔화 되었음'),(49,'114','박대영','1974.08.05','서울 서초구 서초대로74길 4 (서초동,삼성생명서초타워)','http://www.shi.samsung.co.kr/','삼성중공업(주)는 삼성그룹에 속한 계열회사로 1974년 설립되었으며 조선부문과 건설부문 사업을 영위하고 있음. 삼성그룹 계열사에는 동사를 포함한 상장사 17개사, 비상장사 60개사가 있음. 조선해양부문은 쇄빙유조선, LNG, 드릴쉽, 초대형컨테이너선 FPSO, LNG-FPSO, 풍력발전기을 생산하며, 주요고객은 로열더치쉘, BP, 쉐브론 등임. E＆I부문은 일반 고객을 대상으로 주택 건축, 토목공사 등의 사업을 영위. 2013년 3분기 기준, 조선해양부문에서 동사는 약 2,004천톤 규모를 수주하였으며 시장점유율은 약 11.0%로 현대중공업, 삼호중공업, 대우조선에 이어 점유율 4위를 기록함.'),(50,'123','박상진, 조남성','1970.01.20','경기 용인시 기흥구 공세로 150-34 (공세동)','www.samsungsdi.com','삼성에스디아이(주)는 PDP 등의 생산 및 판매를 하고 있는 디스플레이 사업부문과 2차전지 등의 생산 및 판매, 임대수익 등의 기타매출 부분을 포함하는 에너지 및 기타 사업부문을 영위함. 차별화된 리튬이온 배터리 기술력과 양산기술로 제품 경쟁력을 제고하는 한편 유럽, 일본, 미국 등지의 유통채널을 지속적으로 확대하고 있음. 태양전지와 ESS(에너지 저장장치)사업의 시너지를 통해 축발전 시스템을 구축하여 차별화된 경쟁력을 확보할 계획임. 삼성전자와의 PDP 통합 운영을 통해 실속형 제품과 Premium 제품 등 차별화된 제품을 출시하고 있음'),(51,'115','이윤태','1973.08.08','경기도 수원시 영통구 매영로 150(매탄동)','http://www.samsungsem.co.kr/','삼성전기의 모태는 1973년 3월 세워진 삼성산요파츠 이다.\n1987년 2월 삼성전기로 상호를 변경했다.\n\n삼성전기는 창립 당시 Audio/Video부품 생산을 기반으로 시작했다.\n80년대에 소재 및 컴퓨터 부품으로 사업영역을 다각화하고,\n90년대에는 칩부품, 이동통신부품, 광부품 등 신제품들을 개발했다.\n2000년대는 소재, 무선 고주파, 파워/정밀 메카기술을 바탕으로 디지털 전자부품등 고수익 사업기반을 강화했다.\n2010 년대는 베트남에 생산 거점을 신설하고 필리핀, 천진에 새 공장을 완공하는 등 글로벌 기업으로의 본격적인 행보에 나서고 있다. 또한 천안에 Fo-PLP 신규 투자를 실시하여 새로운 성장의 기반을 준비하고 있다.\n\n<사업부>\n삼성전기는 모듈, 컴포넌트, 기판 사업부문으로 구성되어 있다.\n\n모듈 사업부는 카메라모듈, 통신모듈, 무선충전모듈 등 모듈을 제조하는 사업부이다.\n모듈의 경우 새로운 수동소자, 재료의 융∙복합을 통한 솔루션을 창출하는 등 기술 집약적인\n산업 분야이다. 이를 위해 삼성전기는 회로설계, IC 등 핵심부품을 내재화하고,\n제품의 복합∙소형화를 추진하여 고객의 요구를 충족시키고 있다.\n\n컴포넌트 사업부는 MLCC(Multi Layer Ceramic Capacitor), 칩 인덕터, 칩 저항 등을 개발, 생산한다. 위 제품들은 수동소자 제품으로 IT∙산업∙전장 등 기본적으로 사용되는 필수 전자부품이다. 삼성전기는 이에 대한 독자기술을 확보, 자체 개발한 공법과 설비를 바탕으로 초소형 Size 및 고용량 제품을 지속적으로 출시하고 있다.\n\n기판 사업부는 반도체용 패키지기판, 고밀도 다층기판 등 인쇄회로기판을 제조한다.\n삼성전기는 축적된 공정기술과 제품기술을 바탕으로 새로운 기술과 제품을 개발하기 위해 노력하며 고객의 요구에 대응하고 있다. 점차 IT기기의 소형화∙고기능화에 대응하여\n선폭을 더욱 미세화한 패키지 기판을 개발하는 등 시장 리더십을 더욱 강화할 예정이다.\n'),(52,'116','이동훈','2012.04.03','경기도 용인시 기흥구 삼성로 1','www.samsungdisplay.com','삼성디스플레이(주)는 삼성그룹에 속한 계열사로 2012년 7월에 설립된 LCD및 OLED제조기업임. 1991년 삼성전자의 TFT-LCD 사업을 시작으로 1995년 국내 최초 TFT-LCD를 양하였으며, 2013년 85인치 UHD TV패널을 양산하며 국내외 경쟁사를 압도하는 차별화된 기술력을 바탕으로 한 제품 구성으로 고부가가치 패널 업계 세계 1위로 올라섬'),(53,'122','현성철','1957.04.04','서울 서초구 서초대로74길 11','www.samsunglife.com','삼성그룹계열의 시장점유율 1위 대형생명보험사로서 보험업법 및 관계법령에 의한 보험업, 허용되는 범위 내의 자산운용, 겸영가능업무 및 부수업무를 수행하는 국내 1위의 생명보험사로서의 견실한 재무구조와 위기관리능력과 수익성을 기반으로 신성장 동력확보에 총력을 기울이고 있으며 이를 위해 부유층과 은퇴시장에 집중, 장기적으로는 해외사업을 통해 성장성 확보를 꾀함. 해외사업본부를 해외사업부문으로 확대 개편하였고, 해외사업 전문가를 영입하여 내부 역량 강화하고 있으며 이미 진출한 중국과 태국 시장에서는 현지화를 통해 합작사 영업규모를 확대하고 인프라를 선진화하여 해외사업 가속화.'),(54,'117','김태한','2011.04.22','인천 연수구 첨단대로 125 (송도동,삼성바이오로직스)','www.samsungbiologics.com','-'),(55,'118','최영무','1952.01.26','서울 서초구 서초대로74길 14','www.samsungfire.com','삼성그룹계열의 시장점유율 부동의 1위 대형손해보험사로서 손해보험업과 제3보험업을 핵심사업으로 영위, 그 외 다양한 금융상품을 판매, 이를 바탕으로 한 자산을 운용하여 투자수익 창출. 500개 이상의 지점과 2만 7천여명의 전속 설계사 판매조직 등 전국적인 판매망을 확보하고 있으며, 온라인 직판 채널에서도 2009년 온라인 자동차보험 브랜드인 애니카다이렉트 출시 후 빠른 속도로 성장. 보험위험 관리를 위한 보유 및 재보험 전략, 금리리스크 관리를 위한 자산부채관리 전략(ALM), 예정이율 운용 전략 등 주요 리스크관리 전략을 수립하여 운용하는 등 선제적인 리스크 관리 정책을 펼치고 있음. 11개국에서 해외사업을 추진 중임.'),(56,'119','원기찬','1983.03.03','서울 중구 세종대로 67','www.samsungcard.co.kr','삼성카드(주)는 지배회사는 여신전문금융업(신용카드업ㆍ시설대여업ㆍ할부금융업 등)을 영위하는 여신전문금융회사로서, 주요 사업 부문은 카드사업부문, 할부리스사업부문, 기타부문(생활편의서비스) 등임. 동사는 2013년 3분기말 약 993만의 개인 및 법인 회원과 226만여개의 가맹점, 33개의 영업ㆍ채권지역단을 보유하였으며 본사의 마케팅ㆍ영업 조직과 영업 지역단을 통하여 각종 영업 활동을 수행. 2011년 새로운 BI출시 후 삼성카드2,3 등 숫자 카드를 출시하였으며 2012년 하반기에도 고객의 라이프 스타일별 숫자카드를 출시해 숫자 시리즈의 상품 라인업을 구축하는 등 브랜드 이미지 구축에 힘쓰고 있음'),(57,'120','김동환','2013.12.01','서울 중구 세종대로 67 (태평로2가,삼성본관빌딩)','www.welstory.com','-');
/*!40000 ALTER TABLE `ent_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ent_session`
--

DROP TABLE IF EXISTS `ent_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ent_session` (
  `ent_index` int NOT NULL,
  `sessionid` varchar(200) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`ent_index`),
  CONSTRAINT `FK_enterprise_TO_ent_session` FOREIGN KEY (`ent_index`) REFERENCES `enterprise` (`ent_index`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ent_session`
--

LOCK TABLES `ent_session` WRITE;
/*!40000 ALTER TABLE `ent_session` DISABLE KEYS */;
INSERT INTO `ent_session` VALUES (30,NULL),(31,NULL),(33,NULL),(34,NULL),(35,NULL),(36,NULL),(37,NULL),(38,NULL),(39,NULL),(40,NULL),(41,NULL),(42,NULL),(43,NULL),(45,NULL),(47,NULL),(48,NULL),(49,NULL),(50,NULL),(51,NULL),(52,NULL),(53,NULL),(54,NULL),(55,NULL),(56,NULL),(57,NULL);
/*!40000 ALTER TABLE `ent_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprise`
--

DROP TABLE IF EXISTS `enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enterprise` (
  `ent_index` int NOT NULL AUTO_INCREMENT,
  `ent_id` varchar(20) DEFAULT NULL,
  `ent_password` varchar(60) DEFAULT NULL,
  `ent_name` varchar(20) DEFAULT NULL,
  `ent_contact` varchar(11) DEFAULT NULL,
  `ent_email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ent_index`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprise`
--

LOCK TABLES `enterprise` WRITE;
/*!40000 ALTER TABLE `enterprise` DISABLE KEYS */;
INSERT INTO `enterprise` VALUES (30,'kakao','$2a$10$puaWunAK9pGGjUol782mqO8XPJ4BcIc1U9IutlESzrE76YWAEEZD2','카카오','0267181501','kakao@kakao.com'),(31,'kakaopay','$2a$10$MaH2jWeh0EvC14vypTuoCuEXNu2XxeKlNk2S6FapuaNMK.BEHmvv2','카카오페이','0267181501','kakaopay@kakao.com'),(33,'coupang','$2a$10$KDiYTRDAvhIIEXYnFlHiFO.1fGbDfuFHzJRXoIKifvT.nSYoWIakC','쿠팡(주)','0254621923','recruiting@coupang.com'),(34,'tmon','$2a$10$5g3ktQOvKPA3s6ol8z7ebuNuR/4wymB886k6q8oXaWrStTyrux/NS','(주)티몬','0211622523','recruiting@tmon.com'),(35,'samsungSDS','$2a$10$EiHQ2H71lgNwmYBFA7wcEekb5U1lnNuurHlMZCDiY2F0yj/gLcsQq','삼성에스디에스(주)','0252252183','recruiting@samsungsds.com'),(36,'woowahan','$2a$10$CJjkOcyA6LSlwDsO/CybQe3uDYic/j9iAfeHwNu1CJDIIO5xTUsJO','우아한 형제들','022358872','recruiting@woowahan.com'),(37,'sendbird','$2a$10$owQkO49H2ClRLl01aqzvnOmIMg7PNKu07hruqGjdC2KtQoXpgOPx2','(주)센드버드코리아','0215257872','recruiting@sendbird.com'),(38,'navercorp','$2a$10$abu2sa4b2L2NH3uA/u0wHebMV0DYlU.4c32ADY5tT./F4dk4Ez/z6','네이버(주)','0211635522','recruiting@navercorp.com'),(39,'nexonkorea','$2a$10$B5M/wc9M0UP5utUuhhTiheV1ypdVZ4wxWSTqrpPYskKlG7NxXBvpy','(주)넥슨코리아','0277122321','recruiting@nexon.com'),(40,'sktelecom','$2a$10$BaL1GABDIP7C/jl/Tb.yuuFNcx9wK8Lsl.gvMrjG5GAdtOzRaka/C','SK텔레콤(주)','0271623441','recruiting@sktelecom.com'),(41,'daangn','$2a$10$.ud56OpVw9tg5ESln4mXJuk5jdH9OU4MkaiiYCEO3BoyddUY.ZCzG','(주)당근마켓','0278235946','recruiting@daangn.com'),(42,'lineplus','$2a$10$5NOuCs9jxGZUBJutcN06v.g1eikb5rb5/MlShFh7rpIuBhBAd1E/y','라인플러스(주)','0274512367','recruiting@linepluscorp.com'),(43,'autoever','$2a$10$FxDhIBVuGOFwNoQJl253qebXPdskrmNqNJ7B14J0qnuCBU.VUJ.ou','현대오토에버(주)','0222586367','recruiting@hyundai.com'),(45,'ygenter','$2a$10$/bqe5FgwI8RMSVmy7Q/jf.v6VNjgiZGhCxUoipL7ueBFZJ5EXV4RC','와이지엔터테인먼트','01019960301','yg@yg.com'),(47,'JYPEnter','$2a$10$P2mBAFSftqSR2fDUSpkZ8uyALzNSYSvW7LvGJfCCObQlhBB6BYgCi','제이와이피엔터테인먼트','0224256295','JYPEnter@jype.com'),(48,'samsung','$2a$10$BrkwM9MgMA7g64AbO4/Hl.f4JMYfUUX8vYWuXT0QJNhQvUgzAnoYu','삼성전자(주)','0256112492','recruiting@samsung.com'),(49,'samsungsin','$2a$10$Ml4QM6a0TS7vh/AuBGu6Mu/Ibq7pdXn0eWVIyGDhVo8heG/PcABhK','삼성중공업(주)','0256121142','recruitingsin@samsung.com'),(50,'samsungsdi','$2a$10$Ytr1HU6rWRfJTE4yb.CBouaC/vwbPDBHAXheMQ3YQViqMdrJH9n.K','삼성에스디아이(주)','0256121544','recruitingsdi@samsung.com'),(51,'samsungsem','$2a$10$SfPj9w0vbpaFOIyXdWAWFuY5GSUJBsMeIJne2wKNuK93REwY8ONZK','삼성전기(주)','0256122237','recruitingsem@samsung.com'),(52,'samsungdp','$2a$10$rZXyR4hGhF6yGm2ExnnOCeWyhf/sW74u4wCf0q2p6Maeovv0i0dwq','삼성디스플레이(주)','0256122555','recruitingdp@samsung.com'),(53,'samsunglf','$2a$10$7XDdTv44a128ed2Mf6thu.zG./yubSpCwNkP2BI2OXTkoljSWi1ci','삼성생명보험(주)','02561233323','recruitinglife@samsung.com'),(54,'samsungbio','$2a$10$NFIcXhPEavPPnIIFRoZoS.fZQWnct8d7JGfV.pnU4bBUr3/gVWMwq','삼성바이오로직스(주)','0256124462','recruitingbio@samsung.com'),(55,'samsungfir','$2a$10$iefLPh89zWsnldG/c/qQBO8jjncotkF50Ce7PfMJ3orrq.6sP3x7K','삼성화재해상보험(주)','0256123379','recruitingfire@samsung.com'),(56,'samsungcd','$2a$10$4HkuoO.za.3vU5m8VUreW.FwkXB/chUVYiAhwoJxqJLPfz2opBDB2','삼성카드(주)','0256128891','recruitingcard@samsung.com'),(57,'welstory','$2a$10$nffUEZOJwexDFTarGvWbP.aGTng.dzkI.9GqDu5Cx6NnV.5bL5bc.','삼성웰스토리(주)','0256130019','recruitingwelstory@samsung.com');
/*!40000 ALTER TABLE `enterprise` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`peopool`@`%`*/ /*!50003 TRIGGER `enterprise_AFTER_INSERT` AFTER INSERT ON `enterprise` FOR EACH ROW BEGIN
	insert into ent_profile(ent_index,ent_image) values (new.ent_index,1);
    insert into ent_session(ent_index) values (new.ent_index);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `file_info`
--

DROP TABLE IF EXISTS `file_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_info` (
  `fil_index` int NOT NULL AUTO_INCREMENT,
  `savefolder` varchar(200) DEFAULT NULL,
  `originfile` varchar(200) DEFAULT NULL,
  `savefile` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`fil_index`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_info`
--

LOCK TABLES `file_info` WRITE;
/*!40000 ALTER TABLE `file_info` DISABLE KEYS */;
INSERT INTO `file_info` VALUES (0,'없음','없음','없음'),(1,'210818','sample.png','aee6fda2-a650-4523-8b4d-5b4281fc2dd2.png'),(2,'210818','nomal.png','e42494b1-49f1-4aea-98c3-596451d28420.png'),(3,'210810','photo.PNG','0d541d94-26c1-408b-9872-7413f42e745f.PNG'),(4,'210810','photo3.PNG','c9c4d94e-6723-463f-a445-2b063b8778eb.PNG'),(5,'210810','photo.PNG','89b89591-8b42-4c98-a9d9-997b49eeeec6.PNG'),(6,'210810','photo3.PNG','7c7ca1d5-07fa-43ab-be67-47b94ddd8a04.PNG'),(7,'210810','photo.PNG','4988691f-c73f-427b-bd03-25019585bec5.PNG'),(8,'210810','photo3.PNG','8787f049-0ed5-411c-aa19-b61e57e30efd.PNG'),(9,'210810','photo3.PNG','f0ef1db2-65e7-486a-8ab4-dcd19ea2d4f8.PNG'),(10,'210810','photo.PNG','e09462ab-c2e5-4c9b-8f3c-ae86bfcfc896.PNG'),(11,'210810','photo3.PNG','b855cc97-0e98-41d6-bc5f-7313fc807702.PNG'),(12,'210812','파일 업로드.txt','c35943f5-60eb-423d-9829-9b5533afb74f.txt'),(13,'210813','test.pdf','13256295-0dd6-46fa-8637-40f944475848.pdf'),(14,'210813','신대홍 34기 자기소개 영상 (최종합격).mp4','d7be567e-7972-4e4e-9174-1c481314ff94.mp4'),(15,'210813','구미_2반_D206_팀워크 이벤트.png','821e31dd-9a40-4dbd-b12e-e48830b47d18.png'),(16,'210813','파이팅 프런트.docx','2455089c-76f0-491f-95c3-352aeeb5e6ff.docx'),(17,'210814','ppttest.pptx','7a2475e6-b56f-4930-83dd-897e7bb9434f.pptx'),(18,'210814','test.pdf','43885693-0b71-465c-945c-8bd6000d4da8.pdf'),(19,'210814','지역_반_팀코드_이름(공통프로젝트 활용동의서).docx','b679b012-fbe1-4823-8c5c-b9edd54c27f7.docx'),(20,'210814','지역_반_팀코드_이름(공통프로젝트 활용동의서).docx','2e64163c-b8f8-45f3-9bc2-dbb945e8374c.docx'),(21,'210814','ppttest.pptx','1dd7b77d-e62d-403b-81fa-a56511d049f2.pptx'),(22,'210814','test.pdf','b1a6120f-3cdc-4fbc-a38c-1adb6681253f.pdf'),(23,'210814','파이팅 프런트.docx','5152cdf6-bdf7-4ffb-9647-f6dca5c1b018.docx'),(24,'210814','파이팅 프런트.docx','97be5539-1b52-48b4-8ec1-b5678fd9cd14.docx'),(25,'210814','test.pdf','054b8442-0257-44c9-a2c6-bd620ea4c9c2.pdf'),(26,'210814','test.pdf','2f216b62-b62e-4ed5-8302-a6111f9bb05e.pdf'),(27,'210814','파이팅 프런트.docx','8dfadcaa-e716-4d68-9cbb-9791e1404224.docx'),(28,'210814','파이팅 프런트.docx','a90d5495-eeac-40dd-9915-49ba76e12843.docx'),(29,'210814','test.pdf','6d69a819-9a7d-4678-ae91-d7151051aae4.pdf'),(30,'210814','파이팅 프런트.docx','4cc0136a-80b2-4d44-a2e0-874edb53f750.docx'),(31,'210814','파이팅 프런트.docx','e2ed26c6-481f-4c31-ac4f-72ecd1b71300.docx'),(32,'210814','파이팅 프런트.docx','887f3117-91ae-4df0-ba57-8c52477539b8.docx'),(33,'210815','test.pdf','066506b2-848a-4344-8eb3-4979f3b457cc.pdf'),(34,'210815','파이팅 프런트.docx','c3b0dbea-d867-40ad-8666-a1230ebe1428.docx'),(35,'210815','파이팅 프런트.docx','c1e88adc-e638-45da-88fe-c180df2fcf05.docx'),(36,'210815','파이팅 프런트.docx','f8907edd-48f5-4d10-a6fc-9299815fbf44.docx'),(37,'210815','파이팅 프런트.docx','2307cf6b-ada3-4638-8484-6b592f5ada34.docx'),(38,'210815','test.pdf','78f4cb53-e988-457a-ba41-4754dd4eebce.pdf'),(39,'210815','d.jfif','5450df4e-dade-49ea-8d95-98cc52b53a74.jfif'),(40,'210815','당근.png','e17011c3-0280-4d73-a9ec-9ae6d1b058f1.png'),(41,'210815','당근.png','b635ada8-ce36-474f-a422-893e2dd86eca.png'),(42,'210815','당근.png','1bd8b134-57c6-4b29-abbc-fd7a183738f6.png'),(43,'210815','당근.png','dc090569-d8a9-415c-8e13-056c8b42cd2e.png'),(44,'210815','당근.png','4759595f-e672-4508-a657-cf6f116ba66b.png'),(45,'210815','당근.png','41faffd7-fed4-411a-aa68-284d997ab4b2.png'),(46,'210815','카카오.jpg','4b642cbc-b2a6-4a9c-9c1f-66ccadc9f2cc.jpg'),(47,'210815','카카오.jpg','d97640f6-0570-400f-ba31-9416bc13d367.jpg'),(48,'210815','당근.png','556a03c5-35a3-4fbd-b780-197f7c8adb05.png'),(49,'210815','ssafy.png','d708400f-55ef-43a2-88bc-b87543e10c5a.png'),(50,'210815','브랜디.png','8cc9ca40-9529-4486-b8d5-e34800638528.png'),(51,'210816','Jira_Git_옮기는법.pdf','e0d1229e-c843-46f8-b5d0-d51c9c3caa05.pdf'),(52,'210816','브랜디.png','b4637980-3618-48ae-bee4-45fa12e8f3e4.png'),(53,'210816','브랜디.png','1fed564b-997b-4204-903b-4579117da137.png'),(54,'210816','브랜디.png','ce6dd912-7c64-49b3-8b17-72bafa0a1cc7.png'),(55,'210816','브랜디.png','66ceecdb-2d02-4c37-b845-cd6fb746570d.png'),(56,'210816','브랜디.png','6b9137e6-1099-4dec-8922-c9225337ae84.png'),(57,'210816','브랜디.png','b568a05c-8b1a-4177-9b17-48976420cf4c.png'),(58,'210816','ppttest.pptx','24473c8b-28ee-413c-afd8-ec35edd46427.pptx'),(59,'210816','test.pdf','8b9670ea-4370-4d03-91f5-a17adfecce1e.pdf'),(60,'210816','파이팅 프런트.docx','ebcf92f8-76f6-4360-906a-56cb8ae94680.docx'),(61,'210816','test.pdf','139d742b-ae0f-471b-afb7-d3d8e1c0240e.pdf'),(62,'210816','파이팅 프런트.docx','076b6556-402a-48b2-acd8-5684426d9d78.docx'),(63,'210816','test.pdf','10e9b0f2-ad95-4ba0-b14f-c39f9d664325.pdf'),(64,'210816','ppttest.pptx','380bfc5e-8baa-4c86-b0d6-6195805efab6.pptx'),(65,'210816','ppttest.pptx','c5f04684-2855-4679-93fe-bf0f5eefd1aa.pptx'),(66,'210816','samplevideo.mp4','6ebcb325-3ce9-4292-b8fc-19eeb8236fc2.mp4'),(67,'210816','test.pdf','b9b4d663-e3b2-4188-a507-3800d6f91310.pdf'),(68,'210816','신대홍 34기 자기소개 영상 (최종합격).mp4','c65cb0db-83b9-4c1a-84c0-ffcb4c7b259d.mp4'),(69,'210816','a.jpg','2a8838c2-1501-4787-a502-12df2d614c28.jpg'),(70,'210817','Jira_Git_옮기는법.pdf','9b99e2cf-4f7d-4e84-a454-f68e2d0443c2.pdf'),(71,'210817','Couple - 77004.mp4','66534648-fa24-49fa-8cee-e2a5f3d0f873.mp4'),(72,'210817','【컨설턴트가 제공하는 취업정보】 면접 합격전략-면접컨설팅 시 많이 나오는 질문 Tip.pdf','4cfb5dc1-2faa-46cc-b5af-0cf704a92a10.pdf'),(73,'210817','YG_Logo.jpg','6ee7f2cd-923a-4243-9828-f5362b71b2fc.jpg'),(74,'210817','Couple - 77004.mp4','a3acf8fa-a327-4246-8de0-748111d02baa.mp4'),(75,'210818','【자소서】 주요 기업 자기소개서 항목.pdf','3b591b46-e475-47c3-adab-129c84a4a572.pdf'),(77,'210818','카카오.jpg','5d2b199d-13a7-4e99-b4ce-fa01ed8ac645.jpg'),(78,'210818','카카오.jpg','6b2b8376-988a-49f2-934c-0dc7b7f4de96.jpg'),(79,'210818','카카오페이.jpg','d318eae0-8ce7-4876-afef-d1ac51faf48f.jpg'),(80,'210818','쿠팡.jpg','f3c2a29c-f7aa-4bde-a88b-fa81611e97ce.jpg'),(81,'210818','티몬.jpg','347695b9-9441-4f91-af77-83d04390f175.jpg'),(82,'210818','삼성SDS.png','210783ed-efae-4e74-92c4-33e827495b1b.png'),(83,'210818','우아한형제들.png','42de613d-882d-4538-a1eb-702bb6469cf4.png'),(84,'210818','센드버드.jpeg','32a563be-e8be-4747-924b-753726244d28.jpeg'),(85,'210818','네이버.jpg','84932266-624b-4efb-8060-cf7470835af1.jpg'),(86,'210818','네이버.jpg','6e873ef3-8923-447d-9304-960ba363c674.jpg'),(87,'210818','넥슨코리아.jpg','dbc8f932-53b5-45e0-8699-0b09f3631515.jpg'),(88,'210818','SK텔레콤.jpg','6593e36e-99a4-4587-baef-3044f4b3ec95.jpg'),(89,'210818','당근마켓.jpg','49d778f9-ae82-4689-a017-4831eb78f871.jpg'),(90,'210818','현대오토에버.jpeg','4658225e-25fb-462c-8f02-134fc6559476.jpeg'),(91,'210818','logo_4220.jpg','0aaf20ae-b78e-4a2e-83c0-18f00b4c23fb.jpg'),(92,'210818','Saigon - 14385.mp4','c14cba2a-3397-49cc-9089-1af8a4d8a5e1.mp4'),(93,'210818','Saigon - 14385.mp4','86baf68f-24bc-45fa-ab38-900862efea7f.mp4'),(94,'210818','Saigon - 14385.mp4','c68ef9d9-4fa2-46fd-9577-4bdf2bdadcc0.mp4'),(96,'210818','nomal.png','fe86664e-e87d-4ec2-a467-8a5542afd18e.png'),(97,'210818','nomal.png','2e26413d-9e63-4752-ae42-088dd7cb0534.png'),(99,'210818','b.jpg','b55a4f67-3be9-4c5b-9409-b9be8cd956c6.jpg'),(100,'210818','a.jpg','4f019a60-5dbd-4f42-82aa-34d8756af214.jpg'),(101,'210818','b.jpg','21b3834b-e943-4636-94e5-04cd35735c73.jpg'),(102,'210819','test.pdf','dc2108c8-a68e-4f40-bdc5-10b4bcb99805.pdf'),(103,'210819','a.jpg','40c5fa02-cc4b-474a-9f50-2a11c25e0fd7.jpg'),(104,'210819','samplevideo.mp4','e0eeaabd-0bca-4880-bed3-df86e20f3015.mp4'),(105,'210819','d.jfif','8a6b875e-ed1f-44c0-8587-03a79111d296.jfif'),(106,'210819','b.jpg','92481a1b-c7bf-4f53-a8f3-66532752b10c.jpg'),(107,'210819','a.jpg','60a84652-6a22-45a7-93f4-163a4822a1aa.jpg'),(108,'210819','jeahoon.jpg','4893c84c-85d0-438b-ba8c-4dd153d84240.jpg'),(109,'210819','main2.png','19cd8ef6-c21b-4c7c-b16f-2b948550b23c.png'),(110,'210819','main3.png','d9f65568-ef3e-4289-9e0e-3231d5cbe3bd.png'),(111,'210819','취업사진.jpg','3c678704-3ed7-4e8c-b634-99cf99099b96.jpg'),(112,'210819','samplevideo.mp4','70525e02-fec3-4a8c-8d55-8b1127e6eea7.mp4'),(113,'210819','삼성전자.jpg','61248c3c-2d21-45b4-b72b-2a3ee0c90162.jpg'),(114,'210819','삼성중공업.jpg','d81b463c-c2de-40d1-ae5e-126b69d8ef67.jpg'),(115,'210819','삼성전기.jpg','26af74c8-519f-49ef-9777-a6a26d99e413.jpg'),(116,'210819','삼성디스플레이.jpg','ab40cee9-cbde-4129-9989-bc90cf4453f2.jpg'),(117,'210819','삼성바이오로직스.jpg','88ee1e48-3719-4098-96a5-d058e5ccdfa7.jpg'),(118,'210819','삼성화재해상보험.jpg','c437ee7e-4568-4866-bc4e-64d9fea74829.jpg'),(119,'210819','삼성카드.jpg','d449d95a-cc0d-4df7-8ea5-f77f01f0d571.jpg'),(120,'210819','삼성웰스토리.png','153fd4ea-f3af-46e0-b45d-08f1fdc17f49.png'),(121,'210819','삼성생명.png','7023114e-5087-47f6-91f0-0d504fc3327c.png'),(122,'210819','삼성생명.png','7b159024-c139-4030-b8de-8f07b2b769a1.png'),(123,'210819','삼성에스디아이.jpg','573b15ec-2327-4b1d-8c68-17d321921c86.jpg'),(124,'210819','JYP.jpg','b2625ab7-dc8f-49a7-b52f-e7a4cbed46b8.jpg'),(125,'210819','라인플러스.png','d6c89394-179d-4697-9eb2-e9d01d66032f.png'),(126,'210819','jyp.jpg','c44bc08e-3307-4069-955d-4376656f51ab.jpg'),(127,'210819','samplevideo.mp4','8818f248-9c36-4d43-a8f6-4ca52467d2d3.mp4'),(128,'210819','samplepdf.pdf','75b25fdc-78e0-4bfb-aab3-01c18ee835eb.pdf'),(129,'210819','jennie.jpg','05f5ef29-c1bd-400a-813e-070e2866d3ed.jpg'),(130,'210819','짱구.jpg','6e8686b3-8459-4be4-a5a0-e56417b13ca0.jpg'),(131,'210819','짱구.jpg','1a3e3c3a-4642-4235-a029-cc3ea7dc2831.jpg'),(132,'210819','김아무개.jfif','87ab056f-ff7c-4048-a92a-a349a8974085.jfif'),(133,'210819','공승연.jpg','04eb64d5-51cd-4846-ad4c-3d004f0ce56a.jpg'),(134,'210819','PeoPool.png','e062aefa-172c-4898-8ddf-7841f14b9533.png'),(135,'210819','이제훈.jpg','cc1a6532-1d88-4856-a78d-02b2109edf38.jpg'),(136,'210819','권은빈.jpg','1ae07575-38f3-4e87-8b2b-97a4881c9c3d.jpg'),(137,'210819','서현.png','f5a46e34-2dbe-4141-8ea8-5d0ad3fb25ba.png'),(138,'210819','장지환.jpg','bba0bce7-ab5f-455e-afe7-8604b428c035.jpg'),(139,'210819','김혜진.jpg','9178785e-4dd5-482a-80f6-6028e360e476.jpg'),(140,'210819','samplevideo.mp4','d3ebf3d8-af7e-4301-9deb-34156c90b4ab.mp4'),(141,'210819','기우민.jpg','fb409a2e-6eed-4959-b888-33e268788c3d.jpg'),(142,'210819','samplepdf.pdf','0e4a6ed4-c6b7-496b-aadc-536b974791d6.pdf'),(143,'210819','지수.png','9248b7c9-33cf-4e0a-9722-0ce675b6c4d9.png'),(144,'210819','byeong.jfif','09caa14b-e2e4-401e-9b4a-da12ddc45acc.jfif'),(145,'210819','bum.jfif','982ae7bf-0ee5-4db5-97d4-3344ded908b1.jfif'),(146,'210819','채승협.jpeg','3acc7b67-50c7-45fe-a9f5-1f0759f98ddd.jpeg'),(147,'210819','조영우.jpg','5cd35ac4-ca48-4b7b-977c-0c2fa196df6e.jpg'),(148,'210819','rose.png','a0912693-7c2f-48a3-9b84-5af555e81b6a.png'),(149,'210819','samplevideo.mp4','7f9da2bb-6624-4bdf-8634-eaa5025efc71.mp4'),(150,'210819','artworks-000660272461-rmfvxq-t500x500.jpg','7711d26f-d4a5-48d8-8660-1eadd81665dd.jpg'),(151,'210819','경욱.jpg','7d521c9b-ee04-4ec3-9f3c-2b9f60b439a4.jpg'),(152,'210819','kakologo.jpg','f3c488d3-e8bc-4b72-9745-bf1042d76fab.jpg'),(153,'210819','kakologo.jpg','53ae85ac-c28c-414d-ab85-08265dbb2cb1.jpg'),(154,'210819','주호민.jpg','f62dfafd-6627-47b5-bf4a-d0c2c07172ef.jpg'),(155,'210819','이주빈.png','e1baf067-c0ce-4a83-be34-610a4cf6f98e.png'),(156,'210819','카리나.jpg','8dee77cd-152f-4c1c-abe4-e60c48846ce5.jpg'),(157,'210819','진혁.jpg','54bb03d1-4453-4666-ba2a-a48e2fcabfa2.jpg'),(158,'210819','winter.jfif','0b60d4a3-3151-4df3-bd9f-7b29ad1671f8.jfif');
/*!40000 ALTER TABLE `file_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `fol_index` int NOT NULL AUTO_INCREMENT,
  `fol_type` tinyint(1) DEFAULT NULL,
  `follower` int DEFAULT NULL,
  `following` int DEFAULT NULL,
  PRIMARY KEY (`fol_index`)
) ENGINE=InnoDB AUTO_INCREMENT=330 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (274,0,35,44),(275,0,43,44),(276,0,37,44),(281,0,53,42),(282,0,43,42),(284,0,37,42),(285,1,33,48),(286,1,46,48),(287,0,48,45),(294,0,35,45),(295,0,43,45),(296,0,52,42),(299,1,44,30),(300,0,30,42),(301,0,30,48),(302,0,43,48),(303,1,48,30),(304,0,30,50),(305,0,35,50),(306,0,48,50),(307,0,49,50),(308,0,57,50),(309,0,55,50),(310,0,52,50),(311,0,51,50),(312,0,50,50),(313,0,53,50),(314,0,54,50),(315,0,56,50),(316,0,37,48),(320,1,39,30),(323,1,45,48),(324,0,30,45),(325,0,51,45),(326,0,52,45),(328,0,39,44),(329,1,44,39);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtag` (
  `tag_index` int NOT NULL AUTO_INCREMENT,
  `tag_type` int NOT NULL,
  `tag_target` int NOT NULL,
  `taglist_index` int NOT NULL,
  PRIMARY KEY (`tag_index`),
  KEY `FK_taglist_TO_hashtag_1` (`taglist_index`),
  CONSTRAINT `FK_taglist_TO_hashtag_1` FOREIGN KEY (`taglist_index`) REFERENCES `taglist` (`taglist_index`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
INSERT INTO `hashtag` VALUES (171,0,33,1),(172,0,33,10),(173,0,33,4),(174,0,33,2),(175,0,33,12),(176,0,33,17),(177,0,44,9),(178,0,44,5),(179,0,44,2),(180,0,44,11),(181,0,46,1),(182,0,39,37),(183,0,39,38),(184,0,39,36),(185,0,39,35),(186,0,48,35),(187,0,48,36),(189,0,41,2),(190,0,41,1),(191,0,41,5),(192,0,41,11),(193,0,50,2),(194,0,50,1),(195,0,50,6),(196,0,37,11),(197,0,37,2),(198,0,37,17),(199,0,37,1),(201,0,28,11),(202,0,28,2),(203,0,28,17),(204,0,30,87),(205,0,30,85),(206,0,30,86),(207,0,40,49),(208,0,40,48),(209,0,51,86),(210,0,45,2),(211,0,45,5),(212,0,45,11),(214,0,43,71),(215,0,43,87),(216,0,38,11),(217,0,38,5),(218,0,38,4),(219,0,38,1),(220,0,38,17),(221,0,36,129),(222,0,52,17),(223,0,52,11),(224,0,29,16),(225,0,29,4),(226,0,29,10),(227,0,53,57),(228,0,48,37),(229,0,48,40);
/*!40000 ALTER TABLE `hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ind_career`
--

DROP TABLE IF EXISTS `ind_career`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ind_career` (
  `car_index` int NOT NULL AUTO_INCREMENT,
  `car_value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`car_index`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ind_career`
--

LOCK TABLES `ind_career` WRITE;
/*!40000 ALTER TABLE `ind_career` DISABLE KEYS */;
INSERT INTO `ind_career` VALUES (1,'신입 (1년 이하)'),(2,'경력 1년'),(3,'경력 2년'),(4,'경력 3년'),(5,'경력 4년'),(6,'경력 5년 이상'),(7,'경력 10년 이상'),(8,'경력 15년 이상'),(9,'경력 20년 이상');
/*!40000 ALTER TABLE `ind_career` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ind_profile`
--

DROP TABLE IF EXISTS `ind_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ind_profile` (
  `ind_index` int NOT NULL,
  `ind_resume` varchar(200) DEFAULT '-',
  `ind_video` varchar(200) DEFAULT '-',
  `ind_photo` varchar(200) DEFAULT '-',
  `ind_switch` char(1) DEFAULT 'T',
  `ind_introduce` varchar(200) DEFAULT NULL,
  `cat_index` int DEFAULT '9',
  `car_index` varchar(45) DEFAULT '1',
  PRIMARY KEY (`ind_index`),
  KEY `FK_usermaincategory_TO_cat_index_idx` (`cat_index`),
  CONSTRAINT `FK_individual_TO_ind_profile_1` FOREIGN KEY (`ind_index`) REFERENCES `individual` (`ind_index`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_main_category_TO_ind_profile` FOREIGN KEY (`cat_index`) REFERENCES `user_main_category` (`cat_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ind_profile`
--

LOCK TABLES `ind_profile` WRITE;
/*!40000 ALTER TABLE `ind_profile` DISABLE KEYS */;
INSERT INTO `ind_profile` VALUES (23,'0','0','133','F','',1,'1'),(28,'string','string','2','T','나에게도 일을 주세요',1,'1'),(29,'0','0','136','T','',5,'4'),(30,'70','68','108','T','되겠죠?',6,'5'),(32,'0','0','135','T','',1,'1'),(33,'102','104','137','T','안녕하세요\n문영화 입니다.',5,'1'),(34,'0','0','138','T','안녕하세요 장지환입니다.\n잘부탁드립니다.',1,'1'),(35,'0','0','139','T','안녕하십니까!',1,'1'),(36,'0','0','154','T','언제든 연락주십쇼!!\n기다리고있습니다.',8,'6'),(37,'0','0','143','T','지수입니다!\n\n이직 간절합니다.',5,'5'),(38,'0','0','151','T','이경욱입니다!\n\n마음껏 전화주세요!\n\n따르릉~ 따르릉~',5,'1'),(39,'128','127','129','T','안녕하세요 저는 제니 입니다.',2,'6'),(40,'0','74','155','T','이주빈입니다!\n\n잘부탁드립니다.',9,'6'),(41,'0','0','141','F','기우민입니다',5,'3'),(42,'0','0','130','F','',2,'5'),(43,'0','149','148','F','안녕하세요 저는 박채영입니다',4,'6'),(44,'0','94','111','T','',5,'1'),(45,'0','0','146','T','채승협입니다!\n\n에이스를 맡고있습니다!',5,'2'),(46,'0','112','157','T','구미 사는 남자 진혁입니다. \n\n연락주세요',5,'4'),(47,'0','0','2','F',NULL,9,'1'),(48,'0','0','147','T','저는 6팀 팀장 조영우입니다',2,'3'),(49,'0','0','132','F','안녕하세요!\n현재 경력 10년 차 개발자 입니다.\n',9,'1'),(50,'142','140','144','T','안녕하세요 이병헌 입니다',5,'9'),(51,'0','0','145','T','안녕하세요 저는 김범입니다.',6,'7'),(52,'0','0','156','T','안녕하세요 유지민입니다',5,'3'),(53,'0','0','158','T','안녕하세요 김민정입니다',3,'1');
/*!40000 ALTER TABLE `ind_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `individual`
--

DROP TABLE IF EXISTS `individual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `individual` (
  `ind_index` int NOT NULL AUTO_INCREMENT,
  `ind_id` varchar(20) DEFAULT NULL,
  `ind_password` varchar(60) DEFAULT NULL,
  `ind_name` varchar(20) DEFAULT NULL,
  `ind_birth` varchar(8) DEFAULT NULL,
  `ind_gender` char(1) DEFAULT NULL,
  `ind_phone` varchar(11) DEFAULT NULL,
  `ind_email` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ind_index`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `individual`
--

LOCK TABLES `individual` WRITE;
/*!40000 ALTER TABLE `individual` DISABLE KEYS */;
INSERT INTO `individual` VALUES (23,'whduddn123','$2a$10$da1vWkEw3ZQofNWUQWlwxOeRvkS8cxdN.Rwp2Z5JiGykvMLY4mS3u','이민선','950322','M','01070809018','a@a.com'),(28,'test1','$2a$10$okihEqeXLmL3A2QDHRSf8edAKWbV7BXd3/v3kArE0WnWwM4cdR/Fy','최정욱','950322','M','01077821114','asdf@asdf.com'),(29,'test_dong','$2a$10$gQ762Hct22lsNwzBl1KpuuvE0mHWI9UMt0gt7AYHGu7N4vz1MTOVq','장선영','000122','M','01064458778','ssafy@ssafy.com'),(30,'test0','$2a$10$cS3gVLC1vo9RGUYLN9frL..riClmNJQA6YQSqH1u3DO.ij7CCbFF2','조영','950322','M','01022674331','test0@ssafy.com'),(32,'test_cho','$2a$10$MxaeFshM12Gzkj3AxE0ZnOp1GYrHwohgd1sVYBu6.lm4lL4pQTSiq','조규현','000000','M','01062664552','test@test.com'),(33,'movie','$2a$10$PZj8jsZghX9xsfHtavVs5.YnXNdVuNW.k.ipEvtBw3kCBnwKgCSEe','문영화','960617','F','01012345678','sosm8500@gmail.com'),(34,'hwan1','$2a$10$feA42JMmG0rLrprGrTpaN.WwqrcC3YyPFv69rEqnC9gqRdn8wVBw6','장지환','071201','M','01011123452','vuqwlkefn@naver.com'),(35,'ssafy','$2a$10$xwCiT/gJbmQNEgNdmxtgROzqY0.KBVRQ9UO4GZ78t3WzPgxAWx/Om','김혜진','951221','M','01023453211','12314@safa.com'),(36,'test2','$2a$10$49exhqeYfiZYApbedCB0u.vOersWRMrYbe6gitkEswQBMkhIyg9KW','조일우','950322','M','01043378812','choyeongwoo.s@gmail.com'),(37,'setest','$2a$10$0ne50KdR8yd9Ro/QrDzp3elwObt0olsGyqdJwf2N0WTQMNY0YXJNq','박지수','950917','M','01028723432','se@se.com'),(38,'qwe123','$2a$10$oca2Er9oJwK5eMn2xEnEP.pRe/3.n0G.KoDpYgyFFPsb8DjpBW7Qm','이경욱','121212','F','01098392722','dlruddnr@naver.com'),(39,'jennie','$2a$10$cvMnW1KLOeENwcSPzKETru7rdw6LuesNk6JQXfZTmYMWlhkH6m0qK','김제니','960116','F','01020181112','jennie@yg.com'),(40,'jubin','$2a$10$gWwZkfOA468/7e72jR5TueqvrNVzeKvumWxTI06HgjKsKtwqxKmU2','이주빈','890918','F','01020170327','hellobeen@swmp.com'),(41,'testuser','$2a$10$SIky6aT5ABFFz50TzglUtOsXErcfUI80gUIZJrcq1PkwGU5FIfwPq','기우민','000000','M','01064768879','ssafy@ssafy.com'),(42,'testyw','$2a$10$qNXL1hfdt8fWf2fP2NNchObVm2Ln4cHYPeW9tkFKhFlY4mWqZAs4O','김민수','950322','M','01045068040','yw@yw.com'),(43,'rose-','$2a$10$xAkrxj..lxATTEg4S4fkL.gRfELRkgEN13Ix6ukx5KISDT7gr68gO','박채영','970211','F','01020210312','rose@yg.com'),(44,'hwan2328','$2a$10$QKQ6J6sRcSm1SgCDwH8kfu89JUtWLsrSv0Wf50qdpSkB.leIl.6vS','허창환','950616','M','01020484423','hwan1205@gmail.com'),(45,'tgs02159','$2a$10$9sdOXY5ZFFrd56sMxgr7Q.ESzkV9dCmfpm68I92b6Ko1Ak9zJqyma','채승협','950917','M','01072527524','tgs02159@ssafy.com'),(46,'gumi03','$2a$10$MgDfjGA0VLBnepR84AfTguZggVacVpUeG6U/o1GO6vnZijgAAlwwK','현진혁','951212','M','01012345678','ssafy@ssafy.com'),(47,'sbsk1997','$2a$10$TFwpHKzg7Cc6OBdR0GX89eqKtTWiYOb7xdntCfVtQRn79r56lRkC6','홍길동','971117','F','01011111111','email@email.com'),(48,'cho95','$2a$10$uccMpZirwn5HgiHmhzJ3MeGVSk3g3WUPHX/RLJ9I2E7ZaM.OoiPxS','조영우','950322','M','01001010101','asdf@asdf.com'),(49,'aassdd','$2a$10$A9SH1CqWovKLaWZYph/33eR5dmMRHZggljz3gpDtydIBgTNPis2ze','김아무개','960720','M','01090909090','aassdd@ssafy.com'),(50,'byeong','$2a$10$L/RRfJAg8sjPf/UOgCB0DOE6lL.n6yM0wwCBLrzHH1VTDhHlJPoc.','이병헌','700712','M','01019910014','byeong@bh.com'),(51,'bummm','$2a$10$pqqnPoASNETWcSyriDRSv.nngqcH7DEutNFRkyDbVVQCerrONxaAC','김범','890707','M','01020060507','bum@starship.com'),(52,'karina','$2a$10$tpFRV/dYUUdfT3CKio.zeOCWc1Q1y4qFjfoTP7y4l.J2pmvwSwhV2','유지민','000411','F','01020201117','karina@sm.com'),(53,'winter','$2a$10$OSCRF1tZYOvgc8sis8GfaOeEyS5Oa1mN0WclnKteaBnURHDKCsNvy','김민정','010101','F','01020201114','winter@sm.coim');
/*!40000 ALTER TABLE `individual` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`peopool`@`%`*/ /*!50003 TRIGGER `individual_AFTER_INSERT` AFTER INSERT ON `individual` FOR EACH ROW BEGIN
 insert into ind_profile(ind_index,ind_resume,ind_video,ind_photo) values (new.ind_index,0,0,2);
 insert into user_session(ind_index) values (new.ind_index);
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
  `int_index` int NOT NULL AUTO_INCREMENT,
  `ind_index` int DEFAULT NULL,
  `ent_index` int DEFAULT NULL,
  `int_start` datetime DEFAULT NULL,
  `int_end` datetime DEFAULT NULL,
  `int_roomnumber` varchar(100) DEFAULT NULL,
  `int_done` char(1) DEFAULT 'W',
  `int_show` char(1) DEFAULT 'W',
  `int_duty` varchar(100) NOT NULL,
  PRIMARY KEY (`int_index`),
  KEY `FK_individual_TO_interview_1` (`ind_index`),
  KEY `FK_enterprise_TO_interview_1` (`ent_index`),
  KEY `FK_suggestion_TO_interview_idx` (`int_duty`),
  CONSTRAINT `FK_enterprise_TO_interview_1` FOREIGN KEY (`ent_index`) REFERENCES `enterprise` (`ent_index`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_individual_TO_interview_1` FOREIGN KEY (`ind_index`) REFERENCES `individual` (`ind_index`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview`
--

LOCK TABLES `interview` WRITE;
/*!40000 ALTER TABLE `interview` DISABLE KEYS */;
INSERT INTO `interview` VALUES (38,39,45,'2021-08-17 17:30:00',NULL,'21081717304539','W','W','마케팅'),(39,40,45,'2021-08-18 00:00:00',NULL,'21081800004540','W','W','사무직'),(40,44,43,'2021-09-01 16:00:00',NULL,'21090116004344','W','W','개발'),(41,42,30,'2021-08-20 09:00:00','2021-08-19 22:11:09','21082009003042','F','T','개발'),(42,33,48,'2021-08-20 13:00:00',NULL,'21082013004833','W','W','개발'),(43,39,30,'2021-08-20 00:00:00','2021-08-19 22:47:24','21082000003039','P','T','댄서'),(44,32,30,'2021-08-24 00:00:00','2021-08-19 23:59:45','21082400003032','P','T','프론트엔드'),(45,29,30,'2021-08-26 12:40:51','2021-08-20 00:23:33','21082612403029','F','T','프론트엔드'),(46,42,30,'2021-08-21 00:00:00','2021-08-20 05:31:09','21082100003042','P','T','test'),(47,48,30,'2021-08-21 00:00:00','2021-08-20 03:39:18','21082100003048','P','T','test'),(48,44,30,'2021-08-26 12:00:00',NULL,'21082612003044','W','W','데이터베이스'),(49,44,30,'2021-08-25 00:00:00','2021-08-20 05:07:43','21082500003044','P','T','개발자');
/*!40000 ALTER TABLE `interview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suggestion`
--

DROP TABLE IF EXISTS `suggestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suggestion` (
  `sug_index` int NOT NULL AUTO_INCREMENT,
  `sug_send` datetime DEFAULT NULL,
  `sug_timeone` datetime DEFAULT NULL,
  `sug_timetwo` datetime DEFAULT NULL,
  `sug_timethree` datetime DEFAULT NULL,
  `ind_index` int DEFAULT NULL,
  `ent_index` int DEFAULT NULL,
  `sug_decision` datetime DEFAULT NULL,
  `sug_state` char(1) DEFAULT 'W',
  `sug_duty` varchar(100) NOT NULL,
  `sug_message` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`sug_index`),
  KEY `FK_individual_TO_suggestion_1` (`ind_index`),
  KEY `FK_enterprise_TO_suggestion_1` (`ent_index`),
  CONSTRAINT `FK_enterprise_TO_suggestion_1` FOREIGN KEY (`ent_index`) REFERENCES `enterprise` (`ent_index`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_individual_TO_suggestion_1` FOREIGN KEY (`ind_index`) REFERENCES `individual` (`ind_index`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestion`
--

LOCK TABLES `suggestion` WRITE;
/*!40000 ALTER TABLE `suggestion` DISABLE KEYS */;
INSERT INTO `suggestion` VALUES (47,'2021-08-17 23:47:37','2021-08-17 17:30:00','2021-08-20 19:00:00','2021-08-21 09:10:00',39,45,'2021-08-17 17:30:00','T','마케팅','YG 엔터테인먼트입니다. 마케팅 경력직 채용합니다'),(49,'2021-08-17 23:57:02','2021-08-19 00:00:00','2021-08-18 00:00:00','2021-08-20 00:00:00',40,45,'2021-08-18 00:00:00','T','사무직','사무직 채용합니다.'),(64,'2021-08-18 10:37:23','2021-08-25 11:09:00','2021-08-25 19:00:00','2021-08-26 12:40:51',29,30,'2021-08-26 12:40:51','T','프론트엔드','신입 개발자를 모집하고 있습니다. '),(65,'2021-08-18 13:46:04','2021-08-27 00:00:00','2021-08-24 00:00:00','2021-08-22 00:00:00',32,30,'2021-08-24 00:00:00','T','프론트엔드','프론트엔드 개발자 모집중입니다.'),(70,'2021-08-18 17:32:30','2021-08-03 00:00:00','2021-08-04 00:00:00','2021-08-05 00:00:00',33,43,NULL,'F','개발','저희와 함께 해주세요'),(71,'2021-08-18 21:18:31','2021-08-20 09:00:00','2021-08-20 10:00:00','2021-08-20 11:00:00',33,43,NULL,'W','개발','저희와 함께 해주세요.'),(74,'2021-08-18 21:35:24','2021-09-01 16:00:00','2021-09-01 15:00:00','2021-09-01 20:00:00',44,43,'2021-09-01 16:00:00','T','개발','함께하고싶습니다.'),(75,'2021-08-18 21:46:31','2021-08-27 00:00:00','2021-08-27 01:00:00','2021-08-27 02:00:00',44,43,NULL,'F','데이터베이스 개발','함께 하고 싶습니다.'),(78,'2021-08-19 19:09:56','2021-08-20 13:00:00','2021-08-20 14:00:00','2021-08-20 15:00:00',33,48,'2021-08-20 13:00:00','T','개발','간단한 면접 가능하실까요?'),(79,'2021-08-19 19:28:15','2021-08-21 10:00:00','2021-08-21 11:00:00','2021-08-21 12:00:00',33,30,NULL,'W','개발','간단한 면접 가능 할까요?'),(80,'2021-08-19 19:29:16','2021-08-23 11:00:00','2021-08-24 11:00:00','2021-08-25 11:00:00',36,30,NULL,'W','개발','간단한 면접 가능할까요?'),(81,'2021-08-19 21:30:35','2021-08-20 09:00:00','2021-08-20 15:00:00','2021-08-20 18:00:00',42,30,'2021-08-20 09:00:00','T','개발','조영우님 카카오로 모시겠습니다'),(82,'2021-08-19 22:45:29','2021-08-20 00:00:00','2021-08-21 00:00:00','2021-08-22 00:00:00',39,30,'2021-08-20 00:00:00','T','댄서','얼른오세용'),(83,'2021-08-20 02:36:44','2021-08-21 00:00:00','2021-08-22 00:00:00','2021-08-23 00:00:00',36,30,NULL,'W','test','test'),(84,'2021-08-20 02:45:32','2021-08-21 00:00:00','2021-08-22 00:00:00','2021-08-23 00:00:00',48,30,'2021-08-21 00:00:00','T','test','test'),(85,'2021-08-20 02:46:40','2021-08-21 00:00:00','2021-08-19 00:00:00','2021-08-20 02:46:39',42,30,'2021-08-21 00:00:00','T','test','sadf'),(86,'2021-08-20 04:45:02','2021-08-20 14:00:00','2021-08-20 15:00:00','2021-08-20 16:00:00',45,48,NULL,'W','개발','면접 요청 합니다.'),(87,'2021-08-20 05:01:43','2021-08-26 11:00:00','2021-08-26 12:00:00','2021-08-26 13:00:00',44,30,'2021-08-26 12:00:00','T','데이터베이스','데이터베이스 개발자를 모집하고 있습니다.'),(88,'2021-08-20 05:07:18','2021-08-21 00:00:00','2021-08-23 00:00:00','2021-08-25 00:00:00',44,30,'2021-08-25 00:00:00','T','개발자','개발자직군 모집중입니다');
/*!40000 ALTER TABLE `suggestion` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`peopool`@`%`*/ /*!50003 TRIGGER `suggestion_AFTER_UPDATE` AFTER UPDATE ON `suggestion` FOR EACH ROW BEGIN
	if(old.sug_state != new.sug_state and new.sug_state = 'T')
    then
    insert into interview(ind_index,ent_index,int_start,int_roomnumber,int_duty) values 
    (new.ind_index,new.ent_index,new.sug_decision,concat(date_format(new.sug_decision,"%y%m%d%H%i"),new.ent_index,new.ind_index),new.sug_duty);
    end if;
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
  `taglist_index` int NOT NULL AUTO_INCREMENT,
  `taglist_name` varchar(20) DEFAULT NULL,
  `cat_index` int DEFAULT NULL,
  PRIMARY KEY (`taglist_index`),
  KEY `taglsit_cat_index_idx` (`cat_index`),
  CONSTRAINT `taglsit_cat_index` FOREIGN KEY (`cat_index`) REFERENCES `user_main_category` (`cat_index`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taglist`
--

LOCK TABLES `taglist` WRITE;
/*!40000 ALTER TABLE `taglist` DISABLE KEYS */;
INSERT INTO `taglist` VALUES (1,'Java',5),(2,'Spring',5),(3,'Python',5),(4,'Vue',5),(5,'데이터베이스',5),(6,'Oracle',5),(7,'C, C++',5),(8,'게임 엔진',5),(9,'C#',5),(10,'Javascript',5),(11,'Node.js',5),(12,'DevOps',5),(13,'머신러닝',5),(14,'안드로이드',5),(15,'ios',5),(16,'프론트엔드',5),(17,'백엔드',5),(18,'Server',5),(19,'보안',5),(20,'네트워크',5),(21,'React',5),(22,'서비스 기획',9),(23,'사업 기획',9),(24,'조직관리',9),(25,'회계',9),(26,'비서',9),(27,'전시 기획',9),(28,'사무 관리',9),(29,'지역 관리',9),(30,'자금담당',9),(31,'해외 사업 개발',9),(32,'데이터 분석',9),(33,'리스크 관리',9),(35,'디지털 마케팅',2),(36,'콘텐츠 마케팅',2),(37,'퍼포먼스 마케터',2),(38,'브랜드 마케팅',2),(39,'그로스 마케팅',2),(40,'제휴',2),(41,'카피라이터',2),(42,'마켓 리서치',2),(43,'글로벌 마케팅',2),(44,'소셜 마케팅',2),(45,'광고 기획자',2),(46,'마케팅 전략 기획',2),(47,'스포츠 마케팅',2),(48,'UX 디자이너',3),(49,'UI 디자이너',3),(50,'웹 디자이너',3),(51,'그래픽 디자이너',3),(52,'모바일 디자이너',3),(53,'광고 디자이너',3),(54,'패키지 디자이너',3),(55,'영상 디자이너',3),(56,'모션 디자이너',3),(57,'공간 디자이너',3),(58,'캐릭터 디자이너',3),(59,'전시 디자이너',3),(60,'조경 디자이너',3),(61,'인테리어 디자이너',3),(62,'가구 디자이너',3),(63,'패션 디자이너',3),(64,'아트 디렉터',3),(65,'출판, 편집 디자이너',3),(66,'3D 디자이너',3),(67,'운송 관리',4),(68,'물류관리',4),(69,'입출고 관리',4),(70,'유통 관리',4),(71,'수출입사무',4),(72,'배송담당',4),(73,'항공 운송',4),(74,'화물트럭 운전기사',4),(75,'보세사',4),(76,'관세사',4),(77,'바이어 관리',4),(78,'지게차 운전',4),(79,'원산지관리사',4),(80,'웹 퍼블리셔',5),(81,'임베디드',5),(82,'QA, 테스트 엔지니어',5),(83,'VR 엔지니어',5),(84,'기업영업',6),(85,'외부영업',6),(86,'기술영업',6),(87,'해외영업',6),(88,'의료기기영업',6),(89,'제약영업',6),(90,'내부영업',6),(91,'인사담당',7),(92,'채용담당',7),(93,'헤드헌터',7),(94,'급여담당',7),(95,'노무 노사',7),(96,'사내 감사',7),(97,'기술 교육',7),(98,'법무담당',8),(99,'변호사',8),(100,'특허담당',8),(101,'변리사',8),(102,'법무사',8),(103,'수사관',8),(104,'자격증, 기술전문 교육',10),(105,'교직원',10),(106,'조등학교 교사',10),(107,'중, 고등학교 교사',10),(108,'유치원(보육원) 교사',10),(109,'스포츠 강사',10),(110,'학원강사',10),(111,'전문강사',10),(112,'외국어강사',10),(113,'교육기획',10),(114,'회계',11),(115,'투자, 증권',11),(116,'재무 분석',11),(117,'자산운용',11),(118,'자산관리',11),(119,'트레이더',11),(120,'부동산 자산 관리',11),(121,'감정평가사',11),(122,'세무사',11),(123,'금융공학자',11),(124,'애널리스트',11),(125,'손해사정관',11),(126,'투자은행가',11),(127,'계리사',11),(128,'보험 에이전트',11),(129,'준법감시인',11),(130,'공인회계사',11),(131,'IR',11);
/*!40000 ALTER TABLE `taglist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_main_category`
--

DROP TABLE IF EXISTS `user_main_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_main_category` (
  `cat_index` int NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(45) NOT NULL,
  PRIMARY KEY (`cat_index`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_main_category`
--

LOCK TABLES `user_main_category` WRITE;
/*!40000 ALTER TABLE `user_main_category` DISABLE KEYS */;
INSERT INTO `user_main_category` VALUES (1,'선택하지 않음'),(2,'마케팅'),(3,'디자인'),(4,'물류, 무역'),(5,'개발'),(6,'영업'),(7,'인사'),(8,'법률, 법무'),(9,'경영, 사무'),(10,'교육'),(11,'금융');
/*!40000 ALTER TABLE `user_main_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_session`
--

DROP TABLE IF EXISTS `user_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_session` (
  `ind_index` int NOT NULL,
  `sessionid` varchar(200) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`ind_index`),
  CONSTRAINT `FK_individual_TO_user_session` FOREIGN KEY (`ind_index`) REFERENCES `individual` (`ind_index`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_session`
--

LOCK TABLES `user_session` WRITE;
/*!40000 ALTER TABLE `user_session` DISABLE KEYS */;
INSERT INTO `user_session` VALUES (23,NULL),(28,NULL),(29,NULL),(30,'571174cf-611d-4b2b-37d0-811584c656d6'),(32,NULL),(33,NULL),(34,NULL),(35,'e5654c9d-4698-84c6-3071-36395434cff7'),(36,NULL),(37,NULL),(38,NULL),(39,'3f98b4e8-7d18-a622-2349-cadc213e93ab'),(40,NULL),(41,NULL),(42,NULL),(43,NULL),(44,NULL),(45,NULL),(46,NULL),(47,NULL),(48,NULL),(49,NULL),(50,NULL),(51,NULL),(52,NULL),(53,NULL);
/*!40000 ALTER TABLE `user_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'peopool'
--

--
-- Dumping routines for database 'peopool'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-20  5:44:02
