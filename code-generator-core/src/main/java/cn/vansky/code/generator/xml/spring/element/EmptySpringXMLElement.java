package cn.vansky.code.generator.xml.spring.element;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.config.SpringXMLConstants;
import cn.vansky.code.generator.db.AbstractAttributes;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.xml.Attribute;
import cn.vansky.code.generator.xml.XmlElement;

import java.util.List;

/**
 * empty Spring generator
 * Author: CK.
 * Date: 2015/6/6.
 */
public class EmptySpringXMLElement extends AbstractSpringXMLElement<AbstractAttributes> {

    public EmptySpringXMLElement(String name, CodeGenContext context, List<TableInfoWrapper<AbstractAttributes>> ts) {
        super(name, context, ts);
    }

    @Override
    public void getElement(XmlElement answer) {
        answer.addAttribute(new Attribute("\n\t\t\t\t" + SpringXMLConstants.XMLNS_SCHEMA_LOCATION,
                SpringXMLConstants.SPRING_BEANS + "\n\t\t\t\t" + SpringXMLConstants.SPRING_BEANS_XSD));
    }
}
