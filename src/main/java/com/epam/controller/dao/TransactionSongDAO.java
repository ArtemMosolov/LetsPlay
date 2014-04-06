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
import com.epam.model.bean.Genre;
import com.epam.model.bean.Song;

public class TransactionSongDAO implements TransactionalDAO {
	private static HashMap<String, String> HASHMAP_INSERT;
	private static HashMap<String, String> HASHMAP_UPDATE;

	
	static{
		HASHMAP_INSERT = new HashMap<>();
		HASHMAP_INSERT.put("song", "INSERT INTO song (name_song, id_album, file_song) VALUES(?, ?, ?)");
		HASHMAP_INSERT.put("genre_song", "INSERT INTO genre_song (id_song, id_genre) VALUES(?,?)");
		HASHMAP_UPDATE = new HashMap<>();
		HASHMAP_UPDATE.put("song","UPDATE song SET name_song=?, id_album=?, file_song=? WHERE id_song=?");
		HASHMAP_UPDATE.put("genre_song","UPDATE genre_song SET id_genre = ? WHERE id_song = ?");
	}
	
	
	@Override
	public int create(Map<String, ?> map) throws SQLException {
		PreparedStatement prepared = null;
		Connection connection = null;
		try{
			connection =  ConnectionManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			prepared = connection.prepareStatement(HASHMAP_INSERT.get("song"),Statement.RETURN_GENERATED_KEYS);
			Song song = (Song) map.get("song");
			new PreparedStatementManager().putArgs(prepared, song.getNameSong(),song.getIdAlbum(), song.getNameFile());
			prepared.executeUpdate();
			ResultSet resultSet = prepared.getGeneratedKeys();
			resultSet.next();
			Genre genre = (Genre) map.get("genre");
			song.setIdSong(resultSet.getInt(1));
			prepared = connection.prepareStatement(HASHMAP_INSERT.get("genre_song"),Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(prepared, song.getIdSong(), genre.getIdGenre());
			prepared.executeUpdate();
			connection.commit();
			return song.getIdSong();
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
			prepared = connection.prepareStatement(HASHMAP_UPDATE.get("song"),Statement.RETURN_GENERATED_KEYS);
			Song song = (Song) map.get("song");
			new PreparedStatementManager().putArgs(prepared, song.getNameSong(), song.getIdAlbum(),song.getNameFile());
			prepared.executeUpdate();
			Genre genre = (Genre) map.get("genre");
			prepared = connection.prepareStatement(HASHMAP_UPDATE.get("genre_song"),Statement.RETURN_GENERATED_KEYS);
			new PreparedStatementManager().putArgs(prepared, genre.getIdGenre(), song.getIdSong());
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
