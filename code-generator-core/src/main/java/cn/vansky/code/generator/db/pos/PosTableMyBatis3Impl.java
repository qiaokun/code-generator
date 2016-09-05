package cn.vansky.code.generator.db.pos;

import cn.vansky.code.generator.api.comment.ppms.PPmsCommentGenerator;
import cn.vansky.code.generator.api.file.GeneratedJavaFile;
import cn.vansky.code.generator.api.file.GeneratedXmlFile;
import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.CompilationUnit;
import cn.vansky.code.generator.java.file.pos.PosJavaGenerator;
import cn.vansky.code.generator.xml.Document;
import cn.vansky.code.generator.xml.mybatis.mapper.AbstractXmlMapperGenerator;
import cn.vansky.code.generator.xml.mybatis.mapper.EmptyXMLMapperGenerator;
import cn.vansky.code.generator.xml.mybatis.mapper.pos.PosBaseXMLMapperGenerator;
import cn.vansky.framework.common.constant.Constant;

import java.util.List;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/10
 */
public class PosTableMyBatis3Impl extends TableInfoWrapper<PosAttributes> {
    public PosTableMyBatis3Impl(CodeGenContext context) {
        super(context);
        this.xmlMapperGenerator = new EmptyXMLMapperGenerator();
        this.xmlMapperGenerator.setTableInfoWrapper(this);
        this.baseXmlMapperGenerator = new PosBaseXMLMapperGenerator();
        this.baseXmlMapperGenerator.setTableInfoWrapper(this);
        this.javaModelGenerators = new PosJavaGenerator(this);
        this.commentGenerator = new PPmsCommentGenerator();
        this.attributes = new PosAttributes();
    }

    /** base XML内容生成类 */
    protected AbstractXmlMapperGenerator baseXmlMapperGenerator;

    public void getGeneratedXmlFiles(List<GeneratedXmlFile> answer) {
        PosAttributes attribute = this.attributes;
        // bo扩展类
        Document document = xmlMapperGenerator.getDocument();
        GeneratedXmlFile gxf = new GeneratedXmlFile(document,
                attribute.getMyBatis3XmlMapperFileName(),
                attribute.getXMLMapperPackage(),
                context.getTargetPackage(), context.getXmlFormatter());
        answer.add(gxf);
        // bo基础类
        Document baseDocument = baseXmlMapperGenerator.getDocument();
        GeneratedXmlFile base = new GeneratedXmlFile(baseDocument,
                attribute.getMyBatis3XmlMapperBaseFileName(),
                attribute.getXMLMapperPackage(),
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
