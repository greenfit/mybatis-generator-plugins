package com.heleeos.mybatis.plugins;

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

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        log.info(element.toString());
        return true;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        log.info(element.toString());
        return true;
    }

    @Override
    public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        log.info(element.toString());
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

        Method method = new Method();
        method.setName("selectByDomain");
        method.addJavaDocLine("");
        method.addJavaDocLine("/** 实体对象作为查询条件 */");
        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "domain"));
        baseMapper.addMethod(method);

        method = new Method();
        method.setName("selectByPrimaryKey");
        method.setReturnType(new FullyQualifiedJavaType("T"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("int"), "id"));
        baseMapper.addMethod(method);

        method = new Method();
        method.setName("countByDomain");
        method.setReturnType(new FullyQualifiedJavaType("long"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"), "domain"));
        baseMapper.addMethod(method);


//
//        //int deleteByExample(E example);
//        method=new Method();
//        method.setName("deleteBy");
//        method.setReturnType(new FullyQualifiedJavaType("int"));
//        method.addParameter(new Parameter(new FullyQualifiedJavaType("E"),paramExample));
//        service.addMethod(method);
//
//        //int insertSelective(T record);
//        method=new Method();
//        method.setName("insertSelective");
//        method.setReturnType(new FullyQualifiedJavaType("int"));
//        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"),"record"));
//        service.addMethod(method);
//
//        method=new Method();
//        method.setName("insertBatchSelective");
//        method.setReturnType(new FullyQualifiedJavaType("int"));
//        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<T>"),"records"));
//        service.addMethod(method);
//
//
//        //List<T> selectByExample(E example);
//        method=new Method();
//        method.setName("selectBy"+example);
//        method.setReturnType(new FullyQualifiedJavaType("List<T>"));
//        method.addParameter(new Parameter(new FullyQualifiedJavaType("E"),paramExample));
//        service.addMethod(method);
//
//
//        //T selectByPrimaryKey(PK id);
//        method=new Method();
//        method.setName("selectByPrimaryKey");
//        method.setReturnType(new FullyQualifiedJavaType("T"));
//        method.addParameter(new Parameter(new FullyQualifiedJavaType("PK"),"id"));
//        service.addMethod(method);
//
//        method=new Method();
//        method.setName("selectSingleBy"+example);
//        method.setReturnType(new FullyQualifiedJavaType("T"));
//        method.addParameter(new Parameter(new FullyQualifiedJavaType("E"),paramExample));
//        service.addMethod(method);
//
//        //int updateByExampleSelective(T record, E example);
//        method=new Method();
//        method.setName("updateBy"+example+"Selective");
//        method.setReturnType(new FullyQualifiedJavaType("int"));
//        method.addParameter(new Parameter(new FullyQualifiedJavaType("@Param(\"record\") T"),"record"));
//        method.addParameter(new Parameter(new FullyQualifiedJavaType("@Param(\""+paramExample+"\") E"),paramExample));
//        service.addMethod(method);
//
//        //int updateByPrimaryKeySelective(T record);
//        method=new Method();
//        method.setName("updateByPrimaryKeySelective");
//        method.setReturnType(new FullyQualifiedJavaType("int"));
//        method.addParameter(new Parameter(new FullyQualifiedJavaType("T"),"record"));
//        service.addMethod(method);
//
////        int updateBatchByPrimaryKeySelective(List<MaterialCopy> records);
//        method=new Method();
//        method.setName("updateBatchByPrimaryKeySelective");
//        method.setReturnType(new FullyQualifiedJavaType("int"));
//        method.addParameter(new Parameter(new FullyQualifiedJavaType("List<T>"),"records"));
//        service.addMethod(method);

        JavaFormatter javaFormatter = new DefaultJavaFormatter();
        javaFormatter.setContext(context);
        return new GeneratedJavaFile(baseMapper, targetProject, javaFormatter);
    }
}
