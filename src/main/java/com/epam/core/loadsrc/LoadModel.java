package com.epam.core.loadsrc;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;

import com.epam.model.annotation.Model;

public class LoadModel {
	private LoadSrc loadSources;

	private HashMap<String, Class<?>> mapModel;
	private static LoadModel INSTANCE = null;

	private LoadModel() {
	}

	public static LoadModel getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LoadModel();
		}
		return INSTANCE;
	}

	public void generateMapModel() {
		loadSources = new LoadSrc();
		mapModel = new HashMap<>();
		Class<?>[] classes = null;
		try {
			classes = loadSources.getMass("com.epam.model.bean");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		for (Class<?> class1 : classes) {
			Annotation[] annotations = class1.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Model) {
					Model model = (Model) annotation;
					mapModel.put(model.name(), class1);
				}
			}
		}
	}

	public HashMap<String, Class<?>> getMapModel() {
		return mapModel;
	}
}
