package db;

import domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryStub implements UserRepository {
    private Map<String, User> users = new HashMap<String, User>();

    public UserRepositoryStub() {
        User bob = new User("Bob", "t");
        add(bob);
        User jan = new User("Jan", "t");
        add(jan);
        User an = new User("An", "t");
        add(an);

        bob.addFriend(jan);
        jan.addFriend(bob);
    }

    public User get(String username) {
        if (username == null) {
            throw new IllegalArgumentException("No username given");
        }
        return users.get(username);
    }

    public List<User> getAll() {
        return new ArrayList<User>(users.values());
    }

    public void add(User user) {
        if (user == null) {
            throw new IllegalArgumentException("No user given");
        }
        if (users.containsKey(user.getUsername())) {
            throw new IllegalArgumentException("User already exists");
        }
        users.put(user.getUsername(), user);
    }

    public void update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("No user given");
        }
        users.put(user.getUsername(), user);
    }

    public void delete(String username) {
        if (username == null) {
            throw new IllegalArgumentException("No username given");
        }
        users.remove(username);
    }

    public User getAuthenticatedUser(String username, String password) {
        User user = get(username);

        if (user != null && user.isCorrectPassword(password)) {
            return user;
        } else {
            return null;
        }
    }
}
