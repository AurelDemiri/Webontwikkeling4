package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetFriends extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        if (!isUserLoggedIn(request)) {
            // We use setStatus instead of sendError to prevent a useless body being sent to client.
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        else {
            List<User> friends = getLoggedInUser(request).getFriends();
            ObjectMapper mapper = new ObjectMapper();
            String friendsJSON = mapper.writeValueAsString(friends);
            response.setContentType("application/json");
            response.getWriter().write(friendsJSON);
        }

        // Empty string as destination prevents sending a useless body to client
        return "";
    }

}
