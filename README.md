

## 📌 A propos du projet

Ce projet est développé dans le cadre du BTS SIO. Le Rebours Auto est une auto-école fictive située à Paris, où élèves et moniteurs disposent de différentes fonctionnalités et droits.

## 🛠️ Technologies utilisées

- Java
- JavaFX
- MySQL

## 🚀 Installation

### Prérequis

- Java et Git installés
  ```bash
  java -version
  git --version
  ```
- Un IDE ou compilateur (IntelliJ)
- Un serveur local (Wamp, XAMPP...)

### Étapes

1. Cloner le projet :
   ```bash
   git clone https://github.com/LauraPMS/LeReboursAuto
   ```
2. Créer une base de données `lereboursauto` sur phpMyAdmin
3. Importer `lereboursauto.sql` depuis le dossier `SQL`
4. Ouvrir le projet dans un IDE
5. Configurer l'application pour exécuter la classe `com.example.projetautoecole.HelloApplication`

## 🎯 Fonctionnalités principales

### 1️⃣ Connexion / Inscription

![Interface utilisateur](/docImg/eleveReadMe/eleveConexion.png)


- Connexion test :
  - **Élève** : `elelog` `elepass`
  - **Moniteur** : `monlog` `monpass`

### 2️⃣ Profil & Tableau de bord
![Interface utilisateur](/docImg/eleveReadMe/eleveProfil.png)

- Affichage des leçons, informations personnelles et permis souscrits.
- Modification possible des coordonnées (adresse, téléphone...)
- Possibilité de prendre une leçon en cliquant sur le bouton "prendre une leçon"

### 3️⃣ Planning
![Interface utilisateur](/docImg/eleveReadMe/elevePlaning.png)

- Élèves : affichage des leçons à venir

![Interface utilisateur](/docImg/moniteurReadMe/moniteurPlaning.png)

- Moniteurs : affichage hebdomadaire des cours

### 4️⃣ Réserver une leçon (Élève uniquement)
![Interface utilisateur](/docImg/eleveReadMe/eleveLecon.png)


- Choix de la date, de l’horaire et du moniteur
- Vérification des disponibilités

### 5️⃣ Souscription à un permis
![Interface utilisateur](/docImg/eleveReadMe/souscrirePermis.png)

- Un élève ne peut souscrire qu’à un permis à la fois

### 6️⃣ Statistiques & Gestion financière
![Interface utilisateur](/docImg/eleveReadMe/eleveReglement.png)
![Interface utilisateur](/docImg/moniteurReadMe/moniteurRglmt.png)


- Élèves : suivi des paiements
- Moniteurs : calcul automatique du salaire

## 🤝 Contribuer

1. Forker le projet
2. Créer une branche : `git checkout -b feature/NouvelleFeature`
3. Commit : `git commit -m "Ajout de NouvelleFeature"`
4. Pousser la branche : `git push origin feature/NouvelleFeature`
5. Ouvrir une pull request

## 📩 Contact

- **Laura** : [laurapms.codex@gmail.com](mailto\:laurapms.codex@gmail.com)
- **Lorenz** : [lonz25@gmail.com](mailto\:lonz25@gmail.com)

🔗 [Lien du projet](https://github.com/LauraPMS/Auto-Ecole)

