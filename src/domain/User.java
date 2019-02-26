package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String salt;
    private String status;
    @JsonIgnore
    private List<User> friends;

    public User(String username, String password) {
        setUsername(username);
        setHashedPassword(password);
        status = "Online";
        friends = new ArrayList<>();
    }

    public User(String username, String password, String salt) {
        setUsername(username);
        setPassword(password);
        setSalt(salt);
        status = "Online";
        friends = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.isEmpty()) {
            throw new IllegalArgumentException("No username given");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        this.password = password;
    }

    public boolean isCorrectPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        return getPassword().equals(hashPassword(password, getSalt()));
    }

    public void setHashedPassword(String password) {
        if (password.isEmpty()) {
            throw new IllegalArgumentException("No password given");
        }
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] seed = random.generateSeed(20);

        String salt = new BigInteger(1, seed).toString(16);
        this.setSalt(salt);

        return hashPassword(password, salt);
    }

    private String hashPassword(String password, String seed) {
        String hashedPassword = null;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(salt.getBytes("UTF-8"));
            crypt.update(password.getBytes("UTF-8"));
            hashedPassword = new BigInteger(1, crypt.digest()).toString(16);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new DomainException(e.getMessage(), e);
        }
        return hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (username.isEmpty()) {
            throw new IllegalArgumentException("Status can not be empty");
        }
        this.status = status;
    }

    public List<User> getFriends() {
        return this.friends;
    }

    public boolean addFriend(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Nobody can add null as a friend..");
        }

        if (this.friends.contains(user)) {
            return true;
        }

        return this.friends.add(user);
    }

    public boolean removeFriend(User user) {
        if (user == null) {
            throw new IllegalArgumentException("null is not your friend.");
        }
        return this.friends.remove(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
