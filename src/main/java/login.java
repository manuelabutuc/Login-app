import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



/**
 * Created by Manu on 25-Jun-16.
 */

@WebServlet("/login")
public class login extends HttpServlet {
    ArrayList<User> l = new ArrayList<User>();
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out =resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        System.out.println("corect: "+username);
//        System.out.println("corect: "+password);

        boolean log=false;
        for (User u : l) {
            if (u.getU().equals(username) && u.getP().equals(password)) {
                log=true;
                break;
            }

        }
        if(log==true){
            System.out.println("success!");
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");

            out.println("<body>");


            out.println("<b>"+"Te-ai logat cu succes!"+"</b>");

            out.close();



            out.println("</body>");
        }
        else{
            System.out.println("try again");
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");

            out.println("<body>");


            out.println("<b>"+"Username sau parola gresita!"+"</b>");

            out.close();



            out.println("</body>");
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        User u1=new User();
        u1.setU("manuela");
        u1.setP("parola");
        User u2=new User();
        u2.setU("admin");
        u2.setP("12345");

        l.add(u1);
        l.add(u2);
    }
}
