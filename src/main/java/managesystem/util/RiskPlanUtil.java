package managesystem.util;

import managesystem.model.Danger;
import managesystem.model.RiskPlan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Created by XXH on 2016/11/17.
 */
public class RiskPlanUtil {

    Connection conn;

    public RiskPlanUtil(){
        conn = JDBCUtil.getConn();
    }

    public void addRisPlan(RiskPlan riskPLan) throws Exception{
        String sql = "insert into riskplan(name, time) values (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, riskPLan.getName());
        ps.setDate(2, new java.sql.Date(riskPLan.getTime().getTime()));
        ps.executeUpdate();
        ps.close();
    }

    public List<Danger> findDangerListByRid(String id) throws Exception {
        List<Danger> list = new LinkedList<>();
        String sql = "select * from danger where rid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int did = rs.getInt("did");
            String content = rs.getString("content");
            String possiblity = rs.getString("possiblity");
            String effect = rs.getString("effect");
            String threshold = rs.getString("threshold");
            String poster = rs.getString("poster");
            String condition = rs.getString("cond");
            String description = rs.getString("description");
            String time = rs.getString("time");
            int rid = rs.getInt("rid");
            list.add(new Danger(did,content,possiblity, effect,threshold,poster,condition,description, time,rid));
        }


        ps.close();

        return list;
    }

    public List<RiskPlan> riskPlanAll() throws Exception {
        List<RiskPlan> list = new LinkedList<>();
        String sql = "select * from riskplan";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new RiskPlan(rs.getInt("rid"), rs.getString("name"), rs.getDate("time")));
        }
        rs.close();
        return list;
    }
}
