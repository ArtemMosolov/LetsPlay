package com.epam.core;

import java.sql.SQLException;
import java.util.List;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.core.interfaces.DeployData;
import com.epam.core.interfaces.TransactionalDAO;

public abstract class BaseDeploy<T> implements DeployData<T>{
	private TransactionalDAO transactionalDAO;
	private BaseDAOInterfase<T> baseDAOInterfase;
	private ParserModel parserModel;
	public BaseDeploy() {
		parserModel = new ParserModel();
	}
	public BaseDeploy(BaseDAOInterfase<T> baseDAOInterfase){
		this();
		this.baseDAOInterfase = baseDAOInterfase;
	}
	public BaseDeploy(TransactionalDAO transactionalDAO) {
		this();
		this.transactionalDAO = transactionalDAO;
		
	}
	public BaseDeploy(TransactionalDAO transactionalDAO,BaseDAOInterfase<T> baseDAOInterfase){
		this(transactionalDAO);
		this.baseDAOInterfase = baseDAOInterfase;
	}
	
	protected int create(List<T> list) {
		return 0;
	}
	protected boolean update(List<T> list) {
		return false;
	}
	protected int create(T model) throws SQLException {
		int result = 0;
		if (parserModel.generateMapTable(model)) {
			result = transactionalDAO.create(parserModel.getMap());
		}
		return result;
	}
	protected boolean update(T model) throws SQLException {
		boolean result = false;
		if (parserModel.generateMapTable(model)) {
			transactionalDAO.update(parserModel.getMap());
			result = true;
		}
		return result;
	}
	protected int createOne(T model){
		int result = 0;
		result = baseDAOInterfase.create(model);
		return result;
	}
	protected boolean updateOne(T model){
		boolean result = false;
		if(baseDAOInterfase.update(model) !=0){
			result = true;
		}
		return result;
	}
}
