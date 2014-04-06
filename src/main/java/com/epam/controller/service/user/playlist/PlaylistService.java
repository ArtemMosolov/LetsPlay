package com.epam.controller.service.user.playlist;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.controller.dao.BaseDAOInterfase;
import com.epam.controller.dao.playlist.PlayListDAOImpl;
import com.epam.controller.dao.song.SongDAOImpl;
import com.epam.controller.dao.songstatistic.SongStatisticDAOImpl;
import com.epam.controller.dao.userplaylist.UserPlayListDaoImpl;
import com.epam.controller.service.BaseServise;
import com.epam.core.interfaces.DeleteData;
import com.epam.model.bean.PlayList;
import com.epam.model.bean.Song;
import com.epam.model.bean.SongStatistic;
import com.epam.model.bean.UserPlayList;

public class PlaylistService extends BaseServise<UserPlayList> {

	private PlaylistDeployService deployService;

	private UserPlayListDaoImpl userPlayListDaoImpl;

	private PlayListDAOImpl playListDAOImpl;

	private SongDAOImpl songDAOImpl;

	public PlaylistService() {
		this(new PlaylistDeleteService(), new UserPlayListDaoImpl());
		userPlayListDaoImpl = new UserPlayListDaoImpl();
		songDAOImpl = new SongDAOImpl();
		playListDAOImpl = new PlayListDAOImpl();
	}

	protected PlaylistService(DeleteData<UserPlayList> deleteService,
			BaseDAOInterfase<UserPlayList> baseDAOInterfase) {
		super(deleteService, baseDAOInterfase);
	}

	@Override
	public int deploy(UserPlayList model) {
		deployService = new PlaylistDeployService(userPlayListDaoImpl);
		int result = 0;
		if (model != null) {
			if (model.getIdUserPlayList() == 0) {
				try {
					result = deployService.createRecord(model);
				} catch (SQLException e) {
					setError(getAddError());
					e.printStackTrace();
				}
			} else {
				try {
					deployService.updateRecord(model);
				} catch (SQLException e) {
					setError(getUpdateError());
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public void delete(int id) {
		try {
			userPlayListDaoImpl.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addCount(int idUser, int idSong) {
		SongStatisticDAOImpl songStatisticDAOImpl = new SongStatisticDAOImpl();
		return songStatisticDAOImpl.create(new SongStatistic()
				.setIdSong(idSong).setIdUser(idUser).setCount(1));

	}

	public void removeCount(int idUser) {
		SongStatisticDAOImpl songStatisticDAOImpl = new SongStatisticDAOImpl();
		try {
			songStatisticDAOImpl.delete(idUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int addSongPlaylist(int playlistId, int songId) {
		return playListDAOImpl.create(new PlayList().setIdUserPlayList(
				playlistId).setIdSong(songId));
	}

	public UserPlayList getUserPlaylist(int playlistId) {
		return userPlayListDaoImpl.readId(playlistId);
	}

	public List<UserPlayList> getAllUserPlaylists(int userId) {
		return userPlayListDaoImpl.getAll(userId);
	}

	public Map<UserPlayList, Integer> getAllUserPlaylistsStatistics(int userId) {
		List<UserPlayList> userPlayLists = userPlayListDaoImpl.getAll(userId);
		Map<UserPlayList, Integer> statisticMap = new HashMap<>();
		for (UserPlayList userPlayList : userPlayLists) {
			Integer size = Integer.valueOf(getPlaylistContent(
					userPlayList.getIdUserPlayList()).size());
			statisticMap.put(userPlayList, size);
		}
		return statisticMap;
	}

	public List<Song> getPlaylistContent(int playlistId) {
		return songDAOImpl.getPlaylistContent(playlistId);
	}

	public void deletePlaylistsSong(int playlistId, int songId) {
		try {
			playListDAOImpl.delete(playlistId, songId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
