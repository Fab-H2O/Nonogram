# Nonogram

## 🔍 Présentation

**GRETA** (Groupement d'Établissements de Formation-Continue) présente ce projet de développement Web et Desktop.

Il s'agit d'une application complète de jeu de **Nonogram** (aussi appelé Picross), développée dans le cadre d'une formation au GRETA. Le projet est conçu comme un support pédagogique pour apprendre aux étudiants à travailler sur un projet de développement logiciel complet.

> ⚠️ **Statut du projet** : Ce projet est marqué comme **abandonné**. Il a été réalisé à des fins pédagogiques et n'est pas destiné à un usage en production.

## ✨ Fonctionnalités

### 🔐 Gestion des utilisateurs
- Création de compte (nom d'utilisateur et mot de passe)
- Compte administrateur dédié
- Connexion et déconnexion
- Historique de la dernière date de connexion

### 🧩 Jeu de Nonogram
- Génération automatique de puzzles
- Création de puzzles personnalisés
- Attribution d'un identifiant unique à chaque puzzle
- Attribution du nom du créateur de chaque puzzle

### 🏆 Classement
- Système de score
- Classement général (leaderboard)
- Historique des meilleurs scores par puzzle

## 🛠️ Technologies utilisées

### Back-end
- **PHP** (MAMP)
- **MySQL** (InnoDB)
- **phpMyAdmin** pour la gestion de la base de données

### Front-end
- **Application légère** : HTML + CSS + JavaScript
- **Application lourde** : Java

### Base de données

#### Table `player`

| Colonne       | Type        | Description                             |
|---------------|-------------|-----------------------------------------|
| `id`          | INT         | Identifiant unique (auto-incrément)     |
| `player_name` | VARCHAR(32) | Nom d'utilisateur                       |
| `player_pwd`  | VARCHAR(255) | Mot de passe                           |
| `isAdmin`     | BOOLEAN     | Statut administrateur                   |
| `lastLog`     | DATE        | Dernière connexion                      |
| `sign_out`    | BOOLEAN     | Statut de déconnexion                   |

#### Table `puzzle`

| Colonne   | Type   | Description                                  |
|-----------|--------|----------------------------------------------|
| `id`      | INT    | Identifiant unique (auto-incrément)          |
| `matrice` | TEXT   | Matrice du puzzle                            |
| `creator` | INT    | Identifiant du créateur (FK → `player.id`)   |

#### Table `score`

| Colonne  | Type | Description                                  |
|----------|------|----------------------------------------------|
| `id`     | INT  | Identifiant unique (auto-incrément)          |
| `score`  | INT  | Score obtenu                                 |
| `player` | INT  | Identifiant du joueur (FK → `player.id`)     |
| `puzzle` | INT  | Identifiant du puzzle (FK → `puzzle.id`)     |

## 📦 Structure du projet

Le projet est divisé en trois composants principaux :
- **Application Java** : version desktop lourde du jeu
- **Application Web** : version légère HTML/CSS/JS accessible via navigateur
- **Back-end PHP** : serveur et API gérant la logique métier et la base de données MySQL

## 🎯 Objectif pédagogique

Ce projet a été conçu pour former les étudiants à la gestion complète d'un projet de développement :
- Conception de la base de données
- Développement d'une application desktop
- Développement d'une application web
- Intégration back-end / front-end
- Travail en équipe (groupe de 3 personnes)

## 📜 Licence

Projet réalisé à des fins pédagogiques dans le cadre d'une formation au **GRETA**.
