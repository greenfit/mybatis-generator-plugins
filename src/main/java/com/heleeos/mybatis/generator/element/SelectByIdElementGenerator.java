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
public class SelectByIdElementGenerator extends AbstractElementGenerator {

    public SelectByIdElementGenerator() {
        super();
    }

    @Override
    public int getIndex() {
        return 1;
    }

    @Override
    public Method getJavaMethod() {
        Method method = new Method();
        method.setName("selectById");
        method.addJavaDocLine("");
        method.addJavaDocLine("/** 根据主键查询实体 */");
        method.setReturnType(new FullyQualifiedJavaType("T"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("int"), "id"));
        return method;
    }

    @Override
    public void addElements(XmlElement parentElement) {
        String parameterType = introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType().toString();

        XmlElement xmlElement = new XmlElement("select");
        xmlElement.addAttribute(new Attribute("id", "selectById"));
        xmlElement.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
        xmlElement.addAttribute(new Attribute("parameterType", parameterType));

        String column = introspectedTable.getBaseColumnListId();
        String table = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();

        String sql = String.format("select <include refid=\"%s\"/> from %s %s", column, table, getWhereInfo());

        xmlElement.addElement(new TextElement(sql));

        parentElement.addElement(xmlElement);
    }
}
