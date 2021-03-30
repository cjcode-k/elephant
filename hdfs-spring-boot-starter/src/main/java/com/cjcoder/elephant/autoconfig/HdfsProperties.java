package com.cjcoder.elephant.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cjcoder.hdfs")
public class HdfsProperties {
    //dfs链接
    private String dfsUri;
    //dfs用户名
    private String dfsUser;
    //数据块备份数量
    private int replication = 3;
    //资源访问域名
    private String domainContext;
    //缓存7天  1000 * 3600 * 24 * 7 = 604800000
    private Long expires;


    public String getDomainContext() {
        return domainContext;
    }

    public void setDomainContext(String domainContext) {
        this.domainContext = domainContext;
    }

    public String getDfsUri() {
        return dfsUri;
    }

    public void setDfsUri(String dfsUri) {
        this.dfsUri = dfsUri;
    }

    public String getDfsUser() {
        return dfsUser;
    }

    public void setDfsUser(String dfsUser) {
        this.dfsUser = dfsUser;
    }

    public int getReplication() {
        return replication;
    }

    public void setReplication(int replication) {
        this.replication = replication;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}
