use lets_play;
alter table genre_artists drop Foreign key `genre_to_composit`;
alter table genre_artists drop Foreign key`artists_to_composit`;
drop table genre_artists;
CREATE TABLE `genre_song` (
  `id_song` int(11) NOT NULL,
  `id_genre` int(11) NOT NULL,
  PRIMARY KEY (`id_song`,`id_genre`),
  KEY `from genre` (`id_genre`),
  CONSTRAINT `from genre` FOREIGN KEY (`id_genre`) REFERENCES `genre` (`id_genre`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `from song` FOREIGN KEY (`id_song`) REFERENCES `song` (`id_song`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;