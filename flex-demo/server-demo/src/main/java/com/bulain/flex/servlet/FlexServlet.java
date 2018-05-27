package com.bulain.flex.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlexServlet extends HttpServlet {
    private static final long serialVersionUID = 8775603959765259000L;

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<?xml version=\"1.0\"?>\n");
        writer.println("<salesdata>");
        writer.println("<item type=\"CDs\">");
        writer.println("<profit>");
        writer.println("  <beforetax>750</beforetax>");
        writer.println("  <aftertax>600</aftertax>");
        writer.println("</profit>");
        writer.println("<sales>1000.23</sales>");
        writer.println("</item>");
        writer.println("<item type=\"DVD\">");
        writer.println("<profit>");
        writer.println("  <beforetax>550.34</beforetax>");
        writer.println("  <aftertax>350.10</aftertax>");
        writer.println("</profit>");
        writer.println("<sales>1010.98</sales>");
        writer.println("</item>");
        writer.println("</salesdata>");
    }

}
