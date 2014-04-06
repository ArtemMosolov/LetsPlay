USE lets_play;
ALTER TABLE lets_play.user_info
 DROP FOREIGN KEY info_to_image,
 DROP FOREIGN KEY info_to_user,
 CHANGE id_image_user id_image INT(11);
ALTER TABLE lets_play.user_info
 ADD CONSTRAINT info_to_image FOREIGN KEY (id_image) REFERENCES lets_play.image (id_image) ON UPDATE RESTRICT ON DELETE RESTRICT,
 ADD CONSTRAINT info_to_user FOREIGN KEY (id_user) REFERENCES lets_play.user (id_user) ON UPDATE CASCADE ON DELETE CASCADE;