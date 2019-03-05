package domain;

public class Comment {
    private Integer blogId = 0;
    private String name;
    private String content;
    private Integer rating;

    public Comment() {}

    public Comment(String name, String content, Integer rating) {
        setName(name);
        setContent(content);
        setRating(rating);
    }

    public Comment(Integer blogId, String name, String content, Integer rating) {
        setBlogId(blogId);
        setName(name);
        setContent(content);
        setRating(rating);
    }

    public Integer getBlogId() {
        return this.blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name.isEmpty())
            throw new IllegalArgumentException("Name should not be empty");

        this.name = name;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        if (rating < 1 || rating > 10)
            throw new IllegalArgumentException("Rating should be between 1 and 10");

        this.rating = rating;
    }
}
