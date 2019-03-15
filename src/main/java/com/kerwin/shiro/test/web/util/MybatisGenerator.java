package com.kerwin.shiro.test.web.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MybatisGenerator
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-02-28 14:05
 */
public class MybatisGenerator
{
    public void generator() throws Exception
    {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        // 指定配置文件
        File configFile = new File(ResourceUtils.getFile("classpath:generator/mybatis-generator.xml").getPath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    // 执行main方法以生成代码
    public static void main(String[] args)
    {
        try
        {
            MybatisGenerator generatorSqlmap = new MybatisGenerator();
            generatorSqlmap.generator();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}