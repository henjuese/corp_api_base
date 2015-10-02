package com.h2ord.corp.api.web.oauth2.filter;

import com.h2ord.corp.api.service.oauth2.TokenService;
import com.h2ord.corp.api.util.json.ResultUtil;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by chy on 14-10-20.
 */
@Component("oauthFilter")
public class OauthFilter implements Filter {

    @Autowired
    private TokenService tokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        OutputStream out = response.getOutputStream();
        response.setContentType("application/json");

        try {
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request,
                    ParameterStyle.HEADER);

            String accessToken = oauthRequest.getAccessToken();
            if(!tokenService.checkAccessToken(accessToken)){
                out.write(ResultUtil.result(405,"{'msg':'Token does not exist or has expired'}").getBytes("GBK"));
            }else{
                request.setAttribute("accessToken",accessToken);
                chain.doFilter(request, response);
            }
        } catch (OAuthSystemException e) {
            e.printStackTrace();
            out.write(ResultUtil.result(405,new JSONObject("{'msg':"+"'"+e.getMessage()+"'}").toString()).getBytes("GBK"));

        } catch (OAuthProblemException e) {
            e.printStackTrace();
            out.write(ResultUtil.result(405,new JSONObject("{'msg':"+"'"+e.getMessage()+"'}").toString()).getBytes("GBK"));
        }finally {
            out.flush();
            out.close();

        }


    }

    @Override
    public void destroy() {

    }
}
