package com.ivan.itoutiao.filter;

import com.ivan.itoutiao.entity.User;
import com.ivan.itoutiao.service.UserService;
import com.ivan.itoutiao.service.impl.UserServiceImpl;
import com.ivan.itoutiao.utils.IvanTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * @author Ivan
 * @Title: LoginTokenFilter 登录拦截器
 * @date 2021/1/2915:14
 */
public class LoginInterceptor implements HandlerInterceptor {

    UserService userService;

    public LoginInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //解决跨域（浏览发请求之前会先OPTIONS请求，这个请求不会携带token参数）
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            return true;
        }
        //获取token
        String token = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(token) && token.contains("Bearer")) {
            String tokenValue = token.split(" ")[1];
            User user = IvanTool.getUserByToken(userService, tokenValue);
            //验证token是否合法
            if (user == null) {
                //token验证失败
                response.setStatus(401);
                return false;
            }
            System.out.println("token验证成功");
            return true;
        } else {
            //用户没有登录
            response.setStatus(403);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
