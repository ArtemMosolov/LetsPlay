package com.epam.core.factory;

import java.util.ArrayList;

import com.epam.model.bean.AlbumDescription;
import com.epam.model.bean.Image;

public class FactoryAlbumDescription {

	private GenerateRandomString generateRandomString;

	public FactoryAlbumDescription() {
		generateRandomString = new GenerateRandomString();
	}

	public AlbumDescription getAlbumDescription() {
		AlbumDescription albumDescription = new AlbumDescription();

		albumDescription.setTitleAlbum(generateRandomString.getRandomString(5));
		albumDescription.setDescriptionAlbum(generateRandomString
				.getRandomString(7));
		albumDescription.setImage(getImage());

		return albumDescription;
	}

	private Image getImage() {
		Image image = new Image();
		image.setFileName(generateRandomString.getRandomString(4));
		return image;
	}

	public ArrayList<AlbumDescription> getListAlbums(int count) {
		ArrayList<AlbumDescription> arrayList = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			arrayList.add(getAlbumDescription());
		}

		return arrayList;
	}

}
