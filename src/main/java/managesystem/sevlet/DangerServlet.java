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
import java.io.PrintWriter;

/**
 * Created by hc on 2016/11/17.
 */
@WebServlet(name = "DangerServlet",urlPatterns ="/danger/*")
public class DangerServlet extends HttpServlet {

    private DangerService dangerService=new DangerServiceImpl();;
    protected void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("danger service!!!!!!!!!!!!!!!!!!");
        String path=request.getRequestURL().toString();

        System.out.println("path="+path);
        String op=path.substring(path.lastIndexOf('/')+1);
        System.out.println("op="+op);
        if(op.equals("test")){
           response.sendRedirect("/jsp/tmp.jsp");
        }
        if(op.equals("add")){
            String content=request.getParameter("content");
            String possibility=request.getParameter("possibility");
            String effect=request.getParameter("effect");
            String threshold=request.getParameter("threshold");
            String poster=request.getParameter("poster");
            String condition=request.getParameter("condition");
            /////////////////////////////////////////////////////////////////////
            System.out.println("条件是"+content);
            condition="你好！";
            ////////////////////////////////////
            String description=request.getParameter("description");
            //int rid=Integer.parseInt(request.getParameter("rid"));
            int rid=1;

            Danger danger=new Danger();
            danger.setCondition(condition);
            danger.setContent(content);
            danger.setDescription(description);
            danger.setEffect(effect);
            danger.setPossibility(possibility);
            danger.setPoster(poster);
            danger.setThreshold(threshold);
            danger.setRid(rid);
            String result=dangerService.save(danger);

            PrintWriter out = response.getWriter();
            out.write(result);
        }
        else if(op.equals("del")){
            String did=request.getParameter("did");
            String result=dangerService.deleteDanger(Integer.parseInt(did));
            System.out.println("删除结果是"+result);
            PrintWriter out = response.getWriter();
            out.write(result);
        }
        else if(op.equals("update")){
            int did=Integer.parseInt(request.getParameter("did"));
           // String content=request.getParameter("content");
            //String possibility=request.getParameter("possibility");
            //String effect=request.getParameter("effect");
            //String threshold=request.getParameter("threshold");
            //String poster=request.getParameter("poster");
            String condition=request.getParameter("condition");
            String description=request.getParameter("description");
            int rid=Integer.parseInt(request.getParameter("rid"));

            Danger danger=new Danger();
            danger.setDid(did);
            danger.setCondition(condition);
            //danger.setContent(content);
            danger.setDescription(description);
            //danger.setEffect(effect);
            //danger.setPossibility(possibility);
            //danger.setPoster(poster);
            //danger.setThreshold(threshold);
            danger.setRid(rid);

            String result=dangerService.update(danger);
            PrintWriter out = response.getWriter();
            out.write(result);
        }
        else if(op.equals("getDangerByTime")) {

        }
    }


}
