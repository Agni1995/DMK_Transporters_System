/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmk.logistics;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class DBconnect {

    public static Connection conn;

    public static Connection connect() {

        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dmk", "root", "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Connection to the database failed. \nPlease check your connection. Press OK after Fixing.\n",
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
            connect();
        }

        return conn;

    }

}
