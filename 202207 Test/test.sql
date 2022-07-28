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
  `date` varchar(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `introduction` varchar(200) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `organization`
--

INSERT INTO `organization` (`organizationId`, `date`, `name`, `introduction`, `phone`, `email`, `address`) VALUES
(1, '2020-07-12', 'A1', '畫圖', '0100', 'a111@gmail.com', '新北市'),
(6, '2008-02-14', 'B2', '學習', '0233', 'b2@gmail.com', '台南');

-- --------------------------------------------------------

--
-- 資料表結構 `organization_product`
--

CREATE TABLE `organization_product` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `introduction` varchar(200) NOT NULL,
  `sort` varchar(200) NOT NULL,
  `price` int(11) NOT NULL,
  `organizationId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- 傾印資料表的資料 `organization_product`
--

INSERT INTO `organization_product` (`id`, `name`, `introduction`, `sort`, `price`, `organizationId`) VALUES
(1, 'iPhone3', '蘋果第三代', '1', 100, 1),
(2, 'iPhone4', '蘋果第四代', '2', 200, 2),
(3, 'iphone5', '蘋果第五代', '3', 500, 2);

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
  ADD PRIMARY KEY (`id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `organization`
--
ALTER TABLE `organization`
  MODIFY `organizationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `organization_product`
--
ALTER TABLE `organization_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
