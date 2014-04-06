package com.epam.core.factory;

import java.util.ArrayList;

import com.epam.model.bean.Song;

public class FactorySong {
	private GenerateRandomString generateRandomString;
	public FactorySong() {
		generateRandomString = new GenerateRandomString();
	}
	
	public Song getSong() {
		Song song = new Song();
		song.setNameSong(generateRandomString.getRandomString(8));
		song.setIdAlbum(1);
		song.setNameFile(generateRandomString.getRandomString(8));
		return song;
	}
	
	public ArrayList<Song> getListSongs(int count) {
		ArrayList<Song> arrayList = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			arrayList.add(getSong());
		}

		return arrayList;
	}
}
