package com.h2ord.corp.api.web.shiro.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by chy on 14-10-13.
 */
public class ShiroFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        Principal principal = httpRequest.getUserPrincipal();

//        if (principal != null) {
//            Subject subject = SecurityUtils.getSubject();
//            // 为了简单，这里初始化一个用户。实际项目项目中应该去数据库里通过名字取用户：
//            // 例如：User user = userService.getByAccount(principal.getName());
//            User user = new User();
//            user.setName("shiro");
//            user.setPassword("123456");
//
//            if (user.getName().equals(principal.getName())) {
//                UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user
//                        .getPassword());
//                subject = SecurityUtils.getSubject();
//                subject.login(token);
//                subject.getSession();
//            } else {
//                // 如果用户为空，则subjects信息登出
//                if (subject != null) {
//                    subject.logout();
//                }
//            }
//        }
        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }
}
