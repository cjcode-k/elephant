package com.cjcoder.elephant.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cjcoder.hdfsconfig")
public class HdfsConfigurationProperties {
    //备份数量 key
    private String replication = "dfs.replication";
    private String useDataNodeHostname = "dfs.client.use.datanode.hostname";

    public String getReplication() {
        return replication;
    }

    public void setReplication(String replication) {
        this.replication = replication;
    }

    public String getUseDataNodeHostname() {
        return useDataNodeHostname;
    }

    public void setUseDataNodeHostname(String useDataNodeHostname) {
        this.useDataNodeHostname = useDataNodeHostname;
    }
}

