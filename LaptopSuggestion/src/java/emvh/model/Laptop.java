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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "laptop", propOrder = {"id", "name", "processor", "ramSize",
    "disk", "graphicProcessor", "point", "details"})
@XmlRootElement(name = "laptop")
public class Laptop implements Serializable {

    @XmlElement(required = true)
    private int id;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String processor;
    @XmlElement(required = true)
    private String ramSize;
    @XmlElement(required = true)
    private String disk;
    @XmlElement(required = true)
    private String graphicProcessor;
    @XmlElement(required = true)
    private Double point;
    @XmlElement(required = true)
    private LaptopDetails details;

    public Laptop() {
    }

    public Laptop(int id, String name, String processor, String ramSize, String disk, String graphicProcessor, Double point, LaptopDetails details) {
        this.id = id;
        this.name = name;
        this.processor = processor;
        this.ramSize = ramSize;
        this.disk = disk;
        this.graphicProcessor = graphicProcessor;
        this.point = point;
        this.details = details;
    }

    public Laptop(int id, String name, String processor, String ramSize, String disk, String graphicProcessor, Double point) {
        this.id = id;
        this.name = name;
        this.processor = processor;
        this.ramSize = ramSize;
        this.disk = disk;
        this.graphicProcessor = graphicProcessor;
        this.point = point;
    }

    public Laptop(LaptopMapping laptopMapping) {
        this.name = laptopMapping.getName();
        this.processor = laptopMapping.getProcessor();
        this.ramSize = laptopMapping.getRamSize();
        this.disk = laptopMapping.getDisk();
        this.graphicProcessor = laptopMapping.getGraphicProcessor();
        if (this.details == null) {
            this.details = new LaptopDetails();
        }
        this.details.getDetails().add(new LaptopDetail(laptopMapping));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        if (name == null) {
            return "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcessor() {
        if (processor == null) {
            return "";
        }
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRamSize() {
        if(ramSize == null){
            return "";
        }
        return ramSize;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    public String getDisk() {
        if(disk == null){
            return "";
        }
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getGraphicProcessor() {
        if (graphicProcessor == null) {
            return "";
        }
        return graphicProcessor;
    }

    public void setGraphicProcessor(String graphicProcessor) {
        this.graphicProcessor = graphicProcessor;
    }

    public Double getPoint() {
        if (point == null) {
            return 0.0;
        }

        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public LaptopDetails getDetails() {
        if (this.details == null) {
            this.details = new LaptopDetails();
        }
        return details;
    }

    public void setDetails(LaptopDetails details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Laptop{" + "id=" + id + ", name=" + name + ", processor=" + processor + ", ramSize=" + ramSize + ", disk=" + disk + ", graphicProcessor=" + graphicProcessor + ", point=" + point + ", details=" + details + '}';
    }

}
