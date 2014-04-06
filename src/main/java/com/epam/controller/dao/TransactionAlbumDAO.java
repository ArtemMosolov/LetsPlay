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
import com.epam.model.bean.Album;
import com.epam.model.bean.AlbumDescription;
import com.epam.model.bean.Image;

public class TransactionAlbumDAO implements TransactionalDAO{

	private static HashMap<String, String> HASHMAP_INSERT;
	private static HashMap<String, String> HASHMAP_UPDATE;
	
	static{
		HASHMAP_INSERT = new HashMap<>();
		HASHMAP_INSERT.put("album", "INSERT INTO album (name_album, id_artists) VALUES(?, ?)");
		HASHMAP_INSERT.put("album_description", "INSERT INTO album_description (title_album, description_album,id_image,id_album) VALUES(?,?,?,?)");
		HASHMAP_UPDATE = new HashMap<>();
		HASHMAP_UPDATE.put("album","UPDATE album SET name_album=?, id_artists=? WHERE id_album=?");
		HASHMAP_UPDATE.put("album_description","UPDATE album_description SET title_album = ?, description_album = ?, id_image=? WHERE id_album = ?");
	}
	
	@Override
	public int create(Map<String, ?> map) throws SQLException {
		PreparedStatement prepared = null;
		Connection connection = null;
		try{
			connection =  ConnectionManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			prepared = connection.prepareStatement(HASHMAP_INSERT.get("album"),Statement.RETURN_GENERATED_KEYS);
			Album album = (Album) map.get("album");
			new PreparedStatementManager().putArgs(prepared, album.getNameAlbum(),album.getIdArtist());
			prepared.executeUpdate();
			ResultSet resultSet = prepared.getGeneratedKeys();
			resultSet.next();
			AlbumDescription albumDescription = (AlbumDescription) map.get("album_description");
			albumDescription.setIdAlbumDescription(resultSet.getInt(1));
			if(map.containsKey("image")){
				Image image = (Image) map.get("image");
				prepared = connection.prepareStatement(TransactionalDAO.IMAGE_INSERT,Statement.RETURN_GENERATED_KEYS);
				new PreparedStatementManager().putArgs(prepared,image.getFileName());
				prepared.executeUpdate();
				resultSet = prepared.getGeneratedKeys();
				resultSet.next();
				albumDescription.setIdImage(resultSet.getInt(1));
			}
			prepared = connection.prepareStatement(HASHMAP_INSERT.get("album_description"),Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(prepared, albumDescription.getTitleAlbum(),
					albumDescription.getDescriptionAlbum(), albumDescription.getIdImage(),
					albumDescription.getIdAlbumDescription());
			prepared.executeUpdate();
			connection.commit();
			return albumDescription.getIdAlbumDescription();
		}catch (SQLException e) {
			connection.rollback();
			throw e;
		}
		finally{
			if(!connection.isClosed() && connection !=null){
				connection.close();
			}
		}
	}

	@Override
	public void update(Map<String, ?> map) throws SQLException {

		PreparedStatement prepared = null;
		Connection connection = null;
		try{
			connection =  ConnectionManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			prepared = connection.prepareStatement(HASHMAP_UPDATE.get("album"),Statement.RETURN_GENERATED_KEYS);
			Album album = (Album) map.get("album");
			new PreparedStatementManager().putArgs(prepared, album.getNameAlbum(),album.getIdArtist(),album.getIdAlbum());
			prepared.executeUpdate();
			AlbumDescription albumDescription = (AlbumDescription) map.get("album_description");
			albumDescription.setIdAlbumDescription(album.getIdAlbum());
			if(map.containsKey("image")){
				Image image = (Image) map.get("image");
				prepared = connection.prepareStatement(TransactionalDAO.IMAGE_INSERT,Statement.RETURN_GENERATED_KEYS);
				new PreparedStatementManager().putArgs(prepared,image.getFileName());
				prepared.executeUpdate();
				ResultSet resultSet = prepared.getGeneratedKeys();
				resultSet.next();
				albumDescription.setIdImage(resultSet.getInt(1));
			}
			prepared = connection.prepareStatement(HASHMAP_UPDATE.get("album_description"),Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(prepared, albumDescription.getTitleAlbum(),
					albumDescription.getDescriptionAlbum(), albumDescription.getIdImage(),
					albumDescription.getIdAlbumDescription());
			prepared.executeUpdate();
			connection.commit();
		}catch (SQLException e) {
			connection.rollback();
			
			throw e;
		}
		finally{
			if(!connection.isClosed() && connection !=null){
				connection.close();
			}
		}
	}

}
