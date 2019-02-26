package domain;

import db.UserRepository;
import db.UserRepositoryStub;

import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepositoryStub();

    public UserService() {
    }

    public User getUser(String username) {
        return getUserRepository().get(username);
    }

    public List<User> getUsers() {
        return getUserRepository().getAll();
    }

    public void addUser(User user) {
        getUserRepository().add(user);
    }

    public void updateUser(User user) {
        getUserRepository().update(user);
    }

    public void deleteUser(String id) {
        getUserRepository().delete(id);
    }

    public User getAuthenticatedUser(String username, String password) {
        return getUserRepository().getAuthenticatedUser(username, password);
    }

    private UserRepository getUserRepository() {
        return userRepository;
    }
}
