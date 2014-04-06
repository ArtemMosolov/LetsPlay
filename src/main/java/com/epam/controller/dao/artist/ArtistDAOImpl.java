package com.epam.controller.dao.artist;

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
import com.epam.model.bean.Artist;

public class ArtistDAOImpl extends BaseDAO<Artist> implements ArtistDAO<Artist> {

	private static Logger logger = Logger.getLogger(ArtistDAOImpl.class);

	private static final String INSERT_ARTIST = "INSERT INTO artists "
			+ "(name_artists) VALUES(?)";
	private static final String UPDATE_ARTIST = "UPDATE artists "
			+ "SET name_artists=? WHERE id_artists=?";
	/*
	 * private static final String SELECT_ARTIST =
	 * "SELECT * FROM artists NATURAL JOIN artists_description " +
	 * " NATURAL JOIN image WHERE id_artists = ?";
	 */
	private static final String SELECT_ARTIST = "SELECT * FROM artists "
			+ "NATURAL JOIN artists_description "
			+ " LEFT JOIN image ON artists_description.id_image=image.id_image "
			+ "WHERE artists.id_artists = ?";

	private static final String SELECT_ALL_ARTISTS = "SELECT * FROM artists";
	private static final String DELETE_ARTIST = "DELETE from "
			+ "artists WHERE id_artists=?";
	/*
	 * private static final String SELECT_ARTIST_BY_NAME = "SELECT * " +
	 * "FROM artists NATURAL JOIN artists_description" +
	 * " NATURAL JOIN image WHERE name_artists = ?";
	 */
	private static final String SELECT_ARTIST_BY_NAME = "SELECT * "
			+ "FROM artists NATURAL JOIN artists_description "
			+ "LEFT JOIN image ON artists_description.id_image=image.id_image "
			+ "WHERE name_artists LIKE ?";
	private static final String SELECT_ARTIST_BY_ALBUM_ID = "SELECT * "
			+ "FROM artists NATURAL JOIN album WHERE id_album = ?";
	private static final String SELECT_ARTIST_BY_ID_SONG = "SELECT * FROM artists "
			+ "NATURAL JOIN album NATURAL JOIN song WHERE id_song = ?";
	private static final String SELECT_ARTIST_BY_NAME_SONG = "SELECT * "
			+ "FROM artists NATURAL JOIN album NATURAL JOIN song WHERE "
			+ "name_song LIKE ?";
	private static final String SELECT_ARTIST_BY_NAME_ALBUM = "SELECT * "
			+ "FROM artists NATURAL JOIN album WHERE name_album LIKE ? ";
	private static final String SELECT_ARTIST_BY_GENRE_NAME = "select * "
			+ "FROM artists "
			+ "natural join album natural join song natural join genre_song "
			+ "natural join genre where name_genre LIKE ?;";
	private static final String SELECT_ARTIST_BY_GENRE_ID = "select * from artist "
			+ "natural join album "
			+ "natural join song natural join genresong natural join genre where id_genre=?;";

	private static final String SELECT_POPULAR_ARTISTS = "SELECT * "
			+ "FROM artists " + "NATURAL JOIN artists_description "
			+ "LEFT JOIN image ON artists_description.id_image=image.id_image "
			+ "WHERE id_artists IN " + " ( " + "SELECT id_artists " + "FROM "
			+ " ( " + "SELECT id_artists, SUM(count) AS popular "
			+ "FROM artists " + "NATURAL JOIN album " + "NATURAL JOIN song "
			+ "NATURAL JOIN song_statistic " + "GROUP BY name_artists "
			+ "ORDER BY popular DESC " + ") AS SUB_QUERY_POPULAR " + " ) "
			+ " LIMIT 6 ";

	@Override
	public int create(Artist model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_ARTIST,
					Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getNameArtist());
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
	public Artist readId(int id) {
		Artist artist = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("artists");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ARTIST);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			artist = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return artist;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_ARTIST);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(Artist model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_ARTIST);
			new PreparedStatementManager().putArgs(ps, model.getNameArtist(),
					model.getIdArtist());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<Artist> getAll() {
		List<Artist> artists = new ArrayList<Artist>();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALL_ARTISTS);
			resultSet = ps.executeQuery();
			artists = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return artists;
	}

	@Override
	public List<Artist> getArtistByName(String name) {
		List<Artist> artists = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("artists");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ARTIST_BY_NAME);
			ps.setString(1, "%" + name + "%");
			resultSet = ps.executeQuery();
			artists = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return artists;
	}

	public List<Artist> getAllArtistByGenreName(String name) {
		List<Artist> artists = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("artists");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ARTIST_BY_GENRE_NAME);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			artists = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return artists;
	}

	public List<Artist> getAllArtistsByGenreId(int id) {
		List<Artist> artists = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ARTIST_BY_GENRE_ID);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			artists = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return artists;
	}

	@Override
	public Artist getArtistByAlbum(int id) {
		Artist artist = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ARTIST_BY_ALBUM_ID);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			this.setTable("artists");
			artist = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return artist;
	}

	@Override
	public Artist getArtistByIdSong(int id) {
		Artist artist = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ARTIST_BY_ID_SONG);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			this.setTable("artists");
			artist = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return artist;
	}

	@Override
	public ArrayList<Artist> getAtristByNameSong(String name) {
		List<Artist> artists = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("artists");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ARTIST_BY_NAME_SONG);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			this.setTable("artists");
			artists = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return (ArrayList<Artist>) artists;
	}

	@Override
	public ArrayList<Artist> getArtistByNameAlbum(String name) {
		List<Artist> artists = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		setTable("artists");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ARTIST_BY_NAME_ALBUM);
			ps.setString(1, name + "%");
			resultSet = ps.executeQuery();
			this.setTable("artists");
			artists = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return (ArrayList<Artist>) artists;
	}

	@Override
	public List<Artist> getPopularArtists() {
		List<Artist> artists = null;
		Statement s = null;
		ResultSet resultSet = null;
		setTable("artists");
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			s = con.createStatement();
			resultSet = s.executeQuery(SELECT_POPULAR_ARTISTS);
			artists = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return artists;
	}

	@Override
	public List<Artist> getArtistByGenre(int[] id) {
		return null;
	}

}
