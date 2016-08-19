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
        <p><input type="submit" name="languageRegister" value="ENG"/>
            <input type="submit" name="languageRegister" value="UKR"/></p>
        <p><c:out value="${firstNamePageInfo}"></c:out> <input type="text" name="firstName"/></p>
        <p><c:out value="${middleNamePageInfo}"></c:out> <input type="text" name="fatherName"/></p>
        <p><c:out value="${lastNamePageInfo}"></c:out> <input type="text" name="lastName"/></p>
        <p><c:out value="${emailPageInfo}"></c:out> <input type="text" name="email"/></p>
        <p><c:out value="${passwordPageInfo}"></c:out> <input type="password" name="password"/></p>
        <p><c:out value="${repeatPasswordPageInfo}"></c:out> <input type="password" name="passwordRep"/></p>
        <p><c:out value="${enterFromPicPageInfo}"></c:out></p>
        <p><img src="${logimg}"> <input type="text" name="logimg"/></p>
        <p><c:out value="${requiredFieldsPageInfo}"></c:out></p>
        <p><input type="submit" name="registerDo" value="<c:out value="${registerSubmit}"></c:out>"/></p>
        <font color="#a52a2a"><c:out value="${fieldFailInfo}"></c:out></font>
        <p><input type="submit" name="startPage" value="<c:out value="${mainSubmit}"></c:out>"/></p>
    </form>
    </center>
    </body>
</html>
