package controller;

import domain.User;
import domain.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogIn extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        if (isUserLoggedIn(request)) {
            response.sendRedirect("/");
        }

        List<String> errors = new ArrayList<String>();

        String username = request.getParameter("username");
        if (username == null || username.isEmpty()) {
            errors.add("No username given");
        }

        String password = request.getParameter("password");
        if (password == null || password.isEmpty()) {
            errors.add("No password given");
        }

        if (errors.size() == 0) {
            UserService userService = super.getUserService();
            User user = userService.getAuthenticatedUser(username, password);
            if (user != null) {
                createSession(user, request, response);
                response.sendRedirect("/");
            } else {
                errors.add("No valid username/password");
            }
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        }

        return "index.jsp";
    }

    private void createSession(User user, HttpServletRequest request,
                               HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }
}
