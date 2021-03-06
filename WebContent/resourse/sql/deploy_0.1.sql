--<ScriptOptions statementTerminator=";"/>

ALTER TABLE  `user_play_list` DROP PRIMARY KEY;

ALTER TABLE  `delete_file` DROP PRIMARY KEY;

ALTER TABLE  `artists_description` DROP PRIMARY KEY;

ALTER TABLE  `album_description` DROP PRIMARY KEY;

ALTER TABLE  `playlist` DROP PRIMARY KEY;

ALTER TABLE  `genre` DROP PRIMARY KEY;

ALTER TABLE  `artists` DROP PRIMARY KEY;

ALTER TABLE  `user` DROP PRIMARY KEY;

ALTER TABLE  `image` DROP PRIMARY KEY;

ALTER TABLE  `rating` DROP PRIMARY KEY;

ALTER TABLE  `genre_artists` DROP PRIMARY KEY;

ALTER TABLE  `song` DROP PRIMARY KEY;

ALTER TABLE  `album` DROP PRIMARY KEY;

ALTER TABLE  `song_statistic` DROP PRIMARY KEY;

ALTER TABLE  `user_restore` DROP PRIMARY KEY;

ALTER TABLE  `user_info` DROP PRIMARY KEY;

ALTER TABLE  `genre_artists` DROP INDEX  `artists_to_composit`;

ALTER TABLE  `user` DROP INDEX  `login`;

ALTER TABLE  `user_restore` DROP INDEX  `user_to_restore`;

ALTER TABLE  `user_info` DROP INDEX  `info_to_image`;

ALTER TABLE  `album` DROP INDEX  `album_to_artists`;

ALTER TABLE  `artists` DROP INDEX  `name_artists`;

ALTER TABLE  `song_statistic` DROP INDEX  `song_to_statistic`;

ALTER TABLE  `artists_description` DROP INDEX  `description_to_image`;

ALTER TABLE  `playlist` DROP INDEX  `song_to_composit`;

ALTER TABLE  `song` DROP INDEX  `song_to_album`;

ALTER TABLE  `rating` DROP INDEX  `song_to_rating`;

ALTER TABLE  `album_description` DROP INDEX  `image_to_description`;

ALTER TABLE  `user` DROP INDEX  `email`;

ALTER TABLE  `user_play_list` DROP INDEX  `list_to_user`;

DROP TABLE  `user`;

DROP TABLE  `playlist`;

DROP TABLE  `genre_artists`;

DROP TABLE  `genre`;

DROP TABLE  `artists_description`;

DROP TABLE  `user_restore`;

DROP TABLE  `image`;

DROP TABLE  `album`;

DROP TABLE  `user_play_list`;

DROP TABLE  `delete_file`;

DROP TABLE  `artists`;

DROP TABLE  `rating`;

DROP TABLE  `song`;

DROP TABLE  `user_info`;

DROP TABLE  `song_statistic`;

DROP TABLE  `album_description`;

CREATE TABLE  `user` (
	`id_user` INT NOT NULL,
	`login` VARCHAR(128) NOT NULL,
	`password` VARCHAR(128) NOT NULL,
	`email` VARCHAR(128) NOT NULL,
	`language` VARCHAR(128) NOT NULL,
	`is_admin` BIT,
	PRIMARY KEY (`id_user`)
) ENGINE=InnoDB;

CREATE TABLE  `image` (
	`id_image` INT NOT NULL,
	`file_name` VARCHAR(128) NOT NULL,
	PRIMARY KEY (`id_image`)
) ENGINE=InnoDB;

CREATE TABLE  `playlist` (
	`id_user_play_list` INT NOT NULL,
	`id_song` INT NOT NULL,
	PRIMARY KEY (`id_user_play_list`,`id_song`)
) ENGINE=InnoDB;

CREATE TABLE  `album` (
	`id_album` INT NOT NULL,
	`name_album` VARCHAR(128) NOT NULL,
	`id_artists` INT NOT NULL,
	PRIMARY KEY (`id_album`)
) ENGINE=InnoDB;

CREATE TABLE  `genre_artists` (
	`id_genre` INT NOT NULL,
	`id_artists` INT NOT NULL,
	PRIMARY KEY (`id_genre`,`id_artists`)
) ENGINE=InnoDB;

