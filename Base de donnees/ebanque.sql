-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 29 mars 2020 à 17:10
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP :  7.3.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ebanque`
--

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `code` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `loginId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`code`, `nom`, `address`, `loginId`) VALUES
(7, 'user1', 'HAY ASSAKA BLOC C NR131 TIKIOUINE AGADIR', 16),
(8, 'othman hadday', 'HAY ASSAKA BLOC C NR131 TIKIOUINE AGADIR', 18),
(9, 'medhadday', 'HAY ASSAKA BLOC C NR131 TIKIOUINE AGADIR', 19);

-- --------------------------------------------------------

--
-- Structure de la table `comptes`
--

CREATE TABLE `comptes` (
  `numCompte` int(11) NOT NULL,
  `dateCreation` date NOT NULL,
  `solde` double NOT NULL,
  `clientId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `comptes`
--

INSERT INTO `comptes` (`numCompte`, `dateCreation`, `solde`, `clientId`) VALUES
(3, '2020-03-28', 661, 8),
(4, '2020-03-28', 3721, 7),
(19, '2020-03-29', 6500, 9);

-- --------------------------------------------------------

--
-- Structure de la table `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `login`
--

INSERT INTO `login` (`id`, `username`, `password`, `isAdmin`) VALUES
(15, 'admin', 'admin', 1),
(16, 'user', '123', 0),
(18, 'oth', '123', 0),
(19, 'med', 'med', 0);

-- --------------------------------------------------------

--
-- Structure de la table `operations`
--

CREATE TABLE `operations` (
  `numOperation` int(11) NOT NULL,
  `dateOperation` date NOT NULL,
  `montant` double NOT NULL,
  `typeOperation` varchar(1) NOT NULL,
  `compteId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `operations`
--

INSERT INTO `operations` (`numOperation`, `dateOperation`, `montant`, `typeOperation`, `compteId`) VALUES
(3, '2020-03-16', 4444, 'R', 3),
(4, '2020-03-16', 4444, 'V', 4),
(5, '2020-03-18', 55, 'V', 3),
(6, '2020-03-29', 333, 'V', 3),
(7, '2020-03-29', 333, 'R', 3),
(8, '2020-03-29', 20000, 'R', 3),
(9, '2020-03-29', 333, 'V', 3),
(10, '2020-03-29', 150, 'R', 3),
(11, '2020-03-29', 1, 'R', 4),
(12, '2020-03-29', 1, 'V', 3),
(13, '2020-03-29', 23, 'R', 3),
(14, '2020-03-29', 23, 'V', 4),
(15, '2020-03-29', 2000, 'V', 19),
(16, '2020-03-29', 500, 'R', 19),
(17, '2020-03-29', 500, 'V', 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`code`),
  ADD KEY `loginId` (`loginId`);

--
-- Index pour la table `comptes`
--
ALTER TABLE `comptes`
  ADD PRIMARY KEY (`numCompte`),
  ADD KEY `clientId` (`clientId`);

--
-- Index pour la table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `operations`
--
ALTER TABLE `operations`
  ADD PRIMARY KEY (`numOperation`),
  ADD KEY `compteId` (`compteId`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `comptes`
--
ALTER TABLE `comptes`
  MODIFY `numCompte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `operations`
--
ALTER TABLE `operations`
  MODIFY `numOperation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `clients_ibfk_1` FOREIGN KEY (`loginId`) REFERENCES `login` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `comptes`
--
ALTER TABLE `comptes`
  ADD CONSTRAINT `comptes_ibfk_1` FOREIGN KEY (`clientId`) REFERENCES `clients` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `operations`
--
ALTER TABLE `operations`
  ADD CONSTRAINT `operations_ibfk_1` FOREIGN KEY (`compteId`) REFERENCES `comptes` (`numCompte`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
