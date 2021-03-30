package com.cjcoder.elephant.service.impl;

import com.cjcoder.elephant.autoconfig.HdfsConfigurationProperties;
import com.cjcoder.elephant.autoconfig.HdfsProperties;
import com.cjcoder.elephant.exception.HdfsOperationException;
import com.cjcoder.elephant.service.HdfsOpservice;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author: Jeffery
 * @Date: 14:04 2021/3/9
 * @Description:
 */
@Service("hdfsOpservice")
public class HdfsOpServiceImpl implements HdfsOpservice {
    private static final Logger logger = LoggerFactory.getLogger(HdfsOpServiceImpl.class);

    @Resource
    private HdfsProperties hdfsProperties;
    @Resource
    private HdfsConfigurationProperties hdfsConfigurationProperties;

    public FileSystem getFileSystem() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set(hdfsConfigurationProperties.getReplication(), String.valueOf(hdfsProperties.getReplication()));
        conf.set(hdfsConfigurationProperties.getUseDataNodeHostname(), "true");
        return FileSystem.newInstance(new URI(hdfsProperties.getDfsUri()), conf, hdfsProperties.getDfsUser());
    }

    public FileSystem getFileSystemByCache() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set(hdfsConfigurationProperties.getReplication(), String.valueOf(hdfsProperties.getReplication()));
        return FileSystem.get(new URI(hdfsProperties.getDfsUri()), conf, hdfsProperties.getDfsUser());
    }

    @Override
    public boolean mkdir(String dirPath) throws Exception {
        FileSystem fileSystem = this.getFileSystem();
        return fileSystem.mkdirs(new Path(dirPath));
    }

    @Override
    public boolean isExists(String fileName) {
        try {
            return this.getFileSystemByCache().exists(new Path(fileName));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void write(String fileName, byte[] data) throws HdfsOperationException {
        try {
            FSDataOutputStream out = getFileSystem().create(new Path(fileName));
            out.write(data);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new HdfsOperationException("write file by bytes to hdfs failed.");
        }

    }

    @Override
    public void write(String fileName, String data) throws HdfsOperationException {
        try {
            FSDataOutputStream out = getFileSystem().create(new Path(fileName));
            out.writeUTF(data);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new HdfsOperationException("write file by String to hdfs failed.");
        }
    }

    @Override
    public InputStream readFile(String filePath) throws HdfsOperationException {
        try {
            return getFileSystem().open(new Path(filePath));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new HdfsOperationException("read file from hdfs failed. filePath:[" + filePath + "].");
        }
    }


}
