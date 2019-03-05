<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Chat"/>
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chat"/>
</jsp:include>
<section class="section">
    <div class="container">
        <h2 class="subtitle is-4">Chat</h2>
        <div class="content has-text-right">
            <span>Status: </span>
            <span id="currentstatus" class="tag is-primary"><c:out value="${sessionScope.user.status}"/></span>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>Username</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="friend" items="${sessionScope.user.friends}">
                <tr>
                    <td><c:out value='${friend.username}'/></td>
                    <td><c:out value='${friend.status}'/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <label class="label" for="newstatus">Change Status</label>
        <div class="field is-grouped">
            <div class="control">
                <input id="newstatus" class="input" type="text" placeholder="Online">
            </div>
            <div class="control">
                <button id="changestatus" class="button is-primary">Submit</button>
            </div>
        </div>
        <label class="label" for="newfriendusername">Add Friend</label>
        <div class="field is-grouped">
            <div class="control">
                <input id="newfriendusername" class="input" type="text" placeholder="Username">
            </div>
            <div class="control">
                <button id="addfriend" class="button is-primary">Add</button>
            </div>
        </div>
    </div>
</section>
<script defer type="text/javascript" src="js/chat.js"></script>
<jsp:include page="footer.jsp"/>
</body>
</html>