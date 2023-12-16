package com.bulain.jasper;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

class HtmlToPngDemo {

    @Test
    @SneakyThrows
    void htmlToPng() {

        String htmlPath = "src/test/resources/tdata/test01.html";
        String outputPath = "target/test01.png";
        int width = 300;
        int height = 200;

        Java2DRenderer renderer = new Java2DRenderer(htmlPath, width, height);
        BufferedImage image = renderer.getImage();
        ImageIO.write(image, "png", new File(outputPath));

    }

}