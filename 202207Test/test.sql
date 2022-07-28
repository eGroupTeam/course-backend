-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- 主機： localhost
-- 產生時間： 
-- 伺服器版本： 8.0.17
-- PHP 版本： 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `test`
--

-- --------------------------------------------------------

--
-- 資料表結構 `organization`
--

CREATE TABLE `organization` (
  `organizationId` int(11) NOT NULL,
  `createDate` date NOT NULL,
  `organizationName` varchar(8) NOT NULL,
  `organizationIntro` varchar(100) NOT NULL,
  `organizationTel` varchar(10) NOT NULL,
  `organizationMail` varchar(30) NOT NULL,
  `organizationAddr` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `organization`
--

INSERT INTO `organization` (`organizationId`, `createDate`, `organizationName`, `organizationIntro`, `organizationTel`, `organizationMail`, `organizationAddr`) VALUES
(1, '2022-07-28', '萊爾富超商', '萊爾富辛亥店', '0234567899', 'family@gmail.com', '新北市新莊區中正路'),
(2, '2022-07-06', '統一超商', '統一企業的零售店，辛亥門市', '0229973456', 'seven@gmail.com', '新北市三重區中華路'),
(4, '2022-07-28', '全家超商TEST', '全家超商辛亥店', '0229981234', 'family@gmail.com', '新北市新莊區中正路'),
(6, '2022-07-08', 'TEST', 'TESTTEST', 'TESTTESTTE', 'TESTTEST', '新北市新莊區中正路'),
(13, '2022-07-28', '全家超商', '全家超商辛亥店', '0229981234', 'family@gmail.com', '新北市新莊區中正路'),
(14, '2022-07-28', '全家超商', '全家超商辛亥店', '0229981234', 'family@gmail.com', '新北市新莊區中正路'),
(15, '2022-07-28', '全家超商', '全家超商辛亥店', '0229981234', 'family@gmail.com', '新北市新莊區中正路'),
(16, '2022-07-28', '全家超商', '全家超商辛亥店', '0229981234', 'family@gmail.com', '新北市新莊區中正路'),
(17, '2022-07-28', '全家超商', '全家超商辛亥店', '0229981234', 'family@gmail.com', '新北市新莊區中正路'),
(18, '2022-07-28', '全家超商', '全家超商辛亥店', '0229981234', 'family@gmail.com', '新北市新莊區中正路'),
(19, '2022-07-28', '全家超商', '全家超商辛亥店', '0229981234', 'family@gmail.com', '新北市新莊區中正路'),
(20, '2022-07-28', '全家超商', '全家超商辛亥店', '0229981234', 'family@gmail.com', '新北市新莊區中正路');

-- --------------------------------------------------------

--
-- 資料表結構 `organization_product`
--

CREATE TABLE `organization_product` (
  `productId` int(11) NOT NULL,
  `productName` varchar(10) NOT NULL,
  `productDesc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `productSort` int(11) NOT NULL,
  `productPrice` int(11) NOT NULL,
  `organizationId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `organization_product`
--

INSERT INTO `organization_product` (`productId`, `productName`, `productDesc`, `productSort`, `productPrice`, `organizationId`) VALUES
(2, '萊爾富水餃', '俗又大碗', 2, 60, 1),
(3, '修改測試', '讚讚讚讚讚讚讚讚讚讚讚讚讚讚', 7, 7777, 1),
(4, 'Icash悠遊卡', '讚讚讚讚讚讚讚讚讚讚讚讚讚讚', 4, 999, 2),
(7, 'Test', 'TESTTEST', 5, 1, 1),
(17, 'Icash悠遊卡', '讚讚讚讚讚讚讚讚讚讚讚讚讚讚', 4, 100, 1),
(18, 'Icash悠遊卡', '讚讚讚讚讚讚讚讚讚讚讚讚讚讚', 4, 100, 1),
(19, 'Icash悠遊卡', '讚讚讚讚讚讚讚讚讚讚讚讚讚讚', 4, 100, 1),
(20, 'Icash悠遊卡', '讚讚讚讚讚讚讚讚讚讚讚讚讚讚', 4, 100, 1),
(21, 'Icash悠遊卡', '讚讚讚讚讚讚讚讚讚讚讚讚讚讚', 4, 100, 1),
(22, 'Icash悠遊卡', '讚讚讚讚讚讚讚讚讚讚讚讚讚讚', 4, 100, 1),
(23, 'Icash悠遊卡', '讚讚讚讚讚讚讚讚讚讚讚讚讚讚', 4, 100, 1);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `organization`
--
ALTER TABLE `organization`
  ADD PRIMARY KEY (`organizationId`);

--
-- 資料表索引 `organization_product`
--
ALTER TABLE `organization_product`
  ADD PRIMARY KEY (`productId`),
  ADD KEY `organizationId` (`organizationId`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `organization`
--
ALTER TABLE `organization`
  MODIFY `organizationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `organization_product`
--
ALTER TABLE `organization_product`
  MODIFY `productId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `organization_product`
--
ALTER TABLE `organization_product`
  ADD CONSTRAINT `organization_product_ibfk_1` FOREIGN KEY (`organizationId`) REFERENCES `organization` (`organizationId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
