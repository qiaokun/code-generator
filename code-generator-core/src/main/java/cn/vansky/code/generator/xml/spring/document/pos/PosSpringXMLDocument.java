package cn.vansky.code.generator.xml.spring.document.pos;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.xml.Document;
import cn.vansky.code.generator.xml.spring.document.AbstractSpringXMLDocument;
import cn.vansky.code.generator.xml.spring.element.AbstractSpringXMLElement;
import cn.vansky.code.generator.xml.spring.element.pos.PosSpringActionXMLElement;
import cn.vansky.code.generator.xml.spring.element.pos.PosSpringServletXMLElement;

import java.util.ArrayList;
import java.util.List;

/**
 * PPMS Spring generator
 * Author: CK.
 * Date: 2015/6/6.
 */
public class PosSpringXMLDocument extends AbstractSpringXMLDocument {

    public PosSpringXMLDocument(CodeGenContext context, List<TableInfoWrapper> tableInfoWrapper) {
        super(context, tableInfoWrapper);
    }

    public List<Document> getDocument() {
        List<Document> documents = new ArrayList<Document>();

        addSpringActionXml(documents);

        addSpringServletXML(documents);

        return documents;
    }

    protected void addSpringActionXml(List<Document> documents) {
        AbstractSpringXMLElement generator = new PosSpringActionXMLElement("applicationContext-pos-ppms-action.xml", context, tableInfoWrapper);
        documents.add(generator.getDocument());
    }

    protected void addSpringServletXML(List<Document> documents) {
        AbstractSpringXMLElement generator = new PosSpringServletXMLElement("applicationContext-pos-ppms-servlet.xml", context, tableInfoWrapper);
        documents.add(generator.getDocument());
    }
}
