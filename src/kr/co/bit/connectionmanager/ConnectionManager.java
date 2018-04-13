package kr.co.bit.connectionmanager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ConnectionManager {
    public Connection getConnection() {
        Connection connection = null;
        try {
            InitialContext initialContext = new InitialContext();
            Context context = (Context) initialContext.lookup("java:comp/env/"); //네이밍 서버를 찾아감
            DataSource dataSource = (DataSource) context.lookup("jdbc/oracle"); //
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    public Connection getConnection() {
//        Connection con = null;
//        String url = "jdbc:oracle:thin:@huxxinstance.c2corwmutw7a.ap-northeast-2.rds.amazonaws.com:1521:ORCL";
//        String driver = "oracle.jdbc.OracleDriver";
//
//        try {
//            Class.forName(driver);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            con = DriverManager.getConnection(url, "huxx", "12345678");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return con;
//
//    }

    public void connectClose(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
