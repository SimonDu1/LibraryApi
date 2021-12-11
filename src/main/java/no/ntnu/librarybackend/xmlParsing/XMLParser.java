package no.ntnu.librarybackend.xmlParsing;

import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.*;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {


    static JSONArray bookdetail = new JSONArray();

    public XMLParser() {

    }

    public static void main(String args[]) {
        XMLParser parser = new XMLParser();
        String isbn = "978-82-489-2327-5";
        String newHei = "978-82-7900-843-9";
        bookdetail.put(parser.parseXML("47BIBSYS_NTNU_UB", newHei));
    }

    public static JSONObject parseXML(String librarySystem, String isbn) {
        JSONObject book = new JSONObject();
        try {
            String title = "";
            String author = "";
            String summary = "";
            String image = "";
            URL url = new URL("https://bibsys.alma.exlibrisgroup.com/view/sru/" + librarySystem + "?version=1.2&operation=searchRetrieve&recordSchema=marcxml&query=alma.isbn=" + isbn);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(url.openStream());
            doc.getDocumentElement().normalize();

            NodeList datafieldNL = doc.getElementsByTagName("datafield");
            for(int index = 0; index < datafieldNL.getLength(); index++) {
                Node datafieldnode = datafieldNL.item(index);
                Element element = (Element) datafieldnode;

                NodeList subfieldnl = element.getElementsByTagName("subfield");

                //Get Title and author
                if (datafieldnode.getAttributes().item(2).getNodeValue().equals("245")){
                    for(int i = 0; i < subfieldnl.getLength(); i++){
                        Node subfieldnode = subfieldnl.item(i);
                        Element subfieldelement = (Element) subfieldnode;
                        String subfieldCode = String.valueOf(subfieldnode.getAttributes().item(0).getNodeValue());
                        if (subfieldCode.equals("a") || subfieldCode.equals("b")){
                            title += subfieldelement.getTextContent();
                        }
                        if (subfieldCode.equals("c")){
                            author = subfieldelement.getTextContent();
                        }
                    }
                }
                //Get Book summary
                if (datafieldnode.getAttributes().item(2).getNodeValue().equals("520")){
                    for(int i = 0; i < subfieldnl.getLength(); i++){
                        Node subfieldnode = subfieldnl.item(i);
                        Element subfieldelement = (Element) subfieldnode;
                        String subfieldCode = String.valueOf(subfieldnode.getAttributes().item(0).getNodeValue());
                        if (subfieldCode.equals("a") || subfieldCode.equals("b")){
                            summary = subfieldelement.getTextContent();
                        }

                    }
                }
                //Get Image
                if (datafieldnode.getAttributes().item(2).getNodeValue().equals("856") && datafieldnode.getAttributes().item(1).getNodeValue().equals("1")){
                    for(int i = 0; i < subfieldnl.getLength(); i++){
                        Node subfieldnode = subfieldnl.item(i);
                        Element subfieldelement = (Element) subfieldnode;
                        String subfieldCode = String.valueOf(subfieldnode.getAttributes().item(0).getNodeValue());
                        if (subfieldCode.equals("u")){
                            if(image.isEmpty()){
                                image = subfieldelement.getTextContent();
                            }
                        }
                    }
                }


            }
            book.put("Title",title);
            book.put("Author",author);
            book.put("Summary",summary);
            book.put("Image",image);

        } catch (IOException | ParserConfigurationException | SAXException  | JSONException e) {
            System.out.println(e.getMessage());
        }
        return book;
    }

}