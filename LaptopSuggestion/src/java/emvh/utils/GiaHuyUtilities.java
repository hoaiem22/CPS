/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.utils;

import emvh.model.LaptopMapping;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class GiaHuyUtilities implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(GiaHuyUtilities.class.getName());

    public static String getBrandFormHrefDetail(String href) {
        //http://laptopgiahuy.com/may-tinh-xach-tay/dell/dell-inspiron-3168-3272-pentium-n3710-|-4gb-|-500gb-|-w10-gray-15079.html
        int length = "http://laptopgiahuy.com/may-tinh-xach-tay/".length();
        href = href.substring(length);

        return href.substring(0, href.indexOf("/"));

    }

    public static LaptopMapping stAXParserForCategoriesDetail(String document, LaptopMapping laptop) throws UnsupportedEncodingException, XMLStreamException {
        document = document.trim();
        XMLEventReader eventReader = XMLUtilities.parseStringToXMLEventReader(document);
        int n = 0; //number of open tag
        try {
            while (eventReader.hasNext()) {
                n = 0;
                XMLEvent event = (XMLEvent) eventReader.next();
                String title = "";
                String description = "";

                if (event.isStartElement()) {
                    Characters characters;
                    EndElement endElement;
                    StartElement startElement = event.asStartElement();
                    String tagName = startElement.getName().getLocalPart();
                    if ("li".equals(tagName)) {
                        //move on character
                        n = XMLUtilities.moveToCharacters(eventReader);
                        if (n == -1) {
                            continue;
                        }
                        event = (XMLEvent) eventReader.next();
                        //Get character (Model)
                        if (event.isCharacters()) {
                            characters = event.asCharacters();
                            title = characters.getData();
                        }
                        //Move on close tag reset number of open tag
                        XMLUtilities.nextEventReader(eventReader, n);
                        n = 0;
                        description = "";
                        event = (XMLEvent) eventReader.next();
                        while (!XMLUtilities.isEndElementNameTag(event, "li")) {
                            //Get character
                            if (event.isCharacters()) {
                                characters = event.asCharacters();
                                description += " " + characters.getData();
                            }
                            //check if character is colon
                            //Ex: " : Bộ Vi xử lý"
                            if (description.contains(": ")) {
                                if (description.length() > ": ".length()) {
                                    description = description.substring(": ".length());
                                }
                            }
                            event = (XMLEvent) eventReader.next();
                        }
                        //END GET TEXT OF DESCRIPTION
                    }//end if li
                }
//            System.out.println(title + " | " + description);
                if (!title.equals("") && !description.equals("")) {
//                    System.out.println(++i + "| " + title.trim() + "| " + description.trim());
                    XMLUtilities.setLaptopDescription(laptop, title, description);
                }
            }
        } catch (Exception e) {
            LOGGER.info("XMLUtilites: Crawl fail because: " + e.getMessage() + "at Link: " + laptop.getHref());
        }
        return laptop;
    }

    public static Map<String, String> stAXParserForURLLaptop(String document) throws UnsupportedEncodingException, XMLStreamException {
        document = document.trim();
        XMLEventReader eventReader = XMLUtilities.parseStringToXMLEventReader(document);
        Map<String, String> laptopList = new HashMap<>();
        while (eventReader.hasNext()) {
            XMLEvent event = (XMLEvent) eventReader.next();
            //1. while a tag

            while (!XMLUtilities.isEndElementNameTag(event, "ul")) {
                //2. get link = character, link = a href
                String homeURL = "http://laptopgiahuy.com";
                String link = "";
                String name = "";
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String tagName = startElement.getName().getLocalPart();
                    if ("a".equals(tagName)) {
                        Attribute attrHref = startElement.getAttributeByName(new QName("href"));
                        String href = attrHref.getValue();
                        //If first letter of href is / then link = homeURL + href
                        //else link = homeURL + "/" + href
                        link = (Character.toString(href.charAt(0)).equals("/"))
                                ? (homeURL + href) : (homeURL + "/" + href);
                        event = (XMLEvent) eventReader.next();
                        Characters characters = event.asCharacters();
                        name = characters.getData();
                    }
                }
                event = (XMLEvent) eventReader.next();
                if (!link.equals("") && !name.equals("")) {
                    laptopList.put(name, link);
//                    System.out.println(name + " | " + link);
                }
            }

            break;
        }
        return laptopList;
    }

    public static List<LaptopMapping> stAXParserForLaptop(String document) throws UnsupportedEncodingException, XMLStreamException {
        document = document.trim();
        XMLEventReader eventReader = XMLUtilities.parseStringToXMLEventReader(document);
        List<LaptopMapping> laptopList = new ArrayList();
        String homeURL = "http://laptopgiahuy.com/";
        int i = 1;
        while (eventReader.hasNext()) {
            String href = "";
            String name = "";
            String img = "";
            String status = "";
            String price = "";
            XMLEvent event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();
                if ("a".equals(tagName)) {
                    if (!XMLUtilities.isContainAttribute(event, "href")) {
                        continue;
                    }
                    Attribute attrHref = startElement.getAttributeByName(new QName("href"));
                    href = homeURL + attrHref.getValue();
                    Attribute attrTitle = startElement.getAttributeByName(new QName("title"));
                    name = attrTitle.getValue();
                    event = (XMLEvent) eventReader.next();
                    while (!"img".equals(XMLUtilities.getEventReaderNameTag(event))) {
                        event = (XMLEvent) eventReader.next();
                    }
                    img = "http://laptopgiahuy.com" + event.asStartElement().getAttributeByName(new QName("data-original")).getValue();
                    status = event.asStartElement().getAttributeByName(new QName("status")).getValue();
                    while (!"b".equals(XMLUtilities.getEventReaderNameTag(event))) {
                        event = (XMLEvent) eventReader.next();
                    }
                    price = event.asStartElement().getAttributeByName(new QName("data")).getValue();
                    price = Utils.filterPrice(price);
                }
                if (!(name.equals("") && href.equals("") && img.equals("") && status.equals("") && price.equals(""))) {
                    LaptopMapping laptop = new LaptopMapping(status, img, href, name, price);
                    laptop.setBrand(getBrandFormHrefDetail(laptop.getHref()).toUpperCase());
                    laptopList.add(laptop);
                }

            }
//            if (!(name.equals("") && href.equals("") && img.equals("") && status.equals(""))) {
//                System.out.println(num++ + " | " + i++ + "|" + name + " | " + href + " | " + img + " | " + status);
//            }
        }
        return laptopList;
    }
}
