-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 21 déc. 2024 à 02:50
-- Version du serveur : 8.3.0
-- Version de PHP : 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `autoecole`
--

DELIMITER $$
--
-- Procédures
--
DROP PROCEDURE IF EXISTS `genererComptes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `genererComptes` ()   BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE compteId INT;
    DECLARE eleveId INT;
    DECLARE eleveNom VARCHAR(50);
    DECLARE elevePrenom VARCHAR(50);
    DECLARE moniteurId INT;
    DECLARE moniteurNom VARCHAR(50);
    DECLARE moniteurPrenom VARCHAR(50);
    DECLARE suffixe INT DEFAULT 1;
    DECLARE loginUnique VARCHAR(50);

    -- Déclaration des curseurs
    DECLARE eleveCursor CURSOR FOR SELECT CodeEleve, Nom, Prenom FROM eleve;
    DECLARE moniteurCursor CURSOR FOR SELECT CodeMoniteur, Nom, Prenom FROM moniteur;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    -- Traitement pour les élèves
    OPEN eleveCursor;
    eleve_loop: LOOP
        FETCH eleveCursor INTO eleveId, eleveNom, elevePrenom;
        IF done THEN
            LEAVE eleve_loop;
        END IF;

        -- Génération du login
        SET @login = CONCAT(LEFT(elevePrenom, 3), LEFT(eleveNom, 2));
        SET loginUnique = @login;

        -- Vérification des doublons
        WHILE EXISTS (SELECT 1 FROM Compte WHERE login = loginUnique) DO
            SET loginUnique = CONCAT(@login, suffixe);
            SET suffixe = suffixe + 1;
        END WHILE;

        -- Insertion dans la table Compte
        INSERT INTO Compte (login, motDePasse) 
        VALUES (loginUnique, CONCAT('test', LAST_INSERT_ID()));

        -- Récupération de l'ID de compte nouvellement créé
        SET compteId = LAST_INSERT_ID();

        -- Mise à jour de l'élève avec le numéro de compte
        UPDATE eleve SET numCompte = compteId WHERE CodeEleve = eleveId;
    END LOOP;
    CLOSE eleveCursor;

    -- Réinitialisation du signal pour les moniteurs
    SET done = 0;
    SET suffixe = 1;  -- Réinitialisation du suffixe

    -- Traitement pour les moniteurs
    OPEN moniteurCursor;
    moniteur_loop: LOOP
        FETCH moniteurCursor INTO moniteurId, moniteurNom, moniteurPrenom;
        IF done THEN
            LEAVE moniteur_loop;
        END IF;

        -- Génération du login
        SET @login = CONCAT(LEFT(moniteurPrenom, 3), LEFT(moniteurNom, 2));
        SET loginUnique = @login;

        -- Vérification des doublons
        WHILE EXISTS (SELECT 1 FROM Compte WHERE login = loginUnique) DO
            SET loginUnique = CONCAT(@login, suffixe);
            SET suffixe = suffixe + 1;
        END WHILE;

        -- Insertion dans la table Compte
        INSERT INTO Compte (login, motDePasse) 
        VALUES (loginUnique, CONCAT('test', LAST_INSERT_ID()));

        -- Récupération de l'ID de compte nouvellement créé
        SET compteId = LAST_INSERT_ID();

        -- Mise à jour du moniteur avec le numéro de compte
        UPDATE moniteur SET numCompte = compteId WHERE CodeMoniteur = moniteurId;
    END LOOP;
    CLOSE moniteurCursor;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `CodeCategorie` int NOT NULL,
  `Libelle` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Prix` double(5,2) NOT NULL,
  PRIMARY KEY (`CodeCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`CodeCategorie`, `Libelle`, `Prix`) VALUES
(1, 'Automobile', 34.95),
(2, 'Poids lourd', 43.00),
(3, 'Bateau', 51.25),
(4, 'Moto', 38.15),
(5, 'Transport en commun', 40.50);

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `numCompte` int NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `motDePasse` varchar(50) NOT NULL,
  `statut` int NOT NULL,
  PRIMARY KEY (`numCompte`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`numCompte`, `login`, `motDePasse`, `statut`) VALUES
(1, 'GweGn', 'test0', 0),
(2, 'MazMa', 'test1', 0),
(3, 'OmeOs', 'test2', 0),
(4, 'SopSp', 'test3', 0),
(5, 'ThéTo', 'test4', 0),
(6, 'PisPo', 'test5', 0),
(7, 'CélCa', 'test6', 0),
(8, 'RomRa', 'test7', 0),
(9, 'BruBa', 'test8', 0),
(10, 'TanTo', 'test9', 0),
(11, 'VérVe', 'test10', 0),
(12, 'ThoTa', 'test11', 0),
(13, 'GonGh', 'test12', 0),
(14, 'BruBu', 'test13', 0),
(15, 'CléCa', 'test14', 0),
(16, 'ChaCh', 'test15', 0),
(17, 'BasBr', 'test16', 0),
(18, 'FerFa', 'test17', 0),
(19, 'LazLa', 'test18', 0),
(20, 'RolRi', 'test19', 0),
(21, 'PauPo', 'test20', 0),
(22, 'ThéTa', 'test21', 0),
(23, 'MilMo', 'test22', 0),
(24, 'CloCa', 'test23', 0),
(25, 'CorCl', 'test24', 0),
(26, 'FélFe', 'test25', 0),
(27, 'RitRi', 'test26', 0),
(28, 'CléCa1', 'test27', 0),
(29, 'AmaAn', 'test28', 0),
(30, 'ScaSc', 'test29', 0),
(31, 'AmaAn2', 'test30', 0),
(32, 'BriBa', 'test31', 0),
(33, 'FioFu', 'test32', 0),
(34, 'MélMe', 'test33', 0),
(35, 'FerFa3', 'test34', 0),
(36, 'VérVo', 'test35', 0),
(37, 'PasPe', 'test36', 0),
(38, 'ChaCo', 'test37', 0),
(39, 'SabSt', 'test38', 0),
(40, 'MarMi', 'test39', 0),
(41, 'PiePa', 'test40', 0),
(42, 'FloFo', 'test41', 0),
(43, 'CunCa', 'test42', 0),
(44, 'CorCa', 'test43', 0),
(45, 'TerTo', 'test44', 0),
(46, 'ChrCa', 'test45', 0),
(47, 'SimSc', 'test46', 0),
(48, 'LéoLi', 'test47', 0),
(49, 'BénBi', 'test48', 0),
(50, 'UrsUo', 'test49', 0),
(51, 'CamCa', 'test50', 0),
(52, 'UllUc', 'test51', 0),
(53, 'ConCa', 'test52', 0),
(54, 'PriPr', 'test53', 0),
(55, 'PatPe', 'test54', 0),
(56, 'SteSp', 'test55', 0),
(57, 'PatPa', 'test56', 0),
(58, 'ThiTe', 'test57', 0),
(59, 'ZoéZu', 'test58', 0),
(60, 'ArmAr', 'test59', 0),
(61, 'TerTr', 'test60', 0),
(62, 'ChrCa4', 'test61', 0),
(63, 'OrnOs', 'test62', 0),
(64, 'ThéTr', 'test63', 0),
(65, 'PolPo', 'test64', 0),
(66, 'CarCa', 'test65', 0),
(67, 'PiePe', 'test66', 0),
(68, 'VinVi', 'test67', 0),
(69, 'CélCi', 'test68', 0),
(70, 'PauPo5', 'test69', 0),
(71, 'MarMa', 'test70', 0),
(72, 'AlbAb', 'test71', 0),
(73, 'ValVa', 'test72', 0),
(74, 'GérBé', 'test73', 1),
(75, 'PieAm', 'test74', 1),
(76, 'OliCa', 'test75', 1),
(77, 'RégEz', 'test76', 1),
(78, 'testEleve', 'testEleve', 0),
(79, 'testMoniteur', 'testMoniteur', 1),
(80, 'HollowPredator', 'hollow', 1),
(81, 'Succes', 'Succes', 1),
(83, 'SuccesEleve', 'Succes', 0),
(84, 'tryInscMoniteur', 'tryInscMoniteur', 1),
(85, 'tryInscEleve', 'tryInscEleve', 0),
(86, 'tagrandmere', 'la pute', 0),
(87, 'toto', 'titi', 0),
(88, 'Zobi', 'Zobi', 0);

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

DROP TABLE IF EXISTS `eleve`;
CREATE TABLE IF NOT EXISTS `eleve` (
  `CodeEleve` int NOT NULL,
  `Nom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Prenom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Sexe` tinyint NOT NULL,
  `DateDeNaissance` date DEFAULT NULL,
  `Adresse1` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `CodePostal` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Ville` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Telephone` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `numCompte` int DEFAULT NULL,
  `imgPdp` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`CodeEleve`),
  KEY `numCompte` (`numCompte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `eleve`
--

INSERT INTO `eleve` (`CodeEleve`, `Nom`, `Prenom`, `Sexe`, `DateDeNaissance`, `Adresse1`, `CodePostal`, `Ville`, `Telephone`, `numCompte`, `imgPdp`) VALUES
(8, 'Gnocchi', 'Gwendoline', 1, '1972-12-21', '', '75008', 'Paris', '0104310779', 1, NULL),
(16, 'Macaroni', 'Mazarine', 1, '1982-06-01', '', '75016', 'Paris', '0118060182', 2, NULL),
(18, 'Ossobucco', 'Omer', 0, '1981-07-03', '', '75018', 'Paris', '0117070381', 3, NULL),
(22, 'Spaghetti', 'Sophie', 1, '1979-09-07', '', '75002', 'Paris', '0115090779', 4, NULL),
(23, 'Tortellini', 'Théodule', 0, '1995-12-21', '', '75003', 'Paris', '0114210880', 5, NULL),
(41, 'Pomadoro', 'Pistache', 1, '2002-12-21', '', '75001', 'Paris', '0116141178', 6, NULL),
(53, 'Cannelloni', 'Célestine', 1, '1971-12-21', '', '75003', 'Paris', '0171737579', 7, NULL),
(57, 'Ravioli', 'Romuald', 0, '1991-12-21', '', '75017', 'Paris', '0109210781', 8, NULL),
(63, 'Baccalaõ', 'Brutus', 0, '1979-11-09', '', '75003', 'Paris', '0145464748', 9, NULL),
(64, 'Tortilla', 'Tania', 1, '1985-12-21', '', '75004', 'Paris', '0122270181', 10, NULL),
(70, 'Vermicellini', 'Véronique', 1, '1994-12-21', '', '75010', 'Paris', '0128220381', 11, NULL),
(71, 'Tagliatelli', 'Thomas', 0, '1960-12-21', '', '75011', 'Paris', '0129170482', 12, NULL),
(77, 'Ghappati', 'Gontrand', 0, '1981-12-02', '', '75017', 'Paris', '0135120281', 13, NULL),
(84, 'Busecca', 'Bruce', 0, '1981-11-07', '', '75004', 'Paris', '0187868584', 14, NULL),
(85, 'Carpaccio', 'Clémentine', 1, '1962-12-21', '', '75005', 'Paris', '0142130781', 15, NULL),
(87, 'Chipolata', 'Charlotte', 1, '1972-12-21', '', '75007', 'Paris', '0144220379', 16, NULL),
(91, 'Broccoli', 'Basile', 0, '1981-02-07', '', '75011', 'Paris', '0176757473', 17, NULL),
(92, 'Farfalle', 'Fernande', 1, '1967-12-21', '', '75012', 'Paris', '0148141281', 18, NULL),
(93, 'Lasagne', 'Lazare', 0, '1965-12-21', '', '75013', 'Paris', '0149130481', 19, NULL),
(95, 'Risotto', 'Rolande', 1, '1968-12-21', '', '75015', 'Paris', '0151170280', 20, NULL),
(96, 'Polenta', 'Paule', 1, '1986-12-21', '', '75016', 'Paris', '0152290379', 21, NULL),
(97, 'Tapioca', 'Thérèse', 1, '1980-08-05', '', '75017', 'Paris', '0153080580', 22, NULL),
(100, 'Mozzarella', 'Milène', 1, '1979-09-10', '', '75020', 'Paris', '0156091079', 23, NULL),
(102, 'Caponata', 'Clovis', 0, '1980-07-06', '', '75002', 'Paris', '0158070680', 24, NULL),
(106, 'Clafouti', 'Cornelia', 1, '1972-12-21', '', '75006', 'Paris', '0162210281', 25, NULL),
(110, 'Fettucine', 'Félicie', 1, '1981-06-05', '', '75010', 'Paris', '0110060581', 26, NULL),
(111, 'Rigatoni', 'Rita', 1, '1995-12-21', '', '75011', 'Paris', '0161280681', 27, NULL),
(112, 'Cappelletti', 'Clémence', 1, '1981-12-10', '', '75012', 'Paris', '0165091011', 28, NULL),
(113, 'Antipasta', 'Amadeus', 0, '2002-12-21', '', '75013', 'Paris', '0123242526', 29, NULL),
(114, 'Scaloppine', 'Scarlet', 1, '1967-12-21', '', '75014', 'Paris', '0127091283', 30, NULL),
(115, 'Anguilla', 'Amandine', 1, '1974-12-21', '', '75015', 'Paris', '0112131415', 31, NULL),
(116, 'Bagnacauda', 'Brigitte', 1, '1964-12-21', '', '75016', 'Paris', '0156575859', 32, NULL),
(117, 'Funghi', 'Fiona', 1, '1991-12-21', '', '75017', 'Paris', '0187767583', 33, NULL),
(118, 'Melanzane', 'Mélanie', 1, '2005-12-21', '', '75018', 'Paris', '0154467985', 34, NULL),
(119, 'Fagioli', 'Ferdinand', 0, '1997-12-21', '', '75019', 'Paris', '0189888786', 35, NULL),
(120, 'Vongole', 'Véronica', 1, '1962-12-21', '', '75020', 'Paris', '0145464748', 36, NULL),
(121, 'Pesce', 'Pascaline', 1, '1963-12-21', '', '75001', 'Paris', '0131323334', 37, NULL),
(122, 'Cozze', 'Charline', 1, '1971-12-21', '', '75002', 'Paris', '0117191613', 38, NULL),
(123, 'Stracciatella', 'Sabrina', 1, '1975-05-05', '', '75003', 'Paris', '0131649728', 39, NULL),
(124, 'Minestrone', 'Martina', 1, '1959-12-21', '', '75004', 'Paris', '0195969798', 40, NULL),
(125, 'Pavese', 'Pietro', 0, '1979-08-07', '', '75005', 'Paris', '0107080910', 41, NULL),
(126, 'Fonduta', 'Florence', 1, '1979-09-09', '', '75006', 'Paris', '0103050709', 42, NULL),
(127, 'Carozza', 'Cunégonde', 1, '1976-12-21', '', '75007', 'Paris', '0105090307', 43, NULL),
(128, 'Calzone', 'Corentin', 0, '1996-12-21', '', '75008', 'Paris', '0186848280', 44, NULL),
(129, 'Tortino', 'Terrence', 0, '1980-05-12', '', '75009', 'Paris', '0104070205', 45, NULL),
(130, 'Carciofi', 'Christian', 0, '1997-12-21', '', '75010', 'Paris', '0103060908', 46, NULL),
(131, 'Scampi', 'Simone', 1, '1982-01-05', '', '75011', 'Paris', '0104050607', 47, NULL),
(132, 'Limone', 'Léon', 0, '1981-08-09', '', '75012', 'Paris', '0192969498', 48, NULL),
(133, 'Bisi', 'Bénédicte', 1, '1979-07-08', '', '75013', 'Paris', '0165646362', 49, NULL),
(134, 'Uova', 'Ursule', 1, '1990-12-21', '', '75014', 'Paris', '0103050709', 50, NULL),
(135, 'Carbonara', 'Camille', 1, '1979-11-10', '', '75015', 'Paris', '0151535759', 51, NULL),
(136, 'Uccelletto', 'Ulla', 1, '2001-12-21', '', '75016', 'Paris', '0108060402', 52, NULL),
(137, 'Cavoli', 'Constant', 0, '1980-12-21', '', '75017', 'Paris', '0104040404', 53, NULL),
(138, 'Prosciutto', 'Priscilla', 1, '1992-12-21', '', '75018', 'Paris', '0151525354', 54, NULL),
(139, 'Peperonata', 'Patricia', 1, '1972-08-05', '', '75019', 'Paris', '0159575553', 55, NULL),
(140, 'Spinaci', 'Steve', 0, '1979-06-04', '', '75020', 'Paris', '0186848280', 56, NULL),
(141, 'Parmigiana', 'Patrick', 0, '1961-12-21', '', '75001', 'Paris', '0102050809', 57, NULL),
(142, 'Tegame', 'Thierry', 0, '1976-06-16', '', '75002', 'Paris', '0104060709', 58, NULL),
(143, 'Zucchini', 'Zoé', 1, '1973-12-21', '', '75003', 'Paris', '0108070504', 59, NULL),
(144, 'Aragosta', 'Armande', 1, '1978-12-21', '', '75004', 'Paris', '0134353637', 60, NULL),
(145, 'Trotelle', 'Teresa', 1, '1962-12-21', '', '75005', 'Paris', '0160824281', 61, NULL),
(146, 'Cacciucco', 'Christelle', 1, '1980-09-11', '', '57006', 'Paris', '0197959391', 62, NULL),
(147, 'Ostriche', 'Ornella', 1, '1972-12-21', '', '75007', 'Paris', '0194989692', 63, NULL),
(148, 'Triglie', 'Théodule', 0, '1966-12-21', '', '75008', 'Paris', '0168646662', 64, NULL),
(149, 'Pollo', 'Polo', 0, '1957-12-21', '', '75009', 'Paris', '0174859652', 65, NULL),
(150, 'Cacciatora', 'Carmen', 1, '1979-09-09', '', '75010', 'Paris', '0198979695', 66, NULL),
(151, 'Peperoni', 'Pierre', 0, '1981-11-11', '', '75011', 'Paris', '0197643231', 67, NULL),
(152, 'Vitello', 'Vincent', 0, '1982-12-02', '', '75012', 'Paris', '0189452365', 68, NULL),
(153, 'Cima', 'Céline', 1, '1983-12-21', '', '75013', 'Paris', '0131649782', 69, NULL),
(154, 'Polpette', 'Pauline', 1, '1985-12-21', '', '75014', 'Paris', '0148592615', 70, NULL),
(155, 'Manzo', 'Marceline', 1, '1970-12-21', '', '75015', 'Paris', '0136353433', 71, NULL),
(156, 'Abbacchio', 'Alberte', 1, '1987-12-21', '', '75016', 'Paris', '0102030405', 72, NULL),
(157, 'Vaccinara', 'Valentine', 1, '1970-12-21', '', '75017', 'Paris', '0134373895', 73, NULL),
(999, 'Gros', 'Petit', 2, '2006-01-10', '14 allée de la République', '92000', 'Nanterre', '0123456789', 78, NULL),
(1083, 'Succes', 'Succes', 1, '2024-10-28', 'Succes', 'Succes', 'Succes', 'Succes', 83, ' '),
(1085, 'tryInscEleve', 'tryInscEleve', 1, '2024-11-04', 'tryInscEleve', 'tryInscEleve', 'tryInscEleve', 'tryInscEleve', 85, ' '),
(1086, 'noetzel', 'lorenz', 1, '2003-02-28', 'jhedgfv', '92000', 'etb', '0123456789', 86, ' '),
(1087, 'noetzel', 'lorenz', 1, '2003-02-28', 'jhedgfv', '92000', 'etb', '0123456789', 87, ' '),
(1088, 'Zobi', 'Zobi', 0, '2024-11-05', 'Zobi', 'Zobi', 'Zobi', 'Zobi', 88, ' ');

-- --------------------------------------------------------

--
-- Structure de la table `eleve_categorie`
--

DROP TABLE IF EXISTS `eleve_categorie`;
CREATE TABLE IF NOT EXISTS `eleve_categorie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `CodeEleve` int NOT NULL,
  `codeCategorie` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `CodeEleve` (`CodeEleve`),
  KEY `codeCategorie` (`codeCategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `eleve_categorie`
--

INSERT INTO `eleve_categorie` (`id`, `CodeEleve`, `codeCategorie`) VALUES
(5, 8, 2),
(6, 8, 3),
(22, 999, 1),
(23, 999, 3),
(24, 78, 1),
(25, 78, 3),
(26, 78, 2),
(27, 78, 4),
(28, 999, 4),
(29, 999, 2);

-- --------------------------------------------------------

--
-- Structure de la table `lecon`
--

DROP TABLE IF EXISTS `lecon`;
CREATE TABLE IF NOT EXISTS `lecon` (
  `CodeLecon` int NOT NULL AUTO_INCREMENT,
  `Date` date DEFAULT NULL,
  `Heure` varchar(50) DEFAULT NULL,
  `CodeMoniteur` int DEFAULT NULL,
  `CodeEleve` int DEFAULT NULL,
  `Immatriculation` varchar(50) DEFAULT NULL,
  `Reglee` int NOT NULL,
  PRIMARY KEY (`CodeLecon`),
  KEY `CodeMoniteur` (`CodeMoniteur`),
  KEY `CodeEleve` (`CodeEleve`),
  KEY `Immatriculation` (`Immatriculation`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lecon`
--

INSERT INTO `lecon` (`CodeLecon`, `Date`, `Heure`, `CodeMoniteur`, `CodeEleve`, `Immatriculation`, `Reglee`) VALUES
(1, '2024-12-27', '15:00', 79, 999, '456 DE 21', 1),
(8, '2016-08-23', '10:00:00', 11, 22, '123 AB 21', 1),
(9, '2016-07-23', '10:00:00', 11, 63, '234 BC 21', 1),
(10, '2015-12-24', '10:00:00', 11, 63, '234 BC 21', 1),
(11, '2016-06-07', '10:00:00', 11, 63, '234 BC 21', 1),
(16, '2016-09-05', '10:00:00', 14, 113, '789 GH 21', 1),
(17, '2016-04-03', '10:00:00', 14, 113, '789 GH 21', 1),
(18, '2016-03-31', '10:00:00', 14, 113, '789 GH 21', 1),
(19, '2016-07-16', '10:00:00', 14, 113, '789 GH 21', 1),
(20, '2016-07-12', '10:00:00', 14, 113, '789 GH 21', 1),
(21, '2016-08-28', '10:00:00', 8, 113, '234 BC 21', 1),
(22, '2015-12-29', '10:00:00', 8, 8, '234 BC 21', 1),
(23, '2016-01-02', '10:00:00', 8, 16, '234 BC 21', 1),
(24, '2016-09-22', '10:00:00', 11, 22, '456 DE 21', 1),
(25, '2016-01-30', '10:00:00', 14, 23, '345 CD 21', 1),
(26, '2016-04-09', '10:00:00', 8, 18, '234 BC 21', 1),
(27, '2016-08-01', '10:00:00', 11, 8, '234 BC 21', 1),
(28, '2016-02-14', '10:00:00', 14, 16, '234 BC 21', 1),
(29, '2016-01-16', '10:00:00', 11, 23, '234 BC 21', 1),
(30, '2016-01-12', '10:00:00', 8, 53, '234 BC 21', 1),
(31, '2016-08-09', '10:00:00', 11, 22, '234 BC 21', 1),
(32, '2016-04-02', '10:00:00', 8, 8, '234 BC 21', 1),
(33, '2016-08-25', '10:00:00', 8, 8, '234 BC 21', 1),
(34, '2016-02-06', '10:00:00', 8, 8, '234 BC 21', 1),
(35, '2016-03-29', '10:00:00', 8, 8, '234 BC 21', 1),
(36, '2015-12-25', '10:00:00', 8, 8, '234 BC 21', 1),
(37, '2016-02-06', '10:00:00', 8, 8, '234 BC 21', 1),
(38, '2016-08-16', '10:00:00', 11, 8, '234 BC 21', 1),
(39, '2016-05-20', '10:00:00', 14, 16, '345 CD 21', 1),
(40, '2016-08-08', '10:00:00', 14, 53, '456 DE 21', 1),
(41, '2016-07-06', '10:00:00', 14, 18, '456 DE 21', 1),
(42, '2016-02-01', '10:00:00', 11, 148, '567 EF 21', 1),
(43, '2015-12-09', '10:00:00', 11, 57, '234 BC 21', 1),
(44, '2016-07-13', '10:00:00', 14, 71, '456 DE 21', 1),
(45, '2016-04-30', '10:00:00', 14, 71, '456 DE 21', 1),
(46, '2015-12-22', '10:00:00', 14, 71, '456 DE 21', 1),
(47, '2016-08-23', '10:00:00', 14, 71, '456 DE 21', 1),
(48, '2016-06-05', '10:00:00', 14, 71, '456 DE 21', 1),
(49, '2016-05-02', '10:00:00', 8, 116, '234 BC 21', 1),
(50, '2016-08-27', '10:00:00', 8, 96, '234 BC 21', 1),
(51, '2016-01-21', '10:00:00', 14, 41, '345 CD 21', 1),
(52, '2016-01-09', '10:00:00', 11, 115, '345 CD 21', 1),
(53, '2016-04-20', '10:00:00', 14, 128, '789 GH 21', 1),
(54, '2016-01-24', '10:00:00', 14, 128, '789 GH 21', 1),
(56, '2016-06-10', '10:00:00', 11, 148, '567 EF 21', 1),
(57, '2016-07-11', '10:00:00', 11, 148, '567 EF 21', 1),
(58, '2016-09-08', '10:00:00', 8, 85, '890 HJ 21', 1),
(59, '2016-08-07', '10:00:00', 8, 145, '890 HJ 21', 1),
(60, '2016-02-09', '10:00:00', 11, 8, '456 DE 21', 1),
(61, '2016-06-07', '10:00:00', 11, 8, '234 BC 21', 1),
(62, '2016-01-14', '10:00:00', 11, 157, '678 FG 21', 1),
(63, '2016-08-10', '10:00:00', 11, 157, '678 FG 21', 1),
(64, '2016-07-29', '10:00:00', 11, 157, '678 FG 21', 1),
(65, '2016-06-24', '10:00:00', 11, 157, '678 FG 21', 1),
(66, '2016-04-12', '10:00:00', 14, 157, '678 FG 21', 1),
(67, '2016-08-10', '10:00:00', 11, 132, '123 AB 21', 1),
(68, '2016-03-25', '10:00:00', 14, 8, '234 BC 21', 1),
(69, '2016-07-14', '10:00:00', 15, 16, '345 CD 21', 1),
(73, '2016-03-27', '10:00:00', 15, 8, '123 AB 21', 1),
(74, '2016-02-19', '10:00:00', 15, 8, '123 AB 21', 1),
(75, '2016-07-29', '10:00:00', 11, 8, '123 AB 21', 1),
(76, '2016-02-01', '10:00:00', 14, 16, '234 BC 21', 1),
(77, '2016-03-10', '10:00:00', 15, 18, '345 CD 21', 1),
(79, '2016-01-19', '10:00:00', 14, 8, '234 BC 21', 1),
(80, '2016-07-04', '10:00:00', 15, 16, '345 CD 21', 1),
(81, '2016-04-03', '10:00:00', 11, 8, '123 AB 21', 1),
(85, '2016-01-11', '10:00:00', 14, 16, '234 BC 21', 1),
(86, '2016-04-29', '10:00:00', 15, 18, '345 CD 21', 1),
(87, '2016-07-06', '10:00:00', 11, 8, '123 AB 21', 1),
(88, '2016-06-16', '10:00:00', 14, 16, '234 BC 21', 1),
(89, '2016-02-02', '10:00:00', 15, 18, '345 CD 21', 1),
(90, '2016-08-23', '10:00:00', 15, 18, '789 GH 21', 0),
(91, '2016-08-22', '10:00:00', 14, 18, '567 EF 21', 0),
(92, '2017-09-02', '13:56:00', 11, 63, '456 DE 21', 0),
(93, '2017-09-02', '13:58:00', 11, 129, '345 CD 21', 0),
(94, '2017-09-02', '14:00:00', 11, 64, '345 CD 21', 0),
(95, '2017-09-02', '14:09:00', 14, 64, '678 FG 21', 0),
(96, '2017-09-02', '15:00:00', 11, 70, '678 FG 21', 0),
(97, '2017-09-02', '14:13:00', 14, 84, '234 BC 21', 0),
(98, '2017-11-17', '09:15:00', 15, 156, '890 HJ 21', 0),
(99, '2017-08-23', '10:00:00', 8, 23, '567 EF 21', 0),
(100, '2017-09-02', '14:19:00', 11, 16, '567 EF 21', 0),
(101, '2017-09-02', '14:20:00', 14, 123, '345 CD 21', 0),
(102, '2017-09-02', '14:23:00', 14, 8, '456 DE 21', 0),
(103, '2017-09-02', '14:26:00', 11, 141, '456 DE 21', 0),
(104, '2017-09-02', '14:28:00', 8, 16, '345 CD 21', 0),
(105, '2017-09-02', '14:29:00', 11, 16, '456 DE 21', 0),
(106, '2017-09-02', '14:30:00', 14, 77, '345 CD 21', 0),
(107, '2017-09-02', '14:36:00', 14, 16, '234 BC 21', 0),
(108, '2017-09-02', '14:40:00', 14, 16, '234 BC 21', 0),
(109, '2017-09-05', '14:43:00', 11, 8, '456 DE 21', 0),
(110, '2017-09-02', '08:46:00', 11, 8, '345 CD 21', 0),
(111, '2017-09-05', '11:47:00', 8, 16, '345 CD 21', 0),
(112, '2017-09-02', '15:03:00', 11, 8, '456 DE 21', 0),
(113, '2017-09-29', '18:07:00', 11, 16, '234 BC 21', 0),
(114, '2017-11-14', '02:10:00', 11, 128, '789 GH 21', 0),
(115, '2017-09-02', '18:16:00', 11, 8, '345 CD 21', 0),
(116, '2017-09-02', '13:17:00', 11, 8, '345 CD 21', 0),
(117, '2017-11-02', '13:26:00', 11, 131, '345 CD 21', 0),
(118, '2015-11-11', '02:09:00', 15, 57, '789 GH 21', 0),
(119, '2020-09-09', '18:28:00', 11, 53, '456 DE 21', 0),
(120, '2017-09-22', '08:00:00', 8, 23, '567 EF 21', 0),
(121, '2017-09-22', '16:48:00', 11, 16, '345 CD 21', 0),
(122, '2017-09-22', '02:00:00', 11, 53, '567 EF 21', 0),
(123, '2017-09-27', '08:00:00', 14, 64, '678 FG 21', 0),
(124, '2018-08-17', '09:02:00', 11, 102, '456 DE 21', 0),
(125, '2020-06-24', '11:00:00', 14, 64, '789 GH 21', 0),
(126, '2020-12-16', '14:00:00', 14, 16, '345 CD 21', 0),
(127, '2020-12-16', '14:00:00', 15, 8, '678 FG 21', 0),
(128, '2020-12-16', '15:00:00', 14, 126, '567 EF 21', 0),
(129, '2024-12-14', '11:00', 14, 999, '234 BC 21', 0),
(130, '2024-12-13', '10:00', 14, 999, '789 GH 21', 1),
(131, '2024-12-14', '10:00', 14, 999, '789 GH 21', 0),
(132, '2024-12-20', '11:00', 15, 999, '789 GH 21', 0),
(133, '2024-12-20', '10:00', 79, 999, '123 AB 21', 1),
(134, '2024-12-20', '12:00', 79, 999, '456 DE 21', 1),
(135, '2024-12-09', '11:00', 79, 999, '345 CD 21', 0),
(136, '2024-12-22', '11:00', 79, 999, '345 CD 21', 0),
(137, '2024-12-26', '11:00', 79, 999, '456 DE 21', 1),
(138, '2024-12-22', '10:00', 79, 999, '123 AB 21', 1),
(139, '2024-12-23', '12:00', 79, 999, '234 BC 21', 0),
(140, '2024-12-21', '10:00', 11, 999, '789 GH 21', 0);

-- --------------------------------------------------------

--
-- Structure de la table `licence`
--

DROP TABLE IF EXISTS `licence`;
CREATE TABLE IF NOT EXISTS `licence` (
  `CodeLicence` int NOT NULL AUTO_INCREMENT,
  `CodeMoniteur` int DEFAULT NULL,
  `CodeCategorie` int DEFAULT NULL,
  `DateObtention` date DEFAULT NULL,
  PRIMARY KEY (`CodeLicence`),
  KEY `CodeMoniteur` (`CodeMoniteur`,`CodeCategorie`),
  KEY `CodeCategorie` (`CodeCategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `licence`
--

INSERT INTO `licence` (`CodeLicence`, `CodeMoniteur`, `CodeCategorie`, `DateObtention`) VALUES
(1, 11, 1, NULL),
(2, 11, 4, NULL),
(3, 11, 5, NULL),
(4, 8, 3, NULL),
(5, 14, 1, NULL),
(6, 14, 2, NULL),
(7, 14, 5, NULL),
(8, 15, 1, NULL),
(9, 15, 3, NULL),
(10, 11, 2, NULL),
(11, 79, 1, NULL),
(13, 79, 4, NULL),
(14, 79, 2, NULL),
(15, 79, 5, NULL),
(16, 11, 3, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `moniteur`
--

DROP TABLE IF EXISTS `moniteur`;
CREATE TABLE IF NOT EXISTS `moniteur` (
  `CodeMoniteur` int NOT NULL,
  `Nom` varchar(50) DEFAULT NULL,
  `Prenom` varchar(50) DEFAULT NULL,
  `Sexe` tinyint DEFAULT NULL,
  `DateDeNaissance` date DEFAULT NULL,
  `Adresse1` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `CodePostal` varchar(50) DEFAULT NULL,
  `Ville` varchar(50) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL,
  `numCompte` int DEFAULT NULL,
  `imgPdp` varchar(255) NOT NULL,
  PRIMARY KEY (`CodeMoniteur`),
  KEY `numCompte` (`numCompte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `moniteur`
--

INSERT INTO `moniteur` (`CodeMoniteur`, `Nom`, `Prenom`, `Sexe`, `DateDeNaissance`, `Adresse1`, `CodePostal`, `Ville`, `Telephone`, `numCompte`, `imgPdp`) VALUES
(8, 'Béal', 'Géraldine', 1, '1972-01-23', '12, avenue du Collège', '75004', 'Paris', '0180123456', 74, '0'),
(11, 'Ambrosi', 'Pierre', 2, '1969-01-04', '23, rue du Lycée', '75019', 'Paris', '0180234567', 75, '0'),
(14, 'Catard', 'Olivier', 2, '1963-09-12', '34, boulevard de l\'Université', '75005', 'Paris', '0180345677', 76, '0'),
(15, 'Ezard', 'Régine', 1, '1978-04-06', '45, rue des Écoles', '75020', 'Paris', '0180456789', 77, '0'),
(79, 'Eud', 'Jean', 1, '2006-01-10', '6 allée de la République', '92000', 'popo', '0123456789', 79, 'Null'),
(1081, 'Succes', 'Succes', 1, '2004-12-29', 'Succes', 'Succes', 'Succes', 'Succes', 81, ' '),
(1084, 'tryInscMoniteur', 'tryInscMoniteur', 0, '2024-11-13', 'tryInscMoniteur', 'tryInscMoniteur', 'tryInscMoniteur', 'tryInscMoniteur', 84, ' ');

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `Immatriculation` varchar(50) NOT NULL,
  `Marque` varchar(50) DEFAULT NULL,
  `Modele` varchar(50) DEFAULT NULL,
  `Annee` int DEFAULT NULL,
  `CodeCategorie` int DEFAULT NULL,
  PRIMARY KEY (`Immatriculation`),
  KEY `CodeCategorie` (`CodeCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`Immatriculation`, `Marque`, `Modele`, `Annee`, `CodeCategorie`) VALUES
('123 AB 21', 'Mercedes', 'Spania', 2000, 1),
('234 BC 21', 'Peugeot', 'Sisancys', 1996, 1),
('345 CD 21', 'Renault', 'Morgane', 1995, 1),
('456 DE 21', 'Peugeot', 'Catsansys', 1999, 1),
('567 EF 21', 'Kawasaki', 'Zephyr', 1997, 4),
('678 FG 21', 'Renault', 'Betton', 1999, 5),
('789 GH 21', 'Iveco', 'Roader', 1998, 2),
('890 HJ 21', 'Oceansea', 'Tempest', 1999, 3);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `eleve`
--
ALTER TABLE `eleve`
  ADD CONSTRAINT `eleve_ibfk_1` FOREIGN KEY (`numCompte`) REFERENCES `compte` (`numCompte`);

--
-- Contraintes pour la table `eleve_categorie`
--
ALTER TABLE `eleve_categorie`
  ADD CONSTRAINT `eleve_categorie_ibfk_2` FOREIGN KEY (`codeCategorie`) REFERENCES `categorie` (`CodeCategorie`);

--
-- Contraintes pour la table `lecon`
--
ALTER TABLE `lecon`
  ADD CONSTRAINT `lecon_ibfk_1` FOREIGN KEY (`CodeMoniteur`) REFERENCES `moniteur` (`CodeMoniteur`),
  ADD CONSTRAINT `lecon_ibfk_3` FOREIGN KEY (`Immatriculation`) REFERENCES `vehicule` (`Immatriculation`);

--
-- Contraintes pour la table `licence`
--
ALTER TABLE `licence`
  ADD CONSTRAINT `licence_ibfk_1` FOREIGN KEY (`CodeMoniteur`) REFERENCES `moniteur` (`CodeMoniteur`),
  ADD CONSTRAINT `licence_ibfk_2` FOREIGN KEY (`CodeCategorie`) REFERENCES `categorie` (`CodeCategorie`);

--
-- Contraintes pour la table `moniteur`
--
ALTER TABLE `moniteur`
  ADD CONSTRAINT `moniteur_ibfk_1` FOREIGN KEY (`numCompte`) REFERENCES `compte` (`numCompte`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `vehicule_ibfk_1` FOREIGN KEY (`CodeCategorie`) REFERENCES `categorie` (`CodeCategorie`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
