package managesystem.sevlet;

import managesystem.model.RiskPlan;
import managesystem.util.RiskPlanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by XXH on 2016/11/17.
 */
@WebServlet(name = "RiskPlanServlet" , urlPatterns = "/riskPlan")
public class RiskPlanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try {
            RiskPlanUtil riskPlanUtil = new RiskPlanUtil();
            List<RiskPlan> riskPlanList = riskPlanUtil.riskPlanAll();

            request.getSession().setAttribute("riskPlanList", riskPlanList);
            response.sendRedirect("jsp/manage/riskPlanList.jsp");
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
