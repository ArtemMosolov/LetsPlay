package com.epam.controller.dao.albumimage;

import java.util.List;

import com.epam.controller.dao.BaseDAOInterfase;
@Deprecated
public interface AlbumImageDAO<T> extends BaseDAOInterfase<T> {
	List<T> getAllImages(int Id);
}
