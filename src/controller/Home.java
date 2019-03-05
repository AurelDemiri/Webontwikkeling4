package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Home extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        request.setAttribute("blogposts", getChatService().getBlogPosts());
        return "index.jsp";
    }
}