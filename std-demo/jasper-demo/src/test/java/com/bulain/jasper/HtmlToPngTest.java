package com.bulain.jasper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import cz.vutbr.web.css.MediaSpec;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.fit.cssbox.awt.GraphicsEngine;
import org.fit.cssbox.css.CSSNorm;
import org.fit.cssbox.css.DOMAnalyzer;
import org.fit.cssbox.io.DOMSource;
import org.fit.cssbox.io.DefaultDOMSource;
import org.fit.cssbox.io.DocumentSource;
import org.fit.cssbox.io.StreamDocumentSource;
import org.fit.cssbox.layout.Dimension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.xhtmlrenderer.resource.XMLResource;
import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Disabled
class HtmlToPngTest {

    @Test
    @SneakyThrows
    void htmlToPng4flying() {

        String code = "1234567890";
        String barcode = "";
        {
            BitMatrix bitMatrix = new Code128Writer().encode(code, BarcodeFormat.CODE_128, 100, 30);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            barcode = "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
        }
        String d2code = "12345678901234567890";
        String qrcode = "";
        {
            BitMatrix bitMatrix = new QRCodeWriter().encode(d2code, BarcodeFormat.QR_CODE, 60, 60);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            qrcode = "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
        }

        String htmlPath = "tdata/test01.html";
        String outputPath = "target/test01.png";

        InputStream is = new ClassPathResource(htmlPath).getInputStream();
        String html = IOUtils.toString(is, StandardCharsets.UTF_8);
        html = html.replace("####0", barcode);
        html = html.replace("####1", code);
        html = html.replace("XXXX0", qrcode);
        html = html.replace("XXXX1", d2code);
        ByteArrayInputStream bais = new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8));
        Document document = XMLResource.load(bais).getDocument();

        Java2DRenderer renderer = new Java2DRenderer(document, 200, 300);
        BufferedImage image = renderer.getImage();
        ImageIO.write(image, "png", new File(outputPath));

    }

    @Test
    @SneakyThrows
    void htmlToPng4cssbox() {

        String code = "1234567890";
        String barcode = "";
        {
            BitMatrix bitMatrix = new Code128Writer().encode(code, BarcodeFormat.CODE_128, 100, 30);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            barcode = "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
        }
        String qrcode = "";
        {
            BitMatrix bitMatrix = new QRCodeWriter().encode(code, BarcodeFormat.QR_CODE, 60, 60);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            qrcode = "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
        }

        String htmlPath = "tdata/test01.html";
        String outputPath = "target/test02.png";

        InputStream is = new ClassPathResource(htmlPath).getInputStream();
        String html = IOUtils.toString(is, StandardCharsets.UTF_8);
        html = html.replace("####0", barcode);
        html = html.replace("####1", code);
        html = html.replace("XXXX0", qrcode);
        html = html.replace("XXXX1", code);
        ByteArrayInputStream bais = new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8));

        DocumentSource docSource = new StreamDocumentSource(bais, null, "text/html; charset=utf-8");
        DOMSource parser = new DefaultDOMSource(docSource);
        Document doc = parser.parse();

        MediaSpec media = new MediaSpec("screen");
        media.setDimensions(200, 300);
        media.setDeviceDimensions(200, 300);

        DOMAnalyzer da = new DOMAnalyzer(doc, docSource.getURL(),false);
        da.setMediaSpec(media);
        da.attributesToStyles();
        da.addStyleSheet(null, CSSNorm.stdStyleSheet(), DOMAnalyzer.Origin.AGENT);
        da.addStyleSheet(null, CSSNorm.userStyleSheet(), DOMAnalyzer.Origin.AGENT);
        da.addStyleSheet(null, CSSNorm.formsStyleSheet(), DOMAnalyzer.Origin.AGENT);
        da.getStyleSheets();

        GraphicsEngine contentCanvas = new GraphicsEngine(da.getRoot(), da, docSource.getURL());
        contentCanvas.setAutoMediaUpdate(false);
        contentCanvas.getConfig().setClipViewport(true);
        contentCanvas.getConfig().setLoadImages(true);
        contentCanvas.getConfig().setLoadBackgroundImages(true);
        contentCanvas.createLayout(new Dimension(200, 300));
        BufferedImage image = contentCanvas.getImage();
        ImageIO.write(image, "png", new File(outputPath));

    }


}