/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "details", propOrder = {"details"})
public class LaptopDetails implements Serializable {

   @XmlElement(name = "detail", required = true)
    protected List<LaptopDetail> details;

    public List<LaptopDetail> getDetails() {
        if (details == null) {
            details = new ArrayList<>();
        }
        return details;
    }

    public void setDetails(List<LaptopDetail> detail) {
        this.details = detail;
    }

}
