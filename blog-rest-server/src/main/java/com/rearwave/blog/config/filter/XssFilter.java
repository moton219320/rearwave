package com.rearwave.blog.config.filter;

import com.rearwave.blog.config.wrapper.RequestBodyServletWrapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 前端接口调用过滤器
 * @author sunyi
 */
@Order(1)
@Log4j2
@WebFilter(urlPatterns = "/*",filterName = "xssFilter")
public class XssFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("authFilter initial......");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 将request进行xss攻击防御处理，并将body内容取出
        RequestBodyServletWrapper request = new RequestBodyServletWrapper((HttpServletRequest) servletRequest);
        servletResponse.setCharacterEncoding("UTF-8");
        //不需授权的请求直接放行
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {
        log.debug("authFilter destroy......");
    }
}
