package magnitProject.document;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadingXMLDocument {

    public ReadingXMLDocument() {
    }


    public void parseXML() throws ParserConfigurationException, IOException, SAXException {
        long summa = 0;

        List<String> list = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("src/main/resources/2.xml"));
            NodeList elements = document.getDocumentElement().getElementsByTagName("entry");
            for (int i = 0; i < elements.getLength(); i++) {
                Node element = elements.item(i);
                NamedNodeMap attributes = element.getAttributes();
                list.add(attributes.getNamedItem("field").getNodeValue());
            }
            for (String e : list) {
                Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
                Matcher matcher = pat.matcher(e);
                while (matcher.find()) {
                    summa = summa + Integer.parseInt(matcher.group());
                }
            }
        }catch (ParserConfigurationException e ){
            throw new ParserConfigurationException();
        }catch (IOException e){
            throw new IOException(e);
        }catch (SAXException e){
            throw new SAXException(e);
        }
        System.out.println(summa);
    }

}
