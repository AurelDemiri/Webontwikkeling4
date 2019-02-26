package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        if (isUserLoggedIn(request)) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("/");
        }
        return "index.jsp";
    }

}
