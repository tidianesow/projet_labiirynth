# Projet : Résolution de Labyrinthe

## Équipe
 - Tidiane sow
 - Abdou Aziz kandji
 - Diaga Ngom


## Module
Algorithmes et Structures de données

## 1. Introduction

Le projet a pour objectif de résoudre un labyrinthe en utilisant deux algorithmes de recherche : **DFS** (Depth-First Search) et **BFS** (Breadth-First Search). Le labyrinthe sera chargé à partir d'un fichier `maze.txt`, et nous implémenterons les deux algorithmes pour trouver le chemin entre l'entrée et la sortie.

## 2. Objectifs

Les objectifs du projet sont :
- Charger un labyrinthe à partir d'un fichier `maze.txt`.
- Résoudre le labyrinthe avec les algorithmes DFS et BFS.
- Afficher le chemin trouvé pour chaque algorithme.
- (Optionnel) Créer une interface graphique avec JavaFX pour visualiser les résultats.

## 3. Arborescence du Projet
    
     bin
    │   └── src
    │       ├── MazeApp.class
    │       ├── Maze.class
    │       └── MazeSolver.class
    ├── maze.txt
    └── src
        ├── MazeApp.java
        ├── Maze.java
        ├── MazeSolver.java
        └── maze.txt


## 4. Méthodologie

1. **Chargement du labyrinthe** : Le labyrinthe est représenté par une matrice dans le fichier `maze.txt`. Chaque ligne représente une rangée du labyrinthe, et les caractères représentent les murs et les passages.
2. **Implémentation des algorithmes** :
   - **DFS** : L'algorithme explore profondément les chemins avant de revenir en arrière si nécessaire.
   - **BFS** : L'algorithme explore les chemins par niveaux, en cherchant le plus court chemin.
3. **Affichage du résultat** : Après avoir trouvé le chemin, on l'affichera dans la matrice du labyrinthe.
4. **Interface graphique** (facultatif) : Une interface graphique en JavaFX pour visualiser le labyrinthe et les résultats des algorithmes.

## 5. Résultats

Le labyrinthe est correctement chargé et les deux algorithmes résolvent le problème en affichant un chemin. Le DFS explore plus profondément tandis que le BFS garantit le chemin le plus court.

6. Exemple de fichier maze.txt

Voici un exemple de fichier maze.txt :
#####

#S==E#

#=##=#

#====#

#####

    S représente le point de départ.
    E représente la sortie.
    # représente un mur.
    = représente un passage.
soucrce ou site 

 -https://fr.wikipedia.org/wiki/Algorithme_de_parcours_en_profondeur
 -https://fr.wikipedia.org/wiki/Algorithme_de_parcours_en_largeur
 -https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
