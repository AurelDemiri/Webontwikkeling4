package controller;

import domain.ChatService;

public class ControllerFactory {

    public RequestHandler getController(String key, ChatService model) {
        return createHandler(key, model);
    }

    private RequestHandler createHandler(String handlerName, ChatService model) {
        RequestHandler handler = null;
        try {
            Class<?> handlerClass = Class.forName("controller." + handlerName);
            Object handlerObject = handlerClass.newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model);
        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!!!!");
        }
        return handler;
    }


}
