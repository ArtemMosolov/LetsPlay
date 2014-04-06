ALTER TABLE `lets_play`.`user_info` 
CHANGE COLUMN `desription` `description` TEXT NULL DEFAULT NULL ;

ALTER TABLE `lets_play`.`user_info` 
CHANGE COLUMN `gender` `gender` VARCHAR(128) NULL ;

ALTER TABLE `lets_play`.`user_info` 
CHANGE COLUMN `date_of_birth` `date_of_birth` DATE NULL ;

ALTER TABLE `lets_play`.`user_info` 
CHANGE COLUMN `country` `country` VARCHAR(128) NULL ,
CHANGE COLUMN `city` `city` VARCHAR(128) NULL ;
