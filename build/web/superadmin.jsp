<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : user
    Created on : Jul 10, 2016, 6:43:46 PM
    Author     : Zakhar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="adminhead.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="superadmin" method="post">
        <center>
            <p><font color="green"><c:out value="${loginField}"></c:out></font> | 
                <input type="submit" name="languageAdmin" value="ENG"/>
            <input type="submit" name="languageAdmin" value="UKR"/> | 
            <input type="submit" name="logoutAdmin" value="<c:out value="${logoutSubmit}"></c:out>"/></p>
            <p><input type="submit" name="userManagement" value="<c:out value="${userManagementSubmit}"></c:out>"/></p>
            <table border="1" cellspacing="10">
                <tbody>
                    <tr>
                        <th><c:out value="${createNewPublicationMessage}"></c:out></th>
                        <td><c:out value="${titleMessage}"></c:out><input type="text" name="titlePeriodical"/>
                        <c:out value="${priceMessage}"></c:out><input type="text" name="pricePeriodical"/>
                <input type="submit" name="createPeriodical" value="<c:out value="${createSubmit}"></c:out>"/></td>
                    </tr>
                    <tr>
                        <th><c:out value="${deletePeriodicalArticleMessage}"></c:out></th>
                        <td><select name="subject">
                            <c:forEach var="periodical" items="${periodicals}" varStatus="periodicalCounter">
                                <option>
                                    <c:out value="${periodical}"/>
                                </option>
                            </c:forEach>
                        </select>
                <input type="submit" value="<c:out value="${deleteSubmit}"></c:out>" name="deletePeriodical" />
                <input type="submit" value="<c:out value="${deleteArticleSubmit}"></c:out>" name="deletePeriodicalArticle"/>
                        <select name="periodicalArticles">
                            <c:forEach var="article" items="${articles}" varStatus="articleCounter">
                                <option>
                                    <c:out value="${article}"/>
                                </option>
                            </c:forEach>
                        </select>
                <input type="submit" value="<c:out value="${deleteSubmit}"></c:out>" name="deleteArticle"/></td>
                    </tr>
                    <tr>
                        <th><c:out value="${permitArticleMessage}"></c:out></th>
                        <td>
                            <select name="articlesNotPermitted">
                            <c:forEach var="articleNotPermitted" items="${articlesNotPermitted}" varStatus="articleNotPermittedCounter">
                                <option>
                                    <c:out value="${articleNotPermitted}"/>
                                </option>
                            </c:forEach>
                        </select>
                            <input type="submit" value="<c:out value="${viewSubmit}"></c:out>" name="viewArticle"/>
                            <input type="submit" value="<c:out value="${permitSubmit}"></c:out>" name="permitArticle"/>
                            <input type="submit" value="<c:out value="${deleteSubmit}"></c:out>" name="deleteUnpermitedArticle"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table border="0">
                <tbody>
                    <tr>
                        <td><c:forEach var="articleNotPermitted" items="${articlesNotPermitted}" varStatus="articleNotPermittedCounter">
                                <a href="superadmin?viewArticle=View&articlesNotPermitted=<c:out value="${articleNotPermitted}"/>"><option>
                                <c:out value="${articleNotPermitted}"/>
                                </option></a>
                            </c:forEach></td>
                        <td><table border="0" width="800" cellspacing="10">
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
                    <tr>
                        <td><a href="superadmin?permitArticle=Переглянути&articlesNotPermitted=<c:out value="${articleNameField}"/>"><c:out value="${permitUnchecked}"/></a>&nbsp&nbsp
                        <a href="superadmin?deleteUnpermitedArticle=Переглянути&articlesNotPermitted=<c:out value="${articleNameField}"/>"><c:out value="${deleteUnchecked}"/></a>
                    </td>
                    </tr>
                </tbody>
            </table></td>
                    </tr>
                </tbody>
            </table>
        </center>
        </form>
    </body>
</html>
