package com.hmdp.interceptor;

import com.hmdp.dto.UserDTO;
import com.hmdp.utils.UserHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    // 打开需要实现的接口方法：ctrl+i
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1 获取session
        HttpSession session = request.getSession();
        // 2 获取session中的用户
        Object user = session.getAttribute("user");
        // 3 判断用户是否存在
        if (user == null) {
            // 4 不存在，拦截，返回401状态码（未授权）
            response.setStatus(401);
            return false;
        }
        // 5 存在，保存用户信息到ThreadLocal
        UserHolder.saveUser((UserDTO) user);
        // 6 放行

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        UserHolder.removeUser();
    }
}
