package com.epam;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagException;

import com.epam.core.util.SongHeaderUtil;

public class AudioHeaderTest {
	static SongHeaderUtil util;

	public static void main(String[] args) {

		try {
			util = new SongHeaderUtil(new File(
					"E:\\test\\The black keys - Psychotic girl.mp3"));
		} catch (CannotReadException | IOException | TagException
				| ReadOnlyFileException | InvalidAudioFrameException e1) {
			e1.printStackTrace();
		}

		try {
			util.setSongName("Psycotic Girl");
			util.setSongArtistName("The black keys");
			util.setSongAlbumName("The black keys");
			// util.setSongSongGenre("Rock");
		} catch (KeyNotFoundException | FieldDataInvalidException
				| CannotWriteException e) {
			e.printStackTrace();
		}

		System.out.println(util.getSongName() + "\n");
		System.out.println(util.getSongArtistName() + "\n");
		System.out.println(util.getSongAblumName() + "\n");
		System.out.println(util.getSongGenre() + "\n");
	}
}
