package domain;

import db.BlogRepository;
import db.BlogRepositoryStub;
import db.UserRepository;
import db.UserRepositoryStub;

import java.util.List;
import java.util.Map;

public class ChatService {
    private static ChatService chatService;
    private UserRepository userRepository = new UserRepositoryStub();
    private BlogRepository blogRepository = new BlogRepositoryStub();

    public static ChatService getInstance() {
        if (chatService == null)
            chatService = new ChatService();

        return chatService;
    }

    public User getUser(String username) {
        return userRepository.get(username);
    }

    public List<User> getUsers() {
        return userRepository.getAll();
    }

    public void addUser(User user) {
        userRepository.add(user);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteUser(String id) {
        userRepository.delete(id);
    }

    public User getAuthenticatedUser(String username, String password) {
        return userRepository.getAuthenticatedUser(username, password);
    }

    public BlogPost getBlogPost(Integer blogPostId) {
        return blogRepository.get(blogPostId);
    }

    public Map<Integer, BlogPost> getBlogPosts() {
        return blogRepository.getAll();
    }

    public void addBlogPost(BlogPost blogPost) {
        blogRepository.add(blogPost);
    }

    public void deleteBlogPost(Integer blogPostId) {
        blogRepository.delete(blogPostId);
    }
}
