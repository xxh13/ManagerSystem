package managesystem.sevlet;

import managesystem.model.Danger;
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
 *
 * Created by XXH on 2016/11/17.
 */
@WebServlet(name = "RiskPlanFindServlet", urlPatterns = "/editRiskPlan")
public class RiskPlanFindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        System.out.println(rid);
        try {
            RiskPlanUtil riskPlanUtil = new RiskPlanUtil();
            List<Danger> dangerList = riskPlanUtil.findDangerListByRid(rid);
            request.getSession().setAttribute("dangerList", dangerList);
            request.getSession().setAttribute("rid", rid);
//            request.getSession().setAttribute("riskPlanName", rid);
            response.sendRedirect("jsp/manage/editRiskPlan.jsp");
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
