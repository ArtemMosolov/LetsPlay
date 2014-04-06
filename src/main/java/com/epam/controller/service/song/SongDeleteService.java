package com.epam.controller.service.song;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.core.factory.FactoryFolder;
import com.epam.core.factory.FactoryFolder.BilderPath;
import com.epam.core.filesystem.DeleteFile;
import com.epam.core.interfaces.DeleteSource;
import com.epam.model.bean.Song;
import com.epam.model.exception.FileNotDeleteException;

public class SongDeleteService implements DeleteSource<Song> {

	@Override
	public void deleteData(BaseDAOInterfase<Song> baseDAOInterfase, int id)
			throws SQLException, FileNotDeleteException {
		Song song = baseDAOInterfase.readId(id);
		baseDAOInterfase.delete(id);
		BilderPath builderPath = new FactoryFolder().getBilderPath();
		try {
			deleteSource(builderPath.getFileByPath(song.getNameFile())
					.toString());

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void deleteSource(String path) throws FileNotDeleteException,
			FileNotFoundException {
		DeleteFile deleteFile = new DeleteFile();
		deleteFile.delete(path);
		if (!deleteFile.getListDeleteFile().isEmpty()) {
			throw new FileNotDeleteException().setList(deleteFile
					.getListDeleteFile());
		}

	}
}
