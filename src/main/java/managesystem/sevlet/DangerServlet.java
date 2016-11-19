package managesystem.sevlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import managesystem.model.AnalyseData;
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
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            condition="";
            ////////////////////////////////////
            String description=request.getParameter("description");
            int rid=Integer.parseInt(request.getParameter("rid"));
            System.out.println("rid是"+rid);
//            int rid=Integer.parseInt((String)request.getSession().getAttribute("rid"));

            Danger danger=new Danger();
            danger.setCondition(condition);
            danger.setContent(content);
            danger.setDescription(description);
            danger.setEffect(effect);
            danger.setPossibility(possibility);
            danger.setPoster(poster);
            danger.setThreshold(threshold);
            danger.setRid(rid);
            System.out.println(danger.toString());
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
            String date1=request.getParameter("date1");
            String date2=request.getParameter("date2");

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d1=sdf.parse(date1);
                Date d2=sdf.parse(date2);
                List<Danger> list=dangerService.getDangersByTime(d1,d2);
                request.setAttribute("dangerList",list);
                System.out.println("list的长度是"+list.size());
                System.out.println("date1是"+d1);
                System.out.println("date2是"+d2);

                RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/manage/dangerLookUp.jsp");
                dispatcher.forward(request,response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else if(op.equals("analyseDangerByTime")){
                String  data1[]={"问题1","问题2","问题3","问题4","问题5","问题6"};
                double data2[]={2.0, 4.9, 7.0, 23.2, 25.6, 76.7};
                double data3[]={2.0, 4.9, 7.0, 23.2, 25.6, 76.7};
                AnalyseData ad=new AnalyseData();
                ad.setData1(data1);
                ad.setData2(data2);
                ad.setData3(data3);
                String str=JSON.toJSONString(ad);
                PrintWriter out = response.getWriter();
                out.write(str);

        }
    }


}
