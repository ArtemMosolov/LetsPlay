package com.epam.core.filesystem;

import java.io.File;

public class CreateFileSystem {
	public static String CORE_FOLDER;
	private File systemFolder = null;
	private static CreateFileSystem INSTANCE = null;

	private CreateFileSystem() {

	}

	public static CreateFileSystem getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CreateFileSystem();
		}
		return INSTANCE;
	}

	private boolean createSystemFolder() {
		File folder = new File(CORE_FOLDER);
		boolean result = false;
		if (folder.isDirectory()) {
			systemFolder = folder;
		} else if (folder.mkdir()) {
			systemFolder = folder;
		}
		if (systemFolder != null) {
			result = true;
		}
		return result;
	}

	public File createFolder(String nameFolder) throws SecurityException {
		File folder = null;
		if (createSystemFolder()) {
			folder = new File(systemFolder, nameFolder);
			if (!folder.exists()) {
				if (!folder.isDirectory()) {
					if (!folder.mkdir()) {
						throw new SecurityException();
					}
				}
			}
		}
		
		return folder;
	}

	public File createFolder(File parent, String path) throws SecurityException {
		File folder = new File(parent, path);
		if (!folder.exists()) {
			if (!folder.isDirectory()) {
				if (!folder.mkdir()) {
					throw new SecurityException();
				}
			}
		}
		return folder;
	}
}
