package cn.vansky.code.generator.java.file.ppms;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.java.CompilationUnit;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;
import cn.vansky.code.generator.java.element.ppms.*;
import cn.vansky.code.generator.java.file.AbstractJavaGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/10
 */
public class PPmsJavaGenerator extends AbstractJavaGenerator {
    public PPmsJavaGenerator(TableInfoWrapper t) {
        super(t);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        getJavaFile(answer);
        return answer;
    }

    public void getJavaFile(List<CompilationUnit> answers) {
        AbstractJavaElementGenerator extendBo = new PPmsBaseBoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(extendBo, answers);

        AbstractJavaElementGenerator dao = new PPmsDaoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(dao, answers);

        AbstractJavaElementGenerator service = new PPmsServiceGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(service, answers);

        AbstractJavaElementGenerator serverImpl = new PPmsServiceImplGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(serverImpl, answers);

        AbstractJavaElementGenerator action = new PPmsActionGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(action, answers);
    }
}
