package com.heleeos.mybatis.generator.element;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeySelectiveElementGenerator;

/**
 * 根据实体类查询
 * Created by liyu on 2019/1/7.
 */
public class UpdateDomainElementGenerator extends AbstractElementGenerator {

    public UpdateDomainElementGenerator() {
        super();
    }

    @Override
    public int getIndex() {
        return 5;
    }

    @Override
    public Method getJavaMethod() {
        Method method = new Method();
        method.setName("update");
        method.addJavaDocLine("/** 根据主键进行更新实体对象 */");
        method.setReturnType(new FullyQualifiedJavaType("int"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "domain"));
        return method;
    }

    @Override
    public void addElements(XmlElement parentElement) {
        useMybatisGenerator(new UpdateByPrimaryKeySelectiveElementGenerator(), parentElement);
    }
}
