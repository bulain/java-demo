package com.bulain.jasper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Disabled
class HtmlToPdfTest {

    @Test
    @SneakyThrows
    void htmlToPdf4flying() {

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
        String outputPath = "target/test01.pdf";

        InputStream is = new ClassPathResource(htmlPath).getInputStream();
        String html = IOUtils.toString(is, StandardCharsets.UTF_8);
        html = html.replace("####0", barcode);
        html = html.replace("####1", code);
        html = html.replace("XXXX0", qrcode);
        html = html.replace("XXXX1", code);
        ByteArrayInputStream bais = new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8));
        Document document = XMLResource.load(bais).getDocument();

        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        String path = new ClassPathResource("fonts/simsun.ttf").getPath();
        fontResolver.addFont(path, "Identity-H",false);
        renderer.setDocument(document, null);
        renderer.layout();
        renderer.createPDF(Files.newOutputStream(Paths.get(outputPath)));

    }


}