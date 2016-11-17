package managesystem.util;

import managesystem.model.RiskPlan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
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
        ps.setDate(2, new java.sql.Date(riskPLan.getDate().getTime()));
        ps.executeUpdate();
        ps.close();
    }

    public RiskPlan findRiskPlan(String rid) throws Exception {
        String sql = "select * from riskplan where rid = " + rid;
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        RiskPlan riskPlan = new RiskPlan(rs.getInt("rid"), rs.getString("name"), rs.getDate("time"));

        ps.close();

        return riskPlan;
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
