package controller;

import domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddFriend extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        User myUser = getLoggedInUser(request);
        User userToAdd = getUserService().getUser(request.getParameter("username"));

        if (!isUserLoggedIn(request) || userToAdd == null || userToAdd.equals(myUser)) {
            // We use setStatus instead of sendError to prevent a useless body being sent to client.
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        else {
            myUser.addFriend(userToAdd);
            userToAdd.addFriend(myUser);
        }

        // Empty string as destination prevents sending a useless body to client
        return "";
    }

}
