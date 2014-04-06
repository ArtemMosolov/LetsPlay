package com.epam.core.filesystem;

import org.apache.commons.fileupload.FileItem;

import com.epam.core.util.Validator;

public class ValidFile {
	private long maxSizeImage = 1024 * 1024 * 4;
	private long maxSizeSong = 1024 * 1024 * 20;
	public boolean validFileImage(FileItem item) {
		boolean result = false;
		if (item.getSize() <= maxSizeImage) {
			if (Validator.IMAGE_VALID.validate(item.getName())) {
				result = true;
			}
		}
		return result;
	}
	public boolean validFileMusic(FileItem item) {
		boolean result = false;
		if (item.getSize() <= maxSizeSong) {
			if (Validator.MUSIC.validate(item.getName())) {
				result = true;
			}
		}
		return result;

	}

}
