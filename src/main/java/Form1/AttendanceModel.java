package Form1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttendanceModel {
    private Connection con;

    public AttendanceModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/Login?useSSL=false";
            String username = "root";
            String password = "1234";
            con = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AttendanceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public boolean submitAttendance(int id, Date date, String inTime, String outTime) {
    try {
        String sql = "INSERT INTO Attendance (Id, Date, InTime, OutTime) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setDate(2, date);
        ps.setString(3, inTime);
        ps.setString(4, outTime);

        int status = ps.executeUpdate();
        return status == 1;
    } catch (SQLException ex) {
        Logger.getLogger(AttendanceModel.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

    public String[] viewAttendance(int id, Date date) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Attendance WHERE Id=? AND Date=?");
            ps.setInt(1, id);
            ps.setDate(2, date);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String[] details = new String[3];
                details[0] = rs.getString("InTime");
                details[1] = rs.getString("OutTime");
                return details;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
