package com.bulain.image;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;

public class ThumbnailsDemo {

	@Test
	public void testWatermark() throws IOException {
		InputStream is = new FileInputStream("src/test/resources/images/image2.jpg");
		InputStream wis = new FileInputStream("src/test/resources/images/2400_2400.png");
		OutputStream os = new FileOutputStream("target/image2.jpg");

		Thumbnails.of(is)
			.watermark(ImageIO.read(wis))
			.scale(1f)
            .outputQuality(0.5f)
            .toOutputStream(os);
	}

}
