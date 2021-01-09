package cc.kinami.wp.config;

import cc.kinami.wp.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WPWebMvcConfig implements WebMvcConfigurer {
    private MyInterceptor myInterceptor;

    @Autowired
    public void setMyInterceptor(MyInterceptor myInterceptor) {
        this.myInterceptor = myInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(myInterceptor).addPathPatterns("/api/**");
    }

}
