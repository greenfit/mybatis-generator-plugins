package com.heleeos.mybatis.generator;

import com.heleeos.mybatis.generator.xml.BaseMapperXmlGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;

/**
 * javaClient 生成器
 * Created by liyu on 2019/1/7.
 */
public class BaseMapperGenerator extends JavaMapperGenerator {

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new BaseMapperXmlGenerator();
    }
}
