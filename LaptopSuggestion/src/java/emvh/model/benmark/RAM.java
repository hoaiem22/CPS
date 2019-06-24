/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.model.benmark;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class RAM {

    private String name;
    private List<String> bus = new ArrayList();

    public RAM() {
    }

    public RAM(String name, List<String> bus) {
        this.name = name;
        this.bus = bus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBus() {
        if (bus == null) {
            return new ArrayList<String>();
        }
        return bus;
    }

    public void setBus(List<String> bus) {
        this.bus = bus;
    }

    @Override
    public String toString() {
        return "Ram{" + "name=" + name + ", bus=" + bus + '}';
    }

}
