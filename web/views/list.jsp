
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 79523
  Date: 16.12.2020
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Users</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div>
Users
</div>
<div>
    <div>

<p> <%= new String("Hello!!!")%></p>
<p>Today <%= new java.util.Date() %> </p>
        <br>
        <p><h1>List of Users:</h1></p>
<%--        <div><h2>Users</h2></div>--%>

<%

    List<String> namesReq = (List<String>)request.getAttribute("userNames");
    if(namesReq !=null && !namesReq.isEmpty()){
        out.print("<ul>");
        for (String h: namesReq){
            out.println("<li>" + h + "</li>");
        }
        out.print("<ul");
    }else {
        out.print("<p> There are not users yet!</p>");
    }

%>
        <br/>
        <br/>
    </div>
</div>

<form method="post">
    <label> Write User for delete:
        <input type="text" name="deleteUser">
    </label>
    <br/>
    <br/>
    <label> Input user pass for deleting user or root pass:
        <input type="password" name="pass">
    </label>
    <br/>
    <button type="submit">Submit</button>
    <br/>

</form>
<%
    String deleteUser = String.valueOf(request.getAttribute("deleteUser"));
    String deleteParam = String.valueOf(request.getAttribute("deleteParam"));

    if (deleteParam.equals("notDelete")) {
        out.print("<p> Not user with name : '" + deleteUser + "'</p>");
    }
    if (deleteParam.equals("emptyInsert")) {
        out.print("<p> Input field is empty! <p>");
    }
%>

<br/>
<br/>
<div>
<button onclick="location.href='/'">Back to main</button>
</div>

</body>
</html>
