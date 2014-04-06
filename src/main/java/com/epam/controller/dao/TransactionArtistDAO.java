package com.epam.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.epam.controller.db.ConnectionManager;
import com.epam.core.interfaces.TransactionalDAO;
import com.epam.core.preperedstatement.PreparedStatementManager;
import com.epam.model.bean.Artist;
import com.epam.model.bean.ArtistDescription;
import com.epam.model.bean.Image;

public class TransactionArtistDAO implements TransactionalDAO {
	private static HashMap<String, String> HASHMAP_INSERT;
	private static HashMap<String, String> HASHMAP_UPDATE;
	static {
		HASHMAP_INSERT = new HashMap<>();
		HASHMAP_INSERT.put("artists",
				"INSERT INTO artists (name_artists) VALUES (?)");
		HASHMAP_INSERT.put("artists_description",
				"INSERT INTO artists_description "
						+ "(title, artists_description,id_artists,id_image) "
						+ "VALUES(?,?,?,?)");
		HASHMAP_UPDATE = new HashMap<>();
		HASHMAP_UPDATE.put("artists",
				"UPDATE artists SET name_artists=? WHERE id_artists=?");
		HASHMAP_UPDATE.put("artists_description",
				"UPDATE artists_description SET title = ?, "
						+ "artists_description = ?,id_image=? "
						+ "WHERE id_artists = ?");
	}

	@Override
	public int create(Map<String, ?> map) throws SQLException {
		PreparedStatement prepared = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			prepared = connection.prepareStatement(
					HASHMAP_INSERT.get("artists"),
					Statement.RETURN_GENERATED_KEYS);
			Artist artist = (Artist) map.get("artists");
			new PreparedStatementManager().putArgs(prepared,
					artist.getNameArtist());
			prepared.executeUpdate();
			ResultSet resultSet = prepared.getGeneratedKeys();
			resultSet.next();
			ArtistDescription artistDescription = (ArtistDescription) map
					.get("artists_description");
			artistDescription.setIdArtistDescription(resultSet.getInt(1));
			if (map.containsKey("image")) {
				Image image = (Image) map.get("image");
				prepared = connection.prepareStatement(
						TransactionalDAO.IMAGE_INSERT,
						Statement.RETURN_GENERATED_KEYS);
				new PreparedStatementManager().putArgs(prepared,
						image.getFileName());
				prepared.executeUpdate();
				resultSet = prepared.getGeneratedKeys();
				resultSet.next();
				artistDescription.setIdImage(resultSet.getInt(1));
			}
			prepared = connection.prepareStatement(
					HASHMAP_INSERT.get("artists_description"),
					Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(prepared,
					artistDescription.getTitle(),
					artistDescription.getArtistDescription(),
					artistDescription.getIdArtistDescription(),
					artistDescription.getIdImage());
			prepared.executeUpdate();
			connection.commit();
			return artistDescription.getIdArtistDescription();
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		} finally {
			if (!connection.isClosed() && connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public void update(Map<String, ?> map) throws SQLException {
		PreparedStatement prepared = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			prepared = connection.prepareStatement(
					HASHMAP_UPDATE.get("artists"),
					Statement.RETURN_GENERATED_KEYS);
			Artist artist = (Artist) map.get("artists");
			new PreparedStatementManager().putArgs(prepared,
					artist.getNameArtist(), artist.getIdArtist());
			prepared.executeUpdate();
			ArtistDescription artistDescription = (ArtistDescription) map
					.get("artists_description");
			artistDescription.setIdArtistDescription(artist.getIdArtist());
			if (map.containsKey("image")) {
				prepared = connection.prepareStatement(
						TransactionalDAO.IMAGE_UPDATE,
						Statement.RETURN_GENERATED_KEYS);
				Image image = (Image) map.get("image");
				if (image.getIdImage() != null) {
					new PreparedStatementManager().putArgs(prepared,
							image.getFileName(), image.getIdImage());
					prepared.executeUpdate();
					resultSet = prepared.getGeneratedKeys();
					resultSet.next();
					artistDescription.setIdImage(resultSet.getInt(1));
				} else {
					prepared = connection.prepareStatement(
							TransactionalDAO.IMAGE_INSERT,
							Statement.RETURN_GENERATED_KEYS);
					new PreparedStatementManager().putArgs(prepared,
							image.getFileName());
					prepared.executeUpdate();
					resultSet = prepared.getGeneratedKeys();
					resultSet.next();
					artistDescription.setIdImage(resultSet.getInt(1));

				}

			}
			prepared = connection.prepareStatement(
					HASHMAP_UPDATE.get("artists_description"),
					Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(prepared,
					artistDescription.getTitle(),
					artistDescription.getArtistDescription(),
					artistDescription.getIdImage(),
					artistDescription.getIdArtistDescription());
			prepared.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			System.out.println();
			throw e;
		} finally {
			if (!connection.isClosed() && connection != null) {
				connection.close();
			}
		}
	}

}
