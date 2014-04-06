package com.epam.controller.dao.albumimagearlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.BaseDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.AlbumImageArtist;
@Deprecated
public class ArtistImageListDAOImpl extends BaseDAO<AlbumImageArtist> implements
		ArtistImageListDAO<AlbumImageArtist> {

	private static Logger logger = Logger
			.getLogger(ArtistImageListDAOImpl.class);

	private static final String INSERT_ART_IMG_LIST = "INSERT INTO album_image_artist "
			+ "(name_album_image, id_artist, id_image_avatar) VALUES(?, ?, ?)";
	private static final String UPDATE_ART_IMG_LIST = "UPDATE album_image_artist SET"
			+ " name_album_image=?, id_artist=?, id_image_avatar=?  WHERE "
			+ "id_album_image_artist=?";
	private static final String SELECT_ART_IMG_LIST = "SELECT * FROM "
			+ "album_image_artist WHERE id_album_image_artist=?";
	private static final String DELETE_ART_IMG_LIST = "DELETE from "
			+ "album_image_artist WHERE id_album_image_artist=?";

	@Override
	public int create(AlbumImageArtist model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(INSERT_ART_IMG_LIST);
			new PreparedStatementManager().putArgs(ps,
					model.getIdAlbumImageArtist(), model.getIdArtists(),
					model.getIdImageAvatar());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public AlbumImageArtist readId(int id) {
		AlbumImageArtist albumImageArtist = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(SELECT_ART_IMG_LIST);
			new PreparedStatementManager().putArgs(ps, id);
			resultSet = ps.executeQuery();
			albumImageArtist = resultSetToObject(resultSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return albumImageArtist;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = null;
		Connection con = ConnectionManager.getInstance().getConnection();
		ps = con.prepareStatement(DELETE_ART_IMG_LIST);
		new PreparedStatementManager().putArgs(ps, id);
		ps.executeUpdate();
	}

	@Override
	public int update(AlbumImageArtist model) {
		int result = 0;
		PreparedStatement ps = null;
		try (Connection con = ConnectionManager.getInstance().getConnection()) {
			ps = con.prepareStatement(UPDATE_ART_IMG_LIST);
			new PreparedStatementManager().putArgs(ps,
					model.getIdAlbumImageArtist(), model.getIdArtists(),
					model.getIdImageAvatar(), model.getIdAlbumImageArtist());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}



}
