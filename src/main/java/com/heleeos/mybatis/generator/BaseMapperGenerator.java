package com.heleeos.mybatis.generator;

import com.heleeos.mybatis.generator.element.*;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * BaseMapper的xml生成器
 * Created by liyu on 2019/1/7.
 */
public class BaseMapperGenerator extends JavaMapperGenerator {

    public static final List<AbstractElementGenerator> ELEMENT_GENERATOR_LIST = Arrays.asList(
            new CountByDomainElementGenerator(),
            new DeleteDomainElementGenerator(),
            new InsertDomainElementGenerator(),
            new SelectByDomainElementGenerator(),
            new UpdateDomainElementGenerator(),
            new SelectByIdElementGenerator()
    );

    static {
        //按照index排序
        ELEMENT_GENERATOR_LIST.sort(Comparator.comparingInt(AbstractElementGenerator::getIndex));
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new BaseMapperXmlGenerator();
    }

    class BaseMapperXmlGenerator extends XMLMapperGenerator {

        protected XmlElement getSqlMapElement() {
            String namespace = introspectedTable.getMyBatis3SqlMapNamespace();
            FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
            progressCallback.startTask(getString("Progress.12", table.toString())); //$NON-NLS-1$

            XmlElement xmlElement = new XmlElement("mapper"); //$NON-NLS-1$
            xmlElement.addAttribute(new Attribute("namespace", namespace)); //$NON-NLS-1$

            context.getCommentGenerator().addRootComment(xmlElement);
            addResultMapWithoutBLOBsElement(xmlElement);
            addResultMapWithBLOBsElement(xmlElement);
            addBaseColumnListElement(xmlElement);
            addElements(xmlElement);

            return xmlElement;
        }

        /** 添加xml节点 */
        private void addElements(XmlElement parentElement) {
            ELEMENT_GENERATOR_LIST.forEach(generator -> initializeAndExecuteGenerator(generator, parentElement));
        }
    }
}


