<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : register
    Created on : Jul 7, 2016, 11:05:09 PM
    Author     : Zakhar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="headunreg.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <center>
    <form action="periodicals" method="post">
        <p><input type="submit" name="languageMain" value="ENG"/>
            <input type="submit" name="languageMain" value="UKR"/></p>
        <c:out value="${emailPageInfo}"></c:out><input type="text" name="email"/>
        <c:out value="${passwordPageInfo}"></c:out><input type="password" name="password"/>
        <input type="submit" name="loginUser" value="<c:out value="${loginSubmit}"></c:out>"/>
        <input type="submit" name="registerUser" value="<c:out value="${registrationSubmit}"></c:out>"/>
        <p><font color="#a52a2a"><c:out value="${fieldFailInfo}"></c:out></font></p>
        <p><font color="#228b22"><c:out value="${fieldSucInfo}"></c:out></font></p>
    <table border="1">
        <tbody>
            <tr>
                <th><c:out value="${availablePeriodicalsPageInfo}"></c:out></th>
                <td><c:forEach var="periodical" items="${periodicals}" varStatus="periodicalCounter">
            - <c:out value="${periodical}"/><br>
            </c:forEach></td>
            </tr>
            <tr>
                <th><c:out value="${authorsNumberPageInfo}"></c:out></th>
                <td><font color="#228b22"><c:out value="${authNum}"></c:out></font></td>
            </tr>
        </tbody>
    </table>
    </center>
    </body>
</html>
