package com.epam.core.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.epam.core.util.MD5Encrypter;

public class FileUploadCustom {
		private MD5Encrypter md5;
		private static ArrayList<File> arrayList;
		private List<FileItem> items;
		private String coreUri;
		public FileUploadCustom(List<FileItem> items,String coreUri) {
			md5 = new MD5Encrypter();
			this.items = items;
			this.coreUri = coreUri;
		}

		public String createFile(File pathFolder) {
			String result = null;
			boolean res = false;
			try {
				if(items.size() == 1){
					result = uploadFileInFolder(items.get(0), pathFolder);
				}else{
					arrayList = new ArrayList<File>();
					for (FileItem object : items) {
						File tmp = fileInFolder(object, pathFolder);
						if (tmp != null) {
							arrayList.add(tmp);
						}
						res = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return result;
		}

		public ArrayList<File> getArrayFiles() {
			return arrayList;
		}

		public String createFile(String pathFolder) {
			String result = null;
			boolean res = false;
			try {
				arrayList = new ArrayList<File>();
				if(items.size() == 1){
					result = uploadFileInFolder(items.get(0), pathFolder);
				}else{
					for (FileItem object : items) {
						File tmp = fileInFolder(object, pathFolder);
						if (tmp != null) {
							arrayList.add(tmp);
						}
						res = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return result;
		}

		private String uploadFileInFolder(FileItem item, String nameFolder)
				throws Exception {
			File newFile = null;
			String nameFile = changeNameFile(item.getName());
			newFile = new File(nameFolder,
					nameFile);
			newFile.createNewFile();
			item.write(newFile);
			return getRelativePath(newFile.toURI().toString());
		}

		private String uploadFileInFolder(FileItem item, File nameFolder)
				throws Exception {
			File newFile = null;
			String nameFile = changeNameFile(item.getName());
			newFile = new File(nameFolder, nameFile);
			newFile.createNewFile();
			item.write(newFile);
			return getRelativePath(newFile.toURI().toString());
		}
		private File fileInFolder(FileItem item, File nameFolder)
				throws Exception {
			File newFile = null;
			String nameFile = changeNameFile(item.getName());
			newFile = new File(nameFolder, nameFile);
			newFile.createNewFile();
			item.write(newFile);
			return newFile;
		}
		private File fileInFolder(FileItem item, String nameFolder)
				throws Exception {
			File newFile = null;
			String nameFile = changeNameFile(item.getName());
			newFile = new File(nameFolder, nameFile);
			newFile.createNewFile();
			item.write(newFile);
			return newFile;
		}
		private String changeNameFile(String name) {
			String result = null;
			FileNameUtil fileNameUtil = new FileNameUtil(name, '/', '.');
			String extension = fileNameUtil.extension();
			String nameFile = fileNameUtil.filename();
			result = md5.encrypt(nameFile) + "." + extension;
			return result;
		}

		public String getRelativePath(String path) {
			return path.replace(coreUri, "");
		}
}
