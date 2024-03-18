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


public class Login2Model {
    private Connection con;
    
    public Login2Model() {
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/Login?useSSL=false";
            String username = "root";
            String password = "1234";
            con = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        } 
 }           
       
    
    

    boolean validate(String username, String password) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM login2 WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();  // Returns true if there is a matching user in the database
        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}   

