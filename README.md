# MaVille - Gestion des Travaux à Montréal (Groupe 46)

## Description
**MaVille** est une application en ligne de commande permettant aux résidents de Montréal de consulter, signaler et soumettre des requêtes de travaux publics, tandis que les intervenants peuvent soumettre des projets de travaux et gérer leur progression.

Ce projet est réalisé dans le cadre du **Devoir 3 - Conception de la solution** du cours *IFT2255 - Génie Logiciel*, Automne 2024.
L'application utilise des **API** pour :  
- La liste des travaux en cours.  
- La liste des entraves causées par les travaux en cours.  

## Organisation du Répertoire

Ce dossier contient tout ce qui concerne le projet. Voici comment il est organisé :

**maville/**

    Rapport/ :
        - Ce dossier contient le fichier principal HTML, projet46.html.
        - Il y a un dossier images/ avec toutes les images utilisées dans le projet.
        - Une image pour le cas utilisateur 1.
        - Un diagramme UML.

    Code/ :
        - src/: les fichiers Java et des tests unitaires.
        - target/: des fichiers créés par Maven pendant la construction du code.
        - pom.xml : fichier de configuration du projet Maven.

    docs/ :
        - Contient la documentation JavaDoc générée automatiquement.

    MaVille-groupe46-3.0.jar:
        - Contient l'application Java compilée pour simuler l'appli MaVille.

## Fonctionnalités

### Résidents :
- Se connecter avec un courriel personnel et accéder à un menu contenant des options avec lesquelles le résident peut interagir.
- Consulter les travaux en cours ou à venir.
- Filtrer les travaux par quartier. (à faire)
- Filtrer les travaux par type.
- Soumettre une requête de travail.
- Suivre les requêtes de travail.
- Consulter les notifications et le nombre de nouvelles notifications. (à faire)

### Intervenants :
- Se connecter avec un courriel personnel et accéder à un menu contenant des options avec lesquelles l'intervenant peut interagir.
- Consulter la liste des requêtes de travail.
- Soumettre un projet de travaux.
- Envoyer des notifications aux résidents. (à faire)
- Modifier le statut d'un projet. (à faire)

## Prérequis
- **Java 17** ou une version plus récente.
- Bibliothèque **Gson** pour le traitement des fichiers JSON.

## Données Initiales
- 5 résidents, dont deux résident dans le même quartier.
- 5 intervenants de types variés.
- 5 requêtes de travail, dont deux avec des candidatures.
- 5 projets, dont au moins un prévu dans les 3 prochains mois. (à faire)

## Services Utilisés
### Travaux en cours :
- [**URL**](https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=cc41b532-f12d-40fb-9f55-eb58c9a2b12b) : Liste des travaux en cours.

### Entraves liées aux travaux :
- [**URL**](https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=a2bc8014-488c-495d-941b-e7ae1999d1bd) : Liste des entraves causées par les travaux en cours.
