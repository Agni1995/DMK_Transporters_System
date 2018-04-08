/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmk.logistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Function {

    Statement stmt;
    ResultSet rs;

    public boolean login(Connection con, String username, String password) {
        boolean found = false;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM login");

            while (rs.next()) {
                if (rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
                    found = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Oops! Something went wrong!\n" + ex,
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return found;
    }

    public String[] getEmployee(Connection con, String empID) {
        String arr[] = new String[2];
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT name, salary FROM employee WHERE empid = '" + empID + "'");

            while (rs.next()) {
                arr[0] = rs.getString(1);
                arr[1] = rs.getString(2);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Oops! Something went wrong!\n" + ex,
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return arr;
    }

    public String[] getCustomer(Connection con) {
        try {
            int l = 0;
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(cName) FROM customer");
            while (rs.next()) {
                l = rs.getInt(1);
            }
            String arr[] = new String[l];

            try {
                int i = 0;

                rs = stmt.executeQuery("SELECT cName FROM customer");

                while (rs.next()) {
                    arr[i] = rs.getString(1);
                    i++;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,
                        "Oops! Something went wrong!\n" + ex,
                        "Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            return arr;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Oops! Something went wrong!\n" + ex,
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public String[] getRoute(Connection con, String cid) {
        try {
            int l = 0;
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(rId) FROM route WHERE cid = '" + cid + "'");
            while (rs.next()) {
                l = rs.getInt(1);
            }
            String[] arr = new String[l];

            try {
                int i = 0;
                rs = stmt.executeQuery("SELECT rId FROM route WHERE cid = '" + cid + "'");

                while (rs.next()) {
                    arr[i] = rs.getString(1);
                    i++;
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,
                        "Oops! Something went wrong!\n" + ex,
                        "Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            return arr;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Oops! Something went wrong!\n" + ex,
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public String getcid(Connection con, String cname) {
        String cid = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT customerId FROM customer WHERE cName = '" + cname + "'");
            while (rs.next()) {
                cid = rs.getString(1);
            }
            return cid;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Oops! Something went wrong!\n" + ex,
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return cid;
    }

    public int getRate(Connection con, String cid, String rid) {
        int rate = 0;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT ratePerTurn FROM route WHERE rId = '" + rid + "' AND cid = '" + cid + "'");
            while (rs.next()) {
                rate = rs.getInt(1);
            }
            return rate;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Oops! Something went wrong!\n" + ex,
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return rate;
    }

    public boolean updateSalary(Connection con, String empId, String month, double salary) {
        int found = 0;
        boolean success = false;
        try {
            stmt = con.createStatement(); //duplicate3
            rs = stmt.executeQuery("SELECT COUNT(empid) FROM salary WHERE empid = '" + empId + "' AND month = '" + month + "'");
            while (rs.next()) {
                found = rs.getInt(1);
            }
            if (found == 0) {
                stmt.execute("INSERT INTO salary (empid, month, salary) VALUES ('" + empId + "', '" + month + "', " + salary + ");");
                success = true;
            } else {
                stmt.execute("UPDATE salary SET salary = " + salary + "WHERE empid = '" + empId + "' AND month = '" + month + "'");
                success = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Oops! Something went wrong!\n" + ex,
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }

    public static int telephonecheck(String num) {
        int len = num.length();
        if (len == 10) {
            return 0;
        } else {
            return 1;
        }
    }
}
