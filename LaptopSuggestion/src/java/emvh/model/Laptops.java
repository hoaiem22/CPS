/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.model;

import java.util.ArrayList;
import java.util.List;
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
@XmlType(name = "", propOrder = {"laptops"})
@XmlRootElement(name = "laptops")
public class Laptops {

    @XmlElement(name = "laptop", required = true)
    protected List<Laptop> laptops;

    public List<Laptop> getLaptops() {
        if (laptops == null) {
            laptops = new ArrayList<Laptop>();
        }
        return laptops;
    }

    public void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }

}
