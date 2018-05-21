package com.bulain.common.servlet;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

public abstract class Log4jFilter implements Filter {
    private static final String[] FILE_EXTS = new String[]{".js", ".css", ".bmp", ".jpg", ".tiff", ".gif", ".pcx", ".tga",
            "exif", ".fpx", ".svg", ".psd", ".cdr", ".pcd", ".dxf", ".ufo", ".eps", ".ai", ".raw"};

    protected boolean excludeUrl(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        for (String suffix : FILE_EXTS) {
            if (requestURI.endsWith(suffix)) {
                return true;
            }
        }

        return false;
    }
}
