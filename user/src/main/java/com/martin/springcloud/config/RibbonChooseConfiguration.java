package com.martin.springcloud.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonChooseConfiguration {

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        //默认RoundRobinRule
        return new RandomRule();
    }
}
