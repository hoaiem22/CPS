/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.crawler;

import emvh.model.benmark.CPU;
import emvh.model.benmark.GPU;
import emvh.utils.BenmarkUtilities;
import emvh.xmlchecker.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class BenmarkCrawler {

    /**
     * Get stream from uri and use StAx parse to parse steam to List<CPU>
     * @param uri: cpubenmark url
     * @return List<CPU>
     */
    public List<CPU> crawlCPU(String uri, String beginTag, String endTag) throws UnsupportedEncodingException, XMLStreamException, XMLStreamException, SAXException, ParserConfigurationException, IOException {
        String result = "";
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
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
            Logger.getLogger(BenmarkCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BenmarkCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = TextUtils.refineHtml(result).trim();
        result = result.substring(result.indexOf("<tbody>"), result.indexOf("</tbody>"));
        result = TextUtils.refineHtml(result).trim();
        List<CPU> list = new ArrayList();
        //VIA Samuel 2
        list = BenmarkUtilities.parseCPU(result);
        return list;
    }

    /**
     * Get stream from uri and use StAx parse to parse steam to List<GPU>
     * @param uri: gpubenmark url
     * @return List<GPU>
     */
    public List<GPU> crawlGPU(String uri, String beginTag, String endTag) throws UnsupportedEncodingException, XMLStreamException, XMLStreamException {

        String result = "";
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
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
            Logger.getLogger(BenmarkCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BenmarkCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        result = TextUtils.refineHtml(result).trim();
        result = result.substring(result.indexOf("<tbody>"), result.indexOf("</tbody>"));
        result = TextUtils.refineHtml(result).trim();
        List<GPU> list = new ArrayList();
        try {
            list = BenmarkUtilities.parseGPU(result);
        } catch (SAXException | ParserConfigurationException | IOException ex) {
            Logger.getLogger(BenmarkCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
