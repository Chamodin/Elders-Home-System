/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Form1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffDetailsModel {
    private Connection con;

    public StaffDetailsModel() {
        
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

    public boolean saveStaff(int id, String name, String nic, String address, int contactNo, String position, int bedNo) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Staff VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, nic);
            ps.setString(4, address);
            ps.setInt(5, contactNo);
            ps.setString(6, position);
            ps.setInt(7, bedNo);
            
            int status = ps.executeUpdate();
            return status == 1;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateStaff(int id, String nic, String address, int contactNo, String position, int bedNo) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE Staff SET NIC=?, Address=?, ContactNo=?, Position=? , BedNo=? WHERE id=?");
            ps.setInt(6, id);
            ps.setString(1, nic); 
            ps.setString(2, address); 
            ps.setInt(3, contactNo);
            ps.setString(4, position);
            ps.setInt(5, bedNo);
            
            int status = ps.executeUpdate();
            return status == 1;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteStaff(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Staff WHERE id=?");
            ps.setInt(1, id);

            int status = ps.executeUpdate();
            return status == 1;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
     public String[] viewStaff(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Staff WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String[] details = new String[7];
                details[0] = String.valueOf(rs.getInt("id"));
                details[1] = rs.getString("Name");
                details[2] = rs.getString("NIC");
                details[3] = rs.getString("Address");
                details[4] = String.valueOf(rs.getInt("ContactNo"));
                details[5] = rs.getString("Position");
                details[6] = String.valueOf(rs.getInt("BedNo"));
                
                return details;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

           

}
