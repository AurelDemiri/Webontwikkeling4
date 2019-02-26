var changeStatusButton = document.getElementById("changestatus");
changeStatusButton.onclick = changeStatus;
var currentStatusTextElement = document.getElementById("currentstatus");
var newStatusElement = document.getElementById("newstatus");

var addFriendButton = document.getElementById("addfriend");
addFriendButton.onclick = addFriend;
var newFriendUsernameTextElement = document.getElementById("newfriendusername");

var xhr = new XMLHttpRequest();

var timer = setInterval(updateFriendsList, 5000);

function changeStatus() {
    if (newStatusElement.value.length > 0) {
        xhr.open("POST", "/Controller?action=ChangeStatus", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                currentStatusTextElement.innerHTML = newStatusElement.value;
            }
        }
        xhr.send("newstatus=" + newStatusElement.value);
    } else {
        alert("Status can not be empty!");
    }
}

function addFriend() {
    if (newFriendUsernameTextElement.value.length > 0) {
        xhr.open("POST", "/Controller?action=AddFriend", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE) {
                if (this.status === 200) {
                    // lazy method
                    updateFriendsList();
                    newFriendUsernameTextElement.value = "";
                } else {
                    alert("User does not exist or there was a server error!");
                }
            }
        }
        xhr.send("username=" + newFriendUsernameTextElement.value);
    } else {
        alert("Username can not be empty!");
    }
}

function updateFriendsList() {
    xhr.open("GET", "/Controller?action=GetFriends", true);
    xhr.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var friends = JSON.parse(xhr.responseText);
            var tbody = document.getElementsByTagName("TBODY")[0];

            // Clear tbody html
            tbody.innerHTML = ""

            // Update tbody
            for (var i = 0; i < friends.length; i++) {
                var row = document.createElement("TR");
                var tdUsername = document.createElement("TD");
                tdUsername.innerText = friends[i].username;
                var tdStatus = document.createElement("TD");
                tdStatus.innerText = friends[i].status;

                row.appendChild(tdUsername);
                row.appendChild(tdStatus);
                tbody.appendChild(row);
            }
        }
    }
    xhr.send();
}