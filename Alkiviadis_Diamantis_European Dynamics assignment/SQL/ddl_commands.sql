--------------------------- DATABASE CREATION -----------------------------------
DROP SCHEMA IF EXISTS european_dynamics_assignment;

CREATE DATABASE european_dynamics_assignment;

USE  european_dynamics_assignment;


-------------------------------- USERS ---------------------------------------------


CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `avatar` varchar(80) NOT NULL,
  `username` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
);

-------------------------------- POSTS ---------------------------------------------

CREATE TABLE `posts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `user_id` int NOT NULL,
  `body` varchar(500) NOT NULL,
  `title` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_post_user`  (`user_id`),
  CONSTRAINT `FK_post_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

-------------------------------- COMMENTS ---------------------------------------------

CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `body` varchar(500) NOT NULL,
  `created_at` datetime NOT NULL,
  `post_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_comments_post` (`post_id`),
  KEY `FK_comments_user` (`user_id`),
  CONSTRAINT `FK_comments_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_comments_post` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`)
);


