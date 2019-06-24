/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author EmVH <hoaiem.heli22@gmail.com>
 */
public class MyConnection implements Serializable {

    public static Connection getMyConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Laptop;username=sa;password=123");
    }
}
