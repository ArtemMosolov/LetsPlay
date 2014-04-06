package com.epam.core.filesystem;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.epam.core.util.FormValidator;

public class ParserRequest {
	private String coreUri;
	private HttpServletRequest request;
	private HashMap<String, String> hashMapParametr;
	@Deprecated
	private boolean formValide = false;
	private List<FileItem> items;
	private List<FileItem> forLoad = null;
	private CreateFileSystem createFileSystem;
	private ServletFileUpload fileUpload;
	private static final int VALIDATE_IMAGE = 2;
	private static final int VALIDATE_SONG = 3;
	private int validateForm;
	private List<String> invalidFields;

	public static int getValidateImage() {
		return VALIDATE_IMAGE;
	}

	public static int getValidateSong() {
		return VALIDATE_SONG;
	}

	public void setValidateForm(int validateForm) {
		this.validateForm = validateForm;
	}

	public ParserRequest(HttpServletRequest request) {
		this.request = request;
		createFileSystem = CreateFileSystem.getInstance();
	}

	private void init() {
		coreUri = new StringBuilder().append("file:/")
				.append(new ReadProperty().getFolderName()).toString()
				.replace('\\', '/');
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(1024 * 1024);
		File file = createFileSystem.createFolder("temp");
		diskFileItemFactory.setRepository(file);
		fileUpload = new ServletFileUpload(diskFileItemFactory);
	}

	public void getParserParametr() {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			return;
		}
		init();
		hashMapParametr = new HashMap<>();
		invalidFields = new ArrayList<>();
		try {
			items = fileUpload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		forLoad = new ArrayList<>();
		for (FileItem item : items) {
			if (item.isFormField()) {
				String field = item.getFieldName();
				String value=null;
				try {
					value = item.getString("UTF-8").trim();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				if (FormValidator.validateField(field, value)) {
					hashMapParametr.put(field, value);
				} else {
					invalidFields.add(field);
				}
			} else {
				ValidFile validFile = new ValidFile();
				if (validateForm != 0) {
					if (VALIDATE_IMAGE == validateForm) {
						if (validFile.validFileImage(item)) {
							forLoad.add(item);
						} else {
						}
					} else if (VALIDATE_SONG == validateForm) {
						if (validFile.validFileMusic(item)) {
							forLoad.add(item);
						} else {
						}
					}
				}
			}
		}
	}

	public List<FileItem> getForLoad() {
		return forLoad;
	}

	public HashMap<String, String> getHashMapParametr() {
		return hashMapParametr;
	}

	public boolean isFormValide() {
		return formValide;
	}

	public List<String> getInvalidFields() {
		return invalidFields;
	}

	public FileUploadCustom getFileUploadCustom() {
		if (forLoad != null && coreUri != null) {
			return new FileUploadCustom(forLoad, coreUri);
		}
		return null;
	}
}