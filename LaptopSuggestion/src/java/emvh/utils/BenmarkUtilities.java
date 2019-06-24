/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.utils;

import emvh.model.benmark.CPU;
import emvh.model.benmark.GPU;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class BenmarkUtilities implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(BenmarkUtilities.class.getName());

    public static List<CPU> parseCPU(String document) throws UnsupportedEncodingException, XMLStreamException, SAXException, ParserConfigurationException, ParserConfigurationException, IOException {
        document = document.trim();
        XMLEventReader eventReader = XMLUtilities.parseStringToXMLEventReader(document);
        List<CPU> list = new ArrayList<>();
        while (eventReader.hasNext()) {
            XMLEvent event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();
                if (tagName.equals("tr")) {
                    String name = "";
                    int point = -1;
                    int rank = -1;
                    while (!XMLUtilities.isEndElementNameTag(event, "tr")) {
                        //Get name
                        XMLUtilities.moveToCharacters(eventReader);
                        event = (XMLEvent) eventReader.next();
                        //Get character (Model)
                        if (event.isCharacters()) {
                            Characters characters = event.asCharacters();
                            name = characters.getData();
                        }
                        //Get point
                        XMLUtilities.moveToCharacters(eventReader);
                        event = (XMLEvent) eventReader.next();
                        //Get character (Model)
                        if (event.isCharacters()) {
                            Characters characters = event.asCharacters();
                            point = Integer.parseInt(characters.getData());
                        }
                        //Get rank
                        XMLUtilities.moveToCharacters(eventReader);
                        event = (XMLEvent) eventReader.next();
                        //Get character (Model)
                        if (event.isCharacters()) {
                            Characters characters = event.asCharacters();
                            rank = Integer.parseInt(characters.getData());
                        }
                        if (!(name.equals("") && point > 0 && rank > 0)) {
                            list.add(new CPU(name, point, rank));
                        }
                        break;
                    }
                }
            }
        }
        return list;
    }

    public static List<GPU> parseGPU(String document) throws UnsupportedEncodingException, XMLStreamException, SAXException, ParserConfigurationException, ParserConfigurationException, IOException {
        document = document.trim();
        XMLEventReader eventReader = XMLUtilities.parseStringToXMLEventReader(document);
        List<GPU> list = new ArrayList<>();
        while (eventReader.hasNext()) {
            XMLEvent event = (XMLEvent) eventReader.next();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String tagName = startElement.getName().getLocalPart();
                if (tagName.equals("tr")) {
                    String name = "";
                    int point = -1;
                    int rank = -1;
                    while (!XMLUtilities.isEndElementNameTag(event, "tr")) {
                        //Get name
                        XMLUtilities.moveToCharacters(eventReader);
                        event = (XMLEvent) eventReader.next();
                        //Get character (Model)
                        if (event.isCharacters()) {
                            Characters characters = event.asCharacters();
                            name = characters.getData();
                        }
                        //Get point
                        XMLUtilities.moveToCharacters(eventReader);
                        event = (XMLEvent) eventReader.next();
                        //Get character (Model)
                        if (event.isCharacters()) {
                            Characters characters = event.asCharacters();
                            point = Integer.parseInt(characters.getData());
                        }
                        //Get rank
                        XMLUtilities.moveToCharacters(eventReader);
                        event = (XMLEvent) eventReader.next();
                        //Get character (Model)
                        if (event.isCharacters()) {
                            Characters characters = event.asCharacters();
                            rank = Integer.parseInt(characters.getData());
                        }
                        if (!(name.equals("") && point > 0 && rank > 0)) {
                            list.add(new GPU(name, point, rank));
                        }
                        break;
                    }
                }
            }
        }
        return list;
    }
}
