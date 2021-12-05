-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2020 at 12:05 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `app_order`
--

-- --------------------------------------------------------

--
-- Table structure for table `bun_cdq`
--

CREATE TABLE `bun_cdq` (
  `id` int(11) NOT NULL,
  `hinhmon` int(11) NOT NULL,
  `tenmon` varchar(255) NOT NULL,
  `mota` varchar(255) NOT NULL,
  `giatien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bun_cdq`
--

INSERT INTO `bun_cdq` (`id`, `hinhmon`, `tenmon`, `mota`, `giatien`) VALUES
(1, 2131165346, 'Bún bò tái bắp', 'Chả cua,bắp bò tái.', 30000),
(2, 2131165347, '\r\nBún bò bắp gân', 'Chả cua, bắp bò chín, gân', 30000),
(3, 2131165348, 'Bún bò cung đình', 'Chả cua,bắp bò chín,gân,giò heo,đuôi bò.', 45000),
(4, 2131165346, '\r\nBún bò giò heo', 'Chả cua, bắp bò chín, gân, giò heo.', 35000),
(5, 2131165346, 'Tô em bé', 'Bò tái bằm', 20000),
(8, 2131165347, 'bún nước lọc', 'chỉ có nước', 20000);

-- --------------------------------------------------------

--
-- Table structure for table `com_tam_plt`
--

CREATE TABLE `com_tam_plt` (
  `id` int(11) NOT NULL,
  `hinhmon` int(11) NOT NULL,
  `tenmon` varchar(255) NOT NULL,
  `mota` varchar(255) NOT NULL,
  `giatien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `com_tam_plt`
--

INSERT INTO `com_tam_plt` (`id`, `hinhmon`, `tenmon`, `mota`, `giatien`) VALUES
(1, 2131165342, '\r\nCombo Lộc', 'Giá gốc: 65.000 Bao gồm: Cơm Sườn + Chả hấp + Canh rong biển + Coca tươi + Khăn lạnh', 55000),
(2, 2131165343, 'Combo Phúc', 'Giá gốc: 68.000 Bao gồm: Cơm Ba Rọi + Trứng Ốp la + Canh Rong Biển + Coca tươi + Khăn lạnh', 55000),
(3, 2131165344, 'Cơm tấm sườn non', '', 55000),
(4, 2131165345, 'Cơm tấm đùi gà nướng ngũ vị', '', 35000),
(5, 2131165345, 'Cơm tấm sườn', '', 29000),
(6, 2131165345, 'Cơm tấm ba rọi', '', 29000),
(7, 2131165345, 'Cơm thêm', '', 4000),
(8, 2131165345, 'Đùi gà quay mắm', '', 35000),
(10, 2131165345, 'hshs', 'shsjjs', 60000),
(11, 2131165345, 'hsgis', 'jsjs', 70000),
(12, 2131165345, 'đjjj637', 'jdkdk', 40000),
(13, 2131165345, 'uius', 'hssj', 30000);

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `id` int(11) NOT NULL,
  `hinhmon` int(11) NOT NULL,
  `tenmon` varchar(255) NOT NULL,
  `giatien` int(11) NOT NULL,
  `soluong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`id`, `hinhmon`, `tenmon`, `giatien`, `soluong`) VALUES
(29, 2131165343, 'Combo Phúc', 55000, 0),
(30, 2131165344, 'Cơm tấm sườn non', 55000, 0),
(32, 2131165345, 'uius', 30000, 0),
(33, 2131165344, 'Cơm tấm sườn non', 55000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `quan_an`
--

CREATE TABLE `quan_an` (
  `id` int(11) NOT NULL,
  `hinhquan` int(11) NOT NULL,
  `tenquan` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `khoangcach` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `quan_an`
--

INSERT INTO `quan_an` (`id`, `hinhquan`, `tenquan`, `diachi`, `khoangcach`) VALUES
(1, 2131165283, 'Cơm Tấm Phúc Lộc Thọ', '31-33 Lê Văn Việt, P, Thủ Đức, Hồ Chí Minh', '2.1'),
(2, 2131165293, 'Quán Cơm Kim Yến', '63 Song Hành, Thảo Điền, Quận 2, Hồ Chí Minh', '1'),
(3, 2131165280, 'Cung Đình Quán', '236 Lê Thánh Tôn, Phường Bến Thành, Quận 1, Hồ Chí Minh', '10'),
(4, 2131165281, 'Bún riêu Gánh', '4 Đường Phan Bội Châu, Phường Bến Thành, Quận 1, Hồ Chí Minh', '20'),
(5, 2131165338, 'Bánh Huế Thu Thảo', '36/2A Hoàng Xuân Nhị, P. Phú Trung, Tân Phú, TP. HCM', '5'),
(6, 2131165292, 'Funfarm', '25/1 Giải Phóng, P. 4, Tân Bình, TP. HCM', '6.9'),
(7, 2131165341, 'Viva Star Coffee', '45 Hoàng Hoa Thám, P. 13, Tân Bình, TP. HCM', '4.5'),
(8, 2131165317, 'Lava Coffee', '61 Quang Trung, P. 10, Gò Vấp, TP. HCM', '3.7'),
(9, 2131165282, 'Chill Tea - Thống Nhất', '63 Thống Nhất, P. Bình Thọ, Thủ Đức, TP. HCM', '3'),
(26, 2131165317, 'hêi', 'su', '24');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bun_cdq`
--
ALTER TABLE `bun_cdq`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `com_tam_plt`
--
ALTER TABLE `com_tam_plt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quan_an`
--
ALTER TABLE `quan_an`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bun_cdq`
--
ALTER TABLE `bun_cdq`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `com_tam_plt`
--
ALTER TABLE `com_tam_plt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `order_item`
--
ALTER TABLE `order_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `quan_an`
--
ALTER TABLE `quan_an`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
