package managesystem.serviceImpl;

import managesystem.model.Danger;
import managesystem.service.DangerService;
import managesystem.util.JDBCUtil;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.List;

/**
 * Created by hc on 2016/11/17.
 */
public class DangerServiceImpl implements DangerService{

    private Connection connection;

    public DangerServiceImpl(){
        connection= JDBCUtil.getConn();
    }

    @Override
    public void save(Danger danger) {
        String sql = "insert into danger (content,possiblity,effect,threshold,poster,description,rid,time,cond) values(?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt;
        Date date=new Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1,danger.getContent());
            pstmt.setString(2,danger.getPossibility());
            pstmt.setString(3,danger.getEffect());
            pstmt.setString(4,danger.getThreshold());
            pstmt.setString(5,danger.getPoster());
            pstmt.setString(6,danger.getDescription());
            pstmt.setInt(7,0);
            pstmt.setDate(8,sqlDate);
            pstmt.setString(9,danger.getCondition());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Danger> getDangerList(String tracer) {
        return null;
    }

    @Override
    public Danger get(int did) {
        return null;
    }

    @Override
    public void update(int did, String condespt, String desp) {

    }

    @Override
    public List<Danger> getPostedDangerList(String poster) {
        return null;
    }

    protected void finalize( ){
        try {
            connection.close();
        } catch (SQLException e) {

        }
    }
}
