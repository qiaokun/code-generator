package cn.vansky.code.generator.java.file.ppms;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.ppms.PPmsAttributes;
import cn.vansky.code.generator.java.CompilationUnit;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;
import cn.vansky.code.generator.java.element.ppms.PPmsActionGenerator;
import cn.vansky.code.generator.java.element.ppms.PPmsBaseBoGenerator;
import cn.vansky.code.generator.java.element.ppms.PPmsDaoGenerator;
import cn.vansky.code.generator.java.element.ppms.PPmsServiceGenerator;
import cn.vansky.code.generator.java.element.ppms.PPmsServiceImplGenerator;
import cn.vansky.code.generator.java.file.AbstractJavaGenerator;

import java.util.List;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/10
 */
public class PPmsJavaGenerator extends AbstractJavaGenerator<PPmsAttributes> {
    public PPmsJavaGenerator(TableInfoWrapper<PPmsAttributes> t) {
        super(t);
    }

    public void getJavaFile(List<CompilationUnit> answers) {
        AbstractJavaElementGenerator<PPmsAttributes> extendBo = new PPmsBaseBoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(extendBo, answers);

        AbstractJavaElementGenerator<PPmsAttributes> dao = new PPmsDaoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(dao, answers);

        AbstractJavaElementGenerator<PPmsAttributes> service = new PPmsServiceGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(service, answers);

        AbstractJavaElementGenerator<PPmsAttributes> serverImpl = new PPmsServiceImplGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(serverImpl, answers);

        AbstractJavaElementGenerator<PPmsAttributes> action = new PPmsActionGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(action, answers);
    }
}
