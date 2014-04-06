package com.epam.controller.dao.albumdescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.AlbumDescription;

public class AlbumDescriptionDAOImpl extends BaseDAO<AlbumDescription>
		implements
			AlbumDescriptionsDAO<AlbumDescription> {

	private static Logger logger = Logger
			.getLogger(AlbumDescriptionDAOImpl.class);

	private static final String INSERT_ALBUM_DESCRIPTION = "INSERT INTO album_description "
			+ "(title_album, description_album,id_image,id_album) VALUES(?,?,?,?)";
	private static final String SELECT_ALBUM_DESCRIPTION = "SELECT * FROM album_description "
			+ "WHERE id_album = ?";
	private static final String DELETE_ALBUM_DESCRIPTION = "DELETE FROM album_description WHERE "
			+ "id_album = ?";
	private static final String UPDATE_ALBUM_DESCRIPTION = "UPDATE album_description SET "
			+ "title_album = ?, description_album = ?, id_image=? WHERE id_album = ?";

	@Override
	public int create(AlbumDescription model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_ALBUM_DESCRIPTION,
					Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(ps, model.getTitleAlbum(),
					model.getDescriptionAlbum(), model.getIdImage(),
					model.getIdAlbumDescription());
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
	public AlbumDescription readId(int id) {
		AlbumDescription albumDescription = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALBUM_DESCRIPTION);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			albumDescription = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return albumDescription;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_ALBUM_DESCRIPTION);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(AlbumDescription model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_ALBUM_DESCRIPTION);
			new PreparedStatementManager().putArgs(ps, model.getTitleAlbum(),
					model.getDescriptionAlbum(),model.getIdImage(), model.getIdAlbumDescription());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
