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
                <input type="submit" name="languageUser" value="ENG"/>
            <input type="submit" name="languageUser" value="UKR"/> | 
            <input type="submit" name="logout" value="<c:out value="${logoutSubmit}"></c:out>"/> | <font color="#800080"><c:out value="${account}"></c:out></font></p>
                        <p><font color="blue"><c:out value="${periodicalsPageInfo}"></c:out></font></p>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th><strong><c:out value="${availablePageInfo}"></c:out></strong></th>
                                    <th><c:out value="${signedPageInfo}"></c:out></th>
                                    <th><c:out value="${writeArticlePageInfo}"></c:out></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><select name="subject">
                            <c:forEach var="periodical" items="${periodicals}" varStatus="periodicalCounter">
                                <option>
                                    <c:out value="${periodical}"/>
                                </option>
                            </c:forEach>
                        </select>
                <input type="submit" value="<c:out value="${addonInfoSubmit}"></c:out>" name="moreAboutPeriodical" /></td>
                                    <td><select name="subjectOpen">
                            <c:forEach var="periodicalPayed" items="${periodicalsPayed}" varStatus="periodicalPayedCounter">
                                <option>
                                    <c:out value="${periodicalPayed}"/>
                                </option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="<c:out value="${openSubmit}"></c:out>" name="openPeriodical" /></td>
                                    <td><select name="subjectToWrite">
                            <c:forEach var="periodical" items="${periodicals}" varStatus="periodicalCounter">
                                <option>
                                    <c:out value="${periodical}"/>
                                </option>
                            </c:forEach>
                        </select>
                <input type="submit" value="<c:out value="${chooseSubmit}"></c:out>" name="choosePeriodicalToWriteArticle" /></td>
                                </tr>
                            </tbody>
                        </table>
                        <table border="0">
                <tbody>
                    <tr>
                        <td><c:forEach var="articleNotPermitted" items="${articlesNotPermitted}" varStatus="articleNotPermittedCounter">
                                <a href="periodicals?viewUncheckedArticle=View&articlesNotPermitted=<c:out value="${articleNotPermitted}"/>"><option>
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
                        <td><a href="periodicals?permitArticleUser=Переглянути&articlesNotPermitted=<c:out value="${articleNameField}"/>"><c:out value="${permitUnchecked}"/></a>&nbsp&nbsp
                        <a href="periodicals?deleteUnpermitedArticleUser=Переглянути&articlesNotPermitted=<c:out value="${articleNameField}"/>"><c:out value="${deleteUnchecked}"/></a>&nbsp&nbsp
                        <a href="periodicals?backToUser=Переглянути&articlesNotPermitted="><c:out value="${closeUnchecked}"/></a></td>
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
