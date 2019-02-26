<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header role="banner">
    <img alt="Cute Dogs" src="images/doggies.png">
    <h1><span>Chat App</span></h1>
    <nav>
        <ul>
            <c:if test="${sessionScope.user != null}">
                <li><a <c:if test="${param.title == 'Chat'}">id="actual" </c:if>href="Controller?action=Chat">Chat</a></li>
            </c:if>
            <li><a <c:if test="${param.title == 'Home'}">id="actual" </c:if>href="./">Home</a></li>
        </ul>
    </nav>
    <h2>
        ${param.title}
    </h2>

</header>