package com.heleeos.mybatis.plugins;

import com.heleeos.mybatis.generator.BaseMapperGenerator;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

import java.util.*;

/**
 * 生成基本的BaseMapper插件
 * Created by liyu on 2019/1/6.
 */
@Slf4j
public class UseBaseMapperPlugin extends EmptyMapperPlugin {

    private String targetPackage = "";
    private String targetProject = "";

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }

    public boolean validate(List<String> warnings) {
        targetPackage = properties.get("targetPackage").toString();
        targetProject = properties.get("targetProject").toString();

        if(!StringUtility.stringHasValue(targetPackage)) {
            warnings.add(Messages.getString("ValidationError.99", UseBaseMapperPlugin.class.getName(), "targetPackage"));
            return false;
        }

        if(!StringUtility.stringHasValue(targetProject)) {
            warnings.add(Messages.getString("ValidationError.99", UseBaseMapperPlugin.class.getName(), "targetProject"));
            return false;
        }

        return true;
    }

    /**
     * 生成dao
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType("BaseMapper<" + introspectedTable.getBaseRecordType() + ">");
        interfaze.addSuperInterface(fullyQualifiedJavaType);
        interfaze.getMethods().clear();
        interfaze.getAnnotations().clear();
//        Set<FullyQualifiedJavaType> set =  interfaze.getImportedTypes();

        //不在同一个包下面, 新增一个 import
        if(!Objects.equals(interfaze.getType().getPackageName(), targetPackage)) {
            FullyQualifiedJavaType baseMapperImport = new FullyQualifiedJavaType(targetPackage + ".BaseMapper");
            interfaze.addImportedType(baseMapperImport);
        }
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> files = new ArrayList<>();
        files.add(generatedBaseMapperFile());
        return files;
    }

//    @Override
//    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
//        log.info(element.toString());
//        return false;
//    }
//
//    @Override
//    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
//        log.info(element.toString());
//        return false;
//    }
//
//    @Override
//    public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
//        log.info(element.toString());
//        return true;
//    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return true;
    }

    /**
     * 生成 BaseMapper 文件
     */
    private GeneratedJavaFile generatedBaseMapperFile() {
        String className = targetPackage + ".BaseMapper<T>";
        FullyQualifiedJavaType serviceInterfaceType = new FullyQualifiedJavaType(className);
        Interface baseMapper = new Interface(serviceInterfaceType);
        baseMapper.setVisibility(JavaVisibility.PUBLIC);
        baseMapper.addImportedType(new FullyQualifiedJavaType(List.class.getName()));

        BaseMapperGenerator.ELEMENT_GENERATOR_LIST.forEach(generator -> baseMapper.addMethod(generator.getJavaMethod()));

        JavaFormatter javaFormatter = new DefaultJavaFormatter();
        javaFormatter.setContext(context);
        return new GeneratedJavaFile(baseMapper, targetProject, javaFormatter);
    }
}
