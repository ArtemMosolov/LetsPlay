package com.epam.controller.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.controller.dao.DeleteDAOImp;
import com.epam.core.interfaces.DeleteData;
import com.epam.model.bean.DeleteModel;
import com.epam.model.exception.FileNotDeleteException;

public abstract class BaseServise<T> {

	private final String addError = "Item can not be added";

	private final String updateError = "Item can not be updated";

	private final String deleteError = "Item can not be deleted";

	private final String emptyError = "The search result is empty";

	private List<String> errors;

	private DeleteData<T> deleteService;

	private BaseDAOInterfase<T> baseDAOInterfase;

	private DeleteDAOImp deleteDAOImp;
	
	protected BaseServise(DeleteData<T> deleteService,
			BaseDAOInterfase<T> baseDAOInterfase) {
		this.deleteService = deleteService;
		this.baseDAOInterfase = baseDAOInterfase;
		deleteDAOImp = new DeleteDAOImp();

		errors = new ArrayList<String>();
	}

	public boolean isEmptyErrorList() {
		return addError.isEmpty();
	}

	public List<String> getErrors() {
		return errors;
	}

	protected void setError(String error) {
		errors.add(error);
	}

	protected String getAddError() {
		return addError;
	}

	protected String getUpdateError() {
		return updateError;
	}

	protected String getDeleteError() {
		return deleteError;
	}

	protected String getEmptyError() {
		return emptyError;
	}

	public void delete(int id) {
		try {
			try {
				deleteService.deleteData(baseDAOInterfase, id);
			} catch (FileNotDeleteException e) {
				for (String file : e.getList()) {
					DeleteModel deleteModel = new DeleteModel();
					deleteModel.setFileName(file);
					deleteDAOImp.create(deleteModel);
				}
				e.printStackTrace();

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public T getModelById(int id) {
		return baseDAOInterfase.readId(id);
	}

	public abstract int deploy(T model);

	public List<T> getAllModelByTable() {
		return null;
	}

	public List<T> getAllModelByTable(int idParent) {
		return null;
	}

}
