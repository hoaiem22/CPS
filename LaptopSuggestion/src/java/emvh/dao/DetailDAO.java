/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.dao;

import emvh.connection.MyConnection;
import emvh.model.LaptopDetail;
import emvh.model.LaptopDetails;
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
public class DetailDAO implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(DetailDAO.class.getName());

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
     * Check if detail is exist. Return false if isn't exist, else return true
     *
     * @param detail : Laptop Detail.
     * @return true if Exist, else return false.
     */
    public boolean isDetailExist(LaptopDetail detail) {
        try {
            String sql = "select id from Detail where source = ? and price = ? "
                    + "and href = ? and [status] = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, detail.getSource());
            preStm.setString(2, detail.getPrice());
            preStm.setString(3, detail.getHref());
            preStm.setNString(4, detail.getStatus());
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
     * Insert Laptop Detail into database.
     *
     * @param details : List Laptop Detail
     */
    public void insertDetails(LaptopDetails details) {
        try {
            String sql = "Insert Into Detail values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (LaptopDetail detail : details.getDetails()) {
                preStm.setInt(1, detail.getLaptopID());
                preStm.setNString(2, detail.getSource());
                preStm.setNString(3, detail.getPrice());
                preStm.setNString(4, detail.getBrand());
                preStm.setNString(5, detail.getImg());
                preStm.setNString(6, detail.getHref());
                preStm.setNString(7, detail.getColor());
                preStm.setNString(8, detail.getModel());
                preStm.setNString(9, detail.getDvd());
                preStm.setNString(10, detail.getCache());
                preStm.setNString(11, detail.getScreenSize());
                preStm.setNString(12, detail.getTouchScreen());
                preStm.setNString(13, detail.getAudio());
                preStm.setNString(14, detail.getLan());
                preStm.setNString(15, detail.getWl());
                preStm.setNString(16, detail.getBluetooth());
                preStm.setNString(17, detail.getWebcam());
                preStm.setNString(18, detail.getFingerPrint());
                preStm.setNString(19, detail.getPort());
                preStm.setNString(20, detail.getKeyboard());
                preStm.setNString(21, detail.getPin());
                preStm.setNString(22, detail.getWeight());
                preStm.setNString(23, detail.getSize());
                preStm.setNString(24, detail.getAccessories());
                preStm.setNString(25, detail.getOther());
                preStm.setNString(26, detail.getOs());
                preStm.setNString(27, detail.getGuarantee());
                preStm.setNString(28, detail.getShip());
                preStm.setNString(29, detail.getOrigin());
                preStm.setNString(30, detail.getGift());
                preStm.setNString(31, detail.getStatus());
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
     * Insert Laptop Detail into database.
     *
     * @param detail : Laptop Detail.
     * @return true if insert success else return false.
     */
    public boolean insertDetail(LaptopDetail detail) {
        boolean check = false;
        try {
            String sql = "Insert Into Detail values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, detail.getLaptopID());
            preStm.setNString(2, detail.getSource());
            preStm.setNString(3, detail.getPrice());
            preStm.setNString(4, detail.getBrand());
            preStm.setNString(5, detail.getImg());
            preStm.setNString(6, detail.getHref());
            preStm.setNString(7, detail.getColor());
            preStm.setNString(8, detail.getModel());
            preStm.setNString(9, detail.getDvd());
            preStm.setNString(10, detail.getCache());
            preStm.setNString(11, detail.getScreenSize());
            preStm.setNString(12, detail.getTouchScreen());
            preStm.setNString(13, detail.getAudio());
            preStm.setNString(14, detail.getLan());
            preStm.setNString(15, detail.getWl());
            preStm.setNString(16, detail.getBluetooth());
            preStm.setNString(17, detail.getWebcam());
            preStm.setNString(18, detail.getFingerPrint());
            preStm.setNString(19, detail.getPort());
            preStm.setNString(20, detail.getKeyboard());
            preStm.setNString(21, detail.getPin());
            preStm.setNString(22, detail.getWeight());
            preStm.setNString(23, detail.getSize());
            preStm.setNString(24, detail.getAccessories());
            preStm.setNString(25, detail.getOther());
            preStm.setNString(26, detail.getOs());
            preStm.setNString(27, detail.getGuarantee());
            preStm.setNString(28, detail.getShip());
            preStm.setNString(29, detail.getOrigin());
            preStm.setNString(30, detail.getGift());
            preStm.setNString(31, detail.getStatus());
            check = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Insert fail: {0}", e.getMessage());
        } finally {
            closeConnection();
        }
        return check;
    }

    /**
     * Find laptop detail by laptop ID in price (in range).
     *
     * @param findID: laptop id.
     * @param rage: price.
     * @return List Laptop Detail.
     */
    public List<LaptopDetail> findByLaptopIDAndPrice(int findID, int rage) {
        List<LaptopDetail> result = null;
        try {
            String sql = "select id, laptopID, source, price, brand, img, href, color, model, dvd, "
                    + "cache, screenSize, touchScreen, audio, lan, wl, bluetooth, webcam, fingerPrint, "
                    + "[port], keyboard, pin, [weight], size, accessories, other, os, guarantee, "
                    + "ship, origin, gift, [status] "
                    + "from Detail "
                    + "where laptopID = ? and (cast (price as int)) > ?  and (cast (price as int)) < ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, findID);
            preStm.setDouble(2, rage - rage * 0.1); //error 10%
            preStm.setDouble(3, rage + rage * 0.1);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int laptopID = rs.getInt("laptopID");
                String source = rs.getString("source");
                String price = rs.getString("price");
                String brand = rs.getString("brand");
                String img = rs.getString("img");
                String href = rs.getString("href");
                String color = rs.getString("color");
                String model = rs.getString("model");
                String dvd = rs.getString("dvd");
                String cache = rs.getString("cache");
                String screenSize = rs.getString("screenSize");
                String touchScreen = rs.getString("touchScreen");
                String audio = rs.getString("audio");
                String lan = rs.getString("lan");
                String wl = rs.getString("wl");
                String bluetooth = rs.getString("bluetooth");
                String webcam = rs.getString("webcam");
                String fingerPrint = rs.getString("fingerPrint");
                String port = rs.getString("port");
                String keyboard = rs.getString("keyboard");
                String pin = rs.getString("pin");
                String weight = rs.getString("weight");
                String size = rs.getString("size");
                String accessories = rs.getString("accessories");
                String other = rs.getString("other");
                String os = rs.getString("os");
                String guarantee = rs.getString("guarantee");
                String ship = rs.getString("ship");
                String origin = rs.getString("origin");
                String gift = rs.getString("gift");
                String status = rs.getString("status");
                result.add(new LaptopDetail(id, laptopID, source, price, brand, img, href, color, model, dvd, cache, screenSize, touchScreen, audio, lan, wl, bluetooth, webcam, fingerPrint, port, keyboard, pin, weight, size, accessories, other, os, guarantee, ship, origin, gift, status));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "ERROR at findByLaptopIDAndPrice: {0}", ex.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    /**
     * Find laptop detail by laptop ID in price (in range).
     *
     * @param findID: laptop id.
     * @param rage: price.
     * @return List Laptop Detail.
     */
    public List<LaptopDetail> findByLaptopID(int searchID) {
        List<LaptopDetail> result = null;
        try {
            String sql = "select id, laptopID, source, price, brand, img, href, color, model, dvd, "
                    + "cache, screenSize, touchScreen, audio, lan, wl, bluetooth, webcam, fingerPrint, "
                    + "[port], keyboard, pin, [weight], size, accessories, other, os, guarantee, "
                    + "ship, origin, gift, [status] "
                    + "from Detail "
                    + "where laptopID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, searchID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int laptopID = rs.getInt("laptopID");
                String source = rs.getString("source");
                String price = rs.getString("price");
                String brand = rs.getString("brand");
                String img = rs.getString("img");
                String href = rs.getString("href");
                String color = rs.getString("color");
                String model = rs.getString("model");
                String dvd = rs.getString("dvd");
                String cache = rs.getString("cache");
                String screenSize = rs.getString("screenSize");
                String touchScreen = rs.getString("touchScreen");
                String audio = rs.getString("audio");
                String lan = rs.getString("lan");
                String wl = rs.getString("wl");
                String bluetooth = rs.getString("bluetooth");
                String webcam = rs.getString("webcam");
                String fingerPrint = rs.getString("fingerPrint");
                String port = rs.getString("port");
                String keyboard = rs.getString("keyboard");
                String pin = rs.getString("pin");
                String weight = rs.getString("weight");
                String size = rs.getString("size");
                String accessories = rs.getString("accessories");
                String other = rs.getString("other");
                String os = rs.getString("os");
                String guarantee = rs.getString("guarantee");
                String ship = rs.getString("ship");
                String origin = rs.getString("origin");
                String gift = rs.getString("gift");
                String status = rs.getString("status");
                result.add(new LaptopDetail(id, laptopID, source, price, brand, img, href, color, model, dvd, cache, screenSize, touchScreen, audio, lan, wl, bluetooth, webcam, fingerPrint, port, keyboard, pin, weight, size, accessories, other, os, guarantee, ship, origin, gift, status));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "ERROR at findByLaptopIDAndPrice: {0}", ex.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }
}
