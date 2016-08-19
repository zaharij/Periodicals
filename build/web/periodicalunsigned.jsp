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
                <input type="submit" name="replenishAccount" value="<c:out value="${replenishAccountSubmit}"></c:out>"/> |
                <input type="submit" name="languagePeriodicalunsigned" value="ENG"/>
            <input type="submit" name="languagePeriodicalunsigned" value="UKR"/> | 
            <input type="submit" name="logout" value="<c:out value="${logoutSubmit}"></c:out>"/> | <font color="#800080"><c:out value="${account}"></c:out></font></p>
            <p><c:out value="${signeUpPageInfo}"></c:out><font color="grey"><c:out value="${addonSignePageInfo}"></c:out></font> 
                <select name="monthCost">
                            <c:forEach var="monthCost" items="${monthesCost}" varStatus="monthCostCounter">
                                <option>
                                    <c:out value="${monthCost}"/>
                                </option>
                            </c:forEach>
                        </select>
                <input type="submit" name="signeUp" value="<c:out value="${signeUpSubmit}"></c:out>"/>
                <input type="submit" name="backToUser" value="<c:out value="${backSubmit}"></c:out>"/>
            <p><font color="blue">--------------------------------------- <c:out value="${addonInfo}"></c:out> ---------------------------------------
            </font></p>
            <table border="1">
                <tbody>
                    <tr>
                        <th><c:out value="${subjectPageInfo}"></c:out></th>
                        <td><font color="#228b22"><c:out value="${titlePeriodical}"></c:out></font></td>
                    </tr>
                    <tr>
                        <th><c:out value="${pricePageInfo}"></c:out></th>
                        <td><font color="#228b22"><c:out value="${pricePeriodical}"></c:out></font></td>
                    </tr>
                    <tr>
                        <th><c:out value="${articlesNumberPageInfo}"></c:out></th>
                        <td><font color="#228b22"><c:out value="${articlesNum}"></c:out></font></td>
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
                        <input type="submit" value="<c:out value="${getAcquintedSubmit}"></c:out>" name="getAcquainted" /></td>
                    </tr>
                </tbody>
            </table><br>
            <table border="0" width="600">
                <thead>
                    <tr>
                        <th><c:out value="${currentArticleTitle}"></c:out></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><c:out value="${review}"></c:out></td>
                    </tr>
                </tbody>
            </table>
        </center>
        </form>
    </body>
</html>
