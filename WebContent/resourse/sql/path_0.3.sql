use `lets_play`;
set names utf8;

alter table song add COLUMN file_song varchar(128) not null;


CREATE TABLE IF NOT EXISTS `delete_file` (
`id_delete_file`INT NOT NULL AUTO_INCREMENT,
`name_file`varchar(128) not null,
PRIMARY KEY (`id_delete_file`))
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;
