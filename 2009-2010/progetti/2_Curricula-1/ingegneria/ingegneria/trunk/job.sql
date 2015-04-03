-- phpMyAdmin SQL Dump
-- version 3.3.2deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: 28 giu, 2010 at 10:11 PM
-- Versione MySQL: 5.1.41
-- Versione PHP: 5.3.2-1ubuntu4.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `job`
--
CREATE DATABASE `job` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `job`;

-- --------------------------------------------------------

--
-- Struttura della tabella `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `id_account` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `tipologia` int(1) NOT NULL,
  PRIMARY KEY (`id_account`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=41 ;

--
-- Dump dei dati per la tabella `account`
--

INSERT INTO `account` (`id_account`, `username`, `password`, `email`, `tipologia`) VALUES
(3, 'marco', 'marco', '', 1),
(4, 'paolo', 'paolo', 'paolo@email.it', 1),
(5, 'ricci11', 'ricci11', 'email', 1),
(6, 'azienda', 'azienda', 'aziendaprova@email.it', 2),
(7, 'ettore', 'ettore', 'ettore@email.it', 1),
(8, 'carvin', 'carvin', 'carvin@info.it', 2),
(15, 'prova2', 'prova2', 'prova2@email.it', 1),
(16, 'prova3', 'prova3', 'prova3@email.it', 1),
(17, 'a', 'a', 'a', 1),
(18, 'b', 'b', 'b', 1),
(19, 'paolo1', 'ciao', 'ca@ca.it', 1),
(20, 'franco', 'troiano', 'franco@troiano.pe.it', 1),
(21, 'italtel', 'italtel', 'staff@italtel.it', 2),
(22, 'azienda1', 'azienda1', 'email@email.it', 2),
(23, 'aaaaaaaaaa', 'aaaaaaaaaaa', 'aaaaaaaa@aaaaaaaa.it', 2),
(24, 'nnnn', 'nnnn', 'nnn@nnn.iy', 2),
(25, 'roberta', 'roberta', 'roberta@hotmail.it', 1),
(26, 'luca', 'luca', 'luca@hotmail.it', 1),
(27, 'alex', 'alex', 'alex@hotmail.it', 1),
(28, 'flavia', 'flavia', 'flavia@hotmail.it', 1),
(29, 'teresa', 'teresa', 'teresa@gmail.com', 1),
(30, 'alessia', 'alessia', 'alessia@libero.it', 1),
(31, 'paolo2', 'paolo2', 'paolo2@gmail.it', 1),
(32, 'lucia', 'lucia', 'lucia@hotmail.it', 1),
(33, 'daniele', 'daniele', 'daniele@gmail.com', 1),
(34, 'michele', 'michele', 'michele@hotmail.it', 1),
(35, 'rocco', 'rocco', 'rocco@gmail.com', 1),
(36, 'martina', 'martina', 'martina@hotmail.it', 1),
(37, 'italcementi', 'italcementi', 'staff@italcementi.it', 2),
(38, 'ciaotickets', 'ciaotickets', 'ciaotickets@staff.it', 2),
(39, 'aaaaaa', 'aaaaaa', 'aaa@aaa.it', 2),
(40, 'bbbbbb', 'bbbbbb', 'bbb@bbb.it', 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `anagrafica`
--

CREATE TABLE IF NOT EXISTS `anagrafica` (
  `id_anagrafica` int(10) NOT NULL AUTO_INCREMENT,
  `id_curriculum` int(10) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `sesso` varchar(1) NOT NULL,
  `dataNascita` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `indirizzo` varchar(100) NOT NULL,
  `provincia` varchar(2) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  PRIMARY KEY (`id_anagrafica`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dump dei dati per la tabella `anagrafica`
--

INSERT INTO `anagrafica` (`id_anagrafica`, `id_curriculum`, `nome`, `cognome`, `sesso`, `dataNascita`, `email`, `indirizzo`, `provincia`, `telefono`) VALUES
(22, 43, 'alessandro', 'savini', 'M', '1988-07-01', 'email', 'vicino al supermercato ', 'TE', '123'),
(21, 42, 'Valentina', 'Proietti', 'F', '1988-05-27', 'valentina.proietti@hotmail.it', 'via clemente de cesaris 13', 'TE', '234523432423'),
(20, 41, 'marco', 'dagostino', 'M', '1988-08-29', 'marco@email.it', 'via ciao', 'PE', '123423'),
(23, 44, 'paolo', 'dettorre', 'M', '1989-01-01', 'paolo@dettorre.it', 'via pescara', 'CH', '3282828199'),
(24, 44, 'paolo', 'dettorre', 'M', '1989-01-01', 'paolo@dettorre.it', 'via pescara', 'CH', '3282828199'),
(25, 44, 'paolo', 'dettorre', 'M', '1989-01-01', 'poiupoiu@skdjhf.it', 'oiuyoiuy', 'BA', '9876'),
(26, 47, 'paolo', 'ilhglkjgh', 'M', '1989-01-01', 'ljkhlk@ghkjhg.it', 'lujhlkjh', 'NA', '8765'),
(27, 48, 'franco', 'troiano', 'M', '1988-04-13', 'franco@troiano.pe.it', 'via pratelle 30 pianella', 'PE', '3289251343');

-- --------------------------------------------------------

--
-- Struttura della tabella `aziende`
--

CREATE TABLE IF NOT EXISTS `aziende` (
  `id_aziende` int(10) NOT NULL AUTO_INCREMENT,
  `id_account` int(10) NOT NULL,
  `ragioneSociale` varchar(255) NOT NULL,
  `piva` varchar(11) NOT NULL,
  PRIMARY KEY (`id_aziende`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dump dei dati per la tabella `aziende`
--

INSERT INTO `aziende` (`id_aziende`, `id_account`, `ragioneSociale`, `piva`) VALUES
(1, 6, 'azienda prova', '1234'),
(2, 8, 'carvin engineering', '1234'),
(3, 21, 'italtel', '1234'),
(4, 22, 'azienda x', '1209182our0'),
(5, 24, 'tttt', 'tttt'),
(6, 39, 'aaaaaa', 'aaaaaa'),
(7, 40, 'bbbbbb', 'bbbbbb');

-- --------------------------------------------------------

--
-- Struttura della tabella `capacita`
--

CREATE TABLE IF NOT EXISTS `capacita` (
  `id_capacita` int(10) NOT NULL AUTO_INCREMENT,
  `id_curriculum` int(10) NOT NULL,
  `capacita` varchar(255) NOT NULL,
  PRIMARY KEY (`id_capacita`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dump dei dati per la tabella `capacita`
--

INSERT INTO `capacita` (`id_capacita`, `id_curriculum`, `capacita`) VALUES
(11, 41, 'disegno cad'),
(12, 41, 'pacchetto microsoft office'),
(13, 42, 'autocad'),
(17, 47, 'sadfasdfa'),
(18, 47, 'asdf'),
(19, 48, 'programmazione');

-- --------------------------------------------------------

--
-- Struttura della tabella `curriculum`
--

CREATE TABLE IF NOT EXISTS `curriculum` (
  `id_curriculum` int(10) NOT NULL AUTO_INCREMENT,
  `id_account` int(10) NOT NULL,
  `numLingue` int(3) NOT NULL,
  `numIstruzione` int(3) NOT NULL,
  `numCapacita` int(3) NOT NULL,
  `numEsperienze` int(3) NOT NULL,
  `dataModifica` int(20) NOT NULL,
  PRIMARY KEY (`id_curriculum`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

--
-- Dump dei dati per la tabella `curriculum`
--

INSERT INTO `curriculum` (`id_curriculum`, `id_account`, `numLingue`, `numIstruzione`, `numCapacita`, `numEsperienze`, `dataModifica`) VALUES
(41, 16, 2, 1, 2, 1, 1275923173),
(42, 17, 0, 2, 0, 0, 1277737668),
(43, 18, 1, 1, 1, 1, 1275925659),
(47, 4, 1, 1, 2, 1, 1276178760),
(48, 20, 2, 1, 1, 1, 1276179014);

-- --------------------------------------------------------

--
-- Struttura della tabella `esperienze`
--

CREATE TABLE IF NOT EXISTS `esperienze` (
  `id_esperienze` int(10) NOT NULL AUTO_INCREMENT,
  `id_curriculum` int(10) NOT NULL,
  `datore` varchar(255) NOT NULL,
  `incarico` varchar(255) NOT NULL,
  `periodo` varchar(25) NOT NULL,
  PRIMARY KEY (`id_esperienze`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dump dei dati per la tabella `esperienze`
--

INSERT INTO `esperienze` (`id_esperienze`, `id_curriculum`, `datore`, `incarico`, `periodo`) VALUES
(6, 41, 'di quinzio', 'geometra', '2007-2008'),
(9, 47, 'asdfa', 'asdfada', 'sdfa'),
(10, 48, 'pece dino', 'elettricista', '2005');

-- --------------------------------------------------------

--
-- Struttura della tabella `istruzione`
--

CREATE TABLE IF NOT EXISTS `istruzione` (
  `id_istruzione` int(10) NOT NULL AUTO_INCREMENT,
  `id_curriculum` int(10) NOT NULL,
  `titolo` varchar(255) NOT NULL,
  `data` varchar(25) NOT NULL,
  `istituzione` varchar(255) NOT NULL,
  `voto` int(3) NOT NULL,
  `tipologia` int(1) NOT NULL,
  PRIMARY KEY (`id_istruzione`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dump dei dati per la tabella `istruzione`
--

INSERT INTO `istruzione` (`id_istruzione`, `id_curriculum`, `titolo`, `data`, `istituzione`, `voto`, `tipologia`) VALUES
(7, 41, 'geometra', '2007', 'mantone pescara', 68, 1),
(8, 42, 'geometra', '2008', 'mantone pescara', 0, 1),
(10, 43, 'a', '1', 'a', 0, 0),
(11, 42, 'kjhkjh', 'kjhkjh', 'kjhkjh', 67, 3),
(12, 47, 'sadgf', 'dwf', 'dsfsd', 231, 1),
(13, 48, 'Perito Capotecnico elettronica e telecomunicazioni', '2007', 'Itis A.Volta Pescara', 100, 1),
(14, 42, 'mediatrice linguistica', '2009', 'università di l aquila', 90, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `lingue`
--

CREATE TABLE IF NOT EXISTS `lingue` (
  `id_lingue` int(10) NOT NULL AUTO_INCREMENT,
  `id_curriculum` int(10) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `livelloParlato` int(1) NOT NULL,
  `livelloScritto` int(1) NOT NULL,
  PRIMARY KEY (`id_lingue`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dump dei dati per la tabella `lingue`
--

INSERT INTO `lingue` (`id_lingue`, `id_curriculum`, `nome`, `livelloParlato`, `livelloScritto`) VALUES
(8, 41, 'inglese', 1, 1),
(9, 41, 'francese', 1, 1),
(11, 42, 'inglese1', 1, 1),
(13, 44, 'inglese', 1, 1),
(14, 44, 'francese', 1, 1),
(15, 44, 'inglese', 1, 1),
(16, 44, 'francese', 1, 1),
(17, 47, 'asdasd', 1, 1),
(18, 48, 'inglese', 1, 1),
(19, 48, 'francese', 1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `ricerca`
--

CREATE TABLE IF NOT EXISTS `ricerca` (
  `id_ricerca` int(10) NOT NULL AUTO_INCREMENT,
  `id_azienda` int(10) NOT NULL,
  `etaMin` int(3) NOT NULL,
  `etaMax` int(3) NOT NULL,
  `sesso` varchar(3) NOT NULL,
  `lingua1` varchar(20) NOT NULL,
  `lingua2` varchar(20) NOT NULL,
  `lingua3` varchar(20) NOT NULL,
  `tipologia` varchar(1) NOT NULL,
  `denominazione` varchar(255) NOT NULL,
  `partTime` tinyint(1) NOT NULL,
  `fullTime` tinyint(1) NOT NULL,
  `determinato` tinyint(1) NOT NULL,
  `indeterminato` tinyint(1) NOT NULL,
  `dirigente` tinyint(1) NOT NULL,
  `subordinato` tinyint(1) NOT NULL,
  `lavoroEstero` tinyint(1) NOT NULL,
  `soggiornoEstero` tinyint(1) NOT NULL,
  `provincia` varchar(2) NOT NULL,
  `data` int(20) NOT NULL,
  PRIMARY KEY (`id_ricerca`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dump dei dati per la tabella `ricerca`
--

INSERT INTO `ricerca` (`id_ricerca`, `id_azienda`, `etaMin`, `etaMax`, `sesso`, `lingua1`, `lingua2`, `lingua3`, `tipologia`, `denominazione`, `partTime`, `fullTime`, `determinato`, `indeterminato`, `dirigente`, `subordinato`, `lavoroEstero`, `soggiornoEstero`, `provincia`, `data`) VALUES
(36, 8, 0, 99, 'MF', '', '', '', '', '', 0, 0, 0, 0, 0, 0, 0, 0, '', 1276445380);

-- --------------------------------------------------------

--
-- Struttura della tabella `tipoImpiego`
--

CREATE TABLE IF NOT EXISTS `tipoImpiego` (
  `id_tipoImpiego` int(10) NOT NULL AUTO_INCREMENT,
  `id_curriculum` int(10) NOT NULL,
  `provincia` varchar(2) NOT NULL,
  `partTime` tinyint(1) NOT NULL,
  `fullTime` tinyint(1) NOT NULL,
  `determinato` tinyint(1) NOT NULL,
  `indeterminato` tinyint(1) NOT NULL,
  `dirigente` tinyint(1) NOT NULL,
  `subordinato` tinyint(1) NOT NULL,
  `lavoroEstero` tinyint(1) NOT NULL,
  `soggiornoEstero` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_tipoImpiego`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dump dei dati per la tabella `tipoImpiego`
--

INSERT INTO `tipoImpiego` (`id_tipoImpiego`, `id_curriculum`, `provincia`, `partTime`, `fullTime`, `determinato`, `indeterminato`, `dirigente`, `subordinato`, `lavoroEstero`, `soggiornoEstero`) VALUES
(11, 41, 'CH', 0, 1, 0, 1, 0, 0, 1, 1),
(12, 42, 'TE', 0, 1, 0, 1, 0, 1, 1, 1),
(13, 43, 'BS', 1, 1, 1, 1, 1, 1, 1, 1),
(14, 44, '', 0, 0, 0, 0, 0, 0, 0, 0),
(15, 47, 'BS', 0, 0, 0, 0, 1, 1, 0, 0),
(16, 48, 'PE', 0, 1, 0, 0, 0, 0, 0, 0);
