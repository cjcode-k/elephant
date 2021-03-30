package com.cjcoder.elephant.autoconfig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({HdfsProperties.class, HdfsConfigurationProperties.class})
public class HdfsServerAutoConfiguration {
}
