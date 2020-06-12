package com.rearwave.blog.config.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 前端接口调用过滤器
 * @author sunyi
 */
@Log4j2
@WebFilter(urlPatterns = "/api/**",filterName = "authFilter")
public class RearWaveAuthFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("authFilter initial......");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String api = request.getRequestURI();

        //不需授权的请求直接放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.debug("authFilter destroy......");
    }
}
