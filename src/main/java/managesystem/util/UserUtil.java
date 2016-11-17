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
        String sql = "select * from user where name = " + email;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        if (rs.getFetchSize() == 0) {
            pstmt.close();
            conn.close();
            return null;
        }
        else {
            pstmt.close();
            conn.close();
            return new User(rs.getInt("uid"), rs.getString("name"), rs.getString("identity"), rs.getString("pswd"));
        }
    }

    public void addUser(User user) throws Exception{
        String sql = "insert into user(uid, name, identity, pswd) values(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user.getUid());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getIdentity());
        pstmt.setString(2, user.getPswd());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
}
