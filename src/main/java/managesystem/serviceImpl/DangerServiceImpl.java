package managesystem.serviceImpl;

import managesystem.model.Danger;
import managesystem.service.DangerService;
import managesystem.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by hc on 2016/11/17.
 */
public class DangerServiceImpl implements DangerService{

    private Connection connection;

    public DangerServiceImpl(){
        connection= JDBCUtil.getConn();
    }

    @Override
    public String save(Danger danger) {
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
            return "success";
        } catch (SQLException e) {
            return "fail";
        }
    }

    public String deleteDanger(int did){
        String sql = "delete from danger where did="+did;
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
            return "success";
        } catch (SQLException e) {
            return "fail";
        }
    }
    @Override
    public String update(Danger danger) {
        /*String sql = "update danger set content=?,possiblity=?,effect=?,threshold=?,poster=?,description=?,rid=?,time=?,cond=?" +
                " where did=?";*/

        String sql = "update danger set description=?,rid=?,time=?,cond=?" +
                " where did=?";
        PreparedStatement pstmt;
        Date date=new Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1,danger.getDescription());
            pstmt.setInt(2,danger.getRid());
            pstmt.setDate(3,sqlDate);
            pstmt.setString(4,danger.getCondition());
            pstmt.setInt(5,danger.getDid());
            pstmt.executeUpdate();
            return "success";
        } catch (SQLException e) {
            return "fail";
        }
    }

    @Override
    public List<Danger> getDangersByTime(Date d1, Date d2) {
        java.sql.Date sd1=new java.sql.Date(d1.getTime());
        java.sql.Date sd2=new java.sql.Date(d2.getTime());
        String sql = "select * from danger where time>=? and time<=?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setDate(1,sd1);
            pstmt.setDate(2,sd2);
            ResultSet rs=pstmt.executeQuery();
            List<Danger> list=new ArrayList<Danger>();
            while(rs.next()){
                Danger danger=new Danger();
                danger.setDid(rs.getInt("did"));
                danger.setRid(rs.getInt("rid"));
                danger.setThreshold(rs.getString("threshold"));
                danger.setPoster(rs.getString("poster"));
                danger.setPossibility(rs.getString("possibility"));
                danger.setCondition(rs.getString("cond"));
                danger.setDescription(rs.getString("description"));
                danger.setEffect(rs.getString("effect"));
                danger.setTime(String.valueOf(rs.getDate("time")));
                danger.setContent(rs.getString("content"));
                list.add(danger);
            }
            return list;
        }catch (Exception e){

        }

        return new ArrayList<Danger>();
    }

    @Override
    public List<Danger> getDangersByRid(int rid) {
        String sql = "select * from danger where rid="+rid;
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(sql);

            List<Danger> list=new ArrayList<Danger>();
            while(rs.next()){
                Danger danger=new Danger();
                danger.setDid(rs.getInt("did"));
                danger.setRid(rs.getInt("rid"));
                danger.setThreshold(rs.getString("threshold"));
                danger.setPoster(rs.getString("poster"));
                danger.setPossibility(rs.getString("possiblity"));
                danger.setCondition(rs.getString("cond"));
                danger.setDescription(rs.getString("description"));
                danger.setEffect(rs.getString("effect"));
                danger.setTime(String.valueOf(rs.getDate("time")));
                danger.setContent(rs.getString("content"));
                list.add(danger);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Danger>();
    }

    @Override
    public Danger getDangerByDid(int did) {
        String sql = "select * from danger where did="+did;
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(sql);
            Danger danger=new Danger();

            while(rs.next()) {
                danger.setDid(rs.getInt("did"));
                danger.setRid(rs.getInt("rid"));
                danger.setThreshold(rs.getString("threshold"));
                danger.setPoster(rs.getString("poster"));
                danger.setPossibility(rs.getString("possiblity"));
                danger.setCondition(rs.getString("cond"));
                danger.setDescription(rs.getString("description"));
                danger.setEffect(rs.getString("effect"));
                danger.setTime(String.valueOf(rs.getDate("time")));
                danger.setContent(rs.getString("content"));
            }
            return danger;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
            return new Danger();
    }


    protected void finalize( ){
        try {
            connection.close();
        } catch (SQLException e) {

        }
    }
}
