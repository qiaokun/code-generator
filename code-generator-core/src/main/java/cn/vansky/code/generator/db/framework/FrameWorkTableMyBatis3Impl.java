package cn.vansky.code.generator.db.framework;

import cn.vansky.code.generator.api.comment.framework.FrameWorkCommentGenerator;
import cn.vansky.code.generator.api.file.GeneratedJavaFile;
import cn.vansky.code.generator.api.file.GeneratedXmlFile;
import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.CompilationUnit;
import cn.vansky.code.generator.java.file.framework.FrameworkJavaGenerator;
import cn.vansky.code.generator.xml.Document;
import cn.vansky.code.generator.xml.mybatis.mapper.AbstractXmlMapperGenerator;
import cn.vansky.code.generator.xml.mybatis.mapper.EmptyXMLMapperGenerator;
import cn.vansky.code.generator.xml.mybatis.mapper.framework.FrameworkXMLMapperGenerator;
import cn.vansky.framework.common.constant.Constant;

import java.util.List;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/10
 */
public class FrameWorkTableMyBatis3Impl extends TableInfoWrapper<FrameworkAttributes> {
    public FrameWorkTableMyBatis3Impl(CodeGenContext context) {
        super(context);
        this.javaModelGenerators = new FrameworkJavaGenerator(this);
        this.xmlMapperGenerator = new EmptyXMLMapperGenerator();
        this.xmlMapperGenerator.setTableInfoWrapper(this);
        this.baseXmlMapperGenerator = new FrameworkXMLMapperGenerator();
        this.baseXmlMapperGenerator.setTableInfoWrapper(this);
        this.attributes = new FrameworkAttributes();
        this.commentGenerator = new FrameWorkCommentGenerator();
    }

    /** base XML内容生成类 */
    protected AbstractXmlMapperGenerator baseXmlMapperGenerator;

    public void getGeneratedXmlFiles(List<GeneratedXmlFile> answer) {
        FrameworkAttributes frameworkAttributes = this.attributes;
        // bo扩展类
        Document document = xmlMapperGenerator.getDocument();
        GeneratedXmlFile gxf = new GeneratedXmlFile(document,
                frameworkAttributes.getMyBatis3XmlMapperFileName(),
                frameworkAttributes.getXMLMapperPackage(),
                context.getTargetPackage(), context.getXmlFormatter());
        answer.add(gxf);
        // bo基础类
        Document baseDocument = baseXmlMapperGenerator.getDocument();
        GeneratedXmlFile base = new GeneratedXmlFile(baseDocument,
                frameworkAttributes.getMyBatis3XmlMapperBaseFileName(),
                frameworkAttributes.getXMLMapperPackage(),
                context.getTargetPackage(), context.getXmlFormatter());
        answer.add(base);
    }

    public void getGeneratedJavaFiles(List<GeneratedJavaFile> answer, List<CompilationUnit> compilationUnits) {
        for (CompilationUnit compilationUnit : compilationUnits) {
            GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                    context.getTargetPackage(), Constant.UTF_8, context.getJavaFormatter());
            answer.add(gjf);
        }
    }
}
