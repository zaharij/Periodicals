<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : register
    Created on : Jul 7, 2016, 11:05:09 PM
    Author     : Zakhar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="home_admin.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Periodicals</title>
    </head>
    <body>
        <center>
            <form action="periodicals" method="post">
                <table border="1">
                    <thead>
                        <tr>
                            <th><input type="submit" name="user" value="User"/></th>
                        </tr>
                    </thead>
                </table>
            </form>
        </center>
    </body>
</html>
