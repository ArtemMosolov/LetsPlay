package com.epam.core.util;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class SongHeaderUtil {
	private static final String UNKNOWN = "Unknown";
	AudioFile audioFile = null;
	Tag tag = null;

	public SongHeaderUtil(File file) throws CannotReadException, IOException,
			TagException, ReadOnlyFileException, InvalidAudioFrameException {
		this.audioFile = AudioFileIO.read(file);
		tag = audioFile.getTagOrCreateAndSetDefault();

	}

	public String getSongName() {
		String songName = tag.getFirst(FieldKey.TITLE);
		if (songName.isEmpty()) {
			songName = UNKNOWN;
		}
		return songName;
	}

	public String getSongArtistName() {
		String artistName = tag.getFirst(FieldKey.ARTIST);
		if (artistName.isEmpty()) {
			artistName = UNKNOWN;
		}
		return artistName;
	}

	public String getSongAblumName() {
		String albumName = tag.getFirst(FieldKey.ALBUM);
		if (albumName.isEmpty()) {
			albumName = UNKNOWN;
		}
		return albumName;
	}

	public String getSongGenre() {
		String songGenre = tag.getFirst(FieldKey.GENRE);
		if (songGenre.isEmpty()) {
			songGenre = UNKNOWN;
		}
		return songGenre;
	}

	public void setSongName(String name) throws CannotWriteException,
			KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.TITLE, name);
		AudioFileIO.write(audioFile);
	}

	public void setSongAlbumName(String name) throws CannotWriteException,
			KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.ALBUM, name);
		AudioFileIO.write(audioFile);
	}

	public void setSongArtistName(String name) throws CannotWriteException,
			KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.ARTIST, name);
		AudioFileIO.write(audioFile);
	}

	public void setSongGenre(String name) throws CannotWriteException,
			KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.GENRE, name);
		AudioFileIO.write(audioFile);
	}

}
