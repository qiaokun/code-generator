package cn.vansky.code.generator.xml.mybatis.element.ppms;

import cn.vansky.code.generator.db.ColumnInfo;
import cn.vansky.code.generator.db.ppms.PPmsAttributes;
import cn.vansky.code.generator.util.MyBatis3FormattingUtil;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.TextElement;
import cn.vansky.code.generator.xml.XmlElement;
import cn.vansky.code.generator.xml.mybatis.element.AbstractXmlElementGenerator;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2015/6/7
 */
public class PpmsFindPageElementGenerator extends AbstractXmlElementGenerator<PPmsAttributes> {

    public void prepareXmlElement() {
        name = "select";
        id = "findPage";
        parameterType = "page";
        resultMap = attributes.getBaseResultMap();
    }

    public void dealElements() {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getBaseColumnListElement());
        if (tableInfoWrapper.hasBLOBColumns()) {
            answer.addElement(new TextElement(","));
            answer.addElement(getBlobColumnListElement());
        }

        sb.setLength(0);
        sb.append("from ");
        sb.append(tableInfoWrapper.getTableInfo().getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        XmlElement dynamicElement = new XmlElement("where");
        answer.addElement(dynamicElement);

        for (ColumnInfo c : tableInfoWrapper.getNonPrimaryKeyColumns()) {
            XmlElement isNotNullElement = new XmlElement("if");
            sb.setLength(0);
            sb.append("obj.");
            sb.append(c.getJavaProperty());
            sb.append(" != null");
            isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            dynamicElement.addElement(isNotNullElement);

            sb.setLength(0);
            sb.append("and ");
            sb.append(MyBatis3FormattingUtil.getEscapedColumnName(c));
            sb.append(" = ");
            sb.append(MyBatis3FormattingUtil.getFindPageParameterClause(c));

            isNotNullElement.addElement(new TextElement(sb.toString()));
        }
    }
}
