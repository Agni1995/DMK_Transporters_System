-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.20 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for dmk
CREATE DATABASE IF NOT EXISTS `dmk` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dmk`;

-- Dumping structure for table dmk.branch
CREATE TABLE IF NOT EXISTS `branch` (
  `branchId` varchar(50) NOT NULL,
  `branchName` varchar(30) NOT NULL,
  `address` varchar(80) NOT NULL,
  `contact` varchar(10) NOT NULL,
  PRIMARY KEY (`branchId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dmk.branch: ~1 rows (approximately)
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` (`branchId`, `branchName`, `address`, `contact`) VALUES
	('B001', 'Wattala', 'sdfgh', '1234567890');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;

-- Dumping structure for table dmk.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customerId` varchar(50) NOT NULL,
  `cName` varchar(30) NOT NULL,
  `cAddress` varchar(30) DEFAULT NULL,
  `cItem` varchar(50) NOT NULL,
  `bid` varchar(50) NOT NULL,
  PRIMARY KEY (`customerId`),
  KEY `bid` (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dmk.customer: ~2 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customerId`, `cName`, `cAddress`, `cItem`, `bid`) VALUES
	('C001', 'Cargils', 'ghhhj', 'ghjj', 'B001'),
	('C002', 'MAS', 'dd', 'dd', 'B001');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table dmk.driver
CREATE TABLE IF NOT EXISTS `driver` (
  `licenceNo` varchar(15) NOT NULL,
  `empid` varchar(50) NOT NULL,
  PRIMARY KEY (`licenceNo`,`empid`),
  KEY `empid` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dmk.driver: ~2 rows (approximately)
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` (`licenceNo`, `empid`) VALUES
	('123456', 'DR001'),
	('127384', 'DR002');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;

-- Dumping structure for table dmk.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `empid` varchar(50) NOT NULL,
  `name` varchar(30) NOT NULL,
  `nic` varchar(10) NOT NULL,
  `contact` varchar(10) NOT NULL,
  `salary` double NOT NULL,
  PRIMARY KEY (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dmk.employee: ~2 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`empid`, `name`, `nic`, `contact`, `salary`) VALUES
	('DR001', 'Nimal', '966534762v', '0771234567', 2000),
	('DR002', 'Kamal', '', '', 3000);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping structure for table dmk.login
CREATE TABLE IF NOT EXISTS `login` (
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`username`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dmk.login: ~2 rows (approximately)
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`username`, `password`) VALUES
	('user1', 'user1'),
	('user2', 'user2');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;

-- Dumping structure for table dmk.manager
CREATE TABLE IF NOT EXISTS `manager` (
  `empid` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  PRIMARY KEY (`empid`,`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dmk.manager: ~0 rows (approximately)
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;

-- Dumping structure for table dmk.route
CREATE TABLE IF NOT EXISTS `route` (
  `rId` varchar(50) NOT NULL,
  `cid` varchar(50) NOT NULL,
  `ratePerTurn` int(11) NOT NULL,
  `paid` tinyint(1) NOT NULL,
  PRIMARY KEY (`rId`,`cid`),
  KEY `cid` (`cid`),
  KEY `rId` (`rId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dmk.route: ~4 rows (approximately)
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` (`rId`, `cid`, `ratePerTurn`, `paid`) VALUES
	('R001', 'C001', 12, 0),
	('R001', 'C002', 30, 0),
	('R002', 'C001', 25, 0),
	('R003', 'C002', 78, 0);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;

-- Dumping structure for table dmk.routedetails
CREATE TABLE IF NOT EXISTS `routedetails` (
  `rid` varchar(50) NOT NULL,
  `source` varchar(30) NOT NULL,
  `destination` varchar(30) NOT NULL,
  `distance` double NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dmk.routedetails: ~3 rows (approximately)
/*!40000 ALTER TABLE `routedetails` DISABLE KEYS */;
INSERT INTO `routedetails` (`rid`, `source`, `destination`, `distance`) VALUES
	('R001', '', '', 0),
	('R002', '', '', 0),
	('R003', '', '', 0);
/*!40000 ALTER TABLE `routedetails` ENABLE KEYS */;

-- Dumping structure for table dmk.salary
CREATE TABLE IF NOT EXISTS `salary` (
  `empid` varchar(50) NOT NULL,
  `month` varchar(50) NOT NULL,
  `salary` double NOT NULL,
  `paid` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`empid`,`month`),
  KEY `empid` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dmk.salary: ~5 rows (approximately)
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` (`empid`, `month`, `salary`, `paid`) VALUES
	('', 'January', 0, NULL),
	('DR001', 'January', 2120, NULL),
	('DR001', 'July', 2000, NULL),
	('DR001', 'May', 2000, NULL),
	('DR002', 'January', 3000, NULL);
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;

-- Dumping structure for table dmk.vehicle
CREATE TABLE IF NOT EXISTS `vehicle` (
  `vNo` int(11) NOT NULL,
  `vType` varchar(30) NOT NULL,
  `vInsuranceNo` varchar(30) NOT NULL,
  `InsExpDate` date NOT NULL,
  PRIMARY KEY (`vNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dmk.vehicle: ~3 rows (approximately)
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` (`vNo`, `vType`, `vInsuranceNo`, `InsExpDate`) VALUES
	(1, 'car', 'I0001', '2022-01-16'),
	(4, 'Lorry', 'I004', '2022-01-27'),
	(9, 'Car', 'i009', '2022-09-09');
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
