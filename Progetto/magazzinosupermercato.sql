-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 31, 2019 at 12:47 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `magazzinosupermercato`
--

-- --------------------------------------------------------

--
-- Table structure for table `merce`
--

CREATE TABLE `merce` (
  `ID_Merce` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Tipologia` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Prezzo` double NOT NULL,
  `Descrizione` varchar(50) NOT NULL,
  `Quantita` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `merce`
--

INSERT INTO `merce` (`ID_Merce`, `Tipologia`, `Prezzo`, `Descrizione`, `Quantita`) VALUES
('001', '002', 2.99, 'Lavavetri', 23),
('002', '004', 1.99, 'Sapone al limone', 76),
('003', '001', 9.99, 'Pasta DeCecco 500g', 21),
('004', '006', 8.5, 'Cereali Kellogg''s', 53),
('005', '005', 4.5, 'Lenzuola', 11),
('006', '005', 8.99, 'Torta', 47);

-- --------------------------------------------------------

--
-- Table structure for table `operazione`
--

CREATE TABLE `operazione` (
  `ID_Merce` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ruolo`
--

CREATE TABLE `ruolo` (
  `ID_Ruolo` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Desc_Ruolo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ruolo`
--

INSERT INTO `ruolo` (`ID_Ruolo`, `Desc_Ruolo`) VALUES
('001', 'Programmatore SQL'),
('002', 'Impiegato'),
('003', 'Meccanico'),
('004', 'Falegname'),
('005', 'Sistemista'),
('006', 'Commercialista'),
('007', 'Dentista'),
('008', 'Ostetrica'),
('009', 'Dietologo'),
('010', 'Libero professionista');

-- --------------------------------------------------------

--
-- Table structure for table `tipologia`
--

CREATE TABLE `tipologia` (
  `ID_Tipo` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Titolo_Tipo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipologia`
--

INSERT INTO `tipologia` (`ID_Tipo`, `Titolo_Tipo`) VALUES
('001', 'Salumi'),
('002', 'Prodotti per bagno'),
('003', 'Prodotti per la casa'),
('004', 'Vestiti'),
('005', 'Formaggi'),
('006', 'Dolci');

-- --------------------------------------------------------

--
-- Table structure for table `utente`
--

CREATE TABLE `utente` (
  `Email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Nome` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Cognome` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Cell` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Data_Nascita` date NOT NULL,
  `Indirizzo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Citta` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Ruolo` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `utente`
--

INSERT INTO `utente` (`Email`, `Password`, `Nome`, `Cognome`, `Cell`, `Data_Nascita`, `Indirizzo`, `Citta`, `Ruolo`) VALUES
('antonellacarino@email.it', 'MakeUpLove01', 'Antonella', 'Carino', '3396787351', '1998-01-31', 'Via Luce, 25', 'Modena', '008'),
('antoniocaligola98@gmail.com', 'NapoliNelCuore98', 'Antonio', 'Caligola', '3398768172', '1998-05-13', 'Via X Luglio, 22', 'Napoli', '003'),
('fabiovacondio22@gmail.com', 'GigiDaless10', 'Fabio', 'Vacondio', '3338767125', '1989-06-16', 'Via Lirimendi, 9', 'Torino', '005'),
('luciamano@gmail.com', 'LucyHand9', 'Lucia', 'Mano', '3338963726', '1978-11-26', 'Via Rima, 35', 'Spezzano', '007'),
('mariapalumbo@tiscali.com', 'MaryPall13', 'Maria', 'Palumbo', '3368794826', '1988-03-08', 'Via Numeri, 14', 'Venezia', '001'),
('mariocuori@libero.it', 'InterMaiB', 'Mario', 'Cuori', '3389767453', '1978-11-15', 'Via Ilaria Maria, 64', 'Milano', '004'),
('pierfrancescobotrugno@gmail.com', 'MMBotrugn88', 'Pierfrancesco', 'Botrugno', '3208954172', '1988-09-01', 'Via Paranoia, 74', 'Taranto', '010'),
('saraparasoli@libero.it', 'ParasoliNondasoli', 'Sara', 'Parasoli', '3395739267', '1989-05-24', 'Via Carlito, 22', 'Firenze', '009');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `merce`
--
ALTER TABLE `merce`
  ADD PRIMARY KEY (`ID_Merce`),
  ADD KEY `Tipologia` (`Tipologia`);

--
-- Indexes for table `operazione`
--
ALTER TABLE `operazione`
  ADD PRIMARY KEY (`ID_Merce`,`Email`),
  ADD KEY `Email` (`Email`);

--
-- Indexes for table `ruolo`
--
ALTER TABLE `ruolo`
  ADD PRIMARY KEY (`ID_Ruolo`);

--
-- Indexes for table `tipologia`
--
ALTER TABLE `tipologia`
  ADD PRIMARY KEY (`ID_Tipo`);

--
-- Indexes for table `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`Email`),
  ADD KEY `Ruolo` (`Ruolo`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `merce`
--
ALTER TABLE `merce`
  ADD CONSTRAINT `merce_ibfk_1` FOREIGN KEY (`Tipologia`) REFERENCES `tipologia` (`ID_Tipo`);

--
-- Constraints for table `operazione`
--
ALTER TABLE `operazione`
  ADD CONSTRAINT `operazione_ibfk_1` FOREIGN KEY (`ID_Merce`) REFERENCES `merce` (`ID_Merce`),
  ADD CONSTRAINT `operazione_ibfk_2` FOREIGN KEY (`Email`) REFERENCES `utente` (`Email`);

--
-- Constraints for table `utente`
--
ALTER TABLE `utente`
  ADD CONSTRAINT `utente_ibfk_1` FOREIGN KEY (`Ruolo`) REFERENCES `ruolo` (`ID_Ruolo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