CREATE TABLE  `genre` (
	`id_genre` INT NOT NULL,
	`name_genre` VARCHAR(128) NOT NULL,
	PRIMARY KEY (`id_genre`)
) ENGINE=InnoDB;

CREATE TABLE  .`artists_description` (
	`id_artists` INT NOT NULL,
	`title` VARCHAR(128),
	`artists_description` TEXT,
	`id_image` INT,
	PRIMARY KEY (`id_artists`)
) ENGINE=InnoDB;

CREATE TABLE  `user_restore` (
	`id_user_restore` INT NOT NULL,
	`hash` VARCHAR(128) NOT NULL,
	`date` TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
	`id_user` INT NOT NULL,
	PRIMARY KEY (`id_user_restore`)
) ENGINE=InnoDB;

CREATE TABLE  `user_play_list` (
	`id_user_play_list` INT NOT NULL,
	`name_play_list` VARCHAR(128) NOT NULL,
	`id_user` INT NOT NULL,
	PRIMARY KEY (`id_user_play_list`)
) ENGINE=InnoDB;

CREATE TABLE  `delete_file` (
	`id_delete_file` INT NOT NULL,
	`name_file` VARCHAR(128) NOT NULL,
	PRIMARY KEY (`id_delete_file`)
) ENGINE=InnoDB;

CREATE TABLE  `artists` (
	`id_artists` INT NOT NULL,
	`name_artists` VARCHAR(128) NOT NULL,
	PRIMARY KEY (`id_artists`)
) ENGINE=InnoDB;

CREATE TABLE  `rating` (
	`id_user` INT NOT NULL,
	`id_song` INT NOT NULL,
	`like` INT,
	`dislike` INT,
	PRIMARY KEY (`id_user`,`id_song`)
) ENGINE=InnoDB;

CREATE TABLE  `song` (
	`id_song` INT NOT NULL,
	`name_song` VARCHAR(128) NOT NULL,
	`id_album` INT,
	`file_song` VARCHAR(128) NOT NULL,
	PRIMARY KEY (`id_song`)
) ENGINE=InnoDB;

CREATE TABLE  `user_info` (
	`id_user` INT NOT NULL,
	`id_image_user` INT,
	`first_name` VARCHAR(128),
	`last_name` VARCHAR(128),
	`gender` VARCHAR(128),
	`country` VARCHAR(128),
	`city` VARCHAR(128),
	`description` TEXT,
	`date_of_birth` DATE,
	PRIMARY KEY (`id_user`)
) ENGINE=InnoDB;

CREATE TABLE  `song_statistic` (
	`id_user` INT NOT NULL,
	`id_song` INT NOT NULL,
	`count` INT NOT NULL,
	PRIMARY KEY (`id_user`,`id_song`)
) ENGINE=InnoDB;

CREATE TABLE  `album_description` (
	`id_album` INT NOT NULL,
	`title_album` VARCHAR(128),
	`description_album` TEXT,
	`id_image` INT,
	PRIMARY KEY (`id_album`)
) ENGINE=InnoDB;

CREATE INDEX `album_to_artists` ON  `album` (`id_artists` ASC);

CREATE UNIQUE INDEX `name_artists` ON  `artists` (`name_artists` ASC);

CREATE INDEX `song_to_statistic` ON  `song_statistic` (`id_song` ASC);

CREATE INDEX `artists_to_composit` ON  `genre_artists` (`id_artists` ASC);

CREATE INDEX `description_to_image` ON  `artists_description` (`id_image` ASC);

CREATE INDEX `song_to_composit` ON  `playlist` (`id_song` ASC);

CREATE UNIQUE INDEX `login` ON  `user` (`login` ASC);

CREATE INDEX `song_to_album` ON  `song` (`id_album` ASC);

CREATE INDEX `user_to_restore` ON  `user_restore` (`id_user` ASC);

CREATE INDEX `song_to_rating` ON  `rating` (`id_song` ASC);

CREATE INDEX `image_to_description` ON  `album_description` (`id_image` ASC);

CREATE INDEX `info_to_image` ON  `user_info` (`id_image_user` ASC);

CREATE UNIQUE INDEX `email` ON  `user` (`email` ASC);

CREATE INDEX `list_to_user` ON  `user_play_list` (`id_user` ASC);

