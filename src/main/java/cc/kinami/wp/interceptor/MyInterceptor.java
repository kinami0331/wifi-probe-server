package cc.kinami.wp.interceptor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;

@Component
@AllArgsConstructor
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws IOException {

//        BufferedReader bufferedReader = httpServletRequest.getReader();
//        String str;
//        StringBuilder wholeStr = new StringBuilder();
//        while ((str = bufferedReader.readLine()) != null) {
//            wholeStr.append(str);
//        }
//
//        System.out.println(httpServletRequest.getHeader("Content-Length"));
//        System.out.println(wholeStr);
//        System.out.println(wholeStr.length());

        return true;
    }
}
