
package login.sumit;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static sun.swing.SwingUtilities2.submit;

/**
 *
 * @author Ahmad Shawakri
 */
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String un = request.getParameter("name");
        String pass = request.getParameter("password");
        if(un.equals("admin") && pass.equals("admin"))
            
            response.sendRedirect("user-form.jsp");
            //forward(request, response);
        else
            System.err.println("Username or Password wrong.Please try agian");
    }
}
       
