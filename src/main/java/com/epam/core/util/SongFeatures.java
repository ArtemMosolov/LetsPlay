package com.epam.core.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagException;

import com.epam.controller.service.song.SongGenreService;
import com.epam.core.filesystem.DeleteFile;
import com.epam.model.bean.Genre;
import com.epam.model.bean.Song;

public class SongFeatures {
        private static SongFeatures instance;

        private List<Song> songs;
        private final String URI = "file:/";
        private final String relativeURI = "file:/D:/FILESYSTEM";
        private File songFile;
        private SongHeaderUtil songHeaderUtil;
        private HashMap<Song, List<String>> invalidModels = new HashMap<Song, List<String>>();
        private String songGenre;
        private String songName;
        private List<Song> invalidSongs;
        private DeleteFile deleteFile;
        private Map<String, Integer> genres = SongGenreService
                        .getSongGenreService().getAllGenres();

        private SongFeatures() {

        }

        public static SongFeatures getSongFeatures() {
                if (instance == null) {
                        instance = new SongFeatures();
                }
                return instance;
        }

        private String getRelativePath(String path) {
                return path.replace(relativeURI, "");

        }

        private String getAbsolutePath(String path) {
                return path.replace(URI, "");

        }

        private String setAbsolutePath(String path) {
                return relativeURI.concat(path);
        }

        public List<Song> validateSongParameters(Map<String, String[]> parameters) {
                songs = new ArrayList<>();
                deleteFile = new DeleteFile();
                for (Song song : invalidSongs) {
                        if (parameters.containsKey(song.getNameFile())) {
                                String[] params = parameters.get(song.getNameFile());
                                Genre genre = new Genre();
                                genre.setIdGenre(genres.get(params[1]));
                                genre.setNameGenre(params[1]);
                                song.setGenre(genre);
                                if (!Validator.EMPTY_FIELD.validate(params[0])) {
                                        song.setNameSong(params[0]);
                                        setParams(song);
                                        song.setNameFile(getRelativePath(song.getNameFile()));
                                        songs.add(song);
                                } else {
                                        try {
                                                deleteFile.delete(song.getNameFile());
                                        } catch (FileNotFoundException | SecurityException e) {
                                                e.printStackTrace();
                                        }
                                }
                        }
                }
                return songs;

        }

        public void setParams(Song song) {
                File file = new File(getAbsolutePath(song.getNameFile()));
                try {
                        songHeaderUtil = new SongHeaderUtil(file);
                        songHeaderUtil.setSongName(song.getNameSong());
                        songHeaderUtil.setSongGenre(song.getGenre().getNameGenre());

                } catch (CannotReadException | IOException | TagException
                                | ReadOnlyFileException | InvalidAudioFrameException
                                | KeyNotFoundException | CannotWriteException e) {
                        e.printStackTrace();
                }
        }

        public List<Song> getInvalidSongs() {
                return invalidSongs;
        }

        public List<Song> setAllSongModels(List<File> files, String artistName,
                        String albumName, int idAlbum, int idArtist) {
                songs = new ArrayList<>();
                invalidSongs = new ArrayList<>();
                for (File file : files) {
                        Song song = new Song();
                        song = setModelParameters(file.getAbsolutePath(), artistName,
                                        albumName, idAlbum, idArtist);
                        if (song != null) {
                                songs.add(song);
                        }
                }
                return songs;
        }

        public Song setSongModel(String artistName, String albumName,
                        String relativePath, int idAlbum, int idArtist) {
                invalidSongs = new ArrayList<>();
                String absolutePath = getAbsolutePath(setAbsolutePath(relativePath));
                Song song = new Song();
                song = setModelParameters(absolutePath, artistName, albumName, idAlbum,
                                idArtist);
                return song;
        }

        private Song setModelParameters(String path, String artistName,
                        String albumName, int idAlbum, int idArtist) {
                Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);

                songFile = new File(path);
                Song song = new Song();
                try {
                        songHeaderUtil = new SongHeaderUtil(songFile);
                        songHeaderUtil.setSongArtistName(artistName);
                        songHeaderUtil.setSongAlbumName(albumName);
                        songName = songHeaderUtil.getSongName();
                        songGenre = songHeaderUtil.getSongGenre();

                } catch (CannotReadException | IOException | TagException
                                | ReadOnlyFileException | InvalidAudioFrameException
                                | KeyNotFoundException | CannotWriteException e) {
                        e.printStackTrace();
                }
                song.setIdAlbum(idAlbum);
                song.setNameFile(songFile.toURI().toString());
                song.setNameAlbum(albumName);
                song.setNameArtists(artistName);
                song.setNameSong(songName);
                if (song.getIdSong() == null) {
                        song.setIdSong(0);
                }
                if (!genres.containsKey(songGenre)) {
                        invalidSongs.add(song);
                        return null;
                } else {
                        Genre genre = new Genre();
                        genre.setIdGenre(genres.get(songGenre));
                        genre.setNameGenre(songGenre);
                        song.setGenre(genre);
                        song.getGenre().setNameGenre(songGenre);
                }
                return song;
        }

        public HashMap<Song, List<String>> getInvalidModels() {
                return invalidModels;
        }

}