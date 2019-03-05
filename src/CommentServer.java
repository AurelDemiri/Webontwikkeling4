import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.ChatService;
import domain.Comment;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/comment")
public class CommentServer {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
    private final ChatService chatService = ChatService.getInstance();
    private ObjectMapper mapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + " has opened a connection");
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message from " + session.getId() + ": " + message);

        try {
            Comment comment = mapper.readValue(message, Comment.class);
            chatService.getBlogPost(comment.getBlogId()).addComment(comment);
            sendMessageToAll(message);
        } catch (IOException e) {
            e.printStackTrace();
            // sendMessage(session,"{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Chat " + session.getId() + " has ended");
        sessions.remove(session);
    }

    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessageToAll(String message) {
        for (Session s : sessions) {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
