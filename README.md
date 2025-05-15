# Un projet de Java en utilisant swing

## Objectif

Le but de ce projet est de créer un jeux Numeri qui est capable de accueillir plusieurs joueur dans le même temps et de
interagir avec l'interface graphique. Pour chaque partie, chaque joueur possède 6 pions, affectés d’un coefficient de 1
à 6. Lorsque un joueur a obtenu une valeur x par le lancement du dé, il peut avancer un ou plusieurs de ses pions dont
la somme des coefficients est égale à x au prochaine case non occupée.

## Fonctionnalités réalisées

- Mise à jour d'interface en temps réel
- Animation de transition
- Choix de niveau de difficulté
- Prise en charge de plusieurs joueurs
- Classement dew scores des joueurs
- Événements aléatoires pour certaines cases

## Technologies et outils utilisées

- Java
- Swing
- IntelliJ
- Visual Studio Code

## Structure du projet

Pour notre projet, nous avons utilisé la structure MVC(Modèle-Vue-Controleur) pour simplifier le développement et
augmenter la lisibilité du code. Le détail de chaque partie de MVC est représenté comme ci-dessous:

> ### Partie modèle

```Ici, on défini les modèle pour la manipulation des données, la vérification des règles etc.```

> ### Partie vue

```Ici, on défini les interfaces graphiques liées aux donnés du modèle, elles peuvent être mise à jour par le controlleur.```

> ### Partie Controleur

```Ici, on défini la logique du programme, on collecte les données des joueurs et les transmet au modèle.```

## Tableau des classes utilisées

| Nom       | Rôle       | Description                         |
|-----------|------------|-------------------------------------|
| Jeux      | Main       | L'entrée du programme               |

| Nom           | Rôle       | Description                                                  |
|---------------|------------|--------------------------------------------------------------|
| VuePlateau    | Vue        | L'interface du plateau                                       |
| VueAccueil    | Vue        | L'interface d'accueil                                        |
| VueDialogue   | Vue        | La fênetre de dialogue personnalisée                         |
| VueInterm     | Vue        | L'interface intermédiaire pour collecter les infos de joueur |
| VueJeu        | Vue        | L'interface principale du jeu                                |
| VueMenu       | Vue        | L'interface de menu                                          |
| VueParam      | Vue        | L'interface du paramètre                                     |
| VuePlateau    | Vue        | L'interface de plateau contenant toute les case              |

| Nom           | Rôle       | Description                |
|---------------|------------|----------------------------|
| ModeleJoueur  | Modèle     | Les paramètres du joueur   |
| ModeleCase    | Modèle     | La paramètres de case      |
| ModeleDe      | Modèle     | Le gestionnaire de dé      |
| ModeleEvent   | Modèle     | Les événement de clique    |
| ModeleJoueur  | Modèle     | Les paramètres des joueurs |
| ModeleParam   | Modèle     | Les paramètres du jeu      |
| ModelePion    | Modèle     | Les paramètres du pion     |
| ModelePlateau | Modèle     | Les paramètres du plateau  |

| Nom           | Rôle       | Description            |
|---------------|------------|------------------------|
| ControleurJeu | Controleur | Le gestionnaire du jeu |


## Usage

1. Ouvrir le dossier de projet dans IntelliJ ou VS Code.
2. Exécuter la méthode de main qui se trouve dans la classe Jeux.