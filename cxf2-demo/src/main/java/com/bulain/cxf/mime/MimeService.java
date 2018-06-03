package com.bulain.cxf.mime;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

@Path("/mime")
public interface MimeService {

    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    Response upload(@Multipart("id") String id,
            @Multipart(value = "file") Attachment file);
    
    @POST
    @Path("/multiUpload")
    @Consumes("multipart/form-data")
    Response multiUpload(MultipartBody body);

    @GET
    @Path("/download/{id}")
    Response download(@PathParam("id") String id);

}
