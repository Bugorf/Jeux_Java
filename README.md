# Un projet de Java en utilisant swing

## Objectif
Le but de ce projet est de créer un jeux Numeri qui est capable de accueillir plusieurs joueur dans le même temps et de interagir avec l'interface graphique. Pour chaque partie, chaque joueur possède 6 pions, affectés d’un coefficient de 1 à 6. Lorsque un joueur a obtenu une valeur x par le lancement du dé, il peut avancer un ou plusieurs de ses pions dont la somme des coefficients est égale à x au prochaine case non occupée. 

## Fonctionnalités réalisées
- Mise à jour d'interface en temps réel
- Animation de transition
- Prise en charge de jouer sous le réseau local
- Consult de l'historique du jeux
- Classement global des scores des joueurs
- Enregistrement du match
- Profil du joueur
- Communication entre joueur (par emoji ou texte pré-défini)
- Liberté de choisir l'avatar et l'icon de pion
- Système du compteur (ex: 30s pour chaque tour)
- Evénements aléatoires pour certaines cases
- Support de musique(bruitage) en plan arrière
- Mode local et mode en ligne

## Technologies utilisées
- Java
- Swing
- UDP

## Structure du fichier
Pour notre projet, nous avons utilisé la structure MVC(Modèle-Vue-Controleur) pour simplifier le développement et augmenter la lisibilité du code. Le détail de chaque partie de MVC est représenté comme ci-dessous:

> ### Partie modèle

```Ici, on défini les modèle pour la manipulation des données, la vérification des règles etc.```

> ### Partie vue

```Ici, on défini les interfaces graphiques liées aux donnés du modèle, elles peuvent être mise à jour par le controlleur.```

> ### Partie Controleur

```Ici, on défini la logique du programme, on collecte les données des joueurs et les transmet au modèle.```

> ### Arbre des fichiers



## Tableau des classes utilisées
|Nom                |Appartenance   |Description    |
|---------------    |---------------|---------------|
|Jeu                |Main           |L'entrée du programme|
|                   |               ||
|VueMenu            |Vue            ||
|VueJoueur          |Vue            ||
|VuePlateau         |Vue            ||
|                   |               ||
|JeuControleur      |Controleur     |Logique du jeu|
|PionControleur     |Controleur     |Logique du pion|
|MenuControleur     |Controleur     |Logique du menu|
|                   |               ||
|DonneeJoueur       |Modèle         ||
|DonneePlateau      |Modèle         ||
|Pion               |Modèle         || 
|Case               |Modèle         ||
|CaseSpe            |Modèle         ||
|De                 |Modèle         ||
|Evenement          |Modèle         ||
|EvenementSpe       |Modèle         ||
|Partie             |Modèle         ||

>> Fonctionnalités supplémentaires

|Nom                |Appartenance   |Description    |
|---------------    |---------------|---------------|
|JeuLAN             |❌              |Socket|
|Animation          |❌              ||
|Bruitage           |❌              ||
|MenuAccueil        |❌              ||
|ProfilJoueur       |❌              ||
|Historique         |❌              ||
|ComJoueur          |❌              |La communication|
|Prefs              |❌              |La personnalisation|
|Timer              |❌              ||

## Le graphe du relation entre classe


## Usage


À compléter...