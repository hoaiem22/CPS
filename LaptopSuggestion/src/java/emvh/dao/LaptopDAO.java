/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.dao;

import emvh.connection.MyConnection;
import emvh.model.Laptop;
import emvh.model.LaptopMapping;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class LaptopDAO implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LaptopDAO.class.getName());

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
     * Check if laptop is exist. Return false if isn't exist, else return true.
     *
     * @param laptop : laptop which you wanna check.
     * @return -1 if doesnt eixst, else return Laptop ID.
     */
    public Integer isLaptopExist(Laptop laptop) {
        Integer id = -1;
        try {
            String sql = "select id from laptop "
                    + "where name = ? and processor = ? and ramSize = ? and [disk] = ? and graphicProcessor = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setNString(1, laptop.getName());
            preStm.setNString(2, laptop.getProcessor());
            preStm.setNString(3, laptop.getRamSize());
            preStm.setNString(4, laptop.getDisk());
            preStm.setNString(5, laptop.getGraphicProcessor());
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return id;
    }

    /**
     * Insert Laptop into database.
     *
     * @param laptop : laptop has been inserted into DB.
     * @return LaptopID (int)
     */
    public int insertLaptop(Laptop laptop) {
        int id = -1;
        try {
            //N'Dell N5558',N'Intel i7-5500U',N'8GB DDR3',N'HDD 1TB', N'NVDIA 940GB','96.6'
            String sql = "Insert Into Laptop values(?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStm.setNString(1, laptop.getName());
            preStm.setNString(2, laptop.getProcessor());
            preStm.setNString(3, laptop.getRamSize());
            preStm.setNString(4, laptop.getDisk());
            preStm.setNString(5, laptop.getGraphicProcessor());
            preStm.setDouble(6, laptop.getPoint());
            int affectedRows = preStm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preStm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = (int) generatedKeys.getLong(1);
                } else {
                    LOGGER.info("Creating Laptop failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Insert fail: {0}", e.getMessage());
        } finally {
            closeConnection();
        }
        return id;
    }

    /**
     * get all Laptop from Database.
     *
     * @return List Laptop.
     */
    public List<Laptop> getLaptops() {
        List<Laptop> result = null;
        try {
            String sql = "select id, name, processor, ramSize, [disk], graphicProcessor, point from laptop";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String processor = rs.getString("processor");
                String ramSize = rs.getString("ramSize");
                String disk = rs.getString("disk");
                String graphicProcessor = rs.getString("graphicProcessor");
                Double point = rs.getDouble("point");
                result.add(new Laptop(id, name, processor, ramSize, disk, graphicProcessor, point));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "ERROR at getLaptops: {0}", ex.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    /**
     * Find Laptop by price (in range).
     *
     * @param searchPrice: price.
     * @return List Laptop.
     */
    public List<Laptop> findByPrice(int searchPrice) {
        List<Laptop> result = null;
        try {
            String sql = "select top 12 id, name, processor, ramSize, [disk], graphicProcessor, point from laptop "
                    + "where id in (select laptopID from Detail "
                    + "where (cast (price as int)) > ? and (cast (price as int)) < ? "
                    + "group by laptopID) "
                    + "order by point desc;";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setDouble(1, searchPrice - searchPrice * 0.1); //error 10%
            preStm.setDouble(2, searchPrice + searchPrice * 0.1);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
//                String id, name, processor, ramSize, [disk], graphicProcessor, point
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String processor = rs.getString("processor");
                String ramSize = rs.getString("ramSize");
                String disk = rs.getString("disk");
                String graphicProcessor = rs.getString("graphicProcessor");
                Double point = rs.getDouble("point");
                result.add(new Laptop(id, name, processor, ramSize, disk, graphicProcessor, point));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "ERROR at findByPrice: {0}", ex.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    /**
     * Find Laptop is cheap.
     *
     * @return List Laptop.
     */
    public List<Laptop> getCheaps() {
        List<Laptop> result = null;
        try {
            String sql = "select top 12 l.id, l.name, l.processor, l.ramSize, l.disk, l.graphicProcessor, l.point, d.price, d.[status], d.img, (100000000/(CAST(d.price AS float)) + l.point) /2 as Chip "
                    + "from Laptop as l "
                    + "inner join Detail as d on l.id = d.laptopID "
                    + "where d.price > 0 "
                    + "order by chip desc";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
//                String id, name, processor, ramSize, [disk], graphicProcessor, point
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String processor = rs.getString("processor");
                String ramSize = rs.getString("ramSize");
                String disk = rs.getString("disk");
                String graphicProcessor = rs.getString("graphicProcessor");
                Double point = rs.getDouble("point");
                String status = rs.getString("status");
                String img = rs.getString("img");
                String price = String.valueOf(rs.getInt("price"));
                LaptopMapping mapping = new LaptopMapping(img, name, processor, ramSize, disk, graphicProcessor, status, price);
                Laptop laptop = new Laptop(mapping);
                laptop.setId(id);
                laptop.setPoint(point);
                result.add(laptop);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "ERROR at findByPrice: {0}", ex.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    /**
     * Find Laptop by price (in range).
     *
     * @param searchID: LaptopID.
     * @return Laptop.
     */
    public Laptop findByID(int searchID) {
        Laptop result = null;
        try {
            String sql = "select id, name, processor, ramSize, [disk], graphicProcessor, point from laptop where id = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, searchID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String processor = rs.getString("processor");
                String ramSize = rs.getString("ramSize");
                String disk = rs.getString("disk");
                String graphicProcessor = rs.getString("graphicProcessor");
                Double point = rs.getDouble("point");
                result = new Laptop(id, name, processor, ramSize, disk, graphicProcessor, point);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "ERROR at findByPrice: {0}", ex.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    /**
     * Find Laptop by price (in range).
     *
     * @param ids list laptopid.
     * @return Laptop.
     */
    public List<Laptop> findByID(List<Integer> ids) {
        List<Laptop> result = null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            builder.append("?,");
        }
        try {
            String sql = "select id, name, processor, ramSize, [disk], graphicProcessor, point from laptop where id in ("
                    + builder.deleteCharAt(builder.length() - 1).toString() + ")";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            int index = 1;
            for (Integer id : ids) {
                preStm.setInt(index++, id); // or whatever it applies 
            }
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String processor = rs.getString("processor");
                String ramSize = rs.getString("ramSize");
                String disk = rs.getString("disk");
                String graphicProcessor = rs.getString("graphicProcessor");
                Double point = rs.getDouble("point");
                result.add(new Laptop(id, name, processor, ramSize, disk, graphicProcessor, point));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "ERROR at findByID: {0}", ex.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }
    
    /**
     * Find Laptop is cheap.
     *
     * @return List Laptop.
     */
    public List<Laptop> getMostView() {
        List<Laptop> result = null;
        try {
            String sql = "select l.id, l.name, l.processor, l.ramSize, l.disk, l.graphicProcessor, l.point, d.price, d.[status], d.img "
                    + "from Laptop as l "
                    + "inner join Detail as d on l.id = d.laptopID "
                    + "where l.id in (select top 12 laptopid from Popular order by [view] desc)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
//                String id, name, processor, ramSize, [disk], graphicProcessor, point
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String processor = rs.getString("processor");
                String ramSize = rs.getString("ramSize");
                String disk = rs.getString("disk");
                String graphicProcessor = rs.getString("graphicProcessor");
                Double point = rs.getDouble("point");
                String status = rs.getString("status");
                String img = rs.getString("img");
                String price = String.valueOf(rs.getInt("price"));
                LaptopMapping mapping = new LaptopMapping(img, name, processor, ramSize, disk, graphicProcessor, status, price);
                Laptop laptop = new Laptop(mapping);
                laptop.setId(id);
                laptop.setPoint(point);
                result.add(laptop);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "ERROR at findByPrice: {0}", ex.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }
}
