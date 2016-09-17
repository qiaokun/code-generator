package cn.vansky.code.generator.xml.spring.element.ppms;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.config.SpringXMLConstants;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.ppms.PPmsAttributes;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.xml.spring.element.AbstractSpringXMLElement;
import cn.vansky.code.generator.util.JavaBeansUtil;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.XmlElement;

import java.util.List;

/**
 * PPMS action Spring generator
 * Author: CK.
 * Date: 2015/6/6.
 */
public class PPmsSpringActionXMLElement extends AbstractSpringXMLElement<PPmsAttributes> {

    public PPmsSpringActionXMLElement(String name, CodeGenContext context, List<TableInfoWrapper<PPmsAttributes>> ts) {
        super(name, context, ts);
    }

    public void getElement(XmlElement answer) {
        answer.addAttribute(new Attribute("\n\t\t\t\t" + SpringXMLConstants.XMLNS_SCHEMA_LOCATION,
                SpringXMLConstants.SPRING_BEANS + "\n\t\t\t\t" + SpringXMLConstants.SPRING_BEANS_XSD));

        for (TableInfoWrapper<PPmsAttributes> t : tableInfoWrappers) {
            PPmsAttributes attributes = t.getAttributes();
            XmlElement action = new XmlElement("bean");
            JavaTypeInfo actionBean = attributes.getController();
            action.addAttribute(new Attribute("id", JavaBeansUtil.getValidPropertyName(actionBean.getShortName())));
            action.addAttribute(new Attribute("class", actionBean.getFullyQualifiedName()));
            action.addAttribute(new Attribute("parent", "baseController"));

            JavaTypeInfo service = attributes.getService();
            String shortName = JavaBeansUtil.getValidPropertyName(service.getShortName());
            XmlElement serviceProperty = new XmlElement("property", true);
            serviceProperty.addAttribute(new Attribute("name", shortName));
            serviceProperty.addAttribute(new Attribute("ref", shortName));
            action.addElement(serviceProperty);

            XmlElement log = new XmlElement("property", true);
            log.addAttribute(new Attribute("name", "ppmsOpLogTransactionService"));
            log.addAttribute(new Attribute("ref", "ppmsOpLogTransactionService"));
            action.addElement(log);

            answer.addElement(action);
        }
    }
}
