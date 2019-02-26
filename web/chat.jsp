<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Chat"/>
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chat"/>
</jsp:include>
<main>
    <p id="currentstatus"><c:out value="${sessionScope.user.status}"/></p>
    <table>
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
    <p>Change Status:</p>
    <input id="newstatus" type="text" placeholder="Online"/>
    <button id="changestatus">Change</button>
    <p>Add Friend:</p>
    <input id="newfriendusername" type="text" placeholder="Username"/>
    <button id="addfriend">Add</button>
</main>
<script type="text/javascript" src="js/chat.js"></script>
<jsp:include page="footer.jsp"/>
</body>
</html>