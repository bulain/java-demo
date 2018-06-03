package com.bulain.cxf.mime;

import java.io.File;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

public class MimeServiceImpl implements MimeService {

    public Response upload(String id, Attachment file) {
        try {
            DataHandler dataHandler = file.getDataHandler();
            String contentType = dataHandler.getContentType();
            InputStream is = dataHandler.getInputStream();
            String name = dataHandler.getName();

            System.out.println(contentType);
            IOUtils.toString(is);
            System.out.println(name);
            System.out.println(id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.ok().build();
    }

    public Response multiUpload(MultipartBody body) {

        Attachment id = body.getAttachment("id");
        Attachment file = body.getAttachment("file");

        System.out.println(id);
        System.out.println(file);

        return Response.ok().build();
    }

    public Response download(String id) {
        File file = new File("src/test/resources/cxf.properties");
        ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment;filename='cxf.properties'");
        return response.build();
    }

}
