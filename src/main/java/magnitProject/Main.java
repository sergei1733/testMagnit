package magnitProject;

import magnitProject.dao.Dao;
import magnitProject.document.CreateDocument;
import magnitProject.document.ReadingXMLDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        Dao dao = new Dao();
        dao.setData(1000);              //заполнение таблицы бд

        CreateDocument crDoc = new CreateDocument();

        try {
            Document doc = crDoc.createDoc();            //создание документа

            Element entries = crDoc.createXMLHead(doc);  //заполнение шапки документа

            List<Long> list = dao.getData(doc, entries);   //получение записей из таблицы

            for (Long field : list) {
                crDoc.fillingOutTheDocument(doc, entries, field);   //заполнение тела xml документа
            }
            crDoc.saveDoc(doc);
            crDoc.saveTransformerDoc(doc);
            ReadingXMLDocument read = new ReadingXMLDocument();
            read.parseXML();

        }catch (Exception e){
            throw new Exception();
        }


    }

}
