package managesystem.sevlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hc on 2016/11/17.
 */
@WebServlet(name = "DangerItemServlet",urlPatterns ="*.dangerItem")
public class DangerItemServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("dangerItem service!!!!!!!!!!!!!!!!!!");
        String path=request.getServletPath();
        String op=path.substring(path.indexOf('/')+1,path.lastIndexOf('.'));
        System.out.println("op="+op);

        if(op.equals("add")){

        }
    }


}
