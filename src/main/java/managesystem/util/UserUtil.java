package managesystem.util;

import managesystem.model.User;

import java.sql.*;

/**
 * Created by XXH on 2016/11/17.
 */
public class UserUtil {

    private Connection conn;

    public UserUtil() {
        conn = JDBCUtil.getConn();
    }


    public User getUserByEmail(String email) throws Exception{
        String sql = "select COUNT(*) from user where name = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        if (rs.getInt(1) == 0) {
            pstmt.close();
            return null;
        }
        else {
            pstmt = conn.prepareStatement("select * from user where name = " + email);
            rs = pstmt.executeQuery();
            rs.next();
            User user = new User(rs.getInt("uid"), rs.getString("name"), rs.getString("identity"), rs.getString("pswd"));
            pstmt.close();
            return user;
        }
    }

    public void addUser(User user) throws Exception{
        String sql = "insert into user(name, identity, pswd) values(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getIdentity());
        pstmt.setString(3, user.getPswd());
        pstmt.executeUpdate();
        pstmt.close();
    }
}
