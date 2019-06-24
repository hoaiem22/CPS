/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detail", propOrder = {"id", "laptopID", "source", "price", "brand", "img", "href",
    "color", "model", "dvd", "cache", "screenSize", "touchScreen", "audio", "lan", "wl",
    "bluetooth", "webcam", "fingerPrint", "port", "keyboard", "pin", "weight", "size",
    "accessories", "other", "os", "guarantee", "ship", "origin", "gift", "status"})
public class LaptopDetail implements Serializable {

    @XmlElement(required = true)
    private int id;
    @XmlElement(required = true)
    private int laptopID;
    @XmlElement(required = true)
    private String source;
    @XmlElement(required = true)
    private String price;
    @XmlElement(required = true)
    private String brand;
    @XmlElement(required = true)
    private String img;
    @XmlElement(required = true)
    private String href;
    @XmlElement(required = true)
    private String color;
    @XmlElement(required = true)
    private String model;
    @XmlElement(required = true)
    private String dvd;
    @XmlElement(required = true)
    private String cache;
    @XmlElement(required = true)
    private String screenSize;
    @XmlElement(required = true)
    private String touchScreen;
    @XmlElement(required = true)
    private String audio;
    @XmlElement(required = true)
    private String lan;
    @XmlElement(required = true)
    private String wl;
    @XmlElement(required = true)
    private String bluetooth;
    @XmlElement(required = true)
    private String webcam;
    @XmlElement(required = true)
    private String fingerPrint;
    @XmlElement(required = true)
    private String port;
    @XmlElement(required = true)
    private String keyboard;
    @XmlElement(required = true)
    private String pin;
    @XmlElement(required = true)
    private String weight;
    @XmlElement(required = true)
    private String size;
    @XmlElement(required = true)
    private String accessories;
    @XmlElement(required = true)
    private String other;
    @XmlElement(required = true)
    private String os;
    @XmlElement(required = true)
    private String guarantee;
    @XmlElement(required = true)
    private String ship;
    @XmlElement(required = true)
    private String origin;
    @XmlElement(required = true)
    private String gift;
    @XmlElement(required = true)
    private String status;

    public LaptopDetail() {
    }

    public LaptopDetail(LaptopMapping lt) {
        this.source = lt.getSource();
        this.price = lt.getPrice();
        this.brand = lt.getBrand();
        this.img = lt.getImg();
        this.href = lt.getHref();
        this.color = lt.getColor();
        this.model = lt.getModel();
        this.dvd = lt.getDvd();
        this.cache = lt.getCache();
        this.screenSize = lt.getScreenSize();
        this.touchScreen = lt.getTouchScreen();
        this.audio = lt.getAudio();
        this.lan = lt.getLan();
        this.wl = lt.getWl();
        this.bluetooth = lt.getBluetooth();
        this.webcam = lt.getWebcam();
        this.fingerPrint = lt.getFingerPrint();
        this.port = lt.getPort();
        this.keyboard = lt.getKeyboard();
        this.pin = lt.getPin();
        this.weight = lt.getWeight();
        this.size = lt.getSize();
        this.accessories = lt.getAccessories();
        this.other = lt.getOther();
        this.os = lt.getOs();
        this.guarantee = lt.getGuarantee();
        this.ship = lt.getShip();
        this.origin = lt.getOrigin();
        this.gift = lt.getGift();
        this.status = lt.getStatus();
    }

    public LaptopDetail(int id, int laptopID, String source, String price, String brand, String img, String href, String color, String model, String dvd, String cache, String screenSize, String touchScreen, String audio, String lan, String wl, String bluetooth, String webcam, String fingerPrint, String port, String keyboard, String pin, String weight, String size, String accessories, String other, String os, String guarantee, String ship, String origin, String gift, String status) {
        this.id = id;
        this.laptopID = laptopID;
        this.source = source;
        this.price = price;
        this.brand = brand;
        this.img = img;
        this.href = href;
        this.color = color;
        this.model = model;
        this.dvd = dvd;
        this.cache = cache;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLaptopID() {
        return laptopID;
    }

    public void setLaptopID(int laptopID) {
        this.laptopID = laptopID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
        return "LaptopDetail{" + "id=" + id + ", laptopID=" + laptopID + ", source=" + source + ", price=" + price + ", brand=" + brand + ", img=" + img + ", href=" + href + ", color=" + color + ", model=" + model + ", dvd=" + dvd + ", cache=" + cache + ", screenSize=" + screenSize + ", touchScreen=" + touchScreen + ", audio=" + audio + ", lan=" + lan + ", wl=" + wl + ", bluetooth=" + bluetooth + ", webcam=" + webcam + ", fingerPrint=" + fingerPrint + ", port=" + port + ", keyboard=" + keyboard + ", pin=" + pin + ", weight=" + weight + ", size=" + size + ", accessories=" + accessories + ", other=" + other + ", os=" + os + ", guarantee=" + guarantee + ", ship=" + ship + ", origin=" + origin + ", gift=" + gift + ", status=" + status + '}';
    }

}
