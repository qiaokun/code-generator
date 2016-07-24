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
 * PPMS tx Spring generator
 * Author: CK.
 * Date: 2015/6/6.
 */
public class PPmsSpringTxXMLElement extends AbstractSpringXMLElement {

    public PPmsSpringTxXMLElement(String name, CodeGenContext context, List<TableInfoWrapper> ts) {
        super(name, context, ts);
    }

    public void getElement(XmlElement answer) {
        answer.addAttribute(new Attribute("\n\t\t\t\t" + SpringXMLConstants.XMLNS_SCHEMA_LOCATION,
                SpringXMLConstants.SPRING_BEANS + "\n\t\t\t\t" + SpringXMLConstants.SPRING_BEANS_XSD));

        for (TableInfoWrapper t : this.tableInfoWrappers) {
            PPmsAttributes attributes = ((PPmsAttributes) t.getAttributes());
            XmlElement service = new XmlElement("bean");
            JavaTypeInfo serviceId = attributes.getService();
            service.addAttribute(new Attribute("id", JavaBeansUtil.getValidPropertyName(serviceId.getShortName())));
            JavaTypeInfo serviceImpl = attributes.getServiceImpl();
            service.addAttribute(new Attribute("class", serviceImpl.getFullyQualifiedName()));
            service.addAttribute(new Attribute("parent", "baseTransactionService"));

            JavaTypeInfo ppmsDao = attributes.getDao();
            String shortName = JavaBeansUtil.getValidPropertyName(ppmsDao.getShortName());
            XmlElement baseDao = new XmlElement("property", true);
            baseDao.addAttribute(new Attribute("name", "baseDao"));
            baseDao.addAttribute(new Attribute("ref", shortName));
            service.addElement(baseDao);

            XmlElement dao = new XmlElement("property", true);
            dao.addAttribute(new Attribute("name", shortName));
            dao.addAttribute(new Attribute("ref", shortName));
            service.addElement(dao);

            answer.addElement(service);
        }
    }
}
