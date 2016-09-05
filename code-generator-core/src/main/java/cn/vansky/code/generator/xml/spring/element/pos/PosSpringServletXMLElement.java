package cn.vansky.code.generator.xml.spring.element.pos;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.config.SpringXMLConstants;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.db.pos.PosAttributes;
import cn.vansky.code.generator.java.JavaTypeInfo;
import cn.vansky.code.generator.util.JavaBeansUtil;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.TextElement;
import cn.vansky.code.generator.xml.XmlElement;
import cn.vansky.code.generator.xml.spring.element.AbstractSpringXMLElement;

import java.util.List;

/**
 * PPMS servlet Spring generator
 * Author: CK.
 * Date: 2015/6/6.
 */
public class PosSpringServletXMLElement extends AbstractSpringXMLElement<PosAttributes> {

    public PosSpringServletXMLElement(String name, CodeGenContext context, List<TableInfoWrapper<PosAttributes>> ts) {
        super(name, context, ts);
    }

    @Override
    public void getElement(XmlElement answer) {
        answer.addAttribute(new Attribute("\n\t\t\t\t" + SpringXMLConstants.XMLNS_SCHEMA_LOCATION,
                SpringXMLConstants.SPRING_BEANS + "\n\t\t\t\t" + SpringXMLConstants.SPRING_BEANS_XSD));

        XmlElement mapping = new XmlElement("bean");
        mapping.addAttribute(new Attribute("class", "org.springframework.web.servlet.handler.SimpleUrlHandlerMapping"));

        XmlElement property = new XmlElement("property");
        property.addAttribute(new Attribute("name", "mappings"));
        mapping.addElement(property);

        XmlElement props = new XmlElement("props");
        for (TableInfoWrapper<PosAttributes> t : this.tableInfoWrappers) {
            PosAttributes attributes = t.getAttributes();
            JavaTypeInfo javaTypeInfo = attributes.getBo();
            String bo = JavaBeansUtil.getValidPropertyName(javaTypeInfo.getShortName());
            XmlElement prop = new XmlElement("prop");
            prop.addAttribute(new Attribute("key", "/" + bo + "/index.htm"));
            JavaTypeInfo action = attributes.getController();
            prop.addElement(new TextElement(JavaBeansUtil.getValidPropertyName(action.getShortName())));
            props.addElement(prop);
        }
        property.addElement(props);
        answer.addElement(mapping);
    }
}
