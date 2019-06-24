/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.timer;

import emvh.utils.Stage;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class LaptopTimer extends TimerTask {

    @Override
    public void run() {
        try {
            System.out.println("Start crawl GH");
            Stage.crawlLaptopGiaHuy();
            System.out.println("End crawl GH");
            System.out.println("Start crawl LS");
            Stage.crawlLaptopLeSon();
            System.out.println("End crawl LS");
        } catch (XMLStreamException | IOException ex) {
            Logger.getLogger(LaptopTimer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException | ParserConfigurationException ex) {
            Logger.getLogger(LaptopTimer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LaptopTimer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
