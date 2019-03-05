<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<section class="section">
    <div class="container">
        <h2 class="subtitle is-4">Home</h2>
        <c:choose>
            <c:when test="${user!=null}">
                <p class="content">Welcome ${user.username}!</p>
                <form method="post" action="Controller?action=LogOut">
                    <div class="field">
                        <div class="control">
                            <button class="button is-primary" type="submit" id="logoutbutton">Log out</button>
                        </div>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <c:if test="${errors.size()>0 }">
                    <div class="notification is-danger">
                        <ul>
                            <c:forEach var="error" items="${errors}">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <div class="columns">
                    <div class="column is-one-quarter">
                        <form method="post" action="Controller?action=LogIn">
                            <div class="field">
                                <label class="label" for="username">Username</label>
                                <div class="control has-icons-left">
                                    <input class="input" id="username" name="username" type="text" value="Jan">
                                    <span class="icon is-small is-left">
                                    <i class="fas fa-user"></i>
                                </span>
                                </div>
                            </div>
                            <div class="field">
                                <label class="label" for="password">Password</label>
                                <div class="control has-icons-left">
                                    <input class="input" id="password" name="password" type="password" value="t">
                                    <span class="icon is-small is-left">
                                    <i class="fas fa-lock"></i>
                                </span>
                                </div>
                            </div>
                            <div class="field">
                                <div class="control">
                                    <button class="button is-primary" type="submit" id="loginbutton">Log in</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>