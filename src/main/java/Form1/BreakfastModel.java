/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Form1;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chamodi
 */
public class BreakfastModel {
    private Connection con;
    
     public BreakfastModel() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/login?useSSL=false";
            String username = "root";
            String password = "1234";
            con = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StaffDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public boolean saveBreakfast(Date date, String name, String address, int contactNo) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO breakfast VALUES (?,?,?,?)");
            ps.setDate(1, date);
            ps.setString(2, name);
            ps.setString(3,address);
            ps.setInt(4, contactNo);
            
           
            int status = ps.executeUpdate();
            return status == 1;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean updateBreakfast(Date date,String name, String address, int contactNo) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE breakfast SET name=?, address=?, contactNo=? WHERE date=? ");
           
            ps.setDate(4, date);
            ps.setString(1, name);
            ps.setString(2,address);
            ps.setInt(3, contactNo);
            
            int status = ps.executeUpdate();
            return status == 1;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
   public String[] viewBreakfast(Date date) {
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM breakfast WHERE date=?");
           
            ps.setDate(1, date);
           
           ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String[] details = new String[4];
                details[0] = String.valueOf(rs.getDate("date"));
                details[1] = rs.getString("name");
                details[2] = rs.getString("address");
                details[3] = String.valueOf(rs.getInt("contactNo"));
                
                
                return details;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
    public boolean deleteBreakfast(Date date) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM breakfast WHERE Date=?");
            ps.setDate(1, date);

            int status = ps.executeUpdate();
            return status == 1;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    }
    

