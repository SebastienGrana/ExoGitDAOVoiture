-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.5.61-log - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Export de la structure de la base pour voiture_test
DROP DATABASE IF EXISTS `voiture_test`;
CREATE DATABASE IF NOT EXISTS `voiture_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `voiture_test`;

-- Export de la structure de la table voiture_test. automobile
DROP TABLE IF EXISTS `automobile`;
CREATE TABLE IF NOT EXISTS `automobile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marque` varchar(50) NOT NULL DEFAULT '0',
  `modele` varchar(50) NOT NULL DEFAULT '0',
  `moteur_id` int(11) NOT NULL DEFAULT '0',
  `frein_id` int(11) NOT NULL DEFAULT '0',
  `color` varchar(560) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_voiture_test_moteur` (`moteur_id`),
  KEY `FK_voiture_test_frein` (`frein_id`),
  CONSTRAINT `FK_voiture_test_moteur` FOREIGN KEY (`moteur_id`) REFERENCES `moteur` (`id`),
  CONSTRAINT `FK_voiture_test_frein` FOREIGN KEY (`frein_id`) REFERENCES `frein` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- Export de données de la table voiture_test.automobile : ~4 rows (environ)
/*!40000 ALTER TABLE `automobile` DISABLE KEYS */;
INSERT INTO `automobile` (`id`, `marque`, `modele`, `moteur_id`, `frein_id`) VALUES
	(1, 'Bouzouki', 'GTX', 2, 1 , 'rouge'),
	(2, 'Piano', 'Sebal-4', 5, 3, 'bleu'),
	(3, 'Cornemuse', 'Santo', 4, 5, 'vert'),
	(4, 'Basson', 'XXC', 1, 2, 'cyan');
/*!40000 ALTER TABLE `automobile` ENABLE KEYS */;

-- Export de la structure de la table voiture_test. frein
DROP TABLE IF EXISTS `frein`;
CREATE TABLE IF NOT EXISTS `frein` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marque` varchar(50) NOT NULL DEFAULT '0',
  `modele` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Export de données de la table voiture_test.frein : ~5 rows (environ)
/*!40000 ALTER TABLE `frein` DISABLE KEYS */;
INSERT INTO `frein` (`id`, `marque`, `modele`) VALUES
	(1, 'Bendix', '160R'),
	(2, 'Bendix', '180A'),
	(3, 'GUR', '20EZ'),
	(4, 'GUR', '78TY'),
	(5, 'MAZ', 'KIKA11');
/*!40000 ALTER TABLE `frein` ENABLE KEYS */;

-- Export de la structure de la table voiture_test. moteur
DROP TABLE IF EXISTS `moteur`;
CREATE TABLE IF NOT EXISTS `moteur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `marque` varchar(50) NOT NULL DEFAULT '0',
  `modele` varchar(50) NOT NULL DEFAULT '0',
  `cylindree` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Export de données de la table voiture_test.moteur : ~5 rows (environ)
/*!40000 ALTER TABLE `moteur` DISABLE KEYS */;
INSERT INTO `moteur` (`id`, `marque`, `modele`, `cylindree`) VALUES
	(1, 'Subaru', 'TVL', 1600),
	(2, 'Peugeot', 'BTH-1120', 1200),
	(3, 'Peugeot', 'TYU99', 2200),
	(4, 'Toyota', 'GH55', 1800),
	(5, 'Renault', 'VJ55', 1500);
/*!40000 ALTER TABLE `moteur` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
