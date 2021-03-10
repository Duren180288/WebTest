package app.servlets;

import app.entities.User;
import app.model.Connect;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/add")
public class AddServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connect connection = new Connect();
        connection.goConnectToSQL();
        String name = req.getParameter("name").trim();
        String password = req.getParameter("pass").trim();
        User user = new User(name, password);
        String viewParam = "";

        if (!name.isEmpty()) {
            for (int i = 0; i < connection.showAddedUsers().size(); i++) {
                if (connection.showAddedUsers().get(i).equals(name)) {
                    viewParam = "wasAdded";
                    req.setAttribute("userName", name);
                    req.setAttribute("userPass", password);
                    req.setAttribute("viewParam", viewParam);
                    doGet(req, resp);
                    break;
                }
            }
        }

        if (!name.isEmpty() && !viewParam.equals("wasAdded")) {

            if (password.length() >= 3 && password.length() <= 10) {
                connection.add(user);
                viewParam = "add";
                req.setAttribute("userName", name);
                req.setAttribute("userPass", password);
                req.setAttribute("viewParam", viewParam);
                doGet(req, resp);
            }
            if (password.length() < 3) {
                viewParam = "addSortPass";
                req.setAttribute("userName", name);
                req.setAttribute("userPass", password);
                req.setAttribute("viewParam", viewParam);
                doGet(req, resp);

            }
            if (password.length() > 10) {
                viewParam = "addLongPass";
                req.setAttribute("userName", name);
                req.setAttribute("userPass", password);
                req.setAttribute("viewParam", viewParam);
                doGet(req, resp);
            }

        } else {
            viewParam = "notName";
            req.setAttribute("userName", name);
            req.setAttribute("userPass", password);
            req.setAttribute("viewParam", viewParam);
            doGet(req, resp);
        }

        //PrintWriter writer = resp.getWriter();
        //if (password.length() < 3) {

//        req.setAttribute("userName", name);
//
//            req.setAttribute("userPass", password);
        //doGet(req, resp);
//

        // writer.println("Pass is so short!");


        // doGet(req, resp);


//        } else if (password.length() > 10) {
//            writer.println("Pass is so long!");
//        } else {
//            User user = new User(name, password);
//            connection.add(user);

//            req.setAttribute("userName", name);
//            doGet(req, resp);
    }


}




