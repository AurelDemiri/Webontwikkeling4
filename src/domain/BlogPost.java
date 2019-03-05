package domain;

import java.util.ArrayList;
import java.util.List;

public class BlogPost {
    private String title;
    private List<Comment> comments;

    public BlogPost(String title) {
        setTitle(title);
        this.comments = new ArrayList<>();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title.isEmpty())
            throw new IllegalArgumentException("Title should not be empty");

        this.title = title;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public boolean addComment(Comment comment) {
        if (comment == null)
            throw new IllegalArgumentException("Comment should not be null");

        return this.comments.add(comment);
    }

    public boolean removeComment(Comment comment) {
        if (comment == null)
            throw new IllegalArgumentException("Comment should not be null");

        return this.comments.remove(comment);
    }
}
