package managesystem.sevlet;

import managesystem.model.User;
import managesystem.util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by XXH on 2016/11/17.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String identity = request.getParameter("identity");
        String password = request.getParameter("password");
        try {
            UserUtil userUtil = new UserUtil();
            User user = userUtil.getUserByEmail(email);
            if (user == null) {
                response.getWriter().print("false");
            }else {
                if (user.getPswd().equals(password)){
                    request.getSession().setAttribute("name", user.getName());
                    request.getSession().setAttribute("identity", identity);
                    response.getWriter().print("true");
                }else {
                    response.getWriter().print("false");
                }
            }
        }catch (Exception e) {
            System.out.print(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("jsp/auth/login.jsp");
    }
}
