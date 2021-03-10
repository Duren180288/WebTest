<%@ page import="app.model.Connect" %>
<%@ page import="app.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: 79523
  Date: 16.12.2020
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new User</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
</body>
<div>GET from add.jsp</div>
<div>
    <%

        String userName = String.valueOf(request.getAttribute("userName"));
        String userPass = String.valueOf(request.getAttribute("userPass"));
        String userParam = String.valueOf(request.getAttribute("viewParam"));


        if (userParam.equals("wasAdded")) {
            out.print("<p> Not added, because, user '" + request.getAttribute("userName") + "' was added alredy!</p>");

        }
        if (userParam.equals("add")) {
            out.print("<p> User '" + request.getAttribute("userName") + "' added!</p>");

        }
        if (userParam.equals("addSortPass")) {
            out.print("<p> User '" + request.getAttribute("userName") + "' not added! Pass is so short! </p>");
            out.print("<p> Pass length is " + userPass.length() + ". Pass must be from 3 to 10 chars.");
            out.print("<p> Try again!!!");
        }
        if (userParam.equals("addLongPass")) {
            out.print("<p> User '" + request.getAttribute("userName") + "' not added! Pass is so long! </p>");
            out.print("<p> Pass length is " + userPass.length() + ". Pass must be from 3 to 10 chars.");
            out.print("<p> Try again!!!");
        }
        if (userParam.equals("notName")){
            out.print("<p> User not added! input field is empty! <p>");
        }

    %>

    <div>
        <div>
            <h2>Add user</h2>
        </div>
        <form method="post">
            <label>Name:
                <input type="text" name="name"><br/>
            </label>

            <label>Password:
                <input type="password" name="pass"><br/>
            </label>
            <button type="submit">Submit</button>
            <%--    <button type="submit">Back</button>--%>
        </form>
    </div>
</div>

<div>
    <button onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>

