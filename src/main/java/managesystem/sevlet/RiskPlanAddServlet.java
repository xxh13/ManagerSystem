package managesystem.sevlet;

import managesystem.model.RiskPlan;
import managesystem.util.RiskPlanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 新增风险计划
 * Created by XXH on 2016/11/17.
 */
@WebServlet(name = "RiskPlanAddServlet", urlPatterns = "/addRiskPlan")
public class RiskPlanAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RiskPlanUtil riskPlanUtil = new RiskPlanUtil();
        String name = request.getParameter("name");
        try {
            RiskPlan riskPlan = new RiskPlan(name, new Date());
            riskPlanUtil.addRisPlan(riskPlan);
        }catch (Exception e) {
            System.out.print(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

    }
}
