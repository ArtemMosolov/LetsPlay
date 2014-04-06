package com.epam.core.interfaces;

import java.sql.SQLException;
import java.util.Map;

public interface TransactionalDAO {
	String IMAGE_INSERT = "INSERT INTO image (file_name) VALUES(?)";
	String IMAGE_UPDATE = "UPDATE image SET file_name = ? WHERE id_image = ?";
	int create(Map<String, ?> map) throws SQLException;
	void update(Map<String,?> map) throws SQLException;
}
