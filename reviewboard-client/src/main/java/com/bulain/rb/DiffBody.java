package com.bulain.rb;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.content.StringBody;

public class DiffBody extends StringBody {

    private String filename;

    public DiffBody(String filename, String text) throws UnsupportedEncodingException {
        super(text, MIME.DEFAULT_CHARSET);
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
