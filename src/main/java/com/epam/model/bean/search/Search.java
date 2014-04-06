package com.epam.model.bean.search;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "search", propOrder = { "item" })
@XmlRootElement(name = "search")
public class Search {

	@XmlElement(required = true)
	protected List<Item> item;

	public List<Item> getItem() {
		if (item == null) {
			item = new ArrayList<Item>();
		}
		return this.item;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "name", "priority" })
	public static class Item {

		@XmlElement(required = true)
		protected String name;
		protected int priority;
		@XmlAttribute
		protected Integer id;

		public String getName() {
			return name;
		}

		public void setName(String value) {
			this.name = value;
		}

		public int getPriority() {
			return priority;
		}

		public void setPriority(int value) {
			this.priority = value;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer value) {
			this.id = value;
		}

	}

}
