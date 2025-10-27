# H&R Employee Manager

Application de gestion des employés basé sur le cours openclassroom https://openclassrooms.com/fr/courses/6900101-creez-une-application-java-avec-spring-boot

Dépôt original : https://github.com/OpenClassrooms-Student-Center/HR-Association



![alt text](logo.png "Title")

## Technologies / Pile logicielle

- Java
- SpringBoot
- Technologies web (Html/Css)

# Architecture de l'application

Le projet est construit en 2 parties sur le modèle API/Client.

## API 

Interface d'accès à la base de données H2, API basée sur le pattern MVC.

## Application web

Utilise l'interface d'accès à la base de données pour récupérer les données à afficher. 
Code organisé seon le principe du MVC.

# Objectifs

Ce projet a pour objectif de faire évoluer l'application existante en lui ajoutant certaines fonctionnalités :

- Système d'authentification JWT à l'API (sécurité)
- Hashage des mots de passes avant enregistrement en base de données (sécurité)




