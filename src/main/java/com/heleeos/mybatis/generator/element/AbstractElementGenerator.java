package com.heleeos.mybatis.generator.element;

import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

/**
 *
 * Created by liyu on 2019/1/24.
 */
public abstract class AbstractElementGenerator extends AbstractXmlElementGenerator {

    /**
     * 获取Java类中的方法
     */
    public abstract Method getJavaMethod();

    /**
     * 获取显示的顺序, 数值越小, 越靠前
     */
    public abstract  int getIndex();
}
