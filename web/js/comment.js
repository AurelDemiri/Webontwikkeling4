var webSocket = new WebSocket("ws://localhost:8080/comment");

webSocket.onmessage = function (event) {
    var data = JSON.parse(event.data);
    data["name"] = escapeHtml(data["name"]);
    data["content"] = escapeHtml(data["content"]);

    var blogPostMessage = document.getElementById("blogpost-" + data["blogId"]).querySelectorAll('[class=message-body]')[0];
    blogPostMessage.innerHTML += "<strong>" +
        data["name"] +
        ": </strong>" +
        data["content"] +
        " <span class=\"icon is-small\"><i class=\"fas fa-star\"></i></span> " +
        data["rating"] +
        "<br />"
};

function postComment(id) {
    var blogPost = document.getElementById("blogpost-" + id);
    var data = {};
    data["blogId"] = id;
    data["name"] = blogPost.querySelectorAll('[name=name]')[0].value;
    data["content"] = blogPost.querySelectorAll('[name=comment]')[0].value;
    data["rating"] = parseInt(blogPost.querySelectorAll('[name=rating]')[0].value);

    validComment(data);

    webSocket.send(JSON.stringify(data));
}

function validComment(data) {
    if (data["name"].length < 1) {
        alert("Name can not be empty");
    }

    if (data["rating"] < 1 || data["rating"] > 10) {
        alert("Rating should be between 1 and 10");
    }
}

function escapeHtml(unsafe) {
    return unsafe
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}