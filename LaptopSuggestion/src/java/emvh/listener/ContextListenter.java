/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.listener;

import emvh.constant.Constants;
import emvh.timer.BenchmarkTimer;
import emvh.timer.LaptopTimer;
import emvh.timer.ScheduleCrawl;
import emvh.utils.DomainUtilities;
import emvh.utils.Stage;
import emvh.utils.Utils;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class ContextListenter implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("Context Initialized");
            ServletContext context = sce.getServletContext();
            Constants.realPath = context.getRealPath("/");
//            crawl();
//            String xmlFilePath = Constants.realPath + Constants.XML_CONFIG_SCHEDULE;

//            System.out.println("START CRAWL CPU");
//            Stage.crawlCPUBenmark();
//            System.out.println("END CRAWL CPU");
//            System.out.println("START CRAWL GPU");
//            Stage.crawlGPUBenmark();
//            System.out.println("END CRAWL GPU");
//            System.out.println("START CRAWL GIAHUY");
//            Stage.crawlLaptopGiaHuy();
//            System.out.println("END CRAWL GIAHUY");
//            System.out.println("START CRAWL LESON");
//            Stage.crawlLaptopLeSon();
//            System.out.println("END CRAWL LE SON");
//            END FOR SCHEDULE
            System.out.println("END Context Initialized");
        } catch (Exception ex) {
            Logger.getLogger(ContextListenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context Destroyed");
    }

    public void crawl() {
        //Domain for crawl data
        Constants.LAPTOP_GIAHUY = DomainUtilities.getTagDomain("laptopgiahuy");
        Constants.LAPTOP_LESON = DomainUtilities.getTagDomain("laptopleson");
        Constants.BENCHMARK_CPU = DomainUtilities.getTagBenchmark("cpu");
        Constants.BENCHMARK_GPU = DomainUtilities.getTagBenchmark("gpu");
        ScheduleCrawl readSchedule = new ScheduleCrawl(Constants.realPath + Constants.XML_CONFIG_SCHEDULE);
        readSchedule.run();
        Timer timer = new Timer();
        BenchmarkTimer crawlBenchmark = new BenchmarkTimer();
        LaptopTimer crawlLaptop = new LaptopTimer();
        int hour = Constants.mapSchedule.get("hour");
        int min = Constants.mapSchedule.get("minute");
        int second = Constants.mapSchedule.get("second");
        int millisecond = Constants.mapSchedule.get("millisecond");
        long period = Constants.mapSchedule.get("delay");

        timer.scheduleAtFixedRate(readSchedule, Utils.getCalendar(hour - 1, min, second, millisecond).getTime(), period);
        System.out.println("Schedule for Read at: " + Utils.getCalendar(hour - 1, min, second, millisecond).getTime());
        timer.scheduleAtFixedRate(crawlBenchmark, Utils.getCalendar(hour, min, second, millisecond).getTime(), period);
        System.out.println("Schedule for crawlBenchmark at: " + Utils.getCalendar(hour, min, second, millisecond).getTime());
        timer.scheduleAtFixedRate(crawlLaptop, Utils.getCalendar(hour, min + 5, second, millisecond).getTime(), period);
        System.out.println("Schedule for crawlLaptop at: " + Utils.getCalendar(hour, min + 5, second, millisecond).getTime());
    }
}
