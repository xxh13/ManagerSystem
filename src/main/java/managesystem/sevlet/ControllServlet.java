package managesystem.sevlet;

import managesystem.model.Danger;
import managesystem.service.DangerService;
import managesystem.serviceImpl.DangerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hc on 2016/11/17.
 */
@WebServlet(name = "ControllServlet",urlPatterns ="/view/*")
public class ControllServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path=request.getRequestURL().toString();
        String view=path.substring(path.lastIndexOf('/')+1);

        DangerService ds=new DangerServiceImpl();
        if(view.equals("addDanger")){
            response.sendRedirect("/jsp/manage/addDanger.jsp");
        }
        else if(view.equals("editRiskPlan")){
           RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/manage/editRiskPlan.jsp");
            List<Danger> list=ds.getDangersByRid(1);
            System.out.println("list的长度="+list.size());
            request.setAttribute("dangerList",list);
            dispatcher.forward(request,response);
        }
        else if(view.equals("editDanger")){
            RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/manage/editDanger.jsp");
            Danger danger=ds.getDangerByDid(Integer.parseInt(request.getParameter("did")));
            request.setAttribute("danger",danger);
            dispatcher.forward(request,response);
        }
        else if(view.equals("riskAnalyse")){
            RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/manage/riskAnalyse.jsp");
            dispatcher.forward(request,response);
        }
        else if(view.equals("dangerLookUp")){
            RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/manage/dangerLookUp.jsp");
            dispatcher.forward(request,response);
        }

    }


}
