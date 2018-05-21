package com.bulain.springmvc.controller;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value = "/mime/")
public class MimeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "download/{uuid}", method = RequestMethod.GET)
    public void download(@PathVariable("uuid") String uuid, HttpServletResponse res) {
        OutputStream os = null;
        InputStream is = null;
        try {
            os = res.getOutputStream();
            is = new ClassPathResource("log4j.properties").getInputStream();
            res.setHeader("Content-Disposition", "attachment; filename=log4j.properties");  
            res.setContentType("application/octet-stream; charset=utf-8");  
            IOUtils.copy(is, os);
        } catch (Exception e) {
            logger.error("download()", e);
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
    }

    @ResponseBody
    @RequestMapping(value = "uploadParam", method = RequestMethod.POST)
    public String uploadParam(@RequestParam("file") MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream is = file.getInputStream();

            System.out.println(originalFilename);
            System.out.println(contentType);
            System.out.println(is);
        } catch (Exception e) {
            logger.error("uploadParam()", e);
        }

        return "ok";
    }

    @ResponseBody
    @RequestMapping(value = "uploadRequest", method = RequestMethod.POST)
    public String uploadRequest(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");

        try {
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            InputStream is = file.getInputStream();

            System.out.println(originalFilename);
            System.out.println(contentType);
            System.out.println(is);
        } catch (Exception e) {
            logger.error("uploadRequest()", e);
        }

        return "ok";
    }

}
