package org.mybatis.generator.internal;

import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class NoCommentGenerator implements CommentGenerator{

	public void addClassComment(InnerClass innerClass,
			IntrospectedTable introspectedTable) {}

	public void addClassComment(InnerClass innerClass,
			IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {}

	public void addComment(XmlElement xmlElement) {}

	public void addConfigurationProperties(Properties properties) {}

	public void addEnumComment(InnerEnum innerEnum,
			IntrospectedTable introspectedTable) {}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {}
	
	public void addFieldComment(Field field,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {}

	public void addGeneralMethodComment(Method method,
			IntrospectedTable introspectedTable) {}

	public void addGetterComment(Method method,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {}

	public void addJavaFileComment(CompilationUnit compilationUnit) {}

	public void addRootComment(XmlElement rootElement) {}

	public void addSetterComment(Method method,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {}
}
