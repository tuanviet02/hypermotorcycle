/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DBConnection {

 // Kết nối vào SQLServer.
 // (Sử dụng thư viện điều khiển SQLJDBC)
 public static Connection getSQLServerConnection()
         throws SQLException, ClassNotFoundException {
     String hostName = "localhost";
     String sqlInstanceName = "ADMIN";
     String database = "motobike";
     String userName = "sa";
     String password = "sa";

     return getSQLServerConnection(hostName, sqlInstanceName,
             database, userName, password);
 }

 // Trường hợp sử dụng SQLServer.
 // Và thư viện SQLJDBC.
 public static Connection getSQLServerConnection(String hostName,
    String sqlInstanceName, String database, String userName,
    String password) throws ClassNotFoundException, SQLException {
     // Khai báo class Driver cho DB SQLServer
     // Việc này cần thiết với Java 5
     // Java6 tự động tìm kiếm Driver thích hợp.
     // Nếu bạn dùng Java6, thì ko cần dòng này cũng được.
     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

     // Cấu trúc URL Connection dành cho SQLServer
     // Ví dụ:
     // jdbc:sqlserver://ServerIp:1433/SQLEXPRESS;databaseName=simplehr
     String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"
             + ";instance=" + sqlInstanceName + ";databaseName=" + database+";encrypt=true;trustServerCertificate=true;";

     Connection conn = DriverManager.getConnection(connectionURL, userName,
             password);
     return conn;
 }
 
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       DBConnection.getSQLServerConnection();
    }
}
