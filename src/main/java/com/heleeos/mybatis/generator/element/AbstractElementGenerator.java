package com.heleeos.mybatis.generator.element;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
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
    public abstract int getIndex();

    /**
     * 使用mybatis写好的生成器
     * @param elementGenerator 其他生成器
     * @param parentElement 父节点
     */
    protected void useMybatisGenerator(AbstractXmlElementGenerator elementGenerator, XmlElement parentElement) {
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.setProgressCallback(progressCallback);
        elementGenerator.setWarnings(warnings);
        elementGenerator.addElements(parentElement);
    }

    /**
     * 获取where条件
     */
    protected String getWhereInfo() {
        StringBuilder whereBuilder = new StringBuilder();
        boolean and = false;
        for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
            if (and) {
                whereBuilder.append("  and ");
            } else {
                whereBuilder.append("where ");
                and = true;
            }
            whereBuilder.append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn));
            whereBuilder.append(" = ");
            whereBuilder.append(MyBatis3FormattingUtilities .getParameterClause(introspectedColumn));
        }
        return whereBuilder.toString();
    }

    /**
     * 获取where字段的条件
     */
    protected XmlElement getWhereElement() {
        XmlElement whereElement = new XmlElement("where");
        introspectedTable.getAllColumns().forEach(column -> {
            String exec = String.format("and %s = %s", column.getActualColumnName(), column.getJavaProperty());
            XmlElement isNotNullElement = new XmlElement("if");
            isNotNullElement.addAttribute(new Attribute("test", String.format("%s != null", column.getJavaProperty())));
            isNotNullElement.addElement(new TextElement(exec));
            whereElement.addElement(isNotNullElement);
        });
        return whereElement;
    }
}
