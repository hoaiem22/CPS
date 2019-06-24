/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.model.compare;

import emvh.model.Laptop;
import java.io.Serializable;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class LaptopModel implements Serializable {

    private double dp;
    private Laptop laptop;

    public LaptopModel(double dp, Laptop laptop) {
        this.dp = dp;
        this.laptop = laptop;
    }

    public LaptopModel() {
    }

    public double getDp() {
        return dp;
    }

    public void setDp(double dp) {
        this.dp = dp;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

}
