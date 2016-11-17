package managesystem.util;

import managesystem.model.RiskPlan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
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
        String sql = "insert into riskplan(name, date) values (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, riskPLan.getName());
        ps.setDate(2, new java.sql.Date(riskPLan.getDate().getTime()));
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public RiskPlan findRiskPlan(String rid) throws Exception {
        String sql = "select * from riskplan where rid = " + rid;
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        RiskPlan riskPlan = new RiskPlan(rs.getInt("rid"), rs.getString("name"), rs.getDate("date"));

        ps.close();
        conn.close();

        return riskPlan;
    }

}
