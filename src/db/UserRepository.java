package db;

import domain.User;

import java.util.List;

public interface UserRepository {

    void add(User user);

    void delete(String userId);

    User get(String userId);

    List<User> getAll();

    User getAuthenticatedUser(String email, String password);

    void update(User user);

}