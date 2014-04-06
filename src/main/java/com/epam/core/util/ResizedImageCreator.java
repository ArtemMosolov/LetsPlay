package com.epam.core.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

public class ResizedImageCreator {

	private BufferedImage createResizedCopy(Image originalImage,
			int scaledWidth, int scaledHeight) {

		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,
				BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D graphics = scaledBI.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		graphics.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
		graphics.dispose();

		return scaledBI;
	}

	public File resize(File file, int width, int height) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage img2 = createResizedCopy(img, width, height);
		File outputfile = file;
		try {
			ImageIO.write(img2,
					FilenameUtils.getExtension(file.getAbsolutePath()),
					outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputfile;
	}
}
