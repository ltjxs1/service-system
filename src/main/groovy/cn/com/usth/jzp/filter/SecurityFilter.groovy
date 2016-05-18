package cn.com.usth.jzp.filter

import cn.com.usth.jzp.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.servlet.*
import javax.servlet.http.HttpServletRequest

/**
 * Created by Administrator on 2016/5/7.
 */
@Component
public class SecurityFilter implements Filter {
    @Autowired
    UserService userService

    String commonPath = "/common"
    String masterPath = "/master"
    String freePath = "/free"

    @Override
    void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest
        String uri = httpServletRequest.requestURI
        String token = servletRequest.getParameter("token")
        if (!ensureToken(uri, token)) {
            throw new RuntimeException("权限不正确！")
        }
        filterChain.doFilter(servletRequest, servletResponse)
    }

    boolean ensureToken(String uri, String token) {
        if (uri.startsWith(freePath)) {
            return true
        }
        if (uri.startsWith(masterPath)) {
            return userService.masterValidate(token)
        }
        if (uri.startsWith(commonPath)) {
            return userService.validate(token) || userService.masterValidate(token)
        }
        return userService.validate(token)
    }

    @Override
    void destroy() {}
}
