package com.heleeos.mybatis.generator.element;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 根据实体类查询
 * Created by liyu on 2019/1/7.
 */
public class CountByDomainElementGenerator extends AbstractElementGenerator {

    public CountByDomainElementGenerator() {
        super();
    }

    @Override
    public int getIndex() {
        return 3;
    }

    @Override
    public Method getJavaMethod() {
        Method method = new Method();
        method.setName("countByDomain");
        method.addJavaDocLine("/** 实体对象作为查询条件, 统计个数 */");
        method.setReturnType(new FullyQualifiedJavaType("long"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "domain"));
        return method;
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement xmlElement = new XmlElement("select");
        xmlElement.addAttribute(new Attribute("id", "countByDomain"));
        xmlElement.addAttribute(new Attribute("resultType", "int"));

        String sql = String.format("select count(0) from %s", introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        xmlElement.addElement(new TextElement(sql));
        xmlElement.addElement(getWhereElement());
        parentElement.addElement(xmlElement);
    }
}
