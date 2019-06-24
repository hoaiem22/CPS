/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.timer;

import emvh.utils.Stage;
import java.util.TimerTask;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class BenchmarkTimer extends TimerTask {

    @Override
    public void run() {
//        try {
            System.out.println("Start crawl CPU");
            Stage.crawlCPUBenmark();
            System.out.println("END crawl CPU");
            System.out.println("Start crawl GPU");
            Stage.crawlGPUBenmark();
            System.out.println("END crawl GPU");

//        } catch (SAXException ex) {
//            Logger.getLogger(BenchmarkTimer.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParserConfigurationException ex) {
//            Logger.getLogger(BenchmarkTimer.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(BenchmarkTimer.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
