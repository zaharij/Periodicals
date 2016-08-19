<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : user
    Created on : Jul 10, 2016, 6:43:46 PM
    Author     : Zakhar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="head.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="user" method="post">
        <center>
            <p><font color="green"><c:out value="${nameField}"></c:out>  
                <c:out value="${isAuthorField}"></c:out>  
                <c:out value="${isAdminField}"></c:out>  </font> | 
                <input type="submit" name="backToUser" value="<c:out value="${backSubmit}"></c:out>"/> |
                <input type="submit" name="languageReplenish" value="ENG"/>
            <input type="submit" name="languageReplenish" value="UKR"/> | 
            <input type="submit" name="logout" value="<c:out value="${logoutSubmit}"></c:out>"/> | <font color="#800080"><c:out value="${account}"></c:out></font></p>
            <p><font color="blue">--------------------------------------- <c:out value="${replenishPageInfo}"></c:out> ---------------------------------------
            </font></p>
            <table border="1">
                <tbody>
                    <tr>
                        <th><input type="text" name="money"/><input type="submit" name="replenish" value="<c:out value="${replenishSubmit}"></c:out>"/></th>
                    </tr>
                </tbody>
            </table>
            <p><font color="#a52a2a"><c:out value="${fieldFailInfo}"></c:out></font></p>
        </center>
        </form>
    </body>
</html>
