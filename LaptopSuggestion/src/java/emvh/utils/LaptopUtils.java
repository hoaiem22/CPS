/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.utils;

import emvh.model.Laptop;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class LaptopUtils {

    public static boolean isLaptopNull(Laptop laptop) {
        if (laptop.getProcessor().equals("") && laptop.getDisk().equals("")
                && laptop.getGraphicProcessor().equals("") && laptop.getRamSize().equals("")) {
            return false;
        }
        return false;
    }
}
