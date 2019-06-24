/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.utils;

import emvh.constant.Constants;
import emvh.domain.BenchmarkDomain;
import emvh.domain.Domain;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class DomainUtilities {

    private static final Logger LOGGER = Logger.getLogger(DomainUtilities.class.getName());

    public static BenchmarkDomain getTagBenchmark(String str) {
        BenchmarkDomain benchmark = new BenchmarkDomain();
        try {
            File fXMLFile = new File(Constants.realPath + Constants.XML_CONFIG_BENCHMARK);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(fXMLFile);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName(str);
            if (nodes.getLength() == 0) {
                return null;
            }
            for (int temp = 0; temp < nodes.getLength(); temp++) {
                Node nNode = nodes.item(temp);
                Element eElement = (Element) nNode;
                benchmark.setUrl(eElement.getAttribute("url"));
                benchmark.setBegin(eElement.getElementsByTagName("begin").item(0).getTextContent());
                benchmark.setEnd(eElement.getElementsByTagName("end").item(0).getTextContent());

            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            LOGGER.log(Level.INFO, "ERROR at getTagBenchmark: {0}", e.getMessage());
        }
        return benchmark;
    }

    public static Domain getTagDomain(String strDomain) {
        Domain domain = new Domain();
        try {
            File fXmlFile = new File(Constants.realPath + Constants.XML_CONFIG_DOMAIN);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("domain");
            for (int tmp = 0; tmp < nodes.getLength(); tmp++) {
                Node nNode = nodes.item(tmp);
                Element eElement = (Element) nNode;
                String name = eElement.getAttribute("name");
                if (name.equalsIgnoreCase(strDomain)) {
                    domain.setHomeURL(eElement.getAttribute("url"));
                    NodeList childs = nNode.getChildNodes();
                    for (int i = 0; i < childs.getLength(); i++) {
                        Node childNode = childs.item(i);
                        String tag = childNode.getNodeName();
                        if (tag.equals("category")) {
                            Element nElement = (Element) childNode;
                            domain.setBeginCategory(nElement.getElementsByTagName("begin").item(0).getTextContent());
                            domain.setEndCategory(nElement.getElementsByTagName("end").item(0).getTextContent());
                        }
                        if (tag.equals("page")) {
                            Element nElement = (Element) childNode;
                            domain.setBeginPage(nElement.getElementsByTagName("begin").item(0).getTextContent());
                            domain.setEndPage(nElement.getElementsByTagName("end").item(0).getTextContent());
                        }
                        if (tag.equals("product")) {
                            Element nElement = (Element) childNode;
                            domain.setBeginProduct(nElement.getElementsByTagName("begin").item(0).getTextContent());
                            domain.setEndProduct(nElement.getElementsByTagName("end").item(0).getTextContent());
                        }
                        if (tag.equals("detail")) {
                            Element nElement = (Element) childNode;
                            domain.setBeginDetail(nElement.getElementsByTagName("begin").item(0).getTextContent());
                            domain.setEndDetail(nElement.getElementsByTagName("end").item(0).getTextContent());
                        }
                    }
                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {

        }
        return domain;
    }
}
