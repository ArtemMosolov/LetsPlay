package com.epam.controller.dao.artistsdescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.ArtistDescription;

public class ArtistDescriptionDAOImpl extends BaseDAO<ArtistDescription>
		implements ArtistDescriptionsDAO<ArtistDescription> {

	private static Logger logger = Logger
			.getLogger(ArtistDescriptionDAOImpl.class);

	private static final String INSERT_ARTIST_DESCRIPTION = "INSERT "
			+ "INTO artists_description " + "(title, artists_description,id_artists,id_image)"
			+ "VALUES(?,?,?,?)";
	private static final String SELECT_ARTIST_DESCRIPTION = "SELECT * "
			+ "FROM artists_description WHERE id_artist_description = ?";
	private static final String DELETE_ARTIST_DESCRIPTION = "DELETE "
			+ "FROM artists_description WHERE id_artist_description = ?";
	private static final String UPDATE_ARTIST_DESCRIPTION = "UPDATE "
			+ "artists_description SET "
			+ "title = ?, artists_description = ?,id_image=? "
			+ "WHERE id_artist = ?";

	@Override
	public int create(ArtistDescription model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_ARTIST_DESCRIPTION,Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getTitle(),
					model.getArtistDescription(),model.getIdArtistDescription(),model.getIdImage());
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
	public ArtistDescription readId(int id) {
		ArtistDescription artistDescription = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ARTIST_DESCRIPTION);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			artistDescription = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return artistDescription;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_ARTIST_DESCRIPTION);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(ArtistDescription model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_ARTIST_DESCRIPTION);
			new PreparedStatementManager().putArgs(ps, model.getTitle(),
					model.getArtistDescription(),model.getIdImage(),
					model.getIdArtistDescription());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
