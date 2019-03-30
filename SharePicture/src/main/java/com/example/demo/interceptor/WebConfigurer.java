package com.example.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置，拦截所有访问路径，排除静态路径
 * @author Administrator
 *
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer
{
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor).excludePathPatterns("/static/**");
        // 拦截路径
        loginRegistry.addPathPatterns("/**");
        // 排除路径
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("/account/**");
        // 排除资源请求
        loginRegistry.excludePathPatterns("/**.**");
        loginRegistry.excludePathPatterns("/**/**.**");
    }

}
