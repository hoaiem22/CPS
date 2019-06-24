/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.utils;

import emvh.constant.Constants;
import emvh.crawler.BenmarkCrawler;
import emvh.crawler.GiaHuyCrawler;
import emvh.crawler.LeSonCrawler;
import emvh.dao.CpuDAO;
import emvh.dao.DetailDAO;
import emvh.dao.GpuDAO;
import emvh.dao.LaptopDAO;
import emvh.model.Laptop;
import emvh.model.LaptopDetail;
import emvh.model.LaptopMapping;
import emvh.model.benmark.CPU;
import emvh.model.benmark.GPU;
import emvh.model.benmark.RAM;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
public class Stage {
    
    private static final Logger LOGGER = Logger.getLogger(Stage.class.getName());
    
    public static void crawlLaptopGiaHuy() throws UnsupportedEncodingException, XMLStreamException, IOException {
        GiaHuyCrawler crawler = new GiaHuyCrawler();
        LaptopDAO laptopDAO = new LaptopDAO();
        DetailDAO detailDAO = new DetailDAO();
        CpuDAO cpuDAO = new CpuDAO();
        GpuDAO gpuDAO = new GpuDAO();
        List<CPU> cpus = cpuDAO.getCPUs();
        List<RAM> rams = XMLUtilities.getRams();
        List<GPU> gpus = gpuDAO.getGPUs();
        //--DEFINE TAG--
        String homeURL = Constants.LAPTOP_GIAHUY.getHomeURL();
        String BEGIN_TAG_CATEGORY = Constants.LAPTOP_GIAHUY.getBeginCategory();//--CATEGORY
        String END_TAG_CATEGORY = Constants.LAPTOP_GIAHUY.getEndCategory();
        String BEGIN_TAG_PAGE = Constants.LAPTOP_GIAHUY.getBeginPage();//--PAGE
        String END_TAG_PAGE = Constants.LAPTOP_GIAHUY.getEndPage();
        String BEGIN_TAG_PRODUCT = Constants.LAPTOP_GIAHUY.getBeginProduct();//--PRODUCT
        String END_TAG_PRODUCT = Constants.LAPTOP_GIAHUY.getEndProduct(); //get body and cut a part which we need
        String BEGIN_TAG_DETAIL = Constants.LAPTOP_GIAHUY.getBeginDetail();//--DETAIL
        String END_TAG_DETAIL = Constants.LAPTOP_GIAHUY.getEndDetail();
        //--END DEFINE TAG--

        //---------1. CRAWL LAPTOP CATEGORY--------
        Map<String, String> categories = crawler.crawlCategories(homeURL, BEGIN_TAG_CATEGORY, END_TAG_CATEGORY);
        //Remove Laptop in list (Laptop is total of laptop brand)
        categories.remove("Laptop");
        //--------1. END CRAWL LAPTOP CATEGORY-------

        //-----2. CRAWL LAPTOP PAGE OF WITH EVERY CATEGORY-------
        Map<String, Integer> pages = new HashMap<>();
        for (Map.Entry<String, String> entry : categories.entrySet()) {
            pages.put(entry.getValue(), crawler.crawlPage(entry.getValue(), BEGIN_TAG_PAGE, END_TAG_PAGE));
        }
        //----------2. ENDCRAWL LAPTOP PAGE OF WITH EVERY CATEGORY----------

        //----------3. START CRAWL PRODUCT (LAPTOP) AND DETAIL----------
        String page = "-pi=";
        //START CRAWL DETAIL AND STORE TO DB
        int num = 0;
        for (Map.Entry<String, Integer> entry : pages.entrySet()) {
            String URL_LAPTOP = entry.getKey();
            int n = entry.getValue();
            for (int i = 0; i < n; i++) {
                //Custom url
                String urlLaptop = URL_LAPTOP + page + (i + 1);
                List<LaptopMapping> laptopList = crawler.crawlProduct(urlLaptop, BEGIN_TAG_PRODUCT, END_TAG_PRODUCT);
                for (LaptopMapping laptopMapping : laptopList) {
                    LaptopMapping laptopDetails = crawler.crawlDetail(laptopMapping, BEGIN_TAG_DETAIL, END_TAG_DETAIL);
                    laptopDetails.setSource(homeURL);
                    String imageUrl = laptopDetails.getImg();
                    String imagePath = "image/GH-Laptop" + num++ + ".jpg";
                    String destinationFile = Constants.realPath + imagePath;
                    try {
                        XMLUtilities.saveImage(imageUrl, destinationFile);
                    } catch (IOException ex) {
                        LOGGER.log(Level.INFO, "ERROR at Save Image: {0}", ex.getMessage());
                    }
                    laptopDetails.setImg(imagePath);
//                    System.out.println(num + ": " + laptopDetails.toString());
                    Laptop laptop = new Laptop(laptopMapping);
                    //Return if laptop has one of four core field is null
                    if (LaptopUtils.isLaptopNull(laptop)) {
                        LOGGER.log(Level.INFO, "Some latop''s field is null at: {0}", laptop.getName());
                        break;
                    }

                    //START CALCULATE POINT
                    double cpuMax = cpuDAO.getMaxPoint();
                    double gpuMax = gpuDAO.getMaxPoint();
                    double disk = Calculator.calculateDiskPoint(laptop);
                    double cpu = Calculator.calculateCpuPoint(laptop, cpus, cpuMax);
                    double ram = Calculator.calculateRamPoint(laptop, rams);
                    double gpu = Calculator.calculateGpuPoint(laptop, gpus, gpuMax);
                    laptop.setPoint((disk + cpu + ram + gpu) * 100 / 400);
                    //END CALCULATE POINT

                    //Check laptop  is exist in Laptop List
                    //if exist -> update detail, else insert to DB
                    int id = laptopDAO.isLaptopExist(laptop);
                    if (id > 0) { //exist
//                        LOGGER.log(Level.INFO, "{0} is Exist !", laptop.getName());
                        for (LaptopDetail detail : laptop.getDetails().getDetails()) {
                            detail.setLaptopID(id); //set ID for laptop Detail
                            if (!detailDAO.isDetailExist(detail)) { //detail doesnt exist
                                detailDAO.insertDetail(detail);
//                                System.out.println("Insert detail for: " + laptop.getName());
                            }
                        }
                    } else { //doesnt exist
                        id = laptopDAO.insertLaptop(laptop);
//                        System.out.println("Insert laptop: " + laptop.getName());
                        for (LaptopDetail detail : laptop.getDetails().getDetails()) {
                            detail.setLaptopID(id);
                            detailDAO.insertDetail(detail);
//                            System.out.println("Insert detail for: " + laptop.getName());
                        }
                    }
                }
                
            }
        }
        //----------3. END CRAWL PRODUCT (LAPTOP) AND DETAIL----------
    }
    
