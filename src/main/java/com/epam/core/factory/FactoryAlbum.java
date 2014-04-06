package com.epam.core.factory;

import java.util.ArrayList;

import com.epam.model.bean.Album;

public class FactoryAlbum {

	private GenerateRandomString generateRandomString;

	public FactoryAlbum() {
		generateRandomString = new GenerateRandomString();
	}

	public Album getAlbum() {
		Album album = new Album();
		album.setNameAlbum(generateRandomString.getRandomString(6));
		album.setIdArtist(1);
		album.setAlbumDescription(new FactoryAlbumDescription()
				.getAlbumDescription());
		return album;
	}

	public ArrayList<Album> getListAlbums(int count) {
		ArrayList<Album> arrayList = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			arrayList.add(getAlbum());
		}

		return arrayList;
	}
}
