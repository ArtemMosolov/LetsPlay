use `lets_play`;
set names utf8;
alter table artists MODIFY id_artist_description int null;
alter table artists add COLUMN id_avatar_image int null;
alter table artists add CONSTRAINT artists_to_image FOREIGN key (id_avatar_image) REFERENCES image (id_image) on delete cascade on update cascade;
alter table user_info MODIFY id_image_user int null;
alter table album MODIFY id_album_image int null,
MODIFY id_description int null;
alter table user_info MODIFY id_image_user int null;

CREATE TABLE IF NOT EXISTS `album_image_artist` (
`id_album_image_artist`	INT NOT NULL AUTO_INCREMENT,
`name_album_image`varchar(128) not null,
`id_artists` int not null,
`id_image_avatar` int null,
PRIMARY KEY (`id_album_image_artist`),
CONSTRAINT `albumImage_to_artists`FOREIGN KEY (`id_artists`)REFERENCES `artists`(`id_artists`)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT `albumImage_to_image`FOREIGN KEY (`id_image_avatar`)REFERENCES `image`(`id_image`)
ON UPDATE CASCADE
ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `album_image` (
`id_album_image_artist` int not null,
`id_image` int not null,
CONSTRAINT `id_album_image` PRIMARY KEY (`id_album_image_artist`,`id_image`),
CONSTRAINT `album_image_to_image`FOREIGN KEY (`id_image`)REFERENCES `image`(`id_image`)
ON UPDATE CASCADE
ON DELETE CASCADE,
CONSTRAINT `album_image_to_albumImage`FOREIGN KEY (`id_album_image_artist`)REFERENCES `album_image_artist`(`id_album_image_artist`)
ON UPDATE CASCADE
ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;
