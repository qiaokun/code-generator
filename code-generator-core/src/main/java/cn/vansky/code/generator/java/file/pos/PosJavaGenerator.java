package cn.vansky.code.generator.java.file.pos;

import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.pos.PosAttributes;
import cn.vansky.code.generator.java.CompilationUnit;
import cn.vansky.code.generator.java.element.AbstractJavaElementGenerator;
import cn.vansky.code.generator.java.element.pos.*;
import cn.vansky.code.generator.java.file.AbstractJavaGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/10
 */
public class PosJavaGenerator extends AbstractJavaGenerator<PosAttributes> {
    public PosJavaGenerator(TableInfoWrapper<PosAttributes> t) {
        super(t);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        getJavaFile(answer);
        return answer;
    }

    public void getJavaFile(List<CompilationUnit> answers) {
        AbstractJavaElementGenerator<PosAttributes> extendBo = new PosBaseBoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(extendBo, answers);

        AbstractJavaElementGenerator<PosAttributes> pageVo = new PosPageVoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(pageVo, answers);

        AbstractJavaElementGenerator<PosAttributes> vo = new PosVoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(vo, answers);

        AbstractJavaElementGenerator<PosAttributes> dao = new PosDaoGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(dao, answers);

        AbstractJavaElementGenerator<PosAttributes> service = new PosServiceGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(service, answers);

        AbstractJavaElementGenerator<PosAttributes> serverImpl = new PosServiceImplGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(serverImpl, answers);

        AbstractJavaElementGenerator<PosAttributes> action = new PosActionGenerator(tableInfoWrapper);
        initializeAndExecuteGenerator(action, answers);
    }
}
