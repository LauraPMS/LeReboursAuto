-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 22 mars 2025 à 01:14
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
-- Base de données : `lereboursauto`
--

-- --------------------------------------------------------

--
-- Structure de la table `lecon`
--

DROP TABLE IF EXISTS `lecon`;
CREATE TABLE IF NOT EXISTS `lecon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `heure` text NOT NULL,
  `immatriculation` varchar(20) NOT NULL,
  `reglee` int NOT NULL,
  `idEleve` int NOT NULL,
  `idMoniteur` int NOT NULL,
  `idPermis` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lecon_utilisateur` (`idEleve`),
  KEY `fk_lecon_utilisateur_moniteur` (`idMoniteur`),
  KEY `fk_lecon_permis` (`idPermis`),
  KEY `immatriculation` (`immatriculation`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `lecon`
--

INSERT INTO `lecon` (`id`, `date`, `heure`, `immatriculation`, `reglee`, `idEleve`, `idMoniteur`, `idPermis`) VALUES
(1, '2025-03-17', '08:00:00', '1001', 1, 1, 2, 1),
(2, '2025-03-19', '10:30:00', '1002', 1, 1, 2, 1),
(3, '2025-03-22', '14:00:00', '1003', 1, 1, 2, 1),
(4, '2025-03-24', '16:00:00', '1001', 1, 1, 2, 1),
(6, '2025-03-27', '10:05:00', '2002', 0, 1, 2, 2),
(7, '2025-03-21', '10:00:00', '2001', 1, 1, 2, 2),
(8, '2025-04-29', '15:45:00', '2002', 1, 1, 2, 2),
(9, '2025-03-16', '10:30', '1001', 1, 1, 2, 1),
(10, '2025-03-16', '14:00', '2002', 0, 13, 2, 2),
(11, '2025-03-17', '09:00', '1003', 0, 14, 2, 1),
(12, '2025-03-17', '11:30', '2001', 0, 15, 2, 2),
(13, '2025-03-18', '08:45', '1002', 0, 16, 2, 1),
(14, '2025-03-18', '13:15', '1003', 0, 17, 2, 1),
(15, '2025-03-19', '15:00', '2001', 0, 13, 2, 2),
(16, '2025-03-19', '10:00', '1002', 0, 14, 2, 1),
(17, '2025-03-20', '09:30', '2003', 0, 15, 2, 2),
(18, '2025-03-20', '12:00', '1001', 0, 13, 2, 1),
(19, '2025-03-21', '14:00:00', '1002', 0, 1, 2, 1),
(20, '2025-03-22', '12:00:00', '1003', 0, 1, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `licence`
--

DROP TABLE IF EXISTS `licence`;
CREATE TABLE IF NOT EXISTS `licence` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idUser` int NOT NULL,
  `codeCategorie` int NOT NULL,
  `dateObtention` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_licence_permis` (`codeCategorie`),
  KEY `fk_licence_utilisateur` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `licence`
--

INSERT INTO `licence` (`id`, `idUser`, `codeCategorie`, `dateObtention`) VALUES
(1, 1, 1, NULL),
(2, 1, 2, NULL),
(3, 2, 1, NULL),
(4, 2, 2, NULL),
(6, 1, 3, NULL),
(7, 1, 5, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `permis`
--

DROP TABLE IF EXISTS `permis`;
CREATE TABLE IF NOT EXISTS `permis` (
  `id` int NOT NULL AUTO_INCREMENT,
  `libelle` text NOT NULL,
  `prix` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `permis`
--

INSERT INTO `permis` (`id`, `libelle`, `prix`) VALUES
(1, 'Automobile', 45),
(2, 'Moto', 30),
(3, 'Poids Lourd', 50),
(4, 'Transport public', 35),
(5, 'Bateau', 55);

-- --------------------------------------------------------

--
-- Structure de la table `statut`
--

DROP TABLE IF EXISTS `statut`;
CREATE TABLE IF NOT EXISTS `statut` (
  `id` int NOT NULL AUTO_INCREMENT,
  `libelle` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `statut`
--

INSERT INTO `statut` (`id`, `libelle`) VALUES
(1, 'Eleve'),
(2, 'Moniteur'),
(3, 'Admin');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `code` int NOT NULL AUTO_INCREMENT,
  `nom` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prenom` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dateNaissance` date NOT NULL,
  `telephone` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sexe` smallint NOT NULL,
  `codePostal` int NOT NULL,
  `login` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mdp` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `idStatut` int NOT NULL,
  `adresse` text NOT NULL,
  `ville` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`code`),
  KEY `idx_idStatut` (`idStatut`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`code`, `nom`, `prenom`, `dateNaissance`, `telephone`, `sexe`, `codePostal`, `login`, `mdp`, `idStatut`, `adresse`, `ville`) VALUES
(1, 'Jean', 'Jacques', '2001-03-08', '0698765432', 0, 92000, 'elelog', 'elepass', 1, '6 allée ', 'Paris'),
(2, 'Radhan', 'Prime', '2001-01-02', '0123456789', 1, 92000, 'monlog', 'monpass', 2, 'Chateau de caelid', 'Caelid'),
(3, 'Marika', 'Radagon', '2001-03-08', '0123456789', 0, 92000, 'mrAdmin', 'pass', 3, 'L\'arbre monde', 'Entre-Terre'),
(6, 'Durand', 'Jean', '1980-05-12', '0601020304', 1, 75001, 'jdurand', 'mdp123', 2, '12 Rue Lafayette', 'Paris'),
(7, 'Lemoine', 'Claire', '1985-08-25', '0612345678', 0, 69002, 'clemoine', 'mdp123', 2, '5 Place Bellecour', 'Lyon'),
(8, 'Martin', 'Luc', '1992-02-10', '0623456789', 1, 31000, 'lmartin', 'mdp123', 2, '24 Avenue Alsace', 'Toulouse'),
(9, 'Dubois', 'Sophie', '1988-11-17', '0634567890', 0, 13001, 'sdubois', 'mdp123', 2, '8 Rue de Rome', 'Marseille'),
(10, 'Bernard', 'Antoine', '1979-07-30', '0645678901', 1, 59000, 'abernard', 'mdp123', 2, '15 Rue Nationale', 'Lille'),
(11, 'Rousseau', 'Elodie', '1995-04-05', '0656789012', 0, 44000, 'erousseau', 'mdp123', 2, '10 Quai de la Fosse', 'Nantes'),
(12, 'Morel', 'Thomas', '1990-09-21', '0667890123', 1, 67000, 'tmorel', 'mdp123', 2, '3 Rue des Grandes Arcades', 'Strasbourg'),
(13, 'Leroy', 'Emma', '2005-03-15', '0678901234', 0, 75015, 'eleroy', 'mdp123', 1, '25 Rue Vaugirard', 'Paris'),
(14, 'Petit', 'Lucas', '2004-07-08', '0689012345', 1, 69003, 'lpetit', 'mdp123', 1, '10 Rue de la Part-Dieu', 'Lyon'),
(15, 'Garcia', 'Sophie', '2006-11-22', '0690123456', 0, 33000, 'sgarcia', 'mdp123', 1, '18 Rue Sainte-Catherine', 'Bordeaux'),
(16, 'Moreau', 'Ethan', '2005-09-30', '0601234567', 1, 13006, 'emoreau', 'mdp123', 1, '5 Avenue du Prado', 'Marseille'),
(17, 'Robin', 'Chloé', '2003-05-18', '0612345678', 0, 59000, 'crobin', 'mdp123', 1, '8 Rue Masséna', 'Lille');

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `immatriculation` varchar(20) NOT NULL,
  `annee` text NOT NULL,
  `codePermis` int NOT NULL,
  `marque` text NOT NULL,
  `modele` text NOT NULL,
  PRIMARY KEY (`immatriculation`),
  KEY `fk_vehicule_permis` (`codePermis`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`immatriculation`, `annee`, `codePermis`, `marque`, `modele`) VALUES
('1001', '2020', 1, 'Peugeot', '208'),
('1002', '2018', 1, 'Renault', 'Clio'),
('1003', '2022', 1, 'Volkswagen', 'Golf'),
('2001', '2019', 2, 'Yamaha', 'MT-07'),
('2002', '2021', 2, 'Honda', 'CB500F'),
('2003', '2017', 2, 'Kawasaki', 'Z650'),
('3001', '2015', 3, 'Mercedes', 'Actros'),
('3002', '2018', 3, 'Volvo', 'FH16'),
('3003', '2020', 3, 'Scania', 'R450'),
('4001', '2016', 4, 'Iveco', 'Urbanway'),
('4002', '2019', 4, 'Mercedes', 'Citaro'),
('4003', '2021', 4, 'MAN', 'Lion’s City'),
('5001', '2012', 5, 'Beneteau', 'Antares 8'),
('5002', '2015', 5, 'Jeanneau', 'Merry Fisher 895'),
('5003', '2018', 5, 'Bayliner', 'Element E16');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `fk_utilisateur_statut` FOREIGN KEY (`idStatut`) REFERENCES `statut` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `fk_vehicule_permis` FOREIGN KEY (`codePermis`) REFERENCES `permis` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
