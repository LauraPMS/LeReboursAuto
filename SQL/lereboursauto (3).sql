-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 16 mars 2025 à 13:26
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `lecon`
--

INSERT INTO `lecon` (`id`, `date`, `heure`, `immatriculation`, `reglee`, `idEleve`, `idMoniteur`, `idPermis`) VALUES
(1, '2025-03-17', '08:00:00', '1001', 0, 1, 2, 1),
(2, '2025-03-19', '10:30:00', '1002', 0, 1, 2, 1),
(3, '2025-03-22', '14:00:00', '1003', 0, 1, 2, 1),
(4, '2025-03-24', '16:00:00', '1001', 0, 1, 2, 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `licence`
--

INSERT INTO `licence` (`id`, `idUser`, `codeCategorie`, `dateObtention`) VALUES
(1, 1, 1, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`code`, `nom`, `prenom`, `dateNaissance`, `telephone`, `sexe`, `codePostal`, `login`, `mdp`, `idStatut`, `adresse`, `ville`) VALUES
(1, 'Jean', 'Jacques', '2001-03-08', '0698765432', 0, 92000, 'elelog', 'elepass', 1, 'toto', 'titi'),
(2, 'Radhan', 'Prime', '2001-01-02', '0123456789', 1, 92000, 'monlog', 'monpass', 2, 'Chateau de caelid', 'Caelid'),
(3, 'Marika', 'Radagon', '2001-03-08', '0123456789', 0, 92000, 'mrAdmin', 'pass', 3, 'L\'arbre monde', 'Entre-Terre');

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
-- Contraintes pour la table `lecon`
--
ALTER TABLE `lecon`
  ADD CONSTRAINT `fk_lecon_permis` FOREIGN KEY (`idPermis`) REFERENCES `permis` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_lecon_utilisateur` FOREIGN KEY (`idEleve`) REFERENCES `utilisateur` (`code`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_lecon_utilisateur_moniteur` FOREIGN KEY (`idMoniteur`) REFERENCES `utilisateur` (`code`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lecon_ibfk_1` FOREIGN KEY (`immatriculation`) REFERENCES `vehicule` (`immatriculation`);

--
-- Contraintes pour la table `licence`
--
ALTER TABLE `licence`
  ADD CONSTRAINT `fk_licence_permis` FOREIGN KEY (`codeCategorie`) REFERENCES `permis` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_licence_utilisateur` FOREIGN KEY (`idUser`) REFERENCES `utilisateur` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;

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
