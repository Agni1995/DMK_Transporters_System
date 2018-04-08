/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmk.logistics;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author DIM
 */
public class Branch{
    Statement pst=null;
    ResultSet rs=null;
    Connection con=null;
    
    private String bid;
    private String bname;
    private String baddress;
    private String bcontact;
    
    public Branch(String i,String n,String a,String c){
        bid = i;
        bname=n;
        baddress=a;
        bcontact=c;
    }
    public Branch(){}
    public void addb(){
        try {
            con= DBconnect.connect();
            if(Function.telephonecheck(bcontact)==0){
                String qa="INSERT INTO branch VALUES('"+ bid + "','"+bname+"','"+baddress+"','"+bcontact+"')";
                pst=con.prepareStatement(qa);
                pst.execute(qa);
                JOptionPane.showMessageDialog(null,"Successfully Inserted");
            }
            else{
                JOptionPane.showMessageDialog(null, "Enter a 10 digit contact number");
            }
            
            
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,
                    "This record already exists",
                    "Message",
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void updateb(String n,String a,String c,String i){
        try {
             con= DBconnect.connect();
             String qds="SELECT * FROM branch WHERE branchId='"+i+"'";
          pst=con.prepareStatement(qds);  
          rs = pst.executeQuery(qds);

          if(rs.next()){
             String qu="UPDATE branch SET branchName='"+n+"',address='"+a+"',contact='"+c+"' WHERE branchId='"+i+"' ";
             pst=con.prepareStatement(qu);
             pst.execute(qu);
            JOptionPane.showMessageDialog(null,"Successfully Updated");
          }else{
              JOptionPane.showMessageDialog(null,"No Record Found");
          }
             
        } catch (Exception e) {
            System.out.println(e);
        }
    
    }
    public void deleteb(String id){
        try {
          con=DBconnect.connect();
          String qds="SELECT * FROM branch WHERE branchId='"+id+"'";
          pst=con.prepareStatement(qds);  
          rs = pst.executeQuery(qds);

          if(rs.next()){
          String qd="DELETE FROM branch WHERE branchId='"+id+"'";
          pst=con.prepareStatement(qd);  
          pst.execute(qd);
          JOptionPane.showMessageDialog(null,"Sucessfully Deleted");
          }else{
              JOptionPane.showMessageDialog(null,"No record Found");
          }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public ResultSet searchb(String sch){
        try {
            con=DBconnect.connect();
            String qs="SELECT branchId,branchName,address,contact FROM branch WHERE branchName='"+sch+"'";
            pst=con.prepareStatement(qs);
            rs= pst.executeQuery(qs);
            
           
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
    public void Validate(int id, int name,int addr,int cnt){
        if(id == 0 && name == 0 && addr == 0 && cnt == 0)
            JOptionPane.showMessageDialog(null, "Please Enter Data");
        else if(id ==0)
                JOptionPane.showMessageDialog(null, "Enter an Id to branch");
        else if(name==0)
                JOptionPane.showMessageDialog(null, "Enter a name to branch");
        else if(addr==0)
                JOptionPane.showMessageDialog(null, "Enter an address to branch");
        else if(cnt==0)
                JOptionPane.showMessageDialog(null, "Enter an contact to branch");
        
    }
    
    
}
