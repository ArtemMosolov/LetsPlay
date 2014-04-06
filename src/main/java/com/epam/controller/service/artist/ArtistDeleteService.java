package com.epam.controller.service.artist;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.controller.dao.image.ImageDAOImpl;
import com.epam.core.factory.FactoryFolder;
import com.epam.core.factory.FactoryFolder.BilderPath;
import com.epam.core.filesystem.DeleteFile;
import com.epam.core.interfaces.DeleteSource;
import com.epam.model.bean.Artist;
import com.epam.model.exception.FileNotDeleteException;

public class ArtistDeleteService implements DeleteSource<Artist>{


	@Override
	public void deleteData(BaseDAOInterfase<Artist> baseDAOInterfase, int id)
			throws SQLException, FileNotDeleteException {
		Artist artist = baseDAOInterfase.readId(id);
		baseDAOInterfase.delete(id);
		if(artist.getArtistDescription().getImage().getIdImage()!=null){
			ImageDAOImpl imageDAOImpl = new ImageDAOImpl();
			imageDAOImpl.delete(artist.getArtistDescription().getImage().getIdImage());
		}
		BilderPath bilderPath = new FactoryFolder().getBilderPath();
		try {
			deleteSource(bilderPath.getArtistPath(artist.getIdArtist().toString()).getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSource(String path) throws FileNotFoundException, SecurityException, FileNotDeleteException {
		DeleteFile deleteFile = new DeleteFile();
		deleteFile.delete(path);
		if(!deleteFile.getListDeleteFile().isEmpty()){
			throw new FileNotDeleteException().setList(deleteFile.getListDeleteFile());
		}
	}

}
