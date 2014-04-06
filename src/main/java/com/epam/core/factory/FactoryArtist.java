package com.epam.core.factory;

import java.util.ArrayList;

import com.epam.controller.dao.artist.ArtistDAOImpl;
import com.epam.controller.dao.artistsdescription.ArtistDescriptionDAOImpl;
import com.epam.controller.dao.image.ImageDAOImpl;
import com.epam.model.bean.Artist;
import com.epam.model.bean.ArtistDescription;
import com.epam.model.bean.Image;

public class FactoryArtist {
	private GenerateRandomString generateRandomString;
	private ExcecuteSql excecuteSql;
	public FactoryArtist() {
		generateRandomString = new GenerateRandomString();
	}
	public Artist getArtist() {
		Artist artist = new Artist();
		artist.setNameArtist(generateRandomString.getRandomString(6));
		return artist;
	}
	public Image getImage(){
		Image image = new Image();
		image.setFileName(generateRandomString.getRandomString(4));
		return image;
	}
	public ArtistDescription getArtistDescription(int idImage,int id){
		ArtistDescription artistDescription = new ArtistDescription();
		artistDescription.setArtistDescription(generateRandomString.getRandomString(8));
		artistDescription.setTitle(generateRandomString.getRandomString(5));
		artistDescription.setIdImage(idImage);
		artistDescription.setIdArtistDescription(id);
		return artistDescription;
	}
	public void deployDate(){
		new ExcecuteSql<ArtistDescription>().deplyParent(new ArtistDescriptionDAOImpl(), getArtistDescription(new ExcecuteSql<Image>().deplyParent(new ImageDAOImpl(), getImage()),  new ExcecuteSql<Artist>().deplyParent(new ArtistDAOImpl(), getArtist())));
	}
	
	public ArrayList<Artist> getListArtist(int count){
		ArrayList<Artist> arrayList = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			arrayList.add(getArtist());
		}
		return arrayList;
	}
	
}
