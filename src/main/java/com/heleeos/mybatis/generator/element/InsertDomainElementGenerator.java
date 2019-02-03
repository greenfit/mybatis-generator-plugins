package com.heleeos.mybatis.generator.element;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertSelectiveElementGenerator;

/**
 * 根据实体类查询
 * Created by liyu on 2019/1/7.
 */
public class InsertDomainElementGenerator extends AbstractElementGenerator {

    public InsertDomainElementGenerator() {
        super();
    }

    @Override
    public int getIndex() {
        return 4;
    }

    @Override
    public Method getJavaMethod() {
        Method method = new Method();
        method.setName("insert");
        method.addJavaDocLine("/** 新增一个实体对象 */");
        method.setReturnType(new FullyQualifiedJavaType("int"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "domain"));
        return method;
    }

    @Override
    public void addElements(XmlElement parentElement) {
        introspectedTable.setInsertSelectiveStatementId("insert");
        useMybatisGenerator(new InsertSelectiveElementGenerator(), parentElement);
    }
}
