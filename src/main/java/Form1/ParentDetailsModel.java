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

public class ParentDetailsModel {

    private Connection con = null;

    public ParentDetailsModel() {
        // Initialize database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/login?useSSL=false";
            String username = "root";
            String password = "1234";
            con = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ParentDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean saveParent(int id, String name, String nic, String gender, int age, String guardian,
            String address, String contact, String health, int staffID,int bedNo) {

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO parentdetails VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, nic);
            ps.setString(4, gender);
            ps.setInt(5, age);
            ps.setString(6, guardian);
            ps.setString(7, address);
            ps.setString(8, contact);
            ps.setString(9, health);
            ps.setInt(10, staffID);
            ps.setInt(11, bedNo);
            

            int status = ps.executeUpdate();
            return status == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ParentDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean updateParent(int id, String nic,int age, String guardian,
    String address, String contact, String health, int staffID, int bedNo ) {

    try {
        String query = "UPDATE parentdetails SET  NIC=?, Age=?, GName=?, Address=?, Contact=?, Health=?, StaffID=?, BedNo=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, nic);
            
            ps.setInt(2, age);
            ps.setString(3, guardian);
            ps.setString(4, address);
            ps.setString(5, contact);
            ps.setString(6, health);
            ps.setInt(7, staffID);
            
            ps.setInt(8, bedNo);
            ps.setInt(9, id);

            int status = ps.executeUpdate();
            return status == 1;
        }
    } catch (SQLException ex) {
        Logger.getLogger(ParentDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

    
    public boolean deleteParent(int id) {
    try {
        PreparedStatement ps = con.prepareStatement("DELETE FROM parentdetails WHERE id=?");
        ps.setInt(1,id);

        int status = ps.executeUpdate();
        return status == 1;
    } catch (SQLException ex) {
        Logger.getLogger(ParentDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

    public String[] viewParent(int id) {
    try {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM parentdetails WHERE id=?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String[] details = new String[15];
            
            details[1] = rs.getString("EName");
            details[2] = rs.getString("NIC");
            
            details[3] = String.valueOf(rs.getInt("Age"));
            details[4] = rs.getString("GName");
            details[5] = rs.getString("Address");
            details[6] = rs.getString("Contact");
            details[7] = rs.getString("Health");
            details[8] = String.valueOf(rs.getInt("StaffID"));
            details[9] = String.valueOf(rs.getInt("BedNo"));
            
            

            return details;
        }
    } catch (SQLException ex) {
        Logger.getLogger(ParentDetailsModel.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}


    
    
    
    

    // Add methods for other operations like update, delete, view as needed
}

