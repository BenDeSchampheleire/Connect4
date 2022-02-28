# Connect4
Conception Logicielle du jeu Puissance 4

Nous avons choisis pour ce projet de créer un jeu du Puissance 4 en réseau.

Pour le premier sprint notre objectif était de créer un jeu fonctionnel avec une interface graphique.
Nous avons créé 3 classes: Grid, Column et Checker qui représentent respectivement la grille, les colonnes et les pions.
Nous avons réalisé des tests unitaires (dans le package test) pour les deux premières classes afin de vérifier le bon comportement de nos méthodes.
Nous avons une classe Controller qui définit les différentes fenêtres graphiquement, dessine la grille et gère le déroulement de la partie.
Les classes Game.java et Test.java a été utilisée comme un premier test pour l'interface graphique mais n'est plus utilisée désormais.
Enfin la classe GameApplication sert à lancer le jeu du Puissance 4.
A l'heure actuelle le jeu fonctionne à l'exception de certaines diagonales en haut de grille, nous résolverons ce problème lors du second sprint.
Nous n'avons pas encore appliqué les principes SOLID, nous le commencerons lors du prochain sprint qui sera consacré à l'architecture client/serveur.