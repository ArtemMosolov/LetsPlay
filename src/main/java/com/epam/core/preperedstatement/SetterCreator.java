package com.epam.core.preperedstatement;

import java.util.ArrayList;
import java.util.List;

public class SetterCreator {

	private static List<SetterEnum> list;

	static {
		list = new ArrayList<SetterEnum>();
		list.add(SetterEnum.INT);
		list.add(SetterEnum.LONG);
		list.add(SetterEnum.DOUBLE);
		list.add(SetterEnum.STRING);
		list.add(SetterEnum.BOOLEAN);
		list.add(SetterEnum.DATE);
		list.add(SetterEnum.TIMESTAMP);
	}

	public SetterEnum findByType(Class<?> type) {
		SetterEnum result = null;
		for (SetterEnum setter : list) {
			if (type.equals(setter.getType())) {
				result = setter;
				break;
			}
		}
		return result;
	}

}
