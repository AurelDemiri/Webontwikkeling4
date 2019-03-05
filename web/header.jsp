<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <section class="hero is-primary is-bold">
        <div class="hero-body">
            <div class="container">
                <h1 class="title">
                    Chat App
                </h1>
            </div>
        </div>
    </section>
    <div class="tabs is-right">
        <ul>
            <c:if test="${sessionScope.user != null}">
            <li<c:if test="${param.title == 'Chat'}"> class="is-active"</c:if>>
                <a href="Controller?action=Chat">
                    <span class="icon is-small"><i class="fas fa-comment-alt" aria-hidden="true"></i></span>
                    <span>Chat</span>
                </a>
            </li>
            </c:if>
            <li<c:if test="${param.title == 'Home'}"> class="is-active"</c:if>>
                <a href="./">
                    <span class="icon is-small"><i class="fas fa-home" aria-hidden="true"></i></span>
                    <span>Home</span>
                </a>
            </li>
        </ul>
    </div>
</header>