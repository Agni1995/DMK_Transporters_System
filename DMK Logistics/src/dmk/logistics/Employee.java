/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmk.logistics;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Employee {

    private String empId;
    private String empName;
    private String nic;
    private String contact;
    private double salary;

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection con = null;

    public Employee(String i, String n, String nc, String c, Double d) {
        empId = i;
        empName = n;
        nic = nc;
        contact = c;
        salary = d;
    }

    public Employee() {
    }

    public void addE() { //duplicate Entry

        try {
            con = DBconnect.connect();
            if (Function.telephonecheck(contact) == 0) {
                String qa = "INSERT INTO employee VALUES('" + empId + "','" + empName + "','" + nic + "','" + contact + "','" + salary + "')";
                pst = con.prepareStatement(qa);
                pst.execute(qa);
                JOptionPane.showMessageDialog(null, "Successfully Inserted");
            } else {
                JOptionPane.showMessageDialog(null, "Enter a 10 digit contact number");
            }

        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null,
                    "This record already exists",
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateE(String idu, String nu, String nicu, String contactu, double s) { //instead of add
        try {
            con = DBconnect.connect();
            String qds = "SELECT * FROM employee WHERE empid='" + idu + "' ";
            pst = con.prepareStatement(qds);
            rs = pst.executeQuery(qds);
            if (rs.next()) {
                String qu = "UPDATE employee SET name='" + nu + "',nic='" + nicu + "',contact='" + contactu + "',salary=" + s + "WHERE empid='" + idu + "'";
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

    public void deleteE(String eid) { //instead of add
        try {
            con = DBconnect.connect();
            String qds = "SELECT * FROM employee WHERE empid='" + eid + "' ";
            pst = con.prepareStatement(qds);
            rs = pst.executeQuery(qds);
            if (rs.next()) {
                String qd = "DELETE FROM employee WHERE empid='" + eid + "' ";
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

    public ResultSet searchE(String sch) {
        try {
            con = DBconnect.connect();
            String qs = "SELECT * FROM employee WHERE name='" + sch + "'";
            pst = con.prepareStatement(qs);
            rs = pst.executeQuery(qs);

        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void Validate(int ei, int n, int ic, int c, int s) {
        if (ei == 0 && n == 0 && ic == 0 && c == 0 && s == 0) {
            JOptionPane.showMessageDialog(null, "Please Enter Data");
        } else if (ei == 0) {
            JOptionPane.showMessageDialog(null, "Enter Employee ID");
        } else if (n == 0) {
            JOptionPane.showMessageDialog(null, "Enter Employee name");
        } else if (ic == 0) {
            JOptionPane.showMessageDialog(null, "Enter NIC");
        } else if (c == 0) {
            JOptionPane.showMessageDialog(null, "Enter contact number");
        } else if (s == 0) {
            JOptionPane.showMessageDialog(null, "Enter the salary");
        }
    }
}
