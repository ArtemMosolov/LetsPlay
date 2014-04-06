package com.epam.controller.dao.song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.Song;

public class SongDAOImpl extends BaseDAO<Song> implements SongDAO<Song> {

	private static Logger logger = Logger.getLogger(SongDAOImpl.class);

	private static final String INSERT_SONG = "INSERT INTO song "
			+ "(name_song, id_album) VALUES(?,?)";
	private static final String UPDATE_SONG = "UPDATE song SET "
			+ "name_song = ?, id_album = ? WHERE id_song = ?";
	private static final String SELECT_SONG = "SELECT * FROM "
			+ "song natural join album natural join artists WHERE id_song = ?";
	private static final String SELECT_ALL_SONGS_BY_ALBUM_ID = "SELECT "
			+ "* FROM song natural join artists natural join album WHERE id_album = ?";
	private static final String SELECT_ALL_SONGS_BY_ALBUM_NAME = "SELECT "
			+ "* FROM song natural join album natural join album_description "
			+ "left join image on album_description.id_image=image.id_image WHERE name_album LIKE ?";
	private static final String DELETE_SONG = "DELETE from song "
			+ "WHERE id_song = ?";
	private static final String SELECT_SONG_BY_NAME = "SELECT * "
			+ "FROM song NATURAL JOIN album natural join "
			+ "album_description left join image on album_description.id_image=image.id_image"
			+ " WHERE name_song LIKE ?";
	private static final String SELECT_ALL_SONGS_BY_ARTIST_NAME = "SELECT * FROM song "
			+ "NATURAL JOIN album NATURAL JOIN artists "
			+ "NATURAL JOIN artists_description "
			+ "LEFT JOIN image on artists_description.id_image=image.id_image "
			+ "WHERE name_artists LIKE ?";
	private static final String SELECT_ALL_SONG_BY_GENRE_NAME = "SELECT * FROM song "
			+ "NATURAL JOIN genre_song NATURAL JOIN genre "
			+ "WHERE name_genre LIKE ?";
	private static final String SELECT_ALL_SONG = "select * from song natural join "
			+ "artists natural join album";
	private static final String SELECT_PLAYLIST_CONTENT = "SELECT * FROM "
			+ "playlist NATURAL JOIN song NATURAL JOIN album NATURAL JOIN artists "
			+ "WHERE id_user_play_list = ?";

	private static final String SELECT_POPULAR_SONGS = "SELECT id_song, sum(count) as count_sum, name_song, id_album, file_song "
			+ "FROM song_statistic "
			+ "NATURAL JOIN song "
			+ "WHERE id_song IN "
			+ " ( "
			+ "SELECT id_song "
			+ "FROM "
			+ " ( "
			+ "SELECT id_song, SUM(count) AS popular "
			+ "FROM song "
			+ "NATURAL JOIN song_statistic "
			+ "GROUP BY name_song "
			+ "ORDER BY popular DESC "
			+ ") "
			+ "AS SUB_QUERY_POPULAR "
			+ " ) "
			+ "GROUP BY name_song " + "ORDER BY count_sum DESC " + "LIMIT 10 ";

	private static final String SELECT_POPULAR_SONGS_BY_ID_ARTIST = "SELECT id_song, sum(count) as count_sum, name_song, id_album, file_song "
			+ "FROM song_statistic "
			+ "NATURAL JOIN song "
			+ "NATURAL JOIN album "
			+ "WHERE id_song IN "
			+ " ( "
			+ "SELECT id_song "
			+ "FROM "
			+ " ( "
			+ "SELECT id_song, SUM(count) AS popular "
			+ "FROM song "
			+ "NATURAL JOIN song_statistic "
			+ "GROUP BY name_song "
			+ "ORDER BY popular DESC "
			+ ") AS SUB_QUERY_POPULAR "
			+ " ) "
			+ "AND id_artists = ? "
			+ "GROUP BY name_song "
			+ "ORDER BY count_sum DESC " + "LIMIT 10 ";

	private static final String SELECT_POPULAR_SONGS_BY_ID_ALBUM = "SELECT id_song,"
			+ " name_song, id_album, file_song "
			+ "FROM song_statistic "
			+ "NATURAL JOIN song "
			+ "NATURAL JOIN album "
			+ "NATURAL JOIN artists "
			+ "WHERE id_album = ? "
			+ "GROUP BY name_song " + "LIMIT 10 ";

	@Override
	public int create(Song model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_SONG,
					Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getNameSong(),
					model.getIdAlbum());
			result = ps.executeUpdate();
			ResultSet resultSet = ps.getGeneratedKeys();
			resultSet.next();
			result = resultSet.getInt(1);
			resultSet.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public Song readId(int id) {
		Song song = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("song");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_SONG);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			song = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return song;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_SONG);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();

	}

	@Override
	public int update(Song model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_SONG);
			new PreparedStatementManager().putArgs(ps, model.getNameSong(),
					model.getIdAlbum(), model.getIdSong());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Song> getAllSongsByAlbumId(int id) {
		List<Song> songs = new ArrayList<Song>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("song");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_SONGS_BY_ALBUM_ID);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			songs = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return songs;
	}

	@Override
	public List<Song> getSongByName(String name) {
		List<Song> songs = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("song");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_SONG_BY_NAME);
			ps.setString(1, "%" + name + "%");
			resultSet = ps.executeQuery();
			songs = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return songs;
	}

	public List<Song> getSongByGenreName(String name) {
		List<Song> songs = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("song");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_SONG_BY_GENRE_NAME);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			songs = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return songs;
	}

	public List<Song> getSongByArtistName(String name) {
		List<Song> songs = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("song");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_SONGS_BY_ARTIST_NAME);
			ps.setString(1, "%" +name + "%");
			resultSet = ps.executeQuery();
			songs = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return songs;
	}

	@Override
	public List<Song> getAllSongsByAlbumName(String name) {
		List<Song> songs = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("song");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_SONGS_BY_ALBUM_NAME);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			songs = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return songs;
	}

	@Override
	public List<Song> getAllSong() {
		List<Song> songs = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("song");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_SONG);
			resultSet = ps.executeQuery();
			songs = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return songs;
	}

	@Override
	public List<Song> getPopularSongs() {
		List<Song> songs = null;
		Statement s = null;
		ResultSet resultSet = null;
		setTable("song");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			s = con.createStatement();
			resultSet = s.executeQuery(SELECT_POPULAR_SONGS);
			songs = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return songs;
	}

	@Override
	public List<Song> getPopularSongsByIdArtist(int id) {
		List<Song> songs = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("song");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_POPULAR_SONGS_BY_ID_ARTIST);
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			songs = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return songs;
	}

	public List<Song> getPlaylistContent(int playlistId) {
		List<Song> songs = new ArrayList<Song>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("song");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_PLAYLIST_CONTENT);
			new PreparedStatementManager().putArgs(ps, playlistId);
			resultSet = ps.executeQuery();
			songs = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return songs;
	}
}
