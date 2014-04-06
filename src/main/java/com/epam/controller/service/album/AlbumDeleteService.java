package com.epam.controller.service.album;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.controller.dao.image.ImageDAOImpl;
import com.epam.core.factory.FactoryFolder;
import com.epam.core.factory.FactoryFolder.BilderPath;
import com.epam.core.filesystem.DeleteFile;
import com.epam.core.interfaces.DeleteSource;
import com.epam.model.bean.Album;
import com.epam.model.exception.FileNotDeleteException;

public class AlbumDeleteService implements DeleteSource<Album> {


	@Override
	public void deleteData(BaseDAOInterfase<Album> baseDAOInterfase, int id)
			throws SQLException, FileNotDeleteException {
		Album album = baseDAOInterfase.readId(id);
		baseDAOInterfase.delete(id);
		if(album.getAlbumDescription().getImage().getIdImage() !=null){
			ImageDAOImpl imageDAOImpl = new ImageDAOImpl();
			imageDAOImpl.delete(album.getAlbumDescription().getIdImage());
		}
		BilderPath bilderPath = new FactoryFolder().getBilderPath();
		try {
			deleteSource(bilderPath.getAlbumPathFolder(album.getIdArtist().toString(), album.getIdAlbum().toString()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSource(String path) throws FileNotDeleteException,
			FileNotFoundException {
		DeleteFile deleteFile = new DeleteFile();
		deleteFile.delete(path);
		if(!deleteFile.getListDeleteFile().isEmpty()){
			throw new FileNotDeleteException().setList(deleteFile.getListDeleteFile());
		}
	}

}
