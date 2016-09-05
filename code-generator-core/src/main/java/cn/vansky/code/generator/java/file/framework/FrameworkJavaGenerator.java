package cn.vansky.code.generator.java.file.framework;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.framework.FrameworkAttributes;
import cn.vansky.code.generator.java.*;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;
import cn.vansky.code.generator.java.element.framework.*;
import cn.vansky.code.generator.java.file.AbstractJavaGenerator;

import java.util.List;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/10
 */
public class FrameworkJavaGenerator extends AbstractJavaGenerator<FrameworkAttributes> {
    public FrameworkJavaGenerator(TableInfoWrapper<FrameworkAttributes> t) {
        super(t);
    }

    public void getJavaFile(List<CompilationUnit> answers) {
        AbstractJavaElementGenerator<FrameworkAttributes> baseBo = new FrameworkBaseBoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(baseBo, answers);

        AbstractJavaElementGenerator<FrameworkAttributes> extendBo = new FrameworkExtendsBoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(extendBo, answers);

        AbstractJavaElementGenerator<FrameworkAttributes> mapper = new FrameworkMapperGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(mapper, answers);

        AbstractJavaElementGenerator<FrameworkAttributes> dao = new FrameworkDaoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(dao, answers);

        AbstractJavaElementGenerator<FrameworkAttributes> daoImpl = new FrameworkDaoImplGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(daoImpl, answers);

        AbstractJavaElementGenerator<FrameworkAttributes> service = new FrameworkServiceGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(service, answers);

        AbstractJavaElementGenerator<FrameworkAttributes> serverImpl = new FrameworkServiceImplGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(serverImpl, answers);
    }
}
