package com.heleeos.mybatis.generator.element;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

/**
 * 根据实体类查询
 * Created by liyu on 2019/1/7.
 */
public class SelectByDomainElementGenerator extends AbstractElementGenerator {

    public SelectByDomainElementGenerator() {
        super();
    }

    @Override
    public int getIndex() {
        return 2;
    }

    @Override
    public Method getJavaMethod() {
        Method method = new Method();
        method.setName("selectByDomain");
        method.addJavaDocLine("/** 实体对象作为查询条件 */");
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "domain"));
        return method;
    }

    @Override
    public void addElements(XmlElement parentElement) {
        String parameterType = introspectedTable.getBaseRecordType();

        XmlElement xmlElement = new XmlElement("select"); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("id", "selectByDomain"));
        xmlElement.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));
        xmlElement.addAttribute(new Attribute("parameterType", parameterType));

        String column = introspectedTable.getBaseColumnListId();
        String table = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();

        String sql = String.format("select <include refid=\"%s\"/> from %s", column, table);
        xmlElement.addElement(new TextElement(sql));
        xmlElement.addElement(getWhereElement());
        parentElement.addElement(xmlElement);
    }
}
