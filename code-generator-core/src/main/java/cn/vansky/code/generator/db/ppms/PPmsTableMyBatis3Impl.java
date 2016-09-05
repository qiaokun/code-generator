package cn.vansky.code.generator.db.ppms;

import cn.vansky.code.generator.api.comment.ppms.PPmsCommentGenerator;
import cn.vansky.code.generator.api.file.GeneratedJavaFile;
import cn.vansky.code.generator.api.file.GeneratedXmlFile;
import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.CompilationUnit;
import cn.vansky.code.generator.java.file.ppms.PPmsJavaGenerator;
import cn.vansky.code.generator.xml.Document;
import cn.vansky.code.generator.xml.mybatis.mapper.ppms.PpmsBaseXMLMapperGenerator;
import cn.vansky.framework.common.constant.Constant;

import java.util.List;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/10
 */
public class PPmsTableMyBatis3Impl extends TableInfoWrapper<PPmsAttributes> {
    public PPmsTableMyBatis3Impl(CodeGenContext context) {
        super(context);
        this.xmlMapperGenerator = new PpmsBaseXMLMapperGenerator();
        this.xmlMapperGenerator.setTableInfoWrapper(this);
        this.javaModelGenerators = new PPmsJavaGenerator(this);
        this.commentGenerator = new PPmsCommentGenerator();
        this.attributes = new PPmsAttributes();
    }

    public void getGeneratedXmlFiles(List<GeneratedXmlFile> answer) {
        PPmsAttributes pmsAttributes = this.attributes;
        // bo扩展类
        Document document = xmlMapperGenerator.getDocument();
        GeneratedXmlFile gxf = new GeneratedXmlFile(document,
                pmsAttributes.getMyBatis3XmlMapperFileName(),
                pmsAttributes.getXMLMapperPackage(),
                context.getTargetPackage(), context.getXmlFormatter());
        answer.add(gxf);
    }

    public void getGeneratedJavaFiles(List<GeneratedJavaFile> answer, List<CompilationUnit> compilationUnits) {
        for (CompilationUnit compilationUnit : compilationUnits) {
            GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                    context.getTargetPackage(), Constant.UTF_8, context.getJavaFormatter());
            answer.add(gjf);
        }
    }
}
