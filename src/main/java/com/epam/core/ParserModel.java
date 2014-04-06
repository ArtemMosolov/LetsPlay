package com.epam.core;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.el.util.ReflectionUtil;

import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;

import com.epam.model.annotation.CompositionModel;
import com.epam.model.annotation.Model;

public class ParserModel {
	private Map<String, ? super Object> hashMap;
	public ParserModel() {
		hashMap = new HashMap<>();
	}
	public Map<String, ? super Object> getMap() {
		return hashMap;
	}
	@Deprecated
	public Map<String, ? super Object> getMapTable(List<?> listModel){
		Map<String, ? super Object> hashMap = new HashMap<>();
		for (Object parameter : listModel) {
//			getMapTable(parameter);
		}
		return hashMap;
	}
	

	public boolean generateMapTable(Object object){
		Class<?> model = object.getClass();
		Model modelAnotation = (Model)model.getAnnotation(Model.class);
		if(modelAnotation != null){
			parserModel(object);
			return true;
		}
		return false;
	}
	private void parserModel(Object object){
		if(object != null){
			Class<?> model = object.getClass();
			Model modelAnotation = (Model)model.getAnnotation(Model.class);
			if(modelAnotation != null){
				hashMap.put(modelAnotation.name(), object);
				Field[] fields = model.getDeclaredFields();
				for (Field field : fields) {
					CompositionModel annotation = (CompositionModel)field.getAnnotation(CompositionModel.class);
					if(annotation != null){
					String name = field.getName();
						try {
							PropertyDescriptor descriptor = new PropertyDescriptor(name, model);
							Method getter = descriptor.getReadMethod();
							Object value = getter.invoke(object, null);
							if(value != null){
								hashMap.put(annotation.name(), value);
								parserModel(value);
							}
						} catch (IntrospectionException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	private String getKeyTable(Class<?> class1){
		String result = null;
		Annotation[] annotations = class1.getAnnotations();
		for (Annotation annotation : annotations) {
			if(annotation instanceof Model){
				result = ((Model) annotation).name();
			}
		}
		return result;
	}
}
