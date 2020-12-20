package magnitProject;

import magnitProject.dao.Dao;
import magnitProject.document.CreateDocument;
import magnitProject.document.ReadingXMLDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {

        Dao dao = new Dao();
        dao.setData(10);              //заполнение таблицы бд

        CreateDocument crDoc = new CreateDocument();

        try {
            Document doc = crDoc.createDoc();            //создание документа

            Element entries = crDoc.createXMLHead(doc);  //заполнение шапки документа

            List<Long> list = dao.getData();   //получение записей из таблицы

            for (Long field : list) {
                crDoc.fillingOutTheDocument(doc, entries, field);   //заполнение тела xml документа
            }
            crDoc.saveDoc(doc);
            crDoc.saveTransformerDoc();
            ReadingXMLDocument read = new ReadingXMLDocument();
            read.parseXML();

        } catch (Exception e) {
            log.info("Program execution error!");
            throw e;
        }


    }

}
