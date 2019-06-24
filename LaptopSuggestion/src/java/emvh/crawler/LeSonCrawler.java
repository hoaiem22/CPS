/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.crawler;

import emvh.model.LaptopMapping;
import emvh.utils.LeSonUtilities;
import emvh.xmlchecker.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class LeSonCrawler {

    /**
     * Crawl Categories from home uri
     * @param uri: home uri
     * @return Map<String, String> with key is Category and value is url
     */
    public Map<String, String> crawlCategories(String uri, String beginTag, String endTag) throws UnsupportedEncodingException, XMLStreamException {
        String result = "";
        try {
            // First set the default cookie manager.
//            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(3000);
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line;
            boolean begin = false;
            boolean end = false;
            while ((line = br.readLine()) != null) {
                if (line.contains(beginTag)) {
                    begin = true;
                    continue;
                }
                if (begin && !end) {
                    if (line.contains(endTag)) {
                        break;
                    }
                    result += line;
                }
            }
            is.close();
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(LeSonCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeSonCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = "<root>" + TextUtils.refineHtml(result).trim() + "</root>";
//        System.out.println(result);
        Map<String, String> map = LeSonUtilities.stAXParserForURLLaptop(result);
        map.remove("LINH KIỆN"); //remove LINH KIỆN from list
        return map; //return map
    }

    public int crawlPage(String uri, String beginTag, String endTag) throws UnsupportedEncodingException, XMLStreamException, XMLStreamException {
        String result = "";
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line;
            boolean begin = false;
            boolean end = false;
            while ((line = br.readLine()) != null) {
                if (line.contains(beginTag)) {
                    begin = true;
                    continue;
                }
                if (begin && !end) {
                    if (line.contains(endTag)) {
                        break;
                    }
                    result += line;
                }
            }
            is.close();
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(LeSonCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeSonCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = "<root>" + result + "</root>";
        result = TextUtils.refineHtml(result).trim();
//        System.out.println(result);
        int lastPage = LeSonUtilities.getLastPage(result);
//        System.out.println(lastPage);
        return lastPage;
    }

    public List<LaptopMapping> crawlProduct(String uri, String beginTag, String endTag) throws UnsupportedEncodingException, XMLStreamException, XMLStreamException {
        String result = "";
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line;
            boolean begin = false;
            boolean end = false;
            while ((line = br.readLine()) != null) {
                if (line.contains(beginTag)) {
                    begin = true;
                    continue;
                }
                if (begin && !end) {
                    if (line.contains(endTag)) {
                        break;
                    }
                    result += line;
                }
            }
            is.close();
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(LeSonCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeSonCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = "<root>" + LeSonUtilities.cutStream(result) + "</root>";
        result = TextUtils.refineHtml(result).trim();
        List<LaptopMapping> laptopMappings = LeSonUtilities.getLaptopList(result);
        return laptopMappings;
    }

    public LaptopMapping crawlDetail(LaptopMapping laptop, String beginTag, String endTag) throws UnsupportedEncodingException, XMLStreamException, XMLStreamException, SAXException, ParserConfigurationException, IOException {
        String result = "";
        try {
            // First set the default cookie manager.
//            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            URL url = new URL(laptop.getHref());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(3000);
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line;
            boolean begin = false;
            boolean end = false;
            while ((line = br.readLine()) != null) {
                if (line.contains(beginTag)) {
                    begin = true;
                    continue;
                }
                if (begin && !end) {
                    if (line.contains(endTag)) {
                        break;
                    }
                    result += line;
                }
            }
            is.close();
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(LeSonCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeSonCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = result.substring(result.indexOf("<tbody>", result.indexOf("<div class=\"modal-body\">")),
                result.indexOf("</tbody>", result.indexOf("</tbody>") + 1));
        result = "<table>" + result + "</tbody></table>";
//        System.out.println(TextUtils.refineHtml(result).trim());
        laptop = LeSonUtilities.stAXParserForCategoriesDetail(TextUtils.refineHtml(result).trim(), laptop);
        return laptop;
    }

}
