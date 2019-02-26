package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeStatus extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        String newStatus = request.getParameter("newstatus");

        if (!isUserLoggedIn(request) || newStatus == null || newStatus.isEmpty()) {
            // We use setStatus instead of sendError to prevent a useless body being sent to client.
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        else {
            getLoggedInUser(request).setStatus(newStatus);
        }

        // Empty string as destination prevents sending a useless body to client
        return "";
    }

}
