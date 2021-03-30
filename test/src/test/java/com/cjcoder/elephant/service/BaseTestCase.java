package com.cjcoder.elephant.service;


import com.cjcoder.elephant.autoconfig.HdfsProperties;
import org.junit.Before;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;


@WebAppConfiguration
@ContextConfiguration
public class BaseTestCase {

    @Configuration
    @ComponentScan(
            basePackages = "com.cjcoder",
            includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "\\.service.+\\.impl\\..+Impl$")
    )
    static class TestConfig {

        @Bean
        public CacheManager cacheManager() {
            return new SimpleCacheManager();
        }


        @Bean
        public HdfsProperties hdfsProperties() {
            HdfsProperties hdfsProperties = new HdfsProperties();
            hdfsProperties.setReplication(1);
            hdfsProperties.setDfsUri("hdfs://hadoop0:9000");
            hdfsProperties.setDfsUser("root");
            hdfsProperties.setDomainContext("http://api.cjcoder.com");
            return hdfsProperties;
        }

    }

    protected MockMvc mockMvc;
    @Resource
    private WebApplicationContext wac;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

}