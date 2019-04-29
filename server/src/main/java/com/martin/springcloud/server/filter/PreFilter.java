package com.martin.springcloud.server.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zengguoqiang
 * @date: 创建时间：2019/4/29 11:00
 * @description:
 * pre过滤器。在请求被路由之前调用。Zuul请求微服务之前。比如请求身份验证，选择微服务实例，记录调试信息等。
 * route过滤器。负责转发请求到微服务。原始请求在此构建，并使用Apache HttpClient或Netflix Ribbon发送原始请求。
 * post过滤器。在route和error过滤器之后被调用。可以在响应添加标准HTTP Header、收集统计信息和指标，以及将响应发送给客户端等。
 * error过滤器。在处理请求发生错误时被调用。
 */
@Component
public class PreFilter extends ZuulFilter {

    // 指定过滤器类型
    @Override
    public String filterType() {
        return "pre";
    }

    // 过滤器顺序，数字越小越先执行
    @Override
    public int filterOrder() {
        return 0;
    }

    // 是否使用该过滤器。
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // 过滤器具体执行的操作
    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String requestUri = request.getRequestURI();
        System.out.println("pre过滤器，请求的URI：{}" + requestUri);
        return null;
    }

}
