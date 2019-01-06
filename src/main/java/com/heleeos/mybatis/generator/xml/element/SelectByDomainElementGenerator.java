package com.heleeos.mybatis.generator.xml.element;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * Created by liyu on 2019/1/7.
 */
public class SelectByDomainElementGenerator extends AbstractXmlElementGenerator {

    public SelectByDomainElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        String parameterType = introspectedTable.getBaseRecordType();

        XmlElement xmlElement = new XmlElement("select"); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("id", "selectByDomain"));
        xmlElement.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId())); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("parameterType", parameterType)); //$NON-NLS-1$

        parentElement.addElement(xmlElement);
//        context.getCommentGenerator().addComment(answer);
//
//        answer.addElement(new TextElement("select")); //$NON-NLS-1$
//        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
//        ifElement.addAttribute(new Attribute("test", "distinct")); //$NON-NLS-1$ //$NON-NLS-2$
//        ifElement.addElement(new TextElement("distinct")); //$NON-NLS-1$
//        answer.addElement(ifElement);
//
//        StringBuilder sb = new StringBuilder();
//        if (stringHasValue(introspectedTable.getSelectByExampleQueryId())) {
//            sb.append('\'');
//            sb.append(introspectedTable.getSelectByExampleQueryId());
//            sb.append("' as QUERYID,"); //$NON-NLS-1$
//            answer.addElement(new TextElement(sb.toString()));
//        }
//        answer.addElement(getBaseColumnListElement());
//
//        sb.setLength(0);
//        sb.append("from "); //$NON-NLS-1$
//        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
//        answer.addElement(new TextElement(sb.toString()));
//        answer.addElement(getExampleIncludeElement());
//
//        ifElement = new XmlElement("if"); //$NON-NLS-1$
//        ifElement.addAttribute(new Attribute("test", "orderByClause != null")); //$NON-NLS-1$ //$NON-NLS-2$
//        ifElement.addElement(new TextElement("order by ${orderByClause}")); //$NON-NLS-1$
//        answer.addElement(ifElement);
//
//        if (context.getPlugins().sqlMapSelectByExampleWithoutBLOBsElementGenerated(answer,
//                        introspectedTable)) {
//            parentElement.addElement(answer);
//        }
    }
}
