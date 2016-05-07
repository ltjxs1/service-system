package cn.com.usth.jzp.filter

import cn.com.usth.jzp.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest


/**
 * Created by Administrator on 2016/5/7.
 */
@Component
public class SecurityFilter implements Filter {
    @Autowired
    UserService userService
    @Override
    void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest
        String uri =httpServletRequest.requestURI
        if(!uri.startsWith("/free")) {
            String token = servletRequest.getParameter("token")
            if(!token){
                throw new RuntimeException("没有权限！")
            }else{
                if(!userService.validate(token)){
                    throw new RuntimeException("权限不正确！")
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse)

    }

    @Override
    void destroy() {

    }
}
