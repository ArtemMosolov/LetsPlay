package com.epam.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.epam.core.loadsrc.LoadModel;
import com.epam.model.annotation.ColumnName;
import com.epam.model.annotation.CompositionModel;
import com.epam.model.annotation.Model;

public class BaseDAO<T> {
	private static Logger log = Logger.getLogger(BaseDAO.class.getName());
	private LoadModel loadModel = LoadModel.getInstance();
	private String table;

	public void setTable(String table) {
		this.table = table;
	}

	public BaseDAO() {
		table = null;
	}

	private Object generateModel(ResultSet resultSet) throws SQLException {
		Object objectModel = null;
		try {
			String nameTable = null;
			nameTable = resultSet.getMetaData().getTableName(1);
			ResultSetMetaData metaData = resultSet.getMetaData();
			Class<?> model = loadModel.getMapModel().get(nameTable);
			objectModel = model.newInstance();
			Field[] fields = model.getDeclaredFields();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				for (Field field : fields) {
					field.setAccessible(true);
					ColumnName columName = (ColumnName) field
							.getAnnotation(ColumnName.class);
					String nameColum = metaData.getColumnName(i);
					if (columName != null) {
						if (columName.name().equals(nameColum)) {
							field.set(objectModel,
									resultSet.getObject(columName.name()));
							break;
						}
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			for (StackTraceElement element : e.getStackTrace()) {
				log.info(element.toString());
			}
		}

		return objectModel;
	}

	@SuppressWarnings("unchecked")
	protected T resultSetToObject(ResultSet resultSet) throws SQLException {
		Object object = null;
		resultSet.next();
		if (table == null) {
			object = generateModel(resultSet);
		} else {
			object = getObjectByResult(resultSet);
		}
		resultSet.close();
		return (T) object;
	}

	@SuppressWarnings("unchecked")
	protected ArrayList<T> getMassObject(ResultSet resultSet)
			throws SQLException {
		ArrayList<T> objects = new ArrayList<>();
		try {
			if (table == null) {
				while (resultSet.next()) {
					objects.add((T) generateModel(resultSet));
				}
			} else {
				while (resultSet.next()) {
					objects.add((T) getObjectByResult(resultSet));
				}
			}
		} finally {
			resultSet.close();
		}
		return objects;
	}

	private Object getObjectByResult(ResultSet resultSet)
			throws IllegalArgumentException, SQLException {
		Class<?> model = loadModel.getMapModel().get(table);
		Object objectModel = getObjectByResult(resultSet, model);
		return objectModel;
	}

	private Object getObjectByResult(ResultSet resultSet, Class<?> model)
			throws IllegalArgumentException, SQLException {
		Object objectModel = null;
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();
			objectModel = model.newInstance();
			Field[] fields = model.getDeclaredFields();
			Model modelAnotation = model.getAnnotation(Model.class);
			String thisTable = modelAnotation.name();
			for (Field field : fields) {
				field.setAccessible(true);
				Annotation[] annotations = field.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation instanceof ColumnName) {
						ColumnName columnName = (ColumnName) annotation;
						for (int i = 1; i <= metaData.getColumnCount(); i++) {
								String nameColumn = metaData.getColumnName(i);
								if (columnName.name().equals(nameColumn)) {
									field.set(objectModel,
											resultSet.getObject(nameColumn));
									break;
								}
						}
					} else if (annotation instanceof CompositionModel) {
						CompositionModel compositionModel = (CompositionModel) annotation;
						Class<?> tmp = loadModel.getMapModel().get(
								compositionModel.name());
						field.set(objectModel,
								getObjectByResult(resultSet, tmp));
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return objectModel;
	}
}
