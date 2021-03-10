<%--
  Created by IntelliJ IDEA.
  User: 79523
  Date: 01.01.2021
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting</title>
</head>
<body>
<div>

    <%
        String userName = String.valueOf(request.getAttribute("deleteUser"));
        out.print("Deleting");
        String userParam = String.valueOf(request.getAttribute("deleteParam"));
//        out.print(userParam);
        if (userParam.equals("Delete")) {
            out.print("<p> User '" + userName + "' deleted!</p>");
        }
        if (userParam.equals("NotDelete")) {
            out.print("<p> User '" + userName + "' not deleted! Check a pass!</p>");

        }
        if (userParam.equals("EmptyPass")) {
            out.print("<p> User '" + userName + "' not deleted! Pass field is empty!</p>");
        }


    %>
    </form>
    <div>
        <h2>Delete user</h2>
    </div>

<%--    <form method="post" action="/delete">--%>
<%--        <label> Accept deleting--%>
<%--            <input type="submit" name="Agrement">--%>
<%--        </label>--%>
<%--        <button type="submit"> Submit</button>--%>
<%--        <br/>--%>
<%--        <br/>--%>
<%--    </form>--%>


    <div>
        <button onclick="location.href='/list'">Back to user list</button>
    </div>
</div>
</body>
</html>
