/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.crawler;

import emvh.model.LaptopMapping;
import emvh.utils.GiaHuyUtilities;
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
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class GiaHuyCrawler {

    /**
     * Crawl Categories from home uri
     * @param uri: home uri
     * @return Map<String, String> with key is Category and value is url
     */
    public Map<String, String> crawlCategories(String uri, String beginTag, String endTag) throws UnsupportedEncodingException, XMLStreamException, XMLStreamException {
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
//                System.out.println(line);
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
            Logger.getLogger(GiaHuyCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GiaHuyCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = "<ul>" + result + "</ul>";
        Map<String, String> laptop = GiaHuyUtilities.stAXParserForURLLaptop(TextUtils.refineHtml(result).trim());

        return laptop;
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
                    if (line.contains("Paging")) {
                        result += line;
                    }
                }
            }
            is.close();
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(GiaHuyCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GiaHuyCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = TextUtils.refineHtml(result).trim();
        int start = result.indexOf("pages") + 7;
        int end = result.indexOf("count") - 2;

        return Integer.parseInt(result.substring(start, end));
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
                    result += line;
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
            Logger.getLogger(GiaHuyCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GiaHuyCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }

        int start = result.indexOf("<div id=\"ProductGroup-Ajax\"");
        int end = result.indexOf("<div class=\"clearfix\"></div><div class=\"Paging");
        result = result.substring(start, end);
        result = TextUtils.refineHtml(result).trim();
//        System.out.println(result);
        List<LaptopMapping> laptopList = GiaHuyUtilities.stAXParserForLaptop(result);

        return laptopList;
    }

    public LaptopMapping crawlDetail(LaptopMapping laptop, String beginTag, String endTag) throws UnsupportedEncodingException, XMLStreamException, XMLStreamException {
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
//                System.out.println(line);
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
            Logger.getLogger(GiaHuyCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GiaHuyCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }

        result = "<ul>" + result + "</ul>";
        laptop = GiaHuyUtilities.stAXParserForCategoriesDetail(TextUtils.refineHtml(result).trim(), laptop);

        return laptop;
    }
}
