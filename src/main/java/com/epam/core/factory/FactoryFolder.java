package com.epam.core.factory;

import java.io.File;

import com.epam.core.filesystem.CreateFileSystem;

public class FactoryFolder {
	
	private CreateFileSystem createFileSystem;
	
	private String nameFolderImages = "images";
	
	private String nameFolderAlbums = "albums";
	
	private String nameFolderSong = "songs";

	public FactoryFolder() {
		createFileSystem = CreateFileSystem.getInstance();
	}

	public void createArtistWithAlbumFolder() {

	}

	public File createArtistFolder(String path) {
		File file = createFileSystem.createFolder(path);
		createFileSystem.createFolder(file, nameFolderAlbums);
		return setImageFolder(file);
	}

	public File createUserImageFolder(String path) {
		File file = createFileSystem.createFolder(path);
		createFileSystem.createFolder(file, nameFolderImages);
		return setImageFolder(file);

	}

	public File createAlbumFolder(String nameArtist, String nameAlbum) {
		File file = createFileSystem.createFolder(nameArtist);
		File albumsFolder = createFileSystem.createFolder(file,
				nameFolderAlbums);
		return setAlbumFolder(albumsFolder, nameAlbum);
	}

	private File setAlbumFolder(File pathParent, String path) {
		File file = createFileSystem.createFolder(pathParent, path);
		setSongFolder(file);
		return setImageFolder(file);
	}

	private File setImageFolder(File parent) {
		return createFileSystem.createFolder(parent, nameFolderImages);
	}

	private File setSongFolder(File parent) {
		return createFileSystem.createFolder(parent, nameFolderSong);
	}

	public BilderPath getBilderPath() {
		return new BilderPath();
	}

	public class BilderPath {
		private BilderPath() {

		}

		public File getImagePathFolderArtist(String parent) {
			return checkChildeFolder(checkFolder(parent), nameFolderImages);
		}

		public File getArtistPath(String parent) {
			return checkFolder(parent);

		}

		public String getAlbumPathFolder(String parent, String child) {
			String result = null;
			File pathResult = checkChildeFolder(checkFolder(parent),
					nameFolderAlbums);
			pathResult = checkChildeFolder(pathResult, child);
			if (pathResult != null) {
				result = pathResult.getAbsolutePath();
			}
			return result;
		}

		public File getImagePathFolderAlbum(String parentArtist,
				String childAlbum) {
			File pathResult = checkChildeFolder(checkFolder(parentArtist),
					nameFolderAlbums);
			pathResult = checkChildeFolder(pathResult, childAlbum);
			pathResult = checkChildeFolder(pathResult, nameFolderImages);
			return pathResult;
		}

		public String getSongPathFolderAlbum(String parentArtist,
				String childAlbum) {
			File pathResult = checkChildeFolder(checkFolder(parentArtist),
					nameFolderAlbums);
			pathResult = checkChildeFolder(pathResult, childAlbum);
			pathResult = checkChildeFolder(pathResult, nameFolderSong);
			return pathResult.toString();
		}

		public String getImagePathArtist(String parent, String childFile) {
			String result = null;
			File childResult = checkChildeFolder(checkFolder(parent),
					nameFolderImages);
			childResult = checkChildeFolder(childResult, childFile);
			if (childResult != null) {
				result = childResult.getAbsolutePath();
			}
			return result;
		}

		public String getImagePathUser(String parent, String childFile) {
			String result = null;
			File childResult = checkChildeFolder(checkFolder(parent), childFile);
			// childResult = checkChildeFolder(childResult, childFile);
			if (childResult != null) {
				result = childResult.getAbsolutePath();
			}
			return result;
		}

		public String getImagepPathAlbum(String parentFolder,
				String childFolder, String nameFile) {
			String result = null;
			File pathResult = checkChildeFolder(checkFolder(parentFolder),
					nameFolderAlbums);
			pathResult = checkChildeFolder(pathResult, childFolder);
			pathResult = checkChildeFolder(pathResult, nameFolderImages);
			pathResult = checkChildeFolder(pathResult, nameFile);
			if (pathResult != null) {
				result = pathResult.getAbsolutePath();
			}
			return result;
		}

		public String getSongFolder(String parentFolder, String childFolder,
				String nameFile) {
			String result = null;
			File pathResult = checkChildeFolder(checkFolder(parentFolder),
					nameFolderAlbums);
			pathResult = checkChildeFolder(pathResult, childFolder);
			pathResult = checkChildeFolder(pathResult, nameFolderSong);
			pathResult = checkChildeFolder(pathResult, nameFile);
			if (pathResult != null) {
				result = pathResult.getAbsolutePath();
			}
			return result;
		}

		public File getFileByPath(String path) {
			File file = new File(CreateFileSystem.CORE_FOLDER);
			return file = new File(file, path);
		}

		private File checkFolder(String path) {
			File file = new File(CreateFileSystem.CORE_FOLDER);
			File resultFolder = null;
			if (file.exists()) {
				try {
					resultFolder = new File(file, path);
					if (resultFolder.isDirectory()) {
						file = resultFolder;
					} else {
						file = null;
					}
				} catch (NullPointerException e) {
					file = null;
				}

			}
			return file;
		}

		private File checkChildeFolder(File file, String childPath) {
			File resultFolder = null;
			if (file != null && file.exists()) {
				resultFolder = new File(file, childPath);
			} else {
				resultFolder = null;
			}
			return resultFolder;
		}
	}
}
