package com.epam.controller.dao.albumimage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.AlbumImage;
@Deprecated
public class AlbumImageDAOImpl extends BaseDAO<AlbumImage> implements AlbumImageDAO<AlbumImage> {

	private static Logger logger = Logger.getLogger(AlbumImageDAOImpl.class);
	
	private static final String INSERT_ALBUM_IMAGE = "INSERT INTO album_image (id_album_image_artist, id_image) VALUES(?, ?)";
	private static final String SELECT_ALBUM_IMAGE = "SELECT * FROM album_image WHERE id_album_image_artist = ?";
	private static final String DELETE_ALBUM_IMAGE = "DELETE FROM album_image WHERE id_album_image_artist = ?";
	private static final String UPDATE_ALBUM_IMAGE = "UPDATE album_image SET id_image WHERE id_album_image_artist = ?";
	private static final String SELECT_IMG_LIST_ID_ART = "select * from album_image_artist natural join image where id_album_image_artist=?";
	
	@Override
	public int create(AlbumImage model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_ALBUM_IMAGE);
			new PreparedStatementManager().putArgs(ps, model.getIdAlbumImageArtist(),
					model.getIdImage());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public AlbumImage readId(int id) {
		AlbumImage albumImage = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ALBUM_IMAGE);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			albumImage = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return albumImage;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_ALBUM_IMAGE);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(AlbumImage model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_ALBUM_IMAGE);
			new PreparedStatementManager().putArgs(ps, model.getIdImage(),
					model.getIdAlbumImageArtist());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	@Override
	public List<AlbumImage> getAllImages(int id) {
		setTable("image");
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		ArrayList<AlbumImage> listImage = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_IMG_LIST_ID_ART);
			new PreparedStatementManager().putArgs(ps, id);
			listImage = getMassObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return listImage;
	}
	
}
