package com.example.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器
 * 访问所有路径（除静态路径）前判断session是否有isAllowPass且为true，
 * 有则用户已通过登录，可进行下一步访问，无则回到首页
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor
{
//	@Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 检查每个到来的请求对应的session域中是否有登录标识
//        Object isAllowPass = request.getSession().getAttribute("isAllowPass");
//        if (null != isAllowPass && !((boolean)isAllowPass)) {
//            // 未登录，重定向到登录页
//        	return true;  
//        }
//        response.sendRedirect("/");
//        return false;
//    }

}
