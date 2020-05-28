package com.rearwave.blog.config;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author sunyi
 */
@Log4j2
@WebFilter(urlPatterns = "/**",filterName = "authFilter")
public class RearWaveAuthFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("authFilter initial......");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //不需授权的请求直接放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.debug("authFilter destroy......");
    }
}
