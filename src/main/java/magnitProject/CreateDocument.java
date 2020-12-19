package magnitProject;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class CreateDocument {

    public CreateDocument() {
    }

    public Document createDoc() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().newDocument();
        return doc;
    }

    public Element createXMLHead(Document doc) throws Exception {

        Element test = doc.createElement("TEST");
        test.setAttribute("xmlns", "");
        doc.appendChild(test);
        Element entries = doc.createElement("entries");
        test.appendChild(entries);
/*
        FillingOutTheDocument(doc, entries);

        File file = new File("/home/h/IdeaProjects/testTaskMagnit/test.xml");

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));

 */
        return entries;
    }

    public void saveDoc(Document doc) throws Exception {
        File file = new File("1.xml");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));
    }

    public void saveTransformerDoc(Document doc) throws TransformerException {

        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("style.xsl"));
        Transformer transformer = factory.newTransformer(xslt);
        Source xml = new StreamSource(new File("1.xml"));
        transformer.transform(xml, new StreamResult(new File("2.xml")));

    }

    public void FillingOutTheDocument(Document doc, Element entries, Long nomer) {
        Element entry = doc.createElement("entry");
        entries.appendChild(entry);

        Element field = doc.createElement("field");
        field.appendChild(doc.createTextNode("значение поля " + nomer));
        entry.appendChild(field);
    }


}
