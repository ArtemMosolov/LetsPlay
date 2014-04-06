use `lets_play`;
set names utf8;

DROP TABLE `playlist`;
DROP TABLE `genre_artists`;
DROP TABLE `artists_description`;
DROP TABLE `album_description`;

ALTER TABLE artists DROP FOREIGN KEY artists_to_description;
alter table artists drop column id_artist_description;
ALTER TABLE artists DROP FOREIGN KEY artists_to_image;
alter table artists drop column id_avatar_image;
alter table user_info drop FOREIGN KEY info_to_image;
ALTER TABLE user_info DROP PRIMARY KEY;
alter table user_info drop column id_info;
ALTER TABLE album DROP FOREIGN KEY album_to_artists;
ALTER TABLE album DROP FOREIGN KEY album_to_image;
ALTER TABLE album DROP FOREIGN KEY album_to_description;
alter table album drop column id_album_image;
alter table album drop column id_description;
alter table album add CONSTRAINT `album_to_artists`FOREIGN KEY (`id_artists`)REFERENCES `artists`(`id_artists`)ON UPDATE CASCADE ON DELETE CASCADE;
alter table user_info add PRIMARY KEY(id_user);
alter table user_info add CONSTRAINT `info_to_image` FOREIGN KEY (`id_image_user`)REFERENCES `image`(`id_image`);

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



