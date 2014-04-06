package com.epam.core.filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DeleteFile {
	private ArrayList<String> listDeleteFile;

	public DeleteFile() {
		listDeleteFile = new ArrayList<>();
	}

	private void deleteFile(File file) throws SecurityException {
		if (!file.delete()) {
			throw new SecurityException();
		}
	}

	private void deleteFolder(File file) {
		if (!file.delete()) {
			for (File fileTmp : file.listFiles()) {
				if (!fileTmp.isDirectory()) {
					try {
						deleteFile(fileTmp);
						if(file.delete()){
							break;
						}
					} catch (SecurityException e) {
						listDeleteFile.add(fileTmp.getAbsolutePath());
						break;
					}
				} else {
					deleteFolder(fileTmp);
				}
			}
		}
		if(file.delete()){
			return;
		}
	}

	public ArrayList<String> getListDeleteFile() {
		return listDeleteFile;
	}

	public void delete(String path) throws FileNotFoundException,
			SecurityException {
		File file = new File(path);
		if (file.exists()) {
			if (file.isFile()) {
				try {
					deleteFile(file);
				} catch (SecurityException e) {
					listDeleteFile.add(file.getAbsolutePath());
				}
			} else if (file.isDirectory()) {
				deleteFolder(file);
			}
		} else {
			throw new FileNotFoundException();
		}
		if (!listDeleteFile.isEmpty()) {
			throw new SecurityException();
		}
	}
}
