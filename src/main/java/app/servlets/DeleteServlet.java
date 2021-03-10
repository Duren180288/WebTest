package app.servlets;

import app.model.Connect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connect connection = new Connect();
        connection.goConnectToSQL();
        String deleteUser = (String) req.getAttribute("deleteUser");
        String inputPass = (String) req.getAttribute("pass");
        // String deleteParam = (String) req.getAttribute("deleteParam");
        String deleteParam = "";
        System.out.println("name " + deleteUser + "  pass " + inputPass);



        System.out.println(inputPass);
        String userPass = connection.selectOneUserPass(deleteUser);
        System.out.println(userPass);
        String rootPass = "4444";


        if (!inputPass.isEmpty()) {

            if (inputPass.equals(userPass) || inputPass.equals(rootPass)) {
                connection.deleteUser(deleteUser);
                deleteParam = "Delete";
                req.setAttribute("deleteUser", deleteUser);
                req.setAttribute("deleteParam", deleteParam);


            } else {
                deleteParam = "NotDelete";
                req.setAttribute("deleteUser", deleteUser);
                req.setAttribute("deleteParam", deleteParam);
            }
        } else {
            deleteParam = "EmptyPass";
            req.setAttribute("deleteUser", deleteUser);
            req.setAttribute("deleteParam", deleteParam);

        }


        RequestDispatcher dispatcher = req.getRequestDispatcher("views/delete.jsp");
        dispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}




