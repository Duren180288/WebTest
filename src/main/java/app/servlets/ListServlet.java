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

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    //protected  static  String deleteUser;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connect connection = new Connect();
        connection.goConnectToSQL();

        req.setAttribute("userNames", connection.showAddedUsers());

        RequestDispatcher dispatcher = req.getRequestDispatcher("views/list.jsp");
        dispatcher.forward(req, resp);
    }


//        public String getUser(){
//            return deleteUser;
//        }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connect connection = new Connect();
        connection.goConnectToSQL();
        String deleteParam = "";
        String deleteUser = req.getParameter("deleteUser");
        String pass = req.getParameter("pass");
//
        System.out.println(deleteUser);
        for (String s : connection.showAddedUsers()) {
            System.out.println(s);
        }
        if (!deleteUser.isEmpty()) {
            System.out.println("hcjvh");
            for (int i = 0; i < connection.showAddedUsers().size(); i++) {
                if (deleteUser.equals(connection.showAddedUsers().get(i))) {
                    // req.getSession().setAttribute("deleteUser", deleteUser);
                   // deleteParam = "delete";

                    req.setAttribute("deleteUser", deleteUser);
                    req.setAttribute("pass", pass);

                    String path = "/delete";
                    ServletContext servletContext = getServletContext();
                    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
                    requestDispatcher.forward(req, resp);
break;

                } else {
                    deleteParam = "notDelete";
                    req.setAttribute("deleteUser", deleteUser);
                    req.setAttribute("deleteParam", deleteParam);


                }
            }
            doGet(req, resp);

        } else {
            deleteParam = "emptyInsert";
            req.setAttribute("deleteParam", deleteParam);
            doGet(req, resp);

        }
    }
}




//        req.setAttribute("userName", deleteUser);

//    }
//}


//        PrintWriter writer = resp.getWriter();
//        writer.write("Welcome!!! \n");
//        writer.println("Method GET from LISTServlet");
//        Model model = Model.getInstance();
//        List<String> names = model.list();
//        req.setAttribute("userNames", names);

//        Model model = Model.getInstance();
//        List<String> names = model.list();
//        req.setAttribute("userNames", names);

//            Connect connection1 = Connect.getInstance();
//  System.out.println(connection == connection1);
//            Model2 model2 = Model2.getInstance();
//            Model2 model21 =Model2.getInstance();
//            System.out.println(model2 == model21);
//            model2.getConnect();
//            test2 t2 = new test2();
//            t2.goSQL();