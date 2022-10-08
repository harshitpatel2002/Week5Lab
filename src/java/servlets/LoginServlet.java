
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AccountService;
import models.User;
import org.apache.catalina.ha.ClusterSession;

/**
 *
 * @author hsp28
 */
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        String logout = request.getParameter("logout");
        if (session.getAttribute("sessionuser") != null && logout == null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response);
        }
        
        if (logout != null)
        {
            //invalidate the session
            session.invalidate();
            request.setAttribute("logoutmsg", "User has successfully logged out");
        }
                
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
            .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
//      Starting a new session
        HttpSession session = request.getSession();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username.equals("") || password.equals(""))
        {
            request.setAttribute("invalid", "Error: Please input both of the values.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
            .forward(request, response);
            return;
        }
        
//        If both values are inputed
        User user = new User(username, password);
        session.setAttribute("sessionuser", user);
        
        AccountService as = new AccountService();
        User userReturn = as.login(username, password);
        if (userReturn != null)
        {
            session.setAttribute("sessionuser", userReturn);
            response.sendRedirect(request.getContextPath() + "/home");
        }
        else
        {
            request.setAttribute("invalid", "Login failed. Please input the correct values");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
        }
    }

}
