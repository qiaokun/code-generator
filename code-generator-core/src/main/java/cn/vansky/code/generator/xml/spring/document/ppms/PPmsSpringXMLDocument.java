package cn.vansky.code.generator.xml.spring.document.ppms;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.xml.spring.document.AbstractSpringXMLDocument;
import cn.vansky.code.generator.xml.spring.element.AbstractSpringXMLElement;
import cn.vansky.code.generator.xml.spring.element.ppms.PPmsSpringActionXMLElement;
import cn.vansky.code.generator.xml.spring.element.ppms.PPmsSpringServletXMLElement;
import cn.vansky.code.generator.xml.spring.element.ppms.PPmsSpringTxXMLElement;
import cn.vansky.code.generator.xml.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * PPMS Spring generator
 * Author: CK.
 * Date: 2015/6/6.
 */
public class PPmsSpringXMLDocument extends AbstractSpringXMLDocument {

    public PPmsSpringXMLDocument(CodeGenContext context, List<TableInfoWrapper> tableInfoWrapper) {
        super(context, tableInfoWrapper);
    }

    public List<Document> getDocument() {
        List<Document> documents = new ArrayList<Document>();

        addSpringTxXML(documents);

        addSpringActionXml(documents);

        addSpringServletXML(documents);

        return documents;
    }

    protected void addSpringTxXML(List<Document> documents) {
        AbstractSpringXMLElement generator = new PPmsSpringTxXMLElement("applicationContext-ppms-tx.xml", context,  tableInfoWrapper);
        documents.add(generator.getDocument());
    }

    protected void addSpringActionXml(List<Document> documents) {
        AbstractSpringXMLElement generator = new PPmsSpringActionXMLElement("applicationContext-ppms-action.xml", context, tableInfoWrapper);
        documents.add(generator.getDocument());
    }

    protected void addSpringServletXML(List<Document> documents) {
        AbstractSpringXMLElement generator = new PPmsSpringServletXMLElement("applicationContext-ppms-servlet.xml", context, tableInfoWrapper);
        documents.add(generator.getDocument());
    }
}
