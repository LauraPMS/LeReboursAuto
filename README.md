
<a id="readme-top"></a>


<!-- PROJECT LOGO -->
<br />
<div align="center">

  <h3 align="center">Le Rebours Auto</h3>

  <p align="center">
    Projet 2ème année BTS SIO
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Sommaire</summary>
  <ol>
    <li>
      <a href="#about-the-project">A propos du projet</a>
      <ul>
        <li><a href="#built-with">Logiciel utilisé</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Utiliser le projet</a>
      <ul>
        <li><a href="#prerequisites">Prérequis</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Utilisation</a></li>
    <li><a href="#contributing">Contribuer</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## A propos du projet 

Ce projet a été réalisé dans le cadre de ma formaiton en BTS SIO, requis pour passer le diplome.
Le rebours auto est une auto école fictive situé sur Paris. Il y a des élèves et des moniteurs qui ont chacun des fonctionnalité et des droits.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Built With

  Ce projet a été réalisé en Java.

  <p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

* Assurez vous d'avoir java et git d'installer sur votre machine.
  ```bash
  java -version
  git -version
  ```
  Vous devriez avoir la version de java installer, sinon la commande java sera introuvable
* Avoir un IDE ou au moins un compilateur. (IntelliJ Community, CodeBlocks)
* Avoir un serveur local (tel que Wamp, Xamp qui permettra d'ipmorter la base de données)


### Installation

* Ouvrez un terminal bash et cloner le repo a l'emplacement de votre choix.
  ```bash
  cd chemin/vers/emplacement
  git clone https://github.com/LauraPMS/projetAutoEcole
  ```
* Ouvrez phpMyAdmin est créer une nouvelle base de données appelée:
  ```ssh
  autoecole
  ```
* Importer le fichier sql autoecole.sql a la racine du projet que vous trouverez dans le dossier BDD
* Vous pouvez ensuite ouvrir ce projet en tant que projet (sur votre IDE)
* créer un run application avec le chemin vers la fonction main :
  ```ssh
  sio.projetautoecole.HelloApplication
  ```
Voici le schéma MVC associés a ce projet
![Profil Moniteur](Screenshot/GetImage.png)
  
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

1. Connexion / Inscription
   ![Interface utilisateur](Screenshot/Accueil.png)
   Vous pouvez décider de vous inscrire sur la page d'inscription. Sinon je vous propose un jeu de clé pour "tester" l'application.
   Eleve :
       login & mot de passe :
   ```ssh
   testEleve
   ```
   Moniteur :
       login & mot de passe :
   ```ssh
   testMoniteur
   ```

Si vous décider de vous inscrire, cela vous renverra vers la page de connexion qi vous permettra de vérifier si l'enregistrement a été bien fait.
Une fois authentifié vous aurez accées a plusieurs fonctionnalité.

3. Profil
La Page profile joue le rôle d'un "tableau de bord". On y affiche les prochaines leçons, les permis souscrit ainsi que les informations de l'utilisateurs.
Le genre est affiché en fonction de la photo de base affiché.

Voici a quoi ressemble la partie eleve : 
   ![Profil Eleve](Screenshot/Eleve/profile.png)
   
Quand à la parte Moniteur : (en effet très similaire)
   ![Profil Moniteur](Screenshot/Moniteur/Mprofil.png)

   En appuyant sur le bouton modifier profil vous ouvrirez... la page permettant de modifier vos informations.
   Seul le téléphone, l'adresse, la ville et le code postale peuvent être modifié. Nous travaillons actuellement sur un moyen d'inclure des photos a notre application.
   Les données sont affiché dynamiquement en fonction de leur valeurs dans la base de données.
   
5. Planning
  Encore une fois, il n'ya pas de grandes différence entre la partie eleve et moniteur. Si pour l'eleve on affiche toutes ces leçons a venir, le moniteur a toute les leçons limité a la semaine, afin d'éviter d'avoir trop de leçons affiché.
 Planning d'un élève :
![Planning Eleve](Screenshot/Eleve/planning.png)
Planning Moniteur :
![Planning Moniteur](Screenshot/Moniteur/MPlanning.png)
   
6. Lecon

   Page EXCLUSIVE a l'éleve.
   Un élève peut prendre une leçon. Les crénaux sont d'une heure, rien empeche de prendre des heures consécutives.
   Veiller a bien renseigner la date, l'horraire et le type de permis afin de mettre a jour les moniteurs et véhicules disponible. de toute façon si une leçon existe deja avec ce moniteur pour une date et une horraire donnée, la leçon ne se fait pas.
   ![Profil Moniteur](Screenshot/Eleve/PrendreLecon.png)
   
7. Souscrir à un permis / Obtenir une licence
    ![Profil Moniteur](Screenshot/Moniteur/MCatalogue.png)
   Les boutons disable empêche la possession de plusieurs licence ou permis. Une seule est bien suffisante.
9. Statistiques ( Permis )
   Pour ce qui est des statistiques, L'étudiant pourra avoir affiché :
   - un
   - deux
   - trois
   - quatre

    Le moniteur pourra cependant affiché :
   - un
   - deux
   - trois
   - quatre
     
10. Réglement / Salaire
Cette page consiste a calculer le total du cout pour l'eleve de passer son permis, il aura a régler mannuellement chaque leçon.
![Profil Moniteur](Screenshot/Eleve/reglement.png)

Pour le moniteur, cette page lui sert de calculateur pour son salaire mensuel, un cumul de toutes les leçons qu'il a enseigner.
![Profil Moniteur](Screenshot/Moniteur/MRevenu.png)


## Contribuer

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
   

### Top contributors:


<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTACT -->
## Contact

Laura - laurapms.codex@gmail.com
Lorenz - lonz25@gmail.com

Project Link: [https://github.com/LauraPMS/Auto-Ecole](https://github.com/LauraPMS/Auto-Ecole)

<p align="right">(<a href="#readme-top">back to top</a>)</p>




<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

