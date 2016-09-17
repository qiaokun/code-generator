package cn.vansky.code.generator.xml.mybatis.element.ppms;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.ppms.PPmsAttributes;
import cn.vansky.code.generator.util.MyBatis3FormattingUtil;
import cn.vansky.code.generator.xml.TextElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;

/**
 * 删除语句（主键）
 * Author: CK
 * Date: 2015/6/7
 */
public class PpmsDeleteElementGenerator extends AbstractXmlElementGenerator<PPmsAttributes> {

    public void prepareXmlElement() {
        name = "delete";
        id = "delete";
        String parameterClass;
        // 主键及外键
        if (tableInfoWrapper.getPrimaryKeyColumns().size() > 1) {
            parameterClass = "hashmap";
        } else {
            parameterClass = tableInfoWrapper.getPrimaryKeyColumns().get(0).getJavaTypeInfo().toString();
        }
        parameterType = parameterClass;
    }

    public void dealElements() {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ");
        sb.append(tableInfoWrapper.getTableInfo().getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        boolean and = false;
        for (ColumnInfo introspectedColumn : tableInfoWrapper.getPrimaryKeyColumns()) {
            sb.setLength(0);
            if (and) {
                sb.append("  and ");
            } else {
                sb.append("where ");
                and = true;
            }

            sb.append(MyBatis3FormattingUtil.getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtil.getParameterClause(introspectedColumn));
            answer.addElement(new TextElement(sb.toString()));
        }
    }
}
