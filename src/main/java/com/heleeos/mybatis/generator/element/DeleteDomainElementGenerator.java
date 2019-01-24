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
public class DeleteDomainElementGenerator extends AbstractElementGenerator {

    public DeleteDomainElementGenerator() {
        super();
    }

    @Override
    public int getIndex() {
        return 6;
    }

    @Override
    public Method getJavaMethod() {
        Method method = new Method();
        method.setName("delete");
        method.addJavaDocLine("/** 根据主键删除实体对象 */");
        method.setReturnType(new FullyQualifiedJavaType("int"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("int"), "id"));
        return method;
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement xmlElement = new XmlElement("delete");
        xmlElement.addAttribute(new Attribute("id", "delete"));

        String table = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();

        String sql = String.format("delete from %s %s", table, getWhereInfo());

        xmlElement.addElement(new TextElement(sql));

        parentElement.addElement(xmlElement);
    }
}
