drop database `lets_play`;
CREATE DATABASE `lets_play` CHARACTER SET utf8 COLLATE utf8_general_ci;
use `lets_play`;
set names utf8;

CREATE TABLE IF NOT EXISTS `user` (
`id_user` INT NOT NULL AUTO_INCREMENT, 
`login` varchar(128) not null, 
`password` varchar(128) not null, 
`email` varchar(128) not null, 
`language` varchar(128) not null, 
PRIMARY KEY (`id_user`),
UNIQUE(`login`))
ENGINE = InnoDB 
DEFAULT CHARACTER SET =utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `image` (
`id_image` INT NOT NULL AUTO_INCREMENT,
`file_name` varchar(128) not null,
PRIMARY KEY (`id_image`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `artists` (
`id_artists`	INT NOT NULL AUTO_INCREMENT,
`name_artists`	varchar(128) not null,
PRIMARY KEY (`id_artists`),
UNIQUE(`name_artists`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `artists_description` (
`id_artists` INT NOT NULL,
`title`	varchar(128) null,
`artists_description` text null,
`id_image` INT null,
PRIMARY KEY (`id_artists`),
CONSTRAINT `description_to_artists`FOREIGN KEY (`id_artists`)REFERENCES `artists`(`id_artists`)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT `description_to_image`FOREIGN KEY (`id_image`)REFERENCES `image`(`id_image`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `genre` (
`id_genre` INT NOT NULL AUTO_INCREMENT,
`name_genre`varchar(128) not null,
PRIMARY KEY (`id_genre`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `user_info` (
`id_user` int not null,
`id_image_user` int null,
`first_name` varchar(128) null,
`last_name` varchar(128) null,
`gender` varchar(128) null,
`country` varchar(128) null,
`city` varchar(128) null,
`desription` text null,
`date_of_birth` date null,
PRIMARY KEY (`id_user`),
CONSTRAINT `info_to_user`FOREIGN KEY (`id_user`)REFERENCES `user`(`id_user`)
on update cascade
ON DELETE CASCADE,
CONSTRAINT `info_to_image`FOREIGN KEY (`id_image_user`)REFERENCES `image`(`id_image`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;


CREATE TABLE IF NOT EXISTS `genre_artists` (
`id_genre` int not null,
`id_artists` int not null,
CONSTRAINT `id_genre_artists` PRIMARY KEY (`id_genre`,`id_artists`),
CONSTRAINT `genre_to_composit`FOREIGN KEY (`id_genre`)REFERENCES `genre`(`id_genre`)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT `artists_to_composit`FOREIGN KEY (`id_artists`)REFERENCES `artists`(`id_artists`)
ON UPDATE CASCADE
ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `album` (
`id_album`	INT NOT NULL AUTO_INCREMENT,
`name_album`	varchar(128) not null,
`id_artists` int not null,
PRIMARY KEY (`id_album`),
CONSTRAINT `album_to_artists`FOREIGN KEY (`id_artists`)REFERENCES `artists`(`id_artists`)
ON UPDATE CASCADE
ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `album_description` (
`id_album`	INT NOT NULl,
`title_album`	varchar(128) null,
`description_album` text null,
`id_image` int null,
PRIMARY KEY (`id_album`),
CONSTRAINT `album_to_description`FOREIGN KEY (`id_album`)REFERENCES `album`(`id_album`)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT `image_to_description`FOREIGN KEY (`id_image`)REFERENCES `image`(`id_image`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `song` (
`id_song`INT NOT NULL AUTO_INCREMENT,
`name_song`varchar(128) not null,
`id_album` int not null,
PRIMARY KEY (`id_song`),
CONSTRAINT `song_to_album`FOREIGN KEY (`id_album`)REFERENCES `album`(`id_album`)
ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `user_play_list` (
`id_user_play_list`INT NOT NULL AUTO_INCREMENT,
`name_play_list` varchar(128) not null,
`id_user` int not null,
PRIMARY KEY (`id_user_play_list`),
CONSTRAINT `list_to_user`FOREIGN KEY (`id_user`)REFERENCES `user`(`id_user`)
ON UPDATE CASCADE
ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `playlist` (
`id_user_play_list` int not null,
`id_song` int not null,
CONSTRAINT `id_playlist` PRIMARY KEY (`id_user_play_list`,`id_song`),
CONSTRAINT `song_to_composit`FOREIGN KEY (`id_song`)REFERENCES `song`(`id_song`)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT `list_to_composit`FOREIGN KEY (`id_user_play_list`)REFERENCES `user_play_list`(`id_user_play_list`)
ON UPDATE CASCADE
ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `song_statistic` (
`id_user` int not null,
`id_song` int not null,
`count` int not null,
CONSTRAINT `id_song_statistic` PRIMARY KEY (`id_user`,`id_song`),
CONSTRAINT `song_to_statistic`FOREIGN KEY (`id_song`)REFERENCES `song`(`id_song`)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT `user_to_statistic`FOREIGN KEY (`id_user`)REFERENCES `user`(`id_user`)
ON UPDATE CASCADE
ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `rating` (
`id_user` int not null,
`id_song` int not null,
`like` int null,
`dislike` int null,
CONSTRAINT `id_rating` PRIMARY KEY (`id_user`,`id_song`),
CONSTRAINT `song_to_rating`FOREIGN KEY (`id_song`)REFERENCES `song`(`id_song`)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT `user_to_rating`FOREIGN KEY (`id_user`)REFERENCES `user`(`id_user`)
ON UPDATE CASCADE
ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `user_restore` (
`id_user_restore` INT NOT NULL AUTO_INCREMENT, 
`hash` varchar(128) not null, 
`date_time` TIMESTAMP NOT NULL,
`id_user` int not null, 
PRIMARY KEY (`id_user_restore`), 
CONSTRAINT `user_to_restore` FOREIGN KEY (`id_user`) REFERENCES `user`(`id_user`)
ON UPDATE CASCADE
ON DELETE CASCADE)
ENGINE = InnoDB 
DEFAULT CHARACTER SET =utf8 COLLATE = utf8_general_ci;
