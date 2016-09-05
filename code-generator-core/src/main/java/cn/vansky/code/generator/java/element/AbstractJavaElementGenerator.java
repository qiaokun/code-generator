package cn.vansky.code.generator.java.element;

import cn.vansky.code.generator.api.AbstractGenerator;
import cn.vansky.code.generator.db.AbstractAttributes;
import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.*;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/10
 */
public abstract class AbstractJavaElementGenerator<T extends AbstractAttributes> extends AbstractGenerator<T> {

    protected T attributes;

    public AbstractJavaElementGenerator(TableInfoWrapper<T> tableInfoWrapper) {
        setTableInfoWrapper(tableInfoWrapper);
        this.attributes = tableInfoWrapper.getAttributes();
    }

    protected JavaTypeInfo javaTypeInfo;

    protected JavaTypeInfo superClass;

    protected Set<JavaTypeInfo> superInterfaces;

    protected boolean isConstructor;

    public void addCompilationUnit(List<CompilationUnit> answers) {
        answers.add(addCompilationUnit());
    }

    public abstract void prepareElement();

    protected CompilationUnit addCompilationUnit() {
        TopLevelClass topLevelClass = new TopLevelClass(javaTypeInfo);
        topLevelClass.setJavaScope(JavaKeywords.PUBLIC);
        if (superClass != null) {
            // 父类
            topLevelClass.setSuperClass(superClass);
            topLevelClass.addImportedType(superClass);
        }

        if (superInterfaces != null && !superInterfaces.isEmpty()) {
            // 接口
            topLevelClass.addSuperInterfaces(superInterfaces);
            topLevelClass.addImportedTypes(superInterfaces);
        }

        if (isConstructor) {
            // 构造器
            addParameterizedConstructor(topLevelClass);
            addDefaultConstructor(topLevelClass);
        }

        dealElement(topLevelClass);

        commentGenerator.addJavaFileComment(topLevelClass);
        commentGenerator.addClassComment(topLevelClass, tableInfoWrapper);

        return topLevelClass;
    }

    public abstract void dealElement(TopLevelClass topLevelClass);

    public JavaTypeInfo getJavaTypeInfo() {
        return javaTypeInfo;
    }

    public JavaTypeInfo getSuperClass() {
        return superClass;
    }

    /**
     * 默认构造器
     * @param topLevelClass topLevelClass
     */
    protected void addDefaultConstructor(TopLevelClass topLevelClass) {
        Method method = new Method(topLevelClass.getType().getShortName());
        method.setJavaScope(JavaKeywords.PUBLIC);
        method.setConstructor(true);
        method.addBodyLine("super();");
        tableInfoWrapper.getCommentGenerator().addGeneralMethodComment(method, tableInfoWrapper);
        topLevelClass.addMethod(method);
    }

    protected void addParameterizedConstructor(TopLevelClass topLevelClass) {
        Method method = new Method(topLevelClass.getType().getShortName());
        method.setJavaScope(JavaKeywords.PUBLIC);
        method.setConstructor(true);
        tableInfoWrapper.getCommentGenerator().addGeneralMethodComment(method, tableInfoWrapper);

        List<ColumnInfo> constructorColumns =
                includeBLOBColumns() ? tableInfoWrapper.getAllColumns() : tableInfoWrapper.getNonBLOBColumns();

        for (ColumnInfo ColumnInfo : constructorColumns) {
            method.addParameter(new Parameter(ColumnInfo.getJavaTypeInfo(), ColumnInfo.getJavaProperty()));
            topLevelClass.addImportedType(ColumnInfo.getJavaTypeInfo());
        }

        StringBuilder sb = new StringBuilder();
        if (rules.generatePrimaryKeyClass()) {
            boolean comma = false;
            sb.append("super(");
            for (ColumnInfo ColumnInfo : tableInfoWrapper.getPrimaryKeyColumns()) {
                if (comma) {
                    sb.append(", ");
                } else {
                    comma = true;
                }
                sb.append(ColumnInfo.getJavaProperty());
            }
            sb.append(");");
            method.addBodyLine(sb.toString());
        }

        List<ColumnInfo> ColumnInfos = getColumnsInThisClass();

        for (ColumnInfo ColumnInfo : ColumnInfos) {
            sb.setLength(0);
            sb.append("this.");
            sb.append(ColumnInfo.getJavaProperty());
            sb.append(" = ");
            sb.append(ColumnInfo.getJavaProperty());
            sb.append(';');
            method.addBodyLine(sb.toString());
        }

        topLevelClass.addMethod(method);
    }

    protected boolean includePrimaryKeyColumns() {
        return !rules.generatePrimaryKeyClass() && tableInfoWrapper.hasPrimaryKeyColumns();
    }

    protected boolean includeBLOBColumns() {
        return !rules.generateRecordWithBLOBsClass() && tableInfoWrapper.hasBLOBColumns();
    }

    protected List<ColumnInfo> getColumnsInThisClass() {
        List<ColumnInfo> columnInfos;
        if (includePrimaryKeyColumns()) {
            if (includeBLOBColumns()) {
                columnInfos = tableInfoWrapper.getAllColumns();
            } else {
                columnInfos = tableInfoWrapper.getNonBLOBColumns();
            }
        } else {
            if (includeBLOBColumns()) {
                columnInfos = tableInfoWrapper.getNonPrimaryKeyColumns();
            } else {
                columnInfos = tableInfoWrapper.getBaseColumns();
            }
        }

        return columnInfos;
    }

    protected boolean getPk() {
        List<ColumnInfo> pkList = tableInfoWrapper.getPrimaryKeyColumns();
        return pkList.size() > 1;
    }
}
