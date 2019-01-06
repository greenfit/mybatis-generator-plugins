package com.heleeos.mybatis.generator.xml;

import com.heleeos.mybatis.generator.xml.element.SelectByDomainElementGenerator;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

/**
 * Created by liyu on 2019/1/7.
 */
public class BaseMapperXmlGenerator extends XMLMapperGenerator {

    protected void addSelectByExampleWithoutBLOBsElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new SelectByDomainElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
}
