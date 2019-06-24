/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.timer;

import emvh.constant.Constants;
import emvh.listener.ContextListenter;
import emvh.utils.XMLUtilities;
import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class ScheduleCrawl extends TimerTask {

    private String scheduleFile;

    public ScheduleCrawl(String scheduleFile) {
        this.scheduleFile = scheduleFile;
    }

    @Override
    public void run() {
        try {
            Constants.mapSchedule = XMLUtilities.readSchedule(scheduleFile);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(ContextListenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
