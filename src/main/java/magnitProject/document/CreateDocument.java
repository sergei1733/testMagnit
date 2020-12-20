package magnitProject.document;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.logging.Logger;

public class CreateDocument {
    private static Logger log = Logger.getLogger(CreateDocument.class.getName());

    public CreateDocument() {
    }

    public Document createDoc() throws ParserConfigurationException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Document doc = factory.newDocumentBuilder().newDocument();
            return doc;
        } catch (ParserConfigurationException e) {
            log.info("Exception");
            throw e;
        }
    }

    public Element createXMLHead(Document doc) {

        Element test = doc.createElement("TEST");
        test.setAttribute("xmlns", "");
        doc.appendChild(test);
        Element entries = doc.createElement("entries");
        test.appendChild(entries);
        return entries;

    }

    public void saveDoc(Document doc) throws Exception {
        try {
            File file = new File("src/main/resources/1.xml");
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(file));
        } catch (Exception e) {
            log.info("Failed to save document ");
            throw e;
        }
    }

    public void saveTransformerDoc() throws TransformerException {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File("src/main/resources/style.xsl"));
            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(new File("src/main/resources/1.xml"));
            transformer.transform(xml, new StreamResult(new File("src/main/resources/2.xml")));
        } catch (TransformerException exception) {
            log.info("Failed to complete changes");
            throw exception;
        }

    }

    public void fillingOutTheDocument(Document doc, Element entries, Long number) {
        Element entry = doc.createElement("entry");
        entries.appendChild(entry);

        Element field = doc.createElement("field");
        field.appendChild(doc.createTextNode("значение поля " + number));
        entry.appendChild(field);
    }


}
