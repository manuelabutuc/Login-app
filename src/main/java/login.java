import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;



/**
 * Created by Manu on 25-Jun-16.
 */

@WebServlet("/login")
public class login extends HttpServlet {
    ArrayList<User> list = new ArrayList<User>();


    public ArrayList<User> demoRead() throws ClassNotFoundException, SQLException {
        try {// 1. load driver
            Class.forName("org.postgresql.Driver");

            // 2. define connection params to db
            final String URL = "jdbc:postgresql://54.93.65.5/4_Manu";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            Statement st = conn.createStatement();

            // 5. execute a query
            ResultSet rs = st.executeQuery("SELECT username,password FROM login");

            // 6. iterate the result set and print the values


            while (rs.next()) {
                User user = new User();
                String usr = rs.getString("username").trim();
                String pass = rs.getString("password").trim();
                user.setU(usr);
                user.setP(pass);
                list.add(user);
                System.out.println(user.getU() + " - " + user.getP());
            }


            // 7. close the objects
            rs.close();
            st.close();
            conn.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            demoRead();
            System.out.println("hello");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean log = false;

        for (User u : list) {
            if (u.getU().equals(username) && u.getP().equals(password)) {
                log = true;
                break;
            }

        }
        if (log == true) {
            System.out.println("success!");
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");

            out.println("<body>");

            out.println("<p style=\"text-align:center;margin-top:30px;font-size: 30px;\"><b>"
                    + "You logged in successfully!" + "</b><br>");
            out.println("<a href=\"http://oregonaitc.org/wp-content/uploads/2016/02/potato.jpg\" " +
                    "style=\"text-align:center;font-size: 17px;" +
                    "color:#006dcc;position:relative; top:20px;\">" +
                    "<b>Here's a potato for you!</b></a>");
            out.println("<a href=\"index.html\" " +
                    "style=\"text-align:center;font-size: 17px;" +
                    "color:#006dcc;position:relative; top:20px;\">" +
                    "<b>Log out</b></a>");

            out.close();


            out.println("</body>");
        } else {
            System.out.println("try again");
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");

            out.println("<body>");


            out.println("<p style=\"text-align:center;margin-top:30px;font-size: 30px;\"><b>" +
                    "Wrong username or password!" + "</b><br>");
            out.println("<a href=\"index.html\" style=\"text-align:center;" +
                    "font-size: 17px;color:#006dcc;position:relative; top:20px; left:-10px;\">" +
                    "<b>Try again</b></a>");
            out.println("<a href=\"new_account.html\" style=\"text-align:center;" +
                    "font-size: 17px;color:#006dcc;position:relative; top:20px; right:-10px;\">" +
                    "<b>Create account</b></a>");
            out.close();


            out.println("</body>");
        }

    }


}





