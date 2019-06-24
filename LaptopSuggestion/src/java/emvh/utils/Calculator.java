/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.utils;

import emvh.model.Laptop;
import emvh.model.benmark.CPU;
import emvh.model.benmark.GPU;
import emvh.model.benmark.RAM;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class Calculator {

    public static double calculateCpuPoint(Laptop laptop, List<CPU> cpus, double max) {
        double point = 0;
        Map<String, String> signs = new HashMap<>();
        signs.put("U540", "540UM");
        signs.put("-", " ");
        signs.put("@", "");
        String strLaptop = Utils.fixCpuSign(laptop.getProcessor().toUpperCase(), signs);
        if (strLaptop.contains("2670MQ")) {
            strLaptop = strLaptop.replaceAll("2670MQ", "2670QM");
        }
        for (CPU cpu : cpus) {
            boolean flag = true;
            String strCPU = Utils.fixCpuSign(cpu.getName().toUpperCase(), signs);
            if (strCPU.contains("@")) {
                strCPU = strCPU.replaceAll("@", "");
            }
            if (strCPU.contains("APU")) {
                strCPU = strCPU.replaceAll("APU", "");
            }
            String[] cpuArray = strCPU.split(" ");
            for (String string : cpuArray) {
                if (!strLaptop.contains(string) && !string.contains("HZ") && !string.contains("INTEL") && !string.contains("CORE") && !string.contains("PENTIUM")) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                double cpuPoint = cpu.getPoint() * 100 / max;
                point = (cpuPoint > point) ? cpuPoint : point;
            }
        }
        return point;
    }

    public static double calculateGpuPoint(Laptop laptop, List<GPU> gpus, double max) {
        if (laptop.getGraphicProcessor() == null) {
            return 0;
        }
        double point = 0;
        Map<String, String> signs = new HashMap<>();
        signs.put("™", "");
        signs.put("-", "");
        signs.put(",", "");
        signs.put("®", "");
        signs.put("(R)", "");
        signs.put("Share", "");
        signs.put("lntel", "Intel");
        signs.put("HDgraphics", "HD Graphics");
        signs.put("2GB 930MX", "Geforce 930MX");
        signs.put("WIN10 X64 : 2125MB", "");
        signs.put("WIN10 x64: 2120MB", "");
        signs.put("with up to 1792MB total graphics memory", "");
        signs.put("I ntel", "Intel");
        signs.put("6 20", "620");
        signs.put("5 20", "520");
        signs.put("Nvidia", "Nvidia GeForce");
        signs.put("GT 920", "GeForce 920M");
        signs.put("GTX1050MX", "GTX 1050");
        signs.put("GTX970M", "GTX 970M");
        signs.put("Nividia", "Nvidia GeForce");
        signs.put("Nidivia", "Nvidia GeForce");
        signs.put("HD620", "HD 620");
        signs.put("ATI FirePro M3900", "Radeon HD 6470M"); //ATI M3900 base on Redon HD
        signs.put("Intel Graphics 4000", "Intel HD 4000");
        String strLaptop = Utils.fixCpuSign(laptop.getGraphicProcessor(), signs).toUpperCase();
//            System.out.println(n++ + laptop.getGraphicProcessor());
//            String strLaptop = laptop.getGraphicProcessor().toUpperCase();
        if (strLaptop.equalsIgnoreCase("Intel HD Graphics") || strLaptop.contains("Intel Graphics HD".toUpperCase())
                || strLaptop.contains("Integrated Intel HD 620".toUpperCase()) || strLaptop.contains("INTEL HD GRAPHIC 620".toUpperCase())
                || strLaptop.equalsIgnoreCase("Intel HD Graphics 640".toUpperCase())) {
            strLaptop = "Intel HD Graphics 620".toUpperCase();
        }
        if (strLaptop.contains("Intel HD Graphics 530".toUpperCase())) {
            strLaptop = "Intel HD 530".toUpperCase();
        }
        if (strLaptop.contains("Intel Graphics 520".toUpperCase())) {
            strLaptop = "Intel HD 520".toUpperCase();
        }
        if (strLaptop.contains("Integrated Intel HD".toUpperCase())) {
            strLaptop = "Intel HD Family".toUpperCase();
        }
        if (strLaptop.contains("Intel Iris Graphics 520".toUpperCase())) {
            strLaptop = "Intel Iris 540".toUpperCase();
        }
        if (strLaptop.equalsIgnoreCase("ntel Graphics HD 5500") || strLaptop.equalsIgnoreCase("ntel HD Graphics 5500")
                || strLaptop.equalsIgnoreCase("Intel Graphics 5500")) {
            strLaptop = "Intel HD 5500".toUpperCase();
        }
        if (strLaptop.equalsIgnoreCase("UHD Graphic 620") || strLaptop.equalsIgnoreCase("Graphic 620")) {
            strLaptop = "Intel UHD Graphics 620".toUpperCase();
        }
        if (strLaptop.equalsIgnoreCase("Intel Iris Pro Graphics")) {
            strLaptop = "Intel Iris Pro Graphics 6200".toUpperCase();
        }
        if (strLaptop.equalsIgnoreCase("HD Graphics 6000")) {
            strLaptop = "Intel HD Graphics 6000".toUpperCase();
        }
        if (strLaptop.equalsIgnoreCase("Intel HD 620") || strLaptop.equalsIgnoreCase("Intel Graphics 620")
                || strLaptop.equalsIgnoreCase("Intel Graphics 720") || strLaptop.equalsIgnoreCase("Intel HD Graphics 720")
                || strLaptop.equalsIgnoreCase("Intel Graphic HD 620") || strLaptop.equalsIgnoreCase("Intel Iris Graphics 620")) {
            strLaptop = "Intel HD Graphics 620".toUpperCase();
        }
        if (strLaptop.equalsIgnoreCase("Intel Graphics 515")) {
            strLaptop = "Intel HD 515".toUpperCase();
        }
        if (strLaptop.equalsIgnoreCase("Intel graphics") || strLaptop.equalsIgnoreCase("Integrated Graphic")
                || strLaptop.equalsIgnoreCase("Intel HD") || strLaptop.equalsIgnoreCase("Intel HD Graphic")) {
            strLaptop = "Intel HD Family".toUpperCase();
        }
        if (strLaptop.equalsIgnoreCase("Intel HD Graphics 4200") || strLaptop.equalsIgnoreCase("Intel HD Graphics 400")) {
            strLaptop = "Intel HD 4000".toUpperCase();
        }
        if (strLaptop.contains("Intel HD Graphics 405".toUpperCase())) { //Intel HD Graphics 405 with shared graphics memory
            strLaptop = "Intel HD 5300".toUpperCase();
        }
        if (strLaptop.contains("Intel HD Graphics 500".toUpperCase())) {
            strLaptop = "Intel UHD 600".toUpperCase();
        }
        for (GPU gpu : gpus) {
            boolean flag = true;
//                String strCPU = Utils.fixCpuSign(gpu.getName().toUpperCase(), signs);
            String strGPU = gpu.getName().toUpperCase();
            String[] gpuArray = strGPU.split(" ");
            for (String string : gpuArray) {
                if (!strLaptop.contains(string)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                double gpuPoint = gpu.getPoint() * 100 / max;
                point = gpuPoint > point ? gpuPoint : point;
            }
        }
        return point;
    }

    public static double calculateRamPoint(Laptop laptop, List<RAM> rams) {
        if (laptop.getRamSize() == null) {
            return 0;
        }
        double point = 0;
        int max = getMaxBus(rams);
        for (RAM ram : rams) {
            if (laptop.getRamSize().contains("DDR 3")) {
                laptop.setRamSize(laptop.getRamSize().replace("DDR 3", "DDR3"));
            }
            if (laptop.getRamSize().contains("DDR 4")) {
                laptop.setRamSize(laptop.getRamSize().replace("DDR 4", "DDR4"));
            }
            if (laptop.getRamSize().contains("DDR IV")) {
                laptop.setRamSize(laptop.getRamSize().replace("DDR IV", "DDR4"));
            }
            if (laptop.getRamSize().contains("DDR III")) {
                laptop.setRamSize(laptop.getRamSize().replace("DDR III", "DDR3"));
            }
            if (laptop.getRamSize().contains("PC3L")) {
                laptop.setRamSize(laptop.getRamSize().replace("PC3L", "DDR3L"));
            }
            if (laptop.getRamSize().contains("PC4")) {
                laptop.setRamSize(laptop.getRamSize().replace("PC4", "DDR3L"));
            }
            if (XMLUtilities.containsIgnoreCase(laptop.getRamSize(), ram.getName())) { //if ram has ram type like ddr3 or ddr4,..
                for (String bus : ram.getBus()) {
                    if (XMLUtilities.containsIgnoreCase(laptop.getRamSize(), bus)) {
                        if (laptop.getPoint() < Integer.parseInt(bus)) {
                            double ramPoint = Double.parseDouble(bus) * 100 / max;
                            point = ramPoint > point ? ramPoint : point;
                        }
                    }
                }
                if (point == 0) {
                    laptop.setPoint(Double.parseDouble(ram.getBus().get(0)) * 100 / max); //set default is minumun value
                }
            } else { //ram does has ram type
                for (String bus : ram.getBus()) {
                    if (XMLUtilities.containsIgnoreCase(laptop.getRamSize(), bus)) {
                        if (laptop.getPoint() < Integer.parseInt(bus)) {
                            double ramPoint = Double.parseDouble(bus) * 100 / max;
                            point = ramPoint > point ? ramPoint : point;
                        }
                    }
                }
            }
        }
        return point;
    }

    public static double calculateDiskPoint(Laptop laptop) {
        double point = 0;
        if (laptop.getDisk() != null) {
            if (laptop.getDisk().contains("ổ cứng SSD") || XMLUtilities.containsIgnoreCase(laptop.getDisk(), "hỗ trợ")) {
                laptop.setDisk(laptop.getDisk().replace("ổ cứng SSD", ""));
            }
            if (laptop.getDisk().contains("Support SSD")) {
                laptop.setDisk(laptop.getDisk().replace("Support SSD", ""));
            }
            if (XMLUtilities.containsIgnoreCase(laptop.getDisk(), "Solid State Drive")) {
                laptop.setDisk(laptop.getDisk().replace("Solid State Drive", "SSD"));
            }
            if (XMLUtilities.containsIgnoreCase(laptop.getDisk(), "SSD")) {
                point = 100.0;
            } else {
                point = 20.0;
            }
        }
        return point;
    }

    public static int getMaxBus(List<RAM> rams) {
        int max = 0;
        for (RAM ram : rams) {
            for (String bus : ram.getBus()) {
                if (max < Integer.parseInt(bus)) {
                    max = Integer.parseInt(bus);
                }
            }
        }

        return max;
    }
}
