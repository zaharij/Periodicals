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
                <input type="submit" name="languageAdminUserManagement" value="ENG"/>
            <input type="submit" name="languageAdminUserManagement" value="UKR"/> | 
            <input type="submit" name="logoutAdmin" value="<c:out value="${logoutSubmit}"></c:out>"/></p>
            <p><input type="submit" name="periodicalsManagement" value="<c:out value="${periodicalsManagementSubmit}"></c:out>"/></p>
            <table border="1" cellspacing="10">
                <tbody>
                    <tr>
                        <th><c:out value="${setAdminRightsMessage}"></c:out></th>
                        <td><select name="admins">
                            <c:forEach var="admin" items="${admins}" varStatus="adminCounter">
                                <option>
                                    <c:out value="${admin}"/>
                                </option>
                            </c:forEach>
                        </select>
                <input type="submit" value="<c:out value="${unsetAdminSubmit}"></c:out>" name="unsetAdmin" />
                        <select name="usersSimple">
                            <c:forEach var="userSimple" items="${usersSimple}" varStatus="userSimpleCounter">
                                <option>
                                    <c:out value="${userSimple}"/>
                                </option>
                            </c:forEach>
                        </select>
                <input type="submit" value="<c:out value="${setAdminSubmit}"></c:out>" name="setAdmin" /></td>
                    </tr>
                    <tr>
                        <th><c:out value="${deleteUserMessage}"></c:out></th>
                        <td><select name="usersEmail">
                            <c:forEach var="user" items="${usersAll}" varStatus="userCounter">
                                <option>
                                    <c:out value="${user}"/>
                                </option>
                            </c:forEach>
                        </select>
                <input type="submit" value="<c:out value="${deleteSubmit}"></c:out>" name="deleteUser" /></td>
                    </tr>
                    <tr>
                        <th><c:out value="${blockMessage}"></c:out></th>
                        <td><select name="blockedUsers">
                            <c:forEach var="blockedUser" items="${usersBlocked}" varStatus="blockedUserCounter">
                                <option>
                                    <c:out value="${blockedUser}"/>
                                </option>
                            </c:forEach>
                        </select>
                <input type="submit" value="<c:out value="${unblockSubmit}"></c:out>" name="unblockUser" />
                        <select name="unblockedUsers">
                            <c:forEach var="unblockedUser" items="${usersUnblocked}" varStatus="unblockedUserCounter">
                                <option>
                                    <c:out value="${unblockedUser}"/>
                                </option>
                            </c:forEach>
                        </select>
                <input type="submit" value="<c:out value="${blockSubmit}"></c:out>" name="blockUser" /></td>
                    </tr>
                    <tr>
                        <th><c:out value="${payMessage}"></c:out></th>
                        <td>
                            <input type="submit" value="<c:out value="${paySubmit}"></c:out>" name="payForArticles" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </center>
        </form>
    </body>
</html>
