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
 * @author HP
 */
public class Vehicle {

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection con = null;

    public String vid;
    public String VType;//Vehicle type
    public String VInsNo;//Vehicle Insuarance number
    public String VInsExpDate;//Insuarance Expiary Date

    public Vehicle(String id, String vt, String vn, String vd) {
        vid = id;
        VType = vt;
        VInsNo = vn;
        VInsExpDate = vd;
    }

    public Vehicle() {
    }

    public void addv() {

        //Date nd=new SimpleDateFormat("dd/MM/yyyy").parse(VInsExpDate);
        try {
            con = DBconnect.connect();
            //Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(VInsExpDate); 
            String qv = "INSERT INTO vehicle(vNo,vType,vInsuranceNo,InsExpDate) VALUES('" + vid + "','" + VType + "','" + VInsNo + "','" + VInsExpDate + "')";
            pst = con.prepareStatement(qv);
            pst.execute(qv);
            JOptionPane.showMessageDialog(null, "Sucessfully Inserted");
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,
                    "This record already exists",
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void updatev(String v1, String v2, String v3, String v4) {

        try {
            con = DBconnect.connect();
            String qvds = "SELECT * FROM vehicle WHERE vNo='" + v1 + "'";
            pst = con.prepareStatement(qvds);
            rs = pst.executeQuery();

            if (rs.next()) {
                //Date dateu=new SimpleDateFormat("yyyy-MM-dd").parse(v4);
                String qvu = "UPDATE vehicle SET vType='" + v2 + "',vInsuranceNo='" + v3 + "',InsExpDate='" + v4 + "'WHERE vNo='" + v1 + "'";
                pst = con.prepareStatement(qvu);
                pst.execute(qvu);
                JOptionPane.showMessageDialog(null, "Sucessfully updated");
            } else {
                JOptionPane.showMessageDialog(null, "No Record Found");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deletev(String vN) {
        try {
            con = DBconnect.connect();
            String qvds = "SELECT * FROM vehicle WHERE vNo='" + vN + "'";
            pst = con.prepareStatement(qvds);
            rs = pst.executeQuery();

            if (rs.next()) {
                String qvd = "DELETE FROM vehicle WHERE vNo='" + vN + "'";
                pst = con.prepareStatement(qvd);
                pst.execute(qvd);
                JOptionPane.showMessageDialog(null, "Sucessfully Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "No record found");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet searchv(String sch) {
        try {
            con = DBconnect.connect();
            String qs = "SELECT vNo,vType,vInsuranceNo,InsExpDate FROM vehicle WHERE vType='" + sch + "'";
            pst = con.prepareStatement(qs);
            rs = pst.executeQuery(qs);

        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public ResultSet searchva(String sch) {
        try {
            con = DBconnect.connect();
            String qs = "SELECT vNo,vType,vInsuranceNo,InsExpDate FROM vehicle WHERE vInsuranceNo='" + sch + "'";
            pst = con.prepareStatement(qs);
            rs = pst.executeQuery(qs);

        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;

    }

    public void validate(int vi, int vt, int vn, int vEd) {
        if (vi == 0 && vt == 0 && vn == 0 && vEd == 0) {
            JOptionPane.showMessageDialog(null, "Please Enter Data");
        } else if (vi == 0) {
            JOptionPane.showMessageDialog(null, "Enter vehicle Id");
        } else if (vt == 0) {
            JOptionPane.showMessageDialog(null, "Select a vehicle type");
        } else if (vn == 0) {
            JOptionPane.showMessageDialog(null, "Enter the insuarance number");
        } else if (vEd == 0) {
            JOptionPane.showMessageDialog(null, "Enter an Expiration date");
        }

    }

}
