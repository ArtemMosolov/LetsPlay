package com.epam.controller.dao.songstatistic;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.model.bean.SongStatistic;

public interface SongStatisticsDAO<T> extends BaseDAOInterfase<T> {
	public SongStatistic getUserSongStatistic(int id_user, int id_song);

	public void deleteUserSongStatistic(int id_user, int id_song);
}
