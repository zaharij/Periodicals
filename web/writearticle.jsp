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
                <input type="submit" name="languageWriteArticle" value="ENG"/>
            <input type="submit" name="languageWriteArticle" value="UKR"/> | 
            <input type="submit" name="logout" value="<c:out value="${logoutSubmit}"></c:out>"/> | <font color="#800080"><c:out value="${account}"></c:out></font></p>
            <p><font color="blue">--------------------------------------- <c:out value="${titlePeriodical}"></c:out> ---------------------------------------
            </font></p>
            <table border="0" width="900" cellspacing="2">
                <tbody>
                    <tr>
                        <td><c:out value="${titlePageInfo}"></c:out></td>
                        <td><input type="text" name="articleName" value="" style="width: 500px;" /></td>
                    </tr>
                    <tr>
                        <td><c:out value="${annotationPageInfo}"></c:out></td>
                        <td><textarea name="annotation" style="width: 800px; height: 200px">
                            </textarea></td>
                    </tr>
                    <tr>
                    <td><c:out value="${textPageInfo}"></c:out></td>
                        <td><textarea name="articleText" style="width: 800px; height: 400px">
                            </textarea></td>
                    </tr>
                    <tr>
                    <td></td>
                        <td><p align="right"><input type="submit" name="sendArticle" value="<c:out value="${sendSubmit}"></c:out>"/></p></td>
                    </tr>
                </tbody>
            </table>
            
        </center>
        </form>
    </body>
</html>
