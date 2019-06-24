/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.utils;

import emvh.constant.Constants;
import emvh.model.LaptopMapping;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class LeSonUtilities implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(XMLUtilities.class.getName());

    public static String cutStream(String stream) {
        String str = stream;
        str = str.substring(0, str.indexOf("<div class=\"bottom_pagination width_common\">"));

        return str;
    }

    public static int getLastPage(String document) throws UnsupportedEncodingException, XMLStreamException {
        document = document.trim();
        XMLEventReader eventReader = XMLUtilities.parseStringToXMLEventReader(document);
        int lastPage = 1;
        try {
            while (eventReader.hasNext()) {
                XMLEvent event = (XMLEvent) eventReader.next();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String tagName = startElement.getName().getLocalPart();
                    while (!XMLUtilities.isEndElementNameTag(event, "root")) {
                        event = (XMLEvent) eventReader.next();
                        if (event.isCharacters()) {
                            lastPage = Integer.parseInt(XMLUtilities.getTextCharacter(event));
                        }
                    }//end while roole

                    return lastPage;
                }

            }
        } catch (Exception e) {
        }
        return -1;
    }

    public static List<LaptopMapping> getLaptopList(String document) throws UnsupportedEncodingException, XMLStreamException {
        document = document.trim();
        XMLEventReader eventReader = XMLUtilities.parseStringToXMLEventReader(document);
        String href = "";
        String name = "";
        String img = "";
        String status = "";
        String price = "";
        List<LaptopMapping> laptopList = new ArrayList<>();
        try {
            while (eventReader.hasNext()) {
                XMLEvent event = (XMLEvent) eventReader.next();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String tagName = startElement.getName().getLocalPart();
                    //Reset field
                    if (tagName.equals("div")) {
                        Attribute classAttr = startElement.getAttributeByName(new QName("class"));
                        if (classAttr.getValue().contains("box_item")) {
                            if (!(name.equals("") && href.equals("") && img.equals("") && price.equals(""))) {
                                if (status.equals("")) {
                                    status = "Bình thường";
                                }
                                LaptopMapping laptop = new LaptopMapping(status, img, href, name, price);
//                                System.out.println("Add laptop:  " + laptop.toString());
                                laptopList.add(laptop);
                            }
                            href = "";
                            name = "";
                            img = "";
                            status = "";
                            price = "";
                        }
                        //START GET HREF & NAME
                        if (classAttr.getValue().equals("pro_name")) {
                            event = (XMLEvent) eventReader.next();
                            if (event.isStartElement()) {
                                startElement = event.asStartElement();
                                Attribute hrefAttr = startElement.getAttributeByName(new QName("href"));
                                //Get href
                                href = hrefAttr.getValue();
                                event = (XMLEvent) eventReader.next();
                                if (event.isCharacters()) {
                                    Characters characters = event.asCharacters();
                                    //Get name
                                    name = characters.getData();
                                }
                            }
                        }
                        //END GET HREF & NAME
                        //START GET PRICE
                        if (classAttr.getValue().equals("pro_price")) {
                            event = (XMLEvent) eventReader.next();
                            if (event.isStartElement()) {
                                startElement = event.asStartElement();
                                tagName = startElement.getName().getLocalPart();
                                if (tagName.equals("span")) {
                                    XMLUtilities.moveToCharacters(eventReader);
                                    event = (XMLEvent) eventReader.next();
                                    if (event.isCharacters()) {
                                        Characters characters = event.asCharacters();
                                        //Get price
                                        price = characters.getData();
                                        price = Utils.filterPrice(price);
                                    }
                                }
                            }
                        }
                        //END GET PRICE
                    }
                    //GET DISCOUNT PRICE
                    if (tagName.equals("ins")) {
                        event = (XMLEvent) eventReader.next();
                        event = (XMLEvent) eventReader.next();
                        if (event.isCharacters()) {
                            Characters characters = event.asCharacters();
                            //Get price
                            price = characters.getData();
                            price = Utils.filterPrice(price);
                            //Set status is Discount
                            status = "Giảm giá";
                        }
                    }
                    //END GET DISCOUNT PRICE
                    //START GET STATUS
//                    <span class="thumb-discount-label">
//                        <span class="akasa_flag flag_new">New</span>
//                    </span>
                    if (tagName.equals("span")) {
                        Attribute classAttr = startElement.getAttributeByName(new QName("class"));
                        if (classAttr.getValue().equals("thumb-discount-label")) {
                            if (eventReader.peek().isStartElement()) {
                                XMLUtilities.moveToCharacters(eventReader);
                                event = (XMLEvent) eventReader.next();
                                //Get character
                                if (event.isCharacters()) {
                                    Characters characters = event.asCharacters();
                                    status = characters.getData();
                                }
                            }
                        }
                    }
                    //END GET STATUS
                    //START GET IMAGE
                    if (tagName.equals("img")) {
                        Attribute srcAttr = startElement.getAttributeByName(new QName("src"));
                        img = srcAttr.getValue();
                    }
                    //END GET IMAGE
                }
                //Get last item
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    String tagName = endElement.getName().getLocalPart();
                    if (tagName.equals("root")) {
                        if (!(name.equals("") && href.equals("") && img.equals("") && status.equals("") && price.equals(""))) {
                            if (status.equals("")) {
                                status = "Bình thường";
                            }
                            LaptopMapping laptop = new LaptopMapping(status, img, href, name, price);
                            laptopList.add(laptop);
                        }
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("ERROR AT LeSonUtilities: " + e.getMessage());
        }
        return laptopList;
    }

    public static LaptopMapping stAXParserForCategoriesDetail(String document, LaptopMapping laptop) throws UnsupportedEncodingException, XMLStreamException, SAXException, ParserConfigurationException, IOException {
        XMLEventReader eventReader = XMLUtilities.parseStringToXMLEventReader(document);
        Document doc = XMLUtilities.parseDOMFromFile(Constants.realPath + Constants.XML_CONFIG_TITLE);
        if (doc == null) {
            System.out.println("Document is null");
            return null;
        }
        try {
            while (eventReader.hasNext()) {
                String title = "";
                String description = "";
                XMLEvent event = (XMLEvent) eventReader.next();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String tagName = startElement.getName().getLocalPart();
                    if (tagName.equals("tr")) {
                        event = (XMLEvent) eventReader.next();
                        event = (XMLEvent) eventReader.next();
                        if (event.isCharacters()) {
                            Characters characters = event.asCharacters();
                            title = characters.getData();
                            event = (XMLEvent) eventReader.next();
                            while (!XMLUtilities.isEndElementNameTag(event, "tr")) {
                                if (event.isCharacters()) {
                                    characters = event.asCharacters();
                                    description += characters.getData();
                                }
                                event = (XMLEvent) eventReader.next();
                            }
                        }
                    }
                }
                if (!(title.equals("") && description.equals(""))) {
//                    System.out.println(title + " | " + description);
                    XMLUtilities.setDescription(laptop, title, description, doc);
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
        Map<String, String> map = new HashMap<>(); //Map<LaptopCategory, HREF> (Dell, ls.com/dell)
        while (eventReader.hasNext()) {
            XMLEvent event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                String category = "";
                String href = "";
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();
                if (tagName.equals("a")) {
                    //Get href by qName href
                    Attribute attrHref = startElement.getAttributeByName(new QName("href"));
                    href = attrHref.getValue();
                    //Get laptopType as next event (Character)
                    event = (XMLEvent) eventReader.next();
                    if (event.isCharacters()) {
                        Characters characters = event.asCharacters();
//                      Laptop Dell -> DELL
                        category = characters.getData().replace("Laptop", "").trim().toUpperCase();

                    }
                    if (!(href.equals("") && category.equals(""))) {
                        map.put(category, href);
                    }
                }
            }
        }
        return map;
    }
}
