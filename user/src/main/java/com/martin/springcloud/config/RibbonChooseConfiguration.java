package com.martin.springcloud.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//这里的config类所在的包有一定讲究，就是你的包不能被@ComponentScan或者@SpringBootApplication扫描到。
@Configuration
public class RibbonChooseConfiguration {

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        //默认RoundRobinRule
        return new RandomRule();
    }
}
