package com.example;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "encodingServlet", urlPatterns = "/encoding")
public class EncodingServlet extends HttpServlet {

    public static final String 인코딩 = "인코딩";
    public static final String 인코딩1234 = "인코딩1234";
    public static final String 인코딩1234_ = "인코딩1234_#@$%&*?/";
    public static final String ENCODING = "encoding";
    public static final String ENCODING1234 = "encoding1234";
    public static final String ENCODING1234_ = "encoding1234_#@$%&*?/";

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        getServletContext().log("init() 호출");
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        getServletContext().log("service() 호출");
        response.getWriter().write("korean: " + 인코딩 + "\n");
        response.getWriter().write("korean & number: " + 인코딩1234 + "\r\n");
        response.getWriter().print("korean & number & special symbol: " + 인코딩1234_ + "\n");
        response.getWriter().println("english: " + ENCODING);
        response.getWriter().println("english & number: " + ENCODING1234);
        response.getWriter().println("english & number & special symbol: " + ENCODING1234_);
    }

    @Override
    public void destroy() {
        getServletContext().log("destroy() 호출");
    }
}
