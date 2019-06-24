/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.model;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class LaptopMapping {

    private String source;
    private String brand;
    private String img;
    private String href;
    private String name;
    private String price;
    private String color;
    private String model;
    //Chip
    private String processor; //Intel Core i3-5005U (2.0GHz / 3Mb Cache)
    //RAM
    private String ramSize;
    private String memoryTechnology;

    private String disk;
    private String dvd;

    private String cache;
    private String graphicProcessor;
    private String screenSize;
    private String touchScreen;
    private String audio;
    private String lan;
    private String wl;
    private String bluetooth;
    private String webcam;
    private String fingerPrint;
    private String port;
    private String keyboard;
    private String pin;
    private String weight;
    private String size;
    private String accessories;
    private String other;
    private String os;
    private String guarantee;
    private String ship;
    private String origin;
    private String gift;
    private String status;

    public LaptopMapping() {
    }

    public LaptopMapping(String status, String img, String href, String name, String price) {
        this.status = status;
        this.img = img;
        this.href = href;
        this.name = name;
        this.price = price;
    }

    //For get Cheap Laptop
    public LaptopMapping(String img, String name, String processor, String ramSize, String disk, String graphicProcessor, String status, String price) {
        this.img = img;
        this.name = name;
        this.processor = processor;
        this.ramSize = ramSize;
        this.disk = disk;
        this.graphicProcessor = graphicProcessor;
        this.status = status;
        this.price = price;
    }

    public LaptopMapping(String source, String brand, String img, String href, String name, String price, String color, String model, String processor, String ramSize, String memoryTechnology, String disk, String dvd, String cache, String graphicProcessor, String screenSize, String touchScreen, String audio, String lan, String wl, String bluetooth, String webcam, String fingerPrint, String port, String keyboard, String pin, String weight, String size, String accessories, String other, String os, String guarantee, String ship, String origin, String gift, String status) {
        this.source = source;
        this.brand = brand;
        this.img = img;
        this.href = href;
        this.name = name;
        this.price = price;
        this.color = color;
        this.model = model;
        this.processor = processor;
        this.ramSize = ramSize;
        this.memoryTechnology = memoryTechnology;
        this.disk = disk;
        this.dvd = dvd;
        this.cache = cache;
        this.graphicProcessor = graphicProcessor;
        this.screenSize = screenSize;
        this.touchScreen = touchScreen;
        this.audio = audio;
        this.lan = lan;
        this.wl = wl;
        this.bluetooth = bluetooth;
        this.webcam = webcam;
        this.fingerPrint = fingerPrint;
        this.port = port;
        this.keyboard = keyboard;
        this.pin = pin;
        this.weight = weight;
        this.size = size;
        this.accessories = accessories;
        this.other = other;
        this.os = os;
        this.guarantee = guarantee;
        this.ship = ship;
        this.origin = origin;
        this.gift = gift;
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRamSize() {
        return ramSize;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    public String getMemoryTechnology() {
        return memoryTechnology;
    }

    public void setMemoryTechnology(String memoryTechnology) {
        this.memoryTechnology = memoryTechnology;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getDvd() {
        return dvd;
    }

    public void setDvd(String dvd) {
        this.dvd = dvd;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getGraphicProcessor() {
        return graphicProcessor;
    }

    public void setGraphicProcessor(String graphicProcessor) {
        this.graphicProcessor = graphicProcessor;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getTouchScreen() {
        return touchScreen;
    }

    public void setTouchScreen(String touchScreen) {
        this.touchScreen = touchScreen;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getWl() {
        return wl;
    }

    public void setWl(String wl) {
        this.wl = wl;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getWebcam() {
        return webcam;
    }

    public void setWebcam(String webcam) {
        this.webcam = webcam;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Laptop{" + "source=" + source + ", brand=" + brand + ", img=" + img + ", href=" + href + ", name=" + name + ", price=" + price + ", color=" + color + ", model=" + model + ", processor=" + processor + ", ramSize=" + ramSize + ", memoryTechnology=" + memoryTechnology + ", disk=" + disk + ", dvd=" + dvd + ", cache=" + cache + ", graphicProcessor=" + graphicProcessor + ", screenSize=" + screenSize + ", touchScreen=" + touchScreen + ", audio=" + audio + ", lan=" + lan + ", wl=" + wl + ", bluetooth=" + bluetooth + ", webcam=" + webcam + ", fingerPrint=" + fingerPrint + ", port=" + port + ", keyboard=" + keyboard + ", pin=" + pin + ", weight=" + weight + ", size=" + size + ", accessories=" + accessories + ", other=" + other + ", os=" + os + ", guarantee=" + guarantee + ", ship=" + ship + ", origin=" + origin + ", gift=" + gift + ", status=" + status + '}';
    }
}
