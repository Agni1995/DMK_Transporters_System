/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmk.logistics;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Customer {

    private String custId;
    private String custName;
    private String custAddress;
    private String custItem;
    private String sbranch;

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection con = null;

    public Customer(String ci, String n, String a, String i, String cb) {
        custId = ci;
        custName = n;
        custAddress = a;
        custItem = i;
        sbranch = cb;
    }

    public Customer() {
    }

    public void addc() { //duplicate Entry

        try {
            con = DBconnect.connect();
            //int sb=Integer.parseInt(sbranch);
            String qa = "INSERT INTO customer VALUES('" + custId + "','" + custName + "','" + custAddress + "','" + custItem + "','" + sbranch + "')";
            pst = con.prepareStatement(qa);
            pst.execute(qa);
            JOptionPane.showMessageDialog(null, "Successfully Inserted");

        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null,
                    "This record already exists",
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updatec(String idu, String nu, String au, String iu, String cb) { //instead of add
        try {
            con = DBconnect.connect();
            String qds = "SELECT * FROM customer WHERE customerId='" + idu + "' ";
            pst = con.prepareStatement(qds);
            rs = pst.executeQuery(qds);
            if (rs.next()) {
                String qu = "UPDATE customer SET cName='" + nu + "',cAddress='" + au + "',cItem='" + iu + "',bid='" + cb + "'WHERE customerId='" + idu + "'";
                pst = con.prepareStatement(qu);
                pst.execute(qu);
                JOptionPane.showMessageDialog(null, "Sucessfully Updated");
            } else {
                JOptionPane.showMessageDialog(null, "No Record Found");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void deletec(String cid) { //instead of add
        try {
            con = DBconnect.connect();
            String qds = "SELECT * FROM customer WHERE customerId='" + cid + "' ";
            pst = con.prepareStatement(qds);
            rs = pst.executeQuery(qds);
            if (rs.next()) {
                String qd = "DELETE FROM customer WHERE customerId='" + cid + "' ";
                pst = con.prepareStatement(qd);
                pst.execute(qd);
                JOptionPane.showMessageDialog(null, "Sucessfully Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "No Record Found");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public ResultSet searchc(String sch) {
        try {
            con = DBconnect.connect();
            String qs = "SELECT customerId,cName,cAddress,cItem,bid FROM customer WHERE cName='" + sch + "'";
            pst = con.prepareStatement(qs);
            rs = pst.executeQuery(qs);

        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void Validate(int ci, int n, int a, int i, int cb) {
        if (ci == 0 && n == 0 && a == 0 && i == 0 && cb == 0) {
            JOptionPane.showMessageDialog(null, "Please Enter Data");
        } else if (ci == 0) {
            JOptionPane.showMessageDialog(null, "Enter customer ID");
        } else if (n == 0) {
            JOptionPane.showMessageDialog(null, "Enter customer name");
        } else if (a == 0) {
            JOptionPane.showMessageDialog(null, "Enter an address");
        } else if (i == 0) {
            JOptionPane.showMessageDialog(null, "Enter an item");
        } else if (cb == 0) {
            JOptionPane.showMessageDialog(null, "Enter the branch involved");
        }
    }
}
