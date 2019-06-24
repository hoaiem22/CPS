/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.utils;

import emvh.constant.Constants;
import emvh.model.Laptop;
import emvh.model.LaptopMapping;
import emvh.model.Laptops;
import emvh.model.benmark.RAM;
import emvh.servlet.LaptopServlet;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Admin
 */
public class XMLUtilities implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(XMLUtilities.class.getName());

    public static Document parseDOMFromFile(String filePath) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(filePath);
//        Document doc = builder.parse("E:/Study/FPTU/8thSemester/PRX301/Example/XMLDOM/build/web/WEB-INF/studentAccounts.xml");

        return doc;
    }

    public static XPath createXpath() {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        return xpath;
    }

    public static String getValue(String xpathExp, Node node) throws XPathExpressionException {
        XPath xpath = createXpath();
        String result = (String) xpath.evaluate(xpathExp, node, XPathConstants.STRING);

        return result;
    }

    public static boolean transformDOMtoStream(Node node, String filePath) throws TransformerConfigurationException, TransformerException {
        if (node == null) {
            return false;
        }
        Source src = new DOMSource(node);

        File file = new File(filePath);
        Result result = new StreamResult(file);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer trans = factory.newTransformer();

        trans.transform(src, result);

        return true;
    }

    public static Element createElement(Document doc, String elementName, String elementVal, Map<String, String> attributes) {
        if (doc != null) {
            Element element = doc.createElement(elementName);

            if (elementVal != null) {
                element.setTextContent(elementVal);
            }

            if (attributes != null) {
                if (!attributes.isEmpty()) {
                    for (Map.Entry<String, String> entry : attributes.entrySet()) {
                        element.setAttribute(entry.getKey(), entry.getValue());
                    }
                }
            }

            return element;
        }

        return null;
    }

    public static void parseFileToSAX(String xmlFilePath, DefaultHandler handler) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(xmlFilePath, handler);
    }

    public static XMLStreamReader parseInputStreamToStAXCursor(InputStream is) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(is);

        return reader;
    }

    public static String getTextContent(String elementName, XMLStreamReader reader) throws XMLStreamException {
        if (reader == null) {
            return null;
        }

        if (elementName == null) {
            return null;
        }

        if (elementName.trim().isEmpty()) {
            return null;
        }

        while (reader.hasNext()) {
            int currentCursor = reader.getEventType();
            if (currentCursor == XMLStreamConstants.START_ELEMENT) {
                String tagName = reader.getLocalName();
                if (tagName.equals(elementName)) {
                    reader.next();//value
                    String result = reader.getText();
                    reader.nextTag();//end element

                    return result;
                }//end if element
            }//end if start element
            reader.next();
        }//end while

        return null;
    }

    public static String getTextCharacter(XMLEvent xmlEvent) {
        XMLEvent event = xmlEvent;
        if (event.isCharacters()) {

            return event.asCharacters().getData();
        }
        return "";
    }

    public static XMLEventReader parseStringToXMLEventReader(String xmlSection) throws UnsupportedEncodingException, XMLStreamException {
        byte[] byteArray = xmlSection.getBytes("UTF-8");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = inputFactory.createXMLEventReader(inputStream);

        return reader;
    }

    public static void nextEventReader(XMLEventReader eventReader, int n) {
        for (int i = 0; i < n; i++) {
            eventReader.next();
        }
    }

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream is = connection.getInputStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

    public static String getEventReaderNameTag(XMLEvent event) {
        String nameTag = "character";
        XMLEvent xmlEvent = event;
        if (xmlEvent.isStartElement()) {
            nameTag = xmlEvent.asStartElement().getName().getLocalPart();
        } else if (xmlEvent.isEndElement()) {
            nameTag = xmlEvent.asEndElement().getName().getLocalPart();
        }

        return nameTag;
    }

    public static boolean isEndElementNameTag(XMLEvent xmlEvent, String nameTag) {
        XMLEvent event = xmlEvent;
        if (event.isEndElement()) {
            if (nameTag.equals(event.asEndElement().getName().getLocalPart())) {

                return true;
            }
        }

        return false;
    }

    //Return number of open tag
    public static int moveToCharacters(XMLEventReader eventReader) throws XMLStreamException {
        int n = 0;
        //move on character
        try {
            while (!eventReader.peek().isCharacters()) {
                n++;
                eventReader.next();
            }
        } catch (Exception e) {
            return -1;
        }

        return n;
    }

    public static boolean isContainAttribute(XMLEvent event, String attr) {
        XMLEvent eventCheck = event;
        Iterator<Attribute> attribue = eventCheck.asStartElement().getAttributes();
        while (attribue.hasNext()) {
            Attribute myAttribute = attribue.next();
            if (myAttribute.getName().toString().equals(attr)) {

                return true;
            }
        }
        return false;
    }

    public static String trimColon(String s) {
//        String ex: " ::  :::Intel Core :i7-6820HK Processor (4 x 2.70 GHz) - Max Turbo Frequency: 3.60 GHz:: :";
        s = s.trim();
        while (s.startsWith(":") || s.endsWith(":")) {
            if (s.startsWith(":")) {
                s = s.substring(1, s.length()).trim();
            }
            if (s.endsWith(":")) {
                s = s.substring(0, s.length() - 1).trim();
            }
        }

        return s;
    }

    public static LaptopMapping setLaptopDescription(LaptopMapping laptop, String title, String description) {
        description = trimColon(Utils.trim160(description));

        //String img, String link, String name, String price has been parse
        if (containsIgnoreCase(title, "Model") || containsIgnoreCase(title, "Laptop")) {
            laptop.setModel(description);
        } else if (containsIgnoreCase(title, "Bộ Vi xử lý") || containsIgnoreCase(title, "CPU") || containsIgnoreCase(title, "Tốc độ tối đa") || title.equalsIgnoreCase("Chipset") || containsIgnoreCase(title, "Bộ Vi Xử Lý")) {
            String processor = (laptop.getProcessor() == null) ? description : (laptop.getProcessor() + description);
            laptop.setProcessor(processor);
        } else if (containsIgnoreCase(title, "Color") || containsIgnoreCase(title, "Màu sắc")) {
            laptop.setColor(description);
        } else if (containsIgnoreCase(title, "Ram")) {
            laptop.setRamSize(description);
        } else if (containsIgnoreCase(title, "Màn Hình")) {
            laptop.setScreenSize(description);
        } else if (containsIgnoreCase(title, "Card Đồ Họa") || containsIgnoreCase(title, "Graphics") || containsIgnoreCase(title, "Chipset đồ họa") || containsIgnoreCase(title, "Đồ họa") || containsIgnoreCase(title, "Card đồ hoạ") || containsIgnoreCase(title, "VGA")) {
            laptop.setGraphicProcessor(description);
        } else if (containsIgnoreCase(title, "Kết Nối Lan") || containsIgnoreCase(title, "LAN") || containsIgnoreCase(title, "Kết nối mạng")) {
            laptop.setLan(description);
        } else if (containsIgnoreCase(title, "Kết Nối WL") || containsIgnoreCase(title, "Kết nối")) {
            laptop.setWl(description);
        } else if (containsIgnoreCase(title, "Bluetooth")) {
            laptop.setBluetooth(description);
        } else if (containsIgnoreCase(title, "Webcam")) {
            laptop.setWebcam(description);
        } else if (containsIgnoreCase(title, "Pin") || containsIgnoreCase(title, "Thời lượng dùng")) {
            laptop.setPin(description);
        } else if (containsIgnoreCase(title, "Trọng Lượng") || containsIgnoreCase(title, "Cân nặng")) {
            laptop.setWeight(description);
        } else if (containsIgnoreCase(title, "Tính Năng Khác")) {
            laptop.setOther(description);
        } else if (containsIgnoreCase(title, "Hệ Điều Hành") || containsIgnoreCase(title, "HĐH theo máy")) {
            laptop.setOs(description);
        } else if (containsIgnoreCase(title, "Xuất Xứ")) {
            laptop.setOrigin(description);
        } else if (containsIgnoreCase(title, "Bảo Hành")) {
            laptop.setGuarantee(description);
        } else if (containsIgnoreCase(title, "Giao hàng")) {
            laptop.setShip(description);
        } else if (containsIgnoreCase(title, "Ổ Quang") || containsIgnoreCase(title, "Ổ Đĩa Quang") || containsIgnoreCase(title, "Loại đĩa quang") || containsIgnoreCase(title, "Đĩa quang")) {
            laptop.setDvd(description);
        } else if (containsIgnoreCase(title, "Bàn Phím") || containsIgnoreCase(title, "Keyboard")) {
            laptop.setKeyboard(description);
        } else if (containsIgnoreCase(title, "HDD") || containsIgnoreCase(title, "Loại ổ đĩa") || containsIgnoreCase(title, "Dung Lượng") || containsIgnoreCase(title, "Đĩa cứng") || containsIgnoreCase(title, "Bộ nhớ") || containsIgnoreCase(title, "Ổ cứng") || containsIgnoreCase(title, "Ổ Cứng")) {
            laptop.setDisk(description);
        } else if (containsIgnoreCase(title, "Cổng Giao Tiếp")) {
            laptop.setPort(description);
        } else if (containsIgnoreCase(title, "Finger print") || containsIgnoreCase(title, "Fingerprint") && containsIgnoreCase(title, "PrinFinger:")) {
            laptop.setFingerPrint(description);
        } else if (containsIgnoreCase(title, "Kênh âm thanh") || containsIgnoreCase(title, "Audio")) {
            laptop.setAudio(description);
        } else if (containsIgnoreCase(title, "Bộ nhớ đệm")) {
            laptop.setCache(description);
        } else if (containsIgnoreCase(title, "Cảm ứng")) {
            laptop.setTouchScreen(description);
        } else if (containsIgnoreCase(title, "Phụ kiện")) {
            laptop.setAccessories(description);
        } else if (containsIgnoreCase(title, "Kích thước")) {
            laptop.setSize(description);
        } else if (containsIgnoreCase(title, "Khuyến Mại")) {
            laptop.setGift(description);
        } else if (containsIgnoreCase(title, "Tình Trạng")) {
            if (containsIgnoreCase(description, "Zin 100%")) {
                laptop.setStatus("New");
            }
        } else {
            LOGGER.info(title + " : " + description + " is not in list AT LINK: " + laptop.getHref());

            return null;
        }
        return laptop;
    }

    public static LaptopMapping setDescription(LaptopMapping laptop, String title, String description, Document doc) throws XPathExpressionException {
        description = trimColon(Utils.trim160(description));
        XPath xpath = createXpath();
        String strCompare = Constants.ALPHABET + Constants.ALPHABET_VN;
//        //*[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') , 'color')]/color
        String searchValue = title.toLowerCase();
        String exp = "local-name(//*[title[contains(translate(., '" + strCompare + "', '" + strCompare.toLowerCase() + "') , '" + searchValue + "')]])";
        String result = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
        if (!result.equals("")) {
            String setter = Utils.getMethod(result, "set");
            String getter = Utils.getMethod(result, "get");
            try {
                Method getMethod = laptop.getClass().getMethod(getter);
                String laptopDescription = (String) getMethod.invoke(laptop);
                laptopDescription = (laptopDescription == null) ? "" : description;
                Method setMethod = laptop.getClass().getMethod(setter, new Class[]{description.getClass()});
                setMethod.invoke(laptop, laptopDescription + description);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            LOGGER.info(title + " : " + description + " is not in list AT LINK: " + laptop.getHref());

            return null;
        }
        return laptop;
    }

    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }

        final int length = searchStr.length();
        if (length == 0) {
            return true;
        }

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length)) {
                return true;
            }
        }
        return false;
    }

    public static String getBrandFromHref(String href) {

        //return dell with http://laptopgiahuy.com/may-tinh-xach-tay/dell
        return href.substring(href.lastIndexOf("/") + 1).toUpperCase().trim();
    }

    /**
     *
     * @param pathFile
     * @return schedule time for crawl and delay with key: hour | minute |
     * second | millisecond and delay
     */
    public static Map<String, Integer> readSchedule(String pathFile) throws ParserConfigurationException, SAXException, IOException {
        Map<String, Integer> map = new HashMap<>();
        File file = new File(pathFile);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        map.put("hour", Integer.parseInt(document.getElementsByTagName("hour").item(0).getTextContent()));
        map.put("minute", Integer.parseInt(document.getElementsByTagName("minute").item(0).getTextContent()));
        map.put("second", Integer.parseInt(document.getElementsByTagName("second").item(0).getTextContent()));
        map.put("millisecond", Integer.parseInt(document.getElementsByTagName("millisecond").item(0).getTextContent()));
        map.put("delay", Integer.parseInt(document.getElementsByTagName("delay").item(0).getTextContent()));

        return map;
    }

    public static List<RAM> getRams() {
        List<RAM> rams = new ArrayList<>();
        try {
            File fXmlFile = new File("src/ram.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(fXmlFile);
            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("ram");
            for (int temp = 0; temp < nodes.getLength(); temp++) {
                Node nNode = nodes.item(temp);
                Element eElement = (Element) nNode;
                String name = eElement.getAttribute("name");
                RAM ram = new RAM();
                ram.setName(name);
                for (int i = 0; i < eElement.getElementsByTagName("bus").getLength(); i++) {
                    ram.getBus().add(eElement.getElementsByTagName("bus").item(i).getTextContent());
                }
                rams.add(ram);
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
        }
        return rams;
    }

    public void marshallerLaptopsToTransfer(Laptops laptops, OutputStream os) {
        try {
            JAXBContext jaxb = JAXBContext.newInstance(Laptops.class);
            Marshaller marshal = jaxb.createMarshaller();
            marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshal.marshal(laptops, os);
        } catch (JAXBException ex) {
            Logger.getLogger(LaptopServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void marshallerLaptopToTransfer(Laptop laptop, OutputStream os) {
        try {
            JAXBContext jaxb = JAXBContext.newInstance(Laptop.class);
            Marshaller marshal = jaxb.createMarshaller();
            marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshal.marshal(laptop, os);
        } catch (JAXBException ex) {
            Logger.getLogger(LaptopServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String marshallToString(Laptops laptops) {
        try {
            JAXBContext jaxb = JAXBContext.newInstance(Laptops.class);
            Marshaller mar = jaxb.createMarshaller();
//        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            mar.marshal(laptops, sw);

            return sw.toString();
        } catch (Exception ex) {
            Logger.getLogger(LaptopServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
