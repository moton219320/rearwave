package com.rearwave.blog.config.filter;

import com.rearwave.blog.config.wrapper.RequestBodyServletWrapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 前端接口调用过滤器
 * @author sunyi
 */
@Order(1)
@Log4j2
@WebFilter(urlPatterns = "/*",filterName = "xssFilter")
public class XssFilter implements Filter {

    private final List<String> uploadPrefixes = Arrays.asList("/attach","/portal");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("authFilter initial......");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String api = req.getRequestURI();
        for (String pre : uploadPrefixes) {
            if (api.startsWith(pre)) {
                //对于文件上传接口，不做xss处理。
                log.debug("文件上传接口，不做XSS处理 - URI：{}",api);
                filterChain.doFilter(req, servletResponse);
                return;
            }
        }
        // 将request进行xss攻击防御处理，并将body内容取出
        RequestBodyServletWrapper request = new RequestBodyServletWrapper(req);
        servletResponse.setCharacterEncoding("UTF-8");
        //不需授权的请求直接放行
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {
        log.debug("authFilter destroy......");
    }
}
