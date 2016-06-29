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
 * Created by Manu on 29-Jun-16.
 */
@WebServlet("/newAccount")
public class NewAccount  extends HttpServlet {

    ArrayList<User> list = new ArrayList<User>();


    public ArrayList<User> demoCreate(String username, String password) throws ClassNotFoundException, SQLException {
        try {// 1. load driver
            Class.forName("org.postgresql.Driver");

            // 2. define connection params to db
            final String URL = "jdbc:postgresql://54.93.65.5/4_Manu";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 4. create a query statement
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO login (username, password) VALUES (?,?)");
            pSt.setString(1, username);
            pSt.setString(2, password);

            // 5. execute a prepared statement
            int rowsInserted = pSt.executeUpdate();

            // 6. close the objects
            pSt.close();
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

        String username = req.getParameter("usernameNew");
        String password = req.getParameter("passwordNew");
        String password1 = req.getParameter("passwordNew1");
        System.out.println(username);
        if (password.equals(password1) && (username != "") && (password != "") && (password1 != "")) {
            try {
                demoCreate(username, password);
                System.out.println(username);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");

            out.println("<body>");

            out.println("<p style=\"text-align:center;margin-top:30px;font-size: 30px;\"><b>" +
                    "New account was created successfully!" + "</b><br>");
            out.println("<a href=\"index.html\" style=\"text-align:center;font-size: 17px;" +
                    "color:#006dcc;position:relative; top:20px;\"><b>Sign in right now</b></a>");

            out.close();
        } else {
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");

            out.println("<body>");

            out.println("<p style=\"text-align:center;margin-top:30px;font-size: 30px;\"><b>" +
                    "Ups, something went wrong!" + "</b><br>");
            out.println("<a href=\"new_account.html\" style=\"text-align:center;font-size: 17px;" +
                    "color:#006dcc;position:relative; top:20px;\"><b>Try again</b></a>");

            out.close();
        }


    }


}

