<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : register
    Created on : Jul 7, 2016, 11:05:09 PM
    Author     : Zakhar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="adminheadunreg.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <center>
    <form action="superadmin" method="post">
        <p><c:out value="${loginAsSuperadminMessage}"></c:out></p>
        <c:out value="${loginMessage}"></c:out><input type="text" name="login"/>
        <c:out value="${passwordMessage}"></c:out><input type="password" name="password"/>
        <input type="submit" name="loginAdmin" value="<c:out value="${logInSubmit}"></c:out>"/>
        <p><font color="#a52a2a"><c:out value="${fieldFailInfo}"></c:out></font></p>
        <p><font color="#228b22"><c:out value="${fieldSucInfo}"></c:out></font></p>
    </form>
    </center>
    </body>
</html>
