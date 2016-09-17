package cn.vansky.code.generator.xml.spring.document;

import cn.vansky.code.generator.config.CodeGenContext;
import cn.vansky.code.generator.db.AbstractAttributes;
import cn.vansky.code.generator.db.TableInfoWrapper;
import cn.vansky.code.generator.xml.spring.element.AbstractSpringXMLElement;
import cn.vansky.code.generator.xml.spring.element.EmptySpringXMLElement;
import cn.vansky.code.generator.xml.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA .
 * Auth: CK
 * Date: 2016/7/16
 */
public class EmptySpringXMLDocument extends AbstractSpringXMLDocument<AbstractAttributes> {

    public EmptySpringXMLDocument(CodeGenContext context, List<TableInfoWrapper<AbstractAttributes>> tableInfoWrapper) {
        super(context, tableInfoWrapper);
    }

    public List<Document> getDocument() {
        List<Document> documents = new ArrayList<Document>();

        addEmptyXML(documents);

        return documents;
    }

    public void addEmptyXML(List<Document> documents) {
        AbstractSpringXMLElement generator = new EmptySpringXMLElement("empty.xml", context,  tableInfoWrapper);
        documents.add(generator.getDocument());
    }
}
