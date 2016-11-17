package managesystem.sevlet;

import managesystem.model.Danger;
import managesystem.service.DangerService;
import managesystem.serviceImpl.DangerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hc on 2016/11/17.
 */
@WebServlet(name = "DangerServlet",urlPatterns ="*.danger")
public class DangerServlet extends HttpServlet {

    private DangerService dangerService=new DangerServiceImpl();;
    protected void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("danger service!!!!!!!!!!!!!!!!!!");
        String path=request.getServletPath();
        String op=path.substring(path.indexOf('/')+1,path.lastIndexOf('.'));
        System.out.println("op="+op);

        if(op.equals("add")){
            String content=request.getParameter("content");
            String possibility=request.getParameter("possibility");
            String effect=request.getParameter("effect");
            String threshold=request.getParameter("threshold");
            String poster=request.getParameter("poster");
            String condition=request.getParameter("condition");
            String description=request.getParameter("description");
            String rid=request.getParameter("rid");

            Danger danger=new Danger();
            danger.setCondition(condition);
            danger.setContent(content);
            danger.setDescription(description);
            danger.setEffect(effect);
            danger.setPossibility(possibility);
            danger.setPoster(poster);
            danger.setThreshold(threshold);
            danger.setRid(rid);

            dangerService.save(danger);
        }
    }


}
