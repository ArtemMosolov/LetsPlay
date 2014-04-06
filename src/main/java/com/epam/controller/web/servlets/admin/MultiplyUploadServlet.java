package com.epam.controller.web.servlets.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.controller.service.album.AlbumService;
import com.epam.controller.service.artist.ArtistService;
import com.epam.controller.service.song.SongGenreService;
import com.epam.controller.service.song.SongService;
import com.epam.core.PageBilder;
import com.epam.core.factory.FactoryFolder;
import com.epam.core.filesystem.FileUploadCustom;
import com.epam.core.filesystem.ParserRequest;
import com.epam.core.util.SongFeatures;
import com.epam.model.bean.Album;
import com.epam.model.bean.Artist;
import com.epam.model.bean.Song;

public class MultiplyUploadServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private final String BROWSER = "showArtists.jsp";
        private final String UPLOAD_SONGS = "uploadMusic.jsp";
        private final String EDIT_SONGS = "editMusic.jsp";
        private Album album;
        private Artist artist;
        private FileUploadCustom fileUploadCustom;
        private SongFeatures features;

        private ArrayList<Song> songList;

        protected void doGet(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
                AlbumService albumService = new AlbumService();
                ArtistService artistService = new ArtistService();
                int artistId;
                int albumId;
                String itemsToCreate=request.getParameter("items");
                request.setAttribute("items", Integer.parseInt(itemsToCreate));
                try {
                        artistId = Integer.parseInt(request.getParameter("artistid"));
                } catch (NumberFormatException e) {
                        artistId = 0;
                }

                try {
                        albumId = Integer.parseInt(request.getParameter("albumid"));
                } catch (NumberFormatException e) {
                        albumId = 0;
                }

                if (artistId != 0) {
                        artist = artistService.getModelById(artistId);
                        artist = new Artist();
                        request.setAttribute("artistid", artistId);
                }
                if (albumId != 0) {
                        album = new Album();
                        album = albumService.getModelById(albumId);
                        artistId = album.getIdArtist();
                        request.setAttribute("artistid", artistId);
                        request.setAttribute("album", album);
                }

                new PageBilder(request, response).includeAdminJsp(UPLOAD_SONGS, "Home");
        }

        protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
                Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
                List<File> loadedFiles = new ArrayList<>();

                ParserRequest parserRequest = new ParserRequest(request);
                parserRequest.setValidateForm(3);
                parserRequest.getParserParametr();
                fileUploadCustom = parserRequest.getFileUploadCustom();
                ArtistService artistService = new ArtistService();
                artist = artistService.getModelById(album.getIdArtist());

                if (parserRequest.getInvalidFields().isEmpty()) {
                        String songFolder = new FactoryFolder().getBilderPath()
                                        .getSongPathFolderAlbum(
                                                        String.valueOf(artist.getIdArtist()),
                                                        String.valueOf(album.getIdAlbum()));

                        SongService songService = new SongService();
                        features = SongFeatures.getSongFeatures();

                        String singleFilePath = fileUploadCustom.createFile(songFolder);

                        if (!fileUploadCustom.getArrayFiles().isEmpty()) {
                                loadedFiles = fileUploadCustom.getArrayFiles();
                                songList = new ArrayList<Song>();
                                songList = (ArrayList<Song>) features.setAllSongModels(
                                                loadedFiles, artist.getNameArtist(),
                                                album.getNameAlbum(), album.getIdAlbum(),
                                                artist.getIdArtist());
                                for (Song song : songList) {
                                        song.setNameFile(fileUploadCustom.getRelativePath(song
                                                        .getNameFile()));
                                        songService.deploy(song);
                                }
                        } else {
                                Song song = features.setSongModel(artist.getNameArtist(),
                                                album.getNameAlbum(), singleFilePath,
                                                album.getIdAlbum(), artist.getIdArtist());
                                if (song != null) {
                                        song.setNameFile(singleFilePath);
                                        songService.deploy(song);
                                }
                        }

                        if (features.getInvalidSongs().isEmpty()) {
                                new PageBilder(request, response).includeAdminJsp(BROWSER,
                                                "Success");
                        } else {
                                List<Song> list = features.getInvalidSongs();
                                request.setAttribute("songs", list);
                                request.setAttribute("genres", SongGenreService
                                                .getSongGenreService().getAllGenres());
                                new PageBilder(request, response).includeAdminJsp(EDIT_SONGS,
                                                "Edit");
                        }
                }

        }
}
