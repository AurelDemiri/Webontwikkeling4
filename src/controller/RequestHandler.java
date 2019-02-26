package controller;

import domain.User;
import domain.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {

    private UserService userService;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;

    public void setModel(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    protected boolean isUserLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

    protected User getLoggedInUser(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            throw new IllegalArgumentException("User is not logged in");
        }
        return user;
    }
}
