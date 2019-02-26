package db;

import domain.User;

import java.util.List;

public interface UserRepository {

    public abstract void add(User user);

    public abstract void delete(String userId);

    public abstract User get(String userId);

    public abstract List<User> getAll();

    public abstract User getAuthenticatedUser(String email, String password);

    public abstract void update(User user);

}