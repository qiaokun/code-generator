package cn.vansky.code.generator.java.file.framework;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.*;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;
import cn.vansky.code.generator.java.element.framework.*;
import cn.vansky.code.generator.java.file.AbstractJavaGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/10
 */
public class FrameworkJavaGenerator extends AbstractJavaGenerator {
    public FrameworkJavaGenerator(TableInfoWrapper t) {
        super(t);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        getJavaFile(answer);
        return answer;
    }

    public void getJavaFile(List<CompilationUnit> answers) {
        AbstractJavaElementGenerator baseBo = new FrameworkBaseBoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(baseBo, answers);

        AbstractJavaElementGenerator extendBo = new FrameworkExtendsBoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(extendBo, answers);

        AbstractJavaElementGenerator mapper = new FrameworkMapperGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(mapper, answers);

        AbstractJavaElementGenerator dao = new FrameworkDaoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(dao, answers);

        AbstractJavaElementGenerator daoImpl = new FrameworkDaoImplGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(daoImpl, answers);

        AbstractJavaElementGenerator service = new FrameworkServiceGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(service, answers);

        AbstractJavaElementGenerator serverImpl = new FrameworkServiceImplGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(serverImpl, answers);
    }
}
