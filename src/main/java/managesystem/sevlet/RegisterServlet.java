package managesystem.sevlet;

import managesystem.model.User;
import managesystem.util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Created by john on 2016/11/16.
 */
@WebServlet(name = "RegisterServlet",urlPatterns = "/register")

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException
    {
        String email = request.getParameter("email");
        String identity = request.getParameter("identity");
        String password = request.getParameter("password");

        UserUtil userUtil = new UserUtil();
        try {
            User user = userUtil.getUserByEmail(email);
            if (user == null) {

                User userAdd = new User(email, identity, password);
                userUtil.addUser(userAdd);
                System.out.printf(email);
                System.out.printf(identity);
                response.getWriter().print("success");
            }else {
                response.getWriter().print("fail");
            }
        }catch (Exception e) {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException
    {
        response.sendRedirect("jsp/auth/register.jsp");
    }
}
