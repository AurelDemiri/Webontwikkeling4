package controller;

import domain.ChatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ControllerFactory controllerFactory = new ControllerFactory();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String destination = "index.jsp";

        RequestHandler handler;
        try {
            handler = controllerFactory.getController(action == null ? "Home" : action, ChatService.getInstance());
            destination = handler.handleRequest(request, response);
        } catch (NotAuthorizedException exc) {
            List<String> errors = new ArrayList<>();
            errors.add(exc.getMessage());
            request.setAttribute("errors", errors);
            destination = "index.jsp";
        }

        if (!destination.isEmpty() && response.getStatus() != HttpServletResponse.SC_MOVED_TEMPORARILY) {
            request.getRequestDispatcher(destination).forward(request, response);
        }
    }
}