    public static void crawlLaptopLeSon() throws UnsupportedEncodingException, XMLStreamException, IOException, SAXException, ParserConfigurationException, Exception {
        LeSonCrawler crawler = new LeSonCrawler();
        DetailDAO detailDAO = new DetailDAO();
        LaptopDAO laptopDAO = new LaptopDAO();
        CpuDAO cpuDAO = new CpuDAO();
        GpuDAO gpuDAO = new GpuDAO();
        List<CPU> cpus = cpuDAO.getCPUs();
        List<RAM> rams = XMLUtilities.getRams();
        List<GPU> gpus = gpuDAO.getGPUs();
        //--DEFINE TAG--
        String homeURL = Constants.LAPTOP_LESON.getHomeURL();
        String BEGIN_TAG_CATEGORY = Constants.LAPTOP_LESON.getBeginCategory();//--CATEGORY
        String END_TAG_CATEGORY = Constants.LAPTOP_LESON.getEndCategory();
        String BEGIN_TAG_PAGE = Constants.LAPTOP_LESON.getBeginPage();//--PAGE
        String END_TAG_PAGE = Constants.LAPTOP_LESON.getEndPage();
        String BEGIN_TAG_PRODUCT = Constants.LAPTOP_LESON.getBeginProduct();//--PRODUCT
        String END_TAG_PRODUCT = Constants.LAPTOP_LESON.getEndProduct(); //get body and cut a part which we need
        String BEGIN_TAG_DETAIL = Constants.LAPTOP_LESON.getBeginDetail();//--DETAIL
        String END_TAG_DETAIL = Constants.LAPTOP_LESON.getEndDetail();
        //--END DEFINE TAG--

        //---------1. CRAWL LAPTOP CATEGORY--------
        Map<String, String> map = crawler.crawlCategories(homeURL, BEGIN_TAG_CATEGORY, END_TAG_CATEGORY);
        //--------1. END CRAWL LAPTOP CATEGORY-------

        //-----2. CRAWL LAPTOP PAGE OF WITH EVERY CATEGORY-------
        int num = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String brand = entry.getKey();
            String url = entry.getValue();
            //Get number of page
            int page = crawler.crawlPage(url, BEGIN_TAG_PAGE, END_TAG_PAGE);
            String pageURL = entry.getValue() + "page/";
            //----------3. START CRAWL PRODUCT (LAPTOP) AND DETAIL----------
            for (int i = 0; i < page; i++) {
                String laptopURL = pageURL + (i + 1);
                List<LaptopMapping> laptopMappings = crawler.crawlProduct(laptopURL, BEGIN_TAG_PRODUCT, END_TAG_PRODUCT);
                for (LaptopMapping laptopMapping : laptopMappings) {
                    laptopMapping.setSource(homeURL);
                    laptopMapping.setBrand(brand);
                    String imageUrl = laptopMapping.getImg();
                    String imagePath = "image/LS-Laptop" + num++ + ".jpg";
                    String destinationFile = Constants.realPath + imagePath;
//                    System.out.println(destinationFile);
                    try {
                        XMLUtilities.saveImage(imageUrl, destinationFile);
                    } catch (IOException ex) {
                        LOGGER.log(Level.INFO, "ERROR at Save Image: {0}", ex.getMessage());
                    }
                    laptopMapping.setImg(imagePath);
                    //-----4.START CRAWL DETAIL--------
                    laptopMapping = crawler.crawlDetail(laptopMapping, BEGIN_TAG_DETAIL, END_TAG_DETAIL);
                    Laptop laptop = new Laptop(laptopMapping);
                    //Return if laptop has one of four core field is null
                    if (LaptopUtils.isLaptopNull(laptop)) {
                        LOGGER.log(Level.INFO, "Some latop''s field is null at: {0}", laptop.getName());
                        break;
                    }
                    //START CALCULATE POINT
                    double cpuMax = cpuDAO.getMaxPoint();
                    double gpuMax = gpuDAO.getMaxPoint();
                    double disk = Calculator.calculateDiskPoint(laptop);
                    double cpu = Calculator.calculateCpuPoint(laptop, cpus, cpuMax);
                    double ram = Calculator.calculateRamPoint(laptop, rams);
                    double gpu = Calculator.calculateGpuPoint(laptop, gpus, gpuMax);
                    laptop.setPoint((disk + cpu + ram + gpu) * 100 / 400);
                    //END CALCULATE POINT
                    //Check laptop  is exist in Laptop List
                    //if exist -> update detail, else insert to DB
                    int id = laptopDAO.isLaptopExist(laptop);
                    if (id > 0) { //exist
//                        LOGGER.log(Level.INFO, "{0} is Exist !", laptop.getName());
                        for (LaptopDetail detail : laptop.getDetails().getDetails()) {
                            detail.setLaptopID(id); //set ID for laptop Detail
                            if (!detailDAO.isDetailExist(detail)) { //detail doesnt exist
                                detailDAO.insertDetail(detail);
//                                System.out.println("Insert detail for: " + laptop.getName());
                            }
                        }
                    } else { //doesnt exist
                        id = laptopDAO.insertLaptop(laptop);
//                        System.out.println("Insert laptop: " + laptop.getName());
                        for (LaptopDetail detail : laptop.getDetails().getDetails()) {
                            detail.setLaptopID(id);
                            detailDAO.insertDetail(detail);
//                            System.out.println("Insert detail for: " + laptop.getName());
                        }
                    }
                    //-----4.END CRAWL DETAIL--------
                }
            }
            //----------3. END CRAWL PRODUCT (LAPTOP) AND DETAIL----------
        }
    }
    
    public static void crawlCPUBenmark() {
        try {
            BenmarkCrawler crawler = new BenmarkCrawler();
            CpuDAO cpuDAO = new CpuDAO();
            String homeURL = Constants.BENCHMARK_CPU.getUrl();
            String beginTag = Constants.BENCHMARK_CPU.getBegin();
            String endTag = Constants.BENCHMARK_CPU.getEnd();
            try {
                List<CPU> cpus = crawler.crawlCPU(homeURL, beginTag, endTag);
                //START INSERT INTO DATABASE
                //update if it's exist else insert into db
                int n = 0;
                for (CPU cpu : cpus) {
                    if (!cpuDAO.isCpuExist(cpu)) { //exist -> insert
                        cpuDAO.insertCPU(cpu);
                    } else { //doesnt exist -> update
                        cpuDAO.updateCPU(cpu);
                    }
                }
                //END INSERT INTO DATABASE
            } catch (UnsupportedEncodingException | XMLStreamException ex) {
                Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            LOGGER.log(Level.INFO, "ERROR at crawlCPUBenchmark: {0}", e.getMessage());
        }
    }
    
    public static void crawlGPUBenmark() {
        BenmarkCrawler crawler = new BenmarkCrawler();
        GpuDAO gpuDAO = new GpuDAO();
        String homeURL = Constants.BENCHMARK_GPU.getUrl();
        String beginTag = Constants.BENCHMARK_GPU.getBegin();
        String endTag = Constants.BENCHMARK_GPU.getEnd();
        try {
            List<GPU> gpus = crawler.crawlGPU(homeURL, beginTag, endTag);
            //START INSERT INTO DATABASE
            //update if it's exist else insert into db
            int n = 0;
            for (GPU gpu : gpus) {
                if (!gpuDAO.isGpuExist(gpu)) { //exist -> insert
                    gpuDAO.insertGPU(gpu);
                } else { //doesnt exist -> update
                    gpuDAO.updateGPU(gpu);
                }
            }
            //END INSERT INTO DATABASE
        } catch (UnsupportedEncodingException | XMLStreamException ex) {
            Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
