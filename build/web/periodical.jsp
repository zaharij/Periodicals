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
                <input type="submit" name="replenishAccount" value="<c:out value="${replenishAccountSubmit}"></c:out>"/> | 
                <input type="submit" name="languagePeriodical" value="ENG"/>
            <input type="submit" name="languagePeriodical" value="UKR"/> | 
            <input type="submit" name="logout" value="<c:out value="${logoutSubmit}"></c:out>"/> | <font color="#800080"><c:out value="${account}"></c:out></font></p>
            <p><font color="blue">--------------------------------------- <c:out value="${titlePeriodical}"></c:out> ---------------------------------------
            </font></p>
            <table border="1">
                <tbody>
                    <tr>
                        <th><c:out value="${dateSubscriptionPageInfo}"></c:out></th>
                        <td><c:out value="${subscriptionDate}"></c:out></td>
                    </tr>
                    <tr>
                        <th><c:out value="${availableArticlesPageInfo}"></c:out></th>
                        <td><select name="articleName">
                            <c:forEach var="article" items="${articles}" varStatus="articleCounter">
                                <option>
                                    <c:out value="${article}"/>
                                </option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="<c:out value="${openSubmit}"></c:out>" name="openArticle" /></td>
                    </tr>
                </tbody>
            </table>
            <table border="0" width="800" cellspacing="10">
                <thead>
                    <tr>
                        <th><c:out value="${articleNameField}"></c:out></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><c:out value="${articleReviewField}"></c:out></td>
                    </tr>
                    <tr>
                        <td><c:out value="${articleTextField}"></c:out></td>
                    </tr>
                    <tr>
                        <td><c:out value="${articleAuthorNameField}"></c:out></td>
                    </tr>
                    <tr>
                        <td><c:out value="${articleDateField}"></c:out></td>
                    </tr>
                </tbody>
            </table>
        </center>
        </form>
    </body>
</html>
