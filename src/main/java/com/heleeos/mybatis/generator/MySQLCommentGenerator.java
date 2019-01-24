package com.heleeos.mybatis.generator;

import com.heleeos.mybatis.util.TimeFortmatUtil;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Properties;
import java.util.Set;

/**
 * Mysql 注释生成器
 * Created by liyu on 2019/1/6.
 */
public class MySQLCommentGenerator implements CommentGenerator {

    public void addConfigurationProperties(Properties properties) {

    }

    /**
     * 添加Java类的字段注释
     */
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        field.addJavaDocLine("/** " + remarks + " */");
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    /**
     * 添加Java类注释
     */
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String remarks = introspectedTable.getRemarks();
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + remarks);
        topLevelClass.addJavaDocLine(" * Created by liyu on " + TimeFortmatUtil.getNow("yyyy/M/d"));
        topLevelClass.addJavaDocLine(" */");
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {

    }

    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

    }

    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    public void addJavaFileComment(CompilationUnit compilationUnit) {

    }

    public void addComment(XmlElement xmlElement) {

    }

    public void addRootComment(XmlElement rootElement) {

    }

    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

    }

    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

    }

    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

}
