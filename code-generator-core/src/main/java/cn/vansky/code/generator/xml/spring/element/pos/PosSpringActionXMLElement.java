package cn.vansky.code.generator.xml.spring.element.pos;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.config.SpringXMLConstants;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.pos.PosAttributes;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.util.JavaBeansUtil;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.XmlElement;
import cn.vansky.code.generator.xml.spring.element.AbstractSpringXMLElement;

import java.util.List;

/**
 * PPMS action Spring generator
 * Author: CK.
 * Date: 2015/6/6.
 */
public class PosSpringActionXMLElement extends AbstractSpringXMLElement {

    public PosSpringActionXMLElement(String name, CodeGenContext context, List<TableInfoWrapper> ts) {
        super(name, context, ts);
    }

    @Override
    public void getElement(XmlElement answer) {
        answer.addAttribute(new Attribute("\n\t\t\t\t" + SpringXMLConstants.XMLNS_SCHEMA_LOCATION,
                SpringXMLConstants.SPRING_BEANS + "\n\t\t\t\t" + SpringXMLConstants.SPRING_BEANS_XSD));

        for (TableInfoWrapper t : tableInfoWrappers) {
            PosAttributes attributes = ((PosAttributes) t.getAttributes());
            XmlElement action = new XmlElement("bean");
            JavaTypeInfo actionBean = attributes.getController();
            action.addAttribute(new Attribute("id", JavaBeansUtil.getValidPropertyName(actionBean.getShortName())));
            action.addAttribute(new Attribute("class", actionBean.getFullyQualifiedName()));
            action.addAttribute(new Attribute("parent", "baseController"));

            answer.addElement(action);
        }
    }
}
