/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.dao;

import emvh.connection.MyConnection;
import emvh.model.Laptop;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class PopularDAO implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PopularDAO.class.getName());

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "ERROR at closeConnection: {0}", e.getMessage());
        }
    }

    /**
     * Insert View into database.
     *
     * @param id: laptop id.
     * @return LaptopID (int)
     */
    public boolean insertView(int id) {
        boolean check = false;
        try {
            String sql = "insert into Popular values(?,1)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            check = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Insert fail: {0}", e.getMessage());
        } finally {
            closeConnection();
        }
        return check;
    }

    /**
     * Update View.
     *
     * @param id: laptop id.
     * @return LaptopID (int)
     */
    public void updateView(int id) {
        try {
            String sql = "update Popular set [view] = [view] + 1 where laptopid = ?";
            System.out.println("Update");
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            preStm.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Insert fail: {0}", e.getMessage());
        } finally {
            closeConnection();
        }
    }

    /**
     * Check if laptop is exist. Return false if isn't exist, else return true.
     *
     * @param id : laptop id.
     * @return -1 if doesnt eixst, else return Laptop ID.
     */
    public boolean isExist(int id) {
        try {
            String sql = "select id from Popular where laptopid = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return false;
    }
}
