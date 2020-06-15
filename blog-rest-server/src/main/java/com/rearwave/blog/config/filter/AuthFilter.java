package com.rearwave.blog.config.filter;

import com.rearwave.blog.component.response.CodeEnum;
import com.rearwave.blog.component.response.R;
import com.rearwave.blog.component.utils.GSON;
import com.rearwave.blog.component.utils.WebUtils;
import com.rearwave.blog.model.dto.AuthUserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 管理端登录校验
 * @author sunyi
 */
@Order(2)
@Log4j2
@WebFilter(urlPatterns = "/*",filterName = "authFilter",
        initParams = {
        @WebInitParam(name = "ignoreUri",value = "/api/**,/callback/**,/auth/**")
})
public class AuthFilter implements Filter {
    /**
     * 不做登录校验的路径
     */
    private List<String> ignoreUri;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("初始化登录校验拦截器配置信息");
        ignoreUri = Arrays.asList(filterConfig.getInitParameter("ignoreUri").split(","));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.trace("exclude uri - {}",ignoreUri);
        HttpServletRequest req = (HttpServletRequest) request;
        if (match(req.getRequestURI())){
            log.debug("白名单地址，直接放行 - {}",req.getRequestURI());
            chain.doFilter(request,response);
            return;
        }
        //校验是否登录了
        AuthUserDto user = WebUtils.getCurrentUser();
        if (null == user){
            log.debug("未登录，不允许访问接口.用户IP：{}",req.getRemoteAddr());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(GSON.toJSONString(R.error(CodeEnum.TIME_OUT)));
            return;
        }
        chain.doFilter(req,response);
    }

    private boolean match(String uri){
        AntPathMatcher matcher = new AntPathMatcher();
        boolean flag = false;
        for (String pattern : ignoreUri) {
            if (matcher.match(pattern,uri)){
                flag = true;
            }
        }
        return flag;
    }
}
