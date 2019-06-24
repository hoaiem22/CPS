/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.dao;

import emvh.connection.MyConnection;
import emvh.model.benmark.GPU;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class GpuDAO implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(GpuDAO.class.getName());

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
            LOGGER.info("ERROR at closeConnection: " + e.getMessage());
        }
    }

    /**
     * Check if GPU is exist. Return false if isn't exist, else return true
     *
     * @param cpu : GPU cpu.
     * @return true if Exist, else return false.
     */
    public boolean isGpuExist(GPU cpu) {
        try {
            String sql = "select id from gpu  where [name] = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, cpu.getName());
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

    /**
     * Insert GPU into database.
     *
     * @param cpus List GPU
     */
    public void insertGPUs(List<GPU> cpus) {
        try {
            String sql = "insert into gpu values(?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (GPU cpu : cpus) {
                preStm.setNString(1, cpu.getName());
                preStm.setInt(2, cpu.getPoint());
                preStm.setInt(3, cpu.getRank());
                preStm.addBatch();
            }
            preStm.executeBatch();
            conn.commit();
        } catch (Exception e) {
            LOGGER.info("Insert fail: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    /**
     * Insert GPU into database.
     *
     * @param cpu : GPU.
     * @return true if insert success else return false.
     */
    public boolean insertGPU(GPU cpu) {
        boolean check = false;
        try {
            String sql = "insert into gpu values(?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, cpu.getName());
            preStm.setInt(2, cpu.getPoint());
            preStm.setInt(3, cpu.getRank());
            check = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Insert fail: {0}", e.getMessage());
        } finally {
            closeConnection();
        }
        return check;
    }

    /**
     * Update cpu's information.
     *
     * @param cpu : GPU.
     * @return true if update success else return false.
     */
    public boolean updateGPU(GPU cpu) {
        boolean check = false;
        try {
            String sql = "UPDATE gpu SET point = ?, [rank] = ? WHERE name = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, cpu.getPoint());
            preStm.setInt(2, cpu.getRank());
            preStm.setNString(3, cpu.getName());
            check = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "ERROR at Update GPU: {0}", e.getMessage());
        } finally {
            closeConnection();
        }
        return check;
    }

    /**
     * get all GPU from Database.
     *
     * @return List GPU.
     */
    public List<GPU> getGPUs() {
        List<GPU> result = null;
        try {
            String sql = "select id, [name], point, [rank] from gpu";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int point = rs.getInt("point");
                int rank = rs.getInt("rank");
                result.add(new GPU(id, name, point, rank));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "ERROR at getGPUs: {0}", ex.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }
    
    /**
     * get max point of CPU.
     *
     * @return double max point.
     */
    public double getMaxPoint() {
        double point = 0;
        try {
            String sql = "select MAX(point) as max from gpu";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                point = rs.getDouble("max");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "ERROR at get max point: {0}", ex.getMessage());
        } finally {
            closeConnection();
        }
        return point;
    }
}
