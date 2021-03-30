package com.cjcoder.elephant;

import java.io.InputStream;

/**
 * @Author: Jeffery
 * @Date: 10:44 2020/8/25
 * @Description: DFS Operations
 */
public interface OpService {

    /**
     * 创建一个新的子目录
     *
     * @param dirPath 目录路径
     * @return
     */
    boolean mkdir(String dirPath) throws Exception;

    /**
     * 检查文件是否已经存在
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    boolean isExists(String fileName);

    /**
     * 创建文件并写入内容
     *
     * @param fileName
     * @param data
     * @throws Exception
     */
    void write(String fileName, byte[] data);

    /**
     * 创建文件并写入内容
     *
     * @param fileName
     * @param data
     * @throws Exception
     */
    void write(String fileName, String data);


    /**
     * 读单个文件
     *
     * @param filePath 绝对路径
     * @return
     */
    InputStream readFile(String filePath);
}
