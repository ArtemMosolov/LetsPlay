package com.epam.core.factory;

import com.epam.controller.dao.BaseDAOInterfase;

public class ExcecuteSql<T> {

	public int deplyParent(BaseDAOInterfase<T> baseDAOInterfase, T model) {
		return baseDAOInterfase.create(model);
	}
}
