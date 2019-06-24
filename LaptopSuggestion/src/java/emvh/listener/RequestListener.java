/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Web application lifecycle listener.
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request Destroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Request Initialized");
    }
}
