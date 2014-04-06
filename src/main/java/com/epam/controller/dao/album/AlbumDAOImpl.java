package com.epam.controller.dao.album;

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
import com.epam.model.bean.Album;

public class AlbumDAOImpl extends BaseDAO<Album> implements AlbumDAO<Album> {

	private static Logger logger = Logger.getLogger(AlbumDAOImpl.class);

	private static final String INSERT_ALBUM = "INSERT INTO album "
			+ "(name_album, id_artists) VALUES(?, ?)";
	private static final String UPDATE_ALBUM = "UPDATE album "
			+ "SET name_album=?, id_artists=? WHERE id_album=?";
	private static final String SELECT_ALBUM = "SELECT * FROM album "
			+ "NATURAL JOIN album_description "
			+ "left join image on album_description.id_image=image.id_image  "
			+ "WHERE id_album = ?";
	private static final String SELECT_ALBUM_BY_ID = "SELECT * FROM album "
			+ "NATURAL JOIN album_description LEFT JOIN image ON "
			+ "album_description.id_image=image.id_image WHERE id_album = ?";
	private static final String SELECT_ALBUM_BY_TITLE = "SELECT * FROM album  NATURAL "
			+ "JOIN artists NATURAL JOIN album_description "
			+ "left join image on album_description.id_image=image.id_image "
			+ "WHERE name_album LIKE ? ";
	private static final String SELECT_ALBUM_BY_ARTIST_ID = "SELECT * FROM "
			+ "album NATURAL JOIN album_description LEFT JOIN image ON "
			+ "album_description.id_image=image.id_image WHERE id_artists = ?";
	private static final String SELECT_ALBUM_BY_ARTIST = "SELECT * FROM "
			+ "album NATURAL JOIN artists NATURAL JOIN album_description "
			+ "left join image on album_description.id_image=image.id_image "
			+ " WHERE id_artists = ?";
	private static final String SELECT_ALL_ALBUM = "SELECT * FROM album "
			+ "NATURAL JOIN artists";
	private static final String DELETE_ALBUM = "DELETE from album WHERE id_album = ?";
	private static final String SELECT_ALBUM_BY_ARTIST_NAME = "SELECT * FROM album "
			+ "NATURAL JOIN artists NATURAL JOIN album_description "
			+ "left join image on album_description.id_image=image.id_image "
			+ "WHERE name_artists LIKE ?";
	private static final String SELECT_ALBUM_BY_GENRE_NAME = "SELECT * FROM album "
			+ "NATURAL JOIN song NATURAL JOIN genre_song NATURAL JOIN genre "
			+ "WHERE name_genre LIKE ?";
	private static final String SELECT_ALBUM_BY_SONG_NAME = "SELECT * FROM album "
			+ "NATURAL JOIN song WHERE name_song LIKE ?";

	private static final String SELECT_POPULAR_ALBUMS = "SELECT * "
			+ "FROM artists " + "NATURAL JOIN album "
			+ "NATURAL JOIN album_description "
			+ "LEFT JOIN image ON album_description.id_image=image.id_image "
			+ "WHERE id_album IN " + " ( " + "SELECT id_album " + "FROM "
			+ " ( " + "SELECT id_album, SUM(count) AS popular " + "FROM album "
			+ "NATURAL JOIN song " + "NATURAL JOIN song_statistic "
			+ "GROUP BY name_album " + "ORDER BY popular DESC "
			+ ") AS SUB_QUERY_POPULAR " + " ) " + " LIMIT 6 ";

	@Override
	public int create(Album model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_ALBUM,
					Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getNameAlbum(),
					model.getIdArtist());
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
	public Album readId(int id) {
		Album album = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("album");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALBUM_BY_ID);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			album = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return album;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_ALBUM);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(Album model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_ALBUM);
			new PreparedStatementManager().putArgs(ps, model.getNameAlbum(),
					model.getIdArtist(), model.getIdAlbum());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Album> getAll() {
		List<Album> albums = new ArrayList<Album>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("album");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_ALBUM);
			resultSet = ps.executeQuery();
			albums = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return albums;
	}

	@Override
	public List<Album> getAlbumByName(String name) {
		List<Album> albums = new ArrayList<Album>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("album");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALBUM_BY_TITLE);
			ps.setString(1, "%" + name + "%");
			resultSet = ps.executeQuery();
			albums = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return albums;
	}

	public List<Album> getAlbumByGenreName(String name) {
		List<Album> albums = new ArrayList<Album>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("album");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALBUM_BY_GENRE_NAME);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			albums = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return albums;
	}

	public List<Album> getAlbumBySongName(String name) {
		List<Album> albums = new ArrayList<Album>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("album");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALBUM_BY_SONG_NAME);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			albums = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return albums;
	}

	@Override
	public List<Album> getAlbumsByArtistId(int id) {
		List<Album> albums = new ArrayList<Album>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("album");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALBUM_BY_ARTIST);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			albums = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return albums;
	}

	@Override
	public List<Album> getAlbumsByArtistName(String name) {
		List<Album> albums = new ArrayList<Album>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("album");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALBUM_BY_ARTIST_NAME);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			albums = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return albums;
	}

	@Override
	public List<Album> getPopularAlbums() {
		List<Album> albums = new ArrayList<Album>();
		Statement s = null;
		ResultSet resultSet = null;
		setTable("album");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			s = con.createStatement();
			resultSet = s.executeQuery(SELECT_POPULAR_ALBUMS);
			albums = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return albums;
	}

}
