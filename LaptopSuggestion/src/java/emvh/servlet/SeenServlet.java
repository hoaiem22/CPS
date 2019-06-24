/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.servlet;

import emvh.dao.DetailDAO;
import emvh.dao.LaptopDAO;
import emvh.model.Laptop;
import emvh.model.LaptopDetail;
import emvh.model.LaptopDetails;
import emvh.model.Laptops;
import emvh.utils.XMLUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
@WebServlet(name = "SeenServlet", urlPatterns = {"/SeenServlet"})
public class SeenServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        try {
            XMLUtilities utils = new XMLUtilities();
            LaptopDAO laptopDAO = new LaptopDAO();
            String[] seens = request.getParameterValues("seens");
            String s = seens[0];
            if (!s.equals("")) {
                List<String> items = Arrays.asList(s.split("\\s*,\\s*"));
                List<Integer> ids = new ArrayList<>();
                for (String item : items) {
                    ids.add(Integer.parseInt(item));
                }
                Laptops laptops = new Laptops();
                List<Laptop> listLaptop = laptopDAO.findByID(ids);
                DetailDAO detailDAO = new DetailDAO();
                //Set Laptop Detail for each laptop
                for (Laptop l : listLaptop) {
                    LaptopDetails details = new LaptopDetails();
                    details.setDetails(detailDAO.findByLaptopID(l.getId()));
                    l.setDetails(details);
                }
                laptops.setLaptops(listLaptop);
//            System.out.println("LOG: " + marshallToString(laptops));
                utils.marshallerLaptopsToTransfer(laptops, response.getOutputStream());
            }
        } catch (NumberFormatException ex) {
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
