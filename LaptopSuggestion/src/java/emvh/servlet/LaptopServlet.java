/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.servlet;

import emvh.dao.DetailDAO;
import emvh.dao.LaptopDAO;
import emvh.model.Laptop;
import emvh.model.LaptopDetails;
import emvh.model.Laptops;
import emvh.model.compare.LaptopModel;
import emvh.utils.Utils;
import emvh.utils.XMLUtilities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
@WebServlet(name = "LaptopServlet", urlPatterns = {"/LaptopServlet"})
public class LaptopServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        try {
            XMLUtilities utils = new XMLUtilities();
            LaptopDAO laptopDAO = new LaptopDAO();
            List<Laptop> listLaptop = new ArrayList<>();
            Laptops laptops = new Laptops();
            DetailDAO detailDAO = new DetailDAO();
            try {
                int price = Integer.parseInt(request.getParameter("txtPrice"));
                System.out.println(price);
                //Get List laptop in rage price
                listLaptop = laptopDAO.findByPrice(price);
                //Set Laptop Detail for each laptop
                for (Laptop l : listLaptop) {
                    LaptopDetails details = new LaptopDetails();
                    details.setDetails(detailDAO.findByLaptopIDAndPrice(l.getId(), price));
                    l.setDetails(details);
                }
            } catch (NumberFormatException e) {
                List<Laptop> tmpLaptops = laptopDAO.getLaptops();
                String search = request.getParameter("txtPrice");
                System.out.println(search);
                List<LaptopModel> dps = new ArrayList<>();
                for (Laptop tmp : tmpLaptops) {
                    int dp = Utils.computeMatchingDensity(search.toUpperCase(), tmp.getName().toUpperCase());
                    int min = Math.min(tmp.getName().length(), search.length());
                    double sameString = dp / min * 100;
                    if (sameString > 50) {
                        dps.add(new LaptopModel(sameString, tmp));
                    }
                }
                Collections.sort(dps, new Comparator<LaptopModel>() {
                    @Override
                    public int compare(LaptopModel o1, LaptopModel o2) {
                        return Double.compare(o2.getDp(), o1.getDp());
                    }

                });
                int length = (dps.size() > 12) ? 12 : dps.size();
                for (int i = 0; i < length; i++) {
                    System.out.println(dps.get(i).getDp());
                    listLaptop.add(dps.get(i).getLaptop());
                }
                for (Laptop l : listLaptop) {
                    LaptopDetails details = new LaptopDetails();
                    details.setDetails(detailDAO.findByLaptopID(l.getId()));
                    l.setDetails(details);
                }
            }

            laptops.setLaptops(listLaptop);
//            System.out.println("LOG: " + marshallToString(laptops));
            utils.marshallerLaptopsToTransfer(laptops, response.getOutputStream());
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(LaptopServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
