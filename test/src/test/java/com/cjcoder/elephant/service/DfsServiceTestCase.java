package com.cjcoder.elephant.service;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.*;


/**
 * @Author: Jeffery
 * @Date: 17:07 2020/9/9
 * @Description:
 */
@SpringBootTest
public class DfsServiceTestCase {

    @Resource
    private HdfsOpservice hdfsOpservice;

    @Test
    public void mkdir() throws Exception {
        boolean mkdir = hdfsOpservice.mkdir("/hello");
        Assert.assertTrue(mkdir);
    }

    @Test
    public void isExists() throws Exception {
        boolean exists = hdfsOpservice.isExists("/hello");
        Assert.assertTrue(exists);

    }

    @Test
    public void write() throws Exception {
        hdfsOpservice.write("hello.txt", "hello world!");
    }

    @Test
    public void readFile() throws Exception {
        InputStream inputStream = hdfsOpservice.readFile("hello.txt");
        System.out.println(new String(toByteArray(inputStream), "utf-8"));
    }

    protected static void writeToLocal(String localPath, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(localPath);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }

    protected byte[] inputStream2ByteArray(String filePath) throws IOException {

        InputStream in = new FileInputStream(filePath);
        byte[] data = toByteArray(in);
        in.close();

        return data;
    }

    protected byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();

    }

}
