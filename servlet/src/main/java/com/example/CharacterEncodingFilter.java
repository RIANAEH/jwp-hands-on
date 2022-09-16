package com.example;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.getServletContext().log("doFilter() 호출");

        // 서블릿에서 데이터 인코딩을 utf-8로 설정
        response.setCharacterEncoding("utf-8");
        // 브라우저에게 html이 utf-8형이라고 전달
        response.setContentType("text/plain; charset=utf-8");

        chain.doFilter(request, response);
    }
}
