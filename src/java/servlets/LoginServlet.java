
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
        
        if (username.equals("") || username == null || password == null || password.equals(""))
        {
            request.setAttribute("invalid", "Error: Please input both of the values.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
            .forward(request, response);
            return;
        }
        
        User user = new User(username, password);
        
        AccountService as = new AccountService();
        
//        User user = (User) session.getAttribute("user");
        
        User userReturn = (User) as.login(username, password);
        
        if (userReturn != null)
        {
            session.setAttribute("user", userReturn);
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response);
        }
        else
        {
            request.setAttribute("invalid", "Error: Login failed.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
            return;
        }
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
        .forward(request, response);
    }

}
